<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 16:08:10
  - Description:
-->
<head>
<title>专业担保机构合作协议树菜单</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="合同签约" region="west"  width="240" class="sub-sidebar" allowResize="false" >
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>

	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
				<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
					<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
				</div>
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var menudata;
	var tree = nui.get("tree");
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改
	var contractId ="<%=request.getParameter("contractId") %>"; //协议申请ID
	var partyId ="<%=request.getParameter("partyId") %>"; //客户ID
	//根据contractId查询业务信息
	var json = nui.encode({"contractId":"<%=request.getParameter("contractId") %>"});
    $.ajax({
        url: "com.bos.conApply.conApply.initConDbTree.biz.ext",
        type: 'POST',
        data: json,
        contentType:'text/json',
        cache: false,
        success: function (mydata) {
        	var o = nui.decode(mydata);
 			partyId = o.tbConGuarantOrgInfo.partyId;
 			menudata = [
				{id:"专业担保公司合作协议", text:"专业担保公司合作协议",
					children:[
						{id:"明细信息",text:"明细信息",url:"/crt/con_info/con_info_dbht.jsp?contractId="+contractId+"&proFlag="+proFlag},
						{id:"账户信息",text:"账户信息",url:"/crt/accountInfo/account_list_db.jsp?contractId="+contractId+"&proFlag="+proFlag},
						{id:"ecm",text:"影像信息",url:"/xx"}
					]
				}
			];
			//意见提交
			if("-1"!=proFlag){
				menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+contractId+"&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
			}
			git.unmask();
			tree.loadData(menudata);
			nodeclick({"node":menudata[0].children[0]});
 		}
 	})
 	
 		//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='意见') {
			if("1" == proFlag){
				//点击意见时将授权参数传到流程区域
				var json = nui.encode({"contractId":contractId,"processInstId":"<%=request.getParameter("processInstId")%>"});
			     	$.ajax({
			            url: "com.bos.conApply.conApply.saveConInfoToPro.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            cache: false,
			            success: function (text) {
			            }
			        });
		    }
		}
		
		if (e.node['id']=='ecm') {
			//查询客户num
			var json = nui.encode({"partyId":partyId});
		    $.ajax({
		        url: "com.bos.bizProductDetail.bizProductDetail.getPartyByPartyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	o = nui.decode(mydata);
		        	ifDataMove = "0";
		        	if(o.party.partyTypeCd == "02") {//个贷
				    	flowModuleType = "32,323";
				    }else {
				    	flowModuleType = "15,154";
				    }
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.party.partyNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					if(proFlag=="0"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.party.partyNum+'&csmNum='+o.party.partyNum+'&partyTypeCd=01&ismove='+ifDataMove+"&view=1&flowModuleType="+flowModuleType;
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
	
	function getCustmer(){
		toGoCustDetail(partyId);
	}
</script>
</body>
</html>