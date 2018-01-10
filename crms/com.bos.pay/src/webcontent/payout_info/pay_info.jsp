<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil"%>
<%@page import="com.bos.pub.ServiceModuleName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 08:46:44
  - Description:
-->
<head>
<title>放款信息</title>
<%@include file="/common/nui/common.jsp"%>
<%@include file="/crd/util/CrdUtil.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<% 
  String addr = "/"+(String)request.getParameter("addr");
  System.out.println("pAddress=========="+addr);
  String limitMode = ConfigurationUtil.getContributionConfig(ServiceModuleName.PUB,"risk_limit_config", "risk_limit", "limitMode");
 %>
<body>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
		<input id="processInstId" name="processInstId" class="nui-hidden nui-form-input" />
		<input name="conInfo.contractAmt" required="false" class="nui-hidden nui-form-input"  id="conInfo.contractAmt"/>
		<input name="tbLoanInfo.loanId" required="false" class="nui-hidden nui-form-input"  />
		<input name="tbLoanInfo.exchangeRate" id="tbLoanInfo.exchangeRate" class="nui-hidden nui-form-input"  />
		<input name="tbLoanInfo.rmbAmt" id="tbLoanInfo.rmbAmt"  class="nui-hidden nui-form-input"  />
		<input name="tbLoanInfo.repayType" id="tbLoanInfo.repayType"  class="nui-hidden nui-form-input"  />
		<input name="conInfo.contractTerm" required="false" class="nui-hidden nui-form-input"  id="conInfo.contractTerm"/>
		<input name="conInfo.conBalance" required="false" class="nui-hidden nui-form-input"  id="conInfo.conBalance"/>
		<input name="conInfo.productType" required="false" class="nui-hidden nui-form-input"  id="conInfo.productType"/>
		<input name="conInfo.endDate" required="false" class="nui-hidden nui-form-input"  id="conInfo.endDate"/>
		<input name="conInfo.beginDate" required="false" class="nui-hidden nui-form-input"  id="conInfo.beginDate"/>
	 	
	 	<input name="conInfo.contractTerm" required="false" class="nui-hidden nui-form-input"  id="conInfo.contractTerm"/>
	 	<input name="conInfo.cycleUnit" required="false" class="nui-hidden nui-form-input"  id="conInfo.cycleUnit"/>
	 	
	 	<input id="tbLoanInfo.loanSubject1" name="tbLoanInfo.loanSubject1" class="nui-hidden nui-form-input" />
		<input id="tbLoanInfo.userNum" name="tbLoanInfo.userNum" class="nui-hidden nui-form-input" />
		<input id="tbLoanInfo.orgNum" name="tbLoanInfo.orgNum" class="nui-hidden nui-form-input" />
		<input id="tbLoanInfo.productType" name="tbLoanInfo.productType" class="nui-hidden nui-form-input" />
		<input id="tbLoanInfo.trusToPayFlg" name="tbLoanInfo.trusToPayFlg" class="nui-hidden nui-form-input" />
		<input id="loanrate.loanrateId" name="loanrate.loanrateId" class="nui-hidden nui-form-input" />
		<input id="loanrate.rateType" name="loanrate.rateType" class="nui-hidden nui-form-input" />
		<input id="loanrate.baseRateValue" name="loanrate.baseRateValue" class="nui-hidden nui-form-input" />
		<input id="loanrate.floatWay" name="loanrate.floatWay" class="nui-hidden nui-form-input" />
		<input id="loanrate.rateFloatProportion" name="loanrate.rateFloatProportion" class="nui-hidden nui-form-input" />
		<input id="tbBizAmountDetailApprove.detailBalance" name="tbBizAmountDetailApprove.detailBalance" class="nui-hidden nui-form-input" />
		 <fieldset>
	  	<legend>
	   		<span>放款信息</span>
	    </legend>
		<div class="nui-dynpanel" columns="1">
		 	<jsp:include page="<%=addr %>"/>
		</div>
		<div class="nui-dynpanel" columns="4"> 	
		 	<label class="nui-form-label">出账机构：</label>
			<input id="tbLoanInfo.loanOrg" name="tbLoanInfo.loanOrg" required=true class="nui-buttonEdit" allowInput="false" onbuttonclick="selectAccOrg()" dictTypeId="org"/>
  			
  			<label class="nui-form-label" id="payOutFlgId">借新还旧是否归还利息：</label>
			<input id="tbLoanInfo.payOutFlag" name="tbLoanInfo.payOutFlag" required=true class="nui-dictcombobox nui-form-input" allowInput="false" dictTypeId="XD_0002" />
  			
  			<label class="nui-form-label">经办人：</label>
			<input id="tbLoanInfo.userNum" name="tbLoanInfo.userNum" required=true class="nui-text" allowInput="false" dictTypeId="user"/>
				
		 	<label class="nui-form-label">经办机构：</label>
			<input id="tbLoanInfo.orgNum" name="tbLoanInfo.orgNum" required=true class="nui-text" allowInput="false" dictTypeId="org"/>
  		</div>	
  	<fieldset>
  <div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;width: 100%;" borderStyle="border:0;">
				<a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="save">保存</a>
		</div>  
