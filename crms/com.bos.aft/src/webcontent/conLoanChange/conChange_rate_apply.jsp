<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>合同利率变更申请信息</title>
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
			
			<label class="nui-form-label">经办机构：</label>
			<input id="tbConLoanChange.orgNum" name="tbConLoanChange.orgNum" class="nui-text nui-form-input" dictTypeId="org" />
			
			<label class="nui-form-label">客户经理：</label>
			<input id="tbConLoanChange.userNum" name="tbConLoanChange.userNum" class="nui-text nui-form-input" dictTypeId="user" />
			
			<label class="nui-form-label">合同调整类型：</label>
			<input id="tbConLoanChange.conChangeType" name="tbConLoanChange.conChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
			
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input"  vtype="maxLength:999"/> 
			
	    </div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>变更前</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    	<label class="nui-form-label">基准利率：</label>
			<input id="tbConLoanChange.oldBaseRateValue" class="nui-text nui-form-input" name="tbConLoanChange.oldBaseRateValue"/>

			<label class="nui-form-label">利率类型：</label>
			<input id="tbConLoanChange.oldRateType" name="tbConLoanChange.oldRateType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1016"/>
				   
			<label class="nui-form-label">浮动形式：</label>
			<input id="tbConLoanChange.oldFloatType" name="tbConLoanChange.oldFloatType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1145"/>
				   
			<label class="nui-form-label">浮动方式：</label>
			<input id="tbConLoanChange.oldFloatWay" name="tbConLoanChange.oldFloatWay" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1147"/>
				   
			<label class="nui-form-label">浮动比例/浮动值：</label>
			<input id="tbConLoanChange.oldRateFloatProportion" class="nui-text nui-form-input" name="tbConLoanChange.oldRateFloatProportion"/>
			
			<label class="nui-form-label">执行利率：</label>
			<input id="tbConLoanChange.oldYearRate" class="nui-text nui-form-input" name="tbConLoanChange.oldYearRate"/>
			
			<label class="nui-form-label">利率调整方式：</label>
			<input id="tbConLoanChange.oldIrUpdateFrequency" name="tbConLoanChange.oldIrUpdateFrequency" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1148"/>
				   
			<label class="nui-form-label">结息周期：</label>
			<input id="tbConLoanChange.oldInterestCollectType" name="tbConLoanChange.oldInterestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
	    </div> 
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	    
	    <div id="loanrate" class="nui-dynpanel"  columns="4">
		 	<label class="nui-form-label" id="basicValue">基准利率：</label>
			<input id="tbConLoanChange.newBaseRateValue" name="tbConLoanChange.newBaseRateValue" class="nui-textbox nui-form-input" required="true"  />
			
			<label class="nui-form-label" >利率类型：</label>
			<input name="tbConLoanChange.newRateType" id="tbConLoanChange.newRateType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1016" required="true" onvaluechanged="onselectType"/>
		</div>
		<div id="floatdiv" class="nui-dynpanel"  columns="4" style="width:99.5%;">
			<label class="nui-form-label" id="floatType">浮动形式：</label>
			<input name="tbConLoanChange.newFloatType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1145" id="tbConLoanChange.newFloatType" required="true" onvaluechanged="onselectFloat"/>
			
			<label class="nui-form-label" id="floatWay">浮动方式：</label>
			<input name="tbConLoanChange.newFloatWay" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1147" id="tbConLoanChange.newFloatWay" required="true" onvaluechanged="countRate"/>
		</div>	
		<div id="floatprop" class="nui-dynpanel"  columns="4" style="width:99.5%;">
			<label class="nui-form-label" id="rateFloatProportion">浮动比例/浮动值：</label>
			<input id="tbConLoanChange.newRateFloatProportion" name="tbConLoanChange.newRateFloatProportion" class="nui-textbox nui-form-input" required="true"  vtype="negative;maxLength:11" onblur="onRateFloat(this)"/>
		</div>
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label" id="yearRate">执行利率（%）：</label>
			<input id="tbConLoanChange.newYearRate" name="tbConLoanChange.newYearRate" class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0.000001,100" required="true" />
		</div>
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label id="isChangeRate">利率调整方式：</label>
			<input id="tbConLoanChange.newIrUpdateFrequency" name="tbConLoanChange.newIrUpdateFrequency" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_SXCD1148"  />
		</div>
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">结息周期：</label>
			<input name="tbConLoanChange.newInterestCollectType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1018" id="tbConLoanChange.newInterestCollectType" required="true" />
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
            	//nui.get("tbConLoanChange.oldRateType").setValue(o.tbConLoanChange.oldRateType);//值被覆盖，重新赋值nui.get("id").setValue(o.name)
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
        	//alert("保存成功！");
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	

