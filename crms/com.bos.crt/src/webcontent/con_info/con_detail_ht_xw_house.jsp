<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-23 16:03:59
  - Description:小微房贷合同明细页面
-->
<head>
<title>主合同明细信息</title>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
		<div id="form" style="width: 99.5%;  overflow: hidden;">
		<input id="tbConAttachedInfo.attached" class="nui-hidden nui-form-input" name ="tbConAttachedInfo.attached"/>
		<input id="loanrate.loanrateId" class="nui-hidden nui-form-input" name ="loanrate.loanrateId"/>
		<input id="yearrate" class="nui-hidden" name ="yearrate"/>
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
					<div class="nui-dynpanel"  columns="4">
					
					<label>购房合同编号：</label>
					<input name="conDetail.houseContractNum" id="conDetail.houseContractNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:50" />
			
					<label>国家：</label>
					<input name="conDetail.nationalityCd" id="conDetail.nationalityCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" dVlaue="CHN"enabled="false"onvaluechanged="nationChange" />
			
					<label>省/直辖区：</label>
					<input name="conDetail.provinceCd" id="province" required="true" class="nui-combobox nui-form-input" valueField="dictid" textField="dictname" onvaluechanged="provinceChange" />
				
					<label>城市：</label>
					<input name="conDetail.cityCd" id="city"required="true" class="nui-combobox nui-form-input" valueField="dictid"  
					 textField="dictname" onvaluechanged="cityChange" />
	
					<label>区（县）：</label>
					<input name="conDetail.district" id="town" required="true" class="nui-combobox nui-form-input" valueField="dictid" textField="dictname"/>

					<label>街道：</label>
					<input name="conDetail.streetAddress" required="true"  class="nui-textbox" vtype="maxLength:200" />
				
					<label>资金支付方式：</label>
					<input id="tbConContractInfo.payWay" name="tbConContractInfo.payWay" required="true"enabled="false" class="mini-dictradiogroup" dictTypeId="CDXY0144" />
					</div>
					
					<div id='ht2'class="nui-dynpanel"  columns="4" >
<!-- 					<label>提前还款最低金额：</label> -->
<!-- 					<input id="tbConContractInfo.leastPrepayAmount" name="tbConContractInfo.leastPrepayAmount"  dataType="currency"vtype="float;maxLength:20"class="nui-textbox nui-form-input"  /> -->

					<label>提前还款是否计收违约金：</label>
					<input id="tbConContractInfo.prepaymentPenalty" name="tbConContractInfo.prepaymentPenalty" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" onvaluechanged="onselectPrepaymentPenalty" />
						
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
					 	<input id="tbConAttachedInfo.addClause" class="nui-textarea" name="tbConAttachedInfo.addClause"  colspan="3" style="width: 100%;height: 70px"/>
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
            	nui.get("rateFloatProportion").setValue(o.apvrate.rateFloatProportion);//浮动比例
            	nui.get("yearrate").setValue(o.apvrate.yearRate);//固定利率
            	nui.get("tbConContractInfo.payWay").setValue("1");
            	nui.get("conDetail.nationalityCd").setValue("CHN");
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
		//利率模块处理结束
        nui.get("con_contract_detail_info_save").setEnabled(false);
		var o = form.getData();
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
	
		/*********************************国家省市县区*******************************/	
		function nationChange(e){
			git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){
				nui.get("province").setData(data.items);
			});
			nui.get('city').setData(null);//清空“城市”下拉框值
			nui.get('town').setData(null);//清空“区/县”下拉框值
		}
		function provinceChange(e){
			git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){
				var parentid = e.sender.getValue();
				if(parentid=='110000000000'||parentid=='120000000000'||parentid=='310000000000'||parentid=='500000000000'){
					var cityDate = nui.encode([{"dictid":parentid,"dictname":e.sender.getText()}]);
					nui.get('city').setData(cityDate);
				}else{
					nui.get('city').setData(data.items);
				}
			});
			nui.get('town').setData(null);//清空“区/县”下拉框值
		}
		function cityChange(e){
			git.getDistrictsByParentidEcif(e.sender.getValue(),function(data){
				nui.get('town').setData(data.items);
			});
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