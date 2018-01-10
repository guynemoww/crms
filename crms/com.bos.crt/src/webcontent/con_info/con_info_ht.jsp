<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): cc
  - Date: 2016-05-23 21:03:30
  - Description:
  
-->
<head>
<title>主合同基本信息</title>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="conInfo.contractId" class="nui-hidden nui-form-input" name ="conInfo.contractId"/>
	<input id="conInfo.oldContractId" class="nui-hidden nui-form-input" name ="conInfo.oldContractId"/>
	<!-- <input id="conInfo.rmbAmt" class="nui-hidden nui-form-input" name ="conInfo.rmbAmt"/> -->
	<input id="conInfo.conBalance" class="nui-hidden nui-form-input" name ="conInfo.conBalance"/>
	<input id="conInfo.oldamt" class="nui-hidden nui-form-input" name ="conInfo.oldamt"/>
	<input id="tbConFlagInfo.flagId" class="nui-hidden nui-form-input" name ="tbConFlagInfo.flagId"/>
	<input id="tbBizAmountDetailApprove.rmbAmt" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.rmbAmt"/>
	<input id="tbBizAmountDetailApprove.exchangeRate" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.exchangeRate"/>
	<input id="tbBizAmountDetailApprove.detailBalance" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.detailBalance"/>
	<!-- <input id="tbBizAmountDetailApprove.endDate" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.endDate"/>
	 --><input id="tbBizApprove.validDate" class="nui-hidden nui-form-input" name ="tbBizApprove.validDate"/>
	<input id="tbBizAmountDetailApprove.creditTerm" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.creditTerm"/>
	<input id="tbBizAmountDetailApprove.cycleUnit" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.cycleUnit"/>
	<fieldset>
		<legend>
	    	<span>基本信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    	<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/>
			<label class="nui-form-label">客户编号：</label>	
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	
			<label class="nui-form-label">业务品种：</label>
			<input id="conInfo.productType" class="nui-text nui-form-input" dictTypeId="product" name="conInfo.productType"/>
			<label class="nui-form-label">合同编号：</label>
			<input id="conInfo.contractNum" class="nui-text nui-form-input" name="conInfo.contractNum"/>
			<label class="nui-form-label">纸质合同编号：</label>
			<input id="conInfo.paperConNum" class="nui-textbox nui-form-input" name="conInfo.paperConNum" required="true"/>
			<label class="nui-form-label">原合同编号：</label>
			<input id="conInfo.oldContractNum" class="nui-text nui-form-input" name="conInfo.oldContractNum"/>
			<label class="nui-form-label">币种：</label>
			<input id="conInfo.currencyCd" name="conInfo.currencyCd" data="data" valueField="dictID" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" required="true" onvaluechanged="bzChange"/>
			<label class="nui-form-label">金额：</label>
			<input id="conInfo.contractAmt" name="conInfo.contractAmt"  class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:100,100000000000"  dataType="currency" onblur="validAmt"  required="true" />
			<label class="nui-form-label">起始日期：</label>
			<input id="conInfo.beginDate" name="conInfo.beginDate" class="nui-datepicker nui-form-input"  required="true"  allowInput="false" onvaluechanged="validateBeginDate" />
			
			<label id="sqqx">合同期限：</label>
			<div style="width:80%">
				<input id="conInfo.contractTerm" name="conInfo.contractTerm" style="width:60%;float:left" required="true" class="nui-textbox nui-form-input" vtype="int;range:0,95000" onblur="getConEndate"/>
				<input id="conInfo.cycleUnit" name="conInfo.cycleUnit" style="width:40%;float:left" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6009" enabled="false"/>
			</div>
		
			<label class="nui-form-label">到期日期：</label>
			<input id="conInfo.endDate" name="conInfo.endDate" class="nui-datepicker nui-form-input"  required="true"  allowInput="false" enabled="true" onvaluechanged="getTerm"/>
			</div>
		<div class="nui-dynpanel" columns="4" id="hkxx">
			<label class="nui-form-label" id="hkfs">还款方式：</label>
			<input id="conInfo.repaymentType" name="conInfo.repaymentType" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_SXCD1162" onvaluechanged="conRpTpChg" enabled="false" />
			<label class="nui-form-label" id="schkq">首次还款期次：</label>
			<input id="conInfo.firstRepayTerm" name="conInfo.firstRepayTerm" class="nui-textbox nui-form-input"  required="true"   vtype="int;maxLength:4;range:2,10000"/>
			<label class="nui-form-label" id="zdhkr">指定还款日：</label>
	        <input id="conInfo.specPaymentDate" name="conInfo.specPaymentDate" class="nui-textbox nui-form-input" required="true" vtype="int;range:1,31"/>	
			<label class="nui-form-label" id="jgts">间隔天数：</label>
	        <input id="conInfo.internalDays" name="conInfo.internalDays" class="nui-text nui-form-input" />	
	    </div>
	    <div class="nui-dynpanel" columns="4">    
			<label class="nui-form-label">合同循环标识：</label>
			<input id="conInfo.cycleIndCon" name ="conInfo.cycleIndCon" dValue="0"required="true"enabled="false"class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
			<label class="nui-form-label">签约日期：</label>
			<input id="conInfo.contractDate" name="conInfo.contractDate" class="nui-datepicker nui-form-input"   required="true"  allowInput="false" onvaluechanged="qyrq"/>
			<label class="nui-form-label">签约地点：</label>
			<input id="conInfo.contractAddress" class="nui-textarea nui-form-input" name="conInfo.contractAddress" required="true" vtype="maxLength:190"/>
			<label class="nui-form-label">贷款用途：</label>
			<input name="conInfo.loanUse" id="conInfo.loanUse"  class="nui-textbox nui-form-input" required="true" vtype="maxLength:190"/>
		</div>
		
		<div class="nui-dynpanel" columns="4" id="exchangeRate">	
			<label class="nui-form-label">汇率<a href="#" onclick="pjcx()">(获取汇率)</a>：</label>
			<input id="conInfo.exchangeRate" name="conInfo.exchangeRate"  class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0,100000000000"  enabled="false" required="true" />
			<label class="nui-form-label">折算人民币金额：</label>
			<input id="conInfo.rmbAmt" name="conInfo.rmbAmt"  class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:100,100000000000"  dataType="currency" onblur="validAmt" enabled="false" required="true" />
		</div>
		
		<div class="nui-dynpanel" columns="4" id="dbfs">		
			<label class="nui-form-label">担保方式：</label>
	        <input id="conInfo.guarantyType" name="conInfo.guarantyType" data="data" valueField="dictID" 
				   class="nui-newcheckbox nui-form-input" dictTypeId="CDZC0005"  onvaluechanged="guarantyTypechg" required="true"/>	
			<label class="nui-form-label">主担保方式：</label>
	        <input id="conInfo.mainGuarantyType" name="conInfo.mainGuarantyType" data="data" valueField="dictID" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="CDZC0005"  required="true" />
		</div>
		<div class="nui-dynpanel" columns="4" id="nongdandai">		
			<label class="nui-form-label">是否融单：</label>
	        <input id="conInfo.agriculLoans" name="conInfo.agriculLoans" data="data" valueField="dictID" 
				   class="mini-dictradiogroup" dictTypeId="XD_0002" required="true"/>	
		</div>
	</fieldset>

	<fieldset id="bzxx">
		<legend>
	    	<span>标志信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    	<label class="nui-form-label">行业投向：</label>
			<input id="tbConFlagInfo.loanTurn"  name="tbConFlagInfo.loanTurn"  dictTypeId="CDXY0300"  required="true" 
					class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectTrade4" emptyText="--请选择--" />
			
			<label class="nui-form-label">涉及环境、安全等重大情况：</label>
	        <input id="tbConFlagInfo.riskInfo" name="tbConFlagInfo.riskInfo" data="data" valueField="dictID"  required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0211" onvaluechanged="riskinfo"/>	
		</div>
		<div class="nui-dynpanel" columns="4" id="cqcs">	
			<label class="nui-form-label">采取措施：</label>
	        <input id="tbConFlagInfo.act" name="tbConFlagInfo.act" data="data" valueField="dictID"  required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0212" />	
		</div>
		<div class="nui-dynpanel" columns="4">	
			<label class="nui-form-label">节能环保项目及服务类型：</label>
	        <input id="tbConFlagInfo.serviceType" name="tbConFlagInfo.serviceType" data="data" valueField="dictID"  required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0215"  onvaluechanged="servicetype"/>	
		</div>
		<div class="nui-dynpanel" columns="4" id="jnjpl">	
			<label class="nui-form-label">节能减排量：</label>
	        <input id="tbConFlagInfo.reduceAmount" name="tbConFlagInfo.reduceAmount" data="data" valueField="dictID" required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0214" />	
		</div>
		
		<div class="nui-dynpanel" columns="4">	
			<label class="nui-form-label">产业结构调整类型：</label>
	        <input id="tbConFlagInfo.ajustType" name="tbConFlagInfo.ajustType" data="data" valueField="dictID" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD8030" required="true"/>	
			
			<label class="nui-form-label">工业转型升级标识：</label>
	        <input id="tbConFlagInfo.upgradeType" name="tbConFlagInfo.upgradeType" data="data" valueField="dictID"   required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" />	
			
			<label class="nui-form-label">战略新兴产业类型：</label>
	        <input id="tbConFlagInfo.newProductType" name="tbConFlagInfo.newProductType" data="data" valueField="dictID"  
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1204" required="true"/>	
			
	    </div>
	    
		<div class="nui-dynpanel" columns="4" id="kfdk">
			<label class="nui-form-label">人行保障住房分类：</label>
			<input id="tbConFlagInfo.rhbzffl" class="nui-dictcombobox nui-form-input" name="tbConFlagInfo.rhbzffl"  required="true" dictTypeId="XD_SXYW0232"/>
			
			<label class="nui-form-label">银监保障住房分类：</label>
			<input id="tbConFlagInfo.yjbzffl" class="nui-dictcombobox nui-form-input" name="tbConFlagInfo.yjbzffl" required="true" dictTypeId="XD_SXYW0233"/>
			
	    </div>
	</fieldset>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div>

