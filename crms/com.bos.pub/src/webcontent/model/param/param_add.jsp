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
<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
						<tr>
							<td class="nui-form-label">动态参数名称：</td>
							<td>
<input class="nui-textbox nui-form-input" name="param.paramName" vtype="maxLength:50" required="true"/>
<input class="nui-hidden nui-form-input" name="param.paramInd" vtype="maxLength:50" required="true"/>
					        </td>
							<td class="nui-form-label">参数类型：</td>
							<td>
<input id="param.paramType" name="param.paramType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="model_param_type" />
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">财报变量:</td>
							<td>
<input class="nui-textbox nui-form-input" name="param.financeVar" vtype="maxLength:30" required="false"/>
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">参数表达式:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="param.paramExpr" required="true" style="width:500px;height:150px;"/>
					        </td>
						</tr>
					</table>
				</div>
				
		 <div class="nui-toolbar" style="border-bottom:0;">
	        <table style="width:100%;">
	            <tr>
		            <td style="width:100%;text-align:center;">
		                <a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
						<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		            </td>
	            </tr>
	        </table>
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
	o.param.paramInd=o.param.paramName;
	o.param.paramStatus="0";
	o.param.paramClass="1";//参数分类：1-评级模型使用。此字段暂无含义
	var json=nui.encode(o);
	//nui.alert(json);
	$.ajax({
            url: "com.bos.pub.model.param.addParam.biz.ext",
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