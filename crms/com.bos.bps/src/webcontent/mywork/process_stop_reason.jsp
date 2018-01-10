<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): js1688
  - Date: 2014-05-29 10:25:22
  - Description:
-->
<head>
<title>指定流程代办</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
			<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
			<tr>
				<td style="width: 20%"><label class="nui-form-label">终止原因： </label>
				</td>
				<td colspan="2" style="width: 80%">
					<input id="it.opinion" name="it.opinion" class="nui-textarea"
					required="true" maxLength="512" style="width: 80%;height: 100px" />
				</td>
			</tr>
		</table>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-ok" id="btn_submitEval" onclick="onOk()">确认</a>
	   	<a class="nui-button" iconCls="icon-cancel" id="btn_cancel" onclick="onCancel()">取消</a>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1");
	
	function GetData() {
		var opinion=nui.get("it.opinion").getValue();
        return opinion;
    } 
	function onOk() {
   		form.validate();
        if (form.isValid()==false) return;
        CloseWindow("ok");
    }
    
    function onCancel() {
        CloseWindow("cancel");
    }
    
	function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }
</script>