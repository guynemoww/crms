<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<%-- 
	  - Author(s): caohaijie@git.com.cn
	  - Date: 2015-08-12 16:12:06
	  - Description:分类财务报表
	--%>
<head>
<%@include file="/common/nui/common.jsp"%>
<title></title>
</head>
<div id="dw" style="height: auto; overflow: hidden; text-align: right; margin-right: 15%; margin-top: 2%;">
	<label>金额单位：元</label>
</div>
<style>
td {
	border: 1px solid black;
	text-align: left;
}
</style>
<body>
	<div id="form1" style="width: 98%; height: auto; overflow: hidden; text-align: center; margin-top: 5px;">
		<input name="partyId" value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
		<div id="balanceDiv" style="width: 80%; height: auto; overflow: hidden; text-align: center; border: 1px solid black"></div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var logForm = new nui.Form('#balanceDiv');
		var form = new nui.Form("#form1");

		var partyId =
	<%=JspUtil.getParameterHaveSign(request, "partyId")%>
		;
		//partyId = "40a283294e62ed8c014e632553cc0004";
		var sheetData = null;
		function initForm() {
			git.mask("form1");
			var json = nui.encode({
				"partyId" : partyId
			});
			$.ajax({
				url : "com.bos.risk.sort.getFinanceData2.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.exception) {
						alert(text.exception.message);
						git.unmask("form1");
					} else {
						sheetData = text.financeData;
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

		function showBalanceSheet() {
			var html = '';
			//构造表单
			html += '<div class="nui-dynpanel" columns="5" >';
			for (var i = 0, len = sheetData.length; i < len; i++) {
				var map = sheetData[i];
				html += '<label>' + map.itemName + '</label>';
				html += '<label>' + formatMoney(map.lastThreeValue)
						+ '</label>';
				html += '<label>' + formatMoney(map.lastTwoValue) + '</label>';
				html += '<label>' + formatMoney(map.lastOneValue) + '</label>';
				html += '<label>' + formatMoney(map.currValue) + '</label>';
			}
			html += '</div>';
			$('#balanceDiv').html(html);
			nui.parse($('#balanceDiv')[0]);
		}

		function formatMoney(s) {
			if (s == null || s == "") {
				return "--";
			} else if (!isNaN(s)) {
				s = s.toFixed(2)
			} else {
				return s;
			}
			s = s.toString().replace(/^(\d*)$/, "$1.");
			s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
			s = s.replace(".", ",");
			var re = /(\d)(\d{3},)/;
			while (re.test(s))
				s = s.replace(re, "$1,$2");
			s = s.replace(/,(\d\d)$/, ".$1");
			return s;
		}
	</script>
</body>
</html>
