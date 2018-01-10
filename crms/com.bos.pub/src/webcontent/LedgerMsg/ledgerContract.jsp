<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-28

  - Description:TB_CON_CONTRACT, com.bos.dataset.crt.TbConContract-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbConContract" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	
		
		<label>涉农贷款种类：</label>
		<input name="tbConContract.agroLoanType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10" dictTypeId="XD_SXCD1014"/>

		<label>批复ID：</label>
		<input name="tbConContract.approveId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>总行审批书编号：</label>
		<input name="tbConContract.apprNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>合同已用金额：</label>
		<input name="tbConContract.availableAmt" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>人行保障房分类方式：</label>
		<input name="tbConContract.bankHouseClassType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1102"/>

		<label>银监保障房分类方式：</label>
		<input name="tbConContract.bankSupervisorHouClassType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1102"/>

		<label>业务发生性质：</label>
		<input name="tbConContract.bizHappenType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1039"/>

		<label>资本金：</label>
		<input name="tbConContract.capital" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>主管户机构：</label>
		<input name="tbConContract.competentUserOrg" required="false" class="nui-buttonEdit" vtype="maxLength:200" dictTypeId="org"/>

		<label>城镇化建设贷款：</label>
		<input name="tbConContract.constructionLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>合同可用金额：</label>
		<input name="tbConContract.contractBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>撤销原因：</label>
		<input name="tbConContract.contractCallbackResult" required="false" class="nui-textarea" vtype="maxLength:1000" />

		<label>信贷合同唯一标识：</label>
		<input id="contractId" name="tbConContract.contractId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>信贷合同主合同手工编号：</label>
		<input name="tbConContract.contractManualNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>合同性质：</label>
		<input name="tbConContract.contractNatureCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTYpeId="XD_SXCD1008"/>

		<label>信贷合同主合同编号：</label>
		<input name="tbConContract.contractNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>合同终止备注：</label>
		<input name="tbConContract.contractOverContent" required="false" class="nui-textbox nui-form-input" vtype="maxLength:2000" />
		<label>补充协议生效起始日：</label>
		<input name="tbConContract.agreementEffectStartDate" required="true" class="nui-datepicker nui-form-input" />

		<label>签约日期：</label>
		<input name="tbConContract.contractSignDate" required="true" class="nui-datepicker nui-form-input" />

		<label>签约地点：</label>
		<input name="tbConContract.contractSignPlace" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>合同状态：</label>
		<input name="tbConContract.contractStatusCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1106"/>

		<label>合同期限：</label>
		<input name="tbConContract.contractTerm" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>合同终止日期：</label>
		<input name="tbConContract.contractTerminationDate" required="true" class="nui-datepicker nui-form-input" />

		<label>合同终止方式：</label>
		<input name="tbConContract.contractTerminationWayCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1214"/>

		<label>合同总金额：</label>
		<input name="tbConContract.contractTotalAmt" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>创建时间：</label>
		<input name="tbConContract.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>贷款类型：</label>
		<input name="tbConContract.creditType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="CDXY0185"/>

		<label>币种：</label>
		<input name="tbConContract.currencyCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001"/>

		<label>是否循环合同：</label>
		<input name="tbConContract.cycleFlag" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>期限单位：</label>
		<input name="tbConContract.cycleUnit" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>是否为直接替换房地产开发贷款：</label>
		<input name="tbConContract.directReplacementEstateLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>发放类型：</label>
		<input name="tbConContract.distributeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1202"/>

		<label>提款期限：</label>
		<input name="tbConContract.draftPeriod" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>汇率：</label>
		<input name="tbConContract.exchangeRate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:16" />

		<label>合同到期日期：</label>
		<input name="tbConContract.expirationDate" required="true" class="nui-datepicker nui-form-input" />

		<label>首次还款年份：</label>
		<input name="tbConContract.firstRepaymentYear" required="true" class="nui-datepicker nui-form-input" />

		<label>固定资产贷款类别：</label>
		<input name="tbConContract.fixedAssetsLoanType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6"  dictTypeId="XD_SXCD1059" />

		<label>政府授信类型：</label>
		<input name="tbConContract.govermentCreditType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1081" />

		<label>组合担保方式：</label>
		<input name="tbConContract.groupSuretyMode" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:32" dictTypeId="XD_SXCD1020"/>

		<label>是否调整担保：</label>
		<input name="tbConContract.ifAdjustmentGuarantee" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>协议标识：</label>
		<input name="tbConContract.ifAgreementFlag" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>是否中小户型普通商品住房开发：</label>
		<input name="tbConContract.ifCommodityHousing" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否符合分期还款要求：</label>
		<input name="tbConContract.ifComplianceRepaymentRepaym" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否便捷贷：</label>
		<input name="tbConContract.ifConvenientLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否国家限制行业：</label>
		<input name="tbConContract.ifCountryLimit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002" />

		<label>是否额度项下业务：</label>
		<input name="tbConContract.ifCreditDownBusiness" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否属于节能减排授信：</label>
		<input name="tbConContract.ifEnergySavingCredit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否首贷：</label>
		<input name="tbConContract.ifFirstLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否固定资产贷款：</label>
		<input name="tbConContract.ifFixedAssetsLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />

		<label>是否集资合作建房开发贷款：</label>
		<input name="tbConContract.ifFundRaisingLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6"  dictTypeId="XD_0002"/>

		<label>是否政府授信：</label>
		<input name="tbConContract.ifGovermentCredit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否政府土地储备机构贷款：</label>
		<input name="tbConContract.ifGovermentReserveLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否绿色能效贷：</label>
		<input name="tbConContract.ifGreenLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否重点行业淘汰落后产能贷款：</label>
		<input name="tbConContract.ifImpIndEliminatedLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否现代农业园区：</label>
		<input name="tbConContract.ifModernAgriculturalPark" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否安置房建设贷款：</label>
		<input name="tbConContract.ifPlaceLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002" />

		<label>是否保障性住房开发土地储备：</label>
		<input name="tbConContract.ifSecurityFacReserveFlag" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002" />

		<label>是否六大产能过剩行业贷款：</label>
		<input name="tbConContract.ifSixLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否钢贸贷款：</label>
		<input name="tbConContract.ifSteelFlag" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>工业转型升级标识：</label>
		<input name="tbConContract.industryUpdateFlag" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>还息方式：</label>
		<input name="tbConContract.interestRepaymentType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>是否涉农贷款：</label>
		<input name="tbConContract.isAgricultureLoans" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否银团贷款：</label>
		<input name="tbConContract.isBankTeamLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否普通商品住房开发贷：</label>
		<input name="tbConContract.isCommoditHouseLoans" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002" />

		<label>是否合同能源贷：</label>
		<input name="tbConContract.isContractEnergyLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否属于淘汰落后授信：</label>
		<input name="tbConContract.isEliminationBackwardCredit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否项目融资：</label>
		<input name="tbConContract.isProjectFinancing" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否异地业务：</label>
		<input name="tbConContract.isRemoteService" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否为供应链业务：</label>
		<input name="tbConContract.isSupplyChainBussiness" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>最后提款日期：</label>
		<input name="tbConContract.lastDraftDate" required="true" class="nui-datepicker nui-form-input" />

		<label>房地产贷款类型：</label>
		<input name="tbConContract.loanAtIndustryType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="CDXY0185"/>

		<label>贷款投向2011版：</label>
		<input name="tbConContract.loanDirection" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>贷款投向2002版：</label>
		<input name="tbConContract.loanDirectionTwo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>贷款用途：</label>
		<input name="tbConContract.loanUse" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="CDXY0032" />

		<label>21主要担保方式：</label>
		<input name="tbConContract.mainSuretyMode" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1020"/>

		<label>贷款新规种类：</label>
		<input name="tbConContract.newCreditType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1207"/>

		<label>战略新兴产业类型：</label>
		<input name="tbConContract.newIndustryType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1204"/>

		<label>已用金额：</label>
		<input name="tbConContract.occupiedAmt" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>经办机构：</label>
		<input name="tbConContract.orgNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:9" />

		<label>客户ID：</label>
		<input name="tbConContract.partyId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>用款方式：</label>
		<input name="tbConContract.payoutWayCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1068"/>

		<label>还本方式：</label>
		<input name="tbConContract.principalRepaymentType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6"  dictTypeId="XD_SXCD1018"/>

		<label>业务品种：</label>
		<input name="tbConContract.productType" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:20"  dictTypeId="product"/>

		<label>贷款用途描述：</label>
		<input name="tbConContract.purposeOfLoan" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>贷款用途类型：</label>
		<input name="tbConContract.purposeOfLoanType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="CDXY0032"/>

		<label>还款方式：</label>
		<input name="tbConContract.repaymentType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1162"/>

		<label>还款来源：</label>
		<input name="tbConContract.repaySource" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>限制性条款：</label>
		<input name="tbConContract.restrictiveClause" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>结息方式：</label>
		<input name="tbConContract.settlementWay" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1109"/>

		<label>小企业产品名称：</label>
		<input name="tbConContract.smallEnterprisesProductName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>合同起始日期/生效日期：</label>
		<input name="tbConContract.startDate" required="true" class="nui-datepicker nui-form-input" />

		<label>补充条款：</label>
		<input name="tbConContract.supplementaryProvisions" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>更新时间：</label>
		<input name="tbConContract.updateTime" required="false" class="nui-datepicker nui-form-input"  />

		<label>经办用户：</label>
		<input name="tbConContract.userNum" required="false" class="nui-buttonEdit nui-form-input"  dictTypeId="user"/>

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
	}

