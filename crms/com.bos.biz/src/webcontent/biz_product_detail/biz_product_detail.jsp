<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>业务明细页面</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<% 
  String pAddress ="/"+(String)request.getAttribute("jspName")+"?stepFlag=biz";
  System.out.println("pAddress=========="+pAddress);
  System.out.println("ecifPartyNum=========="+request.getAttribute("ecifPartyNum"));
 %>
 <div id="panel1" class="nui-panel" title="业务申请品种明细"
	style="width:99.5%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="amountDetail.amountDetailId" class="nui-hidden nui-form-input" name="amountDetail.amountDetailId"/>
	<input id="amountDetail.amountId" class="nui-hidden nui-form-input" name="amountDetail.amountId"/>
	<input id="amountDetail.oldDetailId" class="nui-hidden nui-form-input" name="amountDetail.oldDetailId"/>
	<input id="productDetail.applyDetailId" class="nui-hidden nui-form-input" name="productDetail.applyDetailId"/>
	<input id="productDetail.wtxmId" class="nui-hidden nui-form-input" name="productDetail.wtxmId"/>
	<input id="tbBizProductInfo.entityName" class="nui-hidden nui-form-input" name="tbBizProductInfo.entityName"/>
	<input id="tbBizProductInfo.productCd" class="nui-hidden nui-form-input" name="tbBizProductInfo.productCd"/>
		<div class="nui-dynpanel" columns="1"  id="base">
			<fieldset>
				<legend>
					<span>基本信息</span>
				</legend>
				<div class="nui-dynpanel" columns="4" id="base1"> 
					<label>业务品种：</label>
					<input id="amountDetail.productType" name="amountDetail.productType" dictTypeId="product" class="nui-text nui-form-input" />
					
					<label>币种：</label>
					<input name="amountDetail.currencyCd" id="currencyCd" required="true" data="data" valueField="dictID"  onvaluechanged="bzchange"
					class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" enabled="false" emptyText="--请选择--" />
				
					<label>申请金额：</label>
					<input id="amountDetail.detailAmt" name="amountDetail.detailAmt"  vtype="float;maxLength:20;range:100,100000000000" required="true" class="nui-textbox nui-form-input" dataType="currency"  onblur="calcuMoney" />
					
					<label id="hl">汇率：</label>
					<input id="amountDetail.exchangeRate" name="amountDetail.exchangeRate" required="true" vtype="float;maxLength:10" class="nui-text nui-form-input" />
			
					<label id="zsrmbje">折算人民币金额：</label>
					<input id="amountDetail.rmbAmt" name="amountDetail.rmbAmt" vtype="float;maxLength:20" required="true" class="nui-text nui-form-input" dataType="currency"/>
					
					<!-- <label id="sqqx">期限(月)：</label>
					<input name="amountDetail.term" id="amountDetail.term"  required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:6"/>
					
					-->
					<label id="sqqx">申请期限：</label>
					<div style="width:80%">
						<input name="amountDetail.creditTerm" id="amountDetail.creditTerm" style="width:60%;float:left" required="true" class="nui-textbox nui-form-input" vtype="int;range:0,95000" onblur="getAppEndate"/>
						<input name="amountDetail.cycleUnit" id="amountDetail.cycleUnit" style="width:40%;float:left" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6009" enabled="false"/>
					</div>
					
					<label id="hkfs">还款方式：</label>
					<input id="amountDetail.repaymentType" name="amountDetail.repaymentType" required="true" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1162" emptyText="--请选择--" onvaluechanged="conRpTpChg"/>
					
					<label id="schkqc">首次还本期次：</label>
					<input id="amountDetail.firstRepayTerm" name="amountDetail.firstRepayTerm" required="true" data="data" class="nui-textbox nui-form-input" vtype="int;maxLength:4;range:2,10000"/>
					
					<!-- <label id="tqhkwyj">提前还款是否收取违约金：</label>
					<input id="amountDetail.prepaymentPenalty" name="amountDetail.prepaymentPenalty" required="true"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="--请选择--"/>
					 -->
					<label id="hkly">还款来源：</label>
					<input id="amountDetail.payment" name="amountDetail.payment" required="true" data="data" class="nui-textarea nui-form-input" vtype="maxLength:290"/>
					
					<label id="loanuse">贷款用途：</label>
					<input name="amountDetail.loanUse" id="amountDetail.loanUse" required="true" data="data" class="nui-textarea nui-form-input" vtype="maxLength:190"/>
					
					<label id="edxhbz">额度循环标志：</label>
					<input id="amountDetail.cycleInd" name="amountDetail.cycleInd" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
					
					<label id="htxhbz">合同循环标志：</label>
					<input name="amountDetail.cycleIndCon" id="amountDetail.cycleIndCon" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" onvaluechanged="conCycleChg"/>
				</div>
				<div id="jmsx" class="nui-dynpanel" columns="4">
					<label id="sfjmrhsx">是否军民融合属性：</label>
					<input name="productDetail.sfjmrhsx" id="productDetail.sfjmrhsx" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" onvaluechanged="jmchange"/>
				
					<label id="jmrhsx">军民融合属性：</label>
					<input id="productDetail.jmrhsx" name="productDetail.jmrhsx" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_JMRHSX001" emptyText="--请选择--"/>
				</div>
				<div class="nui-dynpanel" columns="1" id="base2">
				  	<jsp:include page="<%=pAddress %>"/>
				</div>						
				<div id="sqrq" class="nui-dynpanel" columns="4">
					<label>申请日期：</label>
					<input name="amountDetail.applyDate" id="amountDetail.applyDate"  class="nui-text nui-form-input"/>
				</div>			
			</fieldset>
		</div>
		<div columns="1" id="table1">
			<fieldset>
				<legend>
					<span>利率信息</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_public_rate.jsp"%>
			</fieldset>
		</div>
		<div columns="1" id="dkll">
			<fieldset>
				<legend>
					<span>垫款利率信息</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_dkll_rate.jsp"%>
			</fieldset>
		</div>
		<div columns="1"  id="project">
			<fieldset>
				<legend>
					<span>项目信息</span>
				</legend>
				<%@include file="/biz/biz_product_detail/biz_project.jsp"%>
			</fieldset>
		</div>
		<div columns="1"  id="piaoju">
              <%@include file="/biz/biz_product_detail/pjxx/biz_pj.jsp"%> 
		</div>
		<div columns="1"  id="tiexian">
              <%@include file="/biz/biz_product_detail/pjxx/biz_tx.jsp"%> 
		</div>
		<div columns="1" id="wy">
				<%@include file="/biz/biz_product_detail/wyxx/biz_wy.jsp"%>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
		    <a class="nui-button" id="" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>
	</div>
 </div>

	
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var amountDetailId = "<%=request.getAttribute("amountDetailId") %>";//额度明细ID
	var bizType = "<%=request.getAttribute("bizType") %>";//01-单笔  02-综合授信
	var proFlag = "<%=request.getAttribute("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var productType = "<%=request.getAttribute("productType") %>";
	var bizHappenType = "<%=request.getAttribute("bizHappenType") %>";//04-调整  06-借新还旧
	var contStep = "<%=request.getAttribute("contStep") %>";// 是否处于合同阶段 用于票据在合同阶段添加明细
	var applyDate = '';
	var edithp = "<%=request.getParameter("edithp") %>";//从综合授信的明细信息跳转 编辑汇票
	var productDepartment;
	var partyId = "<%=request.getParameter("partyId") %>";
	var creditLevela = '';//客户信用等级
	$("#piaoju").css("display","none"); 
 	$("#tiexian").css("display","none"); 
 	
	//只有一般流动资金贷款、一般固定资产贷款有军民融合属性
 	if('01001001'==productType||'01003007'==productType||'01001040'==productType
 	 ||'01001041'==productType||'01001042'==productType){
 		var val = nui.get("productDetail.sfjmrhsx").getValue();
 		if('1'==val){
 			$("#sfjmrhsx").css("display","block");
 			nui.get("productDetail.sfjmrhsx").show();//是否军民融合属性
 			$("#jmrhsx").css("display","block");
 			nui.get("productDetail.jmrhsx").show();
 		}else{
	 		$("#sfjmrhsx").css("display","block");
	 		nui.get("productDetail.sfjmrhsx").show();//是否军民融合属性
 			$("#jmrhsx").css("display","none");
 			nui.get("productDetail.jmrhsx").hide();
 		}
 	}else{
 		$("#sfjmrhsx").css("display","none");
 		nui.get("productDetail.sfjmrhsx").hide();//是否军民融合属性
 		$("#jmrhsx").css("display","none");
 		nui.get("productDetail.jmrhsx").hide();
 	}
 	//是否军民融合属性发生改变时，军民融合属性发生变化
 	function jmchange(){
 		var val = nui.get("productDetail.sfjmrhsx").getValue();
 		if('1'==val){
 			$("#jmrhsx").css("display","block");
 			nui.get("productDetail.jmrhsx").show();
 			if('01001001'==productType||'01001040'==productType||'01001041'==productType||'01001042'==productType){
				nui.get("productDetail.jmrhsx").setData(getDictData('XD_JMRHSX001','str','01,02,03,04')); 			
 			}
 			if('01003007'==productType){
 				nui.get("productDetail.jmrhsx").setData(getDictData('XD_JMRHSX001','str','05,06'));
 			}
 			nui.get("base1").refreshTable();
 		}if('0'==val||''==val){
 			$("#jmrhsx").css("display","none");
 			nui.get("productDetail.jmrhsx").hide();
 		}
 	}
 	
 	// 票据
 	if((productType=='01008001' || productType=='01008002' || productType=='01008010')&& contStep=='1'){
/*  		$("#edxhbz").css("display","block");
		  nui.get("amountDetail.cycleInd").show(); */
		$("#base").css("display","none"); 
 	 	$("#piaoju").css("display","block"); 
 	 	var jsonpj = nui.decode({"amountDetailId":amountDetailId});
		var gridpj = nui.get("gridpj");
		gridpj.load(jsonpj);
		if(proFlag!='1'){
			$("#pj_add").css("display","none");
			$("#pj_edit").css("display","none");
			$("#pj_remove").css("display","none");
			$("#pj_add_dianpiao").css("display","none");
		}
 	}
 	//结息周期 只取月季(取消年)
 	nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
 	   //贴现
 	if((productType=='01006001' || productType=='01006002'
 	|| productType=='01006010' //村镇银行贴现产品
 	)&& contStep=='1'){
 		$("#base").css("display","none"); 
 		$("#table1").css("display","none");
 		
 	 	$("#tiexian").css("display","block"); 
 	 	$("#hkfs").css("display","none");
	    nui.get("#amountDetail.repaymentType").hide();
		var jsontx = nui.decode({"amountDetailId":amountDetailId});
		var gridtx = nui.get("gridtx");
		gridtx.load(jsontx);
		if(proFlag!='1'){
			$("#pj_add").css("display","none");
			$("#pj_edit").css("display","none");
			$("#pj_remove").css("display","none");
			$("#pj_add_dianpiao").css("display","none");
		}
 	}
	//汇率
	function bzchange(){
		var bz = nui.get("currencyCd").getValue();
		if(bz=='CNY'||bz==''||bz==null){
			$("#hl").css("display","none");
			$("#zsrmbje").css("display","none");
			nui.get("amountDetail.exchangeRate").hide();
			nui.get("amountDetail.rmbAmt").hide();
		}
		nui.get("base1").refreshTable();
	}
	//汇票贴现不需要贷款用途,却要还款方式/期限单位为天
	if(productType=='01006001'||productType=='01006002'
	|| productType=='01006010' //村镇银行贴现产品
	){
		$("#loanuse").css("display","none");
		$("#tqhkwyj").css("display","none");
		$("#hkly").css("display","none");
		nui.get("amountDetail.loanUse").hide();
		//nui.get("amountDetail.prepaymentPenalty").hide();
		nui.get("amountDetail.payment").hide();
		if(productType=='01006001'
		|| productType=='01006010' //村镇银行贴现产品
		){
			nui.get("productDetail.wylx").setData(getDictData("XD_SXYW0203","str","0"));
		}else{
			nui.get("productDetail.wylx").setData(getDictData("XD_SXYW0203","str","1"));
		}
	}
	//只有流贷有合同循环标志,add 20150823 银承也要
	if(productType=='01001001'||productType=='01001002'||productType=='01001003'
		||productType=='01001005' ||productType=='01001006' ||productType=='01008001' ||productType=='01008002' 
		||productType=='01001007' ||productType=='01001008' ||productType=='01001009' ||productType=='01001010' ||productType=='01001011'||productType=='01001012' 
		||productType=='01001013' ||productType=='01001014' ||productType=='01001015' ||productType=='01001016' ||productType=='01001017' 
		||productType=='01001018' ||productType=='01001019' ||productType=='01001020' ||productType=='01001021' ||productType=='01001022' 
		||productType=='01001023' ||productType=='01001024' ||productType=='01001025' ||productType=='01001026' ||productType=='01001027'
		||productType=='01001028' ||productType=='01001029' ||productType=='01001030' ||productType=='01001031' 
		||productType=='01008010' ||productType=='01001050' ||productType=='01001051' ||productType=='01001052' ||productType=='01001053' ||productType=='01001054'//村镇银行流动资金产品
		||productType=='01001040' ||productType=='01001041' ||productType=='01001042'
		){
		//有合同是否循环结息周期只能选4种
		//nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2,5,6'));
	}else{
		$("#htxhbz").css("display","none");
		nui.get("amountDetail.cycleIndCon").hide();
	}
	
	//垫款利率
	$("#dkll").css("display","none");
	if(productType=='01008001'||productType=='01008002'||productType=='01009001'||productType=='01009002' ||productType=='01009010' ||productType=='01010001'
	    ||productType=='01006001'||productType=='01006002'||productType=='01004001'
	    ||productType=='01008010' ||productType=='01006010' //村镇银行贴现产品
	    ||productType=='01007009'||productType=='01007012'||productType=='01007010'||productType=='01007013'
	    ||productType=='01007014'){
		$("#dkll").css("display","block");
	}
	//利率信息
	if(productType=='01007010'||productType=='01007013'||productType=='01007014'){
		$("#table1").css("display","none");
	}
	// 合同阶段的利率信息不能显示
	if((productType=='01006001'||productType=='01006002'||productType=='01008001'||productType=='01008002'
	||productType=='01008010' || productType=='01006010' //村镇银行贴现产品
	)&& contStep=='1'){
		$("#dkll").css("display","none");
	}
	
	//还款信息
	if(productType=='01008001' ||productType=='01008010' ||productType=='01008002'||productType=='01009001'||productType=='01009002' ||productType=='01009010' ||productType=='01010001'
	    ||productType=='01011001'||productType=='01012001'
		||productType=='01007013'||productType=='01007014'||productType=='01007010'){
		$("#hkfs").css("display","none");
		$("#schkqc").css("display","none");
		$("#tqhkwyj").css("display","none");
		nui.get("amountDetail.repaymentType").hide();
		nui.get("amountDetail.firstRepayTerm").hide();
		//nui.get("amountDetail.prepaymentPenalty").hide();
	}
	//还款来源
	if(productType=='01007008'){
		$("#hkly").css("display","none");
		nui.get("amountDetail.payment").hide();
	}
	
	//物业信息
	$("#wy").css("display","none");
	if(productType=='01001005'||productType=='01003005'){
		$("#wy").css("display","block");
		var jsonwy = nui.decode({"amountDetailId":amountDetailId});
		var gridwy = nui.get("gridwy");
		gridwy.load(jsonwy);
		if("1" != proFlag){
			$("#wy_add").css("display","none");
			$("#wy_edit").css("display","none");
			$("#wy_remove").css("display","none");
		}
	}
	
	//项目
	$("#project").css("display","none");
	//新增固定贷款项目
	if(productType=='01002001'||productType=='01002002'||productType=='01002003'||productType=='01003001'||productType=='01003002'
	||productType=='01003003'||productType=='01003004'||productType=='01003006'||productType=='01005001'||productType=='01003005'
	||productType=='01003007'||productType=='01003011'||productType=='01003012'||productType=='01003013'||productType=='01003015'||productType=='01003016'
	||productType=='01003050'//村镇银行固定资产产品
	){
		$("#project").css("display","block");
		var json = nui.decode({"amountDetailId":amountDetailId});
		var gridxm = nui.get("gridxm");
		gridxm.load(json);
		if(proFlag!='1'){
			$("#view_add").css("display","none");
			$("#view_edit").css("display","none");
			$("#view_remove").css("display","none");
			$("#view_addProject").css("display","none");
		}
	}
	//还款方式（贴现没有还款方式）
	if(productType=='01006001'||productType=='01006002' 
	|| productType=='01006010' //村镇银行贴现产品
	){
		$("#hkfs").css("display","none");
		nui.get("amountDetail.repaymentType").hide();
	}else if(productType=='01007009'||productType=='01007012'||productType=='01007014'||
	         productType=='01007013'||productType=='01007010'){//国结：等额本息、等额本金、阶段性等额本金、阶段性等额本息---不要 
		nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','1100,1400'));//去掉了1700等本等息
	}else if(productType=='01007001'||productType=='01007003'||productType=='01007004'||
			 productType=='01007006'||productType=='01007005'||productType=='01007011'){//以下国结产品增加到期一次性还本付息
		nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','1100,1200,1400'));	 
	}else{
		nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','0100,0200,1100,1400'));
	}
	//获取评级结果
	function getRatingLevel(partyId,productType){
	    var json = nui.encode({"partyId":partyId,"productType":productType});
	    $.ajax({
	            url: "com.bos.bizInfo.bizInfo.getPartyCreditLevel.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	              //防止更改贷种后取不到评级结果却仍然显示以前取出的评级
	              creditLevela = text.tbIrmInternalRatingApply.generalAdjustRatingCd;
	              //页面初始化
	              initPage();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            	initPage();
	                nui.alert(jqXHR.responseText);
	            }
	        });
	}	
	getRatingLevel(partyId,productType);
	function initPage(){
		//传入partyId和productType 获取客户信用等级
		//getRatingLevel(partyId,productType);
        var json = nui.encode({"amountDetailId":"<%=request.getAttribute("amountDetailId")%>"});
		$.ajax({
            url: "com.bos.bizProductDetail.bizProductDetail.getProductDetail.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	git.initParamOfProduct(productType,'1');//业务类型：1业务申请，2合同签订，3出账放款
            	form.setData(o);
            	git.setPtDefaultValue();
            	applyDate = o.amountDetail.applyDate;
				//初始化汇率
				if(o.amountDetail.exchangeRate==''||o.amountDetail.exchangeRate==null){
					nui.get("amountDetail.exchangeRate").setValue("1");
				}
				//利率信息
				if(productType=='01008001' ||productType=='01008010' ||productType=='01008002'||productType=='01009001'||productType=='01009002' ||productType=='01009010' ||
				   productType=='01010001'||productType=='01011001'||productType=='01012001'||
				   productType=='01014001'
					){
					$("#table1").css("display","none");
					//借新还旧的贷款用途直接反显“借新还旧”
					if(bizHappenType == '06'){
						var loanuse = nui.get("amountDetail.loanUse").getValue();
						if(null == loanuse || ''==loanuse){
							nui.get("amountDetail.loanUse").setValue("借新还旧");
						}
					}
				}else if(productType == '01006001'||productType == '01006002'
					|| productType=='01006010' //村镇银行贴现产品
				){//只有申请利率
					nui.get("amountDetail.repaymentType").setValue("21");
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
					
					$("#ovardueRate").css("display","none");
					nui.get("loanrate.overdueRateUpProportion").hide();
					nui.get("loanrate.overdueRateUpProportion").setValue('');
					
					$("#yearRate").css("display","block");
					nui.get("loanrate.yearRate").show();
									
					//贴现产品 的是否协议付息默认为'否'，不可修改
					//贴现产品 的垫款率利率默认为万分之5，不可修改
					nui.get("productDetail.sfxyfx").setValue("0");
					nui.get("productDetail.sfxyfx").setEnabled(false);
					nui.get("productDetail.dkll").setValue(5);
					nui.get("productDetail.dkll").setEnabled(false);
					
					nui.get('tableloanrate').refreshTable();
				}else{
					//借新还旧的贷款用途直接反显“借新还旧”
					if(bizHappenType == '06'){
						var loanuse = nui.get("amountDetail.loanUse").getValue();
						if(null == loanuse || ''==loanuse){
							nui.get("amountDetail.loanUse").setValue("借新还旧");
						}
					}
					//宽限期方式默认为否不可选
					nui.get("loanrate.gracePeriodType").setValue('0');
					nui.get("loanrate.overdueRateUpProportion").setEnabled(true);
					//需求531 委贷罚息可手输
					if(productType == '01013001' || productType == '01013010'
					   ||productType=='01007001'||productType=='01007003'||productType=='01007004'||productType=='01007009'
					   ||productType=='01007011'||productType=='01007012'||productType=='01007005'||productType=='01007006'
					   ||productType=='01007013'||productType=='01007014'||productType=='01007010'){
					}else if(productType == '01004001'){
						nui.get("loanrate.overdueRateUpProportion").setVtype("");
					}else{
						nui.get("loanrate.overdueRateUpProportion").setValue('50');
						nui.get("loanrate.overdueRateUpProportion").setEnabled(false);
						nui.get("loanrate.overdueRateUpProportion").validate();
					}
				}
				if(bizType=='01'){//单笔额度循环标志不显示
					$("#edxhbz").css("display","none");
					nui.get("amountDetail.cycleInd").hide();
					//单笔调整时金额不让动
					if(bizHappenType=='04'){
						nui.get("amountDetail.detailAmt").setEnabled(false);
					}
				}else{//综合授信不展示申请日期及额度循环标志
					//$("#sqrq").css("display","none");
					/* ---二期根据参数控制
					//额度循环标志
					if(productType=='01002001'||productType=='01002002'||productType=='01002003'||productType=='01003001'||productType=='01003002'
						 ||productType=='01003003'||productType=='01003004'||productType=='01003005'||productType=='01003006'||productType=='01003007'
  						||productType=='01003009'||productType=='01003011'||productType=='01003012'||productType=='01003013'||productType=='01003015'||productType=='01003016'
						||productType=='01006001'||productType=='01011001'||productType=='01012001'||productType=='01013001'||productType=='010009001'
						||productType=='01009002' ||productType=='01009010' ||productType=='01007008'){
							nui.get("amountDetail.cycleInd").setValue("0");
							nui.get("amountDetail.cycleInd").setEnabled(false);
					} */
					//截取产品代码的前5位
					var productStr = productType.substring(0,5);
					//产品部门
					var departMent = mydata.productInfo.productDepartment;
					if(productStr.indexOf("01003")!=-1){//固定类资产贷款的额度循环标志默认为"否"
						nui.get("amountDetail.cycleInd").setValue("0");
						nui.get("amountDetail.cycleInd").setEnabled(false);
					}
					//公司类产品 额度循环标志 流动资金贷款默认为“是”，银行承兑汇票默认为“是”
					if(departMent == "1"){
						if(productStr.indexOf("01001")!=-1 || productStr.indexOf("01008")!=-1){
							if(nui.get("amountDetail.cycleInd").getValue() == "" ||nui.get("amountDetail.cycleInd").getValue() == null){
								nui.get("amountDetail.cycleInd").setValue("1");
							}
						}
					}
				}
				conRpTpChg();
				//委托贷款查询客户
				if(productType=='01013001' || productType=='01013010'){
					if(o.productDetail.wtxmId != null && o.productDetail.wtxmId!=''){
						var json=nui.encode({"wtxmId":o.productDetail.wtxmId});
						$.ajax({
				            url: "com.bos.bizInfo.person.queryWtrXx.biz.ext",
				            type: 'POST',
				            data: json,
				            cache: false,
				            async: false,
				            contentType:'text/json',
				            success: function (text) {
				          		nui.get("productDetail.wtr").setValue(text.wtxm.PARTY_ID);
				          		nui.get("productDetail.wtr").setText(text.wtxm.PARTY_NAME);
				          		nui.get("wtxmmc1").setValue(text.wtxm.ENTRUST_PROJECT_NAME);
					        	nui.get("zjlx").setValue(text.wtxm.CERT_TYPE);
					        	nui.get("zjhm").setValue(text.wtxm.CERT_NUM);
					        	nui.get("zjlx").validate();
					        	nui.get("zjhm").validate();
				            }
			           })
					}
				}
				//从综合授信的明细信息跳转过的编辑页面 只能修改汇票张数(仅限汇票产品)
				if(edithp != null && edithp == '1'){
					nui.get("amountDetail.detailAmt").setEnabled(false);
					nui.get("productDetail.bzjblbdy").setEnabled(false);
					nui.get("amountDetail.cycleInd").setEnabled(false);
					nui.get("amountDetail.cycleIndCon").setEnabled(false);
					nui.get("productDetail.dkll").setEnabled(false);
				}
				nui.get("amountDetail.cycleUnit").setValue("04");//页面初始化时 期限申请单位默认为月
				//nui.get("form").refreshTable();
				nui.get("base2").refreshTable();
				
				//综合授信申请时，银承不显示汇票张数字段
				if(bizType == "02"){
					if(productType == "01008001" || productType == "01008010"){//银行承兑汇票 富民银行承兑汇票
						if(nui.get("productDetail.hpzs") != null){
							$("#ychpzs").css("display","none");
							nui.get("productDetail.hpzs").hide();
						}
					}
				}
				
				//产品部门
				productDepartment = mydata.productInfo.productDepartment;
				//申请页面有合同循环标志字段的产品(indexOf!=-1) 只有流贷和银承有合同循环标志
				if(productType.indexOf("010010")!=-1 || productType.indexOf("010080")!=-1){
					//在流贷 小微类(部门为2)产品除心意贷 的合同循环标志默认为否，不可修改
					if(mydata.productInfo.productDepartment == "2" && productType!="01001029"){
						nui.get("amountDetail.cycleIndCon").setValue("0");
						nui.get("amountDetail.cycleIndCon").setEnabled(false);
					}
					//心意贷合同循环标志锁死为是 与其它条件无关
					if(productType=="01001029"){
						nui.get("amountDetail.cycleIndCon").setValue("1");
						nui.get("amountDetail.cycleIndCon").setEnabled(false);
					}
					//低风险 合同循环标志锁死为否
					if(bizType == "01"){//低风险的产品
						if(productType=="01001001" ||productType=="01001007" ||productType=="01008001"
						   ||productType=="01008010"){
							nui.get("amountDetail.cycleIndCon").setValue("0");
							nui.get("amountDetail.cycleIndCon").setEnabled(false);
						}
					}

					//在综合授信时 信用等级AA(不含)以下的客户 合同循环标志默认为'否'
					if(bizType == "02" && productDepartment == "1"){//综合授信 公司类产品
						//所有客户默认为否
						if(nui.get("amountDetail.cycleIndCon").getValue()==""||nui.get("amountDetail.cycleIndCon").getValue==null){
							nui.get("amountDetail.cycleIndCon").setValue("0");
						}
						//AA等级以下的锁死为否
						if(creditLevela!="AAA+"&&creditLevela!="AAA-"&&creditLevela!="AAA"
						   &&creditLevela!="AA+"&&creditLevela!="AA"){
							nui.get("amountDetail.cycleIndCon").setValue("0");
							nui.get("amountDetail.cycleIndCon").setEnabled(false);
						}
					}
				}
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
        var proFlag ="<%=request.getParameter("proFlag") %>";
		if(proFlag=="0"){
			nui.get("btnCreate").hide();
			form.setEnabled(false);
		}
		getAppEndate();
		initUseAmt();//如果是纳入到综合授信里的单笔单批业务 则直接返显单笔业务的申请信息 不可能修改
	}
	getRatingLevel(partyId,productType);
	var appEndate="";//申报止期
	//通过申请起始日期和期限计算申报止期
	function getAppEndate(){
		var beginDate = nui.get("amountDetail.applyDate").getValue();//申请的起始日期
		var term = nui.get("amountDetail.creditTerm").getValue();//申请的期限
		if(beginDate != null && term != null){
			var date = beginDate.substring(0,10);//截取起始日期前十位
			var json = nui.encode({"qxdw":"M","qx":term,"rq":date});//默认为月
			$.ajax({
				url : "com.bos.pub.dateCountUtil.monthAddDate.biz.ext",
				data : json,
				type : "POST",
				cache : false,
				contentType : "text/json",
				success : function(data){
					//nui.get("amountDetail.endDate").setValue(data.dqrq);
					appEndate = data.dqrq;
				}
			});
		}
	}
	
	//initUseAmt();
	
	//综合授信时 给纳入进来的单笔业务 返现金额
	function initUseAmt(){
		var o = form.getData();//oldDetailId只有纳入进来的单笔单批有值 综合授信调整也有值
		if(null != o.amountDetail.oldDetailId && "" != o.amountDetail.oldDetailId && bizType=="02"){
			o.amountDetail.detailAmt = "0";
			var json = nui.encode(o);
			$.ajax({
	            url: "com.bos.bizProductDetail.bizProductDetail.checkAmountDetailAmt.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	            	var rs = nui.decode(mydata);
					if("0" != rs.msg){//综合授信单笔 在编辑时 重新取值
						if(bizHappenType =="01"){//综合授信正常 纳入的单笔单批不能修改
							if(proFlag == "1"){//如果是小微(综合授信纳入的单笔单批)则不能修改金额
								nui.get("amountDetail.detailAmt").setValue(rs.useAmt1-rs.returnAmt);//合同金额-借据已归还金额 
								form.setEnabled(false);
							}
						}if(bizHappenType =="04"){//综合授信调整 
							if(productDepartment == "2" && proFlag == "1"){
								nui.get("amountDetail.detailAmt").setValue(rs.useAmt1-rs.returnAmt);//合同金额-借据已归还金额 
								form.setEnabled(false);
							}
						}
					}
				}
	        });
        }
	}
	
	function create(){

		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
		
        nui.get("btnCreate").setEnabled(false);
        //利率默认按比例浮动
        nui.get("loanrate.floatType").setValue("0");
       //房地产贷款必须有项目
       if(productType=='01002001'||productType=='01002002'||productType=='01002003'||productType=='01003001'||productType=='01003002'){
       		var xmxxcount = nui.get("gridxm").getData().length;
       		if(xmxxcount==0){
       			nui.alert("未添加项目信息!");
				nui.get("btnCreate").setEnabled(true);
				return;
       		}
       }
        var o = form.getData();
        //申报止期
        o.amountDetail.endDate = appEndate;
        //利率
		var rateType = o.loanrate.rateType;
		if(rateType=='1'){//固定
			o.loanrate.floatWay = '';
			o.loanrate.rateFloatProportion = '';
		}else if(rateType=='2'){//浮动
			o.loanrate.yearRate='';
		}
		//宽限期
		if(o.loanrate.gracePeriodType!=null){
			var  gracePeriodType = o.loanrate.gracePeriodType;
			if(gracePeriodType!='2'){
				o.loanrate.gracePeriodDay = '';
			}
			if(gracePeriodType=='0'){
				o.loanrate.graceCountIntFlag = '';
			}
		}
		//节假日
		if(o.loanrate.holidayFlg!=null){
			var  holidayFlg = o.loanrate.holidayFlg;
			if(holidayFlg=='0'){
				o.loanrate.holidayIntFlg = '';
			}
			var  gracePeriodType = o.loanrate.gracePeriodType;
			if(holidayFlg!='' && holidayFlg!=null){
				if(holidayFlg !='0'  && gracePeriodType!='0'){
					nui.alert("节假日与宽限期只能使用一种!");
					nui.get("btnCreate").setEnabled(true);
					return;
				}
			}
		}
		//汇票张数
		if(productType=='01006001'||productType=='01006002' 
			|| productType=='01006010' //村镇银行贴现产品
		){
			var sffx = o.productDetail.sfxyfx;
			if(sffx!='1'){
				o.productDetail.mfcnblBuy = '';
				o.productDetail.mfcnblSell = '';
			}
		}
        //14还款方式不支持合同循环
        var repayType = o.amountDetail.repaymentType;
        var cycleIndCon = o.amountDetail.cycleIndCon;
        if(cycleIndCon!=null&&cycleIndCon!=''&&repayType!=null&& repayType!=''){
        	if(repayType=='1400'||repayType=='1410'){
        		if(cycleIndCon=='1'){
        			nui.alert("所选还款方式不允许合同循环!");
					nui.get("btnCreate").setEnabled(true);
					return;
        		}
        	}
        }
        //如果有合同循环标志，单笔额度循环标志与合同循环标志一样
        if(cycleIndCon!=null&&cycleIndCon!=''){
        	if('01'==bizType){
        		o.amountDetail.cycleInd = o.amountDetail.cycleIndCon;
        	}
        }
        //如果rbm 折合人民币金额隐藏后取不到值
        if(o.amountDetail.rmbAmt==null || ''==o.amountDetail.rmbAmt){
        	o.amountDetail.rmbAmt = o.amountDetail.detailAmt;
        	o.amountDetail.exchangeRate = 1;//隐藏的都是rmb
        }
        form.loading("正在保存数据...");
        var json = nui.encode(o);
        //如果是被包含的分项，分享额度不能小于合同已用额度 借新还旧不校验这个申请金额
        if(null != o.amountDetail.oldDetailId && "" != o.amountDetail.oldDetailId && "06" != bizHappenType){
			$.ajax({
	            url: "com.bos.bizProductDetail.bizProductDetail.checkAmountDetailAmt.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	            	var rs = nui.decode(mydata);
	            	//对综合授信纳入进来的单笔单批业务 
	            	//现在不需要再校验其金额 直接返显 合同金额(借据金额-借据余额)
					if("0" != rs.msg){
						//alert(rs.msg);
						alert("明细金额不得小于该产品项下合同占用金额之和("+(rs.useAmt1-rs.returnAmt)+")");
						form.unmask();
						nui.get("btnCreate").setEnabled(true);
						return;
					}else{
						$.ajax({
				            url: "com.bos.bizProductDetail.bizProductDetail.saveProductDetail.biz.ext",
				            type: 'POST',
				            data: json,
				            cache: false,
				            contentType:'text/json',
				            success: function (mydata) {
				            	form.unmask();
				            	nui.get("btnCreate").setEnabled(true);
				            	var o = nui.decode(mydata);
								CloseWindow('ok');
							}
						});
					}
				}
	        });
        }else{
			$.ajax({
	            url: "com.bos.bizProductDetail.bizProductDetail.saveProductDetail.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	            	form.unmask();
	            	nui.get("btnCreate").setEnabled(true);
	            	var o = nui.decode(mydata);
					CloseWindow('ok');
				}
			});
        }
	}
	
	
	//动态列表点击新增
	function addfp(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"periodsNumber":++count};
        nui.get(gr).addRow(row,0);
    }
    //动态列表删除操作
    function removefp(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	//删除数据库数据
            	if(row.fpId){
            		var json = nui.encode({"fpId":row.fpId});
	            	$.ajax({
			            url: "com.bos.bizInfo.bizInfo.delFp.biz.ext",
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
    
    //动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"periodsNumber":++count};
        nui.get(gr).addRow(row,0);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.repayPlanId){
            		var json = nui.encode({"repayPlanId":row.applyDetailId,"deleteType":"5"});
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
    //计算折合人民币金额
    function calcuMoney(){
    	var detailAmt =  nui.get("amountDetail.detailAmt").getValue();
    	var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{2})?$/;
    	if(detailAmt!='' && detailAmt!=null){
    		if(!mny.test(detailAmt)){
				nui.alert("金额格式有误！");
				nui.get("amountDetail.detailAmt").setValue('');
				return;
			}
    	}
    	var exchangeRate = nui.get("amountDetail.exchangeRate").getValue();
    	nui.get("amountDetail.rmbAmt").setValue(parseFloat(detailAmt)*parseFloat(exchangeRate));
    	nui.get("amountDetail.rmbAmt").validate();
 	}
 	//汇票贴现中付息标志改变触发事件
 	function sffx(){
		var sffx = nui.get("productDetail.sfxyfx").getValue();
		if(sffx=='1'){
			//$("#sq1").css("display","block");
			nui.get("sq1").show();
			nui.get('base2').refreshTable();
		}else{
			nui.get("sq1").hide();
			nui.get('base2').refreshTable();
			//$("#sq1").css("display","none");
		}
	}
 	//只有阶段性还款方式才显示首次还本期次
 	function conRpTpChg(){
		var hkfs = nui.get("amountDetail.repaymentType").getValue();
		if(hkfs=='0300'||hkfs=='0400'){
			$("#schkqc").css("display","block");
			nui.get("amountDetail.firstRepayTerm").show();
		}else{
			$("#schkqc").css("display","none");
			nui.get("amountDetail.firstRepayTerm").hide();
			nui.get("amountDetail.firstRepayTerm").setValue('');
		}
		nui.get("base1").refreshTable();
	}
	
	//合同循环标志变化时
	function conCycleChg(){
		var cycleIndCon = nui.get("amountDetail.cycleIndCon").getValue();
		if('1'==cycleIndCon){
			//合同循环时，“还款方式”只能选择“按周期还息到期一次性还本、按周期还息任意还本、到期一次性还本付息”
			//二期需求：还款方式”只能选择“等额本金、等额本息、阶段性等额本金、阶段性等额本息、按周期还息到期一次性还本、到期一次性还本付息、按周期还息任意还本”
			
			nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','0100,0200,1100'));
			if(nui.get("amountDetail.repaymentType")){
				if(nui.get("amountDetail.repaymentType").getValue()!='1100'&&nui.get("amountDetail.repaymentType").getValue()!='1200'&&
				nui.get("amountDetail.repaymentType").getValue()!='1300'){
					nui.get("amountDetail.repaymentType").setValue('');
					nui.get("amountDetail.repaymentType").validate();
				}
			}
			//合同循环时，“结息周期”只能选择“结息周期”只能选择“按月结息/按季结息/在借款到期日付清全部利息”
			/* nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2,6'));
			if(nui.get("loanrate.interestCollectType")){
				if(nui.get("loanrate.interestCollectType").getValue()!='1'&&nui.get("loanrate.interestCollectType").getValue()!='2'&&
				nui.get("loanrate.interestCollectType").getValue()!='6'){
					nui.get("loanrate.interestCollectType").setValue('');
					nui.get("loanrate.interestCollectType").validate();
				}
			} */
		}else{
			nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','0100,0200,1100,1400'));
			//合同不循环时，“结息周期”只能选择“按月结息/按季结息/按还本计划表付清本期利息/在借款到期日付清全部利息”
			//nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2,5,6'));
		}
	}
	//选委托人
	function selectWtr(e) {
        var btnEdit = this;
        nui.open({
	        url: nui.context + "/biz/biz_product_detail/person/queryWtr.jsp",
	        showMaxButton: true,
	        title: "选择委托人",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	btnEdit.setValue(data.PARTY_ID);
	                    btnEdit.setText(data.PARTY_NAME);
	                    nui.get("productDetail.wtxmId").setValue(data.ACC_ID);
	                    nui.get("wtxmmc1").setValue(data.ENTRUST_PROJECT_NAME);
	                    nui.get("wtxmmc1").validate();
	                    nui.get("zjlx").setValue(data.CERT_TYPE);
	                    nui.get("zjlx").validate();
	                    nui.get("zjhm").setValue(data.CERT_NUM);
	                    nui.get("zjhm").validate();
	                    if(data.CERT_TYPE == '202'){
	                    	nui.get("productDetail.wtrlx").setValue('3');
	                    }else{
	                    	nui.get("productDetail.wtrlx").setValue('4');
	                    }
	                }
	            }
	        }
	    }); 
	}
  
	function validateEndDate(){
		/* var endDate = nui.get("amountDetail.endDate").getValue();
		if(endDate!='' && endDate!= null && endDate!='null'&& endDate!='undefined'){
			if(endDate.substr(0,10)<applyDate.substr(0,10)){
				//nui.alert("申报止期不能小于批复申请日期");
				//nui.get("amountDetail.endDate").setValue('');
				//return;
			}
		} */
	}
</script>
</body>
</html>