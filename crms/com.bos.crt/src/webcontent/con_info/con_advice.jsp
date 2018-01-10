<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 16:53:37
  - Description:
-->
<head>
<title>意见提交</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>

<fieldset>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="biz_enter_info_save" iconCls="icon-save" onclick="sub">提交</a>
	</div>
</fieldset>

</center>
<script type="text/javascript">
	nui.parse();

	function sub(){
		var json = nui.encode({"contractId":"<%=request.getParameter("contractId")%>","contractType":"<%=request.getParameter("contractType")%>"});
		$.ajax({
            url: "com.bos.conInfo.adviceAndFile.submitProcess.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	alert("合同已生效");
			}
        });
	}
</script>
</body>
</html>