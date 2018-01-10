<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>还本计划表调整申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbConLoanChange.contractId" class="nui-hidden nui-form-input" name ="tbConLoanChange.contractId"/>
	<input id="tbLoanInfo.loanId" class="nui-hidden nui-form-input" name ="tbLoanInfo.loanId"/>
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
			<label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
			
			<label class="nui-form-label">还款方式：</label>
			<input id="tbLoanInfo.repayType" name="tbLoanInfo.repayType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1162"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input" vtype="maxLength:999"/> 
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
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var isSmall = "";
	var busDate;
	$("#table3").css("display","none");
	$("#table4").css("display","none");
	
	//初始化页面
	initPage();
	function initPage(){
		
		var json = nui.encode({"changeId":changeId});
		var con;
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	busDate = mydata.busDate;
            	var o = nui.decode(mydata);
            	form.setData(o);
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	isSmall = o.tbConLoanChange.isSmall;
            	if(o.tbConLoanChange.isSmall == "0") {
            		$("#table3").css("display","block");
					$("#table4").css("display","block");
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
	
	
	//初始化还款计划查询
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
  		//alert(o.tbConLoanChange.isSmall);
  		if(isSmall == "0") {
  			var json = nui.decode({"loanId":o.tbLoanInfo.loanId,"changeId":changeId});
  			//var json3 = nui.decode({"dueNum":nui.get("tbLoanSummary.summaryNum").getValue()});
			var grid1 = nui.get("grid1");
	    	var grid2 = nui.get("grid2");
	    //	var grid3 = nui.get("grid3");
	    	grid1.load(json);
			grid2.load(json);
		//	grid3.load(json3);
			 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
			if("1" != proFlag){
				nui.get("grid2add").hide();
				nui.get("grid2del").hide();
				form.setEnabled(false);
			} 
  		}
  		
	}
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"newPeriodsNum":++count};
        //nui.get(gr).addRow(row,0);
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
		o.tbConLoanChange.changeId=changeId;
		o.changeId = changeId;
		o.loanId = o.tbLoanInfo.loanId;

		if(isSmall == "0") {
		
			var repayPlans = nui.get("grid1").getChanges();/* 还款 */
			var repayPlans2 = nui.get("grid2").getChanges();/* 还款 */
			o.repayPlans = repayPlans;
			o.repayPlans2 = repayPlans2;
		
			/* if (repayPlans2==null || repayPlans2 == ""){
	        	nui.alert("请填写还款计划");
	        	return;
	        } */
	        
	        var size1 = nui.get("grid2").getData().length;
	        //alert(size1);
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
	    	
	    	//alert(parseFloat(amt).toFixed(2) + "--" +parseFloat(o.tbLoanSummary.jjye).toFixed(2));
	    	if (parseFloat(o.tbLoanSummary.jjye).toFixed(2)!=parseFloat(amt).toFixed(2)){
	        	nui.alert("累计金额应等于借据余额");
	        	return;
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
	
	
</script>
</body>
</html>