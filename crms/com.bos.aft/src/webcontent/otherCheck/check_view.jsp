<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-02
  - Description:
-->
<head>
<title>流程审批页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<iframe id="exportFrame" src="" width="100%" frameborder="0" height="100%"></iframe>

</body>
</html>

<script>
	var checkType =
<%=JspUtil.getParameterHaveSign(request, "bizType")%>
	;
	var wflow =
<%=JspUtil.getParameterHaveSign(request, "wflow", "1")%>
	;
	var bizId =
<%=JspUtil.getParameterHaveSign(request, "bizId")%>
	;
	var processInstId =
<%=JspUtil.getParameterHaveSign(request, "processInstId")%>
	;
	debugger;
	var proFlag = wflow == "2" ? "0" : "-1";
	if (checkType == "01") {
		document.getElementById("exportFrame").src = nui.context
				+ "/aft/firstCheck/firstCheck_tree.jsp?firstCheckId=" + bizId
				+ "&isSmall=" + checkType + "&processInstId=" + processInstId
				+ "&proFlag=" + proFlag;
	} else if (checkType == "02") {
		document.getElementById("exportFrame").src = nui.context
				+ "/aft/normalCheck/normalCheck_tree.jsp?normalCheckId="
				+ bizId + "&checkType=" + checkType + "&processInstId="
				+ processInstId + "&proFlag=" + proFlag;
	} else if (checkType == "e" || checkType == "p") {
		document.getElementById("exportFrame").src = nui.context
				+ "/aft/otherCheck/check_tree.jsp?checkId=" + bizId
				+ "&checkType=" + checkType + "&processInstId=" + processInstId
				+ "&proFlag=" + proFlag;
	}
</script>