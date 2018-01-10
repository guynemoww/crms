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
	<div class="nui-toolbar" style="text-align:center;" 
	    borderStyle="border-left:0;border-top:0;border-right:0;">
	      <label >名称：</label>
	      <input id="key" class="nui-textbox" style="width:150px;" onenter="onKeyEnter"/>
	      <a class="nui-button" style="width:60px;" onclick="search()">查询</a>
	</div>
	<div class="nui-fit">
        <ul id="tree1" class="nui-tree" style="width:100%;height:100%;" 
			showTreeIcon="true" textField="name" idField="branchCode" parentField="superiorBranchCode" expandOnLoad="false"
			onnodeclick="nodeclick" dataField="codeOrgs" url="com.bos.comm.pub.org.getBatchOrgList.biz.ext">
		</ul>
    </div> 
	<div class="nui-toolbar" style="text-align:center;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;" >
	    <a class="nui-button"  onclick="save">确定</a>
	    <a class="nui-button" id="cancelBtn_01" onclick="CloseWindow()">关闭</a>
	    <a class="nui-button" id="cancelBtn_01" onclick="CloseWindow('clear')">清空</a>
	</div> 
  
			
    <script type="text/javascript">
	 	nui.parse();

	var tree = nui.get("tree1");
	var currentNode = null;
	//tree.loadData(menudata);
	
function reload() {
	$.ajax({
            url: "com.bos.comm.pub.org.getBatchOrgList.biz.ext",
            type: 'POST',
            data: {},
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            	} else {
            		tree.loadList(text.codeOrgs,"branchCode","superiorBranchCode");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
//reload();
	
	function onKeyEnter(e) {
        search();
    }
    
    function search() {
        var key = nui.get("key").getValue();
        if(key == ""){
            tree.clearFilter();
        }else{
            tree.filter(function (node) {
                var text = node.name ? node.name : "";
                if (text.indexOf(key) != -1) {
                    return true;
                }
            });
        }
    }
	
function nodeclick(e) {
		currentNode = e.node;
}
function save(){
	if (!currentNode) {
		nui.alert("请选择一条记录");
		return;
	}
	CloseWindow("ok");
}
	</script>
</body>
</html>
