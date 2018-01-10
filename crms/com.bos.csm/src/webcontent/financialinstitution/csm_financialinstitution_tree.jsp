<%@page pageEncoding="UTF-8"%>
<%@include file="/common/skins/skin0/component.jsp" %>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2013-11-18 14:12:23
  - Description:
-->

<head>
<title>编辑同业客户</title>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="同业客户信息" region="west" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" showTreeIcon="true"  textField="text" idField="id"
			expandOnLoad="true" onnodeclick="nodeclick">
		</ul>
	</div>
	
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	var qote = '<%=request.getParameter("qote")%>';
	var partyId = '<%=request.getParameter("partyId") %>';
	var partyNum = '<%=request.getParameter("partyNum") %>';
	var ismove='<%=request.getParameter("ismove") %>';;
	var view; 
	if(qote=="1"){
		view = [					     
			{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=11"+"&view="+qote}
		]
	}else{
		view = [
		{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?ismove="+ismove+"&businessNumber="+partyNum+"&csmNum="+partyNum+"&partyTypeCd=01&flowModuleType=11"}
		]
	}
		
		
	var menudata =[
		{id:"同业客户",text:"同业客户",
		   children:[
			 {id:"基本信息",text:"基本信息<span class='baxcolor'>*</span>",url:"/csm/financialinstitution/csm_financialinstitution_info.jsp?partyId="+partyId+"&qote="+qote},
			 {id:"财务信息",text:"财务信息",url:"/acc/acccustomerfinance/acccustomerfinance_list.jsp?partyId="+partyId+"&qote="+qote},
			 {id:"评级信息",text:"评级信息",url:"/csm/corporation/csm_external_eval_result_list_out.jsp?partyId="+partyId+"&qote="+qote},
			 {id:"重大事件",text:"重大事件",url:"/csm/corporation/csm_impornant_event_list.jsp?partyId="+partyId+"&qote="+qote+"&partyTypeCd=01"},
			 {id:"我行管理团队",text:"我行管理团队",url:"/csm/corporation/csm_manage_team_list.jsp?partyId="+partyId+"&qote="+qote}
		   ]},
<%--		 {id:"字段灵活补录",text:"字段灵活补录",url:"/pub/fieldFeed/fieldflefeed/field_Interface.jsp?recordeType=01&ids="+partyId+"&qote="+qote},
--%>	 {id:"影像资料", text:"影像资料", 
			children:view}
	
	];
		
	var tree = nui.get("tree1");
	tree.loadData(menudata);
		
	//点击菜单
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	
	//同步ECIF信息
	 function SynchronizationEcif(){
	 		git.mask();
	 		 var json = nui.encode({"partyId":partyId});
	         $.ajax({
	            url: "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask();
	            	//同步完毕后，加载节点
	                nodeclick({"node":menudata[0].children[0]});
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask();
	                nui.alert(jqXHR.responseText);
	            }
		     });
	 }
     //通过修改进入树节点后
	 if(qote=="2"){
//	 	SynchronizationEcif();
		nodeclick({"node":menudata[0].children[0]});
	 }else{
	 	nodeclick({"node":menudata[0].children[0]});
	 }
		
	//默认显示概况信息
     nodeclick({"node":menudata[0]});
</script>
</body>
</html>