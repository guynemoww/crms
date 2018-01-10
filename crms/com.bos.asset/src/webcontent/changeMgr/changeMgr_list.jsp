<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): WangHui
  - Date: 2017-05-08 17:15:08
  - Description:
-->
<head>
<title>不良资产管户权变更</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		boolean enabled = !UserUtil.isManager();
	%>

	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="不良资产管户权变更">
			<div id="form1" class="nui-form">
				<input name="sqlName" class="nui-hidden" value="com.bos.asset.changeMgr.ChangeMgrSql.changeMgrList" />
				<div class="nui-dynpanel" columns="6">
					<label>机构名称：</label>
					<input name="item.orgNum" id="item.orgNum" class="nui-buttonEdit" dictTypeId="org" value="<%=GitUtil.getCurrentOrgCd()%>" enabled="<%=enabled%>" onbuttonclick="selectEmpOrg" />
					<label>经办人：</label>
					<input id="item.userNum" name="item.userNum" class="nui-buttonEdit" dictTypeId="user" value="<%=GitUtil.getCurrentUserId()%>" enabled="<%=enabled%>" onbuttonclick="selectCustManegers" />
					<label>客户名称：</label>
					<input name="item.partyName" class="nui-textbox" />
					<label>现管户人：</label>
					<input name="item.tasUserName" class="nui-textbox" />
					<label>现管户机构：</label>
					<input name="item.tasOrgNum" class="nui-buttonEdit" value="<%=GitUtil.getCurrentOrgCd()%>" dictTypeId="org" onbuttonclick="selectEmpOrg" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<div class="nui-toolbar" style="border-bottom: none; text-align: left; margin-top: 11px">
				<a class="nui-button" iconCls="icon-add" onclick="move">发起变更</a>
			</div>
			<div class="nui-fit">
				<div id="datagrid1" class="nui-datagrid" style="width: 100%; height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="false" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="CONTRACT_NUM" allowSort="true" headerAlign="center">合同编号</div>
						<div field="PARTY_NAME" allowSort="true" headerAlign="center">客户名称</div>
						<div field="PARTY_TYPE_CD" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD1001">客户类型</div>
						<div field="CERT_TYPE" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="CERT_NUM" allowSort="true" headerAlign="center">证件号</div>
						<div field="TAS_DATE" allowSort="true" headerAlign="center">移交时间</div>
						<div field="TAS_ORG_NUM" allowSort="true" headerAlign="center" dictTypeId="org">管户机构</div>
						<div field="TAS_USER_NUM" allowSort="true" headerAlign="center">管户人</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");

	function queryInit() {
		//校验
		var data = validateForm(form, "请输入必填项");
		if (data) {
			grid.load(data);
		}
	}
	queryInit();

	function reset() {
		form.reset();
		queryInit();
	}

	function move() {
		var row = grid.getSelected();
		if (row <= 0) {
			alert("至少选择一条记录");
			return;
		}
		var json = {
			"transferId" : row.TRANSFER_ID
		};
		nui.ajax({
			url : "com.bos.asset.AssetsChangeMgr.createChangeMgr.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			async : false,
			contentType : 'text/json',
			success : function(data) {
				if (data.msg) {
					alert(data.msg);
					return;
				} else {
					showEdit(data);
				}
			}
		});

	}
	function showEdit(data) {
		nui.open({
			url : nui.context + "/asset/changeMgr/changeMgr_tree.jsp?bizId="
					+ data.changeMgrId + "&wflow=2&processInstId="
					+ data.processId,
			title : "资产管户变更",
			width : 1024,
			height : 768,
			showMaxButton : true
		});
	}

	function selectEmpOrg(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp?clear=0",
			showMaxButton : true,
			title : "选择机构",
			width : 450,
			height : 650,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					btnEdit.setText(null);
					btnEdit.setValue(data.orgcode);
				}
			}
		});
	}
	// 经办人
	function selectCustManegers(e) {
		var newOrgNum = nui.get("item.orgNum").getValue();
		if (!newOrgNum || newOrgNum == "") {
			alert("请先选择机构");
			return;
		} else {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/user/select_user.jsp?orgNum="
						+ newOrgNum + "&clear=0",
				showMaxButton : true,
				title : "选择客户经理",
				width : 800,
				height : 550,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						btnEdit.setText(null);
						btnEdit.setValue(data.userNum);
					}
				}
			});
		}
	}
</script>
</html>