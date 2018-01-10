<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 卢金彬
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>业务品种参数</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String itemId = request.getParameter("contractId"); %>
	<%String view = request.getParameter("view");%>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"合同信息", url:nui.context+"/pub/LedgerMsg/ledgerContract.jsp?contractId=<%=itemId %>&view=<%=view %>", showCloseButton:false,refreshOnClick:true},
			{title:"借据信息", url:nui.context+"/pub/LedgerMsg/loan_list.jsp?contractId=<%=itemId %>&view=<%=view %>",refreshOnClick:true},
			{title:"担保合同信息", url:nui.context+"/pub/LedgerMsg/sub_list.jsp?contractId=<%=itemId %>&view=<%=view %>",refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>
