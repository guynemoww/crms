<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>合同贴息调整申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbConLoanChange.contractId" class="nui-hidden nui-form-input" name ="tbConLoanChange.contractId"/>
	<input id="tbConLoanTx.txId" class="nui-hidden nui-form-input" name ="tbConLoanTx.txId"/>
	
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
			
			<label class="nui-form-label">是否贴息：</label>
			<input id="tbConLoanTx.oldIsTx" name="tbConLoanTx.oldIsTx" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="CDGY0001"/>
				   
			<label class="nui-form-label">贴息账号：</label>
			<input id="tbConLoanTx.oldTxAccount" name="tbConLoanTx.oldTxAccount" class="nui-text nui-form-input"  />	   
			
			<label class="nui-form-label">贴息方式：</label>
			<input id="tbConLoanTx.oldTxWay" name="tbConLoanTx.oldTxWay" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_TXFS0001"/>
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>贴息阶段一</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
	    	<label class="nui-form-label">贴息起期：</label>
			<input id="tbConLoanTx.oldFirTxStartdate" name="tbConLoanTx.oldFirTxStartdate" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">贴息止期：</label>
			<input id="tbConLoanTx.oldFirTxEnddate" name="tbConLoanTx.oldFirTxEnddate" class="nui-text nui-form-input" />
				
			<label class="nui-form-label">贴息比例：</label>
			<input id="tbConLoanTx.oldFirTxRate" class="nui-text nui-form-input" name="tbConLoanTx.oldFirTxRate"/>
			
			<label class="nui-form-label">固定金额：</label>
			<input id="tbConLoanTx.oldFirAmt"  name="tbConLoanTx.oldFirAmt" class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">限额：</label>
			<input id="tbConLoanTx.oldFirMaxamt"  name="tbConLoanTx.oldFirMaxamt" class="nui-text nui-form-input" dataType="currency"/>
		</div>		
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>贴息阶段二</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table4">
	    	<label class="nui-form-label">贴息起期：</label>
			<input id="tbConLoanTx.oldSecTxStartdate" name="tbConLoanTx.oldSecTxStartdate" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">贴息止期：</label>
			<input id="tbConLoanTx.oldSecTxEnddate" name="tbConLoanTx.oldSecTxEnddate" class="nui-text nui-form-input" />
				
			<label class="nui-form-label">贴息比例：</label>
			<input id="tbConLoanTx.oldSecTxRate" class="nui-text nui-form-input" name="tbConLoanTx.oldSecTxRate"/>
			
			<label class="nui-form-label">固定金额：</label>
			<input id="tbConLoanTx.oldSecAmt"  name="tbConLoanTx.oldSecAmt" class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">限额：</label>
			<input id="tbConLoanTx.oldSecMaxamt"  name="tbConLoanTx.oldSecMaxamt" class="nui-text nui-form-input" dataType="currency"/>
		</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>贴息阶段三</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table5">
	    	<label class="nui-form-label">贴息起期：</label>
			<input id="tbConLoanTx.oldTrdTxStartdate" name="tbConLoanTx.oldTrdTxStartdate" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">贴息止期：</label>
			<input id="tbConLoanTx.oldTrdTxEnddate" name="tbConLoanTx.oldTrdTxEnddate" class="nui-text nui-form-input" />
				
			<label class="nui-form-label">贴息比例：</label>
			<input id="tbConLoanTx.oldTrdTxRate" class="nui-text nui-form-input" name="tbConLoanTx.oldTrdTxRate"/>
			
			<label class="nui-form-label">固定金额：</label>
			<input id="tbConLoanTx.oldTrdAmt"  name="tbConLoanTx.oldTrdAmt" class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">限额：</label>
			<input id="tbConLoanTx.oldTrdMaxamt"  name="tbConLoanTx.oldTrdMaxamt" class="nui-text nui-form-input" dataType="currency"/>
		</div>	
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table6">
	    
	    	<label class="nui-form-label">是否贴息：</label>
			<input id="tbConLoanTx.newIsTx" name="tbConLoanTx.newIsTx" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" required="true"  />
				   
			<label class="nui-form-label">贴息账号：</label>
			<input id="tbConLoanTx.newTxAccount" name="tbConLoanTx.newTxAccount" class="nui-textbox nui-form-input" required="true" />	   
			
			<label class="nui-form-label">贴息方式：</label>
			<input id="tbConLoanTx.newTxWay" name="tbConLoanTx.newTxWay" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_TXFS0001" required="true" onvaluechanged="onselectType" />
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>贴息阶段一</span>
	    </legend>
	    	<div id="firStart" class="nui-dynpanel"  columns="4" >
		    	<label class="nui-form-label">贴息起期：</label>
				<input id="tbConLoanTx.newFirTxStartdate" name="tbConLoanTx.newFirTxStartdate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			</div>
				
			<div id="firEnd" class="nui-dynpanel"  columns="4" >
				<label class="nui-form-label">贴息止期：</label>
				<input id="tbConLoanTx.newFirTxEnddate" name="tbConLoanTx.newFirTxEnddate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			</div>
				
			<div id="firRate" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">贴息比例：</label>
				<input id="tbConLoanTx.newFirTxRate" class="nui-textbox nui-form-input" name="tbConLoanTx.newFirTxRate"/>
			</div>
			
			<div id="firAmt" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">固定金额：</label>
				<input id="tbConLoanTx.newFirAmt"  name="tbConLoanTx.newFirAmt" 
					class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			</div>
			
			<div id="firMaxamt" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">限额：</label>
				<input id="tbConLoanTx.newFirMaxamt"  name="tbConLoanTx.newFirMaxamt" 
					class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>贴息阶段二</span>
	    </legend>
	    	<div id="secStart" class="nui-dynpanel"  columns="4" >
		    	<label class="nui-form-label">贴息起期：</label>
				<input id="tbConLoanTx.newSecTxStartdate" name="tbConLoanTx.newSecTxStartdate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			</div>
			
			<div id="secEnd" class="nui-dynpanel"  columns="4" >
				<label class="nui-form-label">贴息止期：</label>
				<input id="tbConLoanTx.newSecTxEnddate" name="tbConLoanTx.newSecTxEnddate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			</div>
			
			<div id="secRate" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">贴息比例：</label>
				<input id="tbConLoanTx.newSecTxRate" class="nui-textbox nui-form-input" name="tbConLoanTx.newSecTxRate"/>
			</div>
			
			<div id="secAmt" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">固定金额：</label>
				<input id="tbConLoanTx.newSecAmt"  name="tbConLoanTx.newSecAmt" 
					class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			</div>
			
			<div id="secMaxamt" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">限额：</label>
				<input id="tbConLoanTx.newSecMaxamt"  name="tbConLoanTx.newSecMaxamt" 
					class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>贴息阶段三</span>
	    </legend>
	    	<div id="trdStart" class="nui-dynpanel"  columns="4" >
		    	<label class="nui-form-label">贴息起期：</label>
				<input id="tbConLoanTx.newTrdTxStartdate" name="tbConLoanTx.newTrdTxStartdate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			</div>
			
			<div id="trdEnd" class="nui-dynpanel"  columns="4" >
				<label class="nui-form-label">贴息止期：</label>
				<input id="tbConLoanTx.newTrdTxEnddate" name="tbConLoanTx.newTrdTxEnddate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			</div>
			
			<div id="trdRate" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">贴息比例：</label>
				<input id="tbConLoanTx.newTrdTxRate" class="nui-textbox nui-form-input" name="tbConLoanTx.newTrdTxRate"/>
			</div>
			
			<div id="trdAmt" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">固定金额：</label>
				<input id="tbConLoanTx.newTrdAmt"  name="tbConLoanTx.newTrdAmt" 
					class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			</div>
			
			<div id="trdMaxamt" class="nui-dynpanel"  columns="4" style="width:99.5%;">
				<label class="nui-form-label">限额：</label>
				<input id="tbConLoanTx.newTrdMaxamt"  name="tbConLoanTx.newTrdMaxamt" 
					class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
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
	<%-- var contractId ="<%=request.getParameter("contractId") %>";  --%>
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

