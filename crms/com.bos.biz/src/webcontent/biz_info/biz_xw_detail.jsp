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
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="amountDetail.amountDetailId" class="nui-hidden nui-form-input" name="amountDetail.amountDetailId"/>
	<input id="amountDetail.amountId" class="nui-hidden nui-form-input" name="amountDetail.amountId"/>
	<input id="amountDetail.exchangeRateDate" class="nui-hidden nui-form-input" name="amountDetail.exchangeRateDate"/>
	<input id="amountDetail.createTime" class="nui-hidden nui-form-input" name="amountDetail.createTime"/>
	<input id="loanrate.loanrateId" name="loanrate.loanrateId" class="nui-hidden nui-form-input" />
	<input id="loanrate.floatType" name="loanrate.floatType" class="nui-hidden nui-form-input"/>
	<input id="tbBizXwApply.applyDetailId" name="tbBizXwApply.applyDetailId" class="nui-hidden nui-form-input" />
	<input id="tbBizXwApply.wtxmId" name="tbBizXwApply.wtxmId"   class="nui-hidden nui-form-input"/>
	<input id="tbBizXwApply.createTime" name="tbBizXwApply.createTime"   class="nui-hidden nui-form-input"/>
	<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye"   class="nui-hidden nui-form-input"/>
	<fieldset>
		<legend>
			<span>客户申请信息：</span>
		</legend>
		<div class="nui-dynpanel" columns="4" id="table1">	
			<label>申报金额：</label>
			<input id="tbBizXwApply.applyXwAmt" name="tbBizXwApply.applyXwAmt"  required="true" class="nui-textbox nui-form-input" dataType="currency"  onblur="validMoney" vtype="maxLength:20;range:100,10000000000"/>
			
			<label>申请期限：</label>
			<div style="width:80%">
				<input name="tbBizXwApply.applyXwTerm" id="tbBizXwApply.applyXwTerm" style="width:60%;float:left" required="true" class="nui-textbox nui-form-input" onvalidation="checkXwTerm()" onblur="getAppEndDate()"/>
				<input name="tbBizXwApply.cycleUnitXw" id="tbBizXwApply.cycleUnitXw" style="width:40%;float:left" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6009" onvalidation="checkXwTerm()"/>
			</div>
			
			<label>申请日期：</label>
			<input id="tbBizXwApply.applyDate" name="tbBizXwApply.applyDate" class="nui-datepicker nui-form-input"  allowInput="false"  required="true" format="yyyy-MM-dd" />
		</div>
	</fieldset>
	<fieldset>
		<legend>
			<span>申报基本信息：</span>
		</legend>
		<div class="nui-dynpanel" columns="4"  id="table2">
			<label>共同借款人：</label>
			<div style="width:80%">
				<input id="tbBizXwApply.gtjkr" name="tbBizXwApply.gtjkr" class="nui-buttonEdit nui-form-input"
					allowInput="false" onbuttonclick="selectGtjkr"  style="width:70%;float:left"  emptyText="--请选择--" />
				<a class="nui-button" id="clear" iconCls="icon-edit" style="width:25%;float:left" onclick="clearGtjkr">清除</a>
			</div>
			
			<label>业务品种：</label>
			<input id="tbBizApply.productType" name="tbBizApply.productType" class="nui-text nui-form-input"
				    dictTypeId="product"/>
							
			<label>币种：</label>
			<input name="amountDetail.currencyCd" id="currencyCd" required="true" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"  onvaluechanged="onselectBz"/>
			
			<label>申报金额：</label>
			<input id="amountDetail.detailAmt" name="amountDetail.detailAmt" required="true" class="nui-textbox nui-form-input" dataType="currency"  onblur="validMoney"  vtype="float;maxLength:20;range:100,10000000000"/>
			
			<label id="hl">汇率：</label>
			<input id="amountDetail.exchangeRate" name="amountDetail.exchangeRate"  vtype="float;maxLength:10" class="nui-text nui-form-input" />
	
			<label id="zsrmbje">折算人民币金额：</label>
			<input id="amountDetail.rmbAmt" name="amountDetail.rmbAmt" vtype="float;maxLength:20" class="nui-text nui-form-input" dataType="currency"/>
			
			<!-- <label>申报止期：</label>
			<input id="amountDetail.endDate" name="amountDetail.endDate" class="nui-datepicker nui-form-input"  required="true"  allowInput="false" onblur="validEnddate" />
			 -->
			<!-- <label>建议期限：</label>
			<div style="width:80%">
			<input name="amountDetail.creditTerm" style="width:60%;float:left" id="amountDetail.creditTerm" required="true" vtype="int;maxLength:4;range:1,100000" class="nui-textbox nui-form-input"   onblur="getBasicRate"/>
			<input name="amountDetail.cycleUnit"id="amountDetail.cycleUnit" style="width:40%;float:left"  required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6009" value="04" emptyText="--请选择--" onvaluechanged="getBasicRate"/>
			</div> -->
			
			<label>还款方式：</label>
			<input id="amountDetail.repaymentType" name="amountDetail.repaymentType" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1162"  onvaluechanged="conRpTpChg"/>
		
			<label id="schbqc">首次还本期次：</label>
			<input id="amountDetail.firstRepayTerm" name="amountDetail.firstRepayTerm" required="true" data="data" class="nui-textbox nui-form-input" vtype="int;maxLength:4;range:2,10000"/>
			
			<label>还款来源：</label>
			<input id="amountDetail.payment" name="amountDetail.payment" required="true" data="data" class="nui-textarea nui-form-input"  vtype="maxLength:290"/>
				
			<label>贷款用途：</label>
			<input name="amountDetail.loanUse" id="amountDetail.loanUse" required="true" data="data" class="nui-textarea nui-form-input" vtype="maxLength:190"/>
			
			<label>合同循环标志：</label>
			<input name="amountDetail.cycleIndCon" id="amountDetail.cycleIndCon" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" onvaluechanged="conCycleChg"/>
			
			<label id="wtr"><a href="#" onclick="toGoCust();">委托人：</a></label>
			<input id="tbBizXwApply.wtr" name="tbBizXwApply.wtr" class="nui-buttonEdit nui-form-input" required="true" 
					allowInput="false" onbuttonclick="selectWtr"  style="width:80%;float:left"  emptyText="--请选择--" />
			<label id="wtxmmc">委托项目名称：</label>
			<input id="wtxmmc1" name="wtxmmc1"  class="nui-text nui-form-input"/>
			<label id="zjlx1">证件类型：</label>
			<input id="zjlx" name="zjlx"  required="true"  class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
			<label id="zjhm1">证件号码：</label>
			<input id="zjhm" name="zjhm"  required="true"  class="nui-text nui-form-input"/>
			<label id="wtrlx">委托人类型：</label>
			<input name="tbBizXwApply.wtrlx" id="tbBizXwApply.wtrlx" required="true" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1063"  emptyText="--请选择--" enabled="false"/>
			<label id="wtdkxz">委托贷款性质：</label>
			<input name="tbBizXwApply.wtdkxz" id="tbBizXwApply.wtdkxz" required="true" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0206"  emptyText="--请选择--"/>
		</div>
	</fieldset>
	<fieldset>
		<legend>
			<span>利率信息：</span>
		</legend>
		<div class="nui-dynpanel" columns="4"  id="table3">		
			<label>利率类型：</label>
			<input name="loanrate.rateType" id="loanrate.rateType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1016" required="true" onvaluechanged="onselectType"/>
		
			<label id="floatWay">浮动方式：</label>
			<input name="loanrate.floatWay" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1147" id="loanrate.floatWay" required="true"/>
		
			<label id="rateFloatProportion">浮动比例(%)：</label>
			<input id="loanrate.rateFloatProportion" name="loanrate.rateFloatProportion" class="nui-textbox nui-form-input" required="true"  vtype="float;negative;maxLength:11" onblur="selectRateValue"/>
		
			<label id="yearRate">申请利率（%）：</label>
			<input id="loanrate.yearRate" name="loanrate.yearRate" class="nui-textbox nui-form-input" vtype="float;maxLength:11;range:0.00000001,100" required="true"  onblur="selectBaseValue"/>
			
			<label id="isChangeRate">利率调整方式：</label>
			<input id="loanrate.irUpdateFrequency" name="loanrate.irUpdateFrequency" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_SXCD1148"  />
					
			<label>结息周期：</label>
			<input name="loanrate.interestCollectType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1018" id="loanrate.interestCollectType" required="true" />
			
			<label>节假日顺延标志：</label>
			<input name="loanrate.holidayFlg" id="loanrate.holidayFlg"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  onvaluechanged="conChangeHoliday" required="true"/>
		
			<label id="jjrlxclfs">节假日利息处理方式：</label>
			<input name="loanrate.holidayIntFlg" id="loanrate.holidayIntFlg" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0234"/>
		
			<label>宽限期方式：</label>
			<input name="loanrate.gracePeriodType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0210" id="loanrate.gracePeriodType" required="true" onvaluechanged="onselectGrace"/>																
		
			<label id="gracePeriodDay">宽限期天数：</label>
			<input name="loanrate.gracePeriodDay" class="nui-textbox nui-form-input"  id="loanrate.gracePeriodDay" vtype="int;range:1,10" required="true" />																
		
			<label id="kxqlxclfs">宽限期利息处理方式：</label>
			<input name="loanrate.graceCountIntFlag" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0234" id="loanrate.graceCountIntFlag" required="true"/>																
		
			<label id="ovardueRate">逾期罚息率（%）：</label>
			<input id="loanrate.overdueRateUpProportion" name="loanrate.overdueRateUpProportion" class="nui-textbox nui-form-input"  vtype="int;maxLength:11;range:30,50" required="true"/>
			
			<label>申请日期：</label>
			<input id="tbBizApply.applyDate" name="tbBizApply.applyDate" class="nui-text nui-form-input"   required="true" format="yyyy-MM-dd" />
			
			<!--	<label>提前还款是否收取违约金：</label>
				<input id="amountDetail.prepaymentPenalty" name="amountDetail.prepaymentPenalty" required="true"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="--请选择--"/>
			
				 <label>额度是否循环：</label>
				<input id="amountDetail.cycleInd" name="amountDetail.cycleInd" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
					 -->					
		</div>	
	</fieldset>
