<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-09
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
	<input id="tbAftNormalTrade.normalTradeId" class="nui-hidden nui-form-input" name ="tbAftNormalTrade.normalTradeId"/>
	
	<fieldset>
		<legend>
	    	<span>借款人基本信息</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="4" id="table1">
	    	<label class="nui-form-label">客户名称：</label>
			<input  id="items[0].PARTY_NAME" name="items[0].PARTY_NAME" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">证件类型：</label>
			<input id="items[0].CERT_TYPE" name="items[0].CERT_TYPE" 
						class="nui-text nui-form-input" dictTypeId="CDKH0002"  />
				   
			<label class="nui-form-label">证件号码：</label>
			<input  id="items[0].CERT_NUM" name="items[0].CERT_NUM" class="nui-text nui-form-input" /> 
			
			<label class="nui-form-label">批复金额（元）：</label>
			<input id="items[0].PFJE" name="items[0].PFJE" dataType="" class="nui-text nui-form-input"/>
		
			<label class="nui-form-label">贷款余额（元）：</label>
			<input id="items[0].JJYE" name="items[0].JJYE" dataType="" class="nui-text nui-form-input"/>
	    </div> 
	</fieldset> 
	
	<div class="nui-dynpanel" columns="1" id="table0">
		<%@include file="/aft/normalCheck/summary_list4.jsp"%>
	</div> 
	
	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    	<label class="nui-form-label">检查方式：</label>
			<input id="tbAftNormalCheck.checkWay" name="tbAftNormalCheck.checkWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  />
						
			<label class="nui-form-label">检查日期：</label>
			<!--<input  id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" class="nui-text nui-form-input"  />-->
			 <input id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/> 
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>一、贷款后资金使用、银行账务及贷款变动情况</span>
	    </legend>
	    
	    <fieldset>
	    	<legend>
	    		<span>（一）贷款资金使用用途</span>
	    	</legend>
	    	<%@include file="/aft/normalCheck/money_use_list2.jsp"%>
	    </fieldset>
	    
	    <fieldset>
			<legend>
		    	<span >（二）贷款发放后至检查日，银行账户流水情况</span>
		    </legend>
		    <div class="nui-dynpanel" columns="1" id="table4">
		    	<!-- <label class="nui-form-label">贷款发放后至检查日，银行账户流水情况：</label> -->
				<input id="tbAftNormalTrade.flowCondition" name="tbAftNormalTrade.flowCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			</div>
		</fieldset>
		
		
		<%@include file="/aft/normalCheck/otherbank_credit_list.jsp"%>
		<!-- <fieldset>
			<legend>
		    	<span>（三）贷款发放后至检查日，公司及实际控制人在各家银行贷款情况</span>
		    </legend>
		    
		    <div class="nui-dynpanel" columns="4" id="table5">
		    
		    	<label class="nui-form-label">贷款银行：</label>
				<input  id="tbAftNormalTrade.loanBank" name="tbAftNormalTrade.loanBank" required="true" class="nui-textbox nui-form-input"  />
				
				<label class="nui-form-label">贷款方式（贷款、承兑等）：</label>
				<input id="tbAftNormalTrade.loanWay" name="tbAftNormalTrade.loanWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="product"  />
				<input  id="tbAftNormalTrade.loanWay" name="tbAftNormalTrade.loanWay" required="true" class="nui-textbox nui-form-input"  />
				
				<label class="nui-form-label">贷款起日：</label>
				<input id="tbAftNormalTrade.loanSt" name="tbAftNormalTrade.loanSt" allowInput="false"  required="true" 
						class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
				
				<label class="nui-form-label">贷款止日：</label>
				<input id="tbAftNormalTrade.loanEnd" name="tbAftNormalTrade.loanEnd" allowInput="false"  required="true" 
						class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
				
		    	<label class="nui-form-label">贷款金额：</label>
				<input  id="tbAftNormalTrade.loanAmt" name="tbAftNormalTrade.loanAmt" required="true" class="nui-textbox nui-form-input" 
						vtype="float;maxLength:20" dataType="currency" /> 
						
				<label class="nui-form-label">贷款用途：</label>
				<input  id="tbAftNormalTrade.loanUse" name="tbAftNormalTrade.loanUse" required="true" class="nui-textarea nui-form-input"  />
				
				<label class="nui-form-label">贷款担保方式：</label>
				<input id="tbAftNormalTrade.loanGuaranty" name="tbAftNormalTrade.loanGuaranty" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="CDZC0005"  />
				
				<label class="nui-form-label">贷款利率：</label>
				<input  id="tbAftNormalTrade.loanRate" name="tbAftNormalTrade.loanRate" required="true" class="nui-textbox nui-form-input" /> 
				
				<label class="nui-form-label">以上贷款是否存在违约记录：</label>
				<input id="tbAftNormalTrade.isBreak" name="tbAftNormalTrade.isBreak" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
						
				<label class="nui-form-label">到期贷款归还情况：</label>
				<input  id="tbAftNormalTrade.repayCondition" name="tbAftNormalTrade.repayCondition" required="true" class="nui-textarea nui-form-input"  />
				
		    </div> 
		</fieldset> -->
		
		<fieldset>
			<legend>
		    	<span >（四）交易合规检查</span>
		    </legend> 
		    <div class="nui-dynpanel" columns="1" id="table6" >
		    	<!-- <label class="nui-form-label">交易合规检查：</label> -->
				<input  id="tbAftNormalTrade.tradeCheck" name="tbAftNormalTrade.tradeCheck" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			</div>
		</fieldset>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>二、贷款后非财务因素分析</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table7">
	    	<!-- <label class="nui-form-label">贷款后非财务因素分析：</label> -->
			<input  id="tbAftNormalTrade.noFinanceAnalysis" name="tbAftNormalTrade.noFinanceAnalysis" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>三、贷款后财务因素分析</span>
	    </legend> 
	    <fieldset>
	    	<legend>
	    		<span>（一）财务数据</span>
	    	</legend>
	    	<div class="nui-dynpanel" columns="1" id="table8">
				<input  id="tbAftNormalTrade.finData" name="tbAftNormalTrade.finData" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			</div> 
	    </fieldset>
	    <fieldset>
	    	<legend>
	    		<span>（二）财务指标分析</span>
	    	</legend>
	    	<div class="nui-dynpanel" columns="1" id="table8">
				<!-- <label class="nui-form-label">近期财务分析：</label> -->
				<input  id="tbAftNormalTrade.financeAnalysis" name="tbAftNormalTrade.financeAnalysis" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			</div> 
	    </fieldset>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>四、第二还款来源变动情况分析</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table9">
	    	<!-- <label class="nui-form-label">第二还款来源变动情况分析：</label> -->
			<input  id="tbAftNormalTrade.repayChange" name="tbAftNormalTrade.repayChange" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>五、因素分析及贷款管理措施分析</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table10">
	    	<!-- <label class="nui-form-label">因素分析及贷款管理措施分析：</label> -->
			<input  id="tbAftNormalTrade.riskAnalysis" name="tbAftNormalTrade.riskAnalysis" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>六、其他值得关注的情况说明</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table11">
	    	<!-- <label class="nui-form-label">其他值得关注的情况说明：</label> -->
			<input  id="tbAftNormalTrade.otherCondition" name="tbAftNormalTrade.otherCondition" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
	    </div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>七、检查结论</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table12">
	    	<!-- <label class="nui-form-label">检查结论：</label> -->
			<input  id="tbAftNormalCheck.checkResult" name="tbAftNormalCheck.checkResult" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
	    </div>
	    <div class="nui-dynpanel" columns="4" id="table13">
	    	<label class="nui-form-label">检查人：</label>
			<input  id="tbAftNormalCheck.checkPerson" name="tbAftNormalCheck.checkPerson" class="nui-text nui-form-input"  /> 
			<label class="nui-form-label">系统录入时间：</label>
			<input  id="tbAftNormalCheck.createDate" name="tbAftNormalCheck.createDate" class="nui-text nui-form-input"  /> 
		</div>	
	</fieldset> 
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
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
	$("#table0").css("display","block"); 
	
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
            
            	if(mydata.tbAftNormalCheck.isFinish != null && mydata.tbAftNormalCheck.isFinish != "") {
            		if(mydata.tbAftNormalCheck.isFinish == "1") {
	            		nui.get("btnDownload").setEnabled(true);
            		}else {
            			nui.get("btnDownload").setEnabled(false);
            		}
            	}else {
            		nui.get("btnDownload").setEnabled(false);
            	}
            
            	var o = nui.decode(mydata);
            	form.setData(o);
            	query1();
			}
        });
        
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		} 
        
	}
	
	
  	function query1(){
  		var o = form.getData();
		var json = nui.decode({"partyId":o.tbAftNormalCheck.partyId,"normalCheckId":o.tbAftNormalCheck.normalCheckId});
		partyId = o.tbAftNormalCheck.partyId;
		var grid1 = nui.get("grid1");
    	grid1.load(json);
    	var grid5 = nui.get("grid5");
		grid5.load(json);
		var grid2 = nui.get("grid2");
		grid2.load(json);
		 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("grid2add").hide();
			nui.get("grid2del").hide();
			form.setEnabled(false);
		} 
	}  
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		
		o.tbAftNormalCheck.normalCheckId=normalCheckId
		var summaryItems = nui.get("grid1").getChanges();
		var tbAftCreditCondition = nui.get("grid2").getChanges();
		o.normalCheckId = normalCheckId;
		o.summaryItems = summaryItems;
		o.tbAftCreditCondition = tbAftCreditCondition; 
		
		if(tbAftCreditCondition!=null && tbAftCreditCondition!=""){
	        for(var i=0;i<tbAftCreditCondition.length;i++){
        		if(tbAftCreditCondition[i].cusName == null || tbAftCreditCondition[i].cusName==""){
        			nui.alert("请填写借款人名称");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanBank == null || tbAftCreditCondition[i].loanBank==""){
        			nui.alert("请填写贷款银行");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanWay == null || tbAftCreditCondition[i].loanWay==""){
        			nui.alert("请填写贷款方式（贷款、承兑等）");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanSt == null || tbAftCreditCondition[i].loanSt==""){
        			nui.alert("请填写贷款起期");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanEnd == null || tbAftCreditCondition[i].loanEnd==""){
        			nui.alert("请填写贷款止期");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanUse == null || tbAftCreditCondition[i].loanUse==""){
        			nui.alert("请填写贷款用途");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanAmt == null || tbAftCreditCondition[i].loanAmt==0){
        			nui.alert("请填写贷款金额");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanGuaranty == null || tbAftCreditCondition[i].loanGuaranty==""){
        			nui.alert("请填写贷款担保方式");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].loanRate == null || tbAftCreditCondition[i].loanRate==0){
        			nui.alert("请填写贷款利率（%）");
        			return;
	        	}
	        	if(tbAftCreditCondition[i].isBreak == null || tbAftCreditCondition[i].isBreak==""){
        			nui.alert("以上贷款是否存在违约记录");
        			return;
	        	}
        		tbAftCreditCondition[i].normalCheckId=normalCheckId;
	        }
	    }
	    
		var size1 = nui.get("grid2").getData().length;
		if (size1 == 0){
        	nui.alert("请填写在各家银行贷款情况");
        	return;
        } 
		
		//alert(nui.get("grid2").getData().length);
        for(var i=0; i<nui.get("grid2").getData().length;i++){
	    	if(nui.get("grid2").getData()[i].loanRate > 100 || nui.get("grid2").getData()[i].loanRate < 0) {
	    		nui.alert("利率不合规则！");
	    		//git.unmask();
	    		return;
	    	} 
			if(nui.get("grid2").getData()[i].loanEnd!=null && nui.get("grid2").getData()[i].loanEnd!=''){
	 			if(nui.get("grid2").getData()[i].loanEnd<=nui.get("grid2").getData()[i].loanSt){
		 			nui.alert("到期日要大于起始日");
		 			//git.unmask();
		 			return;
		 		}
 			}
    	}
		
		nui.get("con_contract_info_save").setEnabled(false);
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
	
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"newPeriodsNum":++count};
        //nui.get(gr).addRow(row,0);
        nui.get(gr).addRow(row,count);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.creditId){
            		var json = nui.encode({"creditId":row.creditId});
	            	$.ajax({
			            url: "com.bos.aft.normalCheck.deleteOtherBankCredit.biz.ext",
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
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"checkId":normalCheckId,"partyId":partyId,"reportName":'/aft/normalCheck_normal_trade.docx'}});
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