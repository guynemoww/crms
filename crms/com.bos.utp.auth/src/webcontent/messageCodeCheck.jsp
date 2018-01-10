<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<h:script src="/common/javascripts/eos-verifyCode.js"/>
<style>

BODY {
	color: #333333;
	background-image: url("<%=request.getContextPath()%>/common/skins/styles/default/images/abf/001_git.jpg");
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
	background-image: url("<%=request.getContextPath()%>/common/skins/styles/default/images/abf/login_git.jpg");
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
	background:url("<%=request.getContextPath()%>/common/skins/styles/default/images/abf/loginBtn.png") no-repeat;
	}
.logBtn1{ 
    font-family:微软雅黑;
    font-size:12px;
	width:67px; 
	height:21px; 
	color:#572c01; 
	border:0;
	cursor:pointer; 
	background:url("<%=request.getContextPath()%>/common/skins/styles/default/images/abf/loginBtn1.png") no-repeat;
	}
</style>
<%@page import="com.bos.utp.auth.bizlet.LogonUtil"%>
<html>
<head>
<title><b:message key="auth_login_title" /></title>
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
		   <l:equal property="authValue" value="-2">
		   <tr>
				<td valign='top' class='content'>
				<b:message key="auth_login_invaildpwd"/>
		   </td>
		   </tr>
		   </l:equal>

		   <l:equal property="authValue" value="-1">
		   <tr>
				<td valign='top' class='content'>
				<b:message key="auth_login_nothisaccount"/>
		   </td>
		   </tr>
		   </l:equal>
			<tr>
				<td width='681' height="405" valign='top' align="center">

				<h:form name="passwordChange" checkType="blur" method="post" action="/com.bos.utp.auth.netLogin.flow">
					<input type="hidden" name="_eosFlowAction" value="exit">
					<h:hidden property="choose_lang"/>
					<h:hidden property="empEmployees/mobileno"/>
					<l:notEqual scope="f" property="mesCode" targetValue="wrongPhone">
					<l:notEqual scope="f" property="mesCode" targetValue="false">
					<table align="right" border="0" width="400">
					    <tr>
					       <td colspan="3"  height="233">&nbsp;</td>
					    </tr>
						<tr>
							<td align="left" style="font-family:微软雅黑;font-size:12px;color:#000000;height:40px" colspan="3">
							<b:set name="no" property="empEmployees/mobileno"/>
					      		<% 
					      		String no=(String)request.getAttribute("no");
					      		if(no!=null&&!no.equals(""))
					      		no=no.substring(0,3)+"****"+no.substring(7,11);
					      		%>
					      		已向您号码为<a class="message"><%=no%></a>的手机发送校验码（时效为5分钟）
							</td>

						</tr>

						<tr>

							<td align="left" width="145" style="font-family:微软雅黑;font-size:16px;color:#000000;height:35px">请输入手机验证码：</td>

							<td width="115" height="45" ><h:text property="returnCode" size="15" maxlength="6"/></td>

							<td align="left"><input type="button" class="logBtn1" onclick="codeCheck(2)" value="重新发送"/></td>

						</tr>
						<%--  
						  if(LogonUtil.useVerifyCode()){
						--%>
						<tr>
						    <td colspan="3" align="center" style="height:35px">
						      	<input type="button" class="logBtn" onclick="codeCheck(0)" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        					<input type="button" class="logBtn" onclick="codeCheck(1)" value="返回"/>
						    </td>
						</tr>
						</table>
						</l:notEqual>
						</l:notEqual>
						<l:equal scope="f" property="mesCode" targetValue="wrongPhone">
					    	<table align="right" border="0" width="400">
					    		<tr>
					       			<td colspan="3"  height="233">&nbsp;</td>
					   			</tr>
					    		<tr>
					    			<td align="left" style="font-family:微软雅黑;font-size:14px;color:#000000;height:35px" colspan="3">
					    				您的个人信息中的手机号码未维护，请您在内网系统中维护您<br>的手机号码！
					    			</td>
					    		</tr>
					    		<tr >
							        <td colspan="3" align="center" style="height:35px">
							        	<input type="button" class="logBtn" onclick="codeCheck(1)" value="退出"/>
							        </td>
					      		</tr>
					    	</table>
					    </l:equal>
					    <l:equal scope="f" property="mesCode" targetValue="false">
					    	<table align="right" border="0" width="400">
					    		<tr>
					       			<td colspan="3"  height="233">&nbsp;</td>
					    		</tr>
					    		<tr>
					    			<td align="center" style="font-family:微软雅黑;font-size:14px;color:#000000;height:35px" colspan="3">
					    				手机验证码发送失败，如有问题请联系管理员！
					    			</td>
					    		</tr>
					    		<tr>
						    		<td colspan="3" align="center" style="height:35px">
							        	<input type="button" class="logBtn" onclick="codeCheck(1)" value="退出"/>
							        </td>
						        </tr>
					    	</table>
					    </l:equal>

				</h:form>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
</body>
<script type="text/javascript">
	 function codeCheck(value){
    		switch(value){
    			case 0:$name('_eosFlowAction').value='check';
    				   $name('passwordChange').submit();
    				   break;
    			case 1:$name('passwordChange').submit();
    				   break;
    			case 2:$name('_eosFlowAction').value='resend';
    				   $name('passwordChange').submit();
    				   break;
    		}
    	}
    	window.setTimeout(back,300000);
    	function back(){
    		codeCheck(1);
    	}
    	<% 
    		String error=(String)request.getAttribute("checkError");
    		if(error!=null&&error.equals("true")){
    	%>
    	alert("手机验证码输入错误！");
    	<%}%>
</script>
</html>
