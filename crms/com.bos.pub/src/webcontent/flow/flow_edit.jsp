<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-10

  - Description:TB_SYS_FLOW_TEST, com.bos.pub.sys.TbSysFlowTest-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbSysFlowTest" class="nui-hidden" />
		<fieldset>
			<div class="nui-dynpanel" columns="4">
			<label>测算类型：</label>
			<input id="flow" name="tbSysFlowTest.testType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2"  dictTypeId="XD_GGCD03932"  enabled="false"/>
			</div>
		</fieldset>
		<fieldset>
			<legend>
				<span>上年度财务信息：</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
			<label>上年度销售收入：</label>
			<input id="salleIncome" name="tbSysFlowTest.salleIncome" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>上年度销售利润率(%)：</label>
			<input id="saleProfitRate" name="tbSysFlowTest.saleProfitRate" required="true" class="nui-textbox nui-form-input" vtype="maxLength:8;float" />
			<label>上年度销售成本：</label>
			<input id="seleCost" name="tbSysFlowTest.seleCost" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>上年度平均存货余额：</label>
			<input id="averageStockBal" name="tbSysFlowTest.averageStockBal" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>上年度平均应收账款余额：</label>
			<input id="averateAccountBal" name="tbSysFlowTest.averateAccountBal" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>上年度平均应付账款余额：</label>
			<input id="averatPayAcountBal" name="tbSysFlowTest.averatPayAcountBal" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>上年度平均预收账款余额：</label>
			<input id="averageShouldGetacountBal" name="tbSysFlowTest.averageShouldGetacountBal" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>上年度平均预付账款余额：</label>
			<input id="averageShouldPayacountBal" name="tbSysFlowTest.averageShouldPayacountBal" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<!-- <label>净资产：</label>
			<input id="netAsset" name="tbSysFlowTest.netAsset" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>长期负债：</label>
			<input id="longTermLiability" name="tbSysFlowTest.longTermLiability" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			<label>长期资产：</label>
			<input id="longTermAsset" name="tbSysFlowTest.longTermAsset" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
	 -->	
			</div>
		</fieldset>
		<fieldset>
			<legend>
				<span>测算指标：</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
			<label>存货周转天数：</label><!-- 360/（上年度销售收入/上年度平均存货余额） -->
			<input id="stockTurnoverDays" name="tbSysFlowTest.stockTurnoverDays" required="false" class="nui-textbox nui-form-input" vtype="maxLength:8;int"  enabled="false"/>
			<label>应收帐款周转天数：</label><!-- 360/（上年度销售收入/上年度平均应收账款余额） -->
			<input id="shouldGetTurnoverDays" name="tbSysFlowTest.shouldGetTurnoverDays" required="false" class="nui-textbox nui-form-input" vtype="maxLength:8;int"  enabled="false"/>
			<label>应付账款周转天数：</label><!-- 360/（上年度销售成本/上年度平均应付账款余额） -->
			<input id="shouldPayTurnoverDays" name="tbSysFlowTest.shouldPayTurnoverDays" required="false" class="nui-textbox nui-form-input" vtype="maxLength:8;int"  enabled="false"/>
			<label>预付账款周转天数：</label><!-- 360/(上年度销售成本/上年度平均预付账款余额) -->
			<input id="shouldPayacountTurnoverDays" name="tbSysFlowTest.shouldPayacountTurnoverDays" required="false" class="nui-textbox nui-form-input" vtype="maxLength:8;int"  enabled="false"/>
			<label>预收账款周转天数：</label><!-- 360/(上年度销售收入/上年度平均预收账款余额) -->
			<input id="shouldGetacountTurnoverDays" name="tbSysFlowTest.shouldGetacountTurnoverDays" required="false" class="nui-textbox nui-form-input" vtype="maxLength:8;int"  enabled="false"/>
			</div>
			<div class="nui-dynpanel" columns="4">
			<label>营运资金周转次数：</label><!-- 360/(存货周转天数+应收账款周转天数-应付账款周转天数＋预付账款周转天数-预收账款周转天数) -->
			<input id="fundTurnoverNum" name="tbSysFlowTest.fundTurnoverNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:8;float"  enabled="false"/>
			<label>预计销售收入年增长率(%)：</label>
			<input id="preSaleRiseRate" name="tbSysFlowTest.preSaleRiseRate" required="true" class="nui-textbox nui-form-input" vtype="maxLength:8;float" />
			<label>借款人营运资金量：</label><!-- 上年度销售收入×(1-上年度销售利润率)×(1＋预计销售收入年增长率)/营运资金周转次数 -->
			<input id="borrowerOperateFund" name="tbSysFlowTest.borrowerOperateFund" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26;float"  enabled="false" />
			</div>
			<div class="nui-dynpanel" columns="4">
			<label>借款人自有资金：</label><!-- 净资产+长期负债－长期资产 -->
			<input id="borrowerSelfFound" name="tbSysFlowTest.borrowerSelfFound" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26;float"  enabled="false"/>
	
			<label>现有流动资金贷款：</label>
			<input id="nowFlowFundLoan" name="tbSysFlowTest.nowFlowFundLoan" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
		
			<label>其他渠道提供的营运资金：</label>
			<input id="otherApplyOperateFund" name="tbSysFlowTest.otherApplyOperateFund" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" />
			
			<label>新增流动资金贷款额度：</label><!-- 营运资金量-借款人自有资金-现有流动资金贷款-其他渠道提供的营运资金 -->
			<input id="newFlowFundLoan" name="tbSysFlowTest.newFlowFundLoan" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26;float"  enabled="false"/>
			</div>
		</fieldset>
		
		<fieldset>
			<div class="nui-dynpanel" columns="4">
			<label>测算机构：</label>
			<input name="tbSysFlowTest.orgNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:9" enabled="false" dictTypeId="org"/>
	
			<label>测算人：</label>
			<input name="tbSysFlowTest.userNum" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:10" enabled="false" dictTypeId="user"/>
	
			<label>测算日期：</label>
			<input name="tbSysFlowTest.testDate" required="false" class="nui-DatePicker nui-form-input" vtype="maxLength:10" enabled="false"/>
			</div>
		</fieldset>
	</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
  var  salleIncome,saleProfitRate,//上年度销售收入,上年度销售利润率
	       seleCost ,averageStockBal ,averateAccountBal,//上年度销售成本,上年度平均存货余额,上年度平均应收账款余额
	       averatPayAcountBal, averageShouldGetacountBal,//上年度平均应付账款余额,上年度平均预收账款余额
	       averageShouldPayacountBal, stockTurnoverDays,//上年度平均预付账款余额,存货周转天数
	       shouldGetTurnoverDays, shouldPayTurnoverDays,//应收帐款周转天数,应付账款周转天数
	       shouldPayacountTurnoverDays,shouldGetacountTurnoverDays,//预付账款周转天数,预收账款周转天数
	       fundTurnoverNum, preSaleRiseRate, borrowerOperateFund,//营运资金周转次数,预计销售收入年增长率,借款人营运资金量
	       //netAsset, longTermLiability, longTermAsset,//净资产,长期负债,长期资产
	       borrowerSelfFound,//预收账款周转天数,借款人自有资金
	       nowFlowFundLoan, otherApplyOperateFund, newFlowFundLoan;//现有流动资金贷款,其他渠道提供的营运资金,新增流动资金贷款
	
	   
	    
	    
	      function ceSuan(){
		    	salleIncome=nui.get("salleIncome").getValue();//上年度销售收入
		    	saleProfitRate=nui.get("saleProfitRate").getValue();//上年度销售利润率
			    seleCost=nui.get("seleCost").getValue();//上年度销售成本
			    averageStockBal=nui.get("averageStockBal").getValue();//上年度平均存货余额
			    averateAccountBal=nui.get("averateAccountBal").getValue();//上年度平均应收账款余额
			    averatPayAcountBal=nui.get("averatPayAcountBal").getValue();//上年度平均应付账款余额
			    averageShouldGetacountBal=nui.get("averageShouldGetacountBal").getValue();//上年度平均预收账款余额
			    averageShouldPayacountBal=nui.get("averageShouldPayacountBal").getValue();//上年度平均预付账款余额
			    stockTurnoverDays=nui.get("stockTurnoverDays");//存货周转天数
			    shouldGetTurnoverDays=nui.get("shouldGetTurnoverDays");//应收帐款周转天数
			    shouldPayTurnoverDays=nui.get("shouldPayTurnoverDays");//应付账款周转天数
			    shouldPayacountTurnoverDays=nui.get("shouldPayacountTurnoverDays");//预付账款周转天数
			    shouldGetacountTurnoverDays=nui.get("shouldGetacountTurnoverDays")//预收账款周转天数
			    fundTurnoverNum=nui.get("fundTurnoverNum");//营运资金周转次数
			    preSaleRiseRate=nui.get("preSaleRiseRate").getValue();//预计销售收入年增长率
			    borrowerOperateFund=nui.get("borrowerOperateFund")//借款人营运资金量
			    //netAsset=nui.get("netAsset").getValue();//净资产
			    //longTermLiability=nui.get("longTermLiability").getValue();//长期负债
			    //longTermAsset=nui.get("longTermAsset").getValue();//长期资产
			    borrowerSelfFound=nui.get("borrowerSelfFound");//借款人自有资金
			    nowFlowFundLoan=nui.get("nowFlowFundLoan").getValue();//现有流动资金贷款
			    otherApplyOperateFund=nui.get("otherApplyOperateFund").getValue();//其他渠道提供的营运资金
			    newFlowFundLoan=nui.get("newFlowFundLoan");//新增流动资金贷款
		 		nui.get("stockTurnoverDays").setValue(360/(Number(salleIncome)/Number(averageStockBal)));// 存货周转天数=360/（上年度销售收入/上年度平均存货余额）
		 		nui.get("shouldGetTurnoverDays").setValue(360/(Number(salleIncome)/Number(averateAccountBal)));//应收账款周转天数= 360/（上年度销售收入/上年度平均应收账款余额）
		 		nui.get("shouldPayTurnoverDays").setValue(360/(Number(seleCost)/Number(averatPayAcountBal)));//应付账款周转天数=360/（上年度销售成本/上年度平均应付账款余额）
		 		nui.get("shouldPayacountTurnoverDays").setValue(360/(Number(seleCost)/Number(averageShouldPayacountBal)));//预付账款周转天数=360/(上年度销售成本/上年度平均预付账款余额)
		 		nui.get("shouldGetacountTurnoverDays").setValue(360/(Number(salleIncome)/Number(averageShouldGetacountBal)));//预收账款周转天数=360/(上年度销售收入/上年度平均预收账款余额)
		 		var tian1,tian2,tian3,tian4,tian5;
		 		tian1=nui.get("stockTurnoverDays").getValue();
		 		tian2=nui.get("shouldGetTurnoverDays").getValue();
		 		tian3=nui.get("shouldPayTurnoverDays").getValue();
		 		tian4=nui.get("shouldPayacountTurnoverDays").getValue();
		 		tian5=nui.get("shouldGetacountTurnoverDays").getValue();
		 		//营运资金周转次数=360/(存货周转天数+应收账款周转天数-应付账款周转天数＋预付账款周转天数-预收账款周转天数)
		 		nui.get("fundTurnoverNum").setValue(360/(Number(tian1)+Number(tian2)-Number(tian3)+Number(tian4)-Number(tian5)));
		 		var tian6;
		 		tian6=nui.get("fundTurnoverNum").getValue();
		 		alert("营运资金周转次数="+tian6);
		 		//alert("1-上年度销售利润率="+(1-Number(saleProfitRate)));
		 		//alert("1＋预计销售收入年增长率="+(1+Number(preSaleRiseRate)));
		 		//alert("salleIncome="+salleIncome);
		 		//借款人营运资金量=上年度销售收入×(1-上年度销售利润率)×(1＋预计销售收入年增长率)/营运资金周转次数
		 		nui.get("borrowerOperateFund").setValue(Number(salleIncome)*(1-Number(saleProfitRate)*0.01)*(1+Number(preSaleRiseRate)*0.01)/Number(tian6));
		 		//借款人自有资金=净资产+长期负债－长期资产
		 		//nui.get("borrowerSelfFound").setValue(Number(netAsset)+Number(longTermLiability)-Number(longTermAsset));
		 		//新增流动资金贷款=营运资金量-借款人自有资金-现有流动资金贷款-其他渠道提供的营运资金
		 		var money,money1;
		 		money=nui.get("borrowerOperateFund").getValue();
		 		//money1=nui.get("borrowerSelfFound").getValue();
		 		//alert("money="+money+"--money1+"+money1);
		 		nui.get("newFlowFundLoan").setValue(Number(money)-Number(money1)-Number(nowFlowFundLoan)-Number(otherApplyOperateFund));
		}
