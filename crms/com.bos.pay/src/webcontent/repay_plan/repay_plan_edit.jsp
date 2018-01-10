<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lpc
  - Date: 2014-03-31

  - Description:TB_CON_LOAN_ACCOUNT_INFO, com.bos.dataset.pay.TbConLoanAccountInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:96%;height:90%;overflow:hidden; text-align:center;margin: 10px;" >
	<input id="tbLoanRepayPlan.repayPlanId" name="tbLoanRepayPlan.repayPlanId"  class="nui-hidden nui-form-input" value=""/>
	<input id="tbLoanRepayPlan.loanId" name="tbLoanRepayPlan.loanId"  class="nui-hidden nui-form-input" value=""/>
	<input id="tbLoanRepayPlan.contractId" name="tbLoanRepayPlan.contractId"  class="nui-hidden nui-form-input" value=""/>
	<div class="nui-dynpanel" columns="4">
		<label>期数：</label>
		<input id="tbLoanRepayPlan.periodsNumber" name="tbLoanRepayPlan.periodsNumber" vtype="int"   required="true" class="nui-text nui-form-input"/>
		
		<label>日期：</label>
		<input id="tbLoanRepayPlan.repayDate" name="tbLoanRepayPlan.repayDate" required="true"  allowInput="false" class="nui-datepicker nui-form-input"/>
		
		<label>金额：</label>
		<input id="tbLoanRepayPlan.repayAmt" name="tbLoanRepayPlan.repayAmt" required="true" vtype="float;maxLength:20" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>

<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
</div>
</div>
<script type="text/javascript">
nui.parse();
var form = new nui.Form("#form1");

//初始化页面
function initForm() {
	var json=nui.encode({"repayPlanId":"<%=request.getParameter("repayPlanId") %>"});
	$.ajax({
        url: "com.bos.payInfo.repayPlan.getRepayPlanById.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	form.setData(text);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

//保存页面信息
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	$.ajax({
        url: "com.bos.payInfo.repayPlan.updateRepayPlan.biz.ext",
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
