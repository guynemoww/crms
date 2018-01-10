<%@page pageEncoding="UTF-8"%>
<div class="nui-dynpanel" columns="4">
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
	<input id="tbLoanInfo.loanAmt" name="tbLoanInfo.loanAmt" class="nui-textbox nui-form-input"  required="true"  dataType="currency"  onblur="amtChange"/>		
	
	<label class="nui-form-label">起始日：</label>
	<input id="tbLoanInfo.beginDate" name="tbLoanInfo.beginDate" class="nui-datepicker nui-form-input" required="true" dateFormat="yyyy-MM-dd"  onvaluechanged="validateBeginDate"  allowInput="false"/>
	
	<label class="nui-form-label">到期日：</label>
	<input id="tbLoanInfo.endDate" name="tbLoanInfo.endDate" class="nui-datepicker nui-form-input"   required="true" format="yyyy-MM-dd"  onvaluechanged="validateEndDate"  allowInput="false"/>
	
	<label class="nui-form-label">还款方式</label>
	<input id="tbLoanInfo.repayType" name="tbLoanInfo.repayType" class="nui-dictcombobox nui-form-input"  required="true"  data="data" valueField="dictID" dictTypeId="XD_SXCD1162" enabled="false"/>
		
	<label id="yearRate">利率（%）：</label>
	<input id="loanrate.yearRate" name="loanrate.yearRate" class="nui-text nui-form-input" vtype="float;maxLength:11;range:0.00000001,100" required="false" />
</div>
