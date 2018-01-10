<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-06-24 08:41:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>编辑保证金</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="form1" style="width:auto;height:auto;overflow:hidden;">
	<input name="grtMargin.suretyKeyId"  class="nui-hidden"  id="grtMargin.suretyKeyId"  value="<%=request.getParameter("suretyKeyId") %>" />
	<div class="nui-dynpanel" columns="4" id="table"  style="width:99.5%;">
			
			<label>开户人：</label>
			<input name="grtMargin.acctName" required="true" class="nui-textbox nui-form-input"  id="grtMargin.acctName" onkeydown="acctChange"/>

			<label>保证金账号：</label>
			<div style="width:123%">
				<input name="grtMargin.marginAccount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="grtMargin.marginAccount" style="width:65%;float:left"onkeydown="acctChange"/>
				<a class="nui-button" id="clear" style="width:16%;float:left" onclick="getAccInfo">查询</a>
			</div>
						
			<label>保证金类型：</label>
			<input name="grtMargin.marginSort" required="true" enabled="false"class="nui-dictcombobox nui-form-input"  id="grtMargin.marginSort" dictTypeId="XD_YWDB0134" onvaluechanged="marginTypeChange"/>
			
			<label>开户行：</label>
			<input name="grtMargin.openBank" required="true" class="nui-text nui-form-input"  id="grtMargin.openBank"  dictTypeId="org"/>
			
			<label>币种：</label>
			<input name="grtMargin.currencyCd" required="true" class="nui-text nui-form-input"  id="grtMargin.currencyCd"   dictTypeId="CD000001" /> 
			
			<label>保证金金额：</label>
			<input name="grtMargin.accBalance" required="true" class="nui-text nui-form-input"  id="grtMargin.accBalance"  dataType="currency" vtype="float;maxLength:26;" />
		</div>
		<div class="nui-dynpanel" columns="4" id="date"  style="width:99.5%;">
			
			<label id="beginDateLab">起始日期：</label>
			<input name="grtMargin.beginDate" enabled="false" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"  id="grtMargin.beginDate" allowinput="false"  onvaluechanged="dateChange" />
			
			<label id="endDateLab">到期日期：</label>
			<input name="grtMargin.endDate"enabled="false" required="true" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"  id="grtMargin.endDate" allowinput="false" onvaluechanged="dateChange"/>
		</div>
		
		<div class="nui-dynpanel" columns="4" id="table"  style="width:99.5%;">
