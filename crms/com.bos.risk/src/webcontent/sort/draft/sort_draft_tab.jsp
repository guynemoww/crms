<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<!--
		- Author(s): caohaijie - Date: 2015-06-17 09:44:44 - Description:
	-->
	<head>
		<title>分类客户列表</title>
		<%@include file="/common/nui/common.jsp"%>
	</head>
	<body>
		<center>
			<div id="tabs" class="nui-tabs" activeIndex="0" tabAlign="left" style="width:100%;hight:100%"></div>
		</center>

		<script>
			nui.parse();
			var tabs = nui.get("tabs");
			var partyId = "<%=request.getParameter("partyId")%>";
			var acApplyId = "<%=request.getParameter("acApplyId")%>";
			tabs.setTabs([
				{title:"借款人基本信息", url:nui.context+"/risk/sort/draft/sort_draft_basicInfo_select.jsp?acApplyId="+acApplyId, showCloseButton:false,refreshOnClick:true},
				{title:"押品信息", url:nui.context+"/risk/sort/draft/sort_draft_mort_list.jsp?acApplyId="+acApplyId,showCloseButton:false,refreshOnClick:true},
				{title:"借款人及保证人财务信息", url:nui.context+"/risk/sort/draft/sort_draft_guar_list.jsp?acApplyId="+acApplyId,showCloseButton:false,refreshOnClick:true}
			]);
			$("#tabs").show();
			git.unmask();
	</script>
</body>
</html>
