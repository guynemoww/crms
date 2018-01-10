<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): WangHui
  - Date: 2017-05-08 17:15:08
  - Description:
-->
<head>
<title>不良资产移交</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		boolean enabled = !UserUtil.isManager();
	%>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="不良资产移交">
			<div id="form1" class="nui-form" style="height: auto;">
				<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.asset.transfer.TransferSql.transferList" />
				<div class="nui-dynpanel" columns="6">
					<label>机构名称：</label>
					<input name="item.orgNum" id="item.orgNum" class="nui-buttonEdit" dictTypeId="org" value="<%=GitUtil.getCurrentOrgCd()%>" enabled="<%=enabled%>" onbuttonclick="selectEmpOrg" />
					<label>经办人：</label>
					<input id="item.userNum" name="item.userNum" class="nui-buttonEdit" dictTypeId="user" value="<%=GitUtil.getCurrentUserId()%>" enabled="<%=enabled%>" onbuttonclick="selectCustManegers" />
					<label>客户名称：</label>
					<input name="item.partyName" id="item.partyName" required="false" class="nui-textbox nui-form-input" />
					<label>证件类型：</label>
					<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" allowInput="false" />
					<label>证件号码：</label>
					<input id="item.certNum" class="nui-textbox nui-form-input" name="item.certNum" />
					<label>合同编号：</label>
					<input id="item.contractNum" class="nui-textbox nui-form-input" name="item.contractNum" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<div class="nui-toolbar" style="margin-top: 7px">
				<a class="nui-button" iconCls="icon-add" onclick="move()">移交</a>
				<a class="nui-button" iconCls="icon-zoomin" onclick="showLog()">历史移交记录</a>
			</div>
			<div class="nui-fit">
				<div id="datagrid1" class="nui-datagrid" style="width: 100%; height: 100%;" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="false" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="orgNum" allowSort="true" headerAlign="center" dictTypeId="org">机构名称</div>
						<div field="contractNum" allowSort="true" headerAlign="center" width="120">合同编号</div>
						<div field="partyName" headerAlign="center" allowSort="true" width="180">客户名称</div>
						<div field="partyNum" headerAlign="center" allowSort="true" width="130">客户编号</div>
						<div field="certType" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="certNum" allowSort="true" headerAlign="center">证件号码</div>
						<div field="currencyCd" allowSort="true" headerAlign="center" dictTypeId="CD000001">币种</div>
						<div field="contractAmt" allowSort="true" headerAlign="center" align="right" dataType="currency" dataType="">合同金额</div>
						<div field="conYuE" allowSort="true" headerAlign="center" align="right" dataType="currency">合同已用金额</div>
						<div field="yqts" allowSort="true" headerAlign="center">逾期天数</div>
						<div field="fljg" allowSort="true" headerAlign="center" dictTypeId="XD_FLCD0001">风险分类</div>
						<div field="userNum" allowSort="true" headerAlign="center" dictTypeId="user">管户经理</div>
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
		grid.load(form.getData());
	}
	queryInit();

	function reset() {
		form.reset();
		queryInit();
	}

	//移交
	function move() {
		var row = grid.getSelected();
		if (!row) {
			alert("至少选择一条记录");
			return;
		}
		//判断是否有未结清的业务

		var json = nui.encode({
			"contractId" : row.contractId
		});
		$.ajax({
			url : "com.bos.asset.AssetsTransfer.createTransfer.biz.ext",
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
					showEdit(data)
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}

	function showEdit(data) {
		nui.open({
			url : nui.context + "/asset/transfer/transfer_tree.jsp?bizId="
					+ data.transferId + "&wflow=2&processInstId="
					+ data.processId,
			title : "资产移交",
			width : 1024,
			height : 768
		});
	}
	//移交记录
	function showLog() {
		nui.open({
			url : nui.context + "/asset/transfer/transfer_log.jsp",
			showMaxButton : true,
			title : "不良资产移交记录",
			width : 1024,
			height : 720
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