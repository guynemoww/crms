<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): lijianfei
  - Date: 2014-03-17 11:15:02
  - Description: 审批页面，分为业务信息页面，审批意见页面两个tab页
-->
<head>
<title>审批页面</title>
</head>
<%
	String processInstId = request.getParameter("processInstId");
	String workItemId = request.getParameter("workItemId");
	String conclusion = request.getParameter("conclusion");
	String ruleID = request.getParameter("ruleID");
	String activityDefId = request.getParameter("activityDefId");
	String activityInstId = request.getParameter("activityInstId");
	String processDefName = request.getParameter("processDefName");
	String activityInstName = java.net.URLEncoder.encode(request.getParameter("activityInstName"), "UTF-8");
	String selectType = request.getParameter("selectType");
	String orgLvCd = request.getParameter("orgLvCd");
	String doUrl = request.getParameter("doUrl");
	String workitemMappingId = request.getParameter("workitemMappingId");
	String templateVersion = request.getParameter("templateVersion");
	String userVariable = request.getParameter("userVariable");
	String startTime = request.getParameter("startTime");
	String wfBackOperPositionId = request.getParameter("wfBackOperPositionId");
	String partyNum = request.getParameter("partyNum");
	String postCode = request.getParameter("postNum");
	//意见结论单独页签
	String para = "processInstId=" + processInstId + "&workItemId=" + workItemId + "&conclusion=" + java.net.URLEncoder.encode(conclusion, "UTF-8");
	para += "&ruleID=" + ruleID + "&activityDefId=" + activityDefId + "&activityInstId=" + activityInstId + "&processDefName=" + processDefName;
	para += "&activityInstName=" + activityInstName + "&selectType=" + selectType + "&orgLvCd=" + orgLvCd + "&doUrl=" + doUrl + "&workitemMappingId=" + workitemMappingId;
	para += "&templateVersion=" + templateVersion + "&startTime=" + startTime;
	para += "&partyNum=" + partyNum;
	//合并意见结论页面的情况
	String bizId = request.getParameter("bizId");
	String bizType = request.getParameter("bizType");
	String approveType = request.getParameter("approveType");
	String para2 = "bizId=" + bizId + "&bizType=" + bizType + "&processInstId=" + processInstId + "&processDefName=" + processDefName;
	para2 += "&activityDefId=" + activityDefId + "&workItemId=" + workItemId + "&activityInstId=" + activityInstId + "&activityInstName=" + activityInstName;
	para2 += "&startTime=" + startTime + "&approveType=" + approveType + "&wfBackOperPositionId=" + wfBackOperPositionId + "&partyNum=" + partyNum + "&postCode=" + postCode;

	request.setAttribute("para2", para2);
	request.setAttribute("para", para);
	//out.print(url);
%>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">

		<%
			if ("2".equals(userVariable)) {
		%>
		<div title="基本信息" url="<%=contextPath%>/<%=request.getParameter("viewUrl")%>?wflow=2&bizId=<%=request.getParameter("bizId")%>&bizType=<%=request.getParameter("bizType")%>&processInstId=<%=request.getParameter("processInstId")%>&approveType=<%=request.getParameter("approveType")%>&activityDefId=<%=request.getParameter("activityDefId")%>" refreshOnClick="false"></div>
		<div title="意见结论" url="work_flow_advice.jsp?<%=para%>" refreshOnClick="true"></div>
		<%
			} else {
		%>
		<div title="基本信息" url="<%=contextPath%>/<%=request.getParameter("viewUrl")%>?wflow=2&qote=1&<%=para2%>" refreshOnClick="false"></div>
		<%--<div title="授权结果" url="<%=contextPath%>/pub/createProcess/processLog.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>&ruleID=<%=request.getParameter("ruleID")%>" refreshOnClick="false"></div>--%>
		<%
			}
		%>
		<div title="过程历史" url="<%=contextPath%>/bps/mywork/work_flow_timeline_his.jsp?bizId=<%=request.getParameter("bizId")%>&processInstId=<%=request.getParameter("processInstId")%>" refreshOnClick="false"></div>
	</div>
</html>