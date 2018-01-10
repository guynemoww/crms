<%@page pageEncoding="UTF-8" import="java.util.*" %>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): lijianfei
  - Date: 2014-03-17 11:15:02
  - Description: 审批页面，分为业务信息页面，审批意见页面两个tab页
-->
<head>
<title>历史详情页面</title>
</head>
<%

	String processInstId = request.getParameter("processInstId");
	String bizType = request.getParameter("bizType");
	String bizId = request.getParameter("bizId");
	String processStatus = request.getParameter("processStatus");
	String processDefName = request.getParameter("processDefName");
	String para = "bizId="+bizId+"&bizType="+bizType+"&processInstId="+processInstId+"&processStatus="+processStatus;
	request.setAttribute("para",para);
%>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:100%;" >
	
	<div title="基本信息" url="<%=contextPath%>/<%=request.getParameter("viewUrl")%>?wflow=1&<%=para%>" refreshOnClick="false"></div>
	<%--<% 
		if(null !=processDefName && processDefName.indexOf("biz")!=-1){
	%>
		<div title="授权结果" url="<%=contextPath%>/pub/createProcess/processLog.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>" refreshOnClick="false"></div>
	<%
		}
	%>--%>
	<div title="过程历史" url="<%=contextPath%>/bps/mywork/work_flow_timeline_his.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>" refreshOnClick="false"></div>
	</div>
</html>