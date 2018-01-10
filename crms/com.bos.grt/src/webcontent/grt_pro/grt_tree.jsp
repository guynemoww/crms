<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:27:10
  - Description:
-->
<head>
<title>押品出库</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="押品出库" region="west"  width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var menudata;
	var tree = nui.get("tree");
	var outId ="<%=request.getParameter("outId") %>"; //押品出库ID
	var processInstId ="<%=request.getParameter("processInstId") %>"; //
	var proFlag ="<%=request.getParameter("proFlag") %>";
	<%--var isSrc ="<%=request.getParameter("isSrc") %>";//1:弹出页面。2，包含页面
	
	if(""==isSrc){
		isSrc="2"
	}--%>
		menudata = [
			{id:"押品出库信息", text:"押品出库信息",
				children:[
					{id:"押品出库信息",text:"押品出库信息",url:"/grt/grt_pro/grt_out_list.jsp?outId="+outId+"&proFlag="+proFlag}
				]
			}
		];
		//意见提交
		if("-1"!=proFlag){
			menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+outId+"&processInstId="+processInstId+"&isSrc=2"};
		};
		tree.loadData(menudata);
		nodeclick({"node":menudata[0].children[0]});
 		//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='ecmprint') {
			ecm('print');
			return;
		}
		
		if(e.node['id']=='意见'){
			
			var json = nui.encode({"outId":outId,"processInstId":processInstId});
	     	$.ajax({
	            url: "com.bos.grt.grtMainManage.subcontractView.saveGrtProcess.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (text) {
	            }
	        });
		}
		
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
	
	function ecm(op) {
		var json = nui.encode({"op":op,"custnum":"12345"});
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