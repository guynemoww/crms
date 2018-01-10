<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ganquan
  - Date: 2015-5-5 12:57:38
  - Description:
-->
<head>
<title>审批意见</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="layout1" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="审批意见" region="west" bodyStyle="" width="240"
			class="sub-sidebar" allowResize="false">
			<ul id="tree1" class="nui-tree" style="" showTreeIcon="true"
				textField="text" idField="id" expandOnLoad="true"
				onnodeclick="nodeclick">
			</ul>
		</div>
		<div title="center" region="center"
			style="border: 0; padding-left: 5px; padding-top: 5px;">
			<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0"
				style="width: 100%; height: 100%; border: 0;" refreshOnClick="true"></div>
		</div>
	</div>

	<script type="text/javascript">
	nui.parse(); 
	git.mask();
	var qote  = "<%=request.getParameter("qote")%>" ;
	var partyId = "<%=request.getParameter("bizId")%>" ;
	var processInstId = "<%=request.getParameter("processInstId")%>" ;
	var wflow = "<%=request.getParameter("wflow")%>";
	var isSrc = "<%=request.getParameter("isSrc")%>";
	var isBlack = '0' ;//是否为黑名单客户(0不是 1是)
	function initForm() {
		menudata = [
			{id:"信息",text:"信息",url:"/pub/lst/lst_info_adjust.jsp?partyId="+partyId+"&processInstId="+processInstId+"&qote="+qote+"&newStatus="+isBlack},
			{id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=<%=request.getParameter("isSrc")%>"} 
		];

	 //如果是待办进入，显示意见，跟踪或查看进入，不显示意见
	 if(wflow=='1'){
		menudata.splice(1, 1);
     }
	
		var tree = nui.get("tree1");
		tree.loadData(menudata);
		nodeclick({"node" : menudata[0]});
		git.unmask();

	}
	initForm();

		function nodeclick(e) {
			if (!e.node['url']) {
				return;
			}
			if (e.node['id'] == 'ecmadd') {
				ecm('add');
				return;
			}
			if (e.node['id'] == 'ecmview') {
				ecm('view');
				return;
			}
			if (e.node['id'] == 'ecmprint') {
				ecm('print');
				return;
			}
			var tabs = nui.get("orgTabs");
			tabs.setTabs([ {
				title : e.node.text,
				url : nui.context + e.node.url
			} ]);
			$("#orgTabs").show();
		}
	</script>

</body>
</html>
