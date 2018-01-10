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
	<div id="form1" class="nui-form">
		<input name="sqlName" class="nui-hidden" value="com.bos.asset.handle.HandleSql.handleListByCon" />
		<input id="item.orgcode" class="nui-hidden" name="item.orgcode" value="<%=GitUtil.getCurrentOrgCd()%>" />
		<input id="item.userNum" class="nui-hidden" name="item.userNum" value="<%=GitUtil.getCurrentUserId()%>" />
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input name="item.partyName" required="false" class="nui-textbox" />
			<label>证件类型：</label>
			<input id="item.certType" name="item.certType" class="nui-dictcombobox" dictTypeId="CDKH0002" allowInput="false" />
			<label>证件号码：</label>
			<input id="item.certNum" class="nui-textbox" name="item.certNum" />
			<label>合同编号：</label>
			<input id="item.contractNum" class="nui-textbox" name="item.contractNum" />
		</div>
		<div class="nui-toolbar" style="text-align: right; border: none">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div class="nui-fit">
		<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="true" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" style="width: 45px">选择</div>
				<div field="CONTRACT_NUM" headerAlign="center" allowSort="true">合同编号</div>
				<div field="PRODUCT_TYPE" headerAlign="center" dictTypeId="product" allowSort="true">贷款品种</div>
				<div field="PARTY_NAME" headerAlign="center" allowSort="true">客户名称</div>
				<div field="CURRENCY_CD" headerAlign="center" dictTypeId="CD000001" allowSort="true">币种</div>
				<div field="CONTRACT_AMT" headerAlign="center" align="right" allowSort="true" dataType="currency">合同金额</div>
				<div field="CON_BALANCE" headerAlign="center" align="right" allowSort="true" dataType="currency">合同已用金额</div>
				<div field="BEGIN_DATE" headerAlign="center" pattern="yyyy-MM-dd" allowSort="true">起始日</div>
				<div field="END_DATE" headerAlign="center" pattern="yyyy-MM-dd" allowSort="true">到期日</div>
				<div field="YQTS" headerAlign="center" allowSort="true">逾期天数</div>
				<div field="JJYQBJ" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">逾期本金</div>
				<div field="NORMAL_ITR" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">正常利息</div>
				<div field="ARREAR_ITR" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">拖欠利息</div>
				<div field="PUNISH_ITR" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">罚息</div>
				<div field="CLS_RESULT" headerAlign="center" dictTypeId="XD_FLCD0001" allowSort="true">风险分类</div>
				<div field="ORG_NUM" headerAlign="center" dictTypeId="org" allowSort="true">经办机构</div>
				<div field="USER_NUM" headerAlign="center" dictTypeId="user" allowSort="true">经办人</div>
			</div>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align: right; border: none;">
		<a class="nui-button" id="save_button" iconCls="icon-save" onclick="save">保存</a>
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
				setA_contractNum(e.data[i]);
			}
		}
	});

	window.onload = function() {
		query();
	}

	function query() {
		var o = form.getData();//逻辑流必须返回total
		gridLoan.load(o);
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
				contractId : rows[i].CONTRACT_ID
			};
		}
		json = nui.encode({
			datas : json,
			planId :
<%=JspUtil.getParameterHaveSign(request, "planId")%>
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

	function setA_contractNum(obj) {
		//合同链接
		obj['CONTRACT_NUM'] = '<a href="javascript:void(0);" onclick="clickContractNum('
				+ getValueStr(obj['CONTRACT_ID'])
				+ ');return false;">'
				+ obj['CONTRACT_NUM'] + '</a>';
	}
	function clickContractNum(contractId) {
		var openUrl = nui.context + "/crt/con_info/con_tree.jsp?contractId="
				+ contractId + "&contractType=02&proFlag=-1";
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