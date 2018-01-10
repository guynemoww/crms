<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>业务明细页面</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<% 
  String pAddress ="/"+(String)request.getAttribute("jspName");
  System.out.println("pAddress=========="+pAddress);
 %>
<center>
 <div id="panel1" class="nui-panel" title="业务申请品种明细"
	style="width:99.5%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="amountDetail.amountDetailId" class="nui-hidden nui-form-input" name="amountDetail.amountDetailId"/>
	<input id="productDetail.applyDetailId" class="nui-hidden nui-form-input" name="productDetail.applyDetailId"/>
	<input id="productDetail.applyDetailId" class="nui-hidden nui-form-input" name="productDetail.applyDetailId"/>
	<input id="tbBizProductInfo.entityName" class="nui-hidden nui-form-input" name="tbBizProductInfo.entityName"/>
	<input id="tbBizProductInfo.productCd" class="nui-hidden nui-form-input" name="tbBizProductInfo.productCd"/>
	基本信息	
	<div class="nui-dynpanel" columns="4">
		<label>业务品种：</label>
		<input id="amountDetail.productType" name="amountDetail.productType" dictTypeId="product" class="nui-text nui-form-input" />
		
		<label>金额：</label>
		<input id="amountDetail.detailAmt" name="amountDetail.detailAmt" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
		
		<label>币种：</label>
		<input name="amountDetail.currencyCd" id="currencyCd" required="true" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" enabled="false" emptyText="--请选择--"/>
		
		<!--  <label>汇率：</label>
		<input name="amountDetail.exchangeRate" required="true" vtype="float;maxLength:10" class="nui-textbox nui-form-input" />
		 -->

		<label>期限：</label>
		<div style="width:80%">
		<input name="amountDetail.creditTerm" style="width:60%;float:left" id="amountDetail.creditTerm" required="true" vtype="int;maxLength:4" class="nui-textbox nui-form-input"/>
		<input name="amountDetail.cycleUnit"id="amountDetail.cycleUnit" style="width:40%;float:left"  required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6009" value="04" emptyText="--请选择--"/>
		</div>
		
		<!--<label>宽限期：</label>
		<input name="amountDetail.broadCycle" required="false" vtype="int;maxLength:4" class="nui-textbox nui-form-input" />

		<label>宽期限单位：</label>
		<input name="amountDetail.broadCycleUnit" required="false" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="BIZ_TERMUNITCDAPPR" emptyText="--请选择--" />
		-->
				
		<label>贷款用途：</label>
		<input name="amountDetail.loanUse" id="amountDetail.loanUse" required="true"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1007" emptyText="--请选择--"/>
		
				
		<label id="repaymentType">还款方式：</label>
		<input id="amountDetail.repaymentType" name="amountDetail.repaymentType" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="CDXY0037" emptyText="--请选择--"/>
		
		<label id="repaymentType">还款来源：</label>
		<input id="amountDetail.payment" name="amountDetail.payment" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="CDXY0037" emptyText="--请选择--"/>
		
		<label>提前还款是否收取违约金：</label>
		<input id="amountDetail.prepaymentPenalty" name="amountDetail.prepaymentPenalty" required="true"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="--请选择--"/>

		<label>是否循环：</label>
		<input id="amountDetail.cycleInd" name="amountDetail.cycleInd" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
	</div>
	
	<%--品种信息--%>
	<div class="nui-dynpanel" columns="4" id="table2">
	  <jsp:include page="<%=pAddress %>"/>
	</div>
	  

	
	贷款利率	
	<div class="nui-dynpanel" columns="4" id="table1">
	<%@include file="/biz/biz_product_detail/biz_public_rate.jsp"%>
	</div>
	</div>
 </div>

	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
	    <a class="nui-button" id="" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var amountDetailId = "<%=request.getAttribute("amountDetailId") %>";//额度明细ID
	
	initPage();
	function initPage(){
        var json = nui.encode({"amountDetailId":"<%=request.getAttribute("amountDetailId")%>"});
		$.ajax({
            url: "com.bos.bizProductDetail.bizProductDetail.getProductDetail.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	onselectType(1);
			}
        });
	}
		
	function create(){
			//校验
		form.validate();
        if (form.isValid()==false) return;
        
        nui.get("btnCreate").setEnabled(false);
		
        var o = form.getData();
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.bizProductDetail.bizProductDetail.saveProductDetail.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	alert("保存成功!");
            	form.setData(o);
			}
        });
        nui.get("btnCreate").setEnabled(true);
	}
</script>
</body>
</html>