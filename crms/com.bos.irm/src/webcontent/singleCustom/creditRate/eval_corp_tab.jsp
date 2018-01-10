<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-08 17:26:18
  - Description:
-->
<head>
<title>客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
<script type="text/javascript">
	nui.parse();
	<%String partyId = request.getParameter("corpid"); %>
	var tabs = nui.get("tabs");
	tabs.setTabs([
		{title:"评级发起", url:nui.context+"/irm/singleCustom/creditRate/eval_corp_new.jsp?partyId=<%=partyId %>", showCloseButton:false,refreshOnClick:true}
	]);
	$("#tabs").show();
	function create(applyId,node,flowType){
		
		var posicode = "P1046";			//客户经理
	    var url=nui.context+"/irm/singleCustom/creditRate/eval_corp_tree2.jsp?partyId=<%=partyId %>&applyId="+applyId+"&flowType="+flowType+"&node="+nui.encode(node)+"&posicode="+posicode;
		git.go(url);
	}
</script>
</body>
</html>
