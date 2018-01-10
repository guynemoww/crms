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
				<div id="roleGrid" allowCellSelect="true" class="nui-datagrid"  url="com.bos.utp.framework.ResourceManager.queryShowRole.biz.ext" idField="roleid" multiSelect="true" allowAlternating="true" showPager="true" sizeList="[10,20,30]" pageSize="10" 
						  sortMode="client" onload="onGridLoad" onrowclick="roleRowClick"  onselectionchanged="onSelectoinChanged"  oncellclick="oncelldblclick"><!-- ondrawcell="ondrawRoles" oncellclick="oncelldblclick" -->
				<div property="columns" >
					<div type="checkcolumn" width="10%"  ></div>
					<div field="roleid" width="45%" headerAlign="center" align="center" allowSort="true">角色编码</div>
					<div field="rolename" width="45%" headerAlign="center" align="center" allowSort="true">角色名称</div>
				</div>
				</div>				
			</td>
		</tr>
		<!-- 
			<tr>
			<td>
				
				<fieldset id="fd1" >
				 <legend><label>所选中角色：</label></legend>
					<div id="content" style="height:40px;">
						
					</div>			        
				</fieldset>
			</td>
		</tr>
		 -->
		 <tr>
		 	<td style="height:20px;">
		 		&nbsp;
		 	</td>
		 </tr>
		 <tr valign="top">
			<td  style="width:50%;"  valign="top" align="center">
				<a class="nui-button" iconCls="icon-save" onclick="formShowSaving">确认</a>
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
	//定义角色ID数组
	var RoleId_List = new Array();
	var roleGrid = nui.get("roleGrid");;
	roleGrid.load();
	
	
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
			url:"com.bos.utp.framework.ResourceManager.queryResourceRoleId.biz.ext",
			type:'POST',
			data:sendData,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var returnJson = nui.decode(text.retCode);
				var obj = nui.decode(text);
				if(returnJson=='1'){
					RoleId_List=obj.resourceRoles;
				}else{
					nui.alert('获取角色列表失败！');
				}
			}
		});		
	});
	
	
	//通过ondrawRoles函数选中当前页面资源ID所拥有的角色
	function ondrawRoles(e){
	 var grid=e.sender;
	 var roles="";
	 var findRole="";
	 for(var i=0;i<RoleId_List.length;i++){
	  roles+=RoleId_List[i].ROLEID+",";	       
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
	
	var currentRoleId="";
	function roleRowClick(e){
		 var record=e.sender;
		 var row=record.isSelected(record.getSelected());//是否选中行 返回true or false
		 var result="";
		 if(row){
		   var currentRow=record.getSelected();
		   currentRoleId=currentRow.roleid;
		   result+=currentRow.rolename+","
		   var span="<span id='"+currentRow.roleid+"'>"+result+"</span>";
		   var roleid=currentRow.roleid;
		   if($("#"+roleid).length<=0){//防止添加重复的记录
		    if($("#content").text()==""){
		    	$("#content").append(span);
		    }else{
		    	//$("#content").appendTo(span);
		    	//$(span).appendTo("#content");
		    	//$("span").after(span)
		    	$("#content").append(span);
		    }
		   }
		 }else{
		  var roleid=e.record.roleid;
		  $("#"+roleid).remove();
		 }
		 
	}
	
	function GetSelecteds() {
        var rows = roleGrid.getSelecteds();
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
    	ondrawRoles(e);
        var rows = selectMaps[roleGrid.getPageIndex()];
        if(rows) roleGrid.selects(rows);
    }
    
    function onSelectoinChanged(e) {
        var rows = roleGrid.getSelecteds();
        selectMaps[roleGrid.getPageIndex()] = rows;
    }
    
    //当用户点击取消某行的时候，监听的事件 进行用户取消更新操作
    roleGrid.on("rowclick", function (e) {
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
						roleGrid.reload();
					}else{
						roleGrid.unmask();
					}
				}
			});
  		 }
      
    });
    
    
    
    
    
    
    //保留
	function oncelldblclick(e){
     /*var row = e.row;
   	 var field = e.field;
   	 var fieldVal=row[field];
   	 alert('fieldVal='+fieldVal);
	 $("#"+fieldVal).remove();*/
	 
	 //var record = e.record;
     //alert(roleGrid.indexOf(record));
     //alert(e.column.field);
     
     //var cell = roleGrid.getCurrentCell();
	 //alert(nui.encode(cell[1]));	 
	}
	
	/*roleGrid.on("rowclick", function (e) {
         var roleid=e.record.roleid;
         $("#"+roleid).remove();
    });*/
    
    var showUsers = new Array();
    //点击保存显示的函数
    function formShowSaving(){
    	var rows = roleGrid.getSelecteds();
    	var resouceid='<%=request.getParameter("resouceid")%>';
		if(rows == null || rows.length == 0){
			 var resouceid='<%=request.getParameter("resouceid")%>';
	  		 var sendData = nui.encode({userid:"",resouceid:resouceid,resourcestate:'show',permissiontype:'R'});
	  		 $.ajax({
				url:"com.bos.utp.framework.ResourceManager.deleteResourceRoleByState.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					var returnJson = nui.decode(text.returnCode);
					if(returnJson=='1'){
						roleGrid.reload();
						$(window.parent.onactiveTab()); 
					}else{
						roleGrid.unmask();
					}
				}
			});
		}else {
			var sendData2 = nui.encode({roleIds:rows,resouceid:resouceid,flag:'show'});
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
					            message: "您所选的"+showuser+"角色权限状态已经设置为隐藏状态<br/>您确定重新设置吗？",
					            callback: function (action) {
					                if(action=='ok'){
										roleGrid.loading("正在更新设置中,请稍等...");
										var sendData = nui.encode({roleIds:rows,resouceid:resouceid,resourcestate:'show',permissiontype:'R'});
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
													roleGrid.reload();
													$(window.parent.onactiveTab());
													});
												}else{
													nui.alert("资源角色更新失败！", "系统提示");
													roleGrid.unmask();
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
		 					roleGrid.loading("正在保存中,请稍等...");
							var sendData = nui.encode({roleIds:rows,resouceid:resouceid,resourcestate:'show',permissiontype:'R'});
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
										roleGrid.reload();
										$(window.parent.onactiveTab());
										});
									}else{
										nui.alert("资源角色保存失败！", "系统提示");
										roleGrid.unmask();
									}
								}
							});
		 				}						
					}
				});
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
				roleGrid.loading("正在保存中,请稍等...");
				var sendData = nui.encode({roleIds:rows,resouceid:resouceid,resourcestate:'show',permissiontype:'R'});
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
							roleGrid.reload();
							});
						}else{
							nui.alert("资源角色更新失败！", "系统提示");
							roleGrid.unmask();
						}
					}
				});
			*/
			
		}
    }
    
    
	
	
</script>
