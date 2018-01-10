<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 
  - Author(s): WANGBING
  - Date: 2013-06-26 14:39:27
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>

<%
	//获取标签中使用的国际化资源信息
	String dictManagerLabel = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_title_main");
	String dictImportLabel =  com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_dict_import");
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<title>业务字典管理</title><!-- 业务字典导入管理 <b:message key="DictManager_l_dict_import"></b:message><b:message key="OperatorManager_l_manager"></b:message>-->
</head>
<body>
    <div id="mainTabs" class="nui-tabs bg-toolbar" activeIndex="0" style="width:100%;height:100%;" bodyStyle="border:0;background:white;">
	    <div title="业务字典管理" url="<%=request.getContextPath()%>/utp/dict/dict/dict_manager.jsp" ><!-- 业务字典管理 <%=dictManagerLabel%>-->
	    </div>
	    <div title="业务字典导入管理" url="<%=request.getContextPath()%>/utp/dict/dict/dict_import_manager.jsp" ><!-- 业务字典导入管理 <%=dictImportLabel%>-->
	    </div>
	</div>
</body>
</html>
