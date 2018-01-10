<%@page pageEncoding="UTF-8"%>
<div>
	<div class="nui-dynpanel" columns="4" id="loaninfo">
		<label class="nui-form-label" >出账编号</label>
		<input id="tbLoanInfo.loanNum" name="tbLoanInfo.loanNum" class="nui-text nui-form-input"/></a>
			
		<label class="nui-form-label" >放款通知书编号</label>
		<input id="tbLoanInfo.ntcNum" name="tbLoanInfo.ntcNum" class="nui-text nui-form-input"/></a>
			
		<label class="nui-form-label" >业务品种</label>
		<input id="tbBizProductInfo.productName" name="tbBizProductInfo.productName" class="nui-text nui-form-input"/>
		
		<label class="nui-form-label" >业务别</label>
		<input id="tbLoanInfo.loanSubject1" name="tbLoanInfo.loanSubject1" class="nui-text nui-form-input"/>
		
		<label class="nui-form-label">币种</label>
		<input id="tbLoanInfo.currencyCd" name="tbLoanInfo.currencyCd" class="nui-dictcombobox nui-form-input"  required="true"  data="data" valueField="dictID" dictTypeId="CD000001" enabled="false"/>
		
		<label class="nui-form-label">本次出账金额</label>
		<input id="tbLoanInfo.loanAmt" name="tbLoanInfo.loanAmt" class="nui-textbox nui-form-input"  required="true"  onblur="amtChange" vtype="float;maxLength:20;range:100,999999999999" dataType="currency"/>		

		<label class="nui-form-label">起始日：</label>
		<input id="tbLoanInfo.beginDate" name="tbLoanInfo.beginDate" class="nui-datepicker nui-form-input" required="true" dateFormat="yyyy-MM-dd"  onvaluechanged="validateBeginDate"  allowInput="false"/>
		
		<label id="sqqx">放款期限：</label>
		<div style="width:80%">
			<input name="tbLoanInfo.term" id="tbLoanInfo.term" style="width:60%;float:left" required="true" class="nui-textbox nui-form-input" vtype="int;range:0,95000" onblur="getConEndate"/>
			<input name="tbLoanInfo.unit" id="tbLoanInfo.unit" style="width:40%;float:left" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6010" onvaluechanged="countConEndate" dvalue="04" enabled="false"/>
		</div>
		
		
		<label class="nui-form-label">到期日：</label>
		<input id="tbLoanInfo.endDate" name="tbLoanInfo.endDate" class="nui-datepicker nui-form-input"   required="true" format="yyyy-MM-dd" allowInput="false" enabled="true" onvaluechanged="getTerm"/>
		
		<label class="nui-form-label">还款方式</label>
		<input id="tbLoanInfo.repayType" name="tbLoanInfo.repayType" class="nui-dictcombobox nui-form-input"  required="true"  data="data" valueField="dictID" dictTypeId="XD_SXCD1162" enabled="false"/>
		
		<label class="nui-form-label" >约定还款日</label>
		<input id="conInfo.specPaymentDate" name="conInfo.specPaymentDate" class="nui-text nui-form-input"/>
		
		<label id="yearRate">基准利率（%）：</label>
		<input id="loanrate.baseRateValue" name="loanrate.baseRateValue" class="nui-text nui-form-input" vtype="float;maxLength:11;range:0.00000001,100" required="true" />
		
		<label>利率类型：</label>
		<input name="loanrate.rateType" id="loanrate.rateType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1016" required="true" onvaluechanged="onselectType" enabled="false"/>
	
		<label id="floatWay">浮动方式：</label>
		<input name="loanrate.floatWay" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1147" id="loanrate.floatWay" required="true"  enabled="false"/>
	
		<label id="rateFloatProportion">浮动比例(%)：</label>
		<input id="loanrate.rateFloatProportion" name="loanrate.rateFloatProportion" class="nui-text nui-form-input" required="true"  vtype="negative;maxLength:11"/>
	
		<label id="yearRate">利率（%）：</label>
		<input id="loanrate.yearRate" name="loanrate.yearRate" class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0.00000001,60"  onblur="onselectRate" required="true" />
		
		<label id="isChangeRate">利率调整方式：</label>
		<input id="loanrate.irUpdateFrequency" name="loanrate.irUpdateFrequency" class="nui-text nui-form-input" required="true" dictTypeId="XD_SXCD1148"  />
				
		<label>结息周期：</label>
		<input name="loanrate.interestCollectType" class="nui-text nui-form-input" dictTypeId="XD_SXCD1018" id="loanrate.interestCollectType" required="true" />
		
		<label>节假日顺延标志：</label>
		<input name="loanrate.holidayFlg" id="loanrate.holidayFlg"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  onvaluechanged="conChangeHoliday" required="true" enabled="false"/>
	
		<label id="jjrlxclfs">节假日利息处理方式：</label>
		<input name="loanrate.holidayIntFlg" id="loanrate.holidayIntFlg" required="true" data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="XD_SXYW0234" />
	
		<label>宽限期方式：</label>
		<input name="loanrate.gracePeriodType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0210" id="loanrate.gracePeriodType" required="true" onvaluechanged="onselectGrace" enabled="false"/>																
	
		<label id="gracePeriodDay">宽限期天数：</label>
		<input name="loanrate.gracePeriodDay" class="nui-text nui-form-input"  id="loanrate.gracePeriodDay" vtype="int;range:1,10" required="true" />																
	
		<label id="kxqlxclfs">宽限期利息处理方式：</label>
		<input name="loanrate.graceCountIntFlag" class="nui-text nui-form-input" dictTypeId="XD_SXYW0234" id="loanrate.graceCountIntFlag" required="true"/>																
	</div>
</div>