<!-- 			<label id="isJixiLab">是否计息：</label> -->
<!-- 			<input name="grtMargin.isJixi" required="true" class="nui-dictcombobox nui-form-input"  id="grtMargin.isJixi" allowinput="false"  dictTypeId="XD_0002" onvaluechanged="isJixi"/> -->
			
			<label id="rateLab">执行利率(%)：</label>
			<input name="grtMargin.marginRate"  class="nui-textbox nui-form-input"  id="grtMargin.marginRate" vtype="float;range:0,100;maxLength:8" />
			
			<label>追加标志：</label>
			<input name="grtMargin.appendFlag" required="true" class="nui-dictcombobox nui-form-input"  id="grtMargin.appendFlag"  dictTypeId="XD_0002"onvaluechanged="appendFlagChange"/>
			
			<label>追加金额：</label>
			<input name="grtMargin.appendAmt" required="false" class="nui-textbox nui-form-input"  id="grtMargin.appendAmt"  dataType="currency" vtype="float;maxLength:26;"/>
			
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="btnSave" iconCls="icon-ok" onclick="update()">修改</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		//判断是编辑还是查看  1、查看  0、编辑
		var view="<%=request.getParameter("view") %>";
		//保证金ID
		var suretyKeyId="<%=request.getParameter("suretyKeyId")%>";
		if (view=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
		initForm();
		
		function initForm() {
			var json=nui.encode({"grtMargin":{"suretyKeyId":suretyKeyId}});
			git.mask();
			$.ajax({
		        url: "com.bos.grt.grtMainManage.grtGuarantee.getCashDeposit.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        	} else {
		        		form.setData(text);
		        		git.unmask();
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
		function update(){
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			//先新增押品基本信息表、获得主键后再新增保证金	
			var o=form.getData();
			var json=nui.encode(o);
			$.ajax({
	            url: "com.bos.grt.grtMainManage.grtGuarantee.updateCashDeposit.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg !=null){
	            		nui.alert(text.msg); 
	            		nui.get("btnSave").setEnabled(true);
	            		return;
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
		}
		function marginTypeChange(e){
			if(e.value=='02'){		//定期
				nui.get("grtMargin.beginDate").setValue("");
				nui.get("grtMargin.endDate").setValue("");
				nui.get("grtMargin.endDate").hide();
				nui.get("grtMargin.beginDate").hide();
				nui.get("grtMargin.endDate").hide();
				$("#beginDateLab").hide();
				$("#endDateLab").hide();
			}else{			//活期
				nui.get("grtMargin.beginDate").setValue("");
				nui.get("grtMargin.endDate").setValue("");
				nui.get("grtMargin.beginDate").show();
				nui.get("grtMargin.endDate").show();
				$("#beginDateLab").show();
				$("#endDateLab").show();
			}
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
		
		function getAccInfo(){
	    	//return;
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
	    	 var json=nui.encode({"acctInd":AcctNo});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAccNew.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	if(message != '查询成功'){
		        		nui.alert(message);
		        		nui.get("grtMargin.openBank").setValue('');
		        		nui.get("grtMargin.openBank").validate();
		        		return;
		        	}
		        	
		        	var cusName = text.queryAcc.cstNm;
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
		        	var orgid = text.queryAcc.rgonCd+text.queryAcc.branchId;
		        	var beginDate=text.queryAcc.acctDt.substr(0,4)+"-"+text.queryAcc.acctDt.substr(4,2)+"-"+text.queryAcc.acctDt.substr(6,2);//起始日期
		        	var endDate=text.queryAcc.expDt.substr(0,4)+"-"+text.queryAcc.expDt.substr(4,2)+"-"+text.queryAcc.expDt.substr(6,2);//到期日期
	        		var acctCd=text.queryAcc.acctCd//科目代号
		        	if(text.queryAcc.storeCd=="2"){//0-对公活期1-对私活期2-定期3-贷款4-内部 5-表外
		        		if("220412,220432,220434,220452,220499".indexOf(acctCd)==-1){
		        			return alert("定期保证金账号科目代号只能为信用证定期保证金、银行承兑汇票定期保证金、商业承兑汇票定期保证金、保函定期保证金或其他定期保证金");
		        		}
		        		nui.get("grtMargin.marginSort").setValue("01");//定期
		        		nui.get("grtMargin.beginDate").setValue(beginDate);
 		        		nui.get("grtMargin.endDate").setValue(endDate);
		        	}else{
		        		if("220411,220431,220433,220451,220491".indexOf(acctCd)==-1){
		        			 return alert("活期保证金账号科目代号只能为信用证活期保证金、银行承兑汇票活期保证金、商业承兑汇票活期保证金、保函活期保证金或其他活期保证金");
		        		}
		        		nui.get("grtMargin.marginSort").setValue("02");//活期
		        	}
		        	nui.get("grtMargin.openBank").setValue(orgid);
		        	nui.get("grtMargin.currencyCd").setValue(text.queryAcc.ccyTp);//币种
		        	nui.get("grtMargin.accBalance").setValue(text.queryAcc.acctBal);//余额
		        	nui.get("grtMargin.marginAccount").setValue(AcctNo);
		        	nui.get("grtMargin.acctName").setValue(zhm);
		        	nui.get("grtMargin.openBank").validate();
		        	nui.get("grtMargin.currencyCd").validate();
		        	nui.get("grtMargin.accBalance").validate();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    }
		//保证金类型变更		
		function marginTypeChange(e){
			if(e.value=='02'){		//活期
				nui.get("grtMargin.beginDate").setValue("");
				nui.get("grtMargin.endDate").setValue("");
				nui.get("date").hide();
			}else{//定期
				nui.get("date").show();
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
		function acctChange(){
			nui.get("grtMargin.openBank").setValue();
		    nui.get("grtMargin.currencyCd").setValue();
		    nui.get("grtMargin.accBalance").setValue();
		    nui.get("grtMargin.beginDate").setValue();
		    nui.get("grtMargin.endDate").setValue();
		}    
	    function dateChange(e){
			var beginDate=nui.get("grtMargin.beginDate").getValue();//生效日期
	  		var endDate=nui.get("grtMargin.endDate").getValue();//到期日期
	  		if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("到期日期必须大于起始日期");
					nui.get("grtMargin.beginDate").setValue("");
					nui.get("grtMargin.endDate").setValue("");
		  		}
	  		}
		}
		
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