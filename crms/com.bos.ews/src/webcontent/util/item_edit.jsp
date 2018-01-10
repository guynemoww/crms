<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-10

  - Description:TB_REW_CSM_SIGNAL_LIST, com.bos.dataset.ews.TbRewCsmSignalList-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbRewCsmSignalList" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>信号关闭日期：</label>
		<input name="tbRewCsmSignalList.closeDate" required="true" class="nui-datepicker nui-form-input" />

		<label>预警信号认定日期：</label>
		<input name="tbRewCsmSignalList.confirmDate" required="true" class="nui-datepicker nui-form-input" />

		<label>客户预警信号ID：</label>
		<input name="tbRewCsmSignalList.csmSignalId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>预警信号分类：</label>
		<input name="tbRewCsmSignalList.csmWarningTypeId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>预警信号ID：</label>
		<input name="tbRewCsmSignalList.earlyWarningSignalId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>预警信号发起日期：</label>
		<input name="tbRewCsmSignalList.launchDate" required="true" class="nui-datepicker nui-form-input" />

		<label>预警事项描述：</label>
		<input name="tbRewCsmSignalList.matterState" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>参与人ID：</label>
		<input name="tbRewCsmSignalList.partyid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />

		<label>预警信号来源：</label>
		<input name="tbRewCsmSignalList.signalSourceCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>预警信号说明描述：</label>
		<input name="tbRewCsmSignalList.signalState" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>预警信号状态：</label>
		<input name="tbRewCsmSignalList.signalStatusCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>拟采取的控制措施及建议：</label>
		<input name="tbRewCsmSignalList.suggestState" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>：</label>
		<input name="tbRewCsmSignalList.warnAddAdjustId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>关闭信号变更ID：</label>
		<input name="tbRewCsmSignalList.warnCloseAdjustId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

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
	var json=nui.encode({"tbRewCsmSignalList":
		{"csmSignalId":
		"<%=request.getParameter("csmSignalId") %>"}});
	$.ajax({
        url: "com.bos.pub.crud.getTbRewCsmSignalList.biz.ext",
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
        url: "com.bos.pub.crud.updateTbRewCsmSignalList.biz.ext",
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
