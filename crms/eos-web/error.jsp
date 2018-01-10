<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.data.xpath.XPathLocator"%>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<html>
<head>
<%
	String contextPath = request.getContextPath();
	Object rootObj = XpathUtil.getDataContextRoot("request",pageContext);
  	String reCode = (String)XPathLocator.getInstance().getValue(rootObj,"reCode");
	
// 如果要设置客户端缓存页面，注释以下三行
response.setHeader("Pragma", "No-Cache"); 
response.setHeader("Cache-Control", "No-Cache"); 
response.setDateHeader("Expires", 0);
%>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<script type="text/javascript" src="<%=contextPath%>/common/nui/nui.js"></script>
<script type="text/javascript" src="<%=contextPath%>/common/nui/json2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/common/nui/jquery.cookies.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/common/nui/themes/default/nui-ext-style.css"/>
<!-- 微软windows自带打印插件：打印时起作用的样式 -->
 <style type="text/css" media=print>
.Noprint{display:none;}
.PageNext{page-break-after: always;}
</style>
<style>
BODY {
	color: #333333;
	background-image: url(<%=request.getContextPath()%>/mainframe/images/001_git.jpg);
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
	font-size: 8pt;
	background-position: top left;
	background-repeat: repeat;
	
}
.toptable h1 {
	display: none;
}
.toptable h3 {
	font-size: 12pt;
	display: inline;
}
.toptable h2 {
	font-size: 10pt;
	font-style: italic;
	display: inline;
}
.centertable {
	background-image: url(<%=request.getContextPath()%>/mainframe/images/login_utp.jpg);
}
.footercontent {
	font-size: 8pt;
	text-align: right;
	line-height: 1.1em;
}
.footercontent p {
	font-size: 8pt;
	text-align: right;
	line-height: 1.5em;
}
.content {
	padding: 10px;
}
.content p,ul,li,td,tr {
	font-size: 8pt;
}
.content h1 {
	font-size: 10pt;
	font-weight: bold;
}
.nav_manage th {
	background-color: #cccccc;
}
.message {
	color: red;
}
.logBtn{ 
    font-family:微软雅黑;
    font-size:14px;
	width:67px; 
	height:25px; 
	color:#fff; 
	border:0;
	cursor:pointer; 
	background:url(<%=request.getContextPath()%>/mainframe/images/loginBtn.png) no-repeat;
	}
</style>
<title>错误信息提示</title>
</head>
<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
<br>
<div align="center">
<br>
<br>
<br>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" class="centertable">
	<tr>
		<td>
		<table border='0' cellspacing='0' cellpadding='0' width="">
			<tr>
				<td width='681' height="405" valign='top' align="center">
					<table align="center" border="0" width="550">
					    <tr>
					       <td height="70px" style="padding: 80,0,0,0;">
					       	</td>
					    </tr>
					    <tr>
					       <td height="180px" style="padding: 80,0,0,0;" align="center">
					       	<font id="errorInfo" size="5" style="font-family:微软雅黑;color: red;" ></font>
					       	</td>
					    </tr>
					</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
<!-- 微软windows自带打印插件：王世春 测试打印 -->
 <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id="wb" name="wb" width=0></OBJECT>
</body>
<script type="text/javascript">
	nui.parse();
	if (self != top)
		top.location.href=self.location.href;
	
	var reCode = '<%=reCode %>';
	if(reCode=="-5") {
		document.getElementById("errorInfo").innerHTML = "未分配岗位或所在机构不是“运行中/营业”状态";
	} else if(reCode=="-6"){
		document.getElementById("errorInfo").innerHTML = "用户不存在";
	} else if(reCode=="-9"){
		document.getElementById("errorInfo").innerHTML = "系统正在跑批，请稍后登录";
	} else {
		document.getElementById("errorInfo").innerHTML = "未知错误，请联系管理员";
	}
</script>
</html>