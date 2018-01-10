<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:11:52
  - Description:
-->
<head>
<title>意见页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<script type="text/javascript">
	nui.parse();
	initPage();
	function initPage(){
		var json = nui.encode({"processInstId":"<%=request.getParameter("processInstId")%>"});
		$.ajax({
		    url: "com.bos.npl.assets.AssetsDispositionScheme.getBpsNode.biz.ext",
		    type: 'POST',
		    data: json,
		    cache: false,
		    contentType:'text/json',
		    cache: false,
		    success: function (mydata) {
		    		var node = mydata.node;
   					var url = nui.context + "/bps/mywork/work_flow_advice.jsp?processInstId="+node.processInstId+"&processDefName="+node.processDefName;
					url+="&activityDefId="+node.activityDefId+"&activityInstId="+node.activityInstId+"&activityInstName="+git.toUrlParam(node.activityInstName);
					url+="&workItemId="+node.workItemId+"&ruleID="+node.ruleID+"&selectType="+node.selectType+"&conclusion="+git.toUrlParam(node.conclusion);
					url+="&isSrc=2&workitemMappingId="+node.workitemMappingId+"&templateVersion="+node.templateVersion+"&startTime="+node.startTime+"&doUrl="+node.doUrl;
   					window.location.replace(url);
		    }
		})
	}
</script>
</body>
</html>