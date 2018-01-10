<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<%
	//获取标签中使用的国际化资源信息
	String fileManager = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileManager");
	String fileUpload =  com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileUpload");
%>
<html>
<head>
<title><b:message key="FileuploadManager_l_title_fileManager"></b:message></title>
</head>
<body topmargin="0" leftmargin="0">
    <w:tabPanel id="fileuploadManagerTab" height="100%" width="100%">
        <w:tabPage id="filemanager" tabType="iframe" title="<%=fileManager%>" url="com.bos.utp.tools.FileUploadManager.flow?_eosFlowAction=enterManager" >
        </w:tabPage>
        <w:tabPage id="fileupload" tabType="iframe" title="<%=fileUpload%>" url="com.bos.utp.tools.FileUploadManager.flow?_eosFlowAction=enterUpload" >
        </w:tabPage>
    </w:tabPanel>
</body>
</html>
