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
<title>不良资产逆移交</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		boolean enabled = !UserUtil.isManager();
	%>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="不良资产逆移交">
			<div id="form1" class="nui-form" style="overflow: hidden;">
				<input name="sqlName" class="nui-hidden" value="com.bos.asset.retransfer.RetransferSql.retransferList" />
				<div class="nui-dynpanel" columns="6">
					<label>机构名称：</label>
					<input name="item.orgNum" id="item.orgNum" class="nui-buttonEdit" dictTypeId="org" value="<%=GitUtil.getCurrentOrgCd()%>" enabled="<%=enabled%>" onbuttonclick="selectEmpOrg" />
					<label>经办人：</label>
					<input id="item.userNum" name="item.userNum" class="nui-buttonEdit" dictTypeId="user" value="<%=GitUtil.getCurrentUserId()%>" enabled="<%=enabled%>" onbuttonclick="selectCustManegers" />
					<label>客户名称：</label>
					<input name="item.partyName" class="nui-textbox" />
					<label>证件类型：</label>
					<input id="item.certType" name="item.certType" class="nui-dictcombobox" dictTypeId="CDKH0002" allowInput="false" />
					<label>证件号码：</label>
					<input id="item.certNum" name="item.certNum" class="nui-textbox" />
					<label>合同编号：</label>
					<input id="item.contractNum" name="item.contractNum" class="nui-textbox" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<div class="nui-toolbar" style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
				<a class="nui-button" iconCls="icon-add" onclick="move()">逆移交</a>
			</div>
			<div class="nui-fit">
				<div id="datagrid1" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="false" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="TRANSFER_NUM" allowSort="true" width="155" headerAlign="center">移交编号</div>
						<div field="CONTRACT_NUM" allowSort="true" width="110" headerAlign="center">合同编号</div>
						<div field="PARTY_NAME" allowSort="true" headerAlign="center">客户名称</div>
						<div field="CERT_TYPE" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="CERT_NUM" allowSort="true" headerAlign="center">证件号码</div>
						<div field="CON_ORG_NUM" allowSort="true" headerAlign="center" dictTypeId="org">合同机构</div>
						<div field="CON_USER_NUM" allowSort="true" headerAlign="center" dictTypeId="user">合同经理</div>
						<div field="TAS_DATE" allowSort="true" headerAlign="center">移交时间</div>
						<div field="TAS_ORG_NUM" allowSort="true" headerAlign="center" dictTypeId="org">移交机构</div>
						<div field="TAS_USER_NUM" allowSort="true" headerAlign="center" dictTypeId="user">移交经理</div>
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
		var o = validateForm(form);//逻辑流必须返回total
		if (o) {
			grid.load(o);
		}
	}
	queryInit();

	function reset() {
		form.reset();
		queryInit();
	}

	function move() {
		var row = grid.getSelected();
		if (!row || row <= 0) {
			alert("至少选择一条记录");
			return;
		}
		var json = {
			"transferId" : row.TRANSFER_ID
		};
		nui.ajax({
			url : "com.bos.asset.AssetsRetransfer.createRetransfer.biz.ext",
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
			url : nui.context + "/asset/retransfer/retransfer_tree.jsp?bizId="
					+ data.retransferId + "&wflow=2&processInstId="
					+ data.processId,
			showMaxButton : true,
			title : "不良资产逆移交",
			width : 1024,
			height : 768
		});
	}

	grid.on("preload", function(e) {
		if (!e.data || e.data.length < 1) {
			return;
		}
		for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
			e.data[i]['partyName'] = '<a href="#" onclick="toGoCustDetail(\''
					+ e.data[i].partyId + '\');">' + e.data[i]['partyName']
					+ '</a>';
			e.data[i]['contractNum'] = '<a href="#" onclick="bizView3231(\''
					+ e.data[i].contractNum + '\');">'
					+ e.data[i]['contractNum'] + '</a>';
		}
	});

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