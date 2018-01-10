<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@include file="/common/nui/common.jsp" %>
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-06 14:32:57
  - Description:
-->
<head>
</head>
<body>
	<div class="nui-fit">
		<div id="entrance_tabs" class="nui-tabs" activeIndex="0" style="width:100%;height:90%;" bodyStyle="padding:0;border:0;">
		    <div name="form_tab" title="表单" url="<%=request.getContextPath() %>/utp/framework/function/form_list.jsp" visible="true" style="height;100%;">
		    </div>
		    <div name="view_tab" title="视图" url="<%=request.getContextPath() %>/utp/framework/function/view_list.jsp" visible="true" >
		    </div>
		    <div name="startprocess_tab" title="启动流程" url="<%=request.getContextPath() %>/utp/framework/function/startprocess_list.jsp" visible="true" >
		    </div>
		</div>
		<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
	        <a class="nui-button" style="width:60px;" iconCls="icon-save" onclick="onOk()">确定</a>
	        <span style="display:inline-block;width:25px;"></span>
	        <a class="nui-button" style="width:60px;" iconCls="icon-cancel" onclick="onCancel()">取消</a>
	    </div>
    </div>
</body>
</html>
<script type="text/javascript">
	function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }

    function onOk() {
    	if(getData()) CloseWindow("ok");        
    }
    function onCancel() {
        CloseWindow("cancel");
    }
    
    function getData(){
   		var tabs = nui.get("entrance_tabs");
   	 	var tab = tabs.getTab(tabs.activeIndex);
   	 	var iframe = tabs.getTabIFrameEl(tab);
   	 	var selectdata = iframe.contentWindow.getSelectedData();
   	 	selectdata = nui.clone(selectdata);
    	return selectdata;
    }
</script>