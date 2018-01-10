<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-06-24 08:41:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>新增合同相关的保证金</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="form1" style="width:auto;height:auto;overflow:hidden;">
		<input name="grtMortgageBasic.suretyId"  class="nui-hidden" id="grtMortgageBasic.suretyId"/>
		<input name="grtMortgageBasic.orgNum"  class="nui-hidden" id="grtMortgageBasic.orgNum" value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>"/>
		<input name="grtMortgageBasic.userNum"  class="nui-hidden"  id="grtMortgageBasic.userNum"  value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>"/>
		<input name="grtMortgageBasic.createTime"  class="nui-hidden" id="grtMortgageBasic.createTime" value="<%=com.bos.pub.GitUtil.getBusiDate() %>"/>
		<input name="grtMortgageBasic.updateTime"  class="nui-hidden"  id="grtMortgageBasic.updateTime"  value="<%=com.bos.pub.GitUtil.getBusiDate()%>" />
		<input name="grtMortgageBasic.collType"  class="nui-hidden"  id="grtMortgageBasic.collType"  value="03" />
		<input name="grtMortgageBasic.partyId"  class="nui-hidden"  id="grtMortgageBasic.partyId"  value="<%=request.getParameter("partyId")%>" />
		<input name="grtMargin.suretyKeyId"  class="nui-hidden" id="grtMargin.suretyKeyId"/>
		<input name="bizGrtRel.relationId"  class="nui-hidden"  id="bizGrtRel.relationId"  />
		<input name="bizGrtRel.applyId"  class="nui-hidden"  id="bizGrtRel.applyId"  value="<%=request.getParameter("applyId") %>" />
		<input name="conSubGrtRel.relationId"  class="nui-hidden"  id="conSubGrtRel.relationId" />
		<input name="tbConContractInfo.contractAmt"  class="nui-hidden"  id="tbConContractInfo.contractAmt" />
		<input name="tbConSubcontract.bzjje"  class="nui-hidden"  id="tbConSubcontract.bzjje" />
		<input name="conSubGrtRel.subcontractId"  class="nui-hidden"  id="conSubGrtRel.subcontractId"  value="<%=request.getParameter("subcontractId") %>" />
		<div class="nui-dynpanel" columns="4" id="table"  style="width:99.5%;">
			<label>货币代号：</label>
			<input id="hbdh" name="hbdh" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100001"   dValue="01"/>
			<label>钞汇标志：</label>
			<input id="chbz" name="chbz" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100002"   dValue="1"/>
				
			<label>开户人：</label>
			<input name="grtMargin.acctName" required="true" class="nui-textbox nui-form-input"  id="grtMargin.acctName" />
		
			<label>保证金账号：</label>
			<div style="width:123%">
				<input name="grtMargin.marginAccount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="grtMargin.marginAccount" style="width:65%;float:left" />
				<a class="nui-button" id="clear" style="width:16%;float:left" onclick="getAccInfo">查询</a>
			</div>
			
			<label>保证金类型：</label>
			<input name="grtMargin.marginSort" required="true" class="nui-dictcombobox nui-form-input"  id="grtMargin.marginSort" dictTypeId="XD_YWDB0134"/>
			
			<label>开户行：</label>
			<input name="grtMargin.openBank" class="nui-text nui-form-input"  id="grtMargin.openBank" dictTypeId="org"/>
			
			<label>币种：</label>
			<input name="grtMargin.currencyCd" class="nui-text nui-form-input"  id="grtMargin.currencyCd"   dictTypeId="CD000001"/> 
			
			<label>保证金金额：</label>
			<input name="grtMargin.accBalance"  class="nui-text nui-form-input"  id="grtMargin.accBalance"  dataType="currency" vtype="float;maxLength:26;" />
			<label id="availableAmtLabel">可用余额：</label>
			<input name="grtMargin.availableAmt" require="true" class="nui-text nui-form-input"  id="grtMargin.availableAmt"  dataType="currency" vtype="float;maxLength:26;" />
		
		</div>
		
		<div class="nui-dynpanel" columns="4" id="date"  style="width:99.5%;">
			<label id="beginDateLab">起始日期：</label>
			<input name="grtMargin.beginDate" enabled="false" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"  id="grtMargin.beginDate" allowinput="false"  onvaluechanged="dateChange" />
			
			<!-- <label id="endDateLab">到期日期：</label>
			<input name="grtMargin.endDate"enabled="false" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"  id="grtMargin.endDate" allowinput="false" onvaluechanged="dateChange"/> -->
		</div>	
			
			<div class="nui-dynpanel" columns="4" id="table"  style="width:99.5%;">
			
