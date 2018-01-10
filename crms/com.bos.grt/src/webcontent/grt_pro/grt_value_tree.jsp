<%@page import="com.bos.pub.EosUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 10:30:41
  - Description:
-->
<head>
<title>押品价值审核</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>

</head>
<body>
<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="押品价值审核" region="west"  width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>

	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:92%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var o;
	var menudata;
	var tree = nui.get("tree");
	var proFlag ="<%=request.getParameter("proFlag") %>";
	var processInstId ="<%=request.getParameter("processInstId") %>"; //流程ID
	var outId ="<%=request.getParameter("outId") %>"; //流程ID
		menudata = [
					{id:"审批", text:"审批", //expanded:true,  
						children:[
							{id:"基本信息",text:"基本信息",url:"/grt/grt_pro/grt_value_list.jsp?suretyNo="+outId},
		                    {id:"ecm",text:"影像信息",url:"/xx"}
						//	{id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+outId+"&processInstId="+processInstId+"&isSrc=2"}
					]}
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
		if (e.node['id']=='ecm') {
			//查询客户num
			var json = nui.encode({"suretyNoId":outId});
		    $.ajax({
		        url: "com.bos.bizInfo.bizInfo.getEcmInfoBySuretyNoId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	o = nui.decode(mydata);
		        	     	if(""==o.bizApply.ifDataMove||null==o.bizApply.ifDataMove){
		        		ifDataMove = "0";
		        	}else{
		        		ifDataMove = o.bizApply.ifDataMove;
		        	}
				    	flowModuleType = "13,14";
				    	if(o.bizApply.bizNum==null||o.bizApply.bizNum==""||o.bizApply.bizNum=="null"||o.bizApply.bizNum=="undefined"){
				    nui.alert("请将该押品与业务关联");
			   	      return;
				    	
				    	}
				    
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.bizApply.bizNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					if(proFlag=="0"||proFlag=="-1"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.bizApply.bizNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=1&flowModuleType="+flowModuleType;
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
		        }
					
			})
			return;
		}else{
				var tabs = nui.get("orgTabs");
		tabs.setTabs([
			//{title:"基本信息", url:nui.context+"/grt/grt_pro/grt_value_list.jsp?suretyNo="+outId}
			{title:e.node.text, url:nui.context+e.node.url}
			
		]);
		$("#orgTabs").show();
		}
	}
	
	
</script>
</body>
</html>