<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>模型信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
	nui.parse();
	git.mask();
	<%String modelId = request.getParameter("modelId"); %>
	<%String modelNum = request.getParameter("modelNum"); %>
	<%String view =request.getParameter("view") ;%>
		var tabs = nui.get("tabs");
		if('<%=modelNum %>' == '98'){
			tabs.setTabs([
			
			{title:"基本信息", url:nui.context+"/irm/model/item_edit.jsp?modelId=<%=modelId %>&view=<%=view %>", showCloseButton:false},
			{title:"指标配置", url:nui.context+"/irm/model/GovernmentFinancingPlatform/item_list.jsp?modelId=<%=modelId %>&view=<%=view %>"},
			{title:"适用调整选项", url:nui.context+"/irm/model/GovernmentFinancingPlatform/index/item_list.jsp"}
			]);
		}else{
		tabs.setTabs([
			
			{title:"基本信息", url:nui.context+"/irm/model/item_edit.jsp?modelId=<%=modelId %>&view=<%=view %>", showCloseButton:false},
			{title:"指标配置", url:nui.context+"/irm/model/index/item_list.jsp?modelId=<%=modelId %>&view=<%=view %>"},
			{title:"主标尺配置", url:nui.context+"/irm/model/scale/item_list.jsp?modelId=<%=modelId %>&view=<%=view %>"},
			{title:"适用调整选项", url:nui.context+"/irm/model/option/item_list.jsp?modelNum=<%=modelNum %>"}
		]);
		}
		$("#tabs").show();
		git.unmask();
</script>
</body>
</html>