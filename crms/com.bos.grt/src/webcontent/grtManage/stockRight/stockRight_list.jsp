<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>股票</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtStock"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/stockRight/stockRight_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/stockRight/stockRight_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/stockRight/stockRight_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtStock')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="stockName" headerAlign="center">股票名称</div>
			<div field="stockCode" headerAlign="center">股票代码</div>
			<div field="issuedUnit" headerAlign="center">发行单位</div>
			<div field="issuedDate" headerAlign="center">发行日期</div>
			<div field="stockType" headerAlign="center" dictTypeId="XD_YWDB0117">股票类型</div>
			<div field="stockSort" headerAlign="center" dictTypeId="XD_YWDB0118">股票类别</div>
			<div field="stockCount" headerAlign="center" >持股数量（股）</div>
			<div field="stockRate" headerAlign="center" >持股比重（%）</div>
			<div field="currencyCd" headerAlign="center" dictTypeId="CD000001">币种</div>
			<div field="stockPrice" headerAlign="center" >质押股票市值</div>
		</div>
	</div>
</body>
</html>
