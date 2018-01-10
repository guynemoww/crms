
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-5 12:57:38
  - Description:
-->
<head>
<title>联保小组</title> 
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="联保小组信息" region="west" bodyStyle="" width="240" class="sub-sidebar" allowResize="false">
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
	nui.parse(); 
	git.mask();
	var qote  = "<%=request.getParameter("qote")%>" ;
	var partyId = "<%=request.getParameter("partyId") %>" ;
	var processInstId = "<%=request.getParameter("processInstId") %>" ;
	var partyNum = "<%=request.getParameter("partyNum") %>";
	var wflow = "<%=request.getParameter("wflow") %>";
	var menudata;
	var state;
	var view;
// 	if(qote=="1"){
//		view = [					     
//			{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=05&flowModuleType=1"+"&view="+qote}
//		]
//	}else{
//		view = [
//		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=05&flowModuleType=1,2,3,4"}
//		]
//	}
	if(qote=="1"){
		view = [					     
		{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=21,211,212"+"&view="+qote}
		]
	}else{
		view = [
		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=21,211,212"}
		]
	}
	         menudata = [
						{id:"基本信息",text:"基本信息",url:"/csm/guar/guarGroup_info.jsp?partyId="+partyId+"&qote="+qote+"&state="+state+"&processInstId="+processInstId},
						{id:"成员信息",text:"成员信息<span class='baxcolor'>*</span>",url:"/csm/guar/guarGroup_memberInfo.jsp?partyId="+partyId+"&processInstId="+processInstId+"&qote="+qote+"&state="+state+"&wflow="+wflow},
						{id:"联保小组授信信息",text:"联保小组授信信息",url:"/csm/guar/guarGroup_tab.jsp?partyId="+partyId},
						{id:"我行管理团队",text:"我行管理团队",url:"/csm/corporation/csm_manage_team_list.jsp?partyId="+partyId+"&qote="+qote},
						{id:"影像资料", text:"影像资料",  children:view},
						{id:"意见",text:"意见<span class='baxcolor'>*</span>",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("bizId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=<%=request.getParameter("isSrc")%>"+"&relationType=1"+"&partyId="+partyId+"&groupType=512"}
						];
	            	 if(wflow=='1'||wflow=='null'){//流程跟踪列表进入
						menudata.splice(5, 1);
				     }
					var tree = nui.get("tree1");
					tree.loadData(menudata);
					nodeclick({"node":menudata[0]});
					git.unmask();
	
	
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
