<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-22
  - Description:TB_IRM_RATING_MAPPING, com.bos.dataset.irm.TbIrmRatingMapping
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>内部信用评级：</label>
		<input name="tbIrmRatingMapping.internalCreditRating" required="true" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>内部平均PD：</label>
		<input name="tbIrmRatingMapping.internalAvgPd" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>外部信用等级：</label>
		<input name="tbIrmRatingMapping.externalCreditRating" required="true" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>外部平均PD：</label>
		<input name="tbIrmRatingMapping.externalAvgPd" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>外部评级机构：</label>
		<input name="tbIrmRatingMapping.externalEvalOrg" required="true" class="nui-textbox nui-form-input" vtype="maxLength:6" />

	</div>
</div>
				
<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
function save() {
	git.mask();
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		git.unmask();
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.irm.param.addTbIrmRatingMapping.biz.ext",
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
        	git.unmask();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
             git.unmask();
        }
	});
}
	</script>
</body>
</html>
