<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): WangHui
  - Date: 2017-05-08 17:15:08
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="处置方案">
			<div id="form1" class="nui-form">
				<input name="sqlName" class="nui-hidden" value="com.bos.asset.handle.HandleSql.handleLog" />
				<div class="nui-dynpanel" columns="6">
					<label>机构名称：</label>
					<input name="item.orgNum" id="item.orgNum" class="nui-buttonEdit" dictTypeId="org" onbuttonclick="selectEmpOrg" />
					<label>经办人：</label>
					<input id="item.userNum" name="item.userNum" class="nui-buttonEdit" dictTypeId="user" onbuttonclick="selectCustManegers" />
					<label>计划编号：</label>
					<input name="item.planNum" required="false" class="nui-textbox" />
					<label>计划名称：</label>
					<input id="item.planName" name="item.certType" class="nui-textbox" />
					<label>处置方式：</label>
					<input id="item.planType" class="nui-dictcombobox" dictTypeId="XD_ASSET001" allowInput="false" name="item.planType" />
					<label>清收方式：</label>
					<input id="item.cleanTakeType" class="nui-dictcombobox" dictTypeId="XD_ASSET003" allowInput="false" name="item.cleanTakeType" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="query">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
				</div>
			</div>
			<div>
				<a class="nui-button" iconCls="icon-search" onclick="showInfo">显示</a>
				<a class="nui-button" iconCls="icon-search" onclick="deleteClean">删除清收数据</a>
			</div>
			<div class="nui-fit">
				<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="false" sortMode="client">
					<div property="columns">
						<div type="checkcolumn" width="40px">选择</div>
						<div field="PLAN_NUM" allowSort="true" headerAlign="center" width="10%">计划编号</div>
						<div field="PLAN_NAME" allowSort="true" headerAlign="center" width="10%">计划名称</div>
						<div field="PLAN_TYPE" allowSort="true" headerAlign="center" dictTypeId="XD_ASSET001">处置方式</div>
						<div field="CLEAN_TAKE_TYPE" allowSort="true" headerAlign="center" dictTypeId="XD_ASSET003">清收方式</div>
						<div field="REG_USER_ID" allowSort="true" headerAlign="center" dictTypeId="user">申请人</div>
						<div field="REG_ORG_ID" allowSort="true" headerAlign="center" dictTypeId="org">申请机构</div>
						<div field="REG_DATE" allowSort="true" headerAlign="center">申请时间</div>
						<div field="STATUS" allowSort="true" headerAlign="center" dictTypeId="XD_ASSET002">当前状态</div>
						<div field="UPDATE_USER_ID" allowSort="true" headerAlign="center" dictTypeId="user">最后修改人</div>
						<div field="UPDATE_DATE" allowSort="true" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">最后修改时间</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	nui.parse();

	var form = new nui.Form("#form1");
	var gridLoan = nui.get("gridLoan");

	window.onload = function() {
		query();
	}

	function query() {
		gridLoan.load(form.getData());
	}

	function reset() {
		form.reset();
		query();
	}
	function deleteClean() {
		var row = gridLoan.getSelected();
		if (row && row.CLEAN_TAKE_TYPE && row.PLAN_TYPE == "10") {
		} else {
			nui.alert("只有清收数据能删除");
			return;
		}
		if (nui.confirm("是否确认删除该选中数据？", "询问")) {
			debugger;
			$.ajax({
				url : "com.bos.asset.AssetsCleanTake.removeCleanTake.biz.ext",
				type : 'POST',
				data : nui.encode({
					"cleanTakeId" : row.ID
				}),
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg); //失败时后台直接返回出错信息
					} else {
						query();
						nui.alert(actionSuccess);
					}
					nui.get("save_button").setEnabled(true);
				}
			});
		}
	}

	function showInfo() {
		var row = gridLoan.getSelected();
		//debugger;
		nui
				.open({
					url : nui.context + "/asset/handle/handle_tree.jsp?bizId="
							+ row.ID,
					showMaxButton : true,
					title : "处置方案申报",
					width : 1024,
					height : 768,
					state : "max",
					onload : function(e) {
					},
					ondestroy : function(action) {
						if (action == "ok") {
							query();
						}
					}
				});
	}

	function selectEmpOrg(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
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
				} else if (action == "clear") {
					btnEdit.setText(null);
					btnEdit.setValue(null);
				}
			}
		});
	}
	// 经办人
	function selectCustManegers(e) {
		var newOrgNum = nui.get("item.orgNum").getValue();
		if (newOrgNum == '') {
			newOrgNum = null;
		}
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/user/select_user.jsp?"
					+ (newOrgNum ? ("orgNum=" + newOrgNum) : ""),
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
				} else if (action == "clear") {
					btnEdit.setText(null);
					btnEdit.setValue(null);
				}
			}
		});
	}
</script>
</html>