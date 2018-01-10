<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-21 18:27:10
  - Description:
-->
<head>
<title>押品入库</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="押品入库" region="west"  width="240" class="sub-sidebar" allowResize="false">
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
	var outId ="<%=request.getParameter("outId") %>"; //押品入库ID
	var processInstId ="<%=request.getParameter("processInstId") %>"; //
	var proFlag ="<%=request.getParameter("proFlag") %>";
	<%--var isSrc ="<%=request.getParameter("isSrc") %>";//1:弹出页面。2，包含页面
	
	if(""==isSrc){
		isSrc="2"
	}--%>
	
	var json=nui.encode({"inId":outId});
	$.ajax({ 
	    	url: "com.bos.grtPro.outDetail.checkEpps.biz.ext",
	    	type: 'POST',
	    	data: json,
	    	cache: false,
	    	contentType:'text/json',
	    	success: function (text) {
			//判断是否是期转现业务
				menudata = [
					{id:"押品入库信息", text:"押品入库信息",
						children:[
							{id:"押品入库信息",text:"押品入库信息",url:"/grt/grt_pro/grt_in_list.jsp?outId="+outId+"&proFlag="+proFlag},
							{id:"ecm",text:"影像信息",url:"/xx"}
						]
					}
				];
				if(text.fmap.ifBiz == "true"){
					//期转现业务。展现 面积页面
					menudata[menudata.length]={id:"期转现业务面积录入",text:"期转现业务面积录入",url:"/grt/grt_pro/grt_in_area.jsp?bizId="+outId+"&suretyId="+text.fmap.suretyId+"&proFlag="+proFlag};
				}
				//意见提交
				if("-1"!=proFlag){
					menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+outId+"&processInstId="+processInstId+"&isSrc=2"};
				};
				tree.loadData(menudata);
				nodeclick({"node":menudata[0].children[0]});
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
		if (e.node['id']=='ecm') {
			//查询客户num
			var json = nui.encode({"suretyNoId":outId});
		    $.ajax({
		        url: "com.bos.bizInfo.bizInfo.getEcmInfoByInId.biz.ext",
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
				    	flowModuleType = "109";
				    
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