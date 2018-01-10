<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
<!-- 
  - Author(s): shangmf
  - Date: 2017-4-19 12:47:41
  - Description: 押品信息查询界面
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.bos.utp.tools.EncryptUtil"%>

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
String str = "creditFlag=1&userId="+((UserObject)session.getAttribute("userObject")).getUserId()+"&orgId="+((UserObject)session.getAttribute("userObject")).getUserOrgId();
//byte[] c = EncryptUtil.encrypt(str.getBytes("UTF-8"), "UmR/IymFLBM=");
//str = new String(c, "iso-8859-1");
//System.out.println("加密后字符串:"+str);
str = EncryptUtil.strToHexStr(str);
 %>

	<script>
	window.open('http://'+'<%=ipStr%>'+':'+'<%=portStr%>'+'/default/com.bob.bcms.information.CollInfo.flow?para=<%=str%>');
		//window.open('http://'+'<%=ipStr%>'+':'+'<%=portStr%>'+'/default/com.bob.bcms.information.CollInfo.flow?creditFlag=1&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>');
		//window.location.href='http://127.0.0.1:8080/default/com.bob.bcms.collateralmgr.CollMgr.flow?creditFlag=1&userId=1167';
	
	</script>  
</body>
</html>
