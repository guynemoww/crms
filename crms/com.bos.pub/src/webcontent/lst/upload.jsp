<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
		<form id="form1" action="com.bos.pub.lst.lst.upload.biz.ext"
			method="post" enctype="multipart/form-data" target="downloadFileFrame">
			<fieldset>
				<legend>
					<span>Excel导入</span>
				</legend>
				<div
					style="width: 60%; height: auto; overflow: hidden; text-align: center; margin-top: 20px; border: 1px solid black">
					<div class="nui-dynpanel"
						style="width: 80%; height: auto; overflow: hidden; text-align: center; margin-top: 20px;"
						id="table2" columns="2">
						<label>选择要上传的文件：</label> <input class="nui-htmlfile" id="Fdata"
							name="Fdata" limitType="*.xls" required="true" />
					</div>
					<a class="nui-button" iconCls="icon-upload"
						onclick="uploadExcel();" disableOnClick="true" />上传</a>
				</div>
			</fieldset>
		</form>
	</center>
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>			
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	

	/**
	*导入模板
	*/     
    function uploadExcel() {
   	var frm = document.getElementById("form1");
		var o=form.getData();

    	if(!nui.get("Fdata").value){
			nui.alert("请选择文件");
			return;
		}
		if(/\.xls?$/g.test(nui.get("Fdata").value) == false){
			nui.alert("请选择.xls文件");
			return;
		}
		frm.submit();
		CloseWindow('ok');
    }
    
</script>
</body>
</html>
