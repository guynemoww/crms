<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s):陈川
  - Date: 2016-06-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>债券新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>债券发行方类型：</label>
			<input name="item.singedType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0111"/>
			
			<label>债券发行机构：</label>
			<input name="item.singedUnit" required="true" class="nui-textbox nui-form-input"   vtype="maxLength:100"/>
			
			<label>债券号码：</label>
			<input name="item.bondNo" id="item.bondNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>记账式国债代售/管机构：</label>
			<input name="item.nationalSaveOrg"  class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>债券托管账号：</label>
			<input name="item.trusteeshipAcc"  class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>客户资金账号：</label>
			<input name="item.customerCashAcc"  class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>债券面额：</label>
			<input name="item.bondValue" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>债券年利率（%）：</label>
			<input name="item.bondRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" />
			
			<label>起息日：</label>
			<input name="item.beginDate" id="item.beginDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>到期日：</label>
			<input name="item.endDate" id="item.endDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>本金偿还方式：</label>
			<input name="item.repayType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0112"/>
			
			<label>付息方式：</label>
			<input name="item.intrWay" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0113"/>
			
			<label>是否可转让：</label>
			<input name="item.isTransfer" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
			
			<label>备注：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
		function checkDate(e){
			var beginDate=nui.get("item.beginDate").getValue();//生效日期
	  		var endDate=nui.get("item.endDate").getValue();//到期日期
	  		if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("到期日必须大于起息日");
					nui.get("item.beginDate").setValue("");
					nui.get("item.endDate").setValue("");
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
