<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<title></title>
<%
	//重组菜单设置
    String regroupMenuSetting = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("personalsettingManager_l_tile_menu");
%>
<script type="text/javascript">
	/*
	 *保存重组菜单配置
	 */
	function saveIdentity(){		
        var myAjax = new Ajax("com.bos.utp.auth.PersonalsettingManager.updateSetting.biz");   
 		myAjax.addParam("operconfig/configname", '<b:write property="operconfig/configvalue"/>');    <!-- 使用重组菜单 -->
 		myAjax.addParam("operconfig/configtype", "menureorg");
 		myAjax.addParam("operconfig/acOperator/operatorid", "$name('identity/acOperator/operatorid').value");
 		if( $name("operconfig/configvalue").checked ) {
 		    myAjax.addParam("operconfig/configvalue", "1");
 		} else{
 		    myAjax.addParam("operconfig/configvalue", "2");
 		}
 		
 		myAjax.addParam();
 		
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
		$name('operconfig/configvalue').checked==true
	}

</script>
</head>
<body topmargin="0" leftmargin="0">
<form action="com.bos.utp.auth.application.PersonalsettingManager.flow" name="form1" method="post">
<h:hidden property="identity/acOperator/operatorid" scope="flow"/>
<h:hiddendata property="operconfig" excludeProp="configvalue" />
<input type="hidden" name="_eosFlowAction" value="updatemenureorg" />
<table align="center" border="0" width="100%" class="form_table">
	<tr height="4%">
		<td class="eos-panel-title" colspan="6">&nbsp;&nbsp;<%=regroupMenuSetting%></td>
	</tr>
	<tr>
		<td class="form_label" width="40%">
			<b:message key="personalsettingManager_l_regroupMenu"></b:message><b:message key="l_colon"></b:message>    <!-- 重组菜单 -->
		</td>
		<td>
			<l:equal property="operconfigs/configvalue" targetValue ="1" compareType="string" scope="request">
			     <input type="checkbox" name="operconfig/configvalue" checked />
			     <b:message key="personalsettingManager_l_showRegroupMenu"></b:message>    <!-- 登录后显示重组菜单 -->		      
			</l:equal>
			<l:notEqual property="operconfigs/configvalue" targetValue ="1" compareType="string" scope="request">
			     <input type="checkbox" name="operconfig/configvalue"  />
			     <b:message key="personalsettingManager_l_showRegroupMenu"></b:message>    <!-- 登录后显示重组菜单 -->		      
			</l:notEqual>		
		</td>
	</tr>
	<tr class="form_bottom">
		<td align="center" colspan="2" class="form_bottom">
		<input type="button" class="button" value='<b:message key="l_save"/>' onclick="javascript:saveIdentity();">
		<input type="button" class="button" value='<b:message key="l_reset"/>' onclick="javascript:reset();">	
		</td>
	</tr>
</table>
</form>
</body>
</html>