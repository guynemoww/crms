<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-07-29 17:19:30
  - Description:
-->
<head>
<title>基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width:100%;height:auto;overflow:hidden;">
		<div id="fieldset1">
		<fieldset>
		  	<legend>
		    	<span>基本信息</span>
		    </legend>
		    <div class="nui-dynpanel" columns="4">
		    	<label class="nui-form-label">担保机构编号：</label>
				<input id="partyNum" name="partyNum" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
		    	<label class="nui-form-label">担保机构名称：</label>
				<input id="partyName" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
		    	<label class="nui-form-label">担保机构性质：</label>
				<input id="" name="" required="false" Enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  />
				
		    	<label class="nui-form-label">成立时间：</label>
				<input id="seupTime" name="seupTime" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
			
				<label class="nui-form-label">信用评级（内部）：</label>
				<input id="creditRatingCd" name="creditRatingCd" required="true" Enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  />
				
				<label class="nui-form-label">信用评级（外部）：</label>
				<input id="creditRatingCdEx" name="creditRatingCdEx" required="true" Enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  />
		    
		    	<label class="nui-form-label">注资资金：</label>
		    	<div>
					<input id="regCapital" name="regCapital" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
		        
		        <label class="nui-form-label">国资股权占比：</label>
				<div>
					<input id="stateOwnedShareR" name="stateOwnedShareR" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					%
		        </div>
		        
		        <label class="nui-form-label">净资产：</label>
				<div>
					<input id="netAssets" name="netAssets" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
		        
		    	<label class="nui-form-label">货币资金+存出保证金：</label>
				<div>
					<input id="financeBalAndDepositPaid" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
	
		    	<label class="nui-form-label">担保费收入：</label>
				<div>
					<input id="guaranteeFeeIncome" name="guaranteeFeeIncome" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
	
		    	<label class="nui-form-label">净利润：</label>
				<div>
					<input id="netProfit" name="netProfit" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
		        
		        <label class="nui-form-label">对方接待人员：</label>
				<input id="receptionName" name="receptionName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
				
				<label class="nui-form-label">职务：</label>
				<input id="receptionPost" name="receptionPost" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
				
				<label class="nui-form-label">现场检查时间：</label>
				<input id="inspectDate" name="inspectDate" required="false" setEnabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" vtype="maxLength:100" />
				
		    	<label class="nui-form-label">是否享受政府财政税收补偿机制：</label>
				<input id="isRevenue" name="isRevenue" required="true" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_0002"  />

		    	<label class="nui-form-label">本季度内股权有无发生变化：</label>
				<input id="ifChangeStock" name="ifChangeStock" required="true" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_0003"  />
	
		    	<label class="nui-form-label">后续是否有增资计划：</label>
				<input id="isHaveIncreasePlan" name="isHaveIncreasePlan" required="true" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_0002"  />
				
		    	<label class="nui-form-label">整体控制能力：</label>
				<input id="isOverallRiskControl" name="isOverallRiskControl" required="true" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_DHCD0017"  />
		    
		    	<label class="nui-form-label">代偿是否及时：</label>
				<input id="isCompensatoryTime" name="isCompensatoryTime" required="true" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_0002"  />
		        
		        <label class="nui-form-label">融资性在保余额：</label>
		        <div>
					<input id="bulgariaBal" name="bulgariaBal" onblur="valuechanged1()" vtype="float" required="false" class="nui-textbox nui-form-input"  />
					万元
		        </div>
		        
		    	<label class="nui-form-label">融资性在保户数：</label>
				<div>
					<input id="bulgariaHouseholds" name="bulgariaHouseholds" required="false" vtype="int" class="nui-textbox nui-form-input"  />
					户
		        </div>
	
		    	<label class="nui-form-label">担保放大倍数：</label>
				<input id="securityMagnification" name="securityMagnification" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
		    	<label class="nui-form-label">前十大户在保余额：</label>
				<div>
					<input id="topHoldingBal" name="topHoldingBal" onblur="valuechanged2()" vtype="float" required="false" class="nui-textbox nui-form-input"  />
					万元
		        </div>
				
				<label class="nui-form-label">前十大户在保余额占比：</label>
				<input id="topHoldingBalR" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		    
		    	<label class="nui-form-label">对外投资总额：</label>
				<div>
					<input id="foreignInvestmentAmt" name="foreignInvestmentAmt" onblur="valuechanged3()" vtype="float"  required="false" class="nui-textbox nui-form-input"  />
					万元
		        </div>
		        
		        <label class="nui-form-label">对外投资总额/净资产：</label>
				<input id="foreignInvestmentAmtR" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		        
		    	<label class="nui-form-label">对外投资总额是否超过20%：</label>
				<input id="foreignInvestmentAmtR2" required="true" class="nui-dictcombobox nui-form-input" Enabled="false" valueField="dictID" dictTypeId="XD_0002"  />
	
		    	<label class="nui-form-label">本年度累计代偿金额：</label>
				<div>
					<input id="annualTotal" name="annualTotal" required="false" vtype="float" class="nui-textbox nui-form-input"  />
					万元
		        </div>
	
		    	<label class="nui-form-label">其中: 我行累计代偿金额：</label>
				<div>
					<input id="annualTotalMb" name="annualTotalMb" required="false" vtype="float" class="nui-textbox nui-form-input"  />
					万元
		        </div>
				
				<label class="nui-form-label">本年度累计代偿笔数：</label>
				<div>
					<input id="annualCompensatoryNum" name="annualCompensatoryNum" required="false" vtype="int" class="nui-textbox nui-form-input"  />
					笔
		        </div>
		        
		        <label class="nui-form-label">其中: 我行累计代偿笔数：</label>
				<div>
					<input id="annualCompensatoryMb" name="annualCompensatoryMb" required="false" vtype="int" class="nui-textbox nui-form-input"  />
					笔
		        </div>
		        
		    	<label class="nui-form-label">担保机构应对外代偿未代偿金额合计：</label>
				<div>
					<input id="uncompensatedTotalAmt" name="uncompensatedTotalAmt" vtype="float" required="false" class="nui-textbox nui-form-input"  />
					万元
		        </div>
		    </div>
		    <div class="nui-dynpanel" columns="4">
				    <label class="nui-form-label">备注：</label>
				    <div colspan="3">
						<input id="remark1" name="remark1" required="false" setEnabled="false" class="nui-textarea nui-form-input" vtype="maxLength:100" />
					</div>
			</div>
	    </fieldset>
	    </div>
	    <div id="fieldset2">
	    <fieldset>
		  	<legend>
		    	<span>授信后评价（年度）</span>
		    </legend>
		    <div class="nui-dynpanel" columns="4">
		    	<label class="nui-form-label">近三年累计代偿金额：</label>
				<div>
					<input id="nearly3CompensatoryAmt" name="nearly3CompensatoryAmt" required="false" vtype="float" class="nui-textbox nui-form-input"  />
					万元
		        </div>
		        
		        <label class="nui-form-label">近三年累计解除担保责任金额：</label>
				<div>
					<input id="nearly3ReleaseAmt" name="nearly3ReleaseAmt" required="false" vtype="float" onblur="valuechanged4()" class="nui-textbox nui-form-input"  />
					万元
		        </div>
		        
		        <label class="nui-form-label">近三年累计代偿回收金额：</label>
				<div>
					<input id="nearly3RecoveryAmt" name="nearly3RecoveryAmt" required="false" onblur="valuechanged5()" vtype="float" class="nui-textbox nui-form-input"  />
					万元
		        </div>
		        
		        <label class="nui-form-label">担保准备余额：</label>
				<div>
					<input id="reserveBal" name="reserveBal" required="false" vtype="float" onblur="valuechanged6()" class="nui-textbox nui-form-input"  />
					万元
		        </div>
		        
		        <label class="nui-form-label">累计担保代偿率%：</label>
				<input id="nearly3ReleaseAmtR" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
				
				<label class="nui-form-label">累计代偿回收率%：</label>
				<input id="nearly3RecoveryAmtR" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
				
				<label class="nui-form-label">准备金充足率%：</label>
				<input id="reserveBalR" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		    </div>
	    </fieldset>
	    </div>
	    <div id="fieldset3">
	    <fieldset>
		  	<legend>
		    	<span>行为判断</span>
		    </legend>
			    <div class="nui-dynpanel" columns="4">
			    	<label class="nui-form-label">是否取得经营许可证：</label>
					<input id="isHaveLicense" name="isHaveLicense" required="true" onitemclick="valueChange()" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_0002"  />
					
					<label class="nui-form-label">经营许可证有效期：</label>
					<div>
						<input id="licenseDateBegin" name="licenseDateBegin" enabled="false" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:100px;" />
						至
						<input id="licenseDateEnd" name="licenseDateEnd" required="true" enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width:100px;" />
						<!--<input id="" name=""  required="true" class="nui-datepicker nui-form-input"   format="yyyy-MM-dd"/>-->
					</div>
					
					<label class="nui-form-label">已建立台账并与我行核对无误：</label>
					<input id="isHaveCheckingMb" name="isHaveCheckingMb" required="true" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_0002"  />
					
					<label class="nui-form-label">是否存在预警信号清单中列示的点：</label>
					<div>
						<input id="isRiskEarlyWarning" name="isRiskEarlyWarning" required="true"  enabled="false" class="nui-dictcombobox nui-form-input" valueField="dictID" dictTypeId="XD_0002"  />
						<input id="riskWarningSignals" name="riskWarningSignals" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
					</div>
					<label class="nui-form-label">其他事项：</label>
					<div colspan="3">
						<input id="otherRiskIssues" name="otherRiskIssues" required="false" class="nui-textarea nui-form-input" vtype="maxLength:100" />
					</div>
					<!--<input id="otherRiskIssues" name="otherRiskIssues" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  />-->
				</div>
				<div class="nui-dynpanel" columns="4">
				    <label class="nui-form-label">备注：</label>
				    <div colspan="3">
						<input id="remark2" name="remark2" required="false" class="nui-textarea nui-form-input" vtype="maxLength:100" />
					</div>
			    </div>
	    </fieldset>
	    </div>
	</div>
	<div id="save" class="nui-toolbar"  style="border:0;text-align:right;margin-top: 20px;">
		<a class="nui-button" id="saveBtn" iconCls="icon-save" onclick="save()">保存</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var giId = "<%=request.getParameter("giId")%>";
	var partyId = "<%=request.getParameter("partyId")%>";
	var form = new nui.Form("#form");
	var posicode = "<%=request.getParameter("posicode") %>";
	var state;//用于控制页面能否编辑（状态）
	var financeBalAndDepositPaid;//货币资金+存出保证金
	var flg;//用于区分是否第一季度（1：是，0：否）
	init();
	var flgDisplay;//用于判断是否可编辑
	function getReplacePos(){
     var json = nui.encode({"posicode":posicode});
     nui.ajax({
         url: "com.bos.irm.queryInfo.queryReplacePos.biz.ext",
         type: 'POST',
         data: json,
         cache: false,
         async:false,        
         contentType:'text/json',
         success: function (text) {
             var o = nui.decode(text);
             flgDisplay = o.posCd;
         }
     });
 } 
	
	
	function init(){
		var dateNow;
		getReplacePos();
		var json=nui.encode({"giId":giId,"partyId":partyId});
		nui.ajax({
			url: "com.bos.aft.aft_warrant.queryBaseInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async:false,
            success: function (text) {
            	if(text.msg){
            		alert(text.msg);
            	}else {
	                var o = nui.decode(text);
	                state = o.out.inspectState;
	                dateNow = o.timeNow.substring(5,7);
	                form.setData(o.out);
	                financeBalAndDepositPaid = o.out.financeBal + o.out.depositPaid;
	                nui.get("financeBalAndDepositPaid").setValue(financeBalAndDepositPaid);
	                nui.get("partyName").setValue(o.partyName);
	                var a = o.out.bulgariaBal;//融资性在保余额
				    var b = o.out.topHoldingBal;//前十大户在保余额
				    var c = o.out.foreignInvestmentAmt;//对外投资总额
				    if(a){
				         valuechanged1();
				    }
				    if(b){
				         valuechanged2();
				    }
				    if(c){
				         valuechanged3();
				    }
				}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
		});
		if(dateNow < "04"){	
			flg=1;
			valuechanged4();
	    	valuechanged5();
	    	valuechanged6();
		}else{
			flg=0;
			$("#fieldset2").hide();
		}
		if(flgDisplay == "P1001"){
		
		}else{
		   $("#save").hide();
			form.setEnabled(false);//设置页面不能被编辑
		}
		/*if(state == 0){
			
		}else{
			$("#save").hide();
			form.setEnabled(false);//设置页面不能被编辑
		}*/
		valueChange();
	}
	
	/*设定“存在预警信号清单中列示的点”和“是否存在预警信号清单中列示的点”
	function queryWarningSignal(){
		json = nui.encode({"partyId":partyId});
		nui.ajax({
			url: "com.bos.aft.aft_warrant.queryWarningSignal.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async:false,
            success: function (text) {
            	var o = nui.decode(text);
            	if(o.flg == 1){//存在
            		nui.get("isRiskEarlyWarning").setValue("1");
            	}else{
            		nui.get("isRiskEarlyWarning").setValue("0");
            	}
            	nui.get("riskWarningSignals").setValue(o.outString);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
		});
	}
	*/
	//设定“担保放大倍数”的值(融资性在保余额/净资产)
	function valuechanged1(){
		//融资性在保余额
		var a = nui.get("bulgariaBal").getValue();
		//净资产
		var b = nui.get("netAssets").getValue();
		
		if(b == 0){
			alert("净资产为0，不能计算“担保放大倍数”！");
		}else{
			var c = a / b;
			nui.get("securityMagnification").setValue(c);
		}
	}
	
	//设定“前十大户在保余额占比”的值(前十大户在保余额/融资性在保余额)
	function valuechanged2(){
		//前十大户在保余额
		var a = nui.get("topHoldingBal").getValue();
		//融资性在保余额
		var b = nui.get("bulgariaBal").getValue();
		if(b == 0){
			alert("融资性在保余额为0，不能计算“前十大户在保余额占比”！");
		}else if(b == null){
			alert("融资性在保余额为空，不能计算“前十大户在保余额占比”！");
		}else{
			var c = a / b;
			nui.get("topHoldingBalR").setValue(c);
		}
	}
	
	//设定“对外投资总额/净资产”的值（对外投资总额/净资产）和“对外投资总额是否超过20%”
	function valuechanged3(){
		//对外投资总额
		var a = nui.get("foreignInvestmentAmt").getValue();
		//净资产
		var b = nui.get("netAssets").getValue();
		if(b == 0){
			alert("净资产为0，不能计算“对外投资总额/净资产”！");
		}else{
			var c = a / b;
			nui.get("foreignInvestmentAmtR").setValue(c);
		}
		if(c > 0.2){
			nui.get("foreignInvestmentAmtR2").setValue("1");
		}else{
			nui.get("foreignInvestmentAmtR2").setValue("0");
		}
	}
	
	//设定”累计担保代偿率”的值	
	function valuechanged4(){
		var a = nui.get("nearly3CompensatoryAmt").getValue();
		var b = nui.get("nearly3ReleaseAmt").getValue();
		var c = a / b;
		nui.get("nearly3ReleaseAmtR").setValue(c);
	}
	
	//设定”累计代偿回收率”的值
	function valuechanged5(){
		var a = nui.get("nearly3CompensatoryAmt").getValue();
		var b = nui.get("nearly3RecoveryAmt").getValue();
		var c = a / b;
		nui.get("nearly3RecoveryAmtR").setValue(c);
	}
	
	//设定”准备金充足率”的值
	function valuechanged6(){
		var a = nui.get("reserveBal").getValue();
		var b = nui.get("bulgariaBal").getValue();
		var c = a / b;
		nui.get("reserveBalR").setValue(c);
	}
	
	function valueChange(){
		var value = nui.get("isHaveLicense").getValue();
		if(value == 1){
			nui.get("licenseDateBegin").setEnabled(true);
			nui.get("licenseDateEnd").setEnabled(true);
		}else{
			nui.get("licenseDateBegin").setEnabled(false);
			nui.get("licenseDateEnd").setEnabled(false);
		}
	}
	
	function save(){
		form.validate();
        if (form.isValid() == false){
        	alert("请填写完正确的值！");
        	return;
        } 
		var item = form.getData();
		var json=nui.encode({"giId":giId,"item":item,"flg":flg});
		nui.ajax({
			url: "com.bos.aft.aft_warrant.saveBaseInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async:false,
            success: function (text) {
            	if(text.msg == 1){
            		alert("保存成功");
            	}else{
            		alert("保存失败");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
		});
	}

</script>
</body>
</html>