<!-- 			<label id="isJixiLab">是否计息：</label> -->
<!-- 			<input name="grtMargin.isJixi" required="true" class="nui-dictcombobox nui-form-input"  id="grtMargin.isJixi" allowinput="false"  dictTypeId="XD_0002" onvaluechanged="isJixi"/> -->
			
			<label id="rateLab">执行利率(%)：</label>
			<input name="grtMargin.marginRate"  class="nui-textbox nui-form-input"  id="grtMargin.marginRate" vtype="float;range:0,100;maxLength:8" />
			
			<label>追加标志：</label>
			<input name="grtMargin.appendFlag" required="true" class="nui-dictcombobox nui-form-input"  id="grtMargin.appendFlag"  dictTypeId="XD_0002"onvaluechanged="appendFlagChange"/>

			<label>追加金额：</label>
			<input name="grtMargin.appendAmt" required="true" class="nui-textbox nui-form-input"  id="grtMargin.appendAmt"  dataType="currency" vtype="float;maxLength:26;"/>
			
			<%-- <label>经办机构：</label>
			<input name="grtMargin.orgNum"  enabled="false" class="nui-text nui-form-input" value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" />
									
			<label>经办人：</label>
			<input name="grtMargin.userNum" enabled="false" class="nui-text nui-form-input" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user"/>
							
			<label>创建日期：</label>
			<input name="grtMargin.createTime" class="nui-textbox nui-form-input"  value="<%=com.bos.pub.GitUtil.getBusiDate() %>"  enabled="false" />
					
			<label>更新日期：</label>
			<input name="grtMargin.updateTime" class="nui-textbox nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  enabled="false" /> --%>
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="btnCreate" iconCls="icon-ok" onclick="add()" id="add">确定</a>
			<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var view="<%=request.getParameter("view") %>";
		//客户ID
		var partyId="<%=request.getParameter("partyId")%>";
		//业务ID
	    var applyId="<%=request.getParameter("applyId") %>";
    	//押品Id
    	var suretyId="<%=request.getParameter("suretyId") %>";
       	//担保合同ID
    	var subcontractId="<%=request.getParameter("subcontractId") %>";
    	var xgbz="<%=request.getParameter("xgbz") %>";
    	
    	var contractId =  "<%=request.getParameter("contractId") %>";
    	
		var form = new nui.Form("#form1");
		var ljurl;
		var ljurl2;
		
		//隐藏可用余额信息
		//nui.get("grtMargin.availableAmt").hide();
		//$("#availableAmtLabel").hide();
		if (view=="1") {
			form.setEnabled(false);
			nui.get("btnCreate").hide();
			nui.get("clear").hide();
 		}
		if(suretyId!='null'){
			initForm();
		} 
	function initForm() {
		var json=nui.encode({"suretyId":suretyId,"subcontractId":subcontractId,"applyId":applyId,"xgbz":xgbz,"contractId":contractId});
		$.ajax({
	            url: "com.bos.grt.conGrt.getConCashDeposit.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	           		git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            		if(text.grtMargin.marginSort){
	            		nui.get("grtMargin.marginSort").setEnabled(false);
	            		}else{
	            		nui.get("grtMargin.marginSort").setEnabled(true);
	            		}
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
		function add(){
			form.validate(); 
			 if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			} 
			//先新增押品基本信息表、获得主键后再新增保证金	
			var o=form.getData();
			var json=nui.encode(o);
			
			//校验保证金账户可用余额是否大于保证金追加金额
			if(o.grtMargin.availableAmt==''||o.grtMargin.availableAmt==null){
				nui.alert("请点击查询账户余额信息！");
				return;
			}else if((o.grtMargin.availableAmt-o.grtMargin.appendAmt)<0){
				nui.alert("可用余额不足！");
				return;
			}
			
			//保证金比例是100%的时候  给一个提示
			var contractAmt = o.tbConContractInfo.contractAmt;//合同金额---有效的票据金额总和
			var bzjje = o.tbConSubcontract.bzjje;//原来的保证金金额
			var appendAmt = o.grtMargin.appendAmt;//追加的金额 
			if(parseFloat(bzjje)+parseFloat(appendAmt)>parseFloat(contractAmt)){
			
				nui.confirm("保证金比例已经超过100%,确认追加吗？","确认",function(action){
            		if(action!="ok") return;
            		$.ajax({
	            		url: "com.bos.grt.conGrt.addConCashDeposit.biz.ext",
	            		type: 'POST',
	            		data: json,
	            		cache: false,
	            		contentType:'text/json',
	            		success: function (text) {
	            			if(text.msg =="保存成功！"){
	            				nui.alert(text.msg); 
	            			}
	            			CloseWindow("ok");
	            		},
	            		error: function (jqXHR, textStatus, errorThrown) {
	                		nui.alert(jqXHR.responseText);
	            		}
	        		});
            	}); 
			}else{
				$.ajax({
	            		url: "com.bos.grt.conGrt.addConCashDeposit.biz.ext",
	            		type: 'POST',
	            		data: json,
	            		cache: false,
	            		contentType:'text/json',
	            		success: function (text) {
	            			if(text.msg =="保存成功！"){
	            				nui.alert(text.msg); 
	            			}
	            			CloseWindow("ok");
	            		},
	            		error: function (jqXHR, textStatus, errorThrown) {
	                		nui.alert(jqXHR.responseText);
	            		}
	        		});
			}
		}
		
		
		function getAccInfo(){
	    	//return;
	    	var CurrCode = nui.get("hbdh").getValue();
    		var CashFlag = nui.get("chbz").getValue();
	    	var AcctNo = nui.get("grtMargin.marginAccount").getValue();
	    	if(AcctNo == null || AcctNo == ''){
	    		alert("请输入账号！");
	    		return;
	    	}
	    	AcctNo = AcctNo.trim();
	    	var zhm = nui.get("grtMargin.acctName").getValue();
	    	if(zhm == null || zhm == ''){
	    		alert("请输入账户名称！");
	    		return;
	    	}
	    	zhm = zhm.trim();
	    	CurrCode = CurrCode.trim();
    		CashFlag = CashFlag.trim();
	    	 var json=nui.encode({"acctInd":AcctNo, "currCode": CurrCode , "cashFlag" :CashFlag});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	var code = text.code;
		        	if(code != 'AAAAAAA'){
		        		nui.alert(message);
		        		nui.get("grtMargin.openBank").setValue('');
		        		nui.get("grtMargin.openBank").validate();
		        		return;
		        	}
		        	var cusName = text.hxresponse.oxd052ResBody.custName;
		        	cusName = cusName.trim();
		        	
		        	if(cusName != zhm){
		        		nui.alert("账户名与账号不匹配!");
		        		nui.get("grtMargin.openBank").setValue('');
		        		nui.get("grtMargin.openBank").validate();
		        		return;
		        	}
// 		        	if(text.queryAcc.rcrdSt=="1"||text.queryAcc.rcrdSt=="4"||text.queryAcc.rcrdSt=="1"){
// 		        	}else{
// 		        		nui.alert("只能添加状态为有效、冻结的账户");
// 		        		return;
// 		        	}
		        	
		        	//var orgid = text.queryAcc.rgonCd+text.queryAcc.branchId;
		        	//var beginDate=text.queryAcc.acctDt.substr(0,4)+"-"+text.queryAcc.acctDt.substr(4,2)+"-"+text.queryAcc.acctDt.substr(6,2);//起始日期
		        	//var endDate=text.queryAcc.expDt.substr(0,4)+"-"+text.queryAcc.expDt.substr(4,2)+"-"+text.queryAcc.expDt.substr(6,2);//到期日期
	        		//var acctCd=text.queryAcc.acctCd//科目代号
		        	//if(text.queryAcc.storeCd=="2"){//0-对公活期1-对私活期2-定期3-贷款4-内部 5-表外
		        	//	if("220412,220432,220434,220452,220499".indexOf(acctCd)==-1){
		        	//		return alert("定期保证金账号科目代号只能为信用证定期保证金、银行承兑汇票定期保证金、商业承兑汇票定期保证金、保函定期保证金或其他定期保证金");
		        	//	}
		        	//	nui.get("grtMargin.marginSort").setValue("01");//定期
		        	//	nui.get("grtMargin.beginDate").setValue(beginDate);
 		        	//	nui.get("grtMargin.endDate").setValue(endDate);
		        	//}else{
		        	//	if("220411,220431,220433,220451,220491".indexOf(acctCd)==-1){
		        	//		 return alert("活期保证金账号科目代号只能为信用证活期保证金、银行承兑汇票活期保证金、商业承兑汇票活期保证金、保函活期保证金或其他活期保证金");
		        	//	}
		        	//	nui.get("grtMargin.marginSort").setValue("02");//活期
		        	//}
		        	if(text.hxresponse.oxd052ResBody.depositType == "14" || text.hxresponse.oxd052ResBody.depositType =="16" 
		        	   || text.hxresponse.oxd052ResBody.depositType =="23"){
		        		nui.get("grtMargin.marginSort").setValue("02");
		        	}
		        	if(text.hxresponse.oxd052ResBody.depositType == "15" || text.hxresponse.oxd052ResBody.depositType =="17" 
		        	   || text.hxresponse.oxd052ResBody.depositType == "24"){
		        		nui.get("grtMargin.marginSort").setValue("01");
		        	}
		        	nui.get("grtMargin.openBank").setValue(text.hxresponse.oxd052ResBody.openBrch);
		        	nui.get("grtMargin.currencyCd").setValue(text.hxresponse.oxd052ResBody.currCode);//币种
		        	nui.get("grtMargin.accBalance").setValue(text.hxresponse.oxd052ResBody.accrrestAmt);//余额
		        	nui.get("grtMargin.availableAmt").setValue(text.hxresponse.oxd052ResBody.availableAmt);//可用余额
		        	nui.get("grtMargin.marginAccount").setValue(AcctNo);
		        	nui.get("grtMargin.acctName").setValue(zhm);
		        	if(text.hxresponse.oxd052ResBody.openAcctDate){
		        		var beginDate = text.hxresponse.oxd052ResBody.openAcctDate.substr(0,4)+"-"+text.hxresponse.oxd052ResBody.openAcctDate.substr(4,2)+"-"+text.hxresponse.oxd052ResBody.openAcctDate.substr(6,2);
		        		nui.get("grtMargin.beginDate").setValue(beginDate);
		        	}
		        	nui.get("grtMargin.openBank").validate();
		        	nui.get("grtMargin.currencyCd").validate();
		        	nui.get("grtMargin.availableAmt").validate();
		        	nui.get("grtMargin.accBalance").validate();
		        	//账户标识
		        	/* var zhbs = text.queryAcc.acctTp;
		        	if(zhbs=='0'){
		        		zhbs = '12';
		        	}else if (zhbs=='1'){
		        		zhbs = '11';
		        	}else if (zhbs == '4'){
		        		zhbs = '60';
		        	}else{
		        		nui.alert("不支持的账户类型!");
		        		nui.get("grtMargin.openBank").setValue('');
		        		nui.get("grtMargin.openBank").validate();
		        		return;
		        	} */
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    }
		
		function isJixi(e){
			if(e.value=='1'){	//1表示是
				nui.get("grtMargin.marginRate").setValue("");
				$("#rateLab").show();
				nui.get("grtMargin.marginRate").show();
				nui.get("grtMargin.marginRate").required=true;
				form.validate();
			}else{
				$("#rateLab").hide();
				nui.get("grtMargin.marginRate").setValue("");
				nui.get("grtMargin.marginRate").hide();
			}
		}
		//追加标识变更
		function appendFlagChange(){
			var appendFlag=nui.get("grtMargin.appendFlag").getValue();
			if(appendFlag=='1'){//追加标识为是
				nui.get("grtMargin.appendAmt").setRequired(true);
			}else{
				nui.get("grtMargin.appendAmt").setRequired(false);
			}
		}
		//保证金类型变更		
		function marginTypeChange(e){
			if(e.value=='02'){		//活期
				nui.get("grtMargin.beginDate").setValue("");
				//nui.get("grtMargin.endDate").setValue("");
				nui.get("date").hide();
			}else{//定期
				nui.get("date").show();
			}
		}
		
		function acctChange(){
			nui.get("grtMargin.openBank").setValue();
		    nui.get("grtMargin.currencyCd").setValue();
		    nui.get("grtMargin.accBalance").setValue();
		    nui.get("grtMargin.beginDate").setValue();
		    //nui.get("grtMargin.endDate").setValue();
		}
		
		function setEndTime(e){
			if(e.value=="01"){
				//nui.get("grtMargin.endDate").required=true;
				form.validate();
			}else{
				//nui.get("grtMargin.endDate").required=false;
				form.validate();
			}
		}
		
		function dateChange(e){
			var beginDate=nui.get("grtMargin.beginDate").getValue();//生效日期
	  		//var endDate=nui.get("grtMargin.endDate").getValue();//到期日期
	  		if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("到期日期必须大于起始日期");
					nui.get("grtMargin.beginDate").setValue("");
					//nui.get("grtMargin.endDate").setValue("");
		  		}
	  		}
		}
		
		/**
		 * 比较到期日期和生效日期
		 */
		function CompareDueAndShengXiaoDate(beginDate,endDate){
	  		if(nui.parseDate(endDate)-nui.parseDate(beginDate)<=0){//到期日期小于生效日期
	  			return false;
	  		}else{
	  			return true;
	  		}
		}
	</script>
</body>
</html>