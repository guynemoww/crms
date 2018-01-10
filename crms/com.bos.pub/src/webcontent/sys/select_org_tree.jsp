<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>组织机构树</title>
<%@include file="/common/nui/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css" />
</head>
<body>
	<%
		//searchMode-> 查询模式 
		//random:无条件查询所有机构
		//legorg:法人代码下所有机构 
		//clear->是否 可以清除[1=可以,0=不可以],默认允许清除 
		boolean clear = "1".equals(JspUtil.getParameter(request, "clear",
				"1"));
	%>
	<div class="nui-fit" style="padding: 6px 12px">
		<div class="nui-fit" style="border: 1px solid #8ba0bc;">
			<ul id="tree" class="nui-tree" style="width: 100%; height: 100%" showTreeIcon="true" textField="nodeName" idField="nodeId" resultAsTree="true" dataField="treeNodes" onbeforeload="onBeforeTreeLoad">
			</ul>
		</div>
		<div class="nui-toolbar" style="text-align: center; border: none">
			<a class="nui-button" iconCls="icon-ok" onclick="onOk">确定</a>
			<span style="display: inline-block; width: 25px;"></span>
			<a class="nui-button" iconCls="icon-cancel" onclick="onCancel">取消</a>
			<%
				if (clear) {
			%>
			<span style="display: inline-block; width: 25px;"></span>
			<a class="nui-button" iconCls="icon-cancel" onclick="onClear">清空</a>
			<%
				}
			%>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var selectNode;
		var tree = nui.get("tree");
		var rootNode = {
			nodeName : "",
			nodeType : "Root",
			searchMode : null,
			isLeaf : false,
			expanded : false,
			iconCls : "icon-arrow-merge"
		};
		$(function() {
			nui.parse();
			var tree = nui.get("tree");
			tree.addNode(rootNode);
			tree.selectNode(rootNode);
			refreshNode(rootNode);
		});

		function onBeforeTreeLoad(e) {
			var searchMode =
	<%=JspUtil.getParameterHaveSign(request, "searchMode")%>
		;
			e.url = "com.bos.utp.org.organization.queryOrgNodeOfSelect.biz.ext";
			if (searchMode) {
				e.params.searchMode = searchMode;
			}
			// 增加nodeType参数以便区分是加载机构下的结点还是岗位下的结点
			e.params.nodeType = e.node.nodeType;
		}

		function GetData() {
			return nui.clone(selectNode);
		}

		function getData() {
			return nui.clone(selectNode);
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
			selectNode = tree.getSelectedNode();
			debugger;
			if (!selectNode || selectNode.nodeType == "Root") {
				alert("请选择一条数据");
				return;
			}
			CloseWindow("ok");
		}

		function onCancel() {
			CloseWindow("cancel");
		}

		function onClear() {
			CloseWindow("clear");
		}
	</script>

</body>
</html>