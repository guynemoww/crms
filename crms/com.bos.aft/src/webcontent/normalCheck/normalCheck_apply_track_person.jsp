<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-04
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
	
 		<%@include file="/aft/normalCheck/summary_list3.jsp"%>
 	
	<fieldset>
		<legend>
	    	<span>当前贷款现状</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    
	    	<label class="nui-form-label">在他行或个人借款总额（万元）：</label>
			<input  id="tbAftTrackPerson.loanAmt" name="tbAftTrackPerson.loanAmt" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="" /> 
					
			<label class="nui-form-label">归还贷款及借款情况：</label>
			<input  id="tbAftTrackPerson.repayCondition" name="tbAftTrackPerson.repayCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">欠款情况：</label>
			<input  id="tbAftTrackPerson.debtCondition" name="tbAftTrackPerson.debtCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">当前信用报告显示是否正常：</label>
			<input id="tbAftTrackPerson.isNormal" name="tbAftTrackPerson.isNormal" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	<!--  			
			<label class="nui-form-label">还款意愿：</label>
			<input id="tbAftTrackPerson.repayWish" name="tbAftTrackPerson.repayWish" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_HKYY0001"  />
					
			<label class="nui-form-label">是否有还款能力：</label>
			<input id="tbAftTrackPerson.isRepayAbility" name="tbAftTrackPerson.isRepayAbility" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  /> 
			-->	
	    </div>
	</fieldset>
	<!--  
	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="4" id="table1">
	    
	    	<label class="nui-form-label">检查方式：</label>
			<input id="tbAftNormalCheck.checkWay" name="tbAftNormalCheck.checkWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  />
						
			<label class="nui-form-label">检查日期：</label>
			<input  id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" class="nui-text nui-form-input"  />
			<!-- <input id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/> 
	    </div>
	</fieldset>
	-->
	
	<fieldset>
		<legend>
	    	<span>保证人现状</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
	    
	    	<label class="nui-form-label">保证人的生产经营情况：</label>
			<input id="tbAftTrackPerson.guarantorBusiness" name="tbAftTrackPerson.guarantorBusiness" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHMS0001"  />
					
			<label class="nui-form-label">保证人财务状况：</label>
			<input id="tbAftTrackPerson.guarantorFinance" name="tbAftTrackPerson.guarantorFinance" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHMS0001"  />
					
			<label class="nui-form-label">保证人与借款人关系：</label>
			<input id="tbAftTrackPerson.guarantorRelation" name="tbAftTrackPerson.guarantorRelation" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHMS0001"  />
					
			<label class="nui-form-label">保证人有无发生影响我行债权安全的重大事项：</label>
			<input id="tbAftTrackPerson.isGuarantorDebt" name="tbAftTrackPerson.isGuarantorDebt" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_0003"  />
					
			<label class="nui-form-label">保证有无风险：</label>
			<input id="tbAftTrackPerson.isGuarantorRisk" name="tbAftTrackPerson.isGuarantorRisk" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CsmHaveOrNot"  /> 
					
			<label class="nui-form-label">其他情况：</label>
			<input  id="tbAftTrackPerson.guarantorOthers" name="tbAftTrackPerson.guarantorOthers"   class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>抵（质）押物现状</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table4">
	    
	    	<label class="nui-form-label">抵（质）押物保管、保存是否完好：</label>
			<input id="tbAftTrackPerson.isMortgageGood" name="tbAftTrackPerson.isMortgageGood" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">抵（质）押物现值有无重大变化：</label>
			<input id="tbAftTrackPerson.isMortgageChange" name="tbAftTrackPerson.isMortgageChange" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_0003"  />
					
			<label class="nui-form-label">抵（质）押率是否控制在规定的范围内：</label>
			<input id="tbAftTrackPerson.isMortgageControl" name="tbAftTrackPerson.isMortgageControl" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">借款人是否擅自改变抵质押物的占管人、使用人：</label>
			<input id="tbAftTrackPerson.isMortgageChaOwn" name="tbAftTrackPerson.isMortgageChaOwn" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">借款人是否擅自转让、赠与、出租、设定担保物权等：</label>
			<input id="tbAftTrackPerson.isMortgageRent" name="tbAftTrackPerson.isMortgageRent" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
					
			<label class="nui-form-label">抵（质）押物有无风险：</label>
			<input id="tbAftTrackPerson.isMortgageRisk" name="tbAftTrackPerson.isMortgageRisk" required="false" 
					class="nui-dictcombobox nui-form-input" dictTypeId="XD_0003"  />
					
			<label class="nui-form-label">其他情况：</label>
			<input  id="tbAftTrackPerson.mortgageOthers" name="tbAftTrackPerson.mortgageOthers"   class="nui-textarea nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">借款人工作单位、地址、联系电话等信息的变更情况，借款人品行、职业、收入、住所和健康状况等影响还款能力和诚意的因素变化情况：</label>
			<input  id="tbAftTrackPerson.customerCondition" name="tbAftTrackPerson.customerCondition"   class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>借款人当前财务情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table5">
	    
	    	<label class="nui-form-label">财务数据截止时间：</label>
			<input id="tbAftTrackPerson.financeEndDate" name="tbAftTrackPerson.financeEndDate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
					
			<label class="nui-form-label">总资产（万元）：</label>
			<input  id="tbAftTrackPerson.assetAmt" name="tbAftTrackPerson.assetAmt" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="" onblur="jsfzl"/> 
					
			<label class="nui-form-label">总负债（万元）：</label>
			<input  id="tbAftTrackPerson.debtAmt" name="tbAftTrackPerson.debtAmt" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="" onblur="jsfzl"/>
					
			<label class="nui-form-label">资产负债率%：</label>
			<input id="tbAftTrackPerson.assetDebtRate" name="tbAftTrackPerson.assetDebtRate" class="nui-text nui-form-input"    />
			
			<label class="nui-form-label">我行资金流量情况：</label>
			<input  id="tbAftTrackPerson.amtFlowCondition" name="tbAftTrackPerson.amtFlowCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">存贷比%：</label>
			<input id="tbAftTrackPerson.loanRate" name="tbAftTrackPerson.loanRate" class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:20"  />
	
					
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>本期贷款使用情况</span>
	    </legend>
	    <%@include file="/aft/normalCheck/money_use_list1.jsp"%>
	</fieldset>
	
		    <div class="nui-dynpanel" columns="4" >
	
	<label class="nui-form-label">借款人家庭变化情况是否会对贷款产生影响：</label>
			<input id="tbAftTrackPerson.isFamilyAffect" name="tbAftTrackPerson.isFamilyAffect" required="true" 
					class="nui-textarea nui-form-input" vtype="maxLength:1000"   />
	
	<label class="nui-form-label">通过走访开发商和对个人房产贷款项目进行实地考虑，掌握项目贷款资金使用和工程进度、房屋所有权证及抵押他项权证的办理情况：</label>
			<input id="tbAftTrackPerson.qzblCondition" name="tbAftTrackPerson.qzblCondition" required="true" 
					class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
					
	<label class="nui-form-label">其他可能影响个人贷款资产质量的因素变化情况：</label>
			<input  id="tbAftTrackPerson.otherCondition" name="tbAftTrackPerson.otherCondition"   class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
		
		
		</div>	
	<fieldset>
		<legend>
	    	<span>检查发现问题处理意见</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table6">
	    
	    	<label class="nui-form-label">处理意见：</label>
			<input  id="tbAftNormalCheck.checkResult" name="tbAftNormalCheck.checkResult" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0112"  />
					
			<label class="nui-form-label">检查人：</label>
			<input  id="tbAftNormalCheck.checkPerson" name="tbAftNormalCheck.checkPerson" class="nui-text nui-form-input"  /> 
			
			<label class="nui-form-label">检查日期：</label>
			<input  id="tbAftNormalCheck.createDate" name="tbAftNormalCheck.createDate" class="nui-datepicker nui-form-input"  /> 
			
			<label class="nui-form-label">检查地点：</label>
			<input  id="tbAftNormalCheck.checkPlace" name="tbAftNormalCheck.checkPlace" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">检查方式：</label>
			<input id="tbAftNormalCheck.checkWay" name="tbAftNormalCheck.checkWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0111"  />
			
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
	var normalCheckId ="<%=request.getParameter("normalCheckId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识  
	var partyId;
	var subConBZ="";
	var subConDZY="";
	
 	
 	//初始化页面
	initPage();
	function initPage(){ 
		var json = nui.encode({"normalCheckId":normalCheckId});
		$.ajax({
            url: "com.bos.aft.normalCheck.findNormalCheck.biz.ext",
            type: 'POST',
            data: json,
            cache: false,        
            async: false,
            
            contentType:'text/json',
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	subConBZ=o.subConBZ;
            	subConDZY=o.subConDZY;
            	jcdbfs();
            	
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
  		var o = form.getData();
		var json = nui.decode({"partyId":o.tbAftNormalCheck.partyId});
		var grid1 = nui.get("grid1");
     	grid1.load(json);
   
 
	} 
    
	  	function jcdbfs(){
	  	     //alert(subConBZ+"!!"+subConDZY);
	  if(null!=subConDZY){
	 	 			nui.get("tbAftTrackPerson.isMortgageGood").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isMortgageChange").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isMortgageControl").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isMortgageChaOwn").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isMortgageRent").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isMortgageRisk").setRequired(true);
 	 	 			nui.get("tbAftTrackPerson.customerCondition").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isMortgageGood").validate();
	 	 			nui.get("tbAftTrackPerson.isMortgageChange").validate();
	 	 			nui.get("tbAftTrackPerson.isMortgageControl").validate();
	 	 			nui.get("tbAftTrackPerson.isMortgageChaOwn").validate();
	 	 			nui.get("tbAftTrackPerson.isMortgageRent").validate();
	 	 			nui.get("tbAftTrackPerson.isMortgageRisk").validate();
 	 	 			nui.get("tbAftTrackPerson.customerCondition").validate();
	 	 			
	 	 			
	 	 		 

	  }
	  if(null!= subConBZ){
	 	 	 nui.get("tbAftTrackPerson.guarantorBusiness").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.guarantorFinance").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.guarantorRelation").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isGuarantorDebt").setRequired(true);
	 	 			nui.get("tbAftTrackPerson.isGuarantorRisk").setRequired(true);
 	 	 			nui.get("tbAftTrackPerson.guarantorBusiness").validate();
	 	 			nui.get("tbAftTrackPerson.guarantorFinance").validate();
	 	 			nui.get("tbAftTrackPerson.guarantorRelation").validate();
	 	 			nui.get("tbAftTrackPerson.isGuarantorDebt").validate();
	 	 			nui.get("tbAftTrackPerson.isGuarantorRisk").validate();
 	 	 			

	  }
	  
	  	
	  	}
		
		
	function jsfzl(){
	 var assetAmt=parseFloat(nui.get("tbAftTrackPerson.assetAmt").getValue());
	 var debtAmt=parseFloat(nui.get("tbAftTrackPerson.debtAmt").getValue());	
	 if ( debtAmt!=null && assetAmt!=0 && assetAmt !=null) {
	 nui.get("tbAftTrackPerson.assetDebtRate").setValue(parseFloat(debtAmt/assetAmt*100).toFixed(2));
	 	return;
	 }
	 
	}
	function save(v){
	if(v==1){
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
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
        	initPage();
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
		function clickDownload(){
		var o = form.getData();
		var json = nui.encode({"map":{"checkId":normalCheckId,"partyId":o.tbAftNormalCheck.partyId,"reportName":'/aft/normalCheck_small.docx'}});
		$.ajax({
            url: "com.bos.aft.normalCheck.printNormalCheck.biz.ext",
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