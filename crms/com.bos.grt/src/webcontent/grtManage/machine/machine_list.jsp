<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>机器设备</title>
</head>
<body>
	<div id="form3" style="width:100%;height:auto;overflow:hidden;">
		<input name="tbGrtHouse.suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
		<input name="item._entity" id="item._entity" class="nui-hidden" value="com.bos.dataset.grt.TbGrtMachine"/>
		<div class="nui-dynpanel" columns="6">
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;width:99.5%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="add('/grt/grtManage/machine/machine_add.jsp')" id="add">增加</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('0','/grt/grtManage/machine/machine_add.jsp')" id="edit0">编辑</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit('1','/grt/grtManage/machine/machine_add.jsp')">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('com.bos.dataset.grt.TbGrtMachine')" id="remove">删除</a>
	</div>
		    
	<div id="grid3" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.mortgageCURD.getMortgageList.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="machineNo" headerAlign="center" >机器设备编号</div>
			<div field="machineType" headerAlign="center">设备型号</div>
			<div field="buyPrice" headerAlign="center">购入价格</div>
			<div field="mainUse" headerAlign="center" >主要用途</div>
			<div field="superviseStatus" headerAlign="center" dictTypeId="XD_YWDB0107">监管情况</div>
			<div field="leaseStatus" headerAlign="center" dictTypeId="XD_YWDB0108">租赁状态</div>
		</div>
	</div>
</body>
</html>
