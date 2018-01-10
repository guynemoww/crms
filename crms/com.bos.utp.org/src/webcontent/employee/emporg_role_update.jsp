<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 20:14:50
  - Description:
-->
<head>
<title>人员归属机构及关联角色</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
 <div class="nui-toolbar" style="border-bottom:0;">
    <a class="nui-button" iconCls="icon-add" onclick="addRow">增加</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove">删除</a>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	url="com.bos.utp.auth.PermissionManager.getOperatorRolesByEmpid.biz.ext" 
	dataField="operatorRoles" onselectionchanged="" 　
	allowResize="true" pageSize="1000" multiSelect="false" showPager="false"
	allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true" idField="id">
    <div property="columns">
    	<input field="orglevelLessThan" visible="false" class="nui-hidden" id="orglevelLessThan" name="orglevelLessThan"/><!-- 是否上级机构  -->
        <div type="checkcolumn">选择</div>
<!--        <div type="indexcolumn">序号</div>-->
        <div field="orgname" allowSort="true" headerAlign="center" >机构名称
        </div>  
        <!--<div field="ismainorg" allowSort="true" headerAlign="center" >是否归属机构</div>-->
        <div field="empname" allowSort="true" headerAlign="center" >人员姓名</div> 
        <div field="rolename" allowSort="true" headerAlign="center" >角色名称</div>        
    </div>
</div>
<div id="addRowDiv" style="width:100%;display:none;">
   <table>
     <tr>
       <td class="nui-form-label"><label for="orgid$text">机构：</label></td>
	   <td><input id="orgid" name="employee.orgid" textName="employee.orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"/>
	   <input id="empid" class="nui-hidden"/></td>
     </tr>
			<tr>
				<td class="nui-form-label"><label for="specialty$text">角色：</label></td>
				<td><input id="specialty" name="employee.specialty" textName="employee.specialty"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectRole"/></td>
			</tr>
   </table>
</div>
 
<script type="text/javascript">
	nui.parse();
	
	(function(){
		if(window.parent && window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		} else {
			var tmp = {
				empid:'<%=request.getParameter("empid") %>',
				orgid:'<%=request.getParameter("orgId") %>'
			};
			if (tmp.orgid) {
				var org=git.getOrg();
				tmp = $.extend({}, org, tmp);
			}
			window['parentNode']=tmp;
		}
	})();
	//var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	//var formData = form.getData(false, true);
	var formData = {'acOperator/operatorid':window.parentNode.empid};
    grid.load(formData);
	grid.sortBy("acRole.rolename", "asc");
	
	var bootPath = "<%=request.getContextPath() %>/";
	
	function addRow(){
	
		nui.open({
            url: nui.context + '/utp/org/employee/emporg_role_add.jsp',
            showMaxButton: true,
            title: "新建关联",
            width: 350,
            height: 250,
            onload: function(e) {
            	var iframe = this.getIFrameEl();
            	var org={};
            	if (window.parent && window.parent.searchParentOrgNode)
            		window.parent.searchParentOrgNode(window.parentNode);
            	else
            		org=window.parentNode;
            	iframe.contentWindow.orglevel=org['orglevel'];
            	var orgId;
            	orgId=window.parentNode.orgid;
	            var data = {oid : org['orgid']||orgId,
	            	oname:org['orgname']||window.parentNode.orgname,
	            	olevel:org['orglevel']||window.parentNode.orglevel
	            	};
	            iframe.contentWindow.SetData(data);
            	
            	//_alert(iframe.contentWindow.orglevel);
            	//_alert(nui.encode(org));
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
            		var getData = iframe.contentWindow.getData;
            		nui.get("orgid").setValue(getData("orgid"));
            		nui.get("specialty").setValue(getData("specialty"));
            		doAdd();
                }
            }
        });
	}
	
	function doAdd() {
			var data = {
				"acOperatorRole/orgid": nui.get("orgid").value,
				"acOperatorRole/empid": window.parentNode['empid'],
				"acOperatorRole/roleid": nui.get("specialty").value
			}
			if (!nui.get("orgid").value) {
				nui.alert("未选择机构");
				return;
			}
			if (!nui.get("specialty").value) {
				nui.alert("未选择角色");
				return;
			}
			
			var json = nui.encode(data);
	        nui.ajax({
	            url: "com.bos.utp.auth.PermissionManager.insertOperatorRole.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	eval('var flag='+nui.encode(text));
	            	if(flag.flag==0) {
	            	if(text.res>0){
	            	   alert("角色已存在！");
	            	}else{
	            	    grid.reload();
	            		//window.parent.refreshParentNode();
	            		//window.parent.showNodeTabs();
	            	}
	            		
	            	} else {
	            		nui.alert("操作失败！");
	            	}
	            },
	            error: function () {
	            	nui.alert("操作失败，请联系管理员");
	            }
	        });	
	}
	
	
	function remove(){
		var rows = grid.getSelecteds();
        if (rows && rows.length > 0) {
        	for(var i=0; i<rows.length; i++){
        		var row = grid.getRow(rows[i])
        		if(!!row.orglevelLessThan){
        			nui.alert("无权删除该记录！");
        			return;
        		}
        	}
        	doRemove();
        }else{
			nui.alert("请选中一条记录");
		}
	}
	
	function doRemove() {
		nui.confirm("确定删除所选角色吗？", "提示？",
		function(action){
			if (action != "ok")
				return;
				
			var rows = grid.getSelecteds();
        	if (!rows || rows.length == 0) {
        		return;
        	}
			
			var data = {
				operatorRoles: rows 
			}
			
			var json = nui.encode(data);
	        nui.ajax({
	            url: "com.bos.utp.auth.PermissionManager.deleteOperatorRole.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	eval('var flag='+nui.encode(text));
	            	if(flag.flag==0) {
	            		alert("删除成功");
	            		grid.reload();
	            		//window.parent.refreshParentNode();
	            		//window.parent.showNodeTabs();
	            	} else {
	            		nui.alert("操作失败！");
	            	}
	            },
	            error: function () {
	            	nui.alert("删除失败，请联系管理员");
	            }
	        });	
		
		});
	}  
	
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/pub/sys/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
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
 function selectRole(e) {
    	var btnEdit = this;
    	var orglevel = window.parent.searchParentOrgNode(window.parentNode)['orglevel'];
        nui.open({
            url: bootPath + "/utp/org/employee/select_managed_role.jsp?level=" + orglevel,
            showMaxButton: false,
            title: "选择人员在其归属机构的角色",
            width: 400,
            height: 450,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   parentNode: window['parentNode'],
                   ids:ids,
                   texts:texts
                };
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }
</script>

</body>
</html>