<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2014-02-19
  - Description: 岗位人员管理页面
-->
<head>
	<title>岗位人员管理</title>
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
						<td class="tit" nowrap="true">人员名称：</td>
						<td>
							<input class="nui-textbox" name="criteria._expr[0].posiname" />
							<input class="nui-hidden" name="criteria._expr[0]._op" value="like" />
							<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all" />
						</td>
						<td class="tit" nowrap="true">岗位类型：</td>
						<td>
						  <input class="nui-dictcombobox"   valueField="dictID" textField="dictName"
				                 dictTypeId="ABF_ROLETYPE" name="criteria._expr[1].posilevel" />
				          <input class="nui-hidden" name="criteria._expr[1]._op" value="="/>
						</td>
					</tr>
					<tr>
						<td class="btn-wrap" colspan="5">
							<input class="nui-button" iconCls="icon-search" text="查询" onclick="search" />
							<input class="nui-button" text="重置" onclick="form1.reset();" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
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
	<div id="roleGrid" class="nui-datagrid" style="width:100%;height:auto;" url="com.bos.utp.rights.positionManager.queryPosition.biz.ext"
		idField="roleid" multiSelect="false" allowAlternating="true" showPager="true" sizeList="[15,20,30]" pageSize="20" 
		selectOnLoad="true" onselectionchanged="selectedRoles" sortMode="client">
		<div property="columns">
			<div type="checkcolumn"></div>
			<div field="posicode" width="20%" headerAlign="center" allowSort="true">岗位编码</div>
			<div field="posiname" width="30%" headerAlign="center" allowSort="true">岗位名称</div>
			<div field="posilevel" width="30%" headerAlign="center" allowSort="true" dictTypeId="ABF_ROLETYPE">岗位类型</div>
		</div>
	</div>
  </div>

  <div style="width:40%;height:100%;">
    <div id="tabs1" class="nui-tabs" activeIndex="0" style="height:100%;" bodyStyle="padding:0;border:0;">
	  <div title="岗位人员" allowResize="true" />
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
	    	selectroleid = record.positionid ;
        	//tree1.load({roleId:record.roleid});
	    }
	}

	function addRole(){
		nui.open({
			url:"<%= request.getContextPath() %>/utp/rights/position/position_add.jsp",
			title:'岗位新增',
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
			nui.alert("请选中一岗位！");
			return false;
		}else if(rows.length == 1){
			nui.open({
				url:"<%= request.getContextPath() %>/utp/rights/position/position_update.jsp?positionid="+row.positionid,
				title:'岗位修改',
				width:500,
				height:290,
				onload:function(){
					var iframe = this.getIFrameEl();
					iframe.contentWindow.nui.get('item.posiname').setValue(row.posiname);
				},
				ondestroy:function(action){
					if(action == "saveSuccess"){
						var iframe = this.getIFrameEl();
						var posiname = iframe.contentWindow.nui.get('item.posiname').getValue();
						var sendData = nui.clone(row);
						sendData.posiname = posiname;
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
									roleGrid.reload();
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
			nui.alert("一次只能修改一个岗位！");
			return false;
		}
	}

	function removeRole(){
		var rows = roleGrid.getSelecteds();
		if(rows == null || rows.length == 0){
			nui.alert("请至少选中一个岗位！");
			return false;
		} else if(rows.length == 1){
			nui.confirm("确定删除选中记录？", "系统提示", function(action){
				if(action=="ok"){
					var sendData = nui.encode({item:rows[0]});
					roleGrid.loading("正在删除中,请稍等...");
					$.ajax({
						url:"com.bos.utp.rights.positionManager.removePosition.biz.ext",
						type:'POST',
						data:sendData,
						cache: false,
						contentType:'text/json',
						success:function(text){
							var returnJson = nui.decode(text);
							if(returnJson.returnCode == 1){
								roleGrid.reload();
							}else{
								nui.alert("岗位删除失败", "系统提示");
								roleGrid.unmask();
							}
						}
					});
				}
			});
		}else{
			nui.alert("一次只能删除一个角色！");
			return false;
		}
	}

	function search(){
		var form1Data = form1.getData(false, true);
        roleGrid.load(form1Data);
	}
</script>