<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-25

  - Description:TB_WFM_PROCESSMAPPING, com.bos.bps.database.TbWfmProcessmapping-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.bps.dataset.bps.TbWfmProcessmapping" class="nui-hidden" />
	<input type="hidden" name="item" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>主键：</label>
		<input name="item.processMappingId" enabled="false" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		<label></label>
		<label></label>
		
		<label> 流程定义名称：</label>
		<input name="item.productType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>流程定义ID：</label>
		<input name="item.templateName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />
		
		<label>业务类型：</label>
		<input name="item.bizType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>业务信息显示路径：</label>
		<input name="item.bizViewUrl" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>模板版本号：</label>
		<input name="item.templageVersion" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />
		<label>版本状态：</label>
		<input name="item.versionStatus" class="nui-dictcombobox nui-form-input" data="[{'id':'1','text':'默认'},{'id':'2','text':'停止'}]" required="false"   vtype="maxLength:32" />

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
	var json=nui.encode({"item":
		{"processMappingId":
		"<%=request.getParameter("processMappingId") %>","_entity":"com.bos.bps.dataset.bps.TbWfmProcessmapping"}});
	$.ajax({
        url: "com.bos.bps.util.TbWfmWorkItemMapping.getTbWfmWorkitemmappingSingle.biz.ext",
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
        url: "com.bos.bps.util.TbWfmWorkItemMapping.saveTbWfmWorkitemmapping.biz.ext",
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