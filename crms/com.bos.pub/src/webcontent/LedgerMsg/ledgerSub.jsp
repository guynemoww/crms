<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-28

  - Description:TB_CON_SUBCONTRACT, com.bos.dataset.crt.TbConSubcontract-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbConSubcontract" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
		<label>担保额度模式编号：</label>
		<input name="tbConSubcontract.guaranteeAmountModelNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>保证形式：</label>
		<input name="tbConSubcontract.guaranteeKindCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>本次抵押品占用价值合计：</label>
		<input name="tbConSubcontract.guaranteeLimitSumMoney" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>担保债权金额：</label>
		<input name="tbConSubcontract.guaranteeRightMoney" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>保证方式：</label>
		<input name="tbConSubcontract.guatorTypeCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="YP_GLCD0152"/>

		<label>是否异地担保：</label>
		<input name="tbConSubcontract.ifDifferentPlace" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002"/>

		<label>是否占用担保额度：</label>
		<input name="tbConSubcontract.ifOccupyGuaranteeAmount" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002"/>
		<label>创建时间：</label>
		<input name="tbConSubcontract.createTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>币种：</label>
		<input name="tbConSubcontract.currencyCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001"/>

		<label>到期日期：</label>
		<input name="tbConSubcontract.expirationDate" required="true" class="nui-datepicker nui-form-input" />

		<label>是否有其他签约人：</label>
		<input name="tbConSubcontract.ifOtherPeople" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_0002" />


		<label>担保方式：</label>
		<input name="tbConSubcontract.loanType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>经办机构：</label>
		<input name="tbConSubcontract.orgNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:9" dictTypeId="org"/>

		<label>另行约定事项：</label>
		<input name="tbConSubcontract.otherThing" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>抵质押/保证人ID：</label>
		<input name="tbConSubcontract.partyId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>起始日期：</label>
		<input name="tbConSubcontract.startDate" required="true" class="nui-datepicker nui-form-input" />

		<label>从合同唯一标识：</label>
		<input name="tbConSubcontract.subcontractId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>纸质担保合同编号：</label>
		<input name="tbConSubcontract.subcontractManualNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>从合同唯一标识：</label>
		<input name="tbConSubcontract.subcontractNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>从合同状态：</label>
		<input name="tbConSubcontract.subcontractStatusCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1195"/>

		<label>从合同类型：</label>
		<input name="tbConSubcontract.subcontractTypeCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId=""/>

		<label>签约日期：</label>
		<input name="tbConSubcontract.subContractSignDate" required="true" class="nui-datepicker nui-form-input" />

		<label>签约地点：</label>
		<input name="tbConSubcontract.subContractSignPlace" required="false" class="nui-textarea nui-form-input" vtype="maxLength:200" />

		<label>更新时间：</label>
		<input name="tbConSubcontract.updateTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>经办用户：</label>
		<input name="tbConSubcontract.userNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:10" dictTypeId="user" />

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
	var json=nui.encode({"tbConSubcontract":
		{"subcontractId":
		"<%=request.getParameter("subcontractId") %>"}});
	$.ajax({
        url: "com.bos.pub.ledgerMsg.getTbConSubcontract.biz.ext",
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


	</script>
</body>
</html>
