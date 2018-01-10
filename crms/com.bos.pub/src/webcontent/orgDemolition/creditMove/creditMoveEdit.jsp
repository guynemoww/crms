<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-04-15

  - Description:TB_SYS_BUSINESS_TRANSFER, com.bos.pub.sys.TbSysBusinessTransfer-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbSysBusinessTransfer" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>原开户行机构编号：</label>
		<input name="tbSysBusinessTransfer.oldOrgNum" required="true" class="nui-buttonEdit" vtype="maxLength:32" onbuttonclick="selectEmpOrg" dictTypeId="org"/>
		<label>原用户编号：</label>
		<input name="tbSysBusinessTransfer.oldUserNum" required="true"  class="nui-buttonEdit" onbuttonclick="selectCustManeger"  vtype="maxLength:32" dictTypeId="user"/>
		<label>客户编号：</label>
		<input name="tbSysBusinessTransfer.customerNum" required="true" class="nui-textbox nui-form-input" onbuttonclick="" vtype="maxLength:20" />
		<label>批复编号：</label>
		<input name="tbSysBusinessTransfer.borrowNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
		<label>管理团队性质：</label>
		<input name="tbSysBusinessTransfer.manageTeamStatus" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="CsmTeamAuthorityType"/>
		<label>目标机构编号：</label>
		<input name="tbSysBusinessTransfer.newOrgNum" required="true" class="nui-buttonEdit" vtype="maxLength:32" onbuttonclick="selectEmpOrg" dictTypeId="org"/>
		<label>目标用户编号：</label>
		<input name="tbSysBusinessTransfer.newUserNum" required="true" class="nui-buttonEdit" onbuttonclick="selectCustManeger" vtype="maxLength:32" dictTypeId="user"/>
		<label>移交状态：</label>
		<input name="tbSysBusinessTransfer.repFlag" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD7493"/>
		<label>移交时间：</label>
		<input name="tbSysBusinessTransfer.handingDate" required="true" class="nui-textbox nui-form-input" dateformat="yyyy-MM-dd HH:mm:ss"/>


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
	var json=nui.encode({"tbSysBusinessTransfer":
		{"orgcbAppId":
		"<%=request.getParameter("orgcbAppId") %>"}});
	$.ajax({
        url: "com.bos.pub.orgDemolition.getTbSysBusinessTransfer.biz.ext",
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
        url: "com.bos.pub.orgDemolition.updateTbSysBusinessTransfer.biz.ext",
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
