<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27 
  - Description:
-->
<head>
<title>合同变更</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
			<div title="合同变更" region="west" width="220" class="sub-sidebar" allowResize="false">
				<ul id="tree1" class="nui-tree" style="width:100%;" 
					showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
					onnodeclick="nodeclick">
				</ul>
			</div>
			<div title="center" region="center" style="wborder:0;padding-left:5px;padding-top:5px;width: 60%;">
				<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;margin-top: 10px;" refreshOnClick="true"></div>
		    </div>
		</div>
<script type="text/javascript">
	nui.parse();
	var node=<%=request.getParameter("node") %>;
	var partyId ="<%=request.getParameter("partyId") %>"; 
	var contractId ="<%=request.getParameter("contractId") %>"; 
	var changeId ="<%=request.getParameter("changeId") %>"; //合同变更主键
	var conChangeType ="<%=request.getParameter("conChangeType") %>";
	var processInstId ="<%=request.getParameter("processInstId") %>"; //业务申请ID
	var proFlag ="<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var party;   
	var menudata; 
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
    
	if("01"==conChangeType){//利率变更
		/* menudata = [  
		{id:"申请信息",text:"申请信息",url:"/aft/conLoanChange/conChange_rate_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
		{id:"检查报告",text:"检查报告",url:"/aft/dailyInspect/newCheckReport.jsp?partyId="+partyId+"&node="+nui.encode(node)+"&bizId="+bizId},
		{id:"相关文档",text:"相关文档",
			children:[
					{id:"批复通知",text:"批复通知",url:"/aft/file/biz_view_business.jsp?partyId="+partyId},
					{id:"影像扫描",text:"影像扫描",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&image=loanover"+"&loanOverId="+bizId+"&partyTypeCd="+party.partyTypeCd},
					{id:"文档管理",text:"文档管理",url:"/aft/file/relevantFile.jsp?applyId="+bizId+"&button=1"}
			]}  

		]; */
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_rate_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	} 
	if("02"==conChangeType){//还款方式变更
		/* menudata = [  
		{id:"申请信息",text:"申请信息",url:"/aft/conLoanChange/conChange_repayway_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
		]; */
		
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_repayway_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	}
	if("03"==conChangeType){//约定扣款日变更		
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_repayday_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	}
	if("04"==conChangeType){//还款账号变更
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_repayaccount_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	}
	if("06"==conChangeType){//期限变更
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_term_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	}
	if("07"==conChangeType){//利率调整方式变更
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_irupdf_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	} 
	if("08"==conChangeType){//结息周期调整
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_collecttype_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	}
	if("10"==conChangeType){//还款计划表调整
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_repayplan_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	}
	if("09"==conChangeType){//贴息调整
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_tx_apply.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	} 
	if("05"==conChangeType){//担保方式变更
		/* menudata = [  
		{id:"申请信息",text:"申请信息",url:"/aft/conLoanChange/conChange_guaranty_apply.jsp?changeId="+changeId+"&proFlag="+proFlag},
		{id:"抵质押调整信息",text:"抵质押调整信息",url:"/aft/conLoanChange/conChange_dzy.jsp?changeId="+changeId+"&proFlag="+proFlag},
		{id:"保证人调整信息",text:"保证人调整信息",url:"/aft/conLoanChange/conChange_bzr.jsp?changeId="+changeId+"&proFlag="+proFlag}
		]; */
		
		menudata = [
			{id:"合同变更信息", text:"合同变更信息",
				children:[
					{id:"变更信息",text:"变更信息",url:"/aft/conLoanChange/conChange_guaranty_apply.jsp?changeId="+changeId+"&proFlag="+proFlag},
					{id:"抵质押调整信息",text:"抵质押调整信息",url:"/aft/conLoanChange/conChange_dzy.jsp?changeId="+changeId+"&proFlag="+proFlag},
					{id:"保证人调整信息",text:"保证人调整信息",url:"/aft/conLoanChange/conChange_bzr.jsp?changeId="+changeId+"&proFlag="+proFlag}
				]
			}
		];
	} 
	
	//意见提交
	if("-1"!=proFlag){
		menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("changeId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
	}
	
	var tree = nui.get("tree1"); 
	tree.loadData(menudata);
	/* nodeclick({"node":menudata[0]}); */
	nodeclick({"node":menudata[0].children[0]}); 
	
	//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='意见') {
			var ret = checkBeforeSub();
			if(ret == "1"){
				return;
			}
		}
		if (e.node['id']=='ecmadd') {
			//查询客户num
			var json = nui.encode({"partyId":o.tbBizApply.partyId});
		    $.ajax({
		        url: "com.bos.biz.pub.GetForCsm.getCustByPartyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.map.tbBizApply.bizNum+'&csmNum='+mydata.party.partyNum+'&partyTypeCd='+mydata.party.partyTypeCd+'&image=apply';
					if(proFlag=="0"){
						//ecmurl = nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.map.tbBizApply.bizNum+'&csmNum='+mydata.party.partyNum+'&partyTypeCd='+mydata.party.partyTypeCd+'&view=1';
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
		if (e.node['id']=='ecmview') {
			//查询客户num
			var json = nui.encode({"partyId":o.tbBizApply.partyId});
		    $.ajax({
		        url: "com.bos.biz.pub.GetForCsm.getCustByPartyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	//跳往信息平台页面
		        	nui.open({
		                url: nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.map.tbBizApply.bizNum+'&csmNum='+mydata.party.partyNum+'&partyTypeCd='+mydata.party.partyTypeCd+'&view=1',
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
	
	//提交前校验数据
	function checkBeforeSub(){
		var json = {"changeId":changeId};
		msg = exeRule("RCHA_0001","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return "1";
   	    }
   	    return "0";
	}
</script>
</body>
</html>