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
	<input type="hidden" name="acBatch" class="nui-hidden" />
	<fieldset>
  	<legend>
   	 <span>批量配置信息</span>
    </legend>
	<div class="nui-dynpanel" columns="4">
		
		<label>批量代码：</label>
		<input name="acBatch.batchCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:80" />
		<label>执行时是否判断：</label>
		<input name="acBatch.batchTemp2" required="true" class="nui-dictradiogroup nui-form-input" vtype="maxLength:4"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0005"/>
		<label>批量名称：</label>
		<input name="acBatch.batchName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:80" />
		<label>批量错误是否后续影响：</label>
		<input name="acBatch.batchErrkeep" required="false" class="nui-dictradiogroup nui-form-input" vtype="maxLength:4"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0005"/>
		<label>批量启用状态：</label>
		<input name="acBatch.batchStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0004"/>
		<label>批量是否监控：</label>
		<input name="acBatch.batchTemp1" required="true" class="nui-dictradiogroup nui-form-input" vtype="maxLength:4"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0005"/>
		<label>批量组：</label>
		<input name="acBatch.batchGroup" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
		data="data" valueField="dictID" textField="dictName" dictTypeId="nightGroup"/>
		<label>批量组内排序：</label>
		<input name="acBatch.batchOrder" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />
		（输入整数）
		<label>批量类型：</label>
		<input name="acBatch.batchType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0007" value="1" disabled="true" enabled="false"/>
		<label>批量执行逻辑流：</label>
		<input name="acBatch.batchBizname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>批量描述：</label>
		<input name="acBatch.batchDes" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>批量日志目录：</label>
		<input name="acBatch.batchLogfiledir" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>批量日志文件：</label>
		<input name="acBatch.batchLogfilename" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>执行脚本：</label>
		<input name="acBatch.batchTemp3" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
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
	var json=nui.encode({"acBatch":
		{"batchId":
		"<%=request.getParameter("batchId") %>"}});
	$.ajax({
        url: "com.bos.batch.batchquery.getAcBatch.biz.ext",
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
        url: "com.bos.batch.batchquery.updateAcBatch.biz.ext",
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
