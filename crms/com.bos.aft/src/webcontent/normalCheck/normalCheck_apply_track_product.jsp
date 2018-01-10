<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-08
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
	<input id="tbAftTrackProduct.checkProductId" class="nui-hidden nui-form-input" name ="tbAftTrackProduct.checkProductId"/>
	
	<div class="nui-dynpanel" columns="1" id="table0">
		<%@include file="/aft/normalCheck/summary_list4.jsp"%>
	</div>
	
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
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/> -->
	    </div>
	</fieldset>
	
	<div class="nui-dynpanel" columns="1" id="table00">
		<%@include file="/aft/normalCheck/require_list.jsp"%>
	</div> 
	
	<fieldset>
		<legend>
	    	<span>本期资产和经营变化情况</span>
	    </legend>
	    <!-- <div class="nui-dynpanel" columns="4" id="table2"> -->
	    <div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	    	<label class="nui-form-label">银行存款增减(-)额：</label>
			<input  id="tbAftTrackProduct.depositChange" name="tbAftTrackProduct.depositChange" required="true" class="nui-textbox nui-form-input" 
					vtype="maxLength:1000" dataType="" /> 
			
			<label class="nui-form-label">我行存款余额、月度日均额增减：</label>
			<input  id="tbAftTrackProduct.mybankAmtChange" name="tbAftTrackProduct.mybankAmtChange" required="true" class="nui-textbox nui-form-input" 
					vtype="maxLength:1000" dataType="" />
		</div>
		 
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">在我行的货款回笼情况，笔数、合计交易额：</label>
			<input  id="tbAftTrackProduct.mybankLoanRepay" name="tbAftTrackProduct.mybankLoanRepay" required="true" class="nui-textarea nui-form-input"  />
			
			<label class="nui-form-label">银行贷款或银行承兑汇票敞口额增减：</label>
			<input  id="tbAftTrackProduct.bankLoanChange" name="tbAftTrackProduct.bankLoanChange" required="true" class="nui-textbox nui-form-input" 
					vtype="maxLength:1000" dataType="" /> 
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">其中我行贷款或承兑敞口额增减：</label>
			<input  id="tbAftTrackProduct.mybankLoanChange" name="tbAftTrackProduct.mybankLoanChange" required="true" class="nui-textbox nui-form-input" 
					vtype="maxLength:1000" dataType="" /> 
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">主要商品存货是否有大的变化：</label>
			<input id="tbAftTrackProduct.isGoodsChange" name="tbAftTrackProduct.isGoodsChange" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" />
				
	    	<label class="nui-form-label">主要原材料增减情况：</label>
			<input id="tbAftTrackProduct.dataCondition" name="tbAftTrackProduct.dataCondition" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectType1"  />
		</div>
		
		<div id="div1" class="nui-dynpanel"  columns="4" style="width:99.5%;">			
			<label class="nui-form-label">主要原材料品种：</label>
			<input  id="tbAftTrackProduct.dataType" name="tbAftTrackProduct.dataType" class="nui-textbox nui-form-input"  />
			
			<label class="nui-form-label">主要原材料月末金额（元）：</label>
			<input  id="tbAftTrackProduct.dataMonthendAmt" name="tbAftTrackProduct.dataMonthendAmt" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="" /> 
		</div>
		
		<div id="div2" class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">主要原材料单价变化：</label>
			<input  id="tbAftTrackProduct.dataPriceChange" name="tbAftTrackProduct.dataPriceChange" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="" /> 
		</div>			
					
	    <!-- </div> -->
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>主要产成品增减情况</span>
	    </legend>
	    <div class="nui-dynpanel"  columns="4" style="width:99.5%;">
	    
	    	<label class="nui-form-label">主要产成品品种：</label>
			<input  id="tbAftTrackProduct.productType" name="tbAftTrackProduct.productType" required="true" class="nui-textbox nui-form-input"  />
			
			<label class="nui-form-label">主要产成品月末金额（元）：</label>
			<input  id="tbAftTrackProduct.productMonthendAmt" name="tbAftTrackProduct.productMonthendAmt" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="" /> 
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">
			<label class="nui-form-label">主要产成品单价变化：</label>
			<input  id="tbAftTrackProduct.productPriceChange" name="tbAftTrackProduct.productPriceChange" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20" dataType="" /> 
					
	    </div>
	    
	    <div class="nui-dynpanel"  columns="4" style="width:99.5%;">				
			<label class="nui-form-label">应收账款金额和账龄是否有大的变化：</label>
			<input id="tbAftTrackProduct.isAmtChange" name="tbAftTrackProduct.isAmtChange" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectType2"  />
	    </div>
	    
	    <div id="div3" class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">前10大户付款人名称：</label>
			<input  id="tbAftTrackProduct.payName" name="tbAftTrackProduct.payName" class="nui-textarea nui-form-input"  />
			
			<label class="nui-form-label">变化原因：</label>
			<input  id="tbAftTrackProduct.changeReason" name="tbAftTrackProduct.changeReason" class="nui-textarea nui-form-input"  />
		</div>
		
		<div  id="div4"  class="nui-dynpanel"  columns="4" style="width:99.5%;">			
			<label class="nui-form-label">账龄变化原因：</label>
			<input  id="tbAftTrackProduct.debtageChangeReason" name="tbAftTrackProduct.debtageChangeReason" class="nui-textarea nui-form-input"  />
		</div>	
	</fieldset>
	
	
	<fieldset>
		<legend>
	    	<span>其他项目的变化情况</span>
	    </legend>
	    <!-- <div class="nui-dynpanel" columns="4" id="table4"> -->
	    
	     <div class="nui-dynpanel"  columns="4" style="width:99.5%;">
	    	<label class="nui-form-label">应付账款：</label>
			<input  id="items2[0].A1" name="items2[0].A1" class="nui-text nui-form-input" dataType="" /> 
			
			<label class="nui-form-label">预收账款：</label>
			<input  id="items2[0].A2" name="items2[0].A2" class="nui-text nui-form-input" dataType="" /> 
		</div>
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">
	    	<label class="nui-form-label">预付账款：</label>
			<input  id="items2[0].A3" name="items2[0].A3" class="nui-text nui-form-input" dataType="" /> 
			
			<label class="nui-form-label">其他应收账款：</label>
			<input  id="items2[0].A4" name="items2[0].A4" class="nui-text nui-form-input" dataType="" /> 
		</div>
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">
	    	<label class="nui-form-label">其他应付账款：</label>
			<input  id="items2[0].A5" name="items2[0].A5" class="nui-text nui-form-input" dataType="" /> 
		</div>
	    
	    <div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	    	<label class="nui-form-label">是否添置或处置固定资产（在建工程）：</label>
			<input id="tbAftTrackProduct.isDealAsset" name="tbAftTrackProduct.isDealAsset" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectType3"  />
		</div>
	   
	   <div id="div5" class="nui-dynpanel"  columns="4" style="width:99.5%;">	 
	    	<label class="nui-form-label">固定资产名称：</label>
			<input  id="tbAftTrackProduct.assetName" name="tbAftTrackProduct.assetName" class="nui-textbox nui-form-input"  />
			
			<label class="nui-form-label">固定资产价值：</label>
			<input  id="tbAftTrackProduct.assetValue" name="tbAftTrackProduct.assetValue" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20;range:100,999999999999" dataType="currency"/> 
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">		
			<label class="nui-form-label">当月销售收入额：</label>
			<input  id="tbAftTrackProduct.monthSellamt" name="tbAftTrackProduct.monthSellamt" required="true" class="nui-textbox nui-form-input" 
					vtype="float;maxLength:20;range:100,999999999999" dataType="currency"/> 
			
			<label class="nui-form-label">当月营业利润率（%）：</label>
			<input  id="tbAftTrackProduct.monthProfitRate" name="tbAftTrackProduct.monthProfitRate" required="true" class="nui-textbox nui-form-input" /> 
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">		
			<label class="nui-form-label">主营业务是否有变化，若有，请予描述：</label>
			<input  id="tbAftTrackProduct.isBusinessChange" name="tbAftTrackProduct.isBusinessChange" required="true" class="nui-textarea nui-form-input"  />
			
			<label class="nui-form-label">是否有大的投资或对外担保行为，若有，投资项目或担保人和金额：</label>
			<input  id="tbAftTrackProduct.isGuarantor" name="tbAftTrackProduct.isGuarantor" required="true" class="nui-textarea nui-form-input"  />
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">		
			<label class="nui-form-label">是否有大的技术革新，若有，请予描述：</label>
			<input  id="tbAftTrackProduct.isNewSkill" name="tbAftTrackProduct.isNewSkill" required="true" class="nui-textarea nui-form-input"  />
			
			<label class="nui-form-label">本期是否按期还本付息：</label>
			<input id="tbAftTrackProduct.isRepayOt" name="tbAftTrackProduct.isRepayOt" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	    </div>
	    
	    <div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	    	<label class="nui-form-label">还款来源是否为自有资金：</label>
			<input id="tbAftTrackProduct.isRepayOwn" name="tbAftTrackProduct.isRepayOwn" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	    	
	    	<label class="nui-form-label">在他行是否有逾期或欠息：</label>
			<input id="tbAftTrackProduct.isOtherbankDebt" name="tbAftTrackProduct.isOtherbankDebt" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	    </div>
	    
	    <div class="nui-dynpanel"  columns="4" style="width:99.5%;">		
	    	<label class="nui-form-label">上期承兑汇票是否提供足额发票：</label>
			<input id="tbAftTrackProduct.isAcceptBill" name="tbAftTrackProduct.isAcceptBill" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	    	
	    	<label class="nui-form-label">开票人是否与合同一致：</label>
			<input id="tbAftTrackProduct.isConSame" name="tbAftTrackProduct.isConSame" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
		</div>
		
	    <!-- </div> -->
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>本期市场变化情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table5">
	    
	    	<label class="nui-form-label">主要原材料价格和供求变化：</label>
			<input  id="tbAftTrackProduct.dataNeedChange" name="tbAftTrackProduct.dataNeedChange" required="true" class="nui-textarea nui-form-input"  />
			
			<label class="nui-form-label">产品价格和供求变化：</label>
			<input  id="tbAftTrackProduct.priceNeedChange" name="tbAftTrackProduct.priceNeedChange" required="true" class="nui-textarea nui-form-input"  />
			
			<label class="nui-form-label">本期担保情况是否有变化：</label>
			<input id="tbAftTrackProduct.isGuarantChange" name="tbAftTrackProduct.isGuarantChange" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	    	
	    	<label class="nui-form-label">其他值得关注的情况（包括借款人对外投资、对外担保、股东变化及政策对房地产市场的影响变化等）：</label>
			<input  id="tbAftTrackProduct.otherCondition" name="tbAftTrackProduct.otherCondition"   class="nui-textarea nui-form-input"  />
			
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
	$("#table00").css("display","block");
	
	$("#div1").css("display","none");
	$("#div2").css("display","none");
	$("#div3").css("display","none");
	$("#div4").css("display","none");
	$("#div5").css("display","none");
	
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
		var json = nui.decode({"partyId":o.tbAftNormalCheck.partyId,"normalCheckId":normalCheckId});
		partyId = o.tbAftNormalCheck.partyId;
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
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"newPeriodsNum":++count};
        nui.get(gr).addRow(row,0);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.executeId){
            		var json = nui.encode({"executeId":row.executeId});
	            	$.ajax({
			            url: "com.bos.aft.normalCheck.deleteRequireInfo.biz.ext",
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
	
		var o = form.getData();
		
		o.tbAftNormalCheck.normalCheckId=normalCheckId
		var summaryItems = nui.get("grid1").getChanges();
		var tbAftRequireExecute = nui.get("grid2").getChanges();
		o.normalCheckId = normalCheckId;
		o.summaryItems = summaryItems;
		o.tbAftRequireExecute = tbAftRequireExecute; 
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        if(tbAftRequireExecute!=null && tbAftRequireExecute!=""){
	        for(var i=0;i<tbAftRequireExecute.length;i++){
        		if(tbAftRequireExecute[i].requirement == null || tbAftRequireExecute[i].requirement==""){
        			nui.alert("请填写本次检查需要落实的条件");
        			return;
	        	}
	        	if(tbAftRequireExecute[i].executeResult == null || tbAftRequireExecute[i].executeResult==""){
        			nui.alert("请填写落实情况");
        			return;
	        	}
        		tbAftRequireExecute[i].normalCheckId=normalCheckId;
	        }
	    }
	    
		var size1 = nui.get("grid2").getData().length;
		if (size1 == 0){
        	nui.alert("请填写贷款条件及落实情况");
        	return;
        } 
        
        /* if (tbAftRequireExecute==null || tbAftRequireExecute == ""){
        	nui.alert("请填写贷款条件及落实情况");
        	return;
        } */
        
        var dataCondition = o.tbAftTrackProduct.dataCondition;  
        if(dataCondition=="1"){//是
        	var dataType = o.tbAftTrackProduct.dataType;
        	var dataMonthendAmt = o.tbAftTrackProduct.dataMonthendAmt;
        	var dataPriceChange = o.tbAftTrackProduct.dataPriceChange;
        	if (dataType == null || dataType==""){
        		return alert("请填写主要原材料品种");
        	}
        	if (dataMonthendAmt == null || dataMonthendAmt==""){
        		return alert("请填写主要原材料月末金额（元）");
        	}
        	if (dataPriceChange == null || dataPriceChange==""){
        		return alert("请填写主要原材料单价变化");
        	}
        }
        
        var isAmtChange = o.tbAftTrackProduct.isAmtChange;        
        if(isAmtChange=="1"){//是
        	var payName = o.tbAftTrackProduct.payName;
        	var changeReason = o.tbAftTrackProduct.changeReason;
        	var debtageChangeReason = o.tbAftTrackProduct.debtageChangeReason;
        	if (payName == null || payName==""){
        		return alert("请填写前10大户付款人名称");
        	}
        	if (changeReason == null || changeReason==""){
        		return alert("请填写变化原因");
        	}
        	if (debtageChangeReason == null || debtageChangeReason==""){
        		return alert("请填写账龄变化原因");
        	}
        }
        
        var isDealAsset = o.tbAftTrackProduct.isDealAsset;        
        if(isDealAsset=="1"){//是
        	var assetName = o.tbAftTrackProduct.assetName;
        	var assetValue = o.tbAftTrackProduct.assetValue;
        	if (assetName == null || assetName==""){
        		return alert("请填写固定资产名称");
        	}
        	if (assetValue == null || assetValue==""){
        		return alert("请填写固定资产价值");
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
	
	
</script>

<script>
	
	function onselectType1(e){
		var dataCondition= nui.get("tbAftTrackProduct.dataCondition").getValue();//主要原材料增减情况
		if(dataCondition=="0"){//否
			$("#div1").css("display","none");
			$("#div2").css("display","none");

			nui.get("tbAftTrackProduct.dataType").setValue(null);
			nui.get("tbAftTrackProduct.dataMonthendAmt").setValue(null);
			nui.get("tbAftTrackProduct.dataPriceChange").setValue(null);
		}else if(dataCondition=="1"){//是
			$("#div1").css("display","block");
			$("#div2").css("display","block");
			
		}else{//非反显
			$("#div1").css("display","none");
			$("#div2").css("display","none");
		}
	}
	
	function onselectType2(e){
		var isAmtChange= nui.get("tbAftTrackProduct.isAmtChange").getValue();//应收账款金额和账龄是否有大的变化
		if(isAmtChange=="0"){//否
			$("#div3").css("display","none");
			$("#div4").css("display","none");

			nui.get("tbAftTrackProduct.payName").setValue(null);
			nui.get("tbAftTrackProduct.changeReason").setValue(null);
			nui.get("tbAftTrackProduct.debtageChangeReason").setValue(null);
		}else if(isAmtChange=="1"){//是
			$("#div3").css("display","block");
			$("#div4").css("display","block");
			
		}else{//非反显
			$("#div3").css("display","none");
			$("#div4").css("display","none");
		}
	}
	
	function onselectType3(e){
		var isDealAsset= nui.get("tbAftTrackProduct.isDealAsset").getValue();//是否添置或处置固定资产（在建工程）
		if(isDealAsset=="0"){//否
			$("#div5").css("display","none");

			nui.get("tbAftTrackProduct.assetName").setValue(null);
			nui.get("tbAftTrackProduct.assetValue").setValue(null);
		}else if(isDealAsset=="1"){//是
			$("#div5").css("display","block");
			
		}else{//非反显
			$("#div5").css("display","none");
		}
	}
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"checkId":normalCheckId,"partyId":partyId,"reportName":'/aft/normalCheck_track_product.docx'}});
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