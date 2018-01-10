<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<title>分类树菜单</title>
		<%@include file="/common/nui/common.jsp"%>
	</head>
	<body>
	
		<div id="layout" class="nui-layout" style="width:100%;height:100%;">
			<div id="claTree1" title="分类-<%=request.getParameter("bizType").equalsIgnoreCase("quar")? "季度调整":"日常调整"%>" region="west"  width="240" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" showTreeIcon="true" textField="text" idField="id" expandOnLoad="true" onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="wborder:0;padding-left:5px;padding-top:5px;width: 60%;">
		        <div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;margin-top: 10px;" refreshOnClick="true"></div>
		    </div>
		</div>
		<script type="text/javascript">
			nui.parse();
			var partyId = "<%=request.getParameter("partyId")%>";
			var claMethod = "<%=request.getParameter("bizType")%>";
			var acApplyId = "<%=request.getParameter("bizId")%>";
			var workItemId = "<%=request.getParameter("workItemId")%>";
			var processInstId = "<%=request.getParameter("processInstId")%>";
			var custFlag = "<%=request.getParameter("custFlag")%>";
			var wflow = "<%=request.getParameter("wflow")%>";
			
			var partyNum = "";
			var acApplyNum = "";
			var partyTypeCd = "01";
			
			//查询客户类型big/small/mloan
			//查询影像参数
			var json = nui.encode({"item":{"acApplyId":acApplyId},"nameSqlId":"com.bos.risk.cust.queryVideoParams"});
		     $.ajax({
		        url: "com.bos.risk.common.queryRecord.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
					partyNum = text.record.partyNum;
					acApplyNum = text.record.acApplyNum;
					//partyTypeCd = text.record.partyTypeCd;
					custFlag = text.record.custType;
					loadTree();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			 });
		     
			function loadTree() {
			    var video = "";
			    var flowModuleType = "17";
			    //if(partyTypeCd == "01"){
				//	flowModuleType = "112,1121,1122";
				//}else{
				//	flowModuleType = "25,26";
				//}
				// 1 查看 2 上传
				if(wflow == 2){
					video = [					     
						{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+acApplyNum+"&csmNum="+partyNum+"&partyTypeCd="+partyTypeCd+"&flowModuleType="+flowModuleType}
					]
				}else{
					video = [					     
						{id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?businessNumber="+acApplyNum+"&csmNum="+partyNum+"&partyTypeCd="+partyTypeCd+"&flowModuleType="+flowModuleType+"&view=1"}
					]
				}
	
				var treeNode = [
					{id:"认定工作底稿",text:"认定工作底稿",url:"/risk/sort/draft/sort_draft_tab.jsp?acApplyId="+acApplyId+"&wflow="+wflow},
					{id:"分类报告",text:"分类报告",url:"/risk/sort/report/sort_report.jsp?applyId="+acApplyId+"&workFlag="+wflow},
					<%
					if(!"2".equals(request.getParameter("edit"))){
					%>
					{id:"调整认定结果",text:"调整认定结果",url:"/risk/sort/adjust/sort_adjust_list.jsp?acApplyId="+acApplyId+"&workItemId="+workItemId+"&wflow="+wflow+"&operation=edit"},
					<% 
					} 
					%>
					{id:"过程意见",text:"过程意见",url:"/risk/sort/adjust/sort_adjust_list.jsp?acApplyId="+acApplyId+"&workItemId="+workItemId+"&wflow="+wflow+"&operation=view"},
					{id:"影像资料",text:"影像资料",children:video},
					{id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+acApplyId+"&processInstId="+processInstId+"&isSrc=2"}
				 ];
				if(!("quar" == claMethod && "big" == custFlag)){//季度调整
					treeNode.splice(0, 2);
					if(wflow != 2){
						treeNode.splice(3, 1);
					}
				}else{
					if(wflow != 2){
						treeNode.splice(5, 1);
					}
				}
				
				
				var tree = nui.get("tree1");
				
				tree.loadData(treeNode);
				nodeclick({"node":treeNode[0]});
			}
			
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
