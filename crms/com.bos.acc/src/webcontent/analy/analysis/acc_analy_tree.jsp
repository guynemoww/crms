<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): c_ture
  - Date: 2014-12-23 16:18:58
  - Description:
-->
<head>
<title>财务分析</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="layout1" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="财务分析" region="west" width="240" class="sub-sidebar"
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
	//var modelCd = "<%=request.getParameter("modelCd")%>";
	var modelCd = '01';
	var finanysisProgramId = "<%=request.getParameter("finanysisProgramId")%>" ;
	var qote = "<%=request.getParameter("qote")%>" ;
	var partyNum = "<%=request.getParameter("partyNum") %>";
	var urlTest;
	var menudata;
	var view;
	
	//企业类财务分析
	 menudata = [
		{id:"业务概况", text:"业务概况", //expanded:true, 
			children:[
			{id:"概况",text:"概况",url:"/acc/analy/analysis/acc_any_general.jsp?finanysisProgramId="+finanysisProgramId+"&moduleId=MK01001"}
			]},
		{id:"利润分析", text:"利润分析", //expanded:true, 
			children:[
			{id:"利润结构分析",text:"利润结构分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK02001&series=column&title=利润结构分析"},
			{id:"营业活动核心利润分析",text:"营业活动核心利润分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK02002&series=areaspline&title=营业活动核心利润分析"},
			{id:"利润率对比分析",text:"利润率对比分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK02003&series=line&title=利润率对比分析"},
			{id:"利润对比分析",text:"利润对比分析",url:"/acc/analy/analysis/acc_any_general.jsp?finanysisProgramId="+finanysisProgramId+"&moduleId=MK02004"}
			]},
		{id:"营运投资分析", text:"营运投资分析", //expanded:true, 
			children:[
			{id:"营运投资综合分析",text:"营运投资综合分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK03001&series=column&title=营运投资综合分析"},
			{id:"营运投资分析",text:"营运投资分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK03002&series=column&title=营运投资分析"},
			{id:"应收账款和票据",text:"应收账款和票据",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK03003&series=column&title=应收账款和票据"},
			{id:"存货",text:"存货",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK03004&series=column&title=存货"},
			{id:"预付账款",text:"预付账款",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK03005&series=column&title=预付账款"},
			{id:"应付账款",text:"应付账款",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK03006&series=column&title=应付账款"},
			{id:"预收账款",text:"预收账款",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK03007&series=column&title=预收账款"}
			]},
		{id:"资产负债结构分析", text:"资产负债结构分析", //expanded:true, 
			children:[
			{id:"资产负债表：资产",text:"资产负债表：资产",url:"/acc/analy/analysis/acc_any_general.jsp?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04001"},
			{id:"资产负债表：负债和所有者权益",text:"资产负债表：负债和所有者权益",url:"/acc/analy/analysis/acc_any_general.jsp?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04002"},
			{id:"资本充足分析",text:"资本充足分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04003&series=column&title=资本充足分析"},
			{id:"资产负债表流动性分析",text:"资产负债表流动性分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04004&series=column&title=资产负债表流动性分析"},
			{id:"偿债周期分析",text:"资产负债表流动性分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04005&series=column&title=资产负债表流动性分析"},
			{id:"资产负债表分析",text:"资产负债表分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04006&series=column&title=资产负债表分析"},
			{id:"资产负债融资匹配分析",text:"资产负债融资匹配分析",url:"<%=contextPath %>/com.bos.acc.areaFlow.flow?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04007&series=column&title=资产负债融资匹配分析"},
			{id:"盈利表分析",text:"盈利表分析",url:"/acc/analy/analysis/acc_any_general.jsp?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04008"},
			{id:"所有者变化分析",text:"所有者变化分析",url:"/acc/analy/analysis/acc_any_general.jsp?finanysisProgramId="+finanysisProgramId+"&moduleId=MK04009"}
			]},
	     {id:"经营活动现金流量分析", text:"经营活动现金流量分析", //expanded:true, 
			children:[
			{id:"经营活动现金流量细节分析",text:"经营活动现金流量细节分析",url:"/acc/analy/analysis/acc_any_general.jsp?finanysisProgramId="+finanysisProgramId+"&moduleId=MK05001"}
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