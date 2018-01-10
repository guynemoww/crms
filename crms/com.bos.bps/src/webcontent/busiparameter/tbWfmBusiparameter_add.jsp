<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-23
  - Description:TB_WFM_BUSIPARAMETER, com.bos.bps.dataset.bps.TbWfmBusiparameter
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>字段名称：</label>
		<input name="tbWfmBusiparameter.porpertyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>字段名：</label>
		<input name="tbWfmBusiparameter.porpertyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		

		<label>字典项名称：</label>
		<input name="tbWfmBusiparameter.porpertyDictName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />


		<label>显示类型：</label>
		<input name="tbWfmBusiparameter.showType" required="false" class="nui-textbox nui-form-input" value="nui-dictcombobox" vtype="maxLength:200" />
		
	
		<label>是否必选：</label>
		<input name="tbWfmBusiparameter.isMust" required="false" class="nui-dictcombobox nui-form-input" data="[{'id':'1','text':'是'},{'id':'2','text':'否'}]" vtype="maxLength:6" />

		<label>是否显示：</label>
		<input name="tbWfmBusiparameter.isShow" required="false" class="nui-dictcombobox nui-form-input" data="[{'id':'true','text':'true'},{'id':'false','text':'false'}]" vtype="maxLength:6" />

		<label>外键id：</label>
		<input name="tbWfmBusiparameter.workitemMappingId" enabled="false" value="<%=request.getParameter("workitemMappingId") %>" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

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
        url: "com.bos.bps.util.TbWfmBusiparameters.insertTbWfmBusiparameters.biz.ext",
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
