<%@page pageEncoding="UTF-8"%>
<div id="repaygrid2">
	<fieldset>
		<legend>
			<span>变更后还款计划表</span>
		</legend>
		<!-- <div class="nui-dynpanel" columns="4" id="cs2">
			<label class="nui-form-label">年利率：</label>
			<input id="yearrate2" class="nui-text nui-form-input" name="yearrate2"/>
			<label class="nui-form-label">期次：</label>
			<input id="hkqc2" class="nui-text nui-form-input" name="hkqc2"/>
			<label class="nui-form-label">结息周期：</label>
			<input id="jxzq2"  name="jxzq2"  class="nui-text nui-form-input" dictTypeId="XD_SXCD1018" />
			<label class="nui-form-label">贷款起期：</label>
			<input id="dkqq2" class="nui-text nui-form-input" name="dkqq2"/>
		</div> -->
		<div class="nui-dynpanel" columns="4" id="show2">
			<label class="nui-form-label">还款金额：</label>
			<input id="totalAmt2" class="nui-text nui-form-input" name="totalAmt2"/>
			<label class="nui-form-label">本金：</label>
			<input id="bj2" class="nui-text nui-form-input" name="bj2"/>
			<label class="nui-form-label">利息总额：</label>
			<input id="totalLx2" class="nui-text nui-form-input" name="totalLx2"/>
			<label class="nui-form-label">总期次：</label>
			<input id="totalTerm2" class="nui-text nui-form-input" name="totalTerm2"/>
			<label class="nui-form-label">贷款起期：</label>
			<input id="dkqq2" class="nui-text nui-form-input" name="dkqq2" dateFormat="yyyy-MM-dd"/>
			<label class="nui-form-label">首次还款日期：</label>
			<input id="schkq" class="nui-datepicker nui-form-input" name="schkq" onvaluechanged="firstRepayDayChange"/> 
		</div>   
		<div class="nui-toolbar" style="border-bottom:0;text-align: left;" id="hkjhdiv">
			<a class="nui-button" iconCls="icon-add" onclick="refresh()">重新计算</a>
			<a class="nui-button" iconCls="icon-add" onclick="remove('grid2')">删除</a>
		</div>
		<div id="grid2" class="nui-datagrid" style="width: 100%; height: auto"
		    url="com.bos.aft.conLoanChange.findRepayplanChangeXW.biz.ext"
			dataField="repayPlans2" allowResize="true" showReloadButton="false"
			allowCellEdit="true" allowCellSelect="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15"
			sortMode="client">
			<div property="columns">
				<!-- <div type="checkcolumn">选择</div> -->
				<!-- <div field="NEW_PERIODS_NUM" headerAlign="center" width="80"
					allowSort="true">
					期数 <input property="editor" class="nui-textbox" />
				</div>

				<div field="NEW_REPAY_DATE" headerAlign="center" allowSort="true"
					width="80" dateFormat="yyyy-MM-dd">
					日期 <input property="editor" dateFormat="yyyy-MM-dd"
						class="nui-datepicker" />
				</div> 

				<div field="NEW_REPAY_AMT" headerAlign="center" allowSort="true"
					width="80" dataType="currency" align="right" currencyUnit="￥">
					金额 <input property="editor" class="nui-textbox" />
				</div>  -->
				<div type="checkcolumn" >选择</div>
				<div type="indexcolumn" width="40px">序号</div>
				<div field="NEW_REPAY_DATE" headerAlign="center" allowSort="true"
					dateFormat="yyyy-MM-dd">
					还款时间 <input property="editor" dateFormat="yyyy-MM-dd"
						class="nui-datepicker" onvaluechanged="dataChange(1)"/>
				</div>
				<div field="NEW_DAYS" headerAlign="center">天数
					<input property="editor" class="nui-text"/>
				</div>
				<div field="NEW_REPAY_AMT" headerAlign="center">还款金额
					<input property="editor" class="nui-textbox" onvaluechanged="dataChange(2)"/>
				</div>
				<div field="NEW_BJ" headerAlign="center">本金
					<input property="editor" class="nui-textbox" onvaluechanged="dataChange(3)"/>
				</div>
				<div field="NEW_LX" headerAlign="center">利息
					<input property="editor" class="nui-text" />
				</div>
				<div field="NEW_SYBJ" headerAlign="center">剩余本金
					<input property="editor" class="nui-text" />
				</div>
			</div>
		</div>
	</fieldset>
</div>
