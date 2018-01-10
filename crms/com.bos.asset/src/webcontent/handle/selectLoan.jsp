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
	<%
		String orgNum = GitUtil.getCurrentOrgCd();
		String userNum = GitUtil.getCurrentUserId();
	%>
	<div id="form1" class="nui-form">
		<input name="sqlName" class="nui-hidden" value="com.bos.asset.handle.HandleSql.handleListByLoan" />
		<input id="item.partyId" class="nui-hidden" name="item.partyId" value=<%=JspUtil.getParameterHaveSign(request, "partyId")%> />
		<input id="item.orgcode" class="nui-hidden" name="item.orgcode" value="<%=orgNum%>" />
		<input id="item.userNum" class="nui-hidden" name="item.userNum" value="<%=userNum%>" />
		<input id="item.userNum" class="nui-hidden" name="item.searchMode" value="notHavVerify" />
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input name="item.partyName" required="false" class="nui-textbox" />
			<label>证件类型：</label>
			<input id="item.certType" name="item.certType" class="nui-dictcombobox" dictTypeId="CDKH0002" allowInput="false" />
			<label>证件号码：</label>
			<input id="item.certNum" class="nui-textbox" name="item.certNum" />
			<label>合同编号：</label>
			<input id="item.contractNum" class="nui-textbox" name="item.contractNum" />
			<label>借据编号：</label>
			<input id="item.summaryNum" class="nui-textbox" name="item.summaryNum" />
		</div>
		<div class="nui-toolbar" style="text-align: right; border: none">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div class="nui-fit">
		<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="true" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" width="40px">选择</div>
				<div field="ORG_NUM" allowSort="true" headerAlign="center" width="10%" dictTypeId="org">机构名称</div>
				<div field="PARTY_NAME" allowSort="true" headerAlign="center" width="10%">客户名称</div>
				<div field="PRODUCT_TYPE" allowSort="true" headerAlign="center" dictTypeId="product">业务品种</div>
				<div field="CONTRACT_NUM" allowSort="true" headerAlign="center" width="120px">合同编号</div>
				<div field="SUMMARY_NUM" allowSort="true" headerAlign="center" width="120px">借据编号</div>
				<div field="SUMMARY_AMT" allowSort="true" headerAlign="center" align="right" dataType="currency">借据金额</div>
				<div field="JJYE" allowSort="true" headerAlign="center" align="right" dataType="currency">借据余额</div>
				<div field="NORMAL_ITR" allowSort="true" headerAlign="center" align="right" dataType="currency">正常利息</div>
				<div field="ARREAR_ITR" allowSort="true" headerAlign="center" align="right" dataType="currency">欠息</div>
				<div field="PUNISH_ITR" allowSort="true" headerAlign="center" align="right" dataType="currency">罚息</div>
				<div field="BEGIN_DATE" allowSort="true" headerAlign="center">借据起期</div>
				<div field="END_DATE" allowSort="true" headerAlign="center">借据止期</div>
				<div field="USER_NUM" allowSort="true" headerAlign="center" dictTypeId="user">经办人</div>
			</div>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align: right; border: none">
		<a class="nui-button" id="save_button" iconCls="icon-save" onclick="save">保存</a>
	</div>
</body>
<script type="text/javascript">
	nui.parse();
	git.mask();

	var form = new nui.Form("#form1");
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
			}
		}
	});

	window.onload = function() {
		query();
	}

	function query() {
		var o = form.getData();//逻辑流必须返回total
		gridLoan.load(o);
		git.unmask();
	}

	function reset() {
		form.reset();
		query();
	}

	function save() {
		var rows = gridLoan.getSelecteds();
		if (!rows || rows.length == 0) { //没有选中项，表单不提交。
			alert("请选择一条记录！");
			git.unmask();
			return;
		}
		var json = new Array();
		for (var i = 0; i < rows.length; i++) {
			json[i] = {
				summaryId : rows[i].SUMMARY_ID
			};
		}
		json = nui.encode({
			planId :
<%=JspUtil.getParameterHaveSign(request, "planId")%>
	,
			datas : json
		});
		debugger;
		$.ajax({
			url :
<%=JspUtil.getParameterHaveSign(request, "createUrl")%>
	,
			type : 'POST',
			data : json,
			contentType : 'text/json',
			cache : false,
			async : false,
			success : function(data) {
				if (data.msg) {
					nui.alert(data.msg);
				} else {
					CloseWindow("ok");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
				git.unmask();
			}
		});
	}

	function CloseWindow(action) {
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow(action);
		else
			window.close();
	}

	function setA_partyName(obj) {
		//客户链接
		obj['partyName'] = '<a href="javascript:void(0);" onclick="clickCust('
				+ getValueStr(obj.partyId) + ',' + getValueStr(obj.partyNum)
				+ ',' + getValueStr(obj.corpCustomerTypeCd) + ','
				+ getValueStr(obj.partyTypeCd) + ');return false;">'
				+ obj['partyName'] + '</a>';
	}

	function setA_contractNum(obj) {
		//合同链接
		obj['contractNum'] = '<a href="javascript:void(0);" onclick="clickContractNum('
				+ getValueStr(obj['contractId'])
				+ ');return false;">'
				+ obj['contractNum'] + '</a>';
	}

	function setA_summaryNum(obj) {
		//借据链接
		obj['summaryNum'] = '<a href="javascript:void(0);" onclick="clickSummaryNum('
				+ getValueStr(obj['loanId'])
				+ ');return false;">'
				+ obj['summaryNum'] + '</a>';
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