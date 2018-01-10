<%@page pageEncoding="UTF-8"%>
<%
String filePath = request.getParameter("filePath");
System.out.println("filePath:"+filePath);
 %>
<html>
<!-- 
  - Author(s): 武立松
  - Date: 2014-06-10 14:04:50
  - Description:合同信息预览
-->
<head>
<title>合同信息预览</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/common/flexpaper/flexpaper.js"></script>
<script type="text/javascript" src="<%=contextPath%>/common/flexpaper/flexpaper_handlers.js"></script>
</head>
<body>
			<div id="documentViewer" class="flexpaper_viewer"
				style="width: 100%; *width: 100%; height: 100%"></div>
			<script type="text/javascript">
				$('#documentViewer').FlexPaperViewer(
				        { config : {
				            SWFFile : '<%=contextPath%>/document/docx/temp/<%=filePath%>',
				            Scale : 0.6,
				            ZoomTransition : 'easeOut',
				            Zoom:1,
				            ZoomTime : 0.5,
				            ZoomInterval : 0.2,
				            FitPageOnLoad : true,
				            FitWidthOnLoad : true,
				            FullScreenAsMaxWindow : false,
				            ProgressiveLoading : false,
				            MinZoomSize : 0.2,
				            MaxZoomSize : 5,
				            SearchMatchAll : false,
				            InitViewMode : 'Portrait',
				            RenderingOrder : 'flash',
				            StartAtPage : '',
				            ViewModeToolsVisible : true,
				            ZoomToolsVisible : true,
				            NavToolsVisible : true,
				            CursorToolsVisible : true,
				            SearchToolsVisible : true,
				            WMode : 'window',
				            localeChain: 'zh_CN',
				            jsDirectory:'<%=contextPath%>/common'
				        }}
				);
			</script>
	</body>
</html>