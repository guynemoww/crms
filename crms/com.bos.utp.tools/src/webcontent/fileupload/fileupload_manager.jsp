<%@page pageEncoding="UTF-8" %>
<%@include file="/common.jsp"%>

<html>
  <head>
    <title></title>

<%
    //获取标签中使用的国际化资源信息
    String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
    String fileQueryCond = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileQueryCond");
    String fileQueryResult = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("FileuploadManager_l_title_fileQueryResult");

%>
  <script>

      function updateRecord()
      {
        var g = $id("group1");
        var frm = $name("page_form");
        if (g.getSelectLength() != 1) {
          alert('<b:message key="l_m_alert_mustAdnOnlySelectOne"/>'); //必须且只能选择一行
          return;
        }
        
        var fileId = g.getSelectParams("select_objs/fileId");
        var url = "com.bos.utp.tools.FileUploadManager.flow";
        url += "?_eosFlowAction=prepareUpdate";
        url += "&atFileupload/fileId=" + fileId;
        url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间戳的参数
        
    	showModalCenter(url, "", callBack, 520, 210, '<b:message key="l_update"/>' + '<b:message key="FileuploadManager_l_title_fileUpload"/>');//修改操作员
      }
      

      function deleteRecord()
      {
          var g = $id("group1");
          var frm = $name("page_form");
          if (g.getSelectLength() < 1) {
              alert( '<b:message key="l_m_alert_mustSelectOneOrMore"/>' );  <!-- 必须至少选择一行 -->
           return;
       }
       frm.elements["_eosFlowAction"].value = "delete";
       if( confirm( '<b:message key="FileuploadManager_msg_deleteFileConfirm"/>' ) ) {
             frm.submit();
       }
      }
      
       /*
       * 功能：新增、修改完成后回调函数－重新刷新页面
       *
       * return 
       */
      function callBack()
      {
      	var frm = $name("page_form");
      	frm.elements["_eosFlowAction"].value = "pageQuery";
      	frm.submit();
      }
      
      /*
      * 功能：实现全选复选框
      *
      * return 
      */
      function checkSelectAll()
      {
		 if ($id("checkSelect").checked)
		 {
			 selectAll("group1");
		 }else{
			 selectNone("group1");
		 }
      }
    </script>
  </head>
  <body topmargin="0" leftmargin="0">
    <e:datasource name="criteria" type="entity" path="com.primeton.das.criteria.criteraiType" />
    <e:datasource name="page" type="entity" path="com.eos.foundation.PageCond" />
    <e:datasource name="uploadFiles" type="entity" path="com.bos.utp.dataset.tools.AtFileupload" />
    <h:form name="query_form" action="com.bos.utp.tools.FileUploadManager.flow" method="post" onsubmit="return checkForm(this);">
      <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
      <w:panel id="panel1" width="100%" title="<%=fileQueryCond %>">
        <table align="center" border="0" width="100%" class="form_table">
          <tr>
            <td class="form_label" nowrap="nowrap">  <!-- 操作员姓名 -->
              <b:message key="FileuploadManager_AtFileUpload.operatorname"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td>
              <h:text property="criteria/_expr[1]/operatorname"/>
              <h:hidden property="criteria/_expr[1]/_op" value="like"/>
              <h:hidden property="criteria/_expr[1]/_likeRule" value="all"/>
            </td>
            <td class="form_label" nowrap="nowrap">    <!-- 文件分类 -->
              <b:message key="FileuploadManager_AtFileUpload.fileCatalog"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td >
              <d:select dictTypeId="ABF_FILE_CATALOG" style="width:133" nullLabel="<%=pleaseSelect %>" name="criteria/_expr[2]/fileCatalog"></d:select>

              <h:hidden property="criteria/_expr[2]/_op" value="="/>
            </td>
            <td class="form_label" nowrap="nowrap">    <!-- 文件上传日期 -->
              <b:message key="FileuploadManager_AtFileUpload.fileTime"></b:message><b:message key="l_colon"></b:message>
            </td>
            <td colspan="7">
              <h:hidden property="criteria/_expr[4]/_op" value="between" />
              <h:hidden property="criteria/_expr[4]/fileTime" value=" " />
              <h:hidden property="criteria/_expr[4]/_pattern" value="yyyy-MM-dd"/>
              <table style="border:0px;background-color:transparent" border="0" cellspacing="0" cellpadding="0">
                <tr style="border: 0px none ; background-color: transparent;">
                  <td style="border: 0px none ; background-color: transparent;">
                    <b:message key="configManager_l_label_from"></b:message>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <w:date property="criteria/_expr[4]/_min" defaultNull="true"/>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <b:message key="configManager_l_label_to"></b:message>
                  </td>
                  <td style="border: 0px none ; background-color: transparent;">
                    <w:date property="criteria/_expr[4]/_max" defaultNull="true"/>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="12" class="form_bottom">
              <input type="hidden" name="criteria/_entity" value="com.bos.utp.dataset.tools.AtFileupload">
              <b:message key="l_pageDisplay"></b:message>
              <h:text size="2" property="page/length" value="10" validateAttr="minValue=1;maxValue=100;type=integer;isNull=true" />
              <input type="hidden" name="page/begin" value="0">
              <input type="hidden" name="page/isCount" value="true">
              <input type="submit" class="button" value='<b:message key="l_query"></b:message>'>&nbsp;
              <input type="button" class="button" value='<b:message key="l_reset"></b:message>' onclick="javascript:$name('query_form').reset();">
            </td>
          </tr>
        </table>
      </w:panel>
      <h:hidden property="criteria/_orderby[1]/_sort" value="desc"/>
      <h:hidden property="criteria/_orderby[1]/_property" value="fileTime"/>
    </h:form>
    <h:form name="page_form" action="com.bos.utp.tools.FileUploadManager.flow" method="post">
      <input type="hidden" name="_eosFlowAction" value="pageQuery"/>
      <h:hiddendata property="criteria" />
      <h:hidden property="page/begin"/>
      <h:hidden property="page/length"/>
      <h:hidden property="page/count"/>
        <table align="center" border="0" width="100%" class="EOS_table">
          <tr height="4%">
			<td class="eos-panel-title" colspan="7">&nbsp;&nbsp;<%=fileQueryResult%></td>
		  </tr>
          <tr>
            <th align="center">
              <l:greaterThan property="page/size" targetValue="0" compareType="number">
	          	<input type="checkbox" id="checkSelect" name="checkSelect" title='<b:message key="l_select"></b:message>' onclick="checkSelectAll();"><!-- 选择 -->
	          </l:greaterThan>
              <b:message key="l_select"></b:message>
            </th>
            <th>         <!-- 文件名 -->
              <b:message key="FileuploadManager_AtFileUpload.fileName"></b:message>
            </th>
            <th>         <!-- 文件分类 -->
               <b:message key="FileuploadManager_AtFileUpload.fileCatalog"></b:message>
            </th>
            <th>         <!-- 文件类型 -->
              <b:message key="FileuploadManager_AtFileUpload.fileType"></b:message>
            </th>
            <th>         <!-- 操作员姓名 -->
              <b:message key="FileuploadManager_AtFileUpload.operatorname"></b:message>
            </th>
            <th>         <!-- 上传时间 -->
              <b:message key="FileuploadManager_AtFileUpload.fileTime"></b:message>
            </th>
            <th>         <!-- 操作 -->
              <b:message key="FileuploadManager_l_title_operation"></b:message>
            </th>
          </tr>
          <w:checkGroup id="group1">
            <l:iterate property="uploadFiles" id="id1">
              <tr class="<l:output evenOutput='EOS_table_row' />">
                <td align="center">
                  <w:rowCheckbox afterSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))" afterUnSelectFunc="clickCheck($id('group1'), $id('updateButton'), $id('deleteButton'))">
                    <h:param name='select_objs/fileId' iterateId='id1' property='fileId' indexed='true' />
                  </w:rowCheckbox>
                </td>
                <td>
                  <b:write iterateId="id1" property="fileName"/>
                </td>
                <td>
                  <d:write dictTypeId="ABF_FILE_CATALOG" property="fileCatalog" iterateId="id1" />
                </td>
                <td>
                  <b:write iterateId="id1" property="fileType"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="operatorname"/>
                </td>
                <td>
                  <b:write iterateId="id1" property="fileTime" formatPattern="yyyy-MM-dd"/>
                </td>
                <td align="center">
	                <l:equal iterateId="id1" property="fileSave" targetValue="1">
	                  <!--文件不存在抛出异常。--> 
	                  <%try{ %>
	                  <h:download property="filePath" iterateId="id1"><b:write iterateId="id1" property="fileName"/></h:download>
	                  <%}catch(Exception e){} %>
	                </l:equal>
	                <l:equal iterateId="id1" property="fileSave" targetValue="2">
	                  <%try{ %>
	                  <abf:blob iterateId="id1" blobProperty="content" attachment="true"></abf:blob>
	                  <%}catch(Exception e){} %>
	                </l:equal>
                </td>
              </tr>
            </l:iterate>
          </w:checkGroup>
          <tr>
            <td colspan="11" class="command_sort_area">
                <div style="float:left" >
                <input type="button" class="button" value='<b:message key="l_update"/>' onclick="updateRecord();" id="updateButton" disabled="true">
                <input type="button" class="button" value='<b:message key="l_delete"/>' onclick="deleteRecord();" id="deleteButton" disabled="true">
                </div><div style="float:right" >
                <l:equal property="page/isCount" targetValue="true">
                  <b:message key="l_total"></b:message>
                  <b:write property="page/count"/>
                  <b:message key="l_recordNO."></b:message>
                  <b:write property="page/currentPage"/>
                  <b:message key="l_page"></b:message>/
                  <b:write property="page/totalPage"/>
                  <b:message key="l_page"></b:message>
                </l:equal>
                <l:equal property="page/isCount" targetValue="false">
                  <b:message key="l_NO."></b:message>
                  <b:write property="page/currentPage"/>
                  <b:message key="l_page"></b:message>
                </l:equal>
                <input type="button" class="button" onclick="firstPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_firstPage"></b:message>'  <l:equal property="page/isFirst" targetValue="true">disabled</l:equal> >
                <input type="button" class="button" onclick="prevPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_upPage"></b:message>' <l:equal property="page/isFirst" targetValue="true">disabled</l:equal> >
                <input type="button" class="button" onclick="nextPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_nextPage"></b:message>' <l:equal property="page/isLast" targetValue="true">disabled</l:equal> >
                <l:equal property="page/isCount" targetValue="true">
                  <input type="button" class="button" onclick="lastPage('page', 'pageQuery', null, null, 'page_form');" value='<b:message key="l_lastPage"></b:message>' <l:equal property="page/isLast" targetValue="true">disabled</l:equal> >
                </l:equal>
              </div>
            </td>
          </tr>
        </table>
    </h:form>
  </body>
</html>