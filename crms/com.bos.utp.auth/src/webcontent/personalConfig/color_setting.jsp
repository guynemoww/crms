<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%
	String picker_maskPngStr = request.getContextPath()+"auth/personalConfig/javascript/picker_mask.png";
%>
<html>
<head>
<title></title>
<%
	//请选择颜色
    String selectColor = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("personalsettingManager_l_pleaseChooseColor");
    //颜色配置
    String color = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("personalsettingManager_l_tile_color");
%>
<!-- YUI Dependencies -->  

<h:script src="auth/personalConfig/javascript/utilities.js"></h:script>
<h:script src="auth/personalConfig/javascript/slider-min.js"></h:script> 
 
<!-- Color Picker source files for CSS and JavaScript --> 
<h:css href='auth/personalConfig/css/colorpicker.css'></h:css>
<h:script src="auth/personalConfig/javascript/colorpicker-min.js"></h:script>
<h:css href='auth/personalConfig/colorpicker/windowfiles/dhtmlwindow.css'></h:css>
<h:script src="auth/personalConfig/colorpicker/ddcolorpicker.js"></h:script>

<script>
/***********************************************
* Color PIcker Widget (YUI Based)- By Dynamic Drive DHTML code library (http://www.dynamicdrive.com)
* Requires: YUI Color Picker and DHTML Window Widget
***********************************************/
</script>


<style type="text/css">

* html .yui-picker-bg{ /*Requires CSS. Do not edit/ remove*/
filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=picker_maskPngStr %>',sizingMethod='scale');
}

/*Style used in demos below. Edit if desired*/

.colorpreview{ /*CSS for sample Preview Control*/
border: 1px solid black;
padding: 1px 10px;
cursor: hand;
cursor: pointer;
}

form div{
margin-bottom: 5px;
}

</style>
<body topmargin="0" leftmargin="0">
<form action="" name="form1" method="post">
<h:hiddendata property="operconfig" excludeProp="configvalue" />
<h:hidden property="identity/acOperator/operatorid" scope="flow"/>
  <table align="center" border="0" width="100%" class="form_table">
    <tr height="4%">
		<td class="eos-panel-title" colspan="6">&nbsp;&nbsp;<%=color%></td>
	</tr>
    <tr>
      <td class="form_label" colspan="2" width="40%">
        <b:message key="personalsettingManager_l_default_color"></b:message><b:message key="l_colon"></b:message>    <!-- 默认颜色 -->
      </td>
      <td colspan="2">
        <h:text id="field1" property="operconfigs/configvalue" />&nbsp;<span id="control1" class="colorpreview">&nbsp;</span>
      </td>
    </tr>
    <tr class="form_bottom">
      <td align="center" colspan="4" class="form_bottom">
	  <input type="button" class="button" value='<b:message key="l_save"/>' onclick="javascript:saveIdentity();">
	  <input type="button" class="button" value='<b:message key="l_reset"/>' onclick="javascript:reset();">
	</td>
    </tr>
  </table>

<script type="text/javascript">
//显示选择颜色面板
ddcolorpicker.init({
	colorcontainer: ['ddcolorwidget', 'ddcolorpicker'], //id of widget DIV, id of inner color picker DIV
	displaymode: 'float', //'float' or 'inline'
	floatattributes: ['', 'width=390px,height=250px,resize=1,scrolling=1,center=1'], //'float' window attributes
	fields: ['field1:control1','field1'] //[fieldAid[:optionalcontrolAid], fieldBid[:optionalcontrolBid], etc]
})
</script>

<div id="ddcolorwidget">
&nbsp;<%=selectColor %>
<div id="ddcolorpicker" style="position:relative; height:205px"></div>
<a href="auth/personalConfig/ddcolorpicker/" style="margin-left:5px; font-size:90%"></a>
</div>
</form>
</body>
</html>
<script type="text/javascript">
	//保存个性化颜色配置
	function saveIdentity(){		
        var myAjax = new Ajax("com.bos.utp.auth.PersonalsettingManager.updateSetting.biz");  
 		myAjax.addParam("operconfig/configname", '<b:write property="operconfig/configvalue"/>');    <!-- 个性化颜色配置 -->
 		myAjax.addParam("operconfig/configtype", "color");
 		myAjax.addParam("operconfig/acOperator/operatorid", $name('identity/acOperator/operatorid').value);
 		myAjax.addParam("operconfig/configvalue", $name("operconfigs/configvalue").value); 		
 		
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
	
	<%--$name('operconfig/configvalue').value = '<b:write property="operconfigs/configvalue" />';--%>
	
</script>
<script language="javascript">
    //初始化页面按钮样式
     eventManager.add(window,"load",custInit); 
</script>