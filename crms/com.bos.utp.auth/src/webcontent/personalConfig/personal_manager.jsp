<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<%
	//获取标签中使用的国际化资源信息
    String preference = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("personalsettingManager_l_preference");//首选项
%>

<html>
<head>
<title></title>
</head>
<body topmargin="0" leftmargin="0">
    <w:tabPanel id="personalManagerTab" height="100%" width="100%">
        <w:tabPage id="personalManager" tabType="iframe" title="<%=preference%>" url="com.bos.utp.auth.application.PersonalsettingManager.flow?_eosFlowAction=tab" >
        	<h:param property="operconfig/configname"/>
			<h:param property="operconfig/configvalue"/>
        </w:tabPage>
    </w:tabPanel>
</body>
</html>