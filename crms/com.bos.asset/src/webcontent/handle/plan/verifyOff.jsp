<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-05-23 20:32:11
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		String enable = JspUtil.getParameter(request, "enable", "false");
	%>
	<div id="form" class="nui-form">
		<input id="verify.id" name="verify.id" class="nui-hidden" />
		<fieldset>
			<legend>
				<span>借据信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4" id="table1">
				<label>合同编号：</label>
				<input id="datas.CONTRACT_NUM" name="datas.CONTRACT_NUM" class="nui-text" />
				<label>借据编号：</label>
				<input id="datas.SUMMARY_NUM" name="datas.SUMMARY_NUM" class="nui-text" />
				<label>客户名称：</label>
				<input id="datas.PARTY_NAME" name="datas.PARTY_NAME" class="nui-text" />
				<label>证件类型：</label>
				<input id="datas.CERT_TYPE" name="datas.CERT_TYPE" class="nui-dictcombobox" dictTypeId="CDKH0002" enabled="false" />
				<label>证件号码：</label>
				<input id="datas.CERT_NUM" name="datas.CERT_NUM" class="nui-text" />
				<label>业务品种：</label>
				<input id="datas.PRODUCT_TYPE" name="datas.PRODUCT_TYPE" class="nui-text" enabled="false" dictTypeId="product" />
				<label>借据金额：</label>
				<input id="verify.summaryAmt" name="verify.summaryAmt" class="nui-text" dataType="currency" />
				<label>借据余额：</label>
				<input id="verify.summaryBal" name="verify.summaryBal" class="nui-text" dataType="currency" />
				<label>借据起期：</label>
				<input id="verify.sumBeginDate" name="verify.sumBeginDate" class="nui-text" />
				<label>借据止期：</label>
				<input id="verify.sumEndDate" name="verify.sumEndDate" class="nui-text" />
				<label>还款方式：</label>
				<input id="verify.repaymentType" name="verify.repaymentType" class="nui-dictcombobox" dictTypeId="XD_SXCD1162" enabled="false" />
				<label>结息周期：</label>
				<input id="verify.interestCollectType" name="verify.interestCollectType" dictTypeId="XD_SXCD1018" enabled="false" class="nui-dictcombobox" />
				<label>执行利率（%）：</label>
				<input id="verify.yearRate" name="verify.yearRate" class="nui-text" />
				<label>还款账号：</label>
				<input id="verify.repayNum" name="verify.repayNum" class="nui-text" />
				<label>还款账户名称：</label>
				<input id="verify.repayName" name="verify.repayName" class="nui-text" />
			</div>
		</fieldset>
		<fieldset>
			<legend>
				<span>核销信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>是否结清：</label>
				<input id="verify.settle" name="verify.settle" class="nui-dictcombobox" required="true" enabled="false" dictTypeId="XD_0002" />
				<label>核销总额：</label>
				<input id="verify.cancelAmt" name="verify.cancelAmt" class="nui-textbox" required="true" enabled="false" />
				<label>核销本金：</label>
				<input id="verify.cancelCapitalAmt" name="verify.cancelCapitalAmt" class="nui-textbox" enabled="false" required="true" dataType="currency" />
				<label>核销正常利息：</label>
				<input id="verify.cancelNormalItr" name="verify.cancelNormalItr" class="nui-textbox" enabled="false" dataType="currency" />
				<label>核销拖欠利息：</label>
				<input id="verify.cancelArrearItr" name="verify.cancelArrearItr" class="nui-textbox" enabled="false" dataType="currency" />
				<label>核销罚息：</label>
				<input id="verify.cancelPunishItr" name="verify.cancelPunishItr" class="nui-textbox" enabled="false" dataType="currency" />
				<label>是否保留追索权：</label>
				<input id="verify.pursue" name="verify.pursue" class="nui-dictcombobox" required="true" enabled="<%=enable%>" dictTypeId="XD_0002" />
			</div>
		</fieldset>
	</div>
	<div class="nui-toolbar" style="text-align: right; border: none;">
		<a id="actionVerifyOff" class="nui-button" iconCls="icon-action" onclick="actionVerifyOff">核销</a>
		<a class="nui-button" id="save_button" iconCls="icon-save" enabled="<%=enable%>" onclick="save">保存</a>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		initPage();

		function initPage() {
			debugger;
			var json = {
				planId :
	<%=JspUtil.getParameterHaveSign(request, "planId")%>
		};
			json = nui.encode(json);
			$.ajax({
				url : "com.bos.asset.AssetsVerifyOff.getVerifyOff.biz.ext",
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(mydata) {
					mydata.datas = mydata.datas[0];
					if (mydata.datas.STATUS != "30") {
						nui.get("actionVerifyOff").hide();
					}
					var o = nui.decode(mydata);
					form.setData(o);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				}
			});
		}

		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请按规则填写信息");
				return;
			}
			nui.get("save_button").setEnabled(false);
			//debugger;
			var o = form.getData();
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.asset.AssetsVerifyOff.saveVerifyOff.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg); //失败时后台直接返回出错信息
					} else {
						nui.alert("操作成功");
						initPage();
					}
					nui.get("save_button").setEnabled(true);
				}
			});
		}
		function actionVerifyOff() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请按规则填写信息");
				return;
			}
			var o = form.getData().verify;
			var json = nui.encode({
				planId : o.id
			});
			$.ajax({
				url : "com.bos.asset.AssetsVerifyOff.actionVerifyOff.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
					} else {
						nui.alert("操作成功");
						initPage();
					}
					nui.get("save_button").setEnabled(true);
				}
			});
		}
	</script>
</body>
</html>