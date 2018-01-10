<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.pub.entity.name.AssetsTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-05-23 20:32:11
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		String enable = JspUtil.getParameter(request, "enable", "false");
	%>
	<div id="form">
		<input id="cleanEntrust.id" name="cleanEntrust.id" class="nui-hidden" />
		<fieldset>
			<legend>
				<span>委外清收信息</span>
			</legend>
			<div class="nui-dynpanel">
				<label>代理处置方式：</label>
				<input id="cleanEntrust.proxyActionType" name="cleanEntrust.proxyActionType" class="nui-dictcombobox" required="true" dictTypeId="XD_ASSET011" enabled="<%=enable%>" />
				<label>受托人名称：</label>
				<input id="cleanEntrust.entrustName" name="cleanEntrust.entrustName" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:40" />
				<label>受托人联系方式：</label>
				<input id="cleanEntrust.entrustContact" name="cleanEntrust.entrustContact" class="nui-textbox" enabled="<%=enable%>" vtype="maxLength:40" />
				<label>本行参与人：</label>
				<input id="cleanEntrust.actionPeople" name="cleanEntrust.actionPeople" class="nui-buttonEdit" allowInput="false" dictTypeId="user" required="true" onbuttonclick="selectUser" enabled="<%=enable%>" />
				<label>币种：</label>
				<input id="cleanEntrust.currencyCd" name="cleanEntrust.currencyCd" class="nui-dictcombobox" required="true" dictTypeId="CD000001" enabled="<%=enable%>" />
				<label>委托本金金额：</label>
				<input id="cleanEntrust.entrustAmt" name="cleanEntrust.entrustAmt" class="nui-textbox" required="true" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>委托利息金额：</label>
				<input id="cleanEntrust.entrustBal" name="cleanEntrust.entrustBal" class="nui-textbox" required="true" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>委托起始日期：</label>
				<input id="cleanEntrust.entrustBeginDate" name="cleanEntrust.entrustBeginDate" class="nui-datepicker" required="true" enabled="<%=enable%>" />
				<label>委托终止日期：</label>
				<input id="cleanEntrust.entrustEndDate" name="cleanEntrust.entrustEndDate" class="nui-datepicker" required="true" onvalidation="validDate" enabled="<%=enable%>" />
				<label>代理费金额：</label>
				<input id="cleanEntrust.proxyAmt" name="cleanEntrust.proxyAmt" class="nui-textbox" required="true" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>其他费用金额：</label>
				<input id="cleanEntrust.otherAmt" name="cleanEntrust.otherAmt" class="nui-textbox" required="true" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
			</div>
		</fieldset>
	</div>
	<fieldset class="nui-fit">
		<legend>
			<span>合同列表</span>
		</legend>
		<div id="formLoan">
			<input name="item._entity" id="item._entity" class="nui-hidden" />
			<input name="item.id" id="item.id" class="nui-hidden" />
			<div id="loanActionDiv" class="nui-toolbar" style="border-bottom: 0; text-align: left;">
				<a id="loanAdd" class="nui-button" iconCls="icon-add" enabled="<%=enable%>" onclick="loan_add()">增加</a>
				<a id="loanRemove" class="nui-button" iconCls="icon-remove" enabled="<%=enable%>" onclick="loan_remove()">删除</a>
			</div>
		</div>
		<div class="nui-fit">
			<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items" allowAlternating="true" multiSelect="true" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" style="width: 45px">选择</div>
					<div field="contractNum" headerAlign="center" allowSort="true">合同编号</div>
					<div field="conProductType" headerAlign="center" dictTypeId="product" allowSort="true">贷款品种</div>
					<div field="conCurrencyCd" headerAlign="center" dictTypeId="CD000001" allowSort="true">币种</div>
					<div field="contractAmt" headerAlign="right" allowSort="true" dataType="currency">合同金额</div>
					<div field="contractBal" headerAlign="right" allowSort="true" dataType="currency">合同已用金额</div>
					<div field="conBeginDate" headerAlign="center" allowSort="true">起始日</div>
					<div field="conEndDate" headerAlign="center" allowSort="true">到期日</div>
					<div field="conOverdueDays" headerAlign="center" allowSort="true">逾期天数</div>
					<div field="conOverdueCapital" headerAlign="right" enabled="true" allowSort="true" dataType="currency">逾期本金</div>
					<div field="conNormalItr" headerAlign="right" enabled="true" allowSort="true" dataType="currency">正常利息</div>
					<div field="conArrearItr" headerAlign="right" enabled="true" allowSort="true" dataType="currency">拖欠利息</div>
					<div field="conPunishItr" headerAlign="right" enabled="true" allowSort="true" dataType="currency">罚息</div>
					<div field="conFiveClassify" headerAlign="center" dictTypeId="XD_FLCD0001" allowSort="true">风险分类</div>
					<div field="conOrgNum" headerAlign="center" dictTypeId="org" allowSort="true">经办机构</div>
					<div field="conUserNum" headerAlign="center" dictTypeId="user" allowSort="true">经办人</div>
				</div>
			</div>
		</div>
	</fieldset>
	<div class="nui-toolbar" style="text-align: right; border: none;">
		<a class="nui-button" id="save_button" iconCls="icon-save" enabled="<%=enable%>" onclick="save">保存</a>
	</div>

	<script type="text/javascript">
		nui.parse();

		var form = new nui.Form("#form");
		var formLoan = new nui.Form("#formLoan");
		var gridLoan = nui.get("gridLoan");
		var entityName =
	<%=JspUtil
					.getStrHaveSign(AssetsTableName.TB_ASSET_CLEAN_TAKE_ENT_CON)%>
		;
		var planId =
	<%=JspUtil.getParameterHaveSign(request, "planId")%>
		;

		initPage();

		function initPage() {
			var json = nui.encode({
				"planId" : planId
			});
			$
					.ajax({
						url : "com.bos.asset.AssetsCleanTake.getCleanTakeEntrust.biz.ext",
						type : 'POST',
						data : json,
						contentType : 'text/json',
						cache : false,
						async : false,
						success : function(mydata) {
							debugger;
							var o = nui.decode(mydata);
							if (!mydata.cleanEntrust.currencyCd) {
								mydata.cleanEntrust.currencyCd = "CNY";
							}
							form.setData(o);
						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert(jqXHR.responseText);
						}
					});
			//-----------------------------------
			refurbishCon();

		}

		function refurbishCon() {
			nui.get("item._entity").setValue(entityName);
			nui.get("item.id").setValue(planId);
			gridLoan.load(formLoan.getData());
		}

		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请按规则填写信息");
				return;
			}

			nui.get("save_button").setEnabled(false);
			var data = form.getData();
			var json = nui.encode({
				cleanTakeEntrust : form.getData().cleanEntrust
			});
			debugger;
			$
					.ajax({
						url : "com.bos.asset.AssetsCleanTake.saveCleanTakeEntrust.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								nui.alert(text.msg); //失败时后台直接返回出错信息
							} else {
								nui.alert("操作成功");
								initPage();
							}
							nui.get("save_button").setEnabled(true);
						}
					});
		}

		function loan_add() {
			debugger;
			nui
					.open({
						url : nui.context
								+ "/asset/handle/selectCon.jsp?planId="
								+ planId
								+ "&createUrl=com.bos.asset.AssetsCleanTake.createCleanTakeEntCon.biz.ext",
						title : "合同选择",
						width : "85%",
						height : "90%",
						ondestroy : function(action) {
							refurbishCon();
						}
					});
		}
		function loan_remove() {
			var rows = gridLoan.getSelecteds();
			if (!rows || rows.length < 1) {
				alert("请选择一条记录！");
				return;
			}
			var json = new Array();
			for (var i = 0; i < rows.length; i++) {
				json[i] = {
					contractId : rows[i].contractId
				};
			}
			json = {
				"datas" : json,
				"planId" : planId
			};
			json = nui.encode(json);
			$
					.ajax({
						url : "com.bos.asset.AssetsCleanTake.removeCleanTakeEntCon.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								nui.alert(text.msg); //失败时后台直接返回出错信息
							} else {
								nui.alert("操作成功");
								refurbishCon();
							}
							nui.get("save_button").setEnabled(true);
						}
					});

		}
		function selectUser(e) {
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/user/select_user.jsp?orgNum="
								+
	<%=JspUtil.getStrHaveSign(GitUtil.getCurrentOrgCd())%>
		+ "&clear=0",
						title : "本行负责人/代理人选择",
						width : "60%",
						height : "90%",
						ondestroy : function(action) {
							if (actioin = "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.getData();
								btnEdit.setText(null);
								btnEdit.setValue(data.userNum);
							}
						}
					});
		}
		function validDate(e) {
			if (e.isValid) {
				var endDate = nui.get("cleanEntrust.entrustEndDate").getValue();
				var begDate = nui.get("cleanEntrust.entrustBeginDate")
						.getValue();
				if (!begDate || !endDate) {
					e.errorText = "起始日期与终止日期都要填写";
					e.isValid = false;
				} else if (endDate < begDate) {
					e.errorText = "起始日期不能小于终止日期";
					e.isValid = false;
				}
			}
		}
	</script>
</body>
</html>