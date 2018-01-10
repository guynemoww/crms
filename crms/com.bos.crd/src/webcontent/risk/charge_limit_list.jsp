<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>监管限额</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div class="nui-fit">
		<div id="form">
			<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.crd.LimitService.searchChargeLimit" />
			<div class="nui-toolbar" style="border-bottom: 0; text-align: left;">
				<a class="nui-button" iconCls="icon-add" onclick="add" id="add">新增</a>
				<a class="nui-button" iconCls="icon-edit" onclick="edit" id="edit">修改</a>
			</div>
		</div>
		<div class="nui-fit">
			<div id="grid" class="nui-datagrid" style="height: 100%" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" multiSelect="false" pageSize="50" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">序号</div>
					<div field="LIMIT_TYPE" allowSort="true" dictTypeId="XD_EDCD0005" headerAlign="center" autoEscape="false">监管限额类型</div>
					<div field="LIMIT_AMT" allowSort="true" dataType="currency" headerAlign="center" autoEscape="false">监管限额金额</div>
					<div field="UPDATE_USER_NUM" allowSort="true" dictTypeId="user" headerAlign="center">经办人</div>
					<div field="UPDATE_ORG_NUM" allowSort="true" dictTypeId="org" headerAlign="center">经办机构</div>
					<div field="UPDATE_DATE" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd">经办时间</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("form");
		var grid = nui.get("grid");

		query();
		function query() {
			grid.load(form.getData());
		}

		//增加监管限额
		function add() {
			nui.open({
				url : nui.context + "/crd/risk/charge_limit_edit.jsp",
				title : "监管限额限额管理",
				width : 750,
				height : 300,
				allowResize : true,
				showMaxButton : true,
				ondestroy : function(action) {
					query();
				}
			});
		}
		function edit() {
			var col = grid.getSelected();
			debugger;
			if (col) {
				nui.open({
					url : nui.context
							+ "/crd/risk/charge_limit_edit.jsp?limitId="
							+ col.LIMIT_ID,
					title : "监管限额限额管理",
					width : 750,
					height : 300,
					allowResize : true,
					showMaxButton : true,
					ondestroy : function(action) {
						query();
					}
				});
			} else {
				alert("请先选择一条记录");
			}
		}
	</script>
</body>
</html>