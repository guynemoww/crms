<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>货权(仓单)新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>是否我行合作机构：</label>
			<input name="item.isMybankCoopeUnit" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
			
			<label>仓储机构：</label>
			<input name="item.storageOrg" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>仓单号码：</label>
			<input name="item.depotNo" id="item.depotNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>仓库名称：</label>
			<input name="item.storageName" required="true" class="nui-textbox nui-form-input" />
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>仓单金额：</label>
			<input name="item.depotMoney" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>货物类别：</label>
			<input name="item.cargoSort" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:50"/>
			
			<label>货物单位：</label>
			<!-- <input name="item.cargoUnits" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0109"/> -->
			<input name="item.cargoUnits" required="true" class="nui-textbox nui-form-input" />
			
			<label>货物数量：</label>
			<input name="item.cargoNum" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:10"/>
			
			<label>入库日期：</label>
			<input name="item.instorageDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false"/>
			
			<label>备注：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
	</script>
</body>
</html>
