<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-08 17:26:18
  - Description:
-->
<head>
<title>客户评级</title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/aft/css/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
		<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="客户信息" region="west" width="240" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
				<iframe id="exportFrame2" src="" style="width: 98%;height: 40px;border: 1px solid Skyblue;" frameborder="0" align="top"></iframe>
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
		    </div>
		</div>

<script type="text/javascript">
	nui.parse();
	var partyId;
	var temp1applyId = "<%=request.getParameter("bizId") %>";//评级申请id
	var temp2applyId = "<%=request.getParameter("applyId") %>";//评级申请id
	var processInstId = "<%=request.getParameter("processInstId") %>";//实例号
	var flowType  = "<%=request.getParameter("bizType") %>";//　　01：新增流程  02：更新流程（重检） 03：再审核
	var posicode = '<%=request.getParameter("activityDefId") %>'; 
	var applyId;
	var reAud = "0";
	var docType = "irm01"; //文档管理，irm01：评级  irm02：违约
	var oldApplyId;
	var party;
	var flg;
	init();	
	function init(){
		if (flowType == "03"){
			reAud = "1";
		}
		if(temp1applyId != "null"){
			applyId = temp1applyId;
		}
		if(temp2applyId != "null"){
			applyId = temp2applyId;
		}
		var json = nui.encode({"applyId":applyId});
	    nui.ajax({//通过评级申请Id来获取参与人Id
		    url: "com.bos.irm.queryInfo.queryPartyInfo.biz.ext",
		    type: 'POST',
		    data: json,
		    cache: false,
			async:false,        
		    contentType:'text/json',
		    success: function (text) {
			    if(text.msg){
			    	alert(text.msg);
			    } else {
			        var p = nui.decode(text);
			        partyId = p.item.partyId;
			        party = p.item;
			        if(reAud == '1'){
		        		oldApplyId = p.oldIrrApplyId;
		        	}
		        	
			    }
			}
		});
		var json1 =  nui.encode({"partyId":partyId});
		nui.ajax({//通过评级申请Id来获取参与人是否属于政府融资平台客户
		    url: "com.bos.irm.queryInfo.queryIsGrovernment.biz.ext",
		    type: 'POST',
		    data: json1,
		    cache: false,
			async:false,        
		    contentType:'text/json',
		    success: function (text) {
			    if(text.msg){
			    	alert(text.msg);
			    } else {
			        var o = nui.decode(text);
					flg = o.isGovernmentr;	      	
			    }
			}
		});
	}	
	document.getElementById("exportFrame2").src=nui.context+"/irm/singleCustom/creditRate/irm_rating_link_info_url.jsp?party="+nui.encode(party)+"&applyId="+applyId+"&docType="+docType;
	if(flg == 1){		
	var menudata = [
		{id:"内部评级", text:"内部评级", //expanded:true, 
			children:[
			{id:"基本信息",text:"基本信息",url:"/irm/singleCustom/creditRate/eval_corp_baseInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&flowType="+flowType},
			{id:"财务信息",text:"财务信息",url:"/irm/singleCustom/creditRate/eval_corp_financeReport.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
			{id:"非财务信息",text:"非财务信息",url:"/irm/singleCustom/creditRate/eval_corp_non_financeInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
			{id:"政府融资调整选项",text:"政府融资调整选项",url:"/irm/singleCustom/creditRate/eval_corp_grovernment_non_financeInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
			{id:"通用调整选项",text:"通用调整选项",url:"/irm/singleCustom/creditRate/eval_corp_adjustOption.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
			{id:"评级结论",text:"评级结论",url:"/irm/singleCustom/creditRate/eval_corp_rateConclusion.jsp?partyId="+partyId+"&applyId="+applyId+"&processInstId="+processInstId+"&flowType="+flowType+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&posicode="+posicode},
			{id:"相关文档",text:"相关文档",
			    children:[
			    	{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd},
					{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd+"&view=1"}
			    ]}
			]}
	];}else{
	var menudata = [
		{id:"内部评级", text:"内部评级", //expanded:true, 
			children:[
			{id:"基本信息",text:"基本信息",url:"/irm/singleCustom/creditRate/eval_corp_baseInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&flowType="+flowType},
			{id:"财务信息",text:"财务信息",url:"/irm/singleCustom/creditRate/eval_corp_financeReport.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
			{id:"非财务信息",text:"非财务信息",url:"/irm/singleCustom/creditRate/eval_corp_non_financeInfo.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
			{id:"通用调整选项",text:"通用调整选项",url:"/irm/singleCustom/creditRate/eval_corp_adjustOption.jsp?partyId="+partyId+"&applyId="+applyId+"&reAud="+reAud+"&oldApplyId="+oldApplyId},
			{id:"评级结论",text:"评级结论",url:"/irm/singleCustom/creditRate/eval_corp_rateConclusion.jsp?partyId="+partyId+"&applyId="+applyId+"&processInstId="+processInstId+"&flowType="+flowType+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&posicode="+posicode},
			{id:"相关文档",text:"相关文档",
			    children:[
			    	{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd},
					{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd+"&view=1"}
					//,{id:"条码打印",text:"条码打印",url:"/xx"}
			    ]}
			]}
	];}
	var tree = nui.get("tree1");
	tree.loadData(menudata);

	
  	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='ecmadd') {
			ecm('add');
			return;
		}
		if (e.node['id']=='ecmview') {
			ecm('view');
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
	nodeclick({"node":menudata[0].children[0]});
	function ecm(op) {
		var json = nui.encode({"custnum":"12345","op":op});
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
</script>
</body>
</html>