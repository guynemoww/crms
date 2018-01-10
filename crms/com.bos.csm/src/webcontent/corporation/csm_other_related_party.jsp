<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-05-20
  - Description:关联关系维护
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;//ecifPartyNum
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>" 
			var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"对公客户关系个人信息", url:nui.context+"/csm/corporation/csm_other_related_party_list.jsp?partyId="+partyId+"&qote="+qote+"&ecifPartyNum="+ecifPartyNum+"&cType=1",refreshOnClick:true},
			{title:"对公客户关系企业信息", url:nui.context+"/csm/corporation/csm_other_related_party_list_corp.jsp?partyId="+partyId+"&qote="+qote+"&ecifPartyNum="+ecifPartyNum+"&cType=1",refreshOnClick:true}

		]);
		$("#tabs").show();
	
</script>
</body>
</html>
