<%@page pageEncoding="UTF-8"%>
<div id="repaygrid2">
	<fieldset>
		<legend>
			<span>变更后还款计划表</span>
		</legend>
		<!-- <legend>
			<span>已结计划表</span>
		</legend>
			<div id="grid3" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.aft.conLoanChange.findOverRepayplan.biz.ext" dataField="yhzd"
			allowResize="true" showReloadButton="false" allowCellEdit="false" 
		    allowCellSelect="true" showPager="false" 
			sizeList="[10,20,30,50,100]" multiSelect="false" pageSize="100" sortMode="client">
			<div property="columns">
			<div type="checkcolumn">选择</div>
				<div field="END_DATE" headerAlign="center"  align="center"allowSort="true"
					width="80" >
					日期 
				</div>
				<div field="RCV_PRN" headerAlign="center" allowSort="true"
					width="80" dataType="currency" align="right" currencyUnit="￥">
					金额 <input property="editor" class="nui-textbox" />
				</div>
			</div>
		</div>
		<legend>
			<span>未结计划表</span>
		</legend> -->
		<div class="nui-toolbar" style="border-bottom: 0; text-align: left;">
			<a class="nui-button" iconCls="icon-add" onclick="add('grid2')" id="grid2add">增加</a>
			<a class="nui-button" iconCls="icon-remove" onclick="remove('grid2')" id="grid2del">删除</a>
		</div>
		<div id="grid2" class="nui-datagrid" style="width: 100%; height: auto"
		    url="com.bos.aft.conLoanChange.findRepayplanChange.biz.ext"
			dataField="repayPlans2" allowResize="true" showReloadButton="false"
			allowCellEdit="true" allowCellSelect="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
			sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="newPeriodsNum" headerAlign="center" width="80"
					allowSort="true">
					期数 <input property="editor" class="nui-textbox" />
				</div>

				<div field="newRepayDate" headerAlign="center" allowSort="true"
					width="80" dateFormat="yyyy-MM-dd">
					日期 <input property="editor" dateFormat="yyyy-MM-dd"
						class="nui-datepicker" />
				</div>

				<div field="newRepayAmt" headerAlign="center" allowSort="true"
					width="80" dataType="currency" align="right" currencyUnit="￥">
					金额 <input property="editor" class="nui-textbox" />
				</div>
			</div>
		</div>
	</fieldset>
</div>
