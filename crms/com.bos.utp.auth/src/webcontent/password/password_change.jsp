<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html>
<head>
</head>
<body >
<div id="form1" >
  <div id="panel1" class="nui-panel" title="修改密码" iconCls="icon-add" style="width:99%;height:65%;" 
    showToolbar="false" showCollapseButton="true" showFooter="false" allowResize="true">
    <table align="center" border="0" width="100%" >
      <tr>
      	<td align="right">
         	 原密码
        </td>
        <td colspan="2">
        	<input id="oldpass"  name="password/old" class="nui-password"/>
        </td>
     </tr> 
     <tr>   
        <td align="right">
          	新密码
        </td>
        <td colspan="2">
        	<input id="newpass"  name="password/new" class="nui-password"/>
        </td>
      </tr> 
      <tr>
        <td align="right" >
          	重复密码
        </td>
        <td  colspan="2">
        	<input id="aginpass"  name="password/new_two" class="nui-password"/>
        </td>
      </tr>
      <tr>
        <td><br/>
        </td>
        <td>
        </td>
      </tr>
      <tr>
        <td align="center"  colspan="2">
          	<a class="nui-button" iconCls="icon-edit" onclick="btnUpdate_onClick()" >修改</a>      
        	<a class="nui-button" iconCls="icon-reset" onclick="btnReSet_onClick()" >重置</a>      
        </td>
      </tr>
    </table>
    <table align="center" border="0" width="100%" class="form_table"cellpadding="0" cellspacing="0">
	      <tr>
	        <td class="EOS_table_left">
		         说明：<br /> 
		    	1、密码最小长度不低于六个字符；<br />
		    	2、密码生命周期最长为三个月，修改后的密码至少与上次密码不同；<br />
		    	3、密码至少包含:字母，数字和特殊字符(~!@#$%^&*_+-=)，其中两种字符。
	    	</td>
	      </tr>
	    </table>  
  </div>
</div>

<script type="text/javascript">
   nui.parse();
   function btnUpdate_onClick() {
		var pass_old = nui.get("oldpass").getValue();
		var pass_new_one = nui.get("newpass").getValue();
		var pass_new_two = nui.get("aginpass").getValue();
		//var userId = $name("acOperator/userid").value;
		
		if (pass_new_one.length < 6) {
			alert('密码长度不能低于6位');
			return;
		}
		if (pass_new_one != pass_new_two) 
			alert("新密码与重复密码不一致");   //“新密码”与“重复密码”不一致
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
			var form1 = new nui.Form("#form1");
			var data = form1.getData(true,true);
			var json = nui.encode(data);
	        $.ajax({
	            url: "com.bos.utp.auth.PasswordManager.passwordChange.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	        		if(text.returnValue == '1'){
	        			parent.parent.parent.location.href = "${pageContext.request.contextPath}/com.bos.utp.auth.Login.flow?_eosFlowAction=logout";
	        		} else if(text.returnValue == '-1'){
	        			nui.alert("账号不存在或用户状态非正常 ， 请联系管理员");
	        		} else if(text.returnValue == '-2'){
	        			nui.alert("原密码不正确");
	        		} else if(text.returnValue == '-3'){
	        		 	nui.alert("新密码不能与最近3次密码重复!");
	        		}   	
			
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	                CloseWindow();
	            }
	        });
		}	
    }
    
    
    function btnReSet_onClick(){
    	nui.get("oldpass").setValue("");
    	nui.get("newpass").setValue("");
    	nui.get("aginpass").setValue("");
    }
    
    
</script>
</body>
</html>
