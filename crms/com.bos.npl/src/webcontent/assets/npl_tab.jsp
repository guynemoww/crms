<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-20 16:18:58
  - Description:
-->
<head>
<title>客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
	nui.parse();
	<%String corpid = request.getParameter("corpid"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
		{title:"处置方案", url:nui.context+"/npl/assets/assets_info.jsp?corpid=<%=corpid %>"},
		{title:"财务信息", url:nui.context+"/npl/assets/assets_history.jsp?corpid=<%=corpid %>"},
		{title:"客户保全台账", url:nui.context+"/npl/book/assets_book_bq.jsp?corpid=<%=corpid %>"}
		]);
		$("#tabs").show();
</script>
</body>
</html>
