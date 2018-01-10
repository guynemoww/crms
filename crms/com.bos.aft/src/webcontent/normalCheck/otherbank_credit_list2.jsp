<%@page pageEncoding="UTF-8"%>
<div id="requiregrid">
	<fieldset>
		<legend>
			<span>（二）检查区间内，公司及实际控制人在各家银行贷款情况</span>
		</legend>
		<div class="nui-toolbar" style="border-bottom: 0; text-align: left;">
			<a class="nui-button" iconCls="icon-add" onclick="add('grid2')" id="grid2add">增加</a>
			<a class="nui-button" iconCls="icon-remove" onclick="remove('grid2')" id="grid2del">删除</a>
		</div>
		<div id="grid2" class="nui-datagrid" style="width: 100%; height: auto"
		    url="com.bos.aft.normalCheck.queryOtherBankCredit.biz.ext"
			dataField="tbAftCreditCondition" allowResize="true" showReloadButton="false"
			allowCellEdit="true" allowCellSelect="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15"
			sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="loanBank" headerAlign="center" width="80" allowSort="true">
					贷款银行 <input property="editor" class="nui-textbox" />
				</div>
				<div field="loanWay" headerAlign="center" width="80" allowSort="true">
					贷款方式（贷款、承兑等） <input property="editor" class="nui-textbox" />
				</div>
				<div field="loanSt" headerAlign="center" allowSort="true"
					width="80" dateFormat="yyyy-MM-dd">
					贷款起期 <input property="editor" dateFormat="yyyy-MM-dd"
						class="nui-datepicker" />
				</div>
				<div field="loanEnd" headerAlign="center" allowSort="true"
					width="80" dateFormat="yyyy-MM-dd">
					贷款止期 <input property="editor" dateFormat="yyyy-MM-dd"
						class="nui-datepicker" />
				</div>
				<div field="loanUse" headerAlign="center" width="80" allowSort="true">
					贷款用途 <input property="editor" class="nui-textbox" />
				</div>
				<div field="loanAmt" headerAlign="center" allowSort="true"
					width="80" dataType="currency" align="right" currencyUnit="￥">
					贷款金额 <input property="editor" class="nui-textbox" />
				</div>
				<div field="loanGuaranty" headerAlign="center" width="80" allowSort="true">
					贷款担保方式 <input property="editor" class="nui-textbox" />
				</div>
				<div field="loanRate" headerAlign="center" allowSort="true"
					width="80" align="right" dataType="float">
					贷款利率（%） <input property="editor" class="nui-textbox" />
				</div>
			</div>
		</div>
	</fieldset>
</div>
