<%@page pageEncoding="UTF-8"%>
<div class="nui-dynpanel" columns="4"  id="tableloanrate">		
	<label id="lllx">利率类型：</label>
	<input name="loanrate.rateType" id="loanrate.rateType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1016" required="true" onvaluechanged="onselectType"/>

	<label id="floatWay">浮动方式：</label>
	<input name="loanrate.floatWay" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1147" id="loanrate.floatWay" required="true"/>

	<label id="rateFloatProportionq">浮动比例(%)：</label>
	<input id="loanrate.rateFloatProportion" name="loanrate.rateFloatProportion" class="nui-textbox nui-form-input" required="true"  vtype="float;negative;maxLength:11" onblur="onRateFloat"/>

	<label id="yearRate">申请利率（%）：</label>
	<input id="loanrate.yearRate" name="loanrate.yearRate" class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0.00000001,60" required="true" onblur="onYearrate"/>
	
	<label id="isChangeRate">利率调整方式：</label>
	<input id="loanrate.irUpdateFrequency" name="loanrate.irUpdateFrequency" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_SXCD1148"  />
			
	<label id="jxzq">结息周期：</label>
	<input name="loanrate.interestCollectType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1018" id="loanrate.interestCollectType" required="true" />
	
	<!-- <label id="sftx">是否贴息：</label>
	<input name="loanrate.discFlag" class="nui-dictcombobox nui-form-input" dictTypeId="XD_TXBZ001" id="loanrate.discFlag" required="true" />
	
	<label id="sqfy">是否收取费用：</label>
	<input name="loanrate.feeFlag" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FYSQ001" id="loanrate.feeFlag" required="true" />
	 -->
	<label id="jjrsybz">节假日顺延标志：</label>
	<input name="loanrate.holidayFlg" id="loanrate.holidayFlg"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  onvaluechanged="conChangeHoliday" required="true"/>

	<label id="jjrlxclfs">节假日利息处理方式：</label>
	<input name="loanrate.holidayIntFlg" id="loanrate.holidayIntFlg" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0234"/>

	<label id="kxqfs">宽限期方式：</label>
	<input name="loanrate.gracePeriodType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0210" id="loanrate.gracePeriodType" required="true" onvaluechanged="onselectGrace"  enabled="false"/>																

	<label id="gracePeriodDay">宽限期天数：</label>
	<input name="loanrate.gracePeriodDay" class="nui-textbox nui-form-input"  id="loanrate.gracePeriodDay" vtype="int;range:1,10" required="true" />																

	<label id="kxqlxclfs">宽限期利息处理方式：</label>
	<input name="loanrate.graceCountIntFlag" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0234" id="loanrate.graceCountIntFlag" required="true"/>																

	<label id="ovardueRate">逾期罚息率（%）：</label>
	<input id="loanrate.overdueRateUpProportion" name="loanrate.overdueRateUpProportion" class="nui-textbox nui-form-input"  vtype="int;maxLength:11;range:30,50" required="true" value="50"/>

	<input id="loanrate.loanrateId" name="loanrate.loanrateId" class="nui-hidden nui-form-input" />
	<input name="loanrate.floatType" class="nui-hidden nui-form-input" dictTypeId="XD_SXCD1145" id="loanrate.floatType"/>
</div>	

