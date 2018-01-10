<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-15
  - Description:
-->
<head>
<title>流程查看页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<iframe id="exportFrame" src=""  width="100%" frameborder="0"  height="100%"></iframe>

</body>
</html>

<script>       
var checkType ="<%=request.getParameter("bizType") %>";


if(checkType=="01"){
	document.getElementById("exportFrame").src=nui.context+"/aft/firstCheck/firstCheck_tree.jsp?firstCheckId=<%=request.getParameter("bizId")%>&isSmall=<%=request.getParameter("bizType")%>&processInstId=<%=request.getParameter("processInstId")%>&proFlag=-1";
}else if(checkType=="02"){
	document.getElementById("exportFrame").src=nui.context+"/aft/normalCheck/normalCheck_tree.jsp?normalCheckId=<%=request.getParameter("bizId")%>&checkType=<%=request.getParameter("bizType")%>&processInstId=<%=request.getParameter("processInstId")%>&proFlag=-1";
}else if(checkType=="e" || checkType=="p" ){
	document.getElementById("exportFrame").src=nui.context+"/aft/otherCheck/check_tree.jsp?checkId=<%=request.getParameter("bizId")%>&checkType=<%=request.getParameter("bizType")%>&processInstId=<%=request.getParameter("processInstId")%>&proFlag=-1";

}
                                 
</script>