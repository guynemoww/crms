<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-7-20 12:57:38
  - Description:
-->
<head>
<title>名单制管理</title> 
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<iframe id="exportFrame" src=""  width="100%" frameborder="0"  height="100%"></iframe>

</body>
<script>
	document.getElementById("exportFrame").src=nui.context+"/pub/lst/lst_info_confirm_tree.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2&qote=0";
</script>
</html>
