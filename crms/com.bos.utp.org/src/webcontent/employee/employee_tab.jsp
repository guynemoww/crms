<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>员工信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
<script type="text/javascript">
	nui.parse();
	<%
	String empid = request.getParameter("empid"); 
	String inorgid = request.getParameter("inorgid"); 
	String view = request.getParameter("view"); 
	%>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{ title: '人员信息', url: nui.context+'/utp/org/employee/employee_basicinfo_update.jsp?action=update&empid=<%=empid %>&orgId=<%=inorgid %>&view=<%=view %>' },
			/*{ title: '角色信息', url: nui.context+'/utp/org/employee/employee_rightsmgr.jsp?view=<%=view %>&data=<%=request.getParameter("roleData") %>' },*/
			{ title: '角色信息', url: nui.context+'/utp/org/employee/employee_orglist.jsp?view=<%=view %>&data=<%=request.getParameter("roleData") %>' },
			{ title: '岗位及挂职机构信息', url: nui.context+'/utp/org/employee/emp_posi_list.jsp?empid=<%=empid %>&orgId=<%=inorgid %>&view=<%=view %>&data=<%=request.getParameter("roleData") %>' }
		]);
		$("#tabs").show();
		
//		top["win"]=window;
		function closeWin(){
		  CloseWindow("ok");
		}
</script>
</body>
</html>
