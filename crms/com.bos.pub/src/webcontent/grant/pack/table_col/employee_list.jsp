<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>下级人员</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="padding:5px;">
	<table style="width:100%;table-layout:fixed;" id="table1" class="table" >
		<tr>
			<!--<td class="tit">机构代码：</td>-->
			<td class="tit nui-form-label">工号：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[2].empcode" style="width:95%" vtype="maxLength:32"/>
				<input class="nui-hidden" name="criteria._expr[2]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[2]._likeRule" value="all"/>
			</td>
			<td class="tit nui-form-label">姓名：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[3].empname" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[3]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[3]._likeRule" value="all"/>
				
				<input class="nui-hidden" id="orgid" name="criteria._expr[1].inorgid" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[1]._op" value="="/>
			</td>
			 
			<td class="tit nui-form-label">角色名称：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[4].rolename" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[4]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[4]._likeRule" value="all"/>
			</td>
			 
			<td class="btn-wrap">
				<input class="nui-button" text="查询" iconCls="icon-search" onclick="search"/>
			</td>
		</tr>
	</table>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow('clear')">清空</a>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%; "  sortMode="client"
url="com.bos.utp.org.employee.queryEmpUsers.biz.ext" dataField="treeNodes" onselectionchanged="selectionChanged" 　idField="id" allowResize="true" sizeList="[10,20,50,100]" pageSize="10" multiSelect="true">
    <div property="columns">
        <div type="checkcolumn"></div>
        <div field="empcode" allowSort="true" width="10%" headerAlign="center" >工号</div>  
        <div field="empname" allowSort="true" width="15%" headerAlign="center" >姓名</div> 
        <div field="status" allowSort="true" width="10%" headerAlign="center" renderer="renderStatus" >操作状态</div>       
        <div field="inorgname" allowSort="true" width="20%" headerAlign="center" >所属机构</div>       
        <!--<div field="rolename" allowSort="true" width="20%" headerAlign="center" >所属角色</div>    
        --><div field="lastlogin" allowSort="true" width="25%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" >最后签到时间</div>    
    </div>
</div>
 
