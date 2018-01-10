<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): huangkai
  - Date: 2014-03-30

  - Description:TB_GRT_DRAFTEXPIRE, com.bos.dataset.grt.TbGrtDraftexpire-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbGrtDraftexpire" class="nui-hidden" />
	<div class="nui-dynpanel" columns="2">
		
		<label>担保合同编号：</label>
		<input name="tbGrtDraftexpire.suretyPactNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" />
		
		<label>贷款合同编号：</label>
		<input name="tbGrtDraftexpire.loanPactNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" />
		
		<label>押品名称：</label>
		<input name="tbGrtDraftexpire.guarantyName" required="false" class="nui-text nui-form-input" vtype="maxLength:60" />
		
		<label>提示类型：</label>
		<input name="tbGrtDraftexpire.promptType" required="false" class="nui-text nui-form-input" vtype="maxLength:2" dictTypeId="XD_DBCD4001"/>
		
		<label>押品类型：</label>
		<input name="tbGrtDraftexpire.guarantyType" required="false" class="nui-text nui-form-input" vtype="maxLength:20" dictTypeId="XD_DBCD4002"/>
		
		<label>处理状态：</label>
		<input name="tbGrtDraftexpire.promptCloseState" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_DBCD4003"/>
		
		<label>处理意见：</label>
		<input name="tbGrtDraftexpire.handleIdea" required="false" class="nui-textarea nui-form-input" vtype="maxLength:100" />
		
		<label>处理日期：</label>
		<input name="tbGrtDraftexpire.promptHandleDate" id="tbGrtDraftexpire.promptHandleDate" required="false" Enabled="false" class="nui-text nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  format="yyyy-MM-dd"/>
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
	var json=nui.encode({"tbGrtDraftexpire":
		{"warnId":
		"<%=request.getParameter("warnId") %>"}});
	$.ajax({
        url: "com.bos.grt.warnprompt.draftexpire.getTbGrtDraftexpire.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		nui.get("tbGrtDraftexpire.promptHandleDate").setValue("<%=com.bos.pub.GitUtil.getBusiDate()%>");
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
        url: "com.bos.grt.warnprompt.draftexpire.updateTbGrtDraftexpire.biz.ext",
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
