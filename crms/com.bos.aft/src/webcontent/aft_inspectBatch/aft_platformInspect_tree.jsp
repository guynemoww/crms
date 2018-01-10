<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
-->
<head> 
<title>批量检查</title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/aft/css/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>

		<div id="layout1" class="nui-layout" style="width:98%;height:100%;">
			<div title="批量检查" region="west" width="240" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:200px;padding:5px;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
				<iframe id="exportFrame2" src=""  style="width:98%;height:40px;border: 1px solid Skyblue;"  frameborder="0" align="top" ></iframe>
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
		    </div>
		</div>

<script type="text/javascript">
	
	nui.parse();
	var party;
	var param=<%=request.getParameter("param") %>;
	var bizId="<%=request.getParameter("bizId") %>";
	if(bizId=="null"){
	    bizId=param.pfId;
	}
	var json = nui.encode({partyId:param.partyId});
	$.ajax({
            url: "com.bos.aft.common.getParty.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {     	
            	party = mydata.party;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        });
	var menudata = [
		{id:"贷后检查", text:"贷后检查", //expanded:true, 
			children:[
				{id:"平台客户情况",text:"平台客户情况",url:"/aft/aft_inspectBatch/aft_platformCorpInfo.jsp?param="+nui.encode(param)},
				{id:"平台综合情况",text:"平台综合情况",url:"/aft/aft_inspectBatch/aft_pfComprehensiveInfo_edit.jsp?param="+nui.encode(param)},
				{id:"平台单户检查",text:"平台单户检查",url:"/aft/aft_inspectBatch/aft_pfDetailInfo.jsp?param="+nui.encode(param)},
				{id:"意见",text:"意见",url:"/aft/aft_inspectBatch/aft_platformIdea.jsp?param="+nui.encode(param)}
			]},
		
		{id:"相关文档",text:"相关文档",
			children:[
					{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd},
					{id:"文档管理",text:"文档管理",url:"/aft/file/relevantFile.jsp?applyId="+bizId+"&button=1"+"&corpType=3"+"&party="+nui.encode(party)}
			]}

	];
	var param2 = <%=request.getParameter("param") %>;
	
	document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+bizId;
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