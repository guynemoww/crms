<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@include file="/common/nui/common.jsp" %>
<title>
      日志信息查看
</title>
</head>
<body>
<div id="form1">
<input type="hidden" name="acbatchmonitor" class="nui-hidden" />
	
<!--<input name="acbatchmonitor.fileString"  class="nui-textarea"  enabled="true" height="100%"/>-->
<textarea class="nui-textarea" name="acbatchmonitor.fileString" enabled="true" height="90%" width="100%"
style="border-top:1px solid #8ba0bc;border-bottom:none;"></textarea>		
   
</div>
<!--				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>-->
<div class="nui-toolbar" style="width:100%;text-align:right;padding-top:10px;padding-bottom:5px;" borderStyle="border:0;">
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	<span style="display:inline-block;width:25px;"></span>
</div>	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    
    initForm();
	

function initForm() {
	var json=nui.encode({"acbatchmonitor":{"jobId":"<%=request.getParameter("jobId") %>"}
						,"v":"<%=request.getParameter("v") %>"});
	
	$.ajax({
        url: "com.bos.batch.acbatchmonitorbiz.getDetailLog.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(nui.decode(text));
        		
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
