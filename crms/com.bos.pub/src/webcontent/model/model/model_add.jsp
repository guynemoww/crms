<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-16 10:38:33
  - Description:新增
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>模型名称：</label>
		<input class="nui-textbox nui-form-input" name="item.modelName" vtype="maxLength:50" required="true"/>
		
		<label>总分计算方式：</label>
		<input name="item.gradeType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="model_model_grade" />
		
		<label>模型描述：</label>
		<input colspan="3" style="width:500px;" class="nui-textarea nui-form-input" name="item.modelDesc" vtype="maxLength:500" required="false" />
	</div>
<!--
<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
						<tr>
							<td class="nui-form-label">模型名称：</td>
							<td>
<input class="nui-textbox nui-form-input" name="item.modelName" vtype="maxLength:50" required="true"/>
					        </td>
					        <td class="nui-form-label">总分计算方式:</td>
							<td>
<input name="item.gradeType" required="true"
	class="nui-dictcombobox nui-form-input" dictTypeId="model_model_grade" />
					        </td>
							<td class="nui-form-label">模型描述：</td>
							<td>
<input class="nui-textbox nui-form-input" name="item.modelDesc" vtype="maxLength:500" required="false"/>
					        </td>
						</tr>
						 <tr>
					        <td class="nui-form-label">总分计算表达式:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="item.gradeExpr" required="false" style="width:300px;"/>
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">总分转换表达式:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="item.modelExpr" required="false" style="width:300px;"/>
					        </td>
						</tr> 
					</table>-->
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
	o.item.modelStatus="1";
	o.item.modelClass="1";//分类：1-评级模型使用。此字段暂无含义
	/*if (o.item.gradeType=="2" && !o.item.gradeExpr) {
		nui.alert("总分计算方式为“公式计算得分”时，需要输入“总分计算表达式”");
		return;
	}
	*/
	
	var json=nui.encode(o);
	//nui.alert(json);
	$.ajax({
            url: "com.bos.pub.model.model.addModel.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		CloseWindow("ok");
            		//nui.alert("保存成功");
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