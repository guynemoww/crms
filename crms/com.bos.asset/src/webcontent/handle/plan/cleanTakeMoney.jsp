<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html >
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
		<input id="cleanMoney.id" name="cleanMoney.id" class="nui-hidden" />
		<fieldset>
			<legend>
				<span>借据信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>借款人姓名：</label>
				<input id="datas.PARTY_NAME" name="datas.PARTY_NAME" class="nui-text" />
				<label>证件类型：</label>
				<input id="datas.CERT_TYPE" name="datas.CERT_TYPE" class="nui-dictcombobox" dictTypeId="CDKH0002" enabled="false" />
				<label>证件号码：</label>
				<input id="datas.CERT_NUM" name="datas.CERT_NUM" class="nui-text" />
				<label>贷款种类：</label>
				<input id="datas.PRODUCT_TYPE" name="datas.PRODUCT_TYPE" class="nui-text" enabled="false" dictTypeId="product" />
				<label>借据编号：</label>
				<input id="datas.SUMMARY_NUM" name="datas.SUMMARY_NUM" class="nui-text" />
				<label>是否已核销：</label>
				<input id="cleanMoney.conVerify" name="cleanMoney.conVerify" class="nui-dictcombobox" enabled="false" dictTypeId="XD_0002" />
				<label>是否已停息：</label>
				<input id="cleanMoney.tingxiStatus" name="cleanMoney.tingxiStatus" class="nui-dictcombobox" enabled="false" dictTypeId="XD_0002" />
				<label>贷款起期：</label>
				<input id="cleanMoney.sumBeginDate" name="cleanMoney.sumBeginDate" class="nui-datepicker" enabled="false" />
				<label>贷款止期：</label>
				<input id="cleanMoney.sumEndDate" name="cleanMoney.sumEndDate" class="nui-datepicker" enabled="false" />
				<label>逾期天数：</label>
				<input id="cleanMoney.sumOverdueDays" name="cleanMoney.sumOverdueDays" class="nui-text" />
				<label>借据金额：</label>
				<input id="cleanMoney.summaryAmt" name="cleanMoney.summaryAmt" class="nui-text" enabled="false" dataType="currency" />
				<label>借据余额：</label>
				<input id="cleanMoney.summaryBal" name="cleanMoney.summaryBal" class="nui-text" enabled="false" dataType="currency" />
				<label>贷款结清金额：</label>
				<input id="cleanMoney.supTotPrnItr" name="cleanMoney.supTotPrnItr" class="nui-text" dataType="currency" />
				<label>剩余正常本金：</label>
				<input id="cleanMoney.supResNor" name="cleanMoney.supResNor" class="nui-text" dataType="currency" />
				<label>贷款人拖欠本金：</label>
				<input id="cleanMoney.supDftPrnBal" name="cleanMoney.supDftPrnBal" class="nui-text" dataType="currency" />
				<label>贷款剩余本金：</label>
				<input id="cleanMoney.supSurplusAmt" name="cleanMoney.supSurplusAmt" class="nui-text" dataType="currency" />
				<label>正常利息：</label>
				<input id="cleanMoney.sumNormalItr" name="cleanMoney.sumNormalItr" class="nui-text" dataType="currency" />
				<label>拖欠利息：</label>
				<input id="cleanMoney.sumArrearItr" name="cleanMoney.sumArrearItr" class="nui-text" dataType="currency" />
				<label>罚息：</label>
				<input id="cleanMoney.sumPunishItr" name="cleanMoney.sumPunishItr" class="nui-text" dataType="currency" />
			</div>
		</fieldset>
		<fieldset>
			<legend>
				<span>申请信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>还款金额：</label>
				<input id="cleanMoney.repayAmt" name="cleanMoney.repayAmt" class="nui-textbox" required="true" dataType="currency" enabled="<%=enable%>" onvalidation="repayAmtValid" vtype="maxLength:17" />
				<label>还款顺序：</label>
				<input id="cleanMoney.repaySort" name="cleanMoney.repaySort" class="nui-dictcombobox" required="true" dictTypeId="XD_ASSET005" enabled="<%=enable%>" />
				<label>账户类型：</label>
				<input id="cleanMoney.accType" name="cleanMoney.accType" class="nui-dictcombobox" required="true" dictTypeId="XD_ASSET006" enabled="<%=enable%>" />
				<label>扣款账户类型：</label>
				<input id="cleanMoney.deductAccType" name="cleanMoney.deductAccType" class="nui-dictcombobox" onvaluechanged="changeDeductAccTypeAction" dictTypeId="XD_ASSET007" enabled="<%=enable%>" />
				<label>扣款账户名称：</label>
				<input id="cleanMoney.deductAccName" name="cleanMoney.deductAccName" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:80" />
				<label id="deductAccNum_label">扣款账号：</label>
				<input id="cleanMoney.deductAccNum" name="cleanMoney.deductAccNum" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:80" />
			</div>
		</fieldset>
	</div>
	<div class="nui-toolbar" style="text-align: right; border: none;">
		<a class="nui-button" id="save_button" iconCls="icon-save" enabled="<%=enable%>" onclick="save">保存</a>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var planId =
	<%=JspUtil.getParameterHaveSign(request, "planId")%>
		;
		initPage();
		function initPage() {
			var json = nui.encode({
				"planId" : planId
			});
			$
					.ajax({
						url : "com.bos.asset.AssetsCleanTake.getCleanTakeMoney.biz.ext",
						type : 'POST',
						data : json,
						contentType : 'text/json',
						cache : false,
						async : false,
						success : function(mydata) {
							mydata.datas = mydata.datas[0];
							if (!mydata.cleanMoney.repaySort) {
								mydata.cleanMoney.repaySort = "20";
							}
							form.setData(nui.decode(mydata));
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
			var json = form.getData();
			json.planId = planId;
			//debugger;
			json = nui.encode(json);
			$
					.ajax({
						url : "com.bos.asset.AssetsCleanTake.saveCleanTakeMoney.biz.ext",
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
		function changeDeductAccTypeAction(e) {
			if (e.value == "30") {
				nui.get("cleanMoney.deductAccName").setEnabled(true);
			} else {
				nui.get("cleanMoney.deductAccName").setValue(
						nui.get("datas.PARTY_NAME").getValue());
				nui.get("cleanMoney.deductAccName").setEnabled(false);
			}
			debugger;
			if (e.value == "10") {
				$("#deductAccNum_label").hide();
				nui.get("cleanMoney.deductAccNum").hide();
			} else {
				$("#deductAccNum_label").show();
				nui.get("cleanMoney.deductAccNum").show();
			}
		}

		function repayAmtValid(e) {
			if (e.isValid) {
				var bal = nui.get("cleanMoney.summaryBal").getValue();
				if (Number(e.value) > Number(bal)) {
					e.errorText = "还款金额不能大于借据余额";
					e.isValid = false;
				}
			}
		}
	</script>
</body>
</html>