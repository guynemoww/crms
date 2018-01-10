<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-20

  - Description:TB_BIZ_APPROVE, com.bos.dataset.biz.TbBizApprove-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbBizApprove" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>额度分类：</label>
		<input name="tbBizApprove.amountType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_EDCD2005"/>

		<label>申请日期：</label>
		<input name="tbBizApprove.applyDate" required="true" class="nui-datepicker nui-form-input" />

		<label>申报模式：</label>
		<input name="tbBizApprove.applyModeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1040"/>

		<label>申请类型标识：</label>
		<input name="tbBizApprove.applyTypeMark" required="false" class="nui-textbox nui-form-input" vtype="maxLength:80" />

		<label>批复编号：</label>
		<input name="tbBizApprove.approvalNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>批复结论：</label>
		<input name="tbBizApprove.approveConclusion" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="ApproveConclusion"/>

		<label>生效标识：</label>
		<input name="tbBizApprove.becomeEffectiveMark" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:80" dictTypeId="EffectiveMark"/>

		<label>业务发生性质：</label>
		<input name="tbBizApprove.bizHappenType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1039"/>

		<label>业务编号：</label>
		<input name="tbBizApprove.bizNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>业务性质：</label>
		<input name="tbBizApprove.bizType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1038"/>

		<label>业务产品：</label>
		<input name="tbBizApprove.businessProduct" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:6" dictTypeId="product"/>

		<label>创建时间：</label>
		<input name="tbBizApprove.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>客户信用登记：</label>
		<input name="tbBizApprove.customerCreditLevel" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>到期日期：</label>
		<input name="tbBizApprove.endDate" required="true" class="nui-datepicker nui-form-input" />

		<label>终批机构编号：</label>
		<input name="tbBizApprove.endOrgNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>经办时间：</label>
		<input name="tbBizApprove.handingDate" required="true" class="nui-datepicker nui-form-input" />

		<label>是否银团贷款：</label>
		<input name="tbBizApprove.isBankTeamLoan" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>

		<label>贷后检查频率：</label>
		<input name="tbBizApprove.loanInspectionFre" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>低业务类别：</label>
		<input name="tbBizApprove.lowRiskBizType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1194"/>

		<label>经办机构编号：</label>
		<input name="tbBizApprove.orgNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:9" />

		<label>原批复ID：</label>
		<input name="tbBizApprove.originalApproveId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>客户ID：</label>
		<input name="tbBizApprove.partyId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>决策单意见：</label>
		<input name="tbBizApprove.policyDecisionIdea" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>终审人编号：</label>
		<input name="tbBizApprove.policyDecisionNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>业务品种：</label>
		<input name="tbBizApprove.productType" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:10" dictTypeId="product"/>

		<label>更新时间：</label>
		<input name="tbBizApprove.updateTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>经办人编号：</label>
		<input name="tbBizApprove.userNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:10" dictTypeId="user"/>

		<label>生效日期：</label>
		<input name="tbBizApprove.validDate" required="true" class="nui-datepicker nui-form-input" />

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
	var json=nui.encode({"tbBizApprove":
		{"approveId":
		"<%=request.getParameter("approveId") %>"}});
	$.ajax({
        url: "com.bos.pub.ledgerMsg.getTbBizApprove.biz.ext",
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
        url: "com.bos.pub.ledgerMsg.updateTbBizApprove.biz.ext",
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
