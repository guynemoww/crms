<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<!-- 
  - Author(s): Administrator
  - Date: 2014-03-07 11:26:35
  - Description:
-->
<head>
<title>文件上传控件（HTML）</title>
</head>
  <form id="importForm" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data" >
  <input id="entity" name="item/entity" value="com.bos.utp.dataset.organization.OmOrganization" class="nui-hidden" />
  <div class="nui-dynpanel" columns="2">
  		<label>导入导出类别：</label>
		<input name="importCd" id="importCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD3944"  emptyText="--请选择--" />
    	<label>请选择您要导入的Excel文件</label>
     	<input  class="nui-htmlfile" id="empItemFile" name="empItemFile" size="100"  />
 </div>
 <div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
  	   <a class="nui-button"  onclick="downExcel" type="submit" />下载模板</a>
       <a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>
       <a class="nui-button"  onclick="empImport" />导入信息</a>
        </div>
  </form>
         
</body>
<%
Object ret = request.getAttribute("retCode");
if(ret != null){
	if(ret.equals("0")){
		out.println("<div align='center' class='description'><h3>导入失败</h3></div>");
	}else{
		out.println("<div align='center' class='description'><h3>导入成功</h3></div>");
	}
}
 %>
<script language="javascript">
	nui.parse();
  var importCd=nui.get("importCd").getValue();
 	var f = document.getElementById("importForm");
 	//下载模板
 	function downExcel(){
 	 var importCd=nui.get("importCd").getValue();
     if(importCd==""){
		   alert("请选择类型");
		   return;
		  }
		   f.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=downExcel";
		   f.submit();
 	}
 	//导出
    function exportEmp()
    {
     var importCd=nui.get("importCd").getValue();
     if(importCd==""){
		   alert("请选择类型");
		   return;
		  }
		  if(importCd=="01"){//com.primeton.example.excel.sampledataset.OmEmp
			 var entity=nui.get("entity").setValue("com.bos.utp.dataset.organization.OmEmployeeQueryExp");
		  }
		     f.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile";
		     f.submit();
    }
 
  /*
  * 功能：导入表内
  */
 function empImport(){
  var importCd=nui.get("importCd").getValue();
 if(importCd==""){
		   alert("请选择类型");
		   return;
		  }
  var excelFile = nui.get("empItemFile").getValue();
  if (excelFile=="") {
   alert('请选择您要导入的Excel文件！');//请选择您要导入的Excel文件！
   return;
  }
  var re= /.xls$/;
  if (!re.test(excelFile))
  {
   alert('请选择Excel文件！'); //请选择Excel文件！
   return;
  }
  excelFile = excelFile.substr(excelFile.lastIndexOf("\\") + 1);
  alert(excelFile);
    if(importCd=="01"){
			 var entity=nui.get("entity").setValue("com.bos.utp.dataset.organization.OmEmployee");
		  }
  f.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=importFile";
  f.submit();
 }
</script>
</html>