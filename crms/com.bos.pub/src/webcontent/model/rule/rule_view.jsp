<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-14 10:38:33
  - Description:参数新增
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>规则名称：</label>
		<input class="nui-text nui-form-input" name="rule.ruleName" vtype="maxLength:50" required="true" enabled="false"/>
		
		<label>规则描述：</label>
		<input class="nui-text nui-form-input" name="rule.ruleDesc" vtype="maxLength:500" required="false"/>

		<label>参数列表（逗号分隔）：</label>
		<input class="nui-text nui-form-input" name="rule.ruleParam" vtype="maxLength:1000" required="false"/>

		<label>嵌套引用的规则列表（逗号分隔）：</label>
		<input class="nui-text nui-form-input" name="rule.ruleRuleInd" vtype="maxLength:1000" required="false"/>

		<label>规则表达式：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="rule.ruleExpr" required="true" style="width:300px;"/>
	</div>
</div>
				
		 <div class="nui-toolbar" style="border-bottom:0;">
	        <table style="width:100%;">
	            <tr>
		            <td style="width:100%;text-align:center;">
						<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		            </td>
	            </tr>
	        </table>
	    </div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
function initForm() {
	var json=nui.encode({"rule":{"ruleId":"<%=request.getParameter("ruleId") %>"}});
	$.ajax({
            url: "com.bos.pub.model.rule.getRule.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		form.setData(text);
            		form.setEnabled(false);
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