<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel"  columns="4">
	
</div>
<div id='ht' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	<label>即期/远期：</label>
	<input id="conDetail.jyq" name="conDetail.jyq" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0208" onvaluechanged="daysChange" />
	<label>远期天数：</label>
	<input id="conDetail.yqts" name="conDetail.yqts" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:1,100000000000"/>
	<label>保函类型：</label>
	<input name="conDetail.bhlx" id="conDetail.bhlx" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0235" emptyText="--请选择--" />
	<label>开立日期：</label>
	<input name="conDetail.klrq" id="conDetail.klrq" class="nui-datepicker nui-form-input" required="true" allowInput="false" />
	<label>到期日期：</label>
	<input name="conDetail.dqrq" id="conDetail.dqrq" class="nui-datepicker nui-form-input" required="true" allowInput="false" onblur="validateDqrq" />
	<!--<label>贷款受益人：</label>
	<input id="conDetail.syr" name="conDetail.syr" required="true" class="nui-textbox nui-form-input" />-->
	<label>保证金账号：</label>
	<input id="conDetail.bzjzh" name="conDetail.bzjzh"  class="nui-textbox nui-form-input" />
	<label>保证金币种：</label>
	<input id="conDetail.bzjbz" name="conDetail.bzjbz"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input"  dictTypeId="CD000001" emptyText="--请选择--" onvaluechanged="bzjChange"/>
	<label>保证金比例不低于(%)：</label>
	<input id="conDetail.bzjblbdy" name="conDetail.bzjblbdy" class="nui-textbox nui-form-input"  vtype="float;maxLength:11;range:0,100000000000" onblur="bzjChange"/>
	<label>保证金金额：</label>
	<input id="conDetail.bzjje" name="conDetail.bzjje" vtype="float;maxLength:20;range:0,100000000000"  class="nui-textbox nui-form-input" dataType="currency" onblur="bzjChange"/>
	<!-- <label>贸易合同号：</label>
	<input id="conDetail.myhth" name="conDetail.myhth" required="true" class="nui-textbox nui-form-input" />
	<label>贸易合同金额：</label>
	<input id="conDetail.myhtje" name="conDetail.myhtje" vtype="float;maxLength:20;range:100,100000000000" required="true" class="nui-textbox nui-form-input" dataType="currency" /> -->
