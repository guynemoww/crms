<%@page pageEncoding="UTF-8"%>
<div class="nui-dynpanel" columns="4">
		<label class="nui-form-label" >出账编号</label>
		<input id="tbLoanInfo.loanNum" name="tbLoanInfo.loanNum" class="nui-text nui-form-input"/></a>
			
		<label class="nui-form-label" >业务品种</label>
		<input id="tbBizProductInfo.productName" name="tbBizProductInfo.productName" class="nui-text nui-form-input"/>
		
		<label class="nui-form-label">币种</label>
		<input id="tbLoanInfo.currencyCd" name="tbLoanInfo.currencyCd" class="nui-dictcombobox nui-form-input"  required="true"  data="data" valueField="dictID" dictTypeId="CD000001" enabled="false"/>
		
		<label class="nui-form-label">本次贴现金额</label>
		<input id="tbLoanInfo.loanAmt" name="tbLoanInfo.loanAmt" class="nui-text nui-form-input" required="true" onblur="amtChange" dataType="currency"/>		
		
		<label class="nui-form-label">利率(%)</label>
		<input id="loanrate.yearRate" name="loanrate.yearRate" class="nui-text nui-form-input" required="true"   />		
		
		<label class="nui-form-label">起始日：</label>
		<input id="tbLoanInfo.beginDate" name="tbLoanInfo.beginDate" class="nui-text nui-form-input" required="true" dateFormat="yyyy-MM-dd"  />
		
		<label class="nui-form-label">到期日：</label>
		<input id="tbLoanInfo.endDate" name="tbLoanInfo.endDate" class="nui-text nui-form-input" required="true" dateFormat="yyyy-MM-dd" />
</div>