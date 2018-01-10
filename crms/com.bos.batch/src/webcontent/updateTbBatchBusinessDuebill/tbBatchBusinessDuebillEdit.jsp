<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-09-18

  - Description:TB_BATCH_BUSINESS_DUEBILL, com.bos.dataset.batch.TbBatchBusinessDuebill-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="form1" style="width:94%;height:auto;overflow:hidden;text-align:left">
	<input type="hidden" name="tbBatchBusinessDuebill" class="nui-hidden" />
	<fieldset>
  	<legend>
   	 <span>数仓异常额度调整</span>
    </legend>
	<div class="nui-dynpanel" columns="4">

		<label>借据号：</label>
		<input name="tbBatchBusinessDuebill.serialno" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />
		
		<label>入账机构号：</label>
		<input name="tbBatchBusinessDuebill.orgNum" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:9" />
		
		<label>币种：</label>
		<input name="tbBatchBusinessDuebill.currency" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001"/>
		
		<label>增改标识位：</label>
		<input name="tbBatchBusinessDuebill.auflag" id="auflag" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:9" value=""/>
		
		<label>发放金额：</label>
		<input name="tbBatchBusinessDuebill.businesssum" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />
		
		<label>本金总余额：</label>
		<input name="tbBatchBusinessDuebill.balance" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />
		
		<label>正常余额：</label>
		<input name="tbBatchBusinessDuebill.normalbalance" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />
		
		<label>三个月以内逾期余额：</label>
		<input name="tbBatchBusinessDuebill.overduebalance1" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>三个月以上逾期余额：</label>
		<input name="tbBatchBusinessDuebill.overduebalance2" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />
		
		<label>呆滞余额：</label>
		<input name="tbBatchBusinessDuebill.dullbalance" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />
		
		<label>呆账余额：</label>
		<input name="tbBatchBusinessDuebill.badbalance" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>表内欠息余额：</label>
		<input name="tbBatchBusinessDuebill.interestbalance1" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>表外欠息余额：</label>
		<input name="tbBatchBusinessDuebill.interestbalance2" required="false" dataType="currency" class="nui-textbox nui-form-input" vtype="maxLength:26" />

	</div>
</fieldset>					
<div class="nui-toolbar" style="width:100%;text-align:right;padding-top:10px;padding-bottom:5px;" borderStyle="border:0;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	<span style="display:inline-block;width:25px;"></span>
</div>
</div>
<center>	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    var auflagDefaultValue = "RET";
    var auflagValue = "";
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	initForm();
	
	
function initForm() {
	var json=nui.encode({"tbBatchBusinessDuebill":
		{"uuid":
		"<%=request.getParameter("uuid") %>"}});
	$.ajax({
        url: "com.bos.batch.updateTbBatchBusinessDuebill.getTbBatchBusinessDuebill.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		auflagValue = nui.get("auflag").getValue();
        		if(auflagValue != "RET"){
        			nui.get("auflag").setValue(auflagDefaultValue);
        		} else{
        			//alert(auflagValue);
        		}
        		
        		
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
	
	
}



function save() {
	git.mask();
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		git.unmask();
		return;
	}
	nui.confirm("确定保存修改吗？","确认",function(action){
        if(action!="ok") {git.unmask(); return;}
        var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	        url: "com.bos.batch.updateTbBatchBusinessDuebill.updateTbBatchBusinessDuebill.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	git.unmask();
	        	if(text.msg){
	        		nui.alert(text.msg);
	        	} else {
	        		CloseWindow("ok");
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
		});
    });
	
		
	
}
	</script>
</body>
</html>
