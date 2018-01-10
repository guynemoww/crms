<%@page pageEncoding="UTF-8"%>
<html>

<head>
<title>资产证券化管理</title>
<%@include file="/common/nui/common.jsp"%>

</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"style="width: 100%; height: auto;">
		<div title="资产证券化项目列表">
		<div id="form" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
				<div class="nui-dynpanel" columns="6">
					<label>项目代码：</label> 
					<input name="map.projectNum"id="map.projectNum" required="false"class="nui-textbox nui-form-input" />
					
					<label>项目名称：</label> 
					<input name="map.projectName" id="map.projectName" required="false"class="nui-textbox nui-form-input" /> 
					
					<label>项目编号：</label>
					<input name="map.secuNum" id="map.secuNum" required="false" class="nui-textbox nui-form-input" /> 
					
					<label>信托公司名称：</label>
					<input name="map.secuName" id="map.secuName" required="false"class="nui-textbox nui-form-input" />
					
					<label>项目成立日：</label>
					<div>
					<input id="map.startDate" class="nui-datepicker nui-form-input"style="width: 100px" name="map.startDate"  />-
					<input id="map.endDate" class="nui-datepicker nui-form-input"style="width: 100px" name="map.endDate"  />
					</div>

					<label class="nui-form-label">项目状态：</label>
					<input name="map.projectStatus" class="nui-dictcombobox nui-form-input"dictTypeId="project_status" id="map.endDate" />
				</div>

				<div class="nui-toolbar" style="text-align:right;border:none" >
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>	
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
						<a id="addCust"  class="nui-button"iconCls="icon-add" onclick="add()">增加</a> 
						<a id="editCust"class="nui-button" iconCls="icon-edit" onclick="edit(1)">编辑</a> 
						<a id="editCust" class="nui-button" iconCls="icon-zoomin"onclick="edit(0)">查看</a> 
						<a class="nui-button"iconCls="icon-remove" onclick="remove()" id="rmove">撤销</a> 
						<a id="importContract" style="margin-left: 5px" class="nui-button"iconCls="icon-upload" onclick="send()">发送到计量系统</a>
				</div>
				
			<div id="grid" class="nui-datagrid" sortMode="client"
				url="	com.bos.project.project.findProjectList.biz.ext"
				dataField="items" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="10">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div type="indexcolumn" headerAlign="center">序号</div>
					<div field="PROJECT_NUM" headerAlign="center" align="center" allowSort="true" >项目代码</div>
					<div field="SECU_NUM" headerAlign="center" align="center" allowSort="true" >项目编号</div>
					<div field="PROJECT_NAME" headerAlign="center" align="center" allowSort="true" >项目名称</div>
					<div field="SECU_NAME" headerAlign="center" align="center" allowSort="true" >信托公司名称</div>
					<div field="PACKAGE_DATE" headerAlign="center" align="center" allowSort="true" >项目封包日期</div>
					<div field="START_DATE" headerAlign="center" align="center" allowSort="true" >项目成立日</div>
					<div field="END_DATE" headerAlign="center" align="center" allowSort="true" >项目到期日期</div>
					<div field="PACK_BAL" headerAlign="center" align="right"  allowSort="true" 	dataType="currency">总余额</div>
					<div field="PROJECT_STATUS" headerAlign="center" align="center"allowSort="true" dictTypeId="project_status">项目状态</div>
					<div field="ORG_NUM" headerAlign="center" align="center"dictTypeId="org">经办机构</div>
					<div field="USER_NUM" headerAlign="center" align="center" allowSort="true" dictTypeId="user">经办人</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		//var loanChangeType = nui.get("loanChangeType");
		var form = new nui.Form("#form");
		var grid = nui.get("grid"); //借据列表
		query();
		function query() {
			var o = form.getData();
			grid.load(o);
		}

		function edit(v) {
			var row = grid.getSelected();
			if (row) {
				if (v == 1) {
					if (row.PROJECT_STATUS == "5") {
						alert("状态为部分转出的项目，不能修改");
						return;
					}
					if (row.PROJECT_STATUS == "2") {
						alert("状态为未转出的项目，不能修改");
						return;
					}
					if (row.PROJECT_STATUS == "3") {
						alert("状态为已转出的项目，不能修改");
						return;
					}
					if (row.PROJECT_STATUS == "4") {
						alert("状态为结束的项目，不能修改");
						return;
					}
				}
				if(v=="1"){
					var title="修改项目信息";
				}else{
					var title="查看项目信息";
				}
				var url = "/project/project_edit.jsp?projectId=" + row.PROJECT_ID + "&view="+ v;
				nui.open({
					url : nui.context + url,
					showMaxButton : true,
					title : title,
					width : 1300,
					height : 600,
					onload : function(e) {
						var iframe = this.getIFrameEl();
						var text = iframe.contentWindow.document.body.innerText;
					},
					ondestroy : function(action) {
						query();
					}
				});

			} else {
				alert("请选中一条记录");
			}
		}

		function add() {
			nui.open({
				url : nui.context + '/project/project_add.jsp',
				showMaxButton : true,
				title : "添加项目",
				width : 1200,
				height : 400,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					query();
				}
			});
		}

		function remove() {
			var row = grid.getSelected();
			if (row) {
				if (row.PROJECT_STATUS == "5") {
					alert("状态为部分转出的项目，不能修改");
					return;
				}
				if (row.PROJECT_STATUS == "2") {
					alert("状态为未转出的项目，无法撤销");
					return;
				}
				if (row.PROJECT_STATUS == "3") {
					alert("状态为已转出的项目，无法撤销");
					return;
				}
				if (row.PROJECT_STATUS == "4") {
					alert("状态为结束的项目，无法撤销");
					return;
				}
				nui.confirm("撤销会将项目下所有借据全部移出，确定撤销吗？", "确认", function(action) {
					if (action != "ok")
						return;
					var json = nui.encode({
						"projectId" : row.PROJECT_ID
					});
					$.ajax({
						url : "com.bos.project.project.deleteProject.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							nui.alert(text.msg);
							query();
						},
						error : function() {
							nui.alert("操作失败！");
						}
					});
				});
			} else {
				nui.alert("请选中一条记录");
			}
		}

		function send() {
			var row = grid.getSelected();
			if (row) {
				if (row.PROJECT_STATUS != "1") {
					alert("只有状态为新建的项目，才能发送到计量");
					return;
				}
				//判断借据的止期是否在项目的封包日之后。
				var json1 = {
					"packageDate" : row.PACKAGE_DATE,
					"projectId" : row.PROJECT_ID
				};
				msg = exeRule("PUB_PROJECT_LOAN_DATE2", "1", json1);
				if (null != msg && '' != msg) {
					nui.alert(msg);
					return;
				}
				var json = nui.encode({
					"projectId" : row.PROJECT_ID
				});
				$.ajax({
					url : "com.bos.project.project.sendProject.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						nui.alert(text.msg);
						query();
					},
					error : function() {
						nui.alert("操作失败！");
					}
				});
			} else {
				nui.alert("请选中一条记录");
			}
		}
		//重置
		function reset() {
			form.reset();
		}
	</script>
</body>
</html>