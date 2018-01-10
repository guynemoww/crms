<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<!-- <label>入账账号：</label>
	<input id="conDetail.inAccNo" name="conDetail.inAccNo"  class="nui-textbox nui-form-input"  required="true" /> -->
	<label>托收号：</label>
	<input id="conDetail.tsh" name="conDetail.tsh"  required="true" class="nui-textbox nui-form-input" />
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