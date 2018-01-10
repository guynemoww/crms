<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<div id="form1" style="width:100%;height:auto;overflow:hidden;">
			<input name="item.acApplyId" id="item.acApplyId" class="nui-hidden" />
			<input name="nameSqlId" id="nameSqlId" class="nui-hidden" />
		</div>
		<center>
			<!-- 押品信息展示 -->
			<div id="datagrid" 
				class="nui-datagrid" 
				style="width:99.5%;height:auto;"
				sortMode="client" 
				url="com.bos.risk.common.queryByNamedSqlWithPage.biz.ext" 
				dataField="items" 
				allowAlternating="true" 
				multiSelect="false" 
				showEmptyText="true" 
				showPager="true" 
				emptyText="没有查到数据" 
				showReloadButton="false" 
				showColumnsMenu="true" 
				onrowdblclick="" 
				allowCellEdit="true" 
				allowCellSelect="true"
				sizeList="[10,20,50,100]" 
				pageSize="10">
				<div property="columns">
				 	<div field="COLL_TYPE" allowSort="true" headerAlign="center" autoEscape="false" dictTypeId="XD_YWDB0131">抵质押类型</div>
				 	<div field="SORT_TYPE" headerAlign="center" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YPZL01" allowSort="true" >抵质押物类型</div>
	        		<div field="ASSESS_VALUE" allowSort="true" headerAlign="center" dataType="currency">抵/质押物评估价值</div>
					<div field="ASSESS_DATE" headerAlign="center" allowSort="true" format="yyyy-MM-dd">评估时间</div>
					<div field="ASSESS_ORG" headerAlign="center" allowSort="true">评估机构</div>
					<div field="MORTGAGE_RATE" headerAlign="center" allowSort="true">抵/质押率%</div>
					<div field="CARD_REG_DATE" headerAlign="center" allowSort="true">登记起始日</div>
					<div field="REG_DUE_DATE" headerAlign="center" allowSort="true">登记到期日</div>
				</div>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			git.mask();
			var grid = nui.get("datagrid");
			var form = new nui.Form("#form1"); 
			var acApplyId = "<%=request.getParameter("acApplyId") %>";
			function query() {
				nui.get("item.acApplyId").setValue(acApplyId);
				nui.get("nameSqlId").setValue("com.bos.risk.sort.queryDrafMortList");
				var data = form.getData(); //获取表单多个控件的数据
		        grid.load(data);
		        git.unmask();
	    	}
	    	query();
		</script>
	</body>
</html>
