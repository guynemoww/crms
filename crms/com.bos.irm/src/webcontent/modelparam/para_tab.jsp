<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>主标尺配置</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
		nui.parse();
		git.mask();
		var tabs = nui.get("tabs");
		tabs.setTabs([
			
			{title:"模型主标尺", url:nui.context+"/irm/modelparam/scaledef/item_list.jsp", showCloseButton:false},
			{title:"内外主标尺映射", url:nui.context+"/irm/modelparam/ratingmapping/item_list.jsp",showCloseButton:false}
			
		]);
		$("#tabs").show();
			git.unmask();
</script>
</body>
</html>