<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-23 16:03:59
  - Description:小微非循环类合同基本信息页面
  
-->
<head>
<title>主合同基本信息</title>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="conInfo.contractId" class="nui-hidden nui-form-input" name ="conInfo.contractId"/>
	<input id="conInfo.oldContractId" class="nui-hidden nui-form-input" name ="conInfo.oldContractId"/>
	<input id="conInfo.rmbAmt" class="nui-hidden nui-form-input" name ="conInfo.rmbAmt"/>
	<input id="conInfo.conBalance" class="nui-hidden nui-form-input" name ="conInfo.conBalance"/>
	<input id="conInfo.oldamt" class="nui-hidden nui-form-input" name ="conInfo.oldamt"/>
	<input id="tbConFlagInfo.flagId" class="nui-hidden nui-form-input" name ="tbConFlagInfo.flagId"/>
	<input id="tbBizAmountDetailApprove.rmbAmt" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.rmbAmt"/>
	<!-- <input id="tbBizAmountDetailApprove.endDate" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.endDate"/>
	 --><input id="tbBizAmountDetailApprove.detailBalance" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.detailBalance"/>
	<input id="tbBizAmountDetailApprove.exchangeRate" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.exchangeRate"/>
	<input id="cycleunit" class="nui-hidden nui-form-input" name ="tbBizAmountDetailApprove.cycleUnit"/>
	<input id="tbBizApprove.validDate" class="nui-hidden nui-form-input" name ="tbBizApprove.validDate"/>
	<input id="approveTerm" name="approveTerm"  class="nui-hidden nui-form-input" />
	<input id="approveUnit" name="approveUnit"  class="nui-hidden nui-form-input" />
			
	<fieldset>
		<legend>
	    	<span>基本信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    	<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/>
			
			<label class="nui-form-label">客户编号：</label>	
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	
			
			<label class="nui-form-label">业务品种：</label>
			<input id="conInfo.productType" class="nui-text nui-form-input" dictTypeId="product" name="conInfo.productType"/>
				
			<label class="nui-form-label">合同编号：</label>
			<input id="conInfo.contractNum" class="nui-text nui-form-input" name="conInfo.contractNum"/>
			
			<label class="nui-form-label">纸质合同编号：</label>
			<input id="conInfo.paperConNum" class="nui-textbox nui-form-input" name="conInfo.paperConNum" required="true"/>
			
			<label class="nui-form-label" id="yhtbh">原合同编号：</label>
			<input id="conInfo.oldContractNum" class="nui-text nui-form-input" name="conInfo.oldContractNum"/>
			
			<label class="nui-form-label">币种：</label>
			<input id="conInfo.currencyCd" name="conInfo.currencyCd" data="data" valueField="dictID" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" required="true" enabled="false"/>
			
			<label class="nui-form-label">金额：</label>
			<input id="conInfo.contractAmt" name="conInfo.contractAmt"  class="nui-textbox nui-form-input" vtype="float;maxLength:20;range:100,100000000000"  dataType="currency" onblur="validAmt" required="true" />
			
			<label class="nui-form-label">起始日期：</label>
			<input id="conInfo.beginDate" name="conInfo.beginDate" class="nui-datepicker nui-form-input"  required="true"  onvaluechanged="validateBeginDate"  allowInput="false"/>
			
			<label id="sqqx">合同期限：</label>
			<div style="width:80%">
				<input id="conInfo.contractTerm" name="conInfo.contractTerm" style="width:60%;float:left" required="true" class="nui-textbox nui-form-input" vtype="int;range:0,95000" onblur="getConEndate"/>
				<input id="conInfo.cycleUnit" name="conInfo.cycleUnit" style="width:40%;float:left" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD6009" enabled="false"/>
			</div>
			
			<label class="nui-form-label">到期日期：</label>
			<input id="conInfo.endDate" name="conInfo.endDate" class="nui-datepicker nui-form-input"  required="true"  allowInput="false" enabled="true" onvaluechanged="getTerm"/>
	
			<label class="nui-form-label" id="hkfs">还款方式：</label>
			<input id="conInfo.repaymentType" name="conInfo.repaymentType" class="nui-dictcombobox nui-form-input" required="true" dictTypeId="XD_SXCD1162" onvaluechanged="conRpTpChg" enabled="false"/>

			<label class="nui-form-label" id="schkq">首次还款期次：</label>
			<input id="conInfo.firstRepayTerm" name="conInfo.firstRepayTerm" class="nui-textbox nui-form-input"  required="true"   vtype="int;maxLength:4;range:2,10000"/>

			<label class="nui-form-label">合同循环标识：</label>
			<input id="conInfo.cycleIndCon" name ="conInfo.cycleIndCon" dValue="0"required="true"enabled="false"class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>
	
			<label class="nui-form-label">签约日期：</label>
			<input id="conInfo.contractDate" name="conInfo.contractDate" class="nui-datepicker nui-form-input"   allowInput="false" required="true"  onvaluechanged="qyrq"/>
			
			<label class="nui-form-label">签约地点：</label>
			<input id="conInfo.contractAddress" class="nui-textarea nui-form-input" name="conInfo.contractAddress" required="true" vtype="maxLength:190"/>
			
			<label class="nui-form-label">指定还款日：</label>
	        <input id="conInfo.specPaymentDate" name="conInfo.specPaymentDate" class="nui-textbox nui-form-input" required="true" vtype="int;range:1,31"/>	
			
			<label class="nui-form-label">间隔天数：</label>
	        <input id="conInfo.internalDays" name="conInfo.internalDays" class="nui-text nui-form-input" />	

			<label class="nui-form-label">贷款用途：</label>
			<input name="conInfo.loanUse" id="conInfo.loanUse"  class="nui-textbox nui-form-input" required="true"  vtype="maxLength:190"/>
		
		</div>
		<div class="nui-dynpanel" columns="4" id="dbfs">		
			<label class="nui-form-label">担保方式：</label>
	        <input id="conInfo.guarantyType" name="conInfo.guarantyType" data="data" valueField="dictID" 
				   class="nui-newcheckbox nui-form-input" dictTypeId="CDZC0005"  onvaluechanged="guarantyTypechg" required="true"/>	
			
			<label class="nui-form-label">主担保方式：</label>
	        <input id="conInfo.mainGuarantyType" name="conInfo.mainGuarantyType" data="data" valueField="dictID" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="CDZC0005"  required="true" />	
			
	    </div>

	</fieldset>
	<fieldset>
		<legend>
	    	<span>标志信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    	<label class="nui-form-label">行业投向：</label>
			<input  name="tbConFlagInfo.loanTurn"  dictTypeId="CDXY0300"  required="true" 
					class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectTrade4" emptyText="--请选择--" />
			
			<label class="nui-form-label">涉及环境、安全等重大情况：</label>
	        <input id="tbConFlagInfo.riskInfo" name="tbConFlagInfo.riskInfo" data="data" valueField="dictID"  required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0211" onvaluechanged="riskinfo"/>	
		</div>
		<div class="nui-dynpanel" columns="4" id="cqcs">	
			<label class="nui-form-label">采取措施：</label>
	        <input id="tbConFlagInfo.act" name="tbConFlagInfo.act" data="data" valueField="dictID"  required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0212" />	
		</div>
		<div class="nui-dynpanel" columns="4">	
			<label class="nui-form-label">节能环保项目及服务类型：</label>
	        <input id="tbConFlagInfo.serviceType" name="tbConFlagInfo.serviceType" data="data" valueField="dictID"  required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0215"  onvaluechanged="servicetype"/>	
		</div>
		<div class="nui-dynpanel" columns="4" id="jnjpl">	
			<label class="nui-form-label">节能减排量：</label>
	        <input id="tbConFlagInfo.reduceAmount" name="tbConFlagInfo.reduceAmount" data="data" valueField="dictID" required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0214" />	
		</div>
		<div class="nui-dynpanel" columns="4">	
			<label class="nui-form-label">产业结构调整类型：</label>
	        <input id="tbConFlagInfo.ajustType" name="tbConFlagInfo.ajustType" data="data" valueField="dictID" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD8030" required="true"/>	
			
			<label class="nui-form-label">工业转型升级标识：</label>
	        <input id="tbConFlagInfo.upgradeType" name="tbConFlagInfo.upgradeType" data="data" valueField="dictID"   required="true" 
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" />	
			
			<label class="nui-form-label">战略新兴产业类型：</label>
	        <input id="tbConFlagInfo.newProductType" name="tbConFlagInfo.newProductType" data="data" valueField="dictID"  
				   class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1204" required="true"/>	
			
	    </div>
	</fieldset>
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div>

