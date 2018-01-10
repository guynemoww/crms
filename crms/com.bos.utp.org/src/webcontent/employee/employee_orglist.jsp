<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): guyan
  - Date: 2014-11-29 14:00:00
  - Description:
-->
<head>
<title>用户机构列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-toolbar" style="border-bottom:0;">
    <a class="nui-button" iconCls="icon-add" onclick="add">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit" id="edit_btn">编辑</a>
	<a class="nui-button" iconCls="icon-cancel" onclick="del">删除</a>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%; "  sortMode="client"
	url="com.bos.utp.org.employee.queryEmpOrgsByEmpId.biz.ext" 
	idField="ORGID" allowResize="true" sizeList="[10,20,50,100]" pageSize="10" multiSelect="false">
    <div property="columns">
        <div type="checkcolumn">选择</div>
        <div field="ORGID" allowSort="true"  headerAlign="center" visible="false">机构ID</div>
        <div field="ORGCODE" allowSort="true"  headerAlign="center" >机构行号</div>
        <div field="ORGNAME" allowSort="true" headerAlign="center" >机构名称</div>
        <div field="MAINFLAG" allowSort="true"  headerAlign="center" >主机构标识</div>
         <div field="ROLENAME" allowSort="true"  headerAlign="center" >角色名称</div>
    </div>
</div>
<script type="text/javascript">
	var data=nui.decode('<%=request.getParameter("data") %>');
	var bootPath = "<%=request.getContextPath() %>/";
	nui.parse();
	var grid = nui.get("datagrid1");
	
	loadData();
	
	/**
	 * 加载数据*/
	function loadData(){
		grid.load({'empId':data.empid, 'inorgId':data.inorgid});
	}
	
	/**
	 * 编辑用户-机构-角色关系*/
	function edit(){
		var row = grid.getSelected();
        if (row) {
        	var json = nui.encode({empId:data.empid,orgId:row.ORGID});
        	//编辑前先查询用户在该机构下已存在的角色
        	if(row.MAINFLAG=="是"){
        		row.MAINFLAG = 1;
        	}else{
        		row.MAINFLAG = 0;
        	}
        	//alert(row.MAINFLAG);
	        $.ajax({
	            url: 'com.bos.utp.org.employee.queryEmpRoles.biz.ext',
	            data: json,
	            type: 'POST',
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
		        	nui.open({
		        		//add by shangmf :增加mainFlag
			            url: bootPath + 'utp/org/employee/employee_org_rolelist.jsp?action=update&empid='+data.empid+'&orgid='+row.ORGID+'&roleids='+text.roleIds+'&mainFlag='+row.MAINFLAG,
			            title: '人员角色关系修改', width: 800, height: 600,
			            onload: function () {
			                var iframe = this.getIFrameEl();
			            },
			            ondestroy: function (action) {
			                grid.reload();
			            }
			        });
	            },
	            error: function () {
	            
	            }
	        });
        }
    }
    
    /**
     * 新增用户机构关系*/
    function add(){
    	nui.open({
            url: bootPath + 'utp/org/employee/employee_org_roleadd.jsp?action=update&empid='+data.empid+'&inorgid='+data.inorgid,
            title: '人员机构关系新增', width: 800, height: 600,
            onload: function () {
                var iframe = this.getIFrameEl();
            },
            ondestroy: function (action) {
			    grid.reload();
            }
        });
    } 
    
    /**
     * 删除用户-机构关系，同时级联删除用户在该机构下的角色*/
    function del(){
    	var row = grid.getSelected();
		/* if (row.ORGID==data.inorgid || row.MAINFLAG=='是'){
			nui.alert('主归属机构不可移除！','提醒');
		} else { */
			var json = nui.encode({empId:data.empid,orgId:row.ORGID});
			nui.confirm('是否移除该机构及机构下权限？', '提示',function(action){
				if(action != 'ok'){
					return false;
				} else {
					$.ajax({
			            url: 'com.bos.utp.org.employee.deleteEmpOrg.biz.ext',
			            data: json,
			            type: 'POST',
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			                if (text.res==1){  
				            	 nui.ajax({
			                     	url: "com.bos.utp.org.collService.CollServiceImplServiceService.delCollRoleByWebService.biz.ext",
			                    	type: "post",
			                     	data: "{empId:"+data.empid+",orgId:"+row.ORGID+"}", 
			                     	cache: false,
			                     	async: false,
			                     	contentType: 'text/json',
			                     	success: function (text) {
					                	nui.alert('删除成功！', '系统提示', function(action){
											grid.load({'empId':data.empid, 'inorgId':data.inorgid});
										});   
			                     	}
			                 	});
			                } else {
			            		nui.alert('删除失败！', '系统提示');
			                }
			            },
			            error: function () {
			            	nui.alert('删除失败！', '系统提示');
			            }
			        });
				}
			});
		//}
    } 
    
</script>
</body>
</html>