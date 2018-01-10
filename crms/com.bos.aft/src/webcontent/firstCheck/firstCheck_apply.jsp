<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-04
  - Description:
-->
<head>
<title>首次检查申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbAftFirstCheck.firstCheckId" class="nui-hidden nui-form-input" name ="tbAftFirstCheck.firstCheckId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	
	<fieldset>
		<legend>
	    	<span>客户基本信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4">
		    <label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
			
			<label class="nui-form-label">客户类型：</label>
			<input id="tbCsmParty.partyTypeCd" name="tbCsmParty.partyTypeCd" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_KHCD1001"/>
		</div>
			   
		<div id="type01" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="orgRegisterCd01" name="orgRegisterCd01" class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmCorporation.orgRegisterCd" class="nui-text nui-form-input" name="tbCsmCorporation.orgRegisterCd"/>
		</div>
		
		<div id="type02" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="tbCsmNaturalPerson.certType" name="tbCsmNaturalPerson.certType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmNaturalPerson.certNum" class="nui-text nui-form-input" name="tbCsmNaturalPerson.certNum"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt" dataType="currency" class="nui-text nui-form-input"/>
		
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye" dataType="currency" class="nui-text nui-form-input"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据起期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">借据止期：</label>
			<input id="tbLoanSummary.endDate" name="tbLoanSummary.endDate" class="nui-text nui-form-input"  />
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">贷款用途：</label>
				<input id="tbConContractInfo.loanUse" name="tbConContractInfo.loanUse" data="data" valueField="dictID" 
					   class="nui-text nui-form-input" dictTypeId="XD_SXCD1007"/>
		</div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>授信资金用途</span>
	    </legend>
	    <%@include file="/aft/normalCheck/money_use_list.jsp"%>
	    
	    <!-- <div class="nui-dynpanel" columns="4" id="table2">
	    
	    	
			<label class="nui-form-label">实际用途详情：</label>
			<input  id="tbAftFirstCheck.loanUse" name="tbAftFirstCheck.loanUse" required="true" class="nui-textarea nui-form-input"  /> 
			
			<label class="nui-form-label">是否与审批用途一致：</label>
			<input id="tbAftFirstCheck.isSame" name="tbAftFirstCheck.isSame" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">支付方式：</label>
			<input id="tbAftFirstCheck.payWay" name="tbAftFirstCheck.payWay" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDXY0144"  />
					
			<label class="nui-form-label">支付对象1：</label>
			<input  id="tbAftFirstCheck.payObject" name="tbAftFirstCheck.payObject" required="true" class="nui-textbox nui-form-input"  /> 
			
			<label class="nui-form-label">支付金额1：</label>
			<input  id="tbAftFirstCheck.payAmt" name="tbAftFirstCheck.payAmt" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="currency" /> 
					
			<label class="nui-form-label">支付对象2：</label>
			<input  id="tbAftFirstCheck.payObject2" name="tbAftFirstCheck.payObject2" required="true" class="nui-textbox nui-form-input"  /> 
			
			<label class="nui-form-label">支付金额2：</label>
			<input  id="tbAftFirstCheck.payAmt2" name="tbAftFirstCheck.payAmt2" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="currency" /> 
					
			<label class="nui-form-label">支付对象3：</label>
			<input  id="tbAftFirstCheck.payObject3" name="tbAftFirstCheck.payObject3" required="true" class="nui-textbox nui-form-input"  /> 
			
			<label class="nui-form-label">支付金额3：</label>
			<input  id="tbAftFirstCheck.payAmt3" name="tbAftFirstCheck.payAmt3" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="currency" /> 
			
	    </div>  -->
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
		    <label class="nui-form-label">检查方式：</label>
			<input id="tbAftFirstCheck.checkWay" name="tbAftFirstCheck.checkWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  />
						
			<label class="nui-form-label">检查人：</label>
			<input  id="tbAftFirstCheck.checkPerson" name="tbAftFirstCheck.checkPerson" class="nui-text nui-form-input" dictTypeId="user" /> 
				
			<label class="nui-form-label">检查日期：</label>
			<!-- <input  id="tbAftFirstCheck.checkDate" name="tbAftFirstCheck.checkDate" class="nui-text nui-form-input"  /> -->
			<input id="tbAftFirstCheck.checkDate" name="tbAftFirstCheck.checkDate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/> 
					
			<label class="nui-form-label">检查结论：</label>
			<input  id="tbAftFirstCheck.checkResult" name="tbAftFirstCheck.checkResult" required="true" class="nui-textarea nui-form-input"  /> 
			
			<label class="nui-form-label">累计检查次数：</label>
			<input  id="tbAftFirstCheck.checkCount" name="tbAftFirstCheck.checkCount" class="nui-text nui-form-input"  /> 
			
			
	    </div>
	    
	</fieldset>
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save(1)">保存</a>
			<a class="nui-button" id="con_contract_temp_save" iconCls="icon-save" onclick="save(2)">临时保存</a>
			<a class="nui-button" id="btnDownload" onclick="clickDownload()">下载打印</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var firstCheckId ="<%=request.getParameter("firstCheckId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识 
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
            
            	if(mydata.tbAftFirstCheck.updatePerson != null && mydata.tbAftFirstCheck.updatePerson != "") {
            		nui.get("btnDownload").setEnabled(true);
            	}else {
            		nui.get("btnDownload").setEnabled(false);
            	}
            
            	var o = nui.decode(mydata);
            	//alert(o.tbAftFirstCheck.partyId);
            	partyId = o.tbAftFirstCheck.partyId;
            	form.setData(o);
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	query1();
            }
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			nui.get("con_contract_temp_save").hide();
			
			form.setEnabled(false);
		} 
        
	}
	
  	function query1(){
  		var partyTypeCd= nui.get("tbCsmParty.partyTypeCd").getValue();
  		//alert(partyTypeCd);
  		if(partyTypeCd=="01"){
  			$("#type01").css("display","block");
  			$("#type02").css("display","none");
  		}else if(partyTypeCd=="02"){
  			$("#type01").css("display","none");
  			$("#type02").css("display","block");
  		}else {
  			$("#type01").css("display","none");
  			$("#type02").css("display","none");
  		}
  		
  		var o = form.getData();
		var json = nui.decode({"partyId":partyId});
  		var grid5 = nui.get("grid5");
		grid5.load(json);
	}
	
	function save(v){
		if(v==1){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("con_contract_info_save").setEnabled(false);
        }
		var o = form.getData();
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.firstCheck.saveFirstCheck.biz.ext",
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
        	initPage();
        }});
	} 
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"checkId":firstCheckId,"partyId":partyId,"reportName":'/aft/firstCheck.docx'}});
		$.ajax({
            url: "com.bos.aft.firstCheck.printFirstCheck.biz.ext",
            //url: "com.bos.biz.print.printApproveXw.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.swfPath){
            		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
						title: "检查信息预览", 
						width: 1024,
		            	height: 768,
		            	state:"max",
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid5.reload();
			            }
			
					});
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                git.unmask();
            }
       	});	
	}
</script>

</body>
</html>