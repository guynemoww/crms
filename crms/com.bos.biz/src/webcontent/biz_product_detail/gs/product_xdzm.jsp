<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label>是否可无条件撤销：</label>
	<input name="productDetail.sfkwtjcx" id="productDetail.sfkwtjcx" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"   emptyText="--请选择--"/>
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>项目名称：</label>
	<input id="conDetail.itemName" name="conDetail.itemName" required="true"  class="nui-textbox nui-form-input" vtype="maxLength:150"/>
	<label>费用支付方式：</label>
	<input name="conDetail.payWay" id="conDetail.payWay" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0218"   emptyText="--请选择--"/>		
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
</script>