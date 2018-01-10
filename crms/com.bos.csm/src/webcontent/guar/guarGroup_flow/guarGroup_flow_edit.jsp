<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-7-20 12:57:38
  - Description:
-->
<head>
<title>联保小组</title> 
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<iframe id="exportFrame" src=""  width="100%" frameborder="0"  height="100%"></iframe>

</body>
<script>
	document.getElementById("exportFrame").src=nui.context+"/csm/guar/guarGroup_tree.jsp?partyId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&partyNum=<%=request.getParameter("partyNum")%>&wflow=<%=request.getParameter("wflow")%>&qote=0&isSrc=2";
</script>
</html>
