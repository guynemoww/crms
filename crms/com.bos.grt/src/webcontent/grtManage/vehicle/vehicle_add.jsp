<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>交通工具新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"   />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>购买合同编号：</label>
			<input name="item.buyContractNo"  class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>发票编号：</label>
			<input name="item.invoiceNo" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>合格证编号：</label>
			<input name="item.certificateNo" id="item.certificateNo" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:100"/>
			
			<label>发动机编号：</label>
			<input name="item.carRunNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>车辆牌号：</label>
			<input name="item.carNo"  class="nui-textbox nui-form-input" vtype="maxLength:50;" />
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>购入价格：</label>
			<input name="item.buyPrice" required="true" class="nui-textbox nui-form-input"  dataType="currency" vtype="float;maxLength:26;"/>
			
			<label>营运证编号：</label>
			<input name="item.operationNo" id="item.operationNo" class="nui-textbox nui-form-input"  vtype="maxLength:100"/>
			
			<label>营运证发证机关：</label>
			<input name="item.certiIssue" id="item.certiIssue" class="nui-textbox nui-form-input"  />
			
			<label>载重（吨）：</label>
			<input name="item.weightCapacity" id="item.weightCapacity" class="nui-textbox nui-form-input"  vtype="int;maxLength:7;"/>
			
			<label>备注：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
	
		    
	<script type="text/javascript">
		var	form1=new nui.Form("#form1");
		
		init();
		function init(){
			if(sortType=="040200"){//营运类汽车
				nui.get("item.operationNo").setRequired(true);
				nui.get("item.certiIssue").setRequired(true);
				nui.get("item.weightCapacity").setRequired(true);
			}else{
				nui.get("item.operationNo").setRequired(false);
				nui.get("item.certiIssue").setRequired(false);
				nui.get("item.weightCapacity").setRequired(false);
			}
		}
		
	</script>
</body>
</html>
