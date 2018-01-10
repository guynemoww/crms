<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>为我行客户担保情况</title>
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
			{title:"本行保证情况", url:nui.context+"/csm/myBank/guarantee_list.jsp?partyId=<%=partyId %>", showCloseButton:false},
			{title:"本行抵质押情况", url:nui.context+"/csm/myBank/guarantee_list_diya.jsp?partyId=<%=partyId %>", showCloseButton:false}
		]);
		$("#tabs").show();
</script>
</body>
</html>
