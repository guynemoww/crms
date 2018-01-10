<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-14 19:20:27
  - Description:
-->
<head>
<title>借据流水信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width: 99.5%; height: 99.5%; overflow: hidden;">
 	
		<div id="grid" class="nui-datagrid" style="width: 100%; height: auto;"
			sortMode="client" url="com.bos.pay.LoanSummary.QueryHklsList.biz.ext"
			dataField="dataObjects" allowAlternating="true" multiSelect="true"
			showEmptyText="true" emptyText="没有查到数据" showReloadButton="true"
			sizeList="[10,15,20,50,100]"   pageSize="10"  showPager="false"
			showColumnsMenu="true">
			<div property="columns">
 				<div field="XH" allowSort="true" headerAlign="center">期次</div>
				<div field="JHHKR" allowSort="true" headerAlign="center">计划还款日</div>
				<div field="BZ" allowSort="true" headerAlign="center">币种</div>
				<div field="RCV_PRN" allowSort="true" headerAlign="center" >应还本金</div>
				<div field="RCV_ITR" allowSort="true" headerAlign="center"  >应还利息</div>
				<div field="RCV_PNS_ITR" allowSort="true" headerAlign="center" >本期结计罚息</div>
				<div field="PAD_PRN" allowSort="true" headerAlign="center" >实还本金</div>
				<div field="PAD_ITR" allowSort="true" headerAlign="center" >实还利息</div>
				<div field="PNS_ITR" allowSort="true" headerAlign="center" >实还罚息</div>
				<div field="WHBJ" allowSort="true" headerAlign="center" >未还本金</div>
				<div field="RCV_DATE" allowSort="true" headerAlign="center">结清日期</div>
				
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
	nui.parse();
	var form= new nui.Form("#form");
	var summarynum="<%=request.getParameter("biznum")%>";
	var grid = nui.get("grid");
	
	initPage();
	function initPage(){
	
			var o = form.getData();
			o.summarynum=summarynum;
			grid.load(o);
		}
	
		
	
	</script>
</body>
</html>