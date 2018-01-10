<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-26 8:26:27
  - Description:
-->
<head>
<title>股东名单管理</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 115%;">
		<div title="我行股东名单管理">
			<form id="form2" method="post" action="com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile">
				<input id="entity" name="item/entity" value="com.bos.lst.lst.TbLstStock" class="nui-hidden" />
				<input name="importCd" value='102' id="importCd" class="nui-hidden" />
			</form>
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.lst.lst.stockList" />
				<div class="nui-dynpanel" columns="6">
					<label>股东客户编号：</label>
					<input name="item.rId" id="item.rId" class="nui-textbox nui-form-input" />
					<label>股东客户名称：</label>
					<input name="item.custName" id="item.custName" class="nui-textbox nui-form-input" />
					<label>股东股权性质：</label>
					<input id="item.stockNature" name="item.stockNature" class="nui-dictcombobox nui-form-input" valueField="dictID" textField="dictName" dictTypeId="XD_KHCD0303" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
				</div>
			</div>

			<div class="nui-toolbar" style="padding-right: 20px; text-align: left; padding-top: 5px; padding-bottom: 5px;" borderStyle="border:0;">
				<a id="add" class="nui-button" iconCls="icon-add" onclick="addTbLstSock()">新增</a>
				<a id="edit" class="nui-button" iconCls="icon-edit" onclick="editTbLstSock()">修改</a>
				<a id="cancel" class="nui-button" iconCls="icon-remove" onclick="cancelTbLstSock()">删除</a>
				<!-- <a id="remove" class="nui-button" iconCls="icon-remove" onclick="removeStockHolder()">转出</a> -->
				<a id="upload" class="nui-button" iconCls="icon-upload" onclick="impExcel()">上传</a>
			</div>


			<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto;" allowAlternating="true" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" allowResize="false" showReloadButton="false" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="partyNum" allowSort="true" headerAlign="center">股东客户编号</div>
					<div field=custName allowSort="true" headerAlign="center">股东客户名称</div>
					<div field="stockNature" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD0303">股东股权性质</div>
					<div field="direcsitu" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD0301">董监事单位情况</div>
					<div field="directorName" allowSort="true" headerAlign="center">董监事人员姓名</div>
					<div field="drectorPost" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD0302">董监事人员职务</div>
					<div field="stockNum" allowSort="true" headerAlign="center">持股数量（万股）</div>
					<div field="stockRate" allowSort="true" headerAlign="center">持股比例（%）</div>
					<div field="stockNetval" allowSort="true" headerAlign="center">股权净值（万元）</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		var role = "";
		function queryInit() {
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
		}
		queryInit();
	<%UserObject user = (UserObject) session.getAttribute("userObject");
		String manage = "false";
		DataObject[] roles = (DataObject[]) user.getAttributes().get("roles");
		if (null != roles && roles.length > 0) {
			for (int i = 0; i < roles.length; i++) {
				DataObject role = roles[i];
				if ("eosadmin".equals(role.get("roleid"))) {
					manage = "true";
				} else {
					continue;
				}
			}
		}
		if (manage.equals("false")) {//如果不是系统管理员则隐藏upload 显示remove%>
		initRole();
	<%}%>
		function initRole() {
			nui.get('upload').hide();
			//nui.get('remove').show();
		}

		function reset() {
			form.reset();
			queryInit();
		}

		function addTbLstSock() {
			nui.open({
				url : nui.context + "/pub/lst/lst_stock_edit.jsp?actionType=insert",
				title : "新增股东信息",
				width : 800,
				height : 400,
				ondestroy : function(action) {
					grid.reload();
				}
			});
		}

		function editTbLstSock() {
			var row = grid.getSelected();
			if (row) {
				nui.open({
					url : nui.context + "/pub/lst/lst_stock_edit.jsp?actionType=edit&rId=" + row.rId,
					title : "编辑股东信息",
					width : 800,
					height : 400,
					ondestroy : function(action) {
						grid.reload();
					}
				});
			} else {
				nui.alert("请选中一条记录", "提示");
			}
		}

		function cancelTbLstSock() {
			var row = grid.getSelected();
			if (row) {
				nui.confirm("确定删除[" + row.custName + "]吗？", "确认", function(action) {
					if (action == "ok") {
						//console.info(nui.encode(row));
						var json = nui.encode({
							'rId' : row.rId
						});
						$.ajax({
							url : "com.bos.pub.lst.lst.cancelTbLstStock.biz.ext",
							type : 'POST',
							data : json,
							cache : false,
							contentType : 'text/json',
							success : function(data) {
								if (data.msg) {
									nui.alert(data.msg);
								} else {
									queryInit();
								}
							},
							error : function() {
								nui.alert("操作失败!");
							}
						});
					}
				})
			} else {
				nui.alert("请选中一条记录", "提示");
			}
		}

/* 		//转出我行股东名单客户
		function removeStockHolder() {
			var row = grid.getSelected();
			if (row == null) {
				alert('请选择一条记录');
				return;
			}
			//var json = nui.encode({item:row});//传整条记录
			//将记录的主键传过去
			var json = nui.encode({
				item : {
					'rId' : row.rId
				}
			});
			//alert(json);
			nui.confirm("确定删除吗？", "确认", function(action) {
				if (action == "ok") {
					$.ajax({
						url : "com.bos.pub.lst.lst.removeStockHolder.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							//nui.alert(text.msg);
							queryInit();
						},
						error : function() {
							nui.alert("操作失败!");
						}
					});
				}
			})
		} */

		function upload() {
			//打开上传面
			nui.open({
				url : nui.context + "/pub/lst/upload.jsp",
				showMaxButton : true,
				title : "上传文件",
				width : 800,
				height : 300,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					queryInit();
				}
			});
		}
		function exportEmp() {
			var frm = document.getElementById("form2");
			frm.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile";
			frm.submit();
		}

		function impExcel() {
			//打开上传面
			nui.open({
				url : nui.context + "/com.bos.lst.lstStock.flow",
				showMaxButton : true,
				title : "上传文件",
				width : 800,
				height : 300,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					queryInit();
				}
			});
		}
	</script>
</body>
</html>