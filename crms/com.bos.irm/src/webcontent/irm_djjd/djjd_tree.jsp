<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 11:46:31
  - Description:
-->
<head>
<title>冻结树菜单</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="layout" class="nui-layout" style="width:100%;height:100%;">
	<div title="冻结菜单" region="west"  width="240" class="sub-sidebar" allowResize="false">
		<ul id="tree" class="nui-tree" style="width:300px;padding:5px;" 
			showTreeIcon="true" textField="text" idField="id" expandOnLoad="true"
			onnodeclick="nodeclick">        
		</ul>

	</div>
	<div title="center" region="center" style="border:0;padding-top:5px;">
		<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width:98%;height:100%;border:0;" refreshOnClick="true"></div>
    </div>
</div>
<script type="text/javascript">
	nui.parse();
	//git.mask();
	var o;
	var menudata;
	var tree = nui.get("tree");
	var amountId = "<%=request.getParameter("amountId") %>";
	var wflow = "<%=request.getParameter("wflow")%>";
	var proFlag = "<%=request.getParameter("proFlag")%>";
	var children = [
				{id:"基本信息",text:"基本信息",url:"/irm/irm_djjd/eval_dj_basicInfo.jsp?proFlag="+proFlag+"&amountId="+amountId},
				{id:"冻结操作",text:"冻结操作",url:"/irm/irm_djjd/eval_dj_operate.jsp?proFlag="+proFlag+"&amountId="+amountId}
				];
	var menudata = [
			{id:"冻结", text:"冻结", children:children }
		];
		
	if(wflow=="2"){
	 children[children.length]={id:"意见",text:"意见",url:"/com.bos.bps.service.workFlowAdvice.flow?bizId="+amountId+"&processInstId="+<%=request.getParameter("processInstId")%>+"&isSrc=2"};
	}
	git.unmask();
	tree.loadData(menudata);
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
		var tabs = nui.get("orgTabs");
		tabs.setTabs([
			{title:e.node.text, url:nui.context+e.node.url}
		]);
		$("#orgTabs").show();
		
 
	}
	
	//提交前校验数据
	function checkBeforeSub(){
		var json = {"amountId":amountId};
   	    //未保存冻结原因
   	    msg = exeRule("RBIZ_0078","1",json);
   	    if(null != msg && '' != msg){
	   		nui.alert("冻结操作中未保存冻结原因");
	   		return "1";
   	    }
   	   return "0";
	}
</script>
</body>
</html>