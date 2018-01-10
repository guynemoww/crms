<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common.jsp"%>
<style>
.over
{
text-decoration:underline;
}
.error_td
{
font-weight:bold;
font-size:15px;
color:#E95600;
padding: 2px 2px 2px 20px;
overflow: hidden;
white-space: nowrap;
word-break:break-all
}
.noright_td
{
font-weight:bold;
font-size:12px;
color:#E95600;
padding: 2px 2px 2px 20px;
overflow: hidden;
white-space: nowrap;
word-break:break-all
}
</style>
<%@page import="com.eos.system.exception.EOSException"%>
<%@page import="com.eos.system.exception.EOSRuntimeException"%>
<%@page import="com.eos.data.datacontext.DataContextManager"%>
<html>
<%

     Object eobj =request.getAttribute("_exception");
     String errorCode = null;
     String errorMsg = null;
    if (eobj instanceof EOSException) {
         EOSException eose = (EOSException)eobj;    
         errorCode = eose.getCode();    
         errorMsg = eose.getMessageOnly(DataContextManager.current().getCurrentLocale());
     } else if (eobj instanceof com.eos.system.exception.EOSRuntimeException) {
          EOSRuntimeException eosre = (EOSRuntimeException)eobj;
          errorCode = eosre.getCode();
          errorMsg = eosre.getMessageOnly(DataContextManager.current().getCurrentLocale());
     }
	//详细错误信息
	String l_error_info = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_error_info");
	//错误提示信息
	String l_error_promote = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_error_promote");
	//联系信息提示
	String l_contact_promote = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_contact_promote");
	//无权限信息提示
	String l_no_permission = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_no_permission");
	//堆栈错误提示
	String l_stack_promote = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_stack_promote");
	
	
 %>
<body topmargin="0" leftmargin="0" rightmargin="0">
<table width="100%" height="100%" bgcolor="#FFFFFF">
	<tr height="60%">
		<td width="100%" height="80%" align="center">
		<table border="0" width="575px" height="111px"
			style='background:url(<%=SkinUtil.getStyleFile("images/basic/warn.jpg",request) %>'>
			<tr height="25px" >
				<td width="80px"style="padding-left:10px;font:12px"><%=l_error_info %></td>
				<td ></td>
				<td></td>
			</tr>
		    <tr >
				<td></td>
				<% if("12102001".equals(errorCode)){ %>
				   <td id="msg" class="noright_td">				 	
				 	<%=l_no_permission %><br/><font ><%=errorMsg %></font>
				 	</td>
				 <%}else{ %>
				 <td id="msg" class="error_td">
				  <%= l_error_promote %><%=l_contact_promote %>
				  </td>
				 <%} %>
				</td>
				<td></td>
			</tr>
			<tr >
				<td></td>
				<td id="msg" class="error_td">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
						<tr>
							<td>
								<input type="button" class="button" value="<b:message key="l_back"/>" style="width: 100;" onclick="javascript:history.go(-1);">&nbsp;&nbsp;
								<input type="button" class="button" value="<b:message key="l_print"/>" style="width: 100;" onclick="javascript:print();">&nbsp;&nbsp;
								<input type="button" class="button" value="<b:message key="l_mailto"/>" style="width: 100;" onclick="javascript:window.location.href='mailto:'">&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</td>
				<td></td>
			</tr>
		</table>
		</td>
	</tr>	
	 <tr>
		<td>
			<w:panel id="e" title="<%=l_stack_promote %>" expand="false">
				<h:exception showStacktrace="true"/>
			</w:panel>
		</td>
	</tr>
</table>

<script>

window.onload= function(){ 
	try{
		unMaskTop(); 
	}catch(e){	}
}
</script>
</body>
</html>
