<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/nui/common.jsp" %>
<title>证件类型选择</title>
</head>
<body>

	<div title="证件类型" region="west" style="width:100%;height:90%;" class="nui-layout" allowResize="false">
		<div title="证件类型" allowResize="false">
			<ul id="tree1" class="nui-tree" style="width:auto;height:auto;" 
				showTreeIcon="true" textField="dictname" idField="dictid" parentField="parentid" expandOnLoad="true"
				onnodeclick="nodeclick" dataField="dictList">
			</ul>
		</div>
	</div>


	<div  class="nui-toolbar" style="border-bottom:0;" borderStyle="border-left:0;border-top:0;border-right:0;text-align:right;">
        <a class="nui-button"  iconCls="icon-save" onclick="save()">确定</a>
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
					text.dictList=text.dictList||[];
					$.each(text.dictList, function(idx,val){
						val.dictid=val.dictid||val.dictID;
						val.dictname=val.dictname||val.dictName;
					});
					tree.setExpandOnLoad(text.dictList.length <20);//选择项多余20的时候，不展开
					tree.loadList(text.dictList,"dictid","parentid");
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
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
