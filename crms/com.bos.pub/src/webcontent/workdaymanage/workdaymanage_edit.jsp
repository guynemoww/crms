<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): zhaohuaiyuan@git.com.cn
  - Date: 2013-12-16

  - Description:TB_PUB_GRANT_PACKAGE, com.bos.pub.decision.TbPubGrantPackage-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="item.workdayManageId" id="item.workdayManageId" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.dataset.pub.TbPubWorkdayManage" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>年份：</label>
		<input name="item.workdayYear" required="true" class="nui-textbox nui-form-input" vtype="int;rangeLength:4,4" />

		<label>月份：</label>
		<input name="item.workdayMonth" required="true" class="nui-textbox nui-form-input" vtype="int;rangeLength:1,2" />

		<label>日期：</label>
		<input name="item.workdayDay" required="true" class="nui-textbox nui-form-input" vtype="int;rangeLength:1,2" />

		<label>日期类型：</label>
		<input id ="dateType" name="item.dateType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="DateType" onvaluechanged="dateTypeChange()"/>
      
        <label >节假日类型：</label>
		<input id="holidayType" name="item.holidayType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="HolidayType" />
		
		<label>描述：</label>
		<input name="item.workdayDisc" required="false" class="nui-textarea nui-form-input" vtype="maxLength:100" />
		
		
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
   <a class="nui-button"  iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		if ("<%=request.getParameter("view") %>"=="1") {
					form.setEnabled(false);
		            nui.get("btnSave").hide();
		}
				
		function initForm() {
		var json=nui.encode({"item":{"workdayManageId":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.dataset.pub.TbPubWorkdayManage"}});
			
		$.ajax({
	            url: "com.bos.pub.TbPubWorkdayManage.getItem.biz.ext",
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
		var json=nui.encode(o);
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.pub.TbPubWorkdayManage.saveItem.biz.ext",
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
	   }else if("03" ==dateType && "<%=request.getParameter("view") %>"=="0"){
	   nui.get("holidayType").enable();
	   }	
	}
	

	</script>
</body>
</html>
