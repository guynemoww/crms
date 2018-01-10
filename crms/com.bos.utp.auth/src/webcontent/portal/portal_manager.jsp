<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<html>
<head>
<title>Portal<b:message key="portalManager_l_title_resourceManager"></b:message> </title>

<%
   //请选择
   String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
   //Portal资源查询
   String resourceQuery = "Portal" + com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("portalManager_l_title_resourceQuery");
   //类型
   String type = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("portalManager_atPortalreslist.restype");
   //资源
   String res = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("portalManager_atPortalreslist.resid");
   //功能资源查询
   String funcRes = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("portalManager_l_title_funcResourceQuery");
   //功能名称
   String funcname = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("portalManager_AcFunction.funcname");
   //功能编号
   String funccode = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("portalManager_AcFunction.funccode");
   //新增
   String add = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_add");
 %>

</head>
<body leftmargin="0" topmargin="0">
  <table cellpadding="0">
    <tr>
      <td valign="top">
	    <w:panel id="portalPanel" width="390" title="<%=resourceQuery %>">
	        <table align="center" border="0" width="100%" class="form_table">
	            <h:form id="portalForm">
	            <tr>
	                <td class="form_label">   <!-- 类型 -->
		               <b:message key="portalManager_atPortalreslist.restype"></b:message><b:message key="l_colon"></b:message>
		            </td>
		            <td>
		                 <d:select dictTypeId="ABF_PORTALTYPE" style="width:133" property="atPortalreslist/restype" nullLabel="<%=pleaseSelect %>"></d:select>
		            </td>
		            <%-- td class="form_label">   <!-- 资源 -->
		               <b:message key="portalManager_atPortalreslist.resid"></b:message>
		            </td>
		            <td class="form_label">
		                 <h:text property="atPortalreslist/resid" />
		            </td  --%>
		            <td align="center">
		                 <input type="button" class="button" value='<b:message key="l_query"/>' onclick="javascript:querySubmit( $id('portalDatacell') );"/>
		            </td>
	            </tr>
	            </h:form>
	        </table>
	    </w:panel>
	    <r:datacell id="portalDatacell"
		    queryAction="com.bos.utp.auth.PortalManager.queryAllPortalresList.biz"
		    xpath="portals"
		    width="390"
		    height="380"
		    pageSize="10"
		    pageSizeList="10,20,30" submitAction="com.bos.utp.auth.PortalManager.savePortalreslist.biz"  paramFormId="portalForm">
		    <r:field fieldName="portalid" width="0"></r:field>
		    <r:field fieldName="restype" editId="text2" label="<%=type %>" width="180" defaultValue="0">
		        <d:select property="restype" dictTypeId="ABF_PORTALTYPE" >
		        </d:select>
		    </r:field>
		    <r:field fieldName="resid" width="0"></r:field>
		    <r:field fieldName="funcname" editId="text1" label="<%=res %>" width="190">
		        <h:text id="text1" maxlength="255" validateAttr="allowNull=false"/>
		    </r:field>

		    <r:toolbar tools="nav,pagesize,edit" location="bottom" />
		</r:datacell>
	   </td>
	   <td valign="top">
	       <w:panel id="function" width="390" title="<%=funcRes %>">
	        <table align="center" border="0" width="100%" class="form_table">
	            <h:form id="funcForm">
	            <tr>
		            <td class="form_label">   <!-- 功能名称 -->
		               <b:message key="portalManager_AcFunction.funcname"></b:message><b:message key="l_colon"></b:message>
		            </td>
		            <td>
		                 <h:text property="func/funcname"/>
		            </td>
		            <td align="center">
		                 <input type="button" class="button" value='<b:message key="l_query"/>' onclick="javascript:querySubmit( $id('functionDatacell') );"/>
		            </td>
	            </tr>
	            </h:form>
	        </table>
	        </w:panel>
	        <r:datacell id="functionDatacell"
		    queryAction="com.bos.utp.auth.PortalManager.queryAllFunction.biz"
		    xpath="funcs"
		    width="390"
		    height="380"
		    pageSize="10"
		    pageSizeList="10,20,30"  paramFormId="funcForm">
		    <r:field fieldName="funccode"  label="<%=funccode %>" width="180">

		    </r:field>
		    <r:field fieldName="funcname"  label="<%=funcname %>" width="190" >

		    </r:field>
		    <r:toolbar tools="nav,pagesize,custom"  location="bottom" />
		</r:datacell>

	   </td>
	 </tr>

   </table>
</body>
</html>

<script language="javascript">
	    var datacell = null;
	    var funcDatalcell = null;

	     /*
	      *  单元格编辑之前的接口，单击资源列时，如果类型为功能则不允许修改
	      */
	    function initDatacellBeforeEdit() {
		    datacell.beforeEdit = function() {
		        var activityCell = datacell.getActiveCell();
		        if( activityCell.cellIndex == 3 ) {
		            var entity = datacell.cloneEntity();
			        var restype = entity.getProperty( "restype" );

			        if( restype == "1" ) {
			            return false;
			        } else {
			            return true;
			        }
		        } else {
		            return true;
		        }
		    }
	    }
	    /*
	     *  自定义类型，编辑后应该给resid隐藏字段赋值
	     */
	    function initDatacellAfterEdit() {
		    datacell.afterEdit = function() {
		        var activityCell = datacell.getActiveCell();
		        if( activityCell.cellIndex == 3 ) {
		            var row = datacell.getActiveRow();
				    var entity = datacell.getEntity(row);
				    entity.setProperty("resid", datacell.getCellValue(activityCell) );
				    datacell.refreshRow(row);
		        }
		    }
	    }
	    /*
	     *  新增功能资源
	     */
	    function addRecord() {
	        var obj = funcDatalcell.copyRow();
	        var entity = new Entity("portals");
	        entity.setPropertyByFieldName("resid", obj.getPropertyByFieldName("funccode") );
	        //如果从功能列表中选取资源，资源类型为1，表示是功能资源，0表示自定义资源
	        entity.setPropertyByFieldName("restype", "1");
	        entity.setPropertyByFieldName("funcname", obj.getPropertyByFieldName("funcname"));
	        datacell.insertRow(entity);

	        datacell.isModefied = true;

	        funcDatalcell.deleteRow();
	        funcDatalcell.isModefied = false;
	    }

	    /*
	     * 提交之后获取逻辑流返回值
	     */
	    function initDatacellAfterSubmit() {
		    datacell.afterSubmit = function( ajax ) {
		        var retCode = ajax.getValue("root/data/retCode");
		        if( retCode == "1" ) {
		            alert( '<b:message key="l_m_save_success"/>' );  <!--  保存成功 -->
		        } else {
		            alert( '<b:message key="l_m_save_fail"/>' );         <!-- 保存失败 -->
		        }
		        //保存后，再刷新功能列表
		        querySubmit( funcDatalcell );
		    }
	    }
	    /*
         *  查询时加载datacell
         */
	    function querySubmit( obj ) {
	        obj.reload();
	    }
	    /*
         *  自定义初始化按钮样式
         */
	    function custInit(){
		    datacell = $id("portalDatacell");
	        funcDatalcell = $id("functionDatacell");
		    funcDatalcell.setCustomTool("<input type='button' value='<%=add %>' onclick='addRecord();'>");
		    initDatacellAfterSubmit();
		    initDatacellAfterEdit();
		    initDatacellBeforeEdit();
	    }
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit);
</script>