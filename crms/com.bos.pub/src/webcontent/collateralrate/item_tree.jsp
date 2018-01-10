<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 卢金彬
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="授信品种"  bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="productName" idField="productId" parentField="superiorId" expandOnLoad="false"
			onnodeclick="nodeclick" dataField="products">
		</ul>
	</div>
	
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();

	var tree = nui.get("tree1");
	//tree.loadData(menudata);
	
function reload() {
	$.ajax({
            url: "com.bos.pub.product.getProductList.biz.ext",
            type: 'POST',
            data: {},
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		tree.loadList(text.products,"productId","superiorId");
            		// nodeclick({"node":tree.getRootNode().children[0]});
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
//tree.load("com.bos.pub.product.getProductList.biz.ext");
}
reload();
	var json;
	function nodeclick(e) {
	json=e.node.productId+":"+e.node.productName;
		  CloseWindow("ok");
	}
	</script>
</body>
</html>
