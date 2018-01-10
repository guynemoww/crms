<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<head>
<title>学生信息</title>
</head>
<body>
	<div id="form1" style="width: 100%; height: 100%; overflow: hidden;">
	<input id="expireCheck.id" name="expireCheck.id"  class="nui-hidden" />
		<fieldset>
			<legend>
				<span>授信到期前跟踪检查参数</span>
			</legend>
			<div class="nui-dynpanel" id="table1" columns="2">
				<label>授信到期前余额：</label> 
				<input id="expireCheck.creditExpireYue" name="expireCheck.creditExpireYue" required="true" vtype="float;maxLength:20" dataType="currency" class="nui-textbox nui-form-input" />
				<label>授信到期前跟踪检查标志：</label> 
				<input id="expireCheck.creditExpireCheckInd" name="expireCheck.creditExpireCheckInd"enabled="false" required="true" dictTypeId="YesOrNo" class="nui-dictcombobox" />
			</div>
		</fieldset>
		<div class="nui-toolbar" style="border: 0; text-align: center; padding-right: 20px;">
			<a id="btnSave" class="nui-button" style="margin-right: 5px;" iconCls="icon-save" onclick="update">保存</a> 
			<a id="btnClose" class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>
</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var id="<%=request.getParameter("id")%>";
		var view="<%=request.getParameter("view")%>";
		if(view=="0"){
			form.setEnabled(false);
			nui.get("btnSave").hide();//保存按钮
		}
		//初始化表格数据
		$(document).ready(function() {
			var json = nui.encode({"id" : id});
			$.ajax({
				url : "com.bos.pub.afterLoanCheck.afterLoanCheck.findAfterLoanCheckDetail.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					form.setData(text);
					//备份数据
					window.form1Data = form.getData();
				}
			});
		});
		
		//修改操作
		function update() {
			form.validate();
			if (form.isValid() == false) {
				return;
			}
			var o = form.getData();
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.pub.afterLoanCheck.afterLoanCheck.updateAfterLoanCheck.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
					} else {
						nui.alert("修改成功!","提示",function(action){
				    		CloseWindow();
				  	 	});
					}
				},
				error : function() {
					nui.alert("操作失败！");
				}
			});
		}
	</script>
</body>
</html>

