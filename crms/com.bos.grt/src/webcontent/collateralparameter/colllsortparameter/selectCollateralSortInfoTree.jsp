<%@page pageEncoding="UTF-8"%>
<!-- 
  - Author(s): Administrator
  - Date: 2014-03-30 22:57:34
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css"/>
</head>
<body>
   <div title="押品分类树" region="west" style="width:100%;height:90%;" class="nui-layout" allowResize="false">
		<div title="押品分类树"   allowResize="false">
			<ul id="tree1" class="nui-tree" style="width:auto;height:auto;" 
		   		showTreeIcon="true" textField="sortName" idField="sortType" parentField="parentSortType" expandOnLoad="false"
		   		dataField="collSortParameters">
			</ul>
		</div>
    </div>
   
	<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
        <a class="nui-button"  iconCls="icon-save" onclick="save()">确定</a>
    </div>

	<script type="text/javascript">
		nui.parse();
	 	var tree = nui.get("tree1");
	 	var partyId="<%=request.getParameter("partyId") %>";
	 	var collType = "<%=request.getParameter("collType") %>";
        var isScf = "<%=request.getParameter("isScf")%>";
        
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