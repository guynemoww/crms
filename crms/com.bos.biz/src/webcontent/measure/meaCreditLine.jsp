<%@page import="com.bos.pub.StringUtil"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-07-04 13:30:36
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		String bizId = request.getParameter("bizId");
		String[] names = {"value", "onblur", "style", "enabled","vtype", "onvalidation",
		"dataType"};
		String[][][] tables = {
		{{"b1", "折扣系数<span class='baxcolor'>*</span>", "1", null},
		{null, null, null, "true",null, "onNumberValidation", null}},//
		
		{{"b2","目标杠杆系数×信用等级调节系数<span class='baxcolor'>*</span>","1", null},
		{null, null, null, "false",null, null, null}},//
		
		{{"b3", "目标杠杆系数", "1", null},
		{null, "computeB2", null, "true", null,"onNumberValidation", null}},
		
		{{"b4", "信用等级调节系数", "1", null},
		{null, "computeB2", null, "false", null, "onNumberValidation", null}},
		
		{{"b5", "企业评定期资产负债率", "1", null},
		{null, "computeB6", null, "false", null,"onNumberValidation", null}},
		
		{{"b6","资产负债率/（1-资产负债率)<span class='baxcolor'>*</span>","1", null},
		{null, null, null, "false", null, "onNumberValidation", null}},
		
		{{"b7", "企业所有者权益抵减其他因素后计算值(元)", "1", null},
		{null, null, null, "false", null, "onNumberValidation", "bigdecimal"}},
		
		{{"b8", "企业评定期所有者权益(元)", "1", null},
		{null, "computeB7", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b9", "其中：抵减损耗(元)", "1", null},
		{null, "computeB7", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b10", "&emsp;&emsp;&emsp;企业潜亏(元)", "1", null},
		{null, "computeB7", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b11", "&emsp;&emsp;&emsp;无形资产(元)", "1", "不含土地使用权"},
		{null, "computeB7", null, "false", null, "onNumberValidation","bigdecimal"}},
		
		{{"b11x", "&emsp;&emsp;&emsp;财报无形资产(元)", "1", null},
		{null, null, null, "false", null, "onNumberValidation", "bigdecimal"}},
		
		{{"b26", "&emsp;&emsp;&emsp;土地使用权(元)", "1", null},
		{null, "computeB11", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b12", "&emsp;&emsp;&emsp;少数股东权益(元)", "1", null},
		{null, "computeB7", null, "false", null, "onNumberValidation","bigdecimal"}},
		
		{{"b13", "新增评定授信额(元)", "1", null},
		{null, null, null, "false", null, "onNumberValidation", "bigdecimal"}},
		
		{{"b14", "附加项目：",null, null},
		{null, null, null, "false", null, "onNumberValidation", null}},
		
		{{"b15", "他行授信额(元)", "1", null},
		{null, "computeB1", null, "false", null, "onNumberValidation","bigdecimal"}},
		
		{{"b16", "其中：短期贷款(元)", "1", null},
		{null, "computeB15", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b17", "&emsp;&emsp;&emsp;中长期贷款(元)", "1", null},
		{null, "computeB15", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b18", "&emsp;&emsp;&emsp;贴现(元)", "1", null},
		{null, "computeB15", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b19", "&emsp;&emsp;&emsp;签发承兑汇票(元)", "1", null},
		{null, "computeB15", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b20", "&emsp;&emsp;&emsp;对外担保额(元)", "1", "至少按征信查询情况填写"},
		{null, null, null, "true", null, "onNumberValidation", "bigdecimal"}},
		
		{{"b21", "我行现有用信额(元)", "1", null},
		{null, null, null, "false", null,"onNumberValidation", "bigdecimal"}},
		
		{{"b22", "其中：贷款授信额(元)", "1", null},
		{null, "computeB21", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b23", "&emsp;&emsp;&emsp;表外授信额(元)", "1", null},
		{null, "computeB21", null, "true", null, "onNumberValidation","bigdecimal"}},
		
		{{"b24", "最高综合授信额(元)", "1", null},
		{null, null, null, "false", null, "onNumberValidation", "bigdecimal"}},
		
		{{"b25", "我行最高综合授信额(元)", "1", null},
		{null, null, null, "false", null, "onNumberValidation", "bigdecimal"}}};
	%>
	<div id="form" class="nui-form" style="margin: 0 auto; width: 95%;">
		<div class="nui-dynpanel" columns="4">
			<label>测算人：</label>
			<input id="measure.userNum" name="measure.userNum" class="nui-buttonedit" enabled="false" dictTypeId="user" />
			<label>测算机构：</label>
			<input id="measure.orgNum" name="measure.orgNum" class="nui-buttonedit" enabled="false" dictTypeId="org" />
			<label>测算时间：</label>
			<input id="measure.meaDate" name="measure.meaDate" class="nui-datepicker" enabled="false" />
		</div>
		<table style="width: 100%; border: 1px solid;">
			<thead>
				<tr>
					<td colspan="4" style="text-align: center;">
						<label>绵阳市商业银行法人客户授信测算表</label>
					</td>
				</tr>
				<tr>
					<td>
						<label>项 目</label>
					</td>
					<td>
						<label>测 算 值</label>
					</td>
					<td>
						<label>备 注</label>
					</td>
				</tr>
			</thead>
			<tbody>
				<%
					for (String[][] row:tables) {																					
										String id = row[0][0];
				%>
				<tr>
					<td style="width: 250px;">
						<label><%=row[0][1]%>
						</label>
					</td>
					<td style="width: 200px">
						<input class="nui-textbox" id="measure.<%=id%>" name="measure.<%=id%>" required="true" <%for (int j = 0; j < names.length; j++) {
					if (StringUtil.isNotNull(row[1][j])) {
						out.print(" " + names[j] + "=" + JspUtil.getStrHaveSign(row[1][j]));
					}
				}%> />
					</td>
					<td>
						<label id="<%=id%>_remark"><%=row[0][3] == null ? "" : row[0][3]%></label>
					</td>
					<td>
						<label id="<%=id%>_msg" style="color: red;"></label>
						<input id="<%=id%>_multiplier" class="nui-hidden" value="<%=row[0][2]%>" />
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<input id="limitMap.rebate" name="limitMap.rebate" class="nui-hidden " />
		<input id="limitMap.rebate_2" name="limitMap.rebate_2" class="nui-hidden " />
		<input id="measure.id" name="measure.id" class="nui-hidden " />
		<div class="nui-toolbar" style="text-align: right; padding-top: 15px; padding-right: 25px;" borderStyle="border:0;">
			<a class="nui-button" id="mea_remove" iconCls="icon-remove" onclick="meaRemove">重新测算</a>
			<span style="display: inline-block; width: 50px;"></span>
			<a class="nui-button" id="mea" iconCls="icon-edit" onclick="mea">授信测算</a>
			<a class="nui-button" id="mea_save" iconCls="icon-save" onclick="meaSave">保存</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var measureId =
	<%=JspUtil.getParameterHaveSign(request, "applyId")%>
		;
		initPage();
		function initPage() {

			var json = nui.encode({
				"measureId" : measureId,
				"searchLimitMap" : "searchLimitMap"
			});

			$
					.ajax({
						url : "com.bos.biz.Measure.getCreditLineMea.biz.ext",
						type : 'POST',
						data : json,
						contentType : 'text/json',
						cache : false,
						async : false,
						success : function(data) {
							if (!data.measure || !data.measure.id) {
								meaAdd();
							}
							if (!data.measure.b11) {
								data.measure.b11 = data.measure.b11x
										- data.measure.b26;
							}
							debugger;
							form.setData(data);
							nui.get("mea_add").setEnabled(false);
						}
					});

		}
		function meaSave() {
			var data = validateForm(form);
			if (!data) {
				return;
			}
			$.ajax({
				url : "com.bos.biz.Measure.saveCreditLineMea.biz.ext",
				type : 'POST',
				data : nui.encode(data),
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(data) {
					if (data.msg) {
						nui.alert(data.msg);
						return;
					} else {
						nui.alert("操作成功");
					}
				}
			});
			CloseWindow("ok");
		}

		function meaAdd() {
			var json = {
				"applyId" : measureId,
				"codes" : [ {
					"customerTypeCd" : "002",
					"b5" : "ZCFZL",
					"b8" : "00201050",
					"b11x" : "00201051",
					"b26" : "00204047",
					"b12" : "00201064"
				} ]
			};
			$.ajax({
				url : "com.bos.biz.Measure.createCreditLineMea.biz.ext",
				type : 'POST',
				data : nui.encode(json),
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(data) {
					if (data.msg) {
						nui.alert(data.msg);
						return;
					}
					initPage();
				}
			});
		}
		function meaRemove() {
			var json = {
				"applyId" : measureId
			};
			$.ajax({
				url : "com.bos.biz.Measure.removeCreditLineMea.biz.ext",
				type : 'POST',
				data : nui.encode(json),
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(data) {
					if (data.msg) {
						nui.alert(data.msg);
						return;
					}
					meaAdd();
				}
			});
		}
		function getData() {
			return nui.clone(form.getData());
		}

		function mea() {
			computeB6();
			computeB13();
			computeB24();
			computeB25();
		}
		function computeB1() {
			var data = form.getData();
			var b15Value = getValue("b15");
			debugger;
			nui
					.get("measure.b1")
					.setValue(
							!b15Value || b15Value == "" || b15Value == 0 ? data.limitMap.rebate
									: data.limitMap.rebate_2);

		}
		function computeB2() {
			var b3Value = getValue("b3");
			var b4Value = getValue("b4");
			debugger;
			if (b3Value != null && b4Value != null) {
				var b2Value = b3Value * b4Value;
				nui.get("measure.b2").setValue(b2Value);
			}
		}

		function computeB6() {
			var value = getValue("b5");
			if (value != null) {
				var value = value / (1 - (value == 1 ? 0 : value));
				nui.get("measure.b6").setValue(value.toFixed(2));
			}
		}
		function computeB7() {
			var b8Value = getValue("b8");
			var b9Value = getValue("b9");
			var b10Value = getValue("b10");
			var b11Value = getValue("b11");
			var b12Value = getValue("b12");
			debugger;
			if (b8Value != null && b9Value != null && b10Value != null
					&& b11Value != null && b12Value != null) {
				var b7Value = b8Value - b9Value - b10Value - b11Value
						- b12Value;
				nui.get("measure.b7").setValue(b7Value);
			}
		}
		function computeB11() {
			var b11xValue = getValue("b11x");
			var b12Value = getValue("b12");
			if (b11xValue != null && b12Value != null) {
				var b11Value = b11xValue - b12Value;
				nui.get("measure.b11").setValue(b11Value);
			}
		}
		function computeB13() {
			var b1Value = getValue("b1");
			var b2Value = getValue("b2");
			var b6Value = getValue("b6");
			var b7Value = getValue("b7");
			debugger;
			if (b1Value != null && b2Value != null && b6Value != null
					&& b7Value != null) {
				var b13Value = b1Value * (b2Value - b6Value) * b7Value;
				nui.get("measure.b13").setValue(b13Value.toFixed(2));
			}
		}

		function computeB15() {
			var b16Value = getValue("b16");
			var b17Value = getValue("b17");
			var b18Value = getValue("b18");
			var b19Value = getValue("b19");
			debugger;
			if (b16Value != null && b17Value != null && b18Value != null
					&& b19Value != null) {
				var b15Value = b16Value + b17Value + b18Value + b19Value;
				var multiplier = $("#b15_multiplier").val();
				if (multiplier && multiplier != 1) {
					b15Value = b15Value / (multiplier == 0 ? 1 : multiplier);
				}
				nui.get("measure.b15").setValue(b15Value);
			}
		}
		function computeB21() {
			var b22Value = getValue("b22");
			var b23Value = getValue("b23");
			debugger;
			if (b22Value != null && b23Value != null) {
				var b21Value = b22Value + b23Value;
				nui.get("measure.b21").setValue(b21Value);
			}
		}
		function computeB24() {
			var b13Value = getValue("b13");
			var b15Value = getValue("b15");
			var b20Value = getValue("b20");
			var b21Value = getValue("b21");
			debugger;
			if (b13Value != null && b15Value != null && b20Value != null
					&& b21Value != null) {
				var b24Value = b13Value + b15Value - b20Value + b21Value;
				nui.get("measure.b24").setValue(b24Value);
			}
		}
		function computeB25() {
			var b13Value = getValue("b13");
			var b20Value = getValue("b20");
			var b21Value = getValue("b21");
			debugger;
			if (b13Value != null && b20Value != null && b21Value != null) {
				var b25Value = b13Value + b21Value - b20Value;
				nui.get("measure.b25").setValue(b25Value);
			}
		}

		function getValue(id) {
			var value = nui.get("measure." + id).getValue();
			if (!value || value == "") {
				$("#" + id + "_msg").html("请录入正确数据");
				return;
			} else {
				$("#" + id + "_msg").html(null);
				var multiplier = $("#" + id + "_multiplier").val();
				if (multiplier && multiplier != 1) {
					value = value * multiplier;
				}
				return Number(value);
			}
		}

		function onNumberValidation(e) {
			debugger;
			if (e.isValid) {
				if (isNumber(e.value) == false) {
					e.errorText = "必须输入数字";
					e.isValid = false;
				}
			}
		}

		function isNumber(v) {
			var re = new RegExp("^[-]?\\d*\\.?[\\d]*$");
			if (re.test(v))
				return true;
			return false;
		}

		function getData() {
			return form.getData();
		}
	</script>
</body>
</html>