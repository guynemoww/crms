<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 10:05:16
  - Description:
-->
<head>
<title>已核销资产回收</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
  	<div class="nui-dynpanel" columns="4">	
  		<label id="costType">费用类型：</label>
  		<input id="cost.costType" name="cost.costType"
  		class="nui-textbox nui-form-input" required="true"/>
  	</div>
    <div class="nui-dynpanel" columns="4">
		<label id="costAmt">费用金额：</label>
  		<input id="cost.costAmt" name="cost.costAmt"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="costCurrency">费用币种：</label>
  		<input id="cost.costCurrency" name="cost.costCurrency"
  		class="nui-textbox nui-form-input" required="true"/>
  
  		<label id="costDate">费用发生日期：</label>
  		<input id="cost.costDate" name="cost.costDate"
  		class="nui-datepicker nui-form-input" format="yyyy-MM-dd" required="true"/>
  
		<label id="costProject">费用项目：</label>
  		<input id="cost.costProject" name="cost.costProject"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="costInstruction">费用说明：</label>
  		<input id="cost.costInstruction" name="cost.costInstruction"
  		class="nui-textarea nui-form-input" required="true"/>
	</div>
			<div class="nui-toolbar" style="text-align:right;padding-right:70px;margin-top:3px;border:0">
				<a class="nui-button" iconCls="icon-save" onclick="save">保存</a>
				<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
</div>	
</center>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form");
		
		function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form");
		var o=form.getData();
		var json=nui.encode(o);
		// nui.alert(json);
		// return;
		$.ajax({
	            url: "com.bos.npl.book.AssetsBook.insertCost.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form");
	                nui.alert(jqXHR.responseText);
	            }
		});
		}
</script>
</body>
</html>