<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>还款计划</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="还款计划" >
<center>

	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"
		url="com.bos.pub.standingbook.loaninfo.queryRepaymentPlan.biz.ext"
		dataField="repayments"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="dueserialno" headerAlign="center" allowSort="true" >借据号</div>
			<div field="paydate" headerAlign="center" allowSort="true" >还款日期</div>
			<div field="currency" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="paysum" headerAlign="center" allowSort="true"  dataType="currency">应还本金</div>
		</div>
	</div>
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
	var grid = nui.get("grid1");
	grid.load({"loanDetailId":"<%=request.getParameter("loanDetailId") %>"});

   
	</script>
</body>
</html>