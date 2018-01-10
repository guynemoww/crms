<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-10
  - Description:
-->
<head>
<title>委托人收本收息账号变更申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbConContractInfo.currencyCd" class="nui-hidden nui-form-input" name ="tbConContractInfo.currencyCd"/>
	<input id="tbCsmEntrustAccount.accName" class=" nui-hidden nui-form-input" name ="tbCsmEntrustAccount.accName"/>
	<input id="tbCsmEntrustAccount.entrustReturnAcc" class=" nui-hidden nui-form-input" name ="tbCsmEntrustAccount.entrustReturnAcc"/>
	<input id="tbCsmEntrustAccount.entrustLoanAcc" class=" nui-hidden nui-form-input" name ="tbCsmEntrustAccount.entrustLoanAcc"/>
	
	<input id="tbLoanSummary.entrustReturnPrincipalAcc" class=" nui-hidden nui-form-input" name ="tbLoanSummary.entrustReturnPrincipalAcc"/>
	<input id="tbLoanSummary.entrustReturnInterestAcc" class=" nui-hidden nui-form-input" name ="tbLoanSummary.entrustReturnInterestAcc"/>
	
	
	
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
			<input id="orgRegisterCd01" name="orgRegisterCd01" class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmCorporation.orgRegisterCd" class="nui-text nui-form-input" name="tbCsmCorporation.orgRegisterCd"/>
		</div>
		
		<div id="type02" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="tbCsmNaturalPerson.certType" name="tbCsmNaturalPerson.certType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
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
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input"  vtype="maxLength:999"/> 
		
		</div>

	</fieldset>

	<fieldset>
		<legend>
	    	<span>变更前</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    
	    	<label class="nui-form-label">委托人收本账号:</label>
			<input id="tbConLoanChange.oldWtrhbzh" class="nui-text nui-form-input" name="tbConLoanChange.oldWtrhbzh"/>
			
			<label class="nui-form-label">委托人收息账号:</label>
			<input id="tbConLoanChange.oldWtrhxzh" class="nui-text nui-form-input" name="tbConLoanChange.oldWtrhxzh"/>
			
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
	    
	    	<label class="nui-form-label">委托人收本账号：</label>
			<input id="tbConLoanChange.newWtrhbzh" class="nui-textbox nui-form-input" name="tbConLoanChange.newWtrhbzh" required="true"/>
			
			
			<label class="nui-form-label">委托人收息账号：</label>
			<input id="tbConLoanChange.newWtrhxzh" class="nui-textbox nui-form-input" name="tbConLoanChange.newWtrhxzh" required="true"/>
			
			
			 
			 
			

	    </div>
	</fieldset>
	
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
			<!-- <a class="nui-button" id="toInterfaceBtn" onclick="toInterface">接口</a>  -->
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
            	query1();
			}
        });
        
		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
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
  	//	var oldhb=nui.get("tbLoanSummary.entrustReturnPrincipalAcc").getValue();
//	var oldhx=nui.get("tbLoanSummary.entrustReturnInterestAcc").getValue();
  //	nui.get("tbConLoanChange.oldWtrhbzh").setValue(oldhb);
      //  nui.get("tbConLoanChange.oldWtrhxzh").setValue(oldhx);
  	}
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId;
		var oldhb=nui.get("tbLoanSummary.entrustReturnPrincipalAcc").getValue();
		var oldhx=nui.get("tbLoanSummary.entrustReturnInterestAcc").getValue();
		var wtrmc=nui.get("tbCsmEntrustAccount.accName").getValue();
		var newhb=nui.get("tbConLoanChange.newWtrhbzh").getValue();
		var newhx=nui.get("tbConLoanChange.newWtrhxzh").getValue();
				var entrustLoanAcc=nui.get("tbCsmEntrustAccount.entrustLoanAcc").getValue();
				var entrustReturnAcc=nui.get("tbCsmEntrustAccount.entrustReturnAcc").getValue();
		
		var retMsg="查询成功";
		//alert(wtrmc+"!!"+oldhb+"!!"+oldhx+"!!"+newhb+"!!"+newhx+"!!"+entrustLoanAcc+"!!"+entrustReturnAcc);
	 	if(oldhb==newhb && oldhx==newhx){
	 	alert("委托人:["+wtrmc+"]收本收息账号变更前后不能相同!");
	 	return;
	 	}
		if(newhb==entrustLoanAcc || newhb==entrustReturnAcc || newhx==entrustLoanAcc || newhx==entrustReturnAcc ){
		alert("委托人:["+wtrmc+"]收本收息账号不能和[委托贷款基金账号]或[委托贷款收息账号]相同!");
		return;
		}
		
			// 校验收本收息账号
			var	 json = nui.encode({"acctInd" : newhb});
			$.ajax({
				url : "com.bos.accInfo.accInfo.queryAcc.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				async : false,
				success : function(text) {
					debugger;
					var message = text.msg;
					if (message != '查询成功') {
						retMsg += "  委托人收本账号:"+message;
						 return;
					} else {
						var cusName = text.queryAcc.cstNm;
						cusName = cusName.trim();
						if(cusName!=wtrmc){
							retMsg += '  委托人收本账号与户名不匹配';
 							return;
						}
					}	
				},
				error : function(jqXHR, textStatus, errorThrown) {
					retMsg += "  委托人收本查询失败!";
					return jqXHR.responseText;
				}
			});
			
			json = nui.encode({"acctInd" : newhx});
			$.ajax({
				url : "com.bos.accInfo.accInfo.queryAcc.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				async : false,
				success : function(text) {
					debugger;
					var message = text.msg;
					if (message != '查询成功') {
						retMsg += "  委托人收息账号:"+message;
						 return;
					} else {
						var cusName = text.queryAcc.cstNm;
						cusName = cusName.trim();
						if(cusName!=wtrmc){
							retMsg += '  委托人收息账号与户名不匹配';
 							return;
						}
					}	
				},
				error : function(jqXHR, textStatus, errorThrown) {
					retMsg += "  委托人收息查询失败!";
					return jqXHR.responseText;
				}
			});
		
		if(retMsg!= '查询成功'){
		alert(retMsg);
		return;
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