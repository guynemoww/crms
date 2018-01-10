<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-08-25

  - Description:TB_BATCH_DAYEND_CRMS, TbBatchDayendCrms-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbBatchDayendCrms" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>渠道：</label>
		<input name="tbBatchDayendCrms.channels" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>渠道放款金额：</label>
		<input name="tbBatchDayendCrms.channelsBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>crms放款金额：</label>
		<input name="tbBatchDayendCrms.crmsBalance" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>当前记录状态：</label>
		<input name="tbBatchDayendCrms.currecordstate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>放款核准单号：</label>
		<input name="tbBatchDayendCrms.detailno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>数据日期：</label>
		<input name="tbBatchDayendCrms.inputdate" required="true" class="nui-datepicker nui-form-input" />

		<label>借据号：</label>
		<input name="tbBatchDayendCrms.loanNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />

		<label>放款支付类型：</label>
		<input name="tbBatchDayendCrms.payoutType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>主键ID：</label>
		<input name="tbBatchDayendCrms.serialno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />

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
	var json=nui.encode({"tbBatchDayendCrms":
		{"serialno":
		"<%=request.getParameter("serialno") %>"}});
	$.ajax({
        url: "com.bos.batch.queryCompare.getTbBatchDayendCrms.biz.ext",
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
        url: "com.bos.batch.queryCompare.updateTbBatchDayendCrms.biz.ext",
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
