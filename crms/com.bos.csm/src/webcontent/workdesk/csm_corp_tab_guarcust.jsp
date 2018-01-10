<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="right"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String corpid = request.getParameter("corpid"); %>
	<%String partyNum = request.getParameter("partyNum"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"客户信息", url:nui.context+"/csm/guarcust/guarcust_add_tree.jsp?partyId=<%=corpid %>&qote=1&partyNum=<%=partyNum %>", showCloseButton:false},
			{title:"评级信息", url:nui.context+"/irm/singleCustom/creditRate/eval_corp_tab.jsp?corpid=<%=corpid %>",refreshOnClick:true},
			<%--{title:"押品信息", url:nui.context+"/grt/manage/manage_pages/grt_col_manage_index.jsp?corpid=<%=corpid %>",refreshOnClick:true},--%>
		<%--{title:"额度申请", url:nui.context+"/crd/crdsingle/crd_single_tab.jsp?corpid=<%=corpid %>",refreshOnClick:true},--%>
			{title:"业务申请", url:nui.context+"/biz/biz_guatee_view/biz_apply_tab.jsp?corpid=<%=corpid %>",refreshOnClick:true},
			{title:"合同签约", url:nui.context+"/crt/con_enter.jsp?corpid=<%=corpid %>"},
			{title:"日常检查", url:nui.context+"/aft/aft_warrant/gurantyFlow.jsp?corpid=<%=corpid %>"},
			{title:"客户预警", url:nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid=<%=corpid %>&type=06",refreshOnClick:true}
			<%--{title:"分类", url:nui.context+"/crt/con_enterx.jsp?corpid=<%=corpid %>"},
			{title:"资产保全", url:nui.context+"/npl/assertTransfer/npl_handleSchme_tabs.jsp?corpid=<%=corpid %>"}--%>
		]);
		$("#tabs").show();
</script>
</body>
</html>
