<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): josan
  - Date: 2015-12-18 08:57:14
  - Description:
-->
<head>
<title>获取关联关系为其他时的关联关系</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div title="字典树" region="west"  style="width:97%;height:89%;margin-top:7px;"  class="nui-layout" allowResize="false">
		<div title="字典树"   allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:auto;height:auto;" 
			showTreeIcon="true" textField="dictname" idField="dictid" parentField="parentid" expandOnLoad="true"
			onnodeclick="nodeclick" dataField="dictList">
		</ul>
		</div>
</div>

<div style="width:97%">
	<div class="nui-toolbar" style="height:auto;text-align:right;padding-top:5px;padding-bottom:5px;border:none" >
	    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
	    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
        <a class="nui-button"  iconCls="icon-cancel" onclick="onClear()">清空</a>
	</div>
</div>
    <script type="text/javascript">
	 	nui.parse();
	var sqlName="<%=request.getParameter("sqlName")%>";
	var tree = nui.get("tree1");
	var currentNode = null;
	function reload() {
	$.ajax({
            url: "com.bos.csm.corporation.RelationShip.getReltree.biz.ext",
            type: 'POST',
            data: '{"sqlName":"<%=request.getParameter("sqlName")%>"}',
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		text.dictList=text.dictList||[];
            		$.each(text.dictList, function(idx,val){
            			//console.log(idx + ',' + val);
            			val.dictid=val.dictid||val.DICTID;
            			val.dictname=val.dictname||val.DICTNAME;
            		});
            		tree.setExpandOnLoad(text.dictList.length <20);//选择项多余20的时候，不展开
            		tree.loadList(text.dictList,"DICTID","PARENTID");
            		//nodeclick({"node":tree.getRootNode().children[0]});
            		<%-- com.bos.pub.dict.getCodeList --%>
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
function onClear() {
	    CloseWindow("clear");
    }
	</script>
</body>
</html>