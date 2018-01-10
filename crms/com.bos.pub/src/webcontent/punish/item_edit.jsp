<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-17

  - Description:TB_PUNISH_STANDARD_MESSAGE, com.bos.pub.sys.TbPunishStandardMessage-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbPunishStandardMessage" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>累计类型：</label>
		<input name="tbPunishStandardMessage.addUpType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:9" dictTypeId="XD_L23J3" emptyText="请选择"/>

		<label>积分开始数值：</label>
		<input name="tbPunishStandardMessage.integralStartValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />

		<label>积分截止数值：</label>
		<input name="tbPunishStandardMessage.integralEndValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />

		<label>处罚措施：</label>
		<input name="tbPunishStandardMessage.punishMeasure" required="true" class="nui-TextArea" vtype="maxLength:300" />
		<label>经办机构：</label>
		<input name="tbPunishStandardMessage.orgNum" required="true" class="nui-buttonEdit" dictTypeId="org" vtype="maxLength:60" enabled="false"/>
		<label>经办人：</label>
		<input name="tbPunishStandardMessage.userNum" required="true" class="nui-buttonEdit" dictTypeId="user" vtype="maxLength:60" enabled="false"/>

		<label>创建日期：</label>
		<input name="tbPunishStandardMessage.createTime" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10" />
		<label>维护日期：</label>
		<input id="t" name="tbPunishStandardMessage.maintainTime" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

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
	var json=nui.encode({"tbPunishStandardMessage":
		{"punishStandardId":
		"<%=request.getParameter("punishStandardId") %>"}});
	$.ajax({
        url: "com.bos.pub.punish.getTbPunishStandardMessage.biz.ext",
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
        url: "com.bos.pub.punish.updateTbPunishStandardMessage.biz.ext",
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
