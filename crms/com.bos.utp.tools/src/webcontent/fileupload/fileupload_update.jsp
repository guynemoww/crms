<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common.jsp"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><b:message key="OperatorManager_l_operator"></b:message><b:message key="OperatorManager_l_manager"></b:message></title><!-- 操作员管理 -->
    <script language="javascript">
		
		/*
		 * 功能：保存操作员信息
		 *
		 * return 保存成败标志
		 */
		function fileUpdate()
		{
			var frm = $name("data_form");
	        
	        //表单验证
	        if(!checkForm(frm)) {
	            return;
	        }
			
		    var myAjax = new Ajax("com.bos.utp.tools.FileUploadManager.updateFile.biz");
		    //增加参数
		    myAjax.addParam("atFileupload/fileId",$name("atFileupload/fileId").value);
		    myAjax.addParam("atFileupload/fileName",$name("atFileupload/fileName").value);
		    myAjax.addParam("atFileupload/fileCatalog",$name("atFileupload/fileCatalog").value);
		    
		    //开始调用
		    myAjax.submit();
		    
		    //取得调用后的结果(xml对象)
		    var returnNode =myAjax.getResponseXMLDom();
		    
		    var reCode;
		    if(returnNode)
		    {
		    	//获取指定的节点值
		    	reCode = myAjax.getValue("/root/data/result");
		    }
		    
		    //判断刷新业务字典信息成败
		    if(reCode == 1)
		    {
		    	alert('<b:message key="l_m_save_success"></b:message>'); //保存成功。
	    		window.close();
		    }else{
	    		alert('<b:message key="FileuploadManager_msg_update_failed"/>');
	    		return false;
	    	}
		}
		
	</script>
  </head>
  <body leftmargin="0" rightmargin="0" topmargin="0" class="eos-panel-table">
    <e:datasource name="atFileupload" type="entity" path="com.bos.utp.dataset.tools.AtFileupload" />
    <h:form name="data_form" action="com.bos.utp.tools.FileUploadManager.flow" method="post" onsubmit="return checkForm(this);">
      <h:hidden property="atFileupload/fileId"/>
      <input type="hidden" name="_eosFlowAction" value="update">
        <table align="center" border="0" width="516" class="form_table">
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileName"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <h:text property="atFileupload/fileName" validateAttr="maxLength=64;allowNull=false;"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileCatalog"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <d:select dictTypeId="ABF_FILE_CATALOG" property="atFileupload/fileCatalog" style="width:133"/>
            </td>
          </tr>
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileSize"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write property="atFileupload/fileSize"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileType"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write property="atFileupload/fileType"/>
            </td>
          </tr>
          <tr>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileSave"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <d:write dictTypeId="ABF_FILE_SAVE" property="atFileupload/fileSave"/>
            </td>
            <td class="form_label">
              &nbsp;<b:message key="FileuploadManager_AtFileUpload.fileTime"/><b:message key="l_colon"/>
            </td>
            <td colspan="1">
              <b:write  property="atFileupload/fileTime" formatPattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6">
              <input type="button" class="button" value='<b:message key="l_save"></b:message>' onclick="fileUpdate();">  <!-- 保存 -->
              <input type="button" class="button" value='<b:message key="l_close"></b:message>' onclick="javascript:window.close();"> <!-- 关闭 -->
            </td>
          </tr>
        </table>
    </h:form>
  </body>
</html>