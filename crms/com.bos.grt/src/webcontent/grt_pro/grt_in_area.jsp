<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品(期转现)入库面积录入</title>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6">
		<label>面积：</label>
		<input name="area" class="nui-textbox nui-form-input" id = "area"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
		   <a class="nui-button" iconCls="icon-save" id="btnsave" onclick="save()">保存</a>
		</div>
	</div>
				
	<script type="text/javascript">
	 	nui.parse();
    	var form = new nui.Form("#form1");
		var bizId ="<%=request.getParameter("bizId") %>"; //押品入库ID
		var suretyId ="<%=request.getParameter("suretyId") %>"; //押品ID
		function save(){
			var area = nui.get("area").value;
			var json=nui.encode({"suretyId":suretyId,"area":area});
		     $.ajax({ 
		        	url: "com.bos.grtPro.outDetail.saveArea.biz.ext",
		        	type: 'POST',
		        	data: json,
		        	cache: false,
		        	contentType:'text/json',
		        	success: function (text) {
		        		if(text.msg !=null){
		            		nui.alert(text.msg); //失败时后台直接返回出错信息
		            		return;
		            	}
		            	alert("保存成功！");
		        	},
		        	error: function (jqXHR, textStatus, errorThrown) {
		            	nui.alert(jqXHR.responseText);
		        	}
				});
			}
	</script>
</body>
</html>