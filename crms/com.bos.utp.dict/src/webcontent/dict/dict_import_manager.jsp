<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<link id="css_skin" rel="stylesheet" type="text/css"
	  href="<%=contextPath%>/utp/tools/skins/skin1/style.css" />
<html>
<!-- 
  - Author(s): JFree
  - Date: 2013-03-13 20:40:12
  - Description:
-->
<head>
	<title>文件上传控件（HTML）</title>
	<link href="../demo.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/javascript">
//var retcode = '<b:write property="retCode"/>';
//    if(retcode!=''){
//        if(retcode-0==-1){
//        nui.alert('数据字典导入失败');//<b:message key="DictManager_l_dict_import"/><b:message key="DictManager_l_failed"/>
//        }else{
//        nui.alert('数据字典导入成功');//<b:message key="DictManager_m_import_succeed"></b:message>
//        }
//    }
</script>

<body>
<br />
<table align="center" border="0" width="100%" class="form_table">

	<form id="form1" action="com.bos.utp.dict.DictManager.flow?_eosFlowAction=importDictExcel" method="post" enctype="multipart/form-data">
	<tr>
		<td align="center" width="30%">
	       请选择您要导入的Excel文件！<input class="nui-htmlfile" id="Fdata" name="Fdata" limitType="*.xls" />
	    </td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td colSpan="4" align="center">
	    	<a class="nui-button"  iconCls="icon-upload" onclick="dictAllImport();" />上传</a>
	    </td>
	</tr>
	<tr>
	</tr>
	</form>
</table>
<%
Object ret = request.getAttribute("retCode");
if(ret != null){
	if(ret.equals("-1")){
		out.println("<div align='center' class='description'><h3>数据字典导入失败</h3></div>");
	}else{
		out.println("<div align='center' class='description'><h3>数据字典导入成功</h3></div>");
	}
}
 %>
</body>
<script language="JavaScript" type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	
	function dictAllImport(){
		if(!nui.get("Fdata").value){
			nui.alert("请选择文件");
			return;
		}
		if(/\.xls?$/g.test(nui.get("Fdata").value) == false){
			nui.alert("请选择.xls文件");//也可是xlsx格式
			return;
		}
		var frm = document.getElementById("form1");
			frm.submit();
	}
</script>	
</html>