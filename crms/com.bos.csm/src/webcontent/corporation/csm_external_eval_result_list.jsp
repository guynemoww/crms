<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): wangyanli
  - Date: 2013-11-18 16:54:20
  - Description:
-->
<head>
<title>评级信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<%--<center>
<div id="tabs1" class="nui-tabs" style="width:99.5%;height:100%;overflow:hidden;">
<div title="" url="../../csm/corporation/csm_external_eval_result_list_in.jsp?partyId="+partyId+"&qote="+qote></div>
<div title="外部评级" url="../../csm/corporation/csm_external_eval_result_list_out.jsp?partyId="+partyId+"&qote="+qote></div>


</div> 

</center>--%>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String partyId = request.getParameter("partyId"); %>
	<%String qote = request.getParameter("qote"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"内部评级", url:nui.context+"/csm/corporation/csm_external_eval_result_list_in.jsp?partyId=<%=partyId %>&qote=<%=qote %>"}
<%-- 			{title:"外部评级", url:nui.context+"/csm/corporation/csm_external_eval_result_list_out.jsp?partyId=<%=partyId %>&qote=<%=qote %>"} --%>
		]);
		$("#tabs").show();
</script>
</body>
</html>