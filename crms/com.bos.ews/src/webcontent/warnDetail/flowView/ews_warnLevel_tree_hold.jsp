<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 曹喆
  - Date: 2014-02-13 11:20:58
  - Description:分类信息
-->
<head>
<title></title>
<%@include file="/common/nui/common.jsp"%>
<link href="<%=contextPath%>/ews/warnStyle/flow.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<div id="fmain">
	<div id="fleft">
		<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="" region="west" width="240" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
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
	var bizId = "<%=request.getParameter("bizId") %>";                                         //获取业务ID
	var party ;
    getParty();
	var menudata = [
		{id:"预警级别认定审核", text:"预警级别认定审核",                                                   //expanded:true, 
			children:[
				{id:"待审核预警信号",text:"待审核预警信号",url:"/ews/warnDetail/warnInfo/ews_warning_list.jsp?bizId=<%=request.getParameter("bizId") %>"},
			    {id:"预警级别审核信息",text:"预警级别审核信息",url:"/ews/warnDetail/warnLevel/ews_warnLevel_hold.jsp?bizId=<%=request.getParameter("bizId") %>&processInstId=<%=request.getParameter("processInstId") %>"}
			]},
		{id:"相关文档", text:"相关文档", 
			children:[
				{id:"影像查询",text:"影像查询",url:"/ews/ewsdetail/singleCustom/ews_warning_singleCustomer_list.jsp?corpid=<%=request.getParameter("corpid") %>&image=loanover"}
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
	
	function getParty(){
	    var json = nui.encode({bizId:"<%=request.getParameter("bizId") %>"});
        $.ajax({
            url: "com.bos.ews.util.getParty.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);
                party=o.party;
                document.getElementById("exportFrame2").src=nui.context+"/ews/ews_link_info_url.jsp?party="+nui.encode(party)+"&bizId="+bizId;           
	
            }
        });
	}
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