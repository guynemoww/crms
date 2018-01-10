<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ganquan@git.com.cn
-->
<head>
<title>合作中介客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="layout1" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="合作中介客户信息" region="west" width="240" class="sub-sidebar"
			allowResize="false">
			<ul id="tree1" class="nui-tree" showTreeIcon="true" textField="text"
				idField="id" expandOnLoad="true" onnodeclick="nodeclick">
			</ul>
		</div>
		<div title="center" region="center"
			style="border: 0; padding-left: 5px; padding-top: 5px;">
			<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0"
				style="width: 100%; height: 100%; border: 0;" refreshOnClick="true"></div>
		</div>
	</div>
	<script type="text/javascript">
	nui.parse();
	var cusType = "<%=request.getParameter("cusType")%>";
	var partyId = "<%=request.getParameter("partyId")%>" ;
	var qote = "<%=request.getParameter("qote")%>" ;
	var partyNum = "<%=request.getParameter("partyNum")%>";
	var urlTest;
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
	
	if(!cusType){
		nui.alert("客户类型为空！");
	}else {
		 menudata = [
			{id:"基本信息",text:"基本信息<span class='baxcolor'>*</span>",url:"/csm/thirdParty/csm_thirdParty_edit.jsp?partyId="+partyId+"&qote="+qote},
			{id:"财务信息",text:"财务信息",url:"/acc/acccustomerfinance/acccustomerfinance_list.jsp?partyId="+partyId+"&qote="+qote+"&cusType=6"},
			{id:"客户资质信息",text:"客户资质信息",url:"/csm/corporation/csm_aptitude_info_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"重大事件",text:"重大事件",url:"/csm/corporation/csm_impornant_event_list.jsp?partyId="+partyId+"&qote="+qote+"&partyTypeCd=01"},
			//{id:"评级信息",text:"评级信息",url:"/csm/corporation/csm_external_eval_result_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"影像资料", text:"影像资料", children:view}			
		];
	}
	
	var tree = nui.get("tree1");
	tree.loadData(menudata);
	
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
	
	nodeclick({"node":menudata[0]});
     
	
	
</script>
	<script type="text/javascript">
nui.parse();
//{"custnum":"<%=request.getParameter("corpid")%>","op":op}
<%--function ecm(op) {
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
}--%>
</script>
</body>
</html>