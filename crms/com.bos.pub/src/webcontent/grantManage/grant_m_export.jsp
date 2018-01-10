<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-07-03 09:05:14
  - Description:
-->
<head>
<title>授权维护</title>
</head>
<body>
<%
Object ret = request.getAttribute("retDesc");
if(ret != null && !"".equals(ret)){
	out.println("<div align='center' class='description'>"+ret+"</div>");
}
 %>
<form id="form1" action="com.bos.pub.grantManage.grantManageM.flow" method="post"  enctype="multipart/form-data" >
	<div class="nui-dynpanel"  columns="2">
		<label>授权规则Excel导入：</label>
		<input id="empItemFile" name="empItemFile"  class="nui-htmlfile" limitType="*.xls" /> 
		<a class="nui-button"  iconCls="icon-upload" type="submit" onclick="uploadExcel"/>导入</a>
	    <a class="nui-button"  iconCls="icon-download" type="submit" onclick="downExcel"/>下载模板</a>
	</div>    
</form>	 
<script type="text/javascript">
	nui.parse();
	var f = document.getElementById("form1");
	
	var ret = '<%=request.getAttribute("retCode") %>'
	if('1'==ret){
		//加载完成后，取消透明遮罩
    	git.unmask();
	}
	
	//导出模板
 	function downExcel(){
 	
 		 f.action = "com.bos.pub.grantManage.grantManageM.flow?_eosFlowAction=downExcel";
		 f.submit();
 	}
 	//导入信息
 	function uploadExcel(){
 	
	  var excelFile = nui.get("empItemFile").getValue();
	  if (excelFile=="") {
		   nui.alert('请选择您要导入的Excel文件！');//请选择您要导入的Excel文件！
		   return;
	  }
	  var re= /.xls$/;
	  if (!re.test(excelFile))
	  {
		   nui.alert('请选择Excel文件！'); //请选择Excel文件！
		   return;
	  }
	  //增加遮罩
	  git.mask();
	  excelFile = excelFile.substr(excelFile.lastIndexOf("\\") + 1);
	  f.action = "com.bos.pub.grantManage.grantManageM.flow?_eosFlowAction=importFile";
	  f.submit();
 	}
</script>
</body>
</html>