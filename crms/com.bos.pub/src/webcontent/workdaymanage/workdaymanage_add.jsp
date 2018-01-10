<%@page pageEncoding="UTF-8"%>

<!-- 
  - Author(s): zhaohuaiyuan@git.com.cn
  - Date: 2013-12-16
  - Description:工作日管理增加页面
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="2">	
		<label>年份：</label>
		<input id="workdayYear" name="item.workdayYear" required="true" class="nui-textbox nui-form-input" vtype="int;rangeLength:4,4" />

		<label>月份：</label>
		<input id="workdayMonth" name="item.workdayMonth" required="true" class="nui-textbox nui-form-input" vtype="int;rangeLength:1,2;range:1,12"/> 

		<label>日期：</label>
		<input id="workdayDay" name="item.workdayDay" required="true" class="nui-textbox nui-form-input" vtype="int;rangeLength:1,2;range:1,31" onvaluechanged="workdayDayChange()"/>

		<label>日期类型：</label>
		<input id ="dateType" name="item.dateType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="DateType" onvaluechanged="dateTypeChange()" emptyText="请选择"/>
		
		<label>节假日类型：</label>
		<input id="holidayType" name="item.holidayType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="HolidayType" emptyText="请选择"/>
		
		<label>描述：</label>
		<input name="item.workdayDisc" required="false" class="nui-textarea nui-form-input" vtype="maxLength:100"/>	
	</div>
</div>			
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
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
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
            url: "com.bos.pub.workdaymanage.addworkdaymanage.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		CloseWindow("ok");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
function dateTypeChange(){
	   var dateType = nui.get("dateType").getValue();
	   if("01" ==dateType || "02" ==dateType){
	   nui.get("holidayType").disable();
	   nui.get("holidayType").setValue("");	   
	   }else{
	   nui.get("holidayType").enable();
	   }	
	}
	
function workdayDayChange(){
		var workdayYear = nui.get("workdayYear").getValue();
		var workdayMonth = nui.get("workdayMonth").getValue();
		var workdayDay = nui.get("workdayDay").getValue();
	    var date = new Date(workdayYear,workdayMonth-1,workdayDay);
	    var year = nui.formatDate(date,'yyyy');
	    var month = nui.formatDate(date,'MM');
	    var day = nui.formatDate(date,'dd');
	    if("0" ==workdayMonth.toString().substr(0,1)){
	    	workdayMonth = workdayMonth.toString().substr(1);
	    }
	     if("0" ==workdayDay.toString().substr(0,1)){
	    	workdayDay = workdayDay.toString().substr(1);
	    }
	    if("0" == nui.formatDate(date,'MM').toString().substr(0,1)){
	   		 var month = nui.formatDate(date,'MM').toString().substr(1);
	   		
	   		
	    }
	 	if("0" == nui.formatDate(date,'dd').toString().substr(0,1)){
	   		 var day = nui.formatDate(date,'dd').toString().substr(1);
	   		
	    }
	
   		if(workdayYear!=year || workdayMonth!=month || workdayDay!=day)
   		{
   		
   			nui.alert("输入日期不合法，请从新输入");
   			nui.get("workdayDay").setValue("");
   		}
}





	</script>
</body>
</html>
