<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	<label>保证金比例不低于(%)：</label>
	<input id="productDetail.bzjblbdy" name="productDetail.bzjblbdy"  class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:11;range:0,100"/>
</div>
<div id='ht' class="nui-dynpanel"  columns="4">
	<label>受益人：</label>
	<input id="conDetail.syr" name="conDetail.syr" required="true"  class="nui-textbox nui-form-input" vtype="maxLength:100"/>
	<label>保函种类：</label>
	<input name="conDetail.bhzl" id="conDetail.bhzl" required="true" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0207"   emptyText="--请选择--"/>
	
	<label>保函起始日：</label>
	<input id="conDetail.klrq" name="conDetail.klrq" required="true" class="nui-datepicker nui-form-input"  />
	<label>保函到期日：</label>
	<input id="conDetail.dqrq" name="conDetail.dqrq" required="true" class="nui-datepicker nui-form-input"  />
	
	<label id="jcxmmc">基础合同项目名称：</label>
	<input id="conDetail.jcxmmc" name="conDetail.jcxmmc" required="true"  class="nui-textbox nui-form-input" vtype="maxLength:1000"/>
	<label id="sfbzwb">是否我行标准文本：</label>
	<input name="conDetail.sfbzwb" id="conDetail.sfbzwb" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
	<label id="sffcsx">是否支持分次生效：</label>
	<input name="conDetail.sffcsx" id="conDetail.sffcsx" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
	
	<label>保证金比例(%)：</label>
	<input id="conDetail.bzjbl" name="conDetail.bzjbl" required="true" class="nui-textbox nui-form-input" onblur="bzjblchange" vtype="float;range:0,100"/>
	<label>保证金金额：</label>
	<input id="conDetail.bzjje" name="conDetail.bzjje" vtype="float;maxLength:20" required="true" class="nui-text nui-form-input" dataType="currency"/>
	<label>手续费逾期不付收取违约金比例（万分之）：</label>
	<input id="conDetail.wyjbl" name="conDetail.wyjbl" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input"/>
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