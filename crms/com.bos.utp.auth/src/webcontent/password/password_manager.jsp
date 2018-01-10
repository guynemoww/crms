<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<%
	//获取标签中使用的国际化资源信息
	String setPassword  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("OperatorManager_l_manager_pwd");  //密码设置
%>

<html>
<head>
<title></title>
</head>
<body topmargin="0" leftmargin="0">
    <w:tabPanel id="passwordManagerTab" height="100%" width="100%">
        <w:tabPage id="passwordManager" tabType="iframe" title="<%=setPassword%>" url="com.bos.utp.auth.PasswordChange.flow?_eosFlowAction=pdw" ><!-- 操作员管理 -->
        </w:tabPage>
    </w:tabPanel>
</body>
</html>