<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-11-26
  - Description:
-->
<head>
<title>贷后变更</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="贷后变更" region="west" width="220" class="sub-sidebar" allowResize="false">
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
	<%-- var processInstId ="<%=request.getParameter("processInstId") %>"; //业务申请ID
	var proFlag ="<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮 --%>
	var party;   
	var applyId = "";
	var bizNum = "";
	var conNum = "";
	var loanNum = "";
	var menudata; 
	
	var qote = "2";
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
            	termChangeWay = o.tbConLoanChange.termChangeWay;
            	tbBizAmountApprove = o.tbBizAmountApprove;
            	repayType = o.tbLoanInfo.repayType;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        }); 
    
    flowModuleType = "18";
    
    /* if(proFlag == "1") {
    	qote = "2";
    } */
        
    if(qote=="1"){
    	view = [					     
			{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+changeNum+"&csmNum="+party.partyNum+"&partyTypeCd=01&flowModuleType="+flowModuleType+"&view="+qote}
		]
	}else{
		view = [
		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+changeNum+"&csmNum="+party.partyNum+"&partyTypeCd=01&flowModuleType="+flowModuleType}
		]
	} 
        
	if("0"==isSmall && "15"==loanChangeType){//提前还款
		menudata = [
			{id:"贷后变更信息", text:"贷后变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_earlyrepay_apply_accountrepay.jsp?changeId="+changeId}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
	}
	if("1"==isSmall && "15"==loanChangeType){//小贷提前还款
		if("1400"==repayType || "1410"==repayType) {
			menudata = [
				{id:"贷后变更信息", text:"贷后变更信息",
					children:[
						{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_earlyrepay_apply_accountrepay_xw.jsp?changeId="+changeId},
						{id:"还款计划表",text:"还款计划表",url:"/aft/conLoanChange/accountrepay_xw.jsp?changeId="+changeId}
					]
				},
				{id:"影像资料", text:"影像资料", children:view}
			];
		}else {
			menudata = [
				{id:"贷后变更信息", text:"贷后变更信息",
					children:[
						{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/loanChange_earlyrepay_apply_accountrepay.jsp?changeId="+changeId}
					]
				},
				{id:"影像资料", text:"影像资料", children:view}
			];
		}
		
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

</script>
</body>
</html>