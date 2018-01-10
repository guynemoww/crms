<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<%@page import="com.eos.system.utility.StringUtil"%>
<html>
<head>
<title></title>
<%
	//获取标签中使用的国际化资源信息
    String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");

        //对根对象中获得对象
    String skins= XpathUtil.getStringValue("r",pageContext,"acOperator/menutype");
    String[] layoutstyle = {ABFConfigKey.SKIN_DEFAULT_LAYOUT.getConfigValue(),ABFConfigKey.SKIN_DEFAULT_STYLE.getConfigValue()};
				
	if (StringUtil.isNotNullAndBlank(skins)) {
		String[] tmp = skins.split(",");
		if (tmp.length > 0) {
			layoutstyle[0]=tmp[0];
		} 
		if (tmp.length > 1) {
			layoutstyle[1]=tmp[1];
		}
	}
%>
<b:set name="layout" value="<%=layoutstyle[0] %>"/>
<b:set name="style"  value="<%=layoutstyle[1] %>"/>
<script type="text/javascript">
	/*
	 *保存页面布局配置
	 */
	function saveIdentity(){		
        var myAjax = new Ajax("com.bos.utp.auth.PersonalsettingManager.updateMenuType.biz");  
        myAjax.addParam("acOperator/operatorid", $name('identity/acOperator/operatorid').value);
 		myAjax.addParam("acOperator/menutype", $name("layout").value+","+$name("style").value);
		
 		//myAjax.addParam();
 		
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
	
</script>
</head>
<body topmargin="0" leftmargin="0">
<form action="com.bos.utp.auth.application.PersonalsettingManager.flow" name="form1" method="post">
<h:hiddendata property="operconfig" excludeProp="configvalue" />
<h:hidden property="identity/acOperator/operatorid" scope="flow"/>
<input type="hidden" name="_eosFlowAction" value="update" />
<table align="center" border="0" width="100%" class="form_table">
	<tr height="4%">
		<td class="eos-panel-title" colspan="4">&nbsp;&nbsp;<b:message key="personalsettingManager_l_skinlayout" /></td><!--菜单布局-->
	</tr>
	<tr>
		<td class="form_label" width="40%">
			<b:message key="personalsettingManager_l_default_layout"></b:message><b:message key="l_colon"></b:message>  <!-- 默认布局 -->
		</td>
		<td>
			<d:select dictTypeId="ABF_SKINLAYOUT" property="layout" nullLabel="<%=pleaseSelect %>" style="width:130"/>
		</td>
	</tr>
	<tr height="4%">
		<td class="eos-panel-title" colspan="4">&nbsp;&nbsp;<b:message key="personalsettingManager_l_skinstyle" /></td><!--颜色风格-->
	</tr>
	<tr>
		<td class="form_label" width="40%">
			<b:message key="personalsettingManager_l_default_style"></b:message><b:message key="l_colon"></b:message>  <!-- 默认样式 -->
		</td>
		<td>
			<d:select dictTypeId="ABF_SKINSTYLE" property="style" nullLabel="<%=pleaseSelect %>" style="width:130"/>
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