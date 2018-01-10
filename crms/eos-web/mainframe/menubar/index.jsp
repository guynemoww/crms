<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page import="commonj.sdo.DataObject"%>
<%@page import="java.util.Map"%>
<meta http-equiv="X-UA-Compatible" content="IE =8"/>
<html xmlns="http://www.w3.org/1999/xhtml">  

<head>
    <title>绵阳商行信贷管理系统</title>
	<%@include file="/common/nui/common.jsp"%>  
    <!-- <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> -->
    <script src="<%=request.getContextPath()%>/mainframe/js/core.js" type="text/javascript"></script>
    <style type="text/css">
HTML {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 100%; PADDING-RIGHT: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 100%; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
BODY {
	PADDING-BOTTOM: 0px; BORDER-RIGHT-WIDTH: 0px; MARGIN: 0px; PADDING-LEFT: 0px; WIDTH: 100%; PADDING-RIGHT: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 100%; OVERFLOW: hidden; BORDER-LEFT-WIDTH: 0px; PADDING-TOP: 0px
}
.header DIV {
	LINE-HEIGHT: 60px; PADDING-LEFT: 10px; FONT-FAMILY: 'Trebuchet MS',Arial,sans-serif; COLOR: #333; FONT-SIZE: 25px; FONT-WEIGHT: bold
}
BODY .header .topNav {
	POSITION: absolute;
	LINE-HEIGHT: 25px;
	FONT-SIZE: 12px;
	TOP: 10px;
	RIGHT: 8px;
	vertical-align: middle;
}
.header .headerbg .topNav img {
	margin-right: 3px;
}
.header .topNav A {
	LINE-HEIGHT: 25px; COLOR: #222; MARGIN-LEFT: 3px; FONT-SIZE: 12px; FONT-WEIGHT: normal; MARGIN-RIGHT: 3px; TEXT-DECORATION: none
}
.header .topNav A:hover {
	COLOR: blue; TEXT-DECORATION: underline
}
.nui-layout-region-south IMG {
	VERTICAL-ALIGN: top
}
.Copyright {
	background: url(<%=request.getContextPath()%>/mainframe/images/bottom_bg.png) repeat-x;
	font-family: Verdana, "微软雅黑", Tahoma, "宋体";
	font-size: 12px;
	line-height: 28px;
	height: 28px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-right-color: #dddddd;
	border-bottom-color: #dddddd;
	border-left-color: #dddddd;
}
.headerbg {
	background-image: url(<%=request.getContextPath()%>/mainframe/images/sh_BG.jpg);
	background-repeat: repeat-x;
}
.headerlogo {
	background-image: url(<%=request.getContextPath()%>/mainframe/images/sh_BG2.jpg);
	background-repeat: no-repeat;
	background-position: 0px 0px;
	margin-left: -20px;
}
.indexDiv{
	width: 100%;
	height: 66px;
	background:#FFFFFF;
}
.tbox{
	width:300px;
	height:100px;
	background:#CCC;
	border:1px solid #333;
	padding:12px;
	text-align:center;
	margin-top: 20%;
}
#nav{
	width:100px;
	height:50px;
	border:1px solid #d4cd49;
	position:fixed;
	left:0;
	top:10%;
}
.nav{
	position:absolute;
	top:3%;
	left:40%;
	width:180px;
	height:60px;
	background-color:#fffafa;
	z-index:1001;
	-moz-opacity:0.8;
	opacity:80;
	border:1px solid #7CCFE2;
	<%-- background:url(<%=request.getContextPath()%>/mainframe/images/777.png) no-repeat right; --%>
	border-right-width:6px;
	border-right:9px solid url(<%=request.getContextPath()%>/mainframe/images/777.png);
}
.onmoudiv{
	top:10px;
	right:40px;
	border:1px solid #7CCFE2;
	border-radius:2px;
	width:200px;
	height:auto;
	line-height:22px;
	padding:10px;
	position:absolute;
	z-index:1001;
	background-color:#fffafa;
	display:none;
}
.sanjiao{
	height:15px;
	width:10px;
	background:url(<%=request.getContextPath()%>/mainframe/images/jiao.png);
	right:-10px;
	top:15px;
	position:absolute;
}

.head_ico{
    background:url(<%=request.getContextPath()%>/mainframe/images/ico_name.png) no-repeat;
    width:30px;
    height:30px;
    margin-top:17px;
    
}
s

    </style>   
</head>
<body>
<%
UserObject user = (UserObject)session.getAttribute("userObject");
String userName = "";
//else if (user.getAttributes().get("posicode") == null) { //未选择岗位
	 //	out.print("<script type=\"text/javascript\">top.location.href='"
	 	//	+ request.getContextPath() + "/select.jsp';</script>");
	 //}
