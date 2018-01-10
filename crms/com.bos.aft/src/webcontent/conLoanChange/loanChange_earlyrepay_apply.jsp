<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>提前还款申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason"  class="nui-hidden nui-form-input"  /> 
	
	<input id="tbLoanInfo.loanId" class="nui-hidden nui-form-input" name ="tbLoanInfo.loanId"/>
	<input id="tbLoanInfo.repayType" class="nui-hidden nui-form-input" name ="tbLoanInfo.repayType"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	<input id="tbConLoanChange.oldRepayAccount" class="nui-hidden nui-form-input" name ="tbConLoanChange.oldRepayAccount"/>
	<input id="tbLoanSummary.summaryStatusCd" class="nui-hidden nui-form-input" name ="tbLoanSummary.summaryStatusCd"/>
	
	<fieldset>
		<legend>
	    	<span>借据信息</span>
	    </legend>
	    
	    <div class="nui-dynpanel" columns="4">
		    <label class="nui-form-label">合同编号：</label>
			<input id="tbConContractInfo.contractNum" class="nui-text nui-form-input" name="tbConContractInfo.contractNum"/>
			
			<label class="nui-form-label">借据编号：</label>
			<input id="tbLoanSummary.summaryNum" class="nui-text nui-form-input" name="tbLoanSummary.summaryNum"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
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
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbConContractInfo.productType" name="tbConContractInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
				   
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt"  class="nui-text nui-form-input" dataType="currency"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye"  class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">借据起期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据止期：</label>
			<input id="tbLoanSummary.endDate" name="tbLoanSummary.endDate" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">还款方式：</label>
			<input id="tbLoanInfo.repayType" name="tbLoanInfo.repayType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1162"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
				   
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">还款账号：</label>
			<input id="tbLoanZh.zh" class="nui-text nui-form-input" name="tbLoanZh.zh"/>
			
			<label class="nui-form-label">还款账户名称：</label>
			<input id="tbLoanZh.zhmc" class="nui-text nui-form-input" name="tbLoanZh.zhmc"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
		</div>
		
	</fieldset>
	
	 <fieldset id="zhxx">
	 <legend>
	    	<span>指定账户信息</span>
	    </legend>
	    	<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">还款账号：</label>
			<input id="items1.repayAccount" class="nui-text nui-form-input" name="items1.repayAccount"/>
			
			<label class="nui-form-label">还款账户名称：</label>
			<input id="items1.repayAccountName" class="nui-text nui-form-input" name="items1.repayAccountName"/>
		</div>
	</fieldset>
	
	<fieldset id="hzszhxx">
	 <legend>
	    	<span>账户信息</span>
	    </legend>
	    	<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">还款账号：</label>
			<input id="tbConLoanChange.hzszh" class="nui-text nui-form-input" name="tbConLoanChange.hzszh"/>
			
			<label class="nui-form-label">账户名称：</label>
			<input id="tbConLoanChange.hzsnm" class="nui-text nui-form-input" name="tbConLoanChange.hzsnm"/>
		</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>申请信息</span>
	    </legend>
	    
	   <div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">是否结清：</label>
			<input id="tbConLoanChange.isSettle" name="tbConLoanChange.isSettle" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectSettle" />
		</div>
		
			<div class="nui-dynpanel" id="div_sx" columns="4">
				
			<label class="nui-form-label">收息类型：</label>
			<input id="tbConLoanChange.newTiexiStatus" name="tbConLoanChange.newTiexiStatus" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_TQHK0001" />
		</div>
		
		<div class="nui-dynpanel" id="div01" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnJQSS" onclick="checkJQSS">结清试算</a>
		</div>
		
		<div class="nui-dynpanel" id="div1" columns="4">
				
			<label class="nui-form-label">还款类型：</label>
			<input id="tbConLoanChange.repayType" name="tbConLoanChange.repayType" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_HKLX0001" onvaluechanged="onselectSettle2" />
				
			<label class="nui-form-label">还款顺序：</label>
			<input id="tbConLoanChange.repayOrder" name="tbConLoanChange.repayOrder" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_HKSS0001" onvaluechanged="onselectSettle3" />
		</div>
		
		<div class="nui-dynpanel" id="div02" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnHKSS" onclick="checkHKSS">还款试算</a>
		</div>
		
		<div class="nui-dynpanel" id="div03" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnHBSS" onclick="checkHBSS">还本试算</a>
		</div>
		
		<div class="nui-dynpanel" id="div04" columns="4">
			<label class="nui-form-label"></label>
			<a class="nui-button" id="btnJQDQQSS" onclick="checkJQDQQSS">结清当前期试算</a>
		</div>
		
		<input id="repayCapitalbak" class="nui-hidden nui-form-input" name ="tbConLoanChange.repayCapital"/>
		<input id="repayAmtbak" class="nui-hidden nui-form-input" name ="tbConLoanChange.repayAmt"/>
		
		<div class="nui-dynpanel" id="div2" columns="4">
			<label class="nui-form-label">指定还款金额：</label>
			<input id="tbConLoanChange.repayAmt"  name="tbConLoanChange.repayAmt" 
				class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0.001,99999999999999999999" dataType="currency1"/>
		</div>
		
		<div class="nui-dynpanel" id="div3" columns="4">
			<label class="nui-form-label">提前还本金额：</label>
			<input id="tbConLoanChange.repayCapital"  name="tbConLoanChange.repayCapital" 
				class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0.001,99999999999999999999" dataType="currency1"/>
		</div>
		
		<div class="nui-dynpanel" id="div6" columns="4">
			<label class="nui-form-label">应还总额：</label>
			<input id="tbConLoanChange.yhze"  name="tbConLoanChange.yhze" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
			<label class="nui-form-label">应还本金：</label>
			<input id="tbConLoanChange.yhbj"  name="tbConLoanChange.yhbj" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div>
		
		<div class="nui-dynpanel" id="div4" columns="4">
			<label class="nui-form-label">应还正常利息：</label>
			<input id="tbConLoanChange.yhzclx"  name="tbConLoanChange.yhzclx" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
			<label class="nui-form-label">应还拖欠利息：</label>
			<input id="tbConLoanChange.yhtqlx"  name="tbConLoanChange.yhtqlx" 
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div>
		
		<div class="nui-dynpanel" id="div5" columns="4">
			<label class="nui-form-label">应还罚息：</label>
			<input id="tbConLoanChange.yhfx"  name="tbConLoanChange.yhfx"  
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
			<label class="nui-form-label">应还复利：</label>
			<input id="tbConLoanChange.yhfl"  name="tbConLoanChange.yhfl"  
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div> 
		<div class="nui-dynpanel" id="div15" columns="4">
			<label class="nui-form-label">应还正常利息未结计：</label>
			<input id="tbConLoanChange.padUpAdjOtdItrPre"  name="tbConLoanChange.padUpAdjOtdItrPre"  
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
				
			<label class="nui-form-label">应还罚息未结计：</label>
			<input id="tbConLoanChange.adjOtdPns"  name="tbConLoanChange.adjOtdPns"  
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
		</div> 
		<div class="nui-dynpanel" id="div16" columns="4">
			<label class="nui-form-label">应还复利未结计：</label>
			<input id="tbConLoanChange.adjOtdCpd"  name="tbConLoanChange.adjOtdCpd"  
				class="nui-text nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div> 
		<div class="nui-dynpanel" id="div11" columns="4">
			<label class="nui-form-label">实还本金：</label>
			<input id="tbConLoanChange.shbj"  name="tbConLoanChange.shbj" 
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			<label class="nui-form-label">实还正常利息：</label>
			<input id="tbConLoanChange.shzclx"  name="tbConLoanChange.shzclx" 
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>	
		</div>
		
		<div class="nui-dynpanel" id="div12" columns="4">
			<label class="nui-form-label">实还拖欠利息：</label>
			<input id="tbConLoanChange.shtqlx"  name="tbConLoanChange.shtqlx" 
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			<label class="nui-form-label">实还罚息：</label>
			<input id="tbConLoanChange.shfx"  name="tbConLoanChange.shfx"  
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>	
		</div>
		<div class="nui-dynpanel" id="div13" columns="4">
			<label class="nui-form-label">实还复利：</label>
			<input id="tbConLoanChange.shfl"  name="tbConLoanChange.shfl"  
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			<label class="nui-form-label">实还正常利息未结计：</label>
			<input id="tbConLoanChange.padUpAdjOtdItr"  name="tbConLoanChange.padUpAdjOtdItr"  
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div> 
		<div class="nui-dynpanel" id="div14" columns="4">
			<label class="nui-form-label">实还罚息未结计：</label>
			<input id="tbConLoanChange.padUpAdjOtdPns"  name="tbConLoanChange.padUpAdjOtdPns"  
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			<label class="nui-form-label">实还复利未结计：</label>
			<input id="tbConLoanChange.padUpAdjOtdCpd"  name="tbConLoanChange.padUpAdjOtdCpd"  
				class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
		</div> 
		<!-- 1400按周期还息按还本计划表还本;1410按还本计划表还息按还本计划表还本 -->
		<div id="isModifyPlan" class="nui-dynpanel"  columns="4">
			<label class="nui-form-label">是否修改还本计划表：</label>
			<input id="tbConLoanChange.isModifyPlan" name="tbConLoanChange.isModifyPlan" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectModify" />
		</div>
	</fieldset>
	
	<div class="nui-dynpanel" columns="1" id="table3">
		<%@include file="/aft/conLoanChange/include_old_repayplan.jsp"%>
	</div> 
	
	<div class="nui-dynpanel" columns="1" id="table4">
		<%@include file="/aft/conLoanChange/include_new_repayplan.jsp"%>
	</div> 
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
			<!-- <a class="nui-button" id="btnDownload" onclick="clickDownload()">下载打印</a> -->
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var loanChangeType="<%=request.getParameter("loanChangeType") %>";
	var tbLoanSummary;
	var tbConLoanChange;
	var conDetail;
	var isSmall;
	var repayType;
	var busDate;
	var leastPrepayAmount=0; 
	var prepayJs=1;
 	var isBankStruct ;
	$("#table3").css("display","none");
	$("#table4").css("display","none");
	$("#zhxx").css("display","none");
	$("#hzszhxx").css("display","none");
	
	$("#div1").css("display","none");
	$("#div2").css("display","none");
	$("#div3").css("display","none");
	$("#div4").css("display","none");
	$("#div5").css("display","none");
	$("#div6").css("display","none");
	
	$("#div01").css("display","none");
	$("#div02").css("display","none");
	$("#div03").css("display","none");
	$("#div04").css("display","none");
	
	$("#div11").css("display","none");
	$("#div12").css("display","none");
	$("#div13").css("display","none");	
	$("#div14").css("display","none");	
	$("#div15").css("display","none");
	$("#div16").css("display","none");
	initPage();
	//初始化页面
	function initPage(){
	$("#div_sx").css("display","none");
			if(loanChangeType=="18"){
				$("#zhxx").css("display","block");
				//nui.get("btnDownload").hide();
				
			
			}
			if(loanChangeType=="15"){
				$("#hzszhxx").css("display","block");
				//nui.get("btnDownload").hide();
				
			}
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	busDate = mydata.busDate;
            	
            	var o = nui.decode(mydata);
            	form.setData(o);
            	
            	tbLoanSummary = o.tbLoanSummary;
            	tbConLoanChange = o.tbConLoanChange;
            	tbConLoanChange = o.tbConLoanChange;
            	conDetail=o.tbConContractInfo;
            	leastPrepayAmount =conDetail.leastPrepayAmount; 
				prepayJs =conDetail.prepayJs;
  				
            //	alert(leastPrepayAmount+"!"+prepayJs);
            	isSmall = o.tbConLoanChange.isSmall;
            	repayType = o.tbLoanInfo.repayType;
            	//nui.get("tbConLoanChange.repayOrder").setEnabled(false);
            	
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	
            	if(tbConLoanChange.isSmall == "1") {
            		$("#table3").css("display","none");
					$("#table4").css("display","none");
            	}
            	            	query1();
            	
            	nui.get("repayCapitalbak").setValue(nui.get("tbConLoanChange.repayCapital").getValue());
            	nui.get("repayAmtbak").setValue(nui.get("tbConLoanChange.repayAmt").getValue());
            	//指定账号还款展现
            	if(loanChangeType=="18"){
					$("#zhxx").css("display","block");
					nui.get("items1.repayAccount").setValue(o.items1[0].REPAY_ACCOUNT);
		            nui.get("items1.repayAccountName").setValue(o.items1[0].REPAY_ACCOUNT_NAME);
				}
				debugger;
				isBankStruct = o.tbLoanSummary.isBankStruct;
				//因为界面优先初始化，所以这里需要再次渲染
				if("1" == isBankStruct){
					onselectSettle3(nui.get("tbConLoanChange.repayOrder"));
				}			
			}
        });
        
		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			
			nui.get("btnJQSS").hide();
			nui.get("btnHKSS").hide();
			nui.get("btnHBSS").hide();
			nui.get("btnJQDQQSS").hide(); 
			form.setEnabled(false);
		}
        
	}
	
	function query1(){
		var partyTypeCd= nui.get("tbCsmParty.partyTypeCd").getValue();
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
  		//var repayType = o.tbLoanInfo.repayType;
  		
  		if(repayType == "1700"){
						$("#div_sx").css("display","block");
			}
  		if(o.tbConLoanChange.isSettle == "1") {
    		$("#isModifyPlan").css("display","none");
     		
    	}else {
    		if(repayType == "1400" || repayType == "1410") {
	  			$("#isModifyPlan").css("display","block");
	  		}else {
	  			$("#isModifyPlan").css("display","none");
	  		}
    	} 
  		
  		if(isSmall == "0") {
	  		var json = nui.decode({"loanId":o.tbLoanInfo.loanId,"changeId":changeId});
			var grid1 = nui.get("grid1");
	    	var grid2 = nui.get("grid2");
	    	grid1.load(json);
			grid2.load(json);
			 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
			if("1" != proFlag){
				nui.get("grid2add").hide();
				nui.get("grid2del").hide();
				form.setEnabled(false);
			} 
  		}

	}
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"newPeriodsNum":++count};
        nui.get(gr).addRow(row,count);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.repayplanChangeId){
            		var json = nui.encode({"repayplanChangeId":row.repayplanChangeId});
	            	$.ajax({
			            url: "com.bos.aft.conLoanChange.deleteRepayplanChange.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else{
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		
		if(o.tbConLoanChange.yhzclx==null || o.tbConLoanChange.yhzclx == ""   ) {
			return nui.alert("请先试算！");
		} 
		
		if(o.tbConLoanChange.isSettle=="0"&&""==o.tbConLoanChange.repayOrder){
			return nui.alert("请填写还款顺序！");
		}
		var repayOrder =  o.tbConLoanChange.repayOrder;
		if(repayOrder == '03'){
			var yhbj = nui.get("tbConLoanChange.yhbj").getValue();
		    var yhzclx = nui.get("tbConLoanChange.yhzclx").getValue();
		    var yhtqlx = nui.get("tbConLoanChange.yhtqlx").getValue();
		    var yhfx = nui.get("tbConLoanChange.yhfx").getValue();
		    var yhfl = nui.get("tbConLoanChange.yhfl").getValue();
		    var yhff = nui.get("tbConLoanChange.adjOtdPns").getValue();
		    var yhll = nui.get("tbConLoanChange.adjOtdCpd").getValue();
		        
		    var shbj = nui.get("tbConLoanChange.shbj").getValue();
		    var shzclx = nui.get("tbConLoanChange.shzclx").getValue();
		    var shtqlx = nui.get("tbConLoanChange.shtqlx").getValue();
		    var shfx = nui.get("tbConLoanChange.shfx").getValue();
		    var shfl = nui.get("tbConLoanChange.shfl").getValue();
		    var shff = nui.get("tbConLoanChange.padUpAdjOtdPns").getValue();
		    var shll = nui.get("tbConLoanChange.padUpAdjOtdCpd").getValue();
		    
		    if(parseFloat(shbj)>parseFloat(yhbj)){
		    	nui.alert("实还本金不能大于应还本金");
		    	nui.get("tbConLoanChange.shbj").setValue("");
		    	return false;
		    }
		    debugger;
		     if("1" != isBankStruct && parseFloat(shzclx)>parseFloat(yhzclx)){
		    	nui.alert("实还正常利息不能大于应还正常利息");
		    	nui.get("tbConLoanChange.shzclx").setValue("");
		    	return false;
		    }
		    if("1" != isBankStruct && parseFloat(shtqlx)>parseFloat(yhtqlx)){
		    	nui.alert("实还拖欠利息不能大于应还拖欠利息");
		    	nui.get("tbConLoanChange.shtqlx").setValue("");
		    	return false;
		    }
		    if("1" != isBankStruct && parseFloat(shfx)>parseFloat(yhfx)){
		    	nui.alert("实还罚息不能大于应还罚息");
		    	nui.get("tbConLoanChange.shfx").setValue("");
		    	return false;
		    }
		    if("1" != isBankStruct && parseFloat(shfl)>parseFloat(yhfl)){
		    	nui.alert("实还复利不能大于应还复利");
		    	nui.get("tbConLoanChange.shfl").setValue("");
		    	return false;
		    }
		     if("1" != isBankStruct && parseFloat(shff)>parseFloat(yhff)){
		    	nui.alert("未结计罚息不能大于应还罚息未结计");
		    	nui.get("tbConLoanChange.padUpAdjOtdPns").setValue("");
		    	return false;
		    }
		    if("1" != isBankStruct && parseFloat(shll)>parseFloat(yhll)){
		    	nui.alert("未结计复利不能大于应还复利未结计");
		    	nui.get("tbConLoanChange.padUpAdjOtdCpd").setValue("");
		    	return false;
		    }
		}
		
		if(o.tbConLoanChange.repayType == "1") {
			checkJQSS();
		}else {
			if(o.tbConLoanChange.repayType == "01") {//还款
				//checkHKSS();
        	}else if(o.tbConLoanChange.repayType == "02") {//还本
				checkHBSS();
			}else if(o.tbConLoanChange.repayType == "03") {
				checkJQDQQSS();
			}else if(o.tbConLoanChange.isSettle=="0" && o.tbConLoanChange.repayType==""){
			return nui.alert("请填写还款类型！" );
			}
		}
		o.tbConLoanChange.yhze = nui.get("tbConLoanChange.yhze").getValue();
		o.tbConLoanChange.yhbj = nui.get("tbConLoanChange.yhbj").getValue();
		o.tbConLoanChange.yhzclx = nui.get("tbConLoanChange.yhzclx").getValue();
		o.tbConLoanChange.yhtqlx = nui.get("tbConLoanChange.yhtqlx").getValue();
		o.tbConLoanChange.yhfx = nui.get("tbConLoanChange.yhfx").getValue();
		
		o.tbConLoanChange.changeId=changeId;
		o.tbConLoanChange.changeReason="无";
		
		o.changeId = changeId;
		o.loanId = o.tbLoanInfo.loanId;
		
		if(nui.get("tbConLoanChange.isModifyPlan").getValue() == "1" && nui.get("tbConLoanChange.repayOrder").getValue() != "00") {
			nui.alert("有还本计划变更必须选择默认序！");
		    return;
		}
		//alert(leastPrepayAmount +"!!!"+prepayJs);
		/* if(prepayJs>0){
		if(o.tbConLoanChange.repayType == "01") {
		 if(nui.get("tbConLoanChange.repayAmt").getValue()<leastPrepayAmount || nui.get("tbConLoanChange.repayAmt").getValue()%prepayJs != 0){
		 				nui.alert("最低还款金额不得低于"+leastPrepayAmount+"元且需"+prepayJs+"元的整数倍！");
		 				return;
		 	
		 }
		}
		if(o.tbConLoanChange.repayType == "02"){
		if(nui.get("tbConLoanChange.repayCapital").getValue()<leastPrepayAmount || nui.get("tbConLoanChange.repayCapital").getValue()%prepayJs!=0){
		 				nui.alert("最低还款金额不得低于"+leastPrepayAmount+"元且需"+prepayJs+"元的整数倍！");
		 				return;
		 	
		 }
		
		}
		
	
 											} */
		if(isSmall == "0") {
			if(nui.get("tbConLoanChange.isModifyPlan").getValue() == "1") {
				var repayPlans = nui.get("grid1").getChanges();/* 还款 */
				var repayPlans2 = nui.get("grid2").getChanges();/* 还款 */
				o.repayPlans = repayPlans;
				o.repayPlans2 = repayPlans2;
				
				var size1 = nui.get("grid2").getData().length;
				if (size1 == 0){
		        	nui.alert("请填写还款计划");
		        	return;
		        } 
				
				var amt = 0;
				var planNum=[];
				for(var i=0; i<nui.get("grid2").getData().length;i++){
					if(nui.get("grid2").getData()[i].newRepayDate==null || nui.get("grid2").getData()[i].newRepayDate==''){
						nui.alert("日期不能为空");
				 		return;
		 			}
		 			if(nui.get("grid2").getData()[i].newRepayAmt==null || nui.get("grid2").getData()[i].newRepayAmt==''){
						nui.alert("金额不能为空");
				 		return;
		 			}
		 			
		 			if(nui.get("grid2").getData()[i].newRepayDate.substr(0,10)<busDate) {
		 				nui.alert("还款日不应小于当前营业日期！");
				 		return;
		 			}
		 			
		 			if(nui.get("grid2").getData()[i].newRepayDate.substr(0,10)>nui.get("tbLoanSummary.endDate").getValue()) {
		 				nui.alert("还款日不应大于借据止期！");
				 		return;
		 			} 
					
					planNum[i] = nui.get("grid2").getData()[i].newPeriodsNum;
		 			
		 			if(nui.get("grid2").getData()[i].newPeriodsNum > nui.get("grid2").getData().length 
		 				|| nui.get("grid2").getData()[i].newPeriodsNum < 1) {
		 				nui.alert("期数不正确！");
					 	return;
			 		}
		 			
		 			if(i==0) {
		 			
		 			}else {
			 			if(nui.get("grid2").getData()[i].newPeriodsNum > nui.get("grid2").getData()[i-1].newPeriodsNum 
			 				&& nui.get("grid2").getData()[i].newRepayDate < nui.get("grid2").getData()[i-1].newRepayDate) {
					 		nui.alert("还款日应大于上期还款日！");
						 	return;
			 			}else if(nui.get("grid2").getData()[i].newPeriodsNum < nui.get("grid2").getData()[i-1].newPeriodsNum 
			 				&& nui.get("grid2").getData()[i].newRepayDate > nui.get("grid2").getData()[i-1].newRepayDate) {
			 				nui.alert("还款日应大于上期还款日！");
						 	return;
			 			}
		 			} 
		 			
		 			amt += parseFloat(nui.get("grid2").getData()[i].newRepayAmt);
		 			nui.get("grid2").getData()[i].changeId=changeId;
		    	}
		    	
		    	var planNum2 = planNum.sort();
		    	for(var i=0;i<planNum2.length-1;i++) {
		    		if(planNum2[i] == planNum2[i+1]) {
		    			nui.alert("期次重复！");
						return;
		    		}
		    	}
		    	
		    	if(tbConLoanChange.repayType=='02'){//提前还本
		    		var temp = o.tbLoanSummary.jjye - o.tbConLoanChange.yhbj - o.tbConLoanChange.repayCapital;
		    	}else{
		    		var temp = o.tbLoanSummary.jjye - o.tbConLoanChange.yhbj;
		    	}
		    	//alert("temp--->" + parseFloat(temp).toFixed(2) + "amt--->" + parseFloat(amt).toFixed(2));
		    	if (parseFloat(temp).toFixed(2)!=parseFloat(amt).toFixed(2)){
		        	nui.alert("累计金额应等于借据余额");
		        	return;
		        }
			
			}
			
		}
		
 		if(nui.get("tbConLoanChange.yhze").getValue()==0){
			nui.alert("应还总额为0,不需要提前还款！");
		 				return;
		}
		//校验账户余额
        var yhze = nui.get("tbConLoanChange.yhze").getValue();
        var AcctNo;
        var urls;
        if(loanChangeType=="11"){
          AcctNo = nui.get("tbLoanZh.zh").getValue();
          urls = "com.bos.accInfo.accInfo.queryAcc1.biz.ext";
         }else if (loanChangeType=="15"){
                  AcctNo = nui.get("tbConLoanChange.hzszh").getValue();
                  urls = "com.bos.accInfo.accInfo.queryAcc1.biz.ext";
        
        }else if (loanChangeType=="18"){
                  AcctNo = nui.get("items1.repayAccount").getValue();
         		   urls = "com.bos.accInfo.accInfo.queryAcc2.biz.ext";
        }
    	if(AcctNo == null || AcctNo == ''){
    		alert("还款账号为空，请联系系统管理员！");
    		nui.get("con_contract_info_save").setEnabled(true);
    		
    		return;
    	}
    	nui.get("con_contract_info_save").setEnabled(false);
    	AcctNo = AcctNo.trim();
    	 var json=nui.encode({"acctInd":AcctNo});
		  $.ajax({
	        url: urls,
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var message = text.msg;
	        	var code = text.code;
	        	if(code != 'AAAAAAA'){
	        	 	nui.alert(message);
	        		return;
	        	}
	        //	var avlBal = text.hxresponse.oxd052ResBody.availableAmt;//返回的可用余额
				//if(parseFloat(yhze) > parseFloat(avlBal)) {
 	        	//	nui.alert("还款金额大于账户可用余额！");
 	        	//	nui.get("con_contract_info_save").setEnabled(true);
 	        		
	        	//	return;
	        	//}else {
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
        		return;
        	}
        //	initPage();
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	        //	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		}); 
		
		nui.get("con_contract_info_save").setEnabled(true);
		
		
	} 
	
	function onselectSettle(e){
		var isSettle= nui.get("tbConLoanChange.isSettle").getValue();//是否结清
		if(isSettle=="1"){//结清
			nui.get("tbConLoanChange.repayOrder").setValue("00");//结清是给还款顺序赋值为默认序
			$("#isModifyPlan").css("display","none");
			$("#div1").css("display","none");//屏蔽还款顺序
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div15").css("display","block");
			$("#div16").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","block");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
 			if(repayType == "1700"){
						$("#div_sx").css("display","block");
			}
			nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            nui.get("tbConLoanChange.repayType").setValue("");
            nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
			nui.get("tbConLoanChange.yhfl").setValue("");
			nui.get("tbConLoanChange.padUpAdjOtdItrPre").setValue("");
			nui.get("tbConLoanChange.adjOtdPns").setValue("");
			nui.get("tbConLoanChange.adjOtdCpd").setValue("");
		}else if(isSettle=="0"){
			if(repayType == "1400" || repayType == "1410") {
	  			$("#isModifyPlan").css("display","block");
	  		}else {
	  			$("#isModifyPlan").css("display","none");
	  		}
		
			$("#div1").css("display","block");
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","none");
			$("#div5").css("display","none");
			$("#div15").css("display","none");
			$("#div16").css("display","none");
			$("#div6").css("display","none");
			
			$("#div_sx").css("display","none");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
			
			nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
			nui.get("tbConLoanChange.yhfl").setValue("");
			nui.get("tbConLoanChange.padUpAdjOtdItrPre").setValue("");
			nui.get("tbConLoanChange.adjOtdPns").setValue("");
			nui.get("tbConLoanChange.adjOtdCpd").setValue("");
		}else{
			if(repayType == "1400" || repayType == "1410") {
	  			$("#isModifyPlan").css("display","block");
	  		}else {
	  			$("#isModifyPlan").css("display","none");
	  		}
		
			$("#div1").css("display","none");
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","none");
			$("#div5").css("display","none");
			$("#div15").css("display","none");
			$("#div16").css("display","none");
			$("#div6").css("display","none");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
		}
	} 
	function onselectSettle3(e){
		if(e.value=='03'){
			$("#div11").css("display","block");
			$("#div12").css("display","block");
			$("#div13").css("display","block");
			$("#div14").css("display","block");	
			debugger;
			if("1" == isBankStruct){
				nui.get("tbConLoanChange.padUpAdjOtdItr").setValue(0);
				nui.get("tbConLoanChange.padUpAdjOtdItr").setEnabled(false);
				nui.get("tbConLoanChange.padUpAdjOtdPns").setValue(0);
				nui.get("tbConLoanChange.padUpAdjOtdPns").setEnabled(false);
				nui.get("tbConLoanChange.padUpAdjOtdCpd").setValue(0);
				nui.get("tbConLoanChange.padUpAdjOtdCpd").setEnabled(false);
			}
		}else{
			$("#div11").css("display","none");
			$("#div12").css("display","none");
			$("#div13").css("display","none");
			$("#div14").css("display","none");	
		}
	}
	//调用计量结清试算接口
	function checkJQSS(){		
 		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
 		tbConLoanChange.newTiexiStatus = nui.get("tbConLoanChange.newTiexiStatus").getValue();
 		//alert(tbConLoanChange.newTiexiStatus);
 		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"3"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface1500.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            		
            		var yhbj = parseFloat(mydata.items.dftPrn) + parseFloat(mydata.items.curPrn) + parseFloat(mydata.items.aheadPrn);
            		
            			//nui.get("tbConLoanChange.yhze").setValue(mydata.items.allPrnItrAmt);
            		
            		nui.get("tbConLoanChange.yhze").setValue("");
            		nui.get("tbConLoanChange.yhbj").setValue("");
            		nui.get("tbConLoanChange.yhzclx").setValue("");
            		nui.get("tbConLoanChange.yhtqlx").setValue("");
            		nui.get("tbConLoanChange.yhfx").setValue("");
            		
            		nui.get("tbConLoanChange.yhze").setValue(mydata.items.totPrnItr);
            		nui.get("tbConLoanChange.yhbj").setValue(mydata.items.rcvPrn);
            		nui.get("tbConLoanChange.yhzclx").setValue(mydata.items.rcvNorItrIn);
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.rcvDftItrIn);
            		nui.get("tbConLoanChange.yhfx").setValue(mydata.items.rcvPnsItrIn);
            		nui.get("tbConLoanChange.yhfl").setValue(mydata.items.rcvCpdItrIn);
            	}
			}
    	});
	} 
	
	//调用计量还款试算接口
	function checkHKSS(){
		 tbConLoanChange.newTiexiStatus = "0";
		
		if(nui.get("tbConLoanChange.repayAmt").getValue() == null || nui.get("tbConLoanChange.repayAmt").getValue() == "") {
			return nui.alert("请填写指定还款金额！");
		}
		
		if(nui.get("tbConLoanChange.repayOrder").getValue() == null || nui.get("tbConLoanChange.repayOrder").getValue() == "") {
			return nui.alert("请选择还款顺序！");
		}
	
		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
		tbConLoanChange.repayAmt = nui.get("tbConLoanChange.repayAmt").getValue();
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"1"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface15001.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		if("1" == proFlag){
            			nui.get("tbConLoanChange.repayAmt").setEnabled(true);
            		}
            		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            		
            		var lx = parseFloat(mydata.items.interest) + parseFloat(mydata.items.dftItr) + parseFloat(mydata.items.pnsItr);
            		var yhbj = parseFloat(mydata.items.dftPrn) + parseFloat(mydata.items.curPrn) + parseFloat(mydata.items.aheadPrn);
            		
            		nui.get("tbConLoanChange.yhze").setValue(mydata.items.padUpAmt);
            		nui.get("tbConLoanChange.yhbj").setValue(mydata.items.resNor);
            		nui.get("tbConLoanChange.yhzclx").setValue(mydata.items.rcvNorItrIn);
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.rcvDftItrIn);
            		nui.get("tbConLoanChange.yhfx").setValue(mydata.items.rcvPnsItrIn);
            	}
            	//initPage();
			}
    	});
	} 
	
	//调用计量还本试算接口
	function checkHBSS(){
			 tbConLoanChange.newTiexiStatus = "0";
	
		if(nui.get("tbConLoanChange.repayCapital").getValue() == null || nui.get("tbConLoanChange.repayCapital").getValue() == "") {
			return nui.alert("请填写提前还本金额！");
		}
	
		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
		tbConLoanChange.repayCapital = nui.get("tbConLoanChange.repayCapital").getValue();
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"2"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface15001.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            		if("1" == proFlag){
            			nui.get("tbConLoanChange.repayCapital").setEnabled(true);
            		}
            		
            		//var lx = parseFloat(mydata.items.interest) + parseFloat(mydata.items.dftItr) + parseFloat(mydata.items.pnsItr);
            		//var yhbj = parseFloat(mydata.items.dftPrn) + parseFloat(mydata.items.curPrn);
            		//var a=yhbj + lx + parseFloat(nui.get("tbConLoanChange.repayCapital").getValue());
            		//nui.get("tbConLoanChange.yhze").setValue(a.toFixed(2));
            		
            		if(eval(nui.get("tbConLoanChange.yhze").getValue()) > eval(mydata.items.allPrnItrAmt)) {
            			//nui.get("tbConLoanChange.yhze").setValue(mydata.items.allPrnItrAmt);
            			nui.alert("还款总额大于结清金额("+mydata.items.allPrnItrAmt+")");
            		}
            		var yhzclx =  parseFloat(mydata.items.rcvNorItrIn) + parseFloat(mydata.items.currPrjItr);
            		var yhfx =  parseFloat(mydata.items.rcvPnsItrIn) + parseFloat(mydata.items.adjOtdPns);
            		var yhfl =  parseFloat(mydata.items.rcvCpdItrIn) + parseFloat(mydata.items.adjOtdCpd);
            		nui.get("tbConLoanChange.yhze").setValue(mydata.items.padUpAmt);
            		nui.get("tbConLoanChange.yhbj").setValue(mydata.items.rcvPrn);
            		nui.get("tbConLoanChange.yhzclx").setValue(yhzclx.toFixed(2));
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.rcvDftItrIn);
            		nui.get("tbConLoanChange.yhfx").setValue(yhfx.toFixed(2));
            		nui.get("tbConLoanChange.yhfl").setValue(yhfl.toFixed(2));
            	}
            	//initPage();
			}
    	});
	} 
	
	//调用计量结清当前期试算接口
	function checkJQDQQSS(){
	 tbConLoanChange.newTiexiStatus = "0";
	
		tbConLoanChange.repayCapital = 0;
		tbConLoanChange.repayOrder = nui.get("tbConLoanChange.repayOrder").getValue();
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"amtFlg":"4"});//1还本息;2提前还本金3结清4结清当前期
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface15001.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
            		nui.get("tbConLoanChange.yhze").setValue(null);
            		nui.get("tbConLoanChange.yhbj").setValue(null);
            		nui.get("tbConLoanChange.yhzclx").setValue(null);
            		nui.get("tbConLoanChange.yhtqlx").setValue(null);
            		nui.get("tbConLoanChange.yhfx").setValue(null);
            		nui.get("tbConLoanChange.yhfl").setValue(null);
            		
            		var yhbj = parseFloat(mydata.items.dftPrnBal) + parseFloat(mydata.items.currPrjPrn);
            		var yhzclx = parseFloat(mydata.items.rcvNorItrIn) + parseFloat(mydata.items.currPrjItr);
            		var yhfx = parseFloat(mydata.items.rcvPnsItrIn) + parseFloat(mydata.items.adjOtdPns);
            		var yhfl = parseFloat(mydata.items.rcvCpdItrIn) + parseFloat(mydata.items.adjOtdCpd);
            		var yhze = parseFloat(mydata.items.dftPrnBal) + parseFloat(mydata.items.currPrjPrn)+
            					parseFloat(mydata.items.rcvNorItrIn) + parseFloat(mydata.items.currPrjItr)+
            					parseFloat(mydata.items.rcvDftItrIn) + parseFloat(mydata.items.rcvPnsItrIn)+
            					parseFloat(mydata.items.adjOtdPns) + parseFloat(mydata.items.rcvCpdItrIn)+
            					parseFloat(mydata.items.adjOtdCpd);
            		nui.get("tbConLoanChange.yhze").setValue(yhze.toFixed(2));
            		nui.get("tbConLoanChange.yhbj").setValue(yhbj.toFixed(2));
            		nui.get("tbConLoanChange.yhzclx").setValue(yhzclx.toFixed(2));
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.rcvDftItrIn);
            		nui.get("tbConLoanChange.yhfx").setValue(yhfx.toFixed(2));
            		nui.get("tbConLoanChange.yhfl").setValue(yhfl.toFixed(2));
            	}
            	//initPage();
			}
    	});
	} 
	
	function onselectSettle2(e){
		var repayType= nui.get("tbConLoanChange.repayType").getValue();//还款类型
		var summaryStatusCd = nui.get("tbLoanSummary.summaryStatusCd").getValue();
		
		if(repayType=="01"){//指定还款金额
					nui.get("tbConLoanChange.repayOrder").setValue("00");
		
			if("1" == proFlag){
				nui.get("tbConLoanChange.repayOrder").setEnabled(true);
			}else {
				nui.get("tbConLoanChange.repayOrder").setEnabled(false);
			}
		
			
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div15").css("display","block");
			$("#div16").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
			
			if("1" == proFlag){
				nui.get("tbConLoanChange.repayAmt").setEnabled(true);
			}
    		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
    		
			nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
    		nui.get("tbConLoanChange.yhfl").setValue("");
			nui.get("tbConLoanChange.padUpAdjOtdItrPre").setValue("");
			nui.get("tbConLoanChange.adjOtdPns").setValue("");
			nui.get("tbConLoanChange.adjOtdCpd").setValue("");
    		var json = nui.encode({"tbLoanSummary":tbLoanSummary});
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface15001.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		//nui.get("tbConLoanChange.repayAmt").setEnabled(false);
            		//if("1" == proFlag){
            		//	nui.get("tbConLoanChange.repayCapital").setEnabled(true);
            		//}
            		var yhbj = parseFloat(mydata.items.resNor) + parseFloat(mydata.items.dftPrnBal);
            		var yhzclx =  parseFloat(mydata.items.rcvNorItrIn);
            		var yhfx =  parseFloat(mydata.items.rcvPnsItrIn);
            		var yhfl =  parseFloat(mydata.items.rcvCpdItrIn);
            		nui.get("tbConLoanChange.yhze").setValue(mydata.items.totPrnItr.toFixed(2));
            		nui.get("tbConLoanChange.yhbj").setValue(yhbj.toFixed(2));
            		nui.get("tbConLoanChange.yhzclx").setValue(yhzclx.toFixed(2));
            		nui.get("tbConLoanChange.yhtqlx").setValue(mydata.items.rcvDftItrIn.toFixed(2));
            		nui.get("tbConLoanChange.yhfx").setValue(yhfx.toFixed(2));
            		nui.get("tbConLoanChange.yhfl").setValue(yhfl.toFixed(2));
            		nui.get("tbConLoanChange.adjOtdPns").setValue(mydata.items.adjOtdPns.toFixed(2));
            		nui.get("tbConLoanChange.adjOtdCpd").setValue(mydata.items.adjOtdCpd.toFixed(2));
            		nui.get("tbConLoanChange.padUpAdjOtdItrPre").setValue(mydata.items.currPrjItr.toFixed(2));
            	}
			}
			});
		}else if(repayType=="02"){//指定还本金额
			
			nui.get("tbConLoanChange.repayOrder").setValue("00");
			nui.get("tbConLoanChange.repayOrder").setEnabled(false);
			
			/* if(summaryStatusCd == "03") {
				return nui.alert("逾期借据不能提前还本！");
			} */
		
			$("#div2").css("display","none");
			$("#div3").css("display","block");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div15").css("display","block");
			$("#div16").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","block");
			$("#div04").css("display","none");
			
			nui.get("tbConLoanChange.repayAmt").setEnabled(false);
			if("1" == proFlag){
				nui.get("tbConLoanChange.repayCapital").setEnabled(true);
			}
    		
			nui.get("tbConLoanChange.repayAmt").setValue("");
			
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
			nui.get("tbConLoanChange.yhfl").setValue("");
			nui.get("tbConLoanChange.padUpAdjOtdItrPre").setValue("");
			nui.get("tbConLoanChange.adjOtdPns").setValue("");
			nui.get("tbConLoanChange.adjOtdCpd").setValue("");
		}else if(repayType=="03"){//结清当前期
		
			nui.get("tbConLoanChange.repayOrder").setValue("00");
			nui.get("tbConLoanChange.repayOrder").setEnabled(false);
		
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","block");
			$("#div5").css("display","block");
			$("#div15").css("display","block");
			$("#div16").css("display","block");
			$("#div6").css("display","block");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","block");
			
			nui.get("tbConLoanChange.repayAmt").setEnabled(false);
    		nui.get("tbConLoanChange.repayCapital").setEnabled(false);
    		
			nui.get("tbConLoanChange.repayAmt").setValue("");
    		nui.get("tbConLoanChange.repayCapital").setValue("");
    		nui.get("tbConLoanChange.yhze").setValue("");
    		nui.get("tbConLoanChange.yhbj").setValue("");
    		nui.get("tbConLoanChange.yhzclx").setValue("");
    		nui.get("tbConLoanChange.yhtqlx").setValue("");
    		nui.get("tbConLoanChange.yhfx").setValue("");
			nui.get("tbConLoanChange.yhfl").setValue("");
			nui.get("tbConLoanChange.padUpAdjOtdItrPre").setValue("");
			nui.get("tbConLoanChange.adjOtdPns").setValue("");
			nui.get("tbConLoanChange.adjOtdCpd").setValue("");
		}else{
		
			nui.get("tbConLoanChange.repayOrder").setValue("00");
			nui.get("tbConLoanChange.repayOrder").setEnabled(false);
		
			$("#div2").css("display","none");
			$("#div3").css("display","none");
			$("#div4").css("display","none");
			$("#div5").css("display","none");
			$("#div15").css("display","none");
			$("#div16").css("display","none");
			$("#div6").css("display","none");
			
			$("#div01").css("display","none");
			$("#div02").css("display","none");
			$("#div03").css("display","none");
			$("#div04").css("display","none");
		}
 	}  
	
	function onselectModify(e){
		var isModifyPlan= nui.get("tbConLoanChange.isModifyPlan").getValue();//是否修改还本计划表
		if(isSmall=="1"){
			if(isModifyPlan=="1"){
				//return nui.alert("保存后请到还款计划表页面填写还款计划");
			}
		}else {
			if(isModifyPlan=="1"){
				$("#table3").css("display","block");
				$("#table4").css("display","block");
			}else{
				$("#table3").css("display","none");
				$("#table4").css("display","none");
			}
		}
	}
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"changeId":changeId,"reportName":'/aft/loanChange_earlyrepay.docx'}});
		$.ajax({
            url: "com.bos.aft.conLoanChange.printLoanChange.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.swfPath){
            		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
						title: "变更信息预览", 
						width: 1024,
		            	height: 768,
		            	state:"max",
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
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