<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-07-30 10:03:17
  - Description:
-->
<head>
<title>贷后检查</title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/aft/css/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>

		<div id="layout1" class="nui-layout" style="width:98%;height:100%;">
			<div title="贷后检查" region="west" width="240" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:200px;padding:5px;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="width:98%;border:0;padding-left:5px;padding-top:5px;">
				<iframe id="exportFrame2" src=""  style="width:98%;height:40px;border: 1px solid Skyblue;" frameborder="0"  align="top" ></iframe>
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
		    </div>
		</div>

<script type="text/javascript">
	nui.parse();
	var bizId = "<%=request.getParameter("bizId")%>";
	var processInstId = "<%=request.getParameter("processInstId") %>";//实例号
	var posicode = '<%=request.getParameter("activityDefId") %>';
	var json=nui.encode({"bizId":bizId});
	var party;
	var partyId;
    $.ajax({
            url: "com.bos.aft.aft_warrant.queryCustInfo2.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {
            	party=mydata.item;
            	partyId=party.partyId;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
    });
	document.getElementById("exportFrame2").src=nui.context+"/aft/aft_warrant/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+bizId;
	var menudata = [
		{id:"担保公司贷后检查", text:"担保公司贷后检查", //expanded:true, 
			children:[
				{id:"基本情况",text:"基本情况",url:"/aft/aft_warrant/aft_dailyIns_baseInfo.jsp?partyId="+partyId+"&giId="+bizId+"&posicode="+posicode},
				{id:"与银行合作",text:"与银行合作",url:"/aft/aft_warrant/aft_dailyIns_bankInfo.jsp?partyId="+partyId+"&giId="+bizId+"&processInstId="+processInstId+"&posicode="+posicode},
				{id:"相关文档",text:"相关文档",
					children:[
						{id:"文档管理",text:"文档管理",url:"/aft/file/relevantFile.jsp?applyId="+bizId+"&button=1"},
						{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd}
					]}
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