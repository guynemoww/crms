<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2016-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>基金新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>基金名称：</label>
			<input name="item.fundName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>基金代码：</label>
			<input name="item.fundCode" id="fundCode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>发行单位：</label>
			<input name="item.issuedUnit" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>发行日期：</label>
			<input name="item.issuedDate"  class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false"/>
			
			<label>基金分类：</label>
			<input name="item.fundSort" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0120"/>
			
			<label>基金类型：</label>
			<input name="item.fundType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0121"/>
			
			<label>是否流通：</label>
			<input name="item.isCirculation" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>质押基金市值：</label>
			<input name="item.fundPrice" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
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
