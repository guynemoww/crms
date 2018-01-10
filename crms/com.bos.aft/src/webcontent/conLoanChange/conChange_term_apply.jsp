<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>合同期限变更申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	
	<input id="tbConContractInfo.cycleUnit" class="nui-hidden nui-form-input" name ="tbConContractInfo.cycleUnit"/>
	
	<input id="tbConLoanChange.oldRepayWay" class="nui-hidden nui-form-input" name ="tbConLoanChange.oldRepayWay"/>
	
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
	    
	    	<label class="nui-form-label">贷款期限：</label>
			<input id="tbConLoanChange.oldTerm" name="tbConLoanChange.oldTerm" class="nui-text nui-form-input"  />

			<label class="nui-form-label">到期日期：</label>
			<input id="tbConLoanChange.oldEndDate" name="tbConLoanChange.oldEndDate" class="nui-text nui-form-input"  />
			
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

	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
	    
	    	<label class="nui-form-label">期限变更方式：</label>
			<!-- <input id="tbConLoanChange.termChangeWay" class="nui-textbox nui-form-input" name="tbConLoanChange.termChangeWay"/> -->
			<input id="tbConLoanChange.termChangeWay" name="tbConLoanChange.termChangeWay" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="XD_QXBG0001" onvaluechanged="calEndDate" />
			
			<label class="nui-form-label">贷款期限：</label>
			<input id="tbConLoanChange.newTerm" class="nui-textbox nui-form-input" name="tbConLoanChange.newTerm" 
				vtype="int;maxLength:8" required="true" onblur="calEndDate" />
			
			<label class="nui-form-label">到期日期：</label>
			<input id="tbConLoanChange.newEndDate" name="tbConLoanChange.newEndDate" class="nui-textbox nui-form-input"  />
			
			<label class="nui-form-label">基准利率：</label>
			<input id="tbConLoanChange.oldBaseRateValue2" class="nui-text nui-form-input" name="tbConLoanChange.oldBaseRateValue"/>

			<label class="nui-form-label">利率类型：</label>
			<input id="tbConLoanChange.oldRateType2" name="tbConLoanChange.oldRateType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1016"/>
				   
			<label class="nui-form-label">浮动形式：</label>
			<input id="tbConLoanChange.oldFloatType2" name="tbConLoanChange.oldFloatType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1145"/>
				   
			<label class="nui-form-label">浮动方式：</label>
			<input id="tbConLoanChange.oldFloatWay2" name="tbConLoanChange.oldFloatWay" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1147"/>
				   
			<label class="nui-form-label">浮动比例/浮动值：</label>
			<input id="tbConLoanChange.oldRateFloatProportion2" class="nui-text nui-form-input" name="tbConLoanChange.oldRateFloatProportion"/>
			
			<label class="nui-form-label">执行利率：</label>
			<input id="tbConLoanChange.oldYearRate2" class="nui-text nui-form-input" name="tbConLoanChange.oldYearRate"/>

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
            	nui.get("tbConLoanChange.oldRateType").setValue(o.tbConLoanChange.oldRateType);//值被覆盖，重新赋值nui.get("id").setValue(o.name)
				//nui.get("tbConLoanChange.oldEndDate").setValue(o.tbConLoanChange.oldEndDate);
				nui.get("tbConLoanChange.oldBaseRateValue").setValue(o.tbConLoanChange.oldBaseRateValue);
				nui.get("tbConLoanChange.oldFloatType").setValue(o.tbConLoanChange.oldFloatType);
				nui.get("tbConLoanChange.oldFloatWay").setValue(o.tbConLoanChange.oldFloatWay);
				nui.get("tbConLoanChange.oldRateFloatProportion").setValue(o.tbConLoanChange.oldRateFloatProportion);
				nui.get("tbConLoanChange.oldYearRate").setValue(o.tbConLoanChange.oldYearRate);
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
	
	function calEndDate(){
		var termChangeWay= nui.get("tbConLoanChange.termChangeWay").getValue();
		var newTerm= nui.get("tbConLoanChange.newTerm").getValue();
		var cycleUnit= nui.get("tbConContractInfo.cycleUnit").getValue();
		nui.get("tbConLoanChange.newEndDate").setEnabled(false);
		
		//将数据库中的Timestamp格式转换成yyyy/MM/dd
    	var tempStr = nui.get("tbConContractInfo.endDate").getValue().substr(0,4) 
    	   + "/" + nui.get("tbConContractInfo.endDate").getValue().substr(5,2) 
    	   + "/" + nui.get("tbConContractInfo.endDate").getValue().substr(8,2);
    	//再转换成日期类型
    	var tempDate = new Date(tempStr);
    	var oldRepayWay= nui.get("tbConLoanChange.oldRepayWay").getValue();
		if(termChangeWay=="01"){//延期
			if(oldRepayWay=="0100" || oldRepayWay=="0200" || oldRepayWay=="0300" || oldRepayWay=="0400") {
				return nui.alert("只有按周期还息到期一次还本，到期一次性还本付息，按周期还息任意还本，按周期还息按还本计划表还本，按还本计划表还息按还本计划表还本可以做延期");
			}
			
	    	if(cycleUnit=="01"){//年
	    		//年份增
		    	tempDate.setFullYear(tempDate.getFullYear()+parseInt(newTerm));
		    	
			}else if(cycleUnit=="02"){//半年
			    
			}else if(cycleUnit=="03"){//季
			    
			}else if(cycleUnit=="04"){//月
			    //月份增
		    	tempDate.setMonth(tempDate.getMonth()+parseInt(newTerm));
		    		
			}else if(cycleUnit=="05"){//日
				//日增
		    	tempDate.setDate(tempDate.getDate()+parseInt(newTerm));
		    	
			}
		}else if(termChangeWay=="02"){//缩期
			
			if(oldRepayWay=="1100" || oldRepayWay=="1200" || oldRepayWay=="1300" || oldRepayWay=="1400" || oldRepayWay=="1410") {
				return nui.alert("只有等额本金，等额本息，阶段性等额本息，阶段性等额本金可以做缩期");
			}
			
			if(cycleUnit=="01"){//年
				//年份减
		    	tempDate.setFullYear(tempDate.getFullYear()-parseInt(newTerm));
	
			}else if(cycleUnit=="02"){//半年
			    
			}else if(cycleUnit=="03"){//季
			    
			}else if(cycleUnit=="04"){//月
			    //月份减
		    	tempDate.setMonth(tempDate.getMonth()-parseInt(newTerm));
		    	
			}else if(cycleUnit=="05"){//日
			    //日减
		    	tempDate.setDate(tempDate.getDate()-parseInt(newTerm));
		    	
			}
			
		}
		
		//最后将日期类型转换为字符型
    	var result = tempDate.getFullYear().toString() 
    		+ "-" + (tempDate.getMonth()+1).toString() 
    		+ "-" + tempDate.getDate().toString();
    	nui.get("tbConLoanChange.newEndDate").setValue(result);
		
	}

</script>
</body>
</html>