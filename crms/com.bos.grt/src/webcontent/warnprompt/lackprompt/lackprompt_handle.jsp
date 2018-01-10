<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): huangkai
  - Date: 2014-03-30

  - Description:TB_GRT_LACKPROMPT, com.bos.dataset.grt.TbGrtLackprompt-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbGrtLackprompt" class="nui-hidden" />
	<div class="nui-dynpanel" columns="2">
		
		<label>担保合同号：</label>
		<input name="tbGrtLackprompt.suretyPactNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" />
		
		<label>贷款合同号：</label>
		<input name="tbGrtLackprompt.loanPactNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" />
		
		<label>押品名称：</label>
		<input name="tbGrtLackprompt.guarantyName" required="false" class="nui-text nui-form-input" vtype="maxLength:60" />
		
		<label>押品类型：</label>
		<input name="tbGrtLackprompt.guarantyType" required="false" class="nui-text nui-form-input" vtype="maxLength:20" dictTypeId="XD_DBCD4002"/>
		
		<label>处理状态：</label>
		<input name="tbGrtLackprompt.promptCloseState" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_DBCD4003"/>
		
		<label>处理意见：</label>
		<input name="tbGrtLackprompt.handleIdea" required="false" class="nui-textarea nui-form-input" vtype="maxLength:100" />
		
		<label>处理日期：</label>
		<input name="tbGrtLackprompt.handleDate" id="tbGrtLackprompt.handleDate" required="false" Enabled="false" class="nui-text nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  format="yyyy-MM-dd"/>
	
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
	var json=nui.encode({"tbGrtLackprompt":
		{"warnId":
		"<%=request.getParameter("warnId") %>"}});
	$.ajax({
        url: "com.bos.grt.warnprompt.lackprompt.getTbGrtLackprompt.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		nui.get("tbGrtLackprompt.handleDate").setValue("<%=com.bos.pub.GitUtil.getBusiDate()%>");
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
        url: "com.bos.grt.warnprompt.lackprompt.updateTbGrtLackprompt.biz.ext",
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
