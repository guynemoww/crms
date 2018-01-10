<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2016-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>林权新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>承包经营权人：</label><!--BUG #3018  -->
			<input name="item.cbjyqr" required="true" id="item.cbjyqr" class="nui-textbox nui-form-input"  allowinput="false" enabled="false"/>
			
			<label>不动产权证书编号：</label>
			<input name="item.forestNo" required="true" id="item.forestNo" class="nui-textbox nui-form-input"  />
			
			<label>币种：</label><!--BUG #3020   -->
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>年承包费用：</label><!--BUG #3019  -->
			<input name="item.yearJobCost" required="true" class="nui-textbox nui-form-input"  dataType="currency" vtype="float;maxLength:26;"/>
			
			<label>承包经营起始日：</label><!--BUG #3020   -->
			<input name="item.beginDate" required="true" class="nui-datepicker nui-form-input" id="item.beginDate" format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>承包经营终止日：</label><!--BUG #3020   -->
			<input name="item.endDate" required="true" class="nui-datepicker nui-form-input" id="item.endDate" format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>产权人：</label><!--BUG #3019  -->
			<input name="item.licenseOwner"  required="true"  class="nui-textbox nui-form-input" vtype="maxLength:30" />
			
			<label>林地出让金缴纳状态：</label><!--BUG #3020   -->
			<input name="item.landPaymentState" required="true"  class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0135"/>
			
			<label>林分起源：</label>
			<input name="item.forestOrigin" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>林木蓄积量：</label>
			<input name="item.forestMeasure"  class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>林业采伐指标：</label>
			<input name="item.forestryIndex"  class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
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
					nui.alert("承包经营终止日必须大于承包经营起始日");
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
