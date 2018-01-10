<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-28

  - Description:TB_CON_LOAN_SUMMARY, com.bos.dataset.pay.TbConLoanSummary-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbConLoanSummary" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	
		<label>呆账科目：</label>
		<input name="tbConLoanSummary.badsubjectno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>银行机构代码：</label>
		<input name="tbConLoanSummary.bankCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>银行机构名称：</label>
		<input name="tbConLoanSummary.bankName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		<label>采集日期：</label>
		<input name="tbConLoanSummary.acquisitionDate" required="true" class="nui-datepicker nui-form-input" />

		<label>垫款标志：</label>
		<input name="tbConLoanSummary.advanceflag" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>呆账余额：</label>
		<input name="tbConLoanSummary.badbalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>基准利率：</label>
		<input name="tbConLoanSummary.benchmarkInterestRate" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:20" dictTypeId="RateType"/>

		<label>T24借据编号/票据借据编号：</label>
		<input name="tbConLoanSummary.businessNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>分类结果：</label>
		<input name="tbConLoanSummary.classificationResultCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_FLCD0001"/>

		<label>连续欠款期数：</label>
		<input name="tbConLoanSummary.continuousArrearsPeriods" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>信贷合同ID：</label>
		<input name="tbConLoanSummary.contractId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>创建时间：</label>
		<input name="tbConLoanSummary.createTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>信贷员工号：</label>
		<input name="tbConLoanSummary.creditStaffNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>期限：</label>
		<input name="tbConLoanSummary.creditTerm" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>贷款资金使用位置：</label>
		<input name="tbConLoanSummary.creditUseAddress" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>累计欠款期数：</label>
		<input name="tbConLoanSummary.cumulativeArrearsPeriods" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>币种：</label>
		<input name="tbConLoanSummary.currencyCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001"/>

		<label>当前期数：</label>
		<input name="tbConLoanSummary.currentPeriod" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>客户统一编号：</label>
		<input name="tbConLoanSummary.customerUnifiedNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>期限单位：</label>
		<input name="tbConLoanSummary.cycleUnit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_GGCD6009"/>

		<label>保证金金额：</label>
		<input name="tbConLoanSummary.depositAmount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>明细科目名称：</label>
		<input name="tbConLoanSummary.detailedItemName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>明细科目编号：</label>
		<input name="tbConLoanSummary.detailItemNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>贴现实贴金额：</label>
		<input name="tbConLoanSummary.discountsum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>呆滞余额：</label>
		<input name="tbConLoanSummary.dullbalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>呆滞科目：</label>
		<input name="tbConLoanSummary.dullsubjectno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>终结日期：</label>
		<input name="tbConLoanSummary.endDate" required="true" class="nui-datepicker nui-form-input" />

		<label>受托支付标志：</label>
		<input name="tbConLoanSummary.entrustedPaymentMark" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="CDXY0144"/>

		<label>展期标志：</label>
		<input name="tbConLoanSummary.extensionFlag" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YesOrNo"/>

		<label>金融许可证号：</label>
		<input name="tbConLoanSummary.financialLicenseNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>表内欠息余额：</label>
		<input name="tbConLoanSummary.interestbalance1" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>表外欠息余额：</label>
		<input name="tbConLoanSummary.interestbalance2" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>结息方式：</label>
		<input name="tbConLoanSummary.interestBearingManner" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1109"/>

		<label>入账机构号：</label>
		<input name="tbConLoanSummary.internalMechanism" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>票据类批次号：</label>
		<input name="tbConLoanSummary.invoiceBatchNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>是否轧差台账标示：</label>
		<input name="tbConLoanSummary.isNetIdentity" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>贷款入账账号：</label>
		<input name="tbConLoanSummary.loanAccount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>贷款分户账号：</label>
		<input name="tbConLoanSummary.loanAccountNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>贷款实际到期日期：</label>
		<input name="tbConLoanSummary.loanActualMaturity" required="true" class="nui-datepicker nui-form-input" />

		<label>贷款实际发放日期：</label>
		<input name="tbConLoanSummary.loanActualPaymentDate" required="true" class="nui-datepicker nui-form-input" />

		<label>借据金额：</label>
		<input name="tbConLoanSummary.loanAmt" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>借据余额：</label>
		<input name="tbConLoanSummary.loanBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>引用业务明细或者合同明细：</label>
		<input name="tbConLoanSummary.loanDetailId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>贷款五级分类：</label>
		<input name="tbConLoanSummary.loanFiveLevelClassification" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:20" dictTypeId="XD_FLCD0001"/>

		<label>CRMS借据编号：</label>
		<input name="tbConLoanSummary.loanNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>信贷员姓名：</label>
		<input name="tbConLoanSummary.loanOfficerName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>贷款原始到期日期：</label>
		<input name="tbConLoanSummary.loanOriginalExpirationDate" required="true" class="nui-datepicker nui-form-input" />

		<label>放款核准单号：</label>
		<input name="tbConLoanSummary.loanQuasiOdd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>贷款重组日期：</label>
		<input name="tbConLoanSummary.loanRecombinationDate" required="true" class="nui-datepicker nui-form-input" />

		<label>借据状态：</label>
		<input name="tbConLoanSummary.loanStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_FHCD1008"/>

	
		<label>贷款期限：</label>
		<input name="tbConLoanSummary.loanTerm" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>保证金帐号：</label>
		<input name="tbConLoanSummary.marginAccount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>上期呆账余额：</label>
		<input name="tbConLoanSummary.mfbadbalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>上期呆滞余额：</label>
		<input name="tbConLoanSummary.mfdullbalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>上期正常余额：</label>
		<input name="tbConLoanSummary.mfnormalbalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>上期三个月以内逾期余额：</label>
		<input name="tbConLoanSummary.mfoverduebalance1" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>上期三个月以上逾期余额：</label>
		<input name="tbConLoanSummary.mfoverduebalance2" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>上期总余额：</label>
		<input name="tbConLoanSummary.mfBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>正常余额：</label>
		<input name="tbConLoanSummary.normalbalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>票据号码：</label>
		<input name="tbConLoanSummary.noteNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>现转标志：</label>
		<input name="tbConLoanSummary.nowTurnSign" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="YesOrNo"/>

		<label>三个月以内逾期余额：</label>
		<input name="tbConLoanSummary.overduebalance1" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>三个月以上逾期余额：</label>
		<input name="tbConLoanSummary.overduebalance2" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>三个月以内逾期科目：</label>
		<input name="tbConLoanSummary.overduesubjectno1" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>三个月以上逾期科目：</label>
		<input name="tbConLoanSummary.overduesubjectno2" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>逾期标志：</label>
		<input name="tbConLoanSummary.overdueFlag" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="YesOrNo"/>

		<label>参与人ID：</label>
		<input name="tbConLoanSummary.partyId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>票面金额：</label>
		<input name="tbConLoanSummary.parValue" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>业务品种：</label>
		<input name="tbConLoanSummary.productType" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:20" dictTypeId="product"/>

		<label>利率浮动比例：</label>
		<input name="tbConLoanSummary.rateFloatProportion" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>还款账号：</label>
		<input name="tbConLoanSummary.repaymentAccount" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>还款渠道：</label>
		<input name="tbConLoanSummary.repaymentChannels" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>还款方式：</label>
		<input name="tbConLoanSummary.repaymentType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1162"/>

		<label>还款金额：</label>
		<input name="tbConLoanSummary.repayAmt" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>还款日期：</label>
		<input name="tbConLoanSummary.repayDate" required="true" class="nui-datepicker nui-form-input" />

		<label>还款利息：</label>
		<input name="tbConLoanSummary.repayLnterest" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>还款机构号：</label>
		<input name="tbConLoanSummary.repayOrgNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:9" />

		<label>供应链放款申请编号：</label>
		<input name="tbConLoanSummary.scfApplyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>正常会计科目：</label>
		<input name="tbConLoanSummary.subjectno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>银团成员份额ID：</label>
		<input name="tbConLoanSummary.syndicatedShareId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>总期数：</label>
		<input name="tbConLoanSummary.totalPeriods" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>更新时间：</label>
		<input name="tbConLoanSummary.updateTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>年初分类结果：</label>
		<input name="tbConLoanSummary.yearClassificationResultCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

function initForm() {
	var json=nui.encode({"tbConLoanSummary":
		{"loanSummaryId":
		"<%=request.getParameter("loanSummaryId") %>"}});
	$.ajax({
        url: "com.bos.pub.ledgerMsg.getTbConLoanSummary.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.crud.updateTbConLoanSummary.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
	</script>
</body>
</html>
