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
		<input id="scoreName" name="tbOrderMessage.scoreName" required="true"  class="nui-textbox" allowInput="false"  />

		<label>计分人工号：</label>
		<input id="scoreNumber" name="tbOrderMessage.scoreNumber" required="true"  class="nui-textbox nui-form-input"  enabled="false"/>

		<label>计分人机构名称：</label>
		<input id="scoreOrgName" name="tbOrderMessage.scoreOrgName" required="true"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" dictTypeId="org" enabled="false"/>

		<label>计分人机构编号：</label>
		<input id="scoreOrgNumber" name="tbOrderMessage.scoreOrgNumber" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false"/>

		<label>计分项目名称：</label>
		<input id="scoreProjectName" name="tbOrderMessage.scoreProjectName" required="true" class="nui-buttonEdit" allowInput="false"  vtype="maxLength:60" onbuttonclick="selectPorject"/>
		<label>计分事项：</label>
		<input id="scoreMatter" name="tbOrderMessage.scoreMatter" required="true" class="nui-TextArea" vtype="maxLength:60" enabled="false"/>
		
		<label>应计分：</label>
		<input id="shouldTheScoring" name="tbOrderMessage.shouldTheScoring" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false"/>
		<label>实计分：</label>
		<input id="realScoring" name="tbOrderMessage.realScoring" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />

		<label>经办人姓名：</label>
		<input name="tbPunishMessage.userNum" required="false"  class="nui-buttonEdit" vtype="maxLength:60" dictTypeId="user" />

		<label>经办人工号：</label>
		<input name="tbPunishMessage.orgPeopleName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>经办人机构：</label>
		<input name="tbPunishMessage.orgPeopleNumber" required="false" class="nui-buttonEdit" vtype="maxLength:60" dictTypeId="org"/>

		<label>经办日期：</label>
		<input name="tbPunishMessage.orgTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" />

		<label>处罚措施：</label>
		<input name="tbPunishMessage.punishMeasure" required="false" class="nui-TextArea" vtype="maxLength:60" />

		<label>处罚意见：</label>
		<input name="tbPunishMessage.punishOpinion" required="false" class="nui-TextArea" vtype="maxLength:60" />
        

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
        url: "com.bos.pub.punishDeal.getTbPunishMessage.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		var jsons=nui.encode({"tbOrderMessage":
					{"scoreMessageId":text.tbPunishMessage.orderMessageNumber}});
				$.ajax({
			        url: "com.bos.pub.openOrder.getTbOrderMessage.biz.ext",
			        type: 'POST',
			        data: jsons,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		nui.alert(text.msg);
			        	} else {
			                 nui.get("scoreName").setValue(text.tbOrderMessage.scoreName);
			                    nui.get("scoreNumber").setValue(text.tbOrderMessage.scoreNumber);
			                       nui.get("scoreName").setValue(text.tbOrderMessage.scoreName);
			                          nui.get("scoreOrgName").setValue(text.tbOrderMessage.scoreOrgName);
			                             nui.get("scoreOrgNumber").setValue(text.tbOrderMessage.scoreOrgNumber);
			                                 nui.get("scoreProjectName").setText(text.tbOrderMessage.scoreProjectName);
			                                   nui.get("scoreMatter").setValue(text.tbOrderMessage.scoreMatter);
			                                      nui.get("shouldTheScoring").setValue(text.tbOrderMessage.shouldTheScoring);
			                                         nui.get("realScoring").setValue(text.tbOrderMessage.realScoring);
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
				});
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
        url: "com.bos.pub.punishDeal.updateTbPunishMessage.biz.ext",
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
