<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="com.bos.pub.entity.name.AssetsTableName"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	<div id="form" class="nui-form" style="border: none;">
		<input id="cleanLaw.id" name="cleanLaw.id" class="nui-hidden" />
		<fieldset>
			<legend>
				<span>清收信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>诉讼处置方式：</label>
				<input id="cleanLaw.actionType" name="cleanLaw.actionType" class="nui-dictcombobox" required="true" dictTypeId="XD_ASSET008" enabled="<%=enable%>" />
				<label>代理处置方式：</label>
				<input id="cleanLaw.proxyActionType" name="cleanLaw.proxyActionType" class="nui-dictcombobox" required="true" dictTypeId="XD_ASSET009" onvaluechanged="changeProxyActionType" enabled="<%=enable%>" />
				<label id="actionPeople_label">本行负责人/代理人：</label>
				<input id="cleanLaw.actionPeople" name="cleanLaw.actionPeople" class="nui-buttonEdit" allowInput="false" dictTypeId="user" required="true" onbuttonclick="selectUser" enabled="<%=enable%>" />
				<label id="proxyLawOfficeName_label">代理律师事务所名称：</label>
				<input id="cleanLaw.proxyLawOfficeName" name="cleanLaw.proxyLawOfficeName" class="nui-textbox" enabled="<%=enable%>" vtype="maxLength:80" />
				<label id="lawyerName_label">律师姓名：</label>
				<input id="cleanLaw.lawyerName" name="cleanLaw.lawyerName" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:40" />
				<label id="lawyerContact_label">律师联系方式：</label>
				<input id="cleanLaw.lawyerContact" name="cleanLaw.lawyerContact" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:25" />
				<label id="lawyerMoney_label">律师费金额：</label>
				<input id="cleanLaw.lawyerMoney" name="cleanLaw.lawyerMoney" class="nui-textbox" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>币种：</label>
				<input id="cleanLaw.currencyCd" name="cleanLaw.currencyCd" class="nui-dictcombobox" required="true" dictTypeId="CD000001" enabled="<%=enable%>" />
				<label>诉讼本金金额：</label>
				<input id="cleanLaw.lawAmt" name="cleanLaw.lawAmt" class="nui-textbox" enabled="false" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>诉讼利息金额：</label>
				<input id="cleanLaw.lawBal" name="cleanLaw.lawBal" class="nui-textbox" enabled="false" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>诉讼费金额：</label>
				<input id="cleanLaw.lawMoney" name="cleanLaw.lawMoney" class="nui-textbox" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>其他费用金额：</label>
				<input id="cleanLaw.otherMoney" name="cleanLaw.otherMoney" class="nui-textbox" dataType="currency" enabled="<%=enable%>" vtype="maxLength:17" />
				<label>诉讼日期：</label>
				<input id="cleanLaw.lawDate" name="cleanLaw.lawDate" class="nui-datepicker" required="true" enabled="<%=enable%>" />
				<label>审理机构：</label>
				<input id="cleanLaw.hearOrg" name="cleanLaw.hearOrg" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:80" />
				<label>处置进度：</label>
				<input id="cleanLaw.lawProgress" name="cleanLaw.lawProgress" class="nui-dictcombobox" required="true" dictTypeId="XD_ASSET010" enabled="<%=enable%>" />
			</div>
		</fieldset>
	</div>
	<fieldset class="nui-fit">
		<legend>
			<span>合同列表信息</span>
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
					.getStrHaveSign(AssetsTableName.TB_ASSET_CLEAN_TAKE_LAW_CON)%>
		;
		var planId =
	<%=JspUtil.getParameterHaveSign(request, "planId")%>
		;

		initPage();

		function initPage() {
			var json = nui.encode({
				"planId" : planId
			});
			$.ajax({
				url : "com.bos.asset.AssetsCleanTake.getCleanTakeLaw.biz.ext",
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(mydata) {
					debugger;
					if (!mydata.cleanLaw.currencyCd) {
						mydata.cleanLaw.currencyCd = "CNY";
					}
					var o = nui.decode(mydata);
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
				cleanTakLaw : form.getData().cleanLaw
			});
			debugger;
			$.ajax({
				url : "com.bos.asset.AssetsCleanTake.saveCleanTakeLaw.biz.ext",
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
			//debugger;
			nui
					.open({
						url : nui.context
								+ "/asset/handle/selectCon.jsp?planId="
								+ planId
								+ "&createUrl=com.bos.asset.AssetsCleanTake.createCleanTakeLawCon.biz.ext",
						title : "借据选择",
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
						url : "com.bos.asset.AssetsCleanTake.removeCleanTakeLawCon.biz.ext",
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

		function changeProxyActionType() {
			var temp = nui.get("cleanLaw.proxyActionType").getValue();
			if (temp) {
				if ("20" == temp || "30" == temp) {
					show("lawyerName");
					show("lawyerContact");
					show("proxyLawOfficeName");
					show("lawyerMoney");

					hide("actionPeople");
				} else {
					show("actionPeople");

					hide("lawyerName");
					hide("lawyerContact");
					hide("proxyLawOfficeName");
					hide("lawyerMoney");
				}
			}
		}

		function show(name) {
			$("#" + name + "_label").show();
			nui.get("cleanLaw." + name).show();
		}

		function hide(name) {
			$("#" + name + "_label").hide();
			nui.get("cleanLaw." + name).setValue("");
			nui.get("cleanLaw." + name).hide();
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
	</script>
</body>
</html>