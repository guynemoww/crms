<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>合同约定扣款日变更申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	
	<fieldset>
		<legend>
	    	<span>合同信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    	
			<label class="nui-form-label">合同编号：</label>
			<input id="tbConContractInfo.contractNum" class="nui-text nui-form-input" name="tbConContractInfo.contractNum"/>
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbConContractInfo.productType" name="tbConContractInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
			
			<label class="nui-form-label">合同金额：</label>
			<input id="tbConContractInfo.contractAmt" name="tbConContractInfo.contractAmt"  class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">合同已用金额：</label>
			<input id="tbConContractInfo.conBalance" name="tbConContractInfo.conBalance"  class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">合同起期：</label>
			<input id="tbConContractInfo.beginDate" name="tbConContractInfo.beginDate" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">合同止期：</label>
			<input id="tbConContractInfo.endDate" name="tbConContractInfo.endDate" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">结息周期：</label>
			<input id="tbConLoanrate.interestCollectType" name="tbConLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
				   
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbConLoanrate.yearRate" class="nui-text nui-form-input" name="tbConLoanrate.yearRate"/>
			
			<label class="nui-form-label">经办机构：</label>
			<input id="tbConLoanChange.orgNum" name="tbConLoanChange.orgNum" class="nui-text nui-form-input" dictTypeId="org" />
			
			<label class="nui-form-label">客户经理：</label>
			<input id="tbConLoanChange.userNum" name="tbConLoanChange.userNum" class="nui-text nui-form-input" dictTypeId="user" />
			
			<label class="nui-form-label">合同调整类型：</label>
			<input id="tbConLoanChange.conChangeType" name="tbConLoanChange.conChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
			
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input" vtype="maxLength:999"/> 
			
	    </div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>变更前</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    
	    	<label class="nui-form-label">约定扣款日：</label>
			<input id="tbConLoanChange.oldRepayDay" class="nui-text nui-form-input" name="tbConLoanChange.oldRepayDay"/>
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
			
			<label class="nui-form-label">约定扣款日：</label>
			<input id="tbConLoanChange.newRepayDay" class="nui-textbox nui-form-input" 
					name="tbConLoanChange.newRepayDay" vtype="int;maxLength:2" required="true"/>
			
	    </div>
	</fieldset>
	
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	<%-- var contractId ="<%=request.getParameter("contractId") %>"; --%>
	initPage();
	//初始化页面
	function initPage(){
		//var form1 = new nui.Form("#form");
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
			}
        });

		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}
        
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("con_contract_info_save").setEnabled(false);
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveConLoanChange.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	
</script>
</body>
</html>