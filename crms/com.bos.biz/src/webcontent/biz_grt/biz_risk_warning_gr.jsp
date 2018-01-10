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
				<label class="nui-form-label">信用评级-经营性</label>
				<div id="risk.isCreditRatingJy" colspan="2" style="font-size: 15pt"></div>
					
				<label class="nui-form-label"></label>	
				<label class="nui-form-label">信用评级-消费性</label>
				<div id="risk.isCreditRatingXf" colspan="2" style="font-size: 15pt"></div>

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
					if(o.risk.isCreditRatingJy == ' '){
						document.getElementById("risk.isCreditRatingJy").innerHTML = "未评级";
					}else{
						document.getElementById("risk.isCreditRatingJy").innerHTML = o.risk.isCreditRatingJy;
					}
					if(o.risk.isCreditRatingXf == ' '){
						document.getElementById("risk.isCreditRatingXf").innerHTML = "未评级";
					}else{
						document.getElementById("risk.isCreditRatingXf").innerHTML = o.risk.isCreditRatingXf;
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