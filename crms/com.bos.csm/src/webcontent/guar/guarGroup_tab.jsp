<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ganquan
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>联保小组授信信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String partyId = request.getParameter("partyId"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"批复信息", url:nui.context+"/csm/guar/guarGroup_pifu.jsp?partyId=<%=partyId %>", showCloseButton:false},
			{title:"业务信息", url:nui.context+"/csm/guar/guarGroup_yewu.jsp?partyId=<%=partyId %>", showCloseButton:false}
		]);
		$("#tabs").show();
</script>
</body>
</html>
