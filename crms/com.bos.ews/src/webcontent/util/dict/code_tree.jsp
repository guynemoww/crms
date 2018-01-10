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
			onnodeclick="nodeclick" dataField="products">
		</ul>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow('clear')">清空</a>
</div>
    <script type="text/javascript">
	 	nui.parse();

	var tree = nui.get("tree1");
	var currentNode = null;
function reload() {
    var json=nui.encode({"dicttypeid":"<%=request.getParameter("dicttypeid") %>","dictid":"<%=request.getParameter("dictid") %>"});
	alert(json);
	$.ajax({
            url: "com.bos.ews.dict.Copy_of_GetCodeList.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            	   alert(nui.encode(text));
            	   alert(nui.encode(text.eosDictEntrys));
            		tree.loadList(text.eosDictEntrys,"dictid","parentid");
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
		nui.alert("请选择末级的字典项");
		return;
	}
	CloseWindow("ok");
}
	</script>
</body>
</html>
