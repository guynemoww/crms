<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): wangbing
  - Date: 2013-08-15 16:24:36
  - Description:
-->
<head>
<title>Title</title>
</head>
<body>
	<div id="controls-wrapper" class="load-item">
		<div id="controls">
			<!--Thumb Tray button-->
			<a id="layout"><img id="layoutSkin" src=""/></a>
		</div>
	</div>
<script type="text/javascript">
nui.parse();
//标准方法接口定义
function SetData(data) {
	//跨页面传递的数据对象，克隆后才可以安全使用
	data = nui.clone(data);
	data = nui.decode(data);
	//alert(document.getElementById("menu").src);
	var path = "<%=contextPath%>/mainframe/images/preview/" +　data.menuType + "_" + data.pageStyle+ ".PNG";
	document.getElementById("layoutSkin").src=path;
	//nui.get("menuType").src="dafault/mainframe/welcome/img/button-tray-ups.png";
}
</script>
</body>
</html>