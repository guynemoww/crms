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
	<div id="actionDiv" class="nui-toolbar" style="border: none; display: none;">
		<a class="nui-button" iconCls="icon-search" onclick="addDetail()">添加</a>
		<a class="nui-button" iconCls="icon-reset" onclick="removeDetail()">删除</a>
		<a id="approveBtn" class="nui-button" iconCls="icon-reset" onclick="approve(csmxfe)">确认</a>
	</div>
	<div class=nui-fit>
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="true" sortMode="client">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="STATUS_CN" headerAlign="center" allowSort="true" width="100">移交状态</div>
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
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var actionDiv = nui.get("actionDiv");
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
		var csmxfe;

		init();
		function init() {
			renderingGrid(grid, function(row) {
				row['APPROVAL_NUM_URL'] = '<a href="#" onclick="bizView3231(\''
						+ row['APPROVAL_NUM'] + '\');">' + row['APPROVAL_NUM']
						+ '</a>';
				row['STATUS_CN'] = row['STATUS'] == '1' ? "已移交" : "未移交";

			});
			csmxfe = getCsmxfe(transferId);
			debugger;
			if (("2" == csmxfe.transferType)
					&& (csmxfe.status == '00' || csmxfe.status == '90')) {
				$("#actionDiv").css("display", "block");
				if (csmxfe.processId) {
					nui.get("approveBtn").hide();
				} else {
					nui.get("approveBtn").show();
				}
			}
			query();
		}

		function addDetail() {
			nui.open({
				url : nui.context
						+ "/csm/transfer/csmxfe_approve_select.jsp?transferId="
						+ transferId + "&userNum=" + csmxfe.oldUserId
						+ "&orgNum=" + csmxfe.oldOrgId,
				showMaxButton : true,
				title : "选择移交业务",
				width : 1000,
				height : 600,
				ondestroy : function(action) {
					query();
				}
			});
		}

		function removeDetail() {
			actionDiv.loading("数据处理中...");
			var rows = grid.getSelecteds();
			if (rows.length == 0) {
				alert("请选择数据");
				return;
			}
			var params = [];
			for (var i = 0; i < rows.length; i++) {
				params[i] = {
					"approveId" : rows[i].APPROVE_ID
				};
			}
			var json = nui.encode({
				"transferId" : transferId,
				"params" : params
			});
			$.ajax({
				url : "com.bos.csm.transfer.transfer.removeDetail.biz.ext",
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
			grid.load({
				"sqlName" : "com.bos.csm.transfer.transfer.getApproveInfo",
				"item" : {
					"transferId" : transferId
				}
			});
		}
	</script>
</body>
</html>

