<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>还款撤销</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="还款撤销" region="west" width="220" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:100%;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="wborder:0;padding-left:5px;padding-top:5px;width: 60%;">
			
				<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
					<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
					<label></label><a id="approveInfo" href="#"  onclick="getBiz()">业务信息</a> 
					<label></label><a id="contractInfo" href="#"  onclick="getCon()">合同信息</a>
					<label></label><a id="extensionInfo" href="#"  onclick="getLoan()">借据信息</a> 
					<!-- <label></label><a id="approveInfo" href="#"  onclick="getApprove()">批复信息</a> 
					<label></label><a id="contractInfo" href="#"  onclick="getContract()">合同信息</a>
					<label></label><a id="extensionInfo" href="#"  onclick="getSummary()">借据信息</a>  -->
				</div>
			   
		    	<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;margin-top: 10px;" refreshOnClick="true"></div>
		    </div>
		</div>
<script type="text/javascript">
	nui.parse();
	var node=<%=request.getParameter("node") %>;
	var partyId ="<%=request.getParameter("partyId") %>"; 
	var contractId ="<%=request.getParameter("contractId") %>"; 
	var changeId ="<%=request.getParameter("changeId") %>"; 
	var summaryId ="<%=request.getParameter("summaryId") %>"; 
	var loanChangeType ="<%=request.getParameter("loanChangeType") %>";
	var processInstId ="<%=request.getParameter("processInstId") %>"; //业务申请ID
	var proFlag ="<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var party;   
	var applyId = "";
	var bizNum = "";
	var conNum = "";
	var loanNum = "";
	var menudata; 
	
	var qote = "1";
	var ismove = "0";//是否移迁数据  1是 0 否
	var flowModuleType = "";//影像模板节点类型（参考下面的映射表，多个节点以英文“，”分隔）
	var view; //查看标志 1 查看 2 上传
	var changeNum = "";
	var isSmall = "";
	var changeType = "";
	var termChangeWay = "";
	var repayType = "";
	
	var tbBizAmountApprove;
	
	var json = nui.encode({partyId:partyId,changeId:changeId});

        
        $.ajax({
            url: "com.bos.aft.common.getBiz.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {  
            	o = nui.decode(mydata);
            	applyId = o.tbBizApprove.applyId; 
            	partyId = o.tbConLoanChange.partyId;  
            	bizNum = o.tbBizApprove.bizNum;
            	conNum = o.tbConContractInfo.contractNum;
            	loanNum = o.tbLoanSummary.summaryNum;
            	party = o.tbCsmParty;
            	changeNum = o.tbConLoanChange.changeNum;
            	isSmall = o.tbConLoanChange.isSmall;
            	changeType = o.tbConLoanChange.loanChangeType;
            	loanChangeType= o.tbConLoanChange.loanChangeType;
            	termChangeWay = o.tbConLoanChange.termChangeWay;
            	tbBizAmountApprove = o.tbBizAmountApprove;
            	repayType = o.tbLoanInfo.repayType;
            	//alert(repayType);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        }); 
    /* if(party.partyTypeCd == "02") {//个贷
    	flowModuleType = "27";
    }else {
    	flowModuleType = "115,1151,1152";
    } */
    
    flowModuleType = "18";
    
    if(proFlag == "1") {
    	qote = "2";
    }
        
    if(qote=="1"){
    	view = [					     
			{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+changeNum+"&csmNum="+party.partyNum+"&partyTypeCd=01&flowModuleType="+flowModuleType+"&view="+qote}
		]
	}else{
		view = [
		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+changeNum+"&csmNum="+party.partyNum+"&partyTypeCd=01&flowModuleType="+flowModuleType}
		]
	}
        
	if("01"==loanChangeType){//利率变更
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_rate_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	} 
	
		if("16"==loanChangeType){//利息调整
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_rate_lxtz.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	} 
	
	if("0"==isSmall && "02"==loanChangeType){//还款方式变更
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_repayway_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("1"==isSmall && "02"==loanChangeType){//小贷还款方式变更
		//alert(repayType);
		//if("1400"==repayType || "1410"==repayType) {
			menudata = [
				{id:"贷后变更信息", text:"贷后变更信息",
					children:[
						{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_repayway_apply.jsp?changeId="+changeId+"&proFlag="+proFlag},
						{id:"还款计划表",text:"还款计划表",url:"/aft/conLoanChange/loanChange_repayplan_xw.jsp?changeId="+changeId+"&proFlag="+proFlag}
					]
				},
				{id:"影像资料", text:"影像资料", children:view}
			];
		/* }else {
			menudata = [
				{id:"贷后变更信息", text:"贷后变更信息",
					children:[
						{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_repayway_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
					]
				},
				{id:"影像资料", text:"影像资料", children:view}
			];
		} */
	} 
 
	 
	if("0"==isSmall && ("11"==loanChangeType || "18"==loanChangeType || "15"==loanChangeType )){//提前还款
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_earlyrepay_apply.jsp?changeId="+changeId+"&proFlag="+proFlag+"&loanChangeType="+loanChangeType}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("1"==isSmall && ("11"==loanChangeType || "18"==loanChangeType || "15"==loanChangeType )){//小贷提前还款
		//alert(repayType);
		if("1400"==repayType || "1410"==repayType) {
			menudata = [
				{id:"贷后变更信息", text:"贷后变更信息",
					children:[
						{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_earlyrepay_apply.jsp?changeId="+changeId+"&proFlag="+proFlag+"&loanChangeType="+loanChangeType},
						{id:"还款计划表",text:"还款计划表",url:"/aft/conLoanChange/loanChange_repayplan_xw.jsp?changeId="+changeId+"&proFlag="+proFlag+"&loanChangeType="+loanChangeType}
					]
				},
				{id:"影像资料", text:"影像资料", children:view}
			];
		}else {
			menudata = [
				{id:"贷后变更信息", text:"贷后变更信息",
					children:[
						{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_earlyrepay_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
					]
				},
				{id:"影像资料", text:"影像资料", children:view}
			];
		}
		
	}
	if("06"==loanChangeType){//期限变更
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_term_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("03"==loanChangeType){//约定扣款日变更
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_repayday_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("04"==loanChangeType){//还款账号变更
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_repayaccount_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	
	if("17"==loanChangeType){//还款账号变更
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_wtraccount_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	
	if("08"==loanChangeType){//结息周期调整
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_txinfo_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("09"==loanChangeType){//贴息调整
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_tx_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	
	if("0"==isSmall && "10"==loanChangeType){//还本计划表变更
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_repayplan_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	
	if("1"==isSmall && "10"==loanChangeType){//小贷还款计划表
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_repayplan_apply.jsp?changeId="+changeId+"&proFlag="+proFlag},
					{id:"还款计划表",text:"还款计划表",url:"/aft/conLoanChange/loanChange_repayplan_xw.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
		//menudata[menudata.length]={id:"还款计划表",text:"还款计划表",url:"/aft/conLoanChange/loanChange_repayplan_xw.jsp?changeId="+changeId+"&proFlag="+proFlag}
	}
	
	if("12"==loanChangeType){//信用证修改
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_xyz_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("13"==loanChangeType){//保函修改
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_bh_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("14"==loanChangeType){//停息、终止停息
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_txzztx_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	
	
	/*
 	if(isSmall == "0" && changeType != "04" && changeType != "11" && changeType != "15" && changeType != "17" && changeType != "18") {
		menudata[menudata.length]={id:"协办客户经理",text:"协办客户经理",url:"/biz/biz_info/biz_advice.jsp?applyId="+changeId+"&proFlag="+proFlag}
	}
	 */
	//意见提交
	if("-1"!=proFlag){
		menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("changeId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2&loanChangeType="+loanChangeType};
	}
	
	var tree = nui.get("tree1"); 
	tree.loadData(menudata);
	nodeclick({"node":menudata[0].children[0]}); 
	
	//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='意见') {
			 var ret = checkBeforeSub();
			if(ret == "1"){
				return;
			} 
			
			if("1" == proFlag){
				if(isSmall == "0" && changeType != "04" && changeType != "11") {
				
					var json = nui.encode({partyId:partyId,changeId:changeId});
					$.ajax({
			            url: "com.bos.aft.common.getBiz.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            async:false,
			            success: function (mydata) {  
			            	o = nui.decode(mydata);
			            	termChangeWay = o.tbConLoanChange.termChangeWay;
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                    alert(jqXHR.responseText);
			            }
			        }); 
				
					//alert("=====================" + termChangeWay);
					//点击意见时将授权参数传到流程区域
					var json = nui.encode({"applyId":applyId,"processInstId":processInstId,"changeId":changeId,"termChangeWay":termChangeWay,"loanChangeType":loanChangeType});
			     	$.ajax({
			            url: "com.bos.aft.conLoanChange.saveBizInfoToProAft.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: true,
			            async: false,
			            contentType:'text/json',
			            success: function (text) {
			            }
			        });
				}
				if(isSmall == "1" && changeType != "03" && changeType != "04" && changeType != "11") {
					//alert("=====================" + tbBizAmountApprove.creditAmount);
					//点击意见时将授权参数传到流程区域
					var json = nui.encode({"tbBizAmountApprove":tbBizAmountApprove,"processInstId":processInstId});
			     	$.ajax({
			            url: "com.bos.aft.conLoanChange.saveBizInfoToProAftSmall.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: true,
			            contentType:'text/json',
			            success: function (text) {
			            }
			        });
				}
			}
			
		} 
		if (e.node['id']=='ecmadd') {
			//查询客户num
			var json = nui.encode({"partyId":o.tbBizApply.partyId});
		    $.ajax({
		        url: "com.bos.biz.pub.GetForCsm.getCustByPartyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.map.tbBizApply.bizNum+'&csmNum='+mydata.party.partyNum+'&partyTypeCd='+mydata.party.partyTypeCd+'&image=apply';
					if(proFlag=="0"){
						//ecmurl = nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.map.tbBizApply.bizNum+'&csmNum='+mydata.party.partyNum+'&partyTypeCd='+mydata.party.partyTypeCd+'&view=1';
					}
		        	nui.open({
		                url:ecmurl,
		                title: "查看影像信息", 
		                width: 1200,
		        		height: 600,
		        		state:"max",
		                allowResize:true,
		        		showMaxButton: true,
		                onload: function () {
		                },
		                ondestroy: function (action) {
		                }
	            	})
		        }
					
			})
			return;
		}
		if (e.node['id']=='ecmview') {
			//查询客户num
			var json = nui.encode({"partyId":o.tbBizApply.partyId});
		    $.ajax({
		        url: "com.bos.biz.pub.GetForCsm.getCustByPartyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	//跳往信息平台页面
		        	nui.open({
		                url: nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.map.tbBizApply.bizNum+'&csmNum='+mydata.party.partyNum+'&partyTypeCd='+mydata.party.partyTypeCd+'&view=1',
		                title: "查看影像信息", 
		                width: 1200,
		        		height: 600,
		        		state:"max",
		                allowResize:true,
		        		showMaxButton: true,
		                onload: function () {
		                },
		                ondestroy: function (action) {
		                }
	            	})
		        }
					
			})
			return;
		}
		if (e.node['id']=='ecmprint') {
			ecm('print');
			return;
		}
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
		return;
	}
	
	function ecm(op) {
		var json = nui.encode({"op":op,"custnum":"12345"});
        $.ajax({
            url: "com.bos.csm.corp.customerinfo.ecm.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                window.open(o.url);
            }
        });
	}
	
	//提交前校验数据
	function checkBeforeSub(){
		var json = {"changeId":changeId};
		msg = exeRule("RCHA_0001","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return "1";
   	    }
   	    return "0";
	}
	
	function getCustmer(){
		toGoCustDetail(partyId);
	}
	
	function getBiz(){
		bizView3231(bizNum);
	}
	
	function getCon(){
		bizView3231(conNum);
	}
	
	function getLoan(){
		bizView3231(loanNum);
	}
	
	//批复信息
	/* function getApprove(){
		nui.open({
            url: nui.context+"/biz/biz_info/biz_tree.jsp?applyId="+applyId+"&processInstId=0&proFlag=-1",
            showMaxButton: true,
            title: "批复信息",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            },
            ondestroy: function (action) {
            }
  	 	 });
	} */
</script>
</body>
</html>