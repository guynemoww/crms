<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>贷后检查表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:5013px;height:auto;overflow:hidden;">
<div title="贷后检查表" >
<center>
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:35px" borderStyle="border:0;">
<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
    </div>
<div id="form1" style="width:auto;height:auto;overflow:hidden;overflow:hidden;">
	<!--<div class="nui-dynpanel" columns="6">
		<label>合同号：</label>
		<input name="loanrepayinfo.contractNum" required="false" class="nui-textbox nui-form-input"  />
		<label>贷款申请人：</label>
		<input name="loanrepayinfo.cp" required="false" class="nui-textbox nui-form-input"  />
		<label>借款日：</label>
		<input name="loanrepayinfo.loanActualPaymentDate" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>-->
	<div id="grid1" class="nui-datagrid" style="width:5000px;height:auto;" 
		url="com.bos.pub.standingbook.aftmanager.aftchecklist.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >数据日期</div>
			<div field="orgNum" width="100" headerAlign="center" allowSort="true" >机构号</div>
			<div field="inspectDate" width="100" headerAlign="center" allowSort="true" >检查发生日期</div>
			<div field="loanNum" width="100" headerAlign="center" allowSort="true" >借贷借据编号</div>
			<div field="contractNum" width="100" headerAlign="center" allowSort="true" >借贷合同号</div>
			<div field="partyNum" width="100" headerAlign="center" allowSort="true" >客户统一编号</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >本期财务报表日期</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >上期财务报表日期 </div>
			<div field="productType" width="100" headerAlign="center" allowSort="true" dictTypeId="product">业务品种</div>
			<div field="loanAmt" width="100" headerAlign="center" allowSort="true" >金额</div>
			<div field="currencyCd" width="100" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="startDate" width="100" headerAlign="center" allowSort="true" >贷款合同约定发放日期</div>
			<div field="expirationDate" width="100" headerAlign="center" allowSort="true" >合同到期日期</div>
			<div field="mainSuretyMode" width="100" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1020">主要担保方式</div>
			<div field="loanUse" width="100" headerAlign="center" allowSort="true" >贷款用途</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >检查地点</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >检查内容</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >约见人员姓名</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >营业执照年检日期</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >税务登记证年检日期</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >贷款卡年检日期</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >环保、安全生产年检日期</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >许可证年检日期</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >授信敞口余额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >贷款敞口余额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >银票敞口余额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >商票贴现（保贴）敞口余额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >贸易融资（含开证）敞口余额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >保函敞口余额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >其他敞口余额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目投资金额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >资本金到位金额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >贷款到位金额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >其他资金到位金额</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目资本金比例</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >资本金到位比例</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >贷款到位比例</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >其他资金到位比例</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目资金总体评价</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目进度</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目建设情况评价</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目可销售面积</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目已销售面积</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >销售均价</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >项目销售情况</div>
			<div field="userNum" width="100" headerAlign="center" allowSort="true" >客户经理工号</div>
			<div field="" width="100" headerAlign="center" allowSort="true" >记录状态</div>
		</div>
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
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}
	
	//导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.orgNum != null) return true;
	});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=225";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }
    
	</script>
</body>
</html>