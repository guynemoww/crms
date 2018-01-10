<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-6 16:18:58
  - Description:
-->
<head>
<title>企业客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="公司客户信息" region="west" width="240" style="height:100%;border:0;"
		class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="height:100%;border:0;"
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" 
			refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	
	var cusType = "<%=request.getParameter("cusType")%>";
	var partyId = "<%=request.getParameter("partyId")%>";
	var qote = "<%=request.getParameter("qote")%>";
	var partyNum = "<%=request.getParameter("partyNum") %>";
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum") %>" ;
	var urlBasicInfo;
	if(!cusType){
		nui.alert("客户类型为空！");
	}else if(cusType==1||cusType==9){
		// 企业类
		urlBasicInfo = "/csm/corporation/csm_corporation_edit.jsp?partyId="+partyId+"&qote="+qote+"&ecifPartyNum="+ecifPartyNum;
	}else if(cusType==7){
	// 个体工商户
	urlBasicInfo = "/csm/corporation/csm_corporation_individual_edit.jsp?partyId="+partyId+"&qote="+qote+"&ecifPartyNum="+ecifPartyNum;
	
	
	} else {
			// 事业类
		
			urlBasicInfo = "/csm/corporation/csm_corporation_other_edit.jsp?partyId="+partyId+"&qote="+qote+"&ecifPartyNum="+ecifPartyNum;
	}
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
	
	var menudata = [
			{id:"基本概况信息",text:"基本概况信息<span class='baxcolor'>*</span>",url:urlBasicInfo},
			{id:"附属信息", text:"附属信息<span class='baxcolor'>*</span>",url:"/csm/corporation/csm_address_add.jsp?partyId="+partyId+"&qote="+qote},
			{id:"委托方账户信息", text:"委托方账户信息",url:"/csm/account/csm_entrust_account_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"注册资本",text:"注册资本<span class='baxcolor'>*</span>",url:"/csm/corporation/csm_stockholder_info_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"财务信息",text:"财务信息<span class='baxcolor'>*</span>",url:"/acc/acccustomerfinance/acccustomerfinance_list.jsp?partyId="+partyId+"&qote="+qote+"&cusType="+cusType},
			{id:"信用信息",text:"信用信息<span class='baxcolor'>*</span>",url:"/csm/natural/natural_credit_list.jsp?partyId="+partyId+"&qote="+qote+"&partyTypeCd=01"},
			{id:"实际控制人信息", text:"实际控制人信息<span class='baxcolor'>*</span>",url:"/csm/corporation/csm_control_info_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"对外股权投资情况",text:"对外股权投资情况",url:"/csm/corporation/csm_investment_info_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"关联关系信息",text:"关联关系信息",url:"/csm/corporation/csm_other_related_party.jsp?partyId="+partyId+"&qote="+qote+"&ecifPartyNum="+ecifPartyNum},
			{id:"企业规模认定信息",text:"企业规模认定信息",url:"/csm/corporation/identifyEntScale.jsp?partyId="+partyId+"&qote="+2},
			{id:"评级信息",text:"评级信息",url:"/csm/corporation/csm_external_eval_result_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"项目信息",text:"项目信息",url:"/csm/corporation/csm_project_info_list.jsp?partyId="+partyId+"&qote="+qote+"&partyNum="+partyNum},
			{id:"上市股票信息",text:"上市股票信息",url:"/csm/corporation/csm_stock_info_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"债券发行信息", text:"债券发行信息",url:"/csm/corporation/csm_bond_info_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"重大事件",text:"重大事件",url:"/csm/corporation/csm_impornant_event_list.jsp?partyId="+partyId+"&qote="+qote},
			{id:"预警信息",text:"预警信息",url:"/csm/corporation/ews_warn_main.jsp?corpid="+partyId+"&type=01&rule=3"},
			{id:"本行业务信息", text:"本行业务信息",
				children:[
					{id:"本行融资情况",text:"本行融资情况",url:"/csm/myBank/financing_tab.jsp?partyId="+partyId+"&partyNum="+partyNum+"&partyTypeCd=01"},
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
		
	if (cusType==2) {
		//事业类，没有上市股票信息
		menudata.splice(11, 1);
	}
	if (cusType==3) {
		//个体工商户，没有注册资本
		menudata.splice(3, 1);
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
	
	/* //同步ECIF信息
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
                nodeclick({"node":menudata[0]});
               
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask();
                nui.alert(jqXHR.responseText);
            }
	     });
     } */
     
     /* //通过修改进入树节点后
     if(qote=="2"){
     	SynchronizationEcif();
     }else{
     	nodeclick({"node":menudata[0]});
     } */
     
     nodeclick({"node":menudata[0]});
	
</script>
</body>
</html>