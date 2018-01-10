<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<html>
<head>
<title> <b:message key="loginPolicyManager_l_title_configLoginLimit"></b:message> </title>

<%
	//登录限制配置
    String loginPolicyConfig = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_l_title_loginLimitConfig");
%>

</head>
<body leftmargin="0" topmargin="0">
    <w:tabPanel id="loginPolicyConfig" height="100%" width="100%">
        <w:tabPage id="operLogConfig" tabType="iframe" title="<%=loginPolicyConfig %>" url="com.bos.utp.auth.LoginPolicyManager.flow?_eosFlowAction=configpolicy" >
        </w:tabPage>
    </w:tabPanel>
</body>
</html>