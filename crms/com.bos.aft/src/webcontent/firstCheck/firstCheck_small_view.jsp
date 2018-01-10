<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-11
  - Description:
-->
<head>
<title>小企业信贷中心贷后首次检查申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbAftFirstCheck.firstCheckId" class="nui-hidden nui-form-input" name ="tbAftFirstCheck.firstCheckId"/>
	
	<fieldset>
		<legend>
	    	<span>客户基本信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    
	    	<label class="nui-form-label">客户编号：</label>
			<input id="tbCsmParty.partyNum" class="nui-text nui-form-input" name="tbCsmParty.partyNum"/>
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
			
			<!-- <label class="nui-form-label">客户类型：</label>
			<input id="tbCsmParty.partyTypeCd" name="tbCsmParty.partyTypeCd" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_KHCD1001"/>
				   
			<label class="nui-form-label">证件类型：</label>
			<input id="tbCsmNaturalPerson.certType" name="tbCsmNaturalPerson.certType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_KHCD1001"/> -->
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmNaturalPerson.certNum" class="nui-text nui-form-input" name="tbCsmNaturalPerson.certNum"/>
			
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt" dataType="currency" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye" dataType="currency" class="nui-text nui-form-input"/> 

	    </div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>授信资金用途</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    
	    	<label class="nui-form-label">借款用途：</label>
			<input id="tbConContractInfo.loanUse" name="tbConContractInfo.loanUse" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1007"/>
				   
			<label class="nui-form-label">实际用途详情：</label>
			<input  id="tbAftFirstCheck.loanUse" name="tbAftFirstCheck.loanUse" class="nui-text nui-form-input"  /> 
			
			<label class="nui-form-label">是否按计划使用：</label>
			<input id="tbAftFirstCheck.isSame" name="tbAftFirstCheck.isSame" 
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">是否挪用贷款：</label>
			<input id="tbAftFirstCheck.isEmbezzle" name="tbAftFirstCheck.isEmbezzle" 
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">支付方式：</label>
			<input id="tbAftFirstCheck.payWay" name="tbAftFirstCheck.payWay" 
					class="nui-text nui-form-input" dictTypeId="CDXY0144"  />
					
			<!-- <label class="nui-form-label">支付金额：</label>
			<input  id="tbAftFirstCheck.payAmt" name="tbAftFirstCheck.payAmt" class="nui-text nui-form-input" 
					vtype="float;maxLength:20" dataType="currency" /> 
					
			<label class="nui-form-label">支付对象：</label>
			<input  id="tbAftFirstCheck.payObject" name="tbAftFirstCheck.payObject" class="nui-text nui-form-input"  /> 
			
			<label class="nui-form-label">支付日期：</label>
			<input id="tbAftFirstCheck.payDate" name="tbAftFirstCheck.payDate" allowInput="false" 
					class="nui-text nui-form-input" format="yyyy-MM-dd"/> -->
			
	    </div> 
	    
	    <div class="nui-dynpanel" columns="1" id="table22">
	    	<%@include file="/aft/normalCheck/money_use_list.jsp"%>
	    </div> 
	    
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
	    
	    	<label class="nui-form-label">检查日期：</label>
			<input id="tbAftFirstCheck.checkDate" name="tbAftFirstCheck.checkDate"  
					class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"/>
			
			<label class="nui-form-label">检查地点：</label>
			<input  id="tbAftFirstCheck.checkPlace" name="tbAftFirstCheck.checkPlace" class="nui-text nui-form-input"  /> 
			
			<label class="nui-form-label">检查次数：</label>
			<input  id="tbAftFirstCheck.checkCount" name="tbAftFirstCheck.checkCount" class="nui-text nui-form-input"  /> 
			
			<label class="nui-form-label">检查情况说明：</label>
			<input  id="tbAftFirstCheck.checkResult" name="tbAftFirstCheck.checkResult" disabled="true" class="nui-text nui-form-input"  /> 
			
			<label class="nui-form-label">系统录入时间：</label>
			<input id="tbAftFirstCheck.createDate" name="tbAftFirstCheck.createDate" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"/>
			
			
	    	<!-- <label class="nui-form-label">检查方式：</label>
			<input id="tbAftFirstCheck.checkWay" name="tbAftFirstCheck.checkWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  />
						
			<label class="nui-form-label">检查人：</label>
			<input  id="tbAftFirstCheck.checkPerson" name="tbAftFirstCheck.checkPerson" required="true" class="nui-textbox nui-form-input"  /> 
			 -->	
					
			
	    </div>
	    
	</fieldset>

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var firstCheckId ="<%=request.getParameter("firstCheckId") %>";
	<%-- var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识  --%>
	var partyId;
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"firstCheckId":firstCheckId});
		$.ajax({
            url: "com.bos.aft.firstCheck.findFirstCheck.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	partyId = o.tbAftFirstCheck.partyId;
            	form.setData(o);
            	query1();
            }
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		/* if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}  */
        
	}
	
	function query1(){
  		
  		var o = form.getData();
		var json = nui.decode({"partyId":partyId});
  		var grid5 = nui.get("grid5");
		grid5.load(json);
	}
	
</script>

</body>
</html>