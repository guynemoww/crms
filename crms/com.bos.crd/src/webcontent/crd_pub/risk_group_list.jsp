<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-26 14:43:12
  - Description:
-->
<head>
<title>品种组展示</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<center>
		<div id="form" style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px" class="nui-form">
			<div class="nui-dynpanel" columns="6">
				<label>组名：</label>
				<input name="map.groupName" id="map.groupName" required="false" class="nui-textbox nui-form-input" />
				<label></label>
				<a class="nui-button" onclick="query">搜索</a>
			</div>
		</div>
		<div id="datagrid" class="nui-datagrid" sortMode="client" url="com.bos.crdPub.riskLimitGroup.getGroups.biz.ext" dataField="riskGroups" allowAlternating="true" multiSelect="false" showEmptyText="true" sortMode="client" emptyText="没有查到数据" showReloadButton="false" oncellclick="GetData" allowCellEdit="true" allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="10">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="GROUP_NAME" allowSort="true" width="" headerAlign="center">组名</div>
				<div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">创建人</div>
				<div field="ORG_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="org">创建机构</div>
			</div>
		</div>
		<div class="nui-toolbar" style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
			<a class="nui-button" id="biz_detail_enter_add" iconCls="icon-add" onclick="save()">确定</a>
		</div>
	</center>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var grid = nui.get("datagrid");
		var limitOrg =
	<%="\"" + request.getParameter("limitOrg") + "\""%>
		;
		var data;
		query();
		function query() {
			var o = form.getData();
			grid.load({
				"map.groupName" : o.map.groupName,
				"limitOrg" : limitOrg
			});
		}
		//子页面调用方法
		function GetData() {
			var row = grid.getSelected();
			data = row;
			return data;
		}
		function save() {
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一笔批复");
				return false;
			}
			CloseWindow("ok");
			return;
		}
	</script>
</body>
</html>