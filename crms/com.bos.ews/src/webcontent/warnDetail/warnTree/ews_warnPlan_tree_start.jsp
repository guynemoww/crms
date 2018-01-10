<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 曹喆
  - Date: 2014-02-13 11:20:58
  - Description:分类信息
-->
<head>
<title></title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="" region="west" width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:300px;padding:5px;" 
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
	var party;
	var menudata;
	var bizId ="<%=request.getParameter("bizId") %>";
  function init(){
	 var json = nui.encode({partyId:"<%=request.getParameter("corpid") %>"});
	 $.ajax({
            url: "com.bos.ews.util.getParty.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: true, //异步处理
            success: function (text) {
            	party = text.party; 
                 var menudata = [
		                        {id:"客户预警", text:"客户预警", //expanded:true, 
			                                   children:[
				                                         {id:"预警信号列表",text:"预警信号列表",url:"/ews/warnDetail/warnInfo/ews_warning_list.jsp?corpid=<%=request.getParameter("corpid") %>"},
				                                         {id:"发起申请预案",text:"发起申请预案",url:"/ews/warnDetail/warnPlan/ews_warnReservePlan_add.jsp?corpid=<%=request.getParameter("corpid") %>"+"&bizId="+bizId}
			                                             ]},
		                        {id:"相关文档", text:"相关文档", 
			                                   children:[
				                                         {id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&businessNumber="+party.partyNum+"&image=loanover"+"&partyTypeCd="+party.partyTypeCd+"&view=1"}
			                                             ]}
		                         ];  
		                        var tree = nui.get("tree1");
	                            tree.loadData(menudata); 
	                            nodeclick({"node":menudata[0].children[0]});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	init();
	
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
<script type="text/javascript">
nui.parse();
//{"custnum":"<%=request.getParameter("corpid") %>","op":op}
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