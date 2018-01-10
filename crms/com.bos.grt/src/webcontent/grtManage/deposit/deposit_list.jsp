<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>存单</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtDeposit"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/deposit/deposit_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/deposit/deposit_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/deposit/deposit_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtDeposit')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="depositType" headerAlign="center" dictTypeId="XD_YWDB0110">存单类型</div>
			<div field="depositNo" headerAlign="center">存单号</div>
			<div field="openBank" headerAlign="center">开户行全称</div>
			<div field="depositAmt" headerAlign="center" >存单面额</div>
			<div field="depositRate" headerAlign="center">存单利率</div>
		</div>
	</div>
</body>
</html>