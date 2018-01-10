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
	<%
		boolean enabled = !UserUtil.isManager();
	%>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="处置方案">
			<div id="form1" class="nui-form">
				<input id="sqlName" name="sqlName" class="nui-hidden" />
				<div class="nui-dynpanel" columns="6">
					<label>机构名称：</label>
					<input name="item.orgcode" id="item.orgcode" class="nui-buttonEdit" dictTypeId="org" value="<%=GitUtil.getCurrentOrgCd()%>" enabled="<%=enabled%>" onbuttonclick="selectEmpOrg" />
					<label>经办人：</label>
					<input id="item.userNum" name="item.userNum" class="nui-buttonEdit" dictTypeId="user" value="<%=GitUtil.getCurrentUserId()%>" enabled="<%=enabled%>" onbuttonclick="selectCustManegers" />
					<label>客户名称：</label>
					<input name="item.partyName" required="false" class="nui-textbox" />
					<label>证件类型：</label>
					<input id="item.certType" name="item.certType" class="nui-dictcombobox" dictTypeId="CDKH0002" allowInput="false" />
					<label>证件号码：</label>
					<input id="item.certNum" class="nui-textbox" name="item.certNum" />
					<label>合同编号：</label>
					<input id="item.contractNum" class="nui-textbox" name="item.contractNum" />
					<label id="summaryNum_label">借据编号：</label>
					<input id="item.summaryNum" class="nui-textbox" name="item.summaryNum" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<div id="form2" class="nui-form" style="border: none; padding-top: 6px">
				<div class="nui-toolbar" style="text-align: left;">
					<label>处置方式：</label>
					<input id="planType" name="planType" class="nui-dictcombobox" onvaluechanged="changeSelected" required="true" dictTypeId="XD_ASSET001" style="width: 140px" />
					<input id="cleanTakeType" name="cleanTakeType" class="nui-dictcombobox" onvaluechanged="changeSelected" required="true" dictTypeId="XD_ASSET003" style="width: 140px" />
					<a class="nui-button" iconCls="icon-add" onclick="create()">创建</a>
				</div>
			</div>
			<div class="nui-fit">
				<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="false" sortMode="client">
					<div property="columns">
						<div type="checkcolumn" width="45px">选择</div>
						<div field="ORG_NUM" allowSort="true" headerAlign="center" width="10%" dictTypeId="org">机构名称</div>
						<div field="PARTY_NAME" allowSort="true" headerAlign="center" width="10%">客户名称</div>
						<div field="PRODUCT_TYPE" allowSort="true" headerAlign="center" dictTypeId="product">业务品种</div>
						<div field="CONTRACT_NUM" allowSort="true" headerAlign="center" width="120px">合同编号</div>
						<div field="SUMMARY_NUM" allowSort="true" headerAlign="center" width="120px">借据编号</div>
						<div field="SUMMARY_AMT" allowSort="true" headerAlign="center" align="right" dataType="currency">借据金额</div>
						<div field="JJYE" allowSort="true" headerAlign="center" align="right" dataType="currency">借据余额</div>
						<div field="NORMAL_ITR" allowSort="true" headerAlign="center" align="right" dataType="currency">正常利息</div>
						<div field="ARREAR_ITR" allowSort="true" headerAlign="center" align="right" dataType="currency">欠息</div>
						<div field="PUNISH_ITR" allowSort="true" headerAlign="center" align="right" dataType="currency">罚息</div>
						<div field="BEGIN_DATE" allowSort="true" headerAlign="center">借据起期</div>
						<div field="END_DATE" allowSort="true" headerAlign="center">借据止期</div>
						<div field="USER_NUM" allowSort="true" headerAlign="center" dictTypeId="user">经办人</div>
					</div>
				</div>
				<div id="gridCon" class="nui-datagrid" style="height: 100%;" url="com.bos.pub.dao.search.biz.ext" dataField="items" multiSelect="false" sortMode="client">
					<div property="columns">
						<div type="checkcolumn" style="width: 45px">选择</div>
						<div field="CONTRACT_NUM" headerAlign="center" allowSort="true">合同编号</div>
						<div field="PRODUCT_TYPE" headerAlign="center" dictTypeId="product" allowSort="true">贷款品种</div>
						<div field="PARTY_NAME" headerAlign="center" allowSort="true">客户名称</div>
						<div field="CURRENCY_CD" headerAlign="center" dictTypeId="CD000001" allowSort="true">币种</div>
						<div field="CONTRACT_AMT" headerAlign="center" align="right" allowSort="true" dataType="currency">合同金额</div>
						<div field="CON_BALANCE" headerAlign="center" align="right" allowSort="true" dataType="currency">合同已用金额</div>
						<div field="BEGIN_DATE" headerAlign="center" pattern="yyyy-MM-dd" allowSort="true">起始日</div>
						<div field="END_DATE" headerAlign="center" pattern="yyyy-MM-dd" allowSort="true">到期日</div>
						<div field="YQTS" headerAlign="center" allowSort="true">逾期天数</div>
						<div field="JJYQBJ" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">逾期本金</div>
						<div field="NORMAL_ITR" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">正常利息</div>
						<div field="ARREAR_ITR" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">拖欠利息</div>
						<div field="PUNISH_ITR" headerAlign="center" align="right" enabled="true" allowSort="true" dataType="currency">罚息</div>
						<div field="CLS_RESULT" headerAlign="center" dictTypeId="XD_FLCD0001" allowSort="true">风险分类</div>
						<div field="ORG_NUM" headerAlign="center" dictTypeId="org" allowSort="true">经办机构</div>
						<div field="USER_NUM" headerAlign="center" dictTypeId="user" allowSort="true">经办人</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	nui.parse();

	var form = new nui.Form("#form1");
	var form2 = new nui.Form("#form2");
	var gridLoan = nui.get("gridLoan");
	var gridCon = nui.get("gridCon");

	var grids = [ gridLoan, gridCon ];

	for (var i = 0; i < grids.length; i++) {
		grids[i].on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				if (e.data[i]) {
					setA_partyName(e.data[i]);
					setA_contractNum(e.data[i]);
					setA_summaryNum(e.data[i]);
				}
			}
		});
	}
	changeSelected();
	function query() {
		var data2 = form2.getData();
		if (data2.planType == "") {
			alert("请选择处置方式");
			return;
		} else if (data2.planType == "10" && data2.cleanTakeType == "") {
			alert("请选择清收方式");
			return;
		}
		var grid = getGrid();
		var o = form.getData();
		o.sqlName = grid.sqlName;
		o.item.searchMode = grid.searchMode;
		debugger;
		grid.load(o);
	}

	function reset() {
		form.reset();
		query();
	}

	function getGrid(clean) {
		debugger;
		var cleanTakeType;
		var planType = nui.get("planType").getValue();
		var searchMode = planType;
		if ("10" == planType) {
			cleanTakeType = nui.get("cleanTakeType").getValue();
			searchMode = searchMode + "_" + cleanTakeType;
		}
		var grid;
		if (planType == "20") {
			$("#gridCon").show();
			$("#gridLoan").hide();
			gridCon.sqlName = "com.bos.asset.handle.HandleSql.handleListByCon";
			grid = gridCon;
		} else {
			$("#gridLoan").show();
			$("#gridCon").hide();
			gridLoan.sqlName = "com.bos.asset.handle.HandleSql.handleListByLoan";
			grid = gridLoan;
		}
		grid.searchMode = searchMode;
		if (clean) {
			grid.setData(null);
		}
		return grid;
	}

	function create() {
		form2.validate();
		if (form2.isValid() == false) {
			nui.alert("请按规则填写信息");
			return;
		}

		var cleanTakeType;
		var planType = nui.get("planType").getValue();
		if ("10" == planType) {
			cleanTakeType = nui.get("cleanTakeType").getValue();
		}

		var json;
		var row = getGrid().getSelected();
		if ("20" == cleanTakeType || "30" == cleanTakeType) {
			json = nui.encode({
				"planType" : planType,
				"cleanTakeType" : cleanTakeType
			});
		} else if (!row || row.length == 0) { //没有选中项，表单不提交。
			alert("请选择一条记录！");
			return;
		} else {
			json = nui.encode({
				"planType" : planType,
				"cleanTakeType" : cleanTakeType,
				"partyId" : row.PARTY_ID,
				"loanId" : row.LOAN_ID,
				"summaryId" : row.SUMMARY_ID
			});
		}
		git.mask();
		$.ajax({
			url : "com.bos.asset.AssetsHandle.createHandle.biz.ext",
			type : 'POST',
			data : json,
			contentType : 'text/json',
			cache : false,
			async : false,
			success : function(data) {
				//debugger;
				if (data.msg) {
					nui.alert(data.msg);
				} else {
					nui.open({
						url : nui.context
								+ "/asset/handle/handle_tree.jsp?bizId="
								+ data.handleId + "&wflow="
								+ (data.processId ? "2" : "1")
								+ "&processInstId=" + data.processId,
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
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
			}
		});
		git.unmask();
	}

	function changeSelected() {
		var planType = nui.get("planType").getValue();
		var cleanTakeType = nui.get("cleanTakeType").getValue();
		if ("10" == planType) {
			nui.get("cleanTakeType").show();
		} else {
			nui.get("cleanTakeType").hide();
			nui.get("cleanTakeType").setValue("");
		}
		if ("20" == planType) {
			$("#summaryNum_label").hide();
			nui.get("item.summaryNum").hide();
		} else {
			$("#summaryNum_label").show();
			nui.get("item.summaryNum").show();
		}
		if (cleanTakeType || (planType && planType != "10")) {
			query();
		} else {
			getGrid(true);
		}
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
		var newOrgNum = nui.get("item.orgcode").getValue();
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

	function setA_partyName(obj) {
		debugger;
		//客户链接
		obj['PARTY_NAME'] = '<a href="javascript:void(0);" onclick="clickCust('
				+ getValueStr(obj.PARTY_ID) + ','
				+ getValueStr(obj.CORP_CUSTOMER_TYPE_CD) + ','
				+ getValueStr(obj.PARTY_TYPE_CD) + ');return false;">'
				+ obj['PARTY_NAME'] + '</a>';
	}

	function setA_contractNum(obj) {
		//合同链接
		obj['CONTRACT_NUM'] = '<a href="javascript:void(0);" onclick="clickContractNum('
				+ getValueStr(obj['CONTRACT_ID'])
				+ ');return false;">'
				+ obj['CONTRACT_NUM'] + '</a>';
	}

	function setA_summaryNum(obj) {
		//借据链接
		obj['SUMMARY_NUM'] = '<a href="javascript:void(0);" onclick="clickSummaryNum('
				+ getValueStr(obj['LOAN_ID'])
				+ ');return false;">'
				+ obj['SUMMARY_NUM'] + '</a>';
	}

	function clickCust(partyId, corpCustomerTypeCd, partyTypeCd) {
		var openUrl;
		if (partyTypeCd == "01") {
			openUrl = nui.context
					+ "/csm/corporation/csm_corporation_tree.jsp?partyId="
					+ partyId + "&cusType=" + corpCustomerTypeCd + "&qote=1";
		} else {
			openUrl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
					+ partyId + "&qote=1";
		}
		open(openUrl);
	}

	function clickContractNum(contractId) {
		var openUrl = nui.context + "/crt/con_info/con_tree.jsp?contractId="
				+ contractId + "&contractType=02&proFlag=-1";
		open(openUrl);
	}

	function clickSummaryNum(loanId) {
		var openUrl = nui.context + "/pay/payout_info/pay_tree.jsp?loanId="
				+ loanId + "&processInstId=0&proFlag=-1";
		open(openUrl);
	}

	function getValueStr(value) {
		return '\'' + value + '\'';
	}

	function open(openUrl) {
		nui.open({
			url : openUrl,
			showMaxButton : true,
			title : "",
			width : 1024,
			height : 768,
			state : "max"
		});
	}
</script>
</html>