<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-25

  - Description:TB_WFM_WORKITEMMAPPING, com.bos.bps.database.TbWfmProcessmapping-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.bps.dataset.bps.TbWfmWorkitemmapping" class="nui-hidden" />
<div class="nui-dynpanel" columns="4">
		<label>活动定义ID：</label>
		<input name="item.activityDefId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>活动定义名称：</label>
		<input name="item.activityName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>处理路径：</label>
		<input name="item.doUrl" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>最终判定：</label>
		<input name="item.finalJudge" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>机构级别：</label>
		<input name="item.orgLvCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>机构名称：</label>
		<input name="item.orgName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>岗位编号：</label>
		<input name="item.postCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>流程id(主键)：</label>
		<input name="item.processMappingId" enabled="false" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>退回目标编号：</label>
		<input name="item.reTarget" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>规则Id：</label>
		<input name="item.ruleId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>保存路径：</label>
		<input name="item.saveUrl" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>选择类型：</label>
		<input name="item.selectType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>服务名称：</label>
		<input name="item.serviceName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>服务版本：</label>
		<input name="item.serviceVersion" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>模板名称：</label>
		<input name="item.templateName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>模板版本：</label>
		<input name="item.templateVersion" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		<label>参与人变量名称：</label>
		<input name="item.userVariable" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>信息显示路径：</label>
		<input name="item.viewUrl" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>活动id(主键)：</label>
		<input name="item.workitemMappingId" enabled="false" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

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
		{"workitemMappingId":
		"<%=request.getParameter("workitemMappingId") %>","processMappingId":"<%=request.getParameter("processMappingId") %>","_entity":"com.bos.bps.dataset.bps.TbWfmWorkitemmapping"}});
	$.ajax({
        url: "com.bos.bps.util.TbWfmProcessMapping.getTbWfmProcessMapping.biz.ext",
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
        url: "com.bos.bps.util.TbWfmProcessMapping.saveTbWfmProcessMapping.biz.ext",
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