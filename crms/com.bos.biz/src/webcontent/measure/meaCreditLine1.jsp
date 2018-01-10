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
				{{"折扣系数*"}, {"", "", "", "false", "float", "1", ""}, {""}},//
				{{"目标杠杆系数×信用等级调节系数*"},
						{"", "", "", "false", "float", "1", ""}, {""}},//
				{{"目标杠杆系数"},
						{"", "computeB2", "", "true", "float", "1", ""},
						{""}},
				{{"信用等级调节系数"},
						{"", "computeB2", "", "false", "float", "1", ""},
						{""}},
				{{"企业评定期资产负债率"},
						{"", "computeB6", "", "false", "float", "1", ""},
						{""}},
				{{"资产负债率/（1-资产负债率)*"},
						{"", "", "", "false", "float", "1", ""}, {""}},
				{{"企业所有者权益抵减其他因素后计算值"},
						{"", "", "", "false", "float", "1", "bigdecimal"},
						{""}},
				{
						{"企业评定期所有者权益"},
						{"", "computeB7", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"其中：抵减损耗"},
						{"", "computeB7", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"&emsp;&emsp;&emsp;企业潜亏"},
						{"", "computeB7", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"&emsp;&emsp;&emsp;无形资产"},
						{"", "computeB7", "", "false", "", "1",
								"bigdecimal"}, {"不含土地使用权"}},
				{
						{"&emsp;&emsp;&emsp;土地使用权"},
						{"", "computeB11", "", "false", "", "1",
								"bigdecimal"}, {""}},
				{
				
						{"&emsp;&emsp;&emsp;少数股东权益"},
						{"", "computeB7", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{{"新增评定授信额"},
						{"", "", "", "false", "", "1", "bigdecimal"},
						{""}},
				{{"附加项目："}, {"", "", "", "true", "", "", ""}, {""}},
				{
						{"他行授信额(万元）"},
						{"", "computeB1", "", "false", "", "1",
								"bigdecimal"}, {""}},
				{
						{"其中：短期贷款(万元）"},
						{"", "computeB16", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"&emsp;&emsp;&emsp;中长期贷款(万元）"},
						{"", "computeB16", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"&emsp;&emsp;&emsp;贴现(万元）"},
						{"", "computeB16", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"&emsp;&emsp;&emsp;签发承兑汇票  (万元）"},
						{"", "computeB16", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{{"&emsp;&emsp;&emsp;对外担保额(万元）"},
						{"", "", "", "true", "", "1", "bigdecimal"},
						{"至少按征信查询情况填写"}},
				{{"我行现有用信额(万元）"},
						{"", "computeB22", "", "false", "", "1", "bigdecimal"},
						{""}},
				{
						{"其中：贷款授信额(万元）"},
						{"", "computeB22", "", "true", "", "1",
								"bigdecimal"}, {""}},
				{
						{"&emsp;&emsp;&emsp;表外授信额(万元）"},
						{"", "computeB22", "", "true", "", "1", "bigdecimal"},
						{""}},
				{{"最高综合授信额"}, {"", "", "", "false", "", "1", "bigdecimal"},
						{""}},
				{{"我行最高综合授信额"}, {"", "", "", "false", "", "1", "bigdecimal"},
						{""}}};
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
						String id = "b" + (i + 1);
				%>
				<tr>
					<td style="width: 250px;">
						<label><%=trs[0][0]%>
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
			<a class="nui-button" id="mea_add" iconCls="icon-remove" onclick="meaRemove">删除测算</a>
			<span style="display: inline-block; width: 50px;"></span>
			<a class="nui-button" id="mea_add" iconCls="icon-add" onclick="meaAdd">新增测算</a>
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
					if (!data.measure || !data.measure.b1) {
						nui.alert("请点击[新增测算]按钮增加测算信息");
						nui.get("mea_add").setEnabled(true);
						return;
					}
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
				"customerTypeCd":"002",
				"codes" : {
					"b5" : "ZCFZL",
					"b8" : "00201050",
					"b11" : "00201051",
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
						nui.get("mea_add").setEnabled(true);
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
					nui.alert("操作成功");
					CloseWindow("remove");
				}
			});
		}
		function getData() {
			return nui.clone(form.getData());
		}

		function mea() {
			computeB6();
			computeB14();
			computeB25();
			computeB26();
		}
		function computeB1() {
			var data = form.getData();
			var b16Value = getValue("b16");
			nui.get("measure.b1").setValue(!b16Value || b16Value == "" || b16Value == 0 ? data.limitMap.rebate: data.limitMap.rebate_2);

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
				var value = value / (1 - value);
				nui.get("measure.b6").setValue(value);
			}
		}
		function computeB7() {
			var b8Value = getValue("b8");
			var b9Value = getValue("b9");
			var b10Value = getValue("b10");
			var b11Value = getValue("b11");
			var b12Value = getValue("b12");
			var b13Value = getValue("b13");
			if (b8Value != null && b9Value != null && b10Value != null
					&& b11Value != null && b12Value != null&& b13Value != null) {
				var b7Value = b8Value - b9Value - b10Value - b11Value - b12Value
						- b13Value;
				nui.get("measure.b7").setValue(b7Value);
			}
		}
		
		function computeB11() {
			var b11Value = getValue("b11");
			var b12Value = getValue("b12");
			if (b11Value != null && b12Value != null ){
			var b11Value = b11Value -b12Value;
			nui.get("measure.b11").setValue(b11Value);
			}
		}
		
		function computeB14() {
			var b1Value = getValue("b1");
			var b2Value = getValue("b2");
			var b6Value = getValue("b6");
			var b7Value = getValue("b7");
			if (b1Value != null && b2Value != null && b6Value != null
					&& b7Value != null) {
				var b14Value = b1Value * (b2Value - b6Value) * b7Value;
				nui.get("measure.b14").setValue(b14Value);
			}
		}
		

		function computeB16() {
			var b17Value = getValue("b17");
			var b18Value = getValue("b18");
			var b19Value = getValue("b19");
			var b20Value = getValue("b20");
			if (b17Value != null && b18Value != null
					&& b19Value != null &&  b20Value != null ) {
				var b16Value =  b17Value + b18Value + b19Value+b20Value ;
				nui.get("measure.b16").setValue(b16Value);
			}
		}
		function computeB22() {
			var b23Value = getValue("b23");
			var b24Value = getValue("b24");
			if(b23Value!= null && b24Value!= null){
			var b22Value = b23Value + b24Value;
			nui.get("measure.b22").setValue(b22Value);
			}
		
		}
		function computeB25() {
			var b14Value = getValue("b14");
			var b16Value = getValue("b16");
			var b21Value = getValue("b21");
			var b22Value = getValue("b22");
			if (b14Value != null && b16Value != null && b21Value != null
					&& b22Value != null) {
				var b25Value = b14Value + b16Value - b21Value + b22Value;
				nui.get("measure.b25").setValue(b25Value);
			}
		}
		function computeB26() {
			var b15Value = getValue("b15");
			var b17Value = getValue("b17");
			var b22Value = getValue("b22");
			var b23Value = getValue("b23");
			if (b15Value != null && b17Value != null && b22Value != null&& b23Value!=null) {
				var b26Value = b15Value + b17Value - b22Value+b23Value;
				nui.get("measure.b26").setValue(b26Value);
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