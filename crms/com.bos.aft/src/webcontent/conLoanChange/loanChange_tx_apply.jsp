<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-10
  - Description:
-->
<head>
<title>贴息、暂停贴息信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbConLoanTx.txId" class="nui-hidden nui-form-input" name ="tbConLoanTx.txId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	
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
			<label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">还款账号：</label>
			<input id="tbLoanZh.zh" class="nui-text nui-form-input" name="tbLoanZh.zh"/>
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input"   vtype="maxLength:999"  /> 
		</div>
	</fieldset>
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="btnZT"  onclick="saveZT">暂停贴息</a>
			<a class="nui-button" id="btnHF"  onclick="saveHF">恢复贴息</a>
	</div>

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	<%-- var contractId ="<%=request.getParameter("contractId") %>";  --%>
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
            	//alert(mydata.tbLoanSummary.tiexiStatus);
            	if(mydata.tbLoanSummary.tiexiStatus != null && mydata.tbLoanSummary.tiexiStatus != "") {
            		if(mydata.tbLoanSummary.tiexiStatus == "01") {
	            		nui.get("btnZT").setEnabled(true);
	            		nui.get("btnHF").setEnabled(false);
            		}
	            	if(mydata.tbLoanSummary.tiexiStatus == "02") {
	            		nui.get("btnZT").setEnabled(false);
	            		nui.get("btnHF").setEnabled(true);
	            	}
            	}else {
            		nui.get("btnZT").setEnabled(false);
	            	nui.get("btnHF").setEnabled(false);
            	}
            
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
			nui.get("btnZT").hide();
			nui.get("btnHF").hide();
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
  	}
	
	function saveZT(){
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        nui.get("btnZT").setEnabled(false);
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveTiexiZT.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	nui.alert("暂停成功"); 
        	nui.get("btnZT").setEnabled(true);
        }});
	} 
	
	function saveHF(){
		
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        nui.get("btnHF").setEnabled(false);
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveTiexiHF.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	nui.alert("恢复成功"); 
        	nui.get("btnHF").setEnabled(true);
        }});
	} 
	
</script>