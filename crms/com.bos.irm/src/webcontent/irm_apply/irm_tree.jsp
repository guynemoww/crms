<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 11:46:31
  - Description:
-->
<head>
<title>评级申请树菜单</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="评级申请" region="west"  width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
				<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
					<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
				</div>
		
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:92%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	//git.mask();
	var o;
	var menudata;
	var tree = nui.get("tree");
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改
	var iraApplyId ="<%=request.getParameter("iraApplyId") %>"; //协议申请ID
	var pjlx ="<%=request.getParameter("pjlx") %>";  
	var partyId;
	
	var json = nui.encode({"iraApplyId":iraApplyId});
    $.ajax({
        url: "com.bos.irm.irmApply.irmApply.getIrmApplyInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        cache: false,
        success: function (mydata) {
        	o = nui.decode(mydata);
        	partyId = o.irmApply.partyId;
        	var applyId = o.irmApply.iraApplyId;
        	var reAud = "0";
        	var flowType = "01";
        	var oldApplyId = applyId;
 			//公司客户
 			if("-1" != proFlag){
				var menudata = [
					{id:"内部评级", text:"内部评级", //expanded:true, 
						children:[
						{id:"基本信息",text:"基本信息",url:"/irm/singleCustom/creditRate/eval_corp_baseInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&flowType="+flowType+"&pjlx="+pjlx},
						//{id:"财务信息",text:"财务信息",url:"/irm/singleCustom/creditRate/eval_corp_financeReport.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
						{id:"非财务信息",text:"非财务信息",url:"/irm/singleCustom/creditRate/eval_corp_non_financeInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&proFlag="+proFlag},
						//{id:"政府融资调整选项",text:"政府融资调整选项",url:"/irm/singleCustom/creditRate/eval_corp_grovernment_non_financeInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
						<%--{id:"通用调整选项",text:"通用调整选项",url:"/irm/singleCustom/creditRate/eval_corp_adjustOption.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},--%>
						{id:"通用调整选项",text:"通用调整选项",url:"/irm/singleCustom/creditRate/eval_corp_adjustOption_jj.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&proFlag="+proFlag+"&processInstId=<%=request.getParameter("processInstId")%>"},
						{id:"评级结论",text:"评级结论",url:"/irm/singleCustom/creditRate/eval_corp_rateConclusion.jsp?partyId="+partyId+"&applyId="+applyId+"&flowType="+flowType+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&proFlag="+proFlag+"&processInstId=<%=request.getParameter("processInstId")%>"},
						/*{id:"相关文档",text:"相关文档",
						    children:[
								{id:"ecm",text:"影像信息",url:"/xx"}
						    ]},
						    */
					    {id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("iraApplyId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"}
						]}
				];
			}else{
				var menudata = [
					{id:"内部评级", text:"内部评级", //expanded:true, 
						children:[
						{id:"基本信息",text:"基本信息",url:"/irm/singleCustom/creditRate/eval_corp_baseInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&flowType="+flowType+"&pjlx="+pjlx},
						//{id:"财务信息",text:"财务信息",url:"/irm/singleCustom/creditRate/eval_corp_financeReport.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
						{id:"非财务信息",text:"非财务信息",url:"/irm/singleCustom/creditRate/eval_corp_non_financeInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&proFlag="+proFlag},
						//{id:"政府融资调整选项",text:"政府融资调整选项",url:"/irm/singleCustom/creditRate/eval_corp_grovernment_non_financeInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
						{id:"通用调整选项",text:"通用调整选项",url:"/irm/singleCustom/creditRate/eval_corp_adjustOption_jj.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&proFlag="+proFlag+"&processInstId=<%=request.getParameter("processInstId")%>"},
						{id:"评级结论",text:"评级结论",url:"/irm/singleCustom/creditRate/eval_corp_rateConclusion.jsp?partyId="+partyId+"&applyId="+applyId+"&flowType="+flowType+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&proFlag="+proFlag+"&processInstId=<%=request.getParameter("processInstId")%>"}
					/*	{id:"相关文档",text:"相关文档",
						    children:[
						    	{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+o.party.partyNum+"&businessNumber="+o.party.partyNum+"&partyTypeCd="+o.party.partyTypeCd},
								{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+o.party.partyNum+"&businessNumber="+o.party.partyNum+"&partyTypeCd="+o.party.partyTypeCd+"&view=1"}
						    ]}*/
						]}
				];
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
	if (e.node['id']=='ecm') {
			//查询客户num
			var json = nui.encode({"iraApplyId":"<%=request.getParameter("iraApplyId") %>"});
		    $.ajax({
		        url: "com.bos.irm.irmApply.irmApply.getEcmInfoByIrmId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	o = nui.decode(mydata);
		        	var	ifDataMove = "0";
		        	if(o.party.partyTypeCd == "02") {//个贷
				    	flowModuleType = "31,12,13,14";
				    }else {
				    	flowModuleType = "11,12,13,14";
				    }
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.party.partyNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					if(proFlag=="0"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.party.partyNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=1&flowModuleType="+flowModuleType;
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
</script>
</body>
</html>