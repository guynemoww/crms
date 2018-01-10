<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): guyan
  - Date: 2014-11-29 14:00:00
  - Description:
-->
<head>
<title>机构角色列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-toolbar" style="border-bottom:0;">
    <a class="nui-button" iconCls="icon-add" onclick="save">保存</a>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%; "  sortMode="client"
	url="com.bos.utp.org.employee.queryRolesByOrgId.biz.ext" onload="gridcheckdeal" 
	idField="ROLEID" allowResize="true" sizeList="[10,20,50,100]" pageSize="50" multiSelect="true">
    <div property="columns">
        <div type="checkcolumn">选择</div>
        <div field="ROLEID" allowSort="true" width="20%" headerAlign="center" >角色ID</div>
        <div field="ROLENAME" allowSort="true" width="50%" headerAlign="center" >角色名</div>
    </div>
</div>
<script type="text/javascript">
	var empid = '<%=request.getParameter("empid") %>';
	var orgid = '<%=request.getParameter("orgid") %>';
	var roleids = '<%=request.getParameter("roleids") %>';
	var bootPath = "<%=request.getContextPath() %>/";
	var mainFlag = '<%=request.getParameter("mainFlag") %>';
	
	nui.parse();
	var grid = nui.get("datagrid1");
	
//	debugger;
	loadData();
	
	/**
	 * 加载数据*/
	function loadData(){
		grid.load({'orgId':orgid});
	}
	
    //表格行处理
    function gridcheckdeal(){
    	//所有角色信息
    	var griddata = grid.getData();
    	//用户的角色
    	if (roleids){
    		var userroleids = roleids.split(',');
	    	for (var i = 0; i < griddata.length; i++){
	    		if (userroleids.contains(griddata[i].ROLEID)){
	    			grid.select(grid.getRowByUID(griddata[i]._uid));
	    		} else {
	    			grid.deselect(grid.getRowByUID(griddata[i]._uid),false);
	    		}
	    	}
    	}
    }
	
    /**
     * 用户-角色关系*/
    function save(){
    	var gridselroles = grid.getSelecteds();//选中的角色
    	var gridallroles = grid.getData();//全部的角色
    	
    	var delroleids = [];
    	var selroleids = [];
    	//所有选中角色
    	for(var i = 0; i < gridselroles.length; i++){
    		selroleids.push(gridselroles[i].ROLEID);
    	}
    	//所有未选中角色
    	for(var i = 0; i < gridallroles.length; i++){
    		if(!selroleids.contains(gridallroles[i].ROLEID)){
    			delroleids.push(gridallroles[i].ROLEID);
    		}
    	}
    	
    	var selroleidsjson = ("\""+selroleids+"\"");
    	var delroleidsjson = ("\""+delroleids+"\"");
    	
		$.ajax({
            url: 'com.bos.utp.org.employee.saveEmpRoles.biz.ext',
            data: "{selroleids:"+selroleidsjson+",delroleids:"+delroleidsjson+",empId:"+empid
            	+",orgId:"+orgid+"}",
            type: 'POST',
            cache: false,
            contentType:'text/json',
            success: function (text) {
                if (text.res==1){
                /*  先注掉
                	nui.alert('分配成功！', '系统提示', function(action){
                		window.CloseOwnerWindow('ok');
					});     
				*/	//alert(mainFlag);
				//	if( mainFlag == "1" ){
						//add by shangmf Begin:操作成功候调用webservice更新押品，不管是否成功不影响信贷更新
                    	nui.ajax({
                     	url: "com.bos.utp.org.collService.CollServiceImplServiceService.updCollRoleByWebService.biz.ext",
                    	type: "post",
                     	data: "{selroleids:"+selroleidsjson+",delroleids:"+delroleidsjson+",empId:"+empid+",orgId:"+orgid+"}", 
                     	cache: false,
                     	contentType: 'text/json',
                     	success: function (text) {
       //              	if(text.retCode=="success"){
       //              		alert("分配成功!");
       //              	}else{
       //               		alert("分配成功!");
       //                	}
                       	nui.alert('分配成功！', '系统提示', function(action){
                			window.CloseOwnerWindow('ok');
						}); 
			            	//search();
                     	},
                    	error: function () {
                     	}
                 		});
                    	//add by shangmf End
				//	}else{
						
				//	}					
					 			
                } else {
            		nui.alert('分配失败！', '系统提示');
                }
            },
            error: function () {
            	unmask();
            	nui.alert('分配失败！', '系统提示');
            }
        });
    } 
    
</script>
</body>
</html>