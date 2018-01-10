<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-10 12:57:38
  - Description:
-->
<head>
<title>集团客户</title> 
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="集团客户信息" region="west" bodyStyle="" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>

<script type="text/javascript">
//qote 1 查看   2 编辑
	nui.parse(); 
	var qote  = "<%=request.getParameter("qote")%>" ;
	var partyId = "<%=request.getParameter("partyId") %>" ;
	var partyNum = "<%=request.getParameter("partyNum") %>";
	var processInstId = "<%=request.getParameter("processInstId") %>";
	var wflow = "<%=request.getParameter("wflow")%>";
	var menudata;
	var view;
	if(qote=="1"){
		view = [					     
			{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=11"+"&view="+qote}
		]
	}else{
		view = [
		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=11"}
		]
	}
					
 	menudata = [
	{id:"基本信息",text:"基本信息",url:"/csm/company/groupCompany_info.jsp?partyId="+partyId+"&qote="+qote+"&processInstId="+processInstId},
	{id:"成员信息",text:"成员信息",url:"/csm/company/groupCompany_memberInfo.jsp?partyId="+partyId+"&processInstId="+processInstId+"&qote="+qote+"&wflow="+wflow},
	{id:"成员变更历史信息",text:"成员变更历史信息",url:"/csm/company/groupCompany_memberChangeInfo.jsp?partyId="+partyId+"&processInstId="+processInstId+"&qote="+qote},
	{id:"授信信息",text:"授信信息",url:"/csm/company/groupCompany_pifu.jsp?partyId="+partyId},
	{id:"重大事件",text:"重大事件",url:"/csm/corporation/csm_impornant_event_list.jsp?partyId="+partyId+"&qote="+qote+"&zdsjlx=1&zdsjbz=1&partyTypeCd=06"},
	{id:"管理团队",text:"管理团队",url:"/csm/corporation/csm_manage_team_list.jsp?partyId="+partyId+"&qote="+qote},
	{id:"影像资料", text:"影像资料",  children:view},
	{id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+partyId+"&processInstId="+processInstId+"&isSrc=2"+"&relationType=1"+"&partyId="+partyId+"&groupType=511"}
	];
	 
	 //如果是待办进入，显示意见，跟踪或查看进入，不显示意见
	 if(wflow=='1'||wflow=='null'){
		menudata.splice(7, 1);
     }
	
	var tree = nui.get("tree1");
	tree.loadData(menudata);
	nodeclick({"node":menudata[0]});
	
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

</script>

</body>
</html>
