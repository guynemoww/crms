<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22

  - Description:TB_CSM_STOCK_INFO, com.bos.dataset.csm.TbCsmStockInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="item.stockId" id="item.stockId" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmStockInfo" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>股票名称：</label>
		<input name="item.stockName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>股票代码：</label>
		<input name="item.stockCode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>上市日期：</label>
		<input name="item.listingDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>

		<label>上市交易所：</label>
		<input name="item.stockExchange" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0039"/>
		
		<label>股票类型：</label>
		<input id="item.stockType"  name="item.stockType" vtype="minLength:2" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0077"  />
        
		<label>总股本数：</label>
		<input name="item.totalCapitalStock" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:12" />

		<label>流通股数：</label>
		<input name="item.tradingCapitalStock" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:12" />

		<label>每股净资产：</label>
		<input name="item.onlyMeans" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />

		<label>币种：</label>
		<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>

		<label>每股净收益：</label>
		<input name="item.earningsPerShare" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />

		<label>每股经营活动净现金流：</label>
		<input name="item.cashFlowPerShare" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />
<!-- 
		<label>是否ST：</label>
		<input name="item.listingCorpLogo" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
	 -->
	</div>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>

<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

	function initForm() {
		var json=nui.encode({"item":{"stockId":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.dataset.csm.TbCsmStockInfo"}});
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.getItemObject.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
     
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.saveItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
</script>
</body>
</html>
