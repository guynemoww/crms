<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:36:15
  - Description:
-->
<head>
<title>保证金合同基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="margin: 10px;">
		<input id="subContract.subcontractId" name="subContract.subcontractId" class="nui-hidden nui-form-input" vtype="maxLength:32" />
		<input id="subContract.conPartyId" name="subContract.conPartyId" class="nui-hidden nui-form-input" vtype="maxLength:32" />
		<input id="tbConAttachedInfo.attached" name="tbConAttachedInfo.attached" class="nui-hidden nui-form-input" vtype="maxLength:32" />
		<input id="contractInfo.contractAmt" name="contractInfo.contractAmt" class="nui-hidden nui-form-input" />
		<fieldset>
			<legend>
				<span>基本信息</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>几日内存入保证金：</label>
				<input name="subContract.jrncrbzj" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:20;range:1,1000" />
				<label>币种：</label>
				<input name="subContract.bz" id="subContract.bz" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" allowInput="false" enabled="false" />
				<label>保证金比例（%）：</label>
				<input id="subContract.bzjbl" name="subContract.bzjbl" required="true" class="nui-textbox nui-form-input" onblur="countBzjbl" vtype="range:0,100" />
				<label>保证金金额：</label>
				<input id="subContract.bzjje" name="subContract.bzjje" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:20;range:0,100000000000" dataType="currency" />
				<label>保证金类型：</label>
				<input name="subContract.bzjlx" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0134" onvaluechanged="marginTypeChange" />
				<label id="beginDateLab">保证金起始日：</label>
				<input name="subContract.beginDate" id="subContract.beginDate" required="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" onvaluechanged="dateChange" />
				<label id="endDateLab">保证金到期日：</label>
				<input name="subContract.endDate" id="subContract.endDate" required="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowInput="false" onvaluechanged="dateChange" />
				<!-- 与陶总监沟通 取保证金利率 -->
				<label>按年利率计息(%)：</label>
				<input id="subContract.anlljx" name="subContract.anlljx" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" />
				<label>其他计收利息方式：</label>
				<input name="subContract.qtjsxfs" class="nui-textbox nui-form-input" vtype="maxLength:300" required="false" />
				<label>保证金结息周期：</label>
				<input id="subContract.bzjjxzq" name="subContract.bzjjxzq" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_YWDB0138" />
				<label>若保证金价值下降，应在几日内通知客户补充保证金：</label>
				<input name="subContract.jrtzkh" class="nui-textbox nui-form-input" vtype="int;maxLength:6;range:1,1000" required="true" />
				<!-- <label>违约金比例（%）：</label>
		<input id="subContract.zfwyjbl" name="subContract.zfwyjbl"  class="nui-textbox nui-form-input" required="true" vtype="float;range:0,100;maxLength:8" />
 -->
			</div>
		</fieldset>
		<div class="nui-dynpanel" columns="1" id="table6">
			<fieldset>
				<legend>
					<span>仲裁方式</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_public_zcfs.jsp"%>
			</fieldset>
		</div>
		<div class="nui-dynpanel" columns="1" id="table7">
			<fieldset>
				<legend>
					<span>协议签署</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_public_xyqs.jsp"%>
			</fieldset>
		</div>
		<fieldset>
			<legend>
				<span>补充条款</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>补充条款：</label>
				<input name="tbConAttachedInfo.addClause" class="nui-textArea nui-form-input" vtype="maxLength:2000" colspan="3" />
			</div>
		</fieldset>
		<div class="nui-toolbar" style="text-align: right; padding-top: 5px; padding-bottom: 5px; text-align: right;" borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-save" id="addEdit" onclick="save()">确认</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var view =
	<%=JspUtil.getParameterHaveSign(request, "view")%>
		;
		var applyId =
	<%=JspUtil.getParameterHaveSign(request, "applyId")%>
		;
		var partyId =
	<%=JspUtil.getParameterHaveSign(request, "partyId")%>
		;
		var xgbz =
	<%=JspUtil.getParameterHaveSign(request, "xgbz")%>
		;
		var contractId =
	<%=JspUtil.getParameterHaveSign(request, "contractId")%>
		;

		var ljurl;
		var ljurl2;
		var id;
		if (view == "1") {
			form.setEnabled(false);
			nui.get("addEdit").hide();
		}

		initForm();
		//初始化查询
		function initForm() {
			var subcontractId =
	<%=JspUtil.getParameterHaveSign(request, "subcontractId")%>
		;
			var json = nui.encode({
				"subContract" : {
					"subcontractId" : subcontractId
				},
				"contractId" : contractId
			});
			$.ajax({
				url : "com.bos.grt.conGrt.getGrtConInfo.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					var o = nui.decode(text);
					form.setData(o);
					id = o.subContract.id
					nui.get("subContract.bz").setValue("CNY");
					//国结的产品 放开对币种的控制
					if (text.contractInfo.productType.substring(0, 5).indexOf(
							"01007") != -1) {
						nui.get("subContract.bz").setEnabled(true);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		//保存
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			nui.get("subContract.conPartyId").setValue(partyId);
			var o = form.getData();/* form所有信息 */
			var bzjje = parseFloat(o.subContract.bzjje);
			if (!bzjje || bzjje < 0) {
				alert("保证金金额必填，通过保证金比例反显");
			}
			o.subContract.bzjje = bzjje.toFixed(2);//保证金金额 保留两位小数
			o.tbConAttachedInfo.contractId = o.subContract.subcontractId;
			o.subContract.id = id;
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.grt.conGrt.saveGrtCon.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					alert("保存成功");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function dateChange(e) {
			var beginDate = nui.get("subContract.beginDate").getValue();//生效日期
			var endDate = nui.get("subContract.endDate").getValue();//到期日期
			if (beginDate != "" && endDate != "") {
				if (!CompareDueAndShengXiaoDate(beginDate, endDate)) {//生效日期大于到期日期
					nui.alert("到期日期必须大于起始日期");
					nui.get("subContract.beginDate").setValue("");
					nui.get("subContract.endDate").setValue("");
				}
			}
		}

		function typeChange(e) {
			if (e.value == '01') {
				nui.get("subContract.anlljx").setRequired(true);
				form.validate();
			} else {
				nui.get("subContract.anlljx").setRequired(false);
				form.validate();
			}
		}
		/**
		 * 比较到期日期和生效日期
		 */
		function CompareDueAndShengXiaoDate(beginDate, endDate) {
			if (nui.parseDate(endDate) - nui.parseDate(beginDate) <= 0) {//到期日期小于生效日期
				return false;
			} else {
				return true;
			}
		}

		function marginTypeChange(e) {
			if (e.value == '02') { //活期
				nui.get("subContract.beginDate").setRequired(false);
				nui.get("subContract.endDate").setRequired(false);
				nui.get("subContract.anlljx").setRequired(false);

				//nui.get("subContract.endDate").hide();
				//nui.get("subContract.beginDate").hide();
				//$("#beginDateLab").hide();
				//$("#endDateLab").hide();
			} else {//定期
				nui.get("subContract.beginDate").setRequired(true);
				nui.get("subContract.endDate").setRequired(true);
				nui.get("subContract.anlljx").setRequired(true);
				// 			nui.get("subContract.beginDate").show();
				// 			nui.get("subContract.endDate").show();
				// 			$("#beginDateLab").show();
				// 			$("#endDateLab").show();
			}
		}

		function countBzjbl() {
			var contractAmt = nui.get("contractInfo.contractAmt").getValue();
			if (!contractAmt || contractAmt < 0) {
				alert("请输入正确的合同金额");
				return;
			}
			var bzjbl = nui.get("subContract.bzjbl").getValue();
			if (contractAmt && contractAmt > 0 && bzjbl && bzjbl >= 0
					&& bzjbl <= 100) {
				var bzjje = contractAmt * bzjbl / 100;
				nui.get("subContract.bzjje").setValue(bzjje.toFixed(2));
			}
		}
	</script>
</body>
</html>