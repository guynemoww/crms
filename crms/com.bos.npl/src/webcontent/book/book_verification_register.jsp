<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 10:03:49
  - Description:
-->
<head>
<title>已核销资产登记</title>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
  	<div class="nui-dynpanel" columns="4">	
  		<label id="">客户名称：</label>
  		<div>
  			<input id="" name="" class="nui-textbox nui-form-input" style="width:55%" required="false"/>
  			<a id="" class="nui-button" style="width:25%"  onclick="">查看</a>
  		</div>
  		
		<label id="contractId">合同号：</label>
  		<input id="verificationRegister.contractId" name="verificationRegister.contractId"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="verificationPrincipalAmount">核销本金：</label>
  		<input id="verificationRegister.verificationPrincipalAmount" name="verificationRegister.verificationPrincipalAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
  		<label id="verificationInterestAmount">核销利息：</label>
  		<input id="verificationRegister.verificationInterestAmount" name="verificationRegister.verificationInterestAmount"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="verificationDate">核销日期：</label>
  		<input id="verificationRegister.verificationDate" name="verificationRegister.verificationDate"
  		class="nui-datepicker nui-form-input" format="yyyy-MM-dd" required="true" />
  
		<label id="closureApprovalNum">结案核准编号：</label>
  		<input id="verificationRegister.closureApprovalNum" name="verificationRegister.closureApprovalNum"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="agreeApprovalNum">同意核销文号：</label>
  		<input id="verificationRegister.agreeApprovalNum" name="verificationRegister.agreeApprovalNum"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="closureApprovalDate">结案核准日期：</label>
  		<input id="verificationRegister.closureApprovalDate" name="verificationRegister.closureApprovalDate"
  		class="nui-datepicker nui-form-input" format="yyyy-MM-dd" required="true"/>
  
  		<label id="closureAmt">结案金额：</label>
  		<input id="verificationRegister.closureAmt" name="verificationRegister.closureAmt"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="judgeBasis">认定依据：</label>
  		<input id="verificationRegister.judgeBasis" name="verificationRegister.judgeBasis"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="evidentiaryMaterial">证明材料：</label>
  		<input id="verificationRegister.evidentiaryMaterial" name="verificationRegister.evidentiaryMaterial"
  		class="nui-textarea nui-form-input" required="true"/>
  		
		<label id="remark">备注：</label>
  		<input id="verificationRegister.remark" name="verificationRegister.remark"
  		class="nui-textarea nui-form-input" required="true"/>
  
  		<label id="userNum">经办人：</label>
  		<input id="verificationRegister.userNum" name="verificationRegister.userNum"
  		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="registerDate">建账日期：</label>
  		<input id="verificationRegister.registerDate" name="verificationRegister.registerDate"
  		class="nui-datepicker nui-form-input" format="yyyy-MM-dd" required="true"/>
	</div>
			<div class="nui-toolbar" style="text-align:right;padding-right:70px;margin-top:3px;border:0">
				<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
				<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
</div>	
<script type="text/javascript">
		nui.parse();
		 var form = new nui.Form("#form");
		
		function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form");
		var o=form.getData();
		var json=nui.encode(o);
		// nui.alert(json);
		// return;
		$.ajax({
	            url: "com.bos.npl.book.AssetsBook.insertVerificationRegisterBook.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form");
	                nui.alert(jqXHR.responseText);
	            }
		});
		}
		</script>
</body>
</html>