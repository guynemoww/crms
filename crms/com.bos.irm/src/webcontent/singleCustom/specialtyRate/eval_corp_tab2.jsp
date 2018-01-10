<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
<script type="text/javascript">
	nui.parse(); 
	//<%String corpid = request.getParameter("corpid"); %>
	var corpid = "<%=request.getParameter("corpid") %>";
	<%String rateType = request.getParameter("rateType"); %>
	
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"新增", url:nui.context+"/irm/singleCustom/specialtyRate/irm_professionRateList.jsp?corpid=<%=corpid %>&rateType=<%=rateType %>", showCloseButton:false,refreshOnClick:true},
			{title:"更新", url:nui.context+"/irm/singleCustom/specialtyRate/irm_professionReconsider.jsp?corpid=<%=corpid %>&rateType=<%=rateType %>",refreshOnClick:true},
			{title:"历史", url:nui.context+"/irm/singleCustom/specialtyRate/irm_ratingResHisRec.jsp?corpid=<%=corpid %>",refreshOnClick:true}
		]);
		$("#tabs").show();
	function create(url){
		//if(null == judgeRecordId){
		//	return;
		//}
	   // var url=nui.context+"/irm/singleCustom/specialtyRate/irm_professionJudgeAud.jsp?judgeRecordId="+judgeRecordId +"&partyId=" +corpid;
	    
		git.go(url);
	}
</script>
</body>
</html>
