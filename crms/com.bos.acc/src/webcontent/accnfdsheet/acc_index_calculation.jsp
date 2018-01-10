<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%-- 
  - Author(s): aobin@git.com.cn
  - Date: 2014-03-31 16:12:06
  - Description:财务分析-指标计算-页签
--%>
<head>
<%@include file="/common/nui/common.jsp"%>
<title>财务指标</title>
</head>
<style>
td {
	border: 1px solid black;
	text-align: left;
}
</style>
<body>
	<center>
		<div id="form1" style="width: 80%; height: auto; overflow: hidden; text-align: center; margin-top: 40px;">
			<input name="financeId" value="<%=request.getParameter("financeId")%>" class="nui-hidden" />
			<div id="balanceDiv" style="width: 90%; height: auto; overflow: hidden; text-align: center; border: 1px solid black"></div>
			<div class="nui-toolbar" style="width: 80%; height: auto; overflow: hidden; text-align: right;">
				<a class="nui-button" iconCls="icon-save" onclick="calculate()" id="btnSave" disableOnClick="true">计算</a>
				<!-- <a class="nui-button" iconCls="icon-close" id="btnCloseWindow" onclick="CloseWindow()">关闭</a> -->
			</div>
		</div>
	</certer>
	<script type="text/javascript">
		nui.parse();
		var logForm=new nui.Form('#balanceDiv');
		var logData=[];
		var statementData=[];
		var form = new nui.Form("#form1");
		var view="<%=request.getParameter("view")%>";//页面状态：查看1、修改0
		var reportType="<%=request.getParameter("reportType")%>";
		var sheetCode="<%=request.getParameter("sheetCode")%>";
		var reportStatus="1";
		var financeId="<%=request.getParameter("financeId")%>";
		if ("<%=request.getParameter("view")%>" == "1") {
			nui.get("btnSave").hide();
		}
		function initForm() {
			git.mask("form1");
			var json = nui.encode({
				"reportType" : reportType,
				"sheetCode" : sheetCode,
				"financeId" : financeId
			});
			$.ajax({
				url : "com.bos.acc.accnfdsheet.getAccNfdIndex.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						alert(text.msg);
						git.unmask("form1");
					} else {
						initLogs(text.accNfdIndexs);
						initStatementDatas(text.accFinanceIndexDatas);
						showBalanceSheet();
						git.unmask("form1");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
					git.unmask("form1");
				}
			});
		}
		initForm();

		function initStatementDatas(statementDatas) {
			if (!statementDatas || statementDatas.length < 1) {
				return;
			}
			for (var i = 0, len = statementDatas.length; i < len; i++) {
				var statement = statementDatas[i];
				statementData[statementData.length] = statement;
			}
		}

		function initLogs(logs) {
			//解析日志的meta信息及数据
			if (!logs || logs.length < 1) {
				return;
			}
			for (var i = 0, len = logs.length; i < len; i++) {
				var log = logs[i];
				logData[logData.length] = log;
			}
		}

		//展示资产负债表
		function showBalanceSheet() {
			var html = '';
			//展示日志详细信息
			if (logData.length < 1) {
				$('#balanceDiv').html('<h2>模版没有配置，请配置模版</h2>');
				return;
			}
			//构造表单
			html += '<div class="nui-dynpanel" columns="2">';
			for (var i = 0, len = logData.length; i < len; i++) {
				var log = logData[i];
				if (log.dataType == '1') {
					html += '<label>' + log.indexName + '</label>';
					html += '<label> </label>';
				} else {
					html += '<label>' + log.indexName + '</label>';
					var projectValue = '';
					for (var j = 0, lenj = statementData.length; j < lenj; j++) {
						var statement = statementData[j];
						if (statement.indexCd == log.indexCode) {
							if (statement.stringType == null
									|| statement.stringType == '') {
								projectValue = statement.indexValueDataType;
							} else {
								if ('01' == statement.stringType) {
									projectValue = '上期值为空';
								} else if ('02' == statement.stringType) {
									projectValue = '分母小于等于0且分子小于等于0';
								} else if ('03' == statement.stringType) {
									projectValue = '分母小于等于0';
								} else if ('05' == statement.stringType) {
									projectValue = '科目值小于等于0';
								} else if ('06' == statement.stringType) {
									projectValue = '补充资料为空';
								} else {
									projectValue = statement.stringType;
								}

							}
						}
					}
					html += '<input name="'+log.indexCode+'" class="nui-textbox nui-form-input" value="'+projectValue+'" enabled="false"/>';
				}
			}
			html += '</div>';
			$('#balanceDiv').html(html);
			nui.parse($('#balanceDiv')[0]);
		}

		//计算
		function calculate() {
			git.mask("form1");
			var json = nui.encode({
				"paramMap" : {
					"financeId" : financeId,
					"reportType" : reportType,
					"sheetCode" : sheetCode
				}
			});
			$.ajax({
					url : "com.bos.acc.accnfdsheet.calculateAccIndex.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						if (text.msg) {
							alert(text.msg);
							git.unmask("form1");
						} else {
							var url = nui.context
									+ "/acc/accnfdsheet/acc_index_calculation.jsp?financeId="
									+ financeId + "&view=0&reportType="
									+ reportType + "&sheetCode="
									+ sheetCode;
							git.go(url);
							git.unmask("form1");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.responseText);
						git.unmask("form1");
					}
				});
		}
	</script>
</body>
</html>