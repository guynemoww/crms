<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ganquan
  - Date: 2015-5-5 12:57:38
  - Description:
-->
<head>
<title>客户共享审批意见</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="layout1" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="客户共享审批意见" region="west" bodyStyle="" width="240"
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
	var partyId = "<%=request.getParameter("partyId")%>" ;
	var partyNum = "<%=request.getParameter("partyNum")%>";
	var bizId = "<%=request.getParameter("bizId")%>";
	var wflow = "<%=request.getParameter("wflow")%>";
	var MemId ;//本部成员ID
	var menudata;
	var state;
	function initForm() {
		if(wflow=='1'){
			menudata = [
				{id:"共享信息",text:"共享信息",url:"/csm/share/customer_share_confirm.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=<%=request.getParameter("isSrc")%>"}
			];
		}else{
			menudata = [
				{id:"共享信息",text:"共享信息",url:"/csm/share/customer_share_confirm.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=<%=request.getParameter("isSrc")%>"},
				{id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=<%=request.getParameter("isSrc")%>"} 
			];
		}
		

		var tree = nui.get("tree1");
		tree.loadData(menudata);
		nodeclick({
			"node" : menudata[0]
		});
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
			if (e.node['id'] == '影像扫描') {
				nui
						.open({
							url : nui.context
									+ "/pub/imagePlatform/item_tree.jsp?businessNumber="
									+ partyNum + "&csmNum=" + partyNum
									+ "&partyTypeCd=05&image=custom",
							title : "影像扫描",
							width : 1200,
							height : 600,
							state : "max",
							allowResize : true,
							showMaxButton : true,
							ondestroy : function(action) {
								if (action == "ok") {
								}
							}
						});
				return;
			}
			if (e.node['id'] == '影像查询') {
				nui
						.open({
							url : nui.context
									+ "/pub/imagePlatform/item_tree.jsp?businessNumber="
									+ partyNum + "&csmNum=" + partyNum
									+ "&partyTypeCd=05" + "&view=" + qote,
							title : "影像查询",
							width : 1200,
							height : 600,
							state : "max",
							allowResize : true,
							showMaxButton : true,
							ondestroy : function(action) {
								if (action == "ok") {
								}
							}
						});
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
