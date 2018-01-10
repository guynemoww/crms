<%@page pageEncoding="UTF-8"%>

<!-- <div class="nui-fit" style="padding:5px;">
    <div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    
    <fieldset>
		<legend>
			<span>（一）贷款资金使用用途     暂空</span>
		</legend> -->
    
    
	<div id="grid5" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.aft.normalCheck.queryMoneyUse.biz.ext" dataField="moneyUses"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="true" showColumnsMenu="true">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>        
	        <!-- <div field="PARTY_NAME" allowSort="true"  headerAlign="center">客户名称</div> -->
	        <div field="PAY_TIME" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd">支付日期</div>
	      	<div field="APPLY_AMOUNT" allowSort="true"  headerAlign="center" dataType="currency">支付金额</div>
 	        <div field="PAY_OBJECT" allowSort="true"  headerAlign="center">支付对象</div>
	        <div field="PAY_USE" allowSort="true"  headerAlign="center" >支付用途</div>
 			<div field="IS_FIT_DEAL" allowSort="true"  headerAlign="center" dictTypeId="XD_0002">是否符合审批或约定用途</div>
	     </div>
	</div>
	<!-- </fieldset>
</div> -->
