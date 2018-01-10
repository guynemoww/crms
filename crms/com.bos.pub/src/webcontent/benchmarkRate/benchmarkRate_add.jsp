<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>新增基准利率</title>
<%@include file="/common/nui/common.jsp"%>

</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
			<div class="nui-dynpanel" columns="4">
				<label>利率编号：</label>
				<input   class="nui-textbox nui-form-input" name="tbSysBenchmarkRate.intRateCd" required="true" />	
					
				<label>利率名称：</label>
				<input   class="nui-textbox nui-form-input" name="tbSysBenchmarkRate.intRateName" required="true" />
						
				<label>币种：</label>
				<input   class="nui-dictcombobox nui-form-input" name="tbSysBenchmarkRate.currencyCd" required="true" dictTypeId="CD000001" />
				
				<label>利率值：</label>
				<input   class="nui-textbox nui-form-input" name="tbSysBenchmarkRate.intRate" required="true" />
				
				<label>生效日期：</label>
				<input id="corp/businessLicenseNum" class="nui-DatePicker nui-form-input" name="tbSysBenchmarkRate.validDate" required="true" />
				
				<label>失效日期：</label>
				<input id="corp/businessLicenseNum1" class="nui-DatePicker nui-form-input" name="tbSysBenchmarkRate.invalidDate" required="true" />
		</div>
		
	</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-save" onclick="save()">保存</a>
    <span style="display:inline-block;width:25px;"></span>
</div>

<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var rateTypes={
		'01':'DepositeRate',
		'02':'NormalDepositeRate',
		'03':'HousingProvidentFundLoanRate',
		'04':'CentralBankReloanRate'
	};
	
	function rateTypeChange(e){
		nui.get("rateDescription").changeDictTypeId(rateTypes[this.value]);
	}
    //比较时间大小
    function toDate(str){
     var sd=str.split("-");
    return new Date(sd[0],sd[1],sd[2]);
      }

	function save(){
		//校验
		form.validate();
      if (form.isValid()==false) return;
       var time;
       var a=new Date();
       //当前时间
      var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
      //生效时间
        time=nui.get("corp/businessLicenseNum").getValue();
        //失效时间
         time3 =nui.get("corp/businessLicenseNum1").getValue();
        var time2=time.split(" ");
         var time4=time3.split(" ");
        var d1=toDate(time2[0]);
		var d2=toDate(time1);
		var d3=toDate(time4[0]);
		if(d3<d1){
			alert("失效时间应该晚于生效时间");
			return;
		}
		if(d1>d2){
				var o = form.getData();
				o.tbSysBenchmarkRate.status = '02'
		        var json = nui.encode(o);
		        $.ajax({
		            url: "com.bos.pub.benchmarkRate.addNewcomponent.addBenchmarkRate.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
			            	if(text.msg){
			            		nui.alert(text.msg);
			            	} else {
			            		CloseWindow("ok");
			            	}
			            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
		        });
		}else{
		   alert("生效时间应该晚于当前时间");
		   return;
		}
	}
	
	
</script>
</body>
</html>