<div id="removePrompt" style="width:100%;display:none;">
   <table>
     <tr>
       <td>
          <div class="mini-messagebox-question"></div>
       </td>
       <td>&nbsp;&nbsp;&nbsp;确定删除选中员工记录吗?</td>
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
	nui.get("orgid").setValue(window.parentNode.orgid);
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var formData = form.getData(false, true);
    grid.load(formData);
	grid.sortBy("empcode", "desc");
	function search(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
		var formData = form.getData(false, true);
		
		//var json = nui.encode(formData);
        grid.load(formData);
	}
	
	var bootPath = "<%=request.getContextPath() %>/";
	
	function add(){
			var orgid;
        	orgid=nui.get("orgid").getValue();
		nui.open({
            url: bootPath + "utp/org/employee/employee_add.jsp?orgId="+orgid,
            title: "人员新增", width: 900, height: 500,
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
        	<%-- 
        	//判断坐边树展开的节点中是否有当前所选人员，如果有，则模拟点击该树节点
        	if(window.parent&&window.parent.getCurrentNode&&window.parent.refreshNode){
        		var node=window.parent.getCurrentNode();
        		if (node != null && window.parent.nui) {
        			if(node.expanded != true && node.expanded != 1)
        				window.parent.refreshNode(node);
        			var tree = window.parent.nui.get("tree");
        			if (tree) {alert(1);
        				var flag=false;
        				tree.eachChild(node, function(n){
        					if ('OrgEmployee'==n.nodeType && n.operatorid==row.empid) {
        						tree.selectNode(n);
        						window.parent.onNodeClick({"node":n});
        						flag=true;
        						return false;
        					}
        				});
        				if(flag)
        					return;
        			}
        		}
        	} --%>
        	var orgid;
        	orgid=nui.get("orgid").getValue();
			nui.open({
	            //url: bootPath + "utp/org/employee/employee_update.jsp",
	            url: bootPath + "utp/org/employee/employee_basicinfo_update.jsp?action=update&empid="+row.empid+"&orgId="+row.inorgid,
	            title: "人员信息修改", width: 850, height: 350,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                //var data = row;
	                //data.action="update";
	                //iframe.contentWindow.SetData(data);
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
	   var msgid = nui.showMessageBox({
	      width:300,
	      title:'系统提示',
	      buttons:["ok","cancel"],
	      html:prompt,
	      showModal: false,
	      callback: function (action) {
             nui.hideMessageBox(msgid);
	         var isDeleteCascade = (document.getElementById("isDeleteCascade")||{}).checked;
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
		    var json = nui.encode(getRemoveData(rows, "OrgEmployee"));
	        grid.loading("操作中，请稍后......");
	        $.ajax({
	            url: "com.bos.utp.org.organization.deleteNodes.biz.ext",
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
	
	//组合删除的数据；需要固定的格式
	function getRemoveData(rows, nodeType){
		var childs = [];
		for(var i=0,len=rows.length;i<len;i++){
			var child = rows[i];
			child.nodeId = child.empid;
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
	
	<%--function getAjaxData(){
		if(window.parentNode){
			return {"criteria":{"_expr":[{},{"orgid":window.parentNode.orgid||"","_op":"="}]}};
		}
	}--%>
	
	function selectionChanged(){
		var rows = grid.getSelecteds();
		if(rows.length>1){
			//disable edit button
			nui.get("edit_btn").disable();
		}else{
			nui.get("edit_btn").enable();
		}
	}
	
	function renderGender(e){
		return nui.getDictText("ABF_GENDER",e.row.gender);
	}
	
	function renderEmpStatus(e){
		return nui.getDictText("ABF_EMPSTATUS",e.row.empstatus);
	}
	
	function renderStatus(e){
		return nui.getDictText("ABF_OPERSTATUS",e.row.status);
	}
	/****
    *
	* 函数名：btnChangeStatus_onClick
	* 机 能： 用户状态变更 （解锁、停用、启用)
	* 输 入：无
	* 输 出: 无
	* 日 期：2013-07-18
	* 作 者：谭凯
	*
	*****/
	function btnChangeStatus_onClick(flag){
		var rows = grid.getSelecteds();
        var data = {objs:rows , flag:flag};
        var json = nui.encode(data); 
        if (rows.length > 0) {
             if (confirm("是否确定操作？")) {
                 grid.loading("操作中，请稍后......");
                 nui.ajax({
                     url: "com.bos.utp.org.employee.updateOperatorStatus.biz.ext",
                     type: "post",
                     data: json, 
                     cache: false,
                     contentType: 'text/json',
                     success: function (text) {
                     	if(text.retCode=="1"){
                     		alert("操作成功");
                     	}else{
                       		alert("操作失败");
                       	}
			            search();
                     },
                     error: function () {
                     }
                 });
                 
              }
         } else {
             alert("请选中一条记录");
         }	
	}
	 
	/****
    *
	* 函数名：btnReset_onClick
	* 机 能： 重置密码
	* 输 入：无
	* 输 出: 无
	* 日 期：2013-07-18
	* 作 者：谭凯
	*
	*****/
	function btnReset_onClick(){
		var rows = grid.getSelecteds();
        var data = {objs:rows};
        var json = nui.encode(data); 
        if (rows.length > 0) {
             if (confirm("是否确定操作？")) {
                 grid.loading("操作中，请稍后......");
                 nui.ajax({
                     url: "com.bos.utp.org.employee.resetOperatePwd.biz.ext",
                     type: "post",
                     data: json, 
                     cache: false,
                     contentType: 'text/json',
                     success: function (text) {
                     	if(text.retCode=="1"){
                     		alert("操作成功");
                     	}else{
                       		alert("操作失败");
                       	}
			            search();
                     },
                     error: function () {
                     }
                 });
                 
              }
         } else {
             alert("请选中一条记录");
         }	
	}
</script>

</body>
</html>