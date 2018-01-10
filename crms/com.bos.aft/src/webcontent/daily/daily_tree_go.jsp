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
	
	var node=<%=request.getParameter("node") %>;
	var bizId="<%=request.getParameter("bizId") %>";
	var partyId ="<%=request.getParameter("partyId") %>"; //"8a70d1f0461250c001461276a8ae005b";
	var party;                                            //参与人实体
	var json = nui.encode({partyId:partyId});
	$.ajax({
            url: "com.bos.aft.common.getParty.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {     	
            	party = mydata.party;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        });
         document.getElementById("exportFrame2").src=nui.context+"/aft/aft_small_inspect/aft_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+bizId;//没取到partyId
	
	var menudata = [
		{id:"客户情况",text:"客户情况",url:"/aft/dailyInspect/inspectBasicInfo.jsp?partyId="+partyId},
		{id:"检查报告",text:"检查报告",url:"/aft/dailyInspect/newCheckReport.jsp?partyId="+partyId+"&node="+nui.encode(node)+"&bizId="+bizId},
			//{id:"客户综合评分",text:"客户综合评分",url:"/aft/dailyInspect/customerComprehensiveGrade.jsp?param="+param}
		//{id:"意见",text:"意见",url:"/bps/mywork/work_flow_advice.jsp"},
		{id:"相关文档",text:"相关文档",
			children:[
					{id:"批复通知",text:"批复通知",url:"/aft/file/biz_view_business.jsp?partyId="+partyId},
					{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&image=loanover"+"&loanOverId="+bizId+"&partyTypeCd="+party.partyTypeCd},
					{id:"文档管理",text:"文档管理",url:"/aft/file/relevantFile.jsp?applyId="+bizId+"&button=1"}
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
	nodeclick({"node":menudata[0]});
	
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