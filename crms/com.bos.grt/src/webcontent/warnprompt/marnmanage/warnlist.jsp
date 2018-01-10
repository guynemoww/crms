<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>押品预警</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="押品预警" region="west" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" 
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

	var menudata;
	menudata = [
		{id:"汇票到期提示",text:"汇票到期提示",url:"/grt/warnprompt/marnmanage/TbGrtDraftexpire.jsp"},
		{id:"重复抵质押提示",text:"重复抵质押提示",url:"/grt/warnprompt/marnmanage/TbGrtAgainreach.jsp"},
		{id:"押品重估提示",text:"押品重估提示",url:"/grt/warnprompt/marnmanage/TbGrtAgainprompt.jsp"},
		{id:"保险到期提示",text:"保险到期提示",url:"/grt/warnprompt/marnmanage/TbGrtSafeexpire.jsp"},
		{id:"存单到期提示",text:"存单到期提示",url:"/grt/warnprompt/marnmanage/TbGrtDepositreceipt.jsp"},
		{id:"权证归还时间提示",text:"权证归还时间提示",url:"/grt/warnprompt/marnmanage/TbGrtCardrevert.jsp"}
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
//进入树页面默认进入	
nodeclick({"node":menudata[0]});
	
</script>

</body>
</html>