</script>

<script>
	//在主页面初始化时也调用此方法，要做特别处理
	function onselectType(e){
		var reateType= nui.get("tbConLoanChange.newRateType").getValue();
		if(reateType=="2"){//浮动利率
			$("#floatdiv").css("display","block");
			var floatType = nui.get("tbConLoanChange.newFloatType").getValue();
			if(floatType=='0'){//浮动比例
				$("#floatprop").css("display","block");
			}else if(floatType=='1'){//浮动值
				$("#floatprop").css("display","block");
			}else{//非反显
				$("#floatprop").css("display","none");
			}
			nui.get("tbConLoanChange.newYearRate").setEnabled(false);
			nui.get("tbConLoanChange.newIrUpdateFrequency").setEnabled(true);
		}else if(reateType=="1"){//固定利率
			$("#floatdiv").css("display","none");
			$("#floatprop").css("display","none");
			nui.get("tbConLoanChange.newYearRate").setEnabled(true);
			nui.get("tbConLoanChange.newIrUpdateFrequency").setEnabled(false);
			nui.get("tbConLoanChange.newIrUpdateFrequency").setValue('05');
			
			nui.get("tbConLoanChange.newFloatType").setValue(null);
			nui.get("tbConLoanChange.newFloatWay").setValue(null);
			nui.get("tbConLoanChange.newRateFloatProportion").setValue(null);
		}else{//非反显
			$("#floatdiv").css("display","none");
			$("#floatprop").css("display","none");
		}
	}
	
	function onselectFloat(e){		
		if(e.value){
			if(e.value=='0'){//比例
				$("#floatprop").css("display","block");
			}else{
				$("#floatprop").css("display","block");
			}
		}
		countRate();
	}

 	function onRateFloat(e){
 		if(parseInt(e.getValue())>99999){
 			e.setValue(null);
 		}
 		countRate();
 	}
 	
 	function countRate(){
 		var baseRate = nui.get("tbConLoanChange.newBaseRateValue").getValue();
 		nui.get("tbConLoanChange.newYearRate").setValue('');
 		var floatType = nui.get("tbConLoanChange.newFloatType").getValue();
 		var floatWay = nui.get("tbConLoanChange.newFloatWay").getValue();
 		var rateFloatProportion = nui.get("tbConLoanChange.newRateFloatProportion").getValue();
 		if(floatType&&floatWay&&baseRate){
 			if(floatType=='0'){//比例
 				if(rateFloatProportion){
 					if(floatWay=='1'){//上浮
 						nui.get("tbConLoanChange.newYearRate").setValue(parseFloat(baseRate)+parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01);
 					}else{//下浮
 						nui.get("tbConLoanChange.newYearRate").setValue(parseFloat(baseRate)-parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01);
 					}
 				}
 			}else if(floatType=='1'){
 				if(rateFloatProportion){
 					if(floatWay=='1'){//上浮
 						nui.get("tbConLoanChange.newYearRate").setValue(parseFloat(baseRate)+parseFloat(rateFloatProportion));
 					}else{//下浮
 						nui.get("tbConLoanChange.newYearRate").setValue(parseFloat(baseRate)-parseFloat(rateFloatProportion));
 					}
 				}
 			}
 		}
 	}
	
 </script>

</body>
</html>