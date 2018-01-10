<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 卢金彬
  - Date: 2014-02-14

  - Description:TB_SYS_COLLATERAL_RATE, com.bos.dataset.sys.TbSysCollateralRate-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
<input type="hidden" name="tbSysCollateralRate"  class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
		<label>担保类型：</label>
		<input name="tbSysCollateralRate.guaranteeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1185"/>

		<label>担保物属性：</label>
		<input name="tbSysCollateralRate.collateralProperty" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_DBCD3004"/>

		<label>担保物主类：</label>
		<input name="tbSysCollateralRate.collateralMainType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />

		<label>担保物子类：</label>
		<input name="tbSysCollateralRate.collateralSubType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />

		<label>适用机构级别：</label>
		<input name="tbSysCollateralRate.applicationOrgLevel" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_GGCD6004"/>

		<label>适用机构编码：</label>
		<input name="tbSysCollateralRate.applicationOrgNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>适用业务种类：</label>
		<input id="yewuzhonglei" name="tbSysCollateralRate.applicationBusinessType" required="false"  
			dictTypeId="product" class="nui-buttonEdit" vtype="maxLength:6" onbuttonclick="selectPinZhong"/>

		<label>适用客户信用等级：</label>
		<input name="tbSysCollateralRate.applicationCustCredit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_KHCD0019"/>

		<label>抵质押率（%）：</label>
		<input name="tbSysCollateralRate.collateralRate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>生效日期：</label>
		<input id="rateEffectDate" name="tbSysCollateralRate.rateEffectDate" required="true" class="nui-datepicker nui-form-input" />

		<label>经办机构名称：</label>
		<input name="tbSysCollateralRate.orgNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" dictTypeId="org" enabled="false"/>

		<label>经办用户名称：</label>
		<input name="tbSysCollateralRate.userNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" dictTypeId="user" enabled="false"/>

		<label>经办日期：</label>
		<input name="tbSysCollateralRate.handlingDate" required="true" class="nui-datepicker nui-form-input" />

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
      


function initForm() {
	var json=nui.encode({"tbSysCollateralRate":
		{"collateralRateId":
		"<%=request.getParameter("collateralRateId") %>"}});
	$.ajax({
        url: "com.bos.pub.TbSysCollateralRate.getTbSysCollateralRate.biz.ext",
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
	
	var time;
       var a=new Date();
       //当前时间
      var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
      //生效时间
        time=nui.get("rateEffectDate").getValue();
        var time2=time.split(" ");
        var d1=toDate(time2[0]);
		var d2=toDate(time1);
		if(d1>d2){
							var o=form.getData();
							var json=nui.encode(o);
							//nui.alert(json);return;
							$.ajax({
						        url: "com.bos.pub.TbSysCollateralRate.updateTbSysCollateralRate.biz.ext",
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


function selectPinZhong(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/collateralrate/item_tree.jsp",
            showMaxButton: true,
            title: "选择业务品种",
            width: 230,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                alert("===");
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.json;
                    data = nui.clone(data);
                   //  alert(nui.encode(data));
                    if (data) {
                        btnEdit.setValue(data.split(":")[0]);
                       btnEdit.setText(data.split(":")[1]);
                    }
                }
            }
        });            
    }
    
    
	</script>
</body>
</html>
