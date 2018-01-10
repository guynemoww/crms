<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-19

  - Description:TB_PUNISH_MESSAGE, com.bos.pub.sys.TbPunishMessage-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbPunishMessage" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>计分人姓名：</label>
		<input id="scoreName" name="tbPunishMessage.scoreName" required="true"  class="nui-textbox nui-form-input"  enabled="false"  onbuttonclick="selectCustManeger" dictTypeId="user"/>

		<label>计分人工号：</label>
		<input id="scoreNumber" name="tbPunishMessage.scoreNumber" required="true"  class="nui-textbox nui-form-input"  enabled="false"/>
		<label>计分人机构：</label>
		<input id="scoreOrgNumber" name="tbPunishMessage.scoreOrgNumber" required="true" class="nui-buttonEdit" vtype="maxLength:4" enabled="false" dictTypeId="org"/>
		<label>经办人姓名：</label>
		<input name="tbPunishMessage.userNum" required="false"  class="nui-buttonEdit" vtype="maxLength:60" dictTypeId="user" />
		<label>处罚措施：</label>
		<input name="tbPunishMessage.punishMeasure" required="false" class="nui-TextArea" vtype="maxLength:60" />

		<label>处罚意见：</label>
		<input name="tbPunishMessage.punishOpinion" required="false" class="nui-TextArea" vtype="maxLength:60" />
        <label>经办人工号：</label>
		<input name="tbPunishMessage.orgPeopleName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>经办人机构：</label>
		<input name="tbPunishMessage.orgPeopleNumber" required="false" class="nui-buttonEdit" vtype="maxLength:60" dictTypeId="org"/>

		<label>经办日期：</label>
		<input name="tbPunishMessage.orgTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />
        

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
	var json=nui.encode({"tbPunishMessage":
		{"punishId":
		"<%=request.getParameter("punishId") %>"}});
	$.ajax({
        url: "com.bos.pub.openOrder.getPunishStat.biz.ext",
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


	</script>
</body>
</html>