</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var contractId ="<%=request.getParameter("contractId") %>";//业务合同ID
	var xgbz="<%=request.getParameter("xgbz") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var productType = "<%=request.getParameter("productType") %>";//流程中查看标识
	var oldContractId ='';
	$("#kfdk").css("display","none");
	if(productType=='01002003'){
		$("#kfdk").css("display","block");
	}
	if(productType=='01008001'|| productType=='01008002' ||productType=='01008010' ||productType=='01009001'||productType=='01009002' ||productType=='01009010' ||productType=='01007008'
		||productType=='01010001'||productType=='01007007'||productType=='01011001'||productType=='01012001'){
		$("#hkfs").css("display","none");
		nui.get("conInfo.repaymentType").hide();
		$("#schkq").css("display","none");
		nui.get("conInfo.firstRepayTerm").hide();
	}
	//01007013信用证开证  01007010提货担保 01007014国际保函 为表外业务，故在合同签约界面删除“还款方式”、“首次还款期次”、“指定还款日”等功能界面
	if(productType=='01007013'||productType=='01007010'||productType=='01007014'){
		$("#hkxx").css("display","none");
	}
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"contractId":contractId,"contractType":"02"});
		$.ajax({
            url: "com.bos.conInfo.conInfoSxxy.getConInfoByContarctId.biz.ext",
            type: 'POST',
            data: json,
        	async: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	//调整时初始化原合同金额
            	nui.get("conInfo.oldamt").setValue(o.conInfo.contractAmt);
            	if(o.conInfo.loanUse==null || ''== o.conInfo.loanUse){
            		nui.get("conInfo.loanUse").setValue(o.tbBizAmountDetailApprove.loanUse);
            	}
            	//合同调整时，金额、币种不能变
            	oldContractId = o.conInfo.oldContractId;
            	if(productType=='01001050' || productType=='01001040' ||productType=='01001001'){
            		var agriculLoans = nui.get("conInfo.agriculLoans").getValue();
            		if(!agriculLoans){
            			nui.get("conInfo.agriculLoans").setValue("0");
            		}
            	}else{
            		//nui.get("conInfo.agriculLoans").hide();
            		$("#nongdandai").hide();
            	}
            	if(oldContractId != null && oldContractId!=''){
	            	if(productType=='01007001'||productType=='01007002'||productType=='01007003'
						||productType=='01007004'||productType=='01007005'||productType=='01007006'
						||productType=='010070009'||productType=='010070010'||productType=='01007011'
						||productType=='01007012'||productType=='01007013'||productType=='01007014'){
						
						//6个表内业务---三种还款方式 
						if(productType=='01007001'||productType=='01007003'||productType=='01007004'||
			 			   productType=='01007006'||productType=='01007005'||productType=='01007011'){
			 				nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','1100,1200,1400'));
			 			//5个表外业务---两种还款方式 (3个表外+2个表内走表外)	
			 			}else if(productType=='01007009'||productType=='01007012'||productType=='01007014'||
	         					productType=='01007013'||productType=='01007010'){
			 				nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','1100,1400'));//去掉了1700等本等息
			 			}
						//nui.get("conInfo.repaymentType").setData(getDictData('XD_SXCD1162','str','1100,1400'));//去掉了1700等本等息
		            	//nui.get("conInfo.repaymentType").setEnabled(true);//13722 主合同调整中还款方式应为反显
		            	nui.get("conInfo.firstRepayTerm").setEnabled(true);
					}else if(productType=='01006001'||productType=='01006002'
						||productType=='01006010' //村镇银行贴现产品
					){
						nui.get("conInfo.repaymentType").setValue("21");
						nui.get("conInfo.repaymentType").setEnabled(false);
						nui.get("conInfo.repaymentType").validate();
					}else{
						if(o.conInfo.cycleIndCon!=null && o.conInfo.cycleIndCon!='' && o.conInfo.cycleIndCon=='1'){
							nui.get("conInfo.repaymentType").setData(getDictData('XD_SXCD1162','str','1100'));
						}else{
							nui.get("conInfo.repaymentType").setData(getDictData('XD_SXCD1162','str','0100,0200,1100,1400,1410'));
            			}
						//nui.get("conInfo.repaymentType").setEnabled(true);
            			nui.get("conInfo.firstRepayTerm").setEnabled(true);
					}
            		nui.get("conInfo.contractAmt").setEnabled(false);
            		nui.get("conInfo.currencyCd").setEnabled(false);
            	}
            	//贴现不展示担保方式
            	if("01006001"==productType||"01006002"==productType
            		||"01006010"==productType //村镇银行贴现产品
            	){
            		$("#dbfs").css("display","none");
					nui.get("conInfo.repaymentType").setValue("21");
					nui.get("conInfo.repaymentType").setEnabled(false);
					nui.get("conInfo.repaymentType").validate();
            	}else{
            		//如果担保方式有保证金，主担保方式换成质押，如果已有质押，则直接去掉
            		var grt = o.conInfo.guarantyType;
            		if(grt.indexOf('03')!=-1){
            			grt = grt.replace("05","");
            		}else{
            			grt = grt.replace("05","03");
            		}
	            	nui.get("conInfo.mainGuarantyType").setData(getDictData('CDZC0005','str',grt));
	            	nui.get("conInfo.guarantyType").setData(getDictData('CDZC0005','str',o.tbBizAmountApprove.guarantyType));
            	}
            	//设计环境、安全等重大情况 节能环保服务及项目类型 重点关注行业类型 默认为 未涉及 0非节能环保服务及项目类型 0非重点关注行业类型
            	if(o.tbConFlagInfo.riskInfo==null ||o.tbConFlagInfo.riskInfo==''){
            		nui.get("tbConFlagInfo.riskInfo").setValue('0');
            	}
            	if(o.tbConFlagInfo.serviceType==null ||o.tbConFlagInfo.serviceType==''){
            		nui.get("tbConFlagInfo.serviceType").setValue('0');
            	}
            	//间隔天数反显7天
            	if(o.conInfo.internalDays==null ||o.conInfo.internalDays==''){
            		nui.get("conInfo.internalDays").setValue('7');
            	}
            	
            	$("#exchangeRate").css("display","none");
            	
            	riskinfo();
            	servicetype();
            	form.validate();
            	
            	//合同 期限单位默认为月
            	nui.get("conInfo.cycleUnit").setValue('04');
            	
            	//国结贸易融资表内业务要求能在合同签约的时候操作还款方式
            	if(productType=="01007001"||productType=="01007003"||productType=="01007004"||productType=="01007009"||
           			productType=="01007012"||productType=="01007011"||productType=="01007006"||productType=="01007005"){
           			nui.get("conInfo.repaymentType").setEnabled(true);
           		}
           		//国结：等额本息、等额本金、阶段性等额本金、阶段性等额本息---不要 
           		if(productType=='01007001'||productType=='01007003'||productType=='01007004'||
	         	   productType=='01007009'||productType=='01007012'||productType=='01007011'||
	         	   productType=='01007006'||productType=='01007005'||productType=='01007014'||
	               productType=='01007013'||productType=='01007010'){//国结：等额本息、等额本金、阶段性等额本金、阶段性等额本息---不要 
		           nui.get("conInfo.repaymentType").setData(getDictData('XD_SXCD1162','str','1100,1200,1400'));//去掉了1700等本等息
		  		}
		  	}
        });
        var bz = nui.get("conInfo.currencyCd").getValue();
		if (bz == '' || bz == null || bz == 'null' || bz == 'undefined') {
		}else if (bz != 'CNY'){
			$("#exchangeRate").css("display","block");
          
		}else{
			$("#exchangeRate").css("display","none");
		}
        
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}
		if("1"==xgbz){
			$("#bzxx").css("display","none");
			nui.get("conInfo.beginDate").setEnabled(false);
			nui.get("conInfo.endDate").setEnabled(false);
			nui.get("conInfo.contractDate").setEnabled(false);
			nui.get("conInfo.contractAddress").setEnabled(false);
			nui.get("conInfo.specPaymentDate").setEnabled(false);
			nui.get("conInfo.loanUse").setEnabled(false);
			
			nui.get("tbConFlagInfo.loanTurn").setEnabled(false);
		 	nui.get("tbConFlagInfo.riskInfo").setEnabled(false);
			nui.get("tbConFlagInfo.act").setEnabled(false);
			nui.get("tbConFlagInfo.serviceType").setEnabled(false);
			nui.get("tbConFlagInfo.reduceAmount").setEnabled(false);
		 }
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        //合同币种不是人民币的时候新增的汇率信息和折算人民币金额必须要
        var bz = nui.get("conInfo.currencyCd").getValue();
        var conInfoExchangeRate = nui.get("conInfo.exchangeRate").getValue();
        var conInfoRmbAmt = nui.get("conInfo.rmbAmt").getValue();
        
		if (bz != 'CNY'){
			$("#exchangeRate").css("display","block");
          	if(conInfoExchangeRate==null||conInfoExchangeRate==""||conInfoRmbAmt==null||conInfoRmbAmt==""){
          		nui.alert("汇率信息和折算人民币信息错误！");
        		return;
          	}
            //合同币种不是人民币的时候 需要校验合同金额小于批复可用金额 而且需要转换成人民币金额来比较(系统当前所有批复都是人民币 相当于额度)
          	/* var detailBalance = nui.get("tbBizAmountDetailApprove.detailBalance").getValue();//批复可用金额
          	if(parseFloat(conInfoRmbAmt)>parseFloat(detailBalance)){
          		nui.alert("合同金额折算人民币以后大于批复明细可用金额！");
        		return;
          	} */
		}
		
        nui.get("con_contract_info_save").setEnabled(false);
        
		var o = form.getData();
		o.tbConFlagInfo.contractId=contractId;
		
		//给合同余额赋值
		var conBalance =  nui.get("conInfo.conBalance").getValue();
 		var oldAmt =  nui.get("conInfo.oldamt").getValue();//合同调整时页面初始化此隐藏域
 		if(conBalance==''||conBalance==null||conBalance=='null'){
 			conBalance = 0;
 		}
 		if(conBalance!=0){
 			var ocupy = parseFloat(oldAmt)-parseFloat(conBalance);
 			o.conInfo.conBalance = parseFloat(o.conInfo.contractAmt)- parseFloat(ocupy)
 		}else{
 			o.conInfo.conBalance = parseFloat(o.conInfo.contractAmt)
 		}
 		//贴现没有担保方式，但是为了算业务别默认质押
		if(productType=='01006001'||productType=='01006002'
			||productType=='01006010' //村镇银行贴现产品
		){
			o.conInfo.mainGuarantyType = '03';
		}
		//期限校验
	    var condate = o.conInfo.endDate;
	    var conbedate = o.conInfo.beginDate;
		if(condate.substr(0,10)<=conbedate.substr(0,10)){
			nui.alert("合同止期不能小于等于合同起期"); //失败时后台直接返回出错信息
    		nui.get("con_contract_info_save").setEnabled(true);
    		return;
		}
		
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.conInfo.conInfoSxxy.saveConContractInfo.biz.ext",
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
        	alert("保存成功！");
        	nui.get("con_contract_info_save").setEnabled(true);
        	initPage();
        }});
	}
	
	function selectTrade4(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDXY0300",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname); 
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	function riskinfo(){
 		var riskinfo = nui.get("tbConFlagInfo.riskInfo").getValue();
 		if(riskinfo=='0'||riskinfo==''){
			$("#cqcs").css("display","none");
 		}else{
			$("#cqcs").css("display","block");
 		}
 		form.validate();
 	}
	function servicetype(){
 		var riskinfo = nui.get("tbConFlagInfo.serviceType").getValue();
 		if(riskinfo=='0'||riskinfo==''){
			$("#jnjpl").css("display","none");
 		}else{
			$("#jnjpl").css("display","block");
 		}
 		form.validate();
 	}
 	
 	function validAmt(){
 		var appAmt = nui.get("tbBizAmountDetailApprove.rmbAmt").getValue();//批复折算人民币金额
 		var conAmt = nui.get("conInfo.contractAmt").getValue();//合同金额
 		var exchangeRate = nui.get("tbBizAmountDetailApprove.exchangeRate").getValue();//批复币种汇率
 		if(exchangeRate =='' || exchangeRate == null){
 			exchangeRate = 1;
 		}
 		var changeAmt = (parseFloat(conAmt)*parseFloat(exchangeRate)).toFixed(6);//
 		//合同金额不能大于批复可用金额
 		//如果金额不是人民币  先不用校验--需要用折算人民币金额校验 
 		var bz =  nui.get("conInfo.currencyCd").getValue();
 		if(bz!="CNY"){
 			var rate = nui.get("conInfo.exchangeRate").getValue(); //汇率
 			if(rate == '' || rate == null || rate == 'null' || rate == 'undefined'){
 				nui.alert("请先获取当前币种["+bz+"]的汇率信息");
 				nui.get("conInfo.contractAmt").setValue("");
 				return;
 			}
 		}
 		/* var detailBalance = nui.get("tbBizAmountDetailApprove.detailBalance").getValue();//批复可用金额
 		if(bz=="CNY"){//人民币币种的合同  直接校验合同金额和批复明细金额
 			if(parseFloat(detailBalance.toFixed(6))<parseFloat(conAmt)){
 				nui.alert("合同金额不能大于批复明细可用金额[CNY:"+detailBalance+"]");
 				nui.get("conInfo.contractAmt").setValue("");
 				return;
 			}
 		}else{//如果不是人民币币种  要提示先获取汇率 
 			var rate = nui.get("conInfo.exchangeRate").getValue(); //汇率
 			if(rate == '' || rate == null || rate == 'null' || rate == 'undefined'){
 				nui.alert("请先获取当前币种["+bz+"]的汇率信息");
 				nui.get("conInfo.contractAmt").setValue("");
 				return;
 			}
 		} */
 		//调整时校验调整后金额不能小于已占用金额
 		var conBalance =  nui.get("conInfo.conBalance").getValue();
 		var oldAmt =  nui.get("conInfo.oldamt").getValue();//合同调整时页面初始化此隐藏域
 		if(conBalance==''||conBalance==null||conBalance=='null'){
 			conBalance = 0;
 		}
 		if(conBalance!=0){//调整时、合同金额不能小于已占用金额--合同调整不能修改金额
 			var ocupy = parseFloat(oldAmt)-parseFloat(conBalance);
 			if(conAmt-ocupy<0){
 				nui.alert("合同金额不能小于已占用金额!");
 				nui.get("conInfo.contractAmt").setValue("");
 				return;
 			}
 		}
 		if(bz=="CNY"){
 			nui.get("conInfo.rmbAmt").setValue(changeAmt);
 		}
 		count();
 	}
 	//折算人民币金额的计算 
 	function count(){
 		
 		var rate = nui.get("conInfo.exchangeRate").getValue(); //汇率
 		var bz = nui.get("conInfo.currencyCd").getValue(); //币种 
 		var conAmt = nui.get("conInfo.contractAmt").getValue();//合同金额
 		
 		if (bz == '' || bz == null || bz == 'null' || bz == 'undefined') {
 		
		}else if(rate == '' || rate == null || rate == 'null' || rate == 'undefined'){
			
		}else if(conAmt == '' || conAmt == null || conAmt == 'null' || conAmt == 'undefined'){
				
		}else{//币种 汇率 合同金额 都要有值
			nui.get("conInfo.rmbAmt").setValue(parseFloat(rate)*parseFloat(conAmt));
			/* var detailBalance = nui.get("tbBizAmountDetailApprove.detailBalance").getValue();//批复可用金额
			if(parseFloat(rate)*parseFloat(conAmt)>parseFloat(detailBalance)){//合同折算人民币金额>批复可用金额 
				nui.alert("合同折算人民币金额不能大于批复明细可用金额[CNY:"+detailBalance+"]");
 				nui.get("conInfo.contractAmt").setValue("");
 				nui.get("conInfo.rmbAmt").setValue("");
 				return;
			} */
		}
 	}
 	//合同起期不能小于批复生效日
 	function validateBeginDate(){
 		getConEndate();//合同起期变化时 止期也变化(放在前面 避免合同调整时被屏蔽)
	 	if(oldContractId != null && oldContractId!=''){//合同调整不校验
	 		return;
	 	}else{
	 		var beginDate = nui.get("conInfo.beginDate").getValue();
	 		var validDate = nui.get("tbBizApprove.validDate").getValue();
	 		//var endDate = nui.get("tbBizAmountDetailApprove.endDate").getValue();
	 		if(beginDate!=null && beginDate!=''){
	 			if(beginDate.substr(0,10)<validDate.substr(0,10)){
		 			alert("合同起期不能小于批复生效日期!");
		 			nui.get("conInfo.beginDate").setValue('');
		 			nui.get("conInfo.beginDate").validate();
		 			return;
		 		}
		 		/* if(beginDate.substr(0,10)>endDate.substr(0,10)){
		 			nui.alert("合同起期不能大于批复申报止期!");
		 			nui.get("conInfo.beginDate").setValue('');
		 			nui.get("conInfo.beginDate").validate();
		 			return;
		 		} */
		 		//onselectType();
	 		}
	 	}
 	}
 	
 	//通过起始日期和期限 计算出合同到期日期
 	function getConEndate(){
 		var beginDate = nui.get("conInfo.beginDate").getValue();//合同起始日期
 		var conTerm = nui.get("conInfo.contractTerm").getValue();//合同期限
 		var conUnit = nui.get("conInfo.cycleUnit").getValue();//合同期限单位
 		var creditTerm = nui.get("tbBizAmountDetailApprove.creditTerm").getValue();//批复里的期限
 		var cycleUnit = nui.get("tbBizAmountDetailApprove.cycleUnit").getValue();//批复里的期限单位
 		var qxdw;//期限单位(后台)
 		var conqxdw = "月";//期限单位(页面显示)
 		if(conUnit != null){ //合同里存的期限单位
 			if(conUnit == '01'){//如果合同期限单位为01则是年
 				qxdw = "Y";
 			}if(conUnit == '03'){//如果合同期限单位为03则是季
 				qxdw = "J";
 			}if(conUnit == '04'){//如果合同期限单位为04则是月
 				qxdw = "M";
 			}if(conUnit == '05'){//如果合同期限单位为05 则是日
 				qxdw = "D";
 			}
 		}
 		if(cycleUnit != null){//批复里存的期限单位
 			if(cycleUnit == '01'){//如果合同期限单位为01则是年
 				conqxdw = "年";
 			}if(cycleUnit == '03'){//如果合同期限单位为03则是季
 				conqxdw = "季";
 			}if(cycleUnit == '04'){//如果合同期限单位为04则是月
 				conqxdw = "月";
 			}if(cycleUnit == '05'){//如果合同期限单位为05 则是日
 				conqxdw = "日";
 			}
 		}
 		if(creditTerm != null && cycleUnit != null && creditTerm < conTerm ){
 			nui.get("conInfo.endDate").setValue('');
 			alert("合同期限不能大于批复期限"+creditTerm+"个月");
 			return; 
 		}
 		if(beginDate != null && conTerm != null){
 			var date = beginDate.substring(0,10);//截取起始日期
	 		var json = nui.encode({"qxdw":qxdw,"qx":conTerm,"rq":date});
	 		$.ajax({
	 			url : "com.bos.pub.dateCountUtil.monthAddDate.biz.ext",
	 			type : 'POST',
	 			data : json,
	 			cache : false,
	 			contentType : "text/json",
	 			success : function(data){//返回合同到期日期
					nui.get("conInfo.endDate").setValue(data.dqrq);
	 			}
	 		});
 		}
 	}
 	
	//通过起始日期、到期日期、期限单位计算期限
	function getTerm(){
		var beginDate = nui.get("conInfo.beginDate").getValue().substring(0,10);//合同起期
		var endDate = nui.get("conInfo.endDate").getValue().substring(0,10);//合同止期
		var termUnit = nui.get("conInfo.cycleUnit").getValue();//合同期限单位
		var creditTerm = nui.get("tbBizAmountDetailApprove.creditTerm").getValue();//批复里的期限
		if(beginDate != null && termUnit != null){
			if(endDate <= beginDate){
				nui.get("conInfo.endDate").setValue("");//将合同止期置为空
				alert("合同止期不能小于等于合同起期");
				return;
			}
			//期限单位目前默认为"月(M)"
			var json = nui.encode({"beginDate":beginDate,"endDate":endDate,"termUnit":"M"});
			$.ajax({
				url:"com.bos.pub.dateCountUtil.getTermByEndDate.biz.ext",
				type:'POST',
				data:json,
				cache:false,
				contentType:"text/json",
				success:function(data){
					if(data != null){
						if(data.term > creditTerm){//如果计算出的合同期限大于批复期限
							nui.get("conInfo.endDate").setValue("");
							return alert("不能大于批复期限"+creditTerm+"个月,请重新选择到期日期");
						}
					}
					nui.get("conInfo.contractTerm").setValue(data.term);
				}
			});
		}
	} 	
 	
 	
 	//担保方式发生改变
 	function guarantyTypechg(){
 		var guarantyType = nui.get("conInfo.guarantyType").getValue();
 		
		if(guarantyType.indexOf('03')!=-1){
			guarantyType = guarantyType.replace("05","");
		}else{
			guarantyType = guarantyType.replace("05","03");
		}
 		nui.get("conInfo.mainGuarantyType").setData(getDictData('CDZC0005','str',guarantyType));
 		nui.get("conInfo.mainGuarantyType").setValue('');
 		nui.get("conInfo.mainGuarantyType").validate();
 	}
 	//签约日期要大于或等于批复起期
 	function qyrq(){
 		var conDate = nui.get("conInfo.contractDate").getValue();
 		var validDate = nui.get("tbBizApprove.validDate").getValue();
 		if(conDate!=null && conDate!=''){
 			if(conDate<=validDate){
	 			alert("签约日期不能小于批复生效日期!");
	 			nui.get("conInfo.contractDate").setValue('');
	 			nui.get("conInfo.contractDate").validate();
	 		}
 		}
 	}
 	//只有阶段性还款方式才显示首次还本期次
 	function conRpTpChg(){
		var hkfs = nui.get("conInfo.repaymentType").getValue();
		if(hkfs=='0300'||hkfs=='0400'){
			$("#schkq").css("display","block");
			nui.get("conInfo.firstRepayTerm").show();
		}else{
			$("#schkq").css("display","none");
			nui.get("conInfo.firstRepayTerm").hide();
			nui.get("conInfo.firstRepayTerm").setValue('');
		}
	}
	
	//币种改变
	function bzChange(){
		var bz = nui.get("conInfo.currencyCd").getValue();
		if (bz == '' || bz == null || bz == 'null' || bz == 'undefined') {
		}else if (bz != 'CNY'){
			$("#exchangeRate").css("display","block");
            nui.get("conInfo.exchangeRate").setValue("");
            nui.get("conInfo.rmbAmt").setValue("");
		}else{
			var conAmt = nui.get("conInfo.contractAmt").getValue();
			$("#exchangeRate").css("display","none");
			nui.get("conInfo.exchangeRate").setValue("1");
			nui.get("conInfo.rmbAmt").setValue(conAmt);
		}
		count();
	}
	//牌价查询---获取当前币种的汇率信息 
	function pjcx(){
		var bz = nui.get("conInfo.currencyCd").getValue();
		if (bz == 'CNY'){
            nui.get("conInfo.exchangeRate").setValue("1");//人民币  汇率默认1
            count();
		}else{
			var json = nui.encode({"bz":bz});
			$.ajax({
        			url: "com.bos.conInfo.conInfoSxxy.getChangeRate.biz.ext",
        			type: 'POST',
        			data: json,
        			cache: false,
        			contentType:'text/json',
        			success: function (text) {
        				if(text){
        					if(text.validityInd=="1"){
        						if(""==text.msg||null==text.msg){
        							nui.get("conInfo.exchangeRate").setValue(parseFloat(parseFloat(text.disRateOfRmb)/parseFloat(100)).toFixed(8));
        							alert("币种["+bz+"]的汇率信息获取成功");
        							count();
        						}else{
        							alert(text.msg+",请国结系统推送当天的利率信息到信贷系统！");
        						}
        						
        					}else{
        						alert("未获取到汇率信息");
        					}
        				}else{
        					alert("未获取到汇率信息");
        				}
        			}
        		});
			}
	}
	/* function checkEndDate(){
		//期限校验
		var endddate = nui.get("tbBizAmountDetailApprove.endDate").getValue();
	    var condate = nui.get("conInfo.endDate").getValue();
		if(condate.substr(0,10)>endddate.substr(0,10)){
			nui.alert("合同到期日大于批复止期！"); //失败时后台直接返回出错信息
 			nui.get("conInfo.endDate").setValue('');
 			nui.get("conInfo.endDate").validate();
    		return;
		}
	} */
		function getDictData(dictId,type,str){
		var dictData = nui.getDictData(dictId);//获取业务字典的数据
		var arr = nui.encode(dictData).split("},");//业务字典数据字符串化，方便处理
		var strArr = new Array();
		//将字符串存入数组
		if(str.indexOf(",") != -1){
			strArr = str.split(",");
		}else{
			strArr.push(str);
		}
		var dictStr = "";//拼接业务字典字符串
		if(type == "str"){//如果是指定字符串过滤
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var flag = arr[i].indexOf('"dictID":"'+strArr[n]+'"')!="-1";//如果包含指定的字符串
					if(flag){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "sub"){//如果是只获取指定字符串子集
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var s = strArr[n];
					//var flag = arr[i].indexOf('"dictID":"'+s)!="-1";//必须为指定字符串及其子项
					//var flag1 = arr[i].indexOf('"dictID":"'+s+'"')=="-1";//不能为父项
					var flag2 = arr[i].indexOf('"parentid":"'+s+'"')!="-1";//必须为子项（不包含子项的子项）
					if(flag2){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "top"){//如果是只获取最顶级业务字典
			for(var i = 0;i<arr.length;i++){
				var flag = arr[i].indexOf('"parentid":"null"')!="-1";//必须为顶级业务字典
				if(flag){
					dictStr = contactStr(i,dictStr,arr);
				}
			}
		}
		//如果最后一个字典项不符合条件，则增加结束标识符号“}]”
		if(dictStr.charAt(dictStr.length-1) != "]"){
			dictStr = dictStr + "}]";
		}
		var dict = nui.decode(dictStr);
		return dict;
	}
	
	//根据索引值，字符串和数组值拼接(用于过滤业务字典-getDictData)
	function contactStr(index,str,arr){
		if(index == 0){
			str = str + arr[index];
		}else if(index != (arr.length)){
			if(str == ""){
				str = "[" + arr[index];
			}else{
				str = str + "}," + arr[index];
			}
		}
		return str;
	}
	 /* function onselectType(){
		var reateType= nui.get("conInfo.cycleUnit").getValue();
		var d1= nui.get("conInfo.beginDate").getValue();
		var d2= nui.get("conInfo.endDate").getValue();
		if(d1!=null&&d1!=""&&d2!=null&&d2!=""){
			if(d1.length>'10'&&d2.length>'10'){
				var	date11 = d1.split('-');
				var	date12 = d2.split('-');
				if (date11<date12){
					d1 =d1.substring(0,10);
					d2 =d2.substring(0,10);
					var m= 0;
	   				var days = dateDiff(d1,d2);
	    			m = dateDiffMm(d2,d1);
					if(nui.get("conInfo.cycleUnit").getValue()=="01"){//年
    					nui.get("conInfo.contractTerm").setValue(Math.ceil(m/12));
					}else if(nui.get("conInfo.cycleUnit").getValue()=="02"){//半年
    					nui.get("conInfo.contractTerm").setValue(Math.ceil(m/6));
					}else if(nui.get("conInfo.cycleUnit").getValue()=="03"){//季
       					nui.get("conInfo.contractTerm").setValue(Math.ceil(m/3));
					}else if(nui.get("conInfo.cycleUnit").getValue()=="04"){//月
       					nui.get("conInfo.contractTerm").setValue(m);
					}else{// 05 日
	    				nui.get("conInfo.contractTerm").setValue(days+1);
					}
				}else{
					nui.get("conInfo.endDate").setValue('');
			 		nui.alert("合同起期不能大于合同到期!");
		   	 		return;
					}
				}else{
		    		//alert('对不起将开始与结束时间填写完整，谢谢！');
		    		//return;
				}
			}
		} */
				//两个日期月数
				function dateDiffMm(date1,date2){
				// 拆分年月日
				date1 = date1.split('-');
				// 得到月数
				date1 = parseInt(date1[0]) * 12 + parseInt(date1[1]);
				// 拆分年月日
				date2 = date2.split('-');
				// 得到月数
				date2 = parseInt(date2[0]) * 12 + parseInt(date2[1]);
				  var  m = Math.ceil(date1 - date2);
				  return m;
				}

				//判断是否为闰年
                        function isLeapYear(year){
                        if(year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0)))
                        {
                             return true;
                        }
                        return false;
                        }
                        //判断前后两个日期
                        function validatePeriod(fyear,fmonth,fday,byear,bmonth,bday){
                        if(fyear < byear){
                        return true;
                        }else if(fyear == byear){
                        if(fmonth < bmonth){
                           return true;
                        } else if (fmonth == bmonth){
                           if(fday <= bday){
                            return true;
                           }else {
                            return false;
                           }
                        } else {
                           return false;
                        }
                        }else {
                        return false;
                        }
                        }
                    //计算两个日期的差值
                        function dateDiff(d1,d2){
                            var disNum=compareDate(d1,d2);
                            return disNum;
                        }
                        function compareDate(date1,date2)
                        {
                            var regexp=/^(\d{1,4})[-|\.]{1}(\d{1,2})[-|\.]{1}(\d{1,2})$/;
                            var monthDays=[0,3,0,1,0,1,0,0,1,0,0,1];
                            regexp.test(date1);
                            var date1Year=RegExp.$1;
                            var date1Month=RegExp.$2;
                            var date1Day=RegExp.$3;

                            regexp.test(date2);
                            var date2Year=RegExp.$1;
                            var date2Month=RegExp.$2;
                            var date2Day=RegExp.$3;

                        if(validatePeriod(date1Year,date1Month,date1Day,date2Year,date2Month,date2Day)){
                        firstDate=new Date(date1Year,date1Month,date1Day);
                             secondDate=new Date(date2Year,date2Month,date2Day);

                             result=Math.floor((secondDate.getTime()-firstDate.getTime())/(1000*3600*24));
                             for(j=date1Year;j<=date2Year;j++){
                                 if(isLeapYear(j)){
                                     monthDays[1]=2;
                                 }else{
                                     monthDays[1]=3;
                                 }
                                 for(i=date1Month-1;i<date2Month;i++){
                                     result=result-monthDays[i];
                                 }
                             }
                             return result;
                        }else{
                            return;
                        }
                        }
</script>
</body>
</html>