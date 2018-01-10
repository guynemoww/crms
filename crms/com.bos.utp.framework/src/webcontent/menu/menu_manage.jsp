<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link id="css_icon" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/utp/tools/icons/icon.css"/>
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-02-28 10:38:33
  - Description:菜单管理入口
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<style>
#tree1 .mini-grid-viewport{
	background-color:transparent !important;
}
#tree1 .mini-panel-viewport{
	background-color:transparent !important;
}
#menutabs .mini-tabs-bodys{
	padding:0px;
}
</style>

<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div id="region1" region="west" title="菜单管理" showHeader="true" class="sub-sidebar"
	width="240" allowResize="false">
		<ul id="tree1" class="nui-tree" url="com.bos.utp.framework.MenuManager.queryMenuTreeNode.biz.ext" style="width:95%;height:95%;padding:5px;" 
	        showTreeIcon="true" textField="text" idField="id" resultAsTree="false" parentField="pid" showTreeLines="true" onnodeclick="onNodeClick" contextMenu="#menuTreeMenu"
	          allowDrag="true" allowDrop="true" onbeforeload="onBeforeTreeLoad" ongivefeedback="onGiveFeedback" ondrop="onDrop">
    	</ul>
    	<ul id="menuTreeMenu" class="nui-contextmenu"  onbeforeopen="onBeforeOpen">
		</ul>
    </div>
    <div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
    	<!--Tabs-->
       <div id="menutabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;">
		    <div name="menu_list_tab" title="菜单查询" url="<%=request.getContextPath() %>/utp/framework/menu/menu_list_query.jsp" visible="true" >
		    </div>
		</div>
	</div>
</div>



