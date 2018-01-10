<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): ch
  - Date: 2014-08-25 14:08:34
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>大宗商品--粮油及农副产品</title>
<body>
</head>
  <form id="importForm" action="com.bos.grt.grtImportExcel.ImportOilagricultur.flow" method="post" enctype="multipart/form-data" >
  	<input name="item/suretyId" class="nui-hidden" value="<%=request.getParameter("suretyId") %>"/>
  	<input name="item/sortType" id="sortType" class="nui-hidden" value="<%=request.getParameter("sortType") %>"/>
  	<input name="item/parentSortType" id="parentSortType" class="nui-hidden" value="<%=request.getParameter("parentSortType") %>"/>

  	<div class="nui-dynpanel" columns="2">
    	<label>请选择您要导入的Excel文件</label>
     	<input  class="nui-htmlfile" id="empItemFile" name="empItemFile" size="100"  />
  	</div>
  	<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
  		<a class="nui-button"  onclick="downExcel"/>下载模板</a>
        <a class="nui-button"  onclick="empImport"/>导入信息</a>
  	</div>
  	
 </form>      
</body>
	<%
	Object str = request.getAttribute("msg");
	Object exc=request.getAttribute("exc");
	String exc1=(String)exc;
	String str1 =(String)str;
	if(str1 !=null){
		String[] strs = str1.split(";");
		for(int i = 0 ; i< strs.length;i++){
			out.println("<div align='center' class='description' style='color:red;'><h3>"+strs[i]+"</h3></div>");
		}
	}
	if(exc1 !=null){
		String[] excs = exc1.split(";");
		for(int i = 0 ; i< excs.length;i++){
			out.println("<div align='center' class='description' style='color:red;'><h3>"+excs[i].split(":")[1]+"</h3></div>");
		}
	}
 	%>
<script type="text/javascript">
	nui.parse();
 	var f = document.getElementById("importForm");
 	
 	//下载模板
 	function downExcel(){
 		f.action = "com.bos.grt.grtImportExcel.ImportOilagricultur.flow?_eosFlowAction=downExcel";
	 	f.submit();
 	}
  /*
  * 功能：导入表内
  */
 function empImport(){
 	 var excelFile = nui.get("empItemFile").getValue();
 	 if (excelFile=="") {
    	 nui.alert('请选择您要导入的Excel文件！');//请选择您要导入的Excel文件！
    	 return;
  	}
  	var re= /.xls$/;
  	if (!re.test(excelFile)){
    	 nui.alert('请选择Excel文件！'); //请选择Excel文件！
    	 return;
  	}
  
  	f.action = "com.bos.grt.grtImportExcel.ImportOilagricultur.flow?_eosFlowAction=importFile";
  	f.submit();
 }
 
 function CloseWindow(action) {            
	window.CloseOwnerWindow("ok");
 }
</script>
</html>