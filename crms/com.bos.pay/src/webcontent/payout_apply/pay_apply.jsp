<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-14 19:20:27
  - Description:
-->
<head>
<title>放款申请</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
	<fieldset>
	<legend> <h4>借据列表</h4> </legend>
    <div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
	    <a class="nui-button" id="creat" onclick="add">出账</a>
	    <a class="nui-button" id="onloanNum" onclick="querysummaryInfo">查看借据</a>
	   <!--  <a class="nui-button" id="zjsytz" onclick="viewUse">资金使用台账</a> -->
	    <a class="nui-button" id="hkjh" onclick="hkjh">还款计划</a>
	    <a class="nui-button" id="dycztzs" onclick="cztzs">打印出账通知书</a>
	    <a class="nui-button" id="KHHD" onclick="printKHHD(this)">打印客户回单</a>
	    <a class="nui-button" id="MYSTZFF" onclick="printKHHD(this)">打印受托方结算信息回单</a>
	    <a class="nui-button" id="MYSTZFFT" onclick="printKHHD(this)">打印受托方保证金信息回单</a>
	    <a class="nui-button" id="MYDKQD"  onclick="print(this)">打印放款凭证</a>
	    <a class="nui-button" id="HKPZ" onclick="printHkpz(this)">打印还款凭证</a>
	    <a class="nui-button" id="WTDKHD" onclick="printHkpz(this)" style="display: none;">打印委托贷款回单</a>
	   <!--   <a class="nui-button" id="JXQD" onclick="print(this)">打印结息清单</a>
	    <a class="nui-button" id="JQZM" onclick="print(this)">打印结清证明</a>
	    <a class="nui-button" id="JZJTJG" onclick="print(this)">打印计提减值结果</a>
	    <a class="nui-button" id="TXMX" onclick="print(this)">打印贴息明细</a>
	    <a class="nui-button" id="PZBD" onclick="print(this)">打印凭证补打</a> -->
	    
	    <a class="nui-button" id="cxjy" onclick="cxjy">国结/票据撤销</a>
	    <a class="nui-button" id="cxfhjy" onclick="cxfhjy()">放款/还款撤销</a>
	    <!-- <a class="nui-button" id="" onclick="txcztzs">打印贴现出账通知书</a> -->
	    
	   <!--  <a class="nui-button" id="cxcz" onclick="cxcz">撤销出账</a> -->
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pay.LoanSummary.queryLoanSummary.biz.ext" dataField="loanInfos"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="true" showColumnsMenu="true">
	    <div property="columns">
	    	
	        <div type="checkcolumn" >选择</div>
	        <div type="indexcolumn">序号</div>        
	        <div field="SUMMARY_NUM" allowSort="true"  headerAlign="center">借据编号</div>
	        <div field="SUMMARY_CURRENCY_CD" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">币种</div>
	        <div field="SUMMARY_AMT" allowSort="true"  headerAlign="center" dataType="currency">借据金额</div>
	        <div field="JJYE" allowSort="true"  headerAlign="center" dataType="currency">借据余额</div>
	        <div field="BEGIN_DATE" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">借据起期</div>
	        <div field="END_DATE" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">借据止期</div>
	        <div field="YEAR_RATE" allowSort="true"  headerAlign="center" dataType="currency">利率（%）</div>
	        <div field="SUMMARY_STATUS_CD" allowSort="true"  headerAlign="center" dictTypeId="XD_SXYW0226">借据状态</div>
			<div field="ORG_NUM" allowSort="true"  headerAlign="center" dictTypeId="org">经办机构</div>
	        <div field="USER_NUM" allowSort="true"  headerAlign="center" dictTypeId="user">经办人</div>
	     </div>
	</div>
	</fieldset>
