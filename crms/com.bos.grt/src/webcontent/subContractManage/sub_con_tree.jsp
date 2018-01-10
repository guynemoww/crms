<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:27:10
  - Description:
-->
<head>
<title>担保合同调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="layout" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="担保合同调整" region="west" width="240" class="sub-sidebar"
			allowResize="false">
			<ul id="tree" class="nui-tree" style="width: 200px; padding: 5px;"
				showTreeIcon="true" textField="text" idField="id"
				expandOnLoad="true" onnodeclick="nodeclick">
			</ul>
		</div>
		<div title="center" region="center"
			style="border: 0; padding-top: 5px;">
			<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0"
				style="width: 98%; height: 100%; border: 0;" refreshOnClick="true"></div>
		</div>
	</div>
	<script type="text/javascript">
	nui.parse();
	var menudata;
	var tree = nui.get("tree");
	var subConTId ="<%=request.getParameter("bizId")%>"; //担保合同调整ID
	var proFlag ="<%=request.getParameter("proFlag")%>";
	var subconractType = "<%=request.getParameter("subconractType")%>";
		var subcontractId1 = "";
	var o;
	var json = nui.encode({"subConTId":subConTId});
    $.ajax({
        url: "com.bos.grt.subContractManage.subContract.findSubcontractId.biz.ext",
        type: 'POST',
        data: json,
        async: false,
        cache: false,
        contentType:'text/json',
        success: function (text) {
	        o = nui.decode(text);
	        var subcontractId = o.subcontractT.subcontractId;
	        subcontractId1=subcontractId;
	       	if(proFlag=="-1"){
				menudata = [
				{id:"担保合同调整",text:"担保合同调整",url:"/com.bos.grt.grtMainManage.toSubcontractDetail.flow?subConTId="+subConTId+"&subcontractId="+subcontractId+"&proFlag="+proFlag+"&subconractType="+subconractType},
				{id:"ecm",text:"影像信息",url:"/xx"}
			];
			}else{
				menudata = [
				{id:"担保合同调整",text:"担保合同调整",url:"/com.bos.grt.grtMainManage.toSubcontractDetail.flow?subConTId="+subConTId+"&subcontractId="+subcontractId+"&proFlag="+proFlag+"&subconractType="+subconractType},
				{id:"ecm",text:"影像信息",url:"/xx"},
				{id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=<%=request.getParameter("isSrc")%>"} 
			];
		}
		tree.loadData(menudata);
		nodeclick({"node" : menudata[0]});
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
    });

 
 		//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
				if (e.node['id']=='ecm') {
				    	flowModuleType = "158";
				        var ifDataMove="";
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+subcontractId1+'&partyTypeCd=01&ismove='+ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					if(proFlag=="0"||proFlag=="-1"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+subcontractId1+'&partyTypeCd=01&ismove='+ifDataMove+"&view=1&flowModuleType="+flowModuleType;
					}
		        	nui.open({
		                url:ecmurl,
		                title: "查看影像信息", 
		                width: 1200,
		        		height: 600,
		        		state:"max",
		                allowResize:true,
		        		showMaxButton: true,
		                onload: function () {
		                },
		                ondestroy: function (action) {
		                }
	            	})

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