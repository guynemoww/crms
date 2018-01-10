<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>贷款发放清单台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="贷款发放清单台账" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
	<div class="nui-dynpanel" columns="6">
		<label>合同编号：</label>
		<input name="loanPayoutInfoList.contractNum" required="false" class="nui-textbox nui-form-input"  />
		<label>贷款申请人：</label>
		<input name="loanPayoutInfoList.partyName" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"  
		url="com.bos.pub.standingbook.loaninfo.queryloanPayoutInfoList.biz.ext"
		dataField="loanPayoutInfoLists"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>	
			<div field="contractNum" headerAlign="center" allowSort="true" >合同编号</div>借据号
			<div field="businessNum" headerAlign="center" allowSort="true" >借据号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >贷款申请人</div>
			<div field="guaranteeName" headerAlign="center" allowSort="true" >担保人名称</div>
			<div field="loanType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1020">担保方式1</div>
			<div field="contractTotalAmt" headerAlign="center" allowSort="true" dataType="currency">发放金额(元)</div>
			<div field="contractBalance" headerAlign="center" allowSort="true" dataType="currency">贷款余额(元)</div>
			<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product" >业务品种</div>
			<div field="yearRate" headerAlign="center" allowSort="true" dataType="currency">利率</div>
			<div field="rateFloatProportion" headerAlign="center" allowSort="true" dataType="currency">比例</div>
			<div field="rateFloatMember" headerAlign="center" allowSort="true" dataType="currency">浮动</div>
			<div field="contractSignDate" headerAlign="center" allowSort="true">贷款期限(月)</div>
			<div field="startDate" headerAlign="center" allowSort="true" >借款日</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >到期日</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">客户经理</div>
		</div>
	</div>
</center>
</div>
</div>		
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        //var duebillserialno = "<%=request.getParameter("duebillserialno") %>";
		//var json = {"tbBatchWastebook/duebillserialno":duebillserialno};		
        //grid.load(json);
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}
	

	</script>
</body>
</html>