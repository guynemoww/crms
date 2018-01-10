<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%
UserObject user = (UserObject)session.getAttribute("userObject");
String userName = "";
if(user!=null){
	 userName = user.getUserName();
}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>绵阳银行统一技术平台应用框架</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="description" content="绵阳银行统一技术平台应用框架" />
    <script src="<%=request.getContextPath()%>/mainframe/js/core.js" type="text/javascript"></script>
    <style type="text/css">
    html, body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    .header
    {
        background:url(<%=request.getContextPath()%>/mainframe/images/header.png) repeat-x;
    }
    .header div
    {
        font-family:'Trebuchet MS',Arial,sans-serif;
        font-size:25px;line-height:60px;padding-left:10px;font-weight:bold;color:#333;        
    }    
    body .header .topNav
    {
        position:absolute;right:8px;top:10px;        
        font-size:12px;
        line-height:25px;
    }
    .header .topNav a
    {
        text-decoration:none;
        color:#222;
        font-weight:normal;
        font-size:12px;
        line-height:25px;
        margin-left:3px;
        margin-right:3px;
    }
    .header .topNav a:hover
    {
        text-decoration:underline;
        color:Blue;
    }   
     .nui-layout-region-south img
    {
        vertical-align:top;
    }
    </style>
</head>
<body>
<div class="nui-layout" style="width:100%;height:100%;">
    <div title="north" region="north" class="header" bodyStyle="overflow:hidden;" height="72px;" showHeader="false" showSplit="false">
        <div><img style="position:absolute;left:1px;bottom:2px;font-size:12px;font-weight:normal;" src="<%=request.getContextPath()%>/mainframe/images/logo.jpg"></img>
        <b style="position:absolute;left:12%;bottom:0px;font-family:微软雅黑;">统一技术平台应用框架</b></div>
        <div class="topNav">
            <a href="<%=request.getContextPath()%>/mainframe/default/index.jsp">首页</a> |
            <a href="<%=request.getContextPath()%>/utp/auth/logout.jsp">注销</a>
        </div>
        <div style="position:absolute;right:12px;bottom:5px;font-size:12px;line-height:25px;font-weight:normal;">
        	<span class="font-1"><strong>您好，<%=userName %></strong></span>        	
        </div>
<!-- 
        <div style="position:absolute;right:12px;bottom:5px;font-size:12px;line-height:25px;font-weight:normal;">
            <span style="color:Red;font-family:Tahoma">（推荐Blue）</span>选择皮肤：
            <select id="selectSkin" onchange="onSkinChange(this.value)" style="width:100px;" >
                <option value="">Default</option>
                <option value="blue">Blue</option>
                <option value="gray">Gray</option>
                <option value="olive2003">Olive2003</option>
                <option value="blue2003">Blue2003</option>
                <option value="blue2010">Blue2010</option>
            </select>
        </div>
 -->        
    </div>
    <div showHeader="false" region="south" style="border:0;text-align:center;" height="25" showSplit="false">
        Copyright © 绵阳银行版权所有 
    </div>
    <div region="west" title="应用框架" style="cursor: hand;" showHeader="true" bodyStyle="padding-left:0px;" showSplitIcon="true" width="230" maxWidth="230">
        <ul id="tree1" class="nui-outlookmenu" url="com.bos.utp.auth.LoginManager.getMenuData.biz.ext" showTreeIcon="true" style="width:100%;height:100%;"
             dataField="treeNodes" onitemclick="onItemSelect" enableHotTrack="true" onbeforeexpand="onBeforeExpand" >
        </ul>        
    </div>
    <div title="center" region="center" style="border:0;">
        <iframe onload="onIFrameLoad()" src="<%=request.getContextPath()%>/mainframe/overview.jsp" id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    nui.parse();
    
    var tree = nui.get("tree1");
    
    var iframe = document.getElementById("mainframe");
    
    function onBeforeExpand(e) {
        var tree = e.sender;
        var nowNode = e.node;
        var level = tree.getLevel(nowNode);

        var root = tree.getRootNode();        
        tree.cascadeChild(root, function (node) {
            if (tree.isExpandedNode(node)) {
                var level2 = tree.getLevel(node);
                if (node != nowNode && !tree.isAncestor(node, nowNode) && level == level2) {
                    tree.collapseNode(node, true);
                }
            }
        });

    }
    var contextPath = '<%=request.getContextPath()%>';
   	function onItemSelect(e){
   		var item = e.item;
   		var isLeaf = item.isLeaf;
   		if(isLeaf){
   			iframe.src = contextPath + "/" + item.url;
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
     
     $(window).unload(function (evt) {
     
        var json = "";
                   nui.ajax({
					type: 'POST',
					url: 'com.bos.utp.auth.LoginManager.deleteIp.biz.ext',
					data: json,
					cache:false,
					async: false,
					success: function(text) {
					}
				   });
         

	  });
</script>