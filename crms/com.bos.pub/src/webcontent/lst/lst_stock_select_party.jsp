<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-05-10 15:07:55
  - Description:
-->
<head>
<title>客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label>客户编号：</label>
			<input name="item.partyNum" id="item.partyNum" class="nui-textbox nui-form-input" />
			<label>客户名称：</label>
			<input name="item.partyName" id="item.partyName" class="nui-textbox nui-form-input" />
			<label>客户类型：</label>
			<input id="item.partyType" name="item.partyType" class="nui-dictcombobox" required="true" valueField="dictID" textField="dictName" dictTypeId="XD_KHCD4001" />
			<label>证件号码：</label>
			<input name="item.certNum" id="item.certNum" class="nui-textbox nui-form-input" />
		</div>
		<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">
			<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
			<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
		</div>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: 400px;" allowAlternating="true" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" allowResize="false" showReloadButton="false" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client" emptyText="没有查到数据">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="partyNum" allowSort="true" headerAlign="center">客户编号</div>
			<div field="partyName" allowSort="true" headerAlign="center">客户名称</div>
			<div field="certType" allowSort="true" dictTypeId="CDKH0002" headerAlign="center">证件类型</div>
			<div field="certNum" allowSort="true" headerAlign="center">证件号码</div>
			<div field="updateOrgNum" allowSort="true" dictTypeId="org" headerAlign="center">经办行</div>
			<div field="updateUserNum" allowSort="true" dictTypeId="user" headerAlign="center">经办人</div>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align: center; padding-top: 8px; padding-bottom: 8px;" borderStyle="border:0;">
		<a class="nui-button" iconCls="icon-save" onclick="onOk()">确定</a>
		<span style="display: inline-block; width: 25px;"></span>
		<a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">取消</a>
	</div>

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			form.validate();
			if (form.isValid() == false)
				return;
			var o = form.getData();
			o.sqlName = "02" == o.item.partyType ? "com.bos.lst.lst.lstStockSelectNatuParty"
					: "com.bos.lst.lst.lstStockSelectCorpParty";
			//console.info(nui.encode(o));
			grid.load(o);
		}

		function getData() {
			var row = grid.getSelected();
			return row;
		}

		function reset() {
			form.reset();
		}

		function CloseWindow(action) {
			if (window.CloseOwnerWindow)
				return window.CloseOwnerWindow(action);
			else
				window.close();
		}

		function onOk() {
			CloseWindow("ok");
		}

		function onCancel() {
			CloseWindow("cancel");
		}
	</script>
</body>
</html>