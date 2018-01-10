<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-13

  - Description:TB_SYS_BAL_CONTROL, com.bos.pub.sys.TbSysBalControl-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbSysBalControl" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	
		
		<label>机构编码：</label>
		<input  name="tbSysBalControl.orgnizastionCd" required="false" class="nui-textbox nui-form-input"  vtype="maxLength:9" enabled="false" />

		<label>机构名称：</label>
		<input name="tbSysBalControl.orgnizastionName" required="false" class="nui-buttonEdit"  vtype="maxLength:30" dictTypeId="org" enabled="false"/>

		<label>机构总限额：</label>
		<input name="tbSysBalControl.orgnizistionBal" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26" />
		<label>机构层级：</label>
		<input  name="tbSysBalControl.orgnizastionLevle" required="true"  class="nui-dictcombobox nui-form-input"  dictTypeId="CDZZ0002" enabled="false"/>
		
		<label>生效日期：</label>
		<input name="tbSysBalControl.effectiveDate" required="true" class="nui-datepicker nui-form-input" />

		<label>经办日期：</label>
		<input name="tbSysBalControl.handingDate" required="true" class="nui-datepicker nui-form-input" enabled="false"/>

		<label>经办机构：</label>
		<input name="tbSysBalControl.handingOrgId" required="false" class="nui-buttonEdit"  vtype="maxLength:9" enabled="false" dictTypeId="org" />

		<label>经办人员：</label>
		<input name="tbSysBalControl.handingUserId" required="false" class="nui-buttonEdit"  vtype="maxLength:10" enabled="false" dictTypeId="user" />

		
		
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
	var json=nui.encode({"tbSysBalControl":
		{"controlId":
		"<%=request.getParameter("controlId") %>"}});
	$.ajax({
        url: "com.bos.pub.orgLimit.getTbSysBalControl.biz.ext",
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

function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.orgLimit.updateTbSysBalControl.biz.ext",
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
}
	</script>
</body>
</html>
