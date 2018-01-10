<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<html>
<head>
<%
	String contextPath = request.getContextPath();
	Object userObj = session.getAttribute("userObject");
	String skin = "default";
	String loginUserId = null;
	if (userObj != null) {
		UserObject userObject = (UserObject) userObj;
		loginUserId = userObject.getUserId();
		if (userObject.getAttributes().get("style") != null) {
			skin = (String) userObject.getAttributes().get("style");
		}
	}
	// 如果要设置客户端缓存页面，注释以下三行
	response.setHeader("Pragma", "No-Cache");
	response.setHeader("Cache-Control", "No-Cache");
	response.setDateHeader("Expires", 0);
%>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<script type="text/javascript" src="<%=contextPath%>/common/nui/nui.js"></script>
<script type="text/javascript" src="<%=contextPath%>/common/nui/json2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/common/nui/jquery.cookies.js"></script>
<!-- <script type="text/javascript" src="<%=contextPath%>/common/nui/nui-ext.js"></script> -->
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/common/nui/themes/<%=skin%>/skin.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/common/nui/themes/default/nui-ext-style.css" />
<script>
	/*  $(document).ready(function(){
		$("#acOperator/userid").watermark("请输入您的工号"); 
		$("#acOperator/password").watermark("请输入您的密码"); 
	}); */
	var requestURL = "<%=request.getRequestURL()%>";
	var requestURI = "<%=request.getRequestURI()%>";
	var servletPath = "<%=request.getServletPath()%>";
	$(function(){
		nui.context='<%=contextPath%>'
	});
	nui.DataTree.prototype.dataField='data';//兼容改造
</script>



<SCRIPT language=JScript event=OnObjectReady(objObject,objAsyncContext) for=foo>
if (objObject.IPEnabled != null && objObject.IPEnabled != "undefined" 
&& objObject.IPEnabled == true) { 
	if (objObject.MACAddress != null && objObject.MACAddress != "undefined") 
		MACAddr = objObject.MACAddress;
	
	if (objObject.IPEnabled 
		&& objObject.IPAddress(0) != null 
		&& objObject.IPAddress(0) != "undefined")
		IPAddr = objObject.IPAddress(0); 
	
	if (objObject.DNSHostName != null 
		&& objObject.DNSHostName != "undefined")
		sDNSName = objObject.DNSHostName;
} 

</SCRIPT>

<!-- 微软windows自带打印插件：打印时起作用的样式 -->
<style type="text/css" media=print>
.Noprint {
	display: none;
}

.PageNext {
	page-break-after: always;
}
</style>
<style>
*{
-moz-box-sizing:border-box;
-webkit-box-sizing:border-box;
-o-box-sizing:border-box;
-ms-box-sizing:border-box;
box-sizing:border-box;
}

BODY {
	background-image: url(<%=request.getContextPath()%>/mainframe/images/login_bg.jpg);
	height: 100%;
}

.userStype{
	width:270px;
	height:42px;
	line-height:42px;
	margin-left:12%;
	margin-top:9%;
	font-family: 微软雅黑;
	font-size: 16px;
	background: url(<%=request.getContextPath()%>/mainframe/images/333.png) no-repeat left;
	position:absolute;
	border:0;
	padding-left:50px;
	line-heighyt:42px;
}
.passStype{
	width:270px;
	height:42px;
	line-height:42px;
	margin-left:12%;
	margin-top:30%;
	font-family: 微软雅黑;
	font-size: 16px;
	background: url(<%=request.getContextPath()%>/mainframe/images/444.png) no-repeat left;
	position:absolute;
	border:0;
	padding:0 15px 0 50px;
}

