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
<title>组织机构树</title>

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
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="机构管理" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;" 
			showTreeIcon="true" textField="nodeName" 
			idField="nodeId" resultAsTree="true" dataField="treeNodes"
			onbeforeload="onBeforeTreeLoad" onnodeclick="onNodeClick"
			contextMenu="#treeMenu">
	    </ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>


<div id="dragOptionDiv" style="width:100%;display:none;">
	<input name="dragAction" type="radio" value="move" checked="checked"/>移动&nbsp;
	<input name="dragAction" type="radio" value="copy"/>复制&nbsp;
	<input name="dragAction" type="radio" value="cancel"/>取消
</div>

<div id="empRemovePrompt" style="width:100%;display:none;">
   <table>
     <tr>
       <td>
          <div class="mini-messagebox-question"></div>
       </td>
       <td>&nbsp;&nbsp;&nbsp;确定删除所选员工? </td>
     </tr>
   </table>
</div>

<div id="orgRemovePrompt" style="width:100%;display:none;">
   <table>
     <tr>
       <td>
          <div class="mini-messagebox-question"></div>
       </td>
       <td>&nbsp;&nbsp;&nbsp;删除此机构将删除机构下所有人员,是否确认？</td>
     </tr>
   </table>
</div>

<script type="text/javascript">
	var currentNode = null;

	$(function() {
		nui.parse();
	    var contextPath = "<%=contextPath %>";
	    var tree = nui.get("tree");
	    var rootNode = {nodeName: "", nodeType: "Root", isLeaf: false, expanded: false, iconCls: "icon-arrow-merge"};
	    tree.addNode(rootNode);
	    tree.selectNode(rootNode);
	    currentNode = rootNode;
	    refreshNode(rootNode);
	    showNodeTabs(rootNode);
	});
        
    function onBeforeTreeLoad(e) {
    	e.url = "com.bos.utp.org.organization.queryTreeChildNodes.biz.ext";
    	// 增加nodeType参数以便区分是加载机构下的结点还是岗位下的结点
		e.params.nodeType = e.node.nodeType;
    }
    

	// 定义Tab页面 //
	// 根结点对应的Tab页
	var employee_index = { title: '人员查询', url: '<%=contextPath%>/bps/mywork/employee_index.jsp' };
	
	// 机构结点对应的Tab页
	var org_employee_list  = { title: '人员信息', url: '<%=contextPath%>/bps/mywork/userogr_list.jsp' };
	
	


	var orgTabs_map = {
		<%=SuperAdminService.currUserIsSupserAdmin()?"Root: [employee_index],":"Root: [employee_index],"%>
		OrgOrganization: [org_employee_list]
	};


	/**
	 * 显示指定结点对应的Tab页
	 */
	function showNodeTabs(node) {
		if(!node)
			node=getCurrentNode();
		var orgTabs = orgTabs_map[node.nodeType];
		if(!orgTabs) return;
		var tabs = nui.get("orgTabs");
		tabs.setTabs(orgTabs);
		$("#orgTabs").show();
		//触发第一个tab的SetData对象
		var firstTab = tabs.getTab(0);
		var tab2 = tabs.getTab(1);
		if(firstTab){
			firstTab.onload = function(e){
				var tab = e.sender;
				var iframe = tab.getTabIFrameEl(firstTab);
				if(iframe && iframe.contentWindow && iframe.contentWindow.SetData){
					iframe.contentWindow.SetData(getCurrentNode());
				}
			}
		}
		if(tab2){
			tab2.onload = function(e){
				var tab = e.sender;
				var iframe = tab.getTabIFrameEl(firstTab);
				if(iframe && iframe.contentWindow && iframe.contentWindow.SetData){
					iframe.contentWindow.SetData(getCurrentNode());
				}
			}
		}
		
	}
	
	/**
	 * 树结点被鼠标左键单击后，在右侧打开对应的页面
	 */
	function onNodeClick(e){
		currentNode = e.node;
		showNodeTabs(e.node);
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

	function onRefreshNode(e) {
		refreshNode(getSelectedNode());
	}
	
	var empPrompt = document.getElementById("empRemovePrompt");
	function onRemoveEmp(e){
	   var tree = nui.get("tree");
	   var node = tree.getSelectedNode();
	   var parent = tree.getParentNode(node);
	   if(parent.nodeType=="OrgPosition") {
			removeEmpFromPosition();
	   } else {
	   		removeEmpFromOrg();
	   }
	}

	function removeEmpFromOrg() {
		empPrompt.style.display="";
	    top.nui.showMessageBox({
	      width:300,
	      title:'系统提示',
	      buttons:["ok","cancel"],
	      html:empPrompt,
	      showModal: true,
	      callback: function (action) {
             if(action=="ok"){
                window['result']={
                  action:true
                };
             }else{
                window['result']={
                  action:false
                };
             }
             executeEmpRemove();
          }
	   });
	}
	
	var orgPrompt = document.getElementById("orgRemovePrompt");
	function onRemoveOrg(e){
	   orgPrompt.style.display="";
	   top.nui.showMessageBox({
	      width:300,
	      title:'系统提示',
	      buttons:["ok","cancel"],
	      html:orgPrompt,
	      showModal: true,
	      callback: function (action) {
             if(action=="ok"){
                window['result']={
                  action:true
                };
                hideTabsIfCurrentNodeRemoved();
             }else{
                window['result']={
                  action:false
                };
             }
             executeOrgRemove();
          }
	   });
	}

</script>

</body>
</html>