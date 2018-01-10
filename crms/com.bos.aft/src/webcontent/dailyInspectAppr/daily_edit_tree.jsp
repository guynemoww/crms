<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
-->
<head>
<title>客户日常检查信息维护</title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/aft/css/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<div id="fmain">
	<div id="fleft">
		<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="客户日常检查信息维护" region="west" width="240" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:200px;padding:5px;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
		    </div>
		</div>
	</div>
	<div id="fright">
		<iframe id="exportFrame2" src=""  width="100%"  frameborder="0" height="100%" align="top" ></iframe>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var alcInfoId="<%=request.getParameter("bizId") %>";
	var json=nui.encode({"alcInfoId":alcInfoId});
	var partyId="";
	var lastAlcInfoId="";
	var corpCustomerTypeCd = "";
	var party;                                                                      //参与人实体
	/*var p=<%=request.getParameter("param") %>;
	document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp";
	var alcInfoId=p.bizId;
	var node=p.node;
	var json=nui.encode({"alcInfoId":alcInfoId});
	var partyId="";
	var lastAlcInfoId="";
	var corpCustomerTypeCd = "";*/
	
	$.ajax({
            url: "com.bos.aft.dailyInspect.queryPartyIdByAlcInfoId.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {
                party = mydata.party;
            	if(mydata.partyId!=null){
            		partyId=mydata.partyId;
            	}
            	if(mydata.lastAlcInfoId!=null){
            		lastAlcInfoId=mydata.lastAlcInfoId;
            	}
            	corpCustomerTypeCd=mydata.corpCustomerTypeCd;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        });
     var param=nui.encode({"alcInfoId":alcInfoId,"corpid":partyId,"lastAlcInfoId":lastAlcInfoId,"corpCustomerTypeCd":corpCustomerTypeCd});
     document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+alcInfoId;//没取到partyId
	var menudata = [
		{id:"填写客户相关信息", text:"填写客户相关信息", //expanded:true, 
			children:[
			{id:"基本情况",text:"基本情况",url:"/aft/dailyInspect/basicCondition.jsp?param="+param},
			{id:"经营情况",text:"经营情况",url:"/aft/dailyInspect/operateCondition.jsp?param="+param},
			{id:"财务情况",text:"财务情况",url:"/aft/dailyInspect/financeCondition.jsp?param="+param},
			{id:"信用与还款意愿",text:"信用与还款意愿",url:"/aft/dailyInspect/creditCondition.jsp?param="+param},
			{id:"外部环境与重大事项",text:"外部环境与重大事项",url:"/aft/dailyInspect/externalFactor.jsp?param="+param}
			//{id:"客户综合评分",text:"客户综合评分",url:"/aft/dailyInspect/customerComprehensiveGrade.jsp?param="+param}
			]},
		{id:"填写债项级信息",text:"填写债项级信息",url:"/aft/dailyInspect/risk_debt_info.jsp?param="+param}
		
		,
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