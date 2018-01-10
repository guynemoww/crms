<%@page pageEncoding="UTF-8"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-05-21 
  - Description:
-->
<head>
<title>我的客户</title>
</head>
<body>
<%
UserObject user = (UserObject)session.getAttribute("userObject");
//是否显示小贷中心客户(orgdegree=2)
String orgdegree =(String)user.getAttributes().get("orgdegree");
//是否显示同业客户(R1003)
String tyShow ="false";
DataObject[] roles = (DataObject[])user.getAttributes().get("roles");
if (null != roles && roles.length > 0) {

	for (int i=0; i<roles.length; i++) {
		DataObject role = roles[i];
		if("R1003".equals(role.get("roleid"))){
			tyShow ="true";
		}else{
			continue;
		}
	}	        			
}
//out.print("xdShow="+xdShow+",tyShow="+tyShow);
 %>

<div id="tabs1" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"	style="width:100%;height:100%;border:0;" refreshOnClick="true">
	<div title="公司客户" url="../../csm/workdesk/mycust_corporation.jsp"></div>
<!-- <div title="小贷中心客户" url="../../csm/workdesk/mycust_private.jsp"></div> -->
<div title="自然人客户" url="../../csm/workdesk/mycust_natural.jsp"></div>
<div title="集团客户" url="../../csm/workdesk/mycust_group.jsp"></div>
<div title="第三方客户" url="../../csm/workdesk/mycust_third.jsp"></div>
<div title="同业客户" url="../../csm/workdesk/mycust_financial.jsp"></div>
</div> 
</body>
</html>