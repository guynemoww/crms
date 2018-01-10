<%@page pageEncoding="UTF-8"%>
<html>
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
	var bizId = "<%=request.getParameter("bizId") %>";
	var partyId = "<%=request.getParameter("corpid") %>";
	var processInstId ="<%=request.getParameter("processInstId") %>"; //业务申请ID
	var proFlag ="<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var party;
	var menudata;
	
	function init(){
	    var json = nui.encode({bizId:bizId});
	    $.ajax({
            url: "com.bos.ews.util.getParty.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: true, //异步处理
            success: function (text) {
            	party = text.party; 
            	adjust = text.adjust;
                 var menudata = [
                     {id:"客户预警", text:"客户预警",
                        children:[
                              {id:"预警信号关闭",text:"预警信号关闭",url:"/ews/warnDetail/warnInfo/ews_warnInfo_close.jsp?bizId="+bizId+"&proFlag="+proFlag+"&corpid="+party.partyId+"&flowType=2"},
                              {id:"预警信号变更历史",text:"预警信号变更历史",url:"/ews/warnDetail/warnInfo/ews_warnInfo_adjust_history.jsp?bizId="+bizId+"&proFlag="+proFlag+"&corpid="+party.partyId}
                              ]}
                      ];  
		            //意见提交
					if("-1"!=proFlag){
						menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId=<%=request.getParameter("bizId") %>&processInstId=<%=request.getParameter("processInstId")%>&isSrc=2"};
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
	
	init();
	
	var tree = nui.get("tree1");
	tree.loadData(menudata);
	
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

	//提交前校验数据
	function checkBeforeSub(){
		var json = {"bizId":bizId};
		//msg = exeRule("REWS_0002","1",json);
   	    //if(null != msg && '' != msg){
	   		//nui.alert(msg);
	   		//return "1";
   	    //}
   	    
   	    msg = exeRule("REWS_0003","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return "1";
   	    }
   	    return "0";
	}
	
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