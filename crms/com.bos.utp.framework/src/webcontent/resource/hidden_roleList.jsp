<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
	<title>页面权限配置</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1">
	<table style="width:100%;height:50%;" class="nui-form-table">
		<tr>
			<td>
				<div id="roleGrid2" class="nui-datagrid"  url="com.bos.utp.framework.ResourceManager.queryShowRole.biz.ext" idField="roleid" multiSelect="true" allowAlternating="true" showPager="true" sizeList="[10,20,30]" pageSize="10" 
						  sortMode="client" onload="onGridLoad"   onselectionchanged="onSelectoinChanged"  >
				<div property="columns" >
					<div type="checkcolumn" width="10%"></div>
					<div field="roleid" width="45%" headerAlign="center" allowSort="true" align="center">角色编码</div>
					<div field="rolename" width="45%" headerAlign="center" allowSort="true" align="center">角色名称</div>
				</div>
				</div>	
			</td>
		</tr>
		 <tr>
		 	<td style="height:20px;">
		 		&nbsp;
		 	</td>
		 </tr>
		 <tr valign="top">
			<td style="padding-right:20px;text-align:right;width:50%;"  valign="top" >
				<a class="nui-button" iconCls="icon-save" onclick="formHideSaving">确认</a>
				<span style="display:inline-block;width:25px;"></span>
				<a class="nui-button" iconCls="icon-cancel" onclick="formCancel">返回</a>
			</td>
		</tr>		
	</table>	