</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var contractId ="<%=request.getParameter("contractId") %>";//业务合同ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var productType = "<%=request.getParameter("productType") %>";//流程中查看标识
	var oldContractId='';
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"contractId":contractId,"contractType":"02"});
		$.ajax({
            url: "com.bos.conInfo.conInfoSxxy.getConInfoByContarctId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	//调整时初始化原合同金额
            	nui.get("conInfo.oldamt").setValue(o.conInfo.contractAmt);
            	if(o.conInfo.loanUse==null || ''== o.conInfo.loanUse){
            		nui.get("conInfo.loanUse").setValue(o.tbBizAmountDetailApprove.loanUse);
            	}
            	var grt = o.conInfo.guarantyType;
        		if(grt.indexOf('03')!=-1){
        			grt = grt.replace("05","");
        		}else{
        			grt = grt.replace("05","03");
        		}
            	nui.get("conInfo.cycleIndCon").setEnabled(false);
            	nui.get("conInfo.mainGuarantyType").setData(getDictData('CDZC0005','str',grt));
	            nui.get("conInfo.guarantyType").setData(getDictData('CDZC0005','str',o.tbBizAmountApprove.guarantyType));
            	oldContractId = o.conInfo.oldContractId;
            	if(oldContractId != null && oldContractId!=''){//合同调整
            		nui.get("conInfo.loanUse").setEnabled(true);
            		nui.get("conInfo.contractAmt").setEnabled(false);
            	}
            	//间隔天数反显7天
            	if(o.conInfo.internalDays==null ||o.conInfo.internalDays==''){
            		nui.get("conInfo.internalDays").setValue('7');
            	}
            	//设计环境、安全等重大情况 节能环保服务及项目类型  默认为 未涉及 0非节能环保服务及项目类型 
            	if(o.tbConFlagInfo.riskInfo==null ||o.tbConFlagInfo.riskInfo==''){
            		nui.get("tbConFlagInfo.riskInfo").setValue('0');
            	}
            	if(o.tbConFlagInfo.serviceType==null ||o.tbConFlagInfo.serviceType==''){
            		nui.get("tbConFlagInfo.serviceType").setValue('0');
            	}

            	riskinfo();
            	servicetype();
            	form.validate();
            	initApproveTerm();//小微 合同期限初始化
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}
	}
	var initAppTerm;
	//初始化对私 批复表里的期限
	function initApproveTerm(){
		var item = new Object();
	    item.contractId = contractId;
	    var sqlName = "com.bos.pub.common.getTermFromApprove";
	    var json = nui.encode({"item":item,"sqlName":sqlName});
	    $.ajax({
	      url : "com.bos.csm.pub.ibatis.getItem.biz.ext",
	      type : "POST",
	      data : json,
	      cache : false,
	      contentType : "text/json",
	      success : function(data){
	      	var items = nui.decode(data.items);
	      	var o = items[0];
 	        if(o != null){
	        	nui.get("approveTerm").setValue(o.TERM);//批复期限
	        	nui.get("approveUnit").setValue(o.UNIT);//批复期限单位
	        	var conTerm = nui.get("conInfo.contractTerm").getValue();
	        	//var conUnit = nui.get("conInfo.cycleUnit").getValue();
	        	nui.get("conInfo.cycleUnit").setValue(o.UNIT);//将批复返显给合同期限单位
	        	if(conTerm == ""){//页面初始化的时候 如果合同中未存值 则从批复里面取值返显
	        		//nui.get("conInfo.contractTerm").setValue(o.TERM);//将批复返显给合同期限
	        	}
	        }       
	      }
	    });
	}
	
	//通过起始日期、到期日期、期限单位计算期限
	function getTerm(){
		var beginDate = nui.get("conInfo.beginDate").getValue().substring(0,10);//合同起期
		var endDate = nui.get("conInfo.endDate").getValue().substring(0,10);//合同止期
		var termUnit = nui.get("conInfo.cycleUnit").getValue();//合同期限单位
		var creditTerm = nui.get("approveTerm").getValue();//批复里的期限
		if(beginDate != null && termUnit != null){
			if(endDate <= beginDate){
				nui.get("conInfo.endDate").setValue("");//将合同止期置为空
				alert("合同止期不能小于等于合同起期");
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
							nui.get("conInfo.endDate").setValue("");
							return alert("不能大于批复期限"+creditTerm+"个月,请重新选择到期日期");
						}
					}
					nui.get("conInfo.contractTerm").setValue(data.term);
				}
			});
		}
	} 
	
	//计算合同到期日期
	function getConEndate(){
		var beginDate = nui.get("conInfo.beginDate").getValue();//合同起始日期
		var approveTerm = nui.get("approveTerm").getValue();
		var approveUnit = nui.get("approveUnit").getValue();
		var conTerm = nui.get("conInfo.contractTerm").getValue();

		if(approveTerm != null && approveUnit != null && conTerm > approveTerm){
			nui.get("conInfo.endDate").setValue('');
			alert("合同期限不能大于批复期限"+approveTerm+"个月");
			return;
		}
		if(beginDate !=null && conTerm != null){
			var date = beginDate.substring(0,10);
			var json = nui.encode({"qxdw":"M","qx":conTerm,"rq":date});
	 		$.ajax({
	 			url : "com.bos.pub.dateCountUtil.monthAddDate.biz.ext",
	 			type : 'POST',
	 			data : json,
	 			cache : false,
	 			contentType : "text/json",
	 			success : function(data){//返回合同到期日期
					nui.get("conInfo.endDate").setValue(data.dqrq);
	 			}
	 		});
		}
		 
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("con_contract_info_save").setEnabled(false);
		var o = form.getData();
		//业务合同信息
		o.conInfo.contractId=contractId;
		//业务标志信息
		o.tbConFlagInfo.contractId = contractId;
		//给合同余额赋值
		var conBalance =  nui.get("conInfo.conBalance").getValue();
 		var oldAmt =  nui.get("conInfo.oldamt").getValue();//合同调整时页面初始化此隐藏域
 		if(conBalance==''||conBalance==null||conBalance=='null'){
 			conBalance = 0;
 		}
 		if(conBalance!=0){
 			var ocupy = parseFloat(oldAmt)-parseFloat(conBalance);
 			o.conInfo.conBalance = parseFloat(o.conInfo.contractAmt)- parseFloat(ocupy)
 		}else{
 			o.conInfo.conBalance = parseFloat(o.conInfo.contractAmt)
 		}
		var condate = o.conInfo.endDate;
		var conbedate = o.conInfo.beginDate;
		if(condate.substr(0,10)<=conbedate.substr(0,10)){
			nui.alert("合同止期不能小于等于合同起期"); //失败时后台直接返回出错信息
    		nui.get("con_contract_info_save").setEnabled(true);
    		return;
		}
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.conInfo.conInfoSxxy.saveConContractInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        		return;
        	}
        	alert("保存成功！");
        	nui.get("con_contract_info_save").setEnabled(true);
        	initPage();
        }});
	}
	function selectTrade4(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDXY0300",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname); 
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
 	function validAmt(){
 		var appAmt = nui.get("tbBizAmountDetailApprove.rmbAmt").getValue();
 		var conAmt = nui.get("conInfo.contractAmt").getValue();
 		var exchangeRate = nui.get("tbBizAmountDetailApprove.exchangeRate").getValue();
 		if(exchangeRate =='' || exchangeRate == null){
 			exchangeRate = 1;
 		}
 		var changeAmt = (parseFloat(conAmt)*parseFloat(exchangeRate)).toFixed(6);
 		//合同金额不能大于批复可用金额
/*  		var detailBalance = nui.get("tbBizAmountDetailApprove.detailBalance").getValue();
 		if(parseFloat(detailBalance.toFixed(6))<parseFloat(conAmt)){
 			nui.alert("合同金额不能大于批复明细可用金额!");
 			nui.get("conInfo.contractAmt").setValue("");
 			return;
 		} */
 		
 		//调整时校验调整后金额不能小于已占用金额
 		var conBalance =  nui.get("conInfo.conBalance").getValue();
 		var oldAmt =  nui.get("conInfo.oldamt").getValue();//合同调整时页面初始化此隐藏域
 		if(conBalance==''||conBalance==null||conBalance=='null'){
 			conBalance = 0;
 		}
 		if(conBalance!=0){//调整时、合同金额不能小于已占用金额--合同调整不能修改金额
 			var ocupy = parseFloat(oldAmt)-parseFloat(conBalance);
 			if(conAmt-ocupy<0){
 				nui.alert("合同金额不能小于已占用金额!");
 				nui.get("conInfo.contractAmt").setValue("");
 				return;
 			}
 		}
 		nui.get("conInfo.rmbAmt").setValue(changeAmt);
 	}
 	function riskinfo(){
 		var riskinfo = nui.get("tbConFlagInfo.riskInfo").getValue();
 		if(riskinfo=='0'||riskinfo==''){
			$("#cqcs").css("display","none");
 		}else{
			$("#cqcs").css("display","block");
 		}
 		form.validate();
 	}
	function servicetype(){
 		var riskinfo = nui.get("tbConFlagInfo.serviceType").getValue();
 		if(riskinfo=='0'||riskinfo==''){
			$("#jnjpl").css("display","none");
 		}else{
			$("#jnjpl").css("display","block");
 		}
 		form.validate();
 	}
 	//合同起期不能小于批复生效日
 	function validateBeginDate(){
	 	if(oldContractId != null && oldContractId!=''){//合同调整
	 		return;
	 	}else{
	 		var beginDate = nui.get("conInfo.beginDate").getValue();
	 		var validDate = nui.get("tbBizApprove.validDate").getValue();
	 		//var endDate = nui.get("tbBizAmountDetailApprove.endDate").getValue();
	 		if(beginDate!=null && beginDate!=''){
	 			if(beginDate.substr(0,10)<validDate.substr(0,10)){
		 			alert("合同起期不能小于批复生效日期!");
		 			nui.get("conInfo.beginDate").setValue('');
		 			nui.get("conInfo.endDate").setValue('');
		 			nui.get("conInfo.beginDate").validate();
		 			return;
		 		}
		 		/* if(beginDate.substr(0,10)>endDate.substr(0,10)){
		 			nui.alert("合同起期不能大于批复申报止期!");
		 			nui.get("conInfo.beginDate").setValue('');
		 			nui.get("conInfo.beginDate").validate();
		 			return;
		 		} */
	 		}
	 		getConEndate();//起始日期变动时 自动计算合同到期日期
 		}
 	}
 	function qyrq(){
 		var conDate = nui.get("conInfo.contractDate").getValue();
 		var validDate = nui.get("tbBizApprove.validDate").getValue();
 		if(conDate!=null && conDate!=''){
 			if(conDate<=validDate){
	 			alert("签约日期不能小于批复生效日期!");
	 			nui.get("conInfo.contractDate").setValue('');
	 			nui.get("conInfo.contractDate").validate();
	 		}
 		}
 	}
 	//担保方式
 	function guarantyTypechg(){
 		//初始化时 对担保形式的显隐藏
		var guarantyType = nui.get("conInfo.guarantyType").getValue();

 		var guarantyType = nui.get("conInfo.guarantyType").getValue();
 		if(guarantyType.indexOf('03')!=-1){
			guarantyType = guarantyType.replace("05","");
		}else{
			guarantyType = guarantyType.replace("05","03");
		}
 		nui.get("conInfo.mainGuarantyType").setData(getDictData('CDZC0005','str',guarantyType));
 		nui.get("conInfo.mainGuarantyType").setValue('');
 		nui.get("conInfo.mainGuarantyType").validate();
 	}
 	
 	//只有阶段性还款方式才显示首次还本期次
 	function conRpTpChg(){
		var hkfs = nui.get("conInfo.repaymentType").getValue();
		if(hkfs=='0300'||hkfs=='0400'){
			$("#schkq").css("display","block");
			nui.get("conInfo.firstRepayTerm").show();
		}else{
			$("#schkq").css("display","none");
			nui.get("conInfo.firstRepayTerm").hide();
			nui.get("conInfo.firstRepayTerm").setValue('');
		}
	}
	/* 
	function checkEndDate(){
		//期限校验
		var endddate = nui.get("tbBizAmountDetailApprove.endDate").getValue();
	    var condate = nui.get("conInfo.endDate").getValue();
		if(condate.substr(0,10)>endddate.substr(0,10)){
			nui.alert("合同到期日大于批复止期！"); //失败时后台直接返回出错信息
 			nui.get("conInfo.endDate").setValue('');
 			nui.get("conInfo.endDate").validate();
    		return;
		}
	} */
</script>
</body>
</html>