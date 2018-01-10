<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="授信品种" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:98%;height:98%;padding:5px;background:#fafafa;margin-top:5px;"
			showTreeIcon="true" textField="productName" idField="productId" parentField="superiorId" expandOnLoad="false"
			onnodeclick="nodeclick" dataField="products" contextMenu="#treeMenu">
		</ul>
	</div>
	
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
	    <ul id="treeMenu" class="nui-contextmenu" onbeforeopen="onBeforeOpen"></ul>
			
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
            	// alert(nui.encode(text.products));
            		tree.loadList(text.products,"productId","superiorId");
            		if(tree.getRootNode().children[0]==undefined){
            		var tabs = nui.get("orgTabs");
            		tabs.setTabs([
							{title:"暂无", url:nui.context+"/pub/product/product/item_list.jsp?c=2"}
						]);
						$("#orgTabs").show();
            		}else{
            		  nodeclick({"node":tree.getRootNode().children[0]});
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
		var tabs = nui.get("orgTabs");//alert(nui.encode(e.node));
            		 if(e.node.productLevel=="3"){
            		 alert("三级分类不能再添加下级品种");
            		 }else{
            		  		tabs.setTabs([
										{title:e.node.productName, url:nui.context+"/pub/product/product/item_list.jsp?productId="+e.node.productId+"&productLevel="+e.node.productLevel+"&productCd="+e.node.productCd+"&c=1"}
									]);
										$("#orgTabs").show();
            		 }
	}
	// 授信结点对应的Tab页
	var product_add = { title: '新增授信品种大类', url: '<%=contextPath%>/pub/product/product/item_add.jsp?daLei=1' };
	// 机构右键菜单
	var orgMenu = [
		{text: "增加大类授信产品", onclick: "onAddProduct", iconCls: "icon-add"}
	];

	// 树结点类型与右键菜单的映射
	//var menu_map = {
	//	OrgOrganization: orgMenu
	// };
	
	
	/**
	 * 根据结点类型显示
	 */
	function onBeforeOpen(e) {
		var tree = nui.get("tree1");
		var node = tree.getSelectedNode();
		var menu = e.sender;
   //	alert(node.nodeType);
		//var menuList = menu_map[node.nodeType];
		//if(!menuList || menuList.length == 0) {
		//	e.cancel = true;
		//	return;
		//}
		menu.loadList(nui.clone(orgMenu)); //这里菜单项必须要克隆，否则第二次点击时无法触发事件
	}
	/**
	 * 获取当前选中结点对象，可提供给子页面调用
	 */
	this.getSelectedNode = function() {
		var tree = nui.get("tree1");
		return tree.getSelectedNode();
	}
	function onAddProduct(e) {
	
		  nui.open({
                    url: product_add.url,
                    title: "新增大类授信品种", 
                    width: 800,
            		height: 500,
                    allowResize:true,
            		showMaxButton: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        // var data = row;
                        //iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
	                       reload();
                   	 	}
                    }
                });
	}
	</script>
</body>
</html>
