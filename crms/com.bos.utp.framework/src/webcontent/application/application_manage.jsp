<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-02-28 10:38:33
  - Description:应用功能管理入口
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link id="css_icon" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/utp/tools/icons/icon.css"/>
<%@include file="/common/nui/common.jsp" %>
<%
	//获取标签中使用的国际化资源信息
	//String application_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationList");
	//String application_info = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_applicationInfo");
	//String functiongroup_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_funcGroupList");
	//String functiongroup_info = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_funcGroupInfo");
	//String function_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_functionList");
	//String function_info = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_functionInfo");
	//String subfunctiongroup_list = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("applicationManager_l_subFuncGroup_list");
   
    String application_list="应用列表";
	String application_info="应用信息";
	String functiongroup_list="功能组列表";
	String functiongroup_info="功能组信息";
	String function_list="功能列表";
	String function_info="功能信息";
	String subfunctiongroup_list="子功能组列表";
 
 %>
</head>
<body>

<style>
#tree1 .mini-grid-viewport{
	background-color:transparent !important;
}
#tree1 .mini-panel-viewport{
	background-color:transparent !important;
}
#applicationtabs .mini-tabs-bodys{
	padding:0px;
}
</style>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div id="region1" region="west" title="应用功能管理" showHeader="true" class="sub-sidebar" 
	width="240" allowResize="false">
			<ul id="tree1" class="nui-tree" url="com.bos.utp.framework.ApplicationManager.queryApplicationTreeNode.biz.ext" 
			style="width:98%;height:98%;padding:5px;" showTreeIcon="true" textField="text" idField="id" resultAsTree="false" 
			parentField="pid" showTreeLines="true" onnodeclick="onNodeClick" allowDrag="true" allowDrop="true" 
			contextMenu="#applicaitonTreeMenu" onbeforeload="onBeforeTreeLoad" ongivefeedback="onGiveFeedback" ondrop="onDrop">
	    	</ul>
	    	<ul id="applicaitonTreeMenu" class="nui-contextmenu"  onbeforeopen="onBeforeOpen">
			</ul>
    </div>
    <div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
    	<!--Tabs-->
       <div id="applicationtabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;">
		    <div name="application_list_tab"
		    title="<%=application_list %>" url="<%=request.getContextPath() %>/utp/framework/application/application_list.jsp" visible="true" >
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">
    nui.parse();
    var tree = nui.get("tree1");
    function onBeforeTreeLoad(e) {
		e.params.nodeType = e.node.type;
		e.params.nodeId = e.node.realId;
    }
	var application_list  = { title: '<%=application_list %>', path: '<%=request.getContextPath() %>/utp/framework/application/application_list.jsp' };
	var application_info  = { title: '<%=application_info %>', path: '<%=request.getContextPath() %>/utp/framework/application/application_info.jsp' };
	var functiongroup_info  = { title: '<%=functiongroup_info %>', path: '<%=request.getContextPath() %>/utp/framework/functiongroup/funcgroup_info.jsp' };
	var subfunctiongroup_info  = { title: '子功能组信息', path: '<%=request.getContextPath() %>/utp/framework/subfunctiongroup/subfuncgroup_info.jsp' };
	var function_info  = { title: '<%=function_info %>', path: '<%=request.getContextPath() %>/utp/framework/function/function_info.jsp' };
	var functiongroup_list = { title: '<%=functiongroup_list%>', path: '<%=request.getContextPath() %>/utp/framework/functiongroup/funcgroup_list.jsp' };
	var subfunctiongroup_list = { title: '<%=subfunctiongroup_list%>', path: '<%=request.getContextPath() %>/utp/framework/subfunctiongroup/subfuncgroup_list.jsp' };
	var function_list  = { title: '<%=function_list %>', path: '<%=request.getContextPath() %>/utp/framework/function/function_list.jsp' };
	var applicationtabs_map = {};
	applicationtabs_map["root"] = [application_list];
	applicationtabs_map["application"] = [application_info,functiongroup_list];
	applicationtabs_map["functiongroup"] = [functiongroup_info,subfunctiongroup_list,function_list];
	applicationtabs_map["subfunctiongroup"] = [subfunctiongroup_info,function_list];
	applicationtabs_map["function"] = [function_info];
	
	function setUrlParam(url,params){
		if(!url){
			return url;
		}
		var paramsStr = [];
		for(var prop in params){
			paramsStr.push(prop + "=" + git.toUrlParam(params[prop]));
		}
		if(url.indexOf("?")>=0){
			return url + "&" + paramsStr.join("&");
		}else{
			return url + "?" + paramsStr.join("&");
		}
	
	}
	
	function refreshTab(node){
		var tabs = nui.get("applicationtabs");
		var applicationtabs = applicationtabs_map[node.type];
		
		for(var i=0;i<applicationtabs.length;i++){
			var obj = applicationtabs[i];
			obj.url = setUrlParam(obj.path,node);
		}
		tabs.setTabs(applicationtabs);
	}
	
	function onDrop(e){
		var tree = e.sender;
		var node = e.dragNode;              //被拖拽的节点
		var targetNode = e.dropNode;  //目标投放节点
		var dragAction = e.dragAction 
		
		var json = nui.encode({nodeId:node.realId,nodeType:node.type,targetNodeId:targetNode.realId,targetNodeType:targetNode.type});
		$.ajax({
            url: "com.bos.utp.framework.FuncGroupManager.updateFuncGroupRelation.biz.ext",
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
		//根节点和应用节点不允许拖放
		if(node.type == "root" || node.type == "application"){
			e.effect = "no";
		}
    }	
	//树左键点击触发事件
	function onNodeClick(e){
		var node = e.node;
		refreshTab(node);
	}
	
	function addApplication(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/application/application_add.jsp",
            title: "新增应用", 
            width: 850, 
            height: 445,
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
	
	function addFuncGroup(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/functiongroup/funcgroup_add.jsp",
            title: "新增功能组", 
            width: 850, 
            height: 100,
            allowResize:false,
            onload: function () {
            	var node = tree.getSelectedNode();
            	var id = node.realId;
            	var data = {appid:node.realId};
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
	
	function addSubFuncGroup(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/subfunctiongroup/subfuncgroup_add.jsp",
            title: "新增子功能组",
            width: 850,
            height: 100,
            allowResize:false,
            onload: function () {
            	var node = tree.getSelectedNode();
            	var id = node.realId;
            	var data = {parentgroupid:node.realId};
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
	
	function addFunction(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/function/function_add.jsp",
            title: "新增功能", 
            width: 850, 
            height: 355,
            onload: function () {
            	var node = tree.getSelectedNode();
            	var id = node.realId;
            	var data = {parentgroupid:node.realId};
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
	
	function editApplication(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/application/application_edit.jsp",
            title: "编辑应用", 
            width: 850, 
            height: 445,
            onload: function () {
            	var node = tree.getSelectedNode();
           		var data = {appid:node.realId,type:"application"};
            	var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                	var node = tree.getSelectedNode();
                	tree.selectNode(node);
                	refreshTab(node);
                	refreshParentNode();
                }
            }
        });
	}
	
	function editFuncGroup(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/functiongroup/funcgroup_edit.jsp",
            title: "编辑功能组", 
            width: 850, 
            height: 120,
            allowResize:false,
            onload: function () {
                var node = tree.getSelectedNode();
           		var data = {funcgroupid:node.realId,type:"functiongroup"};
            	var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                	var node = tree.getSelectedNode();
                	tree.selectNode(node);
                	refreshTab(node);
                	refreshParentNode();
                }
            }
        });
	}
	
	function editSubFuncGroup(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/subfunctiongroup/subfuncgroup_edit.jsp",
            title: "编辑子功能组",
            width: 850,
            height: 120,
            allowResize:false,
            onload: function () {
                var node = tree.getSelectedNode();
           		var data = {funcgroupid:node.realId,type:"subfunctiongroup"};
            	var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                	var node = tree.getSelectedNode();
                	tree.selectNode(node);
                	refreshTab(node);
                	refreshParentNode();
                }
            }
        });
	}
	
	function editFunction(){
		nui.open({
            url: "<%=request.getContextPath() %>/utp/framework/function/function_edit.jsp",
            title: "编辑功能", 
            width: 850, 
            height: 355,
            onload: function () {
                var node = tree.getSelectedNode();
           		var data = {funccode:node.realId,type:"function"};
            	var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                	var node = tree.getSelectedNode();
                	tree.selectNode(node);
                	refreshTab(node);
                	refreshParentNode();
                }
            }
        });
	}
	
	function removeApplication(){
		var node = tree.getSelectedNode();
		var json = nui.encode({nodeId:node.realId,nodeType:node.type});
		nui.confirm("该节点下的所有子节点都将被删除，确定？","删除确认",function(action){
		    if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.utp.framework.ApplicationManager.deleteApplication.biz.ext",
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
			node = tree.getNode("root_root");
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
	//应用功能树右键菜单
	function onBeforeOpen(e) {
	    var obj = e.sender;
	    var node = tree.getSelectedNode();
	    if (!node) {
	        e.cancel = true;
	        return;
	    }
	    
	    if(node.type=="root"){
	    	var array = [{id: "addapplication", text: "新建应用", iconCls:"icon-add", onclick:"addApplication"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "refresh", text: "刷新", iconCls:"icon-reload", onclick:"refresh"}];
			e.htmlEvent.preventDefault();
			obj.loadList(array);
	    }
	    
	    //屏蔽应用功能树右键修改应用 {id: "editapplication", text: "修改应用", iconCls:"icon-edit", onclick:"editApplication"},
	    if(node.type=="application"){
	    	var array = [{id: "addfuncgroup", text: "新建功能组", iconCls:"icon-add", onclick:"addFuncGroup"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "removeapplication", text: "删除应用", iconCls:"icon-remove", onclick:"removeApplication"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "refresh", text: "刷新", iconCls:"icon-reload", onclick:"refresh"}];
			e.htmlEvent.preventDefault();
			obj.loadList(array);			
	    }
	    
	    if(node.type=="functiongroup"){
	    	var array = [{id: "addsubfuncgroup", text: "新建子功能组", iconCls:"icon-add", onclick:"addSubFuncGroup"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "addfunction", text: "新建功能", iconCls:"icon-add", onclick:"addFunction"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "editfuncgroup", text: "修改功能组", iconCls:"icon-edit", onclick:"editFuncGroup"},
						{id: "removefuncgroup", text: "删除功能组", iconCls:"icon-remove", onclick:"removeApplication"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "refresh", text: "刷新", iconCls:"icon-reload", onclick:"refresh"}];
			e.htmlEvent.preventDefault();
			obj.loadList(array);			
	    }
	    
	    if(node.type=="subfunctiongroup"){
	    	var array = [{id: "addfunction", text: "新建功能", iconCls:"icon-add", onclick:"addFunction"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "editsubfuncgroup", text: "修改子功能组", iconCls:"icon-edit", onclick:"editSubFuncGroup"},
						{id: "removesubfuncgroup", text: "删除子功能组", iconCls:"icon-remove", onclick:"removeApplication"},
						{id: "separator", text: "", cls:"mini-separator"},
						{id: "refresh", text: "刷新", iconCls:"icon-reload", onclick:"refresh"}];
			e.htmlEvent.preventDefault();
			obj.loadList(array);
	    
	    }
	    
	    if(node.type=="function"){
	   		var array = [{id: "editfunction", text: "修改功能", iconCls:"icon-edit", onclick:"editFunction"},
						{id: "removefunction", text: "删除功能", iconCls:"icon-remove", onclick:"removeApplication"}];
	    	e.htmlEvent.preventDefault();
			obj.loadList(array);
	    }
	}
</script>
</body>
</html>