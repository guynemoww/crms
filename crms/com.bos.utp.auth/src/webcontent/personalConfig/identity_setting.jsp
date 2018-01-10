<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%
	//请选择
	String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("l_pleaseSelect");
	//身份设置
	String indentitySetting = com.eos.foundation.eoscommon.ResourcesMessageUtil
			.getI18nResourceMessage("personalsettingManager_l_tile_indentity");
%>
<html>
<head>
<title></title>
</head>
<body topmargin="0" leftmargin="0">
<form action="com.bos.utp.rights.identitySetting.flow"
	name="form1" method="post">
	<h:hiddendata property="operconfig" excludeProp="configvalue" />
	<h:hidden property="identity/acOperator/operatorid" scope="flow"/>
	<input type="hidden" name="_eosFlowAction" value="update" />
	<table align="center" border="0" width="100%" class="form_table">
		<tr height="4%">
			<td class="eos-panel-title" colspan="4">&nbsp;&nbsp;<%=indentitySetting%></td>
	    </tr>
		<tr id="identityset">
			<td colspan="4" >
			    <fieldset id="AdvOption">
				      <legend  onclick="isDisplay()">
				          <h:checkbox property="operconfig/configvalue" onclick="resignidnetity()" /><b:message key="personalsettingManager_l_chooseIndentity"></b:message> <!-- 使用身份 -->
				      </legend>
				      <div id="AdvOptionDiv" style="display: none;" >
				      <b:message key="personalsettingManager_l_default_indentity"></b:message>
				                       <b:message key="l_colon"></b:message> <!-- 默认身份 -->
				          <h:select property="curidentity/identityid" style="width:133">
								<option value="" selected="selected" ><%=pleaseSelect%></option>
								<h:options property="identityList" labelField="identityname"
									valueField="identityid" />
					      </h:select>
				      </div>
				      <br>
				</fieldset>
			</td>
		</tr>
		<tr class="form_bottom">
			<td align="center" colspan="4" class="form_bottom"><input
				type="button" value='<b:message key="l_save"/>'
				onclick="javascript:saveIdentity();"> <input type="button" class="button"
				value='<b:message key="l_reset"/>' onclick="javascript:reset();">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
<script type="text/javascript" language="javascript">
		/*
		 * 设置身份下拉框为dissble or not
		 */
		function resignidnetity()
		{
			if($name('operconfig/configvalue').checked==true)
			{
				$name('curidentity/identityid').disabled=false;		
			}
			else
			{	
				$name('curidentity/identityid').disabled=true;
			}	
		}
</script>
<script type="text/javascript">
	/*
	 *保存身份配置
	 */
	function saveIdentity(){		
        var myAjax = new Ajax("com.bos.utp.auth.PersonalsettingManager.updateSetting.biz");     
 		myAjax.addParam("operconfig/configname", '<b:write property="operconfig/configvalue"/>');   <!-- 使用身份配置 -->
 		myAjax.addParam("operconfig/configtype", "identity");
 		myAjax.addParam("operconfig/acOperator/operatorid", "$name('identity/acOperator/operatorid').value");
 		try{
 		    myAjax.addParam("operconfig/configvalue", $name('curidentity/identityid').value);
 		} catch(e) {
 		    myAjax.addParam("operconfig/configvalue", "");
 		}
 		
 		myAjax.submit();
        var returnNode = myAjax.getResponseXMLDom();
        if( returnNode ) {
            if( myAjax.getValue("root/data/retCode") == 1 ) {
                alert( '<b:message key="l_m_save_success"/>' );  <!--  保存成功 -->
        		window.close();
            } else {
                alert( '<b:message key="l_m_save_fail"/>' );         <!-- 保存失败 --> 
            }
        } else {
            alert( '<b:message key="l_m_save_fail"/>' );         <!-- 保存失败 --> 
        }
	
	}
	
	function initIdentity() {
		if( '<b:size property="operconfigs"/>' > '0' ) {
		    $name("operconfig/configvalue").checked;
		    $name('curidentity/identityid').disabled=false;
		    $name('curidentity/identityid').value = '<b:write property="operconfigs/configvalue" />';
		}   else  {
		    $name('curidentity/identityid').value = "";
		    $name('curidentity/identityid').disabled=true;
		}
	}
	
	function isDisplay() {
	   
	   if( $name("operconfig/configvalue").checked ) {
           document.getElementById('AdvOptionDiv').style.display='';
           document.getElementById('AdvOption').style.border='1px solid #CCCCCC';
       } else {
           document.getElementById('AdvOptionDiv').style.display='none';
           document.getElementById('AdvOption').style.border='none';
       }
   }
   
   /*
    * 自定义初始化按钮样式
    */
   function custInit(){  
	   $name('curidentity/identityid').disabled = true;
	   initIdentity();
   } 
</script>
