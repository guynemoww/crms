<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-26 11:05:35
  - Description:
-->
<head>
<title>限额</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		String edit = request.getParameter("edit");
		boolean enabled = !"2".equals(edit);
		boolean editByGroup = "3".equals(edit);
		boolean create = "0".equals(edit) || editByGroup;
		String limitId = request.getParameter("limitId");
		if (limitId != null) {
			limitId = "\"" + limitId + "\"";
		}
	%>
	<div id="form">
		<input id="limit.limitCode" name="limit.limitCode" class="nui-hidden" />
		<input id="limit.limitId" name="limit.limitId" class="nui-hidden" />
		<%
			if (editByGroup) {
		%>
		<input id="limit.limitId" name="limit.limitId" class="nui-hidden" value="0" />
		<%
			}
		%>
		<table style="width: 100%">
			<tr>
				<td style="width: 20%; text-align: right;">
					<label>限额类别：</label>
				</td>
				<td style="width: 30%">
					<input id="limit.limitType" class="nui-dictcombobox nui-form-input" name="limit.limitType" allowInput="false" required="true" enabled="<%=enabled%>" dictTypeId="XD_SXYW0238" onvaluechanged="selectLimitType" />
				</td>
				<td style="width: 20%; text-align: right;">
					<label>限额内容：</label>
				</td>
				<td style="width: 30%">
					<input id="limit.limitCodeText" class="nui-buttonEdit nui-form-input" name="limit.limitCodeText" allowInput="false" required="true" enabled="<%=enabled%>" onbuttonclick="selectLimitCode" />
					<input id="limit.limitCodeText2" class="nui-dictcombobox nui-form-input" name="limit.limitCodeText2" allowInput="false" required="true" enabled="<%=enabled%>" dictTypeId="CDZC0005" />
				</td>
			</tr>
			<%
				if (!editByGroup) {
			%>
			<tr>
				<td style="width: 20%; text-align: right;">
					<label>分配额度：</label>
				</td>
				<td>
					<input id="limit.limitAmt" class="nui-textbox nui-form-input" name="limit.limitAmt" required="true" enabled="<%=enabled%>" vtype="float;range:0,10000000000000" dataType="currency" />
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			if (enabled) {
		%>
		<div class="nui-toolbar" style="text-align: right; padding-top: 15px; padding-right: 25px;" borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-edit" onclick="refurbishText" enabled="<%=enabled%>">刷新内容</a>
			<a class="nui-button" id="biz_enter_info_save" iconCls="icon-save" onclick="save" enabled="<%=enabled%>">保存</a>
		</div>
		<div style="color: blue;">注意:当在他处修改限额内容信息导致此处显示错误，请点击刷新按钮同步数据</div>
		<%
			}
		%>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var limitId =
	<%=create ? null : limitId%>
		;//限额ID
		if (limitId == "null") {
			limitId = null;
		}
		initPage();
		//初始化页面
		function initPage() {
			var json = nui.encode({
				"limitId" : limitId
			});
			$.ajax({
				url : "com.bos.crd.LimitService.getRiskLimit.biz.ext",
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				cache : false,
				success : function(mydata) {
					var o = nui.decode(mydata);
					o.limit.limitCodeText2 = o.limit.limitCode;
					debugger;
					form.setData(o);
					debugger;
					if (o.limit.limitType != "30") {
						nui.get("limit.limitCodeText").setText(
								o.limit.limitCodeText);
					}
				}
			});
		}

		function refurbishText() {
			var data = form.getData();
			if (data.limit.limitType && data.limit.limitCode) {
				var text = crd.getLimitCodeText(data.limit.limitType,
						data.limit.limitCode);
				debugger;
				if (data.limit.limitType == "30") {
					nui.get("limit.limitCodeText2").setValue(text);
					nui.get("limit.limitCodeText2").setText(text);
				} else {
					nui.get("limit.limitCodeText").setValue(text);
					nui.get("limit.limitCodeText").setText(text);
				}
			}
		}
		var init = true;
		function selectLimitType(e) {
			if (e && e.value == "30") {
				nui.get("limit.limitCodeText").hide();
				nui.get("limit.limitCodeText2").show();
			} else {
				nui.get("limit.limitCodeText").show();
				nui.get("limit.limitCodeText2").hide();
			}
			if (init) {
				init = false;
			} else {
				nui.get("limit.limitCode").setValue(null);
				nui.get("limit.limitCodeText").setValue(null);
				nui.get("limit.limitCodeText").setText(null);
				nui.get("limit.limitCodeText2").setValue(null);
				nui.get("limit.limitCodeText2").setText(null);
			}
		}

		function selectLimitCode() {
			var type = nui.get("limit.limitType").getValue();
			if (null == type || "" == type) {
				alert("请先选择限额类别");
				return;
			}
			if (type == "10") {
				selectEmpOrg();
			} else if (type == "20") {
				selectProduct();
			} else if (type == "90") {
				selectTrade();
			}
		}

		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请按规则填写信息");
				return;
			}
			nui.get("biz_enter_info_save").setEnabled(false);
			var o = form.getData();
			if (o.limit.limitType == "30") {
				o.limit.limitCode = o.limit.limitCodeText2;
				o.limit.limitCodeText = nui.get("limit.limitCodeText2")
						.getText();
			}
			debugger;
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.crd.LimitService.saveRiskLimit.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						showMsg(text.msg);
						nui.get("biz_enter_info_save").setEnabled(true);
						return;
					}
					alert("保存成功！");
					CloseWindow("ok");
					nui.get("biz_enter_info_save").setEnabled(true);
				}
			});
		}

		//机构
		function selectEmpOrg() {
			var btnEdit = nui.get("limit.limitCodeText");
			nui.open({
				url : nui.context + "/pub/sys/select_org_tree.jsp",
				showMaxButton : true,
				title : "选择机构",
				width : 800,
				height : 500,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.GetData();
						data = nui.clone(data);
						if (data) {
							nui.get("limit.limitCode").setValue(data.orgcode);
							btnEdit.setValue(data.orgname);
							btnEdit.setText(data.orgname);
						}
					} 
				}
			});
		}
		//产品树
		function selectProduct() {
			var btnEdit = nui.get("limit.limitCodeText");
			nui
					.open({
						url : nui.context
								+ "/pub/product/product/select_product_tree.jsp?searchMode=random&selectEvery=1",
						title : "选择",
						width : 800,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.currentNode;
								data = nui.clone(data);
								if (data) {
									nui.get("limit.limitCode").setValue(
											data.productCd);
									btnEdit.setValue(data.productName);
									btnEdit.setText(data.productName);
								}
							} 
						}
					});
		}
		//行业
		function selectTrade() {
			var btnEdit = nui.get("limit.limitCodeText");
			nui
					.open({
						url : nui.context
								+ "/pub/dict/code_tree.jsp?dicttypeid=CDXY0300&selectEvery=1",
						title : "选择字典项",
						width : 800,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.currentNode;
								data = nui.clone(data);
								if (data) {
									nui.get("limit.limitCode").setValue(
											data.dictid);
									btnEdit.setValue(data.dictname);
									btnEdit.setText(data.dictname);
								}
							}
						}
					});
		}

		function showMsg(msg) {
			debugger;
			if (msg.substr(0, 1) == "{" && msg.substr(msg.length - 1, 1) == "}") {
				var error = nui.decode(msg);
				if (error.limitCode) {
					nui.alert(error.msg
							+ "<br/>限额内容:"
							+ crd.getLimitCodeText(error.limitType,
									error.limitCode) + "    分配额度:"
							+ error.limitAmt);
				} else {
					nui.alert(error.msg);
				}
			} else {
				nui.alert(msg);
			}
		}
	</script>
</body>
</html>