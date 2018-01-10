<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-05-20 10:46:30
  - Description:
-->
<head>
<title>自然人信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="自然人信息" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var qote = "<%=request.getParameter("qote")%>";
	var partyId = "<%=request.getParameter("partyId") %>";
	var partyNum = "<%=request.getParameter("partyNum") %>" ;
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum") %>" ;
	debugger;
	var view;
	if(qote=="1"){
		view = [					     
			{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=21,211,212"+"&view="+qote}
		]
	}else{
		view = [
		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=21,211,212"}
		]
	}
	
	var menudata = [				
		{id:"客户概况信息",text:"客户概况信息<span class='baxcolor'>*</span>",url:"/csm/natural/natural_info.jsp?partyId="+partyId+"&qote="+qote+"&ecifPartyNum="+ecifPartyNum},
		{id:"委托方账户信息", text:"委托方账户信息",url:"/csm/account/csm_entrust_account_list.jsp?partyId="+partyId+"&qote="+qote},
		{id:"关系人信息",text:"关系人信息",url:"/csm/natural/natural_relative.jsp?partyId="+partyId+"&qote="+qote+"&cType=1"},
		{id:"经营信息",text:"经营信息",url:"/csm/natural/natural_business.jsp?partyId="+partyId+"&qote="+qote+"&partyNum="+partyNum},
		{id:"高校信息",text:"高校信息",url:"/csm/natural/natural_school.jsp?partyId="+partyId+"&qote="+qote+"&partyNum="+partyNum},
		{id:"信用信息",text:"信用信息<span class='baxcolor'>*</span>",url:"/csm/natural/natural_credit_list.jsp?partyId="+partyId+"&qote="+qote+"&partyTypeCd=02"},
		//{id:"家庭财务信息",text:"家庭财务信息",url:"/csm/natural/natural_family.jsp?partyId="+partyId+"&qote="+qote},
		{id:"评级信息",text:"评级信息",url:"/csm/corporation/csm_external_eval_result_list.jsp?partyId="+partyId+"&qote="+qote},
		{id:"重大事件",text:"重大事件",url:"/csm/corporation/csm_impornant_event_list.jsp?partyId="+partyId+"&qote="+qote+"&partyTypeCd=02"},
		{id:"预警信息",text:"预警信息",url:"/csm/corporation/ews_warn_main.jsp?corpid="+partyId+"&type=01&rule=3"},
		{id:"附加信息",text:"附加信息",url:"/csm/natural/natural_additive_list.jsp?partyId="+partyId+"&qote="+qote},
		{id:"本行业务信息", text:"本行业务信息",
				children:[
					{id:"本行融资情况",text:"本行融资情况",url:"/csm/myBank/financing_tab.jsp?partyId="+partyId+"&partyNum="+partyNum+"&partyTypeCd=02"},
					{id:"为我行客户担保情况",text:"为我行客户担保情况",url:"/csm/myBank/guarantee_tab.jsp?partyId="+partyId},
// 					{id:"被我行客户担保情况",text:"被我行客户担保情况",url:"/csm/myBank/byGuarantee_tab.jsp?partyId="+partyId},
					{id:"违约记录",text:"违约记录",url:"/csm/corporation/csm_illegal_list.jsp?partyId="+partyId},
					{id:"拒贷信息",text:"拒贷信息",url:"/csm/myBank/refuse_list.jsp?partyId="+partyId}
				]},
		{id:"我行管理团队",text:"我行管理团队",url:"/csm/corporation/csm_manage_team_list.jsp?partyId="+partyId+"&qote="+qote},
		{id:"相关文档",text:"相关文档",url:"/biz/biz_info/pro_biz_upload.jsp?bizNum="+partyNum+"&bizType=csm&proFlag="+qote},
		{id:"影像资料", text:"影像资料", 
			children:view}
	];
	
	var tree = nui.get("tree1");
	tree.loadData(menudata);
	
	function nodeclick(e) {
		if(!e.node['url']) {//如果节点的URL为空
			return;
		}
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	nodeclick({"node":menudata[0]});
	
</script>
</body>
</html>