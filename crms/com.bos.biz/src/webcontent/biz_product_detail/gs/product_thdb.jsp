<%@page pageEncoding="UTF-8"%>
<div id='sq' class="nui-dynpanel" columns="4" onlo>
</div>

<div id='ht' class="nui-dynpanel" columns="4">
	<!-- <label>入账账号：</label>
	<input id="conDetail.rzzh" name="conDetail.rzzh" required="true" class="nui-textbox nui-form-input"  /> -->
	<label>审核号码：</label>
	<input id="conDetail.fph" name="conDetail.fph" required="true" class="nui-textbox nui-form-input" vtype="maxLength:16"/>
	<label>信用证号码：</label>
	<input id="conDetail.xyzhm" name="conDetail.xyzhm" required="true" class="nui-textbox nui-form-input" vtype="maxLength:16"/>
	<label>提单号码(多个用逗号隔开)：</label>
	<input id="conDetail.tdhm" name="conDetail.tdhm" required="true" data="data" class="nui-textarea nui-form-input" vtype="maxLength:180"/>
	<label>提单日期：</label>
	<input id="conDetail.tdrq" name="conDetail.tdrq"  class="nui-datepicker nui-form-input" required="true" allowInput="false" onblur="validateDqrq" />
	<label>到期日期：</label>
	<input id="conDetail.dqrq" name="conDetail.dqrq"  class="nui-datepicker nui-form-input" required="true" allowInput="false" onblur="validateDqrq" />
	<label>保证金账号：</label>
	<input id="conDetail.bzjzh" name="conDetail.bzjzh"  class="nui-textbox nui-form-input" vtype="maxLength:20"/>
	<label>保证金币种：</label>
	<input id="conDetail.bzjbz" name="conDetail.bzjbz"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input"  dictTypeId="CD000001" emptyText="--请选择--" onvaluechanged="bzjChange"/>
	<label>保证金比例不低于(%)：</label>
	<input id="conDetail.bzjblbdy" name="conDetail.bzjblbdy" class="nui-textbox nui-form-input"  vtype="float;maxLength:11;range:0,100000000000" onblur="bzjChange"/>
	<label>保证金金额：</label>
	<input id="conDetail.bzjje" name="conDetail.bzjje" vtype="float;maxLength:20;range:0,100000000000"  class="nui-textbox nui-form-input" dataType="currency" onblur="bzjChange"/>
</div>

<div id='fk' class="nui-dynpanel"  columns="4" style="width:99.5%;">
	
</div>

<script type="text/javascript">
	window.onload = function() {
		$("#sq").css("display","none");
		$("#ht").css("display","none");
		$("#fk").css("display","none");
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
	function validateDqrq() {
		var dqrq = nui.get("conDetail.dqrq").getValue();
		if (dqrq != '' && dqrq != null && dqrq != 'null' && dqrq != 'undefined') {
			var tdrq = nui.get("conDetail.tdrq").getValue();
			if (tdrq == '' || tdrq == null || tdrq == 'null' || tdrq == 'undefined') {
				nui.get("conDetail.dqrq").setValue('');
				nui.alert("请先选择【提单日期】");
				return;
			}
			if (dqrq.substr(0, 10) < tdrq.substr(0, 10)) {
				nui.get("conDetail.dqrq").setValue('');
				nui.alert("到期日期不能小于提单日期");
				return;
			}
		}
	}
	
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