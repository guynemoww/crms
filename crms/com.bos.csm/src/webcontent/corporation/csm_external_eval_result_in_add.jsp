<%@page pageEncoding="UTF-8"%>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): 王艳丽
  - Date: 2013-11-11 12:42:24
  - Description:
-->
<head>
<title>新增外部评级信息</title>
<%@include file="/common/nui/common.jsp"%>

</head>
<body>
<center>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input name="item.partyId" id="item.partyId" class="nui-hidden" />
		<fieldset>
	  <legend>
	    评级信息
	   </legend>
    <div class="nui-dynpanel" columns="4">
		    <label>评级日期：</label>
			<input id="item.ratingDt" name="item.ratingDt"  required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" onvalidation="onDayValidate"  />
			
		    <label>初始信用等级：</label>
			<input id="item.creditRatingCd" name="item.creditRatingCd" required="true" class="nui-dictcombobox nui-form-input" />
			
			<label>评级有效期起始日：</label>
			<input id="item.effectiveStartDt" name="item.effectiveStartDt" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" onvalidation="onDayValidate" />
			
			<label>评级有效期截止日：</label>
			<input id="item.effectiveEndDt" name="item.effectiveEndDt" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			
	</div>
	
    </fieldset>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0"> 
		    <a class="nui-button"  iconCls="icon-save" onclick="add">保存</a>
		    <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
	</div>

</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");

	var partyId = "<%=request.getParameter("partyId") %>";
	if (partyId) {
		nui.get("item.partyId").setValue(partyId); 
		//初始化信用等级
		var dictData = new Array();
		dictData[0] = {"text":"AAA","id":"AAA"};
		dictData[1] = {"text":"AA+","id":"AA+"};
		dictData[2] = {"text":"AA","id":"AA"};
		dictData[3] = {"text":"AA-","id":"AA-"};
		dictData[4] = {"text":"A+","id":"A+"};
		dictData[5] = {"text":"A","id":"A"};
		dictData[6] = {"text":"A-","id":"A-"};
		dictData[7] = {"text":"BBB+","id":"BBB+"};
		dictData[8] = {"text":"BBB","id":"BBB"};
		dictData[9] = {"text":"BBB-","id":"BBB-"};
		dictData[10] = {"text":"BB","id":"BB"};
		dictData[11] = {"text":"B","id":"B"};
		dictData[12] = {"text":"CCC","id":"CCC"};
		dictData[13] = {"text":"CC","id":"CC"};
		dictData[14] = {"text":"C","id":"C"};
		dictData[15] = {"text":"D","id":"D"};
		nui.get("item.creditRatingCd").setData(dictData);
	}
		
	function add() {
	    var startDate = nui.get("item.effectiveStartDt").value;
	    var endDate = nui.get("item.effectiveEndDt").value;
	    
	    if(startDate>endDate){
	        alert("起始日期不能大于终止日期！");
	        return;
	    }
	    
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.corporation.InternalRatingResult.insertInternalRatingResult.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            		return;
	            	} else {
	            		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	
     
function onDayValidate(e){
	 if (e.isValid){
	 	if(null != e.value && "" != e.value){
	 		var curDate="<%=GitUtil.getBusiDateStr()%> 00:00:00";
	 		if(e.value > curDate){
	 			e.errorText = "不能大于当前日期<%=GitUtil.getBusiDateStr()%>";
                e.isValid = false;
	 		}
	 	}
	 }
}

<%--
function setDictValue(data){
	var dictData = new Array();
	//循环Data
	for(var i = 0;i<data.length;i++){
		var obj = {"text":data.文本字段名,"id":data.值字段名称};
		dictData[i] = obj;
	}
	nui.get("item.creditRatingCd").setData(dictData);
}--%>

</script>
</body>
</html>