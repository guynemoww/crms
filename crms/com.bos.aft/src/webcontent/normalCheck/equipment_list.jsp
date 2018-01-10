<%@page pageEncoding="UTF-8"%>
<div id="equipmentgrid">
	<fieldset>
		<legend>
			<span>本期设备购置情况（设备贷款）</span>
		</legend>
		<div class="nui-toolbar" style="border-bottom: 0; text-align: left;">
			<a class="nui-button" iconCls="icon-add" onclick="add('grid3')" id="grid3add">增加</a>
			<a class="nui-button" iconCls="icon-remove" onclick="remove('grid3')" id="grid3del">删除</a>
		</div>
		<div id="grid3" class="nui-datagrid" style="width: 100%; height: auto"
		    url="com.bos.aft.normalCheck.queryEquipmentInfo.biz.ext"
			dataField="tbAftBuyEquipment" allowResize="true" showReloadButton="false"
			allowCellEdit="true" allowCellSelect="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15"
			sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="equipmentName" headerAlign="center" width="80" allowSort="true">
					设备名称 <input property="editor" class="nui-textbox" />
				</div>
				<div field="equipmentValue" headerAlign="center" allowSort="true"
					width="80" dataType="currency" align="right" currencyUnit="￥">
					价值  <input property="editor" class="nui-textbox" />
				</div>
			</div>
		</div>
	</fieldset>
</div>
