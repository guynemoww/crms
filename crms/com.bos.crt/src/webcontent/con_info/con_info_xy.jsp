<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 16:43:35
  - Description:
-->
<head>
<title>协议基本信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="tbBizApprove.applyId" class="nui-hidden nui-form-input" name ="tbBizApprove.applyId"/>
		<input id="party.partyId" class="nui-hidden nui-form-input" name ="party.partyId"/>
		<input id="conInfo.contractId" class="nui-hidden nui-form-input" name ="conInfo.contractId"/>
		<div class="nui-dynpanel" columns="4" id="table1">
			
			<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/>
			
			<label class="nui-form-label">客户编号：</label>	
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	

			<label class="nui-form-label">综合授信额度编号：</label>
			<input id="tbBizApprove.approvalNum" class="nui-text nui-form-input" name="tbBizApprove.approvalNum"/>
			
			<label class="nui-form-label">综合授信协议编号：</label>
			<input id="conInfo.contractNum" class="nui-text nui-form-input" name="conInfo.contractNum"/>
			
			<label class="nui-form-label">纸质合同编号：</label><!--默认反显综合授信协议编号，可修改-->
			<input id="conInfo.paperConNum" class="nui-textbox nui-form-input" name="conInfo.paperConNum" required="true"/>
			
			<label class="nui-form-label">币种：</label>
			<input id="tbBizAmountApprove.currencyCd" name="tbBizAmountApprove.currencyCd" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" enabled="false"/>
			
			<label class="nui-form-label">综合授信额度：</label>
			<input id="tbBizAmountApprove.creditAmount" name="tbBizAmountApprove.creditAmount"  class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">期限(月)：</label>
			<input id="tbBizAmountApprove.creditTerm" name="tbBizAmountApprove.creditTerm" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">额度起期：</label>
			<input id="tbBizApprove.validDate" name="tbBizApprove.validDate" class="nui-datepicker nui-form-input" enabled="false" />
			
			<label class="nui-form-label">额度止期：</label>
			<input id="tbBizApprove.endDate" name="tbBizApprove.endDate" class="nui-datepicker nui-form-input"  enabled="false"/>
	
			<label class="nui-form-label">签约日期：</label>
			<input id="conInfo.contractDate" name="conInfo.contractDate" class="nui-datepicker nui-form-input"  allowInput="false" required="true" onblur="datechg"/>
			
			<label class="nui-form-label">签约地点：</label>
			<input id="conInfo.contractAddress" class="nui-textarea nui-form-input" name="conInfo.contractAddress"  required="true"/>
			
			<label class="nui-form-label" id="guarantyType">担保方式：</label>
	        <input id="tbBizAmountApprove.guarantyType" name="tbBizAmountApprove.guarantyType" data="data" valueField="dictID" 
			class="nui-newcheckbox nui-form-input" dictTypeId="CDZC0005" enabled="false"/>
			
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_credit_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var contractId ="<%=request.getParameter("contractId") %>";//协议申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"contractId":"<%=request.getParameter("contractId")%>"});
		$.ajax({
            url: "com.bos.conInfo.conInfoSxxy.getConInfoByContarctId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	o.conInfo.contractNumA=o.conInfo.contractNum;
            	form.setData(o);
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_credit_info_save").hide();
			form.setEnabled(false);
		}
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("con_credit_info_save").setEnabled(false);
		var o = form.getData();
		o.conInfo.applyId=nui.get("tbBizApprove.applyId").getValue();
		o.conInfo.partyId=nui.get("party.partyId").getValue();
		o.conInfo.contractId=contractId;
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.conInfo.conInfoSxxy.saveConInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_credit_info_save").setEnabled(true);
        	}
        	alert("保存成功！");
        	nui.get("con_credit_info_save").setEnabled(true);
        }});
	}
	//签约日期不能小于额度起期
	function datechg(){
		var validDate = nui.get("tbBizApprove.validDate").getValue();
		var conDate = nui.get("conInfo.contractDate").getValue();
		if(conDate!=null && conDate!= ''){
			if(validDate>conDate){
				nui.get("conInfo.contractDate").setValue('');
				nui.alert("签约日期不能小于额度起期");
			}
		}
	}
</script>
</body>
</html>