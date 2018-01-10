<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%
	//获取标签中使用的国际化资源信息
	String excelMgr_l_impMgr  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("excelMgr_l_impMgr");
%>
<html>
<head>
<title>
	<b:message key="DictManager_l_dict_import"></b:message>
	<b:message key="OperatorManager_l_manager"></b:message>
</title>
</head>
<script language="JavaScript" type="text/javascript">

	/*
	 * 功能：导入Excel
	 */
	function dictImport()
	{
		var frm = $id("importForm");
	        
	    //表单验证
	    if(!checkForm(frm)) {
	    	return;
	    }
		
		var excelFile = $name("dictItemFile").value;
		if (excelFile=="") {
			alert('<b:message key="DictManager_m_import_excel"></b:message>');//请选择您要导入的Excel文件！
			return;
		}
		var re= /.xls$/;
		if (!re.test(excelFile))
		{
			alert('<b:message key="DictManager_m_excel_file"></b:message>'); //请选择Excel文件！
			return;
		}

		excelFile = excelFile.substr(excelFile.lastIndexOf("\\") + 1);
		frm.action = "com.bos.utp.tools.ExcelMgr.flow?_eosFlowAction=importExcel&excelFile=" + excelFile;
		frm.submit();
	}     
     <l:present property="retCode">	
		<l:equal property="retCode" targetValue="1">
      		alert('<b:message key="excelMgr_l_succeed"/>');//数据导入成功!
		</l:equal>
		<l:equal property="retCode" targetValue="0">
      		alert('<b:message key="excelMgr_l_failed"/>');//数据导入失败!
		</l:equal>	
	</l:present>
</script>
<body leftmargin="0" rightmargin="0" topmargin="0">
	<table align="center" border="0" width="100%" class="form_table">
		<h:form id="importForm" action="com.bos.utp.tools.ExcelMgr.flow?_eosFlowAction=importExcel" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
		<tr height="4%">
			<td class="eos-panel-title" colspan="6">&nbsp;&nbsp;<%=excelMgr_l_impMgr%></td><!-- 通用Excel导入导出 -->
	    </tr>
		<tr>
			<td class="form_label" align="center">
				<b:message key="excelMgr_l_dataObj"></b:message><b:message key="l_colon"></b:message>
			</td>
			<td>
				<h:text property="dataObjName" validateAttr="allowNull=false;" size="80"/>
			</td>
		</tr>
		 <tr>
			<td class="form_label" align="center">
				<b:message key="excelMgr_l_fileName"></b:message><b:message key="l_colon"></b:message>
			</td>
			<td>
				<input type="file" name="dictItemFile" size="60">
			</td>
		</tr>
		<tr>
			<td colSpan="4" align="center">
				<input type="button" class="button" value='<b:message key="DictManager_l_dict_import_button"></b:message>' onclick="dictImport();"/><!-- 导入 -->
				<input type="reset" class="button" value='<b:message key="DictManager_l_reset"></b:message>'/><!-- 重置 -->
			</td>
		 </tr>
		</h:form>
	</table>
</body>
</html>