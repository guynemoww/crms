<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-08-13
  - Description:
-->
<head>
<title>停息、终止停息申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	
	<fieldset>
		<legend>
	    	<span>借据信息</span>
	    </legend>
	    
	    <div class="nui-dynpanel" columns="4">
		    <label class="nui-form-label">合同编号：</label>
			<input id="tbConContractInfo.contractNum" class="nui-text nui-form-input" name="tbConContractInfo.contractNum"/>
			
			<label class="nui-form-label">借据编号：</label>
			<input id="tbLoanSummary.summaryNum" class="nui-text nui-form-input" name="tbLoanSummary.summaryNum"/>
			
		</div>
	    
	    <div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
		</div>
		
		<div id="type01" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="orgRegisterCd01" name="orgRegisterCd01" class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmCorporation.orgRegisterCd" class="nui-text nui-form-input" name="tbCsmCorporation.orgRegisterCd"/>
		</div>
		
		<div id="type02" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="tbCsmNaturalPerson.certType" name="tbCsmNaturalPerson.certType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmNaturalPerson.certNum" class="nui-text nui-form-input" name="tbCsmNaturalPerson.certNum"/>
		</div>
		
		<!-- <label class="nui-form-label">经办机构：</label>
		<input id="tbConLoanChange.orgNum" name="tbConLoanChange.orgNum" class="nui-text nui-form-input" dictTypeId="org" />
		
		<label class="nui-form-label">客户经理：</label>
		<input id="tbConLoanChange.userNum" name="tbConLoanChange.userNum" class="nui-text nui-form-input" dictTypeId="user" />
		 -->
		 
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbConContractInfo.productType" name="tbConContractInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt"  class="nui-text nui-form-input" dataType="currency"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye"  class="nui-text nui-form-input" dataType="currency"/>
			<label class="nui-form-label">借据起期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据止期：</label>
			<input id="tbLoanSummary.endDate" name="tbLoanSummary.endDate" class="nui-text nui-form-input"  />
			<label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
		
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">还款账号：</label>
			<input id="tbConLoanChange.oldRepayAccount" name="tbConLoanChange.oldRepayAccount" 
				   class="nui-text nui-form-input" />
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
				   
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input"  vtype="maxLength:999"/> 
		
		</div>
		
		<!-- <div class="nui-dynpanel" columns="4" id="div1">

			<label class="nui-form-label">终止停息收取利息类型：</label>
			<input id="tbConLoanChange.stopRateType" name="tbConLoanChange.stopRateType" class="mini-newcheckbox" data="data" valueField="dictID" dictTypeId="XD_SQLX0001" />
				
		</div> -->
		
		<!-- <div class="nui-dynpanel" columns="4" id="div2">
		
			<label class="nui-form-label">表内正常利息：</label>
			<input id="tbConLoanChange.stopZcRate" name="tbConLoanChange.stopZcRate"  class="nui-text nui-form-input" dataType="currency" />
		
			<label class="nui-form-label">表外正常利息：</label>
			<input id="tbConLoanChange.stopZcRateOut" name="tbConLoanChange.stopZcRateOut"  class="nui-text nui-form-input" dataType="currency" />
		
			</div>
		
		<div class="nui-dynpanel" columns="4" id="div3">
			<label class="nui-form-label">表内拖欠利息：</label>
			<input id="tbConLoanChange.stopTqRate" name="tbConLoanChange.stopTqRate"  class="nui-text nui-form-input" dataType="currency" />
			
			<label class="nui-form-label">表外拖欠利息：</label>
			<input id="tbConLoanChange.stopTqRateOut" name="tbConLoanChange.stopTqRateOut"  class="nui-text nui-form-input" dataType="currency" />
			
		</div>
		
		<div class="nui-dynpanel" columns="4" id="div4">
			<label class="nui-form-label">表内罚息：</label>
			<input id="tbConLoanChange.stopFx" name="tbConLoanChange.stopFx"  class="nui-text nui-form-input" dataType="currency" />
		
			<label class="nui-form-label">表外罚息：</label>
			<input id="tbConLoanChange.stopFxOut" name="tbConLoanChange.stopFxOut"  class="nui-text nui-form-input" dataType="currency" />
		</div> -->
		<div class="nui-dynpanel" columns="4" id="div1">

			<label class="nui-form-label">补计利息类型：</label>
			<input id="tbConLoanChange.stopRateType" name="tbConLoanChange.stopRateType" class="nui-dictcombobox nui-form-input"  valueField="dictID" dictTypeId="XD_SQLX0001" required="true"/>
				
		</div>
		<div class="nui-dynpanel" columns="4" id="div2">
		
			<label class="nui-form-label">补计正常利息：</label>
			<input id="tbConLoanChange.stopZcRate" name="tbConLoanChange.stopZcRate"  class="nui-textbox nui-form-input" dataType="currency" />

		</div>
		
		<div class="nui-dynpanel" columns="4" id="div3">
			<label class="nui-form-label">补计拖欠利息：</label>
			<input id="tbConLoanChange.stopTqRate" name="tbConLoanChange.stopTqRate"  class="nui-textbox nui-form-input" dataType="currency" />
			
		</div>
		
		<div class="nui-dynpanel" columns="4" id="div4">
			<label class="nui-form-label">补计罚息：</label>
			<input id="tbConLoanChange.stopFx" name="tbConLoanChange.stopFx"  class="nui-textbox nui-form-input" dataType="currency" />
		
		</div>
		<div class="nui-dynpanel" columns="4" id="div5">
			<label class="nui-form-label">补计复利：</label>
			<input id="tbConLoanChange.stopFl" name="tbConLoanChange.stopFl"  class="nui-textbox nui-form-input" dataType="currency" />
		
		</div>
	</fieldset>

	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="btnTX" onclick="saveTX">停息</a>
			<a class="nui-button" id="btnZZTXCheck" onclick="saveZZTXCheck">终止停息校验</a>
			<a class="nui-button" id="btnZZTX" onclick="saveZZTX">终止停息</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	
	var tbLoanSummary;
	var tbConLoanChange;
	var res;
	
	var zc;
	var tq;
	var fx;
	var fl;
	
	/* var ways = mini.getDictData("XD_SQLX0001"); 
	var datajson=[];
	for(var i=0; i<ways.length; i++){
		var json = {};
		json["id"]=ways[i].dictID;
	    json["text"]=ways[i].dictName;
	    datajson[i]=json;
	}
	nui.get('tbConLoanChange.stopRateType').setData(datajson); */
	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            
            	if(mydata.tbLoanSummary.tingxiStatus != null && mydata.tbLoanSummary.tingxiStatus != "") {
	            	if(mydata.tbLoanSummary.tingxiStatus == "01") {
	            		nui.get("btnZZTXCheck").setEnabled(true);
	            		nui.get("btnZZTX").setEnabled(true);
	            		nui.get("btnTX").setEnabled(false);
	            		$("#div1").css("display","block");
						$("#div2").css("display","block");
						$("#div3").css("display","block");
						$("#div4").css("display","block");
						$("#div5").css("display","block");
	            	}
	            
	            	if(mydata.tbLoanSummary.tingxiStatus == "02") {
	            		nui.get("btnZZTXCheck").setEnabled(false);
	            		nui.get("btnZZTX").setEnabled(false);
	            		nui.get("btnTX").setEnabled(true);
	            		$("#div1").css("display","none");
						$("#div2").css("display","none");
						$("#div3").css("display","none");
						$("#div4").css("display","none");
						$("#div5").css("display","none");
	            	}
            	}else {
            		nui.get("btnZZTXCheck").setEnabled(false);
            		nui.get("btnZZTX").setEnabled(false);
	            	nui.get("btnTX").setEnabled(false);
	            	$("#div1").css("display","none");
					$("#div2").css("display","none");
					$("#div3").css("display","none");
					$("#div4").css("display","none");
					$("#div5").css("display","none");
            	}
            	
            
            	var o = nui.decode(mydata);
            	form.setData(o);
            	
            	tbLoanSummary = o.tbLoanSummary;
            	tbConLoanChange = o.tbConLoanChange;
            	
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	query1();
			}
        });
        
		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("btnTX").hide();
			nui.get("btnZZTX").hide();
			nui.get("btnZZTXCheck").hide();
			form.setEnabled(false);
		}
        
	}
	function query1(){
		var partyTypeCd= nui.get("tbCsmParty.partyTypeCd").getValue();
  		if(partyTypeCd=="01"){
  			$("#type01").css("display","block");
  			$("#type02").css("display","none");
  		}else if(partyTypeCd=="02"){
  			$("#type01").css("display","none");
  			$("#type02").css("display","block");
  		}else {
  			$("#type01").css("display","none");
  			$("#type02").css("display","none");
  		}
  	}
	
	function saveTX(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId
		
		nui.get("btnTX").setEnabled(false);
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveTingxi.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	nui.alert("保存成功"); //失败时后台直接返回出错信息
        	nui.get("btnTX").setEnabled(true);
        	initPage();
        }});
	} 
	
	function saveZZTX(){
		
		//alert(nui.get("tbConLoanChange.stopZcRate").getValue());
		if (nui.get("tbConLoanChange.stopZcRate").getValue()==null || nui.get("tbConLoanChange.stopZcRate").getValue()==""){
        	nui.alert("请先进行终止停息校验");
        	return;
      	}
        
       if (nui.get("tbConLoanChange.stopTqRate").getValue()==null || nui.get("tbConLoanChange.stopTqRate").getValue()==""){
       	nui.alert("请先进行终止停息校验");
        	return;
       }
        
        if (nui.get("tbConLoanChange.stopFx").getValue()==null || nui.get("tbConLoanChange.stopFx").getValue()==""){
        	nui.alert("请先进行终止停息校验");
        	return;
        }
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId;
		//o.tbConLoanChange.stopRateType = "111";
		
		if(zc < o.tbConLoanChange.stopZcRate) {
			return nui.alert("补正常利息超过其最大值" + zc);
		}
		
		if(tq < o.tbConLoanChange.stopTqRate) {
			return nui.alert("补拖欠利息超过其最大值" + tq);
		}
		
		if(fx < o.tbConLoanChange.stopFx) {
			return nui.alert("补罚息超过其最大值" + fx);
		} 
		
		/* var str = o.tbConLoanChange.stopRateType.split(",");
		for(var i=0;i<str.length;i++) {
			alert(str[i]);
			if(str[i]=='1') {
				if((o.tbConLoanChange.stopZcRate==null || o.tbConLoanChange.stopZcRate=="")
					&& (o.tbConLoanChange.stopZcRateOut==null || o.tbConLoanChange.stopZcRateOut=="")) {
					return nui.alert("请填写正常利息！");
				}
			}
			if(str[i]=='2') {
				if((o.tbConLoanChange.stopTqRate==null || o.tbConLoanChange.stopTqRate=="")
					&& (o.tbConLoanChange.stopTqRateOut==null || o.tbConLoanChange.stopTqRateOut=="")) {
					return nui.alert("请填写拖欠利息！");
				}
			}
			if(str[i]=='3') {
				if((o.tbConLoanChange.stopFx==null || o.tbConLoanChange.stopFx=="")
					&& (o.tbConLoanChange.stopFxOut==null || o.tbConLoanChange.stopFxOut=="")) {
					return nui.alert("请填写罚息！");
				}
			} 
		}  */
		
		//alert(o.tbConLoanChange.stopZcRate + o.tbConLoanChange.stopTqRate + o.tbConLoanChange.stopFx);
		nui.get("btnZZTX").setEnabled(false);
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveZZTingxi.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	nui.alert("保存成功"); //失败时后台直接返回出错信息
        	nui.get("btnZZTX").setEnabled(true);
        	initPage();
        }});
	} 
	
	function saveZZTXCheck(){
	
		//var o = form.getData();
	
		/* var f1 = "0";
		var f2 = "0";
		var f3 = "0";
		
		var str = o.tbConLoanChange.stopRateType.split(",");
		for(var i=0;i<str.length;i++) {
			if(str[i]=='1') {
				f1 = "1";
			}
			if(str[i]=='2') {
				f2 = "1";
			}
			if(str[i]=='3') {
				f3 = "1";
			}
		}   
		
		if(f1=="1" && f2=="1" && f3=="1") {
			res = "111";
		}
		if(f1=="0" && f2=="0" && f3=="0") {
			res = "000";
		}
		if(f1=="0" && f2=="0" && f3=="1") {
			res = "001";
		}
		if(f1=="0" && f2=="1" && f3=="0") {
			res = "010";
		}
		if(f1=="1" && f2=="0" && f3=="0") {
			res = "100";
		}
		if(f1=="1" && f2=="1" && f3=="0") {
			res = "110";
		}
		if(f1=="1" && f2=="0" && f3=="1") {
			res = "101";
		}
		if(f1=="0" && f2=="1" && f3=="1") {
			res = "011";
		}
		alert(res)
		
		if(res == "000") {
			return nui.alert("请选择终止停息收取利息类型");
		} */
		
		res = "111";
		
		
		/* if(tbConLoanChange.stopZcRateOut == null || tbConLoanChange.stopZcRateOut == "") {
			tbConLoanChange.stopZcRateOut = 0;
		} */
		/* if(tbConLoanChange.stopTqRate == null || tbConLoanChange.stopTqRate == "") {
			tbConLoanChange.stopTqRate = 0;
		} */
		/* if(tbConLoanChange.stopTqRateOut == null || tbConLoanChange.stopTqRateOut == "") {
			tbConLoanChange.stopTqRateOut = 0;
		} */
		/* if(tbConLoanChange.stopFx == null || tbConLoanChange.stopFx == "") {
			tbConLoanChange.stopFx = 0;
		} */
		/* if(tbConLoanChange.stopFxOut == null || tbConLoanChange.stopFxOut == "") {
			tbConLoanChange.stopFxOut = 0;
		} */
		
		var json = nui.encode({"tbLoanSummary":tbLoanSummary,"tbConLoanChange":tbConLoanChange,"res":res});
		$.ajax({
            url: "com.bos.aft.conLoanChange.getRepayQueryInterface1507.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.msg) {
            		return nui.alert(mydata.msg);
            	}else {
            		zc = parseFloat(mydata.items.rcvNorItrIn)
            		tq = parseFloat(mydata.items.rcvDftItrIn);
            		fx = parseFloat(mydata.items.rcvPnsItrIn);
            		fl = parseFloat(mydata.items.rcvCpdItrIn);
            		//alert("正常---" + zc + "拖欠---" + tq + "罚息---" + fx);
            		nui.get("tbConLoanChange.stopZcRate").setValue(zc);
            		nui.get("tbConLoanChange.stopTqRate").setValue(tq);
            		nui.get("tbConLoanChange.stopFx").setValue(fx);
            		nui.get("tbConLoanChange.stopFl").setValue(fl);
            		//nui.get("tbConLoanChange.stopZcRate").setValue(mydata.items.inNorItr);
            		//nui.get("tbConLoanChange.stopTqRate").setValue(mydata.items.inDftItr);
            		//nui.get("tbConLoanChange.stopFx").setValue(mydata.items.inPnsItr);
            		//nui.get("tbConLoanChange.stopZcRateOut").setValue(mydata.items.outNorItr);
            		//nui.get("tbConLoanChange.stopTqRateOut").setValue(mydata.items.outDftItr);
            		//nui.get("tbConLoanChange.stopFxOut").setValue(mydata.items.outPnsItr);
            	}
			}
    	}); 
	} 
	function getCurrent(e){
		var value = e.value;
		if(value == '1000'){
						$("#div2").css("display","block");
						$("#div3").css("display","none");
						$("#div4").css("display","none");
						$("#div5").css("display","none");
	            	}
	            	if(value == '0100'){
						$("#div2").css("display","none");
						$("#div3").css("display","block");
						$("#div4").css("display","none");
						$("#div5").css("display","none");
	            	}
	            	if(value == '0010'){
						$("#div2").css("display","none");
						$("#div3").css("display","none");
						$("#div4").css("display","block");
						$("#div5").css("display","none");
	            	}
	            	if(value == '0001'){
						$("#div2").css("display","none");
						$("#div3").css("display","none");
						$("#div4").css("display","none");
						$("#div5").css("display","block");
	            	}
	            	if(value == '1100'){
						$("#div2").css("display","block");
						$("#div3").css("display","block");
						$("#div4").css("display","none");
						$("#div5").css("display","none");
	            	}
	            	if(value == '1010'){
						$("#div2").css("display","block");
						$("#div3").css("display","none");
						$("#div4").css("display","block");
						$("#div5").css("display","none");
	            	}
	            	if(value == '1001'){
						$("#div2").css("display","block");
						$("#div3").css("display","none");
						$("#div4").css("display","none");
						$("#div5").css("display","block");
	            	}
	            	if(value == '0110'){
						$("#div2").css("display","none");
						$("#div3").css("display","block");
						$("#div4").css("display","block");
						$("#div5").css("display","none");
	            	}
	            	if(value == '0101'){
						$("#div2").css("display","none");
						$("#div3").css("display","block");
						$("#div4").css("display","none");
						$("#div5").css("display","block");
	            	}
	            	if(value == '0011'){
						$("#div2").css("display","none");
						$("#div3").css("display","none");
						$("#div4").css("display","block");
						$("#div5").css("display","block");
	            	}
	            	if(value == '1101'){
						$("#div2").css("display","block");
						$("#div3").css("display","block");
						$("#div4").css("display","none");
						$("#div5").css("display","block");
	            	}
	            	if(value == '1110'){
						$("#div2").css("display","block");
						$("#div3").css("display","block");
						$("#div4").css("display","block");
						$("#div5").css("display","none");
	            	}
	            	if(value == '0111'){
						$("#div2").css("display","none");
						$("#div3").css("display","block");
						$("#div4").css("display","block");
						$("#div5").css("display","block");
	            	}
	            	if(value == '1111'){
						$("#div2").css("display","block");
						$("#div3").css("display","block");
						$("#div4").css("display","block");
						$("#div5").css("display","block");
	            	}
	            	if(value == '0000'){
						$("#div2").css("display","none");
						$("#div3").css("display","none");
						$("#div4").css("display","none");
						$("#div5").css("display","none");
	            	}
	}
	
</script>
</body>
</html>