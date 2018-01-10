<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-14
  - Description:TB_MATTER_BASE_MESSAGE, com.bos.pub.sys.TbMatterBaseMessage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>项目编号：</label>
		<input name="tbMatterBaseMessage.scoreMessageId" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false" value="<%=request.getParameter("projectNumber") %>"/>
	
		<label>机构名称：</label>
		<input name="tbMatterBaseMessage.organizationName" required="true" class="nui-buttonEdit" vtype="maxLength:50" onbuttonclick="selectEmpOrg" enabled="false" dictTypeId="org" value="<%=request.getParameter("organizationName") %>"/>
		<label>机构级别：</label>
		<input id="level" name="tbMatterBaseMessage.orgLevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="CDZZ0002" enabled="false" />
		<label>计分事项：</label>
		<input name="tbMatterBaseMessage.scoreMatter" required="true" class="nui-TextArea" vtype="maxLength:1000" />
		<label>计分周期：</label>
		<input id="zhouqi" name="tbMatterBaseMessage.scorePeriod" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />
		<label>计分标准：</label>
		<input name="tbMatterBaseMessage.scoreStandard" required="true" class="nui-textbox nui-form-input" vtype="maxLength:4;int" />
		<label>计分周期单位：</label>
		<input id="danwei" name="tbMatterBaseMessage.scoreStandardUnit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:9" dictTypeId="XD_ZQ293" emptyText="请选择" />
		<label>计分单位：</label>
		<input name="tbMatterBaseMessage.scoreUnit" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:60" dictTypeId="XD_JF223" emptyText="请选择"/>
		<label>是否差异化：</label>
		<input id="yn" name="tbMatterBaseMessage.whetherDifference" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:10" dictTypeId="XD_0002" emptyText="请选择" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    nui.get("level").setValue("<%=request.getParameter("orgLevel") %>");
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.scoreMatter.addTbMatterBaseMessage.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
	});
}
	</script>
</body>
</html>
