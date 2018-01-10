<%@page pageEncoding="UTF-8"%>

<div id="">
	<fieldset>
		<legend>
			<span>本期贷款使用情况</span>
		</legend>
    
    
	<div id="grid5" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.aft.normalCheck.queryMoneyUse.biz.ext" dataField="moneyUses"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="true" showColumnsMenu="true">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>        
	        <!-- <div field="PARTY_NAME" allowSort="true"  headerAlign="center">客户名称</div> -->
	        <div field="PAY_TIME" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">支付日期</div>
	      	<div field="PAY_WAY" allowSort="true"  headerAlign="center"  dictTypeId="XD_SXYW0218">支付方式</div>
	        <div field="PAY_OBJECT" allowSort="true"  headerAlign="center">支付对象</div>
	        <div field="PAY_USE" allowSort="true"  headerAlign="center" >支付用途</div>
 			<div field="IS_FIT_DEAL" allowSort="true"  headerAlign="center" dictTypeId="XD_0002">是否符合审批或约定用途</div>
	     </div>
	</div>
	</fieldset>
</div>