function initForm() {
	var json=nui.encode({"tbSysFlowTest":
		{"testFlowNo":
		"<%=request.getParameter("testFlowNo") %>"}});
	$.ajax({
        url: "com.bos.pub.flow.getTbSysFlowTest.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();




function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}else{
		var saleProfitRate =nui.get("saleProfitRate").getValue();
		var saleLength=saleProfitRate.length;
		if(saleProfitRate.indexOf(".")!=-1){
		  var  ss=saleProfitRate.substring(0,3);
		  if(ss.indexOf(".")!=-1){
	      	ceSuan();
			nui.confirm("请核实结果？","确认",function(action){
	            	if(action!="ok") return;
			            	var o=form.getData();
							var json=nui.encode(o);
			               $.ajax({
						        url: "com.bos.pub.flow.updateTbSysFlowTest.biz.ext",
						        type: 'POST',
						        data: json,
						        cache: false,
						        contentType:'text/json',
						        success: function (text) {
						        	if(text.msg){
						        		nui.alert(text.msg);
						        	} else {
						        		CloseWindow("ok");
						        	}
						        },
						        error: function (jqXHR, textStatus, errorThrown) {
						            nui.alert(jqXHR.responseText);
						        }
							});
	            }); 
	  }else{
	     alert("上年度销售利润率小数点前只能有两位数字");
	  }
	}else{
	   if(saleLength>=3){
	     alert("上年度销售利润率不能超过两位数");
	   }else{
	   							ceSuan();
			nui.confirm("请核实结果？","确认",function(action){
	            	if(action!="ok") return;
			            	var o=form.getData();
							var json=nui.encode(o);
			               $.ajax({
						        url: "com.bos.pub.flow.updateTbSysFlowTest.biz.ext",
						        type: 'POST',
						        data: json,
						        cache: false,
						        contentType:'text/json',
						        success: function (text) {
						        	if(text.msg){
						        		nui.alert(text.msg);
						        	} else {
						        		CloseWindow("ok");
						        	}
						        },
						        error: function (jqXHR, textStatus, errorThrown) {
						            nui.alert(jqXHR.responseText);
						        }
							});
	            }); 
	   
	   }
 	}
	}
}
	</script>
</body>
</html>
