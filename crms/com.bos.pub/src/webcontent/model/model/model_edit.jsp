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
	<input class="nui-hidden nui-form-input" name="item"/>
	<div class="nui-dynpanel" columns="4">
		<label>模型名称：</label>
		<input class="nui-textbox nui-form-input" name="item.modelName" vtype="maxLength:50" required="true"/>
		
		<label>总分计算方式：</label>
		<input name="item.gradeType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="model_model_grade" />
		
		<label>模型描述：</label>
		<input colspan="3" style="width:500px;" class="nui-textarea nui-form-input" name="item.modelDesc" vtype="maxLength:500" required="false" />
		
		<label>总分计算表达式：</label>
		<div colspan="3">
			<a href="#" onclick="editRule('model');return false;">请点击此处</a>
		</div>
		
		<label>总分转换表达式：</label>
		<div colspan="3">
			<a href="#" onclick="editRule('modelgrade');return false;">请点击此处</a>
			<br/>以“总分”表示模型总分
		</div>
	</div>
<!-- 
<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
						<tr>
							<td class="nui-form-label">模型名称：</td>
							<td>
<input class="nui-hidden nui-form-input" name="item"/>
<input class="nui-textbox nui-form-input" name="item.modelName" vtype="maxLength:50" required="true" enabled="false"/>
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
<input class="nui-textarea nui-form-input" name="item.gradeExpr" required="false" style="width:450px;height:150px;"/>
					        </td>
						</tr>
						<tr>
					        <td class="nui-form-label">总分转换表达式:</td>
							<td colspan="3">
<input class="nui-textarea nui-form-input" name="item.modelExpr" required="false" style="width:450px;height:150px;"/>
					        </td>
						</tr>
					</table> -->
				</div>
				
		 <div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		 	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
			<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	    </div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var view = "<%=request.getParameter("view") %>";
		if (view=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}

function initForm() {
	var json=nui.encode({"item":{"modelId":"<%=request.getParameter("itemId") %>"}});
	$.ajax({
            url: "com.bos.pub.model.model.getModel.biz.ext",
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

function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	if (o.item.gradeType=="2" && !o.item.gradeExpr) {
		nui.alert("总分计算方式为“公式计算得分”时，需要输入“总分计算表达式”");
		return;
	}
	
	var json=nui.encode(o);
	//nui.alert(json);
	$.ajax({
            url: "com.bos.pub.model.model.updateModel.biz.ext",
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
function editRule(t) {
	var o=form.getData();
	var rid= ( t == 'model' ? o.item.gradeExpr : o.item.modelExpr ); //model表示总分计算表达式、modelgrade表示总分转换表达式
	if (!rid) {
		alert('此指标无此表达式！');
		return;
	}
	var modelid=o.item.modelId;
	nui.open({
            url: nui.context+"/pub/grant/pack/rule/rule_edit.jsp?type="
            	+t
            	+"&rid="+rid
            	+"&modelid="+modelid
            	+"&view="+(view == '1' ? 'const' : view),
            title: (view == 1 ? "查看" : "编辑"), 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    //initForm();
                }
            }
    });
}
	</script>
</body>
</html>