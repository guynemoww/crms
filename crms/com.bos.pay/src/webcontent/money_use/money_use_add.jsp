<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 08:46:44
  - Description:
-->
<head>
<title>资金使用台账</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
		<input name="tbLoanMoneyUse.moneyUseId" required="false" class="nui-hidden nui-form-input"  />
		<input name="loanAmt" id="loanAmt" required="false" class="nui-hidden nui-form-input"  />
		<div class="nui-dynpanel" columns="4">
			<!-- <label class="nui-form-label" >客户名称</label>
			<input id="tbLoanMoneyUse.partyId" name="tbLoanMoneyUse.partyId" class="nui-text nui-form-input"/></a>
				 -->
			<label class="nui-form-label" >支付日期</label>
			<input id="tbLoanMoneyUse.payTime" name="tbLoanMoneyUse.payTime" class="nui-datepicker nui-form-input"  allowInput="false" onvaluechanged="dateVali" required="true" />
			
			<label class="nui-form-label">支付方式</label>
			<input id="tbLoanMoneyUse.payWay" name="tbLoanMoneyUse.payWay" class="nui-dictcombobox nui-form-input"  required="true" valueField="dictID" dictTypeId="XD_SXYW0218" />
  			
  			<label class="nui-form-label" >支付金额</label>	
			<input id="tbLoanMoneyUse.applyAmount" name="tbLoanMoneyUse.applyAmount"  class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:0.001,100000000000"  dataType="currency"  required="true" onblur="moneyvali"/>
		 	
		 	<label class="nui-form-label" >支付对象</label>
			<input name="tbLoanMoneyUse.payObject" class="nui-textbox nui-form-input"  id="tbLoanMoneyUse.payObject" required="true" />

		 	<label class="nui-form-label" >支付用途</label>
			<input id="tbLoanMoneyUse.payUse" name="tbLoanMoneyUse.payUse" class="nui-textbox nui-form-input"  required="true"/></a>
			
			<label class="nui-form-label">是否符合审批或约定用途</label>
			<input id="tbLoanMoneyUse.isFitDeal" name="tbLoanMoneyUse.isFitDeal" class="nui-dictcombobox nui-form-input"  required="true" valueField="dictID" dictTypeId="XD_0002" />
  		</div>	
 		 <div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;width: 100%;" borderStyle="border:0;">
				<a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="save">保存</a>
		</div>  
	</div>	
</body>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var addFlg ="<%=request.getParameter("addFlg") %>"; 
	var loanId;
	var partyId;
	var summaryId;
	if(addFlg=='1'){//新增
		loanId ="<%=request.getParameter("loanId") %>"; 
		partyId ="<%=request.getParameter("partyId") %>"; 
		summaryId ="<%=request.getParameter("summaryId") %>";
	}else if(addFlg=='2'){//修改
		var moneyUseId ="<%=request.getParameter("moneyUseId") %>";
		var json2 = nui.encode({"moneyUseId":moneyUseId});
	    $.ajax({
	        url: "com.bos.payInfo.MoneyUse.querySingleMoneyUse.biz.ext",
	        type: 'POST',
	        data: json2,
        	async: false,
	        contentType:'text/json',
	        cache: false,
	        success: function (mydata) {
	        	var o = nui.decode(mydata);
	        	form.setData(o);
	        	loanId = o.tbLoanMoneyUse.loanId;
	        	partyId = o.tbLoanMoneyUse.partyId;
	        	summaryId = o.tbLoanMoneyUse.summaryId;
	        }
		})
	}
	 
	var loanAmt ="<%=request.getParameter("loanAmt") %>"; 
	var beginDate ="<%=request.getParameter("beginDate") %>"; 
	nui.get("loanAmt").setValue(loanAmt);
	//queryPartyInfo();
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        var o = form.getData();
        o.tbLoanMoneyUse.partyId=partyId;
        o.tbLoanMoneyUse.loanId=loanId;
        o.tbLoanMoneyUse.summaryId=summaryId;
        var json1 = nui.encode(o);
		$.ajax({
	        url: "com.bos.payInfo.MoneyUse.saveMoneyUse.biz.ext",
	        type: 'POST',
	        data: json1,
	        contentType:'text/json',
	        cache: false,
	        success: function (mydata) {
	        	if(mydata.msg){
	        		nui.alert(mydata.msg);
	        		return;
	        	}
	        	alert("保存成功！");
	        	CloseWindow("ok");
	        	//queryPartyInfo();
			}
    	});
	}
	
	//校验：资金使用金额不能大于放款金额
	function moneyvali(){
		var applyAmount = nui.get("tbLoanMoneyUse.applyAmount").getValue();
		var loanAmt = nui.get("loanAmt").getValue();
		if(parseFloat(applyAmount)>parseFloat(loanAmt)){
			nui.alert("申请使用贷款金额不能大于放款金额");
			nui.get("tbLoanMoneyUse.applyAmount").setValue('');
		}
	}
	
	function dateVali(){
		var payTime = nui.get("tbLoanMoneyUse.payTime").getValue();
		if(payTime.substr(0,10)<beginDate.substr(0,10)){
			nui.alert("支付时间小于贷款起期");
			nui.get("tbLoanMoneyUse.payTime").setValue();
		}
	}
	/* //查询客户信息
	function queryPartyInfo(){
		var json = nui.encode({"partyId":partyId});
	    $.ajax({
	        url: "com.bos.bizApply.bizApply.getPartyInfoByPartyId.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	var o = nui.decode(mydata);
	        	nui.get("tbLoanMoneyUse.partyId").setText(o.party.partyName);
	        }
				
		})
	} */
</script>
</html>