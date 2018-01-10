<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-06-29 17:13:15
  - Description:
-->
<%@include file="/common/nui/common.jsp"%>
<head>
</head>
<body>
	<%
		String edit = JspUtil.getParameter(request, "edit", "2");
		boolean enabled = edit.equals("1");
	%>
	<div style="width: 460px; height: 370px; margin: 0 auto;">

		<div id="form_scale" class="nui-form">
			<div class="nui-dynpanel" columns="2">
				<label style="width: 120px">客户名称：</label>
				<input name="party.PARTY_NAME" class="nui-textbox" style="width: 220px" enabled="fasle" />
				<label>从业人数（人）：</label>
				<input id="scale.empNum" name="scale.empNum" class="nui-textbox " enabled="false" />
				<label>销售额(万元)：</label>
				<input id="scale.saleroom" name="scale.saleroom" class="nui-textbox " dataType="currency" enabled="false" />
				<label>资产总额(万元)：</label>
				<input id="scale.assets" name="scale.assets" class="nui-textbox " dataType="currency" enabled="false" />
				<label style="width: 120px">工信部企业规模：</label>
				<input name="party.ENTERPRISE_SCALE_GX" class="nui-dictcombobox" dictTypeId="CDKH0027" style="width: 220px" allowInput="false" enabled="false" />
				<label style="width: 120px">统计口径企业规模：</label>
				<input name="party.COUNT_BORE_ENTER_SCALE" class="nui-dictcombobox" dictTypeId="CDKH0027" style="width: 220px" allowInput="false" enabled="false" />
				<label style="width: 120px">原银行认定企业规模：</label>
				<input name="scale.oldScaleCode" class="nui-dictcombobox" dictTypeId="CDKH0027" style="width: 220px" allowInput="false" enabled="false" />
				<label style="width: 120px">银行认定企业规模：</label>
				<input id="scale.scaleCode" name="scale.scaleCode" class="nui-dictcombobox" dictTypeId="CDKH0027" style="width: 220px" allowInput="false" enabled="<%=enabled%>" required="true" />
			</div>
			<input id="scale.id" name="scale.id" class="nui-hidden" />
			<input id="scale.tradeType" name="scale.tradeType" class="nui-hidden" />
			<input id="scale.partyId" name="scale.partyId" class="nui-hidden" />
		</div>

		<%
			if (enabled) {
		%>
		<div class="nui-toolbar" style="text-align: center; border-top-width: 0">
			<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="computeScale">自动计算</a>
			<a id="editCust" class="nui-button" iconCls="icon-save" onclick="identifyScale">保存</a>
		</div>
		<%
			}
		%>
	</div>
	<script type="text/javascript">
		nui.parse();
		var bizId =
	<%=JspUtil.getParameterHaveSign(request, "bizId")%>
		;
		var partyId =
	<%=JspUtil.getParameterHaveSign(request, "partyId")%>
		;
		var formScale = new nui.Form("#form_scale");

		initPage();
		function initPage() {
			var data = nui.get("scale.scaleCode").getData();
			for (var i = 0; i < data.length; i++) {
				if (data[i].dictID == '5') {
					data.splice(i, 1);
				}
			}
			nui.get("scale.scaleCode").setData(data);
			showIdentifyInfo();
		}
		function identifyScale() {
			formScale.validate();
			if (formScale.isValid() == false) {
				nui.alert("请完整填写信息！");
				return;
			}
			var data = formScale.getData();
			var json = nui.encode({
				"scale" : data.scale
			});
			debugger;
			$
					.ajax({
						url : "com.bos.csm.corporation.corporation.saveIdentifyCorpScale.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								alert(text.msg);
							} else {
								alert("操作成功");
							}
						}
					});
		}

		function showIdentifyInfo() {
			var json = nui.encode({
				"scaleId" : bizId,
				"partyId" : partyId
			});
			$
					.ajax({
						url : "com.bos.csm.corporation.corporation.getIdentifyCorpScale.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(data) {
							debugger;
							if (data.msg) {
								alert(data.msg);
							} else {
								formScale.setData(data);
								if (!data.scale.scaleCode) {
									computeScale();
								}
							}
						}
					});
		}
		function computeScale() {
			var partyId = formScale.getData().scale.partyId;
			$.ajax({
				url : "com.bos.csm.corporation.corporation.genScale.biz.ext",
				type : 'POST',
				data : nui.encode({
					"partyId" : partyId
				}),
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					debugger;
					if (text.msg) {
						alert(text.msg);
					} else if (text.scale) {
						nui.get("scale.scaleCode").setValue(text.scale);
						nui.get("scale.saleroom").setValue(
								text.identifyInfo.saleroom);
						nui.get("scale.assets").setValue(
								text.identifyInfo.assets);
						nui.get("scale.empNum").setValue(
								text.identifyInfo.empNum);
						nui.get("scale.tradeType").setValue(
								text.identifyInfo.tradeType);
					}
				}
			});
		}
	</script>
</body>
</html>