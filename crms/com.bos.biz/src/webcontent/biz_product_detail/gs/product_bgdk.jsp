<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label>并购方式：</label>
	<input id="productDetail.bgfs" name="productDetail.bgfs"   class="nui-textbox nui-form-input"  required="true" vtype="maxLength:100"/>
	<label>并购交易金额：</label>
	<input id="productDetail.bgjyje" name="productDetail.bgjyje" vtype="float;maxLength:20" required="true"  class="nui-textbox nui-form-input" dataType="currency" />
	<label>被并购方：</label>
	<input id="productDetail.bbgf" name="productDetail.bbgf"   class="nui-textbox nui-form-input"  required="true" vtype="maxLength:100"/>
</div>

<div id='ht1' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>资金支付方式：</label>
	<input id="conDetail.payWay" name="conDetail.payWay" required="true" align="center" class="mini-dictradiogroup" valueField="dictID"  dictTypeId="CDXY0144"/>
</div>
<div id='ht2' class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	<label>提前还款最低金额：</label>
	<input id="conDetail.leastPrepayAmount" name="conDetail.leastPrepayAmount"  required="true" class="nui-textbox nui-form-input" dataType="currency"  vtype="float;maxLength:20"/>
	<label>提前还款基数：</label>
	<input id="conDetail.prepayJs" name="conDetail.prepayJs"   class="nui-textbox nui-form-input" required="true"  dataType="currency" vtype="float;maxLength:20"/>
	<label>违约金比例%：</label>
	<input id="conDetail.prepayMakeupRate" name="conDetail.prepayMakeupRate"  class="nui-textbox nui-form-input"  required="true" vtype="float;range:0,99.999999"  />%
</div>
<script type="text/javascript">
	$("#sq").css("display","none");
	$("#ht1").css("display","none");
	$("#ht2").css("display","none");
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
	}
	if(stepFlag=='con'){
		$("#ht1").css("display","block");
		$("#ht2").css("display","block");
	}
</script>