<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label>保证金比例不低于(%)：</label>
	<input id="productDetail.bzjblbdy" name="productDetail.bzjblbdy" required="true"  class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0,100"/>
</div>

<div id='ht' class="nui-dynpanel"  columns="4">
	<label>保证金比例(%)：</label>
	<input id="conDetail.bzjbl" name="conDetail.bzjbl" required="true" class="nui-textbox nui-form-input" onblur="bzjblchange" vtype="int;range:0,100"/>%
	<label>保证金金额：</label>
	<input id="conDetail.bzj" name="conDetail.bzj" required="true"  class="nui-text nui-form-input"  dataType="currency" vtype="float;maxLength:20"/>
	<input id="conDetail.bzjblbdy" name="conDetail.bzjblbdy" class="nui-hidden nui-form-input"  />
</div>
<script type="text/javascript">
	$("#sq").css("display","none");
	$("#ht").css("display","none");
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
	}
	if(stepFlag=='con'){
		$("#ht").css("display","block");
	}
</script>