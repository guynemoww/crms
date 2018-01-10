<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-06-24 08:41:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>新增保证金</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="form1" style="width:auto;height:auto;overflow:hidden;">
		<input name="grtMortgageBasic.orgNum"  class="nui-hidden" id="grtMortgageBasic.orgNum" value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>"/>
		<input name="grtMortgageBasic.userNum"  class="nui-hidden"  id="grtMortgageBasic.userNum"  value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>"/>
		<input name="grtMortgageBasic.createTime"  class="nui-hidden" id="grtMortgageBasic.createTime" value="<%=com.bos.pub.GitUtil.getBusiDate() %>"/>
		<input name="grtMortgageBasic.updateTime"  class="nui-hidden"  id="grtMortgageBasic.updateTime"  value="<%=com.bos.pub.GitUtil.getBusiDate()%>" />
		<input name="grtMortgageBasic.collType"  class="nui-hidden"  id="grtMortgageBasic.collType"  value="<%=request.getParameter("collType") %>" />
		<input name="grtMortgageBasic.partyId"  class="nui-hidden"  id="grtMortgageBasic.partyId"  value="<%=request.getParameter("partyId")%>" />
		<input name="bizGrtRel.applyId"  class="nui-hidden"  id="bizGrtRel.applyId"  value="<%=request.getParameter("applyId") %>" />
		<div class="nui-dynpanel" columns="4" id="table"  style="width:99.5%;">
			
			<label>账户名称：</label>
			<input name="grtMargin.acctName" required="true" class="nui-textbox nui-form-input"  id="grtMargin.acctName" onkeydown="acctChange"/>

			<label>保证金账号：</label>
			<div style="width:123%">
				<input name="grtMargin.marginAccount" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="grtMargin.marginAccount" style="width:65%;float:left" onkeydown="acctChange"/>
				<a class="nui-button" id="clear" style="width:16%;float:left" onclick="getAccInfo">查询</a>
			</div>
						
			<label>保证金类型：</label>
			<input name="grtMargin.marginSort" required="true" enabled="false"class="nui-dictcombobox nui-form-input"  id="grtMargin.marginSort" dictTypeId="XD_YWDB0134"/>
			
			<label>开户行：</label>
			<input name="grtMargin.openBank" required="true" class="nui-text nui-form-input"  id="grtMargin.openBank"  dictTypeId="org"/>
			
			<label>币种：</label>
			<input id="grtMargin.currencyCd" name="grtMargin.currencyCd"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="CD000001"/>
			
			<label>保证金金额：</label>
			<input name="grtMargin.accBalance" required="true" class="nui-text nui-form-input"  id="grtMargin.accBalance"  dataType="currency" vtype="float;maxLength:26;" />
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
			<input name="grtMargin.appendAmt" required="false" class="nui-textbox nui-form-input"  id="grtMargin.appendAmt"  dataType="currency" vtype="float;maxLength:26;"/>
			
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
			<a class="nui-button" id="btnCreate" iconCls="icon-ok" id="addBtn" onclick="add()">添加</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		
		//客户ID
		var partyId="<%=request.getParameter("partyId")%>";
		//业务ID
	    var applyId="<%=request.getParameter("applyId") %>";
	    //抵押/质押/保证金
	    var collType="<%=request.getParameter("collType") %>";
    
		var form = new nui.Form("#form1");
		
		function add(){
			form.validate(); 
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			//先新增押品基本信息表、获得主键后再新增保证金	
			var o=form.getData();
			var json=nui.encode(o);
			$.ajax({
	            url: "com.bos.grt.grtMainManage.grtGuarantee.addCashDeposit.biz.ext",
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
		
		
		function getAccInfo(){
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
		        	nui.get("grtMargin.marginAccount").setValue(AcctNo);
		        	nui.get("grtMargin.acctName").setValue(zhm);
		        	if(text.hxresponse.oxd052ResBody.openAcctDate){
		        		var beginDate = text.hxresponse.oxd052ResBody.openAcctDate.substr(0,4)+"-"+text.hxresponse.oxd052ResBody.openAcctDate.substr(4,2)+"-"+text.hxresponse.oxd052ResBody.openAcctDate.substr(6,2);
		        		nui.get("grtMargin.beginDate").setValue(beginDate);
		        	}
		        	nui.get("grtMargin.openBank").validate();
		        	nui.get("grtMargin.currencyCd").validate();
		        	nui.get("grtMargin.accBalance").validate();
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
			nui.get("grtMargin.openBank").setValue("");
		    nui.get("grtMargin.currencyCd").setValue("");
		    nui.get("grtMargin.accBalance").setValue("");
		    nui.get("grtMargin.beginDate").setValue("");
		    //nui.get("grtMargin.endDate").setValue("");
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
	  		/* if(beginDate!=""&&endDate!=""){
	  			if(!CompareDueAndShengXiaoDate(beginDate,endDate)){//生效日期大于到期日期
					nui.alert("到期日期必须大于起始日期");
					nui.get("grtMargin.beginDate").setValue("");
					nui.get("grtMargin.endDate").setValue("");
		  		}
	  		} */
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