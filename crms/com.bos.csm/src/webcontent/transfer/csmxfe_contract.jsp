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
				<div field="CONTRACT_NUM_URL" headerAlign="center" allowSort="true" width="100">合同编号</div>
				<div field="CONTRACT_AMT" headerAlign="center" align="right" allowSort="true" width="100">合同金额</div>
				<div field="CURRENCY_CD" headerAlign="center" allowSort="true" width="60" dictTypeId="CD000001">币种</div>
				<div field="CONTRACT_TERM" headerAlign="center" allowSort="true" align="right" width="50">期限</div>
				<div field="CYCLE_UNIT" headerAlign="center" allowSort="true" width="60" dictTypeId="XD_GGCD6009">期限单位</div>
				<div field="BEGIN_DATE" headerAlign="center" allowSort="true" width="80" dateFormat="yyyy-MM-dd">起期</div>
				<div field="END_DATE" headerAlign="center" allowSort="true" width="80" dateFormat="yyyy-MM-dd">止期</div>
				<div field="CON_STATUS" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_SXCD8003">合同状态</div>
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
									e.data[i]['CONTRACT_NUM_URL'] = '<a href="#" onclick="bizView3231(\''
											+ e.data[i]['CONTRACT_NUM']
											+ '\');">'
											+ e.data[i]['CONTRACT_NUM']
											+ '</a>';
								}
							});
			query();
		}

		function add() {
			var orgId = csmXfe.originalOrgNum;
			nui.open({
				url : nui.context
						+ "/pub/user/select_user.jsp?clear=0&orgMode=legorg"
						+ (orgId != null ? ("&orgNum=" + orgId) : ""),
				showMaxButton : true,
				title : "选择导入客户经理",
				width : 850,
				height : 450,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						data = nui.clone(data);
						if (data) {
							createDetail(data);
						}
					}
				}
			});
		}

		function query() {
			debugger;
			if (!transferId) {
				return;
			}
			grid.load({
				"sqlName" : "com.bos.csm.transfer.transfer.getContractInfo",
				"item" : {
					"transferId" : transferId
				}
			});
		}
	</script>
</body>
</html>

