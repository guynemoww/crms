<%@page import="com.bos.csm.pub.CsmUtil"%>
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
	<%String custType = request.getParameter("cusType"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			//{title:"客户信息", url:nui.context+"/csm/corp/csm_corp_tree.jsp?corpid=<%=corpid %>", showCloseButton:false},
			{title:"客户信息", url:nui.context+"/csm/corporation/csm_corporation_tree.jsp?partyId=<%=corpid %>&cusType=<%=custType %>&qote=1&partyNum=<%=partyNum %>", showCloseButton:false},
			<% if(CsmUtil.isCorporation(custType)){%>
			{title:"企业规模认定 ", url:nui.context+"/csm/corporation/identify/identifyCorpScaleList.jsp?partyId=<%=corpid %>",refreshOnClick:true},
			<% }%>
			{title:"评级申请", url:nui.context+"/irm/irm_apply/irm_apply.jsp?corpid=<%=corpid %>",refreshOnClick:true},
			{title:"额度信息", url:nui.context+"/crd/crd_apply/crd_apply.jsp?corpid=<%=corpid %>",refreshOnClick:true},
			{title:"业务申请", url:nui.context+"/biz/biz_apply/biz_apply_tab.jsp?corpid=<%=corpid %>",refreshOnClick:true},
			{title:"合同签约", url:nui.context+"/crt/con_apply/con_apply_tab.jsp?corpid=<%=corpid %>",refreshOnClick:true},
			<%--{title:"合同变更", url:nui.context+"/crt/change/contract_change.jsp?corpid=<%=corpid %>"},--%>
			<%-- {title:"日常检查", url:nui.context+"/aft/daily/dailyList.jsp?corpid=<%=corpid %>",refreshOnClick:true}, --%>
			<%--{title:"资金流向监控", url:nui.context+"/aft/aft_capital_flows/aft_capitalFlowsInfo_add.jsp?corpid=<%=corpid %>"},--%>
			{title:"客户预警", url:nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid=<%=corpid %>",refreshOnClick:true}
		    <%--{title:"分类", url:nui.context+"/risk/cust/risk_cust_list.jsp?partyId=<%=corpid %>&position=mycust",refreshOnClick:true}--%>
			<%--{title:"资产保全", url:nui.context+"/npl/assets/npl_tab.jsp?corpid=<%=corpid %>"}--%>
		]);
		$("#tabs").show();
</script>
</body>
</html>
