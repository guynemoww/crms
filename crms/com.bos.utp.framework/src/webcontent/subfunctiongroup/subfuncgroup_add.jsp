<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-01 10:35:09
  - Description:
-->
<head>
</head>
<body>
<div style="padding-top:1px">
	<div id="form1">
		<input id="acfuncgroup.acFuncgroup.funcgroupid" name="acfuncgroup.acFuncgroup.funcgroupid" class="nui-hidden" />
        <table style="width:100%;height:98%;table-layout:fixed;" class="nui-form-table" >
            <tr>
                <th class="nui-form-label"><label for="appfuncgroup.funcgroupname$text">子功能组名称：</label></th>
                <td>    
                    <input id="acfuncgroup.funcgroupname" name="acfuncgroup.funcgroupname" class="nui-textbox nui-form-input" required="true"  vtype="rangeLength:1,20"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="padding-right:20px;text-align:right;">    
                    <a class="nui-button"  iconCls="icon-save" onclick="onOk()">保存</a>
			        <span style="display:inline-block;width:25px;"></span>
			        <a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">取消</a>
                </td>
            </tr>
        </table>
        <%--<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	        <a class="nui-button" iconCls="icon-save" onclick="onOk()">保存</a>
	        <span style="display:inline-block;width:25px;"></span>
	        <a class="nui-button"  iconCls="icon-cancel" onclick="onCancel()">取消</a>
	    </div>  --%>      
    </div>
</div>
    <script type="text/javascript">
        nui.parse();
        var form = new nui.Form("form1");
        
        function SaveData() {
            var o = form.getData(true,true);            
			
            form.validate();
            if (form.isValid() == false) return;

            var json = nui.encode(o);
            $.ajax({
                url: "com.bos.utp.framework.FuncGroupManager.saveSubFuncGroup.biz.ext",
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
        
        function SetData(data) {
            //跨页面传递的数据对象，克隆后才可以安全使用
            data = nui.clone(data);
            document.getElementById("acfuncgroup.acFuncgroup.funcgroupid").value = data.parentgroupid;
        }
    </script>
</body>
</html>