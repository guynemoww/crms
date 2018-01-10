<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-22 15:42:02
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<iframe id="exportFrame" src="" width="100%" frameborder="0" height="100%"></iframe>
</body>
</html>

<script>
debugger;
	document.getElementById("exportFrame").src=nui.context+"/csm/corporation/identify/identifyCorpScaleTree.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&wflow=<%=request.getParameter("wflow") %>&edit=2";
</script>