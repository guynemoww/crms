<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-08

  - Description:TB_PUB_GRANT_TABLE_COL, com.bos.pub.decision.TbPubGrantTableCol-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbPubGrantTableCol" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>规则标识：</label>
		<input name="tbPubGrantTableCol.rind" required="false" class="nui-text" vtype="maxLength:100" />

		<label>适用机构：</label>
		<input name="tbPubGrantTableCol.torg" required="false" class="nui-text" vtype="maxLength:20" dictTypeId="org" />

		<label>参数名称：</label>
		<input name="tbPubGrantTableCol.tname" required="false" class="nui-text" vtype="maxLength:1000" />

		<label>参数值：</label>
		<input name="tbPubGrantTableCol.tvalue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:2000" />

		<label>创建机构：</label>
		<input name="tbPubGrantTableCol.orgNum" class="nui-text" vtype="maxLength:20" dictTypeId="org"/>

		<label>创建时间：</label>
		<input name="tbPubGrantTableCol.createTime" class="nui-text"/>

		<label>创建人：</label>
		<input name="tbPubGrantTableCol.userNum" class="nui-text" vtype="maxLength:20" dictTypeId="user"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
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
	var json=nui.encode({"tbPubGrantTableCol":
		{"tid":"<%=request.getParameter("tid") %>"}});
	$.ajax({
        url: "com.bos.pub.decision.getTbPubGrantTableCol.biz.ext",
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
        url: "com.bos.pub.decision.updateTbPubGrantTableCol.biz.ext",
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
