<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.pub.entity.name.AssetsTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
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
	<div id="form" class="nui-fit">
		<input id="writeOff.id" name="writeOff.id" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>抵债资产编号：</label>
			<input id="writeOff.debtsNum" name="writeOff.debtsNum" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:40" />
			<label>抵债资产类型：</label>
			<input id="writeOff.debtsType" name="writeOff.debtsType" class="nui-dictcombobox" required="true" dictTypeId="XD_ASSET004" enabled="<%=enable%>" />
			<label>抵债资产名称：</label>
			<input id="writeOff.debtsName" name="writeOff.debtsName" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:80" />
			<label>原资产编号：</label>
			<input id="writeOff.oldDebtsNum" name="writeOff.oldDebtsNum" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:40" />
			<label>收取前资产状态：</label>
			<input id="writeOff.debtsStatus" name="writeOff.debtsStatus" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:8" />
			<label>抵债资产费用合计：</label>
			<input id="writeOff.debtsFees" name="writeOff.debtsFees" class="nui-textbox" dataType="currency" required="true" enabled="<%=enable%>" vtype="maxLength:17" />
			<label>资金往来账户账号：</label>
			<div>
				<input id="writeOff.acctNo" name="writeOff.acctNo" class="nui-textbox" required="true" style="width: 180px" enabled="<%=enable%>" vtype="maxLength:40" />
				<a class="nui-button" iconCls="icon-zoomin" enabled="<%=enable%>" onclick="searchAccNo">验证</a>
			</div>
			<label>资金往来账户名称：</label>
			<input id="writeOff.acctName" name="writeOff.acctName" class="nui-textbox" enabled="false" vtype="minLength:2;maxLength:80" required="true" />
			<label>资金往来账户机构号：</label>
			<input id="writeOff.acctOrg" name="writeOff.acctOrg" class="nui-textbox" enabled="false" vtype="maxLength:30" required="true" />
			<label>入账价值：</label>
			<input id="writeOff.inAmt" name="writeOff.inAmt" class="nui-textbox" dataType="currency" required="true" enabled="<%=enable%>" vtype="maxLength:17" />
			<label>抵债金额：</label>
			<input id="writeOff.debtsAmt" name="writeOff.debtsAmt" class="nui-textbox" dataType="currency" required="true" enabled="<%=enable%>" vtype="maxLength:17" />
			<label>公允价值：</label>
			<input id="writeOff.fairValue" name="writeOff.fairValue" class="nui-textbox" dataType="currency" required="true" enabled="<%=enable%>" vtype="maxLength:17" />
			<label>抵债资产收取方式：</label>
			<input id="writeOff.debtsGetType" name="writeOff.debtsGetType" class="nui-textbox" required="true" enabled="<%=enable%>" vtype="maxLength:35" />
			<label>当前操作人工号：</label>
			<input class="nui-text" value="<%=GitUtil.getCurrentUserId()%>" />
			<label>当前操作人机构：</label>
			<input class="nui-text" dictTypeId="org" value="<%=GitUtil.getCurrentOrgCd()%>" />
		</div>
		<div id="formLoan">
			<input name="item._entity" id="item._entity" class="nui-hidden" />
			<input name="item.id" id="item.id" class="nui-hidden" />
			<div id="loanActionDiv" class="nui-toolbar" style="text-align: left;">
				<a id="loanAdd" class="nui-button" iconCls="icon-add" enabled="<%=enable%>" onclick="loan_add()">增加</a>
				<a id="loanRemove" class="nui-button" iconCls="icon-remove" enabled="<%=enable%>" onclick="loan_remove()">删除</a>
				<a id="loanWriteOff" class="nui-button" iconCls="icon-action" onclick="actionWriteOffLoan()">冲销</a>
				<label id="msg_label" style="color: red;"></label>
			</div>
		</div>
		<div class="nui-fit">
			<div id="gridLoan" class="nui-datagrid" style="height: 100%" url="com.bos.csm.pub.getGeneralityInfo.getItem.biz.ext" dataField="items" allowAlternating="true" allowCellEdit="true" allowCellSelect="true" multiSelect="true" sortMode="client" oncellvalidation="offAmtValid">
				<div property="columns">
					<div type="checkcolumn" style="width: 45px">选择</div>
					<div field="contractNum" headerAlign="center" allowSort="true">合同编号</div>
					<div field="summaryNum" headerAlign="center" allowSort="true">借据编号</div>
					<div field="summaryAmt" headerAlign="center" align="right" allowSort="true" dataType="currency">借据金额</div>
					<div field="summaryBal" headerAlign="center" align="right" allowSort="true" dataType="currency">借据余额</div>
					<div field="repaySort" headerAlign="center" allowSort="true" dictTypeId="XD_ASSET005">还款顺序</div>
					<div field="offAmt" headerAlign="center" align="right" enabled="true" dataType="currency" vtype="required;maxLength:17;">
						冲销金额
						<input property="editor" class="nui-textbox" />
					</div>
					<div field="status" headerAlign="center" allowSort="true" dictTypeId="XD_ASSET012">状态</div>
				</div>
			</div>
		</div>
	</div>
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
					.getStrHaveSign(AssetsTableName.TB_ASSET_WRITE_OFF_LOAN)%>
		;
		var planId =
	<%=JspUtil.getParameterHaveSign(request, "planId")%>
		;
		var partyId;
		initPage();
		function initPage() {
			var json = nui.encode({
				"planId" : planId
			});
			$.ajax({
				url : "com.bos.asset.AssetsWriteOff.getWriteOff.biz.ext",
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(data) {
					data.datas = data.datas[0];
					if (data.datas.STATUS != "30") {
						$("#loanWriteOff").css("display", "none");
					}
					partyId = data.datas.PARTY_ID;
					form.setData(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				}
			});
			//-----------------------------------
			refurbishLoan();
		}

		function offAmtValid(e) {
			if (e.field == "offAmt") {
				if (e.isValid) {
					if (e.value == "" || e.value == 0) {
						e.errorText = "请录入冲销金额";
						e.isValid = false;
					} else if (e.value > e.row.summaryBal) {
						e.errorText = "冲销金额不能大于借据余额";
						e.isValid = false;
					}
				}
				$("#msg_label").html(e.isValid ? "" : e.errorText);
			}
		}

		function refurbishLoan() {
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
			gridLoan.commitEdit();
			gridLoan.validate();
			if (gridLoan.isValid() == false) {
				var error = gridLoan.getCellErrors()[0];
				gridLoan.beginEditCell(error.record, error.column);
				return;
			}
			debugger;
			nui.get("save_button").setEnabled(false);
			var json = nui.encode({
				writeOff : form.getData().writeOff,
				writeOffLoan : gridLoan.getChanges()
			});
			$.ajax({
				url : "com.bos.asset.AssetsWriteOff.saveWriteOff.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg); //失败时后台直接返回出错信息
					} else {
						nui.alert(actionSuccess);
						initPage();
					}
					nui.get("save_button").setEnabled(true);
				}
			});
		}

		function loan_add() {
			var url = nui.context
					+ "/asset/handle/selectLoan.jsp?planId="
					+ planId
					+ "&partyId="
					+ partyId
					+ "&createUrl=com.bos.asset.AssetsWriteOff.createWriteOffLoan.biz.ext";
			nui.open({
				url : url,
				title : "借据选择",
				width : "85%",
				height : "90%",
				ondestroy : function(action) {
					refurbishLoan();
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
					summaryId : rows[i].summaryId
				};
			}
			json = {
				"datas" : json,
				"planId" : planId
			};
			json = nui.encode(json);
			$
					.ajax({
						url : "com.bos.asset.AssetsWriteOff.removeWriteOffLoan.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								nui.alert(text.msg); //失败时后台直接返回出错信息
							} else {
								nui.alert(actionSuccess);
								refurbishLoan();
							}
							nui.get("save_button").setEnabled(true);
						}
					});
		}
		function actionWriteOffLoan() {
			var rows = gridLoan.getSelecteds();
			if (!rows || rows.length < 1) {
				alert("请选择一条记录！");
				return;
			}
			var json = new Array();
			for (var i = 0; i < rows.length; i++) {
				json[i] = {
					summaryId : rows[i].summaryId
				};
			}
			json = {
				"datas" : json,
				"planId" : planId
			};
			json = nui.encode(json);
			$
					.ajax({
						url : "com.bos.asset.AssetsWriteOff.actionWriteOffLoan.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(text) {
							//debugger;
							if (text.msgs) {
								var temp = "";
								var i = 0;
								while (text.msgs[i]) {
									temp += text.msgs[i++] + "<br/>";
								}
								nui.alert(temp);
							} else {
								nui.alert(actionSuccess);
							}
							refurbishLoan();
							nui.get("save_button").setEnabled(true);
						}
					});
		}
		function searchAccNo() {
			nui.get("writeOff.acctName").setValue(null);
			nui.get("writeOff.acctOrg").setValue(null);
			var temp = nui.get("writeOff.acctNo").getValue();
			if (!temp || temp == "") {
				return;
			}
			nui.get("writeOff.acctNo").setValue(null);
			var subNo = temp.substring(4, 5);
			if (subNo == '8' || subNo == '9') {
				queryAcc2(temp);
			} else {
				queryAcc1(temp);
			}
		}

		function queryAcc2(acctInd) {
			var json = nui.encode({
				"acctInd" : acctInd
			});
			$
					.ajax({
						url : "com.bos.accInfo.accInfo.queryAcc2.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(data) {
							debugger;
							if (data.code !=
	<%=JspUtil.getStrHaveSign(DictContents.CORE_SUCCESS)%>
		) {
								nui.alert(data.msg);
							} else {
								var accInfo = data.oXD15AccountInfo.oxd015ResBody.fXD151[0];
								if (!accInfo) {
									alert("获取内部账户信息失败");
									return;
								}
								nui.get("writeOff.acctNo").setValue(
										accInfo.acctNo);
								nui.get("writeOff.acctName").setValue(
										accInfo.acctChnName);
								nui.get("writeOff.acctOrg").setValue(
										accInfo.businessBrch);
							}
						}
					});
		}
		function queryAcc1(acctInd) {
			var json = nui.encode({
				"acctInd" : acctInd
			});
			$
					.ajax({
						url : "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(data) {
							debugger;
							if (data.code !=
	<%=JspUtil.getStrHaveSign(DictContents.CORE_SUCCESS)%>
		) {
								nui.alert(data.msg);
							} else {
								nui
										.get("writeOff.acctNo")
										.setValue(
												data.hxresponse.oxd052ResBody.custAcctNo);
								nui.get("writeOff.acctName").setValue(
										data.hxresponse.oxd052ResBody.custName);
								nui.get("writeOff.acctOrg").setValue(
										data.hxresponse.oxd052ResBody.brchNo);
							}
						}
					});
		}
	</script>
</body>
</html>