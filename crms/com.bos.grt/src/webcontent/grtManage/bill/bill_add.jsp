 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉 陈川
  - Date: 2016-05-11
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>票据新增、修改、查看</title>
</head>
<body>
	<div id="form1" >
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>票据号码：</label>
			<input name="item.billNo" id="item.billNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>出票人：</label>
			<input name="item.remitter" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200"/>
			
			<label>出票人帐号：</label>
			<input name="item.billAccount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>出票人开户行：</label>
			<input name="item.billBank" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>出票日期：</label><!--BUG #3011  -->
			<input name="item.beginDate"  id="item.beginDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>到期日期：</label>
			<input name="item.endDate" id="item.endDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false" onvaluechanged="checkDate"/>
			
			<label>收款人帐号：</label>
			<input name="item.payeeAccount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50"/>
			
			<label>收款人开户行：</label>
			<input name="item.payeeBank" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>是否连续背书：</label>
			<input name="item.isLxbs" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
			
			<label>票据金额：</label>
			<input name="item.billValue" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>备注说明：</label>
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
					nui.alert("到期日期必须大于出票日期");
					nui.get("item.beginDate").setValue("");
					nui.get("item.endDate").setValue("");
		  		}
		  		validateDate(beginDate,endDate);
	  		}
		}
		
		//两日期间隔一年内 ---需求变更
		function validateDate(beginDate,endDate){
 			beginDate=parseInt(beginDate.substr(0,4))+1+beginDate.substr(5,2)+beginDate.substr(8,2);
 			endDate=parseInt(endDate.substr(0,4))+endDate.substr(5,2)+endDate.substr(8,2);
// 			var date1=new Date(beginDate.replace(/-/,"/"));
// 			date1= date1.getFullYear()+1+"-"+date1.getMonth()+"-"+date1.getDate();
// 			var date2=new Date(endDate.replace(/-/,"/"));
// 			date2= date2.getFullYear()+"-"+date2.getMonth()+"-"+date2.getDate();
			if(endDate>beginDate){
				alert("票据到期日与出票日间隔应小于或等于一年!");
				nui.get("item.beginDate").setValue("");
				nui.get("item.endDate").setValue("");
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
