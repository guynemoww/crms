<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-02-28 10:38:33
  - Description:菜单项编辑，tab页和弹出面板中使用
-->
<head>
</head>
<body>
<div style="padding-top:0px;">
	<div id="form1">
		<input id="acmenu.acMenu.menuid" name="acmenu.acMenu.menuid" class="nui-hidden" />
		<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table" >
            <tr>
                <th class="nui-form-label"><label for="appmenu.menuname$text">菜单名称：</label></th>
                <td>    
                    <input id="acmenu.menuname" name="acmenu.menuname" class="nui-textbox nui-form-input" required="true"  vtype="rangeLength:1,20"/>
                    <input id="acmenu.menuid" name="acmenu.menuid" class="nui-hidden"/>
                    <input id="acmenu.menuaction" name="acmenu.menuaction" class="nui-hidden"/>
                    <input id="acmenu.menulevel" name="acmenu.menulevel" class="nui-hidden"/>
                    <input id="acmenu.menuseq" name="acmenu.menuseq" class="nui-hidden"/>
                    <input id="acmenu.subcount" name="acmenu.subcount" class="nui-hidden"/>  
                    <input id="type" name="type" class="nui-hidden" />
                </td>
                <th class="nui-form-label"><label for="acmenu.menucode$text">菜单代码：</label></th>
                <td>    
                    <input id="acmenu.menucode" name="acmenu.menucode" class="nui-textbox nui-form-input" readonly="true" required="true"  vtype="rangeLength:1,20" onvalidation="codevalidation"/>
                </td>
            </tr>
            <tr class="odd">
                <th class="nui-form-label"><label for="acmenu.menulabel$text">菜单显示名称：</label></th>
                <td>    
                    <input id="acmenu.menulabel" name="acmenu.menulabel" class="nui-textbox nui-form-input" required="true"  vtype="rangeLength:1,20"/>
                </td>
                <th class="nui-form-label"><label for="acmenu.displayorder$text">菜单显示顺序：</label></th>
                <td>    
                    <input id="acmenu.displayorder" name="acmenu.displayorder" class="nui-textbox nui-form-input" vtype="range:0,100"/>
                </td>
            </tr>
            <tr>
           		<th class="nui-form-label"><label for="test1$text">功能资源：</label></th>
                <td>
                 		<input id="acmenu.menuaction" name="acmenu.menuaction" class="nui-textbox nui-form-input"  required="false"  vtype="rangeLength:1,256"/>
<!--                 	<input id="test1" class="nui-buttonedit nui-form-input" onbuttonclick="onButtonEdit" name="acmenu.funccode" textName="acmenu.funcname"/>     -->
                </td>
            	<th class="nui-form-label"><label for="acmenu.isleaf$text">是否为叶子菜单：</label></th>
                <td> 
                	<input id="acmenu.isleaf" class="nui-dictcombobox nui-form-input" name="acmenu.isleaf" value="0" 
                   		 valueField="dictID" textField="dictName" dictTypeId="XD_0002"/> 
                </td>
            </tr>
            <tr>
                <td colspan="4" style="padding-right:20px;text-align:right;"> 
                	 <a class="nui-button" iconCls="icon-save" onclick="onOk()">保存</a>
			        <span style="display:inline-block;width:25px;"></span>
			        <a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">取消</a>
                </td>
            </tr>
        </table>
        <%--<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	        <a class="nui-button"  iconCls="icon-save" onclick="onOk()">保存</a>
	        <span style="display:inline-block;width:25px;"></span>
	        <a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">取消</a>
	    </div> --%>       
    </div>
</div>
<script type="text/javascript">
        nui.parse();
        var form = new nui.Form("form1");
        
        function onButtonEdit(){
	   		var btnEdit = this;
	    	nui.open({
                url: "<%=request.getContextPath() %>/utp/framework/menu/menu_function_select.jsp",
                title: "选择功能调用入口", 
                width: 800, 
                height: 480,
                allowResize:false,
                ondestroy: function (action) {
                    //grid.reload();
                   if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.getData();
                        data = nui.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.funccode);
                            btnEdit.setText(data.funcname);
                            var menuaction = nui.get("acmenu.menuaction");
                            menuaction.setValue(data.funcaction);
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
                url: "com.bos.utp.framework.MenuManager.updateMenu.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (text) {
                    CloseWindow("ok");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }
		
		var tempMenuCode = "";
        //标准方法接口定义
        function SetData(data) {
            //跨页面传递的数据对象，克隆后才可以安全使用
                data = nui.clone(data);
				var json = nui.encode({template:data});
                $.ajax({
                    url: "com.bos.utp.framework.MenuManager.getMenu.biz.ext",
                    type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    cache: false,
                    success: function (text) {
                        var o = nui.decode(text);
                        form.setData(o);
                        if(document.getElementById("acmenu.subcount").value!=0){
                        	var isleaf = nui.get("acmenu.isleaf");
            				isleaf.enabled = false;
                        }
                        if(data.type){
			                var type = nui.get("type");
			                type.setValue(data.type);
		                }
		                var code = nui.get("acmenu.menucode");
		                tempMenuCode = code.getValue();
                        form.setChanged(false);
                    }
                });
        }
        
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
        function codevalidation(e){
        	if(e.value == tempMenuCode) return;
        	if(e.isValid){
        		var data = {menucode:e.value};
        		var json = nui.encode({template:data});
	        	$.ajax({
                    url: "com.bos.utp.framework.MenuManager.validateMenu.biz.ext",
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
    </script>
</body>
</html>