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
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="orgid" name="temp.inorgid" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">用户编号：</label>
			<input id="temp.empcode" name="temp.empcode"  class="nui-textbox nui-form-input"/>
			<label class="nui-form-label">用户名称：</label>
			<input id="temp.empname" name="temp.empname"  class="nui-textbox nui-form-input"/>
			<%--<label class="nui-form-label">所属部门：</label>
			<input id="temp.departmentId" name="temp.departmentId"  class="nui-textbox nui-form-input"/>--%>
			<label class="nui-form-label">使用现状：</label>
			<input id="temp.status" name="temp.status" data="data" valueField="dictID" textField="dictName" dictTypeId="ABF_OPERSTATUS" class="nui-dictcombobox" />
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset">重置</a>
	</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
    <a class="nui-button" iconCls="icon-add" onclick="add">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)" id="edit_btn">编辑</a>
	<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
	<!-- 
	<a class="nui-button" iconCls="icon-remove" onclick="remove">删除</a>
	 -->
	<a class="nui-button"  onclick="btnChangeStatus_onClick('running')">解锁</a>
	<a class="nui-button"  onclick="btnChangeStatus_onClick('stopuse')">停用</a>
	<a class="nui-button"  onclick="btnChangeStatus_onClick('running')">启用</a>
	<a class="nui-button"  onclick="btnReset_onClick">重置密码</a>
	<!-- <a id="rightsmgr" class="nui-button" iconCls="icon-add" onclick="rightsMange">授权</a> -->
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%; "  sortMode="client"allowAlternating="true"
	url="com.bos.utp.org.employee.queryEmployeeByPages.biz.ext" dataField="treeNodes" onselectionchanged="selectionChanged"
	idField="empid" allowResize="true" sizeList="[10,20,50,100]" pageSize="10" multiSelect="false" onbeforeload="judgeUser">
    <div property="columns">
        <div type="checkcolumn">选择</div>
        <div field="empcode" allowSort="true" width="10%" headerAlign="center" >用户编号</div>  
        <div field="empname" allowSort="true" width="15%" headerAlign="center" >用户名称</div> 
       <%-- <div field="departmentId" allowSort="true" width="20%" headerAlign="center" >所属部门</div>    --%>
        <div field="inorgname" allowSort="true" width="20%" headerAlign="center" >所属机构</div>       
        <div field="empstatus" allowSort="true" width="20%" headerAlign="center" dictTypeId="ABF_EMPSTATUS" >用户状态</div>    
        <div field="status" allowSort="true" width="10%" headerAlign="center" renderer="renderStatus" >使用现状</div>       
        <%--<div field="lastlogin" allowSort="true" width="25%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" >最后签到时间</div>--%>    
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
		function judgeUser(e){
		$.ajax({
            url: "com.bos.utp.org.organization.userAuthJudge.biz.ext",
            data:'',
            type: 'POST',
            cache: false,
            contentType:'text/json',
            success: function (text) {
                var userflag = text.res;
                if(-1==userflag){
                	//nui.get("rightsmgr").hide();
                }
            },
            error: function () {
            }
        });	
	}
	
	/*(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		}
	})();*/
	
	//刘子良 20141118号修改
	(function(){
		if(top["win"].getCurrentNode()){
			var node = top["win"].getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		}
	})();	
   
	//nui.get("orgid").setValue(window.parentNode.orgid);
	nui.get("orgid").setValue(top["win"].getCurrentNode().orgid);//刘子良 修改 20141120
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
	
	function reset(){
	
		form.reset();
		nui.get("orgid").setValue(top["win"].getCurrentNode().orgid);//刘子良 修改 20141120
	}
	
	var bootPath = "<%=request.getContextPath() %>/";
	
	//权限管理
	function rightsMange(){
		var rows = grid.getSelecteds();
		if(null!=rows && rows.length==1){
			nui.open({
	            url: bootPath + "utp/org/employee/employee_rightsmgr.jsp",
	            title: "人员权限管理", width: 800, height: 465,
	            allowResize: false,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = rows[0];
	               //  alert(nui.encode({"map":""}));
	                data.action="rigthsmgr";
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
			nui.alert("请选择一条记录");
		}
	}
	
	function add(){
			var orgid;
        	orgid=nui.get("orgid").getValue();
		nui.open({
            url: bootPath + "utp/org/employee/employee_add.jsp?orgId="+orgid,
            title: "人员新增", width: 900, height: 550,
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
	
	function edit(v){
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
        	var title ="编辑";
        	if('1'==v){
        		title='查看';
        	}
        	
        	var empcode = row.empcode;
        	var userid = "<%=((UserObject)session.getAttribute("userObject")).getUserId()%>";
        	if(userid != "sysadmin"){//只允许超级管理员操作该用户的权限
        	   if(empcode == "sysadmin"){
        	   		nui.alert("不好意思，您的权限不够，不能操作该用户，请联系超级系统管理员！");
        	   		return;
        	   }
        	}
        	var orgid;
        	orgid=nui.get("orgid").getValue();
			nui.open({
	            //url: bootPath + "utp/org/employee/employee_update.jsp",
	            //url: bootPath + "utp/org/employee/employee_basicinfo_update.jsp?action=update&empid="+row.empid+"&orgId="+row.inorgid+"&view="+v,
	            url: bootPath + "utp/org/employee/employee_tab.jsp?action=update&empid="+row.empid+"&orgId="+row.inorgid+"&view="+v+"&roleData="+nui.encode(row),
	            title: title, 
	            width: 900, 
	            height: 550,
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
        //alert(json);
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
                     		//alert("操作成功");
                     		
                     		//add by shangmf Begin:操作成功候调用webservice更新押品，不管是否成功不影响信贷更新
                     		nui.ajax({
                     			url: "com.bos.utp.org.collService.UserSycInter.collServiceCommInter.biz.ext",
                    	 		type: "post",
                     			data: json, 
                     			cache: false,
                     			contentType: 'text/json',
                     			success: function (text) {
                     			if(text.retCode=="success"){
                     				alert("修改成功，同步押品系统操作成功!");
                     			}else{
                       				alert("修改成功，同步押品系统操作失败!");
                       			}
			            			search();
                     			},
                    				error: function () {
                     			}
                 			});
                     		//add by shangmf End
                     		
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
	 //选择机构
    function selectOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/pub/sys/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function(action){
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
	
</script>

</body>
</html>