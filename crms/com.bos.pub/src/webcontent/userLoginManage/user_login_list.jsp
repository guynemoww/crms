<%@page pageEncoding="UTF-8"%>
<html>
 <!-- Author(s):陈川
  - Date: 2015-09-15
  -踢人下线
--> 
<head>
<title>用户登录管理</title>
<%@include file="/common/nui/common.jsp"%>

</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="登录用户列表">
			<div id="form"
				style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px"
				class="nui-form">
				<div class="nui-dynpanel" columns="4">
					
				<label class="nui-form-label">机构名称：</label>
				 <input id="map.orgId"
						name="map.orgId"
						text="<%=((UserObject) session.getAttribute("userObject")).getUserOrgName()%>"
						value="<%=((UserObject) session.getAttribute("userObject")).getUserOrgId()%>"
						allowInput="false" class="nui-buttonEdit nui-form-input" 
						onbuttonclick="selectOrg" />
						
				<label>用户号：</label>
				<input name="map.userNum" id="map.userNum" class="nui-textbox nui-form-input" />
				</div>
						
				<div class="nui-toolbar"
					style="text-align: center; padding-top: 5px; padding-right: 25px;"
					borderStyle="border:0;">
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
				
				
				<div class="nui-toolbar"
					style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
				<a class="nui-button"
						iconCls="icon-remove" onclick="remove()" id="rmove">强制下线</a>
				</div>
			</div>
			<div id="grid" class="nui-datagrid" sortMode="client"
				url="	com.bos.pub.userLoginManage.findUserLoginList.biz.ext"
				dataField="items" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="10">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div type="indexcolumn" headerAlign="center">序号</div>
					<div field="IP" headerAlign="center" align="center" allowSort="true" >IP地址</div>
					<div field="USERID" headerAlign="center" align="center" allowSort="true" >登录用户号</div>
					<div field="USERID" headerAlign="center" align="center" allowSort="true" dictTypeId="user">登录用户名</div>
					<div field="ORGCODE" headerAlign="center" align="center"dictTypeId="org">登录机构</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		//var loanChangeType = nui.get("loanChangeType");
		var form = new nui.Form("#form");
		var grid = nui.get("grid"); //借据列表
		query();
		function query() {
			var o = form.getData();
			grid.load(o);
		}

		function remove() {
			var row = grid.getSelected();
			if (row) {
				nui.confirm("强制下线会中断用户回话，确定吗？", "确认", function(action) {
					if (action != "ok")
						return;
					var json = nui.encode({"userId" : row.USERID
					});
					$.ajax({
						url : "com.bos.pub.userLoginManage.deleteUserLogin.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							if(text.msg){
								nui.alert(text.msg);
							}else{
								nui.alert("操作成功");
								query();
							}
							
						},
						error : function() {
							nui.alert("操作失败！");
						}
					});
				});
			} else {
				nui.alert("请选中一条记录");
			}
		}
		
		//机构选择
		function selectOrg() {
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/sys/select_org_tree.jsp",
						showMaxButton : true,
						title : "选择机构",
						width : 350,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.GetData();
								data = nui.clone(data);
								if (data) {
									self.orglevel = data.orglevel;
									btnEdit.setValue(data.orgid);
									btnEdit.setText(data.orgname);
								}
							}
						}
					});
		}
		//重置
		function reset() {
			nui.get("map.userNum").setValue(null);
		}
	</script>
</body>
</html>