<script type="text/javascript">
    nui.parse();
    var tree = nui.get("tree1");
	 function onBeforeTreeLoad(e) {
		e.params.nodeId = e.node.id;
    }
	var menu_info = { title: '菜单信息', path: '<%=request.getContextPath() %>/utp/framework/menu/menu_info.jsp' };
	var menu_list = { title: '菜单列表', path: '<%=request.getContextPath() %>/utp/framework/menu/menu_list.jsp' };
	var menutabs_map = {};
	menutabs_map["root"] = [menu_list];
	menutabs_map["menugroup"] = [menu_info,menu_list];
	menutabs_map["menu"] = [menu_info];
	
	function setUrlParam(url,params){
		if(!url){
			return url;
		}
		var paramsStr = [];
		for(var prop in params){
			paramsStr.push(prop + "=" + params[prop]);
		}
		if(url.indexOf("?")>=0){
			return url + "&" + paramsStr.join("&");
		}else{
			return url + "?" + paramsStr.join("&");
		}
	
	}
	
	function refreshTab(node){
		var tabs = nui.get("menutabs");
		var menutabs = menutabs_map[node.type];
		
		for(var i=0;i<menutabs.length;i++){
			var obj = menutabs[i];
			obj.url = setUrlParam(obj.path,node);
		}
		tabs.setTabs(menutabs);
	}
	
	function onDrop(e){
		var tree = e.sender;
		var node = e.dragNode;              //被拖拽的节点
		var targetNode = e.dropNode;  //目标投放节点
		var dragAction = e.dragAction 
		
		var json = nui.encode({nodeId:node.id,nodeType:node.type,targetNodeId:targetNode.id,targetNodeType:targetNode.type});
		$.ajax({
            url: "com.bos.utp.framework.MenuManager.updateMenuRelation.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	tree.loadNode(targetNode);
            },
            error: function () {
            }
        });
		
		
	}
	
	function onGiveFeedback(e) {
		var tree = e.sender;
		var node = e.targetNode;              //被拖拽的节点
		var targetNode = e.targetNode;  //目标投放节点
		var effect = e.effect;          //投放方式：add|before|after
		
		if(effect == "before" || effect == "after"){
			e.effect = "no";
		}
		//根节点不允许拖放
		if(node.type == "root"){
			e.effect = "no";
		}
    }	
	
	//树左键点击触发事件
	function onNodeClick(e){
		var node = e.node;
		refreshTab(node);
	}
	
	
	
	
	//菜单管理树右键
	
	function addMenu(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/menu/menu_add.jsp",
            title: "新增菜单", 
            width: 850,
            height: 467,
            allowResize:false,
            onload: function () {
            	var node = tree.getSelectedNode();
            	var data = {parentmenuid:node.id};
            	var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
	        		var node = tree.getSelectedNode();
	            	tree.selectNode(node);
	            	refreshTab(node);
	            	refresh();
	            }
            }
        });
	}
	
	function editMenu(){
		nui.open({
	        url: "<%=request.getContextPath() %>/utp/framework/menu/menu_edit.jsp",
	        title: "编辑菜单", 
	        width: 850,
            height: 167,
	        allowResize:false,
	        onload: function () {
	            var node = tree.getSelectedNode();
	       		var data = {menuid:node.id,type:"menu"};
	        	var iframe = this.getIFrameEl();
	            iframe.contentWindow.SetData(data);
	        },
	        ondestroy: function (action) {
	            if (action == "ok") {
	            	var node = tree.getSelectedNode();
	            	var parentNode = tree.getParentNode(node);
	            	tree.selectNode(node);
	            	refreshTab(node);
	            	refreshParentNode();
	            	
	            }
	        }
	    });
	}
	
	function removeMenu(){
		var node = tree.getSelectedNode();
		var json = nui.encode({menuid:node.id});
		nui.confirm("该节点下的所有子节点都将被删除，确定？","删除确认",function(action){
		    if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.utp.framework.MenuManager.deleteMenu.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	var parentNode = tree.getParentNode(node);
	            	tree.loadNode(parentNode);
	            	tree.selectNode(parentNode);
	            	refreshTab(parentNode);
	            },
	            error: function () {
	            }
	        });
        });
	}
	
	function refresh(){
		var node = tree.getSelectedNode();
		if(!node){
			node = tree.getNode("root");
		}
		tree.loadNode(node);
	
	}
	
	function refreshParentNode(){
		var node = tree.getSelectedNode();
		var parentNode = tree.getParentNode(node);
	    tree.loadNode(parentNode);
	    tree.selectNode(parentNode);
	    refreshTab(parentNode);
		
	}
	
	function onBeforeOpen(e) {
	    var obj = e.sender;
	    var node = tree.getSelectedNode();
	    if (!node) {
	        e.cancel = true;
	        return;
	    }
	    
	    if(node.type=="root"){
	    	var array = [{id: "addmenu", text: "新建菜单", iconCls:"icon-add", onclick:"addMenu"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "refresh", text: "刷新", iconCls:"icon-reload", onclick:"refresh"}];
			e.htmlEvent.preventDefault();
			obj.loadList(array);
	    }
	    
	    if(node.type=="menugroup"){
	    	var array = [{id: "addmenugroup", text: "新建菜单", iconCls:"icon-add", onclick:"addMenu"},
						{id: "editmenu", text: "修改菜单", iconCls:"icon-edit", onclick:"editMenu"},
						{id: "removemenu", text: "删除菜单", iconCls:"icon-remove", onclick:"removeMenu"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "refresh", text: "刷新", iconCls:"icon-reload", onclick:"refresh"}];
			e.htmlEvent.preventDefault();
			obj.loadList(array);			
	    }
	    
	    if(node.type=="menu"){
	   		var array = [{id: "editmenu", text: "修改菜单", iconCls:"icon-edit", onclick:"editMenu"},
						{id: "removemenu", text: "删除菜单", iconCls:"icon-remove", onclick:"removeMenu"}];
	    	e.htmlEvent.preventDefault();
			obj.loadList(array);
	    }
	}
</script>
</body>
</html>