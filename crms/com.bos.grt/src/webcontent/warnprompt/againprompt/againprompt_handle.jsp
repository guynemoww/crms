<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): huangkai
  - Date: 2014-03-30

  - Description:TB_GRT_AGAINPROMPT, com.bos.dataset.grt.TbGrtAgainprompt-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbGrtAgainprompt" class="nui-hidden" />
	<div class="nui-dynpanel" columns="2">
	
	
		<label>担保合同编号：</label>
		<input name="tbGrtAgainprompt.suretyPactNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" />
	
		<label>贷款合同编号：</label>
		<input name="tbGrtAgainprompt.loanPactNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" />
		
		<label>押品名称：</label>
		<input name="tbGrtAgainprompt.guarantyName" required="false" class="nui-text nui-form-input" vtype="maxLength:60"/>
		
		<label>提示类型：</label>
		<input name="tbGrtAgainprompt.promptType" required="false" class="nui-text nui-form-input" vtype="maxLength:2" dictTypeId="XD_DBCD4001"/>
		
		<label>押品类型：</label>
		<input name="tbGrtAgainprompt.guarantyType" required="false" class="nui-text nui-form-input" vtype="maxLength:20" dictTypeId="XD_DBCD4002"/>

		<label>处理状态：</label>
		<input name="tbGrtAgainprompt.promptCloseState" id="dealstatu" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_DBCD4003"/>
		
		<label>处理意见：</label>
		<input name="tbGrtAgainprompt.handleIdea" required="false" class="nui-textarea nui-form-input" vtype="maxLength:100" />
		
		<label>处理日期：</label>
		<input name="tbGrtAgainprompt.handleDate" id="tbGrtAgainprompt.handleDate" required="false" Enabled="false" class="nui-text nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  format="yyyy-MM-dd"/>
		
		<!--  
		<label>重估到期日期：</label>

		<label>重估周期：</label>
		<input name="tbGrtAgainprompt.againRound" required="false" class="nui-textbox nui-form-input" vtype="maxLength:2" />

		<label>客户名称：</label>
		<input name="tbGrtAgainprompt.clientName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
	
		<label>贷款发放日期：</label>
		<input name="tbGrtAgainprompt.loanGrantDate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		
		<label>机构名称：</label>
		<input name="tbGrtAgainprompt.orgName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />

		
		<label>提示生成日期：</label>
		<input name="tbGrtAgainprompt.promptCreateDate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

	
		<label>担保品ID：</label>
		<input name="tbGrtAgainprompt.suretyId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		
-->
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");

	function initForm() {
	var json=nui.encode({"tbGrtAgainprompt":
		{"warnId":
		"<%=request.getParameter("warnId") %>"}});
	$.ajax({
        url: "com.bos.grt.warnprompt.againprompt.getTbGrtAgainprompt.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		nui.get("tbGrtAgainprompt.handleDate").setValue("<%=com.bos.pub.GitUtil.getBusiDate()%>");
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
        url: "com.bos.grt.warnprompt.againprompt.updateTbGrtAgainprompt.biz.ext",
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
