<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>入账账号：</label>
	<input id="conDetail.rzhm" name="conDetail.rzhm"  class="nui-textbox nui-form-input"  required="true" />
	<!-- <label>业务号码：</label>
	<input id="conDetail.ywhm" name="conDetail.ywhm"  class="nui-textbox nui-form-input"   required="true" /> -->
	<label>发票号：</label>
	<input id="conDetail.fph" name="conDetail.fph"  vtype="maxLength:20" class="nui-textbox nui-form-input"   required="true" />
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