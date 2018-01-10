<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ganquan
  - Date: 2015-5-5 12:57:38
  - Description:
-->
<head>
<title>集团客户</title> 
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<iframe id="exportFrame" src=""  width="100%" frameborder="0"  height="100%"></iframe>

</body>
<script>
	document.getElementById("exportFrame").src=nui.context+"/csm/company/groupCompany_tree.jsp?partyId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&partyNum=<%=request.getParameter("partyNum")%>&wflow=<%=request.getParameter("wflow")%>&qote=2";
</script>
</html>
