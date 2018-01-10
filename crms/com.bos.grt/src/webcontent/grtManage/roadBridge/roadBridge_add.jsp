<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>路桥、电费等收费权新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>收费单位名称：</label>
			<input name="item.chargingUnitName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
			<label>收费单位企业性质：</label>
			<input name="item.chargingUnitEnNature" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0136" />
			
			<label>收费合同编号：</label>
			<input name="item.chargingContractNo" id="item.chargingContractNo"  class="nui-textbox nui-form-input"  vtype="int;maxLength:30"/>
			
			<label>收费权起始日：</label><!--BUG #3027  -->
			<input name="item.beginDate" required="true" class="nui-datepicker nui-form-input" id="item.beginDate" format="yyyy-MM-dd" allowinput="false" onvaluechanged="dateChange" />
			
			<label>收费权终止日：</label><!--BUG #3027  -->
			<input name="item.endDate" required="true" class="nui-datepicker nui-form-input" id="item.endDate" format="yyyy-MM-dd" allowinput="false" onvaluechanged="dateChange" />
			
			<label>收费权比例（%）：</label>
			<input name="item.scaleOfCharges" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" /> 
			
			<label>备注：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
		
		/**
		 * 生效日期值发生改变
		 */
		function dateChange(e){
			var beginDate=nui.get("item.beginDate").getValue();
			var endDate=nui.get("item.endDate").getValue();
			if(beginDate!=""&&endDate!=""){
				if(CompareDueAndShengXiaoDate(beginDate,endDate)){
					nui.alert("收费权终止日必须大于收费权起始日");
					nui.get("item.endDate").setValue("");
					nui.get("item.beginDate").setValue("");
		  		}
			}
		}
	  
		/**
		 * 比较到期日期和生效日期
		 */
		function CompareDueAndShengXiaoDate(beginDate,endDate){
	  		if(nui.parseDate(beginDate)-nui.parseDate(endDate)<0){//到期日期小于生效日期
	  			return false;
	  		}else{
	  			return true;
	  		}
		}
	</script>
</body>
</html>