.formStype{
	width: 350px;
	height: 370px;
	margin-left:64.3%;
	margin-top:10%;
	position:relative;
	background:url(<%=request.getContextPath()%>/mainframe/images/login_mainbg.png);

}
.loginBtn{
	margin-left:12%;
	margin-top:58%;
	width: 270px;
	height: 42px;
	line-height: 42px;
	background: #53cde4;
	border: none;   
	border-radius:3px;
	z-index:2;
	position:absolute;
	cursor: pointer;
}
.loginBtn:hover{background:#50c0e0}

.logDiv{
	width: 100%;
	height: 66px;
	background:url(<%=request.getContextPath()%>/mainframe/images/login_topbg.png) repeat;
}
.buspan{
	font-family: 微软雅黑;
	font-size: 16px;
	color: #FFFFFF;
}
.selectClassd{
	width: 270px;
	height: 42px;
	margin-top: 30%;
	background: url(<%=request.getContextPath()%>/mainframe/images/555.png) 0 -5px;
	margin-left:12%;
	z-index:1;
	position:absolute;
}
.orgStype{
	width: 210px;
    margin-left:17%;
	font-family: 微软雅黑;
	font-size: 16px;
	background: transparent;
	padding:8px 0;
	border:0;
}
.Logtitle{
	font-family: 微软雅黑;
	font-size: 20px;
	color:#ebedf2;
	margin-left:30%;
	z-index:1;
	position:relative;
}
.useridmap{
	width:270px;
	height:42px;
	line-height:42px;
	margin-left:12%;
	margin-top:30%;
	font-family: 微软雅黑;
	font-size: 16px;
	background: url(<%=request.getContextPath()%>/mainframe/images/888.png) no-repeat left;
	position:absolute;
	border:0;
	padding:0 15px 0 50px;
}

</style>
<title>用户登录</title>
</head>
<body>
	<div class="logDiv"><img style="margin-left: 30px;margin-top:16px;" src="<%=request.getContextPath()%>/mainframe/images/logo.png"></img></div>
	
	<div class="formStype" >
	<form id="form1" method="post" action="com.bos.utp.auth.Login.flow">
		<h1>&nbsp;</h1>
		<div class="Logtitle">信贷管理系统</div>
		<input type="hidden" name="_eosFlowAction" id="_eosFlowAction" value="submit" />
		<input name="acOperator/userid" style="vertical-align: middle;" id="acOperator/userid" class="userStype" size="23" 
			onvalidation="onCheckUserId()"
			placeholder="请输入您的工号"/>
		<input name="acOperator/password" type="password" id="acOperator/password" class="passStype" size="23" onenter="login" 
			onvalidation="onCheckPassword()" 
			placeholder="请输入您的密码"
			onkeypress="if(window.event && window.event.keyCode==13)login();"/>
		<div class="selectClassd" id="selectClassd" style="display: none;">
			<select id="orgid" name="orgid" class="orgStype"
			onkeypress="if(window.event && window.event.keyCode==13)selectOrg();">
			</select>
		</div>
		<input name="acOperator/useridmap" type="password" id="acOperator/useridmap" class="useridmap" size="23" style="display: none;"
			placeholder="请输入您的动态口令"
			onkeypress="if(window.event && window.event.keyCode==13)submitor();"/>
		<button type="button" class="loginBtn" id="selectMapBtn" onclick="submitor()" style="display: none;"><span class="buspan" id="gorqd">确   定</span></button>
	</form>
	<button type="button" class="loginBtn" id="selectOrgBtn" onclick="selectOrg()" style="display: none;"><span class="buspan" id="gorxz">机构选择</span></button>
	<button type="button" class="loginBtn" id="loginBtn" onclick="login();"><span class="buspan">登      录</span></button>
	</div>
	
	<div style="margin-top: 8%; font-size: 12px; cursor: default;margin-left: 40%;color: #CCCCCC">
			版权所有 ©2017  绵阳市商业银行 </div>
</body>
<script type="text/javascript">
	if(navigator.appName == 'Netscape'){
		$("#orgid").css('margin-top','');
		$(".logDiv").css('background','linear-gradient(to right,#F1E4D1,#DDDDDD)');
	}
	
	nui.parse();//userIDmapFu();
	var operatorid;
	 if (self != top)
	 	top.location.href=self.location.href;
	 	
 	var getnavigator = navigator.userAgent;
	if(!(getnavigator.indexOf('MSIE 8.0') > -1 
	||(getnavigator.indexOf('MSIE 7.0')>-1&&getnavigator.indexOf('Trident/4.0')>-1))){
		_alert('尊敬的用户，请使用IE8版本浏览器！');
	}
	
     document.getElementById("acOperator/userid").focus();
     function login(){
    	var addr = "<%=request.getRemoteAddr()%>";
    	var host = "<%=request.getRemoteHost()%>";
		var loginUserId = "<%=loginUserId %>";
		var passwd = document.getElementById("acOperator/password").value;
		var userid = document.getElementById("acOperator/userid").value;
		if (!passwd || !userid) {
			alert('请填写完整：用户名、密码');
			return;
		}
		//debugger;
		if(loginUserId && loginUserId!="null" && loginUserId!=userid){
			alert('当前浏览器已登陆其他用户，请先退出后再尝试登陆');
			return ;
		}
		
		git.mask();

		
		var acOperator = {
			'userid' : userid,
			'password' : passwd,
			'ipaddress' : addr
		};
		nui
				.ajax({
					url : "com.bos.utp.auth.LoginManager.login.biz.ext",
					type : "post",
					data : nui.encode({
						'acOperator' : acOperator
					}),
					contentType : "text/json",
					success : function(text) {
						git.unmask();
						
						if (text.retCode == '-1') {
							_alert('用户不存在');
							document.getElementById("acOperator/userid").focus();
							return;
						}
						if (text.retCode == '-2') {
							//_alert('输入密码错误');
							if(text.retMsg == '0'){
								_alert('输入密码错误，用户被锁定！');
							}else{
								_alert("输入密码错误，还有"+text.retMsg+"次用户将被锁定！");
							}
							document.getElementById("acOperator/password").focus();
							return;
						}
						if (text.retCode == '-3') {
							_alert('用户被锁定');
							document.getElementById("acOperator/userid").focus();
							return;
						}
						if (text.retCode == '-4') {
							_alert('用户被停用');
							document.getElementById("acOperator/userid").focus();
							return;
						}
						if (text.retCode == '-5') {
							_alert('未分配岗位或所在机构不是“运行中/营业”状态');
							document.getElementById("acOperator/userid").focus();
							return;
						}
						if (text.retCode == '-11') {
							//_alert('不允许多用户同时登录');
							_alert(text.retMsg);
							document.getElementById("acOperator/userid").focus();
							return;
						}
						if (text.retCode == '-9') {
							//_alert('系统正在跑批，请稍后登录');
							_alert(text.retMsg);
							document.getElementById("acOperator/userid").value = '';
							document.getElementById("acOperator/password").value = '';
							return;
						}
						if (text.retCode == '-22') {
							_alert('请设置IE安全级别,1.将 "ActiveX控件初始化并执行脚本" 设置为提示;   2.将 "下载未签名的Activex控件" 设置为提示!');
							document.getElementById("acOperator/userid").value = '';
							document.getElementById("acOperator/password").value = '';
							return;
						}
						document.getElementById("acOperator/password").disabled = 'disabled';
						document.getElementById("acOperator/userid").disabled = 'disabled';
						document.getElementById('loginBtn').style.display = 'none';
						///document.getElementById('resetBtn').style.display = 'none';
						///document.getElementById('orgTR').style.display = '';
						document.getElementById('selectOrgBtn').style.display = '';
						document.getElementById('selectClassd').style.display = '';
						self.orgmap = text.userObject.attributes.orgposimap;
						operatorid = text.userObject.attributes.operatorid;
						//dynamicswitch = text.userObject.attributes.dynamicswitch;
						var orgSelect = $('#orgid');
						orgSelect.html('');
						for ( var org in orgmap) {
							var orgid = org.substr(3);
							var orgname = orgmap[org].orgname;
							$(
									'<option value="'+orgid+'">' + orgname
											+ '</option>').appendTo(orgSelect);
						}
						orgSelect.focus();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						git.unmask();
						nui.alert(jqXHR.responseText);
					}
				});
	}
	
	function selectOrg(){
		git.mask();
		var orgjson = nui.encode( {
			"orgid":document.getElementById("orgid").value
		});
		nui.ajax({
			url : "com.bos.utp.auth.LoginManager.selectOrg.biz.ext",
			type : "post",
			data : orgjson,
			contentType : "text/json",
			success : function(text) {
				git.unmask();
				document.getElementById('selectOrgBtn').style.display = 'none';
				document.getElementById('selectClassd').style.display = 'none';
				document.getElementById('acOperator/useridmap').style.display = '';
				document.getElementById('selectMapBtn').style.display = '';
				document.getElementById("acOperator/useridmap").focus();
				/* if(dynamicswitch == "1"){
					document.getElementById('selectOrgBtn').style.display = 'none';
					document.getElementById('selectClassd').style.display = 'none';
					document.getElementById('acOperator/useridmap').style.display = '';
					document.getElementById('selectMapBtn').style.display = '';
					document.getElementById("acOperator/useridmap").focus();
				}else{
					$("#form1").submit();
				} */
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask();
				nui.alert(jqXHR.responseText);
			}
		});
	}
	//动态口令验证
	function submitor() {
		var operDt = document.getElementById("acOperator/useridmap").value;
		if(!operDt || operDt.length != 6){
			_alert("请输入6位动态口令密码！");
			return;
		}
		git.mask();
		var json = nui.encode( {"operatorid":operatorid,"operDt":operDt});
		nui.ajax({
			url : "com.bos.utp.auth.LoginManager.loginMapSwitch.biz.ext",
			type : "post",
			data :json,
			contentType : "text/json",
			success : function(text) {
				git.unmask();
				if(text.msg != "成功"){
					_alert(text.msg);
					return;
				}
				$("#form1").submit();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask();
				nui.alert(jqXHR.responseText);
			}
		});
	}
	function reset() {
		document.getElementById("acOperator/password").value = '';
		document.getElementById("acOperator/userid").value = '';
	}

	function onCheckUserId(e) {
		if (e.isValid) {
			if (e.value == "") {
				e.errorText = "用户名不能为空";
				e.isValid = false;
			}
		}
	}

	function onCheckPassword(e) {
		if (e.isValid) {
			if (e.value == "") {
				e.errorText = "密码不能为空";
				e.isValid = false;
			}
		}
	}

	//在用户名输入框回车时, 密码输入框获得焦点
	 /* function focusPassword() {
		if (!window.event || event.keyCode != 13)
			return true;
		document.getElementById("acOperator/password").focus();
	}
	document.getElementById("acOperator/userid").onkeypress = focusPassword();
	document.getElementById("acOperator/password").onkeypress = function() {
		if (!window.event || event.keyCode != 13)
			return true;
		//login();
	}  */
	
</script>

</html>