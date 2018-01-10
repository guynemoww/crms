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
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="true" sortMode="client">
		<div property="columns">
			<div field="PARTY_NAME" allowSort="true" headerAlign="center" width="10%">客户名称</div>
			<div field="CONTRACT_NUM" allowSort="true" headerAlign="center" width="120px">合同编号</div>
			<div field="SUMMARY_NUM" allowSort="true" headerAlign="center" width="120px">借据编号</div>
			<div field="PRODUCT_TYPE" allowSort="true" headerAlign="center" dictTypeId="product">贷款类型</div>
			<div field="CLS_RESULT" allowSort="true" headerAlign="center" dictTypeId="XD_FLCD0001">五级分类</div>
			<div field="SUMMARY_AMT" allowSort="true" headerAlign="center" align="right" dataType="currency">贷款金额</div>
			<div field="JJYE" allowSort="true" headerAlign="center" align="right" dataType="currency">贷款余额</div>
			<div field="NORMAL_ITR" allowSort="true" headerAlign="center" align="right" dataType="currency">利息余额</div>
			<div field="BEGIN_DATE" allowSort="true" headerAlign="center">贷款起期</div>
			<div field="END_DATE" allowSort="true" headerAlign="center">贷款止期</div>
			<div field="action" headerAlign="center">操作</div>
		</div>
	</div>
	<div id="win_scale" class="nui-window" title="借据详细信息" style="width: 800px; height: 500px; display: none; padding: 0">
		<div id="form_scale" class="nui-form nui-fit">
			<div class="nui-dynpanel" columns="4">
				<label>客户名称：</label>
				<input name="PARTY_NAME" class="nui-text" />
				<label>证件号码：</label>
				<input name="CERT_NUM" class="nui-text" />
				<label>不良移交申请编号：</label>
				<input name="TRANSFER_ID" class="nui-text" />
				<label>贷款款合同号：</label>
				<input name="CONTRACT_NUM" class="nui-text" />
				<label>借据编号：</label>
				<input name="SUMMARY_NUM" class="nui-text" />
				<label>所属机构名称：</label>
				<input name="ORG_NUM" class="nui-text" dictTypeId="org" />
				<label>分管客户经理：</label>
				<input name="USER_NUM" dictTypeId="user" class="nui-text" />
				<label>贷款种类：</label>
				<input name="PRODUCT_TYPE" class="nui-text" dictTypeId="product" />
				<label>主担保方式：</label>
				<input name="MAIN_GUARANTY_TYPE" class="nui-text" dictTypeId="CDZC0005" />
				<label>还款方式：</label>
				<input name="REPAY_TYPE" class="nui-text" dictTypeId="XD_SXCD1162" />
				<label>贷款起期：</label>
				<input name="BEGIN_DATE" class="nui-text" />
				<label>贷款止期：</label>
				<input name="END_DATE" class="nui-text" />
				<label>贷款期限：</label>
				<div>
					<input name="SUMMARY_TERM" class="nui-text" style="width: 100px" />
					<input name="CYCLE_UNIT" dictTypeId="XD_GGCD6009" class="nui-text" style="width: 60px" />
				</div>
				<label>逾期天数：</label>
				<input name="YQTS" class="nui-text" />
				<label>移交时间：</label>
				<input name="TAS_DATE" class="nui-text" />
				<label>五级分类：</label>
				<input name="CLS_RESULT" class="nui-text" dictTypeId="XD_FLCD0001" />
				<label>借据金额：</label>
				<input name="SUMMARY_AMT" class="nui-text" />
				<label>借据余额：</label>
				<input name="V_JJYE" class="nui-text" />
				<label>逾期本金：</label>
				<input name="JJYQBJ" class="nui-text" />
				<label>拖欠利息：</label>
				<input name="ARREAR_ITR" class="nui-text" />
				<label>借据状态：</label>
				<input name="SUMMARY_STATUS_CD" class="nui-text" dictTypeId="XD_SXYW0226" />
				<label>当前逾期天数：</label>
				<input name="YQTS" class="nui-text" />
				<label>表内正常利息：</label>
				<input name="IN_NOR_BAL_06" class="nui-text" />
				<label>表外正常利息：</label>
				<input name="OUT_NOR_BAL_09" class="nui-text" />
				<label>表内拖欠利息：</label>
				<input name="IN_DFT_BAL_07" class="nui-text" />
				<label>表外拖欠利息：</label>
				<input name="OUT_DFT_BAL_10" class="nui-text" />
				<label>表内罚息：</label>
				<input name="IN_PNS_BAL_08" class="nui-text" />
				<label>表外罚息：</label>
				<input name="OUT_PNS_BAL_11" class="nui-text" />
			</div>
			<div style="text-align: center;">
				<a class="nui-button" iconCls="icon-close" onclick="hideWin_scale">关闭</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	nui.parse();

	var gridLoan = nui.get("gridLoan");

	gridLoan.on("preload", function(e) {
		if (!e.data || e.data.length < 1) {
			return;
		}
		for (var i = 0; i < e.data.length; i++) {
			if (e.data[i]) {
				setA_partyName(e.data[i]);
				setA_contractNum(e.data[i]);
				setA_summaryNum(e.data[i]);
				setA_action(e.data[i]);
			}
		}
	});

	window.onload = function() {
		query();
	}

	function query() {
		gridLoan
				.load({
					"sqlName" : "com.bos.asset.retransfer.RetransferSql.loanInfoList",
					"item" : {
						"id" :
<%=JspUtil.getParameterHaveSign(request, "retransferId")%>
	}
				});
	}

	formScale = new nui.Form("#form_scale");
	function showWin_scale(retransferId, summaryId) {
		debugger;
		$.ajax({
			url : "com.bos.pub.dao.search.biz.ext",
			type : 'POST',
			data : nui.encode({
				"sqlName" : "com.bos.asset.retransfer.RetransferSql.loanInfo",
				"item" : {
					"id" : retransferId,
					"summaryId" : summaryId
				}
			}),
			cache : false,
			contentType : 'text/json',
			success : function(data) {
				if (data && data.items && data.items[0]) {
					formScale.setData(data.items[0]);
					nui.get("win_scale").showAtPos("center", "middle");
				}
			}
		});
	}
	function hideWin_scale() {
		nui.get("win_scale").hide();
	}

	function setA_partyName(obj) {
		debugger;
		//客户链接
		obj['PARTY_NAME'] = '<a href="javascript:void(0);" onclick="clickCust('
				+ getValueStr(obj.PARTY_ID) + ','
				+ getValueStr(obj.CORP_CUSTOMER_TYPE_CD) + ','
				+ getValueStr(obj.PARTY_TYPE_CD) + ');return false;">'
				+ obj['PARTY_NAME'] + '</a>';
	}

	function setA_contractNum(obj) {
		//合同链接
		obj['CONTRACT_NUM'] = '<a href="javascript:void(0);" onclick="clickContractNum('
				+ getValueStr(obj['CONTRACT_ID'])
				+ ');return false;">'
				+ obj['CONTRACT_NUM'] + '</a>';
	}

	function setA_summaryNum(obj) {
		//借据链接
		obj['SUMMARY_NUM'] = '<a href="javascript:void(0);" onclick="clickSummaryNum('
				+ getValueStr(obj['LOAN_ID'])
				+ ');return false;">'
				+ obj['SUMMARY_NUM'] + '</a>';
	}

	function setA_action(obj) {
		obj['action'] = '<a href="javascript:void(0);" onclick="showWin_scale('
				+ getValueStr(obj['ID']) + ',' + getValueStr(obj['SUMMARY_ID'])
				+ ');return false;">查看详细</a>';
	}

	function clickCust(partyId, corpCustomerTypeCd, partyTypeCd) {
		var openUrl;
		debugger;
		if (partyTypeCd == "01") {
			openUrl = nui.context
					+ "/csm/corporation/csm_corporation_tree.jsp?partyId="
					+ partyId + "&cusType=" + corpCustomerTypeCd + "&qote=1";
		} else {
			openUrl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
					+ partyId + "&qote=1";
		}
		open(openUrl);
	}

	function clickContractNum(contractId) {
		var openUrl = nui.context + "/crt/con_info/con_tree.jsp?contractId="
				+ contractId + "&contractType=02&proFlag=-1";
		open(openUrl);
	}

	function clickSummaryNum(loanId) {
		var openUrl = nui.context + "/pay/payout_info/pay_tree.jsp?loanId="
				+ loanId + "&processInstId=0&proFlag=-1";
		open(openUrl);
	}

	function getValueStr(value) {
		return '\'' + value + '\'';
	}

	function open(openUrl) {
		nui.open({
			url : openUrl,
			showMaxButton : true,
			title : "",
			width : 1024,
			height : 768,
			state : "max"
		});
	}
</script>
</html>