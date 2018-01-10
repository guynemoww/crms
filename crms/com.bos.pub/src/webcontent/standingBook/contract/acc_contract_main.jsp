<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 谭峻
  - Date: 2014-02-13 11:20:58
  - Description:合同tree
-->
<head>
<title>合同信息tree</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="" region="west" width="240" class="sub-sidebar" allowResize="false" >
    	<input class="nui-text nui-form-input" id="productCd" style="display: none;"/>
    	<input class="nui-text nui-form-input" id="contractDetailId" style="display: none;"/>
    	<input class="nui-hidden nui-form-input" id="url" style="display: none;"/>
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
			<div id="lj" class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
				</div>
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" >
		</div> 
		</div>
</div>
<script type="text/javascript">
	nui.parse();
	//creditType();
	var contractDetailId;
	var menudata;
	var partyId;
	var bizId;
	var bizNum;
	var recombinationSummaryNum;
	var yContractId;
	if("<%=request.getParameter("yid") %>" == "null" || "<%=request.getParameter("yid") %>" == ""){
		nui.get("lj").hide();
	}
		//查询不同业务品种对应不同合同查看页面的url
	
	getUrl();
	function getUrl(){
			var contractId="<%=request.getParameter("contractId") %>";
			var json = nui.encode({"contractDetail/contractId":contractId,"contract/contractId":contractId});
			$.ajax({
	            url: "com.bos.pay.addPayout.getContractDetailUrl.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	tree.loadData(menudata);
					//alert(mydata.map+"?contractDetailId="+mydata.contractDetail.contractDetailId);
	            	nui.get("url").setValue(mydata.map+"?contractDetailId="+mydata.contractDetail.contractDetailId);
	            	contractDetailId = mydata.contractDetail.contractDetailId;
	            	partyId = mydata.contract.partyId;
	            	//alert(mydata.contract.applyId);
	            	bizId =  mydata.contract.applyId;
	            	//alert(nui.encode(mydata.contract));
	            	bizNum = mydata.contract.bizNum;
	                recombinationSummaryNum = mydata.contract.recombinationSummaryNum;
	                yContractId = mydata.contract.yContractId;
	            	git.unmask();
	            	
	            	menudata = [
							{id:"主合同信息", text:"主合同信息",
								children:[
								{id:"合同信息",text:"合同信息",url:"/crt/view/contract_base_info.jsp?corpid=<%=request.getParameter("contractId") %>"},
								{id:"基本信息选项",text:"基本信息选项",url:"/crt/view/contract_yesOrNo_info.jsp?corpid=<%=request.getParameter("contractId") %>"},
								{id:"contractDetails",text:"合同明细",url:""},
								{id:"担保方式",text:"担保方式",url:"/crt/view/guarantee_type.jsp?corpid=<%=request.getParameter("contractId") %>"},
								{id:"accountInfo",text:"账户信息",url:"/crt/view/view_account_list.jsp?corpid=<%=request.getParameter("contractId") %>&contractDetailId="+contractDetailId}
								]
								}];
					if(mydata.contract.isBankTeamLoan == "2"  ||
					mydata.contract.isBankTeamLoan == "null" || 
					mydata.contract.isBankTeamLoan == null) {
						menudata.splice(2,1);
					}
					
					
					tree.loadData(menudata);
					nodeclick({"node":menudata[0].children[0]});
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	                git.unmask();
	            }
       		});	
       		
	}
	
	
	var tree = nui.get("tree1");
	

	function nodeclick(e) {
		//根据不同的授信品种进入不同合同明细页面
		if (e.node['id']=='ecmadd') {
			//查询客户num
			var json = nui.encode({"partyId":partyId});
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
		                url: nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+bizNum+'&csmNum='+mydata.party.partyNum,
		                title: "查看批复信息", 
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
			var json = nui.encode({"partyId":partyId});
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
		                url: nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+bizNum+'&csmNum='+mydata.party.partyNum+'&view=1',
		                title: "查看批复信息", 
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
		}if (e.node['id']=='ecmprint') {
			ecm('print');
			return;
		}
		if (e.node['id']=='contractDetails') {
			var goToConDetailJsp = "";
			
			goToConDetailJsp +=nui.get("url").getValue();
			e.node['url'] = goToConDetailJsp;
		}
		
		var tabs = nui.get("orgTabs");
		
		if(e.node.url == "" || e.node.url == null) return;
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	} 
</script>

</body>
</html>