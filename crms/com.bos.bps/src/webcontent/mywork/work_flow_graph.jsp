<%@page pageEncoding="UTF-8" import="com.eos.workflow.api.BPSServiceClientFactory,com.bos.bps.util.FlowConstants,com.eos.foundation.eoscommon.ConfigurationUtil,com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<%@ taglib uri="http://eos.primeton.com/tags/workflow" prefix="wf"%>
<script src="<%=request.getContextPath()%>/workflow/wfcomponent/web/js/Graphic.js"></script>
<html>
<!-- 
  - Author(s): lijianfei
  - Date: 2014-03-26 10:36:24
  - Description:
-->
<head>
<title>流程图页面</title>
</head>
<body>
<%
	IUserObject user = CommonUtil.getIUserObject();
	String userID =user.getUserId();
	String userName = user.getUserName();
	String tenantid = ConfigurationUtil.getContributionConfig(FlowConstants.CONTRIBUTION_NAME,
	FlowConstants.BPS_MODULE_NAME,FlowConstants.BPS_GROUP_NAME,FlowConstants.BPS_KEY_TENANTID);
	BPSServiceClientFactory.getLoginManager().setCurrentUser(userID, userName, tenantid, "");
	String processInstId = request.getParameter("processInstId");
%>

<wf:processGraph serverID="default" processInstID="<%=processInstId%>"/>
</body>
</html>