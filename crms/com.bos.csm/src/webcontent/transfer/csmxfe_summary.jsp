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
				<div field="PRODUCT_TYPE" headerAlign="center" allowSort="true" width="100" dictTypeId="product">业务品种</div>
				<div field="SUMMARY_NUM_URL" headerAlign="center" allowSort="true" width="100">借据编号</div>
				<div field="SUMMARY_AMT" headerAlign="center" align="right" allowSort="true" width="100">借据金额</div>
				<div field="SUMMARY_CURRENCY_CD" headerAlign="center" allowSort="true" width="60" dictTypeId="CD000001">币种</div>
				<div field="SUMMARY_TERM" headerAlign="center" allowSort="true" align="right" width="50">期限</div>
				<div field="CYCLE_UNIT" headerAlign="center" allowSort="true" width="60" dictTypeId="XD_GGCD6009">期限单位</div>
				<div field="BEGIN_DATE" headerAlign="center" allowSort="true" width="80" dateFormat="yyyy-MM-dd">起期</div>
				<div field="END_DATE" headerAlign="center" allowSort="true" width="80" dateFormat="yyyy-MM-dd">止期</div>
				<div field="SUMMARY_STATUS_CD" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_SXYW0226">借据状态</div>
				<div field="USER_NUM" headerAlign="center" allowSort="true" width="80" dictTypeId="user">经办人</div>
				<div field="ORG_NUM" headerAlign="center" allowSort="true" width="200" dictTypeId="org">经办机构</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
		var csmXfe = null;

		init();
		function init() {
			grid
					.on(
							"preload",
							function(e) {
								if (!e.data || e.data.length < 1) {
									return;
								}
								for (var i = 0; i < e.data.length; i++) {
									e.data[i]['SUMMARY_NUM_URL'] = '<a href="#" onclick="bizView3231(\''
											+ e.data[i]['SUMMARY_NUM']
											+ '\');">'
											+ e.data[i]['SUMMARY_NUM'] + '</a>';
								}
							});
			csmXfe = getCsmxfe(transferId);
			query();
		}

		function query() {
			debugger;
			if (!transferId) {
				return;
			}
			grid.load({
				"sqlName" : "com.bos.csm.transfer.transfer.getSummaryInfo",
				"item" : {
					"transferId" : transferId
				}
			});
		}
	</script>
</body>
</html>

