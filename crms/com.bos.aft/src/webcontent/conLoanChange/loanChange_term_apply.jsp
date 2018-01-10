<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>展期申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbConLoanChange.oldRepayWay" class="nui-hidden nui-form-input" name ="tbConLoanChange.oldRepayWay"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbLoanLoanrate.baseRateValue" class="nui-hidden nui-form-input" name ="tbLoanLoanrate.baseRateValue"/>
	<input id="tbConContractInfo.endDate" class="nui-hidden nui-form-input" name ="tbConContractInfo.endDate"/>
	<input id="tbConLoanChange.isSmall" class="nui-hidden nui-form-input" name ="tbConLoanChange.isSmall"/>
	<input id="tbLoanSummary.endDate" class="nui-hidden nui-form-input" name ="tbLoanSummary.endDate"/>
	<input id="tbLoanSummary.summaryId" class="nui-hidden nui-form-input" name ="tbLoanSummary.summaryId"/>
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
			<label class="nui-form-label">借据币种：</label>
			<input id="tbLoanSummary.summaryCurrencyCd" name="tbLoanSummary.summaryCurrencyCd"  data="data" valueField="dictID" 
				class="nui-dictcombobox nui-form-input"  dictTypeId="CD000001" emptyText="--请选择--" enabled="false" />	   
			
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt"  class="nui-text nui-form-input" dataType="currency"/>
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye"  class="nui-text nui-form-input" dataType="currency"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">还款方式：</label>
			<input id="tbLoanInfo.repayType" name="tbLoanInfo.repayType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1162"/>
			<label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<!-- <label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/> -->
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input" vtype="maxLength:900"  vtype="maxLength:999"/> 
		</div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>变更前</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
			
			<label class="nui-form-label">起始日期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />

			<label class="nui-form-label">到期日期：</label>
			<input id="tbConLoanChange.oldEndDate" name="tbConLoanChange.oldEndDate" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd" />
			<!-- <input id="tbLoanSummary.endDate" name="tbLoanSummary.endDate" class="nui-text nui-form-input" /> -->
			
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
			
			<label class="nui-form-label">罚息率（%）：</label>
			<input id="tbConLoanChange.oldOverdueRate" class="nui-text nui-form-input" name="tbConLoanChange.oldOverdueRate"/>
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
    	<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	    	<label class="nui-form-label">展期方式：</label>
			<!-- <input id="tbConLoanChange.termChangeWay" name="tbConLoanChange.termChangeWay" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_QXBG0001" onvaluechanged="onselectTermType"/> -->
				<input id="tbConLoanChange.termChangeWay" name="tbConLoanChange.termChangeWay" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_QXBG0001" enabled="false"/>
		</div>
		
		<div id="ratetype" class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label" >利率类型：</label>
			<input name="tbConLoanChange.newRateType" id="tbConLoanChange.newRateType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1016" required="true" onvaluechanged="onselectType"/>
		</div>
		
		<div id="floatdiv" class="nui-dynpanel"  columns="4" style="width:99.5%;">
			<label class="nui-form-label" id="floatWay">浮动方式：</label>
			<input name="tbConLoanChange.newFloatWay" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1147" id="tbConLoanChange.newFloatWay" required="true" onvaluechanged="onselectType"/>
			
			<label class="nui-form-label" id="rateFloatProportion">浮动比例（%）：</label>
			<input id="tbConLoanChange.newRateFloatProportion" name="tbConLoanChange.newRateFloatProportion" class="nui-textbox nui-form-input" required="true"  vtype="float;maxLength:11"/>
		</div>	
		
		<div id="base" class="nui-dynpanel"  columns="4">
		 	<label class="nui-form-label" id="basicValue">基准利率（%）：</label>
			<input id="tbConLoanChange.newBaseRateValue" name="tbConLoanChange.newBaseRateValue" class="nui-text nui-form-input" />
		</div>
		
		<div id="rate2" class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label" id="yearRate">执行利率（%）：</label>
			<input id="tbConLoanChange.newYearRate" name="tbConLoanChange.newYearRate" class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0.000001,100" required="true" /> 
		</div>
		
		<!-- 逾期利率 -->
		<div id="rate3" class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label" id="overdueRate">罚息率（%）：</label>
			<input id="tbConLoanChange.newOverdueRate" name="tbConLoanChange.newOverdueRate" class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0.000001,100" required="true" /> 
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">到期日期：</label>
			<input id="tbConLoanChange.newEndDate" name="tbConLoanChange.newEndDate" class="nui-datepicker nui-form-input"   required="true" format="yyyy-MM-dd"  onvaluechanged="validateEndDate"/>
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
	var loanChangeType ="<%=request.getParameter("loanChangeType") %>";//类型：06-展期 19-期限调整
	var oldType;
	var oldTerm;
 	var newTerm;
 	
	//$("#ratetype").css("display","none");--展期和期限调整不需要
	$("#floatdiv").css("display","none");
	$("#base").css("display","none");
	$("#rate2").css("display","none");
	
	initPage();
	//初始化页面
	function initPage(){
		//alert(changeId);
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	//alert(changeId.tbCsmParty.partyTypeCd);
            	var o = nui.decode(mydata);
            	form.setData(o);
				//nui.get("tbConLoanChange.newBaseRateValue").setValue(o.tbLoanLoanrate.baseRateValue);
				if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	query1();
            	onselectTermType();
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
  		//6个可以做展期的国结产品(01007001 01007003 01007004 01007011 01007006 01007005)没有延期和缩期
  		//页面新增了逾期利率  国结产品的展期有逾期利率信息
		var productType = nui.get("#tbConContractInfo.productType").getValue();
		if(productType=="01007001"||productType=="01007003"||productType=="01007004"||
	   		productType=="01007005"||productType=="01007006"||productType=="01007011"){
	   		//只能做展期
			nui.get("tbConLoanChange.termChangeWay").setValue("03");
			nui.get("tbConLoanChange.termChangeWay").setEnabled(false);
			//固定利率---直接输入
			nui.get("tbConLoanChange.newRateType").setValue("1");
			nui.get("tbConLoanChange.newRateType").setEnabled(false);
		}
  	}
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
       
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId;
		
 		var oldEndDate = o.tbLoanSummary.endDate;//原借据止期
 		var newEndDate = o.tbConLoanChange.newEndDate;//新借据止期
 		var termChangeWay = o.tbConLoanChange.termChangeWay;//期限变更方式
		var oldRepayWay= o.tbConLoanChange.oldRepayWay;
		
		//alert("到期日--->" + newEndDate + "原到期日--->" + oldEndDate);
	 	if(termChangeWay=="01"){//延期
 			if(newEndDate.substr(0,10)<=oldEndDate){
	 			return nui.alert("延期到期日要大于原到期日");
	 		}
 		}else if(termChangeWay=="02"){//缩期
 			if(newEndDate.substr(0,10)>=oldEndDate){
	 			return nui.alert("缩期到期日要小于原到期日");
	 		}
 		}else if(termChangeWay=="03"){//展期
 			if(newEndDate.substr(0,10)<=oldEndDate){
	 			return nui.alert("展期到期日要大于原到期日");
	 		}
 		}
		
		if(termChangeWay=="01" || termChangeWay=="02") {
			if(oldRepayWay=="1100" || oldRepayWay=="1200" || oldRepayWay=="1300" || oldRepayWay=="1400" || oldRepayWay=="1410") {
				return nui.alert("只有等额本金，等额本息，阶段性等额本息，阶段性等额本金可以做延期或缩期");
			}
		}
		if(termChangeWay=="03") {
			if(oldRepayWay=="0100" || oldRepayWay=="0200" || oldRepayWay=="0300" || oldRepayWay=="0400") {
				return nui.alert("只有到期一次性还本付息、按周期还息到期一次还本、按周期还息任意还本、按周期还息按还本计划表还本、按还本计划表还息按还本计划表还本可以做展期");
			}
		}
		      if(nui.get("tbConLoanChange.newRateFloatProportion").getValue()>=400) {
         			alert("浮动比例不能大于等于400%");
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
        		nui.alert(text.msg); 
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	function validateEndDate(){
		//alert((nui.get("tbConLoanChange.newEndDate").getValue()).substr(0,10));
		 countRate();
 		var o = form.getData();
 		var beginDate = o.tbLoanSummary.beginDate;//借据起期
 		var oldEndDate = o.tbLoanSummary.endDate;//原借据止期
 		var newEndDate = o.tbConLoanChange.newEndDate;//新借据止期
 		var conEndDate = o.tbConContractInfo.endDate;//合同止期
 		var termChangeWay = o.tbConLoanChange.termChangeWay;//期限变更方式
 		var isSmall = o.tbConLoanChange.isSmall;//是否小贷
 		var extendDate = nui.get("tbConLoanChange.newEndDate").getValue().substr(0,10);
 		if(newEndDate!=null && newEndDate!=''){
 			if(newEndDate.substr(0,10)<=beginDate){
	 			nui.alert("到期日要大于起始日");
	 			nui.get("tbConLoanChange.newEndDate").setValue('');
	 			return;
	 		}
	 		/* if(newEndDate>conEndDate){
	 			nui.alert("到期日不能大于合同到期日");
	 			nui.get("tbConLoanChange.newEndDate").setValue('');
	 			return;
	 		} */
	 		
	 		if(isSmall=="0") {
		 		if(termChangeWay=="01"){//延期
		 			//alert("延期到期日--->" + newEndDate + "原到期日--->" + oldEndDate);
		 			if(newEndDate.substr(0,10)<=oldEndDate){
			 			nui.alert("延期到期日要大于原到期日");
			 			nui.get("tbConLoanChange.newEndDate").setValue('');
			 			return;
			 		}
			 		
			 		if(oldType == "短") {//一年内
			 			if(parseFloat(newTerm) > parseFloat(oldTerm)) {
				 			nui.alert("短期贷款展期期限累计不超过原贷款期限");
				 			nui.get("tbConLoanChange.newEndDate").setValue('');
				 			return;
			 			} 
			 		}else if(oldType == "中") {//一到五年
			 			if(parseFloat(newTerm) > (parseFloat(oldTerm)/2)) {
				 			nui.alert("中期贷款展期期限累计不得超过原贷款期限的一半");
				 			nui.get("tbConLoanChange.newEndDate").setValue('');
				 			return;
			 			} 
			 		}else if(oldType == "长") {//五年以上
			 			if(parseFloat(newTerm) > 36) {
				 			nui.alert("长期贷款展期期限累计不得超过三年");
				 			nui.get("tbConLoanChange.newEndDate").setValue('');
				 			return;
			 			}
			 		} 
		 		}else if(termChangeWay=="02"){//缩期
		 			//alert("缩期到期日--->" + newEndDate + "原到期日--->" + oldEndDate);
		 			if(newEndDate.substr(0,10)>=oldEndDate){
			 			nui.alert("缩期到期日要小于原到期日");
			 			nui.get("tbConLoanChange.newEndDate").setValue('');
			 			return;
			 		}
		 		}else if(termChangeWay=="03"){//展期
		 			//alert("展期到期日--->" + newEndDate + "原到期日--->" + oldEndDate);
		 			if(newEndDate.substr(0,10)<=oldEndDate){
			 			nui.alert("展期到期日要大于原到期日");
			 			nui.get("tbConLoanChange.newEndDate").setValue('');
			 			return;
			 		}
			 		//业务部门要求：逾期展期不做控制
			 		//获取当前系统时间 
					/* var thisDate="";
					$.ajax({
			            url: "com.bos.pay.LoanSummary.getCurrentTime.biz.ext",
			            type: 'POST',
			            data: '',
			            cache: false,
			            async : false,
			            contentType:'text/json',
			            success: function (text) {
			            	thisDate = text.currentTime;
			            }
			        });
			        //当前系统时间
					var arrn = thisDate.split("-");
					var nowtime = new Date(arrn[0],arrn[1],arrn[2]);
					var nowtims = nowtime.getTime();
					//借据到期日 
					var summaryEnd = nui.get("tbConLoanChange.oldEndDate").getValue();
					var arrs = summaryEnd.split("-");
					var begintime = new Date(arrs[0],arrs[1],arrs[2].substring(0,2));
					var begintims = begintime.getTime(); */
					var summaryId = nui.get("tbLoanSummary.summaryId").getValue();
					var jsonn = nui.encode({"summaryId":summaryId,"beginDate":beginDate,"extendDate":extendDate});
					$.ajax({
			            url: "com.bos.aft.conLoanChange.queryOtherPeriod.biz.ext",
			            type: 'POST',
			            data: jsonn,
			            contentType:'text/json',
			            cache: false,
			            async : false,
			            success: function (mydata) {
			             var oldTerm = mydata.oldTerm;//借据原来期限
			            var caclTerm = mydata.caclTerm;//计算借据的期限
			            var termType = mydata.termType;//期限类型
			             var sum = mydata.sum;//是否做过展期
			            if(sum>0){
			            	if(termType == "短") {//一年内
					 			if(parseFloat(caclTerm) > parseFloat(oldTerm)) {
						 			nui.alert("短期贷款展期期限累计不超过原贷款期限");
						 			nui.get("tbConLoanChange.newEndDate").setValue('');
						 			return;
					 			} 
					 		}else if(termType == "中") {//一到五年
					 			if(parseFloat(caclTerm) > (parseFloat(oldTerm)/2)) {
						 			nui.alert("中期贷款展期期限累计不得超过原贷款期限的一半");
						 			nui.get("tbConLoanChange.newEndDate").setValue('');
						 			return;
					 			} 
					 		}else if(termType == "长") {//五年以上
					 			if(parseFloat(caclTerm) > 36) {
						 			nui.alert("长期贷款展期期限累计不得超过三年");
						 			nui.get("tbConLoanChange.newEndDate").setValue('');
						 			return;
					 			}
					 		} 
					 	  }else{
					 	  		countTerm();//计算期限
					 	  		if(oldType == "短") {//一年内
						 			if(parseFloat(newTerm) > parseFloat(oldTerm)) {
							 			nui.alert("短期贷款展期期限累计不超过原贷款期限");
							 			nui.get("tbConLoanChange.newEndDate").setValue('');
							 			return;
						 			} 
						 		}else if(oldType == "中") {//一到五年
						 			if(parseFloat(newTerm) > (parseFloat(oldTerm)/2)) {
							 			nui.alert("中期贷款展期期限累计不得超过原贷款期限的一半");
							 			nui.get("tbConLoanChange.newEndDate").setValue('');
							 			return;
						 			} 
						 		}else if(oldType == "长") {//五年以上
						 			if(parseFloat(newTerm) > 36) {
							 			nui.alert("长期贷款展期期限累计不得超过三年");
							 			nui.get("tbConLoanChange.newEndDate").setValue('');
							 			return;
						 			}
						 		} 
					 	  }
						}
				});
			 	
			 	  }
		 		}
	 		}else {
	 		
	 			if(termChangeWay=="01"){//延期
		 			if(newEndDate.substr(0,10)<=oldEndDate){
			 			nui.alert("延期到期日要大于原到期日");
			 			nui.get("tbConLoanChange.newEndDate").setValue('');
			 			return;
			 		}
			 		
		 		}else if(termChangeWay=="02"){//缩期
		 			if(newEndDate.substr(0,10)>=oldEndDate){
			 			nui.alert("缩期到期日要小于原到期日");
			 			nui.get("tbConLoanChange.newEndDate").setValue('');
			 			return;
			 		}
		 		}else if(termChangeWay=="03"){//展期
		 			if(newEndDate.substr(0,10)<=oldEndDate){
			 			nui.alert("展期到期日要大于原到期日");
			 			nui.get("tbConLoanChange.newEndDate").setValue('');
			 			return;
			 		}
			 		
		 		}
	 		
	 		}
	 		
	 	//	countRate();
	 		
 		}
 		
 //	}
 	
 	//计算期限
 	function countTerm(){
 		var o = form.getData();
 		var beginDate = o.tbLoanSummary.beginDate;//借据起期
 		var oldEndDate = o.tbLoanSummary.endDate;//原借据止期
 		var newEndDate = o.tbConLoanChange.newEndDate;//新借据止期
 		
 		//计算期限
 		var json1 = nui.encode({"beginDate":beginDate,"oldEndDate":oldEndDate,"newEndDate":newEndDate});
		$.ajax({
            url: "com.bos.aft.conLoanChange.getTerm.biz.ext",
            type: 'POST',
            data: json1,
    		async: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	oldType = o.oldType;
            	oldTerm = o.oldTerm;
            	newTerm = o.newTerm;
            	//alert(oldType + "===" + oldTerm + "===" + newTerm);
			}
		});
 	}
 	
 	//计算基准利率
 	function countRate(){
 		var o = form.getData();
 		var beginDate = o.tbLoanSummary.beginDate;//借据起期
 		var endDate = o.tbConLoanChange.newEndDate;//新借据止期
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
 			}else{
 				nui.alert("请填写浮动比例");
 				nui.get("tbConLoanChange.newEndDate").setValue('');
			 	return;
 			}
 		}
 	}
 	function onselectTermType(){
		/* var termChangeWay = nui.get("tbConLoanChange.termChangeWay").getValue();
		if(termChangeWay=="01" || termChangeWay=="02"){//延期，缩期
			$("#ratetype").css("display","none");
			$("#floatdiv").css("display","none");
			$("#base").css("display","none");
			$("#rate2").css("display","none");

			nui.get("tbConLoanChange.newRateType").setValue(null);
			nui.get("tbConLoanChange.newFloatWay").setValue(null);
			nui.get("tbConLoanChange.newRateFloatProportion").setValue(null);
			nui.get("tbConLoanChange.newBaseRateValue").setValue(null);
			nui.get("tbConLoanChange.newYearRate").setValue(null);
			
		}else if(termChangeWay=="03"){//正常展期
			$("#ratetype").css("display","block");
			nui.get("tbConLoanChange.newEndDate").setValue(null);
		}else if(termChangeWay=="04"){//逾期展期
			$("#ratetype").css("display","block");
			nui.get("tbConLoanChange.newEndDate").setValue(null);
			
		}else{//非反显
			$("#ratetype").css("display","none");
			$("#floatdiv").css("display","none");
			$("#base").css("display","none");
			$("#rate2").css("display","none");
			
			nui.get("tbConLoanChange.newRateType").setValue(null);
			nui.get("tbConLoanChange.newFloatWay").setValue(null);
			nui.get("tbConLoanChange.newRateFloatProportion").setValue(null);
			nui.get("tbConLoanChange.newBaseRateValue").setValue(null);
			nui.get("tbConLoanChange.newYearRate").setValue(null);
		} */
		if(loanChangeType == '06'){
			$("#ratetype").css("display","block");
			nui.get("tbConLoanChange.termChangeWay").setValue("03")
			//nui.get("tbConLoanChange.newEndDate").setValue(null);
		}else if(loanChangeType == '19'){
			$("#ratetype").css("display","block");
			nui.get("tbConLoanChange.termChangeWay").setValue("04")
			//nui.get("tbConLoanChange.newEndDate").setValue(null);
		}else{//非反显
			$("#ratetype").css("display","none");
			$("#floatdiv").css("display","none");
			$("#base").css("display","none");
			$("#rate2").css("display","none");
			
			nui.get("tbConLoanChange.newRateType").setValue(null);
			nui.get("tbConLoanChange.newFloatWay").setValue(null);
			nui.get("tbConLoanChange.newRateFloatProportion").setValue(null);
			nui.get("tbConLoanChange.newBaseRateValue").setValue(null);
			nui.get("tbConLoanChange.newYearRate").setValue(null);
		}
	} 