function initForm() {
	var json=nui.encode({"tbConContract":
		{"contractId":
		"<%=request.getParameter("contractId") %>"}});
	$.ajax({
        url: "com.bos.pub.ledgerMsg.getTbConContract.biz.ext",
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
function editLoan() {
var rcontent,contractId;
 contractId=nui.get("contractId").getValue();
 
	nui.open({
            url: nui.context+"/pub/LedgerMsg/loan_list.jsp?contractId="+contractId,
            title:  "查看" , 
           width: 1300, 
        	height: 500,
        	allowResize: false,
        	showMaxButton: false,
        	onload: function () {
	            //var iframe = this.getIFrameEl();
	            //this.max();
	        },
            ondestroy: function (action) {
                if(action=="ok"){
                    //initForm();
                }
            }
    });
}
function editSub() {
var subId,contractId;
 contractId=nui.get("contractId").getValue();
	nui.open({
            url: nui.context+"/pub/LedgerMsg/sub_list.jsp?contractId="+contractId,
            title:  "查看" , 
            width: 1300, 
        	height: 500,
        	allowResize: false,
        	showMaxButton: false,
        	onload: function () {
	            //var iframe = this.getIFrameEl();
	            //this.max();
	        },
            ondestroy: function (action) {
                if(action=="ok"){
                    //initForm();
                }
            }
    });
}

	</script>
</body>
</html>
