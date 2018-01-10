<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2016-06-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>出口退税新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>资产名称：</label>
			<input name="item.assetName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>出口退税账户：</label>
			<input name="item.cktszh" id="item.cktszh" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>退税金额：</label>
			<input name="item.tsje" required="true" class="nui-textbox nui-form-input"  dataType="currency" vtype="float;maxLength:26;"/>
			
			<label>出口货物报关单：</label>
			<input name="item.ckhwbgd"  class="nui-textbox nui-form-input"  vtype="maxLength:50"/>
			
			<label>增值税专用发票：</label>
			<input name="item.zzszyfp" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>税收缴款书：</label>
			<input name="item.ssjks"  class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>外汇核销单：</label>
			<input name="item.whhxd" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
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
