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
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
<script type="text/javascript">
	nui.parse();
	<%String corpid = request.getParameter("corpid"); %>
		var tabs = nui.get("tabs");
/**cusType判断客户类型 1为单一客户，
                     2为集团客户，
                     3为担保客户。
*/
		tabs.setTabs([
			//{title:"客户信息", url:nui.context+"/csm/corp/csm_corp_tree.jsp?corpid=<%=corpid %>", showCloseButton:false},
			{title:"单一客户预警列表", url:nui.context+"/ews/warnDetail/monitor/ews_warn_singleCustomer_list.jsp", showCloseButton:false,refreshOnClick:true},
			//{title:"集团客户预警列表", url:nui.context+"/ews/warnDetail/monitor/ews_warn_groupCustomer_list.jsp"},
		    {title:"担保客户预警列表", url:nui.context+"/ews/warnDetail/monitor/ews_warn_warrantCustomer_list.jsp",refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>