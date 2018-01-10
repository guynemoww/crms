<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2016-04-19 18:50:22
  - Description:
-->
<head>
<title>产品维护tab页</title>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	<%String itemId = request.getParameter("itemId"); %>
	<%String view = request.getParameter("view");%>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"产品基本信息", url:nui.context+"/pub/product/product/item_edit.jsp?itemId=<%=itemId %>&view=<%=view %>", showCloseButton:false,refreshOnClick:true},
			{title:"产品规格参数", url:nui.context+"/pub/product/parameter/product_param_list.jsp?itemId=<%=itemId %>&view=<%=view %>", refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>