<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-09
  - Description:TB_BATCH_SUBJECT_INFO, com.bos.pub.sys.TbBatchSubjectInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>科目名称：</label>
		<input name="tbBatchSubjectInfo.subjectname" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>科目号：</label>
		<input id="subjectno" name="tbBatchSubjectInfo.subjectno" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		<label>参数1：</label>
		<input name="tbBatchSubjectInfo.attribute1" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:80" dictTypeId="XD_GGCD22011"/>
		
		<label>参数2：</label>
		<input name="tbBatchSubjectInfo.attribute2" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:80" dictTypeId="XD_GGCD220112"/>

		<label>门类代码：</label>
		<input name="tbBatchSubjectInfo.attribute5" required="false" class="nui-textbox nui-form-input" vtype="maxLength:80"/>

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
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
	var jsons=nui.encode({"subject":{"subjectno":nui.get("subjectno").getValue()}});
	$.ajax({
        url: "com.bos.pub.batchSbujectInfo.checkSubjectOnlyOne.biz.ext",
        type: 'POST',
        data: jsons,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
	        		var o=form.getData();
					var json=nui.encode(o);
					//nui.alert(json);return;
					$.ajax({
				        url: "com.bos.pub.batchSbujectInfo.addTbBatchSubjectInfo.biz.ext",
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
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
	
}
	</script>
</body>
</html>
