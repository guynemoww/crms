<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2016-05-11
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>其他抵押资产新增、编辑、查看</title>
</head>
<body>
	<div id="form1" >
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"   />
		<div class="nui-dynpanel" columns="4" id="table1">
			
			<label>权属证编号：</label>
			<input id="item.ownershipNum" name="item.ownershipNum"  required="true" vtype="maxLength:90" class="nui-textbox nui-form-input"  />

			<label>资产数量：</label>
			<input name="item.assetNum"  class="nui-textbox nui-form-input" vtype="int;maxLength:10" />
			
			<label>资产数量单位：</label>
			<input name="item.assetNumUnits"  class="nui-textbox nui-form-input"  />
			
			<label>资产所在地：</label>
			<input name="item.assetAddress"  class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>资产使用情况：</label>
			<input name="item.useStatus" class="nui-textbox nui-form-input" />
			
			<label>资产描述：</label>
			<input name="item.assetDes" required="true" class="nui-textbox nui-form-input" />
			
			<label>备注说明：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
	</script>
</body>
</html>
