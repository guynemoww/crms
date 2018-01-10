<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-10
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
	document.getElementById("exportFrame").src=nui.context+"/aft/conLoanChange/loanChange_tree.jsp?changeId=<%=request.getParameter("bizId")%>&loanChangeType=<%=request.getParameter("bizType")%>&processInstId=<%=request.getParameter("processInstId")%>&proFlag=0";
</script>