
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-26 09:00:58
  - Description:
-->
<head>
<title>品种组添加</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div class="nui-fit">
		<%
			String edit = request.getParameter("edit");
			boolean enabled = !"2".equals(edit);
		%>
		<div id="form">
			<input id="item.groupId" class="nui-hidden nui-form-input" name="item.groupId" />
			<input id="group.groupId" class="nui-hidden nui-form-input" name="group.groupId" />
			<div style="padding: 5px; padding-left: 20px; text-align: left;">
				<label>风险限额组名称：</label>
				<input name="group.groupName" id="group.groupName" required="true" enabled="<%=enabled%>" class="nui-textbox" style="width: 150px" vtype="maxLength:50" />
				<label style="color: blue;">注意:风险限额组只有在包含机构限额类型的情况下才能起效</label>
			</div>
		</div>
		<%
			if (enabled) {
		%>
		<div class="nui-toolbar">
			<a class="nui-button" id="biz_meeting_add2" iconCls="icon-add" enabled="<%=enabled%>" onclick="addRow">添加</a>
			<a class="nui-button" id="biz_meeting_remove" iconCls="icon-remove" enabled="<%=enabled%>" onclick="del">删除</a>
		</div>
		<%
			}
		%>
		<div class="nui-fit">
			<div id="grid" class="nui-datagrid" style="height: 100%;" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" pageSize="50" allowAlternating="true" allowCellEdit="<%=enabled%>" allowCellValid="true" allowCellSelect="true" multiSelect="true" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="aa" type="indexcolumn" headerAlign="center" allowSort="false">序号</div>
					<div field="LIMIT_CODE_TEXT" width="150" headerAlign="center">
						风险限额项目
						<input id="riskLookup" property="editor" class="nui-popupedit" allowInput="false" showPopupOnClick="true" textField="LIMIT_CODE_TEXT" popupWidth="auto" popup="#riskPanel" />
					</div>
					<div field="LIMIT_AMT" width="100" headerAlign="center" dataType="currency">
						风险限额
						<input property="editor" class="nui-textbox" allowInput="true" dataType="currency" />
					</div>
					<div field="UPDATE_DATE" width="100" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">更新时间</div>
					<div field="UPDATE_USER_NUM" width="80" headerAlign="center" dictTypeId="user">更新人</div>
					<div field="UPDATE_ORG_NUM" width="100" headerAlign="center" dictTypeId="org">更新机构</div>
				</div>
			</div>
		</div>
		<%
			if (enabled) {
		%>
		<div class="nui-toolbar" style="height: 35px; text-align: right; padding-top: 15px; padding-right: 25px;" borderStyle="border:0;">
			<a class="nui-button" id="group_save" iconCls="icon-save" enabled="<%=enabled%>" onclick="save">保存</a>
			<a class="nui-button" id="" iconCls="icon-cancel" enabled="<%=enabled%>" onclick="closeWindow">关闭</a>
		</div>
		<%
			}
		%>
	</div>
	<div id="riskPanel" class="nui-panel" iconCls="icon-add" style="width: 600px; height: 350px; display: none;" showHeader="false" bodyStyle="padding:0" borderStyle="border:0">
		<jsp:include page="/crd/risk/div/risk_list_div.jsp?edit=3"></jsp:include>
	</div>
	<%
		String groupId = request.getParameter("groupId");
		if (groupId != null) {
			groupId = "\"" + groupId + "\"";
		}
	%>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var grid = nui.get("grid");
		var groupId =
	<%="0".equals(edit) ? null : groupId%>
		;
		nui.get("riskGrid").on("rowclick", function(risk) {
			if (risk && risk.row) {
				var selected = nui.clone(risk.row);
				var cell = grid.getCurrentCell();
				grid.cancelEdit();
				var temp = {
					NEW_LIMIT_ID : selected.LIMIT_ID,
					LIMIT_TYPE : selected.LIMIT_TYPE,
					LIMIT_CODE : selected.LIMIT_CODE,
					LIMIT_CODE_TEXT : selected.LIMIT_CODE_TEXT
				};
				if (temp.LIMIT_TYPE == "10") {
					temp.LIMIT_AMT = selected.LIMIT_AMT;
				}
				grid.updateRow(cell[0], temp);
			}
		});

		initPage();
		//初始化
		function initPage() {
			if (groupId) {
				var json = nui.encode({
					"groupId" : groupId
				});
				$.ajax({
					url : "com.bos.crd.LimitService.getRiskGroup.biz.ext",
					type : 'POST',
					data : json,
					contentType : 'text/json',
					cache : false,
					success : function(mydata) {
						var o = nui.decode(mydata);
						form.setData(o);
					}
				});
				loadGrid();
			}
		}
		function loadGrid() {
			var formData = form.getData();
			formData.item.groupId = groupId;
			formData.sqlName = "com.bos.crd.LimitService.searchRiskGroupDetail";
			debugger;
			grid.load(formData);
		}

		//新增一行信息
		function addRow() {
			var newRow = {
				name : "New Row"
			};
			grid.addRow(newRow, 0);
		}

		function del() {
			var rows = grid.getSelecteds();
			var json = new Array();
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].DETAIL_ID) {
					json[json.length] = rows[i].DETAIL_ID;
				}
			}
			if (json.length > 0) {
				if (!nui.confirm("删除原有数据会刷新界面，有未保存数据请先保存，是否确定删除？", "询问")) {
					return;
				}
				json = nui.encode({
					"detailIds" : json
				});
				$
						.ajax({
							url : "com.bos.crd.LimitService.removeRiskGroupDetail.biz.ext",
							type : 'POST',
							data : json,
							cache : false,
							async : false,
							contentType : 'text/json',
							success : function(mydata) {
								if (mydata.msg) {
									showMsg(mydata.msg);
									return;
								}
								alert("删除成功！");
							},
							error : function(jqXHR, textStatus, errorThrown) {
								nui.alert(jqXHR.responseText);
							}
						});
				loadGrid();
			} else if (rows.length > 0) {
				grid.removeRows(rows, true);
			}
			return;
		}

		function save() {
			//校验
			if (form.isValid() == false) {
				nui.alert("请按规则填写");
				return;
			}
			var data = form.getData();
			if ("" == data.group.groupName) {
				alert("组名称不能为空！");
				return false;
			}
			data.details = grid.getChanges();
			for (var i = data.details.length - 1; i > -1; i--) {
				if (!data.details[i].LIMIT_ID && !data.details[i].NEW_LIMIT_ID) {
					data.details.splice(i, 1);
				} else if (!data.details[i].LIMIT_AMT) {
					alert("第[" + (i + 1) + "]行，请输入风险限额");
					return;
				}
			}
			var json = nui.encode({
				"group" : data.group,
				"details" : data.details
			});

			$.ajax({
				url : "com.bos.crd.LimitService.saveRiskGroup.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(mydata) {
					if (mydata.msg) {
						showMsg(mydata.msg);
						nui.get("biz_enter_info_save").setEnabled(true);
						return;
					}
					alert("保存成功！");
	<%if ("0".equals(edit)) {%>
		closeWindow();
	<%} else {%>
		loadGrid();
	<%}%>
		},
				error : function(jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function closeWindow() {
			CloseWindow('ok');
		}

		function showMsg(msg) {
			if (msg.substr(0, 1) == "{" && msg.substr(msg.length - 1, 1) == "}") {
				var error = nui.decode(msg);
				nui.alert(error.msg);
			} else {
				nui.alert(msg);
			}
		}
	</script>
</body>
</html>