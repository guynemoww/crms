<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.entity.name.CsmTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<head>
<%@include file="/common/nui/common.jsp"%>
<%@include file="/csm/transfer/csmxfe_common.jsp"%>
</head>
<body>
	<div class=nui-fit>
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="true" sortMode="client">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="PRODUCT_TYPE" headerAlign="center" allowSort="true" width="100" dictTypeId="product">业务品种</div>
				<div field="APPROVAL_NUM_URL" headerAlign="center" allowSort="true" width="100">批复编号</div>
				<div field="CREDIT_AMOUNT" headerAlign="center" align="right" allowSort="true" width="100">申请额度</div>
				<div field="CURRENCY_CD" headerAlign="center" allowSort="true" width="60" dictTypeId="CD000001">币种</div>
				<div field="CREDIT_TERM" headerAlign="center" allowSort="true" align="right" width="50">期限</div>
				<div field="CYCLE_UNIT" headerAlign="center" allowSort="true" width="60" dictTypeId="XD_GGCD6009">期限单位</div>
				<div field="BECOME_EFFECTIVE_MARK" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_SXCD8003">批复状态</div>
				<div field="USER_NUM" headerAlign="center" allowSort="true" width="80" dictTypeId="user">经办人</div>
				<div field="ORG_NUM" headerAlign="center" allowSort="true" width="200" dictTypeId="org">经办机构</div>
			</div>
		</div>
	</div>
	<div id="actionDiv" class="nui-toolbar" style="text-align: center; border: none;">
		<a class="nui-button" iconCls="icon-search" onclick="createDetail()">添加</a>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
		var userNum =
	<%=JspUtil.getParameterHaveSign(request, "userNum")%>
		;
		var orgNum =
	<%=JspUtil.getParameterHaveSign(request, "orgNum")%>
		;
		init();
		function init() {
			renderingGrid(grid, function(row) {
				row['APPROVAL_NUM_URL'] = '<a href="#" onclick="bizView3231(\''
						+ row['APPROVAL_NUM'] + '\');">' + row['APPROVAL_NUM']
						+ '</a>';
			});
			query();
		}

		function createDetail() {
			var actionDiv = nui.get("actionDiv");
			actionDiv.loading("数据处理中...");
			var rows = grid.getSelecteds();
			if (rows.length == 0) {
				alert("请选择数据");
				actionDiv.unmask();
				return;
			}
			var params = [];
			debugger;
			for (var i = 0; i < rows.length; i++) {
				params[i] = {
					"approveId" : rows[i].APPROVE_ID
				};
			}
			var json = nui.encode({
				"transferId" : transferId,
				"params" : params
			});
			debugger;
			$.ajax({
				url : "com.bos.csm.transfer.transfer.createDetail.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(data) {
					actionDiv.unmask();
					if (data.msg) {
						alert(data.msg);
						return;
					} else {
						init();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					actionDiv.unmask();
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function query() {
			grid
					.load({
						"sqlName" : "com.bos.csm.transfer.transfer.getApproveInfoToSelect",
						"item" : {
							"userNum" : userNum,
							"orgNum" : orgNum
						}
					});
		}
	</script>
</body>
</html>

