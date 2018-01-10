<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-01 10:35:35
  - Description:编辑功能，弹出面板和tab页中使用
-->
<head>
</head>
<body>
<div style="padding-top:5px">
	<div id="form1">
		<div style="padding-top:2px;padding-bottom:2px;">
	    </div>
	    
	    <table style="width:100%;table-layout:fixed;" class="nui-form-table" >
            <tr>
                <th class="nui-form-label"><label for="appfunction.funcname$text">功能名称：</label></th>
                <td>    
                    <input id="appfunction.funcname" name="appfunction.funcname" class="nui-textbox nui-form-input" required="true" vtype="rangeLength:1,20" />
                     <input id="type" name="type" class="nui-hidden" />
                </td>
                <th class="nui-form-label"><label for="appfunction.funccode$text">功能编码：</label></th>
                <td>    
                    <input id="appfunction.funccode" name="appfunction.funccode" class="nui-textbox nui-form-input" readonly="true" required="true"  vtype="rangeLength:1,20" />
                </td>
            </tr>
            <tr class="odd">
            	<th class="nui-form-label"><label for="appfunction.ismenu$text">是否定义为菜单：</label></th>
                <td>
                	<input id="appfunction.ismenu" class="nui-dictcombobox nui-form-input" name="appfunction.ismenu" value="1" 
                    valueField="dictID" textField="dictName" dictTypeId="XD_0002"/>
                </td>
            	<th class="nui-form-label"><label for="appfunction.ischeck$text">是否验证权限：</label></th>
                <td>
                	<input id="appfunction.ischeck" class="nui-dictcombobox nui-form-input" name="appfunction.ischeck" value="1" 
                    valueField="dictID" textField="dictName" dictTypeId="XD_0002"/>
                </td>
            </tr>
            <tr>
                <th class="nui-form-label"><label for="resType$text">功能类型：</label></th>
                <td>
                    <input id="resType" class="nui-dictcombobox nui-form-input" name="appfunction.functype" value="0" 
                    valueField="dictID" textField="dictName" dictTypeId="ABF_FUNCTYPE"/>
                </td>
            </tr>
            <tr class="odd">
                <th class="nui-form-label"><label for="test1$text">功能调用入口：</label></th>
                <td colspan="3">
                	<!--<input id="test1" class="nui-buttonedit nui-form-input" onbuttonclick="onButtonEdit" name="appfunction.funcaction" textName="test2" style="width:420px;"/>-->  
                	<input id="test1" class="nui-textbox nui-form-input" name="appfunction.funcaction" required="true" onvalidation="onFuncActionValidation" requiredErrorText="功能调用入口不能为空" textName="test2" style="width:420px;"/>  
                </td>
            </tr>
            <tr>
                <th class="nui-form-label"><label for="paraInfo$text">输入参数：</label></th>
                <td colspan="3">     
                    <input id="paraInfo" name="appfunction.parainfo" class="nui-textbox nui-form-input" style="width:420px;"/>
                </td>
            </tr>
            <tr class="odd">
                <th class="nui-form-label"><label for="appfunction.funcdesc$text ">功能描述：</label></th>
                <td colspan="3">     
                    <input id="appfunction.funcdesc" name="appfunction.funcdesc" class="nui-textarea nui-form-input" style="width:420px;height:100px;" />
                </td>
            </tr>
            <tr>
				<td colspan="4" style="padding-right:20px;text-align:right;spacing:5px;">
					<a class="nui-button"  iconCls="icon-save" onclick="onOk()">保存</a>
				</td>
			</tr>
        </table>        
    </div>
 </div>
	<script type="text/javascript">
        nui.parse();
        var form = new nui.Form("form1");
        var functionid = "<%= request.getParameter("realId") %>";
        var json = nui.encode({template:{funccode:functionid}});
        var tempFuncCode = "";
        var tempName = "";
	    $.ajax({
	        url: "com.bos.utp.framework.FunctionManager.getFunction.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        cache: false,
	        success: function (text) {
	            var o = nui.decode(text);
                form.setData(o);
                var o = nui.get("test1");
                //o.setText(o.getValue());
                var name = nui.get("appfunction.funcname");
                var code = nui.get("appfunction.funccode");
	            tempFuncCode = code.getValue();
	            tempName = name.getValue();
                form.setChanged(false);
                
	        }
	    });
	    
	    function onButtonEdit(){
	   		var btnEdit = this;
	    	nui.open({
                url: "<%=request.getContextPath() %>/utp/framework/function/entrance_select.jsp",
                title: "选择功能调用入口", 
                width: 350, 
                height: 460,
                allowResize:false,
                ondestroy: function (action) {
                    //grid.reload();
                   if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.getData();
                        data = nui.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.resUrl);
                            btnEdit.setText(data.resUrl);
                            var respara = nui.get("paraInfo");
                            respara.setValue(data.resPara);
                            var restype = nui.get("resType");
                            restype.setValue(data.resType);
                        }
                    } 
                }
            });
	    }
	    
        function SaveData() {
            var o = form.getData(true,true);            

            form.validate();
            if (form.isValid() == false) return;

            var json = nui.encode(o);
            $.ajax({
                url: "com.bos.utp.framework.FunctionManager.updateFunction.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (text) {
                    nui.alert("保存成功！");
                    parent.refreshParentNode();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                     nui.alert("保存失败!");
                }
            });
        }
        function onOk(e) {
            SaveData();
        }
        function codevalidationName(e){
        	if(e.value == ''){
        		e.errorText = "必填";
        		e.isValid = false;
        		return;
        	}
        	if(e.value == tempName){
        		return;
        	}else{
        		e.errorText = "不能修改";
        		e.isValid = false;
        		return;
        	}
        }
        
        function codevalidation(e){
        	if(e.value == ''){
        		e.errorText = "必填";
        		e.isValid = false;
        		return;
        	}
        	if(e.value == tempFuncCode){
        		return;
        	}else{
        		e.errorText = "不能修改";
        		e.isValid = false;
        		return;
        	}
        	if(e.isValid){
        		var data = {funccode:e.value};
        		var json = nui.encode({template:data});
	        	$.ajax({
                    url: "com.bos.utp.framework.FunctionManager.validateFunction.biz.ext",
                    type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    cache: false,
                    async:false,
                    success: function (text) {
                       var o = nui.decode(text);
                        if(o.data == "1"){
                        	e.errorText = "功能编码不唯一，请请重新填写";
	        				e.isValid = false;
                        }
                    }
	           });
        	}
        }
        
        
        function onFuncActionValidation(e){
        	if (e.isValid) {
        		if(e.value == ''){
	              e.errorText = "必须输入功能调用入口";
	              e.isValid = false;
        		}
            }
        }
    </script>
</body>
</html>