<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2013-11-28 17:20:29
  - Description:
-->
<head>
<title>Title</title>
</head>
<body>
	<div class="nui-toolbar" style="text-align:center;line-height:30px;" 
        borderStyle="border-left:0;border-top:0;border-right:0;">
        <label >名称：</label>
        <input id="key" class="nui-textbox" style="width:150px;" onenter="onKeyEnter"/>
        <a class="nui-button" style="width:60px;" onclick="search()">查询</a>
    </div>
    
    <div class="nui-fit">
       <ul id="tree1" class="nui-tree" style="width:100%;height:100%;" dataField="omOrganizations"
            showTreeIcon="true" textField="orgname" idField="orgid" parentField="PARENTORGID" 
            resultAsTree="false" onnodedblclick="onNodeDblClick" expandOnDblClick="false"
            >
       </ul>
    </div>
    
    <script type="text/javascript">
        nui.parse();
    	var tree = nui.get("tree1");
    	function orgTreeLoad(){
    		$.ajax({
    		   url:'com.bos.pub.organization.childrenOrgTree.biz.ext',
    		   type:'POST',
    		   data: nui.encode({"orgId":"<%=userObject.getUserOrgId()%>"}),
    		   contentType:'text/json',
    		   success: function (text) {
    		   		tree.loadList(text.omOrganizations,"orgid","PARENTORGID");
    		   }
    		});
    	}
    orgTreeLoad();
    	
    function search() {
        var key = nui.get("key").getValue();
        if(key == ""){
            tree.clearFilter();
        }else{
            key = key.toLowerCase();
            tree.filter(function (node) {
                var text = node.orgname ? node.orgname.toLowerCase() : "";
                if (text.indexOf(key) != -1) {
                    return true;
                }
            });
        }
    }
    function onKeyEnter(e) {
        search();
    }
    
    function onNodeDblClick(e) {
       var node = tree.getSelectedNode();
       if (node && tree.isLeaf(node) == false) {
          alert("不能选中父节点");
          return;
       }
       CloseWindow("ok");
    }
    
    function GetData() {
        var node = tree.getSelectedNode();
        return node;
    }
    
 
   </script>
</body>
</html>