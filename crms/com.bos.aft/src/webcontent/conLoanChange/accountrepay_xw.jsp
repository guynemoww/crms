<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-09-23
  - Description:
-->
<head>
<title>小贷还款计划表</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">

	<div class="nui-dynpanel" columns="1" id="table5">
		<%@include file="/aft/conLoanChange/include_old_repayplan_xw.jsp"%>
	</div> 
	
	<div class="nui-dynpanel" columns="1" id="table6">
		<%@include file="/aft/conLoanChange/include_new_repayplan_xw.jsp"%>
	</div>  
	
	<!-- <div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div>  -->

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	<%-- var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识 --%>
	
	var loanId = "";
	var amountDetailId = "";
	var applyId = "";
	var bj = 0;
	var hkqc;
	var jxzq;
	var yearrate = 0;
	var dkqq;
	var endDate;
	var createTime;
	var repayType;
	
	var loanChangeType;
	var isModifyPlan;
	var newRepayWay;
	
	//$("#cs").css("display","none");
	//$("#cs2").css("display","none");
	$("#table5").css("display","none");
	$("#table6").css("display","none");
	
	init();
	function init(){
		//alert(changeId);
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.findRepayXWInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            
            	if(mydata.repayType == "1400" || mydata.repayType == "1410") {
            		$("#table5").css("display","block");
            	}
            	
            	if(mydata.loanChangeType == "10") {
            		$("#table6").css("display","block");
            	}else if(mydata.loanChangeType == "15") {
            		if(mydata.isModifyPlan == "1") {
            			$("#table6").css("display","block");
            		}
            	}else if(mydata.loanChangeType == "02") {
            		if(mydata.newRepayWay == "1400" || mydata.newRepayWay == "1410") {
            			$("#table6").css("display","block");
            		}
            	}
            
            	var o = nui.decode(mydata);
            	form.setData(o);
            	amountDetailId = mydata.amountDetailId;
            	loanId = mydata.loanId;
            	applyId = mydata.applyId;
            	endDate = mydata.endDate;
            	createTime = mydata.createTime;
            	repayType = mydata.repayType;
            	loanChangeType = mydata.loanChangeType;
            	isModifyPlan = mydata.isModifyPlan;
            	newRepayWay = mydata.newRepayWay;
            	//alert(mydata.amountDetailId + "---" + mydata.loanId + "---" + mydata.applyId +"===="+ mydata.endDate + "---" + mydata.createTime + "---" + mydata.repayType);
            	initPage();
			}
        });
        
        /* if("1" != proFlag){
        	nui.get("hkjhdiv").hide();
			form.setEnabled(false);
			var grid2 = nui.get("grid2");
			grid2.setEnabled(false);
		} */
		
	}
	
	//initPage();
	function initPage(){
		var json = nui.encode({"applyId":applyId,"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.initHkjhPageSmall.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	amountDetailId = mydata.amountDetailId;
            	bj = mydata.bj;
				hkqc = mydata.hkqc;
				jxzq = mydata.jxzq;
				yearrate = mydata.yearrate;
				dkqq = mydata.dkqq;
				//alert(amountDetailId + "---" + bj + "---" + hkqc +"===="+ jxzq + "---" + yearrate + "---" + dkqq);
				initDataTable();
			}
        });
		
	}
	
	function initDataTable(){
    	/* var bj = nui.get("bj").getValue();
		var hkqc = nui.get("hkqc").getValue();
		var yearrate = nui.get("yearrate").getValue();
		var jxzq = nui.get("jxzq").getValue();
		var dkqq = nui.get("dkqq").getValue();  */
		//alert(amountDetailId + "---" + bj + "---" + hkqc +"-------------"+ jxzq + "---" + yearrate + "---" + dkqq);
		var json = nui.encode({"bj":bj,"hkqc":hkqc,"yearrate":yearrate,"jxzq":jxzq,"dkqq":dkqq,"amountDetailId":amountDetailId,"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.saveHkjhListSmall.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
           	 	if(mydata.resultMap.resultMsg !='1'){
            		nui.alert(mydata.resultMap.resultMsg);
            	}else{
            		nui.get("totalAmt2").setValue(mydata.resultMap.totalAmt);
	            	nui.get("totalLx2").setValue(mydata.resultMap.totalLx);
	            	nui.get("totalTerm2").setValue(mydata.resultMap.totalTerm);
	            	nui.get("bj2").setValue(bj);
	            	nui.get("dkqq2").setValue(dkqq);
            	}
	            //loadformhkjh();
	            queryTotal();
	            query1();
			}
        });
	}
	
	function loadformhkjh(){
		var json = nui.decode({"amountDetailId":amountDetailId});
		var grid = nui.get("grid1");
		grid.load(json);
	}
	
	function query1(){
  		var o = form.getData();
  		var json = nui.decode({"amountDetailId":amountDetailId,"loanId":loanId,"changeId":changeId});
    	var grid2 = nui.get("grid2");
		grid2.load(json);
		var grid = nui.get("grid1");
		grid.load(json);
		
		//nui.get("grid2add").hide();
		//nui.get("grid2del").hide();
		//form.setEnabled(false);
  		
	}
	
	function queryTotal(){
		var json = nui.encode({"amountDetailId":amountDetailId,"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.queryTotal.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	
            	if(repayType == "1400" || repayType == "1410") {
            		nui.get("totalAmt1").setValue(mydata.amt);
	            	nui.get("totalLx1").setValue(mydata.lx);
	            	nui.get("totalTerm1").setValue(mydata.qc);
	            	nui.get("bj1").setValue(mydata.bj);
	            	nui.get("dkqq1").setValue(createTime);  
            	}
            	
            	nui.get("totalAmt2").setValue(mydata.newAmt);
            	nui.get("totalLx2").setValue(mydata.newLx);
            	nui.get("totalTerm2").setValue(mydata.newQc);
            	nui.get("bj2").setValue(mydata.newBj); 
            	nui.get("dkqq2").setValue(dkqq);  
            	
            	//loadformhkjh();
			}
        });
		
	}
	
	function save(){
        
		var o = form.getData();
		o.changeId = changeId;
		o.loanId = loanId;
		o.endDate = endDate;
		
		var repayPlans2 = nui.get("grid2").getChanges();/* 还款 */
		o.repayPlans2 = repayPlans2;
		
		var amt = 0;
		var bj = 0;
		for(var i=0; i<nui.get("grid2").getData().length;i++){
			
			if(nui.get("grid2").getData()[i].NEW_REPAY_DATE==null || nui.get("grid2").getData()[i].NEW_REPAY_DATE==''){
				nui.alert("日期不能为空");
		 		return;
 			}
 			if(nui.get("grid2").getData()[i].NEW_REPAY_AMT==null || nui.get("grid2").getData()[i].NEW_REPAY_AMT==''){
				nui.alert("金额不能为空");
		 		return;
 			}

 			amt += parseFloat(nui.get("grid2").getData()[i].NEW_REPAY_AMT);
 			bj += parseFloat(nui.get("grid2").getData()[i].NEW_BJ);
    	}
    	amt = amt.toFixed(2);
    	bj = bj.toFixed(2);
    	
    	o.repayPlans2 = nui.get("grid2").getData();
    	
    	//alert(amt +"---" +nui.get("totalAmt2").getValue());
    	if (parseFloat(nui.get("totalAmt2").getValue()).toFixed(2)!=amt){
        	nui.alert("累计金额应等于总额");
        	return;
        }
        
        //alert(bj +"---" +nui.get("bj2").getValue());
        if (parseFloat(nui.get("bj2").getValue()).toFixed(2)!=bj){
        	nui.alert("累计金额本金应等于本金总额");
        	return;
        }
		
		//nui.get("con_contract_info_save").setEnabled(false);
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveRepayplanXW.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.flag != null && text.flag != "") {
        		if(text.flag == "1") {
        			nui.alert("还款日期不能晚于贷款到期日");
        		}
        		if(text.flag == "2") {
        			nui.alert("还款日期应晚于营业日期");
        		}
        	}else {
	        	if(text.msg){
	        		nui.alert(text.msg);
	        	}
	        	initPage();
	        	
        	}
        	//nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	function dataChange(a){
	
		/* if("1" != proFlag){
            return;
		} */
	
		nui.get("grid2").commitEdit();
		var hkjh = nui.get("grid2").getSelected();
		
		if(hkjh.NEW_BJ==null ||hkjh.NEW_BJ==''||hkjh.NEW_BJ<0){
			nui.alert("值不合法！");
			return;
		}
		if(hkjh.NEW_REPAY_AMT==null ||hkjh.NEW_REPAY_AMT==''||hkjh.NEW_REPAY_AMT<0){
			nui.alert("值不合法！");
			return;
		}
		
		if(hkjh.NEW_REPAY_DATE==null ||hkjh.NEW_REPAY_DATE==''){
			nui.alert("值不合法！");
			return;
		}
		var o = form.getData();
		
		o.hkjh = hkjh;
		
		o.hkjh.newPeriodsNum = hkjh.NEW_PERIODS_NUM;
		
		o.hkjh.repayplanChangeId = hkjh.REPAYPLAN_CHANGE_ID;
		o.hkjh.newRepayDate = hkjh.NEW_REPAY_DATE;
		o.hkjh.newDays = hkjh.NEW_DAYS;
		o.hkjh.newRepayAmt = hkjh.NEW_REPAY_AMT;
		o.hkjh.newBj = hkjh.NEW_BJ;
		o.hkjh.newLx = hkjh.NEW_LX;
		o.hkjh.newSybj = hkjh.NEW_SYBJ;
		o.hkjh.xgbz1 = hkjh.XGBZ1;
		o.hkjh.xgbz2 = hkjh.XGBZ2;
		o.hkjh.xgbz3 = hkjh.XGBZ3;
		o.hkjh.newOrOld = hkjh.NEW_OR_OLD;
		o.hkjh.changeId = hkjh.CHANGE_ID;
		
		o.a = a;
		
		var bj2 = nui.get("bj2").getValue();
		var hkqc2 = hkqc;
		var yearrate2 = yearrate;
		var jxzq2 = jxzq;
		var dkqq2 = nui.get("dkqq2").getValue();
		
		o.bj = bj2;
		o.hkqc = hkqc2;
		o.yearrate = yearrate2;
		o.jxzq = jxzq2;
		o.dkqq = dkqq2;
		//alert("----" + o.hkjh +"----" + o.a+"----" + o.bj+"----" + o.yearrate+"----" + o.jxzq+"----" + o.dkqq+"----" + o.hkqc);
		var json = nui.encode(o);
		$.ajax({
            url: "com.bos.aft.conLoanChange.updateHkjhFlgAft.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	query1();
            	if(mydata.resultMap.resultMsg !='1'){
            		nui.alert(mydata.resultMap.resultMsg);
            	}else{
            		nui.get("totalAmt2").setValue(mydata.resultMap.totalAmt);
	            	nui.get("totalLx2").setValue(mydata.resultMap.totalLx);
	            	nui.get("totalTerm2").setValue(mydata.resultMap.totalTerm);
            	}
            	
			}
        });
	}
	
	//重新开始
	function refresh(){
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.delHkjhListAft.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	initPage();
			}
        });
	}
	
	//动态列表删除操作
    function remove(gr) {
    	//alert(jxzq);
        var row = nui.get(gr).getSelected();
        
        if (row) {
        	//alert(jxzq);
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	//删除数据库数据
            	if(row.REPAYPLAN_CHANGE_ID){
            		
            		var bj2 = nui.get("bj2").getValue();
					var hkqc2 = hkqc;
					var yearrate2 = yearrate;
					//alert(jxzq);
					var jxzq2 = jxzq;
					var dkqq2 = nui.get("dkqq2").getValue();
					//alert("====");
            		var json = nui.encode({"delqc":row.NEW_PERIODS_NUM,"bj":bj2,"hkqc":hkqc2,"yearrate":yearrate2,"jxzq":jxzq2,"dkqq":dkqq2,"uuid":row.REPAYPLAN_CHANGE_ID,"changeId":row.CHANGE_ID,"amountDetailId":amountDetailId});
	            	$.ajax({
			            url: "com.bos.aft.conLoanChange.delSingleHkjhAft.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
			            	query1();
			            	nui.get("totalAmt2").setValue(mydata.resultMap.totalAmt);
			            	nui.get("totalLx2").setValue(mydata.resultMap.totalLx);
			            	nui.get("totalTerm2").setValue(mydata.resultMap.totalTerm);
						}
	        		});
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
    function firstRepayDayChange(){
    
    	/* if("1" != proFlag){
            return;
		} */
    
    	var bj2 = nui.get("bj2").getValue();
		var hkqc2 = hkqc;
		var yearrate2 = yearrate;
		var jxzq2 = jxzq;
		var dkqq2 = nui.get("dkqq2").getValue();
		var schkq = nui.get("schkq").getValue();
		//alert("amountDetailId--->" + amountDetailId + "schkq--->" + schkq + "bj2--->" + bj2 + "hkqc2--->" + hkqc2 + "yearrate2--->" + yearrate2 + "jxzq2--->" + jxzq2 + "dkqq2--->" + dkqq2 + "changeId--->" + changeId);
		var json = nui.encode({"amountDetailId":amountDetailId,"schkq":schkq,"bj":bj2,"hkqc":hkqc2,"yearrate":yearrate2,"jxzq":jxzq2,"dkqq":dkqq2,"changeId":changeId});
    	$.ajax({
            url: "com.bos.aft.conLoanChange.changeFirstRepayDateAft.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	//loadformhkjh();
            	query1();
            	if(mydata.resultMap.resultMsg =='1'){
	        		nui.get("totalAmt2").setValue(mydata.resultMap.totalAmt);
	            	nui.get("totalLx2").setValue(mydata.resultMap.totalLx);
	            	nui.get("totalTerm2").setValue(mydata.resultMap.totalTerm);
	        	}else{
	        		nui.alert(mydata.resultMap.resultMsg);
	        	}
			}
		});
    }
</script>
</body>
</html>