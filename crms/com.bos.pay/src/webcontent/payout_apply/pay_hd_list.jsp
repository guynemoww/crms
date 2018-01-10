<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-14 19:20:27
  - Description:
-->
<head>
<title>回单信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width: 99.5%; height: 99.5%; overflow: hidden;">
		<fieldset>
			<legend>
				<span>回单列表查询</span>
			</legend>

			<div title="回单列表查询" id="contract">
				<div class="nui-dynpanel" columns="4">
					<label>还款日期起期：</label>
					<input id="map.startDate" class="nui-datepicker nui-form-input" name="map.startDate" onvalidation="" /> 
					<label>还款日期止期 ：</label> 
					<input id="map.endDate" class="nui-datepicker nui-form-input" name="map.endDate" onvalidation="" /> 
					<label>借款人名称：</label>
					<input id="map.partyName" class="nui-textbox nui-form-input" name="map.partyName" /> 
					<label>借据编号 ：</label> 
					<input id="map.summaryNum" class="nui-textbox nui-form-input" name="map.summaryNum" /> 
				</div>
			<div class="nui-toolbar"
				style="text-align: right; padding-top: 15px; padding-right: 25px;"
				borderStyle="border:0;">
				<a class="nui-button" iconCls="icon-search" onclick="initPage()"
					id="btnQuery">查询</a> 
					<a class="nui-button" iconCls="icon-reset"
					onclick="reset()">重置</a> 
			</div>
		<div class="nui-toolbar"
			style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
			<a class="nui-button" id="" onclick="dkhd" iconCls="icon-download">打印贷款回单</a>
		</div>
		</div>
	</fieldset>
		<div id="grid" class="nui-datagrid" style="width: 100%; height: auto;"
			sortMode="client" url="com.bos.pay.LoanSummary.queryHdList.biz.ext"
			dataField="dataObjects" allowAlternating="true" multiSelect="true"
			showEmptyText="true" emptyText="没有查到数据" showReloadButton="true"
			showColumnsMenu="true">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div type="indexcolumn">序号</div>
				<div field="PARTY_NAME" headerAlign="center">借款人名称</div>
				<div field="SUMMARY_NUM" headerAlign="center">借据编号</div>
				<div field="ZHMC" headerAlign="center">还款户名</div>
				<div field="ZH" headerAlign="center" width="13%">还款账号</div>
				<div field="YEAR_RATE" headerAlign="center">利率</div>
				<div field="RCV_DATE" allowSort="true" headerAlign="center">还款日期</div>
				<div field="rmbAmt" headerAlign="center" dataType="currency">偿还本金</div>
				<div field="CHLX" headerAlign="center" dataType="currency">偿还利息</div>
				<div field="CHFX" headerAlign="center" dataType="currency">偿还罚息</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	nui.parse();
	var form= new nui.Form("#form");
	var summaryId="<%=request.getParameter("summaryId")%>";
	var grid = nui.get("grid");
	
	initPage();
	function initPage(){
			var o = form.getData();
			grid.load(o);
		}
		function dkhd() {
			var rows = grid.getSelecteds();
			if (rows.length ==0) {
				alert("请选择一条记录");
				return;
			}
			var json = nui.encode({"rows" : rows});
			$.ajax({
				url : "com.bos.pay.LoanSummary.loanHD.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.swfPath) {
						nui.open({
							url : nui.context + "/biz/biz_report/contract_view.jsp?filePath=" + text.swfPath,
							title : "回单信息预览",
							width : 1000,
							height : 500,
							onload : function() {
							},
							ondestroy : function(action) {
								grid.reload();
							}

						});
					} else {
						alert("无打印信息!");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}
		
				//重置
		function reset() {
			form.reset();
		}
	</script>
</body>
</html>