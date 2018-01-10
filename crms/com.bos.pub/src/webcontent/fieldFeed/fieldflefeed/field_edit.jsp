<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-07-04

  - Description:TB_PUB_FIELD_FLE_FEED, com.bos.pub.sys.TbPubFieldFleFeed-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbPubFieldFleFeed" class="nui-hidden" />
			<input name="tbPubFieldFleFeed.recordeId" required="false" class="nui-hidden"  />
	<div class="nui-dynpanel" columns="4">
		<label>中文字段名称：</label>
		<input name="tbPubFieldFleFeed.recordeName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>英文字段名称：</label>
		<input name="tbPubFieldFleFeed.recordeField" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>输入域类型：</label>
		<input name="tbPubFieldFleFeed.fildType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="pub_grant_param"/>
		<label>补入类型：</label>
		<input name="tbPubFieldFleFeed.recordeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD2901"/>
		<label>字典项：</label>
		<input name="tbPubFieldFleFeed.fildProperty" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
		<label>是否显示：</label>
		<input name="tbPubFieldFleFeed.isShow" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_0002"/>

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
	var json=nui.encode({"tbPubFieldFleFeed":
		{"recordeId":
		"<%=request.getParameter("recordeId") %>"}});
	$.ajax({
        url: "com.bos.pub.fieldflefeed.getTbPubFieldFleFeed.biz.ext",
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
        url: "com.bos.pub.fieldflefeed.updateTbPubFieldFleFeed.biz.ext",
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
