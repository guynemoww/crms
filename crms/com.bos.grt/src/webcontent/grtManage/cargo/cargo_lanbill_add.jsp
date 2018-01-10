<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>货权(提单)新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>提单号码：</label>
			<input name="item.lanBillNo"  id="item.lanBillNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>货物名称：</label>
			<input name="item.cargoName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>货物单位：</label>
			<!-- <input name="item.cargoUnits" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0109"/> -->
			<input name="item.cargoUnits" required="true" class="nui-textbox nui-form-input" />
			
			<label>货物数量：</label>
			<input name="item.cargoNum" required="true" class="nui-textbox nui-form-input"  vtype="int;maxLength:10"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>提单金额：</label>
			<input name="item.sumMoney" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>发货日：</label>
			<input name="item.sendDate" id="item.sendDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>提货日：</label>
			<input name="item.deliveryDate" id="item.deliveryDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>提货人：</label>
			<input name="item.forwarder" required="true" class="nui-textbox nui-form-input" vtype="maxLength:10"/>
			
			<label>到/存货地：</label>
			<input name="item.address" required="true" class="nui-textbox nui-form-input" vtype="maxLength:80" />
			
			<label>备注：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
		function checkDate(e){
			var beginDate=nui.get("item.sendDate").getValue();//生效日期
	  		var endDate=nui.get("item.deliveryDate").getValue();//到期日期
	  		if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("提货日必须大于发货日");
					nui.get("item.sendDate").setValue("");
					nui.get("item.deliveryDate").setValue("");
		  		}
	  		}
		}
		/**
		 * 比较到期日期和生效日期
		 */
		function CompareDueAndShengXiaoDate(beginDate,endDate){
	  		if(nui.parseDate(endDate)-nui.parseDate(beginDate)<=0){//到期日期小于生效日期
	  			return false;
	  		}else{
	  			return true;
	  		}
		}
	</script>
</body>
</html>
