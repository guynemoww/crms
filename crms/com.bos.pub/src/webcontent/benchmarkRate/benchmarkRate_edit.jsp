<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 2013-11-28

  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="tbSysBasicRate.intRateId" name="tbSysBasicRate.intRateId" class="nui-hidden" value="<%=request.getParameter("intRateId") %>"/>	
	<input id="tbSysBasicRate.status" name="tbSysBasicRate.status" class="nui-hidden" />	
	<div class="nui-dynpanel" columns="4">
			<label>利率编号：</label>
			<input   class="nui-textbox nui-form-input" name="tbSysBasicRate.intRateCd" required="true" enabled="false"/>	
			
			<label>利率名称：</label>
			<input   class="nui-textbox nui-form-input" name="tbSysBasicRate.intRateName" required="true" enabled="false" />
					
		<%--<label>币种：</label>
			<input   class="nui-dictcombobox nui-form-input" name="tbSysBasicRate.currencyCd" required="true" dictTypeId="CD000001" />
			--%>
			<label>利率值：</label>
			<input   class="nui-textbox nui-form-input" name="tbSysBasicRate.intRate" required="true" />
			
			<%--<label>生效日期：</label>
			<input id="corp/businessLicenseNum" class="nui-DatePicker nui-form-input" name="tbSysBasicRate.validDate" required="true" />
			
			<label>失效日期：</label>
			<input id="corp/businessLicenseNum1" class="nui-DatePicker nui-form-input" name="tbSysBasicRate.invalidDate" required="true" />
			--%>
			<%--<label>利率类型：</label>
			<input class="nui-dictcombobox nui-form-input" required="true" name="tbSysBasicRate.rateType" id="rateType" dictTypeId="RateType" onvaluechanged="rateTypeChange"/>
			
			<label>利率描述：</label>
			<input id="rateDescription" name="tbSysBasicRate.rateDescription" data="data" valueField="dictID"  required="false" 
				 class="nui-dictcombobox nui-form-input"/>
			
			<label>利率数值：</label>
			<input id="corp/orgnNum" onvalidation=""  class="nui-textbox nui-form-input" name="tbSysBasicRate.rateNum" required="true" vtype="int"/>
						
			<label>利率期限：</label>
			<input id="corp/businessLicenseNum" class="nui-textbox nui-form-input" name="tbSysBasicRate.rateTerm" required="true" vtype="int"/>
		
			<label>利率期限单位：</label>
			<input class="nui-dictcombobox nui-form-input" name="tbSysBasicRate.rateTermUnit" id="nation" dictTypeId="RateTermUnit"/>
			
			<label>生效日期：</label>
			<input id="corp/businessLicenseNum" class="nui-DatePicker nui-form-input" name="tbSysBasicRate.rateEffectDate" required="true" />
			<label>经办日期：</label>
				<input name="tbSysBasicRate.handlingDate" required="true" class="nui-datepicker nui-form-input" enabled="false"/>
		
				<label>经办机构：</label>
				<input name="tbSysBasicRate.orgNum" required="false" class="nui-buttonEdit"  vtype="maxLength:9" enabled="false" dictTypeId="org" />
		
				<label>经办人员：</label>
				<input name="tbSysBasicRate.userNum" required="false" class="nui-buttonEdit"  vtype="maxLength:10" enabled="false" dictTypeId="user" />--%>
		</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

	//nui.get("tbSysBasicRate.intRateId").setValue('');
	function initForm() {

		var o = form.getData();
		var json=nui.encode(o);
			
		$.ajax({
	            url: "com.bos.pub.TbSysBenchmarkRate.getTbSysBenchmarkRate.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	var rateTypes={
		'01':'DepositeRate',
		'02':'NormalDepositeRate',
		'03':'HousingProvidentFundLoanRate',
		'04':'CentralBankReloanRate'
	};
	
	
	function rateTypeChange(e){
	//	alert(this.value);
		nui.get("rateDescription").changeDictTypeId(rateTypes[this.value]);
	}
	
	   //比较时间大小
    function toDate(str){
     	var sd=str.split("-");
    	return new Date(sd[0],sd[1],sd[2]);
    }
      
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o=form.getData();
		//o.tbSysBasicRate.status = '01';
		var json=nui.encode(o);
		//nui.alert(json);return;
		//var time;
       	//var a=new Date();
      	//当前时间
      	//var time1='<%=GitUtil.getBusiDateStr()%>';//a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
      	//生效时间
        //time=nui.get("corp/businessLicenseNum").getValue();
      	//失效时间
        //time3 =nui.get("corp/businessLicenseNum1").getValue();
        //var time2=time.split(" ");
        //var time4=time3.split(" ");
        //var d1=toDate(time2[0]);
		//var d2=toDate(time1);
		//var d3=toDate(time4[0]);
		//if(d3<d1){
		//	alert("失效时间应该晚于生效时间");
		//	return;
		//}
		//if(d1>d2){
				$.ajax({
			            url: "com.bos.pub.TbSysBenchmarkRate.updateTbSysBenchmarkRate.biz.ext",
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
		//}else{
		//		  alert("生效时间应该晚于当前时间");
		//  		  return;
		//}
	}
</script>
</body>
</html>
