<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.primeton.bfs.engine.json.JSONObject"%>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="layout1" class="nui-layout" style="width: 100%; height: 100%;">
		<div title="处置方案" region="west" width="220" class="sub-sidebar" allowResize="false">
			<ul id="tree1" class="nui-tree" style="width: 100%;" showTreeIcon="true" textField="text" idField="id" expandOnLoad="true" onnodeclick="nodeclick">
			</ul>
		</div>
		<div title="center" region="center" style="border: none">
			<div id="orgTabs" class="nui-panel" activeIndex="0" style="width: 100%; height: 100%;" refreshOnClick="true"></div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		//wflow:2-可提交意见，1-不可提交意见
		var wflow =
	<%=JspUtil.getParameterHaveSign(request, "wflow", "1")%>
		;
		//edit:0-新增,1-编辑,2-查看
		var edit =
	<%=JspUtil.getParameterHaveSign(request, "edit", "1")%>
		;

		var planSimp;// 页面数据对象
		initMenuTree();

		function initMenuTree() {
			var planId =
	<%=JspUtil.getParameterHaveSign(request, "bizId")%>
		;
			var json = nui.encode({
				"nameSql" : "com.bos.asset.handle.HandleSql.handleSimp",
				"param" : {
					"planId" : planId
				}
			});
			$.ajax({
				url : "com.bos.pub.dao.queryByNameSql.biz.ext",
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(datas) {
					if (datas.datas) {
						planSimp = datas.datas[0];
						if (planSimp.status != "10") {
							edit = "2";
						}
						createMenu();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				}
			});
		}

		function createMenu() {
			var menudata = new Array();//树形菜单

			var key = planSimp.planType;
			if (planSimp.cleanTakeType) {
				key += "_" + planSimp.cleanTakeType;
			}
			var childrens = [ {
				id : "处置方案信息",
				text : "处置方案信息",
				url : "/asset/handle/handle_edit.jsp?" + getParam()
			} ];

			if ("30" == key) {
				childrens[childrens.length] = {
					id : "核销详情",
					text : "核销详情",
					url : "/asset/handle/plan/verifyOff.jsp?" + getParam()
				};
			} else if ("20" == key) {
				childrens[childrens.length] = {
					id : "抵债资产冲销详情",
					text : "抵债资产冲销详情",
					url : "/asset/handle/plan/writeOff.jsp?" + getParam()
				};
			} else if ("10_10" == key) {
				childrens[childrens.length] = {
					id : "现金清收详情",
					text : "现金清收详情",
					url : "/asset/handle/plan/cleanTakeMoney.jsp?" + getParam()
				};
			} else if ("10_20" == key) {
				childrens[childrens.length] = {
					id : "诉讼清收详情",
					text : "诉讼清收详情",
					url : "/asset/handle/plan/cleanTakeLaw.jsp?" + getParam()
				};
			} else if ("10_30" == key) {
				childrens[childrens.length] = {
					id : "委外清收详情",
					text : "委外清收详情",
					url : "/asset/handle/plan/cleanTakeEntrust.jsp?"
							+ getParam()
				};
			}
			if (childrens.length < 2) {
				alert("错误的业务参数:" + planSimp.planType + ","
						+ planSimp.cleanTakeType);
				return;
			}
			menudata[menudata.length] = {
				id : "处置方案",
				text : "处置方案",
				children : childrens
			};

			menudata[menudata.length] = {
				id : "影像资料",
				text : "影像资料",
				children : createView()
			};

			//意见提交
			if (wflow == "2") {
				menudata[menudata.length] = {
					id : "意见",
					text : "意见",
					url : "/com.bos.bps.service.workFlowAdvice.flow?bizId="
							+ planSimp.planId + "&processInstId="
							+ planSimp.processId + "&isSrc=2&"
				};
			}
			var tree = nui.get("tree1");
			tree.loadData(menudata);
			nodeclick({
				"node" : menudata[0].children[0]
			});
		}
		function getParam() {
			return "planId=" + planSimp.planId + "&edit=" + edit + "&enable="
					+ (edit == "0" || edit == "1" ? "true" : "false");
		}

		function createView() {
			var view = new Array();
			var flowModuleType = "18";//影像模板节点类型（参考下面的映射表，多个节点以英文“，”分隔）
			var ismove = "0";//是否移迁数据  1是 0 否
			if (edit == "0" || edit == "1") {
				view[view.length] = {
					id : "影像扫描",
					text : "影像扫描",
					url : "/pub/imagePlatform/item_tree.jsp?ismove=" + ismove
							+ "&businessNumber=" + planSimp.planId + "&csmNum="
							+ planSimp.partyNum
							+ "&partyTypeCd=01&flowModuleType="
							+ flowModuleType + "&view=1"
				};
			} else {
				view[view.length] = {
					id : "影像查询",
					text : "影像查询",
					url : "/pub/imagePlatform/item_tree.jsp?ismove=" + ismove
							+ "&businessNumber=" + planSimp.planId + "&csmNum="
							+ planSimp.partyNum
							+ "&partyTypeCd=01&flowModuleType="
							+ flowModuleType + "&view=2"
				};
			}
			return view;
		}

		function nodeclick(e) {
			if (!e.node['url']) {
				return;
			}
			var tabs = nui.get("orgTabs");
			tabs.load(nui.context + e.node.url);
			tabs.setTitle(e.node.text);
			$("#orgTabs").show();
			return;
		}
	</script>
</body>
</html>