<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<html>
<head>
<%
	String contextPath = request.getContextPath();
	Object userObj = session.getAttribute("userObject");
	String skin = "default";
	if(userObj != null){
		UserObject userObject = (UserObject)userObj;
		if(userObject.getAttributes().get("style") != null){
			skin = (String)userObject.getAttributes().get("style");
		}
	}
	
// 如果要设置客户端缓存页面，注释以下三行
response.setHeader("Pragma", "No-Cache"); 
response.setHeader("Cache-Control", "No-Cache"); 
response.setDateHeader("Expires", 0);
%>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<script type="text/javascript" src="<%=contextPath%>/common/nui/nui.js"></script>
<script type="text/javascript" src="<%=contextPath%>/common/nui/nui-ext.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/common/nui/themes/<%=skin%>/skin.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/common/nui/themes/default/nui-ext-style.css"/>
<script>
	var requestURL = "<%=request.getRequestURL() %>";
	var requestURI = "<%=request.getRequestURI() %>";
	var servletPath = "<%=request.getServletPath() %>";
	$(function(){
		nui.context='<%=contextPath %>'
	});
	nui.DataTree.prototype.dataField='data';//兼容改造
</script>
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
<title>用户登录</title>
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

				<form id="form1" class="" method="post" action="com.bos.utp.auth.Login.flow">
					<input type="hidden" name="_eosFlowAction" value="login">
					<table align="right" border="0" width="480">
					<tr><td colspan="3" height="20px;" >&nbsp;</td></tr>
					    <tr>
					       <td colspan="3"  height="180px" style="padding: 80,0,0,0;"><font size="5" style="font-family:微软雅黑;">信贷管理系统</font></td>
					    </tr>
						<tr>
							<td align="right" style="font-family:微软雅黑;font-size:14px;color:#000000;height:35px">用户名：</td>
							<td><input name="acOperator/userid" id="acOperator/userid" class="nui-textbox" size="23" style="width:202px;height:26px" onenter="focusPassword" onvalidation="onCheckUserId"/></td>
							<td></td>
						</tr>
						<tr>
							<td align="right" style="font-family:微软雅黑;font-size:14px;color:#000000;height:35px">密&nbsp&nbsp&nbsp码：</td>
							<td><input name="acOperator/password" id="acOperator/password" class="nui-password" size="23" style="width:202px;height:26px" onenter="keyboardLogin" onvalidation="onCheckPassword"/></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td><p id="error" class="login-error" style="display:inline-block;font-size:14px;height:20px;color:red;">
							<%
								if(request.getAttribute("retCode") != null){
									String retCode = (String)request.getAttribute("retCode");
									String errorMessage = "";
									switch(Integer.parseInt(retCode)){
										case -1: errorMessage = "用户不存在"; break;
										case -2: errorMessage = "输入密码错误"; break;
										case -3: errorMessage = "用户被锁定"; break;
										case -4: errorMessage = "用户被停用"; break;
										case -5: errorMessage = "未分配岗位或所在机构不是“运行中/营业”状态"; break;
									}
									out.print(errorMessage);
								}
							 %>
							</p></td>
						</tr>
						<tr>
						    <td colspan="3" align="center" style="height:35px">
						      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="logBtn" value="登录" onclick="login();" >&nbsp;&nbsp;
						      <input type="reset" class="logBtn"  value="重置">
						    </td>
						</tr>
					</table>
				</form>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
</body>

	<script type="text/javascript">
	 if (self != top)
	 	top.location.href=self.location.href;
	
     nui.parse();
     var form = new nui.Form("#form1");
     
     nui.get("acOperator/userid").focus();
     
     function login(){
       form.validate();
       if(form.isValid()==false){
       		return;
       }
       document.getElementById("form1").submit();
     }
     
     function reset(){
       form.reset();
     }
     
     function onCheckUserId(e){
       if (e.isValid) {
         if(e.value==""){
           e.errorText = "用户名不能为空";
           e.isValid = false;
         }
       }
     }
     
     function onCheckPassword(e){
       if (e.isValid) {
         if(e.value==""){
           e.errorText = "密码不能为空";
           e.isValid = false;
         }
       }
     }
     
     //在用户名输入框回车时, 密码输入框获得焦点
     function focusPassword(){
     	if (!window.event || event.keyCode!=13)
     		return true;
     	nui.get("acOperator/password").focus();
     }
     document.getElementById("acOperator/userid$value").onkeypress=focusPassword;
     
     //获取键盘 Enter 键事件并响应登录
     function keyboardLogin(e){
       login();
     }
   </script>
   
</html>