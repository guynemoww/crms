<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-07-08
  - Description:TB_PUB_FIELD_RECORD, com.bos.pub.sys.TbPubFieldRecord
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>客户id等：</label>
		<input name="tbPubFieldRecord.businessId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>主键：</label>
		<input name="tbPubFieldRecord.dataRecordId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>英文字段名称：</label>
		<input name="tbPubFieldRecord.recordeField" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>页面显示补录名称：</label>
		<input name="tbPubFieldRecord.recordeName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>补入类型：客户信息补录，合同信息补录，借据信息补录：</label>
		<input name="tbPubFieldRecord.recordeType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:2" />

		<label>补录结果值：</label>
		<input name="tbPubFieldRecord.recordeValue" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

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
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.fieldflefeed.addTbPubFieldRecord.biz.ext",
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
