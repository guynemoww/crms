<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-14 20:57:45
  - Description:
-->
<head>
<title>放款撤销</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
</head>
<body>

<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="放款撤销" region="west"  width="240" class="sub-sidebar" allowResize="true">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>

	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
		
				<div class="nui-dynpanel" columns="9" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
					<a href="#"  onclick="getCustmer()">客户信息</a>
					<label></label><a id="approveInfo" href="#"  onclick="getApprove()">批复信息</a> 
					<label></label><a id="contractInfo" href="#"  onclick="getContract()">合同信息</a>
				</div>
		
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var menudata;
	var proFlag ="<%=request.getParameter("proFlag") %>";
	var loanId ="<%=request.getParameter("loanId") %>"; //放款ID
	var processInstId = "<%=request.getParameter("processInstId")%>";
	var json1 = nui.encode({"loanId":loanId})
	var addr = "pay/payout_info/loan_info.jsp";
	var productType = '01001001';
	var repayType = '0100';
	var contractId = '';
	var loanstatus;
	var biznum="<%=request.getParameter("bizNum") %>";
	var partyId = "";
	var trusToPayFlg = '0';
	
	<%
		UserObject user = (UserObject)session.getAttribute("userObject");
	%>
	
	//根据loanid查出productType
	$.ajax({
        url: "com.bos.payInfo.PayInfo.getProductType.biz.ext",
        type: 'POST',
        data: json1,
        async: false,
        cache: false,
        contentType:'text/json',
        success: function (mydata) {
        	productType = mydata.tbLoanInfo.productType;
        	if(mydata.tbLoanInfo.repayType!=null &&mydata.tbLoanInfo.repayType!=''){
        		repayType = mydata.tbLoanInfo.repayType;
        	}
        	partyId = mydata.tbLoanInfo.partyId;
        	contractId = mydata.tbLoanInfo.contractId;
        	loanstatus=mydata.tbLoanInfo.loanStatus;
        	trusToPayFlg =  mydata.tbLoanInfo.trusToPayFlg;
        	if(productType=='01007008'||productType=='01009001'|| productType=='01009002' || productType=='01009010' ||
        		productType=='01011001'||productType=='01012001'){//保函、信贷证明、项目贷款承诺函
				addr = "pay/payout_info/bh_info.jsp";
			}else if(productType=='01008001'|| productType=='01008002' || productType=='01008010'){//承兑汇票
				addr = "pay/payout_info/cdhp_info.jsp";
			}else if(productType=='01006001'||productType=='01006002'
			||productType=='01006010' //村镇银行贴现产品
			){//贴现
				addr = "pay/payout_info/tx_info.jsp";
			}else if(productType == '01007001'||productType == '01007002'||productType == '01007003'||
				productType == '01007004'||productType == '01007005'||productType == '01007006'
				||productType=='01007009'||productType=='01007010'||productType=='01007011'
				||productType=='01007012'||productType=='01007013'||productType=='01007014'
				) {//押汇类
				addr = "pay/payout_info/yh_info.jsp";
			}else if(productType == '01010001'||productType == '01007007') {//信用证
				addr = "pay/payout_info/xyz_info.jsp";
			}else{
				addr = "pay/payout_info/loan_info.jsp";
			}
		}
    });
	
	if(startWith(productType,'0200')==1||startWith(productType,'0300')==1){//个贷
		if(repayType.indexOf('14')!=-1){//有还款计划
			if(trusToPayFlg == '1'){//受托支付
				menudata = [
					{id:"放款申请信息", text:"放款申请信息",
						children:[
							{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
							{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"还本计划表",text:"还本计划表",url:"/pay/repay_plan/repay_plan_list.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"贴息信息",text:"贴息信息",url:"/pay/payout_info/pay_xw_tx.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"受托支付信息",text:"受托支付信息",url:"/pay/payout_info/loan_info_stzf.jsp?loanId="+loanId+"&proFlag="+proFlag+"&productType="+productType}
						]
					}
				];
			}else{//自主支付
				menudata = [
					{id:"放款申请信息", text:"放款申请信息",
						children:[
							{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
							{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"还本计划表",text:"还本计划表",url:"/pay/repay_plan/repay_plan_list.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"贴息信息",text:"贴息信息",url:"/pay/payout_info/pay_xw_tx.jsp?loanId="+loanId+"&proFlag="+proFlag}
						]
					}
				];
			}
			
		}else{//无还款计划
			if(trusToPayFlg == '1'){//受托支付
				menudata = [
					{id:"放款申请信息", text:"放款申请信息",
						children:[
							{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
							{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"贴息信息",text:"贴息信息",url:"/pay/payout_info/pay_xw_tx.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"受托支付信息",text:"受托支付信息",url:"/pay/payout_info/loan_info_stzf.jsp?loanId="+loanId+"&proFlag="+proFlag+"&productType="+productType}
						]
					}
				];
			}else{//自主支付
				menudata = [
					{id:"放款申请信息", text:"放款申请信息",
						children:[
							{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
							{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag},
							{id:"贴息信息",text:"贴息信息",url:"/pay/payout_info/pay_xw_tx.jsp?loanId="+loanId+"&proFlag="+proFlag}
						]
					}
				];
			}
			
		}
		
	}else if('01006001'==productType||'01006002'==productType||'01006010'==productType){
		menudata = [
			{id:"放款申请信息", text:"放款申请信息",
				children:[
					{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
					{id:"汇票明细信息",text:"汇票明细信息",url:"/pay/pjxx/pay_tx.jsp?loanId="+loanId+"&proFlag="+proFlag},
					{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag}
				]
			}
		];
	}else if('01008001'==productType||'01008002'==productType ||'01008010'==productType){
		menudata = [
			{id:"放款申请信息", text:"放款申请信息",
				children:[
					{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
					{id:"汇票明细信息",text:"汇票明细信息",url:"/pay/pjxx/pay_pj.jsp?loanId="+loanId+"&proFlag="+proFlag},
					{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag}
				]
			}
		];
	}else{
		if(repayType.indexOf('14')!=-1){
			menudata = [
				{id:"放款申请信息", text:"放款申请信息",
					children:[
						{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
						{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag},
						{id:"还本计划表",text:"还本计划表",url:"/pay/repay_plan/repay_plan_list.jsp?loanId="+loanId+"&proFlag="+proFlag}
					]
				}
			];
		}else{
			menudata = [
				{id:"放款申请信息", text:"放款申请信息",
					children:[
						{id:"放款信息",text:"放款信息",url:"/pay/payout_info/pay_info.jsp?loanId="+loanId+"&proFlag="+proFlag+"&addr="+addr+"&processInstId="+processInstId},
						{id:"账户信息",text:"账户信息",url:"/pay/account_info/account_list.jsp?loanId="+loanId+"&proFlag="+proFlag}
					]
				}
			];
		}
	}
	if(loanstatus=="03"){
		menudata[menudata.length]={id:"hkls",text:"借据流水信息",url:"/pay/payout_apply/pay_hkls_list.jsp?biznum="+biznum};
	}
	menudata[menudata.length]={id:"ecm",text:"影像信息",url:"/xx"};
	
	
	if("-1"!=proFlag){
		menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("loanId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
	}else{
		var json = nui.encode({"item":{"processInstId":processInstId},"sqlName":"com.bos.bps.dataset.bpmNamingSql.getWorkItems"});
		var userName = "<%=user.getUserName() %>";//登录人名
		var userId = "<%=user.getUserId() %>";//登录人ID
		var code;
		var name;
		//alert(json);
		//获取流程记录
		$.ajax({
			url:'com.bos.csm.pub.ibatis.getItem.biz.ext',
			type:'POST',
			data:json,
			cache:false,
			contentType:'text/json',
			success:function(text){
				if(text){
					var data = text.items;
					if(data.length == 1){
						code = nui.decode(nui.encode(data[0].POSTCD));
						userNum = nui.decode(nui.encode(data[0].USERNUM));
						if(code=='P1001'&&userId==userNum){
							menudata[menudata.length]={id:"jkpz",text:"打印借款凭证",url:"/xx"};
						}
					}if(data.length == 2){
						userNum1 = nui.decode(nui.encode(data[0].USERNUM));
						userNum2 = nui.decode(nui.encode(data[1].USERNUM));
						if(userId==userNum1||userId==userNum2){
							menudata[menudata.length]={id:"jkpz",text:"打印借款凭证",url:"/xx"};
						}
					}
				}
			},error: function (jqXHR, textStatus, errorThrown){
	            nui.alert(jqXHR.responseText);
	        } 
		});
	}
	var tree = nui.get("tree");
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
	if (e.node['id']=='ecm') {
			//查询客户num
			var json = nui.encode({"loanId":"<%=request.getParameter("loanId") %>"});
		    $.ajax({
		        url: "com.bos.payInfo.PayInfo.getContractByLoanId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	o = nui.decode(mydata);
		        	if(""==o.loanInfo.ifDataMove||null==o.loanInfo.ifDataMove){
		        		ifDataMove = "0";
		        	}else{
		        		ifDataMove = o.loanInfo.ifDataMove;
		        	}
		        	if(o.party.partyTypeCd == "02") {//个贷
				    	flowModuleType = "22,224,223";
				    }else {
				    	flowModuleType = "15,155,157";
				    }
		        	//跳往信息平台页面

						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.contactInfo.contractNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					if(proFlag=="0"||proFlag=="-1"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.contactInfo.contractNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=1&flowModuleType="+flowModuleType;
					}
		        	nui.open({
		                url:ecmurl,
		                title: "查看影像信息", 
		                width: 1200,
		        		height: 600,
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
		if (e.node['id']=='汇票明细信息') {
			var json = {"loanId":loanId};
	   	   //放款基本信息保存校验
	   	    msg = exeRule("RLON_0002","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert("放款信息未保存！");
		   		return "1";
	   	    }
		}
		if (e.node['id']=='jkpz'){
			var json = nui.encode({"contractId":contractId,"loanId":loanId});
	        $.ajax({
	            url: "com.bos.pay.LoanSummary.loanPZ.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.swfPath){
		        		nui.open({
							url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
							title: "借款凭证信息预览", width: 1000, height: 600,
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
	        return;
		}
		if (e.node['id']=='还款计划表') {
			var json = {"loanId":loanId};
	   	   //合同基本信息保存校验
	   	    msg = exeRule("RLON_0002","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return "1";
	   	    }
		}
		if (e.node['id']=='贴息信息') {
			var json = {"loanId":loanId};
	   	   //合同基本信息保存校验
	   	    msg = exeRule("RLON_0002","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return "1";
	   	    }
	   	   //申请未添加贴息信息
	   	    msg = exeRule("RLON_0004","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return "1";
	   	    }
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
	//批复信息
	function getApprove(){
		var json = nui.encode({"loanId":loanId});
		$.ajax({
            url: "com.bos.payInfo.PayInfo.getApplyIdByLoanId.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
        		var applyId = mydata.applyId;
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
        });
	}
	//合同
	function getContract(){
        nui.open({
            url: nui.context+"/crt/con_info/con_tree.jsp?contractId="+contractId+"&contractType=02&proFlag=-1",
            showMaxButton: true,
            title: "合同信息",
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
	
	function getCustmer(){
		toGoCustDetail(partyId);
	}
	 function checkBeforeSub(){
	 	var json1 = {"loanId":loanId};
   	    var msg = exeRule("RLON_0030","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		 git.unmask();
	   		return "1";
   	    } 
   	    var msg1 = exeRule("RLON_0032","1",json1);
   	    if(null != msg1 && '' != msg1){
	   		nui.alert(msg1);
	   		 git.unmask();
	   		return "1";
   	    } 
	 }
</script>
</body>
</html>