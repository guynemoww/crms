<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" id="limit.limitId" name="limit.limitId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
			
		<label>监管限额类型：</label>
		<input name="limit.limitType" id="limit.limitType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0230" />
		
		<label>资本净额：</label>
		<input name="limit.limitCap" id="limit.limitCap" required="true" class="nui-textbox nui-form-input" vtype="float;range:1,10000000000000" dataType="currency" onblur="getXe"/>

		<label>客户集中度比例%：</label>
		<input name="limit.limitSacle" id="limit.limitSacle" required="true" class="nui-textbox nui-form-input" vtype="float;range:1,100" onblur="getXe"/>
		
		<label>客户集中度限额：</label>
		<input name="limit.limitAmt" id="limit.limitAmt" required="true" class="nui-text nui-form-input" vtype="float;range:1,10000000000000" dataType="currency" />

		<label>经办人：</label>
		<input name="limit.updateUserNum" id="limit.updateUserNum" enabled="false" class="nui-text nui-form-input"  dictTypeId="user"/>
		
		<label>经办机构：</label>
		<input name="limit.updateOrgNum" id="limit.updateOrgNum" enabled="false" class="nui-text nui-form-input" dictTypeId="org" />
		
		<label>经办时间：</label>
		<input name="limit.updateDate" id="limit.updateDate" class="nui-text nui-form-input" enabled="false" format="yyyy-MM-dd"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border:0;text-align:right;">
	<a class="nui-button" id="btn" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()" id="btnClose">关闭</a>
</div>

<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form");
    var limitId = "<%=request.getParameter("limitId") %>";
    var chargeType = "02";
    if("" != limitId && null != limitId && 'null' != limitId){
    	nui.get("limit.limitType").setEnabled(false);
    }else{
    	limitId = null;
    }
	initForm();//初始化表单数据
	//表单初始化--读取监管限额信息
	function initForm() {
		if(limitId){
			var json = nui.encode({"limitId":limitId});
			$.ajax({
		        url: "com.bos.crd.LimitService.getRiskLimit.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	var o = nui.decode(text);
		        	form.setData(o);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}else{
			nui.get("limit.updateUserNum").setValue("<%=GitUtil.getCurrentUserId() %>");
			nui.get("limit.updateOrgNum").setValue("<%=GitUtil.getCurrentOrgCd() %>");
			nui.get("limit.updateDate").setValue("<%=GitUtil.getBusiDateStr() %>");
		}	
	}
	function getXe(){
		var zbje = nui.get("limit.limitCap").getValue();
		var jzdbl = nui.get("limit.limitSacle").getValue();
		if(0==zbje || 0==jzdbl){
			return;
		}
		if(zbje.indexOf('-') != "-1"){
			nui.alert("净资本额不能录入负值");
			return;
		}
		if(jzdbl.indexOf('-') != "-1"){
			nui.alert("集中度比例不能录入负值");
			return;
		}
		var jzdxe = (parseFloat(zbje)*parseFloat(jzdbl)/100);
		nui.get("limit.limitAmt").setValue(jzdxe);
	}
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请按照规则将信息填写完整");
			return;
		}
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	        url: "com.bos.crd.LimitService.saveChargeLimit.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var o = nui.decode(text);
	        	if("" == o.msg || null== o.msg){
		        	initForm();
	        		nui.alert("监管限额已生效");
	        		return;
        		}
        		alert(o.msg);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
	}
</script>
</body>
</html>
