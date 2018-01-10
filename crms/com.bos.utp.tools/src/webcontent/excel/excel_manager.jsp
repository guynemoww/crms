<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<%
	//获取标签中使用的国际化资源信息
	String excelMgr_l_impMgr  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("excelMgr_l_impMgr");//通用Excel导入
%>
<html>
<head>
<title></title>
</head>
<body topmargin="0" leftmargin="0">
    <w:tabPanel id="excelManagerTab" height="100%" width="100%">
        <w:tabPage id="excelManager" tabType="iframe" title="<%=excelMgr_l_impMgr%>" url="com.bos.utp.tools.ExcelMgr.flow?_eosFlowAction=tab" >
        </w:tabPage>
    </w:tabPanel>
</body>
</html>