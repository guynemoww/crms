<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-22

  - Description:TB_PUB_DECI_TABLE, com.bos.pub.decision.TbPubDeciTable-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbPubDeciTable" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>决策表编号：</label>
		<input name="tbPubDeciTable.tid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>

		<label>决策表名称：</label>
		<input name="tbPubDeciTable.tname" required="true" onvalidation="xx" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>创建时间：</label>
		<input name="tbPubDeciTable.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" enabled="false"/>
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
	var json=nui.encode({"tbPubDeciTable":
		{"tid":
		"<%=request.getParameter("tid") %>"}});
	$.ajax({
        url: "com.bos.pub.deciTable.getTbPubDeciTable.biz.ext",
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
        url: "com.bos.pub.deciTable.updateTbPubDeciTable.biz.ext",
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
