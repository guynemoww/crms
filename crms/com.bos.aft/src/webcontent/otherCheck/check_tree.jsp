<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-07-08 
  - Description:
-->
<head>
<title>贷后检查</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="贷后检查" region="west" width="220" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:100%;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="wborder:0;padding-left:5px;padding-top:5px;width: 60%;">
			
				<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" id="e1">
					<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
					 <label></label><a id="approveInfo" href="#"  onclick="getApprove()">批复信息</a> 
					<label></label><a id="contractInfo" href="#"  onclick="getCon()">合同信息</a>
					<label></label><a id="extensionInfo" href="#"  onclick="getLoan()">借据信息</a> 
					<!--<label></label><a id="contractInfo" href="#"  onclick="getContract()">合同信息</a>
					<label></label><a id="approveInfo" href="#"  onclick="getBiz()">业务信息</a> 
					<label></label><a id="extensionInfo" href="#"  onclick="getSummary()">借据信息</a>  -->
				</div>
				
				<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" id="p1">
					<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
					<label></label><a id="approveInfo" href="#"  onclick="getBiz()">业务信息</a> 
					<label></label><a id="contractInfo" href="#"  onclick="getCon()">合同信息</a>
				</div>
			   
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;margin-top: 10px;" refreshOnClick="true"></div>
		    </div>
		</div>
<script type="text/javascript">
	nui.parse();
	var node=<%=request.getParameter("node") %>;
	var partyId ="<%=request.getParameter("partyId") %>"; 
	var checkId ="<%=request.getParameter("checkId") %>"; //检查主键
	var checkType ="<%=request.getParameter("checkType") %>"; 
	var processInstId ="<%=request.getParameter("processInstId") %>"; //业务申请ID
	var proFlag ="<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮  
	var party;   
	var menudata; 
	var bizNum = "";
	var conNum = "";
	var loanNum = "";
	var applyId = "";
	
	var qote = "1";
	var ismove = "0";//是否移迁数据  1是 0 否
	//var flowModuleType="115,1151,1152";//影像模板节点类型（参考下面的映射表，多个节点以英文“，”分隔）
	var flowModuleType = "16";
	var view; //查看标志 1 查看 2 上传
	var checkNum = "";
	
	if("e"==checkType){
		$("#e1").css("display","block");
		$("#p1").css("display","none");
	}else {
		$("#e1").css("display","none");
		$("#p1").css("display","block");
	}
	
	var json = nui.encode({partyId:partyId,checkId:checkId,flag:checkType});
	 /* $.ajax({
            url: "com.bos.aft.common.getParty.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {     	
            	party = mydata.party;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });  */
        
        $.ajax({
            url: "com.bos.aft.common.getBizForCheck.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) { 
            	o = nui.decode(mydata);
            	if("p"==checkType) {
            		partyId = o.tbAftPointCheck.partyId; 
	            	bizNum = o.tbBizApprove.bizNum;
	            	applyId = o.tbBizApprove.applyId;
	            	conNum = o.tbConContractInfo.contractNum;
	            	checkNum = o.tbAftPointCheck.checkNum;
            		party = o.tbCsmParty;
            	}else if("e"==checkType) {
	            	partyId = o.tbAftExpireCheck.partyId; 
	            	bizNum = o.tbBizApprove.bizNum;
	            	conNum = o.tbConContractInfo.contractNum;
	            	applyId = o.tbBizApprove.applyId;
	            	loanNum = o.tbLoanSummary.summaryNum;
	            	checkNum = o.tbAftExpireCheck.checkNum;
            		party = o.tbCsmParty;
            	}else if("01"==checkType) {
	            	partyId = o.tbAftExpireCheck.partyId; 
	            	bizNum = o.tbBizApprove.bizNum;
	            	conNum = o.tbConContractInfo.contractNum;
	            	applyId = o.tbBizApprove.applyId;
	            	loanNum = o.tbLoanSummary.summaryNum;
	            	checkNum = o.tbAftExpireCheck.checkNum;
            		party = o.tbCsmParty;
            	}else if("02"==checkType) {
	            	partyId = o.tbAftExpireCheck.partyId; 
	            	bizNum = o.tbBizApprove.bizNum;
	            	conNum = o.tbConContractInfo.contractNum;
	            	applyId = o.tbBizApprove.applyId;
	            	loanNum = o.tbLoanSummary.summaryNum;
	            	checkNum = o.tbAftExpireCheck.checkNum;
            		party = o.tbCsmParty;
            	}
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        }); 
        
    if(proFlag == "1") {
    	qote = "2";
    }
        
    if(qote=="1"){
    	view = [					     
			{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+checkNum+"&csmNum="+party.partyNum+"&partyTypeCd=01&flowModuleType="+flowModuleType+"&view="+qote}
		]
	}else{
		view = [
		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+checkNum+"&csmNum="+party.partyNum+"&partyTypeCd=01&flowModuleType="+flowModuleType}
		]
	}
        
    if("p"==checkType){//重点检查
	    menudata = [
			{id:"重点检查信息", text:"重点检查信息",
				children:[
					{id:"检查信息",text:"检查信息",url:"/aft/otherCheck/pointCheck_apply.jsp?checkId="+checkId+"&proFlag="+proFlag}
				]
			},
			{id:"重大事件", text:"重大事件",
				children:[
					{id:"事件信息",text:"事件信息",url:"/csm/corporation/csm_impornant_event_list.jsp?partyId="+partyId+"&qote="+qote}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
    }
    if("e"==checkType){//授信到期前跟踪检查
	    menudata = [
			{id:"贷款到期前跟踪检查信息", text:"贷款到期前跟踪检查信息",
				children:[
					{id:"检查信息",text:"检查信息",url:"/aft/otherCheck/expireCheck_apply.jsp?checkId="+checkId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
    }
    
	if("01"==checkType){//授信首次检查
	    menudata = [
			{id:"首次检查信息", text:"首次检查信息",
				children:[
					{id:"检查信息",text:"检查信息",url:"/aft/firstCheck/firstCheck_apply.jsp?firstCheckId="+checkId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
    }
	if("02"==checkType){//日常检查 
	    menudata = [
			{id:"日常检查内容", text:"日常检查内容",
				children:[
					{id:"检查内容",text:"检查内容",url:"/aft/normalCheck/normalCheck_apply_track_house.jsp?normalCheckId="+checkId+"&proFlag="+proFlag}
				]
			},
			{id:"影像资料", text:"影像资料", children:view}
		];
    }
	//意见提交
	if("-1"!=proFlag){
		menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("checkId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
	}else if("01"==proFlag){
		menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("checkId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
	}else if("02"==proFlag){
		menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("checkId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
	}
	
	var tree = nui.get("tree1"); 
	tree.loadData(menudata);
	nodeclick({"node":menudata[0].children[0]}); 
	
	//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		/*  if (e.node['id']=='意见') {
			var ret = checkBeforeSub();
			if(ret == "1"){
				return;
			}
		}  */
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
		
   	    if("p"==checkType){
			var json = {"checkId":checkId};
			msg = exeRule("RAFT_0004","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return "1";
	   	    }
		}
		if("e"==checkType){
			var json = {"checkId":checkId};
			msg = exeRule("RAFT_0005","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return "1";
	   	    }
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
	
	function getApprove(){
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
	}
	
</script>
</body>
</html>