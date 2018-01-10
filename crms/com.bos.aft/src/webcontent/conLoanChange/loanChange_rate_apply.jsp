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
		</div>
		<!-- 
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
		</div> 
-->
		
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
	    	<label class="nui-form-label">利率类型：</label>
			<input id="tbConLoanChange.oldRateType" name="tbConLoanChange.oldRateType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1016"/>
				   
			<label class="nui-form-label">浮动方式：</label>
			<input id="tbConLoanChange.oldFloatWay" name="tbConLoanChange.oldFloatWay" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1147"/>
				   
			<label class="nui-form-label">浮动比例（%）：</label>
			<input id="tbConLoanChange.oldRateFloatProportion" class="nui-text nui-form-input" name="tbConLoanChange.oldRateFloatProportion"/>
			
			<label class="nui-form-label">基准利率：</label>
			<input id="tbConLoanChange.oldBaseRateValue" class="nui-text nui-form-input" name="tbConLoanChange.oldBaseRateValue"/> 
			
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbConLoanChange.oldYearRate" class="nui-text nui-form-input" name="tbConLoanChange.oldYearRate"/>
			
			<label class="nui-form-label">利率调整方式：</label>
			<input id="tbConLoanChange.oldIrUpdateFrequency" name="tbConLoanChange.oldIrUpdateFrequency" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1148"/>
				   
			<label id="jjrsybz">节假日顺延标志：</label>
			<input name="tbConLoanrate.holidayFlg" id="tbConLoanrate.holidayFlg"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="XD_0002"  />

			<label id="jjrlxclfs">节假日利息处理方式：</label>
			<input name="tbConLoanrate.holidayIntFlg" id="tbConLoanrate.holidayIntFlg"   data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="XD_SXYW0234"/>
			
			<label id="kxqfs">宽限期方式：</label>
			<input name="tbConLoanrate.gracePeriodType" class="nui-text nui-form-input" dictTypeId="XD_SXYW0210" id="tbConLoanrate.gracePeriodType"  />																

			<label id="gracePeriodDay">宽限期天数：</label>
			<input name="tbConLoanrate.gracePeriodDay" class="nui-text nui-form-input"  id="tbConLoanrate.gracePeriodDay"    />																
			
			
			<!-- <label class="nui-form-label">结息周期：</label>
			<input id="tbConLoanChange.oldInterestCollectType" name="tbConLoanChange.oldInterestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/> -->
	    </div> 
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>

	    <div id="loanrate" class="nui-dynpanel"  columns="4">
			<label class="nui-form-label" >利率类型：</label>
			<input name="tbConLoanChange.newRateType" id="tbConLoanChange.newRateType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1016" required="true" 
			onvaluechanged="onselectType"/>
		</div>
		
		<div id="floatdiv" class="nui-dynpanel"  columns="4" style="width:99.5%;">
			<label class="nui-form-label" id="floatWay">浮动方式：</label>
			<input name="tbConLoanChange.newFloatWay" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1147" id="tbConLoanChange.newFloatWay" required="true" />
		
			<label class="nui-form-label" id="rateFloatProportion">浮动比例(%)：</label>
			<input id="tbConLoanChange.newRateFloatProportion" name="tbConLoanChange.newRateFloatProportion" class="nui-textbox nui-form-input" required="true"  vtype="float;maxLength:11;range:0,10000" onblur="countRate()"/>
		</div>	

		<div id="base" class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">基准利率：</label>
			<input id="tbConLoanChange.newBaseRateValue" class="nui-text nui-form-input" name="tbConLoanChange.newBaseRateValue"/> 
		</div>
		
		<div id="rate1" class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label" id="yearRate">执行利率（%）：</label>
			<input id="tbConLoanChange.newYearRate" name="tbConLoanChange.newYearRate" class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0,100" required="true" />
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label id="isChangeRate">利率调整方式：</label>
			<input id="tbConLoanChange.newIrUpdateFrequency" name="tbConLoanChange.newIrUpdateFrequency" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_SXCD1148"  />
			
			<!-- <label class="nui-form-label">结息周期：</label>
			<input name="tbConLoanChange.newInterestCollectType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1018" id="tbConLoanChange.newInterestCollectType" required="true" /> -->
		</div>

	</fieldset>
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
 	<%-- var contractId ="<%=request.getParameter("contractId") %>"; --%>
	$("#floatdiv").css("display","none");
	$("#base").css("display","none");
	$("#rate1").css("display","none");
	nui.get("tbConLoanrate.gracePeriodDay").hide();
	nui.get("tbConLoanrate.holidayIntFlg").hide();
	$("#gracePeriodDay").css("display","none");
	$("#jjrlxclfs").css("display","none");
	 
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
            	//nui.get("tbConLoanChange.newBaseRateValue").setValue(o.tbLoanLoanrate.baseRateValue);//值被覆盖，重新赋值nui.get("id").setValue(o.name)
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
  		if(nui.get("tbConLoanrate.holidayFlg").getValue()=="1"){
  		
	$("#jjrlxclfs").css("display","block");
	nui.get("tbConLoanrate.holidayIntFlg").show();
  		}
  		if(nui.get("tbConLoanrate.gracePeriodType").getValue()=="2"){
	$("#gracePeriodDay").css("display","block");
  	nui.get("tbConLoanrate.gracePeriodDay").show();
  		}
  		
	 
  	}
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        var partyTypeCd= nui.get("tbCsmParty.partyTypeCd").getValue();
        
      if(nui.get("tbConLoanChange.newYearRate").getValue()>=100) {
         			alert("执行利率不能超过100%");
        			return;
         }
        if(nui.get("tbConLoanChange.newRateFloatProportion").getValue()>=400) {
         			alert("浮动比例不能大于等于400%");
        			return;
         }  
        var repayType = nui.get("tbLoanInfo.repayType").getValue();
        var productType= nui.get("tbConContractInfo.productType").getValue();
        if(repayType=="0100" || repayType=="0200"  || repayType=="0300"   || repayType=="0400"  || repayType=="1700"){
        if(nui.get("tbConLoanChange.newYearRate").getValue()<=0 ){
        if (productType=="02005001" || productType=="02005010" || productType=="02005002" || productType=="02005003"){
        }else{
        alert("分期贷款利率必须大于0!");
        return;
        }
        }
        }
         
        var o = form.getData();
		o.tbConLoanChange.changeId=changeId;
		
		if(o.tbConLoanChange.newRateType=="2" && o.tbConLoanChange.newIrUpdateFrequency=="05") {
			return nui.alert("浮动利率的利率调整方式不可为不调整！");
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
        	//alert("保存成功！");
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
 	
 	//计算基准利率
 	function countRate(){
 		var newFloatWay = nui.get("tbConLoanChange.newFloatWay").getValue();
 		if(!newFloatWay){
 			nui.get("tbConLoanChange.newRateFloatProportion").setValue("");
 			nui.alert("请先选择浮动方式!");
 			return;
 		}
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
 			var productType= nui.get("tbConContractInfo.productType").getValue();
 			var ratettype = '1';
 			if(productType=='02005001'||productType=='02005003' ||productType=='02005010'){
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