if(user!=null){
	 userName = user.getUserName();
	 if (user.getUserOrgId() != null && user.getUserOrgId().contains(",")) {//未选择机构
	 	out.print("<script type=\"text/javascript\">top.location.href='"
	 		+ request.getContextPath() + "/select.jsp';</script>");
	 } else {
	 	Map orgposimap = (Map)user.getAttributes().get("orgposimap");
	 	if (null != orgposimap) {
	 		if (orgposimap.size() == 0) {//无岗位： 登出系统
	 			out.print("<script type=\"text/javascript\">_alert('该用户未分配岗位');git.go(nui.context + \"/utp/auth/logout.jsp\", top);</script>");
	 		} else {//有岗位：选择机构、岗位
	 			//out.print("<script type=\"text/javascript\">self.location.href='"
	 			//	+ request.getContextPath() + "/select.jsp';</script>");
	 		}
	 	}
	 }
}
%>
    <div style="height:65px;" class="indexDiv">
     
    <div style="position: relative;height:65px;line-height:65px;float:right;margin-right:10px;z-index:10">
    	<div style=" float:left; position: relative;">
    	 <div class="onmoudiv" id="onmoudiv">
    	  <span class="indexAsty"><%=userName %>，您好&nbsp;&nbsp;&nbsp;<%=user.getUserOrgName() %></span>
    	  <%DataObject[] roles = (DataObject[])user.getAttributes().get("roles");
      	  if (null != roles && roles.length > 0) {
    		  for (int i=0; i<roles.length; i++) {
    			  DataObject role = roles[i];
    			  if (null == role.get("rolename"))
    				  continue;
    			  if ((i+1)%2 == 1){
    				  out.print("<br>");
    	 		  }
    			  out.print("<img src="+request.getContextPath()+"/mainframe/images/view1.png>&nbsp;</img><span class='indexAsty'>"+role.get("rolename")+"&nbsp;&nbsp;&nbsp;</span>");
    	  	  }
      	  }
      	  %>
    	  <div class="sanjiao"></div>
        </div>
    	<div class="head_ico" onmouseover="dispalyBox()" onmouseout="disappearBox()"></div>
    		</div>
        <div style=" float:left;"> 
   		<span class="indexAsty"><%=userName %>，您好&nbsp;&nbsp;&nbsp;&nbsp;</span>
   		<!-- <span onclick="toHome();return false;">首页</span> | -->
   		<a href="#" onclick="toHome();return false;" class="indexAsty">首页&nbsp;</a>| 
        <a href="#" onclick="toPasswordChange();return false;" class="indexAsty">修改密码&nbsp;</a>|
        <a href="#" onclick="toPositionSelect();return false;" class="indexAsty">切换机构&nbsp;</a>|
        <a href="#" onclick="toMyInfo();return false;" class="indexAsty">个人信息&nbsp;</a>|
       <!--  <a href="#" onclick="dkhdlist();return false;" class="indexAsty">&nbsp;回单打印&nbsp;</a> | 
        <a href="#" onclick="toAccInfo();return false;" class="indexAsty">账户余额查询&nbsp;</a>|-->
        <a href="<%=request.getContextPath()%>/com.bos.utp.auth.Login.flow?_eosFlowAction=logout" class="indexAsty">安全退出&nbsp;</a>|
        <span class="indexAsty">营业日期：<%=datestr %></span>
        </div>
    </div>
      
      <img style="height: 35px;margin:15px 0 0 30px;" src="<%=request.getContextPath()%>/mainframe/images/logo.png">
    </div>
    
    <ul id="menu1" class="nui-menubar" style="width:100%;"
            url="com.bos.utp.auth.LoginManager.getMenuData.biz.ext" onitemclick="onItemClick" 
            dataField="treeNodes" idField="id" parentField="pid" 
        >
   	</ul>
    <div class="nui-fit" style="padding-top:5px;">
    	<iframe onload="onIFrameLoad()" src="<%=request.getContextPath()%>/mainframe/menubar/index2.jsp" id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
    </div>
    <div style="line-height:28px;text-align:center;cursor:default;color:#A09C9C;" class="Copyright">版权所有 ©2017  绵阳市商业银行 </div>
    <div style="text-align:center;display:<%="1".equals(request.getParameter("d")) ? "block" : "none" %>">
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmWorkitemMappingMaintain.flow?_eosFlowAction=query" target="_blank">工作项参数定义</a>
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmProcessMappingMaintain.flow?_eosFlowAction=query" target="_blank">工作流参数定义</a>
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmProcessInstanceMaintain.flow?_eosFlowAction=query" target="_blank">流程实例查询</a>
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmWorkitemInstanceMaintain.flow?_eosFlowAction=query" target="_blank">工作项实例查询</a>
    </div>
    
