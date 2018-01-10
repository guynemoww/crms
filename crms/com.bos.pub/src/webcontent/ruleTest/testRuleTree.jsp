<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.bos.pub.DecisionUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): js1688
  - Date: 2014-09-23 09:37:23
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>批量测试</title>
</head>

<body>
<div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
 	<a class="nui-button" id="btn" onclick="btn">批量测试</a>
</div>
</body>
</html>
<script type="text/javascript">

function btn(){
	<%		
		DecisionUtil du=new DecisionUtil();
		du.ruletest();
	%>
}

</script>