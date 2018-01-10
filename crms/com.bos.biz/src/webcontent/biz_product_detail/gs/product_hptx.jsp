<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label>贴现资金用途：</label>
	<input id="productDetail.txzjyt" name="productDetail.txzjyt" class="nui-textbox nui-form-input" required="true" vtype="maxLength:300"/>
	<label>汇票种类：</label>
	<input name="productDetail.hpzl" id="productDetail.wylx" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0203"  emptyText="--请选择--"/>
	<label>是否协议付息：</label>
	<input name="productDetail.sfxyfx" id="productDetail.sfxyfx" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  emptyText="--请选择--" onvaluechanged="sffx"/>
</div>
<div id='sq2' class="nui-dynpanel"  columns="4">	
	<label>汇票张数：</label>
	<input id="productDetail.hpzs" name="productDetail.hpzs"   class="nui-textbox nui-form-input" required="true"  vtype="int;maxLength:8"/>
</div>
<div id='sq1' class="nui-dynpanel"  columns="4">	
	<label>卖方承担利息比例（%）：</label>
	<input id="productDetail.mfcnblBuy" name="productDetail.mfcnblBuy" class="nui-textbox nui-form-input"  required="true" vtype="float;range:0,100" onblur="cnblchange"/>
	<label>买方承担利息比例（%）：</label>
	<input id="productDetail.mfcnblSell" name="productDetail.mfcnblSell" class="nui-text nui-form-input" required="true"/>
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>汇票张数：</label>
	<input id="conDetail.hpzs" name="conDetail.hpzs"  class="nui-text nui-form-input"/>
	<!-- <label>汇票编号：</label>
	<input id="conDetail.hpbh" name="conDetail.hpbh"  class="nui-text nui-form-input" />
	<label>币种：</label>
	<input id="conDetail.currencyCd" name="conDetail.currencyCd"  class="nui-text nui-form-input" dictTypeId="CD000001"/>
	<label>汇票(总)金额：</label>
	<input id="conDetail.hpTotalAmt" name="conDetail.hpTotalAmt"  vtype="float;maxLength:20;range:100,100000000000" required="true" class="nui-text nui-form-input" dataType="currency"/>
		 -->		
	<label>贴现起期：</label>
	<input id="conDetail.txqq" name="conDetail.txqq" formate="yyyy-mm-dd"  allowInput="false" class="nui-datepicker nui-form-input"/>
</div>
<script type="text/javascript">
	$("#sq").css("display","none");
	$("#sq1").css("display","none");
	$("#sq2").css("display","none");
	$("#ht").css("display","none");
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
		$("#sq2").css("display","block");
	}
	if(stepFlag=='con'){
		$("#ht").css("display","block");
	}
	function cnblchange(){
		var mfcnblBuy = nui.get("productDetail.mfcnblBuy").getValue();
		if(mfcnblBuy!=null && mfcnblBuy!=''){
			var mfcnblSell = 100-mfcnblBuy;
			nui.get("productDetail.mfcnblSell").setValue(mfcnblSell);
			nui.get("productDetail.mfcnblSell").validate();
		}
	}
	
</script>