</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var roleGrid2 = nui.get("roleGrid2");;
	roleGrid2.load();	
	
	//定义隐藏角色ID数组
	var HideRoleId_List = new Array();
	<%-- 关闭窗口 --%>
	function CloseWindow(action){
		if(action=="close" && form1.isChanged()){
			if(confirm("数据已改变,是否先保存?")){
				return false;
			}
		}else if(window.CloseOwnerWindow){
			return window.CloseOwnerWindow(action);
		}else{
			return window.close();
		}
	}
	
	function formCancel(){
		$(window.parent.formCancel());
	}
	
	
	$(function(){
	  var resouceid='<%=request.getParameter("resouceid")%>';
	  var sendData = nui.encode({resouceid:resouceid});
	  //查询角色ID
	   $.ajax({
			url:"com.bos.utp.framework.ResourceManager.queryResourceHiddenRoleId.biz.ext",
			type:'POST',
			data:sendData,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var returnJson = nui.decode(text.retCode);
				var obj = nui.decode(text);
				if(returnJson=='1'){
					HideRoleId_List=obj.resourceRoles;
				}else{
					nui.alert('获取角色列表失败！');
				}
			}
		});
	});
	
	
	//通过onHidedrawRoles函数选中当前页面资源ID所拥有的角色
	function onHidedrawRoles(e){
	 var grid=e.sender;
	 var roles="";
	 var findRole="";
	 for(var i=0;i<HideRoleId_List.length;i++){
	  roles+=HideRoleId_List[i].ROLEID+",";	       
	 }
	 if(roles!=""){
		 if(roles.lastIndexOf(",")!=-1){
		 	roles=roles.substring(0,roles.lastIndexOf(","));
		 }else{
		 	roles=roles;
		 }
	 }
	 var str= new Array(); 
	 str=roles.split(",");
	
	 var rows = grid.findRows(function (row) {
	   		for(var i=0;i<str.length;i++){
	   			if(str[i]==row.roleid){
	   				return true;
	   			}
	   		}	
         }
      );    
      grid.selects(rows);
	}
	
	
	function GetSelecteds() {
        var rows = roleGrid2.getSelecteds();
        return rows;
    }
    
    var selectMaps = {};
    function GetAllSelecteds() {
        var data = [];
        for(var pageIndex in selectMaps){
            var rows = selectMaps[pageIndex];
            data.addRange(rows);
        }
        return data;
    }
    
    function GetData() {
        var rows = GetAllSelecteds();
        var ids = [], texts = [];
        for (var i = 0, l = rows.length; i < l; i++) {
            var row = rows[i];
            ids.push(row.id);
            texts.push(row.name);
        }
        var data = {};
        data.id = ids.join(",");
        data.text = texts.join(",");
        return data;
    }
    
    function onGridLoad(e) {
    	onHidedrawRoles(e);
        var rows = selectMaps[roleGrid2.getPageIndex()];
        if(rows) roleGrid2.selects(rows);
    }
    
    function onSelectoinChanged(e) {
        var rows = roleGrid2.getSelecteds();
        selectMaps[roleGrid2.getPageIndex()] = rows;
    }
    
    
     //当用户点击取消某行的时候，监听的事件 进行用户取消更新操作
    roleGrid2.on("rowclick", function (e) {
         var roleid=e.record.roleid;
         var dg=e.sender;  //var row=dg.getSelected();==e.record
  		 var isSelect=dg.isSelected(e.record); 
  		 if(isSelect==false){//当点击取消，不选中的时候
  		  var resouceid='<%=request.getParameter("resouceid")%>';
	  		 var sendData = nui.encode({roleid:roleid,resouceid:resouceid,resourcestate:'show',permissiontype:'R'});
	  		 $.ajax({
				url:"com.bos.utp.framework.ResourceManager.clickDeleteResourceRole.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					var returnJson = nui.decode(text.returnCode);
					if(returnJson=='1'){						
						//$(window.parent.onactiveTab()); 
						var row = grid.findRows(function(row){
							if(row.roleid == e.record.roleid) return false;
						});
						roleGrid2.reload();
					}else{
						roleGrid2.unmask();
					}
				}
			});
  		 }     
    });
    
    
    
    var showUsers = new Array();
    //点击保存显示的函数
    function formHideSaving(){
    	var rows = roleGrid2.getSelecteds();
    	var resouceid='<%=request.getParameter("resouceid")%>';
		if(rows == null || rows.length == 0){
			 var resouceid='<%=request.getParameter("resouceid")%>';
	  		 var sendData = nui.encode({userid:"",resouceid:resouceid,resourcestate:'hidden',permissiontype:'R'});
	  		 $.ajax({
				url:"com.bos.utp.framework.ResourceManager.deleteResourceRoleByState.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					var returnJson = nui.decode(text.returnCode);
					if(returnJson=='1'){
						roleGrid2.reload();
						$(window.parent.onactiveTab()); 
					}else{
						roleGrid2.unmask();
					}
				}
			});
		}else {
			var sendData2 = nui.encode({roleIds:rows,resouceid:resouceid,flag:'hidden'});
			$.ajax({
					url:"com.bos.utp.framework.ResourceManager.ShowRoleState.biz.ext",
					type:'POST',
					data:sendData2,
					cache: false,
					contentType:'text/json',
					success:function(text){
						var obj = nui.decode(text);
						showUsers=obj.showUsers;
						var showuser="";
						if(showUsers.length!=0){
							 for(var i=0;i<showUsers.length;i++){
				  			  showuser+=showUsers[i]+",";	    
				             }
						}
						if(showuser!=""){
							if(showuser.lastIndexOf(",")!=-1){
							  showuser=showuser.substring(0,showuser.lastIndexOf(","));
							}else{
							  showuser=showuser;
							}
							flag=2;
		 				}
		 				
		 				if(flag==2){
		 					nui.showMessageBox({
					            title: "系统提示！",
					            iconCls: "nui-messagebox-question",
					            buttons: ["ok", "no"],
					            message: "您所选的"+showuser+"角色权限状态已经设置为显示状态<br/>您确定重新设置吗？",
					            callback: function (action) {
					                if(action=='ok'){
										roleGrid2.loading("正在更新设置中,请稍等...");
										var sendData = nui.encode({roleIds:rows,resouceid:resouceid,resourcestate:'hidden',permissiontype:'R'});
										$.ajax({
											url:"com.bos.utp.framework.ResourceManager.updateResourceRole.biz.ext",
											type:'POST',
											data:sendData,
											cache: false,
											contentType:'text/json',
											success:function(text){
												var returnJson = nui.decode(text.returnCode);
												if(returnJson=='1'){
													nui.alert("资源角色更新成功！", "系统提示", function(action){
													roleGrid2.reload();
													$(window.parent.onactiveTab());
													});
												}else{
													nui.alert("资源角色更新失败！", "系统提示");
													roleGrid2.unmask();
												}
											}
										});
					                }
					                if(action=='no'){
					                	return;
					                }
					            }
					        });
		 				}else{
		 					roleGrid2.loading("正在保存中,请稍等...");
							var sendData = nui.encode({roleIds:rows,resouceid:resouceid,resourcestate:'hidden',permissiontype:'R'});
							$.ajax({
								url:"com.bos.utp.framework.ResourceManager.updateResourceRole.biz.ext",
								type:'POST',
								data:sendData,
								cache: false,
								contentType:'text/json',
								success:function(text){
									var returnJson = nui.decode(text.returnCode);
									if(returnJson=='1'){
										nui.alert("资源角色保存成功！", "系统提示", function(action){
										roleGrid2.reload();
										$(window.parent.onactiveTab());
										});
									}else{
										nui.alert("资源角色保存失败！", "系统提示");
										roleGrid2.unmask();
									}
								}
							});
		 				}
					
					}
				});
			
			
			/**
				roleGrid2.loading("正在保存中,请稍等...");
				var sendData = nui.encode({roleIds:rows,resouceid:resouceid,resourcestate:'hidden',permissiontype:'R'});
				//alert('sendData='+sendData);
				$.ajax({
					url:"com.bos.utp.framework.ResourceManager.updateResourceRole.biz.ext",
					type:'POST',
					data:sendData,
					cache: false,
					contentType:'text/json',
					success:function(text){
						var returnJson = nui.decode(text.returnCode);
						if(returnJson=='1'){
							nui.alert("资源角色更新成功！", "系统提示", function(action){
							roleGrid2.reload();
							});
						}else{
							nui.alert("资源角色更新失败！", "系统提示");
							roleGrid2.unmask();
						}
					}
				});
			*/
			
			
		}
    }
	
	
</script>
