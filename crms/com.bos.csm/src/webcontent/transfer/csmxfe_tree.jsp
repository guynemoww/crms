<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>客户移交菜单树</title>
<%@include file="/common/nui/common.jsp"%>
<%@include file="/csm/transfer/csmxfe_common.jsp"%>
</head>
<body>
	<div id="layout" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="" region="west" width="240" class="sub-sidebar" allowResize="true">
			<ul id="tree" class="nui-tree" style="width: 300px; padding: 5px;" showTreeIcon="true" textField="text" idField="id" expandOnLoad="true" onnodeclick="nodeclick">
			</ul>
		</div>
		<div title="center" region="center" style="border: 0; padding-top: 5px;">
			<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width: 98%; height: 100%; border: 0;" refreshOnClick="true"></div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var tree = nui.get("tree");
		var menudata;
		var proFlag =
	<%=JspUtil.getParameterHaveSign(request, "proFlag")%>
		;//1：可修改。0：不可修改
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "bizId")%>
		var processInstId=
	<%=JspUtil.getParameterHaveSign(request, "processInstId")%>
		;
		var csmxfe;
		initPage();
		function initPage() {
			csmxfe = getCsmxfe(transferId);
			initMenu();
		}
		function initMenu() {
			var menudata = [
					{id : "基本信息",text : "基本信息",url : "/csm/transfer/csmxfe_edit.jsp?transferId="+ transferId + "&proFlag=" + proFlag},
					{id : "经理信息",text : "经理信息",url : "/csm/transfer/csmxfe_user.jsp?transferId="+ transferId + "&transferType="+csmxfe.transferTypeCd},
					{id : "客户信息",text : "客户信息",url : "/csm/transfer/csmxfe_party.jsp?transferId="+ transferId + "&transferType="+csmxfe.transferTypeCd},
					{id : "批复信息",text : "批复信息",url : "/csm/transfer/csmxfe_approve.jsp?transferId="+ transferId + "&transferType="+csmxfe.transferTypeCd},
					{id : "合同信息",text : "合同信息",url : "/csm/transfer/csmxfe_contract.jsp?transferId="+ transferId +"&transferType="+csmxfe.transferTypeCd},
					{id : "借据信息",text : "借据信息",url : "/csm/transfer/csmxfe_summary.jsp?transferId="+ transferId + "&transferType="+csmxfe.transferTypeCd},
					{id : "担保信息",text : "担保信息",url : "/csm/transfer/csmxfe_subcontract.jsp?transferId="+ transferId +"&transferType="+csmxfe.transferTypeCd}
			];
			if ("-1" != proFlag && processInstId) {
				menudata[menudata.length] = {
					id : "意见",
					text : "意见",
					url : "/com.bos.bps.service.workFlowAdvice.flow?bizId="
							+ transferId + "&processInstId=" + processInstId
							+ "&isSrc=2&"
				};
			} 
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