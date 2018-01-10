<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>应收类新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>收款单位名称：</label>
			<input name="item.payeeName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>收款单位开户行名称：</label>
			<input name="item.payeeOpenbankName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>收款单位收款账号：</label>
			<input name="item.payeeNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>付款单位名称：</label>
			<input name="item.payerName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>付款单位企业性质：</label>
			<input name="item.payerEnterprisePro" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0124"/>
			
			<label>合同编号：</label>
			<input name="item.contractNo" id="item.contractNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:80" />
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>应收款余额：</label>
			<input name="item.accountsDueBalance" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>账龄（月）：</label>
			<input name="item.aging" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:3"/>
			
			<label>赊销发生时间：</label>
			<input name="item.beginDate" id="item.beginDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>应收款到期日：</label>
			<input name="item.endDate" id="item.endDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>备注：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
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
					nui.alert("应收款到期日必须大于赊销发生时间");
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
