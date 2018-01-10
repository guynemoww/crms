<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-25 17:12:23
  - Description:
-->
<head>
<title>限额管理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<jsp:include page="/crd/risk/div/risk_list_div.jsp?"></jsp:include>
	<div>
		<label style="color: blue;">注意:分配额度为[0]的数据代表不需要监管</label>
	</div>
	<script type="text/javascript">
		nui.parse();
		searchRisk();
	</script>
</body>
</html>