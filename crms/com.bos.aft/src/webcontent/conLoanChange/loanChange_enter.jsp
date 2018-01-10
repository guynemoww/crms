<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-26
  - Description:
-->
<head>
<title>贷后变更</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: auto;">
		<div title="贷后变更">
	<div id="form" style="width:99.5%"border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px;"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
		
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
			<!-- <input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" /> -->
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<label>合同编号：</label>
			<input name="map.contractNum" id="map.contractNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<label>借据编号：</label>
			<input name="map.summaryNum" id="map.summaryNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<!-- <a class="nui-button"onclick="query">查询</a> -->
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:1              5px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
		
		<div class="nui-toolbar" style="margin-top:7px;width:99.5%">
			<label class="nui-form-label">贷后变更类型：</label>
			<input name="loanChangeType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHBG0001" id="loanChangeType" 
					style="margin-right:5px;width:13%"/>
			<a class="nui-button" id="btnCreate" onclick="add" >创建</a>
			<a class="nui-button" id="btnBgCx" onclick="gjBgCx" >国结变更撤销</a>
		</div>
		
		 <div id="grid" class="nui-datagrid" style="width: 99.5%; height: auto;" sortMode="client" url="com.bos.aft.conLoanChange.findLoanList.biz.ext" dataField="items" allowResize="true" showReloadButton="false" allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" headerAlign="center" align="center" >客户名称</div>
		        <div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">业务品种</div>
		        <div field="CONTRACT_NUM" headerAlign="center" align="center" >合同编号</div>
		        <div field="SUMMARY_NUM" headerAlign="center" align="center" >借据编号</div>
				<div field="SUMMARY_AMT" headerAlign="center" align="right" dataType="currency">借据金额</div>
				<div field="JJYE" headerAlign="center" align="right" dataType="currency">借据余额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据起期</div>
				<div field="END_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据止期</div>
		    </div>
		</div>
		
	</div>
</div>
</div>

