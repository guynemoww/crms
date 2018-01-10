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
		<label>指标名称：</label>
		<input class="nui-textbox nui-form-input" name="index.indexName" vtype="maxLength:50" required="true"/>
		
		<label>指标类型：</label>
		<input name="index.indexType" required="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="model_index_type" />
		
		<label>得分类型：</label>
		<input colspan="3" name="index.gradeType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="model_grade_type" />
			
		<label>指标描述：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="index.indexDesc" vtype="maxLength:500" required="false" style="width:300px;"/>
		
		<!-- <label>指标表达式：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="index.indexExpr" required="false" style="width:300px;"/>
		
		<label>参数列表（逗号分隔）：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="index.indexParam" required="false" style="width:300px;"/>
		
		<label>嵌套引用的指标列表（逗号分隔）：</label>
		<input colspan="3" class="nui-textarea nui-form-input" name="index.indexIndex" required="false" style="width:300px;"/>

		<label>得分表达式：</label>
		<div colspan="3">
			<input class="nui-textarea nui-form-input" name="index.gradeExpr" required="false" style="width:300px;"/>
			<br/>以“指标值”表示指标的值
		</div>-->
	</div>
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
	/*2013-12-19 wsc.hi@163.com 指标表达式、得分表达式都已修改为可视化编辑工具编辑，无法校验。
	if ((o.index.indexType=='2'||o.index.indexType=='3') && !o.index.indexExpr) {
		nui.alert("指标类型为“计算公式”时，必须输入指标表达式");
		return;
	}
	if ((o.index.gradeType=='3') && !o.index.gradeExpr) {
		nui.alert("得分类型为“计算公式得分”时，必须输入得分表达式");
		return;
	}*/
	o.index.indexInd=o.index.indexName;
	o.index.indexStatus="0";
	o.index.indexClass="1";//分类：1-评级模型使用。此字段暂无含义
	var json=nui.encode(o);
	//nui.alert(json);
	$.ajax({
            url: "com.bos.pub.model.index.addIndex.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		if (text.index.indexId) {
            			self.location.href=nui.context+"/pub/model/baseindex/index_edit.jsp?indexId="+text.index.indexId;
            			return;
            		}
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