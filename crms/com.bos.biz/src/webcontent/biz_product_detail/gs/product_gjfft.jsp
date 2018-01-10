<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<!-- <label>入账账号：</label>
	<input id="conDetail.rzzh" name="conDetail.rzzh" required="true" class="nui-textbox nui-form-input"  /> -->
	<label id="ywbh">业务编号：</label>
	<input id="conDetail.ywbh" name="conDetail.ywbh"  required="true" class="nui-textbox nui-form-input"/>
	<label>产品类型：</label>
	<input id="conDetail.cplx" name="conDetail.cplx" required="true" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FFTLX0001" emptyText="--请选择--" />
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