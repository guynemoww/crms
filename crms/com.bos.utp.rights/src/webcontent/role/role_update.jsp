
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
		body{
			width:100%;
			height: 150px;
			padding: 0px;
		}
	</style>
</head>
<body>
	<div id="form1" style="margin-top:10px;">
		<table style="width:100%;" class="nui-form-table">
			<tr>
				<td class="nui-form-label"><label for="acRole.roleid$text">角色代码：</label></td>
				<td><input id="acRole.roleid" class="nui-textbox" name="acRole.roleid" allowInput="false" disabled="true"/></td>
				<td class="nui-form-label"><label for="acRole.rolename$text">角色名称：</label></td>
				<td><input id="acRole.rolename" class="nui-textbox" name="acRole.rolename" required="true"/></td>
			</tr>
			<tr class="odd">
				<td class="nui-form-label"><label for="acRole.roletype$text">角色类型：</label></td>
				<td colspan="3">
				  <input id="acRole.roletype" readonly="true" class="nui-dictcombobox" valueField="dictID" textField="dictName" emptyText="全部角色"
		                 dictTypeId="ABF_ROLETYPE" name="acRole.roletype" showNullItem="true" nullItemText="全行角色"/>
		          <input class="nui-hidden" id="roletype" name="roletype" value=""/>
				</td>
			</tr>
		</table>
		<div class="nui-toolbar" style="padding:0px;" borderStyle="border:0;">
			<table width="100%">
				<tr>
					<td style="text-align:center;">
						<a class="nui-button" iconCls="icon-save" onclick="formSaving">保存</a>
						<span style="display:inline-block;width:25px;"></span>
						<a class="nui-button" iconCls="icon-cancel" onclick="formCancel">取消</a>
					</td>
				</tr>
			</table>
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

	<%-- 验证角色是否存在 --%>
	function checkRoleExist(roleCode){
		if(roleCode == srcRoleCode){
			return false;
		}
		var sendData = {"criteria":{"_expr":[{"roleCode":roleCode,"_op":"="}]}};
		var isExist;
		$.ajax({
			url: "com.bos.utp.rights.RoleManager.checkRoleExist.biz.ext",
			type: 'POST',
			data: nui.encode(sendData),
			cache: false,
			async: false,
			contentType: 'text/json',
			success: function(text){
				var returnJson = nui.decode(text);
				if(returnJson.isRoleExist == "true"){
					isExist = true;
				}else{
					isExist = false;
				}
			},
			error: function(jqXHR, textStatus, errorThrown){}
		});
		return isExist;
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
		if(form1.isChanged() == false){
			nui.confirm("数据未改变。<br/>是否关闭窗口?", "系统提示", function(action){
				if(action == "ok"){
					CloseWindow("noChanged");
				}else{}
			});
			return false;
		}
		form1.validate();
		if(form1.isValid()==false) return;

		var form1Data = form1.getData(false,true);
		var sendData = nui.encode(form1Data);
		$.ajax({
			url:"com.bos.utp.rights.RoleManager.updateRole.biz.ext",
			type:'POST',
			data:sendData,
			cache:false,
			contentType:'text/json',
			success:function(text){
				var returnJson = nui.decode(text);
				if(returnJson.exception == null){
					nui.alert("编辑角色成功", "系统提示", function(action){
						if(action == "ok" || action == "close"){
							CloseWindow("saveSuccess");
						}
					});
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
