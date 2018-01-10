<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-14

  - Description:TB_SCORE_PROJECT_MESSAGE, com.bos.pub.sys.TbScoreProjectMessage-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbScoreProjectMessage" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>项目名称：</label>
		<input name="tbScoreProjectMessage.projectName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
		<label>机构名称：</label>
		<input name="tbScoreProjectMessage.organizationName" required="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" vtype="maxLength:60" enabled="false" dictTypeId="org"/>

		<label>经办机构：</label>
		<input name="tbScoreProjectMessage.orgNum" required="true" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"  vtype="maxLength:60" dictTypeId="org" enabled="false"/>

		<label>机构级别：</label>
		<input name="tbScoreProjectMessage.orgLevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="CDZZ0002" enabled="false"/>

		<label>经办人：</label>
		<input name="tbScoreProjectMessage.userNum" required="false" class="nui-buttonEdit" vtype="maxLength:60" dictTypeId="user" enabled="false"/>
		
		<label>创建日期：</label>
		<input name="tbScoreProjectMessage.setUpTime" required="" class="nui-datepicker nui-form-input" vtype="maxLength:10" enabled="false"/>
		
		<label>维护日期：</label>
		<input id="t" name="tbScoreProjectMessage.maintainTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" enabled="false"/>
		
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
	var json=nui.encode({"tbScoreProjectMessage":
		{"projectNumber":
		"<%=request.getParameter("projectNumber") %>"}});
	$.ajax({
        url: "com.bos.pub.scorePorject.getTbScoreProjectMessage.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        			var a=new Date();
                var time=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
        		nui.get("t").setValue(time);
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
        url: "com.bos.pub.scorePorject.updateTbScoreProjectMessage.biz.ext",
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