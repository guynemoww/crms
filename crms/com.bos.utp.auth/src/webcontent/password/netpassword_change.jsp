<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
</head>
<body leftmargin="0" topmargin="0">
	<h:form name="passwordChange" method="post" checkType="blur;">
		<h:hidden property="acOperator/userid" />
	    <table align="center" border="0" width="100%" class="form_table"cellpadding="0" cellspacing="0">
	      <tr>
	        <td class="form_label">
	          <b:message key="pwd_l_old"></b:message><b:message key="DictManager_l_colon"></b:message>
	        </td>
	        <td colspan="1">
	          <h:password property="password/old" validateAttr="allowNull=false"/>
	        </td>
	      </tr>
	      <tr>
	        <td class="form_label">
	          <b:message key="pwd_l_new_one"></b:message><b:message key="DictManager_l_colon"></b:message>
	        </td>
	        <td colspan="1">
	          <h:password property="password/new_one" validateAttr="allowNull=false"/>
	        </td>
	      </tr>
	      <tr>
	        <td class="form_label">
	          <b:message key="pwd_l_new_two"></b:message><b:message key="DictManager_l_colon"></b:message>
	        </td>
	        <td colspan="1">
	          <h:password property="password/new_two" validateAttr="allowNull=false"/>
	        </td>
	      </tr>
	      <tr class="form_bottom">
	        <td colspan="4" class="form_bottom">
	          <input type="button" class="button" onclick="changePassword()" value='<b:message key="l_update"></b:message>'> <!-- 修改 -->
	          <input type="reset" class="button" value='<b:message key="l_reset"></b:message>'> <!-- "重置" -->
	        </td>
	      </tr>
	    </table>
    </h:form>
</body>
</html>

<script>
	/*
	 * 功能：修改密码
	*/
	function changePassword(){
		var frm = $name(passwordChange);
		if (checkForm(frm)){
			var pass_old = $name("password/old").value;
			var pass_new_one = $name("password/new_one").value;
			var pass_new_two = $name("password/new_two").value;
			var userId = $name("acOperator/userid").value;
			
			if (pass_new_one.length < 6) {
				alert('密码长度不能低于6位');
				return;
			}
			
			if (pass_new_one != pass_new_two) 
				alert('<b:message key="pwd_l_not_equal"/>');   //“新密码”与“重复密码”不一致
			else{
				if (pass_old == pass_new_one) {
			       	alert("新密码不能和老密码一致");
			      	return;
				}
				
				var cn = 0;
				//字母
				if (pass_new_one.match(/[A-Za-z]+/)) {
					cn++;
				}
				//数字
				if (pass_new_one.match(/[0-9]+/)) {
					cn++;
				}
				var tempArr = new Array("~","!","@","#","$","%","^","&","*","_","+","-","="); 
				//特殊字符
				for (var i = 0; i < tempArr.length; i++) {
					if (pass_new_one.indexOf(tempArr[i]) >= 0) {
						cn++;
					}
				}
				if (cn < 2) {
					alert("密码至少包含:字母，数字和特殊字符(~!@#$%^&*_+-=)，其中两种字符");
					return;
				}
			
				var myAjax = new Ajax("com.bos.utp.auth.PasswordManager.passwordChange.biz");
				myAjax.addParam("acOperator/userid", userId );
				
				myAjax.addParam("password/old", pass_old );
				myAjax.addParam("password/new", pass_new_one );
				myAjax.submit();
				
				var returnNode = myAjax.getResponseXMLDom();
			    if( returnNode ) {
					if( myAjax.getValue("root/data/returnValue") == -2 ){
			        	alert('<b:message key="pwd_l_old_not_ok"/>');   //原密码不正确
			        }else{
			       	 	if( myAjax.getValue("root/data/returnValue") == -1 ){
			        		alert('<b:message key="pwd_l_change_not_ok"/>');   //修改失败
			        	}else{
			            	alert('<b:message key="pwd_l_change_ok"/>');      //修改成功
			            	location.href = "${pageContext.request.contextPath}/com.bos.utp.auth.netLogin.flow?_eosFlowAction=logout";
						}
					}
				} else {
					alert('<b:message key="pwd_l_change_not_ok"/>');          //修改失败
				}
			}
		}
		frm.reset();
	}	
</script>