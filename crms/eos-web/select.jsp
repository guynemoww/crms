<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<%
	String contextPath = request.getContextPath();
	Object userObj = session.getAttribute("userObject");
	String skin = "default";
	String[] orgids = new String[0];
	String[] orgnames = new String[0];
	String userName = null;
	if(userObj != null){
		UserObject userObject = (UserObject)userObj;
		if(userObject.getAttributes().get("style") != null){
			skin = (String)userObject.getAttributes().get("style");
		}
		userName = (String)userObject.getUserId();
		Map map = (Map)userObject.getAttributes().get("orgposimap");
     	if (map != null) {
     		orgids = new String[map.keySet().size()];
     		orgnames = new String[orgids.length];
     		Iterator it = map.keySet().iterator();
     		int cnt=0;
     		while (it.hasNext()) {
     			String orgid = String.valueOf(it.next());
     			Map posimap = (Map)map.get(orgid);
     			
     			orgids[cnt] = orgid.substring(3);
     			orgnames[cnt] = String.valueOf(posimap.get("orgname"));
     			cnt++;
     		}
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
<!--<script type="text/javascript" src="<%=contextPath%>/common/nui/nui-ext.js"></script>-->
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
.tologClass{
	float:right; 
	margin-top: -28px;
	margin-right: 30px;
	
}
a{
	text-decoration:none;
	color:#22aacc;
}
</style>
<title>用户登录</title>
</head>
<body>
	<div class="logDiv">
		<img style="margin-left: 30px;margin-top:16px;" src="<%=request.getContextPath()%>/mainframe/images/logo.png"></img>
		<a href="#" class="tologClass" onclick="toLogout();return false;">注&nbsp;销</a>
	</div>
	
	<div class="formStype" >
	<form id="form1" method="post" action="com.bos.utp.auth.Login.flow">
		<h1>&nbsp;</h1>
		<div class="Logtitle">信贷管理系统</div>
		<input type="hidden" name="_eosFlowAction" id="_eosFlowAction"  value="submit"/>
		<input name="acOperator/userid" style="vertical-align: middle;" id="acOperator/userid" class="userStype" size="23" 
			value="<%=userName %>" disabled="disabled"/>
		<div class="selectClassd" id="selectClassd">
			<select id="orgid" name="orgid" class="orgStype">
				<%
				for (int i=0; i<orgids.length; i++) {
				%>
					<option value="<%=orgids[i] %>"><%=orgnames[i] %></option>
				<%} %>
			</select>
		</div>
		<button type="button" class="loginBtn" id="selectMapBtn" onclick="login()"><span class="buspan" id="gorqd">确定</span></button>
	</form>
	</div>
	
	<div style="margin-top: 8%; font-size: 12px; cursor: default;margin-left: 40%;color: #CCCCCC">
			版权所有 ©2017  绵阳市商业银行 </div>
</body>
<script type="text/javascript">
	if (self != top)
	 	top.location.href=self.location.href;
     nui.parse();
     function login(){
     	nui.ajax({
    		url: "com.bos.utp.auth.LoginManager.selectOrg.biz.ext",
    		type: "post",
    		data: nui.encode({"orgid":document.getElementById("orgid").value}),
    		contentType: "text/json",
    		success: function (text) {
    			$("#form1").submit();
    		}
		});
     	
     }
     document.getElementById('orgid').focus();
     document.getElementById('orgid').onkeypress=function(e){
     	if ((e && e.keyCode==13)
     		|| (window.event && window.event.keyCode==13)){
     		login();
     	}
     }
     
     var orgposimap = {
	     <%
		     if(userObj != null){
				UserObject userObject = (UserObject)userObj;
		     	Map map = (Map)userObject.getAttributes().get("orgposimap");
		     	if (map != null) {
		     		Iterator it = map.keySet().iterator();
		     		int cnt = 0;
		     		while (it.hasNext()) {
		     			String orgid = String.valueOf(it.next());
		     			Map posimap = (Map)map.get(orgid);
		     			if (cnt != 0)
		     				out.print(",");
		     			cnt++;
		     			out.print("org");
		     			out.print(orgid.substring(3));
		     			out.print(":[");
		     			if (null != posimap) {
		     				Iterator posiIt = posimap.keySet().iterator();
		     				int posiCnt=0;
		     				while (posiIt.hasNext()) {
		     					String posicode = String.valueOf(posiIt.next());
		     					if ("orgname".equals(posicode))
		     						continue;
		     					if (posiCnt > 0) {
		     						out.print(",");
		     					}
		     					String posiname = String.valueOf(posimap.get(posicode));
		     					out.print("{'posicode':'");
		     					out.print(posicode);
		     					out.print("','posiname':'");
		     					out.print(posiname);
		     					out.print("'}");
		     					posiCnt++;
		     				}
		     			}
		     			out.print("]");
		     		}
		     	}
		     }
	      %>
      };
      
    function toLogout() {
   		git.go("<%=request.getContextPath() %>/utp/auth/logout.jsp", top);
   	}
   	
     function orgChange() {
     	//var posi = document.getElementById("posi");
     	//var orgid = document.getElementById("orgid");
     	var orgid = $("#orgid").val();
     	var posi = $("#posi");
     	posi.html("");
     	//alert(nui.encode(orgposimap));
     	var positions = orgposimap['org'+orgid];
     	if (positions && positions.length > 0) {
     		for (var i=0; i<positions.length; i++) {
     			$('<option value="'+positions[i].posicode+'">'+positions[i].posiname+'</option>').appendTo(posi);
     		}
     	}
     }
     orgChange();
</script>

</html>