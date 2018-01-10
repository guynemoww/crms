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
	var partyId = "<%=request.getParameter("corpid") %>"; // "8a70d1f045f366bb0145f380c2ec0027";
	var party;
	var menudata;
	var type = "<%=request.getParameter("type") %>";
	if(!type&&typeof(type)!="undefined"&&type!=0){
	  if(type==06){
            	 menudata = [
		                         {id:"客户预警", text:"客户预警", //expanded:true, 
			                          children:[
				                                {id:"预警信号列表",text:"预警信号列表",url:"/ews/warnDetail/warnInfo/ews_warning_list.jsp?corpid="+partyId},
				                                {id:"新增预警信号",text:"新增预警信号",url:"/ews/warnDetail/warnMgr/ews_warnInfo_add.jsp?corpid="+partyId}
			                                    ]}
		                       ];  
            	}else{
                 menudata = [
		                         {id:"客户预警", text:"客户预警", //expanded:true, 
			                          children:[
				                                {id:"预警信号列表",text:"预警信号列表",url:"/ews/warnDetail/warnInfo/ews_warning_list.jsp?corpid="+partyId},
				                                {id:"新增预警信号",text:"新增预警信号",url:"/ews/warnDetail/warnMgr/ews_warnInfo_add.jsp?corpid="+partyId},
			                                    {id:"预警级别认定",text:"预警级别认定",url:"/ews/warnDetail/warnMgr/ews_warnLevel_hold.jsp?corpid="+partyId}
			                                    ]}
		                       ]; 
		               
		               
		               } 
		                        var tree = nui.get("tree1");
	                            tree.loadData(menudata); 
	                            nodeclick({"node":menudata[0].children[0]});
	}else{
	  	init();
	}
	
	function init(){
	var json = nui.encode({partyId:partyId});
	 $.ajax({
            url: "com.bos.aft.common.getParty.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: true, //异步处理
            success: function (text) {
            	party = text.party; 
            	if(party.partyTypeCd==06){
            	 menudata = [
		                         {id:"客户预警", text:"客户预警", //expanded:true, 
			                          children:[
				                                {id:"预警信号列表",text:"预警信号列表",url:"/ews/warnDetail/warnInfo/ews_warning_list.jsp?corpid="+partyId},
				                                {id:"新增预警信号",text:"新增预警信号",url:"/ews/warnDetail/warnMgr/ews_warnInfo_add.jsp?corpid="+partyId}
			                                    ]}
		                       ];  
            	}else{
                 menudata = [
		                         {id:"客户预警", text:"客户预警", //expanded:true, 
			                          children:[
				                                {id:"预警信号列表",text:"预警信号列表",url:"/ews/warnDetail/warnInfo/ews_warning_list.jsp?corpid="+partyId},
				                                {id:"新增预警信号",text:"新增预警信号",url:"/ews/warnDetail/warnMgr/ews_warnInfo_add.jsp?corpid="+partyId},
			                                    {id:"预警级别认定",text:"预警级别认定",url:"/ews/warnDetail/warnMgr/ews_warnLevel_hold.jsp?corpid="+partyId}
			                                    ]}
		                       ]; 
		               
		               
		               } 
		                        var tree = nui.get("tree1");
	                            tree.loadData(menudata); 
	                            nodeclick({"node":menudata[0].children[0]});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
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