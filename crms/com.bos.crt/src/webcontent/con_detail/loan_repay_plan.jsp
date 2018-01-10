<%@page pageEncoding="UTF-8"%>
<fieldset>
	<legend>
		<span>还本计划表</span>
	</legend>
	<div class="nui-toolbar" style="border-bottom: 0; text-align: left;"
		id="hkjhdiv">
		<a class="nui-button" iconCls="icon-add" onclick="add('grid2')">增加</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('grid2')">删除</a>
	</div>
	<div id="grid2" class="nui-datagrid" style="width: 100%; height: auto"
		url="com.bos.conInfo.conContractInfo.queryRepayPlans.biz.ext"
		dataField="repayPlans" allowResize="true" showReloadButton="false"
		allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100,500]" multiSelect="false" pageSize="100"
		sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="periodsNumber" headerAlign="center" width="80"
				allowSort="true">
				期数 <input property="editor" class="nui-text" />
			</div>

			<div field="repayDate" headerAlign="center" allowSort="true"
				width="80" dateFormat="yyyy-MM-dd">
				日期 <input property="editor" dateFormat="yyyy-MM-dd"  allowInput="false"
					class="nui-datepicker" />
			</div>

			<div field="repayAmt" headerAlign="center" allowSort="true"
				width="80" dataType="currency" align="right" currencyUnit="￥">
				金额 <input property="editor" class="nui-textbox" />
			</div>
		</div>
	</div>
</fieldset>
