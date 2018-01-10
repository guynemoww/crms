<%@page pageEncoding="UTF-8"%>
<div class="nui-dynpanel" columns="4">
	<label class="nui-form-label" >出账编号：</label>
	<input id="tbLoanInfo.loanNum" name="tbLoanInfo.loanNum" class="nui-text nui-form-input"/></a>
	
	<label class="nui-form-label" >业务品种：</label>
	<input id="tbBizProductInfo.productName" name="tbBizProductInfo.productName" class="nui-text nui-form-input"/>
	
	<label class="nui-form-label">票据种类：</label>
	<input id="tbLoanInfo.pjzl" name="tbLoanInfo.pjzl" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1123" required="true" enabled="false"/>
	
	<label class="nui-form-label">币种：</label>
	<input id="tbLoanInfo.currencyCd" name="tbLoanInfo.currencyCd" class="nui-dictcombobox nui-form-input"  required="true"  data="data" valueField="dictID" dictTypeId="CD000001" enabled="false"/>
			
	<label class="nui-form-label">汇票张数：</label>
	<input id="tbLoanInfo.hpzs" name="tbLoanInfo.hpzs" class="nui-textbox nui-form-input" required="true"  vtype="int;maxLength:4;range:1,10000" enabled="false"/>
	
	<label class="nui-form-label">汇票总金额：</label>
	<input id="tbLoanInfo.loanAmt" name="tbLoanInfo.loanAmt" class="nui-textbox nui-form-input"  required="true"  onblur="amtChange" vtype="float;maxLength:20;range:100,999999999999" dataType="currency" enabled="false"/>		
	
	<label class="nui-form-label">承兑起始日：</label>
	<input id="tbLoanInfo.beginDate" name="tbLoanInfo.beginDate" class="nui-text nui-form-input" required="true" dateFormat="yyyy-MM-dd" enabled="false"/>
	
	<label id="sqqx">放款期限：</label>
	<div style="width:80%">
		<input name="tbLoanInfo.term" id="tbLoanInfo.term" style="width:60%;float:left" required="true" class="nui-textbox nui-form-input" vtype="int;range:0,95000" onblur="getConEndate"/>
		<input name="tbLoanInfo.unit" id="tbLoanInfo.unit" style="width:40%;float:left" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6010" onvaluechanged="countConEndate" dvalue="04" enabled="false"/>
	</div>
	
	<label class="nui-form-label">承兑到期日：</label>
	<input id="tbLoanInfo.endDate" name="tbLoanInfo.endDate" class="nui-datepicker nui-form-input"  allowInput="false"  required="true" format="yyyy-MM-dd" enabled="false"/><!-- 不校验到期日期onvaluechanged="validateEndDateyc" -->
	
	<label>付款行全称：</label>
	<input name="tbLoanInfo.drweBnkNm" id="tbLoanInfo.drweBnkNm" required="true" class="nui-textbox nui-form-input"  onblur="checkLength1(this.value)" enabled="false" />
	
	<label>付款行行号：</label>
	<input name="tbLoanInfo.drweBnkNo" id="tbLoanInfo.drweBnkNo" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:14" enabled="false" />
	
	<label>付款行地址：</label>
	<input name="tbLoanInfo.drweBnkAdr" required="true" class="nui-textbox nui-form-input" />
	
	<label>承诺费：</label>
	<input name="tbLoanInfo.comAmt" id="tbLoanInfo.comAmt" class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:20;range:0,999999999999" dataType="currency" />
	
</div>




