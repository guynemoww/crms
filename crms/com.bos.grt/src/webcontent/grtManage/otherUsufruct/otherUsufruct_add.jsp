<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉 陈川
  - Date: 2016-05-11
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>其他受益权新增、编辑、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>权属证编号：</label>
			<input id="item.ownershipNum" name="item.ownershipNum"  required="true" vtype="maxLength:90" class="nui-textbox nui-form-input"  />
			
			<label>收益权起始日：</label><!--BUG #3024  -->
			<input name="item.syqqsr" id="item.syqqsr" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>收益权终止日：</label><!--BUG #3024  -->
			<input name="item.syqzzr" id="item.syqzzr" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>收益信息描述：</label>
			<input name="item.syxxms" required="true" class="nui-textbox nui-form-input" vtype="maxLength:2000" />
			
			<label>备注：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:2000" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
		
		function checkDate(e){
			var beginDate=nui.get("item.syqqsr").getValue();//生效日期
	  		var endDate=nui.get("item.syqzzr").getValue();//到期日期
	  		if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("收益权终止日必须大于收益权起始日");
					nui.get("item.syqqsr").setValue("");
					nui.get("item.syqzzr").setValue("");
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
