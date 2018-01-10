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
	var bizId = "<%=request.getParameter("bizId") %>";
	var partyId = "<%=request.getParameter("corpid") %>";
	var type = "<%=request.getParameter("type") %>";
	//alert(bizId + "---" + partyId + "---" + type);//bizId、partyId、proFlag有值
	var adjust;
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
                var node = <%=request.getParameter("node") %>;
            	party = text.party; 
            	adjust = text.adjust;
            	if(party.partyTypeCd==06){
            	 menudata = [
		                         {id:"客户预警", text:"客户预警", //expanded:true, 
			                          children:[
				                                {id:"预警信号列表",text:"预警信号列表",url:"/ews/warnDetail/warnInfo/ews_warning_list.jsp?corpid="+partyId},
				                                {id:"新增预警信号",text:"新增预警信号",url:"/ews/warnDetail/warnInfo/ews_warnInfo_add.jsp?bizId=<%=request.getParameter("bizId") %>&corpid=<%=request.getParameter("corpid") %>&type=<%=request.getParameter("type") %>&flowType=1&monitor=<%=request.getParameter("monitor") %>&node="+nui.encode(node)}
			                                    ]},
			                     {id:"提交流程",text:"提交流程",url:"/ews/warnDetail/warnInfo/warnIdea.jsp?node="+nui.encode(node)},
		                         {id:"相关文档", text:"相关文档", 
			                          children:[
			                                    {id:"预警信号变更历史",text:"预警信号变更历史",url:"/ews/warnDetail/warnInfo/ews_warnInfo_adjust_history.jsp?bizId=<%=request.getParameter("bizId") %>&partyId="+partyId},
				                                {id:"文档管理",text:"文档管理",url:"/ews/file/relevantFile.jsp?applyId="+bizId+"&button=1"},
			                                    {id:"影像查询",text:"影像查询",url:"/pub/imagePlatform/item_tree.jsp?csmNum="+party.partyNum+"&image=loanover"+"&businessNumber="+party.partyNum+"&partyTypeCd="+party.partyTypeCd+"&view=1"}
			                                    ]}
		                       ];  
            	}else{
                 menudata = [
		                         {id:"客户预警", text:"客户预警", //expanded:true, 
			                          children:[
				                                <%-- {id:"新增预警信号",text:"新增预警信号",url:"/ews/warnDetail/warnInfo/ews_warnInfo_add.jsp?party="+nui.encode(party)+"&bizId="+bizId+"&corpid=<%=request.getParameter("corpid") %>&type=<%=request.getParameter("type") %>&flowType=1&monitor=<%=request.getParameter("monitor") %>&node="+nui.encode(node)}, --%>
			                                    {id:"新增预警信号",text:"新增预警信号",url:"/ews/warnDetail/warnInfo/signal_save.jsp?party="+nui.encode(party)+"&bizId="+bizId+"&corpid="+party.partyId+"&type=<%=request.getParameter("type") %>&monitor=<%=request.getParameter("monitor") %>&node="+nui.encode(node)},
			                                    
			                                    {id:"预警报告",text:"预警报告",url:"/ews/warnDetail/warnInfo/levelPage.jsp?party="+nui.encode(party)+"&bizId="+bizId}
			                                    
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
	init();
	
	function nodeclick(e) {
		if(!e.node['url']) {
			return;
		}
		/* if (e.node['id']=='意见') {
			var ret = checkBeforeSub();
			if(ret == "1"){
				return;
			}
		} */
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
		msg = exeRule("REWS_0001","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return "1";
   	    }

   	    msg = exeRule("REWS_0003","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		return "1";
   	    }
   	    return "0";
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