<script type="text/javascript">
	
	//在主页面初始化时也调用此方法，要做特别处理
	//利率类型触发事件
    function onselectType(){
    	//节假日顺延标志默认为“不顺延”
 		//nui.get("loanrate.holidayFlg").setData(getDictData('XD_0002','str','0,1'));
 		nui.get("loanrate.holidayFlg").setValue('0');
		var reateType= nui.get("loanrate.rateType").getValue();
		if(reateType=="2"){//浮动利率
			//隐藏固定利率
			dealRate('fixed','hide');
			//显示浮动利率
			dealRate('float','show');
			//利率调整方式--浮动时不能选不调整
			nui.get("loanrate.irUpdateFrequency").setEnabled(true);
			nui.get("loanrate.irUpdateFrequency").setData(getDictData('XD_SXCD1148','str','01,02,03,04,06'));
			nui.get("loanrate.irUpdateFrequency").setValue('04');
			if(nui.get("loanrate.irUpdateFrequency")){
				if(nui.get("loanrate.irUpdateFrequency").getValue()=='05'){
					nui.get("loanrate.irUpdateFrequency").setValue('');
				}
			}
		}else if(reateType=="1"){//固定利率
			//显示固定利率
			dealRate('fixed','show');
			//隐藏浮动利率
			dealRate('float','hide');
			//利率调整方式--固定时只能选不调整
			nui.get("loanrate.irUpdateFrequency").setEnabled(false);
			nui.get("loanrate.irUpdateFrequency").setData(getDictData('XD_SXCD1148','str','05'));
			nui.get("loanrate.irUpdateFrequency").setValue('05');
		}else{//非反显
			nui.get("loanrate.irUpdateFrequency").setValue('');
			nui.get("loanrate.irUpdateFrequency").setEnabled(false);
			dealRate('fixed','hide');
			dealRate('float','hide');
		}
		//刷新table
		nui.get('tableloanrate').refreshTable();
	}
	
	//隐藏、显示利率
	function dealRate(rateType,dealType){
		if(rateType=='fixed'){//固定
			if(dealType == 'hide'){
				$("#yearRate").css("display","none");
				nui.get("loanrate.yearRate").hide();
				nui.get("loanrate.yearRate").setValue('');
			}else if(dealType == 'show'){
				$("#yearRate").css("display","block");
				nui.get("loanrate.yearRate").show();
			}
		}else if(rateType=='float'){//浮动
			if(dealType == 'hide'){
				nui.get("loanrate.floatWay").setValue('');
				nui.get("loanrate.rateFloatProportion").setValue('');
				$("#floatWay").css("display","none");
				$("#rateFloatProportionq").css("display","none");
				nui.get("loanrate.floatWay").hide();
				nui.get("loanrate.rateFloatProportion").hide();
			}else if(dealType == 'show'){
				$("#floatWay").css("display","block");
				$("#rateFloatProportionq").css("display","block");
				nui.get("loanrate.floatWay").show();
				nui.get("loanrate.rateFloatProportion").show();
			}
		}
		//刷新table
		nui.get('tableloanrate').refreshTable();
	}
	//节假日顺延标志选否，节假日利息处理方式为空且不可选；
	//节假日顺延标志选择“是”，则“节假日利息处理方式默认为追加罚息，不可修改
	function conChangeHoliday(){
		var holidayFlag = nui.get("loanrate.holidayFlg").getValue();
		if(holidayFlag == '1'){
			$("#jjrlxclfs").css("display","block");
			nui.get("loanrate.holidayIntFlg").show();
			nui.get("loanrate.holidayIntFlg").setValue('1');
			nui.get("loanrate.holidayIntFlg").setEnabled(false);
		}else{//不顺延或者为空
			$("#jjrlxclfs").css("display","none");
			nui.get("loanrate.holidayIntFlg").hide();
			nui.get("loanrate.holidayIntFlg").setValue('');
		}
		//刷新table
		nui.get('tableloanrate').refreshTable();
	}
	
	/* 宽限期--
		宽限期方式选择“无宽限期”，则“宽限期利息处理方式”不展示；
		宽限期方式选择“宽限至月底/按日计算”，则“宽限期利息处理方式”默认为追加罚息，不可修改 */
 	function onselectGrace(){
		var graceType = nui.get("loanrate.gracePeriodType").getValue();
		if(graceType=='2'){//按日
			$("#gracePeriodDay").css("display","block");
			$("#kxqlxclfs").css("display","block");
			nui.get("loanrate.gracePeriodDay").show();
			nui.get("loanrate.graceCountIntFlag").show();
			
			//默认追加罚息不可修改
			nui.get("loanrate.graceCountIntFlag").setValue('1');
			nui.get("loanrate.graceCountIntFlag").setEnabled(false);
		}else if(graceType == '1'){//宽限至月底
			$("#gracePeriodDay").css("display","none");
			$("#kxqlxclfs").css("display","block");
			nui.get("loanrate.gracePeriodDay").hide();
			nui.get("loanrate.graceCountIntFlag").show();
			
			nui.get("loanrate.gracePeriodDay").setValue('');
			
			//默认追加罚息不可修改
			nui.get("loanrate.graceCountIntFlag").setValue('1');
			nui.get("loanrate.graceCountIntFlag").setEnabled(false);
		}else{//无宽限期
			$("#gracePeriodDay").css("display","none");
			$("#kxqlxclfs").css("display","none");
			nui.get("loanrate.gracePeriodDay").hide();
			nui.get("loanrate.graceCountIntFlag").hide();
			
			nui.get("loanrate.gracePeriodDay").setValue('');
			nui.get("loanrate.graceCountIntFlag").setValue('');
			nui.get("loanrate.graceCountIntFlag").setEnabled(true);
		}
		//刷新table
		nui.get('tableloanrate').refreshTable();
	}
 	function onRateFloat(){
 		//合同校验--浮动比例
 		if(nui.get("rateFloatProportion")){
 			var oldFloatPro = nui.get("rateFloatProportion").getValue();
 			var newFloatPro = nui.get("loanrate.rateFloatProportion").getValue();
 			var floatWay = nui.get("loanrate.floatWay").getValue();
			if(floatWay == '1'){//上浮
				if(parseFloat(newFloatPro)<parseFloat(oldFloatPro)){
					nui.alert("上浮时改动值不能小于审批浮动比例");
					nui.get("loanrate.rateFloatProportion").setValue('');
				}
			}else{//下浮
				if(parseFloat(newFloatPro)>parseFloat(oldFloatPro)){
					nui.alert("下浮时改动值不能大于审批浮动比例");
					nui.get("loanrate.rateFloatProportion").setValue('');
				}
			}
 		}
 		var loateValue = nui.get("loanrate.rateFloatProportion").getValue();
 		if(parseFloat(loateValue)>parseFloat('400')){
 			nui.get("loanrate.rateFloatProportion").setValue("");
 			nui.alert("浮动比例不能超过400%");
 			return false;
 		}
		//刷新table
		nui.get('tableloanrate').refreshTable();
 	}
 	function onYearrate(e){
 		//合同校验--固定利率
 		if(nui.get("yearrate")){
 			var oldyearrate = nui.get("yearrate").getValue();
 			var newyearrate = nui.get("loanrate.yearRate").getValue();
			if(parseFloat(newyearrate)<parseFloat(oldyearrate)){
				nui.alert("改动值不能小于审批利率");
				nui.get("loanrate.yearRate").setValue('');
				return false;
			}
 		}
 		var maxRate;
 		if(typeof(contractId)!='undefined'){
 			var json = nui.encode({"contractId":contractId});
			$.ajax({
	            url: "com.bos.pay.LoanSummary.queryRateByDate.biz.ext",
	            type: 'POST',
	            data: json,
	            async: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            maxRate = mydata.maxRate;
				}
	        });
 		}else{
 			$.ajax({
	            url: "com.bos.pay.LoanSummary.queryMaxRate.biz.ext",
	            type: 'POST',
	            data: "",
	            async: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            maxRate = mydata.maxRate;
				}
	        });
 		}
	        maxRate = parseFloat(maxRate)*4;
	        var currRate = nui.get("loanrate.yearRate").getValue();
	        if(parseFloat(currRate)>parseFloat(maxRate)){
	        	nui.get("loanrate.yearRate").setValue("");
				nui.alert("固定利率不能超过基准利率400%");
				return false;
	        }
		//刷新table
		nui.get('tableloanrate').refreshTable();
 	}
 </script>