<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>
    <title>绵阳银行信贷管理系统</title>
	<%@include file="/common/nui/common.jsp"%>  
</head>
<body>
    <div id="tabs" class="nui-tabs" activeIndex="0" tabAlign="left"
		style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
<script type="text/javascript">
    nui.parse();
    
    var tabs = nui.get("tabs");
	tabs.setTabs([
		{title:"分类及参数管理", url:nui.context+"/pub/grant/pack/item_list.jsp", 
			showCloseButton:false,refreshOnClick:true},
		{title:"规则管理（授权）", url:nui.context+"/pub/grant/pack/rule/item_list.jsp",refreshOnClick:true},
		{title:"规则管理（财报）", url:nui.context+"/pub/grant/pack/rule/item_list.jsp?ptype=03",refreshOnClick:true},
		{title:"规则管理（模型指标）", url:nui.context+"/pub/grant/pack/rule/item_list.jsp?ptype=02",refreshOnClick:true},
		{title:"规则管理（其他）", url:nui.context+"/pub/grant/pack/rule/item_list.jsp?ptype=99",refreshOnClick:true},
		{title:"决策树管理", url:nui.context+"/pub/grant/pack/rule/item_list.jsp?ptype=01&rtype=04",refreshOnClick:true},
		{title:"决策表管理", url:nui.context+"/pub/decision/deciTable/deci_table_list.jsp",refreshOnClick:true}
	]);
	$("#tabs").show();
</script>
    
</body>
</html>