<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:TB_CSM_STOCK_INFO, com.bos.dataset.csm.TbCsmStockInfo
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<center>
		<div id="form1" style="width: 100%; height: auto; overflow: hidden;">
			<input name="item.partyId" id="item.partyId" class="nui-hidden" />
			<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmStockInfo" class="nui-hidden" />
			<div class="nui-dynpanel" columns="4">
				<label>股票名称：</label>
				<input name="item.stockName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

				<label>股票代码：</label>
				<input name="item.stockCode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:30" />

				<label>上市日期：</label>
				<input name="item.listingDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />

				<label>上市交易所：</label>
				<input name="item.stockExchange" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0039" />

				<label>股票类型：</label>
				<input id="item.stockType" name="item.stockType" vtype="minLength:2" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0077" />

				<label id="totalCapitalStock_label">总股本数</label>
				：
				<input id="item.totalCapitalStock" name="item.totalCapitalStock" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:12" onblur="checkTradingCapitalStock" />

				<label id="tradingCapitalStock_label">流通股数</label>
				：
				<input id="item.tradingCapitalStock" name="item.tradingCapitalStock" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:12" onblur="checkTradingCapitalStock" />

				<label>每股净资产：</label>
				<input name="item.onlyMeans" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />

				<label>币种：</label>
				<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" dvalue="CNY" />

				<label>每股净收益：</label>
				<input name="item.earningsPerShare" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />

				<label>每股经营活动净现金流：</label>
				<input name="item.cashFlowPerShare" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />
				<%--
		<label>是否ST：</label>
		<input name="item.listingCorpLogo" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  />

		<label>登记人：</label>
		<input name="item.handlingUserId" required="false" Enabled="false" dictTypeId="user" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" class="nui-textbox nui-form-input"  />
		
		<label>登记机构：</label>
		<input name="item.handlingOrgId" required="false" Enabled="false" dictTypeId="org" value="<%=com.bos.pub.GitUtil.getCurrentOrgId()%>" class="nui-textbox nui-form-input"  />
		
		<label>登记日期：</label>--%>
				<input name="item.createTime" type="hidden" required="false" Enabled="false" value="<%=com.bos.pub.GitUtil.getBusiDate()%>" format="yyyy-MM-dd" />

			</div>
		</div>

		<div class="nui-toolbar" style="border: 0; text-align: right;">
			<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
			<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
	</center>

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");

		var partyId =
	<%="\"" + request.getParameter("partyId") + "\""%>
		;
		if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}

		function checkTradingCapitalStock() {
			var msg = null;
			var temp = nui.get("item.tradingCapitalStock");
			var tradingCapitalStock = getNumberValue(temp.getValue());
			if (tradingCapitalStock) {
				var totalCapitalStock = getNumberValue(nui.get("item.totalCapitalStock").getValue());
				if (totalCapitalStock) {
					if (tradingCapitalStock >= totalCapitalStock) {
						msg = "【" + $("#tradingCapitalStock_label").text() + "】不能大于【" + $("#totalCapitalStock_label").text() + "】";
					}
				} else {
					msg = "请先填写【" + $("#totalCapitalStock_label").text() + "】";
				}
				if (msg) {
					nui.alert(msg);
					temp.setValue("");
				}
			}
		}

		function getNumberValue(value) {
			value = getValue(value);
			if (value) {
				return value;
			}
			return null;
		}

		function getValue(value) {
			if (!value || "" == value || "null" == value || "undefined" == value) {
				return null;
			}
			return value;
		}

		function save() {
			checkTradingCapitalStock();
		
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			
			git.mask("form1");
			var o = form.getData();
			var json = nui.encode(o);
			//nui.alert(json);return;
			$.ajax({
				url : "com.bos.csm.pub.crudCustInfo.insertItem.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						nui.alert(text.msg);
					} else {
						alert("保存成功!");
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmaks("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
	</script>
</body>
</html>