</div>
<script type="text/javascript">
	nui.parse();
	//nui.get("cxcz").hide();//出账撤销按钮
	//nui.get("dyjkpz").hide();//打印借款凭证
	nui.get("dycztzs").hide();//打印出账通知书
	nui.get("MYDKQD").hide();//放款清单
	nui.get("HKPZ").hide();//还款清单
	//nui.get("hkjh").hide();//还款计划
	//nui.get("zjsytz").hide();//资金使用台账
	
	//获取法人代码
	var legOrg ="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("legorg") %>";
	//非绵商行(9999的不能打印合同)
	//if(legOrg != "9999"){
		nui.get("dycztzs").hide();
	//}
	
	var contractId="<%=request.getParameter("contractId") %>";
	var amountDetailId = "";
	var grid = nui.get("grid");
	var productType_="";//产品代码
	var contractNum;//产品编号
	initPage();
	function initPage(){
		//合同信息查出贷种
		var json = nui.encode({"contractId":contractId});
		$.ajax({
            url: "com.bos.pay.LoanSummary.getConInfoByConId.biz.ext",
            type: 'POST',
            data: json,
        	async: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var productType = mydata.conInfo.productType;
            	productType_ = productType;
            	contractNum = mydata.conInfo.contractNum;
            	amountDetailId = mydata.conInfo.amountDetailId;
            	if("03" != mydata.conInfo.conStatus){//生效合同
	            	nui.get("creat").hide();
	            	//nui.get("cxcz").hide();
            	}
            	//显示委托贷款回单打印
            	var productTypes = ["01013010","02005010","02005001","02005002","01013001"];
            	for(var i =0;i<productTypes.length;i++){
            		if(productType==productTypes[i]){
						nui.get("WTDKHD").show();
            		}
            	}
				//显示撤销出账按钮
            	if(productType=='01008001' ||productType=='01008002'||productType=='01008010' ||productType=='01009001'||productType=='01009002' ||productType=='01009010' ||
            		productType=='01011001'||productType=='01012001'||productType=='01010001'){
	            		//nui.get("cxcz").show();
	            }
	            //隐藏打印借款凭证(没有打印借款凭证)
	            if(productType=='01007007'||productType=='01007008'||productType=='01008001'||productType=='01008010'||productType=='01008002'||
	            	productType=='01009001'||productType=='01009002' ||productType=='01009010' ||productType=='01010001'||
	            	productType=='01011001'||productType=='01012001'){
	            		//nui.get("dyjkpz").hide();
	            }
	            //隐藏还款计划
	            if(startWith(productType,'01007')==1||productType=='01008001'||productType=='01008002'||productType=='01008010'||productType=='01009001'||
	            	productType=='01010001'||productType=='01009002' ||productType=='01009010' ||
	            	productType=='01011001'||productType=='01012001'){
	            		nui.get("hkjh").hide();
	            }
	            //显示出账通知书
	            //if(legOrg =='9999'){
	            	if(productType=='01004001'||productType=='01006001'||productType=='01006002'||
	            	productType=='01008001'||productType=='01008002'||productType=='01008010'||productType=='01010001'||
	            	productType=='01011001'||productType=='01012001'
	            	||productType=='01006010'){//村镇银行贴现产品
	            		nui.get("dycztzs").show();
	            		if(productType=='01008001'||productType=='01008002'||productType=='01008010'){
	            			nui.get("HKPZ").show();
	            		}
	            	}else{
	            		nui.get("MYDKQD").show();
	            		nui.get("HKPZ").show();
	            	}
	            //}
	            //隐藏资金使用台账
	            if(startWith(productType,'01007')==1||productType=='01008001'||productType=='01008002'||productType=='01008010'||productType=='01009001'||
	            	productType=='01010001'||productType=='01009002' ||productType=='01009010' ||
	            	productType=='01011001'||productType=='01012001'){
	            	//	nui.get("zjsytz").hide();
	            }
	            //显示隐藏按钮
	            if(productType_=="01007001"||productType_=="01007003"||productType_=="01007004"||productType_=="01007009"||
		      	   productType_=="01007012"||productType_=="01007011"||productType_=="01007006"||productType_=="01007005"||
		      	   productType_=="01007010"||productType_=="01007013"||productType_=="01007014"||productType_=="01006001"||productType_=="01006002"
					||productType_=="01006010"||productType_== "01008001"||productType_=="01008002"||productType_=="01008010"){
	            	nui.get("cxfhjy").hide();
	            }else{
	            	nui.get("cxjy").hide();
	            }
            	grid.load({"contractId":"<%=request.getParameter("contractId") %>"});
			}
        });
	}
	
	//放款还款撤销
	function cxfhjy(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		var json1 = {"summaryNum":row.SUMMARY_NUM};
   	    var msg = exeRule("RLON_0029","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    } 
   	     //判断借据状态
        var status = row.SUMMARY_STATUS_CD;
        if('02' ==status || '04' ==status){
        }else{
        	nui.alert("只能撤销借据状态为'正常'的当日放款还款或状态为'结清'的还款交易!");
			return false;
        }
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
        //借据起始日期
		var jtime = row.BEGIN_DATE;
		var arrs = jtime.split("-");
		var begintime = new Date(arrs[0],arrs[1],arrs[2].substring(0,2));
		var begintims = begintime.getTime();
		//当前系统时间
		var arrn = thisDate.split("-");
		var nowtime = new Date(arrn[0],arrn[1],arrn[2]);
		var nowtims = nowtime.getTime();
		//判断调用放款还是还款
		var changeId;
		var loanChangeType;
		var contractId;
		var partyId;
		var summaryId;
		var changeNum;
		var changeLoanId;
		var changeDate;
		var jsonChange = nui.encode({"summaryId":row.SUMMARY_ID});
		  $.ajax({
            url: "com.bos.pay.LoanSummary.getChangeTime.biz.ext",
            type: 'POST',
            data: jsonChange,
            cache: false,
            async : false,
            contentType:'text/json',
            success: function (text) {
            	if(text.changeInfo){
            		changeId = text.changeInfo.CHANGE_ID;
	            	loanChangeType = text.changeInfo.LOAN_CHANGE_TYPE;
	            	contractId = text.changeInfo.CONTRACT_ID;
	            	partyId = text.changeInfo.PARTY_ID;
	            	summaryId = text.changeInfo.SUMMARY_ID;
	            	changeNum = text.changeInfo.SUMMARY_NUM;
	            	changeLoanId = text.changeInfo.LOAN_ID;
	            	changeDate = text.changeInfo.CHANGE_DATE;
            	}
            }
        });
        //还款撤销
        if(changeId && (changeNum == row.SUMMARY_NUM)){
		        //还款时间
				var changDat = changeDate.split("-");
				var ntime = new Date(changDat[0],changDat[1],changDat[2]);
				var ntimes = ntime.getTime();
				if(nowtims !=ntimes){
						nui.alert("只能撤销当日还款交易");
					return false;
				}
        		var jsonr=nui.encode({"changeId":changeId,"loanId":changeLoanId});
				$.ajax({  
			        url: "com.bos.pay.LoanSummary.createRepayInfo.biz.ext",
			        type: 'POST',
			        data: jsonr,
			        contentType:'text/json',
			        cache: false,
			        success: function (data) {
		            	nui.get("cxfhjy").setEnabled(true);
		            	if(data.flag){
		                	return nui.alert(data.flag);
		                }
				           git.go(nui.context+"/aft/conLoanChange/loanChange_tree1.jsp?changeId="+changeId+"&contractId="+contractId+"&partyId="+partyId+"&summaryId="+summaryId+"&loanChangeType="+loanChangeType+"&processInstId="+data.processInstId+"&proFlag=0",parent);
					},
			        error: function (jqXHR, textStatus, errorThrown) {
			            alert(jqXHR.responseText);
			            git.unmask();
			        }
		        });	
        }else{//放款撤销
        		if(nowtims !=begintims){
					nui.alert("只能撤销当日放款交易");
					return false;
				}
		        var jsonp = nui.encode({"loanId":row.LOAN_ID});	
				 $.ajax({
		            url: "com.bos.pay.LoanSummary.createPayInfo.biz.ext",
		            type: 'POST',
		            data: jsonp,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	git.unmask();
		            	if(text.msg !=null){
		            		nui.alert(text.msg); //失败时后台直接返回出错信息
		            		nui.get("cxfhjy").setEnabled(true);
		            		return;
		            	}
		            	 git.go(nui.context+"/pay/payout_info/pay_tree1.jsp?loanId="+row.LOAN_ID+"&processInstId="+text.processInstId+"&proFlag=0",parent);
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
		        });
        }
	}
	
	//撤销交易
	function cxjy(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		//国结的产品 调用国结的放款撤销接口
		if(productType_=="01007001"||productType_=="01007003"||productType_=="01007004"||productType_=="01007009"||
      	   productType_=="01007012"||productType_=="01007011"||productType_=="01007006"||productType_=="01007005"||
      	   productType_=="01007010"||productType_=="01007013"||productType_=="01007014"){
      	   //国结的撤销逻辑没有限制---不用判断 add by shendl
		}else if (productType_=="01006001"||productType_=="01006002"||productType_=="01006010"||
			productType_=="01008001"||productType_=="01008002"||productType_=="01008010"){
		}else{
      	    //判断借据状态
        	var status = row.SUMMARY_STATUS_CD;
        	if('02' !=status){
        		nui.alert("只能撤销借据状态为'正常'的当日放款还款交易!");
				return false;
        	}
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
			//借据起始日期
			var jtime = row.BEGIN_DATE;
			var arrs = jtime.split("-");
			var begintime = new Date(arrs[0],arrs[1],arrs[2].substring(0,2));
			var begintims = begintime.getTime();
			//当前系统时间
			var arrn = thisDate.split("-");
			var nowtime = new Date(arrn[0],arrn[1],arrn[2]);
			var nowtims = nowtime.getTime();
			if(nowtims !=begintims){
				nui.alert("只能撤销当日交易");
				return false;
			}
      	}
      	
		var json = nui.encode({"summaryNum":row.SUMMARY_NUM,"nftNo":row.NFT_NO,"rcnStan":row.RCN_STAN,"beginDate":row.BEGIN_DATE,"summaryId":row.SUMMARY_ID});	
		var json1 = nui.encode({"summaryNum":row.SUMMARY_NUM,"loanId":row.LOAN_ID,"summaryId":row.SUMMARY_ID});
		git.mask(); 
		
		//国结的产品 调用国结的放款撤销接口
		if(productType_=="01007001"||productType_=="01007003"||productType_=="01007004"||productType_=="01007009"||
      	   productType_=="01007012"||productType_=="01007011"||productType_=="01007006"||productType_=="01007005"||
      	   productType_=="01007010"||productType_=="01007013"||productType_=="01007014"){
			$.ajax({
				url: "com.bos.pay.LoanSummary.GjBussBackout.biz.ext",
				type: 'POST',
				data: json1,
				async: false,
				contentType:'text/json',
				cache: false,
				success: function (mydata) {
					if(mydata.resultCode=="9999"){
						if(mydata.resultMsg == ""||mydata.resultMsg==null){
							alert("调用国结撤销接口异常，请联系管理人员！");
						}else{
							alert(mydata.resultMsg);
						}
						nui.get("cxjy").setEnabled(true);
						git.unmask();  
					}else{
						alert("撤销成功");
						nui.get("cxjy").setEnabled(true);
						initPage();
						git.unmask();  
					}
				}
			});
		}
		// 贴现撤销
		else if(productType_=="01006001"||productType_=="01006002"||productType_=="01006010"){
			$.ajax({
				url: "com.primeton.ecds.client.CallECDS.ECDS021004.biz.ext",
				type: 'POST',
				data: json1,
				async: false,
				contentType:'text/json',
				cache: false,
				success: function (mydata) {
					git.unmask();
					alert(mydata.msg);
					//grid.refresh();
					initPage();
					nui.get("cxjy").setEnabled(true);
				}
			});
		}
		// 银承撤销
		else if(productType_=="01008001"||productType_=="01008002" || productType_=="01008010"){
			$.ajax({
				url: "com.primeton.ecds.client.CallECDS.ECDS021002.biz.ext",
				type: 'POST',
				data: json1,
				async: false,
				contentType:'text/json',
				cache: false,
				success: function (mydata) {
					git.unmask();  
					alert(mydata.msg);
					//grid.refresh();
					initPage();
					nui.get("cxjy").setEnabled(true);
				}
			});
		}
	}
	
	// 出账
	function add(){
		nui.get("creat").setEnabled(false);
		git.mask();
		//又在途不让申请新的出账
		var json1 = {"contractId":"<%=request.getParameter("contractId") %>","amountDetailId":amountDetailId};
   	    var msg = exeRule("RLON_0001","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    } 
   	    //批复冻结不能出账
   	    msg = exeRule("RCON_0027","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    } 
   	    //担保金额和合同金额比较
   	    msg = exeRule("RCON_0014","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    }
   	    //在途合同调整
   	    msg = exeRule("RCON_0026","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    }
   	    var json2 = nui.encode({"contractId":"<%=request.getParameter("contractId") %>"});
   	    var flag = "1";
   	    $.ajax({
				url: 'com.bos.grt.grtMainManage.grtOuter.checkAnjie.biz.ext',
				type: 'POST',
				data: json2,
				async: false,
				contentType:'text/json',
				cache: false,
				success: function (text) {
					if(text.msg == "1"){
						//押品未入库--委托贷款及房产类押品（当不动产证书办理状态为“未办理”的）在出账时不用校验押品入库
						msg = exeRule("RLON_0008","1",json1);
				   	    if(null != msg && '' != msg){
					   		nui.alert(msg);
					   		 git.unmask();
					   		 flag ="2";
					   		//return "1";
				   	    } 
					}else{
						msg = exeRule("RLON_0036","1",json1);
				   	    if(null != msg && '' != msg){
					   		nui.alert(msg);
					   		 git.unmask();
					   		  flag ="2";
					   		//return "1";
				   	    } 
					}
				}
			});
   	    
   	    if( flag =="2"){
   	    	return "1";
   	    }
		//押品未入库--委托贷款及房产类押品（当不动产证书办理状态为“未办理”的）在出账时不用校验押品入库
		msg = exeRule("RLON_0008","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    } 
   	    //校验合同金额是否足够
   	    msg = exeRule("RLON_0035","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    } 
   	    // 合同起止日期之内
      	msg = exeRule("RLON_0034","1",json1);
		if(null != msg && '' != msg){
				nui.get("creat").setEnabled(false);
				git.unmask();
				nui.alert(msg);
				return "1";
		} 		
   	    //国结的产品---一笔合同只能做一次出账
   	    if(productType_=="01007001"||productType_=="01007003"||productType_=="01007004"||productType_=="01007009"||
      	   productType_=="01007012"||productType_=="01007011"||productType_=="01007006"||productType_=="01007005"||
      	   productType_=="01007010"||productType_=="01007013"||productType_=="01007014"){
      	   msg = exeRule("RLON_0028","1",json1);
   	       if(null != msg && '' != msg){
   	       		nui.get("creat").setEnabled(false);
   	       		git.unmask();
	   			nui.alert(msg);
	   			return "1";
   	    	} 		
      	}
      	// 银承贴现只能出账一次 
		if("01006001"==productType_||"01006002"==productType_||"01006010"==productType_
		||"01008001"==productType_||"01008002"==productType_||"01008010"==productType_  ){
			msg = exeRule("RLON_0033","1",json1);
			if(null != msg && '' != msg){
				nui.get("creat").setEnabled(false);
				git.unmask();
				nui.alert(msg);
				return "1";
			} 		
		}
		
        var json = nui.encode({"contractId":"<%=request.getParameter("contractId") %>","partyId":"<%=request.getParameter("partyId") %>","productType":productType_,"amountDetailId":amountDetailId});
        $.ajax({
            url: "com.bos.pay.LoanSummary.createLoanInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask();
            	if(text.msg !=null){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
            		nui.get("creat").setEnabled(true);
            		return;
            	}
            	git.go(nui.context+"/pay/payout_info/pay_tree.jsp?loanId="+text.tbLoanInfo.loanId+"&processInstId="+text.processInstId+"&proFlag=1",parent);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	function querysummaryInfo(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		nui.open({
			url:nui.context + "/pay/payout_info/summary_view.jsp?summaryId="+row.SUMMARY_ID,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            }
		});
	}
	
	function addUse(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		nui.open({
			url:nui.context + "/pay/money_use/money_use_add.jsp?summaryId="+row.SUMMARY_ID+"&loanId="+row.LOAN_ID+"&partyId="+row.PARTY_ID+"&summaryAmt="+row.SUMMARY_AMT+"&beginDate="+row.BEGIN_DATE,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            }
		});
	}
	function viewUse(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		nui.open({
			url:nui.context + "/pay/money_use/money_use_list.jsp?summaryId="+row.SUMMARY_ID+"&loanId="+row.LOAN_ID+"&partyId="+row.PARTY_ID+"&loanAmt="+row.SUMMARY_AMT+"&beginDate="+row.BEGIN_DATE,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            }
		});
	}
	function hkjh(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		nui.open({
			url:nui.context + "/pay/payout_info/loan_hkjh.jsp?summaryNum="+row.SUMMARY_NUM+"&loanId="+row.LOAN_ID,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            }
		});
	}
	function cztzs(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		var json = nui.encode({"loanId":row.LOAN_ID});
        $.ajax({
            url: "com.bos.pay.LoanSummary.loanNotice.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.swfPath){
	        		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
						title: "出账通知书信息预览", width: 1000, height: 500,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
	        	}else{
	        		alert(text.msg);
	        	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	function printKHHD(printType){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		var trusToPayFlg= row.TRUS_TO_PAY_FLG;
		if("1" == trusToPayFlg){
			print(printType);
		}else{
			nui.alert("只有受托支付的业务能打印此客户回单");
		}
	}
	// 公共打印方法
	function print(printType){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		var productType = row.PRODUCT_TYPE;
		var printType = printType.id;
		if(productType.substring(0,5)=='01009'){
			printType='MYGNBH';
		}
		var json = nui.encode({"printType":printType,"dueNum":row.SUMMARY_NUM,"begDate":row.BEGIN_DATE,"endDate":row.END_DATE,"loanId":row.LOAN_ID,"orgNum":row.ORG_NUM,"sumId":row.SUMMARY_ID});
        $.ajax({
            url: "com.bos.pay.LoanSummary.PrintCommon.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.swfPath){
					nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
						title: "借款凭证信息预览", width: 1000, height: 500,
			            onload: function () {
			           },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
				}else{
					alert("无打印信息!");
				}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
        }
	
	function dkhd(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔回单");
			return false;
		}
		var json = nui.encode({"contractId":contractId,"loanId":row.LOAN_ID});
	        		nui.open({
						url:nui.context +"/pay/payout_apply/pay_hd_list.jsp?summaryId="+row.SUMMARY_ID,
						title: "回单信息", width: 1000, height: 500,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
					});
		}
	
	
// 	function dkhd(){
// 		var row = grid.getSelected();
// 		if (null == row) {
// 			nui.alert("请选择一笔回单");
// 			return false;
// 		}
// 		var json = nui.encode({"contractId":contractId,"loanId":row.LOAN_ID});
//         $.ajax({
//             url: "com.bos.pay.LoanSummary.loanHD.biz.ext",
//             type: 'POST',
//             data: json,
//             cache: false,
//             contentType:'text/json',
//             success: function (text) {
//             	if(text.swfPath){
// 	        		nui.open({
// 						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
// 						title: "回单信息预览", width: 1000, height: 600,
// 			            onload: function () {
// 			            },
// 			            ondestroy: function (action) {
// 			                  grid.reload();
// 			            }
			
// 					});
// 	        	}else{
// 	        		alert("无打印信息!");
// 	        	}
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//                 nui.alert(jqXHR.responseText);
//             }
//         });
// 	}
	//撤销出账
	function cxcz(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		if(row.SUMMARY_STATUS_CD == '06'){
			nui.alert("该笔借据已失效！");
			return false;
		}
		
		//nui.get("cxcz").setEnabled(false);
		//执行撤销操作
		var json = nui.encode({"loanId":row.LOAN_ID,"partyId":row.PARTY_ID});
		$.ajax({
            url: "com.bos.pay.LoanSummary.loanCancel.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	nui.alert(text.msg);
            	//nui.get("cxcz").setEnabled(true);
            	grid.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	
/* 	function txcztzs(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		var json = nui.encode({"contractId":contractId,"loanId":row.LOAN_ID});
        $.ajax({
            url: "com.bos.pay.LoanSummary.loanNotice.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.swfPath){
	        		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
						title: "贴现出账通知书信息预览", width: 1000, height: 600,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
	        	}else{
	        		alert("无打印信息!");
	        	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	} */
	function startWith(src,str){
		if(str==null || str==''|| src==null || src==''){
			return -1;
		}
		if(src.substr(0,str.length)==str){
			return 1;
		}else{
			return -1;
		}
	}
	function printHkpz(e){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		nui.open({
			url:nui.context +"/pay/replay/repay_witness_list.jsp?printType="+e.id+"&summaryId="+row.SUMMARY_ID+"&summaryNum="+row.SUMMARY_NUM,
			title: "还款记录列表", 
			width: 900, 
			height: 700,
			showModal : true,
            ondestroy: function (action) {
                  grid.reload();
            }
		});
	}
</script>
</body>
</html>