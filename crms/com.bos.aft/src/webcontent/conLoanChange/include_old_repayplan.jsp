<%@page pageEncoding="UTF-8"%>
<div id="repaygrid1">
	<fieldset>
		<legend>
			<span>变更前还款计划表</span>
		</legend>
		<div id="grid1" class="nui-datagrid" style="width: 100%; height: auto"
		    url="com.bos.aft.conLoanChange.findRepayplanChangeOld.biz.ext"
			dataField="repayPlans" allowResize="true" showReloadButton="false"
			allowCellEdit="true" allowCellSelect="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
			sortMode="client">
			<div property="columns">
				<div field="oldPeriodsNum" headerAlign="center" width="80"
					allowSort="true">
					期数 <input property="editor" class="nui-text nui-form-input" />
				</div>

				<div field="oldRepayDate" headerAlign="center" allowSort="true"
					width="80" dateFormat="yyyy-MM-dd">
					日期 <input property="editor" dateFormat="yyyy-MM-dd"
						class="nui-text nui-form-input" />
				</div>

				<div field="oldRepayAmt" headerAlign="center" allowSort="true"
					width="80" dataType="currency" align="right" currencyUnit="￥">
					金额 <input property="editor" class="nui-text nui-form-input" />
				</div>
			</div>
		</div> 
		<!-- <div id="grid1" class="nui-datagrid" style="width: 100%; height: auto"
		    url="com.bos.aft.conLoanChange.findRepayplanOld.biz.ext"
			dataField="repayPlans" allowResize="true" showReloadButton="false"
			allowCellEdit="true" allowCellSelect="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
			sortMode="client">
			<div property="columns">
				<div field="periodsNumber" headerAlign="center" width="80"
					allowSort="true">
					期数 <input property="editor" class="nui-text nui-form-input" />
				</div>

				<div field="repayDate" headerAlign="center" allowSort="true"
					width="80" dateFormat="yyyy-MM-dd">
					日期 <input property="editor" dateFormat="yyyy-MM-dd"
						class="nui-text nui-form-input" />
				</div>

				<div field="repayAmt" headerAlign="center" allowSort="true"
					width="80" dataType="currency" align="right" currencyUnit="￥">
					金额 <input property="editor" class="nui-text nui-form-input" />
				</div>
			</div>
		</div>   -->
	</fieldset>
</div>
