<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-07 18:50:29
  - Description:
-->
<head>
</head>
<body>
<div style="padding-top:5px;">
	<div id="form1">
		<input id="appmenu.appMenu.menuid" name="appmenu.appMenu.menuid" class="nui-hidden" />
		
		<table style="width:98%;height:30%;table-layout:fixed;" class="nui-form-table" >
            <tr>
                <th class="nui-form-label"><label for="appmenu.menuname$text">菜单名称：</label></th>
                <td>    
                    <input id="acmenu.menuname" name="acmenu.menuname" class="nui-textbox nui-form-input" required="true"  vtype="rangeLength:1,20"/>
                    <input id="acmenu.menuid" name="acmenu.menuid" class="nui-hidden"/>
                    <input id="acmenu.menuaction" name="acmenu.menuaction" class="nui-hidden"/>
                    <input id="acmenu.menulevel" name="acmenu.menulevel" class="nui-hidden"/>
                    <input id="acmenu.menuseq" name="acmenu.menuseq" class="nui-hidden"/>
                    <input id="acmenu.subcount" name="acmenu.subcount" class="nui-hidden"/>  
                </td>
                <th class="nui-form-label"><label for="acmenu.menucode$text">菜单代码：</label></th>
                <td>    
                    <input id="acmenu.menucode" name="acmenu.menucode" class="nui-textbox nui-form-input" readonly="true" required="true"  vtype="rangeLength:1,20" />
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
<!--                 	<input id="test1" class="nui-buttonedit nui-form-input" onbuttonclick="onButtonEdit" name="acmenu.funccode"  textName="acmenu.funcname"/>     -->
                </td>
            	<th class="nui-form-label"><label for="acmenu.isleaf$text">是否为叶子菜单：</label></th>
                <td> 
                	<input id="acmenu.isleaf" class="nui-dictcombobox nui-form-input" name="acmenu.isleaf" value="0" 
                   		 valueField="dictID" textField="dictName" dictTypeId="XD_0002"/> 
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
        var menuid = "<%= request.getParameter("id") %>";
        var json = nui.encode({template:{menuid:menuid}});
        var tempMenuCode = "";
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
                    var code = nui.get("acmenu.menucode");
	                tempMenuCode = code.getValue();
                    form.setChanged(false);
                }
            });
        function onButtonEdit(){
	   		var btnEdit = this;
	    	nui.open({
                url: "<%=request.getContextPath() %>/utp/framework/menu/menu_function_select.jsp",
                title: "选择功能调用入口",
				width: 800, 
                height: 525,
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
                            nui.get("acmenu.menucode").setValue(data.funccode);
                            nui.get("acmenu.isleaf").setValue("1");
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
                    nui.alert("保存成功！");
                    parent.refreshParentNode();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert("保存失败！");
                }
            });
        }

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
                        form.setChanged(false);
                    }
                });
        }
        
        function onOk(e) {
            SaveData();
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
