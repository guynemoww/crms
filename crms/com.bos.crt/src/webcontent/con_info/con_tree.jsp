<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 16:08:10
  - Description:
-->
<head>
<title>合同树菜单</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="合同签约" region="west"  width="240" class="sub-sidebar" allowResize="true" >
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>

	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
				<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
					<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
					<label></label><a id="approveInfo" href="#"  onclick="getApprove()" >批复信息</a> 
					<label></label><a id="extensionInfo" href="#"  onclick="getSummary()" style="display:none">借据信息</a> 
					<label></label><a id="contractInfo" href="#"  onclick="getContract()"style="display:none">合同信息</a>
				</div>
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	//git.mask();
	var o;
	var menudata;
	var tree = nui.get("tree");
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改
	var contractId ="<%=request.getParameter("contractId") %>"; //协议申请ID
	var amountDetailId ="<%=request.getParameter("amountDetailId") %>"; //业务申请ID
	var contractType ="<%=request.getParameter("contractType") %>"; //合同类型	01：综合授信协议 。02：业务合同
	var jspName = "1";//合同详细信息页面
	var tableName = "tb_con_ldzj";
	var partyId = "";
	var ecifPartyNum = "";// ecif客户号
	var applyId = "";
	var conSrc="2";//区分是单笔业务还是综合授信业务，单笔为1，综合为2
	var syndicatedObjectCd = "100";
	//根据contractId查询业务信息
	var json = nui.encode({"contractId":"<%=request.getParameter("contractId") %>","contractType":"<%=request.getParameter("contractType") %>"});
	//先将品种对应的合同明细页面取出来
	 $.ajax({
        url: "com.bos.conInfo.conContractInfo.getDetailJspByContractId.biz.ext",
        type: 'POST',
        data: json,
        async: false,
        cache: false,
        contentType:'text/json',
        success: function (mydata) {
        	jspName = mydata.jspAddress;
        	productType = mydata.productType;
        	tableName = mydata.tableName;
        	cycleIndCon=mydata.cycleIndCon;
        	//-------------
        	/* jspName = "biz/biz_product_detail/gs/product_ckxyzyh.jsp";
        	productType = "01007001";
        	tableName="tb_biz_ckxyzyh_apply"; */
		}
    });
	
    $.ajax({
        url: "com.bos.conInfo.conInfoSxxy.getConInfoByContarctId.biz.ext",
        type: 'POST',
        data: json,
        contentType:'text/json',
        cache: false,
        success: function (mydata) {
        	o = nui.decode(mydata);
 			//公司客户
 			partyId = o.conInfo.partyId;
 			var bizType = o.tbBizApprove.bizType;
 			var contypename = "综合授信项下主合同";
 			contractType = o.contractType;
 			amountDetailId = o.conInfo.amountDetailId;
 			applyId = o.tbBizApprove.applyId;
 			partyId = o.party.partyId;
			ecifPartyNum = o.party.ecifPartyNum;// ecif客户号
 			proFlag_s=o.proFlag1;
 			//如果是银团，行内银团合同只有利率信息
 			var isBankTeam = o.tbBizApprove.isBankTeamLoan;
 			if(isBankTeam!=null && isBankTeam !=''){
 				if(isBankTeam == '1'){
 					var json2 = nui.encode({"applyId":applyId});
			        $.ajax({
			            url: "com.bos.bizInfo.bizInfo.getBankTeamStruct.biz.ext",
			            type: 'POST',
			            data: json2,
			            cache: false,
        				async: false,
			            contentType:'text/json',
			            success: function (text) {
			            	var o = nui.decode(text);
			            	syndicatedObjectCd = o.bankTs.syndicatedObjectCd;
			            	if(syndicatedObjectCd == '02'){
			            		contractType = "03";
			            	}
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                nui.alert(jqXHR.responseText);
			            }
			        });
 				
 				}
 				
 			}
 			
 			//如果合同信息里协议id为空，则是单笔申请的业务；
 			if(o.conInfo.xyId==null || o.conInfo.xyId==''){
 				conSrc="1"
 				contypename = "单笔业务项下主合同"
 			}
 			if(bizType=='01'||bizType=='02'||bizType=='05'){
 				if("01"==contractType){
	 				menudata = [
						{id:"综合授信协议", text:"综合授信协议", //expanded:true,  
							children:[
								{id:"基本信息",text:"基本信息",url:"/crt/con_info/con_info_xy.jsp?contractId="+contractId+"&proFlag="+proFlag},
								{id:"明细信息",text:"明细信息",url:"/crt/con_info/con_detail_xy.jsp?contractId="+contractId+"&proFlag=0"},
								{id:"附属信息",text:"附属信息",url:"/crt/con_info/con_fs_xy.jsp?contractId="+contractId+"&proFlag="+proFlag}
						]}
					];
					menudata[menudata.length]={id:"从合同信息", text:"从合同信息", 
						children:[
							{id:"抵押合同",text:"抵押合同",url:"/crt/con_grt/con_dy_list.jsp?contractId="+contractId+"&subcontractTypeCd=01&applyId="+o.tbBizApprove.applyId+"&contractType=01&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"质押合同",text:"质押合同",url:"/crt/con_grt/con_zy_list.jsp?contractId="+contractId+"&subcontractTypeCd=02&applyId="+o.tbBizApprove.applyId+"&contractType=01&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"保证合同",text:"保证合同",url:"/crt/con_grt/con_bzr_list.jsp?contractId="+contractId+"&subcontractTypeCd=04&applyId="+o.tbBizApprove.applyId+"&contractType=01&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag}
							<%--{id:"保证金协议",text:"关联保证金",url:"/crt/con_grt/con_bzj_list.jsp?contractId="+contractId+"&subcontractTypeCd=03&partyId="+o.tbBizApprove.partyId+"&contractType=01&applyId="+o.tbBizApprove.applyId}--%>
					]}
				}
			if("02"==contractType){
				// 贴现
				if("01006001"==productType||"01006002"==productType	|| "01006010"==productType //村镇银行贴现产品
				){
		 			menudata = [
		 				{id:contypename, text:contypename, //expanded:true,  
							children:[
								{id:"主合同基本信息",text:"主合同基本信息",url:"/crt/con_info/con_info_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType+"&conSrc="+conSrc},
								{id:"主合同明细信息",text:"主合同明细信息",url:"/crt/con_info/con_detail_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&jspName="+jspName+"&productType="+productType+"&amountDetailId="+amountDetailId+"&cycleIndCon="+o.conInfo.cycleIndCon+"&syndicatedObjectCd="+syndicatedObjectCd},
								{id:"票据明细信息",text:"票据明细信息",url:"/biz/biz_product_detail/pjxx/biz_tx_all.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum+"&amountDetailId="+amountDetailId+"&contractNum="+o.conInfo.contractNum+"&contractId="+o.conInfo.contractId}
							]
						}
					];
				}
				// 银承
				else if("01008001"==productType||"01008002"==productType || "01008010"==productType){
		 			menudata = [
		 				{id:contypename, text:contypename, //expanded:true,  
							children:[
								{id:"主合同基本信息",text:"主合同基本信息",url:"/crt/con_info/con_info_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType+"&conSrc="+conSrc},
								{id:"主合同明细信息",text:"主合同明细信息",url:"/crt/con_info/con_detail_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&jspName="+jspName+"&productType="+productType+"&amountDetailId="+amountDetailId+"&cycleIndCon="+o.conInfo.cycleIndCon+"&syndicatedObjectCd="+syndicatedObjectCd},
								{id:"票据明细信息",text:"票据明细信息",url:"/biz/biz_product_detail/pjxx/biz_pj_all.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum+"&amountDetailId="+amountDetailId+"&contractNum="+o.conInfo.contractNum+"&contractId="+o.conInfo.contractId}
							]
						}
					];
				}
				else{
					menudata = [
						{id:contypename, text:contypename, //expanded:true,  
							children:[
								{id:"主合同基本信息",text:"主合同基本信息",url:"/crt/con_info/con_info_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType+"&conSrc="+conSrc},
								{id:"主合同明细信息",text:"主合同明细信息",url:"/crt/con_info/con_detail_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&jspName="+jspName+"&productType="+productType+"&amountDetailId="+amountDetailId+"&cycleIndCon="+o.conInfo.cycleIndCon+"&syndicatedObjectCd="+syndicatedObjectCd}
							]
						}
					];
				}
				menudata[menudata.length]={id:"从合同信息", text:"从合同信息", 
					children:[
						{id:"抵押合同",text:"抵押合同",url:"/crt/con_grt/con_dy_list.jsp?contractId="+contractId+"&subcontractTypeCd=01&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
						{id:"质押合同",text:"质押合同",url:"/crt/con_grt/con_zy_list.jsp?contractId="+contractId+"&subcontractTypeCd=02&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
						{id:"保证合同",text:"保证合同",url:"/crt/con_grt/con_bzr_list.jsp?contractId="+contractId+"&subcontractTypeCd=04&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
						{id:"保证金协议",text:"关联保证金",url:"/crt/con_grt/con_bzj_list.jsp?contractId="+contractId+"&subcontractTypeCd=03&partyId="+o.tbBizApprove.partyId+"&contractType=02&applyId="+o.tbBizApprove.applyId+"&proFlag="+proFlag+"&proFlag_s="+proFlag_s}
					]
				}
			}
				if("03"==contractType){
	 				menudata = [
						{id:contypename, text:contypename, //expanded:true,  
							children:[
								{id:"主合同基本信息",text:"主合同基本信息",url:"/crt/con_info/con_info_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType+"&conSrc="+conSrc},
								{id:"主合同明细信息",text:"主合同明细信息",url:"/crt/con_info/con_detail_ht.jsp?contractId="+contractId+"&proFlag="+proFlag+"&jspName="+jspName+"&productType="+productType+"&amountDetailId="+amountDetailId+"&cycleIndCon="+o.conInfo.cycleIndCon+"&syndicatedObjectCd="+syndicatedObjectCd}
						]}
					];
					menudata[menudata.length]={id:"从合同信息", text:"从合同信息", 
						children:[
							{id:"抵押合同",text:"抵押合同",url:"/crt/con_grt/con_dy_list.jsp?contractId="+contractId+"&subcontractTypeCd=01&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"质押合同",text:"质押合同",url:"/crt/con_grt/con_zy_list.jsp?contractId="+contractId+"&subcontractTypeCd=02&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"保证合同",text:"保证合同",url:"/crt/con_grt/con_bzr_list.jsp?contractId="+contractId+"&subcontractTypeCd=04&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"保证金协议",text:"关联保证金",url:"/crt/con_grt/con_bzj_list.jsp?contractId="+contractId+"&subcontractTypeCd=03&partyId="+o.tbBizApprove.partyId+"&contractType=02&applyId="+o.tbBizApprove.applyId+"&proFlag="+proFlag+"&proFlag_s="+proFlag_s}
					]}
				}
 			}else if(bizType=='04'||bizType=='06'){//小微
 				if("02002"==productType.substr(0,5)||"02005003"==productType||"02005001"==productType){//房贷
						menudata = [
						{id:"单笔业务项下主合同", text:"单笔业务项下主合同", 
							children:[
								{id:"主合同基本信息",text:"主合同基本信息",url:"/crt/con_info/con_info_ht_xw.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType},
								{id:"主合同明细信息",text:"主合同明细信息",url:"/crt/con_info/con_detail_ht_xw_house.jsp?contractId="+contractId+"&proFlag="+proFlag+"&amountDetailId="+amountDetailId+"&productType="+productType}
						]}
					];
				}else{
					if(cycleIndCon=="1"){//循环类合同
						menudata = [
						{id:"单笔业务项下主合同", text:"单笔业务项下主合同",
							children:[
								{id:"主合同基本信息",text:"主合同基本信息",url:"/crt/con_info/con_info_ht_xw.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType},
								{id:"主合同明细信息",text:"主合同明细信息",url:"/crt/con_info/con_detail_ht_xw_cycle.jsp?contractId="+contractId+"&proFlag="+proFlag+"&amountDetailId="+amountDetailId+"&productType="+productType}
						]}
					];
					}else{
						menudata = [
						{id:"单笔业务项下主合同", text:"单笔业务项下主合同", //expanded:true,  
							children:[
								{id:"主合同基本信息",text:"主合同基本信息",url:"/crt/con_info/con_info_ht_xw.jsp?contractId="+contractId+"&proFlag="+proFlag+"&productType="+productType},
								{id:"主合同明细信息",text:"主合同明细信息",url:"/crt/con_info/con_detail_ht_xw_uncycle.jsp?contractId="+contractId+"&proFlag="+proFlag+"&amountDetailId="+amountDetailId+"&productType="+productType}
						]}
					];
					}
				}
					menudata[menudata.length]={id:"从合同信息", text:"从合同信息", 
						children:[
							{id:"抵押合同",text:"抵押合同",url:"/crt/con_grt/con_dy_list.jsp?contractId="+contractId+"&subcontractTypeCd=01&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"质押合同",text:"质押合同",url:"/crt/con_grt/con_zy_list.jsp?contractId="+contractId+"&subcontractTypeCd=02&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"保证合同",text:"保证合同",url:"/crt/con_grt/con_bzr_list.jsp?contractId="+contractId+"&subcontractTypeCd=04&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag},
							{id:"保证金协议",text:"关联保证金",url:"/crt/con_grt/con_bzj_list.jsp?contractId="+contractId+"&subcontractTypeCd=03&applyId="+o.tbBizApprove.applyId+"&contractType=02&partyId="+o.tbBizApprove.partyId+"&proFlag="+proFlag+"&proFlag_s="+proFlag_s}
					]}
 			}else{
 				//这种数据基本为数据迁移问题
 				alert("错误的业务性质TB_BIZ_APPROVE.BIZ_TYPE[XD_SXYW0002->"+bizType+"]");
 				return ;
 			}
			
			//公用信息
			if(contractType!='01'){
				menudata[menudata.length]={id:"账户信息",text:"账户信息",url:"/crt/accountInfo/account_list.jsp?contractId=<%=request.getParameter("contractId") %>&proFlag="+proFlag};
			}
			menudata[menudata.length]={id:"ecm",text:"影像信息",url:"/xx"};
			<%--menudata[menudata.length]={id:"upload",text:"相关文档",url:"/biz/biz_info/pro_biz_upload.jsp?applyId=<%=request.getParameter("applyId") %>"};--%>
			//批复意见
			<%-- menudata[menudata.length]={id:"批复意见",text:"批复意见",url:"/crt/accountInfo/account_ideabook.jsp?contractId=<%=request.getParameter("contractId") %>&proFlag="+proFlag}; --%>
			//意见提交
			if("-1"!=proFlag){
				menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("contractId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2&amountDetailId="+amountDetailId+"&productType="+productType+"&contractId="+contractId+"&contractNum="+o.conInfo.contractNum};//contractId
			}
			git.unmask();
			tree.loadData(menudata);
			nodeclick({"node":menudata[0].children[0]});
 		}
 	})
 	
 		//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='意见') {
			if("1" == proFlag){
				//点击意见时将授权参数传到流程区域
				var json = nui.encode({"contractId":contractId,"processInstId":"<%=request.getParameter("processInstId")%>"});
			     	$.ajax({
			            url: "com.bos.conApply.conApply.saveConInfoToPro.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            cache: false,
			            success: function (text) {
			            }
			        });
		    }
		}
		if(e.node['id']=='主合同明细信息'){
			 //合同基本信息保存校验
			var json = {"contractId":contractId};
	   	    msg = exeRule("RCON_0003","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
	   	    }
		}
		if (e.node['id']=='ecm') {
			//查询客户num
			var json = nui.encode({"contractId":"<%=request.getParameter("contractId") %>"});
		    $.ajax({
		        url: "com.bos.conInfo.conInfoSxxy.getConInfoByContarctId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	o = nui.decode(mydata);
		        	if(""==o.conInfo.ifDataMove||null==o.conInfo.ifDataMove){
		        		ifDataMove = "0";
		        	}else{
		        		ifDataMove = o.conInfo.ifDataMove;
		        	}
		        	if(o.party.partyTypeCd == "02") {//个贷
				    	flowModuleType = "22,223";
				    }else {
				    	flowModuleType = "15,154,156";
				    }
		        	//跳往信息平台页面

						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.conInfo.contractNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					if(proFlag=="0"||proFlag=="-1"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.conInfo.contractNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=1&flowModuleType="+flowModuleType;
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
	
	function getCustmer(){
		toGoCustDetail(partyId);
	}
	//批复信息
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