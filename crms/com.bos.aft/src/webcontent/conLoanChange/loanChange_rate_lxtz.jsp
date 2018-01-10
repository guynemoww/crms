<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>利率变更申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbLoanLoanrate.baseRateValue" class="nui-hidden nui-form-input" name ="tbLoanLoanrate.baseRateValue"/>
	<input id="tbLoanInfo.productType" class="nui-hidden nui-form-input" name ="tbLoanInfo.productType"/>
	
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
	    <label class="nui-form-label">正常利息：</label>
			<input id="tbConLoanChange.beNorItrIn" class="nui-text nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.beNorItrIn" dataType="currency"/>
			
			<label class="nui-form-label">拖欠利息：</label>
			<input id="tbConLoanChange.beDftItrIn" class="nui-text nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.beDftItrIn" dataType="currency"/> 
			
			<label class="nui-form-label">罚息：</label>
			<input id="tbConLoanChange.bePnsItrIn" class="nui-text nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.bePnsItrIn" dataType="currency"/>
			
			<label class="nui-form-label">复利：</label>
			<input id="tbConLoanChange.beOtdItrIn" class="nui-text nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.beOtdItrIn" dataType="currency"/>
			
			<label class="nui-form-label">未结计正常利息：</label>
			<input id="tbConLoanChange.beNorOtdItr" class="nui-text nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.beNorOtdItr" dataType="currency"/> 
			
			<label class="nui-form-label">未结计罚息：</label>
			<input id="tbConLoanChange.bePnsOtdItr" class="nui-text nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.bePnsOtdItr" dataType="currency"/>
			
			<label class="nui-form-label">未结计复利：</label>
			<input id="tbConLoanChange.beOtdCpd" class="nui-text nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.beOtdCpd" dataType="currency"/>
		 
	    </div> 
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
		<div  class="nui-dynpanel"  columns="4">
	    
	     <label class="nui-form-label">利息调整类型：</label>
              <input id="tbConLoanChange.adjItrFlg"   class="nui-combobox" style="width:30%;" name="tbConLoanChange.adjItrFlg"  data="tzlxs" required="true" />  
             
			<label class="nui-form-label">正常利息：</label>
			<input id="tbConLoanChange.norItrIn" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999"  name="tbConLoanChange.norItrIn" dataType="currency" required="true" />
			
			<label class="nui-form-label">拖欠利息：</label>
			<input id="tbConLoanChange.dftItrIn" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.dftItrIn" dataType="currency"  required="true"/> 
			
			<label class="nui-form-label">罚息：</label>
			<input id="tbConLoanChange.pnsItrIn" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.pnsItrIn" dataType="currency"  required="true"/>
			
			<label class="nui-form-label">复利：</label>
			<input id="tbConLoanChange.otdItrIn" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.otdItrIn" dataType="currency"  required="true"/>
			
			<label class="nui-form-label">未结计正常利息：</label>
			<input id="tbConLoanChange.norOtdItr" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.norOtdItr" dataType="currency"  required="true"/> 
			
			<label class="nui-form-label">未结计罚息：</label>
			<input id="tbConLoanChange.pnsOtdItr" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.pnsOtdItr" dataType="currency"  required="true"/>
			
			<label class="nui-form-label">未结计复利：</label>
			<input id="tbConLoanChange.otdCpd" class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,99999999999999999999" name="tbConLoanChange.otdCpd" dataType="currency"  required="true"/>
		
		</div>

	</fieldset>
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	 var tzlxs = [{ id: '2', text: '调增' }, { id: '1', text: '调减'}];
	nui.parse();
	var tbLoanSummary;
	var tbConLoanChange;
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var state="";
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
            async: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	 tbLoanSummary = o.tbLoanSummary;
            	 tbConLoanChange = o.tbConLoanChange;
            	 state=o.tbConLoanChange.changeStatus;
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
		            	
 		
		json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange});
		$.ajax({
            url: "com.bos.aft.conLoanChange.GetRepayQueryInterface1109.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (mydata) {
            	var code = mydata.items.baseVO.errCod;
            	if(code !='00000') {
            		return nui.alert(mydata.msg);
            	}else {
            		if(state!="03"){
            		
             		nui.get("tbConLoanChange.beNorItrIn").setValue(mydata.items.rcvNorItrIn);
            		nui.get("tbConLoanChange.beDftItrIn").setValue(mydata.items.rcvDftItrIn);
            		nui.get("tbConLoanChange.bePnsItrIn").setValue(mydata.items.rcvPnsItrIn);
            		nui.get("tbConLoanChange.beOtdItrIn").setValue(mydata.items.rcvCpdItrIn);
            		nui.get("tbConLoanChange.beNorOtdItr").setValue(mydata.items.currPrjItr);
            		nui.get("tbConLoanChange.bePnsOtdItr").setValue(mydata.items.adjOtdPns);
            		nui.get("tbConLoanChange.beOtdCpd").setValue(mydata.items.adjOtdCpd);
            		}
            	}
			}
    	});
        
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
        //变更后利息
    	var adjItrFlg = parseFloat(nui.get("tbConLoanChange.adjItrFlg").getValue());
        var norItrIn = parseFloat(nui.get("tbConLoanChange.norItrIn").getValue());
        var dftItrIn = parseFloat(nui.get("tbConLoanChange.dftItrIn").getValue());
        var pnsItrIn = parseFloat(nui.get("tbConLoanChange.pnsItrIn").getValue());
        var norOtdItr = parseFloat(nui.get("tbConLoanChange.norOtdItr").getValue());
        var pnsOtdItr = parseFloat(nui.get("tbConLoanChange.pnsOtdItr").getValue());
        var otdCpd = parseFloat(nui.get("tbConLoanChange.otdCpd").getValue());
        var otdItrIn= parseFloat(nui.get("tbConLoanChange.otdItrIn").getValue());
        
        //变更前利息
        var be_norItrIn= parseFloat(nui.get("tbConLoanChange.beNorItrIn").getValue());
		var	be_dftItrIn= parseFloat(nui.get("tbConLoanChange.beDftItrIn").getValue());
		var	be_pnsItrIn= parseFloat(nui.get("tbConLoanChange.bePnsItrIn").getValue());
		var	be_norOtdItr= parseFloat(nui.get("tbConLoanChange.beNorOtdItr").getValue());
		var	be_pnsOtdItr= parseFloat(nui.get("tbConLoanChange.bePnsOtdItr").getValue());
		var	be_otdCpd= parseFloat(nui.get("tbConLoanChange.beOtdCpd").getValue());
		var be_otdItrIn= parseFloat(nui.get("tbConLoanChange.beOtdItrIn").getValue());
		 
        if(pnsOtdItr==0 && norItrIn==0 && dftItrIn==0 && pnsItrIn==0 && norOtdItr==0 && otdCpd == 0 && otdItrIn == 0){
                	nui.alert("至少有一项调整额大于0!");
                	return;
        }
         if(adjItrFlg=="1"){
        if(be_norItrIn<norItrIn ||be_dftItrIn<dftItrIn || be_pnsItrIn<pnsItrIn || be_norOtdItr<norOtdItr || be_pnsOtdItr<pnsOtdItr || be_otdCpd<otdCpd || be_otdItrIn< otdItrIn){
        			nui.alert("调减时，变更后的金额不能大于变更前的金额");
                	return;
        }
        }
        
        var o = form.getData();
		
		
		
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
        	//alert("保存成功！");
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
 	
 	//计算基准利率
 	function countRate(){
 		var o = form.getData();
 		var beginDate = o.tbLoanSummary.beginDate;//借据起期
 		var endDate = o.tbLoanSummary.endDate;//借据止期
 		var loanlength=0;
 		if(o.tbConLoanChange.newRateType!=null && o.tbConLoanChange.newRateType!=''){//有“利率类型”说明有利率信息且不是国结贷款
 			if(o.tbConLoanChange.newRateType=='1'){//固定利率
 				return;
 			}
 			//根据起止期取期限
 			var json1 = nui.encode({"beginDate":beginDate,"endDate":endDate});
			$.ajax({
	            url: "com.bos.bizApply.bizApply.getLoanlength.biz.ext",
	            type: 'POST',
	            data: json1,
        		async: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	var o = nui.decode(mydata);
	            	loanlength = o.loanlength;
				}
			});
 			
 			//根据期限取基准利率
 			var productType= nui.get("tbLoanInfo.productType").getValue();
 			var ratettype = '1';
 			if(productType=='02005001'||productType=='02005003'||productType=='02005010'){
 				ratettype = '2';
 			}
			var json = nui.encode({"loanlength":loanlength,"rateType":ratettype});
			$.ajax({
	            url: "com.bos.bizApply.bizApply.getBasicrate.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	var o = nui.decode(mydata);
	            	nui.get("tbConLoanChange.newBaseRateValue").setValue(o.basicrate);
	            	nui.get("tbConLoanChange.newBaseRateValue").validate();
	            	countRate1();
				}
			});
 		}
 	}
 	
 	//计算利率
	function countRate1(){
 		var baseRate = nui.get("tbConLoanChange.newBaseRateValue").getValue();
 		nui.get("tbConLoanChange.newYearRate").setValue('');
 		var floatWay = nui.get("tbConLoanChange.newFloatWay").getValue();
 		var rateFloatProportion = nui.get("tbConLoanChange.newRateFloatProportion").getValue();
 		if(floatWay&&baseRate){
 			if(rateFloatProportion){
 				if(floatWay=='1'){//上浮
 					nui.get("tbConLoanChange.newYearRate").setValue((parseFloat(baseRate)+parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01).toFixed(6));
 				}else{//下浮
 					nui.get("tbConLoanChange.newYearRate").setValue((parseFloat(baseRate)-parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01).toFixed(6));
 				}
 			}
 		}
 	}
