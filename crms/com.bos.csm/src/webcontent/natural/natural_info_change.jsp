<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-05-20 10:46:30
  - Description:
-->
<head>
<title>共享信息审核</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="共享信息审核" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var qote = "<%=request.getParameter("qote")%>";
	var partyId = "<%=request.getParameter("partyId") %>";
	var partyNum = "<%=request.getParameter("partyNum") %>" ;
	var view;
	
	var menudata = [				
		{id:"变更信息",text:"变更信息",url:"/csm/natural/natural_change_list.jsp?bizId=<%=request.getParameter("bizId") %>&processInstId=<%=request.getParameter("processInstId")%>"},
		{id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("bizId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=<%=request.getParameter("isSrc")%>"}
	];
	
	var tree = nui.get("tree1");
	tree.loadData(menudata);
	
	function nodeclick(e) {
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	nodeclick({"node":menudata[0]});
	
</script>
</body>
</html>