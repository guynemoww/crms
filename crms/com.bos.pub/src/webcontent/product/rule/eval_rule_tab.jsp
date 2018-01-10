<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 卢金彬
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>业务品种参数</title>
<%@include file="/common/nui/common.jsp"%>
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
			{title:"申请控制规格参数", url:nui.context+"/pub/product/rule/item_edit.jsp?itemId=<%=itemId %>&view=<%=view %>", showCloseButton:false,refreshOnClick:true},
			{title:"利率控制规格参数", url:nui.context+"/pub/product/rule/item_edit2.jsp?itemId=<%=itemId %>&view=<%=view %>",refreshOnClick:true},
			{title:"合同变更参数", url:nui.context+"/pub/product/rule/item_edit3.jsp?itemId=<%=itemId %>&view=<%=view %>",refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>