</script>

<script>
	//在主页面初始化时也调用此方法，要做特别处理
	function onselectType(e){
		var reateType= nui.get("tbConLoanChange.newRateType").getValue();
		var productType= nui.get("tbLoanInfo.productType").getValue();
		//alert("productType---" + productType + "productType---" + productType.substr(0,5));
		if(productType.substr(0,5) == "01007" && reateType=="2"){//国际业务
			if(reateType=="2"){//浮动利率
				nui.get("tbConLoanChange.newRateType").setValue(null);
				return nui.alert("国际业务只能选择固定利率！");
			}
		}else if(productType.substr(0,5) != "01007" && reateType=="2"){//浮动利率
			$("#floatdiv").css("display","block");
			$("#base").css("display","block");
			$("#rate1").css("display","block");
			
			nui.get("tbConLoanChange.newYearRate").setEnabled(false);
			nui.get("tbConLoanChange.newYearRate").setValue(null);
			nui.get("tbConLoanChange.newBaseRateValue").setValue(null);
			if("1" == proFlag){
				nui.get("tbConLoanChange.newIrUpdateFrequency").setEnabled(true);
			}
			nui.get("tbConLoanChange.newIrUpdateFrequency").setValue(null);
			//nui.get("tbConLoanChange.newInterestCollectType").setEnabled(true);
			//nui.get("tbConLoanChange.newInterestCollectType").setValue(null);
			
		}else if(reateType=="1"){//固定利率
			$("#floatdiv").css("display","none");
			$("#base").css("display","none");
			$("#rate1").css("display","block");
			
			if("1" == proFlag){
				nui.get("tbConLoanChange.newYearRate").setEnabled(true);
			}
			nui.get("tbConLoanChange.newYearRate").setValue(null);
			nui.get("tbConLoanChange.newIrUpdateFrequency").setEnabled(false);
			nui.get("tbConLoanChange.newIrUpdateFrequency").setValue('05');
			
			nui.get("tbConLoanChange.newFloatWay").setValue(null);
			nui.get("tbConLoanChange.newRateFloatProportion").setValue(null);
			//nui.get("tbConLoanChange.newInterestCollectType").setEnabled(true);
			//nui.get("tbConLoanChange.newInterestCollectType").setValue(null);
		}else{//非反显
			$("#floatdiv").css("display","none");
			$("#base").css("display","none");
			$("#rate1").css("display","none");
		}
	}
	
 </script>

</body>
</html>