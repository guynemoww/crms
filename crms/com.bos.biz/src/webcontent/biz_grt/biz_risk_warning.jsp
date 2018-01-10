<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 叶奔
  - Date: 2014-05-13 19:24:48
  - Description:
-->
<head>
<title>提示</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>

	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input id="risk.riskWarningId" class="nui-hidden nui-form-input" name ="risk.riskWarningId">
		<input id="risk.applyId" class="nui-hidden nui-form-input" name ="risk.applyId">
		
		<fieldset>
			<legend>
				<span>客户信息检查：</span>
			</legend>
				<div class="nui-dynpanel" columns="4" id="table1" >
						
				<label class="nui-form-label"></label>	
				<label class="nui-form-label">是否黑名单</label>
				<div id="risk.isBlackList" colspan="2" style="font-size: 15pt"></div>
					
				<label class="nui-form-label"></label>	
				<label class="nui-form-label">信用评级</label>
				<div id="risk.isCreditRating" colspan="2" style="font-size: 15pt"></div>
					
				<label class="nui-form-label"></label>	
				<label class="nui-form-label">营业执照是否一个月内到期</label>
				<div id="risk.isBuslMaturityMonth" colspan="2" style="font-size: 15pt"></div>
					
				<label class="nui-form-label"></label>	
				<label class="nui-form-label">组织机构代码是否一个月内到期</label>
				<div id="risk.isCreditcardInspection" colspan="2" style="font-size: 15pt"></div>
					
				<label class="nui-form-label"></label>	
				<label class="nui-form-label">财报是否3个月以内</label>
				<div id="risk.isEarnings3Months" colspan="2" style="font-size: 15pt"></div>

		</fieldset>
		<fieldset>
			<legend>
				<span>业务信息检查：</span>
			</legend>
				<div class="nui-dynpanel" columns="4" id="table1" >
					<label class="nui-form-label"></label>	
					<label class="nui-form-label">是否有多头授信</label>
					<div id="risk.isMultiLoan" colspan="2" style="font-size: 15pt"></div>
				</div>
		</fieldset>
	</div>
		<script type="text/javascript">
			nui.parse();
			var form = new nui.Form("#form1");
			var applyId="<%=request.getParameter("applyId")%>";
			var json=nui.encode({"applyId":applyId});
			git.mask();
			 $.ajax({
	        	url:"com.bos.bizInfo.bizInfo.getRiskInfo.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	cache: false,
	        	success: function (mydata) {
	           	 	var o = nui.decode(mydata);
					if(o.risk.isBlackList!="0"){
						document.getElementById("risk.isBlackList").innerHTML = " √ ";
					}else{
						document.getElementById("risk.isBlackList").innerHTML = " × ";
					}
					if(o.risk.isCreditRating == ' '){
						document.getElementById("risk.isCreditRating").innerHTML = "未评级";
					}else{
						document.getElementById("risk.isCreditRating").innerHTML = o.risk.isCreditRating;
					}
					if(o.risk.isBuslMaturityMonth =='1'){
						document.getElementById("risk.isBuslMaturityMonth").innerHTML = " √ ";
					}else{
						document.getElementById("risk.isBuslMaturityMonth").innerHTML = " × ";
					}
					if(o.risk.isCreditcardInspection=="1"){
						document.getElementById("risk.isCreditcardInspection").innerHTML = " √ ";
					}else{
						document.getElementById("risk.isCreditcardInspection").innerHTML = " × ";
					}
					if(o.risk.isEarnings3Months=="1"){
						document.getElementById("risk.isEarnings3Months").innerHTML = " √ ";
					}else{
						document.getElementById("risk.isEarnings3Months").innerHTML = " × ";
					}
					if(o.risk.isMultiLoan=="1"){
						document.getElementById("risk.isMultiLoan").innerHTML = " √ ";
					}else{
						document.getElementById("risk.isMultiLoan").innerHTML = " × ";
					}
	           	 	git.unmask();
	        	}
	    	});
		</script>
</body>
</html>