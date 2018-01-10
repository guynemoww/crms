<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<title><b:message key="DictManager_l_dict_import"></b:message><b:message key="OperatorManager_l_manager"></b:message></title><!-- 业务字典导入管理 -->
</head>
<script language="JavaScript" type="text/javascript">
	
	/*
	 * 功能：导入Excel到业务字典项内
	 */
	function dictAllImport(){
		var frm = $id("importAllForm");
		var excelFile = $name("dictFile").value;
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
		frm.submit();
	}
    var retcode = '<b:write property="retCode"/>';
    if(retcode!=''){
        if(retcode-0==-1){
        alert('<b:message key="DictManager_l_dict_import"/><b:message key="DictManager_l_failed"/>');
        }else{
        alert('<b:message key="DictManager_m_import_succeed"></b:message>');
        }
    }
</script>
<body leftmargin="0" rightmargin="0" topmargin="0">
	<!-- 业务字典导入 -->
	<table align="center" border="0" width="100%" class="form_table">
		<tr>
			<td  colSpan="4" class="eos-panel-title">
				<b:message key="DictManager_m_dict_item_excel"></b:message><b:message key="DictManager_l_dict_import_button"></b:message><!-- 业务字典Excel文件导入 -->
			</td>
		</tr>
		<h:form id="importAllForm" action="com.bos.utp.tools.DictManager.flow?_eosFlowAction=importDictExcel" method="post" enctype="multipart/form-data" >
		<tr>
			<td class="form_label" align="center" width="30%">
				<b:message key="DictManager_m_import_excel"></b:message><!-- 请选择您要导入的Excel文件！ -->
			</td>
			<td class="form_label" style="text-align: left;">
				<input type="file" name="dictFile" size="60">
			</td>
		</tr>
		<tr>
			<td colSpan="4" align="center">
				<input type="button" value='<b:message key="DictManager_l_dict_import_button"></b:message>' onclick="dictAllImport();"/><!-- 导入 -->
				<input type="reset" value='<b:message key="DictManager_l_reset"/>'/> <!-- 重置 -->
			</td>
		 </tr>
		</h:form>
	</table>
</body>
</html>

