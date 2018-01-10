<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):ljf
  - Date: 2015-06-04
  - Description:影像管理
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="影像类型" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;"
			showTreeIcon="true" textField="imageTypeName" idField="imageTypeId" parentField="superiorId" expandOnLoad="true"
			onnodeclick="nodeclick" dataField="images" contextMenu="#treeMenu">
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<ul id="treeMenu" class="nui-contextmenu" onbeforeopen="onBeforeOpen"></ul>
			
<script type="text/javascript">
debugger;
	nui.parse();
	var tree = nui.get("tree1");
	//业务号
	var businessNumber = "<%=request.getParameter("businessNumber")%>"; 
	//客户号
	var csmNum ="<%=request.getParameter("csmNum")%>";
	//标志查询，还是扫描。 1查询，2扫描
	var view="<%=request.getParameter("view") %>";
	//客户类型，用于查找对应的结构树
	var partyTypeCd="<%=request.getParameter("partyTypeCd") %>";
	//流程模块类型 ，用于控制在不同的流程阶段，查询对应的树节点
	var flowModuleType="<%=request.getParameter("flowModuleType") %>";
	
	//加载左侧树结构	
	function reload() {
		
		var json = nui.encode({"image":{"imageModelType":partyTypeCd,"flowModuleType":flowModuleType}});
		$.ajax({
	        url: "com.bos.pub.image.getImageTypeList.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		nui.alert(text.msg);
	        	} else {
	        		tree.loadList(text.images,"imageTypeId","superiorId");
	        		nodeclick({"node":tree.getRootNode().children[0].children[0].children[0]});
	        		
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
	}
	reload();
	function nodeclick(e) {
		//alert(nui.encode(e.node.imageTypeName));tree.getLevel (e.node)
		var tabs = nui.get("orgTabs");//alert(nui.encode(e.node));
		url =nui.context+"/pub/imagePlatform/item_list.jsp?businessNumber="+businessNumber+"&csmNum="+csmNum
		+"&imageTypeId="+e.node.imageTypeId+"&imageTypeName="+e.node.imageTypeName+"&nodeFlag="+tree.isLeaf(e.node)
		+"&view="+view+"&partyTypeCd=01"+"&imageControlType="+e.node.imageControlType;
 		url = encodeURI(encodeURI(url));
         tabs.setTabs([
			{title:e.node.imageTypeName, url:url}
		]);
		$("#orgTabs").show();
	}

</script>
</body>
</html>
