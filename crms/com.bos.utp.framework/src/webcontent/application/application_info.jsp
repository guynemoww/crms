<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-01 10:31:32
  - Description:编辑应用，tab页和弹出面板使用
-->
<head>
</head>
<body>
	<div style="padding-top:0px">
		<div id="form1">
			<div style="padding-top:2px;padding-bottom:2px;">
	        </div>
	    	<table style="width:100%;height:98%;table-layout:fixed;" class="nui-form-table" >
	            <tr>
	                <th class="nui-form-label"><label for="appapplication.appname$text">应用名称：</label></th>
	                <td>    
	                    <input id="appapplication.appname" name="appapplication.appname" class="nui-textbox nui-form-input" required="true"  vtype="rangeLength:1,20"/>
	                    <input name="appapplication.appid" class="nui-hidden"/>
	                </td>
	                <th class="nui-form-label"><label for="appapplication.appcode$text">应用代码：</label></th>
	                <td >                        
	                    <input id="appapplication.appcode" name="appapplication.appcode" class="nui-textbox nui-form-input" required="true" vtype="rangeLength:1,20"/>
	                </td>
	                
	            </tr>
	            <tr class="odd">
	                <th class="nui-form-label"><label for="appapplication.opendate$text">开通日期：</label></th>
	                <td>
	                	<input id="appapplication.opendate" name="appapplication.opendate" class="nui-datepicker nui-form-input" allowInput="false"/>   
	                </td>
	                <th class="nui-form-label"><label for="appapplication.ipport$text">应用端口：</label></th>
	                <td >                        
	                    <input id="appapplication.ipport" name="appapplication.ipport" class="nui-textbox nui-form-input" vtype="range:0,65535" />
	                </td>
	                <!--<th class="nui-form-label"><label for="appapplication.protocoltype$text">协议类型：</label></th>
	                <td>
	                	<input id="appapplication.protocoltype" name="appapplication.protocoltype" id="combo1" class="nui-combobox nui-form-input" textField="text" valueField="id" 
	                    data="combo_protocoltype" value="http"/>                       
	                </td>-->
	            </tr>
	            <tr>
	                <th class="nui-form-label"><label for="appapplication.apptype$text">应用类别：</label></th>
	                <td> 
	                	<input id="appapplication.apptype" class="nui-dictcombobox nui-form-input" name="appapplication.apptype" value="0" 
                   		 valueField="dictID" textField="dictName" dictTypeId="ABF_APPTYPE"/>
	                </td>
	                <th class="nui-form-label"><label for="appapplication.isopen$text">是否开通：</label></th>
	                <td> 
	                	<input id="appapplication.isopen" class="nui-dictcombobox nui-form-input" name="appapplication.isopen" value="1" 
                   		 valueField="dictID" textField="dictName" dictTypeId="XD_0002"/>   
	                </td>
	            </tr>
	            <tr class="odd">
	                <th class="nui-form-label"><label for="appapplication.ipaddr$text">应用IP：</label></th>
	                <td>    
	                    <input id="appapplication.ipaddr" name="appapplication.ipaddr" class="nui-textbox nui-form-input" onvalidation="ipvalidation"/>
	                </td>
	                
	            </tr>
	            <tr>
	                <th class="nui-form-label"><label for="appapplication.url$text">应用上下文：</label></th>
	                <td colspan="3">                        
	                    <input id="appapplication.url" name="appapplication.url" class="nui-textbox nui-form-input" style="width:420px;" onvalidation="urlvalidation"/>
	                </td>
	            </tr>
	            <tr class="odd">
	                <th class="nui-form-label"><label for="appapplication.appdesc$text">应用描述：</label></th>
	                <td colspan="3">    
	                    <input id="appapplication.appdesc" name="appapplication.appdesc" class="nui-textarea nui-form-input" style="width:420px;height:100px;" vtype="rangeLength:1,255"/>
	                </td>
	            </tr>
	            <tr>
					<td colspan="4" style="padding-right:20px;text-align:right;spacing:5px;">
						<a class="nui-button" style="width:60px;" iconCls="icon-save" onclick="onOk()">保存</a>
	        			<span style="display:inline-block;width:25px;"></span>
					</td>
				</tr>
	        </table>
	    </div>
	</div>
    <script type="text/javascript">
    	var combo_protocoltype = [{id:"http",text:"http"},{id:"https",text:"https"}];
        nui.parse();
        var form = new nui.Form("form1");
        var appid = "<%= request.getParameter("realId") %>";
		var json = nui.encode({template:{appid:appid}});
        $.ajax({
            url: "com.bos.utp.framework.ApplicationManager.getApplication.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (text) {
                var o = nui.decode(text);
                form.setData(o);
                form.setChanged(false);
            }
        });
        function SaveData() {
            var o = form.getData(true,true);            

            form.validate();
            if (form.isValid() == false) return;

            var json = nui.encode(o);
            //alert(JSON.stringify(json));
            $.ajax({
                url: "com.bos.utp.framework.ApplicationManager.updateApplication.biz.ext",
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
       function ipvalidation(e){
        	if(e.value == "") return ;
        	 if (e.isValid) {
                var pattern = /^((0[0-9]|1[0-9]\d{1,2})|(2[0-5][0-5])|(2[0-4][0-9])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-5][0-5])|(2[0-4][0-9])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-4][0-9])|(2[0-5][0-5])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-4][0-9])|(2[0-5][0-5])|(\d{1,2}))$/;
                if (pattern.test(e.value) == false) {
                    e.errorText = "必须为合法的ip";
                    e.isValid = false;
                }
            }
        }
        
        function urlvalidation(e){
        	if(e.value == "") return;
        	if(e.isValid){
        		var pattern = /^\/\w{2,30}$/;
        		 if (pattern.test(e.value) == false) {
                    e.errorText = "必须以/开头,2-30个字符";
                    e.isValid = false;
                }
        	}
        }
    </script>
</body>
</html>