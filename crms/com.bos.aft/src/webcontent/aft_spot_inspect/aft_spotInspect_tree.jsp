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
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="客户抽样检查信息" region="west" width="220" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:100%;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="wborder:0;padding-left:5px;padding-top:5px;width: 60%;">
			   <!-- <div id="fright"> -->
		        <iframe id="exportFrame2" src="" style="width: 98%;height: 40px;border: 1px solid Skyblue;" frameborder="0" align="top" ></iframe>
	             <!--</div> -->
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;margin-top: 10px;" refreshOnClick="true"></div>
		    </div>
		</div>
<script type="text/javascript">
	nui.parse();
	var sidId="<%=request.getParameter("bizId") %>";
	var party;
	
	var partyId;
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
	document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+sidId;//没取到partyId
	var coverParam=nui.encode({"sidId":sidId,"partyId":partyId});
	var menudata = [
		{id:"贷后检查", text:"贷后检查", //expanded:true, 
			children:[
				{id:"客户情况",text:"客户情况",url:"/aft/aft_spot_inspect/aft_spotCorpInfo.jsp?param="+coverParam},
				{id:"抽样检查报告",text:"抽样检查报告",url:"/aft/aft_spot_inspect/aft_spotLocaleInpsect.jsp?param="+coverParam},
				//{id:"基本情况",text:"基本情况",url:"/aft/aft_spot_inspect/aft_spot_basicMessage.jsp?param="+coverParam},
				//{id:"存在的主要问题",text:"存在的主要问题",url:"/aft/aft_spot_inspect/aft_spot_existProblem.jsp?param="+coverParam},
				//{id:"授信后特定的管理要求",text:"授信后特定的管理要求",url:"/aft/aft_spot_inspect/aft_spot_specialManage.jsp?param="+coverParam},
				{id:"抽样检查意见",text:"抽样检查意见",url:"/aft/aft_spot_inspect/aft_spot_InpsectIdea.jsp?param="+coverParam+"&processInstId="+<%=request.getParameter("processInstId") %>}
				
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