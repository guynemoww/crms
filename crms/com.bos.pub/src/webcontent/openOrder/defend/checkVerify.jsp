<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-18
  - Description:TB_PLEA_MESSAGE, com.bos.pub.sys.TbPleaMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<input id="scoreMessageId"  name="tbOrderMessage.scoreMessageId" required="false" class="nui-hidden nui-form-input" vtype="maxLength:4" enabled="false" />
		
	<div class="nui-dynpanel" columns="4">
		<label>计分人姓名：</label>
		<input  name="tbOrderMessage.scoreName" enabled="false"  class="nui-textbox"  />

		<label>计分人工号：</label>
		<input  name="tbOrderMessage.scoreNumber" required="false"  class="nui-textbox nui-form-input"  enabled="false" />

		<label>计分人机构名称：</label>
		<input  name="tbOrderMessage.scoreOrgName" required="false"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" dictTypeId="org" enabled="false"/>

		<label>计分人机构编号：</label>
		<input  name="tbOrderMessage.scoreOrgNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false" />

		<label>计分项目名称：</label>
		<input  name="tbOrderMessage.scoreProjectName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60"  enabled="false"/>

		<label>计分项目编号：</label>
		<input  name="tbOrderMessage.scoreProjectNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false" />
		
		<label>计分事项：</label>
		<input  name="tbOrderMessage.scoreMatter" required="false" class="nui-TextArea" vtype="maxLength:60" enabled="false" />
		<label>应计分：</label>
		<input  name="tbOrderMessage.shouldTheScoring" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false" />
		<label>实计分：</label>
		<input name="tbOrderMessage.realScoring" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4"  enabled="false"/>
		
		<label>开单信息编号：</label>
		<input  name="tbPleaMessage.orderMessageNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>

		<label>申辩人部门：</label>
		<input id="scoreDepartment" name="tbPleaMessage.pleaDep" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:60"   dictTypeId="XD_BM0023" enabled="false"/>
		<label>申辩人姓名：</label>
		<input id="scoreName" name="tbPleaMessage.pleaName" required="false" class="nui-textbox"   vtype="maxLength:60"  enabled="false"/>
		<label>申辩人工号：</label>
		<input id="scoreNumber" name="tbPleaMessage.pleaNumber" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" enabled="false"/>

		<label>申辩人机构：</label>
		<input id="scoreOrgName" name="tbPleaMessage.pleaOrg" required="false" class="nui-buttonEdit"  dictTypeId="org" vtype="maxLength:60"  enabled="false"/>

		<label>申辩信息：</label>
		<input name="tbPleaMessage.pleaTable" required="false" class="nui-TextArea" vtype="maxLength:1000" />

		<label>申辩日期：</label>
		<input  name="tbPleaMessage.pleaTime" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" enabled="false"/>

	</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-edit" onclick="verify()">申辩成功</a>
	<a class="nui-button" iconCls="icon-edit" onclick="verifyFail()">申辩失败</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		form.setEnabled(false);

function initForm() {
	var json=nui.encode({"scoreMessageId":"<%=request.getParameter("scoreMessageId") %>"});
	$.ajax({
        url: "com.bos.pub.openOrder.queryVerifyMessage.biz.ext",
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
	   
       
// 申辩成功
function verify(){
        var scoreMessageId;
        scoreMessageId=nui.get("scoreMessageId").getValue();
        		 nui.confirm("确定申辩成功吗？","确认",function(action){
            	if(action!="ok") return;  
            	var json=nui.encode({"tbOrderMessage":{"scoreMessageId":
            		scoreMessageId,"state":"07"}});
                $.ajax({
                     url: "com.bos.pub.openOrder.updateState.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                       CloseWindow("ok");
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
}
// 申辩失败
function verifyFail(){
        var scoreMessageId;
        scoreMessageId=nui.get("scoreMessageId").getValue();
        		 nui.confirm("确定申辩驳回吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbOrderMessage":{"scoreMessageId":
            		scoreMessageId,"state":"08"}});
                $.ajax({
                     url: "com.bos.pub.openOrder.updateState.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        CloseWindow("ok");
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 

}

	</script>
</body>
</html>
