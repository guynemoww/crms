<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>我的业务信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="right"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
<script type="text/javascript">
	nui.parse();
	<%String contractId = request.getParameter("contractId"); %>
	
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"合同信息", url:nui.context+"/crt/contract_manage.jsp?bizId=<%=contractId %>&contractId=<%=contractId %>", showCloseButton:false,refreshOnClick:true},
			{title:"放款信息", url:nui.context+"/pay/payOutMessage/payout_info.jsp?contractId=<%=contractId %>",refreshOnClick:true},
			{title:"对公资金流向监控", url:nui.context+"/pay/payOutMessage/payout_infox.jsp?contractId=<%=contractId %>",refreshOnClick:true},
			{title:"首次检查", url:nui.context+"/pay/payOutMessage/payout_infox.jsp?contractId=<%=contractId %>",refreshOnClick:true},
			{title:"贷款要素变更", url:nui.context+"/pay/payOutMessage/payout_infox.jsp?contractId=<%=contractId %>",refreshOnClick:true},
			{title:"合同终止", url:nui.context+"/pay/payOutMessage/payout_infox.jsp?contractId=<%=contractId %>",refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>
