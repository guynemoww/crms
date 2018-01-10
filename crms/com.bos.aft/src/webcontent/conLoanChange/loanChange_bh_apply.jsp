<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-07-16
  - Description:
-->
<head>
<title>保函修改申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<!-- <input id="tbLoanSummary.cycleUnit" class="nui-hidden nui-form-input" name ="tbLoanSummary.cycleUnit"/> -->
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbConContractInfo.endDate" class="nui-hidden nui-form-input" name ="tbConContractInfo.endDate"/>
	<input id="tbBizAmountDetailApprove.detailAmt" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.detailAmt"/>
	<input id="tbBizAmountDetailApprove.detailBalance" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.detailBalance"/>
	<input id="tbConContractInfo.exchangeRate" class="nui-hidden nui-form-input" name ="tbConContractInfo.exchangeRate"/>
	<input id="tbConLoanChange.oldBhBzjje" class="nui-hidden nui-form-input" name ="tbConLoanChange.oldBhBzjje"/>
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
			<label class="nui-form-label">ECIF客户编号：</label>
			<input id="tbCsmParty.ecifPartyNum" class="nui-text nui-form-input" name="tbCsmParty.ecifPartyNum"/>
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
			<label class="nui-form-label">借据币种：</label>
			<input id="tbLoanSummary.summaryCurrencyCd" name="tbLoanSummary.summaryCurrencyCd"  data="data" valueField="dictID" 
				class="nui-dictcombobox nui-form-input"  dictTypeId="CD000001" emptyText="--请选择--" enabled="false" />
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据金额：</label>
			<input id="tbConLoanChange.newSummaryamt" name="tbConLoanChange.newSummaryamt"  class="nui-textbox nui-form-input" dataType="currency" required="true"/>
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye"  class="nui-text nui-form-input" dataType="currency"/>
		</div>
		<!-- 
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据起期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />
		</div> -->
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">开立日期：</label>
			<input id="tbLoanJkBh.klrq" name="tbLoanJkBh.klrq" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd" />	
			<label class="nui-form-label">到期日期：</label>
			<input id="tbConLoanChange.newBhDqrq" name="tbConLoanChange.newBhDqrq" 
					required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
		</div>
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">即期/远期：</label>
			<input id="tbLoanJkBh.jyq" name="tbLoanJkBh.jyq"  valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0208" onvaluechanged="daysChange" enabled="false"/>
			<label class="nui-form-label">远期天数：</label>
			<input id="tbLoanJkBh.yqts" name="tbLoanJkBh.yqts" required="true" class="nui-text nui-form-input" vtype="float;maxLength:20;range:1,100000000000"/>
		</div>
		
		<div id="bzj1" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">保证金账号：</label>
			<input id="tbLoanJkBh.bzjzh" name="tbLoanJkBh.bzjzh" class="nui-text nui-form-input" />
			<label class="nui-form-label">保证金币种：</label>
			<input id="tbLoanJkBh.bzjbz" name="tbLoanJkBh.bzjbz"   valueField="dictID" 
					class="nui-dictcombobox nui-form-input" enabled="false" dictTypeId="CD000001" emptyText="--请选择--" />
		</div>
		<div id="bzj2" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">保证金比例不低于(%)：</label>
			<input id="tbConLoanChange.newBhBzjblbdy" name="tbConLoanChange.newBhBzjblbdy" class="nui-textbox nui-form-input"  
				vtype="float;maxLength:11;range:0,100000000000" />
			<label class="nui-form-label">保证金金额：</label>
			<input id="tbConLoanChange.newBhBzjje" name="tbConLoanChange.newBhBzjje" vtype="float;maxLength:20;range:0,100000000000" 
				class="nui-textbox nui-form-input" dataType="currency" />
		</div>	
		<!--
		<div class="nui-dynpanel" columns="4">
			
			 <label class="nui-form-label">借据止期：</label>
			<input id="tbConLoanChange.newEndDate" name="tbConLoanChange.newEndDate" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd" />
		
			<label class="nui-form-label">期限：</label>
			<div style="width:80%">
				<input name="tbConLoanChange.newTerm" style="width:60%;float:left" id="tbConLoanChange.newTerm" vtype="int;maxLength:4" class="nui-textbox nui-form-input" onblur="calEndDate"/>
				<input name="tbLoanSummary.cycleUnit" id="tbLoanSummary.cycleUnit" style="width:40%;float:left"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="XD_GGCD6009"/> 
			</div> 
			
			<label class="nui-form-label">借据止期：</label>
			<input id="tbConLoanChange.newEndDate" name="tbConLoanChange.newEndDate" onvaluechanged="validateEndDate"
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
		</div>-->
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">保函类型：</label>
			<input name="tbConLoanChange.newBhBhlx" id="tbConLoanChange.newBhBhlx" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0235" emptyText="--请选择--" />
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input" vtype="maxLength:999"/> 
		</div>

	</fieldset>
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
			<!-- <a class="nui-button" id="toInterfaceBtn" onclick="toInterface">接口</a> -->
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	<%-- var contractId ="<%=request.getParameter("contractId") %>"; --%>
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
            	//nui.get("tbConLoanChange.oldRateType").setValue(o.tbConLoanChange.oldRateType);//值被覆盖，重新赋值nui.get("id").setValue(o.name)
				if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	var detailBalance = nui.get("tbBizAmountDetailApprove.detailBalance").getValue();//批复---可用金额
            	var jjye = nui.get("tbLoanSummary.jjye").getValue();//当前借据余额
            	detailBalance = parseFloat(detailBalance) + parseFloat(jjye);
            	nui.get("tbBizAmountDetailApprove.detailBalance").setValue(detailBalance);
            	//if(nui.get("tbLoanJkBh.bzjbz").getValue()==""){
            		//$("#bzj1").css("display","none");
  					//$("#bzj2").css("display","none");
            	//}
            	query1();
            	//nui.get("tbLoanSummary.cycleUnit2").setValue(o.tbLoanSummary.cycleUnit);
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
  	}
  	
  	function validateEndDate(){
 		var o = form.getData();
 		var beginDate = o.tbLoanSummary.beginDate;
 		var endDate = o.tbConLoanChange.newEndDate;
 		var conEndDate = o.tbConContractInfo.endDate;
 		if(endDate!=null && endDate!=''){
 			if(endDate<beginDate){
	 			nui.alert("止期不能小于起期");
	 			nui.get("tbConLoanChange.newEndDate").setValue('');
	 			return;
	 		}
	 		
	 		if(endDate>conEndDate){
	 			nui.alert("止期不能大于合同止期");
	 			nui.get("tbConLoanChange.newEndDate").setValue('');
	 			return;
	 		}
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
		git.mask();
		nui.get("con_contract_info_save").setEnabled(false);
		//if(o.tbBizAmountDetailApprove.detailAmt<o.tbConLoanChange.newSummaryamt) {
        	//nui.alert("综合授信额度中的信用证修改不得超过其分项额度["+o.tbBizAmountDetailApprove.detailAmt+"]");
			//nui.get("con_contract_info_save").setEnabled(true);
			//git.unmask();
        	//return;
		//}
		if(parseFloat(o.tbConLoanChange.newSummaryamt)>parseFloat(o.tbBizAmountDetailApprove.detailBalance)){//合同折算人民币金额>批复可用金额 
			nui.alert("保函折算人民币金额["+newRmbAmt+"]不能大于批复明细可用金额[CNY:"+o.tbBizAmountDetailApprove.detailBalance+"]");
 			//nui.get("conDetail.kzje").setValue("");
 			nui.get("con_contract_info_save").setEnabled(true);
 			git.unmask();
 			return;
 		}
		 //保证金账号、币种、金额、比例四个信息  要么全有 要么全都不能有
         var bondAcct = o.tbLoanJkBh.bzjzh//保证金账号
         var bondCurr = o.tbLoanJkBh.bzjbz;//保证金币种
		 var bondAmt = o.tbConLoanChange.newBhBzjje;//保证金金额
		 var bondRate = o.tbConLoanChange.newBhBzjblbdy;//保证金比例不低于
		 
		  if(bondAcct != '' && bondAcct != null && bondAcct != 'null' && bondAcct != 'undefined'){
           if(bondCurr==null||bondCurr==''||bondAmt==null||bondAmt==''||bondRate==null||bondRate==''){
           		nui.alert("保证金信息不完整,请检查[币种&金额&比例]！");
				nui.get("con_contract_info_save").setEnabled(true);
				git.unmask();
				return;
           	}
          }
           if(bondCurr != '' && bondCurr != null && bondCurr != 'null' && bondCurr != 'undefined'){
           		if(bondAcct==null||bondAcct==''||bondAmt==null||bondAmt==''||bondRate==null||bondRate==''){
           			nui.alert("保证金信息不完整,请检查[账号&金额&比例]！");
					nui.get("con_contract_info_save").setEnabled(true);
					git.unmask();
					return;
           		}
           }
           if(bondAmt != '' && bondAmt != null && bondAmt != 'null' && bondAmt != 'undefined'){
           		if(bondCurr==null||bondCurr==''||bondAcct==null||bondAcct==''||bondRate==null||bondRate==''){
           			nui.alert("保证金信息不完整,请检查[账号&币种&比例]！");
					nui.get("con_contract_info_save").setEnabled(true);
					git.unmask();
					return;
           		}
           }
           if(bondRate != '' && bondRate != null && bondRate != 'null' && bondRate != 'undefined'){
           		if(bondCurr==null||bondCurr==''||bondAmt==null||bondAmt==''||bondAcct==null||bondAcct==''){
           			nui.alert("保证金信息不完整,请检查[币种&金额&比例]！");
					nui.get("con_contract_info_save").setEnabled(true);
					git.unmask();
					return;
           		}
           }   
           
        //保证金金额---只能增加不能减少
        var oldBondAmt = o.tbConLoanChange.oldBhBzjje;//保证金金额
        if(parseFloat(oldBondAmt)>parseFloat(bondAmt)){
        	nui.alert("保证金金额只能做上浮调整，不能做下浮调整！");
			nui.get("con_contract_info_save").setEnabled(true);
			git.unmask();
			return;
        }
           
        var newSummaryAmt = o.tbConLoanChange.newSummaryamt;//借据金额 	
        var jjRate = o.tbConContractInfo.exchangeRate;//借据币种的汇率 
        	
		var bz = o.tbLoanSummary.summaryCurrencyCd;//借据币种
		var bzjRate = "1";//保证金币种的汇率
		var minBzjje;//最低保证金金额
        
        if(bondCurr != '' && bondCurr != null && bondCurr != 'null' && bondCurr != 'undefined'){
			if(bz!==bondCurr){//保证金币种和借据币种不一致 
			if (bondCurr != 'CNY'){//保证金币种和借据币种不一致而且保证金币种不是人民币
            	var json = nui.encode({"bz":bondCurr});
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
        						if (bondRate != '' && bondRate != null && bondRate != 'null' && bondRate != 'undefined') {
									minBzjje = parseFloat(newSummaryAmt)*parseFloat(bondRate)/parseFloat(100)*parseFloat(jjRate)/parseFloat(bzjRate);
								}
								if (bondAmt != '' && bondAmt != null && bondAmt != 'null' && bondAmt != 'undefined') {
									if((parseFloat(bondAmt))<parseFloat(minBzjje)){
										git.unmask();
										nui.alert("最低保证金金额["+bondCurr+":"+minBzjje+"]");
										nui.get("con_contract_info_save").setEnabled(true);
										return;
									}else{
										saveConLoanChange();
									}
								}
        					}else{
        						git.unmask();
        						alert("未获取到币种["+bondCurr+"]的汇率信息");
        						nui.get("con_contract_info_save").setEnabled(true);
        						return;
        					}
        				}
        			}
        		});	
			}else{//保证金币种和借据币种不一致但是保证金币种是人民币
        		if (bondRate != '' && bondRate != null && bondRate != 'null' && bondRate != 'undefined') {
					minBzjje = parseFloat(newSummaryAmt)*parseFloat(bondRate)/parseFloat(100)*parseFloat(jjRate);
				}
				if (bondAmt != '' && bondAmt != null && bondAmt != 'null' && bondAmt != 'undefined') {
					if((parseFloat(bondAmt))<parseFloat(minBzjje)){
						git.unmask();
						nui.alert("最低保证金金额["+bondCurr+":"+minBzjje+"]");
						nui.get("con_contract_info_save").setEnabled(true);
						return;
					}else{
						saveConLoanChange();
					}
				}
			}
		}else{//保证金币种和借据币种一致 
			if (bondRate != '' && bondRate != null && bondRate != 'null' && bondRate != 'undefined') {
				minBzjje = parseFloat(newSummaryAmt)*parseFloat(bondRate)/parseFloat(100);
			}
			if (bondAmt != '' && bondAmt != null && bondAmt != 'null' && bondAmt != 'undefined') {
				if((parseFloat(bondAmt))<parseFloat(minBzjje)){
						git.unmask();
						nui.alert("最低保证金金额["+bondCurr+":"+minBzjje+"]");
						nui.get("con_contract_info_save").setEnabled(true);
						return;
					}else{
						saveConLoanChange();
					}
				}
			}
		}else{//没有保证金信息---直接保存
			saveConLoanChange();
		}
	} 
	
	function saveConLoanChange(){
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId;
		var json = nui.encode(o);
   		$.ajax({
	        url: "com.bos.aft.conLoanChange.saveConLoanChange.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		git.unmask();
	        		nui.alert(text.msg); //失败时后台直接返回出错信息
	        		nui.get("con_contract_info_save").setEnabled(true);
	        	}
	        	git.unmask();
	        	nui.get("con_contract_info_save").setEnabled(true);
	        }
        });
	}
	
	//当期限类型为即期时远期天数为0
	function daysChange() {
		var days = nui.get("tbLoanJkBh.jyq").getValue();
		if ("1" == days) {
			nui.get("tbLoanJkBh.yqts").setValue("0");
			nui.get("tbLoanJkBh.yqts").setEnabled(false);
		} else {
			nui.get("tbLoanJkBh.yqts").setValue("");
			nui.get("tbLoanJkBh.yqts").setEnabled(true);
		}
		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}
	}
	/* function calEndDate(){
		var newTerm= nui.get("tbConLoanChange.newTerm").getValue();
		var cycleUnit= nui.get("tbLoanSummary.cycleUnit").getValue();
		//var cycleUnit2= nui.get("tbLoanSummary.cycleUnit2").getValue();
		//alert("1--->" + cycleUnit + "2--->" + cycleUnit2);
		nui.get("tbConLoanChange.newEndDate").setEnabled(false);
		
		//将数据库中的Timestamp格式转换成yyyy/MM/dd
    	var tempStr = nui.get("tbLoanSummary.beginDate").getValue().substr(0,4) 
    	   + "/" + nui.get("tbLoanSummary.beginDate").getValue().substr(5,2) 
    	   + "/" + nui.get("tbLoanSummary.beginDate").getValue().substr(8,2);
    	//再转换成日期类型
    	var tempDate = new Date(tempStr);
    	
		if(cycleUnit=="01"){//年
    		//年份增
	    	tempDate.setFullYear(tempDate.getFullYear()+parseInt(newTerm));
		    	
		}else if(cycleUnit=="02"){//半年
			    
		}else if(cycleUnit=="03"){//季
			    
		}else if(cycleUnit=="04"){//月
		    //月份增
	    	tempDate.setMonth(tempDate.getMonth()+parseInt(newTerm));
		    		
		}else if(cycleUnit=="05"){//日
			//日增
	    	tempDate.setDate(tempDate.getDate()+parseInt(newTerm));
		    	
		}
		
		//最后将日期类型转换为字符型
    	var result = tempDate.getFullYear().toString() 
    		+ "-" + (tempDate.getMonth()+1).toString() 
    		+ "-" + tempDate.getDate().toString();
    	nui.get("tbConLoanChange.newEndDate").setValue(result);
		//alert(nui.get("tbConLoanChange.newEndDate").getValue());
	} */
	
	/* function toInterface(){

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
	        	if(text.msg){
	        		nui.alert(text.msg); 
	        	}
	        	nui.get("toInterfaceBtn").setEnabled(true);
        	}
        }); 
	}  */
</script>
</body>
</html>