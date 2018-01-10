<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s):WangHui
  - Date: 2016-05-23
-->
<head>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
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
		String enabled = "1".equals(edit) ? "true" : "false";
		String[][] tables = {{"移交资料：", "transfer.information"},
		{"移交原因：", "transfer.tasCause"},
		{"不良资产现在描述：", "transfer.assetDescribe"},
		{"回收可能性分析：", "transfer.reclaimAnalyse"},
		{"已采取的回收措施：", "transfer.reclaimAction"}};
	%>
	<div id="form1" style="height: auto; overflow: hidden;">
		<input name="transfer.id" class="nui-hidden" />
		<fieldset>
			<legend>合同信息</legend>
			<div class="nui-dynpanel" columns="4">
				<label>合同编号</label>
				<input id="con.contractNum" name="con.contractNum" class="nui-textbox " enabled="false" />
				<label>客户名称</label>
				<input id="con.partyName" name="con.partyName" class="nui-textbox " enabled="false" />
				<label>证件类型</label>
				<input id="con.certType" name="con.certType" class="nui-buttonedit" dictTypeId="CDKH0002" enabled="false" />
				<label>证件号码</label>
				<input id="con.certNum" name="con.certNum" class="nui-textbox " enabled="false" />
				<label>合同金额</label>
				<input id="con.contractAmt" name="con.contractAmt" class="nui-textbox " enabled="false" />
				<label>合同已用金额</label>
				<input id="con.conYuE" name="con.conYuE" required="true" class="nui-textbox " enabled="false" />
				<label>经办机构：</label>
				<input id="transfer.conOrgNum" name="transfer.conOrgNum" class="nui-buttonedit" enabled="false" dictTypeId="org" />
				<label>经办人：</label>
				<input id="transfer.conUserNum" name="transfer.conUserNum" class="nui-buttonedit" enabled="fasle" dictTypeId="user" />
			</div>
		</fieldset>
		<fieldset>
			<legend>移交信息</legend>
			<div class="nui-dynpanel" columns="4">
				<label>移交编号：</label>
				<input id="transfer.transferNum" name="transfer.transferNum" required="true" class="nui-textbox" enabled="false" />
				<label>经办日期：</label>
				<input id="transfer.tasDate" name="transfer.tasDate" required="true" allowInput="false" class="nui-datepicker" enabled="<%=enabled%>" />
				<label>移交后机构：</label>
				<input id="transfer.tasOrgNum" name="transfer.tasOrgNum" required="true" allowInput="false" class="nui-buttonEdit" dictTypeId="org" enabled="false" />
				<label>移交后客户经理：</label>
				<input id="transfer.tasUserNum" name="transfer.tasUserNum" required="true" allowInput="false" class="nui-buttonEdit" dictTypeId="user" onbuttonclick="selectCustManegers" enabled="<%=enabled%>" />
				<label>经办机构：</label>
				<input id="transfer.orgNum" name="transfer.orgNum" enabled="false" class="nui-buttonEdit" dictTypeId="org" enabled="<%=enabled%>" />
				<label>经办人：</label>
				<input id="transfer.userNum" name="transfer.userNum" enabled="fasle" class="nui-buttonEdit" dictTypeId="user" enabled="<%=enabled%>" />
			</div>
			<div style="height: 11px"></div>
			<table style="width: 100%">
				<%
					for(String [] t:tables){
				%>
				<tr>
					<td class="tdLabel">
						<label><%=t[0]%></label>
					</td>
					<td class="tdText">
						<input id="<%=t[1]%>" name="<%=t[1]%>" required="true" class="nui-textarea" vtype="maxLength:800" style="width: 92%; height: 100%" enabled="<%=enabled%>" />
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</fieldset>
	</div>
	<div class="nui-toolbar" style="border: none; padding-right: 20px; text-align: right;">
		<a class="nui-button" iconCls="icon-save" id="btnSave" enabled="<%=enabled%>" onclick="save">保存</a>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		initPage();
		function initPage() {
			var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
			$.ajax({
				url : "com.bos.asset.AssetsTransfer.getTransfer.biz.ext",
				type : 'POST',
				data : nui.encode({
					"transferId" : transferId,
					"showConInfo" : "true"
				}),
				cache : false,
				contentType : 'text/json',
				success : function(data) {
					if (data.msg) {
						alert(data.msg);
						return;
					} else {
						form.setData(data);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function save() {
			var data = validateForm(form, "请按规则填写内容");
			if (!data) {
				return;
			}
			var json = nui.encode(data);
			$.ajax({
				url : "com.bos.asset.AssetsTransfer.saveTransfer.biz.ext",
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
						nui.alert(actionSuccess);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		// 新客户经理
		function selectCustManegers(e) {
			var btnEdit = this;
			nui.open({
				url : nui.context
						+ "/pub/user/select_user.jsp?orgMode=legorg&clear=0",
				showMaxButton : true,
				title : "选择客户经理",
				width : 850,
				height : 450,
				ondestroy : function(action) {
					if (action == "ok") {
						btnEdit.setText(null);
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						nui.get("transfer.tasOrgNum").setValue(data.orgNum);
						btnEdit.setValue(data.userNum);
					}
				}
			});
		}
	</script>
</body>
</html>
