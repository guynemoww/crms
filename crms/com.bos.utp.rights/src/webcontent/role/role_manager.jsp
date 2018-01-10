<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): liuzn (mailto:liuzn@primeton.com)
  - Date: 2013-03-06 19:07:31
  - Description: 角色管理页面
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
	<title>角色管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body style="width:100%;height:100%;">
<div class="nui-splitter" style="width:100%;height:100%;">
  <div size="650" showCollapseButton="false" style="height:100%;">
	<div class="search-condition">
		<div class="list">
			<div id="form1">
				<table id="table1" class="table" style="width:100%; align:center; ">
					<tr>
						<td >角色名称：</td>
						<td width="25%">
							<input class="nui-textbox" name="criteria._expr[0].rolename" />
							<input class="nui-hidden" name="criteria._expr[0]._op" value="like" />
							<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all" />
						</td>
						<td >角色类型：</td>
						<td width="25%">
						  <input class="nui-dictcombobox"   valueField="dictID" textField="dictName"
				                 dictTypeId="ABF_ROLETYPE" name="criteria._expr[1].roletype"/>
				          <input class="nui-hidden" name="criteria._expr[1]._op" value="="/>
						</td>
					</tr>
					<tr>
						<td class="btn-wrap" colspan="4">
							<input class="nui-button" iconCls="icon-search" text="查询" onclick="search" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div style="padding:10px 5px 0px 5px;">
		<div class="nui-toolbar" style="border-bottom:0;">
		  <table style="width:100%">
			<tr>
				<td>
					<a class="nui-button" iconCls="icon-add" onclick="addRole">增加</a>
					<a class="nui-button" id="btnEdit" iconCls="icon-edit" onclick="updateRole">修改</a>
					<a class="nui-button" iconCls="icon-remove" onclick="removeRole">删除</a>
				</td>
			</tr>
		  </table>
		</div>
	</div>
	<div class="nui-fit" style="padding:0px 5px 5px 5px;" id="form2">
		<div id="roleGrid" class="nui-datagrid" style="width:100%;height:99%;" url="com.bos.utp.rights.RoleManager.queryRole.biz.ext"
			idField="roleid" multiSelect="true" allowAlternating="true" showPager="true" sizeList="[15,20,30]" pageSize="20" 
			selectOnLoad="true" onselectionchanged="selectedRoles" sortMode="client">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="roleid" width="20%" headerAlign="center" allowSort="true">角色编码</div>
				<div field="rolename" width="30%" headerAlign="center" allowSort="true">角色名称</div>
				<div field="roletype" width="30%" headerAlign="center" renderer="renderRoletype" allowSort="true">角色类型</div>
			</div>
		</div>
	</div>
  </div>

  <div style="width:40%;height:100%;">
    <div id="tabs1" class="nui-tabs" activeIndex="0" style="height:100%;" bodyStyle="padding:0;border:0;">
	  <div title="权限功能树" allowResize="true">
 		<div style="height:1%;">&nbsp;</div>
		<div id="region1" region="west" title="权限功能树" showHeader="true" class="sub-sidebar" style="width:100%;height:99%;" allowResize="true">
			<ul id="tree1" class="nui-tree" url="com.bos.utp.rights.user.TreeManager.getRoleMenu.biz.ext" style="width:100%;padding:5px;height:91%;" 
		        showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false"  
		        showCheckBox="true" checkRecursive="true" dataField="roleTreeList" onbeforenodecheck="onBeforeNodeCheck" 
		        expandOnLoad="false" allowSelect="false" enableHotTrack="false">
		    </ul>
			<div class="nui-toolbar" style="text-align:center;padding-top:1px;padding-bottom:8px;"  borderStyle="border-left:0;border-bottom:0;border-right:0;">
		        <a class="nui-button" iconCls="icon-save" onclick="onOk()">保存</a>
		   	</div>
		</div>
	  </div>
	</div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var form1 = new nui.Form("#form1");
	var roleGrid = nui.get("roleGrid");
	var tree1 = nui.get("tree1");
	var selectedRowsNumber = 0;
	roleGrid.load();
	var selectroleid = "" ;
    
    //角色菜单查询(包括初始查询第一个角色菜单查询)
	function selectedRoles(e){
		var grid = e.sender;
	    var record = grid.getSelected() ;
	    if (record) {
	    	selectroleid = record.roleid ;
        	tree1.load({roleId:record.roleid});
	    } else {
	    	selectroleid = "" ;
	    }
	}

	function addRole(){
		nui.open({
			url:"<%= request.getContextPath() %>/utp/rights/role/role_add.jsp",
			title:'角色新增',
			width:500,
			height:290,
			onload:function(){
			},
			ondestroy:function(action){
				if(action == "saveSuccess"){
					roleGrid.reload();
				}
			}
		});
	}

	function updateRole(){
		var rows = roleGrid.getSelecteds();
		var row = roleGrid.getSelected();
		if(rows == null || rows.length == 0){
			nui.alert("请选中一角色！");
			return false;
		}else if(rows.length == 1){
			nui.open({
				url:"<%= request.getContextPath() %>/utp/rights/role/role_update.jsp",
				title:'角色修改',
				width:700,
				height:200,
				onload:function(){
					var iframe = this.getIFrameEl();
					iframe.contentWindow.setData(row);
				},
				ondestroy:function(action){
					if(action == "saveSuccess"){
						roleGrid.reload();
					}
				}
			});
		}else{
			nui.alert("只能修改一角色！");
			return false;
		}
	}

	function removeRole(){
		var rows = roleGrid.getSelecteds();
		if(rows == null || rows.length == 0){
			nui.alert("请至少选中一角色！");
			return false;
		} else if(rows.length == 1){
			nui.confirm("确定删除选中记录？", "系统提示", function(action){
				if(action=="ok"){
					var sendData = nui.encode({roles:rows});
					roleGrid.loading("正在删除中,请稍等...");
					$.ajax({
						url:"com.bos.utp.rights.RoleManager.removeRoles.biz.ext",
						type:'POST',
						data:sendData,
						cache: false,
						contentType:'text/json',
						success:function(text){
							var returnJson = nui.decode(text);
							if(returnJson.exception == null){
								nui.alert("角色删除成功", "系统提示", function(action){
									roleGrid.reload();
								});
							}else{
								nui.alert("角色删除失败", "系统提示");
								roleGrid.unmask();
							}
						}
					});
				}
			});
		}else{
			nui.alert("只能删除一角色！");
			return false;
		}
	}

	function search(){
		var form1Data = form1.getData(false, true);
        roleGrid.load(form1Data);
	}

	//角色类型字典
	function renderRoletype(e){
		return nui.getDictText("ABF_ROLETYPE", e.row.roletype);
	}

	//选中机构树节点
	function onOk() {
	    var node = tree1.getCheckedNodes();
	    if (node && tree1.isLeaf(node) == false) {
	        alert("不能只选中父菜单节点！");
	        return;
	    } else {
	    	var role = roleGrid.getSelecteds();
	    	if (role==null || role=="") {
				nui.alert("角色未选，请选择！", "系统提示");
				return true ;
	    	}
			var sendData = nui.encode({menus:node,roleid:selectroleid});
			tree1.loading("角色菜单保存中,请稍等...");
			nui.ajax({
				url:"com.bos.utp.rights.RoleManager.saveRoleMenus.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					var returnJson = nui.decode(text);
					if(returnJson.exception == null){
						if (returnJson.retCode == "-1") {
							nui.alert("角色菜单保存失败，请重试！", "系统提示", function(action){
								roleGrid.unmask();
							});
						} else {
							nui.alert("角色菜单保存成功！", "系统提示", function(action){
								roleGrid.reload();
							});
						}
					}else{
						nui.alert("角色菜单保存失败！", "系统提示");
						roleGrid.unmask();
					}
				}
			});
	    }
	}
	
	//菜单树节点点击处理
	function onBeforeNodeCheck(e) {
        var tree = e.sender;
        var node = e.node;
        if (tree.hasChildren(node)) {}
    }
	


</script>