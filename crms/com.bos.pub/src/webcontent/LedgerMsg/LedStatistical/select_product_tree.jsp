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
 	
<div title="授信品种" region="west"  style="width:100%;height:90%;"  class="nui-layout" allowResize="false">
		<div title="授信品种管理"   allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:auto;height:auto;" 
			showTreeIcon="true" textField="productName" idField="productId" parentField="superiorId" expandOnLoad="false"
			onnodeclick="nodeclick" dataField="products">
		</ul>
		</div>
</div>
	<div class="nui-toolbar" style="padding-right:20px;text-align:right;" >
	    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
	    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
	    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow('clear')">清空</a>
	</div> 
  
			
    <script type="text/javascript">
	 	nui.parse();

	var tree = nui.get("tree1");
	var currentNode = null;
	//tree.loadData(menudata);
	
function reload() {
	var productType="";//<%=request.getParameter("productTypeCd")%>
	$.ajax({
            url: "com.bos.pub.ledgerMsg.getProductList.biz.ext",
            type: 'POST',
            data: nui.encode({"productType":productType}),
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		tree.loadList(text.products,"productId","superiorId");
            		//nodeclick({"node":tree.getRootNode().children[0]});
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
		nui.alert("请选择末级的授信品种");
		return;
	}
	CloseWindow("ok");
}
	</script>
</body>
</html>
