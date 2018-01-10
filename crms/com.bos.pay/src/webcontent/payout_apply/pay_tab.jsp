<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-14 11:01:53
  - Description:
-->
<head>
<title>发放支付</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String contractId = request.getParameter("contractId"); %>
	<%String partyId = request.getParameter("partyId"); %>
	var recomSumId="1";
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"合同信息", url:nui.context+"/crt/con_info/con_tree.jsp?contractId=<%=contractId %>&contractType=02&proFlag=-1", showCloseButton:false},
			{title:"借据信息", url:nui.context+"/pay/payout_apply/pay_apply.jsp?contractId=<%=contractId %>&partyId=<%=partyId %>&proFlag=1"}
			<%--{title:"放款信息", url:nui.context+"/pay/payout_apply/payout.jsp?contractId=<%=contractId %>&partyId=<%=partyId %>&proFlag=1"}--%>
		]);
		$("#tabs").show();
</script>
</body>
</html>