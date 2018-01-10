<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div title="字典树" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="dictname" idField="dictid" parentField="parentid" expandOnLoad="true"
			onnodeclick="nodeclick" dataField="dictList">
		</ul>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;">
	    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
	</div>
    <script type="text/javascript">
	 	nui.parse();
		var tree = nui.get("tree1");
		var currentNode = null;
		function reload() {
			$.ajax({
	            url: "com.primeton.components.nui.DictLoader2.getDictData.biz.ext",
	            type: 'POST',
	            data: '{"dictTypeId":"<%=request.getParameter("dicttypeid")%>"}',
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		//如果押品类型为：基金-YP_GLCD0042，且类型为：封闭式基金-100，则去除封闭式（100）选项。
	            		if(text.dictList[0].dictID == "100" && "YP_GLCD0042" == "<%=request.getParameter("dicttypeid")%>"){
	            			text.dictList.remove(text.dictList[0]);
	            			text.dictList=text.dictList||[];
		            		$.each(text.dictList, function(idx,val){
		            			val.dictid=val.dictid||val.dictID;
		            			val.dictname=val.dictname||val.dictName;
		            		});
		            		tree.setExpandOnLoad(text.dictList.length < 20);//选择项多余20的时候，不展开
		            		tree.loadList(text.dictList,"dictid","parentid");
	            		}else{
		            		text.dictList=text.dictList||[];
		            		$.each(text.dictList, function(idx,val){
		            			val.dictid=val.dictid||val.dictID;
		            			val.dictname=val.dictname||val.dictName;
		            		});
		            		tree.setExpandOnLoad(text.dictList.length < 20);//选择项多余20的时候，不展开
		            		tree.loadList(text.dictList,"dictid","parentid");
	            		}
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
			});
		//tree.load("com.bos.pub.product.getProductList.biz.ext");
		}
		reload();
	
		function nodeclick(e) {
				currentNode = e.node;
		}
		function save(){
			if (!currentNode) {
				nui.alert("请选择一条记录");
				return;
			}
			if (currentNode.children) {
				nui.alert("请选择末级的字典项");
				return;
			}
			CloseWindow("ok");
		}
	</script>
</body>
</html>
