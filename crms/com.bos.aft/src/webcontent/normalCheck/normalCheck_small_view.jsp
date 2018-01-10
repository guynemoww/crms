<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-08-05
  - Description:
-->
<head>
<title>日常检查申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbAftNormalCheck.normalCheckId" class="nui-hidden nui-form-input" name ="tbAftNormalCheck.normalCheckId"/>
	<input id="tbAftNormalCheck.checkType" class="nui-hidden nui-form-input" name ="tbAftNormalCheck.checkType"/>
	<input id="tbAftNormalCheck.partyId" class="nui-hidden nui-form-input" name ="tbAftNormalCheck.partyId"/>
	<input id="tbAftTrackPerson.checkPersonId" class="nui-hidden nui-form-input" name ="tbAftTrackPerson.checkPersonId"/>
	
	<fieldset>
		<legend>
	    	<span>客户基本信息</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="4" id="table00">
	    
	    	<label class="nui-form-label">客户编号：</label>
			<input  id="items[0].PARTY_NUM" name="items[0].PARTY_NUM" class="nui-text nui-form-input" /> 
	    
	    	<label class="nui-form-label">客户名称：</label>
			<input  id="items[0].PARTY_NAME" name="items[0].PARTY_NAME" class="nui-text nui-form-input" /> 
			
			<label class="nui-form-label">证件号码：</label>
			<input  id="items[0].CERT_NUM" name="items[0].CERT_NUM" class="nui-text nui-form-input" /> 
			
			<label class="nui-form-label">贷款总金额：</label>
			<input  id="items[0].PFJE" name="items[0].PFJE" class="nui-text nui-form-input" 
					dataType="currency" /> 
					
			<label class="nui-form-label">贷款总余额：</label>
			<input  id="items[0].JJYE" name="items[0].JJYE" class="nui-text nui-form-input" 
					dataType="currency" />  
			
	    </div>
	</fieldset>
	
	<div class="nui-dynpanel" columns="1" id="table0">
		<%@include file="/aft/normalCheck/summary_list.jsp"%>
	</div>
	
	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="4" id="table1">
	    
	    	<label class="nui-form-label">检查原因：</label>
			<input id="tbAftNormalCheck.checkReason" name="tbAftNormalCheck.checkReason" 
						class="nui-text nui-form-input" dictTypeId="XD_DHBG0003"  />
			
			<label class="nui-form-label">检查日期：</label>
			<input id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" 
					class="nui-text nui-form-input" format="yyyy-MM-dd"/>
	    	
	    	<label class="nui-form-label">检查地点：</label>
			<input  id="tbAftNormalCheck.checkPlace" name="tbAftNormalCheck.checkPlace"  class="nui-text nui-form-input"  />
			
	    	<label class="nui-form-label">检查次数：</label>
			<input  id="tbAftNormalCheck.checkCount" name="tbAftNormalCheck.checkCount" class="nui-text nui-form-input"  />
						
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>当前贷款现状</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    
	    	<label class="nui-form-label">所有银行借款总额（万元）：</label>
			<input  id="tbAftTrackPerson.loanAmt" name="tbAftTrackPerson.loanAmt" class="nui-text nui-form-input" 
					vtype="float;maxLength:20" dataType="currency" /> 
					
			<label class="nui-form-label">检查期限内归还贷款及借款情况：</label>
			<input  id="tbAftTrackPerson.repayCondition" name="tbAftTrackPerson.repayCondition" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">检查期限内欠款情况：</label>
			<input  id="tbAftTrackPerson.debtCondition" name="tbAftTrackPerson.debtCondition" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">逾期原因：</label>
			<input  id="tbAftTrackPerson.exceedReason" name="tbAftTrackPerson.exceedReason" class="nui-text nui-form-input"  />
			
	    </div>
	</fieldset>
	
	<fieldset id="table3">
		<legend>
	    	<span>保证人现状</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" >
	    
	    	<label class="nui-form-label">保证人的生产经营情况：</label>
			<input id="tbAftTrackPerson.guarantorBusiness" name="tbAftTrackPerson.guarantorBusiness" 
					class="nui-text nui-form-input" dictTypeId="XD_DHMS0001"  />
					
			<label class="nui-form-label">保证人财务状况：</label>
			<input id="tbAftTrackPerson.guarantorFinance" name="tbAftTrackPerson.guarantorFinance"  
					class="nui-text nui-form-input" dictTypeId="XD_DHMS0001"  />
					
			<label class="nui-form-label">保证人与借款人关系：</label>
			<input id="tbAftTrackPerson.guarantorRelation" name="tbAftTrackPerson.guarantorRelation"  
					class="nui-text nui-form-input" dictTypeId="XD_DHMS0001"  />
					
			<label class="nui-form-label">保证人是否发生影响我行债权安全的重大事项：</label>
			<input id="tbAftTrackPerson.isGuarantorDebt" name="tbAftTrackPerson.isGuarantorDebt"  
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">担保有无：</label>
			<input id="tbAftTrackPerson.isGuarantorRisk" name="tbAftTrackPerson.isGuarantorRisk"  
					class="nui-text nui-form-input" dictTypeId="CsmHaveOrNot"  /> 
					
			<label class="nui-form-label">担保分析：</label>
			<input  id="tbAftTrackPerson.guarantorOthers" name="tbAftTrackPerson.guarantorOthers" class="nui-text nui-form-input"  />
			
			
	    </div>
	</fieldset>
	
	<fieldset id="table4">
		<legend>
	    	<span>抵（质）押物现状</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" >
	    
	    	<label class="nui-form-label">抵（质）押物保管、保存是否完好：</label>
			<input id="tbAftTrackPerson.isMortgageGood" name="tbAftTrackPerson.isMortgageGood" 
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">抵（质）押物现值是否有重大变化：</label>
			<input id="tbAftTrackPerson.isMortgageChange" name="tbAftTrackPerson.isMortgageChange"  
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">抵（质）押率是否控制在规定的范围内：</label>
			<input id="tbAftTrackPerson.isMortgageControl" name="tbAftTrackPerson.isMortgageControl" 
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">借款人是否擅自改变抵质押物的占管人、使用人：</label>
			<input id="tbAftTrackPerson.isMortgageChaOwn" name="tbAftTrackPerson.isMortgageChaOwn"  
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">借款人是否擅自转让、赠与、出租、设定担保物权等：</label>
			<input id="tbAftTrackPerson.isMortgageRent" name="tbAftTrackPerson.isMortgageRent" 
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">抵（质）押物是否有：</label>
			<input id="tbAftTrackPerson.isMortgageRisk" name="tbAftTrackPerson.isMortgageRisk"  
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			
	    </div>
	</fieldset>
	
	<fieldset id="table5">
	
	    <div class="nui-dynpanel" columns="4" >
	    
	    	
			<label class="nui-form-label">担保分析：</label>
			<input  id="tbAftTrackPerson.mortgageOthers" name="tbAftTrackPerson.mortgageOthers" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">借款人工作单位、地址、联系电话等信息的变更情况，借款人品行、职业、收入、住所和健康状况等影响还款能力和诚意的因素变化情况说明：</label>
			<input  id="tbAftTrackPerson.customerCondition" name="tbAftTrackPerson.customerCondition" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">借款人家庭变化情况是否会对贷款产生影响：</label>
			<input id="tbAftTrackPerson.isFamilyAffect" name="tbAftTrackPerson.isFamilyAffect" 
					class="nui-text nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">其他可能影响个人贷款资产质量的因素变化情况：</label>
			<input  id="tbAftTrackPerson.otherCondition" name="tbAftTrackPerson.otherCondition" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">处理意见：</label>
			<input id="tbAftNormalCheck.dealOpinion" name="tbAftNormalCheck.dealOpinion" 
					class="nui-text nui-form-input" dictTypeId="XD_DHBG0004"  />
			
			<label class="nui-form-label">具体建议：</label>
			<input  id="tbAftNormalCheck.opinionDetail" name="tbAftNormalCheck.opinionDetail" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">系统录入时间：</label>
			<input  id="tbAftNormalCheck.createDate" name="tbAftNormalCheck.createDate" class="nui-text nui-form-input"  /> 
			
	    </div>
	</fieldset>

	<!-- <div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div>  -->

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var normalCheckId ="<%=request.getParameter("normalCheckId") %>";
	$("#table0").css("display","block");
	
	$("#table3").css("display","none");//保证人
	$("#table4").css("display","none");//抵质押
	
	//初始化页面
	initPage();
	function initPage(){
		var json = nui.encode({"normalCheckId":normalCheckId});
		$.ajax({
            url: "com.bos.aft.normalCheck.findNormalCheck.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            
            	if(mydata.subConBZ != null && mydata.subConBZ != "") {
            		if(mydata.subConBZ == "04") {
	            		$("#table3").css("display","block");
            		}
            	}
            	if(mydata.subConDZY != null && mydata.subConDZY != "") {
            		if(mydata.subConDZY == "01" || mydata.subConDZY == "02") {
            			$("#table4").css("display","block");
            		}
            	}
            
            	var o = nui.decode(mydata);
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
		var json = nui.decode({"partyId":o.tbAftNormalCheck.partyId});
		var grid1 = nui.get("grid1");
    	grid1.load(json);
	} 
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        nui.get("con_contract_info_save").setEnabled(false);
		var o = form.getData();
		
		o.tbAftNormalCheck.normalCheckId=normalCheckId
		var summaryItems = nui.get("grid1").getChanges();
		o.normalCheckId = normalCheckId;
		o.summaryItems = summaryItems;
		
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.normalCheck.saveNormalCheck.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	alert("保存成功！");
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
	
</script>
</body>
</html>