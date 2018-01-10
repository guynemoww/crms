<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:11:13
  - Description:
-->
<head>
<title>菜单页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="资产保全申请" region="west"  width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
	
		<div class="nui-dynpanel" columns="10" style="border:1px solid Skyblue;margin-bottom:4px;width:98%;" >
			<label></label><a href="#"  onclick="getCustmer()">客户信息</a> 
			<label></label><a href="#"  onclick="getContract()">合同信息</a>
		</div>
				
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	git.mask();
	var tree = nui.get("tree");
	var menudata;
	var schemeId='<%=request.getParameter("schemeId")%>';
	var flag='<%=request.getParameter("flag")%>';
	menudata = [
		{id:"资产保全处置信息", text:"资产保全处置信息",
			children:[
				{id:"处置方案信息",text:"处置方案信息",url:"/npl/assets/assets_disposition_scheme.jsp?schemeId="+schemeId},
				{id:"处置方案明细信息",text:"处置方案明细信息",url:"/npl/assets/assets_disposition_scheme_detail.jsp?schemeId="+schemeId}
		]}
	];
	if('1'==flag){
		menudata[menudata.length]={id:"意见提交",text:"意见提交",url:"/npl/assets/assets_advice.jsp?processInstId=<%=request.getParameter("processInstId")%>"}
	}
	menudata[menudata.length]={id:"相关文档",text:"相关文档",url:"/npl/assets/documents.jsp?schemeId="+schemeId},
	menudata[menudata.length]={id:"ecmview",text:"影像信息",url:"/xx"}
	
	git.unmask();
	tree.loadData(menudata);
	nodeclick({"node":menudata[0].children[0]});
	
	//控制js
	//默认打开
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		if (e.node['id']=='ecmview') {
			//查询客户num
			var json = nui.encode({"schemeId":"<%=request.getParameter("schemeId")%>"});
		    $.ajax({
		        url: "com.bos.npl.assets.AssetsDispositionScheme.getDispositionScheme.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	//跳往信息平台页面
		        	nui.open({
		                url: nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+mydata.tbNplDispositionScheme.schemeNum+'&csmNum='+mydata.tbNplDispositionScheme.partyNum+'&partyTypeCd='+mydata.tbNplDispositionScheme.partyType+'&view=1',
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
//客户信息
	function getCustmer(){
		var json = nui.encode({"schemeId":"<%=request.getParameter("schemeId")%>"});
		$.ajax({
		    url: "com.bos.npl.assets.AssetsDispositionScheme.getDispositionScheme.biz.ext",
		    type: 'POST',
		    data: json,
		    cache: false,
		    contentType:'text/json',
		    cache: false,
		    success: function (mydata) {
		    	//客户信息
		        $.ajax({
		            url: "com.bos.npl.assets.AssetsDispositionScheme.viewCustomer.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            cache: false,
		            success: function (mydata) {
		                var o = nui.decode(mydata);
		                if(o.msg){
		                	nui.alert(o.msg);
		                }else{
		                	nui.open({
		            			url: nui.context + o.url,
		            			title: "客户信息", 
		            			width: 1024, 
		        				height: 600,
		        				allowResize:true,
		        				showMaxButton: true,
		           				ondestroy: function (action) {
		            			}
		        			});
		                }
		            }
		        })
		    }
		})
	}
	//合同信息
	function getContract(){
		var json = nui.encode({"schemeId":"<%=request.getParameter("schemeId")%>"});
		$.ajax({
		    url: "com.bos.npl.assets.AssetsDispositionScheme.getDispositionScheme.biz.ext",
		    type: 'POST',
		    data: json,
		    cache: false,
		    contentType:'text/json',
		    cache: false,
		    success: function (mydata) {
		    	//合同信息
				nui.open({
					url:nui.context + "/npl/assets/assets_contract_info.jsp?partyId="+mydata.tbNplDispositionScheme.partyId,
					title: "合同列表",
			            width: 1100,
			            height: 450,
			            onload:function(){
			            	top.bizConWin = this;
			            },
			            ondestroy: function (action) {            
			                
			        }
				})
		    }
		})
	}
</script>
</body>
</html>