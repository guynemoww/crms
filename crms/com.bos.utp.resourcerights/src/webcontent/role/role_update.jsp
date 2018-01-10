
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): liuzn (mailto:liuzn@primeton.com)
  - Date: 2013-03-11 14:20:35
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
	<title>修改角色</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/common/nui/common.jsp" %>
	<style type="text/css">
		
	</style>
</head>
<body>
	<div id="form1" style="margin-top:0px;">
		<input class="nui-hidden" name="acRole.roleid" />
		<div class="nui-dynpanel" columns="2">
			<label for="acRole.rolename$text">角色名称：</label> 
			<input id="acRole.rolename" class="nui-textbox" name="acRole.rolename" required="true"/> 
		</div>
		<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
			<a class="nui-button" iconCls="icon-save" onclick="formSaving">保存</a>
		    <a class="nui-button" iconCls="icon-cancel" onclick="formCancel">返回</a>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var form1 = new nui.Form("#form1");
	var srcRoleCode;

	<%-- 父页面调用：初始化传值 --%>
	function setData(row){
		data = nui.clone(row);
		var sendData = nui.encode({acRole:data});
		var modData = nui.decode(sendData);
		modData.roletype = modData.acRole.roletype ;//modData增加roletype对象，并赋值。
		form1.setData(modData);
		form1.setChanged(false);
		srcRoleCode = modData.acRole.roleid ;
	}

	<%-- 关闭窗口 --%>
	function CloseWindow(action){
		if(action=="close" && form1.isChanged()){
			if(confirm("数据已改变,是否先保存?")){
				return false;
			}
		}else if(window.CloseOwnerWindow){
			return window.CloseOwnerWindow(action);
		}else{
			return window.close();
		}
	}

	function checkRoleCode(e){
		if(e.isValid){
			if(checkRoleExist(e.value) == true){
				e.errorText = "角色已存在";
				e.isValid = false;
			}
		}
	}

	function formSaving(){
		form1.validate();
		if(form1.isValid()==false) return;

		var form1Data = form1.getData(false,true);
		var sendData = nui.encode(form1Data);
		$.ajax({
			url:"com.bos.utp.resourcerights.RoleManager.updateRole.biz.ext",
			type:'POST',
			data:sendData,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var returnJson = nui.decode(text);
				if(returnJson.exception == null){
					if(returnJson.returnCode == "2") {
						nui.alert("角色名称已经存在，请重新输入", "系统提示", function(action) {
							nui.get("acRole.rolename").focus();
						});
					} else if(returnJson.returnCode == "-1") {
						nui.alert("编辑角色失败", "系统提示", function(action){
							CloseWindow("saveFailed");
						});
					} else {
						nui.alert("编辑角色成功", "系统提示", function(action){
							CloseWindow("saveSuccess");
						});
					}
				}else{
					nui.alert("编辑角色失败", "系统提示", function(action){
						if(action == "ok" || action == "close"){
							CloseWindow("saveFailed");
						}
					});
				}
			}
		});
	}

	function formCancel(){
		CloseWindow("cancel");
	}
</script>
