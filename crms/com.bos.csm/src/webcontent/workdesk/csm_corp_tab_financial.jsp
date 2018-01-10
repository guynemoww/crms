<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>客户信息-同业客户</title>
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
			{title:"客户信息", url:nui.context+"/csm/financialinstitution/csm_financialinstitution_tree.jsp?partyId=<%=corpid %>&qote=1&partyNum=<%=partyNum %>", showCloseButton:false},
			<%--{title:"评级信息", url:nui.context+"/irm/irm_apply/irm_apply.jsp?corpid=<%=corpid %>",refreshOnClick:true},--%>
			<%--{title:"押品信息", url:nui.context+"/grt/manage/manage_pages/grt_col_manage_index.jsp?corpid=<%=corpid %>",refreshOnClick:true},--%>
			{title:"额度申请", url:nui.context+"/crd/crd_apply/crd_apply.jsp?corpid=<%=corpid %>",refreshOnClick:true},
			<%--{title:"业务申请", url:nui.context+"/biz/financial/biz_apply_tab.jsp?corpid=<%=corpid %>",refreshOnClick:true},--%>
			<%--{title:"合同签约", url:nui.context+"/crt/peersAdd/con_peers_apply_tab.jsp?corpid=<%=corpid %>"},--%>
			<%-- {title:"客户预警", url:nui.context+"/cls/clsdetail/cls_warning_tree.jsp?corpid=<%=corpid %>",refreshOnClick:true} --%>
			{title:"客户预警", url:nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid=<%=corpid %>&type=05",refreshOnClick:true}
<%--			{title:"分类", url:nui.context+"/crt/con_enterx.jsp?corpid=<%=corpid %>"},
			{title:"资产保全", url:nui.context+"/npl/assertTransfer/npl_handleSchme_tabs.jsp?corpid=<%=corpid %>"}--%>
		]);
		$("#tabs").show();
</script>
</body>
</html>
