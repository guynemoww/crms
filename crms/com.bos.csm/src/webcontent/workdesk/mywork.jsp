<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2013-08-17 00:02:17
  - Description:
-->
<head>
<title>我的工作</title> 
</head>
<body>
<ul id="treeMenu" class="nui-contextmenu" onbeforeopen="onBeforeOpen">
	<li onclick="refresh" iconCls="icon-reload">刷新</li>
</ul>
<div id="tabs1" class="nui-tabs" activeIndex="0"
	style="width:100%;height:100%;" contextMenu="#treeMenu">
	<div title="待办列表" url="../../bps/mywork/workinglist.jsp?flowTypeCd=<%=request.getParameter("flowTypeCd")%>" refreshOnClick="false"></div>
	<div title="跟踪列表" url="../../bps/mywork/workedlist.jsp" refreshOnClick="false"></div>
	<div title="提示列表" url="../../pub/remind/remind_total.jsp" refreshOnClick="true"></div>
	<%--<div title="业务待审" url="../../biz/bizPendingList.jsp" refreshOnClick="true"></div>--%>
	<%--<div title="集团成员进度" url="../../biz/biz_membersProgress_list.jsp" refreshOnClick="true"></div>--%>
</div>
<script type="text/javascript">
var currentTab = null;
function onBeforeOpen(e) {
            currentTab = nui.get("tabs1").getTabByEvent(e.htmlEvent);
            if (!currentTab) {
                e.cancel = true;                
            }
        }
function refresh() {
	if (!!currentTab)
		nui.get("tabs1").reloadTab(currentTab);
}
</script>
</body>
</html>
