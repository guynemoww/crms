<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-05-20 10:46:30
  - Description:
-->
<head>
<title>自然人信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
	<div title="白名单" region="west" bodyStyle="overflow:hidden;" width="240" class="sub-sidebar" allowResize="false">
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
	var tree = nui.get("tree1");
	var del = "<%=request.getParameter("del")%>";
	var qote = "<%=request.getParameter("qote")%>";
	var partyId = "<%=request.getParameter("partyId") %>";
	var partyNum = "<%=request.getParameter("partyNum") %>" ;
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum") %>" ;
	var batchNumber = "<%=request.getParameter("number") %>" ;
	var delWhite = "<%=request.getParameter("number") %>" ;
	var proFlag = "<%=request.getParameter("number") %>" ;
	var processInstId = "<%=request.getParameter("processInstId") %>" ;
	var menudata;
	if(del==0){
					menudata = [
				{id:"add",text:"加入白名单客户",url:"/csm/natural/add_white_customer_list.jsp?batchNumber="+batchNumber+"&qote="+qote},
				{id:"ecm",text:"影像信息",url:"/xx"}
				];
	}else{
			menudata = [
				{id:"del",text:"移除白名单客户",url:"/csm/natural/del_white_customer_list.jsp?delWhite="+delWhite+"&qote="+qote},
				{id:"ecm",text:"影像信息",url:"/xx"}
				];
	}
	if("-1"!=proFlag){
				menudata[menudata.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+delWhite+"&processInstId="+processInstId+"&isSrc=2"}
				}
	tree.loadData(menudata);
	nodeclick({"node":menudata[0]});
	function nodeclick(e) {
		if(!e.node['url']) {//如果节点的URL为空
			return;
		}
		if(e.node['id']=='ecm'){
		
				var rows=grid.getSelecteds();
			//var row = grid.getSelected();
			if (rows.length <= 0) {
				alert("至少选择一条记录");
				return;
			}
			if(rows.length == 1){
				var json = nui.encode({"items":rows[0]});
			}else{
				var json = nui.encode({"items":rows});
			}
			git.mask();
			$.ajax({
				url : "com.bos.csm.inteface.ecif.synchronizationEcif1.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask();
					if (text.errMsg) {
						alert("ECIF提示信息："+text.errMsg);
					} else if(text.code=='AAAAAAA'){
						alert("同步成功!");
						queryInit();
					} 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask();
					nui.alert(jqXHR.responseText);
				}
			});
		}
	 if(e.node['id']=='意见'){
	                 var json = nui.encode({"batchNumber":batchNumber});
/* 	      			$.ajax({
				url : "com.bos.csm.inteface.ecif.whiteCommit.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask();
					if (text.msg=="1") {

					} else {
					alert("客户号为"+text.msg+"为准入状态不能提交");
					return;
					} 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask();
					nui.alert(jqXHR.responseText);
				}
			});  */
						var json = {"batchNumber":batchNumber};
	   	    msg = exeRule("WHITE_0001","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		return;
	   	    }
		}
		
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
	}
</script>
</body>
</html>