<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
-->
<head> 
<title>客户日常检查信息</title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/aft/css/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">  
			<div title="客户日常检查信息" region="west" width="220" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:100%;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="wborder:0;padding-left:5px;padding-top:5px;width: 60%;">
			   <!-- <div id="fright"> -->
		        <iframe id="exportFrame2" src="" style="width: 98%;height: 40px;border: 1px solid Skyblue;" frameborder="0" align="top" ></iframe>
	             <!--</div> -->
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;margin-top: 10px;" refreshOnClick="true"></div>
		    </div>
		</div>
<script type="text/javascript">
	nui.parse();
	var p=<%=request.getParameter("param") %>;
	var irId = p.irId;
	//alert(irId);
	var alcInfoId=p.bizId;
	var lastAlcInfoId = p.lastAlcInfoId;
	var goEdit = "<%=request.getParameter("goEdit") %>";
	var node=p.node;
	var json=nui.encode({"alcInfoId":alcInfoId,pId:p.partyId,irId:irId});
	var partyId="";
	//var lastAlcInfoId="";
	var party;
	$.ajax({
            url: "com.bos.aft.dailyInspect.queryPartyIdByAlcInfoId.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {
            	if(mydata.partyId!=null){
            		partyId=mydata.partyId;                                                                 //获取该客户的参与人ID
            	}
            	if(alcInfoId==null||typeof(alcInfoId)=="undefined"||alcInfoId==""){
            	    alcInfoId=mydata.alcId; 
            	}
            	if(mydata.lastAlcInfoId!=null){
            		if(goEdit=="1"){
            		
            		}else{
            		    lastAlcInfoId=mydata.lastAlcInfoId;                                                  //获取该客户最近一次的改后检查ID
            		}
            	}
            	party = mydata.party;
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {  
                    alert(jqXHR.responseText);
            }
        });
     var param=nui.encode({"alcInfoId":alcInfoId,"corpid":p.partyId,"lastAlcInfoId":lastAlcInfoId});
     document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+alcInfoId;
     /*var url = "/bps/mywork/work_flow_advice.jsp?processInstId="+node.processInstId+"isSrc=2&processDefName="+node.processDefName;
		url+="&activityDefId="+node.activityDefId+"&activityInstId="+node.activityInstId+"&activityInstName="+git.toUrlParam(node.activityInstName);
		url+="&workItemId="+node.workItemId+"&ruleID="+node.ruleID+"&selectType="+node.selectType+"&conclusion="+git.toUrlParam(node.conclusion);
		url+="&workitemMappingId="+node.workitemMappingId+"&templateVersion="+node.templateVersion+"&startTime="+node.startTime+"&doUrl="+node.doUrl;*/
	var menudata = [
		{id:"填写客户相关信息", text:"填写客户相关信息", //expanded:true, 
			children:[
			{id:"基本情况",text:"基本情况",url:"/aft/dailyInspect/basicCondition.jsp?param="+param+"&goEdit="+goEdit},
			{id:"专业贷款信息",text:"专业贷款信息",url:"/aft/dailyInspect/loanCondition.jsp?param="+param+"&goEdit="+goEdit},
			//{id:"经营情况",text:"经营情况",url:"/aft/dailyInspect/operateCondition.jsp?param="+param+"&goEdit="+goEdit},
			{id:"财务情况",text:"财务情况",url:"/aft/dailyInspect/financeCondition.jsp?param="+param+"&goEdit="+goEdit},
			{id:"信用与还款意愿",text:"信用与还款意愿",url:"/aft/dailyInspect/creditCondition.jsp?param="+param+"&goEdit="+goEdit},
			{id:"外部环境与重大事项",text:"外部环境与重大事项",url:"/aft/dailyInspect/externalFactor.jsp?param="+param+"&goEdit="+goEdit},
			{id:"预警信息",text:"预警信息",url:"/aft/dailyInspect/reportWarnInfoView.jsp?partyId="+partyId}
			]},
		
		{id:"填写债项级信息",text:"填写债项级信息",url:"/aft/dailyInspect/risk_debt_info.jsp?param="+param+"&goEdit=1"},
		{id:"保存本次贷后检查信息",text:"保存本次贷后检查信息",url:"/aft/dailyInspect/reviewPage.jsp?alcInfoId="+alcInfoId},
		//{id:"意见",text:"意见",url:url},
		//{id:"信贷资产检查报告",text:"信贷资产检查报告",url:"/aft/dailyInspect/aft_norBusiInspectReport.jsp?param="+param},
		{id:"相关文档",text:"相关文档",
			children:[
					{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&image=loanover"+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd},
					{id:"文档管理",text:"文档管理",url:"/aft/file/relevantFile.jsp?applyId="+alcInfoId+"&button=1"}
			]}

	];
	
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
	
</script>
<script type="text/javascript">
nui.parse();
//{"custnum":"<%=request.getParameter("corpid") %>","op":op}
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