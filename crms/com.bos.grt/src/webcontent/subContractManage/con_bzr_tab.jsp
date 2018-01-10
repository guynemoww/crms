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
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var contractType="<%=request.getParameter("contractType")%>";
	var view="<%=request.getParameter("view")%>";
	var conSubconId="<%=request.getParameter("conSubconId")%>";
	var contractId="<%=request.getParameter("contractId")%>";
	//新增抵质押合同
	tabs.setTabs([
		{title:"保证合同基本信息", url:nui.context+"/crt/con_grt/con_bzr_info.jsp?subcontractId="+subcontractId+"&contractType="+contractType+"&view="+view+"&conSubconId="+conSubconId+"&contractId="+contractId,refreshOnClick:true},
		{title:"保证人关联信息", url:nui.context+"/crt/con_grt/con_grt_bzr_rel.jsp?subcontractId="+subcontractId+"&applyId="+applyId+"&subcontractType=04&view="+view,refreshOnClick:true}
	]);
	$("#tabs").show();

</script>
</body>
</html>