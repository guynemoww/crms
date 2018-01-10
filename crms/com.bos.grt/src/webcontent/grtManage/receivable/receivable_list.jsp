<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>应收类</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtReceivable"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/receivable/receivable_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/receivable/receivable_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/receivable/receivable_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtReceivable')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="payeeName" headerAlign="center">收款单位名称</div>
			<div field="payeeOpenbankName" headerAlign="center">收款单位开户行名称</div>
			<div field="payeeNum" headerAlign="center">收款单位收款账号</div>
			<div field="payerName" headerAlign="center">付款单位名称</div>
			<div field="payerEnterprisePro" headerAlign="center" dictTypeId="XD_YWDB0124">付款单位企业性质</div>
			<div field="contractNo" headerAlign="center" >合同编号</div>
			<div field="currencyCd" headerAlign="center" dictTypeId="CD000001">币种</div>
			<div field="accountsDueBalance" headerAlign="center" >应收款余额</div>
			<div field="beginDate" headerAlign="center" >赊销发生时间</div>
			<div field="endDate" headerAlign="center" >应收款到期日</div>
		</div>
	</div>
</body>
</html>
