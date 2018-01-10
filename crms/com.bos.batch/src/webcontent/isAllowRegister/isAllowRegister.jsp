<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<html>
  <head>
    <title>
      登录权限配置
    </title>
    <%@include file="/common/nui/common.jsp" %>
  </head>
  <body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
	<div title="登录权限配置" >
	<center>
   <div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>跑批时，是否允许其他人员登录：</label>
		<input name="registerFlag" id="registerFlag" required="true" class="nui-dictradiogroup nui-form-input"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_0002" value="1" />
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-save" onclick="javascript:save();" name="save" id="save">确定</a>
	</div>
	</div>
	</center>
	</div>
	</div>
	<!-- 
	<br>
	<div style="width:99.5%;margin-left:23px;">				
		<textarea class="nui-textarea" name="" enabled="false" height="40%" width="40%"
		style="border-top:1px solid #8ba0bc;border-bottom:none;" value="设置说明："></textarea>
	</div>
	 -->
    <script>
    nui.parse();
    /**设置登录权限*/
   	function save(){
   		git.mask();
   		var registerFlag = nui.get('registerFlag').getValue();
   		var json=nui.encode({'registerFlag':registerFlag});
   		//alert(json);return;
   		nui.ajax({
		    url: "com.bos.batch.isAllowRegister.isAllowRegister.biz.ext" ,
		    contentType:'text/json',
		    type: "post",
		    data:json,
		    success: function (text) {
		    	git.unmask();
		        var msg = text.msg;
		        if(msg=='1'){
		        	alert('设置成功');
		        }else{
		        	alert('设置失败');
		        }
		        
		    },
		    error: function () {
		    	git.unmask();
		    	alert('设置失败');
		    }
		});
   		
   			    
   	}
  
    </script>
  </body>
</html>
