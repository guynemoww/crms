
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): YANGZHOU
  - Date: 2013-03-01 17:43:27
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<%
	String orgMng = ResourcesMessageUtil.getI18nResourceMessage("orgSubMaintain_l_title_orgMng");
%>
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/utp/org/icons/icon.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>组织机构树</title>
</head>
<body>

<div class="nui-fit" style="padding:5px 0px 0px 5px;">
	<ul id="tree1" class="nui-tree" style="width:100%;" url="com.bos.utp.org.organization.queryOrgNodes.biz.ext"
	  showTreeIcon="true" textField="orgname" idField="nodeId" dataField="treeNodes" onnodedblclick="onOk">
	</ul>
</div>

<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="nui-button" style="width:60px;" iconCls="icon-cancel" onclick="onCancel()">取消</a>
</div>


<script type="text/javascript">
    nui.parse();
    var contextPath = "<%=request.getContextPath() %>";
    var tree = nui.get("tree1");
        
	function GetData() {
        return tree.getSelectedNode();
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
