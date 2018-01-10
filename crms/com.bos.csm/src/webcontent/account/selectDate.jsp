<%@page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>时间选择</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div class="nui-dynpanel" columns="2">
		<label>起始时间：</label>
		<input id="beginDate" name="beginDate" class="nui-datepicker" allowInput="false" style="width: 100px" />
		<label>结束时间：</label>
		<input id="endDate" name="endDate" class="nui-datepicker" allowInput="false" style="width: 100px" />
	</div>
	<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;">
		<a class="nui-button" iconCls="icon-save" onclick="print2(this)" id="btnSave">打印</a>
		<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()" id="btnCancel">关闭</a>
	</div>
</body>
<script type="text/javascript">
	nui.parse();

	var currRow;
	function print2(a) {
		if (!currRow) {
			return;
		}
		a.setEnabled(false);
		currRow.BEGIN_DATE = nui.get("beginDate").getValue();
		currRow.END_DATE = nui.get("endDate").getValue();
		realPrint(currRow);
	}

	function setData(row) {
		currRow = row;
		nui.get("beginDate").setValue(currRow.BEGIN_DATE);
		nui.get("endDate").setValue(currRow.END_DATE);
	}

	function realPrint(row) {
		debugger;
		var json = nui.encode({
			"printType" : row.PRINT_TYPE,
			"dueNum" : row.SUMMARY_NUM,
			"begDate" : row.BEGIN_DATE,
			"endDate" : row.END_DATE,
			"loanId" : row.LOAN_ID,
			"orgNum" : row.ORG_NUM,
			"sumId" : row.SUMMARY_ID
		});
		
		$.ajax({
			url : "com.bos.pay.LoanSummary.PrintCommon.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				debugger;
				if (text.swfPath) {
					nui.open({
						url : nui.context
								+ "/biz/biz_report/contract_view.jsp?filePath="
								+ text.swfPath,
						title : "借款凭证信息预览",
						width : 1000,
						height : 500,
						ondestroy:function(action){
							CloseWindow();
						}
					});
				} else {
					alert("无打印信息!");
					CloseWindow();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}
</script>
</html>