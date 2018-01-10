<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 
  - Date: 2017-4-19 12:47:41
  - Description: 贷后重估页面
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
</head>
<body>
<!-- creditFlag=1&userId=1167 -->
<%
String module = "CollUrlConfig";
String group = "coll_url_server";
String ip = "ip";
String port = "port";
String ipStr = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
String portStr = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
 %>
	<script>
		window.open('http://'+'<%=ipStr%>'+':'+'<%=portStr%>'+'/default/com.bob.bcms.bscivalue.bscivaluePageflow.flow?creditFlag=1&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>');
		
	</script>  
</body>
</html>
