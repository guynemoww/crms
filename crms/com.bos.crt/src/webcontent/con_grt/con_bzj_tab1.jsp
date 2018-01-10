<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:30:35
  - Description:
-->
<head>
<title>保证合同页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
	nui.parse();
	var tabs = nui.get("tabs");
	var contractId="<%=request.getParameter("contractId")%>";
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var partyId="<%=request.getParameter("partyId")%>";
	var view="<%=request.getParameter("view") %>";
	var xgbz="<%=request.getParameter("xgbz")%>";
	
	//新增抵质押合同
	tabs.setTabs([
		{title:"保证金协议基本信息", url:nui.context+"/crt/con_grt/con_bzj_info1.jsp?subcontractId="+subcontractId+"&contractId="+contractId+"&view="+view+"&partyId="+partyId+"&applyId="+applyId+"&xgbz="+xgbz,refreshOnClick:true},
		{title:"保证金关联信息", url:nui.context+"/crt/con_grt/con_grt_bzj_rel1.jsp?subcontractId="+subcontractId+"&contractId="+contractId+"&applyId="+applyId+"&subcontractType=04&partyId="+partyId+"&xgbz="+xgbz+"&view="+view,refreshOnClick:true}
	]);
	$("#tabs").show();

</script>
</body>
</html>