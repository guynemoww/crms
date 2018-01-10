<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<%
	//获取标签中使用的国际化资源信息
	String dictManagerLabel = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_title_main");
	String dictImportLabel =  com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_dict_import");
%>
<html>
<head>
<title><b:message key="DictManager_l_dict_import"></b:message><b:message key="OperatorManager_l_manager"></b:message></title><!-- 业务字典导入管理 -->
</head>
<body topmargin="0" leftmargin="0">
    <w:tabPanel id="operatorManagerTab" height="100%" width="100%">
        <w:tabPage id="dictManager" tabType="iframe" title="<%=dictManagerLabel%>" url="com.bos.utp.tools.DictManager.flow?_eosFlowAction=dictManager" ><!-- 业务字典导入管理 -->
        </w:tabPage>
        <w:tabPage id="dictImport" tabType="iframe" title="<%=dictImportLabel%>" url="com.bos.utp.tools.DictManager.flow?_eosFlowAction=dictImport" ><!-- 业务字典导入管理 -->
        </w:tabPage>
    </w:tabPanel>
</body>
</html>
