<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<title> <b:message key="loginlimitManager_l_title_configLoginLimit"></b:message> </title>

<%
	//Protal模式管理
    String portalManager = "Protal"
                         + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("portalManager_l_title_resourceManager");
%>

</head>
<body leftmargin="0" topmargin="0">
    <w:tabPanel id="loginlimitConfig" height="100%" width="100%">
        <w:tabPage id="operLogConfig" tabType="iframe" title="<%=portalManager %>" url="com.bos.utp.auth.PortalManager.flow?_eosFlowAction=portal" >
        </w:tabPage>
    </w:tabPanel>
</body>
</html>