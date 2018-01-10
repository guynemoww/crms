<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.entity.name.CsmTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<head>
<%@include file="/common/nui/common.jsp"%>
<%@include file="/csm/transfer/csmxfe_common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form">
		<div class="nui-dynpanel" columns="8">
			<label class="nui-form-label">原机构名称：</label>
			<input id="item.oldOrgId" name="item.oldOrgId" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg(this)" dictTypeId="org" />
			<label>原客户经理：</label>
			<input id="item.oldUserId" name="item.oldUserId" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectUser('item.originalOrgNum',this)" dictTypeId="user" />
			<label class="nui-form-label">新机构名称：</label>
			<input id="item.newOrgId" name="item.newOrgId" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg(this)" />
			<label>新客户经理：</label>
			<input id="item.newUserId" name="item.newUserId" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectUser('item.orgId',this)" />
			<input id="item.userId" name="item.userId" class="nui-hidden" />
			<input id="item.orgId" name="item.orgId" class="nui-hidden" />
		</div>
		<div class="nui-toolbar" style="text-align: right; border: none">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>

	<div class="nui-toolbar" style="border-top: 1px solid #8ba0bc; border-bottom: none; margin-top: 5px">
		<!-- <input id="transferTypeCd" name="transferTypeCd" class="nui-dictcombobox" dictTypeId="XD_CSMXFE001" style="width: 150px" /> -->
		<a class="nui-button" iconCls="icon-add" onclick="create()">创建跨机构业务移交申请</a>
	</div>
	<div class=nui-fit>
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="true" sortMode="client">
			<div property="columns">
				<!-- <div field="transferType" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_CSMXFE001">移交类型</div> -->
				<div field="oldOrgId" headerAlign="center" allowSort="true" width="200" dictTypeId="org">原机构名称</div>
				<div field="oldUserId" headerAlign="center" allowSort="true" width="80" dictTypeId="user">原客户经理</div>
				<div field="newOrgId" headerAlign="center" allowSort="true" width="200" dictTypeId="org">新机构名称</div>
				<div field="newUserId" headerAlign="center" allowSort="true" width="80" dictTypeId="user">新客户经理</div>
				<div field="xfeBiz" headerAlign="center" allowSort="true" width="50" dictTypeId="XD_0002">移交业务</div>
				<div field="xfeAcct" headerAlign="center" allowSort="true" width="50" dictTypeId="XD_0002">移交账务</div>
				<div field="newLoanOrg" headerAlign="center" allowSort="true" width="200" dictTypeId="acctOrg">新会计机构</div>
				<div field="keepCsm" headerAlign="center" allowSort="true" width="50" dictTypeId="XD_0002">保留客户共享权</div>
				<div field="status" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_CSMXFE002">状态</div>
				<div field="createTime" headerAlign="center" allowSort="true" width="100" dateFormat="yyyy-MM-dd">经办时间</div>
				<div field="userId" headerAlign="center" allowSort="true" width="80" dictTypeId="user">经办人</div>
				<div field="action" headerAlign="center" width="60">操作</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("grid1");

		initPage();
		function initPage() {
			var userNum =
	<%=UserUtil.isManager() ? JspUtil.getStrHaveSign(GitUtil
					.getCurrentUserId()) : null%>
		;
			var orgNum =
	<%=UserUtil.isManager() ? JspUtil.getStrHaveSign(GitUtil
					.getCurrentOrgCd()) : null%>
		;
			nui.get("item.userId").setValue(userNum);
			nui.get("item.orgId").setValue(orgNum);
			grid.on("preload", function(e) {
				if (!e.data || e.data.length < 1) {
					return;
				}
				for (var i = 0; i < e.data.length; i++) {
					e.data[i]['action'] = '<a href="#" onclick="showInfo(\''
							+ e.data[i].transferId + "','"
							+ e.data[i].processId + '\');">查看</a>';
				}
			});
		}
		query();
		function query() {
			var o = form.getData();
			o.sqlName = "com.bos.csm.transfer.transfer.searchCsmxfe";
			o.item.transferType = "2";
			grid.load(o);
		}
		function create() {
			var isManager =
	<%=!UserUtil.isAdmin()%>
		;
			createCsmxfe("2", isManager);
		}

		function reset() {
			if (nui.get("roleType").getValue() == "2") {
				nui.get("item.orgNum").setValue(null);
				nui.get("item.userNum").setValue(null);
			}
			nui.get("item.partyName").setValue(null);
			nui.get("item.partyTypeCd").setValue(null);
			nui.get("item.certType").setValue(null);
			nui.get("item.certNum").setValue(null);
			nui.get("item.middleCode").setValue(null);
		}
	</script>
</body>
</html>
