<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 11:46:31
  - Description:
-->
<head>
<title>移交申请树菜单</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="layout" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="不良资产移交" region="west" width="240" class="sub-sidebar" allowResize="false">
			<ul id="tree" class="nui-tree" style="width: 300px; padding: 5px;" showTreeIcon="true" textField="text" idField="id" expandOnLoad="true" onnodeclick="nodeclick">
			</ul>
		</div>
		<div title="center" region="center" style="border: 0; padding-top: 5px;">
			<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width: 98%; height: 100%; border: 0;" refreshOnClick="true"></div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var wflow =
	<%=JspUtil.getParameterHaveSign(request, "wflow")%>
		;

		var edit = wflow == "1" ? "2"
				:
	<%=JspUtil.getParameterHaveSign(request, "edit")%>
		;
		var bizId =
	<%=JspUtil.getParameterHaveSign(request, "bizId")%>
		; //协议申请ID
		var processInstId =
	<%=JspUtil.getParameterHaveSign(request, "processInstId")%>
		;

		initMenu();

		function initMenu() {
			var menudata = new Array();
			menudata[menudata.length] = {
				id : "移交信息",
				text : "移交信息",
				url : "/asset/transfer/transfer_edit.jsp?transferId="
						+ bizId + "&edit=" + edit
			};
			if (wflow == "2") {
				menudata[menudata.length] = {
					id : "意见",
					text : "意见",
					url : "/com.bos.bps.service.workFlowAdvice.flow?bizId="
							+ bizId + "&processInstId=" + processInstId
							+ "&isSrc=2"
				};
			}
			var tree = nui.get("tree");
			tree.loadData(menudata);
			nodeclick({
				"node" : menudata[0]
			});
		}

		//默认打开
		function nodeclick(e) {
			if (!e.node['url']) {
				return;
			}
			var tabs = nui.get("orgTabs");
			tabs.setTabs([ {
				title : e.node.text,
				url : nui.context + e.node.url
			} ]);
			$("#orgTabs").show();
		}
	</script>
</body>
</html>