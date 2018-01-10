<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): yangyong
  - Date: 2013-03-01 17:43:27
  - Description:
-->
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/utp/org/icons/icon.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>日历控件</title>
</head>
<body>
	<div id="calendar1" showDaysHeader="false" showFooter="true" showClearButton="false" showOkButton="false" class="mini-calendar"  ondateclick="onDateClick"></div>
<!-- 
<div class="nui-toolbar" style="width:212px;text-align:center;">
	<input class="nui-button" style="width:60px;" text="today" />
	<input class="nui-button" style="width:60px;" text="ok" />
</div>
 -->
<script type="text/javascript">
    nui.parse();
    
    var contextPath = "<%=request.getContextPath() %>";
    var calendar = nui.get("calendar1");
    
    (function(){
    	var ok = $(".mini-calendar-todayButton");
    	ok.bind("click", function(){
    		calendar.setValue(new Date());
    		CloseWindow("ok");
    	});
    	var clear = $(".mini-calendar-clearButton");
    	clear.bind("click", function(){
    		calendar.setValue("");
    		CloseWindow("ok");
    	});
    })();
    
    function SetData(data){
    	data = nui.clone(data);
    	if(data){
    		var date = nui.parseDate(data);
    		if(date){
	    		calendar.setValue(date);
	    		return ;
    		}
    	}
    	calendar.setValue(new Date());
    }
    function GetData(){
       return calendar.getValue();
    }
    function onDateClick(e) {
    	CloseWindow("ok");
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
</script>

</body>
</html>
