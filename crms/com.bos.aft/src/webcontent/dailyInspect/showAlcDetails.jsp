<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lizhi 
  - Date: 2014-06-10 10:16:54
  - Description:显示客户日常检查详情
-->
<head>
<title>客户日常检查信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="客户日常检查信息" region="west" width="240" class="sub-sidebar" allowResize="false">
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
	var lastAlcInfoId = '<%=request.getParameter("lastAlcInfoId") %>';
	var menudata = [
		{id:"填写客户相关信息", text:"填写客户相关信息", //expanded:true, 
			children:[
			{id:"基本情况",text:"基本情况",url:"/aft/dailyInspect/customerDetails.jsp?pageName=basicConditionPage&lastAlcInfoId="+lastAlcInfoId},
			{id:"经营情况",text:"经营情况",url:"/aft/dailyInspect/customerDetails.jsp?pageName=operateConditionPage&lastAlcInfoId="+lastAlcInfoId},
			{id:"财务情况",text:"财务情况",url:"/aft/dailyInspect/customerDetails.jsp?pageName=financeConditionPage&lastAlcInfoId="+lastAlcInfoId},
			{id:"信用与还款意愿",text:"信用与还款意愿",url:"/aft/dailyInspect/customerDetails.jsp?pageName=creditConditionPage&lastAlcInfoId="+lastAlcInfoId},
			{id:"外部环境与重大事项",text:"外部环境与重大事项",url:"/aft/dailyInspect/customerDetails.jsp?pageName=externalFactorPage&lastAlcInfoId="+lastAlcInfoId},
			]
		},
		{id:"填写债项级信息",text:"填写债项级信息",url:"/aft/dailyInspect/customerDetails.jsp?pageName=operateConditionPage&lastAlcInfoId="+lastAlcInfoId}
		
	];
	
	var tree = nui.get("tree1");
	tree.loadData(menudata);
	
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='ecmadd') {
			ecm('add');
			return;
		}
		if (e.node['id']=='ecmview') {
			ecm('view');
			return;
		}
		if (e.node['id']=='ecmprint') {
			ecm('print');
			return;
		}
		
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	nodeclick({"node":menudata[0].children[0]});
	
</script>

</body>
</html>