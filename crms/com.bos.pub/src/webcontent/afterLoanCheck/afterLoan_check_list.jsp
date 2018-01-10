<%@page pageEncoding="UTF-8"%>
<html>

<head>
<title>授信到期前跟踪检查</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"style="width: 100%; height: auto;">
	<div title="授信到期前跟踪检查参数">
				<div class="nui-toolbar" style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
					<a id="addS" style="margin-left: 5px" class="nui-button" iconCls="icon-add" onclick="add()">增加</a> 
					<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(1)">编辑</a> 
					<!--  <a id="view" class="nui-button" iconCls="icon-zoomin" onclick="edit(0)">查看</a>-->
					<a id="del" class="nui-button" iconCls="icon-remove" onclick="del()">删除</a> 
				</div>

			<div id="grid" class="nui-datagrid" sortMode="client"
				url="com.bos.pub.afterLoanCheck.afterLoanCheck.findAfterLoanCheckList.biz.ext"
				dataField="items" allowAlternating="true" multiSelect="false"
				showEmptyText="true" allowResize="true" emptyText="没有查到数据"onrowdblclick="" allowCellEdit="true"
				allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="10">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="CREDIT_EXPIRE_YUE" headerAlign="center" align="center"allowSort="true"dataType="currency" >授信到期前余额</div>
					<div field="CREDIT_EXPIRE_CHECK_IND" headerAlign="center" align="center" allowSort="true"dictTypeId="YesOrNo" >授信到期前跟踪检查标志</div>
				</div>
			</div>
					</div></div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid"); 
		query();
		function query() {
			grid.load();
		}
		
		function add() {
			var rows = nui.get("grid").getData();
			if(rows.length>0){
				alert("只能存在一条记录");
				return;
			}
			nui.open({
				url : nui.context + '/pub/afterLoanCheck/afterLoan_check_add.jsp',
				showMaxButton : true,
				title : "添加财授信到期前跟踪检查参数",
				width : 600,
				height : 350,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
						query();
				}
			});
		}
		
		function del() {
			var row = grid.getSelected();
			if (!row) {
				alert("至少选择一条记录");
				return;
			}
				nui.confirm("确定要删除选中条目吗？","提示信息",function(action){
					if('ok' == action){
						var json = nui.encode({"expireCheck":{"id":row.ID}});
						$.ajax({
								url : "com.bos.pub.afterLoanCheck.afterLoanCheck.delAfterLoanCheck.biz.ext",
								type : 'POST',
								data : json,
								cache : false,
								contentType : 'text/json',
								success : function(text) {
									if(text.msg){
										alert(text.msg);
									}else{
										nui.alert("删除成功！","提示",function(action){
											query();
										});
									}
								},
								error : function() {
									nui.alert("操作失败！");
								}
							});
					}
				})
		}
		
		function edit(v) {
			var row = grid.getSelected();
			if (!row) {
				alert("请选择一条记录！");
				return;
			}
				nui.open({
					url : nui.context + "/pub/afterLoanCheck/afterLoan_check_edit.jsp?id="+row.ID+"&view="+v,
					showMaxButton : true,
					title : "授信到期前跟踪检查参数",
					width : 600,
					height : 350,
					onload : function(e) {
						var iframe = this.getIFrameEl();
						var text = iframe.contentWindow.document.body.innerText;
					},
					ondestroy : function(action) {
							query();
					}
				});
		}
		
		//查询机构选择
	function selectOrgQuery() {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp",
			showMaxButton : true,
			title : "选择机构",
			width : 350,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					data = nui.clone(data);
					if (data) {
						self.orglevel = data.orglevel;
						btnEdit.setValue(data.orgcode);
						btnEdit.setText(data.orgname);
					}
				}
			}
		});
	}
		//重置
		function reset() {
			form.reset();
		}
	</script>
</body>
</html>