</div>
<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var partyId ="<%=request.getParameter("partyId")%>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var productType = '';
	var bizHappenType = "<%=request.getParameter("bizHappenType") %>";//04-调整  06-借新还旧
	nui.get("tbBizXwApply.cycleUnitXw").setData(getDictData('XD_GGCD6009','str','04'));//期限单位默认为月
	//节假日顺延标志默认为“不顺延”
 	nui.get("loanrate.holidayFlg").setValue('0');
	initPage();
	function initPage(){
        var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>"});
		$.ajax({
            url: "com.bos.bizProductDetail.bizProductDetail.getXwProductDetail.biz.ext",
            type: 'POST',
            data: json,
            async:false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	if(null==o.tbBizAmountApply.amountId||""==o.tbBizAmountApply.amountId){
            		alert("请先保存基本信息！");
					nui.get("btnCreate").hide();
				}else{
					productType =o.tbBizApply.productType;
	            	git.initParamOfProduct(productType,'1');//业务类型：1业务申请，2合同签订，3出账放款
	            	form.setData(o);
	            	git.setPtDefaultValue();
					/* //若业务品种为小贷业务品种或“个人房屋按揭贷款”、“住房公积金委托贷款－一手房”、“住房公积金委托贷款－二手房”，则“宽限期方式”可选，其他业务品种“宽限期方式”默认为“无宽限期”。
					if(productType.indexOf("020020") != -1||productType.indexOf("020050") != -1){
						nui.get("loanrate.gracePeriodType").setEnabled(true);
					}else{
						nui.get("loanrate.gracePeriodType").setValue("0");
						nui.get("loanrate.gracePeriodType").setEnabled(false);
					} */
					//需求默认人民币
					if(o.amountDetail.currencyCd==null ||o.amountDetail.currencyCd==''){
						nui.get("currencyCd").setValue("CNY");
					}
					//单位默认年
					if(o.tbBizXwApply.cycleUnitXw==null ||o.tbBizXwApply.cycleUnitXw==''){
						nui.get("tbBizXwApply.cycleUnitXw").setValue("01");
					}
					//节假日顺延标识---默认否
					if(o.loanrate.holidayFlg==null ||o.loanrate.holidayFlg==''){
						nui.get("loanrate.holidayFlg").setValue("0");
					}
					//宽限期方式---默认否
					if(o.loanrate.gracePeriodType==null ||o.loanrate.gracePeriodType==''){
						nui.get("loanrate.gracePeriodType").setValue("0");
					}
					//利率调整方式---默认按年
					//if(o.loanrate.irUpdateFrequency==null ||o.loanrate.irUpdateFrequency==''){
					//	nui.get("loanrate.irUpdateFrequency").setValue("04");
					//}
					//申请期限---默认月
					if(o.tbBizXwApply.cycleUnitXw==null ||o.tbBizXwApply.cycleUnitXw==''){
						nui.get("tbBizXwApply.cycleUnitXw").setValue("04");
					}
					
					nui.get("loanrate.floatType").setValue("0");
					//共同借款人
					nui.get("tbBizXwApply.gtjkr").setText(o.partyy.partyName);
					//借新还旧的贷款用途直接反显“借新还旧”
					if(bizHappenType == '06'){
						var loanuse = nui.get("amountDetail.loanUse").getValue();
						if(null == loanuse || ''==loanuse){
							nui.get("amountDetail.loanUse").setValue("借新还旧");
						}
					}
					//单笔调整时金额不让动
					if(bizHappenType=='04'){
						nui.get("amountDetail.detailAmt").setEnabled(false);
					}
					//不是委贷不显示委贷信息--20160420回复
					if(productType.indexOf("020050") == -1){
						nui.get("loanrate.overdueRateUpProportion").setValue("50");
						nui.get("loanrate.overdueRateUpProportion").setEnabled(false);
						nui.get("loanrate.overdueRateUpProportion").validate();
						hideWd();
					}else{
						//委托贷款查询客户
						if(o.tbBizXwApply.wtxmId != null && o.tbBizXwApply.wtxmId!=''){
							var json=nui.encode({"wtxmId":o.tbBizXwApply.wtxmId});
							$.ajax({
					            url: "com.bos.bizInfo.person.queryWtrXx.biz.ext",
					            type: 'POST',
					            data: json,
					            cache: false,
					            async: false,
					            contentType:'text/json',
					            success: function (text) {
					          		nui.get("tbBizXwApply.wtr").setValue(text.wtxm.PARTY_ID);
					          		nui.get("tbBizXwApply.wtr").setText(text.wtxm.PARTY_NAME);
					          		nui.get("wtxmmc1").setValue(text.wtxm.ENTRUST_PROJECT_NAME);
						        	nui.get("zjlx").setValue(text.wtxm.CERT_TYPE);
						        	nui.get("zjhm").setValue(text.wtxm.CERT_NUM);
					            }
				           })
						}
					}
					form.validate();
				}
			}
        });
		searchZHSXInfo();
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if(proFlag=="0"||proFlag=="-1" ){
			nui.get("btnCreate").hide();
			form.setEnabled(false);
			nui.get("clear").hide();
		}
	}
	var groupCreditTerm;
	function searchZHSXInfo(){
 		var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>"});
		$.ajax({
		        url: "com.bos.bizApply.groupApply.searchZHSXInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        async:false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	debugger;
			        if(mydata && mydata.data && mydata.data[0]){
			        	groupCreditTerm = mydata.data[0].CREDIT_TERM;
			        }
		        }
		});
	}
	function checkXwTerm(){
		var temp = nui.get("tbBizXwApply.applyXwTerm").getValue();
		var unitXw = nui.get("tbBizXwApply.cycleUnitXw").getValue();
    	if(groupCreditTerm){
    		nui.get("tbBizXwApply.cycleUnitXw").setValue("04");
    		nui.get("tbBizXwApply.cycleUnitXw").setEnabled(false);
    		if(temp>groupCreditTerm){
	    		nui.get("tbBizXwApply.applyXwTerm").setValue("");
	    		nui.alert("申请期限不能大于["+groupCreditTerm+"]");
    		}
    	}
	}
	
	var appEndDate = "";
	//计算到期日
	function getAppEndDate(){
		var beginDate = nui.get("tbBizApply.applyDate").getValue();//申请的起始日期
		var term = nui.get("tbBizXwApply.applyXwTerm").getValue();//申请的期限
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
					appEndDate = data.dqrq;
				}
			});
		}
	}
	
	function create(){
		form.validate();
		//校验
		git.validParamOfProduct(productType,'1');//业务类型：1业务申请，2合同签订，3出账放款
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
         	return;
        }
        nui.get("btnCreate").setEnabled(false);
        var o = form.getData();
       /*  //14还款方式不支持合同循环
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
        } */
         //利率模块处理开始
		var rateType = nui.get("loanrate.rateType").getValue();
		if(rateType){
			if(rateType == '2'){//浮动
				var irUpdateFrequency = nui.get("loanrate.irUpdateFrequency").getValue();
				if(irUpdateFrequency == '05'){
					nui.alert("浮动利率不允许选不调整");
					nui.get("btnCreate").setEnabled(true);
					return;
				}
				nui.get("loanrate.floatType").setValue("0");
				o.loanrate.floatType = '0';
				o.loanrate.yearRate = '';
			}
			if(rateType == '1'){//固定
				nui.get("loanrate.floatType").setValue("");
				nui.get("loanrate.floatWay").setValue("");
				nui.get("loanrate.rateFloatProportion").setValue(""); 
				/*  用hide()隐藏后取不到值 */
				o.loanrate.floatType = '';
				o.loanrate.floatWay = '';
				o.loanrate.rateFloatProportion = '';
			}
			
		}
		//宽限期天数
		if(nui.get("loanrate.gracePeriodType")!=null){
			var  gracePeriodType = nui.get("loanrate.gracePeriodType").getValue();
			if(gracePeriodType!='2'){
				nui.get("loanrate.gracePeriodDay").setValue("");
				o.loanrate.gracePeriodDay = '';
			}
			if(gracePeriodType=='0'){
				nui.get("loanrate.graceCountIntFlag").setValue("");
				o.loanrate.graceCountIntFlag = '';
			}
		}
		//节假日
		if(nui.get("loanrate.holidayFlg")!=null){
			var  holidayFlg = nui.get("loanrate.holidayFlg").getValue();
			if(holidayFlg=='0'){
				nui.get("loanrate.holidayIntFlg").setValue("");
				o.loanrate.holidayIntFlg = '';
			}
			var  gracePeriodType = nui.get("loanrate.gracePeriodType").getValue();
			if(holidayFlg !='0'  && gracePeriodType!='0'){
				nui.alert("节假日与宽限期只能使用一种!");
				nui.get("btnCreate").setEnabled(true);
				return;
			}
		}
		form.loading("正在保存数据...");
		//利率模块处理结束
        //单笔合同循环与额度循环一样
        o.amountDetail.cycleInd = o.amountDetail.cycleIndCon;
        //贷种赋值
        o.amountDetail.productType = nui.get("tbBizApply.productType").getValue();
		//币种为人民币时汇率和折算rmb金额隐藏后取不到值需手动赋值
		if(o.amountDetail.currencyCd=='CNY'){
			o.amountDetail.exchangeRate = '1'; 
			o.amountDetail.rmbAmt = o.amountDetail.detailAmt; 
		}else{
			//取汇率
			 var jsonbz = nui.encode({"bz":o.amountDetail.currencyCd});
			$.ajax({
	            url: "com.bos.bizInfo.person.getHl.biz.ext",
	            type: 'POST',
	            data: jsonbz,
	            async: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	/* nui.get("amountDetail.exchangeRate").setValue(mydata.hl);
	            	nui.get("amountDetail.exchangeRate").validate();
	            	nui.get("amountDetail.exchangeRateDate").setValue(mydata.hldate); */
	            	o.amountDetail.exchangeRate = mydata.hl; 
	            	o.amountDetail.exchangeRateDate = mydata.hldate; 
					o.amountDetail.rmbAmt = (parseFloat(o.amountDetail.detailAmt)*parseFloat(mydata.hl)).toFixed(6);
				}
	        });
		}
		
		//申报止期取值
		o.amountDetail.endDate = appEndDate;
		
		//首次还本期次
		var hkfs = o.amountDetail.repaymentType;
		if(hkfs!='0300'&&hkfs!='0400'){
			o.amountDetail.firstRepayTerm = '';
		}
        var json = nui.encode(o);
		$.ajax({
            url: "com.bos.bizProductDetail.bizProductDetail.saveXwProductDetail.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	form.unmask();
            	nui.get("btnCreate").setEnabled(true);
            	if(mydata.msg){
	        		nui.alert(mydata.msg);
	        		return;
            	}
            	var o = nui.decode(mydata);
            	alert("保存成功!");
            	initPage();
			}
        });
	}
    
 	//产品树
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp?partyId="+partyId,
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productCd);
                        btnEdit.setText(data.productName);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
 	//选共同借款人（个人客户）
	function selectGtjkr(e) {
        var btnEdit = this;
        nui.open({
	        url: nui.context + "/grt/manage/chioseParty/queryCommonParty.jsp?partyId="+partyId,
	        showMaxButton: true,
	        title: "选择共同借款人",
	        width: 800,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
                    	btnEdit.setValue(data.partyId);
	                    btnEdit.setText(data.partyName);
	                }
	            }
	        }
	    }); 
	}
	//申报金额变动时触发  校验申请金额要大于等于建议金额
 	function validMoney(){
 		var detailAmt =  nui.get("amountDetail.detailAmt").getValue(); 
 		var applyXwAmt =  nui.get("tbBizXwApply.applyXwAmt").getValue();
 		//借新还旧时要小于等于借据余额
 		if(null!=nui.get("tbLoanSummary.jjye")){
 			var summaryAmt =  nui.get("tbLoanSummary.jjye").getValue();
 			if(summaryAmt == '' || null == summaryAmt || 'null' == summaryAmt){
 				//非借新还旧
 			}else{
 				if(parseFloat(detailAmt)>parseFloat(summaryAmt)){
	 				nui.alert("借新还旧金额不能大于原借据余额");
		 			nui.get("amountDetail.detailAmt").setValue('');
		 			return;
	 			}
 			}
 		}
 		
 		if(parseFloat(detailAmt)>parseFloat(applyXwAmt)){
 			nui.alert("申报金额不能大于申请金额");
 			nui.get("amountDetail.detailAmt").setValue('');
 			return;
 		} else{//校验通过计算折算人民币金额
 			calcuMoney();
 		}
 	}
 	//计算折算人民币金额
 	function calcuMoney(){
 		var hl = nui.get("amountDetail.exchangeRate").getValue();
 		var detailAmt =  nui.get("amountDetail.detailAmt").getValue();
 		if(hl!='' && hl!=null && detailAmt!='' && detailAmt!=null  ){
	 		var rmbAmt = (parseFloat(detailAmt)*parseFloat(hl)).toFixed(6);
	 		nui.get("amountDetail.rmbAmt").setValue(rmbAmt);
	    	nui.get("amountDetail.rmbAmt").validate();
 		}
 	}
 	
	//清除共同借款人
	function clearGtjkr(){
		nui.get("tbBizXwApply.gtjkr").setValue('');
		nui.get("tbBizXwApply.gtjkr").setText('');
	}
	
	
	//只有阶段性还款方式才显示首次还本期次
 	function conRpTpChg(){
		var hkfs = nui.get("amountDetail.repaymentType").getValue();
		if(hkfs=='0300'||hkfs=='0400'){
			nui.get("amountDetail.firstRepayTerm").setValue('');
			$("#schbqc").css("display","block");
			nui.get("amountDetail.firstRepayTerm").show();
		}else{
			//nui.get("amountDetail.firstRepayTerm").setValue('');隐藏后取不到值无法更新此字段，只有在保存时处理
			$("#schbqc").css("display","none");
			nui.get("amountDetail.firstRepayTerm").hide('');
		}
		//刷新table
		nui.get('table2').refreshTable();
	}
	
	//选币种触发事件
	function onselectBz(){
		var bz = nui.get("currencyCd").getValue();
		if(bz=='' || bz== null || bz=='null'){//刚进页面或选”请选择“
			nui.get("amountDetail.exchangeRate").setValue('');
			nui.get("amountDetail.rmbAmt").setValue('');
			nui.get("amountDetail.exchangeRate").setValue('1');
			$("#hl").css("display","none");
			nui.get("amountDetail.exchangeRate").hide();
			$("#zsrmbje").css("display","none");
			nui.get("amountDetail.rmbAmt").hide();
		}else if(bz=='CNY'){
			nui.get("amountDetail.exchangeRate").setValue('1');
			$("#hl").css("display","none");
			nui.get("amountDetail.exchangeRate").hide();
			$("#zsrmbje").css("display","none");
			nui.get("amountDetail.rmbAmt").hide();
		}else{//外币实时取汇率
			nui.get("amountDetail.exchangeRate").setValue('');
			nui.get("amountDetail.rmbAmt").setValue('');
			$("#hl").css("display","block");
			nui.get("amountDetail.exchangeRate").show();
			$("#zsrmbje").css("display","block");
			nui.get("amountDetail.rmbAmt").show();
			
			
		}
 		//calcuMoney();
		//刷新table
		nui.get('table2').refreshTable();
	}
	//利率类型触发事件
    function onselectType(){
		var reateType= nui.get("loanrate.rateType").getValue();
		if(reateType=="2"){//浮动利率
			//隐藏固定利率
			dealRate('fixed','hide');
			//显示浮动利率
			dealRate('float','show');
			//利率调整方式--浮动时不能选不调整
			nui.get("loanrate.irUpdateFrequency").setEnabled(true);
			nui.get("loanrate.irUpdateFrequency").setData(getDictData('XD_SXCD1148','str','01,02,03,04,06'));
			nui.get("loanrate.irUpdateFrequency").setValue('04');//初始化按年调整
			if(nui.get("loanrate.irUpdateFrequency")){
				if(nui.get("loanrate.irUpdateFrequency").getValue()=='05'){
					nui.get("loanrate.irUpdateFrequency").setValue('');
				}
			}
		}else if(reateType=="1"){//固定利率
			//显示固定利率
			dealRate('fixed','show');
			//隐藏浮动利率
			dealRate('float','hide');
			//利率调整方式--固定时只能选不调整
			nui.get("loanrate.irUpdateFrequency").setEnabled(false);
			nui.get("loanrate.irUpdateFrequency").setData(getDictData('XD_SXCD1148','str','05'));
			nui.get("loanrate.irUpdateFrequency").setValue('05');
		}else{//非反显
			nui.get("loanrate.irUpdateFrequency").setValue('');
			nui.get("loanrate.irUpdateFrequency").setEnabled(false);
			dealRate('fixed','hide');
			dealRate('float','hide');
		}
		//刷新table
		nui.get('table3').refreshTable();
	}
	
	//隐藏、显示利率
	function dealRate(rateType,dealType){
		if(rateType=='fixed'){//固定
			if(dealType == 'hide'){
				$("#yearRate").css("display","none");
				nui.get("loanrate.yearRate").hide();
				nui.get("loanrate.yearRate").setValue('');
			}else if(dealType == 'show'){
				$("#yearRate").css("display","block");
				nui.get("loanrate.yearRate").show();
			}
		}else if(rateType=='float'){//浮动
			if(dealType == 'hide'){
				nui.get("loanrate.floatWay").setValue('');
				nui.get("loanrate.rateFloatProportion").setValue('');
				$("#floatWay").css("display","none");
				$("#rateFloatProportion").css("display","none");
				nui.get("loanrate.floatWay").hide();
				nui.get("loanrate.rateFloatProportion").hide();
			}else if(dealType == 'show'){
				$("#floatWay").css("display","block");
				$("#rateFloatProportion").css("display","block");
				nui.get("loanrate.floatWay").show();
				nui.get("loanrate.rateFloatProportion").show();
			}
		}
		
	}
	//节假日顺延标志选否，节假日利息处理方式为空且不可选；
	//节假日顺延标志选择“是”，则“节假日利息处理方式默认为追加罚息，不可修改
	function conChangeHoliday(){
		var holidayFlag = nui.get("loanrate.holidayFlg").getValue();
		if(holidayFlag == '1'){
			$("#jjrlxclfs").css("display","block");
			nui.get("loanrate.holidayIntFlg").show();
			nui.get("loanrate.holidayIntFlg").setValue('1');
			nui.get("loanrate.holidayIntFlg").setEnabled(false);
		}else{//不顺延或者为空
			$("#jjrlxclfs").css("display","none");
			nui.get("loanrate.holidayIntFlg").hide();
			nui.get("loanrate.holidayIntFlg").setValue('');
		}
		//刷新table
		nui.get('table3').refreshTable();
	}
	
	/* 宽限期--
		宽限期方式选择“无宽限期”，则“宽限期利息处理方式”不展示；
		宽限期方式选择“宽限至月底/按日计算”，则“宽限期利息处理方式”默认为追加罚息，不可修改 */
 	function onselectGrace(){
		var graceType = nui.get("loanrate.gracePeriodType").getValue();
		if(graceType=='2'){//按日
			$("#gracePeriodDay").css("display","block");
			$("#kxqlxclfs").css("display","block");
			nui.get("loanrate.gracePeriodDay").show();
			nui.get("loanrate.graceCountIntFlag").show();
			
			//默认追加罚息不可修改
			nui.get("loanrate.graceCountIntFlag").setValue('1');
			nui.get("loanrate.graceCountIntFlag").setEnabled(false);
		}else if(graceType == '1'){//宽限至月底
			$("#gracePeriodDay").css("display","none");
			$("#kxqlxclfs").css("display","block");
			nui.get("loanrate.gracePeriodDay").hide();
			nui.get("loanrate.graceCountIntFlag").show();
			
			nui.get("loanrate.gracePeriodDay").setValue('');
			
			//默认追加罚息不可修改
			nui.get("loanrate.graceCountIntFlag").setValue('1');
			nui.get("loanrate.graceCountIntFlag").setEnabled(false);
		}else{//无宽限期
			$("#gracePeriodDay").css("display","none");
			$("#kxqlxclfs").css("display","none");
			nui.get("loanrate.gracePeriodDay").hide();
			nui.get("loanrate.graceCountIntFlag").hide();
			
			nui.get("loanrate.gracePeriodDay").setValue('');
			nui.get("loanrate.graceCountIntFlag").setValue('');
			nui.get("loanrate.graceCountIntFlag").setEnabled(true);
		}
		//刷新table
		nui.get('table3').refreshTable();
	}
	
	//非委贷隐藏委贷信息
	function hideWd(){
		$("#wtr").css("display","none");
		$("#wtxmmc").css("display","none");
		$("#zjlx1").css("display","none");
		$("#zjhm1").css("display","none");
		$("#wtrlx").css("display","none");
		$("#wtdkxz").css("display","none");
		nui.get("tbBizXwApply.wtr").hide();
		nui.get("wtxmmc1").hide();
		nui.get("zjlx").hide();
		nui.get("zjhm").hide();
		nui.get("tbBizXwApply.wtrlx").hide();
		nui.get("tbBizXwApply.wtdkxz").hide();
		//刷新table
		nui.get('table2').refreshTable();
	}
	//选委托人---加入委托项目信息
	/* function selectWtr(e) {
        var btnEdit = this;
        nui.open({
	        url: nui.context + "/csm/pub/third_cust_query.jsp?thirdCustTypeCd=4",
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
                    	btnEdit.setValue(data.partyId);
	                    btnEdit.setText(data.partyName);
	                    nui.get("zjlx").setValue(data.certType);
	                    nui.get("zjlx").validate();
	                    nui.get("zjhm").setValue(data.certNum);
	                    nui.get("zjhm").validate();
	                    if(data.certType == '202'){
	                    	nui.get("tbBizXwApply.wtrlx").setValue('3');
	                    }else{
	                    	nui.get("tbBizXwApply.wtrlx").setValue('4');
	                    }
	                }
	            }
	        }
	    }); 
	} */
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
	                    nui.get("tbBizXwApply.wtxmId").setValue(data.ACC_ID);
	                    nui.get("wtxmmc1").setValue(data.ENTRUST_PROJECT_NAME);
	                    nui.get("wtxmmc1").validate();
	                    nui.get("zjlx").setValue(data.CERT_TYPE);
	                    nui.get("zjlx").validate();
	                    nui.get("zjhm").setValue(data.CERT_NUM);
	                    nui.get("zjhm").validate();
	                    if(data.CERT_TYPE == '202'){
	                    	nui.get("tbBizXwApply.wtrlx").setValue('3');
	                    }else{
	                    	nui.get("tbBizXwApply.wtrlx").setValue('4');
	                    }
	                }
	            }
	        }
	    }); 
	}
	//合同循环标志变化时
	function conCycleChg(){
		var cycleIndCon = nui.get("amountDetail.cycleIndCon").getValue();
		if(cycleIndCon=='1'){
			//合同循环时，“还款方式”只能选择“按周期还息到期一次性还本、按周期还息任意还本、到期一次性还本付息、等额本金、等额本息、等本等息”---需求532 加2个阶段性
			nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','0100,0200,1100'));
			if(nui.get("amountDetail.repaymentType")){
				if(nui.get("amountDetail.repaymentType").getValue()!='1100'&&nui.get("amountDetail.repaymentType").getValue()!='1200'&&
				nui.get("amountDetail.repaymentType").getValue()!='1300'&&nui.get("amountDetail.repaymentType").getValue()!='0100'&&
				nui.get("amountDetail.repaymentType").getValue()!='0200'){
					nui.get("amountDetail.repaymentType").setValue('');
					nui.get("amountDetail.repaymentType").validate();
				}
			}
			//合同循环时，“结息周期”只能选择“结息周期”只能选择“按月结息/按季结息/在借款到期日付清全部利息”
			//nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2,6'));
			nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
			if(nui.get("loanrate.interestCollectType")){
				if(nui.get("loanrate.interestCollectType").getValue()!='1'&&nui.get("loanrate.interestCollectType").getValue()!='2'&&
				nui.get("loanrate.interestCollectType").getValue()!='6'){
					nui.get("loanrate.interestCollectType").setValue('');
					nui.get("loanrate.interestCollectType").validate();
				}
			}
		}else{
			/* 若业务品种为“个人房屋按揭贷款”、“住房公积金委托贷款－一手房”、“住房公积金委托贷款－二手房”，
				则“还款方式”只能选择“等额本金/等额本息/阶段性等额本金/阶段性等额本息”，
				“结息周期”只能选择“按月结息/按季结息” */
				//个人住房按揭类业务的合同循环标志默认为否
			if(productType.indexOf("020020") != -1||productType.indexOf("02005001") != -1||productType.indexOf("02005010") != -1||productType.indexOf("02005003") != -1){
				nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','0100,0200'));
				nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
				nui.get("amountDetail.cycleIndCon").setEnabled(false);
				nui.get("amountDetail.cycleIndCon").setValue('0');
			}else{
				nui.get("amountDetail.repaymentType").setData(getDictData('XD_SXCD1162','str','0100,0200,1100,1400'));
				//合同不循环时，“结息周期”只能选择“按月结息/按季结息/按还本计划表付清本期利息/在借款到期日付清全部利息”
				//nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2,5,6'));
				nui.get("loanrate.interestCollectType").setData(getDictData('XD_SXCD1018','str','1,2'));
			}
		}
	}
	
	function validEnddate(){
		/* var endDate = nui.get("amountDetail.endDate").getValue();
		var applyDate = nui.get("tbBizApply.applyDate").getValue();
		if(endDate!='' && endDate!= null && endDate!='null'&& endDate!='undefined'){
			if(endDate.substr(0,10)<applyDate.substr(0,10)){
				//nui.alert("申报止期不能小于批复申请日期");
				//nui.get("amountDetail.endDate").setValue('');
				//return;
			}
		} */
	}
	function toGoCust(){
		var wtr = nui.get("tbBizXwApply.wtr").getValue();
		if(wtr){
			toGoCustDetail(wtr);
		}else{
			alert("请先选择委托人！");
		}
	}
	function selectRateValue(){
		var rate = nui.get("loanrate.rateFloatProportion").getValue();
		if(parseFloat(rate)>parseFloat('400')){
		nui.get("loanrate.rateFloatProportion").setValue("");
		nui.alert("浮动比例不能超过400%");
		}
	}
	function selectBaseValue(){
		var maxRate;
			$.ajax({
	            url: "com.bos.pay.LoanSummary.queryMaxRate.biz.ext",
	            type: 'POST',
	            data: "",
	            async: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            maxRate = mydata.maxRate;
				}
	        });
	        maxRate = parseFloat(maxRate)*4;
	        var currRate = nui.get("loanrate.yearRate").getValue();
	        if(parseFloat(currRate)>parseFloat(maxRate)){
	        	nui.get("loanrate.yearRate").setValue("");
				nui.alert("固定利率不能超过基准利率400%");
	        }
	}
</script>
</body>
</html>