</script>

<script>
	
	
	

	function onselectType(e){
		var reateType= nui.get("tbConLoanChange.newRateType").getValue();
		if(reateType=="2"){//浮动利率
			$("#floatdiv").css("display","block");
			$("#base").css("display","block");
			$("#rate2").css("display","block");

			nui.get("tbConLoanChange.newYearRate").setEnabled(false);
			nui.get("tbConLoanChange.newYearRate").setValue(null);
			
			nui.get("tbConLoanChange.newBaseRateValue").setValue(null);
			nui.get("tbConLoanChange.newEndDate").setValue(null);
			var provalue = nui.get("tbConContractInfo.productType").getValue();
			var prov = provalue.substr(0,5);
			if("01007"==prov ||"01013"==prov ||"02005"==prov){
				nui.get("tbConLoanChange.newOverdueRate").setValue(null);
			}else{
				nui.get("tbConLoanChange.newOverdueRate").setValue("50");
				nui.get("tbConLoanChange.newOverdueRate").setEnabled(false);
			}
		}else if(reateType=="1"){//固定利率
			$("#floatdiv").css("display","none");
			$("#base").css("display","none");
			$("#rate2").css("display","block");
			
			if("1" == proFlag){
				nui.get("tbConLoanChange.newYearRate").setEnabled(true);
			}
			nui.get("tbConLoanChange.newYearRate").setValue(null);
			
			nui.get("tbConLoanChange.newFloatWay").setValue(null);
			nui.get("tbConLoanChange.newRateFloatProportion").setValue(null);
			nui.get("tbConLoanChange.newEndDate").setValue(null);
			var provalue = nui.get("tbConContractInfo.productType").getValue();
			var prov = provalue.substr(0,5);
			if("01007"==prov ||"01013"==prov ||"02005"==prov){
				nui.get("tbConLoanChange.newOverdueRate").setValue(null);
			}else{
				nui.get("tbConLoanChange.newOverdueRate").setValue("50");
				nui.get("tbConLoanChange.newOverdueRate").setEnabled(false);
			}
		}else{//非反显
			$("#floatdiv").css("display","none");
			$("#base").css("display","none");
			$("#rate2").css("display","none");
		}
	}
 </script>

</body>
</html>