<script type="text/javascript">
    nui.parse();
    if(navigator.appName == 'Netscape'){
		<!-- $(".onmoudiv").css('right','49%'); -->
	}
    var contextPath = '<%=request.getContextPath()%>';
    var iframe = document.getElementById("mainframe");
   	function onItemClick(e){
   		var item = e.item;
   		var isLeaf = item.isLeaf;
   		if(isLeaf){
   			if (item.url.indexOf('/')==0)
   				item.url=item.url.substr(1);
   			//iframe.src = contextPath + "/" + item.url;
   			if (item.url.indexOf('?')>0)
   				item.url += '&_nui_client_time='+(+(new Date()));
   			else
   				item.url += '?_nui_client_time='+(+(new Date()));
   			git.go(contextPath + "/" + item.url, iframe);
   		}
   	}
   	function toHome() {
   		//iframe.src=contextPath + "/mainframe/menubar/index2.jsp";
   		git.go(contextPath + "/mainframe/menubar/index2.jsp", iframe);
   	}
   	function toPasswordChange() {
   		git.go(contextPath + "/com.bos.utp.auth.PasswordChange.flow", iframe);
   	}
   	function toPositionSelect() {
   		git.go(contextPath + "/select.jsp");
   	}
   	function toMyInfo() {
   		git.go(contextPath + "/com.bos.utp.org.empinfoManager.EditEmployeeContact.flow", iframe);
   	}
   	
   	//账户余额查询
   	function toAccInfo(){
   	
		nui.open({
	            url: nui.context + "/crt/accountInfo/account_query.jsp",
	            showMaxButton: false,
	            title: "账户余额查询",
	            width: 1000,
	            height: 350,
	            ondestroy: function (action) {
	            
	            }
	        });   	
   	
   	}
   	function toLogout() {
   		git.go(contextPath + "/utp/auth/logout.jsp", top);
   	}
   	function getMenuItems() {//此方法给本文件夹下的index2.jsp页面判断快捷菜单是否在当前登录角色的菜单中时使用
   		if (top.menuItems) 
   			return top.menuItems;
   		var menuItems = [];
   		
   		var its = nui.get("menu1").getItems();
   		for (var i=0; i<its.length; i++) {
   			if (its[i].url)
   				menuItems[menuItems.length] = {text:its[i].text, url:its[i].url};
   			if (its[i].menu && its[i].menu.items) {
   				getChildItems(its[i].menu.items, menuItems);
   			}
   		}
   		top.menuItems = menuItems;
   		return top.menuItems;
   	}
   	function getChildItems(its, menuItems) {
   		for (var i=0; i<its.length; i++) {
   			if (its[i].url)
   				menuItems[menuItems.length] = {text:its[i].text, url:its[i].url};
   			if (its[i].menu && its[i].menu.items) {
   				getChildItems(its[i].menu.items, menuItems);
   			}
   		}
   	}
   	   	$(function(){
   		 //整个项目中只调用一次，根据用户id获取所有的功能权限列表以及对应的操作
  		 initMain();
   	 });
   	 
   	 function initMain(){
       var arr = new Array();
       arr[USER_ID] = "<%=((UserObject)session.getAttribute("userObject")).getUserId()%>";//验证阶段，默认使用初始化用户id
       window[GLOBAL_CONTEXT] = arr;//初始化全局权限容器
       //整个项目中只调用一次，根据用户id获取所有的功能权限列表以及对应的操作
       nui.loadAuthResList('<%=((UserObject)session.getAttribute("userObject")).getUserId()%>');//获取权限资源列表
     }
     
     //贷款回单
     function dkhdlist(){
			nui.open({
				url:nui.context +"/pay/payout_apply/pay_hd_list.jsp",
				title: "回单信息", width: 1000, height: 500,
	            onload: function () {
	            },
	            ondestroy: function (action) {
	                  grid.reload();
	            }
			});
		}
     
   	 //关闭或刷新浏览器时，注销登录
   	 /*
   	 $(window).bind('beforeunload',function(evt){
   		var json = "";
	    nui.ajax({
			type: 'POST',
			//url: 'com.bos.utp.auth.LoginManager.deleteIp.biz.ext',
			url:'com.bos.utp.auth.LoginManager.logout.biz.ext',
			data: json,
			cache:false,
			async: false,
			success: function(text) {
			}
	    });
   	 });*/
   	 function dispalyBox(){
   	 	//document.getElementById("onmoudiv").style.disply = "block";
   	 	$("#onmoudiv").show();
   	 }
   	 function disappearBox(){
   	 	//document.getElementById("onmoudiv").style.disply = "none";
   	 	$("#onmoudiv").hide();
   	 }
</script>
    
</body>
</html>