<%@page pageEncoding="UTF-8"%>
<div id="repaygrid1">
	<fieldset>
		<legend>
			<span>变更前还款计划表</span>
		</legend>
		<!-- <div class="nui-dynpanel" columns="4" id="cs">
			<label class="nui-form-label">年利率：</label>
			<input id="yearrate" class="nui-text nui-form-input" name="yearrate"/>
			<label class="nui-form-label">期次：</label>
			<input id="hkqc" class="nui-text nui-form-input" name="hkqc"/>
			<label class="nui-form-label">结息周期：</label>
			<input id="jxzq"  name="jxzq"  class="nui-text nui-form-input" dictTypeId="XD_SXCD1018" />
			<label class="nui-form-label">贷款起期：</label>
			<input id="dkqq" class="nui-text nui-form-input" name="dkqq"/>
		</div>  -->
		<div class="nui-dynpanel" columns="4" id="show">
			<label class="nui-form-label">还款金额：</label>
			<input id="totalAmt1" class="nui-text nui-form-input" name="totalAmt1"/>
			<label class="nui-form-label">本金：</label>
			<input id="bj1" class="nui-text nui-form-input" name="bj1"/>
			<label class="nui-form-label">利息总额：</label>
			<input id="totalLx1" class="nui-text nui-form-input" name="totalLx1"/>
			<label class="nui-form-label">总期次：</label>
			<input id="totalTerm1" class="nui-text nui-form-input" name="totalTerm1"/>
			<label class="nui-form-label">贷款起期：</label>
			<input id="dkqq1" class="nui-text nui-form-input" name="dkqq1" dateFormat="yyyy-MM-dd"/>
		</div>  
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.aft.conLoanChange.getHkjhListAft.biz.ext" dataField="hkjhs"
			allowResize="true" showReloadButton="false" allowCellEdit="true" 
		    allowCellSelect="true" showPager="false" 
			sizeList="[10,20,30,50,100]" multiSelect="false" pageSize="100" sortMode="client">
			<div property="columns">
            	<div type="indexcolumn" width="40px">序号</div>
				<div field="OLD_REPAY_DATE" headerAlign="center">还款时间
					<input property="editor" class="nui-text" />
				</div>
				<div field="OLD_DAYS" headerAlign="center">天数
					<input property="editor" class="nui-text"/>
				</div>
				<div field="OLD_REPAY_AMT" headerAlign="center">还款金额
					<input property="editor" class="nui-text"/>
				</div>
				<div field="OLD_BJ" headerAlign="center">本金
					<input property="editor" class="nui-text"/>
				</div>
				<div field="OLD_LX" headerAlign="center">利息
					<input property="editor" class="nui-text" />
				</div>
				<div field="OLD_SYBJ" headerAlign="center">剩余本金
					<input property="editor" class="nui-text" />
				</div>
			</div>
		</div>	

	</fieldset>
</div>
