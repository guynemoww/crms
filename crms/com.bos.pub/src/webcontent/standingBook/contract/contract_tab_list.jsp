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
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String contractId = request.getParameter("contractId"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"合同信息", url:nui.context+"/pub/standingBook/contract/acc_contract_main.jsp?contractId=<%=contractId %>", showCloseButton:false}
		]);
		//$("#tabs").show();
</script>
</body>
</html>
