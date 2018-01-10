<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-26 14:43:12
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div class="nui-fit">
		<div id="form">
			<div style="padding-left: 20px; text-align: left;">
				<label>风险限额组名：</label>
				<input name="item.groupName" id="item.groupName" required="false" class="nui-textbox " style="width: 150px" />
				<a class="nui-button" onclick="query">搜索</a>
			</div>
			<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.crd.LimitService.searchRiskGroup" />
		</div>
		<div class="nui-toolbar">
			<input id="limitInfo.limitId" class="nui-hidden nui-form-input" name="limitInfo.limitId" />
			<a class="nui-button" id="" iconCls="icon-add" onclick="edit(0)">添加</a>
			<a class="nui-button" id="" iconCls="icon-edit" onclick="edit(1)">编辑</a>
			<a class="nui-button" id="" iconCls="icon-node" onclick="edit(2)">查看</a>
			<a class="nui-button" id="" iconCls="icon-remove" onclick="del()">删除</a>
		</div>
		<div class="nui-fit">
			<div id="grid" class="nui-datagrid" style="height: 100%" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" multiSelect="false" pageSize="50" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="GROUP_NAME" allowSort="true" width="" headerAlign="center">组名</div>
					<div field="CREATE_USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">创建人</div>
					<div field="CREATE_ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">创建机构</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var grid = nui.get("grid");
		query();
		function query() {
			var o = form.getData();
			grid.load(o);
		}
		function edit(type) {
			var groupId;
			if (type != 0) {
				var row = grid.getSelected();
				if (null == row) {
					nui.alert("请选择一笔组信息!");
					return false;
				}
				groupId = row.GROUP_ID;
			}
			var title = "风险限额组-"
					+ (type == 0 ? "新增" : (type == 1 ? "编辑" : "查看"));
			nui.open({
				url : nui.context + "/crd/risk/risk_group_edit.jsp?edit="
						+ type + (groupId ? "&groupId=" + groupId : ""),
				title : title,
				width : 800,
				height : 500,
				ondestroy : function(e) {
					grid.reload();
					top.bizConWin = this;
				}
			});
		}
		function del() {
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一笔组信息!");
				return false;
			} else if (!nui.confirm("是否确定删除所选数据，以及对应的明细信息？", "询问")) {
				return;
			}
			var json = nui.encode({
				"groupId" : row.GROUP_ID
			});
			$.ajax({
				url : "com.bos.crd.LimitService.removeRiskGroup.biz.ext",
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				success : function(mydata) {
					if (mydata.msg) {
						alert(mydata.msg);
						return;
					} else {
						alert("删除成功");
						query();
					}
				}
			});
		}
	</script>
</body>
</html>