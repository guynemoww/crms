<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): pc
  - Date: 20160511
  - Description:
-->
<head>
<title>贴息信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width: 100%; height: auto" class="nui-form">
	<div class="nui-toolbar"
		style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
		<a class="nui-button" id="tx_add" iconCls="icon-add"
			onclick="addtx()">增加</a>
		<a class="nui-button" id="tx_edit"
			iconCls="icon-edit" onclick="edittx(0)">编辑</a> 
		<a class="nui-button" id="tx_view" iconCls="icon-node"
			onclick="edittx(1)">查看</a> 
		<a class="nui-button" id="tx_remove"
			iconCls="icon-remove" onclick="removetx()">删除</a>
	</div>

	<div id="gridtx" class="nui-datagrid" style="width: 100%; height: auto"
		url="com.bos.bizProductDetail.bizTx.getTxList.biz.ext"
		dataField="txs" allowResize="false" showReloadButton="false"
		allowCellEdit="false" sizeList="[10,15,20,50,100]"
		multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="txfs" headerAlign="center" allowSort="true"
				dictTypeId="XD_TXFS0001">贴息方式</div>
			<div field="txbl" headerAlign="center" allowSort="true">贴息比例（%）</div>
			<!-- <div field="gdje" headerAlign="center" allowSort="true">固定金额（元）</div>
			<div field="xe" headerAlign="center" allowSort="true">限额</div> -->
			<div field="qx" headerAlign="center" allowSort="true">期限（月）</div>
			<div field="txzt1" headerAlign="center" allowSort="true">贴息主体</div>
			<div field="txzh1" headerAlign="center" allowSort="true">贴息账号</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
    var form = new nui.Form("#form1"); 
	var gridtx = nui.get("gridtx");
	var applyId = "<%=request.getParameter("applyId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	if("1" != proFlag){
		nui.get("tx_add").hide();
		nui.get("tx_edit").hide();
		nui.get("tx_remove").hide();
		form.setEnabled(false);
	}
	 function search() {
		var json = {"applyId":applyId};	
        gridtx.load(json);
    }
    search();
	    
	//添加贴息信息
	function addtx() {
		nui.open({
				url : nui.context + "/biz/biz_product_detail/person/biz_tx_add.jsp?applyId=" + applyId,
				title : "新增",
				width : 800,
				height : 500,
				allowResize : true,
				showMaxButton : true,
				ondestroy : function(action) {
					if (action == "ok") {
						var json = nui.decode({ "applyId" : applyId });
						gridtx.load(json);
					}
				}
			});
	}
	//编辑贴息信息
	function edittx(v) {
		var grid = nui.get("gridtx");
		var row = grid.getSelected();
		if (row) {
			nui.open({
				url : nui.context + "/biz/biz_product_detail/person/biz_tx_edit.jsp?txId=" + row.txId + "&view=" + v,
				title : "编辑",
				width : 800,
				height : 500,
				allowResize : true,
				showMaxButton : true,
				onload : function() {
					var iframe = this.getIFrameEl();
					var data = row;
					//iframe.contentWindow.SetData(data);
				},
				ondestroy : function(action) {
					if (action == "ok") {
						var json = nui.decode({ "applyId" : applyId });
						gridtx.load(json);
					}
				}
			});
		} else {
			alert("请选择项目信息！");
		}
	}
	//删除贴息信息
	function removetx() {
		var grid = nui.get("gridtx");
		var rows = grid.getSelected();
		if (null == rows) {
			nui.alert("请选择项目信息！");
			return false;
		}
		var json = nui.encode({
			"tbBizTx" : rows
		});
		nui.confirm("确定删除吗？", "确认", function(action) {
			if (action != "ok")
				return;
			$.ajax({
				url : "com.bos.bizProductDetail.bizTx.delTx.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
						return;
					}
					var json = nui.decode({"applyId" : applyId});
					gridtx.load(json);
				},
				error : function() {
					nui.alert("操作失败！");
				}
			});
		});
	}
</script>
</body>
</html>