</body>

<script type="text/javascript">
	nui.parse();
	CrdUtil.setLimitMode(<%=JspUtil.getStrHaveSign(limitMode) %>);
	var form = new nui.Form("#form");
	var loanId ="<%=request.getParameter("loanId") %>"; 
	var processInstId = "<%=request.getParameter("processInstId")%>";
	var limitMode = <%=JspUtil.getStrHaveSign(limitMode)%>;
	var contractId;
	var productType = "";
	var pjzl;
	var bizHappenType;
	var ynflag;
	var oldloanrate;
	$(document).ready(function(){
		initPage();
	});
	function initPage(){
		var json = nui.encode({"loanId":loanId});
		$.ajax({
	        url: "com.bos.payInfo.PayInfo.getPayInfo.biz.ext",
	        type: 'POST',
	        data: json,
        	async: false,
	        contentType:'text/json',
	        cache: false,
	        success: function (mydata) {
	        	var o = nui.decode(mydata);
	        	o.tbLoanInfo.ntcNum = o.tbLoanInfo.loanNum;
	        	contractId = o.tbLoanInfo.contractId;
	        	if(o.loanrate.yearRate !=null && o.loanrate.yearRate !=""){
		        	o.loanrate.yearRate = o.loanrate.yearRate.toFixed(4);//将后台的利率值 取小数点后4位 返显到前台
		        	oldloanrate=o.loanrate.yearRate;
	        	}
	        	form.setData(o);
    			productType = o.conInfo.productType;
    			pjzl = o.tbLoanInfo.pjzl;
            	//如果没有还款方式，默认1200
            	if(o.tbLoanInfo.repayType==null || o.tbLoanInfo.repayType==''){
            		nui.get("tbLoanInfo.repayType").setValue('1200');
            	}
            	var flagType = o.bizHappenType;
            	bizHappenType = flagType.substring(0,2);
            	var ynflag = flagType.substring(3,4);
            	if(bizHappenType !='06'){
            		$("#payOutFlgId").hide();
            		nui.get("tbLoanInfo.payOutFlag").hide();
            	}else{
            		if(!nui.get("tbLoanInfo.payOutFlag").getValue()){
            			nui.get("tbLoanInfo.payOutFlag").setValue('1');
            		}
            	}
            	if(ynflag=="1"){
            		nui.get("loanrate.yearRate").setEnabled(true);
            	}else{
            		nui.get("loanrate.yearRate").setEnabled(false);
            	}
            	nui.get("processInstId").setValue(processInstId);
			}
    	});
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
        var proFlag ="<%=request.getParameter("proFlag") %>"; 
		if("1" != proFlag){
			nui.get("btnCreate").hide();
			form.setEnabled(false);
		}
		
		var o = form.getData();
 		var beginDate = o.tbLoanInfo.beginDate;
 		var endDate = o.tbLoanInfo.endDate;
 		if((null!=beginDate||""!=beginDate||'undefinded'!=beginDate)&&(null!=endDate||""!=endDate||'undefinded'!=endDate)){
 			//countRate();
 		}
 		//initDate();//初始化期限
 		//nui.get("tbLoanInfo.unit").setValue("04");//为空就默认为月
 		//getConEndate();
	}
	//页面初始化时 给起始日期赋值
	function initDate(){
		var conBeginDate = nui.get("conInfo.contractTerm").getValue();//合同期限
		var conEndDate = nui.get("conInfo.cycleUnit").getValue();//合同期限单位
		var loanTerm = nui.get("tbLoanInfo.term").getValue();//放款期限
     	var loanUnit = nui.get("tbLoanInfo.unit").getValue();//放款期限单位
		if(loanTerm =="" || loanUnit == ""){
			if(conBeginDate != ""){
	        	nui.get("tbLoanInfo.term").setValue(conBeginDate);//放款的期限
	       }
	       if(conEndDate != ""){
	        	nui.get("tbLoanInfo.unit").setValue(conEndDate);//放款期限单位
	       }else{
	        	nui.get("tbLoanInfo.unit").setValue("04");//为空就默认为月
	       }
		}
	}
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        if(nui.get("tbLoanInfo.endDate").getValue() == ""){
        	return alert("请填入正确的期限");
        }
        var jsonch = nui.encode({"loanId":loanId});
        var flg=true;
		$.ajax({
	        url: "com.bos.payInfo.PayInfo.queryRongDan.biz.ext",
	        type: 'POST',
	        data: jsonch,
        	async: false,
	        contentType:'text/json',
	        cache: false,
	        success: function (mydata) {
            	var flagType = mydata.bizHappenType;
            	var ynflag = flagType.substring(3,4);
            	if(ynflag=="1"){
            		var yearRa = nui.get("loanrate.yearRate").getValue();
            		if(parseFloat("5.2")>parseFloat(yearRa)){
            			flg=false;
            			nui.alert("长虹融单最低利率不低于5.2%!");
            		}
            	}
			}
    	});
    	if(!flg){
    		return flg;
    	}
        nui.get("btnCreate").setEnabled(false);
        
        //如果出账机构没有结算机构不让选
		/* var czjg = nui.get("tbLoanInfo.loanOrg").getValue();
        var json1 = {"orgId":czjg};
   	    msg = exeRule("RLON_0010","1",json1);
   	    if(null != msg && '' != msg){
	   		nui.alert(msg);
	   		nui.get("btnCreate").setEnabled(true);
	   		return "1";
   	    } */
        var o = form.getData();
		if(o.tbLoanInfo.loanAmt==null|| o.tbLoanInfo.loanAmt==''){
        	nui.alert("出账金额为0");
	   		nui.get("btnCreate").setEnabled(true);
	   		return "1";
        }
        
    	if((o.tbLoanInfo.beginDate).substr(0,10)==(o.tbLoanInfo.endDate).substr(0,10)){
    		nui.alert("起期不能等于止期");
    		nui.get("btnCreate").setEnabled(true);
    		return;
    	}
        //合同起期大于营业日期即借据起期，不让申请
        if((o.tbLoanInfo.beginDate).substr(0,10)<(o.conInfo.beginDate).substr(0,10)){
    		nui.alert("借据起期小于合同起期");
    		nui.get("btnCreate").setEnabled(true);
    		return;
    	}
        
        //默认参数
        //止付标志
        o.tbLoanInfo.stopPayFlg = '0';
        //对公受托支付标志位否 --取消，流贷和固贷根据合同来取
        //对公 非流贷和固贷 受托支付标志为否
        if(startWith(productType,'01')==1 && o.tbLoanInfo.trusToPayFlg!='1'){
        	o.tbLoanInfo.trusToPayFlg = '0';
        }
       
        //受托支付标志--考虑为空的情况
        if(o.tbLoanInfo.trusToPayFlg !='1'){
        	o.tbLoanInfo.trusToPayFlg = '0';
        }
        
        //批扣标志（垫款为0，但垫款不用此页面）
        o.tbLoanInfo.batFlg = '1';
        //if(productType!='01008001'|| productType!='01008002' || productType!='01008010'){
        	//o.tbLoanInfo.drweBnkAdr = '';
        //}
     	if(!CrdUtil.checkLimit( "com.bos.crd.LimitService.riskLimitValid.biz.ext",
     		{"contractId":contractId,"amt":o.tbLoanInfo.loanAmt,"orgNum":o.tbLoanInfo.orgNum })){
	 		return ;
	 	}
        var json = nui.encode(o);
		$.ajax({
	        url: "com.bos.payInfo.PayInfo.savePayInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (mydata) {
	        	if(mydata.msg==null||mydata.msg==''){
	        		nui.alert("保存成功！");
		        	nui.get("btnCreate").setEnabled(true);
		        	initPage();
	        	}else{
	        		nui.alert(getMsg(mydata.msg,true));
		        	nui.get("btnCreate").setEnabled(true);
		        	initPage();
	        	}
			}
    	});
	}

	function getMsg(msg,isHtml){
		if (msg.substr(0, 1) == "{" && msg.substr(msg.length - 1, 1) == "}") {
			var error = nui.decode(msg);
			if (error.limitCode) {
				return error.msg + " -> "
						+ crd.getLimitCodeText(error.limitType,
								error.limitCode);
			} else {
				return error.msg;
			}
		} else {
			return msg;
		}
	}
	//选择机构
		 function selectEmpOrg(e) {
	        var btnEdit = this;
	        nui.open({
	            url: nui.context + "/utp/org/employee/select_all_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    
	                    if (data) {
	                    
	                    	if(data.auditbankno==null || data.auditbankno=='' || data.auditbankno == 'null'){
	                    		nui.alert("该机构无对应核算机构!");
	                    	}
	                    	
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });            
	    }
	//期限校验
	function termChange(){
		var conterm = nui.get("conInfo.contractTerm").getValue();
		var loanterm = nui.get("tbLoanInfo.loanTerm").getValue();
		if(parseFloat(conterm)<parseFloat(loanterm)){
			nui.alert("出账期限不能大于合同期限："+conterm);
			nui.get("tbLoanInfo.loanTerm").setValue('');
		}
		begindatechange();
	}
	//金额校验
	function amtChange(){
		var loanamt = nui.get("tbLoanInfo.loanAmt").getValue();
		var conbalance = nui.get("conInfo.conBalance").getValue();
		var exchangeRate = nui.get("tbLoanInfo.exchangeRate").getValue();
		if(conbalance==null||conbalance==''||conbalance=='null'){
			conbalance=0;
		}
		if(parseFloat(conbalance)<(parseFloat(loanamt))){
			nui.alert("出账金额不能大于合同可用金额");
			nui.get("tbLoanInfo.loanAmt").setValue('');
			return;
		}else{
			nui.get("tbLoanInfo.rmbAmt").setValue(parseFloat(loanamt)*parseFloat(exchangeRate));
		}
		//批复明细可用校验
		var detailBalance = nui.get("tbBizAmountDetailApprove.detailBalance").getValue();
		if(parseFloat(detailBalance)<(parseFloat(loanamt)*parseFloat(exchangeRate))){
			nui.alert("出账金额不能大于批复明细可用金额");
			nui.get("tbLoanInfo.loanAmt").setValue('');
			return;
		}
		
	}
 	function validateBeginDate(){
 		var o = form.getData();
 		var beginDate = o.tbLoanInfo.beginDate;
 		var endDate = o.tbLoanInfo.endDate;
 		var conbeginDate = o.conInfo.beginDate;
 		var conEndDate = o.conInfo.endDate;//合同的止期
 		if(beginDate==null || beginDate==''){
 			return;
 		}
 		if(beginDate.substr(0,10)<conbeginDate.substr(0,10)){
	 		nui.alert("起期不能小于合同起期"+conbeginDate.substr(0,10));
	 		nui.get("tbLoanInfo.beginDate").setValue('');
	 		return;
	 	}
	 	if(beginDate.substr(0,10)>conEndDate.substr(0,10)){
	 		nui.alert("起期不能大于合同到期日"+conEndDate.substr(0,10));
	 		nui.get("tbLoanInfo.beginDate").setValue('');
	 		return;
	 	}
	 	//当选择合同起始日期时 若期限不为空 则计算到期日
	 	getConEndate();
 		if(endDate==null || endDate==''){
 			return;
 		}
 		if(endDate<beginDate){
	 		/* nui.alert("止期不能小于起期");
	 		nui.get("tbLoanInfo.beginDate").setValue('');
	 		return; */
	 	}
	 	countRate();
 	}
 	//当下拉框期限单位的值发生改变时 也计算止期
 	function countConEndate(){
 		getConEndate();
 	}
 	//通过起始日期和期限 计算出放款到期日期
 	function getConEndate(){
 		var loanBeginDate = nui.get("tbLoanInfo.beginDate").getValue();//放款起始日期
 		var loanTerm = nui.get("tbLoanInfo.term").getValue();//放款期限
 		var loanUnit = nui.get("tbLoanInfo.unit").getValue();//放款期限单位
 		var conTerm = nui.get("conInfo.contractTerm").getValue();//合同期限
		var conUnit = nui.get("conInfo.cycleUnit").getValue();//合同期限单位
 		var unit;//期限换算
 		var loanQxdw;//页面显示用
 		//放款期限换算
 		if(loanUnit != ""){
 			if(loanUnit == "01"){//年
 				unit = "Y";
 			}if(loanUnit == "03"){//季
 				unit = "J";
 			}if(loanUnit == "04"){//月
 				unit = "M";
 			}if(loanUnit == "05"){//日
 				unit = "D";
 			}
 		}
 		//合同的期限换算
 		if(conUnit != ""){
 			if(conUnit == "01"){//年
 				loanQxdw = "年";
 			}if(conUnit == "03"){//季
 				loanQxdw = "季";
 			}if(conUnit == "04"){//月
 				loanQxdw = "月";
 			}if(conUnit == "05"){//日
 				loanQxdw = "日";
 			}
 		}
 		//放款的期限必须小于等于合同的期限
 		if(loanTerm != "" && conTerm != "" && parseInt(conTerm) < loanTerm){
			nui.get("tbLoanInfo.endDate").setValue('');
 			alert("放款期限不能大于合同期限"+conTerm+loanQxdw);
 			return; 
 		}
 		//调用后台计算到期日期的值
		if(loanBeginDate != "" && loanTerm != "" && loanUnit != ""){
			var date = loanBeginDate.substring(0,10);//截取起始日期前十位
			var json = nui.encode({"qxdw":unit,"qx":loanTerm,"rq":date});
			$.ajax({
				url : "com.bos.pub.dateCountUtil.monthAddDate.biz.ext",
				data : json,
				type : "POST",
				cache : false,
				contentType : "text/json",
				success : function(data){
					nui.get("tbLoanInfo.endDate").setValue(data.dqrq);
					countRate();//期限变动 到期日期改变时 计算利率
				}
			});
		} 		
 	}
 	
 	//通过起始日期、到期日期、期限单位计算期限
	function getTerm(){
		var beginDate = nui.get("tbLoanInfo.beginDate").getValue().substring(0,10);//合同起期
		var endDate = nui.get("tbLoanInfo.endDate").getValue().substring(0,10);//合同止期
		var termUnit = nui.get("tbLoanInfo.unit").getValue();//合同期限单位
		var creditTerm = nui.get("conInfo.contractTerm").getValue();//批复里的期限
		if(beginDate != null && termUnit != null){
			if(endDate < beginDate){
				nui.get("tbLoanInfo.endDate").setValue("");//将合同止期置为空
				alert("不能小于起始日期");
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
							nui.get("tbLoanInfo.endDate").setValue("");
							return alert("不能大于合同期限"+creditTerm+"个月,请重新选择到期日期");
						}
					}
					nui.get("tbLoanInfo.term").setValue(data.term);
					countRate();//返现到期日后计算利率
				}
			});
		}
	}
 	
 	function validateEndDate(){
 		var o = form.getData();
 		var beginDate = o.tbLoanInfo.beginDate;
 		var endDate = o.tbLoanInfo.endDate;
 		var conEndDate = o.conInfo.endDate;
 		if(endDate==null || endDate==''){
 			return;
 		}
	 	if(endDate.substr(0,10)>conEndDate.substr(0,10)){
	 		/* nui.alert("止期不能大于合同到期日"+conEndDate.substr(0,10));
	 		nui.get("tbLoanInfo.endDate").setValue('');
	 		return; */
	 	}
	 	if(beginDate==null || beginDate==''){
 			return;
 		}
 		if(endDate<beginDate){
	 		nui.alert("止期不能小于起期");
	 		nui.get("tbLoanInfo.endDate").setValue('');
	 		return;
	 	}
	 	countRate();
 	}
 	//银承的提示信息不一样。。。
 	function validateEndDateyc(){
 		var o = form.getData();
 		var beginDate = o.tbLoanInfo.beginDate;
 		var endDate = o.tbLoanInfo.endDate;
 		var conEndDate = o.conInfo.endDate;
 		if(endDate!=null && endDate!=''){
 			if(endDate<beginDate){
	 			nui.alert("承兑到期日不能小于承兑起始日");
	 			nui.get("tbLoanInfo.endDate").setValue('');
	 			return;
	 		}
	 		
	 		if(endDate>conEndDate){
	 			nui.alert("承兑到期日不能大于合同到期日"+conEndDate.substr(0,10));
	 			nui.get("tbLoanInfo.endDate").setValue('');
	 			return;
	 		}
 		}
 	}
 	//计算基准利率
 	function countRate(){
 		var o = form.getData();
 		var beginDate = o.tbLoanInfo.beginDate;
 		var endDate = o.tbLoanInfo.endDate;
 		var loanlength=0;
 		var ratetype = o.loanrate.rateType;
 		if(o.loanrate.rateType!=null && o.loanrate.rateType!=''){//有“利率类型”说明有利率信息且不是国结贷款
 			//根据起止期取期限
 			var json1 = nui.encode({"beginDate":beginDate,"endDate":endDate});
			$.ajax({
	            url: "com.bos.bizApply.bizApply.getLoanlength.biz.ext",
	            type: 'POST',
	            data: json1,
        		async: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	var o = nui.decode(mydata);
	            	loanlength = o.loanlength;
				}
			});
 			//公积金利率/普通贷款利率类型
 			var ratettype = '1';
 			if(productType=='02005001'||productType=='02005003'||productType=='02005010'){
 				ratettype = '2';
 			}
 			//根据期限取基准利率
			var json = nui.encode({"loanlength":loanlength,"rateType":ratettype});
			$.ajax({
	            url: "com.bos.bizApply.bizApply.getBasicrate.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	var o = nui.decode(mydata);
	            	nui.get("loanrate.baseRateValue").setValue(o.basicrate);
	            	//nui.get("loanrate.baseRateValue").validate();
	            	form.validate();
	            	if(ratetype=='1'){//固定利率
		 				return;
		 			}
	            	countRate1();
				}
			});
 		}
 	}
 	
	//计算利率
	function countRate1(){
 		var baseRate = nui.get("loanrate.baseRateValue").getValue();
 		nui.get("loanrate.yearRate").setValue('');
 		var floatWay = nui.get("loanrate.floatWay").getValue();
 		var rateFloatProportion = nui.get("loanrate.rateFloatProportion").getValue();
 		if(floatWay&&baseRate){
 			if(rateFloatProportion){
 				if(floatWay=='1'){//上浮
 					nui.get("loanrate.yearRate").setValue((parseFloat(baseRate)+parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01).toFixed(6));
 					oldloanrate=(parseFloat(baseRate)+parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01).toFixed(6);
 					nui.get("loanrate.yearRate").validate();
 				}else{//下浮
 					nui.get("loanrate.yearRate").setValue((parseFloat(baseRate)-parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01).toFixed(6));
 					oldloanrate=(parseFloat(baseRate)-parseFloat(baseRate)*parseFloat(rateFloatProportion)*0.01).toFixed(6);
 					nui.get("loanrate.yearRate").validate();
 				}
 				form.validate();
 			}
 		}
 	}
 	//利率类型触发事件
    function onselectType(){
		var reateType= nui.get("loanrate.rateType").getValue();
		if(reateType=="2"){//浮动利率
			//显示浮动利率
			dealRate('show');
		}else if(reateType=="1"){//固定利率
			//隐藏浮动利率
			dealRate('hide');
		}
		//刷新table
		nui.get('loaninfo').refreshTable();
	}
	//隐藏、显示利率
	function dealRate(dealType){
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
		nui.get('loaninfo').refreshTable();
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
		nui.get('loaninfo').refreshTable();
	}
	
	function checkLength1(a){
		var ret = maxLength(a,60)
		if(false == ret){
			nui.alert("超过最大字符长度:60");
			nui.get("tbLoanInfo.drweBnkNm").setValue('');
		}
	}
	
	function maxLength(a, c) {
        //修改中文所占长度为2，其余字符长度1--begin
        var re = /[^\u4e00-\u9fa5]/;
		var len = 0;
		for(var i=0;i<a.length;i++){
			var t = a.charAt(i);
			if(re.test(t)){
				len = parseInt(len)+1;
			}else{
				len = parseInt(len)+2;//如果是中文，增加2长度
			}
		}
        if(len <= c){
            return true
        } else {
            return false
        }
    }
    
    function startWith(src,str){
		if(str==null || str==''|| src==null || src==''){
			return -1;
		}
		if(src.substr(0,str.length)==str){
			return 1;
		}else{
			return -1;
		}
	}
	//国结的产品---一个合同只能做一次出账 而且出账金额要等于合同金额
		if(productType=="01007001"||productType=="01007003"||productType=="01007004"||productType=="01007009"||
           productType=="01007012"||productType=="01007011"||productType=="01007006"||productType=="01007005"||
           productType=="01007010"||productType=="01007013"||productType=="01007014"){
		   var conAmt = nui.get("conInfo.contractAmt").getValue();
		   nui.get("tbLoanInfo.loanAmt").setValue(conAmt);
		   nui.get("tbLoanInfo.loanAmt").setEnabled(false);
		}
	function selectAccOrg(){
		   var org = nui.get("tbLoanInfo.orgNum").getValue();
		   var productId = nui.get("conInfo.productType").getValue();
		   var loanOrg = nui.get("tbLoanInfo.loanOrg").getValue();
	        nui.open({
	            url: nui.context + "/pay/payout_info/pay_org_select.jsp?orgCode="+org+"&productId="+productId+(pjzl?("&pjzl="+pjzl):""),
	            showMaxButton: true,
	            title: "选择会计机构",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {
	                if (action == 'ok') {
	                	var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						nui.get("tbLoanInfo.loanOrg").setText(null);
						nui.get("tbLoanInfo.loanOrg").setValue(data.ACC_ORG_NO);
						if("1"==data.COL1){
							nui.get("tbLoanInfo.loanOrg").setText(data.ACC_ORG_ID);
						}
	                }else if(loanOrg){//此处不能删除
	                }else{
	                	nui.alert("该机构无对应核算机构!");
	                }
	            }
	        }); 
	}
	function onselectRate(){
		var endDate = nui.get("tbLoanInfo.endDate").getValue();
		if(!endDate){
			nui.alert("请填写借据到期日期!");
			return false;
		}
		var loanRate = nui.get("loanrate.yearRate").getValue();
		if(oldloanrate){
			if(parseFloat(oldloanrate)>parseFloat(loanRate)){
				nui.get("loanrate.yearRate").setValue(oldloanrate);
				nui.alert("利率只能上浮不能下调");
				return false;
			}
		}
		var rateType = nui.get("loanrate.rateType").getValue();
			var baseRate = nui.get("loanrate.baseRateValue").getValue();
			baseRate = baseRate*4;
			if(parseFloat(loanRate)>parseFloat(baseRate)){
				nui.get("loanrate.yearRate").setValue(oldloanrate);
				nui.alert("利率不能超过基准利率400%");
				return false;
			}
	}
</script>
</html>