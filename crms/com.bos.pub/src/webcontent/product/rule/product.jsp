<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css"/>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;overflow:hidden;">
	<div title="授信品种" region="west" bodyStyle="overflow:hidden;" width="200" class="sub-sidebar" allowResize="true">
		<ul id="tree1" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;" 
			showTreeIcon="true" textField="productName" idField="productId" parentField="superiorId" expandOnLoad="false"
			onnodeclick="nodeclick" dataField="products" allowDrag="true" allowDrop="true"  allowLeafDropIn="true">
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
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
reload();
 var productId;
 var productName;
	function nodeclick(e) {
       productId=e.node.productCd;
       productName=e.node.productName;
       // alert(productId+"=="+productName);
       CloseWindow("ok");
	}
	function GetData(){
	  return productId+":"+productName;
	}
	</script>
</body>
</html>
