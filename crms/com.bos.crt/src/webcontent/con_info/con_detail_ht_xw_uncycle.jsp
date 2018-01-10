<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-23 16:03:59
  - Description:小微非循环类合同明细页面
-->
<head>
<title>主合同明细信息</title>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
		<div id="form" style="width: 99.5%;  overflow: hidden;">
		<input id="tbConAttachedInfo.attached" class="nui-hidden nui-form-input" name ="tbConAttachedInfo.attached"/>
		<input id="loanrate.loanrateId" class="nui-hidden nui-form-input" name ="loanrate.loanrateId"/>
		<input id="yearrate" class="nui-hidden nui-form-input" name ="yearrate"/>
		<input id="rateFloatProportion" class="nui-hidden nui-form-input" name ="rateFloatProportion"/>
		<input id="conDetail.applyDetailId" class="nui-hidden nui-form-input" name ="conDetail.applyDetailId"/>
		<input id="tbConContractInfo.contractId" class="nui-hidden nui-form-input" name ="tbConContractInfo.contractId"/>
		<input id="tbConContractInfo.contractAmt" class="nui-hidden nui-form-input" name ="tbConContractInfo.contractAmt"/>
		<input id="tbConContractInfo.beginDate" class="nui-hidden nui-form-input" name ="tbConContractInfo.beginDate"/>
		<input id="tbConContractInfo.endDate" class="nui-hidden nui-form-input"  name ="tbConContractInfo.endDate"/>
		<input id="tbConContractInfo.repaymentType" name="tbConContractInfo.repaymentType"  class="nui-hidden nui-form-input"/>
			<div  class="nui-dynpanel" columns="1" id="table1">
				<fieldset>
					<legend>
						<span>明细信息</span>
					</legend>
					<div id="zffs" class="nui-dynpanel" columns="4">
						<label>资金支付方式：</label>
						<input id="tbConContractInfo.payWay" name="tbConContractInfo.payWay" required="true" align="center" class="mini-dictradiogroup" dictTypeId="CDXY0144" required="true"/>
					</div>
					<div id="tqhk" class="nui-dynpanel" columns="4">
						<label>提前还款最低金额：</label>
						<input id="tbConContractInfo.leastPrepayAmount" name="tbConContractInfo.leastPrepayAmount"  dataType="currency"vtype="float;maxLength:20"class="nui-textbox nui-form-input"  />

						<label>提前还款基数(元)：</label>
						<input id="tbConContractInfo.prepayJs" name="tbConContractInfo.prepayJs"  dataType="currency"vtype="float;maxLength:20"class="nui-textbox nui-form-input"  />
				
						<label>提前还款是否收取违约金：</label>
						<input id="tbConContractInfo.prepaymentPenalty" name="tbConContractInfo.prepaymentPenalty" required="true"  class="nui-dictcombobox nui-form-input" onvaluechanged="onselectPrepaymentPenalty"dictTypeId="XD_0002"  />
						
						<label id="prepayMakeupRate">违约金比例%：</label>
						<input id="tbConContractInfo.prepayMakeupRate" name="tbConContractInfo.prepayMakeupRate" required="true"  vtype="float;maxLength:20" class="nui-textbox nui-form-input" />
						
					</div>
				</fieldset>
			</div>
			
			<div class="nui-dynpanel" columns="1" id="table2">
				<fieldset>
					<legend>
						<span>利率信息</span>
					</legend>
					<%@include file="/biz/biz_product_detail/biz_public_rate_xw.jsp"%>
				</fieldset>
			</div>
			
			
			<div class="nui-dynpanel" columns="1" id="table9">
				<fieldset>
					<legend>
						<span>通知和文书送达</span>
					</legend>
						<%@include file="/biz/biz_product_detail/biz_public_notice.jsp"%>
				</fieldset>
			</div>
			
			<div class="nui-dynpanel" columns="1" id="table6">
				<fieldset>
					<legend>
						<span>仲裁方式</span>
					</legend>
						<%@include file="/biz/biz_product_detail/biz_public_zcfs.jsp"%>
				</fieldset>
			</div>

			<div class="nui-dynpanel" columns="1" id="table7">
				<fieldset>
					<legend>
						<span>协议签署</span>
					</legend>
						<%@include file="/biz/biz_product_detail/biz_public_xyqs.jsp"%>
				</fieldset>
			</div>
			
			<!-- 提款计划 -->
			<%@include file="/crt/con_detail/payout_plan.jsp"%>
			<!-- 还款计划 -->
			<%@include file="/crt/con_detail/loan_repay_plan.jsp"%>
			
			<div id="table5">
				<%@include file="/crt/con_detail/fee.jsp"%>
			</div>
			<div class="nui-dynpanel" columns="1" id="table8">
				<fieldset>
					<legend>
						<span>补充条款</span>
					</legend>
					<div class="nui-dynpanel" columns="4">
						<label class="nui-form-label">补充条款：</label>
					 	<input id="tbConAttachedInfo.addClause" class="nui-textarea nui-form-input" name="tbConAttachedInfo.addClause"  colspan="3"/>
					 </div>
				</fieldset>
			</div>
			
			<div class="nui-toolbar" style="text-align: right; padding-top: 15px; padding-right: 25px;" borderStyle="border:0;">
				<a class="nui-button" id="con_contract_detail_info_save" iconCls="icon-save" onclick="save">保存</a>
			</div>
		</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var contractId ="<%=request.getParameter("contractId") %>";//htID
	var amountDetailId ="<%=request.getParameter("amountDetailId") %>";//协议申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var productType =  "<%=request.getParameter("productType") %>";//贷种
	var oldContractId = "";
	
	$("#table5").css("display","none");//对私 隐藏费率信息项
	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"contractId":contractId,"productType":productType});
		$.ajax({
            url: "com.bos.conInfo.conContractInfo.getConDetailInfoByContarctId.biz.ext",
            type: 'POST',
            data: json,
        	async: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	var json = nui.decode({"contractId":contractId});
            	var grid1 = nui.get("grid1");
				grid1.load(json);
				var grid2 = nui.get("grid2");
				grid2.load(json);
				var grid3 = nui.get("grid3");//初始化费率信息项
				grid3.load(json);
            	nui.get("rateFloatProportion").setValue(o.apvrate.rateFloatProportion);//浮动比例
            	nui.get("yearrate").setValue(o.apvrate.yearRate);//固定利率
            	form.validate();
			}
        });
        setXht();//循环通/续接贷 资金支付方式默认为否 
	    //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_detail_info_save").hide();
			nui.get("payoutdiv").hide();
			nui.get("hkjhdiv").hide();
			form.setEnabled(false);
		}
		//对私的委托贷款 才有费率信息项
		if(productType == '02005001'||productType == '02005002'||productType == '02005010'){
			$("#table5").css("display","block");
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
					if(nui.get("tbConContractInfo.payWay")!=null){
						nui.get("tbConContractInfo.payWay").setValue("0");//自主字符
						nui.get("tbConContractInfo.payWay").setEnabled(false);//置灰
						nui.get("tbConContractInfo.payWay").validate();
					}
				}
			}
		});
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
            	}else if(row.feeId){//费率信息项(删除功能)
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
    	var grid3 = nui.get("grid3");
    	var amt = nui.get("tbConContractInfo.contractAmt").getValue();//合同金额
    	var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{1,4})?$/;
    	if(e.field == "chargingProportion"){
    		if(mny.test(e.value) && amt!=null){
    			grid3.updateRow(e.row,{"shouldFee":amt*e.value/1000});
    		}
    	}
    	if(e.field == "shouldFee"){
    		if(mny.test(e.value) && amt!=null){
    			grid3.updateRow(e.row,{"chargingProportion":e.value*1000/amt});
    		}
    	}
    }
	//保存主合同明细信息
    function save(){
    	form.validate();
    	if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
		//节假日
		var  holidayFlg = nui.get("loanrate.holidayFlg").getValue();
		var  gracePeriodType = nui.get("loanrate.gracePeriodType").getValue();
		if(holidayFlg!='' && holidayFlg!=null){
			if(holidayFlg !='0'  && gracePeriodType!='0'){
				nui.alert("节假日与宽限期只能使用一种!");
				nui.get("btnCreate").setEnabled(true);
				return;
			}
		}
		//当选择自主支付的时候 将受托的三种方式设为空
		if(nui.get("tbConContractInfo.payWay")!=null){
			var zffs = nui.get("tbConContractInfo.payWay").getValue();
			if(zffs != '1'){//1是受托 
				//nui.get("tbConContractInfo.payDepend1").setValue("");
				//nui.get("tbConContractInfo.payDepend2").setValue("");
				//nui.get("tbConContractInfo.payDepend3").setValue("");
			}
		}
		var o = form.getData();
		var PayoutPlans = nui.get("grid1").getChanges();/* 提款 */
		var repayPlans = nui.get("grid2").getChanges();/* 还款 */
		//费用信息
		var fees = nui.get("grid3").getChanges();/* 费用 */
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
				var mny = /^([1-9][\d]{0,16}|0)(\.[\d]{2})?$/;
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
				}
			}
		}
		//利率模块处理结束
        nui.get("con_contract_detail_info_save").setEnabled(false);
		o.contractId = contractId;
		o.productType = productType;
		o.PayoutPlans = PayoutPlans;
		o.repayPlans = repayPlans;
		o.fees = fees;//费用信息
		o.op="2";//小微合同明细
    	var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.conInfo.conContractInfo.saveConContractDetailInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_detail_info_save").setEnabled(true);
        	}else{
	        	alert("保存成功！");
	        	nui.get("con_contract_detail_info_save").setEnabled(true);
        	}
        	initPage();
        }});
	}
	function onselectPrepaymentPenalty(){
		if(nui.get("tbConContractInfo.prepaymentPenalty").getValue()=="1"){
			$("#prepayMakeupRate").css("display","block");
			nui.get("tbConContractInfo.prepayMakeupRate").show();
		}else{
			$("#prepayMakeupRate").css("display","none");
			nui.get("tbConContractInfo.prepayMakeupRate").hide();
			nui.get("tbConContractInfo.prepayMakeupRate").setValue();
		}
	}
	
</script>
</body>
</html>