<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): zhufaying
  - Date: 2014-03-27 11:21:42
  - Description:
-->
<head>
<title>业务信息tab</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
	nui.parse();
	<%String corpid = request.getParameter("corpid"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"贷款合同", url:nui.context+"/crt/con_pub/con_apv_list.jsp",refreshOnClick:true},
			{title:"内部银团合同", url:nui.context+"/crt/con_pub/con_apv_list2.jsp",refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>