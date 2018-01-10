<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>组织机构树</title>
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css"/>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:70%;">
	<div title="机构管理"   allowResize="false">
		<ul id="tree" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;" 
			showTreeIcon="true" textField="nodeName" 
			idField="nodeId" resultAsTree="true" dataField="treeNodes"
			onbeforeload="onBeforeTreeLoad">
	    </ul>		
	</div>
	
</div>
 <div class="nui-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" iconCls="icon-ok" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">取消</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="nui-button"  iconCls="icon-cancel" onclick="onClear()">清空</a>
</div>


 
 
<script type="text/javascript">
	nui.parse();
	var currentNode = null;
	var tree = nui.get("tree");
	//var rootNode = {nodeName: "", nodeType: "Root", isLeaf: false, expanded: false, iconCls: "icon-arrow-merge"};
	var rootNode = {nodeName: "<%=request.getParameter("orgname") %>", nodeId: "<%=request.getParameter("orgid") %>", 
		orgid:"<%=request.getParameter("orgid") %>", orgname:"<%=request.getParameter("orgname") %>",
		orglevel:"<%=request.getParameter("orglevel") %>",
		nodeType: "OrgOrganization", isLeaf: false, expanded: false, iconCls: "icon-organization"};
	if (rootNode.nodeName=='null')
		rootNode = {nodeName: "", nodeType: "Root", isLeaf: false, expanded: false, iconCls: "icon-arrow-merge"};
	$(function() {
		nui.parse();
	    var contextPath = "<%=contextPath %>";
	    var tree = nui.get("tree");
	    tree.addNode(rootNode);
	    tree.selectNode(rootNode);
	    currentNode = rootNode;
	    refreshNode(rootNode);
	});
        
    function onBeforeTreeLoad(e) {
    	e.url = "com.bos.utp.org.organization.queryCurrentOrgAndChildOrg.biz.ext";
    	// 增加nodeType参数以便区分是加载机构下的结点还是岗位下的结点
		e.params.nodeType = e.node.nodeType;
		if(e.node.nodeType=="OrgOrganization"||e.node.nodeType=='Root'){
		}else{
			e.params.orgId=nui.encode(<%=request.getParameter("orgid") %>);
			e.params.nodeId=nui.encode(<%=request.getParameter("orgid") %>);
		}
    }
    
      
	function GetData() {
        return tree.getSelectedNode();
    } 
	
	/**
	 * 刷新指定结点，可提供给子页面调用
	 * @param node NUI的树结点对象
	 */
	this.refreshNode = function(node) {
		var tree = nui.get("tree");
		tree.loadNode(node);
	}
	
	function onOk() {
        CloseWindow("ok");
    }
    
    function onCancel() {
        CloseWindow("cancel");
    }
    function onClear() {
	    tree.selectNode(rootNode);
        CloseWindow("ok");
    }
</script>

</body>
</html>