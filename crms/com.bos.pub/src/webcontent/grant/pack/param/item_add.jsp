<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16
  - Description:TB_PUB_GRANT_PARAM, com.bos.pub.decision.TbPubGrantParam
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.decision.TbPubGrantParam" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<!-- <label>参数编号：</label>
		<input name="item.paramid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" /> -->

		<label>参数名称：</label>
		<input name="item.paramname" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		
		<label>英文名称：</label>
		<input name="item.enname" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>参数类型：</label>
		<input colspan="1" name="item.paramtype" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_param" />

		<label>参数业务字典代码：</label>
		<input colspan="1" name="item.paramdicttype" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>描述/备注：</label>
		<input colspan="3" style="width:500px;" 
			name="item.paramnote" required="false" class="nui-textarea nui-form-input" vtype="maxLength:4000" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var packageid="<%=request.getParameter("pid") %>";
	o.item.tbPubGrantPackage={pid:packageid};
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
            url: "com.bos.pub.decision.addParam.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		CloseWindow("ok");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
	</script>
</body>
</html>
