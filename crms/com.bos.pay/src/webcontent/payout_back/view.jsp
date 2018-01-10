<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-06-15 15:42:43
  - Description:
-->
<head>
<title>流程审批页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<iframe id="exportFrame" src=""  width="100%" frameborder="0"  height="100%"></iframe>

</body>
</html>

<script>
	document.getElementById("exportFrame").src=nui.context+"/pay/payout_info/pay_tree1.jsp?loanId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&proFlag=0";
</script>