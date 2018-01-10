<!-- chenchuan 仲裁方式公共页面 -->
<%@page pageEncoding="UTF-8"%>
	<div class="nui-dynpanel" columns="4">
		<label class="nui-form-label">争议解决方式：</label>
		<input id="tbConAttachedInfo.arbitrateType"  name="tbConAttachedInfo.arbitrateType" data="data" valueField="dictID" required="true" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0209" emptyText="--请选择--"  onvaluechanged="arbiTypeChange"/>
	</div>
	
	<div id="zcdiv" class="nui-dynpanel" columns="4">
		<label class="nui-form-label">仲裁委员会名称：</label>	
		<input id="tbConAttachedInfo.arbitrateName" class="nui-textbox nui-form-input" name="tbConAttachedInfo.arbitrateName" required="true" vtype="maxLength:255"/>	
							
		<label class="nui-form-label">仲裁委员会地址：</label>
		<input id="tbConAttachedInfo.arbitrateAddress" class="nui-textbox nui-form-input" name="tbConAttachedInfo.arbitrateAddress" required="true" vtype="maxLength:255"/>
	</div>
				
	<div id="otherediv" class="nui-dynpanel">
		<label class="nui-form-label">其它方式：</label>	
		<input id="tbConAttachedInfo.other" class="nui-textbox nui-form-input" name="tbConAttachedInfo.other" required="true" vtype="maxLength:500"/>	
	</div>
<script>
//仲裁方式变更时触发
	function arbiTypeChange(e){
		if('02'==e.value){//如果选仲裁，显示仲裁模块,并将“其他”项隐藏并清空
			$("#zcdiv").css("display","block");
			$("#otherediv").css("display","none");
		}else if('03'==e.value){//如果选其他，显示其他模块,并将“仲裁”隐藏并清空
			$("#zcdiv").css("display","none");
			$("#otherediv").css("display","block");
		}else{//选诉讼或请选择，将“其他”及“仲裁”项隐藏并清空
			$("#otherediv").css("display","none");
			$("#zcdiv").css("display","none");
		}
	}
 </script>