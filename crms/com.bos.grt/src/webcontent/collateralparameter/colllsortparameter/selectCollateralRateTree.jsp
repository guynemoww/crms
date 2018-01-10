<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2014-03-30 22:57:34
  - Description:
-->
<head>
<title>Title</title>
</head>
<body>
	<div title="押品分类树"  bodyStyle="overflow:hidden;" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:350px;padding:5px;"  region="west"
			showTreeIcon="true" textField="sortName" idField="sortType" parentField="parentSortType" expandOnLoad="false"
			onnodedblclick="onNodeDblClick" dataField="collSortParameters" resultAsTree="false">
		</ul>
    </div>
   
    <div  class="nui-toolbar" style="border-bottom:0;" borderStyle="border-left:0;border-top:0;border-right:0;">
		<a class="nui-button"  iconCls="icon-save" onclick="save()">确定</a>
    </div>

	<script type="text/javascript">
     	nui.parse();
	 	var tree = nui.get("tree1");
	 	var corpid="<%=request.getParameter("corpid") %>";
	 	var collType = "<%=request.getParameter("collType") %>";

		function reload() {
			if(null==collType||"null"==collType){
				collType="";
			}
			var json  = nui.encode({"collSortParameter":{"collType":collType,"ableState":"2"}});
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
	
	    //选择需要新增押品信息的分类
		function save(){
			var node = tree.getSelectedNode();
			if(!node){
				alert("请选择一条押品分类");
				return;
			}
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
   
   		/**
		 * 点击关闭按钮，关闭窗口	
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
   </script>
</body>
</html>