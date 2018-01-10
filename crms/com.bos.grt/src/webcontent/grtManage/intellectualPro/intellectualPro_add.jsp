<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>知识产权新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>知识产权证书号：</label>
			<input name="item.licenseNo" id="item.licenseNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>知识产权证书发证机构：</label>
			<input name="item.licenseName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>知识产权保护届满日期：</label>
			<input name="item.endDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false"/>
			
			<label>知识产权年限（年）：</label><!--BUG #3015  -->
			<input name="item.years" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:3"/>
			
			<label>产权规定内容简介：</label>
			<input name="item.summary" required="true" class="nui-textbox nui-form-input" vtype="maxLength:80"/>
			
			<label>备注：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
	
	</script>
</body>
</html>
