<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-10
  - Description:
-->
<head>
<title>还款账号变更申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbConContractInfo.currencyCd" class="nui-hidden nui-form-input" name ="tbConContractInfo.currencyCd"/>
	<input id="tbLoanSummary.summaryCurrencyCd" class="nui-hidden nui-form-input" name ="tbLoanSummary.summaryCurrencyCd"/>
	<fieldset>
		<legend>
	    	<span>借据信息</span>
	    </legend>
	    
	     <div class="nui-dynpanel" columns="4">
		    <label class="nui-form-label">合同编号：</label>
			<input id="tbConContractInfo.contractNum" class="nui-text nui-form-input" name="tbConContractInfo.contractNum"/>
			<label class="nui-form-label">借据编号：</label>
			<input id="tbLoanSummary.summaryNum" class="nui-text nui-form-input" name="tbLoanSummary.summaryNum"/>
			
		</div>
		
	    <div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
		</div>
		
		<div id="type01" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="orgRegisterCd01" name="orgRegisterCd01" class="nui-text nui-form-input"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmCorporation.orgRegisterCd" class="nui-text nui-form-input" name="tbCsmCorporation.orgRegisterCd"/>
		</div>
		
		<div id="type02" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="tbCsmNaturalPerson.certType" name="tbCsmNaturalPerson.certType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_KHCD1001"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmNaturalPerson.certNum" class="nui-text nui-form-input" name="tbCsmNaturalPerson.certNum"/>
		</div>
		
		<!-- <label class="nui-form-label">经办机构：</label>
		<input id="tbConLoanChange.orgNum" name="tbConLoanChange.orgNum" class="nui-text nui-form-input" dictTypeId="org" />
		
		<label class="nui-form-label">客户经理：</label>
		<input id="tbConLoanChange.userNum" name="tbConLoanChange.userNum" class="nui-text nui-form-input" dictTypeId="user" />
		 -->
		 
		
		 
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbConContractInfo.productType" name="tbConContractInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt"  class="nui-text nui-form-input" dataType="currency"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye"  class="nui-text nui-form-input" dataType="currency"/>
			<label class="nui-form-label">借据起期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据止期：</label>
			<input id="tbLoanSummary.endDate" name="tbLoanSummary.endDate" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">还款方式：</label>
			<input id="tbLoanInfo.repayType" name="tbLoanInfo.repayType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1162"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
				   
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
			
		</div> 
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
				   
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input" vtype="maxLength:999"/> 
		
		</div>

	</fieldset>

	<fieldset>
		<legend>
	    	<span>变更前</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    	<label>账户类型：</label>
			<input id="zhlx" name="tbConZh.zhlx"   class="nui-text nui-form-input" dictTypeId="XD_ZHLX10001"/>
			
	    	<label class="nui-form-label">账户名称：</label>
			<input id="tbConLoanChange.oldZhmc" class="nui-text nui-form-input" name="tbConLoanChange.oldZhmc"/>
			
			<label class="nui-form-label">还款账号：</label>
			<input id="tbConLoanChange.oldRepayAccount" class="nui-text nui-form-input" name="tbConLoanChange.oldRepayAccount"/>
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
    		<label>货币代号：</label>
			<input id="hbdh" name="hbdh" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100001" enabled="false"/>
			
			<label>钞汇标志：</label>
			<input id="chbz" name="chbz" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100002" enabled="false"/>
	    
	    	<label class="nui-form-label">账户名称：</label>
			<input id="tbConLoanChange.newZhmc" class="nui-textbox nui-form-input" name="tbConLoanChange.newZhmc" required="true"/>
			
			<label class="nui-form-label">还款账号：</label>
			<div>
				<input id="tbConLoanChange.newRepayAccount" style="width:68%;float:left" class="nui-textbox nui-form-input" name="tbConLoanChange.newRepayAccount" required="true"/>
				<a class="nui-button" id="clear" style="width:16%;float:left" onclick="getAccInfo">查询</a>
			</div>
			
			<!-- <label class="nui-form-label">卡折标志：</label>
			<input id="tbConLoanChange.newKzbs" class="nui-text nui-form-input" name="tbConLoanChange.newKzbs"/> -->
			
			<label class="nui-form-label">开户行：</label>
			<input id="tbConLoanChange.newZhkhjg" class="nui-text nui-form-input" name="tbConLoanChange.newZhkhjg" dictTypeId="org"/>
			

	    </div>
	</fieldset>
	
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
		<!-- 	<a class="nui-button" id="toInterfaceBtn" onclick="toInterface">接口</a> -->
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	transfer();
            	query1();
			}
        });
        
		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}
        
	}
	function transfer(){
		var type = nui.get("tbLoanSummary.summaryCurrencyCd").getValue();
		if(type == "CNY"){
			nui.get("hbdh").setValue("01");
			nui.get("chbz").setValue("0");
		}else if(type == "HKD"){//港币
			nui.get("hbdh").setValue("13");
			nui.get("chbz").setValue("1");
		}else if(type == "JPY"){//日元
			nui.get("hbdh").setValue("27");
			nui.get("chbz").setValue("1");
		}else if(type == "MOP"){//澳门元
			nui.get("hbdh").setValue("81");
			nui.get("chbz").setValue("1");
		}else if(type == "AUD"){//澳洲元
			nui.get("hbdh").setValue("29");
			nui.get("chbz").setValue("1");
		}else if(type == "SGD"){//新加坡元
			nui.get("hbdh").setValue("32");
			nui.get("chbz").setValue("1");
		}else if(type == "CHF"){//瑞士法郎
			nui.get("hbdh").setValue("15");
			nui.get("chbz").setValue("1");
		}else if(type == "GBP"){//英镑
			nui.get("hbdh").setValue("12");
			nui.get("chbz").setValue("1");
		}else if(type == "USD"){//美元
			nui.get("hbdh").setValue("14");
			nui.get("chbz").setValue("1");
		}else if(type == "EUR"){//欧元
			nui.get("hbdh").setValue("38");
			nui.get("chbz").setValue("1");
		}else if(type == "CAD"){//加拿大元
			nui.get("hbdh").setValue("28");
			nui.get("chbz").setValue("1");
		}else{
			nui.alert("不支持的币种");
			return false;
		}
	}
	function query1(){
		var partyTypeCd= nui.get("tbCsmParty.partyTypeCd").getValue();
  		if(partyTypeCd=="01"){
  			$("#type01").css("display","block");
  			$("#type02").css("display","none");
  		}else if(partyTypeCd=="02"){
  			$("#type01").css("display","none");
  			$("#type02").css("display","block");
  		}else {
  			$("#type01").css("display","none");
  			$("#type02").css("display","none");
  		}
  	}
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId
		
		if(o.tbConLoanChange.oldRepayAccount==o.tbConLoanChange.newRepayAccount) {
			return nui.alert("变更后的还款账号不能与变更前相同！");
		}
		
		if(o.tbConLoanChange.newZhkhjg==null || o.tbConLoanChange.newZhkhjg=="") {
			return nui.alert("请先校验账号！");
		}
		
		nui.get("con_contract_info_save").setEnabled(false);
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveConLoanChange.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	
	function getAccInfo(){
    	var AcctNo = nui.get("tbConLoanChange.newRepayAccount").getValue();
    	var currencyCd = nui.get("tbConContractInfo.currencyCd").getValue();
    	var CurrCode = nui.get("hbdh").getValue();
    	var CashFlag = nui.get("chbz").getValue();
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
    	CurrCode = CurrCode.trim();
    	CashFlag = CashFlag.trim();
    	zhm = zhm.trim();
    	 var json=nui.encode({"acctInd":AcctNo, "currCode": CurrCode, "cashFlag" :CashFlag});
		  $.ajax({
	        //url: "com.bos.accInfo.accInfo.queryAcc.biz.ext",
	        url: "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var message = text.msg;
	        	var code = text.code;
	        	if(code != 'AAAAAAA'){
	        		nui.alert(message);
	        		nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        		nui.get("tbConLoanChange.newZhkhjg").validate();
	        		return;
	        	}
	        	//var cusName = text.queryAcc.cstNm;
	        	var cusName = text.hxresponse.oxd052ResBody.custName;
	        	cusName = cusName.trim();
	        	if(cusName != zhm){
	        		nui.alert("账户名与账号不匹配!");
	        		nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        		nui.get("tbConLoanChange.newZhkhjg").validate();
	        		return;
	        	}
	        	//合同币种与账户币种必须一样的校验
	        	//var currcd = text.queryAcc.ccyTp;
	        	//if(currcd!=currencyCd){
	        	//	nui.alert("账户币种和合同币种不匹配!");
	        	//	nui.alert(currcd+"!!!"+currencyCd);
	        	//	nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        	//	nui.get("tbConLoanChange.newZhkhjg").validate();
	        	//	return;
	        	//}
	        	
	        	//var orgid = text.queryAcc.acctRgonCd+text.queryAcc.acctBrId;
	        	//nui.get("tbConLoanChange.newZhkhjg").setValue(orgid);
	        	//nui.get("tbConLoanChange.newZhkhjg").validate();
	        	var orgid=text.hxresponse.oxd052ResBody.openBrch;
	        	nui.get("tbConLoanChange.newZhkhjg").setValue(orgid);
	        	nui.get("tbConLoanChange.newZhkhjg").validate();
	        	//去掉空格
	        	nui.get("tbConLoanChange.newRepayAccount").setValue(AcctNo);
	        	nui.get("tbConLoanChange.newZhmc").setValue(zhm);
	        	//账户标识
	        	//var zhbs = text.queryAcc.acctTp;
	        	//if(zhbs=='0'){
	        		//zhbs = '12';
	        	//}else if (zhbs=='1'){
	        		zhbs = '11';
	        	//}else if (zhbs == '4'){
	        		//zhbs = '60';
	        	//}else{
	        		//nui.alert("不支持的账户类型!");
	        		//nui.get("tbConLoanChange.newZhkhjg").setValue('');
	        		//nui.get("tbConLoanChange.newZhkhjg").validate();
	        		//return;
	        	//}
	        	
	        //	nui.get("tbConLoanChange.newZhbs").setValue(zhbs);
	        	
	        	
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
    }
	
	function toInterface(){

		form.validate();
        if (form.isValid()==false){
        	nui.alert("请先填写基本信息，并保存");
        	return;
        }
		
	   nui.get("toInterfaceBtn").setEnabled(false);
		
       var o = form.getData();
       o.changeId=changeId;
       var json = nui.encode(o);
       $.ajax({
            url: "com.bos.aft.conLoanChange.addInterface.biz.ext", 
            type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
            success: function (text) {
	        	if(text.message){
	        		alert(text.message); 
	        	}
	        	nui.get("toInterfaceBtn").setEnabled(true);
        	}
        }); 
	} 
</script>
</body>
</html>