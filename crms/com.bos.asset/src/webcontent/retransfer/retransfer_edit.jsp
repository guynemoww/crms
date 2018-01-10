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
<title>不良资产逆移交</title>
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
		String enabled = "1".equals(edit)?"true":"false";
		String [][] tables = {
			{"资产现状描述：","retransfer.assetDescribe"},
			{"已采取的回收措施：","retransfer.reclaimAction"},
			{"申请逆移交原因：","retransfer.retasCause"}};
	%>
	<div id="form1" class="nui-form">
		<fieldset>
			<legend>合同信息</legend>
			<div class="nui-dynpanel" columns="4">
				<label>合同编号：</label>
				<input name="con.CONTRACT_NUM" class="nui-text" enabled="false" />
				<label>客户编号：</label>
				<input name="con.PARTY_NUM" class="nui-text" enable="false" />
				<label>借款人名称：</label>
				<input name="con.PARTY_NAME" class="nui-text" enable="false" />
				<label>证件类型：</label>
				<input name="con.CERT_TYPE" class="nui-text" enable="false" dictTypeId="CDKH0002" />
				<label>证件号码：</label>
				<input name="con.CERT_NUM" class="nui-text" enable="false" />
				<label>合同金额：</label>
				<input name="con.CONTRACT_AMT" class="nui-text" enable="false" dataType="currency" />
				<label>合同期限：</label>
				<div>
					<input name="con.CONTRACT_TERM" class="nui-text" enable="false" style="width: 60px" />
					<input name="con.CYCLE_UNIT" class="nui-text" enable="false" style="width: 80px" dictTypeId="XD_GGCD6009" />
				</div>
				<label>合同起期：</label>
				<input name="con.BEGIN_DATE" class="nui-text" enable="false" />
				<label>合同止期：</label>
				<input name="con.END_DATE" class="nui-text" enable="false" />
				<label>贷款执行利率：</label>
				<input name="con.YEAR_RATE" class="nui-text" enable="false" />
				<label>还款方式：</label>
				<input name="con.REPAYMENT_TYPE" class="nui-text" enable="false" dictTypeId="XD_SXCD1162" />
				<label>逾期罚息利率：</label>
				<input name="con.OVERDUE_RATE_UP_PROPORTION" class="nui-text" enable="false" />
			</div>
		</fieldset>
		<fieldset>
			<legend>逆移交信息</legend>
			<input name="retransfer.id" class="nui-hidden" />
			<input name="retransfer.contractId" class="nui-hidden" />
			<input name="retransfer.transferId" class="nui-hidden" />
			<table style="width: 100%">
				<tr>
					<td class="tdLabel">
						<label>逆移交时间</label>
					</td>
					<td>
						<input name="retransfer.retasDate" required="true" allowInput="false" class="nui-datepicker" enabled="<%=enabled%>" style="width: 200px" />
					</td>
				</tr>
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
			<div class="nui-dynpanel" columns="4">
				<label>经办机构</label>
				<input id="retransfer.orgNum" name="retransfer.orgNum" required="true" class="nui-buttonedit" dictTypeId="org" enabled="false" />
				<label>经办人</label>
				<input id="retransfer.userNum" name="retransfer.userNum" required="true" class="nui-buttonedit" dictTypeId="user" enabled="false" />
			</div>
		</fieldset>
	</div>
	<div class="nui-toolbar" style="border: none; padding-right: 20px; text-align: right;">
		<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save" enabled="<%=enabled%>">保存</a>
	</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	initPage();
	function initPage() {
		var retransferId =
<%=JspUtil.getParameterHaveSign(request, "retransferId")%>
	;
		var json = nui.encode({
			"retransferId" : retransferId,
			"showConInfo" : "true"
		});
		nui.ajax({
			url : "com.bos.asset.AssetsRetransfer.getRetransfer.biz.ext",
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
			url : "com.bos.asset.AssetsRetransfer.saveRetransfer.biz.ext",
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
</script>
</html>