</div>
<div id='fk' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	
</div>
<script type="text/javascript">
	window.onload = function() {
	
		$("#sq").css("display", "none");
		$("#ht").css("display", "none");
		$("#fk").css("display", "none");
		var stepFlag = <%="\"" + request.getParameter("stepFlag") + "\""%>;//阶段标志
		if (stepFlag == 'biz') {
			$("#sq").css("display", "block");
		}
		if (stepFlag == 'con') {
			$("#ht").css("display", "block");
			var bz = nui.get("conDetail.bzjbz").getValue();
			if (bz == '' || bz == null || bz == 'null' || bz == 'undefined') {
				//var conCurrencyCd = nui.get("tbConContractInfo.currencyCd").getValue();//合同币种 
				//nui.get("conDetail.bzjbz").setValue(conCurrencyCd);
			}
		}
		if(stepFlag=='pay'){
			$("#fk").css("display","block");
		}
	}
	//开立日期和到期日期验证
	function validateDqrq() {
		var dqrq = nui.get("conDetail.dqrq").getValue();
		if (dqrq != '' && dqrq != null && dqrq != 'null' && dqrq != 'undefined') {
			var klrq = nui.get("conDetail.klrq").getValue();
			if (klrq == '' || klrq == null || klrq == 'null' || klrq == 'undefined') {
				nui.get("conDetail.dqrq").setValue('');
				nui.alert("请先选择【开立日期】");
				return;
			}
			if (dqrq.substr(0, 10) < klrq.substr(0, 10)) {
				nui.get("conDetail.dqrq").setValue('');
				nui.alert("到期日期不能小于开立日期");
				return;
			}
		}
	}
	//当“信用证付款期限”为“即期”时远期天数为0
	function daysChange() {
		var days = nui.get("conDetail.jyq").getValue();
		if ("1" == days) {
			nui.get("conDetail.yqts").setValue("0");
			nui.get("conDetail.yqts").setEnabled(false);
		} else {
			nui.get("conDetail.yqts").setValue("");
			nui.get("conDetail.yqts").setEnabled(true);
		}
	}
	//保证金金额和比例验证
	function bzjChange(){
		var bzjbz = nui.get("conDetail.bzjbz").getValue();//保证金币种
		var bzjje = nui.get("conDetail.bzjje").getValue();//保证金金额
		var bzjbl = nui.get("conDetail.bzjblbdy").getValue();//保证金比例不低于
		var bzjRate = "1";//保证金币种的汇率
		var bz = nui.get("tbConContractInfo.currencyCd").getValue();//合同币种 
		var conInfoRmbAmt = nui.get("tbConContractInfo.rmbAmt").getValue();//合同折算人民币金额
		var conAmt = nui.get("tbConContractInfo.contractAmt").getValue();//合同金额
		var minBzjje;//最低保证金金额
		if(bzjbz != '' && bzjbz != null && bzjbz != 'null' && bzjbz != 'undefined'){
			if(bz!==bzjbz){//保证金币种和合同币种不一致 
			if (bzjbz != 'CNY'){//保证金币种和合同币种不一致而且保证金币种不是人民币
            	var json = nui.encode({"bz":bzjbz});
				$.ajax({
        			url: "com.bos.conInfo.conInfoSxxy.getChangeRate.biz.ext",
        			type: 'POST',
        			data: json,
        			cache: false,
        			contentType:'text/json',
        			success: function (text) {
        				if(text){
        					if(text.validityInd=="1"){
        						bzjRate = parseFloat(text.disRateOfRmb)/parseFloat(100);
        						if (bzjbl != '' && bzjbl != null && bzjbl != 'null' && bzjbl != 'undefined') {
									minBzjje = parseFloat(conInfoRmbAmt)*parseFloat(bzjbl)/parseFloat(100)/parseFloat(bzjRate);
								}
								if (bzjje != '' && bzjje != null && bzjje != 'null' && bzjje != 'undefined') {
									if((parseFloat(bzjje))<parseFloat(minBzjje)){
										nui.alert("最低保证金金额["+bzjbz+":"+minBzjje+"]");
										nui.get("conDetail.bzjje").setValue("");
										return;
									}
								}
        					}else{
        						alert("未获取到币种["+bzjbz+"]的汇率信息");
        						nui.get("conDetail.bzjbz").setValue("");
        						return;
        					}
        				}
        			}
        		});	
			}else{//保证金币种和合同币种不一致但是保证金币种是人民币
        		if (bzjbl != '' && bzjbl != null && bzjbl != 'null' && bzjbl != 'undefined') {
					minBzjje = parseFloat(conInfoRmbAmt)*parseFloat(bzjbl)/parseFloat(100)/parseFloat(bzjRate);
				}
				if (bzjje != '' && bzjje != null && bzjje != 'null' && bzjje != 'undefined') {
					if((parseFloat(bzjje))<parseFloat(minBzjje)){
						nui.alert("最低保证金金额["+bzjbz+":"+minBzjje+"]");
						nui.get("conDetail.bzjje").setValue("");
						return;
					}
				}
			}
		}else{//保证金币种和合同币种一致 
			if (bzjbl != '' && bzjbl != null && bzjbl != 'null' && bzjbl != 'undefined') {
				minBzjje = parseFloat(conAmt)*parseFloat(bzjbl)/parseFloat(100)/parseFloat(bzjRate);
			}
			if (bzjje != '' && bzjje != null && bzjje != 'null' && bzjje != 'undefined') {
				if((parseFloat(bzjje))<parseFloat(minBzjje)){
					nui.alert("最低保证金金额["+bzjbz+":"+minBzjje+"]");
					nui.get("conDetail.bzjje").setValue("");
					return;
					}
				}
			}
		}
	}
</script>