<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>特许经营权新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>特许经营起始日：</label><!--BUG #3022  -->
			<input name="item.txjyqsr" required="true"  class="nui-datepicker nui-form-input" id="item.txjyqsr" format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>特许经营终止日：</label><!--BUG #3022  -->
			<input name="item.txjyzzr" required="true"  class="nui-datepicker nui-form-input" id="item.txjyzzr" format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>出租车经营许可证：</label>
			<input name="item.czcjyxkz"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0127"/>
			
			<label>公路客运线路经营许可证：</label>
			<input name="item.glkyxljyxkz"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0128"/>
			
			<label>经营权信息描述：</label>
			<input name="item.jyqxxms" required="true" class="nui-textbox nui-form-input" vtype="maxLength:2000" />
			
			<label>备注：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:2000" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
		function checkDate(e){
			var beginDate=nui.get("item.txjyqsr").getValue();//生效日期
	  		var endDate=nui.get("item.txjyzzr").getValue();//到期日期
	  		if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("特许经营终止日必须大于特许经营起始日");
					nui.get("item.txjyqsr").setValue("");
					nui.get("item.txjyzzr").setValue("");
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
