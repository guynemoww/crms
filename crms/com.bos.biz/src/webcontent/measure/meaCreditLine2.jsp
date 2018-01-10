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
		String[][][] tables = {
				{{"b1", "折扣系数<span class='baxcolor'>*</span>"},
						{"", "", "", "true", "float", "1", ""}, {""}},//
				{{"b2", "目标杠杆系数×信用等级调节系数<span class='baxcolor'>*</span>"},
						{"", "", "", "false", "float", "1", ""}, {""}},//
				{{"b3", "目标杠杆系数"},
						{"", "computeB2", "", "true", "float", "1", ""},
						{""}},
				{{"b4", "信用等级调节系数"},
						{"", "computeB2", "", "false", "float", "1", ""},
						{""}},
				{{"b5", "企业评定期资产负债率"},
						{"", "computeB6", "", "true", "float", "1", ""},
						{""}},
				{{"b6", "资产负债率/（1-资产负债率)<span class='baxcolor'>*</span>"},
						{"", "", "", "true", "", "1", ""}, {""}},
				{{"b7", "企业所有者权益抵减其他因素后计算值(元)"},
						{"", "", "", "true", "", "1", "bigdecimal"},
						{""}},
				{
						{"b8", "企业评定期所有者权益(元)"},
						{"", "computeB7", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b9", "其中：抵减损耗(元)"},
						{"", "computeB7", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b10", "&emsp;&emsp;&emsp;企业潜亏(元)"},
						{"", "computeB7", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b11", "&emsp;&emsp;&emsp;无形资产(元)"},
						{"", "computeB7", "", "false", "float", "1",
								"bigdecimal"}, {"不含土地使用权"}},
				{{"b11x", "&emsp;&emsp;&emsp;财报无形资产(元)"},
						{"", "", "", "true", "float", "1", "bigdecimal"},
						{""}},
				{
						{"b26", "&emsp;&emsp;&emsp;土地使用权(元)"},
						{"", "computeB11", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"b12", "&emsp;&emsp;&emsp;少数股东权益(元)"},
						{"", "computeB7", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{{"b13", "新增评定授信额(元)"},
						{"", "", "", "true", "float", "1", "bigdecimal"},
						{""}},
				{{"b14", "附加项目："}, {"", "", "", "false", "", "", ""}, {""}},
				{
						{"b15", "他行授信额(元)"},
						{"", "computeB1", "", "false", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b16", "其中：短期贷款(元)"},
						{"", "computeB15", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b17", "&emsp;&emsp;&emsp;中长期贷款(元)"},
						{"", "computeB15", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b18", "&emsp;&emsp;&emsp;贴现(元)"},
						{"", "computeB15", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b19", "&emsp;&emsp;&emsp;签发承兑汇票(元)"},
						{"", "computeB15", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{{"b20", "&emsp;&emsp;&emsp;对外担保额(元)"},
						{"", "", "", "true", "float", "1", "bigdecimal"},
						{"至少按征信查询情况填写"}},
				{{"b21", "我行现有用信额(元)"},
						{"", "", "", "false", "float", "1", "bigdecimal"},
						{""}},
				{
						{"b22", "其中：贷款授信额(元)"},
						{"", "computeB21", "", "true", "float", "1",
								"bigdecimal"}, {""}},
				{
						{"b23", "&emsp;&emsp;&emsp;表外授信额(元)"},
						{"", "computeB21", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{{"b24", "最高综合授信额(元)"},
						{"", "", "", "false", "", "1", "bigdecimal"}, {""}},
				{{"b25", "我行最高综合授信额(元)"},
						{"", "", "", "false", "", "1", "bigdecimal"}, {""}}};
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
					String[][] trs;
					for (int i = 0; i < tables.length; i++) {
						trs = tables[i];
						String id = trs[0][0];
				%>
				<tr>
					<td style="width: 250px;">
						<label><%=trs[0][1]%>
						</label>
					</td>
					<td style="width: 200px">
						<input class="nui-textbox" id="measure.<%=id%>" name="measure.<%=id%>" value="<%=trs[1][0]%>" onblur="<%=trs[1][1]%>" style="<%=trs[1][2]%>" enabled="<%=trs[1][3]%>" required="<%=trs[1][3]%>" vtype="<%=trs[1][4]%>" dataType="<%=trs[1][6]%>" />
					</td>
					<td>
						<label id="<%=id%>_remark"><%=trs[2][0]%></label>
					</td>
					<td>
						<label id="<%=id%>_msg" style="color: red;"></label>
						<input id="<%=id%>_multiplier" class="nui-hidden" value="<%=trs[1][5]%>" />
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

			$.ajax({
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
					if(!data.measure.b11){
						data.measure.b11 = data.measure.b11x-data.measure.b26;
					}
					form.setData(data);
					nui.get("mea_add").setEnabled(false);
				}
			});

		}
		function meaSave() {
		
			var data = validateForm(form);
			debugger;
			if (!data) {
				return nui.alert("数据有误，请检查");;
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
				"customerTypeCd":"002",
				"codes" : {
					"b5" : "ZCFZL",
					"b8" : "00201050",
					"b11x" : "00201051",
					"b26" : "00204047",					
					"b12" : "00201064"
				}
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
			nui
					.get("measure.b1")
					.setValue(
							!b15Value || b15Value == "" || b15Value == 0 ? data.limitMap.rebate
									: data.limitMap.rebate_2);

		}
		function computeB2() {
			var b3Value = getValue("b3");
			var b4Value = getValue("b4");
			if (b3Value != null && b4Value != null) {
				var b2Value = b3Value * b4Value;
				nui.get("measure.b2").setValue(b2Value);
			}
		}

		function computeB6() {
			var value = getValue("b5");
			if (value != null) {
				var value = value / (1 - (value==1?0:value));
				nui.get("measure.b6").setValue(value.toFixed(2));
			}
		}
		function computeB7() {
			var b8Value = getValue("b8");
			var b9Value = getValue("b9");
			var b10Value = getValue("b10");
			var b11Value = getValue("b11");
			var b12Value = getValue("b12");
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
			if (b11xValue != null && b12Value != null ){
			var b11Value = b11xValue - b12Value;
			nui.get("measure.b11").setValue(b11Value);
			}
		}
		function computeB13() {
			var b1Value = getValue("b1");
			var b2Value = getValue("b2");
			var b6Value = getValue("b6");
			var b7Value = getValue("b7");
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
			if (b16Value != null && b17Value != null && b18Value != null
					&& b19Value != null) {
				var b15Value = b16Value + b17Value + b18Value + b19Value;
				var multiplier = $("#b15_multiplier").val();
				if (multiplier && multiplier != 1) {
					b15Value = b15Value / (multiplier==0?1:multiplier);
				}
				nui.get("measure.b15").setValue(b15Value);
			}
		}
		function computeB21() {
			var b22Value = getValue("b22");
			var b23Value = getValue("b23");
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
		
		function getData() {
			return form.getData();
		}
	</script>
</body>
</html>