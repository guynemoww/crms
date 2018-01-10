<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-25 17:12:23
  - Description:
-->
<head>
<title>机构财务对应关系</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="机构财务对应关系" >
	<center>
		<div id="form" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
			<div class="nui-dynpanel" columns="6">
				<label>机构：</label>
				<input name="item.oprOrgId" id="item.oprOrgId" class="nui-buttonEdit" selectOnFocus="true" dictTypeId="org" onbuttonclick="selectOrg" />
				<label>财务机构：</label>
				<input name="item.accOrgId" id="item.accOrgId" class="nui-buttonEdit" selectOnFocus="true" dictTypeId="org" onbuttonclick="selectOrg" />
				<label>入账类型：</label>
				<input id="item.col2" name="item.col2" class="nui-dictcombobox" required="true" dictTypeId="XD_RZLX"  />
				<label>产品：</label>
				<input name="item.productId" id="item.productId" class="nui-buttonEdit" selectOnFocus="true" dictTypeId="product" onbuttonclick="selectProduct" />
				<label>启用状态：</label>
				<input name="item.status" id="item.status" class="nui-dictcombobox" dictTypeId="XD_0002" />
				<label>是否手工数据：</label>
				<input name="item.col1" id="item.col1" class="nui-dictcombobox" dictTypeId="XD_0002" />
				
			</div>
			<div class="nui-toolbar" style="text-align:right;border:none">
			    <a class="nui-button" onclick="query">搜索</a>
				<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
				<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.utp.org.organization.searchOrgAccRelList" />
			</div>
		</div>
		<div style="width:99.5%">				
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" iconCls="icon-add" onclick="add">添加</a>
				<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
				<a class="nui-button" iconCls="icon-node" onclick="edit(1)">查看</a>
				<a class="nui-button" id="" iconCls="icon-ok" onclick="action(1)">启用</a>
				<a class="nui-button" id="" iconCls="icon-cancel" onclick="action(0)">注销</a>
				<a class="nui-button" id="" iconCls="icon-remove" onclick="remove()">删除</a>
			</div>
		</div>	    
			<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto;"
					url="com.bos.pub.dao.search.biz.ext"
					dataField="items" allowAlternating="true" 
					allowResize="true" showReloadButton="false" 
					sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="OPR_ORG_NO">机构编号</div>
					<div field="OPR_ORG_ID" dictTypeId="org">机构名称</div>
					<div field="PRODUCT_NO">产品编号</div>
					<div field="PRODUCT_ID" dictTypeId="product">产品名称</div>
					<div field="ACC_ORG_NO">财务机构编号</div>
					<div field="ACC_ORG_ID" dictTypeId="org">财务机构名称</div>
					<div field="COL2" dictTypeId="XD_RZLX">入账类型</div>
					<div field="STATUS" dictTypeId="XD_0002">是否启用</div>
					<div field="COL1" dictTypeId="XD_0002">是否手工数据</div>
					<div field="CREATE_TIME" dateFormat="yyyy-MM-dd">创建时间</div>
					<div field="UPDATE_TIME" dateFormat="yyyy-MM-dd">最后更新时间</div>
					<div field="OPR_USER_NO" dictTypeId="user">操作人</div>
				</div>
			</div>
		</center>
	</div>
</div>	
	
	
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		var grid = nui.get("grid");
		query();
		function query() {
			grid.load(form.getData());
			form.unmask();
		}

		function reset() {
			form.reset();
			query();
		}
		
		function add(type) {
			nui.open({
				url : nui.context + "/utp/org/org_acc_rel/org_acc_rel_add.jsp",
				showMaxButton : true,
				title : "提示：可点击最大化按钮放大此窗口",
				width : "800",
				height : "400",
				ondestroy : function(e) {
					if (type != "2") {
						grid.reload();
					}
				}
			})
		}
		
	function edit(type) {
		var orgAccRelId;
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条信息!");
			return false;
		}
		orgAccRelId = row.ORG_REL_ID;
		nui.open({
			url : nui.context + "/utp/org/org_acc_rel/org_acc_rel_edit.jsp?view=" + type + "&orgAccRelId=" + orgAccRelId,
			showMaxButton : true,
			title : "提示：可点击最大化按钮放大此窗口",
			width : "800",
			height : "400",
			ondestroy : function(e) {
				if (type == "0") {
					grid.reload();
				}
			}
		})
	}

		function action(status) {
			var row = grid.getSelected();
			debugger;
			if (null == row) {
				nui.alert("请选择一条信息!");
				return false;
			} else if (!nui.confirm("是否确认[" + (status == 1 ? "启用" : "注销")
					+ "]该选中数据？", "询问")) {
				return;
			}
			form.loading("正在保存数据");
			var json = nui.encode({
				"orgAccRelId" : row.ORG_REL_ID,
				"status" : status
			});
			$
					.ajax({
						url : "com.bos.utp.org.OrgAccRelManager.cancelOrgAccRel.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(data) {
							form.unmask();
							if (data.msg) {
								alert(data.msg);
								return;
							} else {
								alert("保存成功！");
								query();
							}
						}
					});
		}

		function remove() {
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一条信息!");
				return false;
			} else if (row.STATUS != "0") {
				alert("非[注销]状态的数据无法删除");
				return false;
			} else if (!nui.confirm("是否确认[删除]该选中数据？", "询问")) {
				return false;
				;
			}
			form.loading("正在保存数据");
			var json = nui.encode({
				"orgAccRelId" : row.ORG_REL_ID
			});
			$
					.ajax({
						url : "com.bos.utp.org.OrgAccRelManager.removeOrgAccRel.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(data) {
							form.unmask();
							if (data.msg) {
								alert(data.msg);
								return;
							} else {
								alert("删除成功！");
								query();
							}
						}
					});
		}
		function selectOrg(e) {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/sys/select_org_tree.jsp",
				showMaxButton : true,
				title : "选择机构",
				width : 800,
				height : 500,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						if (data) {
							btnEdit.setText(null);
							btnEdit.setValue(data.orgid);
						}
					}
				}
			});
		}
		function selectProduct(e) {
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/product/product/select_product_tree.jsp?searchMode=random&selectEvery=1",
						title : "选择",
						width : 800,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.getData();
								if (data) {
									btnEdit.setText(null);
									btnEdit.setValue(data.orgid);
								}
							}
						}
					});
		}
	</script>
</body>
</html>