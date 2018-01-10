<%@page pageEncoding="UTF-8"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-05-21 
  - Description:
-->
<head>
<title>我的客户</title>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
			var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"对私客户关系个人信息", url:nui.context+"/csm/natural/natural_relative_list.jsp?partyId="+partyId+"&qote="+qote+"&cType=person",refreshOnClick:true},
			{title:"对私客户关系企业信息", url:nui.context+"/csm/natural/natural_relative_list_corp.jsp?partyId="+partyId+"&qote="+qote+"&cType=org",refreshOnClick:true}

		]);
		$("#tabs").show();
	
</script>
</body>
</html>