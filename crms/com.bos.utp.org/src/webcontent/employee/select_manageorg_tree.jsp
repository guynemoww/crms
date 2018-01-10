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
<div class="nui-fit" style="width:100%;height:100%;">
   <table style="width:100%;height:89%;">
      <tr>
         <td>所有机构</td>
         <td></td>
         <td>可管理机构</td>
      </tr>
      <tr>
         <td style="width:40%;height:100%;border:1px solid #878787;vertical-align:top;">
            <ul id="tree1" class="nui-tree" style="width:100%;" url="om.bos.utp.org.organization.getOrgStaticTree.biz.ext"
	          dataField="treeNodes" showTreeIcon="true" textField="nodeName" idField="nodeId" resultAsTree="false" parentField="parentId"
	          onnodedblclick="onNodeDbClick">
	        </ul>
         </td>
         <td style="width:20%;">
            <table style="width:100%;">
		    	<tr>
		    	  <td align="center">
		    	    <input type="button" id="addBtn" style="width:65px;text-align:center;" class="nui-button" onclick="addOrgResource" text="添加" />
		    	  </td>
		    	</tr><%-- 添加 --%>
				<tr>
				  <td align="center">&nbsp;</td>
				</tr>
				<tr>
				  <td align="center">
				    <input type="button" id="allDeleteBtn" class="nui-button" style="width:65px;text-align:center;" text="全部删除" onclick="deleteAllOrgResource"/>
				  </td>
				</tr><%-- 全部删除 --%>
				<tr>
				  <td align="center">
				    <input type="button" id="deleteBtn" class="nui-button" style="width:65px;text-align:center;" text="删除" onclick="deleteOrgResource" />
				  </td>
				</tr><%-- 删除 --%>
			</table>
         </td>
         <td style="width:40%;">
            <div class="nui-fit">
              <div id="listbox1" class="nui-listbox" style="width:100%;height:100%;"
               textField="nodeName" valueField="nodeId" multiSelect="true">
              </div>
            </div>
         </td>
      </tr>
   </table>
   <div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border:0;">
        <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="nui-button" style="width:60px;" iconCls="icon-cancel" onclick="onCancel()">取消</a>
</div>
</div>

<script type="text/javascript">
    nui.parse();
    
    var contextPath = "<%=request.getContextPath() %>";
    var tree = nui.get("tree1");
    var listbox = nui.get("listbox1");
    
    function addOrgResource(){
        var node = tree.getSelectedNode();
        if(!node){
            nui.alert("请选择一个机构");
        }
        var parentNodes = tree.getAncestors(node);
        var items = listbox.getData();
        for(var i=0,len=items.length;i<len;i++){
            if(node.nodeId==items[i].nodeId){
                nui.alert("已拥有对该机构的管理权限");
                return;
            }
            for(var j=0,len1=parentNodes.length;j<len1;j++){
	            if(parentNodes[j].nodeId==items[i].nodeId){
	                nui.alert("已拥有对该机构的父机构的管理权限");
	                return;
	            }
            }
        }
        
        tree.cascadeChild(node, function (childNode) {
            for(var i=0,len=items.length;i<len;i++){
                if(childNode.nodeId==items[i].nodeId){
                    listbox.removeItems([items[i]]);
                }
            }
        });

        listbox.addItem(node);
    }
    
    function onNodeDbClick(){
        addOrgResource();
    }
    
    function SetData(data){
       data = nui.clone(data);
       var ids = data.ids.split(",");
       var texts = data.texts.split(",");
       if(ids!=""){
	       for(var i=0;i<ids.length;i++){
	          var id = ids[i].split(":");
	          var node ={
	             nodeId:id[0],
	             nodeName:texts[i]
	          };
	          listbox.addItem(node);
	       }
       }
    }
    
	function GetData() {
		var items = listbox.getData();
		var ids = [];
		var texts = [];
		for(var i=0;i<items.length;i++){
			ids.push(items[i].nodeId+":"+items[i].nodeName);
			texts.push(items[i].nodeName);
		}
        return {id:ids.join(","),text:texts.join(",")};
    }
    
    function deleteOrgResource(){
        var nodes = listbox.getSelecteds();
        if(nodes.length==0){
            nui.alert("请选择至少一个可管理机构");
            return;
        }
        listbox.removeItems(nodes);
    }
    
    function deleteAllOrgResource(){
        listbox.removeAll();
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
