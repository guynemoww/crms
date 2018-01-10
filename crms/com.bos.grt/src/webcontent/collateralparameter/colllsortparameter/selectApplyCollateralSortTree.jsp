<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): menglei
  - Date: 2014-06-17
  - Description:
-->
<head>
<h:css href="/css/style1/style-custom.css"/>
<%@include file="/common/nui/common.jsp"%>
<title>Title</title>
</head>
<body>
<!-- 
   <div class="nui-toolbar" style="text-align:center;line-height:30px;" 
        borderStyle="border-left:0;border-top:0;border-right:0;">
        <label >名称：</label>
        <input id="key" class="nui-textbox" style="width:150px;" onenter="onKeyEnter"/>
        <a class="nui-button" style="width:60px;" onclick="search()">查询</a>
    </div>
   -->  
    <div title="押品分类树"  bodyStyle="overflow:hidden;">
		<ul id="tree1" class="nui-tree" style="width:350px;padding:5px;"  region="west"
			showTreeIcon="true" textField="sortName" idField="sortType" parentField="parentSortType" expandOnLoad="false"
			showCheckBox="true" 
			showFolderCheckBox="false"
			dataField="collSortParameters" 
		    resultAsTree="true" >
		</ul>
    </div>
    
     <div  class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border-left:0;border-top:0;border-right:0;">
        <a class="nui-button"  iconCls="icon-save" onclick="getCheckedNodes()">确定</a>
    </div>
   
	<script type="text/javascript">
     	nui.parse();
	 	var tree = nui.get("tree1");
     	function reload() {
			var collType = "<%=request.getParameter("collType")%>";
			var json = nui.encode({"collSortParameter":{"collType":collType}});
			$.ajax({
				url: "com.bos.grt.collateralparameter.collsortparameters.getsortparameterstree.biz.ext",
				type: 'POST',
				data: json,
				contentType:'text/json',
				success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		tree.loadList(text.collSortParameters,"sortType","parentSortType");	
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
			});
		}
		reload();
	
		function search() {
	        var key = nui.get("key").getValue();
	        if(key == ""){
	            tree.clearFilter();
	        }else{
	            key = key.toLowerCase();
	            tree.filter(function (node) {
	                var text = node.sortName ? node.sortName.toLowerCase() : "";
	                if (text.indexOf(key) != -1) {
	                    return true;
	                }
	            });
	        }
	    }
    
	    function onKeyEnter(e) {
	        search();
	    }
    
	    //供父窗口获取该页面选中的值
	    function GetData() {
	        var node = tree.getSelectedNode();
	        return node;
	    }
    
	    function getCheckedNodes() {
	    	var tree = nui.get("tree1");
	    	var nodes = {};
	        nodes = tree.getCheckedNodes(false);//获取所有选中的节点
	        if(nodes.length == 0){
	        	alert("请选择一条押品分类");
	       		return;
			}
	        CloseWindow(nodes);
	    }
    
   </script>
</body>
</html>