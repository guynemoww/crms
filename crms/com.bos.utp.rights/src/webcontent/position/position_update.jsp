<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Date: 2014-02-19
  - author: 王世春
  - Description: 岗位添加
-->

<head>
	<title>添加岗位</title>
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
		<div class="nui-dynpanel" columns="2">
			 
				  <label>岗位编码：</label> 
				  <input id="item.posicode" class="nui-textbox" name="item.posicode" required="true" enabled="false"/>
				  <label>岗位名称：</label> 
				  <input id="item.posiname" class="nui-textbox" name="item.posiname" required="true" vtype="maxLength:128"/>
			 	  <label>岗位说明： </label>
			 	  <input id="item.posistate" class="nui-TextArea nui-form-input"  name="item.posistate" vtype="maxLength:2000" />
			 </div>
		<div class="nui-toolbar" style="padding:0px;" borderStyle="border:0;">
			<table width="100%">
				<tr>
					<td style="text-align:center;">
						<a class="nui-button" iconCls="icon-save" onclick="formSaving" id="btnSave">保存</a>
						<span style="display:inline-block;width:25px;"></span>
						<a class="nui-button" iconCls="icon-cancel" onclick="formCancel">返回</a>
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
if ("<%=request.getParameter("view") %>"=="1") {
			form1.setEnabled(false);
			nui.get("btnSave").hide();
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

	function formSaving(){
		form1.validate();
		if(form1.isValid()==false) return;
		
		CloseWindow("saveSuccess");
	}

	function formCancel(){
		CloseWindow("cancel");
	}
</script>
