<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 卢金彬
  - Date: 2014-03-17
  - Description:
-->
<head>
<title>统计查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="统计查询" region="west" width="240" class="sub-sidebar" allowResize="false">
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
	
	var menudata = [
			{id:"按计分项目统计",text:"按计分项目统计",url:"/pub/findMessage/projectStatistics.jsp"},
			{id:"按机构统计",text:"按机构统计",url:"/pub/findMessage/orgStatistics.jsp"},
			{id:"明细查询",text:"明细查询",url:"/pub/findMessage/projectDetailsInquire.jsp"},
			{id:"处罚查询",text:"处罚查询",url:"/pub/findMessage/punishmentQuery.jsp"},
			{id:"汇总查询", text:"汇总查询",
			children:[
			{id:"按机构汇总",text:"按机构汇总",url:"/pub/findMessage/projectOrgStatistics.jsp"},
			{id:"按计分对象汇总",text:"按计分对象汇总",url:"/pub/findMessage/ScorerStatisics.jsp"}
			]}
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
	nodeclick({"node":menudata[0]});
	
</script>
<script type="text/javascript">
nui.parse();
//{"custnum":"<%=request.getParameter("corpid") %>","op":op}
function ecm(op) {
		var json = nui.encode({"custnum":"12345","op":op});
        $.ajax({
            url: "com.bos.csm.corp.customerinfo.ecm.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                window.open(o.url);
            }
        });
}
</script>
</body>
</html>