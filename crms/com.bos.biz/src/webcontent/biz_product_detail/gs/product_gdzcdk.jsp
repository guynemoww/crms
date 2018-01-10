<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4" style="width:99.5%;">
</div>
<div id='ht1' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>提前还款是否计收违约金：</label>
	<input id="conDetail.prepaymentPenalty" name="conDetail.prepaymentPenalty" required="true" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  onvaluechanged="onselectPrepaymentPenalty" />
	<label id="prepayMakeupRate">违约金比例%：</label>
	<input id="conDetail.wybcbl" name="conDetail.wybcbl" required="true"   class="nui-textbox nui-form-input"  required="true"  vtype="float;range:0,99.999999"  />
	
	<label>资金支付方式：</label>
	<input id="conDetail.payWay" name="conDetail.payWay" required="true" class="mini-dictradiogroup" dictTypeId="CDXY0144" valueField="dictID" />
</div>
<div id='ht3' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label id="sfjmrhsx">是否军民融合属性：</label>
	<input name="conDetail.sfjmrhsx" id="conDetail.sfjmrhsx" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" enabled="false"/>
	<label id="jmrhsx">军民融合属性：</label>
	<input id="conDetail.jmrhsx" name="conDetail.jmrhsx" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_JMRHSX001" enabled="false"/>
</div>
<div id='ht2' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>提前还款最低金额：</label>
	<input id="conDetail.leastPrepayAmount" name="conDetail.leastPrepayAmount"  required="true" class="nui-textbox nui-form-input" dataType="currency" vtype="float;maxLength:20" />
	<label>提前还款基数：</label>
	<input id="conDetail.prepayJs" name="conDetail.prepayJs"   class="nui-textbox nui-form-input" required="true"  dataType="currency" vtype="float;maxLength:20" />
	
	<!-- <label>其他提款条件1：</label>
	<input id="conDetail.otherCondition1" name="conDetail.otherCondition1"  class="nui-textbox nui-form-input"  vtype="maxLength:100"/>
	<label>其他提款条件2：</label>
	<input id="conDetail.otherCondition2" name="conDetail.otherCondition2"  class="nui-textbox nui-form-input"  vtype="maxLength:100"/>
	<label>其他提款条件3：</label>
	<input id="conDetail.otherCondition3" name="conDetail.otherCondition3"  class="nui-textbox nui-form-input"  vtype="maxLength:100"/>
	<label>其他提款条件4：</label>
	<input id="conDetail.otherCondition4" name="conDetail.otherCondition4"  class="nui-textbox nui-form-input"  vtype="maxLength:100"/>
	
	<label>向第三方申请借款超过（万元）：</label>	
	<input id="conDetail.thirdLoanAmount"  name="conDetail.thirdLoanAmount" class="nui-textbox nui-form-input" vtype="float;maxLength:20"/>	
	<label>总负债超过（万元）：</label>	
	<input id="conDetail.totalDebt"  name="conDetail.totalDebt" class="nui-textbox nui-form-input" vtype="float;maxLength:20"/>
	<label>向第三方提供借款超过（万元）：</label>		
	<input id="conDetail.thirdBorrowAmount"  name="conDetail.thirdBorrowAmount" class="nui-textbox nui-form-input" vtype="float;maxLength:20"/>
	<label>向第三方提供担保超过（万元）：</label>		
	<input id="conDetail.thirdGuarant"  name="conDetail.thirdGuarant" class="nui-textbox nui-form-input" vtype="float;maxLength:20"/>
	<label>股东或股权变更超过（%）：</label>		
	<input id="conDetail.stockChange"  name="conDetail.stockChange" class="nui-textbox nui-form-input" vtype="float;range:0,99.999999;maxLength:8" />
	<label>财务状况阀值（万元）：</label>		
	<input id="conDetail.financialLimit"  name="conDetail.financialLimit" class="nui-textbox nui-form-input"  vtype="float;maxLength:20"/> -->
</div>
<!-- <div id='remark'  style="text-align: left;" >说明：
</br>
在贷款期内将流动资产和资产净值、资产负债比例、资产流动比例等财务状况维持在财务状况阀值范围内;
</div> -->
<script type="text/javascript">
	$("#sq").css("display","none");
	$("#ht1").css("display","none");
	$("#ht2").css("display","none");
	$("#ht3").css("display","none");
	$("#remark").css("display","none");
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	var productType ="<%=request.getParameter("productType") %>";//贷种
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
	}
	if(stepFlag=='con'){
		if('01003007'==productType){
			$("#ht1").css("display","block");
			$("#ht2").css("display","block");
			$("#ht3").css("display","block");
		}else{
			$("#ht1").css("display","block");
			$("#ht2").css("display","block");
		}
		$("#remark").css("display","block");
	}
</script>