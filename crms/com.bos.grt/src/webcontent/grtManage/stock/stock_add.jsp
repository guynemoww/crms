<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2014-07-07
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>股权新增、修改、查看</title>
</head>
<body>
	<div id="form1">
		<input name="item.suretyKeyId" id="suretyKeyId" class="nui-hidden"  />
		<div class="nui-dynpanel" columns="4" id="table1">
			<label  id="stockCodeName">股权证号码：</label>
			<input name="item.stockCode" id="item.stockCode" required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:100" />
			
			<label>股权种类：</label>
			<input name="item.stockType" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0115"/>
			
			<label>币种：</label>
			<input name="item.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"/>
			
			<label>质押股权金额：</label>
			<input name="item.stockValue" id="item.stockValue" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency"/>
			
			<label>质押股权占股比例（%）：</label>
			<input name="item.stockPercent" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" />
			
			<label>股权性质：</label>
			<input name="item.stockQuality" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_YWDB0116"/>
			
			<label>发行企业：</label>
			<input name="item.issueName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>是否银行或上市公司股权：</label>
			<input name="item.isPublicCompany" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_0002" />
			
			<label>发行日期：</label>
			<input name="item.issuingDate" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd" allowinput="false"/>
			
			<label>到期日：</label>
			<input name="item.endDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" allowinput="false"/>
			
			<label>备注：</label>
			<textarea name="item.remark"  class="nui-textarea" vtype="maxLength:200" emptyText="请输入备注信息..." 
				style="width:300px;height:60px;" ></textarea>
		</div>
	</div>
					
		    
	<script type="text/javascript">
		var form1 = new nui.Form("#form1");

	</script>
</body>
</html>
