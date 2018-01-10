<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-6
  - Description:TB_CSM_BOND_INFO, com.bos.dataset.csm.TbCsmBondInfo
-->
<head>
<title>对公客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="全行客户查询">
			<div id="form1" class="nui-form">
				<input name="item.legOrg" id="item.legOrg" class="nui-hidden" />
				<div class="nui-dynpanel" columns="6">
					<label>客户名称：</label>
					<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input" />
					<label>证件类型：</label>
					<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" />
					<label>证件号码：</label>
					<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input" required="false" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<div class="nui-fit">
				<div id="datagrid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.csm.corporation.corporation.getCustList.biz.ext" dataField="items" allowAlternating="true" multiSelect="false" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="ecifPartyNum" headerAlign="center" allowSort="true">ECIF客户号</div>
						<div field="partyName" headerAlign="center" allowSort="true">客户名称</div>
						<div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
						<div field="certNum" headerAlign="center" allowSort="true">证件号码</div>
						<div field="userNum" dictTypeId="user" headerAlign="center" allowSort="true">管户客户经理</div>
						<div field="orgNum" dictTypeId="org" headerAlign="center" allowSort="true">管户机构</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function search() {
			var data = form.getData(); //获取表单多个控件的数据
			grid.load(data);
		}
		search();
	</script>
</body>
</html>
