<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.entity.name.CsmTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div class="nui-fit">
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="false" sortMode="client">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="ORG_NUM" headerAlign="center" allowSort="true" width="100" dictTypeId="org">原机构名称</div>
				<div field="USER_NUM" headerAlign="center" allowSort="true" width="100" dictTypeId="user">原客户经理</div>
				<div field="PARTY_NUM" headerAlign="center" align="right" allowSort="true" width="100">可移交客户数量</div>
			</div>
		</div>
	</div>
	<div id="actionDiv" class="nui-toolbar" style="text-align: center; border: none;">
		<a class="nui-button" iconCls="icon-search" onclick="createDetail()">添加</a>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var actionDiv = nui.get("actionDiv");
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
		var orgNum =
	<%=JspUtil.getParameterHaveSign(request, "orgNum")%>
		;
		query();
		function query() {
			grid
					.load({
						"sqlName" : "com.bos.csm.transfer.transfer.getUserInfoToSelect",
						"item" : {
							"transferId" : transferId,
							"orgNum" : orgNum
						}
					});
		}

		function createDetail() {
			actionDiv.loading("数据处理中...");
			var row = grid.getSelected();
			if (!row) {
				alert("请选择一条数据");
				actionDiv.unmask();
				return;
			}
			var json = nui.encode({
				"param" : {
					"transferId" : transferId,
					"orgNum" : orgNum,
					"userNum" : row.USER_NUM
				}
			});
			$
					.ajax({
						url : "com.bos.csm.transfer.transfer.createDetailByUser.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(data) {
							actionDiv.unmask();
							if (data.msg) {
								alert("<html><label style='text-align: left;'>"
										+ data.msg + "</label><html>");
								return;
							} else {
								alert(actionSuccess);
								query();
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							actionDiv.unmask();
							nui.alert(jqXHR.responseText);
						}
					});
		}
	</script>
</body>
</html>