<script>
	
	function onselectType(e){		
		var newTxWay= nui.get("tbConLoanTx.newTxWay").getValue();
		if(newTxWay=='01'){//比例
				$("#firRate").css("display","block");
				$("#secRate").css("display","block");
				$("#trdRate").css("display","block");
				$("#firAmt").css("display","none");
				$("#secAmt").css("display","none");
				$("#trdAmt").css("display","none");
				$("#firMaxamt").css("display","none");
				$("#secMaxamt").css("display","none");
				$("#trdMaxamt").css("display","none");
			}else if(newTxWay=='02'){//固定金额
				$("#firRate").css("display","none");
				$("#secRate").css("display","none");
				$("#trdRate").css("display","none");
				$("#firAmt").css("display","block");
				$("#secAmt").css("display","block");
				$("#trdAmt").css("display","block");
				$("#firMaxamt").css("display","none");
				$("#secMaxamt").css("display","none");
				$("#trdMaxamt").css("display","none");
			}else if(newTxWay=='03'){//限额+比例
				$("#firRate").css("display","block");
				$("#secRate").css("display","block");
				$("#trdRate").css("display","block");
				$("#firAmt").css("display","none");
				$("#secAmt").css("display","none");
				$("#trdAmt").css("display","none");
				$("#firMaxamt").css("display","block");
				$("#secMaxamt").css("display","block");
				$("#trdMaxamt").css("display","block");
			}else{//限额+比例
				$("#firRate").css("display","none");
				$("#secRate").css("display","none");
				$("#trdRate").css("display","none");
				$("#firAmt").css("display","none");
				$("#secAmt").css("display","none");
				$("#trdAmt").css("display","none");
				$("#firMaxamt").css("display","none");
				$("#secMaxamt").css("display","none");
				$("#trdMaxamt").css("display","none");
			}
	}
	
 </script>

</body>
</html>