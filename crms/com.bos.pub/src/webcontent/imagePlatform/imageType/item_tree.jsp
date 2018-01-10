<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):ljf
  - Date: 2015-06-11
  - Description:影像模板管理
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="影像类型" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;"
			showTreeIcon="true" textField="imageTypeName" idField="imageTypeId" parentField="superiorId" expandOnLoad="0"
			onnodeclick="nodeclick" dataField="images" contextMenu="#treeMenu">
		</ul>
	</div>
	
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
	<ul id="treeMenu" class="nui-contextmenu" onbeforeopen="onBeforeOpen"></ul>
			
<script type="text/javascript">
	nui.parse();

	var tree = nui.get("tree1");
	//tree.loadData(menudata);
	var imageModelType="<%=request.getParameter("imageModelType") %>";
	

function reload() {

	var json = nui.encode({"image":{"imageModelType":imageModelType}});

	$.ajax({
            url: "com.bos.pub.image.getImageTypeList2.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            	// alert(nui.encode(text.products));
            		tree.loadList(text.images,"imageTypeId","superiorId");
            		if(tree.getRootNode().children[0]==undefined){
            		var tabs = nui.get("orgTabs");
            		tabs.setTabs([
							{title:"暂无", url:nui.context+"/pub/imagePlatform/imageType/item_list.jsp?imageModelType="+imageModelType}
						]);
						$("#orgTabs").show();
            		}else{
            		  nodeclick({"node":tree.getRootNode().children[0]});
            		}
            		
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
//tree.load("com.bos.pub.product.getProductList.biz.ext");
}
reload();
	
	
	function nodeclick(e) {
		var tabs = nui.get("orgTabs");//alert(nui.encode(e.node));
		
		 tabs.setTabs([
			{title:e.node.imageTypeName, url:nui.context+"/pub/imagePlatform/imageType/item_list.jsp?imageTypeId="+e.node.imageTypeId+"&imageModelType="+imageModelType}
		 ]);
		$("#orgTabs").show();
	}
	
	
	// 机构右键菜单
	var orgMenu = [
		{text: "增加下级分类", onclick: "onAddImage", iconCls: "icon-add"}
	];
	
	
	/**
	 * 根据结点类型显示
	 */
	function onBeforeOpen(e) {
		var tree = nui.get("tree1");
		var node = tree.getSelectedNode();
		var menu = e.sender;
   //	alert(node.nodeType);
		//var menuList = menu_map[node.nodeType];
		//if(!menuList || menuList.length == 0) {
		//	e.cancel = true;
		//	return;
		//}
		menu.loadList(nui.clone(orgMenu)); //这里菜单项必须要克隆，否则第二次点击时无法触发事件
	}
	/**
	 * 获取当前选中结点对象，可提供给子页面调用
	 */
	this.getSelectedNode = function() {
		var tree = nui.get("tree1");
		return tree.getSelectedNode();
	}
	
	function onAddImage(e) {
	
		var url = nui.context+"/pub/imagePlatform/imageType/item_add.jsp?superiorId="+getSelectedNode().imageTypeId+"&imageModelType="+imageModelType;
		  nui.open({
                    url: url,
                    title: "新增影像分类", 
                    width: 800,
            		height: 500,
                    allowResize:true,
            		showMaxButton: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        // var data = row;
                        //iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
	                       reload();
                   	 	}
                    }
                });
	}
	
	</script>
</body>
</html>
