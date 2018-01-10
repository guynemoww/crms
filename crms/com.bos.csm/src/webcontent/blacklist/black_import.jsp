<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<!-- 
  - Author(s): Administrator
  - Date: 2014-03-07 11:26:35
  - Description:
-->
<head>
<title>黑名单导入</title>
</head>
  <form id="importForm" action="com.bos.csm.blacklist.black_import.flow" method="post" enctype="multipart/form-data" >
  <input id="entity" name="item/entity" value="com.bos.dataset.csm.TbCsmBlackList" class="nui-hidden" />
  <div class="nui-dynpanel" columns="2">
  	
    	<label>请选择您要导入的Excel文件</label>
     	<input  class="nui-htmlfile" id="empItemFile" name="empItemFile" size="100"  />
 </div>
 <div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
  	   <a class="nui-button"  onclick="downExcel" type="submit" />下载模板</a>
    <%--   <a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
       <a class="nui-button"  onclick="empImport" />导入信息</a>
        </div>
  </form>
         
</body>
<%
Object str = request.getAttribute("msg");

	String str1 =(String)str;
	if(str1 !=null){
		String[] strs = str1.split(";");
		for(int i = 0 ; i< strs.length;i++){
			out.println("<div align='center' class='description' style='color:red;'><h3>"+strs[i]+"</h3></div>");
		}
	}
		


 %>
<script language="javascript">
	nui.parse();
<%--  var importCd=nui.get("importCd").getValue();--%>
 	var f = document.getElementById("importForm");
 	//下载模板
 	function downExcel(){
 <%--	 var importCd=nui.get("importCd").getValue();
     if(importCd==""){
		   alert("请选择类型");
		   return;
		  }--%>
		   f.action = "com.bos.csm.blacklist.black_import.flow?_eosFlowAction=downExcel";
		   f.submit();
 	}
 	//导出
<%--    function exportEmp()
    {
     var importCd=nui.get("importCd").getValue();
     if(importCd==""){
		   alert("请选择类型");
		   return;
		  }
		  if(importCd=="01"){//com.primeton.example.excel.sampledataset.OmEmp
			 var entity=nui.get("entity").setValue("com.bos.utp.dataset.organization.OmEmployee");
		  }
		     f.action = "com.bos.csm.blacklist.black_import.flow?_eosFlowAction=exportFile";
		     f.submit();
    }--%>
 
  /*
  * 功能：导入表内
  */
 function empImport(){
<%--  var importCd=nui.get("importCd").getValue();--%>
<%-- if(importCd==""){
		   alert("请选择类型");
		   return;
		  }--%>
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

<%--    if(importCd=="01"){--%>
			 var entity=nui.get("entity").setValue("com.bos.dataset.csm.TbCsmBlackList");
	<%--	  }--%>
  f.action = "com.bos.csm.blacklist.black_import.flow?_eosFlowAction=importFile";
  f.submit();
 }
</script>
<%@page import="com.itextpdf.text.log.SysoCounter"%>
</html>