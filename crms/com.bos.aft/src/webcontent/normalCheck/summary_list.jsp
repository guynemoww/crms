<%@page pageEncoding="UTF-8"%>
<div id="summarygrid">
	<fieldset>
		<legend>
			<span>贷款基本情况</span>
		</legend>
		
		<div id="grid1" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.normalCheck.querySummaryInfo.biz.ext" dataField="summaryItems"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">           
		        <div type="indexcolumn" width="50px;">序号</div>
		        <!-- <div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div> -->
		        <div field="CONTRACT_NUM" headerAlign="center" align="center">合同编号</div> 
		        <div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">贷款种类</div>
				<div field="CONTRACT_AMT" headerAlign="center" align="right" dataType="currency">贷款金额（元）</div>
				<div field="CON_YU_E" headerAlign="center" align="right" dataType="currency">贷款余额（元）</div>
				<div field="CONTRACT_TERM"headerAlign="center" align="center">贷款期限</div>
				 <div field="BEGIN_DATE" headerAlign="center" align="center">贷款起期</div>
				<div field="END_DATE" headerAlign="center" align="center">贷款止期</div>  
		        <div field="GUARANTY_TYPE" headerAlign="center" align="center" dictTypeId="CDZC0005">担保方式</div>
		       	<div field="" headerAlign="center" align="center" dictTypeId="">检查方式</div>
		        
		    </div>
		</div>
		
	</fieldset>
</div>


<script type="text/javascript">


</script>