<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉 陈川
  - Date: 2016-05-11
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>其他质押资产新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>资产名称：</label>
			<input name="item.assetName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>资产价值：</label>
			<input name="item.assetValue" required="true" class="nui-textbox nui-form-input"  dataType="currency" vtype="float;maxLength:26;"/>
			
			<label>资产数量单位：</label>
			<input name="item.assetUnit" required="true" class="nui-textbox nui-form-input"  />
			
			<label>资产数量：</label>
			<input name="item.assetNum" required="true" class="nui-textbox nui-form-input"  vtype="float;maxLength:7"/>
			
			<label>备注说明：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:200"
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
	</script>
</body>
</html>
