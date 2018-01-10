<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-03 10:28:23
  - Description:
-->
<head>
<title>放款信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
    <div><h4>借据列表</h4></div>
    <div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
	    <a class="nui-button" id="creat" onclick="add">放款</a>
	    <a class="nui-button" id="onloanNum" onclick="querysummaryInfo">查看借据</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pay.LoanSummary.queryLoanSummary.biz.ext" dataField="loanInfos"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="true" showColumnsMenu="true">
	    <div property="columns">
	        <div type="checkcolumn" >选择</div>
	        <div type="indexcolumn">序号</div>        
	        <div field="LOAN_NUM" allowSort="true"  headerAlign="center">放款编号</div>
	        <div field="LOAN_AMT" allowSort="true"  headerAlign="center" dataType="currency">金额</div>
	        <div field="CURRENCY_CD" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">放款币种</div>
	        <div field="SUMMARY_STATUS_CD" allowSort="true"  headerAlign="center" dictTypeId="XD_SXYW0226">借据状态</div>
	        <div field="USER_NUM" allowSort="true"  headerAlign="center" dataType="user">经办人</div>
			<div field="ORG_NUM" allowSort="true"  headerAlign="center" dictTypeId="org">经办机构</div>
	     </div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var contractId="<%=request.getParameter("contractId") %>";
	var grid = nui.get("grid");
	initPage();
	function initPage(){
		grid.load({"contractId":"<%=request.getParameter("contractId") %>"});
	}
	
	function add(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		if("02"==row.SUMMARY_STATUS_CD){
			alert("此借据已放款");
			return;
		}
		nui.get("creat").setEnabled(false);
		nui.open({
			url:nui.context + "/pay/payout_apply/payout_info.jsp?loanId="+row.LOAN_ID,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	top.bizConWin = this;
            	initPage();
            	nui.get("creat").setEnabled(true);
            }
		});
	}
	function querysummaryInfo(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		nui.open({
			url:nui.context + "/pay/payout_info/summary_view.jsp?summaryId="+row.SUMMARY_ID,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            }
		});
	}
</script>
</body>
</html>