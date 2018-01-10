<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2013-09-05 22:17:51
  - Description:
-->
<head>
<title>业务申请</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>

<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="业务申请" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
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
		{id:"贷款结构", text:"贷款结构", //expanded:true, 
			children:[
				{id:"基本信息",text:"基本信息",url:"/biz/biz_grt/biz_enter_info_view.jsp?bizId=<%=request.getParameter("bizId") %>"},
				{id:"业务明细",text:"业务明细",url:"/biz/biz_grt/biz_detail_enter_view.jsp?bizId=<%=request.getParameter("bizId") %>"}
			]}, 
		{id:"担保评价", text:"担保评价",
			children:[
				{id:"抵质押",text:"抵质押",url:"/biz/biz_grt/biz_Collateral_list.jsp?bizId=<%=request.getParameter("bizId") %>"},
				{id:"保证",text:"保证",url:""},
				{id:"保证金",text:"保证金",url:""},
				{id:"信用保险",text:"信用保险",url:""},
				{id:"备用信用证",text:"备用信用证",url:""}
			]},	
		{id:"调查报告",text:"调查报告",url:"/xx"},
		{id:"相关文档", text:"相关文档",
			children:[
				{id:"ecmprint",text:"条码打印",url:"/xx"},
				{id:"ecmadd",text:"影像新增",url:"/xx"},
				{id:"ecmview",text:"影像查看",url:"/xx"}
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
		if (e.node['id']=='调查报告') {
			var ifrm = document.getElementById("exportFrame");
			ifrm.src="com.bos.biz.ExportManager.flow?_eosFlowAction=download&bizId=<%=request.getParameter("bizId") %>";
			return;
		}
		
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	nodeclick({"node":menudata[0].children[0]});
	
	var json = nui.encode({"biz/bizId":"<%=request.getParameter("bizId") %>"});
        $.ajax({
            url: "com.bos.biz.createBiz.getBiz.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                window['formData'] = o;
                //alert(nui.encode(o.corp.tbCsmParty));
            }
        });
//{"bizno":"<%=request.getParameter("bizId") %>","op":op,"custnum":window.formData.corp.tbCsmParty.partyid}
function ecm(op) {
		var json = nui.encode({"op":op,"custnum":"12345"});
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