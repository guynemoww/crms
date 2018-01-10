<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 18:06:28
  - Description:
-->
<head>
<title>放款意见提交</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>

<fieldset>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="biz_enter_info_save" iconCls="icon-save" onclick="sub">提交</a>
		<a class="nui-button" id="biz_enter_info_save" iconCls="icon-save" onclick="pay">放款</a>
		<a class="nui-button" id="biz_enter_info_save" iconCls="icon-save" onclick="cel">撤销</a>
	</div>
</fieldset>

</center>
<script type="text/javascript">
	nui.parse();
	var loanId ="<%=request.getParameter("loanId") %>"; //放款ID
	function sub(){
		var json = nui.encode({"loanId":loanId});
		$.ajax({
            url: "com.bos.payInfo.PayInfo.submitProcess.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	alert("出账流程已提交");
			}
        });
	}
	function pay(){
		var json = nui.encode({"loanId":loanId});
		$.ajax({
            url: "com.bos.payInfo.PayInfo.payoutForSummary.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	alert("已生成借据");
			}
        });
	}
	function cel(){
		var json = nui.encode({"loanId":loanId});
		$.ajax({
            url: "com.bos.payInfo.PayInfo.cancelProcess.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	alert("出账流程已撤销");
			}
        });
	}
</script>
</body>
</html>