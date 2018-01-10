<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input type="hidden" name="item._entity" value="com.bos.dataset.cla.TbClaApproveDetail" class="nui-hidden" />
				<input name="item.cdInfoId" id="item.cdInfoId"  class="nui-hidden" />
			</div>
			<!-- 分类历史展示 -->
			<div id="datagrid" 
				class="nui-datagrid" 
				style="width:100%;height:100%;"
				sortMode="client" 
				url="com.bos.risk.sort.queryApproveList.biz.ext" 
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
					<div field="userNum" headerAlign="center" align="center" allowSort="true" dictTypeId="user">操作人 </div>
					<div field="orgNum" headerAlign="center" align="center" allowSort="true" dictTypeId="org">操作人机构代码</div>
					<div field="beforeResult" headerAlign="center" align="center" allowSort="true" dictTypeId="XD_FLCD0001">调整前分类结果</div>
					<div field="approveResult" headerAlign="center" align="center" allowSort="true" dictTypeId="XD_FLCD0001">审核结果</div>
					<div field="approveOpinion" align="center" headerAlign="center">审核意见</div>
					<div field="createTime" headerAlign="center" align="center" allowSort="true"  >创建时间</div>
				</div>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			git.mask();
			var grid = nui.get("datagrid");
			var form = new nui.Form("#form1"); 
			var cdInfoId = "<%=request.getParameter("cdInfoId") %>";
			function query() {
				if (cdInfoId) {
					nui.get("item.cdInfoId").setValue(cdInfoId);
				}
				var data = form.getData();
		        grid.load(data);
		        git.unmask();
	    	}
	    	query();
		</script>
	</body>
</html>
