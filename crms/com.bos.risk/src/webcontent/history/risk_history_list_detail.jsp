<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<div id="form1" style="width:100%;height:auto;overflow:hidden;">
			<input name="item.contractNum" id="item.contractNum"  class="nui-hidden" />
			<input name="nameSqlId" id="nameSqlId"  class="nui-hidden" value="com.bos.risk.history.queryRiskHistoryListDetail"/>
		</div>
		<center>
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
					<div field="CREATE_TIME" headerAlign="center" allowSort="true">调整日期</div>
					<div field="CLA_METHOD" headerAlign="center" allowSort="true" dictTypeId="claMethod">调整类型</div>
					<div field="CLS_RESULT" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001">调整结果</div>
					<div field="USER_NUM" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
				</div>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			git.mask();
			var grid = nui.get("datagrid");
	  		var form = new nui.Form("#form1");
	  		var contractNum = "<%=request.getParameter("contractNum")%>";
	  		nui.get("item.contractNum").setValue(contractNum);
	  		function query(){
		       var o = form.getData();
		       grid.load(o);
			   git.unmask();
			}
			query();
		</script>
	</body>
</html>
