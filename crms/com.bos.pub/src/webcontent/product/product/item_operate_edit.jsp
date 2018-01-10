<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31

  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		
		<div class="nui-dynpanel" columns="4">
			
			<label>授信品种参数代码：</label>
			<input  name="item.productId" allowInput="false" required="false" class="nui-textbox nui-form-input"  enabled="false"/>
	
			<label>机构名称：</label>
			<input name="item.orgNum" allowInput="false" required="false" class="nui-buttonEdit" enabled="false" dictTypeId="org"/>
		</div>
	</div>
					
	<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">

		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		

	function initForm() {

		var json=nui.encode({"item":{"orgOperateAuthorizationId":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.pub.product.TbSysOperateAuthorization"}});
		$.ajax({
	            url: "com.bos.pub.product.getItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
			
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {

	            		form.setData(text);

	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();

	

	
	</script>
</body>
</html>
