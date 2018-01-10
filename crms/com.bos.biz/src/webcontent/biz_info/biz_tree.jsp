<%@page import="com.bos.pub.ServiceModuleName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 10:30:41
  - Description:
-->
<head>
<title>业务申请树菜单</title>
<%@include file="/common/nui/common.jsp"%>
<%@include file="/crd/util/CrdUtil.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil"%>
<%@page import="com.eos.*"%>

</head>
<body>
<%
String module = "CollUrlConfig";
String group = "coll_url_server";
String ip = "ip";
String port = "port";
String ipStr = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
String portStr = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
String limitMode = ConfigurationUtil.getContributionConfig(ServiceModuleName.PUB,"risk_limit_config", "risk_limit", "limitMode");
 %>
<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="业务申请" region="west"  width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
				<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
					<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
					<label></label><a id="approveInfo" href="#"  onclick="getApprove()" style="display:none">批复信息</a> 
					<label></label><a id="extensionInfo" href="#"  onclick="getSummary()" style="display:none">借据信息</a> 
					<label></label><a id="contractInfo" href="#"  onclick="getContract()"style="display:none">合同信息</a>
				</div>
		
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:92%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	//git.mask();
	CrdUtil.setLimitMode(<%=JspUtil.getStrHaveSign(limitMode)%>);
	var o;
	var menudata;
	var tree = nui.get("tree");
	var proFlag ="<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var applyId ="<%=request.getParameter("applyId") %>"; //业务申请ID
	var processInstId ="<%=request.getParameter("processInstId") %>"; //流程ID
	var partyId = "";
	var addr = "";
	var pjAddr = "";
	var bizNum="";
	var lowRiskBizType;
	var prodType = '1';//1-对公  2-个人消费 3-个人经营
	var bizHappenType = '';//04-调整  06-借新还旧
	var productType = "";
	var examRecord = "0";//审批意见通知书 0->不显示,1->显示
	//根据applyId查询业务信息
	var json = nui.encode({"applyId":applyId,"searchProcessStatus":"true"});
    $.ajax({
        url: "com.bos.bizInfo.bizInfo.getBizInfoByApplyId.biz.ext",
        type: 'POST',
        data: json,
        contentType:'text/json',
        cache: false,
        async: false,
        success: function (mydata) {
        	o = nui.decode(mydata);
 			partyId = o.tbBizApply.partyId;
 			bizNum=o.tbBizApply.bizNum;
 			bizHappenType = o.tbBizApply.bizHappenType;
 			examRecord = o.tbBizApply.processStatus;
 			productType = o.tbBizApply.productType;
 			lowRiskBizType = o.tbBizApply.lowRiskBizType;
 			if(productType == null || productType=='' || productType=='null'){//综合或统一授信
 				productType = '1111111';
 			}
 			if(productType.indexOf('020020')!=-1||productType.indexOf('020030')!=-1||productType.indexOf('020040')!=-1||productType.indexOf('020050')!=-1){//消费--委贷展示消费类
 				addr="/biz/biz_product_detail/person/natural_family.jsp"
 				pjAddr = "/biz/biz_grt/biz_risk_warning_gr.jsp";
 				prodType = '2';
 			}else if(productType.indexOf('030010')!=-1||productType.indexOf('020010')!=-1){//经营
 				addr="/biz/biz_product_detail/person/acccustomerfinance_list.jsp"
 				pjAddr = "/biz/biz_grt/biz_risk_warning_gr.jsp";
 				prodType = '3';
 			}else{//对公
 				pjAddr = "/biz/biz_grt/biz_risk_warning.jsp";
 				prodType = '1';
 			}
 			
 			//单笔
 			if("01"==o.tbBizApply.bizType ){
 				menudata = [
					{id:"贷款结构", text:"贷款结构", //expanded:true,  
						children:[
							{id:"基本信息",text:"基本信息",url:"/biz/biz_info/biz_gs_info_db.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType},
							{id:"业务明细",text:"业务明细",url:"/biz/biz_info/biz_gs_detail.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType+"&partyId="+o.tbBizApply.partyId},
							{id:"监管报送信息",text:"监管报送信息",url:"/biz/biz_info/biz_gs_jgbs.jsp?applyId="+applyId+"&proFlag="+proFlag}
					]}
				];
				if(productType=='01006001' || productType=='01006002' || productType=='01006010'){//贴现业务
				menudata[menudata.length]={id:"票据明细信息",text:"票据明细信息",url:"/biz/biz_product_detail/pjxx/biz_tx_all1.jsp?productType="+productType+"&applyId="+applyId+"&proFlag="+proFlag};
				} 
				
				//单一客户存在是否银团
				if (o.tbBizApply.isBankTeamLoan=='1') {
					menudata[menudata.length]={id:"银团信息",text:"银团信息",
					children:[
						{id:"银团结构",text:"银团结构",url:"/biz/biz_info/biz_bankTeam_struct.jsp?applyId="+applyId+"&proFlag="+proFlag},
						{id:"成员信息",text:"成员信息",url:"/biz/biz_info/biz_bankTeam_member.jsp?applyId="+applyId+"&proFlag="+proFlag}
					]};
				}
			}
 			//综合授信
 			if("02"==o.tbBizApply.bizType || ("05"==o.tbBizApply.bizType)){
 				menudata = [
					{id:"贷款结构", text:"贷款结构", //expanded:true,  
						children:[
							{id:"基本信息",text:"基本信息",url:"/biz/biz_info/biz_gs_info.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType},
							{id:"额度明细",text:"额度明细",url:"/biz/biz_info/biz_gs_detail.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType},
							{id:"低风险额度",text:"低风险额度",url:"/biz/biz_info/biz_gs_detailList.jsp?partyId="+o.tbBizApply.partyId},
							{id:"监管报送信息",text:"监管报送信息",url:"/biz/biz_info/biz_gs_jgbs.jsp?applyId="+applyId+"&proFlag="+proFlag}
					]}
				];
			}
			//小微信贷
			if("04"==o.tbBizApply.bizType || ("06"==o.tbBizApply.bizType)){
				menudata = [
					{id:"贷款结构", text:"贷款结构", //expanded:true,  
						children:[
							{id:"基本信息",text:"基本信息",url:"/biz/biz_info/biz_xw_info.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType},
							{id:"家庭财务信息",text:"家庭财务信息",url:addr+"?partyId="+o.tbBizApply.partyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType+"&cusType=5"+"&applyId="+applyId},
							{id:"业务明细",text:"业务明细",url:"/biz/biz_info/biz_xw_detail.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType+"&partyId="+o.tbBizApply.partyId+"&bizHappenType="+bizHappenType},
							{id:"合作项目额度信息",text:"合作项目额度信息",url:"/biz/biz_product_detail/person/natural_project.jsp?applyId="+applyId+"&proFlag="+proFlag+"&partyId="+o.tbBizApply.partyId+"&productType="+productType},
							{id:"贴息信息",text:"贴息信息",url:"/biz/biz_product_detail/person/biz_tx_list.jsp?applyId="+applyId+"&proFlag="+proFlag}
					]}
				];
				if(prodType == '3' || productType.indexOf("020020")!=-1){//经营性及个人房屋按揭才有
					menudata[menudata.length]={id:"监管报送信息",text:"监管报送信息",url:"/biz/biz_info/biz_gr_jgbs.jsp?applyId="+applyId+"&proFlag="+proFlag+"&prodType="+prodType};
				}
				menudata[menudata.length]={id:"审批条件",text:"审批条件",url:"/biz/biz_info/biz_xw_tj.jsp?applyId="+applyId+"&proFlag="+proFlag};
				
			}
			//集团贷款
			if("03"==o.tbBizApply.bizType){
				menudata = [
					{id:"贷款结构", text:"贷款结构", //expanded:true,  
						children:[
							{id:"基本信息",text:"基本信息",url:"/biz/biz_info/biz_jt_info.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType+"&partyId="+o.tbBizApply.partyId},
							{id:"成员明细",text:"成员明细",url:"/biz/biz_info/biz_jt_detail.jsp?applyId="+applyId+"&proFlag="+proFlag+"&bizType="+o.tbBizApply.bizType+"&partyId="+o.tbBizApply.partyId}
					]}
				];
				
			}
			if("03"!=o.tbBizApply.bizType){
				//公用信息
				menudata[menudata.length]= {id:"担保评价", text:"担保评价",
				children:[
					{id:"保证人",text:"保证人",url:"/grt/guaranMainManager/guarantee_apply_list.jsp?applyId="+applyId+"&partyId="+o.tbBizApply.partyId+"&collType=04&proFlag="+proFlag},
					{id:"抵押",text:"抵押",
						children:[
							{id:"抵押物",text:"抵押物",url:"http://115.114.113.217:8080/default/com.bob.bcms.collateralmgr.CollMgr.flow?creditFlag=1&userId=<%=((UserObject) session.getAttribute("userObject")).getUserId()%>&applyId="+applyId+"&partyId="+o.tbBizApply.partyId+"&collType=01&proFlag="+proFlag+"&cltFlag=1&senceCode=1"},
							{id:"最高额担保合同关联",text:"最高额担保合同关联",url:"/biz/biz_info/biz_gs_dy.jsp?applyId="+applyId+"&partyId="+o.tbBizApply.partyId+"&collType=01&proFlag="+proFlag+"&partyId="+o.tbBizApply.partyId}
						]	
					},
					{id:"质押",text:"质押",
						children:[
							{id:"质押物",text:"质押物",url:"http://115.114.113.217:8080/default/com.bob.bcms.collateralmgr.CollMgr.flow?creditFlag=1&userId=<%=((UserObject) session.getAttribute("userObject")).getUserId()%>&applyId="+applyId+"&partyId="+o.tbBizApply.partyId+"&collType=01&proFlag="+proFlag+"&cltFlag=1&senceCode=1"},
							{id:"最高额担保合同关联",text:"最高额担保合同关联",url:"/biz/biz_info/biz_gs_zy.jsp?applyId="+applyId+"&partyId="+o.tbBizApply.partyId+"&collType=02&proFlag="+proFlag+"&partyId="+o.tbBizApply.partyId}
						]	
					},			
					{id:"保证金",text:"保证金",url:"/grt/guaranMainManager/guarantee_apply_cash_deposit_list.jsp?applyId="+applyId+"&partyId="+o.tbBizApply.partyId+"&collType=03&proFlag="+proFlag},
					{id:"信用保险",text:"信用保险",url:"/grt/guaranMainManager/guarantee_apply_list_credit_insurance.jsp?applyId="+applyId+"&partyId="+o.tbBizApply.partyId+"&collType=05&proFlag="+proFlag}
				]}
				<!-- menudata[menudata.length]={id:"批复通知书",text:"批复通知书"}; -->
				if(examRecord=="1"){
					var changeId = "<%=request.getParameter("changeId") %>";
					menudata[menudata.length]={id:"批复意见书",text:"批复意见书",url:"/crt/accountInfo/account_ideabook.jsp?applyId="+applyId+"&proFlag="+proFlag+"&changeId="+changeId};
					//menudata[menudata.length]={id:"审批意见通知书",text:"审批意见通知书"}; <!-- 打印审批意见通知书暂时屏蔽,待要求上线后解除注释即可-->
				}
				menudata[menudata.length]={id:"岗位责任",text:"岗位责任",url:"/biz/biz_info/biz_advice.jsp?applyId="+applyId+"&proFlag="+proFlag+"&orgNum="+o.tbBizApply.orgNum}
				menudata[menudata.length]={id:"提示",text:"提示",url:pjAddr+"?applyId="+applyId+"&proFlag="+proFlag+"&prodType="+prodType};
				
				if("04"!=o.tbBizApply.bizType){
					menudata[menudata.length]={id:"流动资金需求量测算",text:"流动资金需求量测算",url:"/pub/flow/flow_list.jsp?applyId="+applyId+"&proFlag="+proFlag};
				}
			}
			menudata[menudata.length]={id:"ecm",text:"影像信息",url:"/xx"};
			menudata[menudata.length]={id:"upload",text:"相关文档",url:"/biz/biz_info/pro_biz_upload.jsp?bizNum="+applyId+"&bizType=biz&proFlag="+proFlag};
			//意见提交
			if("-1"!=proFlag){
				menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+applyId+"&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2&partyId="+o.tbBizApply.partyId+""};
			}
			git.unmask();
			tree.loadData(menudata);
			nodeclick({"node":menudata[0].children[0]});
 		}
 	})
 	
 		//默认打开
	function nodeclick(e) {
		/* if(e.node['id']=="审批意见通知书"){
		
			var json = nui.encode({"map":{"sqlName":"com.bos.bizApprove.bizApprove.getApproveInfo","applyId":applyId,"processInstId":processInstId}});
			$.ajax({
	            url: "com.bos.biz.print.printSXSPTZS.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	if(mydata.swfPath){
	            		nui.open({
							url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
							title: "预览", width: 1000, height: 500,
				            onload: function () {
				            },
				            ondestroy: function (action) {
				                  grid.reload();
				            }
				
						});
	            	}
	            	if(mydata.msg){
	            		alert(mydata.msg);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	                git.unmask();
	            }
	       	});	
		}
		if(e.node['id']=="批复通知书"){
		
			var json = nui.encode({"map":{"sqlName":"com.bos.bizApprove.bizApprove.getApproveInfo","applyId":applyId,"processInstId":processInstId}});
			$.ajax({
	            url: "com.bos.biz.print.printApproveXw.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	if(mydata.swfPath){
	            		nui.open({
							url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
							title: "预览", width: 1000, height: 500,
				            onload: function () {
				            },
				            ondestroy: function (action) {
				                  grid.reload();
				            }
				
						});
	            	}
	            	if(mydata.msg){
	            		alert(mydata.msg);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	                git.unmask();
	            }
	       	});	
		} */
		
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='意见') {
		 	//bug13685  申请金额与合作方额度余额
		 	if(proFlag =='1'){
		 		var json1 = {"applyId":applyId};
		   	    msg = exeRule("RBIZ_0063","1",json1);
		   	    if(null != msg && '' != msg){
		   	    	nui.alert(msg);
		   	    }
		 	}
			if("1" == proFlag){
				//点击意见时将授权参数传到流程区域
				var json = nui.encode({"applyId":applyId,"processInstId":processInstId});
			     	$.ajax({
			            url: "com.bos.bizApply.bizApply.saveBizInfoToPro.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: true,
			            contentType:'text/json',
			            success: function (text) {
			            }
			        });
		    }
		    if(lowRiskBizType){
		    	if(productType !='01006001' && productType !='01006002' && productType !='01006010'){//贴现产品无担保方式就不校验担保方式
			     	var typeRule = [{"type":"12","rule":"RBIZ_0080"},{"type":"13","rule":"RBIZ_0081"},{"type":"21","rule":"RBIZ_0082"},{"type":"23","rule":"RBIZ_0082"},{"type":"25","rule":"RBIZ_0082"}];
			    	var jsonx = {"applyId":applyId};
			    	for(var x=0;x<typeRule.length;x++){
			    		if(typeRule[x].type==lowRiskBizType){
				    		msg = exeRule(typeRule[x].rule,"1",jsonx);
					   	    if(null != msg && '' != msg){
					   	    	nui.alert(msg);
					   	    	return ;
					   	    }
			    		}
			    	}
		    	}
		    }
		    //非低风险 单笔单批 综合授信
		    if(!lowRiskBizType && proFlag == "1"){//查询该申请是否为银承产品 若无保证金则提示
		    	var jsonG = {"applyId":applyId};
		    	msg = exeRule("RBIZ_0083","1",jsonG);
		    	if(null == msg || '' == msg){//有银承产品
		    		var msg1 = exeRule("RBIZ_0084","1",jsonG);
		    		if(null != msg1 && '' != msg1){
		    			alert(msg1);
		    		}
		    	}
		    }
		    if(!CrdUtil.checkLimit("com.bos.crd.LimitService.riskLimitValidByApplyId.biz.ext",{"applyId":applyId})){
	 			return ;
	 		}
	 		if(!validBankMaxCreditAmt()){
	 			return ;
	 		}
		}
		if (e.node['id']=='ecm') {
			//查询客户num
			var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>"});
		    $.ajax({
		        url: "com.bos.bizInfo.bizInfo.getEcmInfoByApplyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	o = nui.decode(mydata);
		        	if(""==o.bizApply.ifDataMove||null==o.bizApply.ifDataMove){
		        		ifDataMove = "0";
		        	}else{
		        		ifDataMove = o.bizApply.ifDataMove;
		        	}
		        	if(o.party.partyTypeCd == "02"){//个贷
		        		if(startWith(productType,"02002")=='1' || productType=='02005001'|| productType=='02005010'||productType == '02005003'){//不动产
		        			flowModuleType = "22,222,225,13,14";
		        		}else if(productType == '02001004'||productType == '02004001'||productType == '02004002'||productType == '02003012'){//动产
		        			flowModuleType = "22,222,226,13,14";
		        		}else{//其他
		        			flowModuleType = "22,221,222,13,14";
		        		}
				    }else {
				    	flowModuleType = "15,151,152,13,14,12";
				    }
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.bizApply.bizNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					if(proFlag=="0"||proFlag=="-1"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.bizApply.bizNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=1&flowModuleType="+flowModuleType;
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
		if (e.node['id']=='ecmprint') {
			ecm('print');
			return;
		}
		var tabs = nui.get("orgTabs");
		//if (e.node['id']=='质押') {
		//	tabs.setTabs([
		//		{title:e.node.text, url:e.node.url}
		//	]);
		//}else 
		//var partyid = o.tbBizApply.partyId;
		if(e.node['id']=='抵押物'){
			var json03 = nui.encode({"applyId":applyId,"guarantyType":"02"});
				$.ajax({
		            url: "com.bos.bizInfo.bizGrt.getBizGrtType.biz.ext",
		            type: 'POST',
		            data: json03,
		            cache: false,
		            contentType:'text/json',
		            cache: false,
		            success: function (mydata) {
		            	var o = nui.decode(mydata);
		            	var ifFlag = o.ifFlag;
		            	if('0' == ifFlag){
					    	nui.alert("该申请担保方式不为抵押！");
					    	return;
					    }else{
				//	    	$.ajax({
				//			        url: "com.bos.bizApply.bizApply.getBizzApply.biz.ext",
				//			        type: 'POST',
				//			        data: json03,
				//			        contentType:'text/json',
				//			        cache: false,
				//			        success: function (data) {
				//			        	var oldApplyId = data.arrays[0].OLD_APPLY_ID;
							        	if(proFlag == -1){
							        		proFlag =0;
							        	}
							        	var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.CollMgr.flow?creditFlag=1&orgId=<%=((UserObject) session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject) session.getAttribute("userObject")).getUserId()%>&applyId="+applyId+"&collType=01&proFlag="+proFlag+"&cltFlag=01&ifApp=1&senceCode=1";
							       		window.open(url);
										return;
				//			        },
				//			        error: function (jqXHR, textStatus, errorThrown) {
				//			            alert(jqXHR.responseText);
				//			            git.unmask();
				//			        }
				//		     });	
					    }	
					}
		        });
		}else if(e.node['id']=='质押物'){
			var json02 = nui.encode({"applyId":applyId,"guarantyType":"03"});
			$.ajax({
	            url: "com.bos.bizInfo.bizGrt.getBizGrtType.biz.ext",
	            type: 'POST',
	            data: json02,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	var o = nui.decode(mydata);
	            	var ifFlag = o.ifFlag;
	            	if('1' == ifFlag){
            	//		$.ajax({
				//		        url: "com.bos.bizApply.bizApply.getBizzApply.biz.ext",
				//		        type: 'POST',
				//		        data: json03,
				//		        contentType:'text/json',
				//		        cache: false,
				//		        success: function (data) {
				//			        var oldApplyId = data.arrays[0].OLD_APPLY_ID;
							        if(proFlag == -1){
							        		proFlag =0;
							        	}
						        	var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.CollMgr.flow?creditFlag=1&orgId=<%=((UserObject) session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject) session.getAttribute("userObject")).getUserId()%>&applyId="+applyId+"&collType=02&proFlag="+proFlag+"&cltFlag=02&ifApp=1&senceCode=1";
						       		window.open(url);
									return;
				//		        },
				//		        error: function (jqXHR, textStatus, errorThrown) {
				//		            alert(jqXHR.responseText);
				//		            git.unmask();
				//		        }
				//	     });
				    }else{
				    	nui.alert("该申请担保方式不为质押！");
				    }	
				}
	        }); 
		}else{
			tabs.setTabs([
				{title:e.node.text, url:nui.context+e.node.url}
			]);
		}
		$("#orgTabs").show();
	}
 	
 	function validBankMaxCreditAmt(){
 		var json = nui.encode({"applyId":applyId});
 		var action;
 		 $.ajax({
            url: "com.bos.bizInfo.bizGrt.validBankMaxCreditAmt.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (mydata) {
                var o = nui.decode(mydata);
                if(o.msg!=null){
                 	action = nui.confirm(o.msg,"询问");
                }else{
                	action =true;
                }
            }
        });
        return action;
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
</script>
</body>
</html>