<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 10:24:58
  - Description:
-->
<head>
<title>客户经理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<iframe id="exportFrame" src=""  width="100%" frameborder="0"  height="100%"></iframe>

<script>
	document.getElementById("exportFrame").src=nui.context+"/npl/assets/assets_tree.jsp?schemeId=<%=request.getParameter("bizId")%>";
</script>

</body>
</html>