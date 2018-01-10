<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): guyan
  - Date: 2014-04-21 18:36:52
  - Description:
-->
<head>
<title>人员权限管理</title>
<%@include file="/common/nui/common.jsp" %>
<%
	String empid = request.getParameter("empid");
%>
</head>
<body>
<div class="nui-fit" style="padding:1px 1px 1px 1px;" >
	<div class="nui-splitter" style="width:100%;height:100%;">
		<div showCollapseButton="true" size="40%" style="height:100%;overflow:auto;">
			<div id="orgpanel" class="mini-panel" title="所属机构" style="width:100%;height:100%;" 
			    showToolbar="false" showCollapseButton="false" showFooter="false" allowResize="false" collapseOnTitleClick="true">
				<ul id="tree" class="nui-tree" onbeforeload="onBeforeTreeLoad" url="com.bos.utp.org.organization.queryOrgNodeOfSelect.biz.ext" style="width:100%;height:100%;" 
					showTreeIcon="false" textField="nodeName" idField="nodeId" dataField="treeNodes" checkRecursive="false" 
					onload="onLoad" onnodeclick="nodeClick" >
			    </ul>
			</div>
		</div>
		<div showCollapseButton="true" style="height:100%;overflow:auto;">
			<div id="posipanel" class="mini-panel" title="所属岗位" style="width:100%;height:100%;" 
			    showToolbar="false" showCollapseButton="false" showFooter="false" allowResize="false" collapseOnTitleClick="true">
			    <div  style="padding:0px 0px 0px 0px;" id="form2">
					<div id="posiGrid" class="nui-datagrid" style="width:100%;" url="com.bos.utp.rights.positionManager.queryPositionAuth.biz.ext"
						idField="roleid" multiSelect="true" allowAlternating="true" showPager="false" sizeList="[15,20,30]" pageSize="20" 
						sortMode="client" >
						<div property="columns">
							<div type="checkcolumn" width="8%">序号</div>
							<div field="posicode" width="30%" headerAlign="center" allowSort="true">岗位编码</div>
							<div field="posiname" width="62%" headerAlign="center" allowSort="true">岗位名称</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;" borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-save" onclick="save" >保存</a>
</div>
</body>
<script type="text/javascript">
	var data = nui.decode('<%=request.getParameter("data") %>');
	var n = 1;
	
	nui.parse();
	var empid = '<%=empid %>';
	var orgid = "";
	
	var posiGrid = nui.get("posiGrid");
	posiGrid.load();
	
	function nodeClick(e) {
		callNodeClick(e.node);
	}
	
	function callNodeClick(node) {
		orgid = node.nodeId;
		
		nui.get("posipanel").setTitle(node.nodeName + "--所属岗位");
		
		$.ajax({
            url: "com.bos.utp.rights.positionManager.queryEmpOrgPosi.biz.ext",
            data:"{empid:"+empid+",orgid:"+orgid+"}",
            type: 'POST',
            cache: false,
            contentType:'text/json',
            success: function (text) {
                if(text.retCode=="1"){
                	var allgrid = posiGrid.getData();
                	var posi = text.data;
                	posiGrid.deselectAll();             	
			    	for(var i = 0; i < allgrid.length; i++) {
			    		var m = 0;
			    		for(m = 0;m < posi.length; m++) {
			    			if(allgrid[i].positionid==posi[m].omPosition.positionid) {
			    			       posiGrid.select(posiGrid.getRowByUID(allgrid[i]._uid));
			    			       break;		    	
			    			} 
			    		}
			    		/**if(m>=posi.length) {
			    			posiGrid.deselect(posiGrid.getRow(i),false);
			    		}**/
			    	}
                } else{
                   alert("获取权限失败");
                }
            },
            error: function () {
            }
        });
	}
	
	function save() {
		if(orgid=="") {
			nui.alert("请先选择所属机构!");
			return;
		}
		
		var gridSelVals = posiGrid.getSelecteds();
//modify by zhangfahui  岗位可以取消配置
// 		if(gridSelVals.length < 1){
// 			nui.alert("请选择所属岗位");
// 			return;
// 		}
		var posis = [];
    	//所有选中岗位
    	for(var i = 0; i < gridSelVals.length; i++){
    		posis.push(gridSelVals[i].positionid);
    	}
    	var json = nui.encode({empid:empid,orgid:orgid,posis:posis});
    	$.ajax({
            url: "com.bos.utp.rights.positionManager.saveEmpOrgPosi.biz.ext",
            data:json,
            type: 'POST',
            cache: false,
            contentType:'text/json',
            success: function (text) {
                if(text.reCode==1){
                	nui.alert("保存成功");
                } else {
                	if(text.reCode==3){
                	
                		nui.alert(text.reDesc);
                	}else{
                	
	                	nui.alert("保存失败");
                	}
                }
            },
            error: function () {
            }
        });
	}
	
	//树加载完后加载grid
    function onLoad(){
    	var tree = nui.get("tree");
    	/**setTimeout(function(){
	    	var rootNode = tree.getRootNode();
	    	if(null != rootNode) {
	    		var childNode = rootNode.children;
	    		if(null != childNode) {
	    			tree.loadNode(childNode[0]);
	    		}
	    	}
    	},500);**/
    }
	
	//树加载前设置打勾的结点
    function onBeforeTreeLoad(e) {
    	var tree = nui.get("tree");
    	if(null!=data){
    		e.params.orgids = data["orgids"];
    		var orgs = data["orgids"].split(',');
    		setTimeout(function(){
	    		for (var i=0,len=orgs.length; i<len; i++) {
	    			var node  = tree.getNode(orgs[i]);
	    			if(null != node) {
	    				if(n < 2) {
	    					tree.selectNode(node);
	    					callNodeClick(node);
	    					n++;
	    				}
	    			}
	    		}
    		},1500);
    	}
    }
</script>
</html>