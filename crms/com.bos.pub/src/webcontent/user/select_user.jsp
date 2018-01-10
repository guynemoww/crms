<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 
  - Description:
-->
<head>
<title>查询客户经理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		//orgMode->机构查询模式 random:所有机构,legorg:法人代码下所有机构 
		//userMode->用户查询模式 manager:客户经理
		//clear->是否 可以清除[1=可以,0=不可以],默认允许清除 
		boolean clear = "1".equals(JspUtil.getParameter(request, "clear",
				"1"));
	%>
	<div class="nui-fit" style="overflow: hidden;">
		<div id="form1" class="nui-form">
			<div style="padding-left: 20px">
				<label>所属机构：</label>
				<input id="item.orgNum" name="item.orgNum" allowInput="false" style="width: 160px" class="nui-buttonEdit" onbuttonclick="selectOrg" dictTypeId="org" />
				<label>用户编号：</label>
				<input name="item.userNum" style="width: 130px" class="nui-textbox" />
				<label>用户名称：</label>
				<input name="item.userName" style="width: 130px" class="nui-textbox" />
				<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			</div>
			<input id="item.legOrg" name="item.legOrg" class="nui-hidden" />
			<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.pub.user.usersql.selectUser" />
		</div>
		<div class="nui-fit">
			<div id="datagrid1" class="nui-datagrid" style="width: 100%; height: 100%;" sortMode="client" url="com.bos.pub.user.UserEos.searchUser.biz.ext" dataField="items" allowAlternating="true" multiSelect="false" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" width="40">选择</div>
					<div field="userNum" headerAlign="center" width="60">用户编号</div>
					<div field="userName" " headerAlign="center" width="100">用户名称</div>
					<div field="orgNum" headerAlign="center" width="60">机构编号</div>
					<div field="orgName" headerAlign="center" width="150">机构名称</div>
				</div>
			</div>
		</div>
		<div id="formAction" class="nui-toolbar" style="text-align: center; border: none">
			<a class="nui-button" iconCls="icon-ok" onclick="onOk">确定</a>
			<span style="display: inline-block; width: 25px;"></span>
			<a class="nui-button" iconCls="icon-cancel" onclick="onCancel">取消</a>
			<%
				if (clear) {
			%>
			<span style="display: inline-block; width: 25px;"></span>
			<a class="nui-button" iconCls="icon-cancel" onclick="onClear">清空</a>
			<%
				}
			%>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var formAction = new nui.Form("#formAction");
		var grid = nui.get("datagrid1");
		var selectRow;
		var orgMode =
	<%=JspUtil.getParameterHaveSign(request, "orgMode")%>
		;
		var userMode =
	<%=JspUtil.getParameterHaveSign(request, "userMode")%>
		;
		var orgNum =
	<%=JspUtil.getParameterHaveSign(request, "orgNum")%>
		;
		var clear =
	<%=JspUtil.getStrHaveSign(clear ? "1" : "0")%>
		;
		initSelectUserPage();

		query();

		function initSelectUserPage() {
			debugger;
			//当没有传入机构编号时，需要根据机构查询模式进行设置，没有配置模式时取当前登陆机构
			if (!orgNum) {
				if ("random" == orgMode) {
				} else if ("legorg" == orgMode) {
					nui.get("item.legOrg").setValue(
	<%=JspUtil.getStrHaveSign(GitUtil.getLegorg())%>
		);
				} else {
					orgNum =
	<%=JspUtil.getStrHaveSign(GitUtil.getCurrentOrgCd())%>
		;
				}
			}
			if (orgNum) {
				var button = nui.get("item.orgNum");
				button.setValue(orgNum);
				button.setEnabled(false);
			}
		}

		function selectOrg(e) {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/sys/select_org_tree.jsp?clear="
						+ (orgMode ? ("1&searchMode=" + orgMode) : "0"),
				showMaxButton : true,
				title : "选择机构",
				width : 350,
				height : 450,
				ondestroy : function(action) {
					debugger;
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.GetData();
						if (data) {
							btnEdit.setText(null);
							btnEdit.setValue(data.orgcode);
						}
					} else if (action == "clear") {
						btnEdit.setValue(null);
						btnEdit.setText(null);
					}
				}
			});
		}

		function query() {
			var o = form.getData();
			if (userMode) {
				o.item.searchMode = userMode;
			}
			grid.mask();
			grid.load(o, function() {
				grid.unmask();
			});
		}

		function getData() {
			return nui.clone(selectRow);
		}

		function onOk() {
			formAction.loading();
			selectRow = grid.getSelected();
			if (!selectRow) {
				alert("请选择一条数据");
				formAction.unmask();
				return;
			}
			CloseWindow("ok");
		}

		function onCancel() {
			CloseWindow("cancel");
		}

		function onClear() {
			CloseWindow("clear");
		}
	</script>
</body>
</html>