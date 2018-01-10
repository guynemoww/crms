<%@page pageEncoding="UTF-8"%>
<div id="accountgrid">
	<fieldset>
		<legend>
			<span>账户信息</span>
		</legend>
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">还款账号：</label>
			<input id="tbConLoanChange.newRepayAccount" name="tbConLoanChange.newRepayAccount" 
				class="nui-text nui-form-input" />
				
			<label class="nui-form-label">账户名称：</label>
			<input id="tbConLoanChange.newZhmc" name="tbConLoanChange.newZhmc" 
				class="nui-text nui-form-input" />
				
			<!-- <label class="nui-form-label">账户类型：</label>
			<input id="" name="" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="" />
				
			<label class="nui-form-label">贷款科目：</label>
			<input id="" name="" required="true" 
				class="nui-textbox nui-form-input" /> -->
		</div>
		
		<!-- <div class="nui-dynpanel" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="account_btn" onclick="queryAccount">查询</a>
		</div> -->
	</fieldset>
</div>

<script type="text/javascript">
/*
	function queryAccount(){
    	var AcctNo = nui.get("tbConLoanChange.newRepayAccount").getValue();
    	if(AcctNo == null || AcctNo == ''){
    		alert("请输入账号！");
    		return;
    	}
    	AcctNo = AcctNo.trim();
    	var zhm = nui.get("tbConLoanChange.newZhmc").getValue();
    	if(zhm == null || zhm == ''){
    		alert("请输入账户名称！");
    		return;
    	}
    	zhm = zhm.trim();
    	 var json=nui.encode({"acctInd":AcctNo});
		  $.ajax({
	        url: "com.bos.accInfo.accInfo.queryAcc.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var message = text.msg;
	        	if(message != '查询成功'){
	        		nui.alert(message);
	        		nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        		nui.get("tbConLoanChange.newZhkhjg").validate();
	        		return;
	        	}
	        	var cusName = text.queryAcc.cstNm;
	        	cusName = cusName.trim();
	        	
	        	if(cusName != zhm){
	        		nui.alert("账户名与账号不匹配!");
	        		nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        		nui.get("tbConLoanChange.newZhkhjg").validate();
	        		return;
	        	}
	        	
	        	//合同币种与账户币种必须一样的校验
	        	var currcd = text.queryAcc.ccyTp;
	        	if(currcd!=currencyCd){
	        		nui.alert("账户币种和合同币种不匹配!");
	        		nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        		nui.get("tbConLoanChange.newZhkhjg").validate();
	        		return;
	        	}
	        	
	        	var orgid = text.queryAcc.acctRgonCd+text.queryAcc.acctBrId;
	        	nui.get("tbConLoanChange.newZhkhjg").setValue(orgid);
	        	nui.get("tbConLoanChange.newZhkhjg").validate();
	        	
	        	//去掉空格
	        	nui.get("tbConLoanChange.newRepayAccount").setValue(AcctNo);
	        	nui.get("tbConLoanChange.newZhmc").setValue(zhm);
	        	//账户标识
	        	var zhbs = text.queryAcc.acctTp;
	        	if(zhbs=='0'){
	        		zhbs = '12';
	        	}else if (zhbs=='1'){
	        		zhbs = '11';
	        	}else if (zhbs == '4'){
	        		zhbs = '60';
	        	}else{
	        		nui.alert("不支持的账户类型!");
	        		nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        		nui.get("tbConLoanChange.newZhkhjg").validate();
	        		return;
	        	}
	        	
	        	nui.get("tbConLoanChange.newZhbs").setValue(zhbs);
	        	
	        	
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
    }
*/
</script>