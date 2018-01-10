<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>股票新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label>股票名称：</label>
			<input name="item.stockName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
			<label>股票代码：</label>
			<input name="item.stockCode" id="item.stockCode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:80"/>
			
			<label>发行单位：</label>
			<input name="item.issuedUnit" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>发行日期：</label>
			<input name="item.issuedDate"  class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false"/>
			
			<label>股票类型：</label>
			<input name="item.stockType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0117"/>
			
			<label>股票类别：</label>
			<input name="item.stockSort" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0118"/>
			
			<label>股权流通情况：</label>
			<input name="item.circulateCase" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_YWDB0119"/>
			
			<label>持股数量（股）：</label>
			<input name="item.stockCount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:15"/>
			
			<label>持股比重（%）：</label>
			<input name="item.stockRate"  class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" />
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>质押股票市值：</label>
			<input name="item.stockPrice" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>备注：</label>
			<textarea name="item.remark" class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");
		
	</script>
</body>
</html>
