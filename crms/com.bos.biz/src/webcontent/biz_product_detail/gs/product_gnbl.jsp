<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel" columns="4">
	<label>保理类型：</label>
	<input name="productDetail.bllx" id="productDetail.bllx" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0204" emptyText="--请选择--" />
	<label>是否有追索权：</label>
	<input name="productDetail.sfyzsq" id="productDetail.sfyzsq" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="--请选择--" />
	<label>保理贴付方式：</label>
	<input name="productDetail.bltffs" id="productDetail.bltffs" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD8012" emptyText="--请选择--" />
</div>
<div id='ht' class="nui-dynpanel" columns="4">
	<label>保理类型：</label>
	<input name="conDetail.bllx" id="conDetail.bllx" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0204" emptyText="--请选择--" />
	<label>是否有追索权：</label>
	<input name="conDetail.sfyzsq" id="conDetail.sfyzsq" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="--请选择--" />
	<label>保理贴付方式：</label>
	<input name="conDetail.bltffs" id="conDetail.bltffs" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD8012" emptyText="--请选择--" />
</div>
<!--
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>应收账款回收专户户名：</label>
	<input id="conDetail.yszkhszhhm" name="conDetail.yszkhszhhm" required="true" class="nui-textbox nui-form-input" />
	<label>应收账款回收专户账号：</label>
	<input id="conDetail.yszkhszhzh" name="conDetail.yszkhszhzh" required="true" class="nui-textbox nui-form-input" />
	<label>应收账款回收专户是否为保证金账户：</label>
	<input id="conDetail.sfwbzjzh" name="conDetail.sfwbzjzh"  required="true" valueField="dictID" 	class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  />
	<label>宽限期：</label>
	<input id="conDetail.gracePeriod" name="conDetail.gracePeriod"  required="true"  class="nui-textbox nui-form-input"  />
	<label>融资费收取方式：</label>
	<input name="conDetail.rzfsqfs" id="conDetail.rzfsqfs" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0217" emptyText="--请选择--"/>
	<label>保理类型：</label>
	<input name="conDetail.bllx" id="conDetail.bllx" required="true" data="data" valueField="dictID" 
	class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0204" emptyText="--请选择--"/>
	<label>基本收购款比例：</label>
	<input id="conDetail.jbsgkbl" name="conDetail.jbsgkbl"   class="nui-textbox nui-form-input" required="true"  vtype="float;" />%
	<label>扣除费用1：</label>
	<input id="conDetail.kcfy1" name="conDetail.kcfy1"  class="nui-textbox nui-form-input"    vtype="float;maxLength:20"  dataType="currency"/>
	<label>扣除费用2：</label>
	<input id="conDetail.kcfy2" name="conDetail.kcfy2"  class="nui-textbox nui-form-input"    vtype="float;maxLength:20"  dataType="currency"/>
	<label>追加收购参数：</label>
	<input id="conDetail.zjsgcs" name="conDetail.zjsgcs"  class="nui-textbox nui-form-input"  required="true" />
	<label>收购款比例：</label>
	<input id="conDetail.sgkbl" name="conDetail.sgkbl"  class="nui-textbox nui-form-input"  required="true"  vtype="float;"/>%
	<label>重大事项标的金额：</label>
	<input id="conDetail.zdsxbdje" name="conDetail.zdsxbdje"  class="nui-textbox nui-form-input"  required="true" dataType="currency" vtype="float;maxLength:20"/>
</div>
-->

<div id='fk' class="nui-dynpanel" columns="4" style="width: 99.5%;"></div>
<script type="text/javascript">
	window.onload = function() {
		$("#sq").css("display", "none");
		$("#ht").css("display", "none");
		$("#fk").css("display", "none");
		var stepFlag =
<%="\"" + request.getParameter("stepFlag") + "\""%>
	;//阶段标志
		if (stepFlag == 'biz') {
			$("#sq").css("display", "block");
		} else if (stepFlag == 'con') {
			$("#ht").css("display", "block");
		} else if (stepFlag == 'pay') {
			$("#fk").css("display", "block");
		}
	}
</script>