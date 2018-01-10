<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 10:09:57
  - Description:
-->
<head>
<title>合同入口页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
	nui.parse();
	<%String corpid = request.getParameter("corpid"); %>
		var tabs = nui.get("tabs");
		tabs.setTabs([
			{title:"综合授信协议", url:nui.context+"/crt/con_apply/con_apply_sxxy.jsp?partyId=<%=corpid %>&type=01",refreshOnClick:true},
			{title:"业务合同", url:nui.context+"/crt/con_apply/con_apply_ywht.jsp?partyId=<%=corpid %>&type=02",refreshOnClick:true},
			{title:"主合同调整", url:nui.context+"/crt/con_apply/con_apply_tz.jsp?partyId=<%=corpid %>&type=0",refreshOnClick:true},
			{title:"担保合同调整", url:nui.context+"/grt/subContractManage/subContarct_list.jsp?partyId=<%=corpid %>&type=0",refreshOnClick:true},
			<%-- {title:"补足保证金", url:nui.context+"/crt/con_apply/supply_bzj.jsp?partyId=<%=corpid %>&type=0",refreshOnClick:true}, --%>
			{title:"合同打印", url:nui.context+"/crt/con_apply/con_apply_print.jsp?partyId=<%=corpid %>&type=0",refreshOnClick:true}
		]);
		$("#tabs").show();
</script>
</body>
</html>