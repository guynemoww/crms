<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page import="commonj.sdo.DataObject"%>
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>
    <title>绵阳银行信贷管理系统</title>
	<%@include file="/common/nui/common.jsp"%>  
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="<%=request.getContextPath()%>/mainframe/js/core.js" type="text/javascript"></script>
    <style type="text/css">
    html, body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
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
<%
UserObject user = (UserObject)session.getAttribute("userObject");
String userName = "";
if(user!=null){
	 userName = user.getUserName();
	 if (user.getUserOrgId() != null && user.getUserOrgId().contains(",")) {
	 	out.print("<script type=\"text/javascript\">self.location.href='"
	 		+ request.getContextPath() + "/select.jsp';</script>");
	 }
}
%>
    <div class="header">
    	<div style="height:72px;">        
	        <div><!-- logo.jpg -->
<img style="position:absolute;left:0px;top:10px;font-size:12px;font-weight:normal;height:53px;" src="<%=request.getContextPath()%>/mainframe/images/sh-bank-logo.jpg"></img>
	        <b style="position:absolute;left:200px;top:5px;font-family:微软雅黑;">信贷管理系统</b></div>
	        <div class="topNav">
	            <!-- <a href="<%=request.getContextPath()%>/mainframe/menubar/index.jsp">首页</a> | -->
	            <!--<a href="<%=request.getContextPath()%>/com.bos.utp.auth.Login.flow">首页</a> |-->
	            <a href="#" onclick="toHome();return false;">首页</a> |
	            <a href="<%=request.getContextPath()%>/utp/auth/logout.jsp">注销</a>
	        </div>
	        <div style="position:absolute;right:12px;top:40px;font-size:12px;line-height:25px;font-weight:normal;">
	        	<span class="font-1"><strong>您好，<%=userName %>&nbsp;<%=user.getUserOrgName() %>
	        	<%DataObject[] roles = (DataObject[])user.getAttributes().get("roles");
	        	if (null != roles && roles.length > 0) {
	        		out.print("【");
	        		for (int i=0; i<roles.length; i++) {
	        			DataObject role = roles[i];
	        			if (null == role.get("rolename"))
	        				continue;
	        			if (i != 0)
	        				out.print(", ");
	        			out.print(role.get("rolename"));
	        		}
	        		out.print("】");
	        	}
	        	%>
	        	</strong></span>        	
	        </div>
	     </div>
    </div>
    <ul id="menu1" class="nui-menubar" style="width:100%;"
            url="com.bos.utp.auth.LoginManager.getMenuData.biz.ext" onitemclick="onItemClick" 
            dataField="treeNodes" idField="id" parentField="pid" 
        >
    </ul>
    <div class="nui-fit" style="padding-top:5px;">
    	<iframe onload="onIFrameLoad()" src="<%=request.getContextPath()%>/mainframe/menubar/index2.jsp" id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
    </div>
    <div style="line-height:28px;text-align:center;cursor:default">Copyright © 绵阳银行版权所有 </div>
    <div style="text-align:center;display:<%="1".equals(request.getParameter("d")) ? "block" : "none" %>">
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmWorkitemMappingMaintain.flow?_eosFlowAction=query" target="_blank">工作项参数定义</a>
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmProcessMappingMaintain.flow?_eosFlowAction=query" target="_blank">工作流参数定义</a>
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmProcessInstanceMaintain.flow?_eosFlowAction=query" target="_blank">流程实例查询</a>
    	<a href="<%=request.getContextPath() %>/com.git.easyloan.bpm.TbWfmWorkitemInstanceMaintain.flow?_eosFlowAction=query" target="_blank">工作项实例查询</a>
    </div>
    
<script type="text/javascript">
    nui.parse();
    
    var contextPath = '<%=request.getContextPath()%>';
    var iframe = document.getElementById("mainframe");
   	function onItemClick(e){
   		var item = e.item;
   		var isLeaf = item.isLeaf;
   		if(isLeaf){
   			if (item.url.indexOf('/')==0)item.url=item.url.substr(1);
   			iframe.src = contextPath + "/" + item.url;
   		}
   	}
   	function toHome() {
   		iframe.src="<%=request.getContextPath()%>/mainframe/menubar/index2.jsp";
   	}
</script>
    
</body>
</html>