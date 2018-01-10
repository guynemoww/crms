<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-11-26
  - Description:
-->
<head>
<title>提前还款申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbLoanInfo.loanId" class="nui-hidden nui-form-input" name ="tbLoanInfo.loanId"/>
	<input id="tbLoanInfo.repayType" class="nui-hidden nui-form-input" name ="tbLoanInfo.repayType"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbConLoanChange.oldRepayAccount" class="nui-hidden nui-form-input" name ="tbConLoanChange.oldRepayAccount"/>
	<input id="tbLoanSummary.summaryStatusCd" class="nui-hidden nui-form-input" name ="tbLoanSummary.summaryStatusCd"/>
	
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
			
			<label class="nui-form-label">还款账号：</label>
			<input id="tbLoanZh.zh" class="nui-text nui-form-input" name="tbLoanZh.zh"/>
			
			<label class="nui-form-label">还款账户名称：</label>
			<input id="tbLoanZh.zhmc" class="nui-text nui-form-input" name="tbLoanZh.zhmc"/>
		</div>
		
		<!-- <div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
		</div> -->
		
	</fieldset>
	
	<div class="nui-dynpanel" columns="1" id="table_account">
		<%@include file="/aft/conLoanChange/change_account_info.jsp"%>
	</div> 
	
	<fieldset>
		<legend>
	    	<span>申请信息</span>
	    </legend>
	    
	   <div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">是否结清：</label>
			<input id="tbConLoanChange.isSettle" name="tbConLoanChange.isSettle" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectSettle" />
		</div>
		
		<div class="nui-dynpanel" id="div01" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnJQSS" onclick="checkJQSS">结清试算</a>
		</div>
		
		<div class="nui-dynpanel" id="div1" columns="4">
				
			<label class="nui-form-label">还款类型：</label>
			<input id="tbConLoanChange.repayType" name="tbConLoanChange.repayType" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_HKLX0001" onvaluechanged="onselectSettle2" />
				
			<label class="nui-form-label">还款顺序：</label>
			<input id="tbConLoanChange.repayOrder" name="tbConLoanChange.repayOrder" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_HKSS0001" />
		</div>
		
		<div class="nui-dynpanel" id="div02" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnHKSS" onclick="checkHKSS">还款试算</a>
		</div>
		
		<div class="nui-dynpanel" id="div03" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnHBSS" onclick="checkHBSS">还本试算</a>
		</div>
		
		<div class="nui-dynpanel" id="div04" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnJQDQQSS" onclick="checkJQDQQSS">结清当前期试算</a>
		</div>
		
		<div class="nui-dynpanel" id="div2" columns="4">
			<label class="nui-form-label">指定还款金额：</label>
			<input id="tbConLoanChange.repayAmt"  name="tbConLoanChange.repayAmt" 
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" />
		</div>
		
		<div class="nui-dynpanel" id="div3" columns="4">
			<label class="nui-form-label">提前还本金额：</label>
			<input id="tbConLoanChange.repayCapital"  name="tbConLoanChange.repayCapital" 
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" />
		</div>
		
		<div class="nui-dynpanel" id="div6" columns="4">
			<label class="nui-form-label">应还总额：</label>
			<input id="tbConLoanChange.yhze"  name="tbConLoanChange.yhze" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
			<label class="nui-form-label">应还本金：</label>
			<input id="tbConLoanChange.yhbj"  name="tbConLoanChange.yhbj" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div>
		
		<div class="nui-dynpanel" id="div4" columns="4">
			<label class="nui-form-label">应还正常利息：</label>
			<input id="tbConLoanChange.yhzclx"  name="tbConLoanChange.yhzclx" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
			<label class="nui-form-label">应还拖欠利息：</label>
			<input id="tbConLoanChange.yhtqlx"  name="tbConLoanChange.yhtqlx" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div>
		
		<div class="nui-dynpanel" id="div5" columns="4">
			<label class="nui-form-label">应还罚息：</label>
			<input id="tbConLoanChange.yhfx"  name="tbConLoanChange.yhfx"  
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
		</div> 
		
		<!-- 1400按周期还息按还本计划表还本;1410按还本计划表还息按还本计划表还本 -->
		<div id="isModifyPlan" class="nui-dynpanel"  columns="4">
			<label class="nui-form-label">是否修改还本计划表：</label>
			<input id="tbConLoanChange.isModifyPlan" name="tbConLoanChange.isModifyPlan" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectModify" />
		</div>
	</fieldset>
	
	<div class="nui-dynpanel" columns="1" id="table3">
		<%@include file="/aft/conLoanChange/include_old_repayplan.jsp"%>
	</div> 
	
	<div class="nui-dynpanel" columns="1" id="table4">
		<%@include file="/aft/conLoanChange/include_new_repayplan.jsp"%>
	</div> 
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
			<a class="nui-button" id="jl_save" iconCls="icon-save" onclick="savejl">计量处理</a>
			<!-- <a class="nui-button" id="btnDownload" onclick="clickDownload()">下载打印</a> -->
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	<%-- var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识 --%>
	
	var tbLoanSummary;
	var tbConLoanChange;
	var isSmall;
	var repayType;
	var busDate;
	
	$("#table3").css("display","none");
	$("#table4").css("display","none");
	
	$("#div1").css("display","none");
	$("#div2").css("display","none");
	$("#div3").css("display","none");
	$("#div4").css("display","none");
	$("#div5").css("display","none");
	$("#div6").css("display","none");
	
	$("#div01").css("display","none");
	$("#div02").css("display","none");
	$("#div03").css("display","none");
	$("#div04").css("display","none");
			
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
            success: function (mydata) {
            	busDate = mydata.busDate;
            	
            	/* if(mydata.tbConLoanChange.isSettle != null && mydata.tbConLoanChange.isSettle != "") {
            		nui.get("btnDownload").setEnabled(true);
            	}else {
            		nui.get("btnDownload").setEnabled(false);
            	} */
            	
            	/* if(mydata.tbConLoanChange.changeStatus != "03") {
            		nui.get("btnDownload").hide();
            	} */
            	
            	var o = nui.decode(mydata);
            	form.setData(o);
            	
            	tbLoanSummary = o.tbLoanSummary;
            	tbConLoanChange = o.tbConLoanChange;
            	isSmall = o.tbConLoanChange.isSmall;
            	repayType = o.tbLoanInfo.repayType;
            	//nui.get("tbConLoanChange.repayOrder").setEnabled(false);
            	
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	
            	if(tbConLoanChange.isSmall == "1") {
            		$("#table3").css("display","none");
					$("#table4").css("display","none");
            	}
            	
            	query1();
			}
        });
        
		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		/* if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			
			nui.get("btnJQSS").hide();
			nui.get("btnHKSS").hide();
			nui.get("btnHBSS").hide();
			nui.get("btnJQDQQSS").hide();
			
			form.setEnabled(false);
		} */
        
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
	
  		var o = form.getData();
  		//var repayType = o.tbLoanInfo.repayType;
  		
  		if(o.tbConLoanChange.isSettle == "1") {
    		$("#isModifyPlan").css("display","none");
    	}else {
    		if(repayType == "1400" || repayType == "1410") {
	  			$("#isModifyPlan").css("display","block");
	  		}else {
	  			$("#isModifyPlan").css("display","none");
	  		}
    	} 
  		
  		if(isSmall == "0") {
	  		var json = nui.decode({"loanId":o.tbLoanInfo.loanId,"changeId":changeId});
			var grid1 = nui.get("grid1");
	    	var grid2 = nui.get("grid2");
	    	grid1.load(json);
			grid2.load(json);
			 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
			/* if("1" != proFlag){
				nui.get("grid2add").hide();
				nui.get("grid2del").hide();
				form.setEnabled(false);
			}  */
  		}

	}
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"newPeriodsNum":++count};
        nui.get(gr).addRow(row,count);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.repayplanChangeId){
            		var json = nui.encode({"repayplanChangeId":row.repayplanChangeId});
	            	$.ajax({
			            url: "com.bos.aft.conLoanChange.deleteRepayplanChange.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else{
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		
		if(o.tbConLoanChange.yhzclx==null || o.tbConLoanChange.yhzclx == "") {
			return nui.alert("请先试算！");
		}
		
		if(o.tbConLoanChange.isSettle=="0"&&""==o.tbConLoanChange.repayOrder){
			return nui.alert("请填写还款顺序！");
		}
		
		
		o.tbConLoanChange.changeId=changeId;
		o.changeId = changeId;
		o.loanId = o.tbLoanInfo.loanId;
		
		if(nui.get("tbConLoanChange.isModifyPlan").getValue() == "1" && nui.get("tbConLoanChange.repayOrder").getValue() != "00") {
			nui.alert("有还本计划变更必须选择默认序！");
		    return;
		}
		
		if(isSmall == "0") {
			if(nui.get("tbConLoanChange.isModifyPlan").getValue() == "1") {
				var repayPlans = nui.get("grid1").getChanges();/* 还款 */
				var repayPlans2 = nui.get("grid2").getChanges();/* 还款 */
				o.repayPlans = repayPlans;
				o.repayPlans2 = repayPlans2;
				
				var size1 = nui.get("grid2").getData().length;
				if (size1 == 0){
		        	nui.alert("请填写还款计划");
		        	return;
		        } 
				
				var amt = 0;
				var planNum=[];
				for(var i=0; i<nui.get("grid2").getData().length;i++){
					if(nui.get("grid2").getData()[i].newRepayDate==null || nui.get("grid2").getData()[i].newRepayDate==''){
						nui.alert("日期不能为空");
				 		return;
		 			}
		 			if(nui.get("grid2").getData()[i].newRepayAmt==null || nui.get("grid2").getData()[i].newRepayAmt==''){
						nui.alert("金额不能为空");
				 		return;
		 			}
		 			
		 			if(nui.get("grid2").getData()[i].newRepayDate.substr(0,10)<busDate) {
		 				nui.alert("还款日不应小于当前营业日期！");
				 		return;
		 			}
		 			
		 			if(nui.get("grid2").getData()[i].newRepayDate.substr(0,10)>nui.get("tbLoanSummary.endDate").getValue()) {
		 				nui.alert("还款日不应大于借据止期！");
				 		return;
		 			} 
					
					planNum[i] = nui.get("grid2").getData()[i].newPeriodsNum;
		 			
		 			if(nui.get("grid2").getData()[i].newPeriodsNum > nui.get("grid2").getData().length 
		 				|| nui.get("grid2").getData()[i].newPeriodsNum < 1) {
		 				nui.alert("期数不正确！");
					 	return;
			 		}
		 			
		 			if(i==0) {
		 			
		 			}else {
			 			if(nui.get("grid2").getData()[i].newPeriodsNum > nui.get("grid2").getData()[i-1].newPeriodsNum 
			 				&& nui.get("grid2").getData()[i].newRepayDate < nui.get("grid2").getData()[i-1].newRepayDate) {
					 		nui.alert("还款日应大于上期还款日！");
						 	return;
			 			}else if(nui.get("grid2").getData()[i].newPeriodsNum < nui.get("grid2").getData()[i-1].newPeriodsNum 
			 				&& nui.get("grid2").getData()[i].newRepayDate > nui.get("grid2").getData()[i-1].newRepayDate) {
			 				nui.alert("还款日应大于上期还款日！");
						 	return;
			 			}
		 			} 
		 			
		 			amt += parseFloat(nui.get("grid2").getData()[i].newRepayAmt);
		 			nui.get("grid2").getData()[i].changeId=changeId;
		    	}
		    	
		    	var planNum2 = planNum.sort();
		    	for(var i=0;i<planNum2.length-1;i++) {
		    		if(planNum2[i] == planNum2[i+1]) {
		    			nui.alert("期次重复！");
						return;
		    		}
		    	}
		    	
		    	if(tbConLoanChange.repayType=='02'){//提前还本
		    		var temp = o.tbLoanSummary.jjye - o.tbConLoanChange.yhbj - o.tbConLoanChange.repayCapital;
		    	}else{
		    		var temp = o.tbLoanSummary.jjye - o.tbConLoanChange.yhbj;
		    	}
		    	//alert("temp--->" + parseFloat(temp).toFixed(2) + "amt--->" + parseFloat(amt).toFixed(2));
		    	if (parseFloat(temp).toFixed(2)!=parseFloat(amt).toFixed(2)){
		        	nui.alert("累计金额应等于借据余额");
		        	return;
		        }
			
			}
			
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
        	initPage();
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	function savejl(){
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		
		if(o.tbConLoanChange.isSettle==null || o.tbConLoanChange.isSettle == "") {
			return nui.alert("请先保存！");
		}
		
		if(o.tbConLoanChange.yhzclx==null || o.tbConLoanChange.yhzclx == "") {
			return nui.alert("请先试算！");
		}
		
		if(o.tbConLoanChange.isSettle=="0"&&""==o.tbConLoanChange.repayOrder){
			return nui.alert("请填写还款顺序！");
		}
		
		o.tbConLoanChange.changeId=changeId;
		o.changeId = changeId;
		o.loanId = o.tbLoanInfo.loanId;
		
		if(nui.get("tbConLoanChange.isModifyPlan").getValue() == "1" && nui.get("tbConLoanChange.repayOrder").getValue() != "00") {
			nui.alert("有还本计划变更必须选择默认序！");
		    return;
		}
		
		if(isSmall == "0") {
			if(nui.get("tbConLoanChange.isModifyPlan").getValue() == "1") {
				var repayPlans = nui.get("grid1").getChanges();/* 还款 */
				var repayPlans2 = nui.get("grid2").getChanges();/* 还款 */
				o.repayPlans = repayPlans;
				o.repayPlans2 = repayPlans2;
				
				var size1 = nui.get("grid2").getData().length;
				if (size1 == 0){
		        	nui.alert("请填写还款计划");
		        	return;
		        } 
				
				var amt = 0;
				var planNum=[];
				for(var i=0; i<nui.get("grid2").getData().length;i++){
					if(nui.get("grid2").getData()[i].newRepayDate==null || nui.get("grid2").getData()[i].newRepayDate==''){
						nui.alert("日期不能为空");
				 		return;
		 			}
		 			if(nui.get("grid2").getData()[i].newRepayAmt==null || nui.get("grid2").getData()[i].newRepayAmt==''){
						nui.alert("金额不能为空");
				 		return;
		 			}
		 			
		 			if(nui.get("grid2").getData()[i].newRepayDate.substr(0,10)<busDate) {
		 				nui.alert("还款日不应小于当前营业日期！");
				 		return;
		 			}
		 			
		 			if(nui.get("grid2").getData()[i].newRepayDate.substr(0,10)>nui.get("tbLoanSummary.endDate").getValue()) {
		 				nui.alert("还款日不应大于借据止期！");
				 		return;
		 			} 
					
					planNum[i] = nui.get("grid2").getData()[i].newPeriodsNum;
		 			
		 			if(nui.get("grid2").getData()[i].newPeriodsNum > nui.get("grid2").getData().length 
		 				|| nui.get("grid2").getData()[i].newPeriodsNum < 1) {
		 				nui.alert("期数不正确！");
					 	return;
			 		}
		 			
		 			if(i==0) {
		 			
		 			}else {
			 			if(nui.get("grid2").getData()[i].newPeriodsNum > nui.get("grid2").getData()[i-1].newPeriodsNum 
			 				&& nui.get("grid2").getData()[i].newRepayDate < nui.get("grid2").getData()[i-1].newRepayDate) {
					 		nui.alert("还款日应大于上期还款日！");
						 	return;
			 			}else if(nui.get("grid2").getData()[i].newPeriodsNum < nui.get("grid2").getData()[i-1].newPeriodsNum 
			 				&& nui.get("grid2").getData()[i].newRepayDate > nui.get("grid2").getData()[i-1].newRepayDate) {
			 				nui.alert("还款日应大于上期还款日！");
						 	return;
			 			}
		 			} 
		 			
		 			amt += parseFloat(nui.get("grid2").getData()[i].newRepayAmt);
		 			nui.get("grid2").getData()[i].changeId=changeId;
		    	}
		    	
		    	var planNum2 = planNum.sort();
		    	for(var i=0;i<planNum2.length-1;i++) {
		    		if(planNum2[i] == planNum2[i+1]) {
		    			nui.alert("期次重复！");
						return;
		    		}
		    	}
		    	
		    	if(tbConLoanChange.repayType=='02'){//提前还本
		    		var temp = o.tbLoanSummary.jjye - o.tbConLoanChange.yhbj - o.tbConLoanChange.repayCapital;
		    	}else{
		    		var temp = o.tbLoanSummary.jjye - o.tbConLoanChange.yhbj;
		    	}
		    	//alert("temp--->" + parseFloat(temp).toFixed(2) + "amt--->" + parseFloat(amt).toFixed(2));
		    	if (parseFloat(temp).toFixed(2)!=parseFloat(amt).toFixed(2)){
		        	nui.alert("累计金额应等于借据余额");
		        	return;
		        }
			
			}
			
		}
		
		nui.get("con_contract_info_save").setEnabled(false);
		
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveAccountrepay.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg != "1"){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        	}else {
        		nui.alert("计量处理成功！"); 
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	initPage();
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	function onselectSettle(e){
		var isSettle= nui.get("tbConLoanChange.isSettle").getValue();//是否结清
		if(isSettle=="1"){//结清
			nui.get("tbConLoanChange.repayOrder").setValue("00");//结清是给还款顺序赋值为默认序
			$("#isModifyPlan").css("display","none");
			$("#div1").css("display","none");//屏蔽还款顺序
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","block");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
			
			nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            nui.get("tbConLoanChange.repayType").setValue("");
            nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
			
		}else if(isSettle=="0"){
			if(repayType == "1400" || repayType == "1410") {
	  			$("#isModifyPlan").css("display","block");
	  		}else {
	  			$("#isModifyPlan").css("display","none");
	  		}
		
			$("#div1").css("display","block");
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","none");
			$("#div5").css("display","none");
			$("#div6").css("display","none");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
			
			nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
			
		}else{
			if(repayType == "1400" || repayType == "1410") {
	  			$("#isModifyPlan").css("display","block");
	  		}else {
	  			$("#isModifyPlan").css("display","none");
	  		}
		
			$("#div1").css("display","none");
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","none");
			$("#div5").css("display","none");
			$("#div6").css("display","none");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
		}
	} 
	
	//调用计量结清试算接口
	function checkJQSS(){
		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"3"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface1500.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            		
            		var yhbj = parseFloat(mydata.items.dftPrn) + parseFloat(mydata.items.curPrn) + parseFloat(mydata.items.aheadPrn);
            		nui.get("tbConLoanChange.yhze").setValue(mydata.items.allPrnItrAmt);
            		nui.get("tbConLoanChange.yhbj").setValue(yhbj);
            		nui.get("tbConLoanChange.yhzclx").setValue(mydata.items.interest);
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.dftItr);
            		nui.get("tbConLoanChange.yhfx").setValue(mydata.items.pnsItr);
            	}
            	//initPage();
			}
    	});
	} 
	
	//调用计量还款试算接口
	function checkHKSS(){
	
		if(nui.get("tbConLoanChange.repayAmt").getValue() == null || nui.get("tbConLoanChange.repayAmt").getValue() == "") {
			return nui.alert("请填写指定还款金额！");
		}
		
		if(nui.get("tbConLoanChange.repayOrder").getValue() == null || nui.get("tbConLoanChange.repayOrder").getValue() == "") {
			return nui.alert("请选择还款顺序！");
		}
	
		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
		tbConLoanChange.repayAmt = nui.get("tbConLoanChange.repayAmt").getValue();
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"1"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface1500.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		/* if("1" == proFlag){
            			nui.get("tbConLoanChange.repayAmt").setEnabled(true);
            		} */
            		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            		
            		var lx = parseFloat(mydata.items.interest) + parseFloat(mydata.items.dftItr) + parseFloat(mydata.items.pnsItr);
            		var yhbj = parseFloat(mydata.items.dftPrn) + parseFloat(mydata.items.curPrn) + parseFloat(mydata.items.aheadPrn);
            		nui.get("tbConLoanChange.yhze").setValue(yhbj + lx);
            		nui.get("tbConLoanChange.yhbj").setValue(yhbj);
            		nui.get("tbConLoanChange.yhzclx").setValue(mydata.items.interest);
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.dftItr);
            		nui.get("tbConLoanChange.yhfx").setValue(mydata.items.pnsItr);
            	}
            	//initPage();
			}
    	});
	} 
	
	//调用计量还本试算接口
	function checkHBSS(){
	
		if(nui.get("tbConLoanChange.repayCapital").getValue() == null || nui.get("tbConLoanChange.repayCapital").getValue() == "") {
			return nui.alert("请填写提前还本金额！");
		}
	
		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
		tbConLoanChange.repayCapital = nui.get("tbConLoanChange.repayCapital").getValue();
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"2"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface1500.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            		/* if("1" == proFlag){
            			nui.get("tbConLoanChange.repayCapital").setEnabled(true);
            		} */
            		
            		var lx = parseFloat(mydata.items.interest) + parseFloat(mydata.items.dftItr) + parseFloat(mydata.items.pnsItr);
            		var yhbj = parseFloat(mydata.items.dftPrn) + parseFloat(mydata.items.curPrn);
            		nui.get("tbConLoanChange.yhze").setValue(yhbj + lx + parseFloat(nui.get("tbConLoanChange.repayCapital").getValue()));
            		
            		if(eval(nui.get("tbConLoanChange.yhze").getValue()) > eval(mydata.items.allPrnItrAmt)) {
            			nui.get("tbConLoanChange.yhze").setValue(mydata.items.allPrnItrAmt);
            		}
            		
            		nui.get("tbConLoanChange.yhbj").setValue(yhbj);
            		nui.get("tbConLoanChange.yhzclx").setValue(mydata.items.interest);
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.dftItr);
            		nui.get("tbConLoanChange.yhfx").setValue(mydata.items.pnsItr);
            	}
            	//initPage();
			}
    	});
	} 
	
	//调用计量结清当前期试算接口
	function checkJQDQQSS(){
		tbConLoanChange.repayCapital = 0;
		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"4"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface1500.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            		
            		var lx = parseFloat(mydata.items.interest) + parseFloat(mydata.items.dftItr) + parseFloat(mydata.items.pnsItr);
            		var yhbj = parseFloat(mydata.items.dftPrn) + parseFloat(mydata.items.curPrn)
            		nui.get("tbConLoanChange.yhze").setValue(yhbj + lx);
            		nui.get("tbConLoanChange.yhbj").setValue(yhbj);
            		nui.get("tbConLoanChange.yhzclx").setValue(mydata.items.interest);
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.dftItr);
            		nui.get("tbConLoanChange.yhfx").setValue(mydata.items.pnsItr);
            	}
            	//initPage();
			}
    	});
	} 
	
	function onselectSettle2(e){
		var repayType= nui.get("tbConLoanChange.repayType").getValue();//还款类型
		var summaryStatusCd = nui.get("tbLoanSummary.summaryStatusCd").getValue();
		if(repayType=="01"){//指定还款金额
		
			//nui.get("tbConLoanChange.repayOrder").setValue("00");
			/* if("1" == proFlag){
				nui.get("tbConLoanChange.repayOrder").setEnabled(true);
			}else {
				nui.get("tbConLoanChange.repayOrder").setEnabled(false);
			} */
			nui.get("tbConLoanChange.repayOrder").setEnabled(true);
		
			$("#div2").css("display","block");
			$("#div3").css("display","none");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","none");
			$("#div02").css("display","block");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
			
			/* if("1" == proFlag){
				nui.get("tbConLoanChange.repayAmt").setEnabled(true);
			} */
			nui.get("tbConLoanChange.repayAmt").setEnabled(true);
    		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
    		
			nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
			
		}else if(repayType=="02"){//指定还本金额
			
			nui.get("tbConLoanChange.repayOrder").setValue("00");
			nui.get("tbConLoanChange.repayOrder").setEnabled(false);
			
			/* if(summaryStatusCd == "03") {
				return nui.alert("逾期借据不能提前还本！");
			} */
		
			$("#div2").css("display","none");
			$("#div3").css("display","block");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","block");
			$("#div04").css("display","none");
			
			nui.get("tbConLoanChange.repayAmt").setEnabled(false);
			/* if("1" == proFlag){
				nui.get("tbConLoanChange.repayCapital").setEnabled(true);
			} */
    		nui.get("tbConLoanChange.repayCapital").setEnabled(true);
    		
			nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");

		}else if(repayType=="03"){//结清当前期
		
			nui.get("tbConLoanChange.repayOrder").setValue("00");
			nui.get("tbConLoanChange.repayOrder").setEnabled(false);
		
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","block");
			
			nui.get("tbConLoanChange.repayAmt").setEnabled(false);
    		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
    		
			nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");

		}else{
		
			nui.get("tbConLoanChange.repayOrder").setValue("00");
			nui.get("tbConLoanChange.repayOrder").setEnabled(false);
		
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","none");
			$("#div5").css("display","none");
			$("#div6").css("display","none");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
		}
	}  
	
	function onselectModify(e){
		var isModifyPlan= nui.get("tbConLoanChange.isModifyPlan").getValue();//是否修改还本计划表
		if(isSmall=="1"){
			if(isModifyPlan=="1"){
				//return nui.alert("保存后请到还款计划表页面填写还款计划");
			}
		}else {
			if(isModifyPlan=="1"){
				$("#table3").css("display","block");
				$("#table4").css("display","block");
			}else{
				$("#table3").css("display","none");
				$("#table4").css("display","none");
			}
		}
	}
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"changeId":changeId,"reportName":'/aft/loanChange_earlyrepay.docx'}});
		$.ajax({
            url: "com.bos.aft.conLoanChange.printLoanChange.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.swfPath){
            		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
						title: "变更信息预览", 
						width: 1024,
		            	height: 768,
		            	state:"max",
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                git.unmask();
            }
       	});	
	}
	function getDictData(dictId,type,str){
		var dictData = nui.getDictData(dictId);//获取业务字典的数据
		var arr = nui.encode(dictData).split("},");//业务字典数据字符串化，方便处理
		var strArr = new Array();
		//将字符串存入数组
		if(str.indexOf(",") != -1){
			strArr = str.split(",");
		}else{
			strArr.push(str);
		}
		var dictStr = "";//拼接业务字典字符串
		if(type == "str"){//如果是指定字符串过滤
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var flag = arr[i].indexOf('"dictID":"'+strArr[n]+'"')!="-1";//如果包含指定的字符串
					if(flag){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "sub"){//如果是只获取指定字符串子集
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var s = strArr[n];
					//var flag = arr[i].indexOf('"dictID":"'+s)!="-1";//必须为指定字符串及其子项
					//var flag1 = arr[i].indexOf('"dictID":"'+s+'"')=="-1";//不能为父项
					var flag2 = arr[i].indexOf('"parentid":"'+s+'"')!="-1";//必须为子项（不包含子项的子项）
					if(flag2){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "top"){//如果是只获取最顶级业务字典
			for(var i = 0;i<arr.length;i++){
				var flag = arr[i].indexOf('"parentid":"null"')!="-1";//必须为顶级业务字典
				if(flag){
					dictStr = contactStr(i,dictStr,arr);
				}
			}
		}
		//如果最后一个字典项不符合条件，则增加结束标识符号“}]”
		if(dictStr.charAt(dictStr.length-1) != "]"){
			dictStr = dictStr + "}]";
		}
		var dict = nui.decode(dictStr);
		return dict;
	}
	
	//根据索引值，字符串和数组值拼接(用于过滤业务字典-getDictData)
	function contactStr(index,str,arr){
		if(index == 0){
			str = str + arr[index];
		}else if(index != (arr.length)){
			if(str == ""){
				str = "[" + arr[index];
			}else{
				str = str + "}," + arr[index];
			}
		}
		return str;
	}
	nui.get("tbConLoanChange.repayType").setData(getDictData("XD_HKLX0001","str","02,03"));
</script>
</body>
</html>