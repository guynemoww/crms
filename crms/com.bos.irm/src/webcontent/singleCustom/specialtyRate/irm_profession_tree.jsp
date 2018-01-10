<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-08 17:26:18
  - Description:
-->
<head>
<title>客户评级</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="客户信息" region="west" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">
		</ul>
	</div>
	<div title="center" region="center" style="border:0;padding-left:5px;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	var applyIdTem1 = "<%=request.getParameter("bizId") %>";//评级申请id
	var	applyIdTem2 = "<%=request.getParameter("applyId") %>";//评级申请id
	var partyId = "<%=request.getParameter("partyId") %>";
	var judgeRecordId = "<%=request.getParameter("judgeRecordId") %>";
	var projectId = "<%=request.getParameter("projectId") %>";

	var applyId;
	
	init();	
	function init(){
		if(applyIdTem1 != 'null'){
			applyId = applyIdTem1;
		}
		if(applyIdTem2 != 'null'){
			applyId = applyIdTem2;
		}
		
		var json = nui.encode({"applyId":applyId});
	    nui.ajax({//通过评级申请Id来获取参与人Id
		    url: "com.bos.irm.queryInfo.queryPartyInfo.biz.ext",
		    type: 'POST',
		    data: json,
		    cache: false,
			async:false,        
		    contentType:'text/json',
		    success: function (text) {
		    if(text.msg){
		    	alert(text.msg);
		    } else {
		        var p = nui.decode(text);
		        partyId = p.item.partyId;
		        }
		    }
		});
	}			
	var menudata = [
		{id:"客户评级", text:"客户评级", //expanded:true, 
			children:[
			{id:"基本信息",text:"基本信息",url:"/irm/singleCustom/creditRate/eval_corp_baseInfo.jsp?partyId="+partyId+"&applyId="+applyId },
			{id:"项目判断信息",text:"项目判断信息",url:"/irm/singleCustom/specialtyRate/irm_professionJudge.jsp?partyId="+partyId+"&applyId="+applyId+"&judgeRecordId="+judgeRecordId+"&projectId="+projectId },
			{id:"相关文档",text:"相关文档",
			    children:[
			    {id:"影像查询",text:"影像查询",url:"XXXXXXXXXXXXXXXX.jsp?partyId="+partyId+"&applyId="+applyId }
			    ]}
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