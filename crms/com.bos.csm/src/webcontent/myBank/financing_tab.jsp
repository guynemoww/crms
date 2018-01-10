<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-05-06 16:18:58
  - Description:
-->
<head>
<title>本行融资情况</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String partyId = request.getParameter("partyId"); 
		String partyTypeCd = request.getParameter("partyTypeCd"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"批复信息", url:nui.context+"/csm/myBank/financing_list.jsp?partyId=<%=partyId %>", showCloseButton:false},
			{title:"业务信息", url:nui.context+"/csm/myBank/financing_list_yewu.jsp?partyId=<%=partyId %>&partyTypeCd=<%=partyTypeCd %>", showCloseButton:false}
		]);
		$("#tabs").show();
</script>
</body>
</html>
