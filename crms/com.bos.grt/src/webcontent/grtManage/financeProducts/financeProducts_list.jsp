<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>理财产品</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtManagemoney"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/financeProducts/financeProducts_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/financeProducts/financeProducts_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/financeProducts/financeProducts_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtManagemoney')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="manageMoneyName" headerAlign="center">理财名称</div>
			<div field="beginDate" headerAlign="center">起始日</div>
			<div field="endDate" headerAlign="center">到期日</div>
			<div field="yieldRate" headerAlign="center">预期收益率（％）</div>
			<div field="earningsType" headerAlign="center" dictTypeId="XD_YWDB0122">收益类型</div>
			<div field="totalMoney" headerAlign="center" >到期总金额</div>
			<div field="accountNo" headerAlign="center" >理财交易账户号</div>
			<div field="dangerLevel" headerAlign="center" dictTypeId="XD_YWDB0123">等级</div>
		</div>
	</div>
</body>
</html>
