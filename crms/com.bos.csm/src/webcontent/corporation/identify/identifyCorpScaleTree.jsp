<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="layout1" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="企业规模认定" region="west" width="220" class="sub-sidebar" allowResize="false">
			<ul id="tree1" class="nui-tree" style="width: 100%;" showTreeIcon="true" textField="text" idField="id" expandOnLoad="true" onnodeclick="nodeclick">
			</ul>
		</div>
		<div title="center" region="center" style="wborder: 0; padding-left: 5px; padding-top: 5px; width: 60%;">
			<div id="orgTabs" class="nui-tabs  bg-toolbar" activeIndex="0" style="width: 98%; height: 100%; border: 0; margin-top: 10px;" refreshOnClick="true"></div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();

		var wflow =
	<%=JspUtil.getParameterHaveSign(request, "wflow")%>
		;
		var bizId =
	<%=JspUtil.getParameterHaveSign(request, "bizId")%>
		;
		var processId =
	<%=JspUtil.getParameterHaveSign(request, "processInstId")%>
		;
		var edit = wflow == "1" ? "2"
				:
	<%=JspUtil.getParameterHaveSign(request, "edit", "1")%>
		;
		var partyId = null;

		var menudata = new Array();//树形菜单

		initMenuTree();
		nodeclick({
			"node" : menudata[0]
		});
		function initMenuTree() {

			var flowModuleType = "18";//影像模板节点类型（参考下面的映射表，多个节点以英文“，”分隔）
			var view = new Array();

			$
					.ajax({
						url : "com.bos.csm.corporation.corporation.getIdentifyCorpScale.biz.ext",
						type : 'POST',
						data : nui.encode({
							"scaleId" : bizId
						}),
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(text) {
							if (text.msg) {
								alert(text.msg);
							} else {
								partyId = text.scale.partyId;
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							nui.alert(jqXHR.responseText);
						}
					});
			menudata[menudata.length] = {
				id : "企业规模认定",
				text : "企业规模认定",
				url : "/csm/corporation/identify/identifyCorpScale.jsp?bizId="
						+ bizId + "&partyId=" + partyId + "&edit=" + edit
			};
			menudata[menudata.length] = {
				id : "客户基本概况信息",
				text : "客户基本概况信息",
				url : "/csm/corporation/csm_corporation_edit.jsp?partyId="
						+ partyId + "&qote=1"
			};

			//意见提交
			if ("2" == wflow) {
				menudata[menudata.length] = {
					id : "意见",
					text : "意见",
					url : "/com.bos.bps.service.workFlowAdvice.flow?bizId="
							+ bizId + "&processInstId=" + processId
							+ "&isSrc=2&"
				};
			}
			var tree = nui.get("tree1");
			tree.loadData(menudata);
		}

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