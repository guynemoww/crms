<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>还款方式变更申请信息</title>
<%-- <%@include file="/common/nui/common.jsp"%> --%>
<script type="text/javascript" src="<%=contextPath %>/biz/biz_js/biz.js"></script>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbLoanInfo.loanId" class="nui-hidden nui-form-input" name ="tbLoanInfo.loanId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbBizAmountDetailApprove.cycleIndCon" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.cycleIndCon"/>
	<input id="tbLoanInfo.productType" class="nui-hidden nui-form-input" name ="tbLoanInfo.productType"/>
	<input id="tbConLoanChange.isSmall" class="nui-hidden nui-form-input" name ="tbConLoanChange.isSmall"/>
	
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
			
			<!-- <label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/> -->
				   
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
				   
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input"  vtype="maxLength:999" /> 
		</div>

	</fieldset>

	<fieldset>
		<legend>
	    	<span>变更前</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
			
			<label class="nui-form-label">还款方式：</label>
			<input id="tbConLoanChange.oldRepayWay" name="tbConLoanChange.oldRepayWay" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1162"/>
				   
			<label class="nui-form-label">结息周期：</label>
			<input id="tbConLoanChange.oldInterestCollectType" name="tbConLoanChange.oldInterestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table31">
			
			<label class="nui-form-label">还款方式：</label>
			<input id="tbConLoanChange.newRepayWay" name="tbConLoanChange.newRepayWay" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1162" onvaluechanged="onselectWay" />
			
	    </div>
	    
	    <div class="nui-dynpanel" columns="4" id="table32">
			
			<label class="nui-form-label">首次还本期次：</label>
			<input id="tbConLoanChange.firstPeriods" name="tbConLoanChange.firstPeriods" class="nui-textbox nui-form-input" vtype="int;maxLength:3;range:0,999" required="true" />

	    </div>
	    
	    <div class="nui-dynpanel" columns="4" id="table33">
			
			<label class="nui-form-label">结息周期：</label>
			<input name="tbConLoanChange.newInterestCollectType" class="nui-dictcombobox nui-form-input" data="data" valueField="dictID" 
				dictTypeId="XD_SXCD1018" id="tbConLoanChange.newInterestCollectType" required="true" onvaluechanged="onselectCollect"/>
			
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
	var cycleIndCon;
	var isSmall;
	var busDate;
	nui.get("tbConLoanChange.newRepayWay").setData(getDictData('XD_SXCD1162','str','0100,0200,1100,1400'));
	nui.get("tbConLoanChange.newInterestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
	$("#table32").css("display","none");
	
	$("#table3").css("display","none");
	$("#table4").css("display","none");
	
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
            	busDate = mydata.busDate;
            	var o = nui.decode(mydata);
            	cycleIndCon= o.tbConContractInfo.cycleIndCon;
            	form.setData(o);
            	isSmall = o.tbConLoanChange.isSmall;
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	if(null != cycleIndCon && "" != cycleIndCon) {
            		if("1" == cycleIndCon) {
            			nui.get("tbConLoanChange.newInterestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
            		}else {
            			nui.get("tbConLoanChange.newInterestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
            		}
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
	var productType=nui.get("tbConContractInfo.productType").getValue();
 		if(productType=="02005001" ||productType=="02005003" ||productType=="02005010" || productType.substr(0,5)=="02002" ){
 				nui.get("tbConLoanChange.newRepayWay").setData(getDictData('XD_SXCD1162','str','0100,0200'));
 		
 		         nui.get("tbConLoanChange.newInterestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
		
		}
		var partyTypeCd= nui.get("tbCsmParty.partyTypeCd").getValue();
   		if(partyTypeCd=="01"){
  			$("#type01").css("display","block");
  			$("#type02").css("display","none");
  		}else if(partyTypeCd=="02"){
   			if(cycleIndCon=="1"){
  			nui.get("tbConLoanChange.newRepayWay").setData(getDictData('XD_SXCD1162','str','0100,0200,1100'));
  			
  			}
  			$("#type01").css("display","none");
  			$("#type02").css("display","block");
  		}else {
  			$("#type01").css("display","none");
  			$("#type02").css("display","none");
  		}
  		
  		var o = form.getData();
  		//alert(o.tbLoanInfo.loanId);
  		if(isSmall == "0") {
	  		var json = nui.decode({"loanId":o.tbLoanInfo.loanId,"changeId":changeId});
			var grid1 = nui.get("grid1");
	    	var grid2 = nui.get("grid2");
	    	grid1.load(json);
			grid2.load(json);
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
		
		if(o.tbConLoanChange.oldRepayWay == o.tbConLoanChange.newRepayWay 
			&& o.tbConLoanChange.oldInterestCollectType == o.tbConLoanChange.newInterestCollectType) {//还款方式与结算周期至少修改一项
			nui.alert("还款方式与结算周期至少修改一项！");
		    return;
		}
		
		if(o.tbConLoanChange.newRepayWay=="0300" || o.tbConLoanChange.newRepayWay=="0400") {//阶段性等额本金、阶段性等额本息显示首次还本期次
			if(o.tbConLoanChange.firstPeriods < 2) {
				nui.alert("首次还款期次应大于等于2");
		        return;
			}
		}
		
		if(isSmall == "0") {
			if(nui.get("tbConLoanChange.newRepayWay").getValue() == "1400" || nui.get("tbConLoanChange.newRepayWay").getValue() == "1410") {
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
		    	//alert("jjye--->" + parseFloat(o.tbLoanSummary.jjye).toFixed(2) + "amt--->" + parseFloat(amt).toFixed(2));
		    	if (parseFloat(o.tbLoanSummary.jjye).toFixed(2)!=parseFloat(amt).toFixed(2)){
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
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	function onselectWay(e){
		if(e.value==""){
		    return;
		}
		var oldRepayWay= nui.get("tbConLoanChange.oldRepayWay").getValue();
		var newRepayWay= nui.get("tbConLoanChange.newRepayWay").getValue();
		var productType= nui.get("tbLoanInfo.productType").getValue();
		var cycleIndCon= nui.get("tbBizAmountDetailApprove.cycleIndCon").getValue();
		var isSmall= nui.get("tbConLoanChange.isSmall").getValue();
		/* if(oldRepayWay==newRepayWay) {
			nui.get("tbConLoanChange.newRepayWay").setValue('');
			return nui.alert("变更后的还款方式不能与原还款方式相同");
		} */
		/* if(newRepayWay=="2100") {
			nui.get("tbConLoanChange.newRepayWay").setValue('');
			return nui.alert("不能调整为预收息");
		} */
		/* if(newRepayWay=="1200" || newRepayWay=="1500" || newRepayWay=="1700") {
			nui.get("tbConLoanChange.newRepayWay").setValue('');
			return nui.alert("不能调整为到期一次性还本付息或等本等息，利随本清");
		} */
		
		if(cycleIndCon != null && cycleIndCon == "1" && productType.substr(0,5) == "01001") {
			if(newRepayWay!="1100" && newRepayWay!="1200" && newRepayWay!="1300") {
				nui.get("tbConLoanChange.newRepayWay").setValue('');
				//return nui.alert("流贷类（循环）可以选择的还款方式为：按周期还息到期一次性还本、按周期还息任意还本、到期一次性还本付息");
				return nui.alert("流贷类（循环）可以选择的还款方式为：按周期还息到期一次性还本");
			}
		}
		
		if(oldRepayWay=="0100" || oldRepayWay=="0200" || oldRepayWay=="0300" || oldRepayWay=="0400"){
			if(newRepayWay=="1100" || newRepayWay=="1200" || newRepayWay=="1300" || newRepayWay=="1400" || newRepayWay=="1410" || newRepayWay=="1500") {
				nui.get("tbConLoanChange.newRepayWay").setValue('');
				//return nui.alert("还款方式只能调整为等额本金、等额本息、阶段性等额本金、阶段性等额本息");
				return nui.alert("还款方式只能调整为等额本金、等额本息");
			}
		}else if(oldRepayWay=="1100" || oldRepayWay=="1200" || oldRepayWay=="1300" || oldRepayWay=="1400" || oldRepayWay=="1410" || oldRepayWay=="1500"){
			if(newRepayWay=="0100" || newRepayWay=="0200" || newRepayWay=="0300" || newRepayWay=="0400") {
				nui.get("tbConLoanChange.newRepayWay").setValue('');
				//return nui.alert("还款方式只能调整为到期一次性还本付息、按周期还息到期一次还本、按周期还息任意还本、按周期还息按还本计划表还本、按还本计划表还息按还本计划表还本");
				return nui.alert("还款方式只能调整为按周期还息到期一次还本、按周期还息按还本计划表还本");
			}
		}
		
		if(newRepayWay=="0300" || newRepayWay=="0400") {//阶段性等额本金、阶段性等额本息显示首次还本期次
			$("#table32").css("display","block");
		}else {
			nui.get("tbConLoanChange.firstPeriods").setValue('');
			$("#table32").css("display","none");
		}
		
		if(isSmall == "0") {
			if(newRepayWay == "1400" || newRepayWay == "1410") {
	  			$("#table3").css("display","block");
				$("#table4").css("display","block");
	  		}
		}
		
	}
	
	function onselectCollect(e){
		if(e.value==""){
		    return;
		}
		var newInterestCollectType= nui.get("tbConLoanChange.newInterestCollectType").getValue();
		var productType= nui.get("tbLoanInfo.productType").getValue();
		var cycleIndCon= nui.get("tbBizAmountDetailApprove.cycleIndCon").getValue();
		
		if(productType.substr(0,5) == "01001") {
			if(cycleIndCon != null && cycleIndCon == "1") {
				if(newInterestCollectType!="1" && newInterestCollectType!="2" && newInterestCollectType!="6") {
					nui.get("tbConLoanChange.newInterestCollectType").setValue('');
					//return nui.alert("流贷类（循环）可以选择的结息周期：按月、按季、在借款到期日付清全部利息");
					return nui.alert("流贷类（循环）可以选择的结息周期：按月、按季");
				}
			}else {
				if(newInterestCollectType!="1" && newInterestCollectType!="2" && newInterestCollectType!="5" && newInterestCollectType!="6") {
					nui.get("tbConLoanChange.newInterestCollectType").setValue('');
					//return nui.alert("流贷类（单次）可以选择的结息周期为按月、按季、按还本计划表付清本期利息、在借款到期日付清全部利息");
					return nui.alert("流贷类（单次）可以选择的结息周期为按月、按季");
				}
			}
			
		} 
		
	}
</script>
</body>
</html>