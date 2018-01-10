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
				<div field="SUBCONTRACT_TYPE" headerAlign="center" allowSort="true" width="60" dictTypeId="XD_YWDB0131">担保类型</div>
				<div field="SUBCONTRACT_NUM_URL" headerAlign="center" allowSort="true" width="150">担保编号</div>
				<div field="SUBCONTRACT_AMT" headerAlign="center" align="right" allowSort="true" width="100">担保金额</div>
				<div field="BZ" headerAlign="center" allowSort="true" width="60" dictTypeId="CD000001">币种</div>
				<div field="BEGIN_DATE" headerAlign="center" allowSort="true" width="80" dateFormat="yyyy-MM-dd">起期</div>
				<div field="END_DATE" headerAlign="center" allowSort="true" width="80" dateFormat="yyyy-MM-dd">止期</div>
				<div field="IF_TOP_SUBCON" headerAlign="center" allowSort="true" width="50" dictTypeId="XD_0002">最高额</div>
				<div field="SUBCONTRACT_STATUS" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_SXCD8003">担保状态</div>
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
									e.data[i]['SUBCONTRACT_NUM_URL'] = '<a href="#" onclick="bizView3231(\''
											+ e.data[i]['SUBCONTRACT_NUM']
											+ '\');">'
											+ e.data[i]['SUBCONTRACT_NUM']
											+ '</a>';
								}
							});
			csmXfe = getCsmxfe(transferId);
			query();
		}

		function query() {
			debugger;
			if (transferId != null) {
				grid
						.load({
							"sqlName" : "com.bos.csm.transfer.transfer.getSubcontractInfo",
							"item" : {
								"transferId" : transferId
							}
						});
			}
		}
	</script>
</body>
</html>

