<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>特许经营权</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtTxjyq"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/franchiseRight/franchiseRights_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/franchiseRight/franchiseRights_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/franchiseRight/franchiseRights_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtTxjyq')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="txjyqsr" headerAlign="center" >特许经营起始日</div>
			<div field="txjyzzr" headerAlign="center">特许经营终止日</div>
			<div field="czcjyxkz" headerAlign="center" dictTypeId="XD_YWDB0127">出租车经营许可证</div>
			<div field="glkyxljyxkz" headerAlign="center" dictTypeId="XD_YWDB0128">公路客运线路经营许可证</div>
		</div>
	</div>
</body>
</html>
