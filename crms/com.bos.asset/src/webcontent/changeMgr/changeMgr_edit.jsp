<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): WangHui
  - Date: 2017-05-08 17:15:08
  - Description:
-->
<head>
<title>管户变更</title>
<%@include file="/common/nui/common.jsp"%>
<style type="text/css">
.tdLabel {
	width: 20%;
	text-align: right;
}

.tdText {
	width: 80%;
	height: 60px
}
</style>
</head>
<body>
	<%
		String edit = JspUtil.getParameter(request, "edit", "1");
		String enable = "1".equals(edit) ? "true" : "false";
	%>
	<div id="form1" class="nui-form">
		<fieldset>
			<legend>移交合同信息</legend>
			<div class="nui-dynpanel" columns="4">
				<label>移交编号：</label>
				<input name="tas.TRANSFER_NUM" class="nui-text" enabled="false" />
				<label>合同编号：</label>
				<input name="tas.CONTRACT_NUM" class="nui-text" enabled="false" />
				<label>客户名称：</label>
				<input name="tas.PARTY_NAME" class="nui-text" enable="false" />
				<label>证件类型：</label>
				<input name="tas.CERT_TYPE" class="nui-text" enable="false" dictTypeId="CDKH0002" />
				<label>证件号码：</label>
				<input name="tas.CERT_NUM" class="nui-text" enable="false" />
			</div>
		</fieldset>
		<fieldset>
			<legend>管户变更信息</legend>
			<input name="changeMgr.id" class="nui-hidden" />
			<input name="changeMgr.transferId" class="nui-hidden" />
			<div class="nui-dynpanel" columns="4">
				<label>原管户机构</label>
				<input id="changeMgr.oldOrgNum" name="changeMgr.oldOrgNum" class="nui-buttonedit" dictTypeId="org" enabled="false" />
				<label>原管户人</label>
				<input id="changeMgr.oldUserNum" name="changeMgr.oldUserNum" class="nui-buttonedit" dictTypeId="user" enabled="false" />
				<label>新管户机构</label>
				<input id="changeMgr.changeOrgNum" name="changeMgr.changeOrgNum" required="true" class="nui-buttonedit" dictTypeId="org" enabled="<%=enable%>" onbuttonclick="selectEmpOrgs" />
				<label>新管户人</label>
				<input id="changeMgr.changeUserNum" name="changeMgr.changeUserNum" required="true" class="nui-buttonedit" dictTypeId="user" enabled="<%=enable%>" onbuttonclick="selectCustManegers" />
				<label>经办机构</label>
				<input id="changeMgr.orgNum" name="changeMgr.orgNum" required="true" class="nui-buttonedit" dictTypeId="org" enabled="false" />
				<label>经办人</label>
				<input id="changeMgr.userNum" name="changeMgr.userNum" required="true" class="nui-buttonedit" dictTypeId="user" enabled="false" />
				<label>变更时间</label>
				<input id="changeMgr.changeDate" name="changeMgr.changeDate" required="true" class="nui-datepicker" enabled="<%=enable%>" />
			</div>
			<table style="width: 100%;">
				<tr>
					<td style="width: 20%; text-align: right;">
						<label>变更原因</label>
					</td>
					<td style="width: 60%; height: 65px">
						<input id="changeMgr.changeCause" name="changeMgr.changeCause" style="width: 100%; height: 100%" required="true" class="nui-textarea" enabled="<%=enable%>" />
					</td>
					<td style="width: 20%"></td>

				</tr>
			</table>
		</fieldset>
	</div>
	<div class="nui-toolbar" style="border: none; padding-right: 20px; text-align: right;">
		<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save" enabled="<%=enable%>">保存</a>
	</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	initPage();
	function initPage() {
		var changeMgrId =
<%=JspUtil.getParameterHaveSign(request, "changeMgrId")%>
	;
		var json = nui.encode({
			"changeMgrId" : changeMgrId,
			"showConInfo" : "true"
		});
		nui.ajax({
			url : "com.bos.asset.AssetsChangeMgr.getChangeMgr.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			async : false,
			contentType : 'text/json',
			success : function(data) {
				debugger;
				if (data.msg) {
					alert(data.msg);
					return;
				} else {
					form.setData(data);
				}
			}
		});
	}

	function save() {
		var data = validateForm(form, "请按规则填写");
		if (!data) {
			return;
		}
		var json = nui.encode(data);
		nui.ajax({
			url : "com.bos.asset.AssetsChangeMgr.saveChangeMgr.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			async : false,
			contentType : 'text/json',
			success : function(data) {
				if (data.msg) {
					alert(data.msg);
					return;
				} else {
					alert(actionSuccess);
				}
			}
		});
	}

	// 新机构信息
	function selectEmpOrgs(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp?clear=0",
			showMaxButton : true,
			title : "选择机构",
			width : 350,
			height : 400,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					btnEdit.setText(null);
					btnEdit.setValue(data.orgcode);
				}
				nui.get("changeMgr.changeUserNum").setText(null);
				nui.get("changeMgr.changeUserNum").setValue(null);
			}
		});
	}

	// 新客户经理
	function selectCustManegers(e) {
		var newOrgNum = nui.get("changeMgr.changeOrgNum").getValue();
		if (!newOrgNum || newOrgNum == "") {
			nui.alert("请选择变更后所在机构");
			return;
		}
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/user/select_user.jsp?clear=0&orgNum="
					+ newOrgNum,
			showMaxButton : true,
			title : "选择客户经理",
			width : 850,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					btnEdit.setText(null);
					btnEdit.setValue(data.userNum);
				}
			}
		});

	}
</script>
</html>