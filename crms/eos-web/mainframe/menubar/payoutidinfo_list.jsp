<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>放款流水台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<center>
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"
		url="com.bos.pub.standingbook.loaninfo.payoutldinfo.biz.ext"
		dataField="payoutldInfos"
		allowResize="true" showReloadButton="false" onselectchanged="getBatch" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="6" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div type="indexcolumn" >序号</div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同号</div>
			<div field="businessNum" headerAlign="center" allowSort="true" >借款编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >借款人</div>
			<div field="payTypeName" headerAlign="center" allowSort="true" >放款类型</div>
			<div field="startDate" headerAlign="center" allowSort="true" >放款日</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >到期日</div>
			<div field="contractTotalAmt" headerAlign="center" allowSort="true" dataType="currency">合同金额（元）</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">出账金额（元）</div>
			<div field="arrangeAotslypayMoney" headerAlign="center" allowSort="true" dataType="currency">自主 </div>
			<div field="arrangeLiftpayMoney" headerAlign="center" allowSort="true" dataType="currency">受托 </div>
			<div field="mainSuretyMode" headerAlign="center" allowSort="true" >担保类型</div>
			<div field="distributeType" headerAlign="center" allowSort="true" >发放类型</div>
			<div field="orgname" headerAlign="center" allowSort="true" >支行</div>
			<div field="enpname" headerAlign="center" allowSort="true" >客户经理</div>
		</div>
	</div>
</center>
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}
	
	function getBatch(){
		var row=grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		this.max();
		nui.open({
	            url:nui.context+"/pub/standingBook/openRepaymentPlan.jsp?loanDetailId="+row.loanDetailId,
	            showMaxButton: true,
	            title: "还款计划",
	            width: 800,
	            height: 500,
	            ondestroy: function(e) {
	            }
	        });
	}
	</script>
</body>
</html>