<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>预警管理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="right"
	style="width:100%;height:100%;border:0;"></div>

<script type="text/javascript">
	nui.parse();
	<%String corpid = request.getParameter("corpid"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			//{title:"客户信息", url:nui.context+"/csm/corp/csm_corp_tree.jsp?corpid=<%=corpid %>", showCloseButton:false},
			{title:"单一法人客户预警列表", url:nui.context+"/ews/warnDetail/warnCsmMgr/singleCustom/ews_warning_singleCustomer_list.jsp?partyId=<%=corpid %>&type=01&monitor=<%=request.getParameter("monitor") %>", showCloseButton:false,refreshOnClick:true},
			{title:"集团客户预警列表", url:nui.context+"/ews/warnDetail/warnCsmMgr/groupCustom/ews_warning_groupCustomer_list2.jsp?partyId=<%=corpid %>&type=06&monitor=<%=request.getParameter("monitor") %>", showCloseButton:false,refreshOnClick:true}
			//{title:"集团客户预警列表", url:nui.context+"/ews/warnDetail/warnCsmMgr/groupCustom/ews_warning_groupCustomer_list.jsp?partyId=<%=corpid %>&type=05&monitor=<%=request.getParameter("monitor") %>"}
		    //{title:"担保客户预警列表", url:nui.context+"/ews/warnDetail/warnCsmMgr/warrant/ews_warn_warrantCustomer_list.jsp?partyId=<%=corpid %>&type=06", refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>
