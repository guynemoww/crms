<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2014-02-19
  - Description: 岗位管理页面
-->
<head>
	<title>岗位管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="2">
				 
		<label> 岗位名称： </label>
		<input class="nui-textbox" name="posiname" />
<!-- 		<input class="nui-textbox" name="criteria._expr[0].posiname" /> -->
<!-- 		<input class="nui-textbox" name="criteria._expr[0].posiname" /> -->
<!-- 		<input class="nui-hidden" name="criteria._expr[0]._op" value="like" /> -->
<!-- 		<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all" /> -->
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<input class="nui-button" iconCls="icon-search" text="查询" onclick="search" />
			<input class="nui-button" text="重置" onclick="form1.reset();" />
    </div>
</div>
<div style="width:99.5%">
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<!-- resId="pub_position_manager_add" -->
			<a class="nui-button" iconCls="icon-add" onclick="addRole">增加</a>
			<a class="nui-button" id="btnEdit" iconCls="icon-edit" onclick="updateRole(0)">修改</a>
			<a class="nui-button" id="btnEdit" iconCls="icon-edit" onclick="updateRole(1)">查看</a>
			<a class="nui-button" iconCls="icon-remove" onclick="removeRole">删除</a>
			<a class="nui-button"  onclick="toMutex()">形成互斥岗位组</a>
		</div>
</div>
<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto;" url="com.bos.utp.rights.positionManager.queryPosition.biz.ext"
	idField="roleid" multiSelect="false" allowAlternating="true" showPager="true" sizeList="[15,20,30]" pageSize="20" 
	selectOnLoad="true" onselectionchanged="selectedRoles" sortMode="client">
	<div property="columns">
		<div type="checkcolumn"></div>
		<div field="posicode"   headerAlign="center" allowSort="true">岗位编码</div>
		<div field="posiname"  headerAlign="center" allowSort="true">岗位名称</div>
		<div field="posistate"  headerAlign="center" allowSort="true" style="width:50%;">岗位说明</div>
		<!--<div field="posilevel" width="30%" headerAlign="center" allowSort="true" dictTypeId="ABF_ROLETYPE">岗位类型</div>-->
	</div>
</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	
	var form1 = new nui.Form("#form1");
	var grid = nui.get("grid");
	grid.load();
    
    //角色菜单查询(包括初始查询第一个角色菜单查询)
	function selectedRoles(e){
		var grid = e.sender;
	    var record = grid.getSelected() ;
	    if (record) {
	    	var positionid = record.positionid ;
/*	    	var tabs1 = nui.get('tabs1');
	    	tabs1.setTabs([
				{title:'岗位人员', url:nui.context+'/utp/rights/position/empposition_manager.jsp?positionid='+positionid}
			]);
			tabs1.show();*/
	    }
	}
	
	//互斥岗位事件
	function toMutex(){
	
		var row = grid.getSelected();
		if(row){
			nui.open({
				url:"<%= request.getContextPath() %>/utp/rights/position/position_mutex_list.jsp?posicode="+row.posicode,
				title:'互斥岗位新增',
				width:800,
				height:400,
				onload:function(){
				},
				ondestroy:function(action){
						grid.reload();
				}
			});
		}else{
		
			alert("请选择一条记录");
		}
	
	}
	

	function addRole(){
		nui.open({
			url:"<%= request.getContextPath() %>/utp/rights/position/position_add.jsp",
			title:'岗位新增',
			width:500,
			height:390,
			onload:function(){
			},
			ondestroy:function(action){
				if(action == "saveSuccess"){
					grid.reload();
				}
			}
		});
	}

	function updateRole(v){
		var row = grid.getSelected();
		if(row){
			nui.open({
				url:"<%= request.getContextPath() %>/utp/rights/position/position_update.jsp?positionid="+row.positionid+"&view="+v,
				title:'岗位修改',
				width:500,
				height:290,
				onload:function(){
					var iframe = this.getIFrameEl();
					iframe.contentWindow.nui.get('item.posicode').setValue(row.posicode);
					iframe.contentWindow.nui.get('item.posiname').setValue(row.posiname);
					iframe.contentWindow.nui.get('item.posistate').setValue(row.posistate);
				},
				ondestroy:function(action){
					if(action == "saveSuccess"){
						var iframe = this.getIFrameEl();
						var posiname = iframe.contentWindow.nui.get('item.posiname').getValue();
						var posistate = iframe.contentWindow.nui.get('item.posistate').getValue();
						var sendData = nui.clone(row);
						sendData.posiname = posiname;
						sendData.posistate = posistate;
						sendData = nui.encode({item:sendData});
						$.ajax({
							url:"com.bos.utp.rights.positionManager.updatePosition.biz.ext",
							type:'POST',
							data:sendData,
							cache:false,
							contentType:'text/json',
							success:function(text){
								var returnJson = nui.decode(text);
								if(returnJson.returnCode+'' == 1){
									grid.reload();
								} else {
									alert('修改失败');
								}
							},
				     		error: function (jqXHR, textStatus, errorThrown) {
				                nui.alert(jqXHR.responseText);
				            }
						});
					}
				}
			});
		}else{
			nui.alert("请选择一条记录");
			return false;
		}
	}

	function removeRole(){
		var row = grid.getSelected();
		if(row){
		    var json=nui.encode({"map":{"position":row.positionid}});
		    $.ajax({
					url:"com.bos.utp.rights.positionManager.checkPositionInUser.biz.ext",
					type:'POST',
					data:json,
					cache:false,
					contentType:'text/json',
					success:function(text){
						if(text.msg){
						alert(text.msg);
						} else {
							  nui.confirm("确定删除选中记录？", "系统提示", function(action){
										if(action=="ok"){
											var sendData = nui.encode({item:row});
											grid.loading("正在删除中,请稍等...");
											$.ajax({
												url:"com.bos.utp.rights.positionManager.removePosition.biz.ext",
												type:'POST',
												data:sendData,
												cache: false,
												contentType:'text/json',
												success:function(text){
													var returnJson = nui.decode(text);
													if(returnJson.returnCode == 1){
														grid.reload();
													}else{
														nui.alert("岗位删除失败", "系统提示");
														grid.unmask();
													}
												}
											});
										}
									});
						}
					},
		     		error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
				});
		
			
		}else{
			nui.alert("请选择一条记录！");
			return false;
		}
	}

	function search(){
		var form1Data = form1.getData(false, true);
        grid.load(form1Data);
	}
</script>