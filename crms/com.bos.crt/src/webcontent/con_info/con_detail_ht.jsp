<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-12 21:03:59
  - Description:
-->
<head>
<title>主合同明细信息</title>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<% 
  String pAddress ="/"+(String)request.getParameter("jspName")+"?stepFlag=con";
  System.out.println("pAddress=========="+pAddress);
 %>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="tbConAttachedInfo.attached" class="nui-hidden nui-form-input" name ="tbConAttachedInfo.attached"/>
		<input id="tbConContractInfo.contractAmt" class="nui-hidden nui-form-input" name ="tbConContractInfo.contractAmt"/>
		
		<input id="tbConContractInfo.currencyCd" class="nui-hidden nui-form-input" name ="tbConContractInfo.currencyCd"/>
		<input id="tbConContractInfo.exchangeRate" class="nui-hidden nui-form-input" name ="tbConContractInfo.exchangeRate"/>
		<input id="tbConContractInfo.rmbAmt" class="nui-hidden nui-form-input" name ="tbConContractInfo.rmbAmt"/>
		
		<input id="tbBizAmountDetailApprove.detailBalance" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.detailBalance"/>
		
		<input id="tbConContractInfo.contractId" class="nui-hidden nui-form-input" name ="tbConContractInfo.contractId"/>
		<input id="tbConContractInfo.beginDate" class="nui-hidden nui-form-input" name ="tbConContractInfo.beginDate"/>
		<input id="tbConContractInfo.endDate" class="nui-hidden nui-form-input"  name ="tbConContractInfo.endDate"/>
		<input id="bizDate" class="nui-hidden nui-form-input" name ="tbConContractInfo.bizDate"/>
		<input id="rateFloatProportion" class="nui-hidden nui-form-input" name ="rateFloatProportion"/>
		<input id="yearrate" class="nui-hidden nui-form-input" name ="yearrate"/>
		<input id="conDetail.applyDetailId" name="conDetail.applyDetailId"  class="nui-hidden nui-form-input"  />
			<div  class="nui-dynpanel" columns="1" id="table1">
				<fieldset class="fieldsetnew">
					<legend>
						<span>明细信息</span>
					</legend>
					<jsp:include page="<%=pAddress %>"/>
				</fieldset>
			</div>
			
			<div class="nui-dynpanel" columns="1" id="table2">
				<fieldset  class="fieldsetnew">
					<legend>
						<span>利率信息</span>
					</legend>
					<%@include file="/biz/biz_product_detail/biz_public_rate.jsp"%>
				</fieldset>
			</div>
			<div class="nui-dynpanel" columns="1" id="dkll">
				<fieldset  class="fieldsetnew">
					<legend>
						<span>垫款利率信息</span>
					</legend>
					<%@include file="/biz/biz_product_detail/con_dkll_rate.jsp"%>
				</fieldset>
			</div>
			
			<div class="nui-dynpanel" columns="1" id="table10">
				<fieldset  class="fieldsetnew">
					<legend>
						<span>通知和文书送达</span>
					</legend>
					<jsp:include page="/biz/biz_product_detail/biz_public_notice.jsp">
						<jsp:param name="productType" value='<%=request.getParameter("productType")%>'/>
					</jsp:include>
				</fieldset>
			</div>
			<div class="nui-dynpanel" columns="1" id="table6">
				<fieldset  class="fieldsetnew">
					<legend>
						<span>仲裁方式</span>
					</legend>
						<%@include file="/biz/biz_product_detail/biz_public_zcfs.jsp"%>
				</fieldset>
			</div>

			<div class="nui-dynpanel" columns="1" id="table7">
				<fieldset  class="fieldsetnew">
					<legend>
						<span>协议签署</span>
					</legend>
						<%@include file="/biz/biz_product_detail/biz_public_xyqs.jsp"%>
				</fieldset>
			</div>
			<div class="nui-dynpanel" columns="1" id="table10">
				<fieldset>
					<legend>
						<span>财务约束指标</span>
					</legend>
					<div class="nui-dynpanel" columns="4">
						<label class="nui-form-label">财务约束指标：</label>
					 	<input id="tbConAttachedInfo.constraintIndex" class="nui-textarea nui-form-input" name="tbConAttachedInfo.constraintIndex"  colspan="3" vtype="maxLength:3500" style="width: 100%;height: 70px"/>
					 </div>
				</fieldset>
			</div>
			<div class="nui-dynpanel" columns="1" id="table8">
				<fieldset  class="fieldsetnew">
					<legend>
						<span>补充条款</span>
					</legend>
					<div class="nui-dynpanel" columns="4">
						<label class="nui-form-label" >补充条款：</label>
					 	<input id="tbConAttachedInfo.addClause" class="nui-textarea nui-form-input" name="tbConAttachedInfo.addClause" colspan="3"/>
					 </div>
				</fieldset>
			</div>
			<div id="table9">
				<%@include file="/biz/biz_product_detail/myht.jsp"%>
			</div>
			<div id="table3">
				<%@include file="/crt/con_detail/payout_plan.jsp"%>
			</div> 
			
			<div id="table4">
				<%@include file="/crt/con_detail/loan_repay_plan.jsp"%>
			</div>
			<div id="table5">
				<%@include file="/crt/con_detail/fee.jsp"%>
			</div>
	</div>
	<div class="nui-toolbar" style="text-align: right; padding-top: 15px; padding-right: 25px;" borderStyle="border:0;">
		<a class="nui-button" id="con_contract_detail_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var contractId ="<%=request.getParameter("contractId") %>";//htID
	var amountDetailId ="<%=request.getParameter("amountDetailId") %>";//协议申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var productType =  "<%=request.getParameter("productType") %>";//贷种
	var syndicatedObjectCd = "<%=request.getParameter("syndicatedObjectCd") %>";//银团标志：01-外部 ，02-内部
	var cycleindcon = "1";//合同循环标志
	var repayType = '';
	$("#table3").css("display","none");
	$("#table4").css("display","none");
	$("#table5").css("display","none");
	$("#table6").css("display","none");
	$("#table7").css("display","none");
	$("#table9").css("display","none");
	$("#dkll").css("display","none");
	
	if("01006001"==productType||"01006002"==productType||"01008001"==productType|| "01008002"==productType
	||"01008010"==productType ||"01006010"==productType //村镇银行贴现产品
	){
		$("#table1").css("display","none");
	}
	
	if(productType.substring(0,6).indexOf("010080") != -1){
		var cycleFlag = "<%=request.getParameter("cycleIndCon") %>";//循环标志
		if(cycleFlag == "0"){
			$("#table1").css("display","block");
		}else{
			$("#table1").css("display","none");
		}
	}
		
	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"contractId":contractId,"productType":productType});
		$.ajax({
            url: "com.bos.conInfo.conContractInfo.getConDetailInfoByContarctId.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
        	async: false,
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	repayType = o.tbConContractInfo.repaymentType;
            	//合同调整时有些东西能改
            	var oldContractId = o.tbConContractInfo.oldContractId;
            	//提前还款是否收取违约金
            	if(nui.get("conDetail.prepaymentPenalty")){
            		if(o.tbConContractInfo.prepaymentPenalty){
            			nui.get("conDetail.prepaymentPenalty").setValue(o.tbConContractInfo.prepaymentPenalty);
            			if(nui.get("conDetail.prepayMakeupRate")){
            				nui.get("conDetail.prepayMakeupRate").setValue(o.tbConContractInfo.prepayMakeupRate);
            			}
            		}
            		//nui.get("conDetail.prepaymentPenalty").validate();
            	}
            	if(syndicatedObjectCd == '02'){//行内银团
            		$("#table1").css("display","none");
            		$("#table8").css("display","none");
            		if(oldContractId==null || oldContractId==''){//合同调整
            			var rategrid = new nui.Form("#table2");
	    				rategrid.setEnabled(false);
	    				
	    				var rateType = nui.get("loanrate.rateType").getValue();
	    				if(rateType=='2'){//浮动比例
							nui.get("loanrate.rateFloatProportion").setEnabled(true);
							nui.get("rateFloatProportion").setValue(o.apvrate.rateFloatProportion);
						}else if(rateType=='1'){//固定
							nui.get("loanrate.yearRate").setEnabled(true);
							nui.get("yearrate").setValue(o.apvrate.yearRate);
						}
            		}else{
            			var rateType = nui.get("loanrate.rateType").getValue();
	    				if(rateType=='2'){//浮动比例
							nui.get("rateFloatProportion").setValue(o.apvrate.rateFloatProportion);
						}else if(rateType=='1'){//固定
							nui.get("yearrate").setValue(o.apvrate.yearRate);
						}
            		}
            		
					//节假日利息处理方式
					nui.get("loanrate.holidayIntFlg").setValue('1');
            		
            		
            	}else{
            		if(o.tbBizAmountDetailApprove.cycleIndCon!=null && o.tbBizAmountDetailApprove.cycleIndCon!='' ){
	            		cycleindcon = o.tbBizAmountDetailApprove.cycleIndCon;
	            	}
	            	//并购走单笔流贷合同(01005001并购代码　01003001客户并购贷款)
	            	if(productType=='01005001'||productType=='01003011'){
	            		cycleindcon = '0';
	            	}
	            	
	            	amountDetailId = o.tbConContractInfo.amountDetailId;
					//保理不展示基本信息
					//if(productType=='01004001'){
					//	$("#table1").css("display","none");
					//}
					
					//贸易合同信息
					if(productType=='01008001'||productType=='01008002'||productType=='01008010' ||productType=='01009001'||productType=='01009002' ||productType=='01009010'){
						$("#table9").css("display","block");
						//nui.get("myhtdiv").hide();
						var json = nui.decode({"amountDetailId":contractId});//此处存合同ID
						var myht = nui.get("myht");
						myht.load(json);
						if("1" != proFlag){
							nui.get("myhtdiv").hide();
							myht.setEnabled(false);
						}
					}
					//流贷中只有单笔有还款计划--只有14还款方式才有
					if(repayType=='1400'||repayType=='1410'){
						if(cycleindcon == '0'
							||productType=='01002001'||productType=='01002002'||productType=='01002003'||productType=='01003001'||productType=='01003002'
							||productType=='01003003'||productType=='01003004'||productType=='01003005'||productType=='01003006'
							||productType=='01003007'||productType=='01003009'||productType=='01003011'||productType=='01003012'||productType=='01003013'
							||productType=='01003015'||productType=='01003016'||productType=='01013001'||productType=='01013010'
							||productType=='01003050' //村镇银行固定资产产品
							){
								$("#table4").css("display","block");
								var json = nui.decode({"contractId":contractId});
						    	var grid2 = nui.get("grid2");
								grid2.load(json);
								if("1" != proFlag){
									nui.get("hkjhdiv").hide();
									grid2.setEnabled(false);
								}
						}
					}
					
					//费用信息
					if(productType=='01013010'|| productType=='01013001'||productType=='01008001'||productType=='01008010'||productType=='01008010' ||productType=='01008002'||productType=='01011001'
						||productType=='01009001'||productType=='01004001'){
						$("#table5").css("display","block");
						var json = nui.decode({"contractId":contractId});//由于国内保理申请就要填费用信息，此处用amountDetailId取信息
				    	var grid3 = nui.get("grid3");
						grid3.load(json);
						if("1" != proFlag||productType=='01004001'){
							nui.get("feediv").hide();
							grid3.setEnabled(false);
						}
					}
					//单次流贷才有提款计划，只有流贷才有合同循环标志，所以只要这个标志是0（初始化为1），就说明要显示提款计划--银承也有此标志
					if('0'==cycleindcon &&productType!='01008001'&& productType!='01008010' &&productType!='01008002'){
						$("#table3").css("display","block");
						var json = nui.decode({"contractId":contractId});
				    	var grid1 = nui.get("grid1");
						grid1.load(json);
						if("1" != proFlag){
							nui.get("payoutdiv").hide();
							grid1.setEnabled(false);
						}
					}
					//贷款利率
					if(productType=='01008001'||productType=='01008002'||productType=='01008010'||productType=='01008010'||productType=='01009001'||productType=='01009002' ||productType=='01009010'
						||productType=='01010001'||productType=='01011001'||productType=='01012001'
						||productType=='01004001'
						){
						$("#table2").css("display","none");
					}else if(productType=='01006001'||productType=='01006002'
						||"01006010"==productType //村镇银行贴现产品
					){//贴现只有申请利率
						$("#lllx").css("display","none");
						nui.get("loanrate.rateType").hide();
						nui.get("loanrate.rateType").setValue('');
						
						$("#floatWay").css("display","none");
						nui.get("loanrate.floatWay").hide();
						nui.get("loanrate.floatWay").setValue('');
						
						$("#rateFloatProportionq").css("display","none");
						nui.get("loanrate.rateFloatProportion").hide();
						nui.get("loanrate.rateFloatProportion").setValue('');
						
						$("#isChangeRate").css("display","none");
						nui.get("loanrate.irUpdateFrequency").hide();
						nui.get("loanrate.irUpdateFrequency").setValue('');
						
						$("#jxzq").css("display","none");
						nui.get("loanrate.interestCollectType").hide();
						nui.get("loanrate.interestCollectType").setValue('');
						
						$("#jjrsybz").css("display","none");
						nui.get("loanrate.holidayFlg").hide();
						nui.get("loanrate.holidayFlg").setValue('');
						
						$("#jjrlxclfs").css("display","none");
						nui.get("loanrate.holidayIntFlg").hide();
						nui.get("loanrate.holidayIntFlg").setValue('');
						
						$("#kxqfs").css("display","none");
						nui.get("loanrate.gracePeriodType").hide();
						nui.get("loanrate.gracePeriodType").setValue('');
						
						$("#gracePeriodDay").css("display","none");
						nui.get("loanrate.gracePeriodDay").hide();
						nui.get("loanrate.gracePeriodDay").setValue('');
						
						$("#kxqlxclfs").css("display","none");
						nui.get("loanrate.graceCountIntFlag").hide();
						nui.get("loanrate.graceCountIntFlag").setValue('');
						//逾期罚息率
						if(productType=='01006001'||productType=='01006002'
						||productType=='01006010' //村镇银行贴现产品
						){
							$("#ovardueRate").css("display","none");
							nui.get("loanrate.overdueRateUpProportion").hide();
							nui.get("loanrate.overdueRateUpProportion").setValue('');
						}
						
						$("#yearRate").css("display","block");
						nui.get("loanrate.yearRate").show();
						
						nui.get('tableloanrate').refreshTable();
						nui.get("yearrate").setValue(o.apvrate.yearRate);
					}else{
            			//1、利率类型、浮动形式、浮动方式、利率调整方式、结息周期、节假日顺延标志、节假日利息处理方式、宽限期方式、宽限期天数、宽限期利息处理方式、罚息率应均为反显不可修改
        				//2、浮动比率/浮动值只允许在大于审批值（上浮大于，下浮小于）
    					var rategrid = new nui.Form("#table2");
        				rategrid.setEnabled(false);
        				
        				var rateType = nui.get("loanrate.rateType").getValue();
        				if(rateType=='2'){//浮动比例
							nui.get("loanrate.rateFloatProportion").setEnabled(true);
							nui.get("rateFloatProportion").setValue(o.apvrate.rateFloatProportion);
						}else if(rateType=='1'){//固定
							nui.get("loanrate.yearRate").setEnabled(true);
							nui.get("yearrate").setValue(o.apvrate.yearRate);
						}
						//节假日利息处理方式
						nui.get("loanrate.holidayIntFlg").setValue('1');
						
						//结息周期--合同循环标志
						var conflg = o.tbConContractInfo.cycleIndCon;
						/*if(conflg=='0'){
							//合同循环时，“结息周期”只能选择“按月结息/按季结息/在借款到期日付清全部利息”
							nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2,6'));
						}else if(conflg=='1'){
							//合同不循环时，“结息周期”只能选择“按月结息/按季结息/按还本计划表付清本期利息/在借款到期日付清全部利息”
							nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2,5,6'));
						}*/
						nui.get('tableloanrate').refreshTable();
					}
					//利率信息---国际保函 信用证开证和提货担保没有利率信息 
					if(productType=='01007010'||productType=='01007013'||productType=='01007014'){
						$("#table2").css("display","none");	
					}
					//明细信息add by shendl
					if(productType=='01007001'||productType=='01007003'||productType=='01007004'||productType=='01007009'
						||productType=='01007011'||productType=='01007012'||productType=='01007005'||productType=='01007006'){
						//国结的利率信息 暂时都设置为反显 
						nui.get("loanrate.yearRate").setEnabled(true);
						nui.get("loanrate.overdueRateUpProportion").setEnabled(true);
						}
					
					//垫款利率
					if(productType=='01008001'||productType=='01008002'||productType=='01008010'||productType=='01009001'||productType=='01009002' ||productType=='01009010'
						||productType=='01006001'||productType=='01006002'
						||productType=='01006010' //村镇银行贴现产品
						||productType=='01007009'
						||productType=='01007010'||productType=='01007012'||productType=='01007013'||productType=='01007014'){
						$("#dkll").css("display","block");
						//nui.get("conDetail.dkll").setEnabled(false);
						nui.get("conDetail.dkll").validate();
					}
					//仲裁方式
					if(productType=='01001001'||productType=='01001002'||productType=='01001003'||productType=='01001004'||productType=='01001005'||productType=='01001006'
						||productType=='01002001'||productType=='01002002'||productType=='01002003'||productType=='01003001'||productType=='01003002'||productType=='01003003'
						||productType=='01003004'||productType=='01003005'||productType=='01003006'||productType=='01003007'||productType=='01003009' 
						||productType=='01003050'||productType=='01001050' ||productType=='01001051' ||productType=='01001052' ||productType=='01001053' ||productType=='01001054'//村镇银行流动资金产品
						||productType=='01003011'||productType=='01003012'||productType=='01003013'||productType=='01003015'||productType=='01003016'||productType=='01009001'||productType=='01011001'
						||productType=='01010001'||productType=='01005001'||productType=='01006001'||productType=='01006002'||productType=='01008001'||productType=='01008002'
						||productType=='01008010'||productType=='01006010'  //村镇银行贴现产品
						||productType=='01001007'||productType=='01001008' ||productType=='01001009' ||productType=='01001010' ||productType=='01001011'
					    ||productType=='01001012'||productType=='01001013' ||productType=='01001014' ||productType=='01001015' ||productType=='01001016' 
					    ||productType=='01001017'||productType=='01001018' ||productType=='01001019' ||productType=='01001020' ||productType=='01001021' 
					    ||productType=='01001022'||productType=='01001023'||productType=='01001024' ||productType=='01001025' ||productType=='01001026' 
					    ||productType=='01001027'||productType=='01001028' |productType=='01001029' ||productType=='01001030' ||productType=='01001031'||productType=='01008001'||productType=='01008010'
					    ||productType=='01001040'||productType=='01001041'||productType=='01001042'){
						$("#table6").css("display","block");
						//arbiTypeChange();
					}
					//协议签署
					if(productType=='01001001'||productType=='01001002'||productType=='01001003'||productType=='01001004'||productType=='01001005'||productType=='01001006'
						||productType=='01002001'||productType=='01002002'||productType=='01002003'||productType=='01003001'||productType=='01003002'||productType=='01003003'
						||productType=='01003004'||productType=='01003005'||productType=='01003006'||productType=='01003007' 
						||productType=='01003050'||productType=='01001050' ||productType=='01001051' ||productType=='01001052' ||productType=='01001053' ||productType=='01001054'//村镇银行固定资产产品
  						||productType=='01003009'||productType=='01003011'||productType=='01003012'||productType=='01003013'||productType=='01003015'||productType=='01003016'
						||productType=='01008001'||productType=='01008002'||productType=='01008010'
						||productType=='01009001'||productType=='01010001'||productType=='01011001'||productType=='01005001'
						||productType=='01001007'||productType=='01001008' ||productType=='01001009' ||productType=='01001010' ||productType=='01001011'
					    ||productType=='01001012'||productType=='01001013' ||productType=='01001014' ||productType=='01001015' ||productType=='01001016' 
					    ||productType=='01001017'||productType=='01001018' ||productType=='01001019' ||productType=='01001020' ||productType=='01001021' 
					    ||productType=='01001022'||productType=='01001023'||productType=='01001024' ||productType=='01001025' ||productType=='01001026' 
					    ||productType=='01001027'||productType=='01001028' |productType=='01001029' ||productType=='01001030' ||productType=='01001031'||productType=='01006001'||productType=='01006002'||productType=='01008001'
					    ||productType=='01008010'||productType=='01006010' //村镇银行贴现产品
					    ||productType=='01001040'||productType=='01001041'||productType=='01001042'){
						$("#table7").css("display","block");
					}
					//补充条款
					if(productType=='01004001'||productType=='01010001'||productType=='01011001'||productType=='01012001'){
						$("#table8").css("display","none");
						// 国结的产品不显示 贴息和收费的标识
						//$("#sftx").css("display","none");
						//nui.get("loanrate.discFlag").hide();
						//$("#sqfy").css("display","none");
						//nui.get("loanrate.feeFlag").hide();
					}
					//汇票贴现才会有发票/票据
					if(productType=='01006001'||productType=='01006002'
					||productType=='01006010' //村镇银行贴现产品
					){
						nui.get("conDetail.txqq").setEnabled(false);
						nui.get("conDetail.currencyCd").setValue("CNY");
					}
					//融资性保函
					if(productType=='01009001'){
						nui.get("conDetail.bhzl").setData(getDictData('XD_SXYW0207','str','15,16,17,18'));
					}
					//非融资保函保函种类
					if(productType=='01009002' || productType=='01009010'){
						nui.get("conDetail.bhzl").setData(getDictData('XD_SXYW0207','str','01,02,03,04,05,06,07,08,09,10,11,12,13,14,18'));
					}
					
					//出口托收押汇合同中“押汇贴现标志”只能选押汇
					if(productType=='01007003'){
						nui.get("conDetail.yhtxbz").setValue('0');
						nui.get("conDetail.yhtxbz").setEnabled(false);
					}
					
					//第三方类型
					thirdType();
					form.validate();
					
            	}
			}
        });
        setXht();//循环通/续接贷 资金支付方式默认为否
    	//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_detail_info_save").hide();
			form.setEnabled(false);
		}
	}

	//循环通/续接贷资金支付方式默认为否 不可选
	function setXht(){
		var jsonC = nui.encode({"contractId":contractId});
		$.ajax({
			url  :"com.bos.conApply.conApply.getBizHappenType.biz.ext",
			type :"POST",
			data : jsonC,
			contentType: "text/json",
			cache : false,
			success : function(mydata){
				var o = nui.decode(mydata.Infos);
				if(o[0].BIZHAPPENTYPE == "06"){//借新还旧的业务
					if(nui.get("conDetail.payWay")!=null){
						nui.get("conDetail.payWay").setValue("0");//自主字符
						nui.get("conDetail.payWay").setEnabled(false);//置灰
						nui.get("conDetail.payWay").validate();
					}
				}
			}
		});
	}
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	if(productType == '01013001' || productType == '01013010'){
    		var row={"periodsNumber":++count,"chargingType":'手续费'};
    	}else{
    		var row={"periodsNumber":++count,"currencyCd":'CNY'};
    	}
        nui.get(gr).addRow(row,count);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.repayPlanId){
            		var json = nui.encode({"repayPlanId":row.repayPlanId,"deleteType":"1"});
	            	$.ajax({
			            url: "com.bos.conInfo.conContractInfo.deleteRepayPlan.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else if(row.payoutPlanId){
            		var json = nui.encode({"repayPlanId":row.payoutPlanId,"deleteType":"2"});
	            	$.ajax({
			            url: "com.bos.conInfo.conContractInfo.deleteRepayPlan.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else if(row.feeId){
            		var json = nui.encode({"repayPlanId":row.feeId,"deleteType":"3"});
	            	$.ajax({
			            url: "com.bos.conInfo.conContractInfo.deleteRepayPlan.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else if(row.htId){
            		var json = nui.encode({"repayPlanId":row.htId,"deleteType":"5"});
	            	$.ajax({
			            url: "com.bos.conInfo.conContractInfo.deleteRepayPlan.biz.ext",
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
    //费率项 自动计算收费比例或者收费金额
    function grid3Click(e){
    	debugger;
    	var grid3 = nui.get("grid3");
    	var amt = nui.get("tbConContractInfo.contractAmt").getValue();//合同金额
    	var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{1,4})?$/;
    	if(e.field == "chargingProportion"){
    		if(mny.test(e.value) && amt!=null){
    			var a=amt*e.value/1000;
    			a=a.toFixed(2);
    			grid3.updateRow(e.row,{"shouldFee":a});
    		}
    	}
    	if(e.field == "shouldFee"){
    		if(mny.test(e.value) && amt!=null){
    			var a=e.value*1000/amt;
    			a=a.toFixed(2);
    			grid3.updateRow(e.row,{"chargingProportion":a});
    		}
    	}
    }
    
    //保证金比例赋值后，计算保证金
    function bzjblchange(){
    	var amt = nui.get("tbConContractInfo.contractAmt").getValue();
    	var bl = nui.get("conDetail.bzjbl").getValue();
    	var blbdy = nui.get("conDetail.bzjblbdy").getValue();//保证金比例不低于
    	if(parseFloat(bl)<parseFloat(blbdy)){
    		nui.get("conDetail.bzjbl").setValue('');
    		nui.alert("低于申请输入的最低比例限额！");
    		return;
    	}
    	var bzjje = parseFloat(bl)*parseFloat(amt)/100;
    	bzjje = bzjje.toFixed(8)+"";
    	if(nui.get("conDetail.bzjje")){
	    	if(parseFloat(bzjje.substring(bzjje.indexOf(".")+3))>parseFloat("0")){
				var mm = parseFloat(bzjje.substring(0,bzjje.indexOf(".")+3))+parseFloat("0.01");
				nui.get("conDetail.bzjje").setValue(mm.toFixed(2));
	    		nui.get("conDetail.bzjje").validate();
			}else{
				var mn = parseFloat(bzjje.substring(0,bzjje.indexOf(".")+3));
				nui.get("conDetail.bzjje").setValue(mn.toFixed(2));
	    		nui.get("conDetail.bzjje").validate();
			}
    		//nui.get("conDetail.bzjje").setValue(bzjje);
    		//nui.get("conDetail.bzjje").validate();
    	}
    	if(nui.get("conDetail.bzj")){
	    	if(parseFloat(bzjje.substring(bzjje.indexOf(".")+3))>parseFloat("0")){
				var mm = parseFloat(bzjje.substring(0,bzjje.indexOf(".")+3))+parseFloat("0.01");
				nui.get("conDetail.bzj").setValue(mm.toFixed(2));
	    		nui.get("conDetail.bzj").validate();
			}else{
				var mn = parseFloat(bzjje.substring(0,bzjje.indexOf(".")+3));
				nui.get("conDetail.bzj").setValue(mn.toFixed(2));
	    		nui.get("conDetail.bzj").validate();
			}
    		//nui.get("conDetail.bzj").setValue(bzjje);
    		//nui.get("conDetail.bzj").validate();
    	}
    }
    
    function save(){
    	form.validate();
    	if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        git.mask();
        nui.get("con_contract_detail_info_save").setEnabled(false);
		//仲裁方式处理
		var arbivar = nui.get("tbConAttachedInfo.arbitrateType").getValue();
		if('02'==arbivar){//如果选仲裁，显示仲裁模块,并将“其他”项隐藏并清空
			nui.get("tbConAttachedInfo.other").setValue("");
		}else if('03'==arbivar){//如果选其他，显示其他模块,并将“仲裁”隐藏并清空
			nui.get("tbConAttachedInfo.arbitrateName").setValue("");
			nui.get("tbConAttachedInfo.arbitrateAddress").setValue("");
		}else{//选诉讼或请选择，将“其他”及“仲裁”项隐藏并清空
			nui.get("tbConAttachedInfo.other").setValue("");
			nui.get("tbConAttachedInfo.arbitrateName").setValue("");
			nui.get("tbConAttachedInfo.arbitrateAddress").setValue("");
		}
		//仲裁方式处理结束      
		//宽限期天数
		var  gracePeriodType = nui.get("loanrate.gracePeriodType").getValue();
		if(gracePeriodType){
			if(gracePeriodType!='2'){
				nui.get("loanrate.gracePeriodDay").setValue("");
			}
		}
		//宽限期
		if(nui.get("loanrate.gracePeriodType")!=null){
			var  gracePeriodType = nui.get("loanrate.gracePeriodType").getValue();
			if(gracePeriodType!='2'){
				nui.get("loanrate.gracePeriodDay").setValue("");
			}
			if(gracePeriodType=='0'){
				nui.get("loanrate.graceCountIntFlag").setValue("");
			}
		}
		//节假日
		if(nui.get("loanrate.holidayFlg")){
			var  holidayFlg = nui.get("loanrate.holidayFlg").getValue();
			if(holidayFlg=='0'){
				nui.get("loanrate.holidayIntFlg").setValue("");
			}
			var  gracePeriodType = nui.get("loanrate.gracePeriodType").getValue();
			if(holidayFlg!='' && holidayFlg!=null){
				if(holidayFlg !='0'  && gracePeriodType!='0'){
					nui.alert("节假日与宽限期只能使用一种!");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
			}
		}
		//提前还款是否收取违约金
/* 		if(nui.get("conDetail.prepayMakeupRate")){
			if(nui.get("conDetail.prepayMakeupRate").getValue=="0"){
				o.tbConContractInfo.prepaymentPenalty = "";
			} 
		}else{
			o.tbConContractInfo.prepaymentPenalty = "";
			o.tbConContractInfo.prepayMakeupRate = "";
		} */
		
		//利率模块处理结束
		
		var o = form.getData();
		//利率
		var rateType = o.loanrate.rateType;
		if(rateType=='1'){//固定
			o.loanrate.floatWay = '';
			o.loanrate.rateFloatProportion = '';
		}else if(rateType=='2'){//浮动
			o.loanrate.yearRate='';
		}
		//进口保函，主合同明细信息中保函有效期应不得早于系统日
		if(productType=='01007008'){
			if(o.conDetail.bhyxq<o.tbConContractInfo.bizDate){
				nui.alert("保函有效期应不得早于当前日期！");
				nui.get("con_contract_detail_info_save").setEnabled(true);
				git.unmask();
				return;
			}
		}
		//“进口信用证”，则主合同明细信息中“信用证有效期”应不得早于系统日
		if(productType=='01007007'){
			if(o.conDetail.xyzyxq<o.tbConContractInfo.bizDate){
				nui.alert("信用证有效期应不得早于当前日期！");
				nui.get("con_contract_detail_info_save").setEnabled(true);
				git.unmask();
				return;
			}
		}
		
		//贸易合同信息必填--银承、保函
		if(productType=='01008001'||productType=='01008002'||productType=='01008010'||productType=='01009001'){
			if(syndicatedObjectCd!='02'){
				var myhtcount = nui.get("myht").getData().length;
				if(myhtcount=='0'){
					nui.alert("未填写贸易合同信息");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
			}
		}
		
		//最低还款金额
		if(o.conDetail.leastPrepayAmount){
			if(o.conDetail.leastPrepayAmount>o.tbConContractInfo.contractAmt){
				nui.alert("最低还款金额不能大于合同金额");
				nui.get("con_contract_detail_info_save").setEnabled(true);
				git.unmask();
				return;
			}
		}
		
		var PayoutPlans = nui.get("grid1").getChanges();/* 提款 */
		var repayPlans = nui.get("grid2").getChanges();/* 还款 */
		var fees = nui.get("grid3").getChanges();/* 费用 */
		var myhtxxs = nui.get("myht").getChanges();/* 贸易合同信息 */
		var amt = nui.get("tbConContractInfo.contractAmt").getValue();
		//校验动态列表必输项
		//提款计划--------------------begin-----------------
	 	if(PayoutPlans){
			var beginDate = o.tbConContractInfo.beginDate;
			var endDate = o.tbConContractInfo.endDate;
			for(var i=0;i<PayoutPlans.length;i++){
				if(PayoutPlans[i].payoutDate==null ||PayoutPlans[i].payoutDate=='' ||
				PayoutPlans[i].payoutAmt==null ||PayoutPlans[i].payoutAmt=='' ){
					nui.alert("请将提款计划填写完整！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
				if(PayoutPlans[i].payoutAmt<100){
					nui.alert("提款金额不能小于100！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
			}
		} 
		
		//提款计划-------------------end-----------------
		//还款计划-----------------begin------------
		//var totalrepay = 0;
		if(repayPlans!=null&&repayPlans.length!='0'){
			var beginDate = o.tbConContractInfo.beginDate;
			var endDate = o.tbConContractInfo.endDate;
			for(var i=0;i<repayPlans.length;i++){
				if(repayPlans[i].repayDate==null ||repayPlans[i].repayDate=='' ||
				repayPlans[i].repayAmt==null ||repayPlans[i].repayAmt==''){
					nui.alert("请将还款计划填写完整！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
			}
		}
		//还款计划-----------------end------------	
		//费用信息
		if(fees){
			for(var i=0;i<fees.length;i++){
				if(fees[i].chargingType==null ||fees[i].chargingType=='' ||
				fees[i].costType==null ||fees[i].costType=='' 
				||fees[i].shouldFee==0){
					nui.alert("请将费率信息填写完整！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
				if((fees[i].shouldFee==null || fees[i].shouldFee=='')&&(fees[i].chargingProportion==null || fees[i].chargingProportion=='')){
        			nui.alert("收费金额和收费比例必须填一项！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
        		}
				/* var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{2})?$/;
				if(fees[i].shouldFee){
					if(!mny.test(fees[i].shouldFee)){
						nui.alert("费率信息中收费金额输入有误！");
						initPage();
						nui.get("con_contract_detail_info_save").setEnabled(true);
						git.unmask();
						return;
					}
				}
				var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{1,2})?$/;
				if(fees[i].chargingProportion){
					if(!mny.test(fees[i].chargingProportion)){
						nui.alert("费率信息中收费比例输入有误！");
						initPage();
						nui.get("con_contract_detail_info_save").setEnabled(true);
						git.unmask();
						return;
					}
				} */
			}
		}
		//贸易合同信息
		if(myhtxxs){
			for(var i=0;i<myhtxxs.length;i++){
				if(myhtxxs[i].htbh==null ||myhtxxs[i].htbh=='' ||
				myhtxxs[i].htgf==null ||myhtxxs[i].htgf=='' ||
				myhtxxs[i].htxf==null ||myhtxxs[i].htxf=='' ||
				myhtxxs[i].htqdrq==null ||myhtxxs[i].htqdrq=='' ||
				myhtxxs[i].currencyCd==null ||myhtxxs[i].currencyCd=='' ||
				myhtxxs[i].htzje==null ||myhtxxs[i].htzje==''){
					nui.alert("请将贸易合同信息填写完整！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
				
				if(myhtxxs[i].htdqrq!=null && myhtxxs[i].htdqrq!=''){
					if(myhtxxs[i].htqdrq>myhtxxs[i].htdqrq){
						nui.alert("贸易合同信息中签订日期不能大于到期日期！");
						nui.get("con_contract_detail_info_save").setEnabled(true);
						git.unmask();
						return;
					}
				}
				
				//var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{2})?$/;
				//if(!mny.test(myhtxxs[i].htzje)){
					//nui.alert("贸易合同信息中合同总金额输入有误！");
					//initPage();
					//nui.get("con_contract_detail_info_save").setEnabled(true);
					//git.unmask();
					//return;
				//}
			if(myhtxxs[i].htzje==0){
					nui.alert("贸易合同信息中合同总金额不能为0！");
					//initPage();
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
				}
			}
		}
		
		o.contractId = contractId;
		o.amountDetailId = amountDetailId;
		o.productType = productType;
		o.PayoutPlans = PayoutPlans;
		o.repayPlans = repayPlans;
		o.fees = fees;
		o.myhtxxs = myhtxxs;
		o.op="1";//对公合同明细
    	var json = nui.encode(o);
    	
    	//国结三个表外业务---保证金信息
        if(productType=="01007013"||productType=="01007010"||productType=="01007014"){
           //保证金账号、币种、金额、比例四个信息  要么全有 要么全都不能有
           var bondAcct = nui.get("conDetail.bzjzh").getValue();//保证金账号
           var bondCurr = nui.get("conDetail.bzjbz").getValue();//保证金币种
		   var bondAmt = nui.get("conDetail.bzjje").getValue();//保证金金额
		   var bondRate = nui.get("conDetail.bzjblbdy").getValue();//保证金比例不低于
           
           if(bondAcct != '' && bondAcct != null && bondAcct != 'null' && bondAcct != 'undefined'){
           		if(bondCurr==null||bondCurr==''||bondAmt==null||bondAmt==''||bondRate==null||bondRate==''){
           			nui.alert("保证金信息不完整,请检查[币种&金额&比例]！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
           		}
           }
           if(bondCurr != '' && bondCurr != null && bondCurr != 'null' && bondCurr != 'undefined'){
           		if(bondAcct==null||bondAcct==''||bondAmt==null||bondAmt==''||bondRate==null||bondRate==''){
           			nui.alert("保证金信息不完整,请检查[账号&金额&比例]！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
           		}
           }
           if(bondAmt != '' && bondAmt != null && bondAmt != 'null' && bondAmt != 'undefined'){
           		if(bondCurr==null||bondCurr==''||bondAcct==null||bondAcct==''||bondRate==null||bondRate==''){
           			nui.alert("保证金信息不完整,请检查[账号&币种&比例]！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
           		}
           }
           if(bondRate != '' && bondRate != null && bondRate != 'null' && bondRate != 'undefined'){
           		if(bondCurr==null||bondCurr==''||bondAmt==null||bondAmt==''||bondAcct==null||bondAcct==''){
           			nui.alert("保证金信息不完整,请检查[币种&金额&比例]！");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
           		}
           }  
        }
        
        
        
        
    	//如果点击保存的是国结的业务产品---调用国结系统的业务编号校验接口 校验业务编号的合法性
        if(productType=="01007001"||productType=="01007003"||productType=="01007004"||productType=="01007009"||
           productType=="01007012"||productType=="01007011"||productType=="01007006"||productType=="01007005"||
           productType=="01007010"||productType=="01007013"){
            //如果是国结产品---信用证开证需要判断溢装比例更新合同金额
        	if(productType=="01007013"){
        		var yzbl = nui.get("conDetail.yzbl").getValue();
				var bz = nui.get("tbConContractInfo.currencyCd").getValue();//合同币种 
				var kzje = nui.get("conDetail.kzje").getValue();//合同金额
				var exchangeRate = nui.get("tbConContractInfo.exchangeRate").getValue();//合同汇率
				
				if(null==yzbl||""==yzbl){
					yzbl = 0;
				}
				
				var detailBalance = nui.get("tbBizAmountDetailApprove.detailBalance").getValue();//批复可用金额
 				var newAmt = parseFloat(kzje)*(1+parseFloat(yzbl)/100);//新的合同金额
				var newRmbAmt = parseFloat(newAmt)*parseFloat(exchangeRate);//新的折算人民币金额
				if(parseFloat(newRmbAmt)>parseFloat(detailBalance)){//合同折算人民币金额>批复可用金额 
					git.unmask();
					nui.alert("开证金额折算人民币金额["+newRmbAmt+"]不能大于批复明细可用金额[CNY:"+detailBalance+"]");
 					nui.get("conDetail.kzje").setValue("");
 					nui.get("con_contract_detail_info_save").setEnabled(true);
 					return;
 				}
				o.tbConContractInfo.contractAmt = newAmt;
				o.tbConContractInfo.rmbAmt = newRmbAmt;
				o.tbConContractInfo.conBalance=newAmt;
				
				var bzjbz = nui.get("conDetail.bzjbz").getValue();//保证金币种
				var bzjje = nui.get("conDetail.bzjje").getValue();//保证金金额
				var bzjbl = nui.get("conDetail.bzjblbdy").getValue();//保证金比例不低于
				var bzjRate = "1";//保证金币种的汇率
				var bz = nui.get("tbConContractInfo.currencyCd").getValue();//合同币种 
				var conInfoRmbAmt = newRmbAmt;//新的折算人民币金额
				var conAmt = newAmt;//新的合同金额
				var minBzjje;//最低保证金金额
				if(bzjbz != '' && bzjbz != null && bzjbz != 'null' && bzjbz != 'undefined'){
					if(bz!==bzjbz){//保证金币种和合同币种不一致 
						if (bzjbz != 'CNY'){//保证金币种和合同币种不一致而且保证金币种不是人民币
            				var jsonHl = nui.encode({"bz":bzjbz});
							$.ajax({
        						url: "com.bos.conInfo.conInfoSxxy.getChangeRate.biz.ext",
        						type: 'POST',
        						data: jsonHl,
        						cache: false,
        						contentType:'text/json',
        						success: function (text) {
        							if(text){
        								if(text.validityInd=="1"){
        									bzjRate = parseFloat(text.disRateOfRmb)/parseFloat(100);
        								if (bzjbl != '' && bzjbl != null && bzjbl != 'null' && bzjbl != 'undefined') {
											minBzjje = parseFloat(conInfoRmbAmt)*parseFloat(bzjbl)/parseFloat(100)/parseFloat(bzjRate);
									}
								if (bzjje != '' && bzjje != null && bzjje != 'null' && bzjje != 'undefined') {
									if((parseFloat(bzjje))<parseFloat(minBzjje)){
										nui.alert("最低保证金金额["+bzjbz+":"+minBzjje+"]");
										nui.get("con_contract_detail_info_save").setEnabled(true);
										git.unmask();
										return;
									}
								}
        					}else{
        						alert("未获取到币种["+bzjbz+"]的汇率信息");
        						nui.get("con_contract_detail_info_save").setEnabled(true);
								git.unmask();
								return;
        					}
        				}
        			}
        		});	
			}else{//保证金币种和合同币种不一致但是保证金币种是人民币
        		if (bzjbl != '' && bzjbl != null && bzjbl != 'null' && bzjbl != 'undefined') {
					minBzjje = parseFloat(conInfoRmbAmt)*parseFloat(bzjbl)/parseFloat(100)/parseFloat(bzjRate);
				}
				if (bzjje != '' && bzjje != null && bzjje != 'null' && bzjje != 'undefined') {
					if((parseFloat(bzjje))<parseFloat(minBzjje)){
						nui.alert("最低保证金金额["+bzjbz+":"+minBzjje+"]");
						nui.get("con_contract_detail_info_save").setEnabled(true);
						git.unmask();
						return;
					}
				}
			}
		}else{//保证金币种和合同币种一致 
			if (bzjbl != '' && bzjbl != null && bzjbl != 'null' && bzjbl != 'undefined') {
				minBzjje = parseFloat(conAmt)*parseFloat(bzjbl)/parseFloat(100)/parseFloat(bzjRate);
			}
			if (bzjje != '' && bzjje != null && bzjje != 'null' && bzjje != 'undefined') {
				if((parseFloat(bzjje))<parseFloat(minBzjje)){
					nui.alert("最低保证金金额["+bzjbz+":"+minBzjje+"]");
					nui.get("con_contract_detail_info_save").setEnabled(true);
					git.unmask();
					return;
					}
				}
			}
		}
				
				
				json = nui.encode(o);
				//没有填写溢装比例或者填写为0---不处理合同金额
				if(null==yzbl||""==yzbl||0==yzbl){
					//alert("信用证溢装比例的变化会引起合同金额的改变，请留意");
					$.ajax({
        				url: "com.bos.conInfo.conContractInfo.saveConContractDetailInfo.biz.ext",
        				type: 'POST',
        				data: json,
        				cache: false,
        				contentType:'text/json',
        				success: function (text) {
	        			if(text.msg){
	        				git.unmask();
	        				nui.alert(text.msg);
	        				nui.get("con_contract_detail_info_save").setEnabled(true);
	        				return "1";
	        			}else{
	        				git.unmask();
	        				nui.get("con_contract_detail_info_save").setEnabled(true);
	        				nui.alert("保存成功!信用证溢装比例的变化会引起合同金额的改变，请留意");
	        				initPage();
							return "1";
	        				}
        				}
        			});
				}else{
					
        			$.ajax({
        							url: "com.bos.conInfo.conContractInfo.saveConContractDetailInfo.biz.ext",
        							type: 'POST',
        							data: json,
        							cache: false,
        							contentType:'text/json',
        							success: function (text) {
	        						if(text.msg){
	        							git.unmask();
	        							nui.alert(text.msg);
	        							nui.get("con_contract_detail_info_save").setEnabled(true);
	        							return "1";
	        						}else{
	        							git.unmask();
	        							nui.get("con_contract_detail_info_save").setEnabled(true);
	        							nui.alert("保存成功!合同金额根据溢装比例的变化更新为: ["+bz+"]"+newAmt);
	        							initPage();
										return "1";
	        						}
        						}});
				}	
				return; 
        	}
         /**
		 * 特殊处理(国结业务需求)
		 * 1. 进口代收押汇01007006   产品代码的时候使用01007006 子类型是3
		 */
		//业务编号 
		var ywbh="";
		//产品子类型
		var cpzlx="";
		//提示信息
		var str = "业务编号";
        if(productType=="01007001"){//出口信用证押汇
        	ywbh = nui.get("conDetail.yfh").getValue();
        	str = "议付号";
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0074","1",json);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007003"){//出口托收押汇
        	ywbh = nui.get("conDetail.tsh").getValue();
        	str = "托收号";
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0075","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007004"){//进口信用证押汇
        	ywbh = nui.get("conDetail.yfh").getValue();
        	str = "议付号";
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0076","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007005"){//进口T/T押汇
        	ywbh = nui.get("conDetail.ywbh").getValue();
        	cpzlx="2";
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0077","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007009"){//进口代付
        	ywbh = nui.get("conDetail.ywbh").getValue();
        	cpzlx = nui.get("conDetail.cplx").getValue();
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0078","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007010"){//提货担保
        	ywbh = nui.get("conDetail.fph").getValue();
        	str = "审核号码";
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0079","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007012"){//国际福费廷
        	ywbh = nui.get("conDetail.ywbh").getValue();
        	cpzlx = nui.get("conDetail.cplx").getValue();
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0080","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007011"){//国际信用证打包贷款
        	ywbh = nui.get("conDetail.xyzh").getValue();
        	cpzlx = nui.get("conDetail.tzlx").getValue();
        	str = "信用证号";
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0081","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        if(productType=="01007006"){//进口代收押汇
        	ywbh = nui.get("conDetail.ywbh").getValue();
        	cpzlx="3";
        	
        	var jsonCheck = {"ywbh":ywbh};
	   	    msg = exeRule("RCON_0082","1",jsonCheck);
	   	    if(null != msg && '' != msg){
		   		nui.alert(msg);
		   		git.unmask();
        		nui.get("con_contract_detail_info_save").setEnabled(true);
		   		return "1";
	   	    }
        }
        var json1 = {"productType":productType,"ywbh":ywbh,"amountDetailId":amountDetailId,"cpzlx":cpzlx,"contractId":contractId};
        $.ajax({
	            url: "com.bos.bizProductDetail.bizProductDetail.checkYwbh.biz.ext",
	            type: 'POST',
	            data: nui.encode(json1),
	            cache: false,
	            contentType:'text/json',
	            cache:false,
	            success:function(mydata){
	            	if(mydata.isValid=="0"){
        				nui.alert("["+str+"]校验失败:"+mydata.msg);
        				git.unmask();
        				nui.get("con_contract_detail_info_save").setEnabled(true);
        				return;
        			}else{
        				//编号校验成功---国结返回的信息需要和当期合同信息做比较 并且给出相应的提示
        				var amt = nui.get("tbConContractInfo.contractAmt").getValue();//合同金额
        				var bz = nui.get("tbConContractInfo.currencyCd").getValue();//合同币种 
        				var gjbz = mydata.bz;//国结返回的币种
        				if(gjbz==""||gjbz==null||gjbz=='undefinded'){
        					nui.alert("国结系统未返回当前业务所需币种信息，请到国结系统确认当前业务完整性");
        					git.unmask();
        					nui.get("con_contract_detail_info_save").setEnabled(true);
        					return;
        				}
        				var gjje = mydata.je;//国结返回的金额
        				if(gjje==""||gjje==null||gjje=='undefinded'){
        					nui.alert("国结系统未返回当前业务所需金额信息，请到国结系统确认当前业务完整性");
        					git.unmask();
        					nui.get("con_contract_detail_info_save").setEnabled(true);
        					return;
        				}
        				var gjfkqx = mydata.fkqx;//国结返回的放款期限
        				var gjyj = mydata.gjyj;//国结返回的意见
        				if(gjbz=="01"){
							gjbz="CNY";
						}else if(gjbz=="250"){//法国法郎
							gjbz="FRF";
						}else if(gjbz=="276"){//德国马克
							gjbz="DEM";
						}else if(gjbz=="13"){//港币
							gjbz="HKD";
						}else if(gjbz=="380"){//意大利里拉
							gjbz="ITL";
						}else if(gjbz=="27"){//日元
							gjbz="JPY";
						}else if(gjbz=="410"){//韩国元
							gjbz="KRW";
						}else if(gjbz=="81"){//澳门元
							gjbz="MOP";
						}else if(gjbz=="458"){//马来西亚币
							gjbz= "MYR";
						}else if(gjbz=="528"){//荷兰盾
							gjbz="NLG";
						}else if(gjbz=="554"){//新西兰元 
							gjbz="NZD";
						}else if(gjbz=="29"){//澳洲元
							gjbz="AUD";
						}else if(gjbz=="578"){//挪威克朗
							gjbz="NOK";
						}else if(gjbz=="608"){//菲律宾比索
							gjbz="PHP";
						}else if(gjbz=="643"){//卢布
							gjbz="RUB";
						}else if(gjbz=="32"){//新加坡元
							gjbz="SGD";
						}else if(gjbz=="724"){//西班牙比塞塔
							gjbz="ESP";
						}else if(gjbz=="752"){//瑞典克朗
							gjbz="SEK";
						}else if(gjbz=="15"){//瑞士法郎
							gjbz="CHF";
						}else if(gjbz=="764"){//泰国铢
							gjbz="THB";
						}else if(gjbz=="12"){//英镑
							gjbz="GBP";
						}else if(gjbz=="14"){//美元
							gjbz="USD";
						}else if(gjbz=="38"){//欧元
							gjbz="EUR";
						}else if(gjbz=="040"){//奥地利先令
							gjbz="ATS";
						}else if(gjbz=="999"){//其他
							gjbz="OTHER";
						}else if(gjbz=="056"){//比利时法郎
							gjbz="BEF";
						}else if(gjbz=="28"){//加拿大元
							gjbz="CAD";
						}else if(gjbz=="158"){//新台湾币
							gjbz="TWD";
						}else if(gjbz=="208"){//丹麦克朗
							gjbz="DKK";
						}else if(gjbz=="246"){//芬兰马克
							gjbz="FIM";
						}else{
							nui.alert("国结系统对当前业务定义的币种["+gjbz+"]找不到对应的信息，请检查！");
        					git.unmask();
        					nui.get("con_contract_detail_info_save").setEnabled(true);
        					return;
						}
        				if(bz!=gjbz){
        					nui.alert("当前合同币种["+bz+"]与国结系统对当前业务定义的币种["+gjbz+"]不匹配，请检查！");
        					git.unmask();
        					nui.get("con_contract_detail_info_save").setEnabled(true);
        					return;
        				}
        				if(amt!=gjje){
        					nui.alert("当前合同金额["+amt+"]与国结系统对当前业务定义的金额["+gjje+"]不匹配，请检查！");
        					git.unmask();
        					nui.get("con_contract_detail_info_save").setEnabled(true);
        					return;
        				}
        				saveConContractDetailInfo(json);
        			}
				}
			});
        }else{
        	saveConContractDetailInfo(json);
        }
	}
	function saveConContractDetailInfo(json){
   		$.ajax({
        url: "com.bos.conInfo.conContractInfo.saveConContractDetailInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
	        if(text.msg){
	        	nui.alert(text.msg);
	        }else{
	        	
	        	nui.alert("保存成功");
	        }
        	initPage();
        	nui.get("con_contract_detail_info_save").setEnabled(true);
			git.unmask();
        }});
	}
	function thirdType(e){
		if(nui.get("tbConAttachedInfo.cType")!=null){
			var thirdtype = nui.get("tbConAttachedInfo.cType").getValue();
			if(thirdtype==""){
				nui.get("tbConAttachedInfo.cHoldCount").setEnabled(false);
				nui.get("tbConAttachedInfo.cHoldCount").setRequired(false);
				nui.get("tbConAttachedInfo.cHoldCount").setValue('');
				form.validate();
			}else{
				nui.get("tbConAttachedInfo.cHoldCount").setEnabled(true);
				nui.get("tbConAttachedInfo.cHoldCount").setRequired(true);
				form.validate();
			}
		}
	}
	//提前还款是否收取违约金
	function onselectPrepaymentPenalty(){
		if(nui.get("conDetail.prepaymentPenalty").getValue()=="1"){
			$("#prepayMakeupRate").css("display","block");
			nui.get("conDetail.wybcbl").show();
		}else{
			$("#prepayMakeupRate").css("display","none");
			nui.get("conDetail.wybcbl").hide();
			nui.get("conDetail.wybcbl").setValue();
		}
	}
	//提前还款是否收取违约金--流贷
	function onselectPrepaymentPenalty1(){
		if(nui.get("conDetail.prepaymentPenalty").getValue()=="1"){
			$("#prepayMakeupRate").css("display","block");
			nui.get("conDetail.prepayMakeupRate").show();
		}else{
			$("#prepayMakeupRate").css("display","none");
			nui.get("conDetail.prepayMakeupRate").hide();
			nui.get("conDetail.prepayMakeupRate").setValue();
		}
	}
	
	//流动资金、固定资产贷款军民融合属性
	if('01001001'==productType||'01003007'==productType||'01001040'==productType
	 ||'01001041'==productType||'01001042'==productType){
		var val = nui.get("conDetail.sfjmrhsx").getValue();
		if(''==val||'0'==val){
			$("#jmrhsx").css("display","none");
 			nui.get("conDetail.jmrhsx").hide();	
		}
		if('1'==val){
			$("#jmrhsx").css("display","block");
 			nui.get("conDetail.jmrhsx").show();
		}
	}
</script>
</body>
<style>
	.mini-grid-rows-view {height:auto}
</style>
</html>