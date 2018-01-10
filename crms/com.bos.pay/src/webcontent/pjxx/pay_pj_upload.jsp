<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): ch
  - Date: 2014-08-25 14:08:34
  - Description:
-->
<head>
<%@page import="commonj.sdo.DataObject"%>
<%@include file="/common/nui/common.jsp" %>
<title>银行承兑汇票导入</title>
<body>
</head>
  <form id="importForm"  method="post" enctype="multipart/form-data" >
  	<input name="item/loanId" id="loanId" class="nui-hidden"/>

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
	DataObject item = (DataObject)request.getAttribute("item"); 
	String loanId = "";
	if(null !=item){
		loanId = item.getString("loanId");
	}
 	%>
<script type="text/javascript">
	nui.parse();
 	var f = document.getElementById("importForm");
 	
 	var loanId = "<%=request.getParameter("loanId") %>";
 	if(loanId == null || loanId == '' || loanId == 'null'){
 		loanId = "<%=loanId %>";
 	}
 	nui.get("loanId").setValue(loanId);
 	//下载模板
 	function downExcel(){
 		f.action = "com.bos.pjxx.ImportPayPjxx.flow?_eosFlowAction=downExcel";
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
  
  	f.action = "com.bos.pjxx.ImportPayPjxx.flow?_eosFlowAction=importFile";
  	f.submit();
 }
 
 function CloseWindow(action) {            
	window.CloseOwnerWindow("ok");
 }
</script>
</html>