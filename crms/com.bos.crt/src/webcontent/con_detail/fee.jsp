<%@page pageEncoding="UTF-8"%>
<fieldset>
	<legend>
		<span>费率信息项</span>
	</legend>
	<div class="nui-toolbar" style="border-bottom: 0; text-align: left;"
		id="feediv">
		<a class="nui-button" iconCls="icon-add" onclick="add('grid3')">增加</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('grid3')">删除</a>
	</div>
	<div id="grid3" class="nui-datagrid"
		url="com.bos.conInfo.conContractInfo.getFee.biz.ext" dataField="fees"
		allowResize="true" showReloadButton="false" allowCellEdit="true"
		allowCellSelect="true" sizeList="[15,20,100]" multiSelect="false"
		pageSize="15" sortMode="client" oncellendedit="grid3Click">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="chargingType" headerAlign="center" width="80"
				allowSort="true">
				收费种类 <input property="editor" class="nui-textbox" width="80" />
			</div>
			<div field="chargingProportion" headerAlign="center" width="80"
				allowSort="true" align="right">
				收费比例（‰） <input property="editor" class="nui-textbox" />
			</div>
			<div field="shouldFee" headerAlign="center" allowSort="true"
				width="80" dataType="currency" align="right">
				收费金额 <input property="editor" class="nui-textbox" class="nui-textbox nui-form-input" dataType="currency" />
			</div>
			<div field="costType" headerAlign="center" allowSort="true"
				width="80" dictTypeId="XD_SXYW0217" align="right">
				收费方式 <input property="editor" class="nui-dictcombobox"
					dictTypeId="XD_SXYW0217" />
			</div>
		</div>
	</div>
</fieldset>
