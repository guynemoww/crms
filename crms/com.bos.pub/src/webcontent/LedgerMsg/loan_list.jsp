<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-28
  - Description:TB_CON_LOAN_SUMMARY, com.bos.dataset.pay.TbConLoanSummary
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="contractId" name="tbConLoanSummary.contractId" required="false" class="nui-hidden " vtype="maxLength:32" />
<!-- 
	<div class="nui-dynpanel" columns="6">
		
		<label>T24借据编号/票据借据编号：</label>
		<input name="tbConLoanSummary.businessNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />


		<label>信贷员工号：</label>
		<input name="tbConLoanSummary.creditStaffNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>期限：</label>
		<input name="tbConLoanSummary.creditTerm" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />


		<label>票据类批次号：</label>
		<input name="tbConLoanSummary.invoiceBatchNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>是否轧差台账标示：</label>
		<input name="tbConLoanSummary.isNetIdentity" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>贷款入账账号：</label>
		<input name="tbConLoanSummary.loanAccount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>贷款分户账号：</label>
		<input name="tbConLoanSummary.loanAccountNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>贷款实际到期日期：</label>
		<input name="tbConLoanSummary.loanActualMaturity" required="false" class="nui-datepicker nui-form-input" />

		<label>贷款实际发放日期：</label>
		<input name="tbConLoanSummary.loanActualPaymentDate" required="false" class="nui-datepicker nui-form-input" />

		<label>借据金额：</label>
		<input name="tbConLoanSummary.loanAmt" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>借据余额：</label>
		<input name="tbConLoanSummary.loanBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>引用业务明细或者合同明细：</label>
		<input name="tbConLoanSummary.loanDetailId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />


		<label>借据状态：</label>
		<input name="tbConLoanSummary.loanStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_FHCD1008"/>

		<label>借据信息ID：</label>
		<input name="tbConLoanSummary.loanSummaryId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>贷款期限：</label>
		<input name="tbConLoanSummary.loanTerm" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>保证金帐号：</label>
		<input name="tbConLoanSummary.marginAccount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>上期呆账余额：</label>
		<input name="tbConLoanSummary.mfbadbalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>票据号码：</label>
		<input name="tbConLoanSummary.noteNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>票面金额：</label>
		<input name="tbConLoanSummary.parValue" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>业务品种：</label>
		<input name="tbConLoanSummary.productType" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:20" dictTypeId="product"/>

		<label>还款账号：</label>
		<input name="tbConLoanSummary.repaymentAccount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />


		<label>还款日期：</label>
		<input name="tbConLoanSummary.repayDate" required="false" class="nui-datepicker nui-form-input" />

		<label>还款利息：</label>
		<input name="tbConLoanSummary.repayLnterest" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />


		<label>供应链放款申请编号：</label>
		<input name="tbConLoanSummary.scfApplyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

	</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
</div>
 -->
</div>
			
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.ledgerMsg.getTbConLoanSummaryList.biz.ext"
	dataField="tbConLoanSummarys"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="businessNum" headerAlign="center" allowSort="true" >票据借据编号</div>
		<div field="continuousArrearsPeriods" headerAlign="center" allowSort="true" >连续欠款期数</div>
		<div field="contractId" headerAlign="center" allowSort="true" >信贷合同ID</div>
		<div field="creditStaffNumber" headerAlign="center" allowSort="true" >信贷员工号</div>
		<div field="creditTerm" headerAlign="center" allowSort="true" >期限</div>
		<div field="currencyCd" headerAlign="center" allowSort="true"  dictTypeId="CD000001">币种</div>
		<div field="currentPeriod" headerAlign="center" allowSort="true" >当前期数</div>
		<div field="customerUnifiedNum" headerAlign="center" allowSort="true" >客户统一编号</div>
		<div field="cycleUnit" headerAlign="center" allowSort="true" >期限单位</div>
		<div field="depositAmount" headerAlign="center" allowSort="true" >保证金金额</div>
		<div field="detailedItemName" headerAlign="center" allowSort="true" >明细科目名称</div>
		<div field="detailItemNumber" headerAlign="center" allowSort="true" >明细科目编号</div>
		<div field="entrustedPaymentMark" headerAlign="center" allowSort="true" >受托支付标志</div>
		<div field="interestbalance1" headerAlign="center" allowSort="true" >表内欠息余额</div>
		<div field="interestbalance2" headerAlign="center" allowSort="true" >表外欠息余额</div>
		<div field="invoiceBatchNum" headerAlign="center" allowSort="true" >票据类批次号</div>
		<div field="loanAccountNumber" headerAlign="center" allowSort="true" >贷款分户账号</div>
		<div field="loanAmt" headerAlign="center" allowSort="true" >借据金额</div>
		<div field="loanBalance" headerAlign="center" allowSort="true" >借据余额</div>
		<div field="loanNum" headerAlign="center" allowSort="true" >CRMS借据编号</div>
		<div field="loanOfficerName" headerAlign="center" allowSort="true" >信贷员姓名</div>
		<div field="loanStatus" headerAlign="center" allowSort="true" dictTypeId="XD_FHCD1008">借据状态</div>
		<div field="marginAccount" headerAlign="center" allowSort="true" >保证金帐号</div>
		<div field="mfbadbalance" headerAlign="center" allowSort="true" >上期呆账余额</div>
		<div field="mfdullbalance" headerAlign="center" allowSort="true" >上期呆滞余额</div>
		<div field="mfBalance" headerAlign="center" allowSort="true" >上期总余额</div>
		<div field="normalbalance" headerAlign="center" allowSort="true" >正常余额</div>
		<div field="noteNumber" headerAlign="center" allowSort="true" >票据号码</div>
		<div field="nowTurnSign" headerAlign="center" allowSort="true" >现转标志</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	nui.get("contractId").setValue("<%=request.getParameter("contractId") %>");
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context+ "/pub/LedgerMsg/ledgerLoan.jsp?loanSummaryId="+row.loanSummaryId+"&view="+v,
                title: "编辑", 
                width: 800,
        		height: 650,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    


	</script>
</body>
</html>
