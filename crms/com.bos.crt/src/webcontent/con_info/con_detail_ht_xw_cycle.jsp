<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-23 16:03:59
  - Description:小微循环类合同明细页面
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
						<input id="tbConContractInfo.leastPrepayAmount" name="tbConContractInfo.leastPrepayAmount"  dataType="currency" vtype="float;maxLength:20" class="nui-textbox nui-form-input"  />

						<label>提前还款基数(元)：</label>
						<input id="tbConContractInfo.prepayJs" name="tbConContractInfo.prepayJs"  dataType="currency" vtype="float;maxLength:20" class="nui-textbox nui-form-input"  />
						
					</div>
				</fieldset>
			</div>
			
			<div class="nui-dynpanel" columns="1" id="table2">
				<fieldset>
					<legend>
						<span>利率信息</span>
					</legend>
					<%@include file="/biz/biz_product_detail/biz_public_rate_xw_cycle.jsp"%>
				</fieldset>
			</div>
			
			<div class="nui-dynpanel" columns="1" id="table6">
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
			<div class="nui-dynpanel" columns="1" id="table9">
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
				<fieldset>
					<legend>
						<span>补充条款</span>
					</legend>
					<div class="nui-dynpanel" columns="4">
						<label class="nui-form-label">补充条款：</label>
					 	<input id="tbConAttachedInfo.addClause" class="nui-textarea nui-form-input" name="tbConAttachedInfo.addClause"  colspan="3" style="width: 100%;height: 70px"/>
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
            	nui.get("rateFloatProportion").setValue(o.apvrate.rateFloatProportion);//浮动比例
            	nui.get("yearrate").setValue(o.apvrate.yearRate);//固定利率
            	form.validate();
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
					if(nui.get("tbConContractInfo.payWay")!=null){
						nui.get("tbConContractInfo.payWay").setValue("0");//自主字符
						nui.get("tbConContractInfo.payWay").setEnabled(false);//置灰
						nui.get("tbConContractInfo.payWay").validate();
					}
				}
			}
		});
	}
	
    function save(){
    	form.validate();
    	if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
		var o = form.getData();
		//利率模块处理结束
        nui.get("con_contract_detail_info_save").setEnabled(false);
		o.contractId = contractId;
		o.productType = productType;
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
	
</script>
</body>
</html>