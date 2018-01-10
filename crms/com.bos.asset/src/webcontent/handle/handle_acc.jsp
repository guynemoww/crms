<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
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
	<iframe name="exportFrame" id="exportFrame" src="" style="display: none;"></iframe>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="处置方案">
			<div id="form1" class="nui-form">
				<input name="sqlName" class="nui-hidden" value="com.bos.asset.handle.HandleSql.handleAcc" />
				<div class="nui-dynpanel" columns="6">
					<label>机构名称：</label>
					<input id="item.orgNum" name="item.orgNum" class="nui-buttonedit" allowInput="false" value="<%=GitUtil.getCurrentOrgCd()%>" dictTypeId="org" onbuttonclick="selectEmpOrg" />
					<label>经办人：</label>
					<input id="item.userNum" name="item.userNum" class="nui-buttonedit" allowInput="false" value="<%=GitUtil.getCurrentUserId()%>" dictTypeId="user" onbuttonclick="selectCustManegers" />
					<label>客户名称：</label>
					<input name="item.partyName" class="nui-textbox" />
					<label>证件类型：</label>
					<input id="item.certType" name="item.certType" class="nui-dictcombobox" dictTypeId="CDKH0002" allowInput="false" />
					<label>证件号码：</label>
					<input id="item.certNum" name="item.certNum" class="nui-textbox" />
					<label>处置方式：</label>
					<input id="item.planType" name="item.planType" class="nui-dictcombobox" dictTypeId="XD_ASSET001" allowInput="false" />
					<label>合同编号：</label>
					<input id="item.contractNum" name="item.contractNum" class="nui-textbox" />
					<label>借据编号：</label>
					<input id="item.summaryNum" name="item.summaryNum" class="nui-textbox" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="query">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
					<!-- <a class="nui-button" iconCls="icon-download" onclick="exportxls">导出EXCEL</a> -->
				</div>
			</div>
			<div class="nui-fit">
				<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="false" sortMode="client" allowAlternating="true">
					<div property="columns">
						<div field="PLAN_NUM" allowSort="true" headerAlign="center">处置方案编号</div>
						<div field="CONTRACT_NUM" allowSort="true" headerAlign="center">合同编号</div>
						<div field="SUMMARY_NUM" allowSort="true" headerAlign="center">借据编号</div>
						<div field="PARTY_NAME" allowSort="true" headerAlign="center">客户名称</div>
						<div field="CERT_TYPE" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="CERT_NUM" allowSort="true" headerAlign="center">证件号码</div>
						<div field="PRODUCT_TYPE" allowSort="true" headerAlign="center" dictTypeId="product">业务品种</div>
						<div field="SUMMARY_AMT" allowSort="true" headerAlign="center">借据金额</div>
						<div field="SUMMARY_BAL" allowSort="true" headerAlign="center">借据余额</div>
						<div field="SUM_BEGIN_DATE" allowSort="true" headerAlign="center">借据起期</div>
						<div field="SUM_END_DATE" allowSort="true" headerAlign="center">借据止期</div>
						<div field="PLAN_TYPE" allowSort="true" headerAlign="center" dictTypeId="XD_ASSET001">方案处置方式</div>
						<div field="REG_ORG_ID" allowSort="true" headerAlign="center" dictTypeId="org">经办机构</div>
						<div field="REG_USER_ID" allowSort="true" headerAlign="center" dictTypeId="user">经办人员</div>
						<div field="REG_DATE" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">经办日期</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	nui.parse();

	var form = new nui.Form("#form1");
	var gridLoan = nui.get("gridLoan");

	gridLoan.on("preload", function(e) {
		if (!e.data || e.data.length < 1) {
			return;
		}
		for (var i = 0; i < e.data.length; i++) {
			if (e.data[i]) {
				setA_planNum(e.data[i]);
				setA_partyName(e.data[i]);
				setA_contractNum(e.data[i]);
				setA_summaryNum(e.data[i]);
			}
		}
	});

	window.onload = function() {
		query();
	}

	function query() {
		gridLoan.load(form.getData());
	}

	function reset() {
		form.reset();
		query();
	}

	function showInfo() {
		var row = gridLoan.getSelected();
		nui.open({
			url : nui.context + "/asset/handle/handle_tree.jsp?bizId=" + row.ID
					+ "&edit=2",
			showMaxButton : true,
			title : "处置方案申报",
			width : 1024,
			height : 768,
			state : "max"
		});
	}
	function exportxls() {
		git.mask();
		var ifrm = document.getElementById("exportFrame");
		var data = form.getData();
		var json = nui.encode({
			"item" : data.item,
			"sqlName" : data.sqlName,
			"sheetName" : "不良资产处置方案",
			"downloandTitle" : "handleAcc"
		});
		$.ajax({
			url : "com.bos.asset.Assets.downloadxls.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("form1");
				if (text.msg) {
					git.unmask();
					ifrm.src = nui.context
							+ "/pub/io/file/download.jsp?deleteFile=true";

				} else {
					git.unmask();
					nui.alert("下载数据有误！");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("form1");
				nui.alert(jqXHR.responseText);
			}
		});
	}

	function selectEmpOrg(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 450,
			height : 650,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					btnEdit.setText(null);
					btnEdit.setValue(data.orgcode);
					nui.get("item.userNum").setValue(null);
					nui.get("item.userNum").setText(null);
				}
			}
		});
	}
	function selectCustManegers(e) {
		var newOrgNum = nui.get("item.orgNum").getValue();
		if (!newOrgNum || newOrgNum == "") {
			alert("请先选择机构");
			return;
		} else {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/user/select_user.jsp?orgNum="
						+ newOrgNum,
				showMaxButton : true,
				title : "选择客户经理",
				width : 800,
				height : 550,
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
	}

	function setA_planNum(obj) {
		obj['PLAN_NUM'] = '<a href="javascript:void(0);" onclick="clickPlanNum('
				+ getValueStr(obj.ID)
				+ ');return false;">'
				+ obj['PLAN_NUM']
				+ '</a>';
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

	function clickCust(partyId, partyNum, corpCustomerTypeCd, partyTypeCd) {
		var openUrl;
		if (partyTypeCd == "01") {
			openUrl = nui.context
					+ "/csm/corporation/csm_corporation_tree.jsp?partyId="
					+ partyId + "&partyNum=" + partyNum + "&cusType="
					+ corpCustomerTypeCd + "&qote=1";
		} else {
			openUrl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
					+ partyId + "&partyNum=" + partyNum + "&qote=1";
		}
		open(openUrl);
	}

	function clickPlanNum(planId) {
		var openUrl = nui.context + "/asset/handle/handle_tree.jsp?bizId="
				+ planId + "&edit=2";
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