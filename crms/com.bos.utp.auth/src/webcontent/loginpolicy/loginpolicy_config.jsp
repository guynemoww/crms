<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<html>
<head>
<title></title>

<%
	//登录限制管理
    String blackList = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.blackList");
    String whiteList = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.whiteList");

	Object root= com.eos.web.taglib.util.XpathUtil.getDataContextRoot("request", pageContext);
    String paramValue=(String)com.eos.web.taglib.util.XpathUtil.getObjectByXpath(root,"atSystemParam/paramvalue");
    //当参数值为-1时表示不启用该策略
    String checkedPolicy="-1".equals(paramValue)?"false":"true";
    String blackChecked="";
    String whiteChecked="";
    if("0".equals(paramValue))
       blackChecked="checked";
    else if("1".equals(paramValue))
       whiteChecked="checked";
    
%>

</head>
<body leftmargin="0" topmargin="0">
    <table align="left" border="0" width="770" class="form_table">
        <tr>
            <td colspan="4" align="left">   <!-- 登录策略 -->
               <h:checkbox value="-1" name="isEnabled" onclick="javascript:isEnabledPolicy();" checked="<%=checkedPolicy%>"/><b:message key="loginPolicyManager_l_title_loginPolicyStart"/>&nbsp;
               <h:form id="queryForm" name="queryForm">
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <b:message key="loginPolicyManager_l_title_currentPolicyType"></b:message><b:message key="l_colon"></b:message>
               <input type="radio" value="0"  name="policyType" <%=blackChecked %>/><b:message key="loginPolicyManager_AtLoginPolicy.blackList"/>
               <input type="radio" value="1"  name="policyType" <%=whiteChecked %>/><b:message key="loginPolicyManager_AtLoginPolicy.whiteList"/>
			   <input type="button" class="button" value="设置" onClick="javascript:setPolicyType()"/>
			   </h:form>
            </td>
        </tr>
    </table>
    <w:tabPanel id="config" height="100%" width="100%">
        <w:tabPage id="blackConfig" tabType="iframe" title="<%=blackList %>" url="/utp/auth/loginpolicy/loginpolicy_black_config.jsp" >
        </w:tabPage>
        <w:tabPage id="whiteConfig" tabType="iframe" title="<%=whiteList %>" url="/utp/auth/loginpolicy/loginpolicy_white_config.jsp" >
        </w:tabPage>
    </w:tabPanel>
</body>
</html>

<script language="javascript">

   function isEnabledPolicy(){

         if(!$name("isEnabled").checked){
		     var myAjax = new Ajax("com.bos.utp.auth.LoginPolicyManager.setLoginPolicy.biz");
		     myAjax.addParam("policyType", "-1");
		     myAjax.submit();
		     var returnNode = myAjax.getResponseXMLDom();
		     if( returnNode ) {
		         if( myAjax.getValue("root/data/retValue") == 1 ) {
		              alert( '<b:message key="loginPolicyManager_m_disablePolicySuccess"/>' );//禁止策略成功
		          } else {

		              alert( '<b:message key="loginPolicyManager_m_disablePolicyFailed"/>' ); //禁止策略失败
		          }
		      }
		  disableForm("queryForm");
       }else{
          enableForm("queryForm");
       }
   }


   function setPolicyType(){
         if(!$name("policyType").checked){
           alert('<b:message key="loginPolicyManager_m_selectPolicy"/>');
           return ;
         }

	     var myAjax = new Ajax("com.bos.utp.auth.LoginPolicyManager.setLoginPolicy.biz");

	     myAjax.addParam("policyType", $name("policyType").value);

	     myAjax.submit();
	     var returnNode = myAjax.getResponseXMLDom();
	     if( returnNode ) {

	         if( myAjax.getValue("root/data/retValue") == 1 ) {
	              alert( '<b:message key="loginPolicyManager_m_setPolicySuccess"/>' );//策略设置成功
	          } else {

	              alert( '<b:message key="loginPolicyManager_m_setPolicyFailed"/>' ); //策略设置失败
	          }
	      }


   }
   function initEnablePolicy(){
      var checkedPolicy=<%=checkedPolicy %>;
      if(!checkedPolicy){
         disableForm("queryForm");
      }else{
         enableForm("queryForm");
      }
   }

    //初始化页面按钮样式
     eventManager.add(window,"load",initEnablePolicy);
</script>