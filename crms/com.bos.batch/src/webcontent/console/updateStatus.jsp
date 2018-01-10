<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-25

  - Description:AC_BATCH, com.bos.batch.monitor.AcBatch-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="form1" style="width:94%;height:auto;overflow:hidden;text-align:left">
	<fieldset>
  	<legend>
   	 <span>修改批量任务状态</span>
    </legend>
	<div class="nui-dynpanel" columns="4">
		<label>批量代码：</label>
		<input name="tbBatchTaskConsole.batchCode" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:80" />
		<label>批量名称：</label>
		<input name="tbBatchTaskConsole.batchName" required="false" enabled="false" class="nui-textbox nui-form-input"/>
		<label>批量组：</label>
		<input name="tbBatchTaskConsole.batchGroup" required="false" enabled="false" class="nui-dictcombobox nui-form-input"
		data="data" valueField="dictID" textField="dictName" enabled="false" dictTypeId="nightGroup"/>
		<label>批量组内排序：</label>
		<input name="tbBatchTaskConsole.batchOrder" required="false" enabled="false" class="nui-textbox nui-form-input"/>
		<label>任务状态：</label>
		<input name="tbBatchTaskConsole.status" required="false" enabled="true" class="nui-dictcombobox nui-form-input"
		data="data" valueField="dictID" textField="dictName" enabled="false" dictTypeId="XD_RZCD0006"/>
		<label>批量日期：</label>
		<input name="tbBatchTaskConsole.batchDate" required="false" enabled="false" class="nui-textbox nui-form-input"/>
		<label>开始时间：</label>
		<input name="tbBatchTaskConsole.starttime" required="false" enabled="false" class="nui-textbox nui-form-input"/>
		<label>结束时间：</label>
		<input name="tbBatchTaskConsole.endtime" required="false" enabled="false" class="nui-textbox nui-form-input"/>
		
		<input name="tbBatchTaskConsole.consoleId" required="false" enabled="false" class="nui-hidden"/>
	</div>
</fieldset>				
<div class="nui-toolbar" style="width:100%;text-align:right;padding-top:10px;padding-bottom:5px;" borderStyle="border:0;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	<span style="display:inline-block;width:25px;"></span>
</div>
</div>
</center>	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
function initForm() {
	var json=nui.encode({"tbBatchTaskConsole":
		{"consoleId":
		"<%=request.getParameter("consoleId") %>"}});
	$.ajax({
        url: "com.bos.batch.updateBatchTaskStatus.getOneTask.biz.ext",
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


function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	$.ajax({
        url: "com.bos.batch.updateBatchTaskStatus.updateStatus.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.meg=='0'){
        		nui.alert('修改失败');
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
