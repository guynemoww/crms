<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%
	String contextPath = request.getContextPath();
	Object userObj = session.getAttribute("userObject");
	UserObject userObject = null;
	String skin = "default";
	if (userObj != null) {
		userObject = (UserObject) userObj;
		if (userObject.getAttributes().get("style") != null) {
			skin = (String) userObject.getAttributes().get("style");
			//skin = "blue2010";
			skin = "blue";
		}
	}

	String datestr = GitUtil.getBusiDateStr();

	// 如果要设置客户端缓存页面，注释以下三行
	response.setHeader("Pragma", "no-cache");// No-Cache
	response.setHeader("Cache-Control", "No-Store");
	response.setDateHeader("Expires", 0);
%>
<!-- wangshichun@git.com.cn -->
<%@page import="com.bos.pub.GitUtil"%>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="progma" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Cache-Control" content="no-cache,must-revalidate" />
<script type="text/javascript" src="<%=contextPath%>/common/nui/nui.js"></script>
<!-- <script type="text/javascript" src="<%=contextPath%>/common/nui/nui-ext.js"></script> -->
<script type="text/javascript" src="<%=contextPath%>/common/nui/json2.js"></script>
<!-- <script type="text/javascript" src="<%=contextPath%>/common/nui/store.js"></script> -->
<script type="text/javascript" src="<%=contextPath%>/common/nui/jquery.cookies.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/common/nui/themes/<%=skin%>/skin.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/common/nui/themes/default/nui-ext-style.css" />
<script>
	var requestURL = "<%=request.getRequestURL()%>";
	var requestURI = "<%=request.getRequestURI()%>";
	var servletPath = "<%=request.getServletPath()%>";
	var actionSuccess = "操作成功";
	var actionFail = "操作失败";
	nui.context="<%=contextPath%>";
	if (top.location.href.indexOf('com.bos.utp.auth.Login.flow') < 1);
		//top.location.href = '<%=contextPath%>	/com.bos.utp.auth.Login.flow';//不允许敲地址栏进入系统
	$(function() {
		//在此初始化
	});
	nui.DataTree.prototype.dataField = 'data';//兼容改造
	
	function validateForm(form, msg) {
		form.validate();
		if (!form.isValid()) {
			if(msg){
				nui.alert(msg);
			}
			return;
		}
		return form.getData();
	}
	
	function renderingGrid(grid, action) {
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				action(e.data[i]);
			}
		});
	}
</script>