<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-09

  - Description:TB_BATCH_SUBJECT_INFO, com.bos.pub.sys.TbBatchSubjectInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbBatchSubjectInfo" class="nui-hidden" />

	<div class="nui-dynpanel" columns="4">
		<label>科目名称：</label>
		<input name="tbBatchSubjectInfo.subjectname" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>科目号：</label>
		<input name="tbBatchSubjectInfo.subjectno" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>
		<label>参数1：</label>
		<input name="tbBatchSubjectInfo.attribute1" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:80" dictTypeId="XD_GGCD22011"/>
		<label>参数2：</label>
		<input name="tbBatchSubjectInfo.attribute2" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:80" dictTypeId="XD_GGCD220112"/>
		<label>门类代码：</label>
		<input name="tbBatchSubjectInfo.attribute5" required="false" class="nui-textbox nui-form-input" vtype="maxLength:80"/>
		
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
	var json=nui.encode({"tbBatchSubjectInfo":
		{"subjectno":
		"<%=request.getParameter("subjectno") %>"}});
	$.ajax({
        url: "com.bos.pub.batchSbujectInfo.getTbBatchSubjectInfo.biz.ext",
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
        url: "com.bos.pub.batchSbujectInfo.updateTbBatchSubjectInfo.biz.ext",
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