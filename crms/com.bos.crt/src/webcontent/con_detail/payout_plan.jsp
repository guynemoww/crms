<%@page pageEncoding="UTF-8"%>
<fieldset>
	<legend>
		<span>提款计划</span>
	</legend>
	<div class="nui-toolbar" style="border-bottom: 0; text-align: left;" id="payoutdiv">
		<a class="nui-button" iconCls="icon-add" onclick="add('grid1')">增加</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove('grid1')">删除</a>
	</div>
	<div id="grid1" class="nui-datagrid" style="width: 100%; height: auto"
		url="com.bos.conInfo.conContractInfo.getPayoutPlan.biz.ext"
		dataField="payoutPlans" allowResize="true" showReloadButton="false"
		allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100,500]" multiSelect="false" pageSize="100"
		sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div type="indexcolumn" width="40px">序号</div>
			<div field="payoutDate" headerAlign="center" allowSort="true"
				width="80" dateFormat="yyyy-MM-dd">
				提款日期 <input property="editor" dateFormat="yyyy-MM-dd"  allowInput="false"
					class="nui-datepicker" />
			</div>

			<div field="payoutAmt" headerAlign="center" allowSort="true"
				width="80" dataType="currency" align="right" currencyUnit="￥">
				金额 <input property="editor" class="nui-textbox" />
			</div>
		</div>
	</div>
</fieldset>
