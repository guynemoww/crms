<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-08-14
  - Description:TB_IRM_RATING_FIN_INDEX, com.bos.dataset.irm.TbIrmRatingFinIndex
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
	<%--	<label>客户类型代码：</label>
		<input name="tbIrmRatingFinIndex.customerTypeCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />--%>

		<label>指标代码：</label>
		<input name="tbIrmRatingFinIndex.indexCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>指标名称：</label>
		<input name="tbIrmRatingFinIndex.indexName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
		


		<label>属性类型代码：</label>
		<input name="tbIrmRatingFinIndex.propertyTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCCD0006" vtype="maxLength:20" />
		
		<label>附注：</label>
		<input name="tbIrmRatingFinIndex.remarks" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>指标公式：</label>
		<input colspan="3"  name="tbIrmRatingFinIndex.indexFormula" required="false" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
		

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
        url: "com.bos.irm.param.addTbIrmRatingFinIndex.biz.ext",
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
