<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-05-18 14:53:03
  - Description:
-->
<head>
<title>打印测试</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
		<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" iconCls="icon-search" onclick="print()">打印</a>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: 65%; margin-top: 10px" sortMode="client" url="test.TestPrint.getAllFileName.biz.ext" dataField="fileNames" multiSelect="false" emptyText="没有查到数据" showReloadButton="false">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="reportName" width="10%" headerAlign="center">文件名称</div>
			<div field="path" headerAlign="center">路径</div>
		</div>
	</div>


	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("datagrid1");
		var form = new nui.Form("#form1");
		function query() {
			var o = form.getData();
			grid.load(o);
		}
		function print() {
			var row = grid.getSelected();
			if (row) {
				var json = {
					"map" : row
				};
				console.info(json);
				$.ajax({
					url : "test.TestPrint.print.biz.ext",
					type : 'POST',
					cache : false,
					data : nui.encode(json),
					contentType : 'text/json',
					success : function(text) {
					},
					error : function() {
						nui.alert("操作失败！");
					}
				});
			} else {
				nui.alert("请选中一条记录", "提示");
			}
		}
	</script>
</body>
</html>