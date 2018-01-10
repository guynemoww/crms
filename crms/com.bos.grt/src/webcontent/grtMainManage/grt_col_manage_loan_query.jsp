<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): syq
  - Date: 2017-4-19 12:47:41
  - Description: 关联贷款信息查询界面
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
</head>
<body>
<%
String module = "CollUrlConfig";
String group = "coll_url_server";
String ip = "ip";
String port = "port";
String ipStr = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
String portStr = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
 %>
<!-- creditFlag=1&userId=1167 -->
	<script>
		var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.CollMgr.flow?creditFlag=1&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>&senceCode=1&ifApp=2";
		window.open(url); 
	</script>  
</body>
</html>
