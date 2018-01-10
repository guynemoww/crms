<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-14
  - Description:TB_IRM_MODEL_SCALE, com.bos.dataset.irm.TbIrmModelScale
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<input type="hidden" name="tbIrmModelScale.modelId"  value="<%=request.getParameter("modelId") %>"   class="nui-hidden"  />
	<div class="nui-dynpanel" columns="4">
		

		<label>等级名称：</label>
		<input name="tbIrmModelScale.creditRatingDisplay" required="true" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>最小校值：</label>
		<input name="tbIrmModelScale.minValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" />
		
		<label>最大分值：</label>
		<input name="tbIrmModelScale.maxValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" />


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
        url: "com.bos.irm.model.addTbIrmModelScale.biz.ext",
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
