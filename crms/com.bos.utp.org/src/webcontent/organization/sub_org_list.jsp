<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>下级机构</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div style="padding:5px 5px 0px 5px;">
	 <div class="nui-toolbar" style="border-bottom:0;">
        <table style="width:100%;">
            <tr>
	            <td style="width:100%;">
	                <a class="nui-button" iconCls="icon-add" onclick="add">增加</a>
	            	<a class="nui-button" iconCls="icon-edit" onclick="edit" id="edit_btn">编辑</a>
	            	<a class="nui-button" iconCls="icon-remove" onclick="remove">删除</a>
	            </td>
            </tr>
        </table>
    </div>
</div>
<div class="nui-fit" style="padding:0px 5px 5px 5px">
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;" 
	url="com.bos.utp.org.organization.queryOrg.biz.ext" dataField="treeNodes" onselectionchanged="selectionChanged" ajaxData="getAjaxData" idField="id" allowResize="true" sizeList="[2,10,20,50,100]" pageSize="10" multiSelect="true">
	    <div property="columns">
	        <div type="checkcolumn"></div>
	        <div field="orgname" width="120" headerAlign="center" >机构名称</div>    
	        <div field="orgcode" width="120" headerAlign="center" >机构代码</div>
	        <div field="buno" width="120" headerAlign="center" >BU号</div>
	        <div field="linkman" width="120" headerAlign="center" >联系人</div>
	        <!--<div field="orgtype" width="120" renderer="renderOrgType" headerAlign="center" >机构类型</div>    
	        <div field="status" width="120" renderer="renderOrgStatus" headerAlign="center" >机构状态</div>-->    
	    </div>
	</div>
</div>

<div id="removePrompt" style="width:100%;display:none;">
   <table>
     <tr>
       <td>
          <div class="mini-messagebox-question"></div>
       </td>
       <td>&nbsp;&nbsp;&nbsp;确实想要删除选中机构吗?</td>
     </tr>
     <tr>
       <td style="height:22px;">
         &nbsp;&nbsp;&nbsp;&nbsp;
         <input id="isDeleteCascade" type="checkbox" checked="true"/>
       </td>
       <td style="vertical-align:top;">删除机构下员工关联用户</td>
     </tr>
   </table>
</div>

<script type="text/javascript">
	nui.parse();
	
	(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		}
	})();
	
	var grid = nui.get("datagrid1");
	grid.load();
	
	var bootPath = "<%=request.getContextPath() %>/";
	
	function add(){
		nui.open({
            url: bootPath + "coframe/org/organization/org_add.jsp",
            title: "添加下级机构", width: 600, height: 400,
            onload:function(){
            	var iframe = this.getIFrameEl();
                var data = {parentNode:window.parentNode||{}};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
               grid.reload();
               if(action=="ok"){
	                if(window.parent){
	               		window.parent.refreshCurrentNode();
	               	}
               }
            }
        });
	}
	
	function edit(){
		var row = grid.getSelected();
        if (row) {
			nui.open({
	            url: bootPath + "coframe/org/organization/org_update.jsp",
	            title: "修改下级机构", width: 610, height: 392,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	                data.action="update";
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	                grid.reload();
	                if(action=="ok"){
		                if(window.parent){
		               		window.parent.refreshCurrentNode();
		               	}
              		}
	            }
	        });
		}else{
			nui.alert("请选中一条记录");
		}
	}
	
	function remove(){
		var rows = grid.getSelecteds();
        if (rows && rows.length > 0) {
            removePrompt(rows);
        }else{
			nui.alert("请选中一条记录");
		}
	}
	
	var prompt = document.getElementById("removePrompt");
	function removePrompt(rows){
	   prompt.style.display="";
	   nui.showMessageBox({
	      width:300,
	      title:'系统提示',
	      buttons:["ok","cancel"],
	      html:prompt,
	      showModal: false,
	      callback: function (action) {
	         var isDeleteCascade = document.getElementById("isDeleteCascade").checked;
             if(action=="ok"){
                window['result']={
                  isDeleteCascade:isDeleteCascade,
                  action:true
                };
             }else{
                window['result']={
                  isDeleteCascade:isDeleteCascade,
                  action:false
                };
             }
             executeRemove(rows);
          }
	   });
	}
	
	function executeRemove(rows){
	    var result = window.result;
	    if(result.action){
	       	var json = nui.encode(getRemoveData(rows, "OrgOrganization"));
	        grid.loading("操作中，请稍后......");
	        $.ajax({
	            //url: "com.bos.utp.org.organization.deleteOrg.biz.ext",
	            url:"com.bos.utp.org.organization.deleteNodes.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	                grid.reload();
	                if(!text.exception){
		                if(window.parent){
		               		window.parent.refreshCurrentNode();
		               	}
	               }
	            },
	            error: function () {
	            }
	        });
        }
	}
	
	function selectionChanged(){
		var rows = grid.getSelecteds();
		if(rows.length>1){
			//disable edit button
			nui.get("edit_btn").disable();
		}else{
			nui.get("edit_btn").enable();
		}
	}
	
	//组合删除的数据；需要固定的格式
	function getRemoveData(rows, nodeType){
		var childs = [];
		for(var i=0,len=rows.length;i<len;i++){
			var child = rows[i];
			child.nodeId = child.orgid;
			child.nodeType = nodeType;
			child.orgOrganization = null;
			childs.push(child);			
		}
		var parentId = "";
		var parentType = "";
		if(window.parentNode){
			parentId = window.parentNode.nodeId;
			parentType = window.parentNode.nodeType;
		}
		var data = {
			childs:childs,
			parentType:parentType,
			parentId:parentId,
			isDeleteCascade:window.result.isDeleteCascade
		}
		return data;
	}
	
	function getAjaxData(){
		if(window.parentNode){
			return {"criteria":{"_expr":[{"orgOrganization.orgid":window.parentNode.orgid||"","_op":"="}],
								"_order":[{"_asc":"sortno"}]}
			};
		}
	}
	
	function renderOrgType(e){
		return nui.getDictText("COF_ORGTYPE", e.row.orgtype);
	}
	
	function renderOrgStatus(e){
		return nui.getDictText("COF_ORGSTATUS", e.row.status);
	}
	
	/*
	function changeValue(rowid){
		var row = grid.getRow(0);
		if(!row.internal_selected){
			grid.select(row);
		}else{
			grid.deselect(row);
		}
		grid.updateRow(row,{internal_selected:!row.internal_selected});
	}
	
	function checkboxRender(e){
		//debugger;
		if(grid.isSelected(e.row)){
			e.row.internal_selected = true;
			return "<img onclick='changeValue(" + e.record.id + ");' src='/default/utp/tools/images/checkbox/checkbox_yes.png'/>";
		}else{
			e.row.internal_selected = false;
			return "<img onclick='changeValue(" + e.record.id + ")' src='/default/utp/tools/images/checkbox/checkbox_no.png'/>";
		}
		return "";
	}
	*/
</script>

</body>
</html>