<script type="text/javascript">
	nui.parse();
	var loanChangeType = nui.get("loanChangeType");
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //借据列表
	
	var busDate;
	
	query();
	function query(){
	
		var json = nui.encode({});
		$.ajax({
            url: "com.bos.aft.conLoanChange.getBusDate.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
				//alert(mydata.busDate);
            	//var o = nui.decode(mydata);
            	//form.setData(o);
            	busDate = mydata.busDate;
			}
        });
		
		var o = form.getData();
		grid.load(o);
		//grid.load({"partyName":o.map.partyName,"contractNum":o.map.contractNum,"summaryNum":o.map.summaryNum});
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			//nui.alert(nui.encode(e.data[i].PARTY_NAME));
       			if(null !=e.data[i].PARTY_NAME && ''!=e.data[i].PARTY_NAME){
	       			//客户链接
	       			e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
	       				
	       			//合同链接
	       			e.data[i]['CONTRACT_NUM']='<a href="#" onclick="goToLoan();return false;" value="'
	       				+ e.data[i].contractDetailId
	       				+ '">'+e.data[i]['CONTRACT_NUM']+'</a>';
	       				
	       			//借据链接
	       			e.data[i]['SUMMARY_NUM']='<a href="#" onclick="goToLoanSum();return false;" value="'
	       				+ e.data[i].contractDetailId
	       				+ '">'+e.data[i]['SUMMARY_NUM']+'</a>';
	       				
       			}else{
       			
       				e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['ENGLISH_NAME']+'</a>';
       			}
       		}
       });
	}
	
	function goToLoan(e){
		var row=grid.getSelected();
		//alert(row.CONTRACT_ID);
		nui.open({
            url:nui.context +"/crt/con_info/con_tree.jsp?contractId="+row.CONTRACT_ID+"&contractType=02&proFlag=-1",
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
	}
	
	function goToLoanSum(e){
		var row=grid.getSelected();
		//alert(row.CONTRACT_ID);
		nui.open({
            url:nui.context +"/pay/payout_info/pay_tree.jsp?loanId="+row.LOAN_ID+"&processInstId=0&proFlag=-1",
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
	}
	
	function clickCust(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
		var corpCustomerTypeCd = ps[2];
		var partyTypeCd = ps[3];
		//alert(ps[0]+"--"+ps[1]+"--"+ps[2]+"--"+ps[3]);
        if(partyTypeCd=="01") {
        	/* var infourl = nui.context + "/csm/workdesk/csm_corp_tab.jsp?corpid="
            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd; */
	        var infourl = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd+"&qote=1";
        }else {
        	/* var infourl = nui.context + "/csm/workdesk/csm_corp_tab_natural.jsp?corpid="
            + partyId+"&partyNum="+partyNum; */
	        var infourl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&qote=1";
        }
        
            
            
             nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
            
	}
	function gjBgCx(){
		nui.open({//传值到tree页面
		            url: nui.context+"/aft/conLoanChange/loanChange_GJ_changing.jsp",
		            showMaxButton: false,
		            title: "贸易融资变更中列表",
		            width: 1024,
		            height: 768,
		            state:"max",
		            ondestroy: function (action) {
		            	
		            }
	        	});
	}
	
	
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		var repayType =  row.REPAY_TYPE;
		 //获取当前系统时间 
		var thisDate="";
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
		var jtime = row.BEGIN_DATE;
		var arrs = jtime.split("-");
		var begintime = new Date(arrs[0],arrs[1],arrs[2].substring(0,2));
		var begintims = begintime.getTime();
		//当前系统时间
		var arrn = thisDate.split("-");
		var nowtime = new Date(arrn[0],arrn[1],arrn[2]);
		var nowtims = nowtime.getTime();
		//借据结束日期
		var etime = row.END_DATE;
		var arre = etime.split("-");
		var endtime = new Date(arre[0],arre[1],arre[2].substring(0,2));
		var endtimes = endtime.getTime();
		if(nowtims ==begintims){
			if((row.PRODUCT_TYPE=="01008001" || row.PRODUCT_TYPE=="01008010" || row.PRODUCT_TYPE=="01008002") && row.SUMMARY_STATUS_CD=="03"){
			}else{
				nui.alert("当日放款的借据不能做贷后变更!");
				return false;
			}
		}
		if(loanChangeType.getValue()=="") {
			return nui.alert("请选择贷后变更类型");
		}
		if(loanChangeType.getValue() == '06'){
			if(row.PRODUCT_TYPE.substring(0,5)=='01003'){
			    nui.alert("固定资产类产品不允许做展期!");
				 return false;
			}
			if(nowtims>endtimes){
				 nui.alert("当前借据已经到期，不允许做展期!");
				 return false;
			}
			if(repayType=="0100" || repayType=="0200" || repayType=="0300" || repayType=="0400") {
				return nui.alert("只有到期一次性还本付息、按周期还息到期一次还本、按周期还息任意还本、按周期还息按还本计划表还本、按还本计划表还息按还本计划表还本可以做展期");
			}
			var termType;//未执行的展期
	        var jsons = nui.encode({"summaryId":row.SUMMARY_ID});
	       $.ajax({
	            url: "com.bos.aft.conLoanChange.queryStatus.biz.ext",
	            type: 'POST',
	            data: jsons,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            async : false,
	            success: function (mydata) {
	            	termType = mydata.type;
	            	}
	        });
			if(termType != '0'){
				return nui.alert("该借据有未执行的展期，不能重复发起");
			}
			var numperiod;//是否做过期限调整
            var oldTerm;//借据原来期限
            var currTerm;//当前执行借据的期限
            var termType;//期限类型
            var sum;//是否做过展期
		var jsonn = nui.encode({"summaryId":row.SUMMARY_ID,"beginDate":row.BEGIN_DATE,"flag":"1"});
			$.ajax({
	            url: "com.bos.aft.conLoanChange.queryOtherPeriod.biz.ext",
	            type: 'POST',
	            data: jsonn,
	            contentType:'text/json',
	            cache: false,
	            async : false,
	            success: function (mydata) {
	            numperiod = mydata.extendCount;//是否做过期限调整
	            oldTerm = mydata.oldTerm;//借据原来期限
	            currTerm = mydata.currTerm;//当前执行借据的期限
	            termType = mydata.termType;//期限类型
	            sum = mydata.sum;//是否做过展期
		         }
			});
			    if(numperiod !='0'){
		            	nui.alert("该借据做过期限调整，不允许做展期");
		            	return false;
		            }
		         if(sum !='0'){
		         	   if(termType == "短") {//一年内
			 			if(parseFloat(currTerm)+parseFloat(0.1) >= parseFloat(oldTerm)) {
				 			nui.alert("短期贷款展期期限累计不得超过原贷款期限");
				 			return;
			 			} 
			 		}else if(termType == "中") {//一到五年
			 			if(parseFloat(currTerm)+parseFloat(0.1) >= (parseFloat(oldTerm)/2)) {
				 			nui.alert("中期贷款展期期限累计不得超过原贷款期限的一半");
				 			return;
			 			} 
			 		}else if(termType == "长") {//五年以上
			 			if(parseFloat(currTerm)+parseFloat(0.1) >= 36) {
				 			nui.alert("长期贷款展期期限累计不得超过三年");
				 			return;
			 			}
			 			}
			 		}  
		}
		if(loanChangeType.getValue() == '19'){
			if(nowtims<endtimes){
				 nui.alert("当前借据还没有到期，不允许做期限调整!");
				 return false;
			}
			if(repayType=="0100" || repayType=="0200" || repayType=="0300" || repayType=="0400") {
				return nui.alert("只有到期一次性还本付息、按周期还息到期一次还本、按周期还息任意还本、按周期还息按还本计划表还本、按还本计划表还息按还本计划表还本可以做期限调整");
			}
		}
		if(row.PRODUCT_TYPE=="01006001" || row.PRODUCT_TYPE=="01006002" || row.PRODUCT_TYPE=="01009001" || row.PRODUCT_TYPE=="01009002" || row.PRODUCT_TYPE=="01009010"
		|| row.PRODUCT_TYPE=="01006010" //村镇银行贴现产品
		) {
			return nui.alert("汇票贴现、国内保函不可做贷后变更！");
		}
		
	 	
		
		if((row.PRODUCT_TYPE=="01008001" || row.PRODUCT_TYPE=="01008010") && row.SUMMARY_STATUS_CD!="03") {
			return nui.alert("银行承兑汇票不可做贷后变更！");
		}
		
		if(row.PRODUCT_TYPE=="01008002" && row.SUMMARY_STATUS_CD!="03") {
			return nui.alert("银承通不可做贷后变更！");
		}
		 
		if(loanChangeType.getValue()=="17"){
		if(row.PRODUCT_TYPE=="01013010" || row.PRODUCT_TYPE=="02005010" || row.PRODUCT_TYPE=="01013001" || row.PRODUCT_TYPE=="02005001" || row.PRODUCT_TYPE=="02005002" || row.PRODUCT_TYPE=="02005003" ) {
		
		}else{
		return nui.alert("不是委托贷款！");
		}
		}
	
		
		if(row.SUMMARY_STATUS_CD=="03") {
			if(loanChangeType.getValue()=="02") {
				return nui.alert("逾期借据不能做还款方式变更！");
			} 
			/* if(loanChangeType.getValue()=="06") {
				return nui.alert("逾期借据不能做期限变更！");
			} */
			if(loanChangeType.getValue()=="10" && row.JJYQBJ > 0) {
				return nui.alert("该笔借据有逾期本金，不能做还本计划变更！");
			}
			/* if(loanChangeType.getValue()=="11") {
				return nui.alert("逾期借据不能做提前还款！");
			} */
		} 
		
		if(loanChangeType.getValue()=="03") {
			if(row.REPAY_TYPE=="0300" || row.REPAY_TYPE=="0400") {
				return nui.alert("阶段性还款方式不能做约定还款日变更！");
			}
		}
		
		if(loanChangeType.getValue()=="04" && row.PRODUCT=="2" && row.SUMMARY_STATUS_CD!="03") {
			if(!row.PRODUCT_TYPE=="02005001"){
				return nui.alert("仅贷款业务能办理还款账号变更！");
			}
		}
		
		if(loanChangeType.getValue()=="08") {
			if(row.BIZ_TYPE!="04") {
				return nui.alert("公司客户不可以做贴息主体变更！");
			} 
			if(row.TIEXI_STATUS==null || row.TIEXI_STATUS=="") {
				return nui.alert("贴息状态为空，请联系系统管理员！");
			}
			if(row.TIEXI_STATUS!="01") {
				return nui.alert("没有原贴息主体信息，无法变更！");
			}
		} 
		
		if(loanChangeType.getValue()=="09") {
			alert("aa:"+row.BIZ_TYPE);
			if(row.BIZ_TYPE!="04") {
				return nui.alert("公司客户不可以做贴息、暂停贴息！");
			}
			if(row.TIEXI_STATUS==null || row.TIEXI_STATUS=="") {
				return nui.alert("贴息状态为空，请联系系统管理员！");
			}
		} 
		
		if(loanChangeType.getValue()=="14") {
			if(row.TINGXI_STATUS==null || row.TINGXI_STATUS=="") {
				return nui.alert("停息状态为空，请联系系统管理员！");
			}
		}
		
		if(loanChangeType.getValue()=="02" && row.REPAY_TYPE=="21") {
			return nui.alert("还款方式为预收息的不能调整");
		}
		
		if(loanChangeType.getValue()=="02" && row.TINGXI_STATUS=="01") {
			return nui.alert("停息贷款不可做还款方式变更");
		}
		
		if(loanChangeType.getValue()=="16"){
		
		if( row.TINGXI_STATUS=="01" ){
			return nui.alert("停息贷款不可做利息调整");
		}
		if(row.REPAY_TYPE=="1200"){
					return nui.alert("一次性还本付息到期前不能做利息调整");
		
		}
		}
		if(  row.TINGXI_STATUS=="01"){
		if (loanChangeType.getValue()=="04" || loanChangeType.getValue()=="14") {
		
		}else{
					return nui.alert("停息贷款不能做此交易！");
		
		}
		}
		
		if(loanChangeType.getValue()=="02" || loanChangeType.getValue()=="03" ){
		if(row.REPAY_TYPE=="1700"){
					return nui.alert("等本等息不能做还款方式变更和约定还款日变更！");
		
		}
		
		}
		
		if(loanChangeType.getValue()=="06" && row.REPAY_TYPE=="21") {
			return nui.alert("预付息贷款不能做期限变更");
		}
		
		//if(loanChangeType.getValue()=="06") {
		//	if(row.END_DATE <= busDate) {
			//	return nui.alert("营业日期小于到期日才可做期限变更！");
		//	}
		//}
		
		if(loanChangeType.getValue()=="10") {
			if(row.REPAY_TYPE!="1400" && row.REPAY_TYPE!="1410") {
				return nui.alert("只有[按还本计划表还息按还本计划表还本]，[按周期还息按还本计划表还本]，才可进行还本计划表变更");
			}
		}
		
		//国结产品的贷后变更逻辑
		//信用证- 只能做信用证修改 ;  
		//保函-只能做保函修改;
		//提货担保没有贷后;
		//表内6个产品(不能做：信用证\保函修改	贴息主体变更	贴息及暂停贴息	提前及手工还款	委托人收本收息账户变更)
        //进口代付及福费廷不涉及贷后功能.
		if(loanChangeType.getValue()!="12" && row.PRODUCT_TYPE=="01007013") {
			return nui.alert("[进口信用证]只能做信用证修改");
		}
		
		if(loanChangeType.getValue()!="13" && row.PRODUCT_TYPE=="01007014") {
			return nui.alert("[国际保函]只能做保函修改");
		} 
		
		if(loanChangeType.getValue()=="12" && row.PRODUCT_TYPE!="01007013") {
			return nui.alert("只有[进口信用证]才能做信用证修改");
		}
		if(loanChangeType.getValue()=="13" && row.PRODUCT_TYPE!="01007014") {
			return nui.alert("只有[国际保函]才能做保函修改");
		}
		if(row.PRODUCT_TYPE=='01007010'||row.PRODUCT_TYPE=='01007012'||row.PRODUCT_TYPE=='01007009'){
			return nui.alert("国结业务[提货担保][进口代付][福费廷]不能发起贷后变更");
		}
		if((row.PRODUCT_TYPE=='01007001'||row.PRODUCT_TYPE=='01007003'||row.PRODUCT_TYPE=='01007004'||
		   row.PRODUCT_TYPE=='010070011'||row.PRODUCT_TYPE=='01007005'||row.PRODUCT_TYPE=='01007006')&&
		   (loanChangeType.getValue()=="08"||loanChangeType.getValue()=="09"||
		    loanChangeType.getValue()=="11"||loanChangeType.getValue()=="17")){
		    
		   var product;
		   if(row.PRODUCT_TYPE=='01007001'){
		        product = '[出口信用证押汇]';
		   } 
		   if(row.PRODUCT_TYPE=='01007003'){
		        product = '[出口托收押汇]';
		   } 
		   if(row.PRODUCT_TYPE=='01007004'){
		        product = '[进口信用证押汇]';
		   }  
		   if(row.PRODUCT_TYPE=='010070011'){
		        product = '[国际信用证打包贷款]';
		   }  
		   if(row.PRODUCT_TYPE=='01007005'){
		        product = '[进口T/T押汇]';
		   }  
		   if(row.PRODUCT_TYPE=='01007006'){
		        product = '[进口信用证押汇]';
		   }  
		   if(loanChangeType.getValue()=="08"){
		   		return alert(product+"不能发起类型为[贴息主体变更]的贷后变更");
		   }
		   if(loanChangeType.getValue()=="09"){
		   		return alert(product+"不能发起类型为[贴息、暂停贴息]的贷后变更");
		   }
		   if(loanChangeType.getValue()=="11"){
		   		return alert(product+"不能发起类型为[提前/手工还款]的贷后变更");
		   }
		   if(loanChangeType.getValue()=="17"){
		   		return alert(product+"不能发起类型为[委托人收本收息账户变更]的贷后变更");
		   }
		}
		//如果当前有信用证修改正在国结审批中---不让修改  要等国结调用业务通知接口以后才能再次发起 
		if(loanChangeType.getValue()=="12" && row.PRODUCT_TYPE=="01007013") {
			var summaryId = row.SUMMARY_ID;
			var partyId = row.PARTY_ID;
			var contractId = row.CONTRACT_ID;
        	var json1 = {"summaryId":summaryId,"partyId":partyId,"contractId":contractId,changeType:"12"};
   	    	msg = exeRule("REWS_0009","1",json1);
   	    	if(null != msg && '' != msg){
	   			nui.alert(msg);
	   			return "1";
   	   		}
		}

		//如果当前有保函修改正在国结审批中---不让修改  要等国结调用业务通知接口以后才能再次发起 
		if(loanChangeType.getValue()=="13" && row.PRODUCT_TYPE=="01007014") {
			var summaryId = row.SUMMARY_ID;
			var partyId = row.PARTY_ID;
			var contractId = row.CONTRACT_ID;
        	var json1 = {"summaryId":summaryId,"partyId":partyId,"contractId":contractId,changeType:"13"};
   	    	msg = exeRule("REWS_0009","1",json1);
   	    	if(null != msg && '' != msg){
	   			nui.alert(msg);
	   			return "1";
   	   		}
		} 
		
		if(loanChangeType.getValue()=="12" && row.BEGIN_DATE == busDate) {
			return nui.alert("放款当天不能做信用证修改！");
		}
		
		if(loanChangeType.getValue()=="13" && row.BEGIN_DATE == busDate) {
			return nui.alert("放款当天不能做保函修改！");
		}
		
		 //|| loanChangeType.getValue()=="06" --绵阳银行删除
		if(loanChangeType.getValue()=="02" || loanChangeType.getValue()=="03" || loanChangeType.getValue()=="10"){
		if(row.END_DATE <= busDate){
		return nui.alert("当前营业日期须小于贷款到期日才能做此交易！");
		}
		}
		
		
 		if(row.TINGXI_STATUS=="01" && (loanChangeType.getValue()=="11" || loanChangeType.getValue()=="15" || loanChangeType.getValue()=="18" )){
		return nui.alert("停息不能做提请还款、合作商代偿、指定账号还款！");
		
		}
		
		nui.get("btnCreate").setEnabled(false);
		/* var loanchagetypechagne;
		if(loanChangeType.getValue() == '19'){
			loanchagetypechagne = '06';
		}else{
			loanchagetypechagne = loanChangeType.getValue();
		} */
		
		//var json=nui.encode({"contractId":row.CONTRACT_ID,"partyId":row.PARTY_ID,"summaryId":row.SUMMARY_ID,"loanChangeType":"01"});
		var json=nui.encode({"contractId":row.CONTRACT_ID,"partyId":row.PARTY_ID,"summaryId":row.SUMMARY_ID,"loanChangeType":loanChangeType.getValue(),"isSmall":"0"});
		$.ajax({  
	        url: "com.bos.aft.conLoanChange.createConLoanChange.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
            	if(data.flag){
                	return nui.alert(data.flag);
                }
            	nui.open({//传值到tree页面
		            url: nui.context+"/aft/conLoanChange/loanChange_tree.jsp?changeId="+data.tbConLoanChange.changeId+"&contractId="+row.CONTRACT_ID+"&partyId="+row.PARTY_ID+"&summaryId="+row.SUMMARY_ID+"&loanChangeType="+loanChangeType.getValue()+"&processInstId="+data.processInstId+"&proFlag=1",
		            showMaxButton: false,
		            title: "贷后变更",
		            width: 1024,
		            height: 768,
		            state:"max",
		            ondestroy: function (action) {
		            	query();//重新刷新页面
		            }
	        	});
	        	if(data.msg){
	        		if(data.msg != "创建成功！") {
	        			alert(data.msg);
	        		}
                }
            	//git.go(nui.context+"/aft/conLoanChange/loanChange_tree.jsp?changeId="+data.tbConLoanChange.changeId+"&contractId="+row.CONTRACT_ID+"&partyId="+row.PARTY_ID+"&summaryId="+row.SUMMARY_ID+"&loanChangeType=01",parent); 
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}
    
    //重置
	function reset(){
		form.reset();
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
	
	nui.get("loanChangeType").setData(getDictData("XD_DHBG0001","str","01,02,03,04,06,08,09,10,11,12,13,14,15,16,18,19"));
</script>
</body>
</html>