<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-22 15:42:02
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
	document.getElementById("exportFrame").src=nui.context+"/irm/irm_apply/irm_tree.jsp?iraApplyId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&proFlag=1";
</script>