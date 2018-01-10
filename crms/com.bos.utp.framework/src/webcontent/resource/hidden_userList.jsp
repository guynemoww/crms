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
				<div id="userGrid2" class="nui-datagrid"  url="com.bos.utp.framework.ResourceManager.queryOperator.biz.ext" idField="userid" multiSelect="true" allowAlternating="true" showPager="true" sizeList="[10,20,30]" pageSize="10" 
						  sortMode="client" onload="onGridLoad"   onselectionchanged="onSelectoinChanged"  >
				<div property="columns" >
					<div type="checkcolumn" width="10%"></div>
					<div field="userid" width="45%" headerAlign="center" allowSort="true" align="center">操作员编码</div>
					<div field="operatorname" width="45%" headerAlign="center" allowSort="true" align="center">操作员名称</div>
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
			<td  style="width:50%;padding-right:20px;text-align:right;"  valign="top">
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
	var userGrid2 = nui.get("userGrid2");;
	userGrid2.load();	
	var form1 = new nui.Form("#form1");
	//定义隐藏角色ID数组
	var HideUserId_List = new Array();
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
	  //查询人员ID
	   $.ajax({
			url:"com.bos.utp.framework.ResourceManager.queryResourceHiddenUserId.biz.ext",
			type:'POST',
			data:sendData,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var returnJson = nui.decode(text.retCode);
				var obj = nui.decode(text);
				if(returnJson=='1'){
					HideUserId_List=obj.resourceUsers;
				}else{
					nui.alert('获取人员列表失败！');
				}
			}
		});
	});
	
	
	//通过onHidedrawRoles函数选中当前页面资源ID所拥有的角色
	function onHidedrawUsers(e){
	 var grid=e.sender;
	 var users="";
	 var findRole="";
	 for(var i=0;i<HideUserId_List.length;i++){
	  users+=HideUserId_List[i].USERID+",";	       
	 }
	 if(users!=""){
		 if(users.lastIndexOf(",")!=-1){
		 	users=users.substring(0,users.lastIndexOf(","));
		 }else{
		 	users=users;
		 }
	 }
	 var str= new Array(); 
	 str=users.split(",");
	
	 var rows = grid.findRows(function (row) {
	   		for(var i=0;i<str.length;i++){
	   			if(str[i]==row.userid){
	   				return true;
	   			}
	   		}	
         }
      );    
      grid.selects(rows);
	}
	
	
	function GetSelecteds() {
        var rows = userGrid2.getSelecteds();
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
    	onHidedrawUsers(e);
        var rows = selectMaps[userGrid2.getPageIndex()];
        if(rows) userGrid2.selects(rows);
    }
    
    function onSelectoinChanged(e) {
        var rows = userGrid2.getSelecteds();
        selectMaps[userGrid2.getPageIndex()] = rows;
    }
    
    
    //当用户点击取消某行的时候，监听的事件 进行用户取消更新操作
    userGrid2.on("rowclick", function (e) {
         var userid=e.record.userid;
         var dg=e.sender;  //var row=dg.getSelected();==e.record
  		 var isSelect=dg.isSelected(e.record); 
  		 if(isSelect==false){//当点击取消，不选中的时候
  		 	 var resouceid='<%=request.getParameter("resouceid")%>';
	  		 var sendData = nui.encode({userid:userid,resouceid:resouceid,resourcestate:'hidden',permissiontype:'P'});
	  		 $.ajax({
				url:"com.bos.utp.framework.ResourceManager.clickDeleteResourceUser.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					var returnJson = nui.decode(text.returnCode);
					if(returnJson=='1'){
						//$(window.parent.onUserActiveTab()); 
						var row = grid.findRows(function(row){
							if(row.userid == e.record.userid) return false;
						});
						userGrid2.reload();
					}else{
						userGrid2.unmask();
					}
				}
			});
  		 }
      
    });
    
    
    
    var showUsers = new Array();
    //点击保存显示的函数
    function formHideSaving(){
    	var flag=1;
    	var rows = userGrid2.getSelecteds();
    	var resouceid='<%=request.getParameter("resouceid")%>';
		if(rows == null || rows.length == 0){
			var resouceid='<%=request.getParameter("resouceid")%>';
	  		 var sendData = nui.encode({userid:"",resouceid:resouceid,resourcestate:'hidden',permissiontype:'P'});
	  		 $.ajax({
				url:"com.bos.utp.framework.ResourceManager.deleteResourceUserByHide.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					var returnJson = nui.decode(text.returnCode);
					if(returnJson=='1'){
						userGrid2.reload();
						$(window.parent.onUserActiveTab()); 
					}else{
						userGrid2.unmask();
					}
				}
			});
		}else {
			var sendData2 = nui.encode({userIds:rows,resouceid:resouceid,flag:'hidden'});
			$.ajax({
				url:"com.bos.utp.framework.ResourceManager.showUserState.biz.ext",
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
				            message: "您所选的"+showuser+"用户权限状态已经设置为显示状态<br/>您确定重新设置吗？",
				            callback: function (action) {
				                if(action=='ok'){
				                 	userGrid2.loading("正在更新设置中,请稍等...");
									var sendData = nui.encode({userIds:rows,resouceid:resouceid,resourcestate:'hidden',permissiontype:'P'});
									$.ajax({
										url:"com.bos.utp.framework.ResourceManager.updateResourceUser.biz.ext",
										type:'POST',
										data:sendData,
										cache: false,
										contentType:'text/json',
										success:function(text){
											var returnJson = nui.decode(text.returnCode);
											if(returnJson=='1'){
												nui.alert("资源人员更新成功！", "系统提示", function(action){
												userGrid2.reload();
												$(window.parent.onUserActiveTab());
												});
											}else{
												nui.alert("资源人员更新失败！", "系统提示");
												userGrid2.unmask();
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
	 					userGrid2.loading("正在保存中,请稍等...");
						var sendData = nui.encode({userIds:rows,resouceid:resouceid,resourcestate:'hidden',permissiontype:'P'});
						$.ajax({
							url:"com.bos.utp.framework.ResourceManager.updateResourceUser.biz.ext",
							type:'POST',
							data:sendData,
							cache: false,
							contentType:'text/json',
							success:function(text){
								var returnJson = nui.decode(text.returnCode);
								if(returnJson=='1'){
									nui.alert("资源人员保存成功！", "系统提示", function(action){
									userGrid2.reload();
									$(window.parent.onUserActiveTab());
									});
								}else{
									nui.alert("资源人员保存失败！", "系统提示");
									userGrid2.unmask();
								}
							}
						});
	 				}
	 				
	 				
				}
			});
		
		}
    }
	
	
</script>
