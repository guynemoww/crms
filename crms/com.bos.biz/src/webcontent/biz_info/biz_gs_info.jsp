<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 16:35:54
  - Description:
-->
<head>
<title>公司客户业务申请基本信息</title>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<center>

	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="tbBizApply.applyId" class="nui-hidden nui-form-input" name ="tbBizApply.applyId"/>
		<input id="tbBizApply.partyId" class="nui-hidden nui-form-input" name ="tbBizApply.partyId"/>
		<input id="tbBizAmountApply.applyId" class="nui-hidden nui-form-input" name ="tbBizAmountApply.applyId"/>
		<input id="tbBizAmountApply.amountId" class="nui-hidden nui-form-input" name ="tbBizAmountApply.amountId"/>
		<div class="nui-dynpanel" columns="4" id="table1">
			<label class="nui-form-label">客户编号：</label>	
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	
				
			<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/>
			
			<label id="certt">证件类型：</label>
			<input id="party.certType" class="nui-text nui-form-input" name="party.certType"  dictTypeId="CDKH0002" />
			
			<label id="certn">证件号码：</label>
			<input id="party.certNum" class="nui-text nui-form-input" name="party.certNum" />
		
			<label id="credlevel">信用等级：</label>
			<input id="party.creditLevel" class="nui-text nui-form-input" name="party.creditLevel"  required="true"/>
			
			<label class="nui-form-label">业务发生方式：</label>
			<input id="tbBizApply.bizHappenType" name="tbBizApply.bizHappenType" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0001" />
			
			<label class="nui-form-label">综合授信额度编号：</label>
			<input id="tbBizApply.bizNum" class="nui-text nui-form-input" name="tbBizApply.bizNum"/>
			
			<label class="nui-form-label">币种：</label>
			<input id="tbBizAmountApply.currencyCd" name="tbBizAmountApply.currencyCd"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="CD000001"/>
			
			<label class="nui-form-label">综合授信额度（元）：</label>
			<input id="tbBizAmountApply.creditAmount" name="tbBizAmountApply.creditAmount" class="nui-text nui-form-input" vtype="float;maxLength:20"  dataType="currency"/>
			
			<label id="sqqx">申请期限：</label>
			<div style="width:80%">
			<input name="tbBizAmountApply.creditTerm" style="width:60%;float:left" id="tbBizAmountApply.creditTerm"  vtype="int;maxLength:4;range:1,12" class="nui-textbox nui-form-input" required="true" onvalidation="checkXwTerm()"/>
			<input name="tbBizAmountApply.cycleUnit" id="tbBizAmountApply.cycleUnit" style="width:40%;float:left"   data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="XD_GGCD6009" value="04"  enabled="false"/>
			</div>
			
			<label class="nui-form-label" id="guarantyType">担保方式：</label>
	        <input id="tbBizAmountApply.guarantyType" name="tbBizAmountApply.guarantyType" class="mini-newcheckbox" required="true" data="data" valueField="dictID" dictTypeId="CDZC0005"/>
		
	        <label class="nui-form-label">经办机构：</label>
			<input id="tbBizApply.orgNum" name="tbBizApply.orgNum" class="nui-text nui-form-input"  dictTypeId="org"/>
			
			<label class="nui-form-label">经办人：</label>
			<input id="tbBizApply.userNum" name="tbBizApply.userNum" class="nui-text nui-form-input"  dictTypeId="user"/>
			
			<label class="nui-form-label">申请日期：</label>
			<input id="tbBizApply.applyDate" name="tbBizApply.applyDate" class="nui-text nui-form-input"   required="true" format="yyyy-MM-dd" />
			
			<label id="maxCreditAmt_label">最高综合授信额：</label>
			<input id="creditLine.maxCreditAmt" name="creditLine.maxCreditAmt" required="true" class="nui-text"  dataType="currency"/>
			
			<label id="bankMaxCreditAmt_label">我行最高综合授信额：</label>
			<input id="creditLine.bankMaxCreditAmt" name="creditLine.bankMaxCreditAmt"  required="true" class="nui-text" dataType="currency"/>
			
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="limit_compute" iconCls="icon-edit" onclick="limitCompute">授信测算</a>
    		<a class="nui-button" id="biz_gs_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var applyId ="<%=request.getParameter("applyId") %>";//业务申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	
	var bizType ="<%=request.getParameter("bizType")%>";//01-单笔  02-综合授信 03-集团综合授信
	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>","ratingType":''});
		var o ;
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.getAmountInfoByApplyId.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async: false,
            success: function (mydata) {
          	    o = nui.decode(mydata);
            	form.setData(o);
            	nui.get("tbBizAmountApply.currencyCd").setValue("CNY");
            	nui.get("tbBizAmountApply.cycleUnit").setValue("04");
			}
        });
        searchZHSXInfo();
        if(o){
        debugger;
        	if(o.party && o.party.corpCustomerTypeCd != "1"){
        		$("#maxCreditAmt_label").hide();
        		nui.get("creditLine.maxCreditAmt").hide();
        		$("#bankMaxCreditAmt_label").hide();
        		nui.get("creditLine.bankMaxCreditAmt").hide();
        		nui.get("limit_compute").hide();
        	}else if(o.tbBizApply){
        		//getCreditLineMea(o.tbBizApply.applyId,o.tbBizApply.oldApplyId);
        		getCreditLineMea(o.tbBizApply.applyId);
        	}
        }
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("biz_gs_info_save").hide();
			nui.get("limit_compute").hide();
			form.setEnabled(false);
		}
		if(bizType=="02"||bizType=="03"){//综合授信
			$("#sqqx").text("授信有效期:");
		}
	}
	
	var groupCreditTerm;
	function searchZHSXInfo(){
 		var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>"});
		$.ajax({
		        url: "com.bos.bizApply.groupApply.searchZHSXInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        async:false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
			        if(mydata && mydata.data && mydata.data[0]){
			        	groupCreditTerm = mydata.data[0].CREDIT_TERM;
			        }
		        }
		});
	}
	function getCreditLineMea(measureId,applyId){
		var json = nui.encode({
				"measureId" : measureId,
				"applyId" : applyId
			});
		$.ajax({
				url : "com.bos.biz.Measure.getCreditLineMea.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				cache : false,
				success : function(data) {
					setCreditLine(data);
				}
			});
	}
	function setCreditLine(data){
		if (data&&data.measure) {
			nui.get("creditLine.maxCreditAmt").setValue(data.measure.b24);
			nui.get("creditLine.bankMaxCreditAmt").setValue(data.measure.b25);
		}else{
			nui.get("creditLine.maxCreditAmt").setValue(null);
			nui.get("creditLine.bankMaxCreditAmt").setValue(null);
		}
		nui.get("creditLine.maxCreditAmt").validate();
		nui.get("creditLine.bankMaxCreditAmt").validate();
	}
	
	function checkXwTerm(){
		var temp = nui.get("tbBizAmountApply.creditTerm").getValue();
    	if(groupCreditTerm && temp>groupCreditTerm){
    		nui.get("tbBizAmountApply.creditTerm").setValue("");
    		nui.alert("申请期限不能大于["+groupCreditTerm+"]");
    	}
	}
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("biz_gs_info_save").setEnabled(false);
		var o = form.getData();
		o.tbBizAmountApply.applyId=nui.get("tbBizApply.applyId").getValue();
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.bizInfo.bizInfo.saveAmoutInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("biz_gs_info_save").setEnabled(true);
        	}
        	alert("保存成功！");
        	initPage();
        	nui.get("biz_gs_info_save").setEnabled(true);
        }});
	}
	
	
	function limitCompute(){
		var data = form.getData();
		debugger;
		var url=nui.context+ "/biz/measure/meaCreditLine.jsp?applyId="+data.tbBizApply.applyId;
		var partyId = data.tbBizApply.partyId;
		var json = nui.encode({"partyId":partyId});
			$.ajax({
		        url: "com.bos.biz.Measure.getFinanceCount.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.count>0){
		        		url=nui.context+ "/biz/measure/meaCreditLine2.jsp?applyId="+data.tbBizApply.applyId;
		        	}
			        nui.open({
						url : url,
						title : "法人客户授信测算",
						width : 800,
						height : 600,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.getData();
								setCreditLine(data);
							}else {
								setCreditLine();
							}
						}
					});
		        }	
		     });
		
	}
</script>
</body>
</html>