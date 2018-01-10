<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>林权</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtResourceprofit"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/forestRight/forestRight_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/forestRight/forestRight_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/forestRight/forestRight_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtResourceprofit')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="cbjyqr" headerAlign="center" >承包经营权人</div>
			<div field="currencyCd" headerAlign="center" dictTypeId="CD000001">币种</div>
			<div field="yearJobCost" headerAlign="center" >年承包费用</div>
			<div field="landPaymentState" headerAlign="center" dictTypeId="XD_YWDB0135">林地出让金缴纳状态</div>
			<div field="forestMeasure" headerAlign="center">林木蓄积量</div>
			<div field="forestryIndex" headerAlign="center" >林业采伐指标</div>
		</div>
	</div>
</body>
</html>
