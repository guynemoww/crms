<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2016-06-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>存货新增、编辑、查看</title>
</head>
<body>
	<div id="form1" >
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="tableDY">
			<label>存货单号：</label>
			<input name="item.inventoryNo"id="inventoryNoDY" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

			<label>存货数量：</label>
			<input name="item.inventoryNum" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:10" />
			
			<label>存货单位：</label>
			<input name="item.inventoryUnits" required="true" class="nui-textbox nui-form-input"  />
			
			<label>购入时间：</label>
			<input name="item.buyTime" class="nui-datepicker nui-form-input" id="item.buyTime" format="yyyy-MM-dd" allowinput="false"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>购入总价：</label>
			<input name="item.buyTotalPrice"  required="true" class="nui-textbox nui-form-input"  dataType="currency" vtype="float;maxLength:26;"/>
			
			<label>监管机构：</label>
			<input name="item.superviseUnit" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>备注：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
		</div>
		<div id="form2" >
		<div class="nui-dynpanel" columns="4" id="tableZY">
			<label>存货单号：</label>
			<input name="item.inventoryNo" id="inventoryNoZY" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>存货主要物种：</label>
			<input name="item.inventorySort" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>存货所在地：</label>
			<input name="item.inventoryAddress" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>币种：</label>
			<input name="item.currencyCd"  required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>购入总价：</label>
			<input name="item.buyTotalPrice" required="true" class="nui-textbox nui-form-input"  dataType="currency" vtype="float;maxLength:26;"/>
			
			<label>存货单位：</label>
			<input name="item.inventoryUnits" required="true" class="nui-textbox nui-form-input"  />
			
			<label>存货数量：</label>
			<input name="item.inventoryNum" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26" />
			
			<label>存货保管方式：</label>
			<input name="item.saveWay" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0125"/>
			
			<label>监管机构：</label>
			<input name="item.superviseUnit" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>备注说明：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
		    
	<script type="text/javascript">
		var form1;
		function init(){
			if(collType=="02"){//质押
				form1=new nui.Form("#form2");
				$("#form1").css("display","none");
				$("#form2").css("display","block");
			}else{
				form1=new nui.Form("#form1");
				$("#form2").css("display","none");
				$("#form1").css("display","block");
			}
		}
		
	
		
	</script>
</body>
</html>
