<%@page import="java.net.URLDecoder"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="java.util.Map"%>
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
	var temp =
<%=JspUtil.parameterToStr(request, '&', true)%>
	;

	document.getElementById("exportFrame").src = nui.context
			+ "/risk/sort/risk_sort_tree.jsp?edit=2" + temp;
</script>