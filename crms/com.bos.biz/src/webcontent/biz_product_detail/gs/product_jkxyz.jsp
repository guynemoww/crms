<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label>即期/远期：</label>
	<input id="productDetail.jyq" name="productDetail.jyq" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0208" onvaluechanged="daysChange" />
	<label>远期天数：</label>
	<input id="productDetail.yqts" name="productDetail.yqts" required="true" class="nui-textbox nui-form-input" />
	<label>保证金比例不低于(%)：</label>
	<input id="productDetail.bzjblbdy" name="productDetail.bzjblbdy" required="true"  class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0,100"/>%
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>即期/远期：</label>
	<input id="conDetail.jyq" name="conDetail.jyq" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0208" onvaluechanged="daysChange" />
	<label>远期天数：</label>
	<input id="conDetail.yqts" name="conDetail.yqts" required="true" class="nui-textbox nui-form-input" />
	<label>开证上浮比例(%)：</label>
	<input id="conDetail.kzsfbl" name="conDetail.kzsfbl"  class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:11"/>%
	<label>开证下浮比例(%)：</label>
	<input id="conDetail.kzxfbl" name="conDetail.kzxfbl"  class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:11"/>%
	<label>受益人名称：</label>
	<input id="conDetail.syrmc" name="conDetail.syrmc"   class="nui-textbox nui-form-input"  required="true"/>
	<label>信用证有效期：</label>
	<input id="conDetail.xyzyxq" name="conDetail.xyzyxq"  class="nui-datepicker nui-form-input"  allowInput="false" required="true"/>
</div>
<div id='fk' class="nui-dynpanel"  columns="4" style="width:99.5%;">
</div>
<script type="text/javascript">
	$("#sq").css("display","none");
	$("#ht").css("display","none");
	$("#fk").css("display","none");
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
	}
	if(stepFlag=='con'){
		$("#ht").css("display","block");
	}
	if(stepFlag=='pay'){
		$("#fk").css("display","block");
	}
	//当“信用证付款期限”为“即期”时远期天数为0
	function daysChange() {
		var days = nui.get("productDetail.jyq").getValue();
		if ("1" == days) {
			nui.get("productDetail.yqts").setValue("0");
			nui.get("productDetail.yqts").setEnabled(false);
		} else {
			nui.get("productDetail.yqts").setValue("");
			nui.get("productDetail.yqts").setEnabled(true);
		}
	}
</script>