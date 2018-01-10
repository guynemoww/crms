<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-08

  - Description:TB_PUB_GRANT_TABLE, com.bos.pub.decision.TbPubGrantTable-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbPubGrantTable" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">


		<label>授权表标识：</label>
		<input name="tbPubGrantTable.pind" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>授权表名称：</label>
		<input name="tbPubGrantTable.pname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<!--<label>规则类别编号：</label>
		<input name="tbPubGrantTable.tid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />-->

		<!--<label>授权表状态：</label>
		<input name="tbPubGrantTable.pstatus" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />-->

		<label>创建机构：</label>
		<input name="tbPubGrantTable.orgNum"  textName="tbPubGrantTable.orgNum"  required="false" class="nui-text" dictTypeId="org" />

		<label>创建时间：</label>
		<input name="tbPubGrantTable.createtime"  required="false" class="nui-text nui-form-input" vtype="maxLength:10" enabled="false"/>

		<label>创建人：</label>
		<input colspan="3" name="tbPubGrantTable.userNum"  textName="tbPubGrantTable.userNum"  required="false" class="nui-text" dictTypeId="user" />
		
		<label>适用范围或备注：</label>
		<input colspan="3" name="tbPubGrantTable.pnote" required="false" class="nui-textarea nui-form-input" style="width:80%;" vtype="maxLength:4000" />
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
	var json=nui.encode({"tbPubGrantTable":
		{"pid":
		"<%=request.getParameter("pid") %>"}});
	$.ajax({
        url: "com.bos.pub.decision.getTbPubGrantTable.biz.ext",
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
        url: "com.bos.pub.decision.updateTbPubGrantTable.biz.ext",
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
