<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
-->
<head>
<title>抽样检查</title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/aft/css/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<div id="fmain">
	<div id="fleft">
		<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="抽样检查" region="west" width="240" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:200px;padding:5px;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
		    </div>
		</div>
	</div>
	<div id="fright">
		<iframe id="exportFrame2" src=""  width="100%"  frameborder="0" height="100%" align="top" ></iframe>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var sidId="<%=request.getParameter("bizId") %>";
	var partyId;
	var party;
	var json={"param":{"sidId":sidId}};
		nui.ajax({
		 	url: "com.bos.aft.aft_spot_inspect.querySpotInspect.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: false,
            success: function (text) {
                partyId=text.spotInpsect.partyId;
                party = text.party;
            }
	
	});
	var coverParam=nui.encode({"sidId":sidId,"partyId":partyId});
	document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+sidId;//没取到partyId
	
	var menudata = [
		{id:"贷后检查", text:"贷后检查", //expanded:true, 
			children:[
				{id:"客户情况",text:"客户情况",url:"/aft/aft_spot_inspect/aft_spotCorpInfo.jsp?param="+coverParam+"&callback=y"},
				{id:"现场检查记录",text:"现场检查记录",url:"/aft/aft_spot_inspect/aft_spotLocaleInpsect.jsp?param="+coverParam+"&callback=y"},
				{id:"基本情况",text:"基本情况",url:"/aft/aft_spot_inspect/aft_spot_basicMessage.jsp?param="+coverParam+"&callback=y"},
				{id:"存在的主要问题",text:"存在的主要问题",url:"/aft/aft_spot_inspect/aft_spot_existProblem.jsp?param="+coverParam+"&callback=y"},
				{id:"授信后特定的管理要求",text:"授信后特定的管理要求",url:"/aft/aft_spot_inspect/aft_spot_specialManage.jsp?param="+coverParam+"&callback=y"},
				{id:"检查意见",text:"检查意见",url:"/aft/aft_spot_inspect/aft_spot_InpsectIdea.jsp?param="+coverParam+"&callback=y"+"&processInstId="+<%=request.getParameter("processInstId") %>}
				
			]},
		
		{id:"相关文档",text:"相关文档",
			children:[
					{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd},
					{id:"文档管理",text:"文档管理",url:"/aft/file/relevantFile.jsp?applyId="+sidId+"&button=1"}
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
	nodeclick({"node":menudata[0].children[0]});
	
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