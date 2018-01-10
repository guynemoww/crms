<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-15
  - Description:
-->
<head>
<title>客户经理审批页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<iframe id="exportFrame" src=""  width="100%" frameborder="0"  height="100%"></iframe>

</body>
</html>

<script>
	document.getElementById("exportFrame").src=nui.context+"/aft/firstCheck/firstCheck_tree.jsp?firstCheckId=<%=request.getParameter("bizId")%>&isSmall=<%=request.getParameter("bizType")%>&processInstId=<%=request.getParameter("processInstId")%>&proFlag=1";
</script>