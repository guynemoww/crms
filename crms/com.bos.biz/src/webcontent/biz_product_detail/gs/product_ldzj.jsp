<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
</div>
<div id='ht1' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>资金支付方式：</label>
	<input id="conDetail.payWay" name="conDetail.payWay" required="true" align="center" class="mini-dictradiogroup" dictTypeId="CDXY0144" required="true" />
</div>
<div id='ht2' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>提前还款最低金额：</label>
	<input id="conDetail.leastPrepayAmount" name="conDetail.leastPrepayAmount"  required="true" class="nui-textbox nui-form-input" dataType="currency" vtype="float;maxLength:20"/>
	<label>提前还款基数：</label>
	<input id="conDetail.prepayJs" name="conDetail.prepayJs"   class="nui-textbox nui-form-input" required="true"  dataType="currency" vtype="float;maxLength:20" />
</div>
<div id='ht3' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label id="sfjmrhsx">是否军民融合属性：</label>
	<input name="conDetail.sfjmrhsx" id="conDetail.sfjmrhsx" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" enabled="false"/>
	<label id="jmrhsx">军民融合属性：</label>
	<input id="conDetail.jmrhsx" name="conDetail.jmrhsx" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_JMRHSX001" enabled="false"/>
</div>


<div id='htdc' class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	<label>提前还款是否收取违约金：</label>
	<input id="conDetail.prepaymentPenalty" name="conDetail.prepaymentPenalty"  class="nui-dictcombobox nui-form-input"  dictTypeId="XD_0002" required="true" enabled="true" onvaluechanged="onselectPrepaymentPenalty1" />
	<label id="prepayMakeupRate">违约金比例%：</label>
	<input id="conDetail.prepayMakeupRate" name="conDetail.prepayMakeupRate" required="true"   class="nui-textbox nui-form-input"   vtype="float;range:0,99.999999"  />
</div>

<script type="text/javascript">
	$("#sq").css("display","none");
	$("#ht1").css("display","none");
	$("#ht2").css("display","none");
	$("#ht3").css("display","none");
	$("#htdc").css("display","none");
	var cycleIndCon =  "<%=request.getParameter("cycleIndCon") %>";//循环标志
	var productType =  "<%=request.getParameter("productType") %>";//贷种
	if(cycleIndCon==null||cycleIndCon==''||cycleIndCon=='null'){
		cycleIndCon ='1';
	}
	
	var stepFlag =  "<%=request.getParameter("stepFlag") %>";//阶段标志
	if(stepFlag=='biz'){
		$("#sq").css("display","block");
	}
	if(stepFlag=='con'){
		if('01001001'==productType||'01001040'==productType
		 ||'01001041'==productType||'01001042'==productType){
			$("#ht1").css("display","block");
			$("#ht2").css("display","block");
			$("#ht3").css("display","block");
		}else{
			$("#ht1").css("display","block");
			$("#ht2").css("display","block");
		}
		if(cycleIndCon=='0'){
			$("#htdc").css("display","block");
		}
	}
</script>