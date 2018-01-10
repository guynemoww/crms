<%@page import="com.bos.utp.tools.DBUtil"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-14 19:20:27
  - Description:
-->
<head>
<title>还款凭证打印</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>

	<div class="nui-toolbar" style="border-bottom: none; text-align: left;">
		<a class="nui-button" id="HKQD" onclick="print(this)">打印</a>
	</div>
	<div class="nui-fit">
		<div id="grid" class="nui-datagrid" style="width: 100%; height: 100%;" sortMode="client" url="com.bos.pub.dao.searchByDb.biz.ext" dataField="items" allowAlternating="true" multiSelect="false" showColumnsMenu="true">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="DUE_NUM" headerAlign="center">借据编号</div>
				<div field="RCV_DATE" headerAlign="center">还款时间</div>
				<div field="STAN" headerAlign="center">流水号</div>
				<div field="AMT" headerAlign="center" dataType="currency">还款金额</div>
				<div field="BJ" headerAlign="center" dataType="currency">归还本金</div>
				<div field="LX" headerAlign="center" dataType="currency">归还利息</div>
				<div field="TQ" headerAlign="center" dataType="currency">归还拖欠利息</div>
				<div field="FX" headerAlign="center" dataType="currency">归还罚息</div>
				<div field="FL" headerAlign="center" dataType="currency">归还复利</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid");
		var summaryId =
	<%=JspUtil.getParameterHaveSign(request, "summaryId")%>
		;
		var summaryNum =
	<%=JspUtil.getParameterHaveSign(request, "summaryNum")%>
		;
		var printType =
	<%=JspUtil.getParameterHaveSign(request, "printType")%>
		initPage();
		function initPage() {
			grid.load({
				dbName :
	<%=JspUtil.getStrHaveSign(DBUtil.DB_NAME_APLUS)%>
		,
				sqlName : "com.bos.utp.rights.funds.queryRepayWitness",
				"item" : {
					"summaryId" : summaryId,
					"summaryNum" : summaryNum
				}
			});
		}

		// 公共打印方法
		function print() {
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一笔借据");
				return false;
			}
			debugger;
			var json = nui.encode({
				"printType" : printType,
				"sumId" : summaryId,
				"dueNum" : summaryNum,
				"loanId" : row.LOAN_ID,
				"rcvDate" : row.RCV_DATE,
				"stan" : row.STAN
			});
			$
					.ajax({
						url : "com.bos.pay.LoanSummary.PrintCommon.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.swfPath) {
								nui
										.open({
											url : nui.context
													+ "/biz/biz_report/contract_view.jsp?filePath="
													+ text.swfPath,
											title : "借款凭证信息预览",
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
	</script>
</body>
</html>