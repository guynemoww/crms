<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-06
  - Description:TB_BATCH_SP_LOG, com.bos.dataset.batch.TbBatchSpLog
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>过程开始时间：</label>
		<input name="tbBatchSpLog.begTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>过程结束时间：</label>
		<input name="tbBatchSpLog.endTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>执行标识：</label>
		<input name="tbBatchSpLog.execResult" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1" />

		<label>入参1：</label>
		<input name="tbBatchSpLog.inPara1" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>入参2：</label>
		<input name="tbBatchSpLog.inPara2" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>入参3：</label>
		<input name="tbBatchSpLog.inPara3" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>入参4：</label>
		<input name="tbBatchSpLog.inPara4" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>入参5：</label>
		<input name="tbBatchSpLog.inPara5" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>日志ID：</label>
		<input name="tbBatchSpLog.logId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>出参1：</label>
		<input name="tbBatchSpLog.outPara1" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>出参2：</label>
		<input name="tbBatchSpLog.outPara2" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>出参3：</label>
		<input name="tbBatchSpLog.outPara3" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>出参4：</label>
		<input name="tbBatchSpLog.outPara4" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>出参5：</label>
		<input name="tbBatchSpLog.outPara5" required="false" class="nui-textbox nui-form-input" vtype="maxLength:500" />

		<label>过程或函数名：</label>
		<input name="tbBatchSpLog.spName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
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
        url: "com.bos.pub.crud.addTbBatchSpLog.biz.ext",
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
