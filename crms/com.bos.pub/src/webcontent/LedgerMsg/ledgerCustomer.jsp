<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-28

  - Description:TB_CSM_PARTY, com.bos.dataset.csm.TbCsmParty-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbCsmParty" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
	
		<label>行政区划：</label>
		<input name="tbCsmParty.administrativeDivisionsCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>国家和地区：</label>
		<input name="tbCsmParty.contryRegionCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="XD_KHCD0087"/>

		<label>ecf编号：</label>
		<input name="tbCsmParty.ecifPartyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>金交所客户：</label>
		<input name="tbCsmParty.goldExchangeCustCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />
<!--  
		<label>客户ID：</label>
		<input name="tbCsmParty.partyId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
-->
		<label>客户名称：</label>
		<input name="tbCsmParty.partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:300" />

		<label>客户编号：</label>
		<input name="tbCsmParty.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>客户类型：</label>
		<input name="tbCsmParty.partyTypeCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_KHCD0001"/>

		<label>代理商名称：</label>
		<input name="tbCsmParty.agentName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>企业年检结果：</label>
		<input name="tbCsmParty.annualInspectionCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>贷款卡年检时间：</label>
		<input name="tbCsmParty.annualInspectionDate" required="true" class="nui-datepicker nui-form-input" />

		<label>贷款卡年检标识：</label>
		<input name="tbCsmParty.annualInspectionIndexCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>黑名单进入原因：</label>
		<input name="tbCsmParty.blackListReasonCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_KHCD0010"/>

		<label>黑名单原因说明：</label>
		<input name="tbCsmParty.blackReasonExplain" required="false" class="nui-textarea nui-form-input" vtype="maxLength:200" />

		<label>营业收入：</label>
		<input name="tbCsmParty.businessIncome" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>主管部门：</label>
		<input name="tbCsmParty.competentDepartment" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>联系电话：</label>
		<input name="tbCsmParty.contactTelNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>公司E-Mail：</label>
		<input name="tbCsmParty.corporationEMail" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>公司网址：</label>
		<input name="tbCsmParty.corporationUrl" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
<!-- 
		<label>企业控股类型：</label>
		<input name="tbCsmParty.corpHoldingTypeCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:5" dictTypeId="XD_KHCD0160"/>
 -->
		<label>客户方联系人：</label>
		<input name="tbCsmParty.corpLinkman" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>创建时间：</label>
		<input name="tbCsmParty.createTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>是否信贷客户：</label>
		<input name="tbCsmParty.creditCustomers" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>与我行建立信贷关系时间：</label>
		<input name="tbCsmParty.creditRelationshipTime" required="true" class="nui-datepicker nui-form-input" />

		<label>企业成立日期：</label>
		<input name="tbCsmParty.dateOfEstablishment" required="true" class="nui-datepicker nui-form-input" />
