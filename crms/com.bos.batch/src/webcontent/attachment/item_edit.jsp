<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-05

  - Description:TB_BATCH_DATALIST_ATTACHMENT, com.bos.batch.DB.TbBatchDatalistAttachment-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbBatchDatalistAttachment" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>机构号：</label>
		<input name="tbBatchDatalistAttachment.belongorg" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>文件长度：</label>
		<input name="tbBatchDatalistAttachment.contentlength" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>状态：</label>
		<input name="tbBatchDatalistAttachment.contentstatus" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>文件类型（默认：application/x-gzi）：</label>
		<input name="tbBatchDatalistAttachment.contenttype" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>文件所在路径：</label>
		<input name="tbBatchDatalistAttachment.datalistpath" required="false" class="nui-textbox nui-form-input" vtype="maxLength:250" />

		<label>数据清单类型：</label>
		<input name="tbBatchDatalistAttachment.datalisttype" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>文件名：</label>
		<input name="tbBatchDatalistAttachment.filename" required="false" class="nui-textbox nui-form-input" vtype="maxLength:250" />

		<label>数据日期：</label>
		<input name="tbBatchDatalistAttachment.inputdate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>录入时间：</label>
		<input name="tbBatchDatalistAttachment.inputtime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
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
	var json=nui.encode({"tbBatchDatalistAttachment":
		{"belongorg":
		"<%=request.getParameter("belongorg") %>"}});
	$.ajax({
        url: "com.bos.pub.crud.getTbBatchDatalistAttachment.biz.ext",
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
        url: "com.bos.pub.crud.updateTbBatchDatalistAttachment.biz.ext",
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
