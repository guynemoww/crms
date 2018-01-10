<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<%@page import="com.bos.bps.service.FlowService"%>
<%@page import="com.bos.workflow.webservice.client.data.BpsResultArray"%>
<html>
<%-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-02-25 10:31:55
  - Description:预警信号关闭
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>发起流程判断</title>
</head>
<body>
<% 
	String [] xpath={"isLevelEdit"};
	BpsResultArray ab=FlowService.getRelativeDataBatch(request.getParameter("processInstId"),xpath);
	String [] string=ab.getResult().getString();
 %>  <!-- 获取流程载体数据中其他KEY值 -->
</body>
<script type="text/javascript">
var flowType= "<%=request.getParameter("flowType") %>";    
var flow= "<%=request.getParameter("bizType") %>";    //判断流程类型
var bizId = "<%=request.getParameter("bizId") %>"; 
var isLevelEdit="<%=string[0] %>";

/** 
 1为新增信号复核，2为关闭信号复核
*/
if(flowType=="1"){
 var url=nui.context+"/ews/warnDetail/warnInfo/warnInfoReview/ews_warnInfo_add.jsp?corpid=<%=request.getParameter("corpid")%>&monitor=<%=request.getParameter("monitor") %>&type=<%=request.getParameter("type") %>";
 git.go(url);
} else if(flowType=="2"){
 var url=nui.context+"/ews/warnDetail/warnInfo/ews_warnInfo_close.jsp?bizId="+bizId+"&corpid=<%=request.getParameter("corpid")%>&monitor=<%=request.getParameter("monitor") %>&type=<%=request.getParameter("type") %>";
 git.go(url);
} else if(flow=="1"){
 var url=nui.context+"/ews/warnDetail/warnTree/ews_warnInfo_tree_addView.jsp?bizId="+bizId+"&isLevelEdit="+isLevelEdit+"&processInstId=<%=request.getParameter("processInstId") %>";
 git.go(url);
} else if(flow=="2"){
 var url=nui.context+"/ews/warnDetail/warnTree/ews_warnInfo_tree_closeView.jsp?bizId="+bizId;
 git.go(url);
} 
</script>
</html>