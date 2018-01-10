<%@page import="com.eos.foundation.data.DataObjectUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.eos.foundation.database.DatabaseUtil"%>
<%@page import="commonj.sdo.DataObject"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page pageEncoding="UTF-8"%>
<%
	
UserObject user = (UserObject)session.getAttribute("userObject");

session.invalidate();
response.sendRedirect(request.getContextPath() + "/login.jsp");
%>
