<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): YANGZHOU
  - Date: 2013-03-01 17:43:27
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<%@page import="com.bos.utp.tools.superadmin.SuperAdminService"%>
<%
String orgMng = ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_orgMng");
%>
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>机构编码树</title>
</head>
<body>
<style>
	#tree .mini-grid-viewport{
		background-color:transparent !important;
	}
	#tree  .mini-panel-viewport{
		background-color:transparent !important;
	}
	#orgTabs .mini-tabs-bodys{
		padding:0px;
	}
</style>
<div id="layout1" class="nui-layout" style="width:100%;height:70%;">
	<div title="机构管理"   allowResize="false">
		<ul id="tree" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;" 
			showTreeIcon="true" textField="nodeName" 
			idField="nodeId" resultAsTree="true" dataField="treeNodes"
			onbeforeload="onBeforeTreeLoad" allowDrag="true" allowDrop="true"  allowLeafDropIn="true"
			ongivefeedback="onGiveFeedback" ondrop="onDrop" onbeforedrop="onBeforeDrop" >
	    </ul>		
	</div>
	
</div>
 <div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="nui-button" style="width:60px;" iconCls="icon-cancel" onclick="onCancel()">取消</a>
</div>


 
 
<script type="text/javascript">
	nui.parse();
	var currentNode = null;
	var tree = nui.get("tree");
	$(function() {
		nui.parse();
	    var contextPath = "<%=contextPath %>";
	    var tree = nui.get("tree");
	    var rootNode = {nodeName: "", nodeType: "Root", isLeaf: false, expanded: false, iconCls: "icon-arrow-merge"};
	    tree.addNode(rootNode);
	    tree.selectNode(rootNode);
	    currentNode = rootNode;
	    refreshNode(rootNode);
	});
        
    function onBeforeTreeLoad(e) {
    	e.url = "com.bos.utp.org.organization.queryOrgNodeOfSelect.biz.ext";
    	// 增加nodeType参数以便区分是加载机构下的结点还是岗位下的结点
		e.params.nodeType = e.node.nodeType;
    }
    
      
	function GetData() {
        return tree.getSelectedNode();
    } 
	/**
	 * 获取当前结点对象，可提供给子页面调用，以避免在子页面之间传递结点参数
	 */
	this.getCurrentNode = function() {
		return currentNode;
	}
	
	/**
	 * 获取当前选中结点对象，可提供给子页面调用
	 */
	this.getSelectedNode = function() {
		var tree = nui.get("tree");
		return tree.getSelectedNode();
	}
	
	/**
	 * 获取父结点对象，可提供给子页面调用
	 * @param node NUI的树结点对象，默认为当前树结点currentNode
	 */
	this.getParentNode = function(node) {
		node = node || getCurrentNode();
		var tree = nui.get("tree");
		return tree.getParentNode(node);
	}
	
	/**
	 * 刷新指定结点，可提供给子页面调用
	 * @param node NUI的树结点对象
	 */
	this.refreshNode = function(node) {
		var tree = nui.get("tree");
		tree.loadNode(node);
	}
	
	/**
	 * 刷新当前结点下的子节点，可提供给子页面调用
	 */
	this.refreshCurrentNode = function() {
		refreshNode(getCurrentNode());
	}
	
	/**
	 * 刷新指定结点的父结点，可提供给子页面调用
	 * @param node NUI的树结点对象，默认为当前树结点currentNode
	 */
	this.refreshParentNode = function(node) {
		node = node || getCurrentNode();
		refreshNode(getParentNode(node));
	}
	
	/**
	 * 递归搜索父机构结点
	 */
	function searchParentOrgNode(node) {
		if(!node || !node.nodeType) return null;
		
		if(node.nodeType == "OrgOrganization") return node;
		
		return searchParentOrgNode(getParentNode(node));
	}
	
	/**
	 * 根据结点类型显示
	 */
	function onBeforeOpen(e) {
		var tree = nui.get("tree");
		var node = tree.getSelectedNode();
		var menu = e.sender;

		var menuList = menu_map[node.nodeType];
		if(!menuList || menuList.length == 0) {
			e.cancel = true;
			return;
		}
		
		menu.loadList(nui.clone(menuList)); //这里菜单项必须要克隆，否则第二次点击时无法触发事件
	}
	
	/**
	 * 树结点拖拽前处理，判断被拖拽结点是否能拖动到目标结点下
	 */
	function onGiveFeedback(e) {
		var tree = e.sender;
		var node = e.node;              //被拖拽的节点
		node.parentNode = tree.getParentNode(node); //被拖拽节点的父节点（扩展的参数，在onDrop()事件中会被使用）
		var targetNode = e.targetNode;  //目标投放节点
		
		// 只处理add，不处理before和after，即不处理调整结点顺序的情况
//		if(effect == "before" || effect == "after") {
//			e.effect = "no";
//			return;
//		}
		
		if(node.parentNode == targetNode) {
			e.effect = "no";
			return;
		}
		
		switch(node.nodeType) {
		case "OrgOrganization":
			if(targetNode.nodeType != "OrgOrganization") {
				e.effect = "no";
			}
			break;
		case "OrgPosition":
			if(targetNode.nodeType != "OrgOrganization" && targetNode.nodeType != "OrgPosition") {
				e.effect = "no";
			}
			break;
		case "OrgEmployee":
			if(targetNode.nodeType != "OrgOrganization" && targetNode.nodeType != "OrgPosition") {
				e.effect = "no";
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * 拖动到目标结点前的事件处理
	 */
	function onBeforeDrop(e) {
		var dragAction = e.dragAction; //投放方式：add|before|after
	
		var tree = e.sender;
		var dragNode = e.dragNode; //被拖拽的节点
		var dragParentNode = dragNode.parentNode; //被拖拽节点的父节点
		var dropNode = e.dropNode; //目标投放节点
		
		var data = {
			dragNodeId: dragNode.nodeId, 
			dragNodeType: dragNode.nodeType,
			dragParentNodeId: dragParentNode.nodeId,
			dragParentNodeType: dragParentNode.nodeType,
			dropNodeId: dropNode.nodeId,
			dropNodeType: dropNode.nodeType
		};
		
		// 打开拖拽结点处理选项，选择“移动”、“复制”或“取消”
    	var $dragOption = $("#dragOptionDiv").clone();
        $dragOption.show();
        mini.showMessageBox({
            width: 250,
            title: "请选择您需要的操作",
            buttons: ["ok", "cancel"],
            html: $dragOption[0].innerHTML,
            callback: function (action) {
            	if (action == "ok") {
                    var dragOptionVal = $("input[name='dragAction']:checked").val();
                    var url = null;
					if(dragOptionVal == "move") {
						var url = "com.bos.utp.org.organization.moveNode.biz.ext";
					} else if(dragOptionVal == "copy") {
						var url = "com.bos.utp.org.organization.copyNode.biz.ext";
					}
                    
                    if(url) {
						var json = nui.encode(data);
						$.ajax({
				            url: url,
				            type: 'POST',
				            data: json,
				            contentType:'text/json',
				            success: function (text) {
				            	refreshNode(dragParentNode);
				            	refreshNode(dropNode);
				            }
						});
                    }
                } 
            }
        });
        
        // 阻止拖动后将结点直接移动，要通过刷新来体现移动结果
        e.cancel = true;
	}
	
	/**
	 * 弹出提示框，在指定时间后自动消失
	 * @params message 提示消息
	 * @params title 提示标题，默认为"提示"
	 * @params timeout 提示框多长时间后消失，单位毫秒，默认为500毫秒
	 */
	function alertTip(message, title, timeout) {
		title = title || "提示";
		timeout = timeout || 500;
		var messageid = nui.loading(message, title);
		setTimeout(function () {
			nui.hideMessageBox(messageid);
		}, timeout);
	}
	
	/**
	 * 打开对话框，自动处理onload和ondestroy事件
	 * @params params 额外提供params.data属性
	 */
	function openDialog(params) {
		var openParams = nui.clone(params);
		
		openParams.onload || (openParams.onload = function () {
			var iframe = this.getIFrameEl();
			var contentWindow = iframe.contentWindow;
			
			if(contentWindow.SetData) {
				contentWindow.SetData(openParams.data);
			}
		});
		
		// 子窗口点确定时刷新当前结点
		openParams.ondestroy || (openParams.ondestroy = function (action) {
			if (action == "ok") {
				refreshNode(getSelectedNode());
			}
		});
		
		nui.open(openParams);
	}
	 
	
	function onOk() {
        CloseWindow("ok");
    }
    
    function onCancel() {
        CloseWindow("cancel");
    }
    
	function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }
</script>

</body>
</html>