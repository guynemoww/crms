<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>机器设备新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"   />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>机器设备编号：</label>
			<input name="item.machineNo" id="item.machineNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>设备型号：</label>
			<input name="item.machineType" required="true" class="nui-textbox nui-form-input"  />
			
			<label>出厂时间：</label>
			<input name="item.factoryDate" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>购入价格：</label>
			<input name="item.buyPrice" required="true" class="nui-textbox nui-form-input"  dataType="currency" vtype="float;maxLength:20"/>
			
			<label>主要用途：</label>
			<input name="item.mainUse" required="true" class="nui-textbox nui-form-input"  />
			
			<label>监管情况：</label>
			<input name="item.superviseStatus" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0107"/>
			
			<label>租赁状态：</label>
			<input name="item.leaseStatus" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0108" />
			
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
