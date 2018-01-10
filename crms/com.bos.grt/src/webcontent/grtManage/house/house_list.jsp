<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-01
-->
<head>
<title>房地产</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtHouse"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/house/house_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/house/house_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/house/house_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtHouse')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<!-- <div field="country" headerAlign="center" allowSort="true" dictTypeId="CD000003" >国家</div>
			<div field="province" headerAlign="center" allowSort="true" >省</div>
			<div field="city" headerAlign="center" allowSort="true" >城市</div>
			<div field="town" headerAlign="center" allowSort="true" >区（县）</div> -->
			<div field="houseLocation" headerAlign="center" allowSort="true" >坐落位置</div>
			<div field="housePropNo" headerAlign="center" allowSort="true" >房产权证号</div>
			<div field="houseStructure" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0102" >建筑结构</div>
			<div field="houseArea" headerAlign="center" allowSort="true" >建筑面积(㎡)</div>
			<div field=landUseNo headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0105" >土地使用权证号</div>
			<div field="landGainWay" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0104" >土地取得方式</div>
			<div field="landQuale" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0103" >土地性质</div>
		</div>
	</div>
</body>
</html>
