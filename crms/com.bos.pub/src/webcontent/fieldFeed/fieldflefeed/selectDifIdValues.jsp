<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>员工信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
<script type="text/javascript">
	nui.parse();
	<%
	String empid = request.getParameter("empid"); 
	String inorgid = request.getParameter("inorgid"); 
	String view = request.getParameter("view"); 
	%>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{ title: '客户信息补入', url: nui.context+'/pub/fieldFeed/fieldflefeed/fieldCus.jsp' },
			{ title: '合同信息补入', url: nui.context+'/pub/fieldFeed/fieldflefeed/fieldCon.jsp' },
			{ title: '借据信息补入', url: nui.context+'/pub/fieldFeed/fieldflefeed/fieldLoan.jsp' }
		]);
		$("#tabs").show();
</script>
</body>
</html>