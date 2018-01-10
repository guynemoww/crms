<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16

  - Description:TB_PUB_GRANT_PACKAGE, com.bos.pub.decision.TbPubGrantPackage-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>规则分类编号：</label>
		<input name="item.pid" required="false" class="nui-text nui-form-input" vtype="maxLength:32" />

		<label>规则分类名称：</label>
		<input name="item.pname" required="false" class="nui-text nui-form-input" vtype="maxLength:200" />

		<label>规则分类类型：</label>
		<input colspan="3" name="item.ptype" required="true" class="nui-text nui-form-input" dictTypeId="pub_dec_pack_type" />

		<label>适用范围说明：</label>
		<input colspan="3" style="width:500px;" name="item.pnote" required="false" class="nui-textarea nui-form-input" vtype="maxLength:4000" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
		}

function initForm() {
	var json=nui.encode({"item":{"pid":"<%=request.getParameter("itemId") %>"}});
	$.ajax({
            url: "com.bos.pub.decision.getItem.biz.ext",
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