<!-- 
		<label>企业经济类型：</label>
		<input name="tbCsmParty.economicCategoriesCode" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4" dictTypeId="XD_KHCD0029"/>
 -->

		<label>从业人数：</label>
		<input name="tbCsmParty.employeesNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>英文名：</label>
		<input name="tbCsmParty.englishName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:300" />

		<label>传真电话：</label>
		<input name="tbCsmParty.faxPhone" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>财务部联系电话：</label>
		<input name="tbCsmParty.financeContactPhone" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>金融机构类型：</label>
		<input name="tbCsmParty.financeEnterpriseType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>财务部联系人：</label>
		<input name="tbCsmParty.financeLinkman" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>经费来源：</label>
		<input name="tbCsmParty.financialResourcesCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_KHCD0184"/>

		<label>是否重点客户：</label>
		<input name="tbCsmParty.focusCustomer" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>四部委企业规模-人工输入：</label>
		<input name="tbCsmParty.fourrEnterpriseSizeCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:5" />

		<label>四部委企业规模-自动计算：</label>
		<input name="tbCsmParty.fourzEnterpriseSizeCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:5" />

		<label>税务登记证号（地税）：</label>
		<input name="tbCsmParty.governmentTentNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>是否集团客户：</label>
		<input name="tbCsmParty.groupCustomer" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>集团成员关系种类：</label>
		<input name="tbCsmParty.groupRelTypeCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>有无优惠政策：</label>
		<input name="tbCsmParty.haveNotPreferentialPolicy" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>举办单位：</label>
		<input name="tbCsmParty.hostUnit" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>是否自贸区客户：</label>
		<input name="tbCsmParty.ifFtaCsm" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>行业类型：</label>
		<input name="tbCsmParty.industrialTypeCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:5" dictTypeId="XD_KHCD0092"/>

		<label>开办资金：</label>
		<input name="tbCsmParty.initialFund" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>开办资金币种：</label>
		<input name="tbCsmParty.initialFundCurrecyCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001"/>

		<label>法人证书有效期：</label>
		<input name="tbCsmParty.legalCertificateEndDate" required="true" class="nui-datepicker nui-form-input" />

		<label>法人证书号码：</label>
		<input name="tbCsmParty.legalCertificateNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>是否上市公司：</label>
		<input name="tbCsmParty.listingCorporation" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>


		<label>贷款卡首次申领日期：</label>
		<input name="tbCsmParty.loanCardOpenDate" required="true" class="nui-datepicker nui-form-input" />

		<label>主要产品情况：</label>
		<input name="tbCsmParty.majorProductState" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>客户历史沿革管理水平简介：</label>
		<input name="tbCsmParty.manageLevelState" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>反洗钱评级结果：</label>
		<input name="tbCsmParty.moneyLaunderingResultsCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_KHCD0005" />

		<label>税务登记号（国税）：</label>
		<input name="tbCsmParty.nationalTaxNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>开户核准号：</label>
		<input name="tbCsmParty.openAcctApprovalNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>经营状况说明：</label>
		<input name="tbCsmParty.operateState" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>经营场地面积（平方米）：</label>
		<input name="tbCsmParty.operatingArea" required="false" class="nui-textbox nui-form-input" vtype="maxLength:22" />

		<label>经营场地所有权：</label>
		<input name="tbCsmParty.operatingAreaOwnershipCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>主营业务：</label>
		<input name="tbCsmParty.operatingBusiness" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>组织机构登记日期：</label>
		<input name="tbCsmParty.orgRegisterDate" required="true" class="nui-datepicker nui-form-input" />

		<label>宗旨和业务范围：</label>
		<input name="tbCsmParty.purposeBizScope" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>注册资本：</label>
		<input name="tbCsmParty.registerAssets" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>注册资本币种：</label>
		<input name="tbCsmParty.registerAssetsCurrencyCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001"/>

		<label>注册资本到位率：</label>
		<input name="tbCsmParty.registerAssetsRate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:13" />

		<label>注册日期：</label>
		<input name="tbCsmParty.registerDate" required="true" class="nui-datepicker nui-form-input" />

		<label>登记机关：</label>
		<input name="tbCsmParty.registerOrg" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>登记注册类型：</label>
		<input name="tbCsmParty.registrationType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_KHCD0024" />

		<label>小企业认定原因：</label>
		<input name="tbCsmParty.smallCorpReason" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>备注：</label>
		<input name="tbCsmParty.state" required="false" class="nui-textarea nui-form-input" vtype="maxLength:1000" />

		<label>资产总额：</label>
		<input name="tbCsmParty.totalAssets" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>单位规模：</label>
		<input name="tbCsmParty.unitScaleCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:5" />

		<label>更新机构：</label>
		<input name="tbCsmParty.updateOrgNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:9" dictTypeId="org"/>

		<label>更新时间：</label>
		<input name="tbCsmParty.updateTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>更新人：</label>
		<input name="tbCsmParty.updateUserNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:10" dictTypeId="user"/>

		<label>是否规模以上企业：</label>
		<input name="tbCsmParty.whetherAboveDesignatedSize" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_0002"/>

		<label>是否限额以上企业：</label>
		<input name="tbCsmParty.whetherAboveLimitSize" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_0002"/>

		<label>是否出口收汇关注企业：</label>
		<input name="tbCsmParty.whetherAttentionEnterprise" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否全行及重点目标客户：</label>
		<input name="tbCsmParty.whetherBankImportantCorp" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否黑名单客户：</label>
		<input name="tbCsmParty.whetherBlackList" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否关停企业：</label>
		<input name="tbCsmParty.whetherCloseCorp" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否从事房地产开发：</label>
		<input name="tbCsmParty.whetherEstateDev" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:255" dictTypeId="XD_0002"/>

		<label>是否有出口退税帐户：</label>
		<input name="tbCsmParty.whetherExpDrawbackAccount" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否高新技术企业：</label>
		<input name="tbCsmParty.whetherHightechCorp" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:255" dictTypeId="XD_0002"/>

		<label>有无进出口经营权：</label>
		<input name="tbCsmParty.whetherImpExp" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>贷款卡是否有效：</label>
		<input name="tbCsmParty.whetherLoanValidate" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:255" dictTypeId="XD_0002"/>

		<label>是否小企业认定通过：</label>
		<input name="tbCsmParty.whetherPassPeanuts" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否无需提供财务报表：</label>
		<input name="tbCsmParty.whetherRequiredFinanceOrnot" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:255" dictTypeId="XD_0002"/>

		<label>是否科技型企业：</label>
		<input name="tbCsmParty.whetherScienceCorp" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" />

		<label>是否在我行有结算帐户：</label>
		<input name="tbCsmParty.whetherSettlementAccount" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否专项客户：</label>
		<input name="tbCsmParty.whetherSpecialCorp" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>是否Vip客户：</label>
		<input name="tbCsmParty.whetherVipCorp" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>
		
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
	var json=nui.encode({"tbCsmParty":{"partyId":"<%=request.getParameter("partyId") %>"}});
	$.ajax({
        url: "com.bos.pub.ledgerMsg.getCustomer.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        alert(nui.encode(text));
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


	</script>
</body>
</html>
