<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-05 10:55:28
  - Description:
-->
<head>
<title>借据信息查看</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  >
	<input name="tbLoanInfo.loanId" required="false" class="nui-hidden nui-form-input"  />
		<div class="nui-dynpanel" columns="4"  id="loan1">
		
		<label class="nui-form-label" >借据编号</label>
		<input id="summaryInfo.summaryNum" name="summaryInfo.summaryNum" class="nui-text nui-form-input"/></a>
	
		<label class="nui-form-label" id="pjhm">票据号码</label>
		<input id="pjxxInfo.billNo" name="pjxxInfo.billNo" class="nui-text nui-form-input"/></a>
		
		<label class="nui-form-label" id="ywbh">业务号码</label>
		<input id="summaryInfo.ywbh" name="summaryInfo.ywbh" class="nui-text nui-form-input"/></a>
	
		<label class="nui-form-label" >放款通知书编号</label>
		<input id="loanInfo.loanNum" name="loanInfo.loanNum" class="nui-text nui-form-input"/></a>
	
		<label class="nui-form-label" >借据状态</label>
		<input id="summaryInfo.summaryStatusCd" name="summaryInfo.summaryStatusCd" class="nui-text nui-form-input" dictTypeId="XD_SXYW0226"/>
		
		<label class="nui-form-label" >业务品种</label>
		<input id="loanInfo.productType" name="loanInfo.productType" class="nui-text nui-form-input"  dictTypeId="product"/>
	
		<label class="nui-form-label" >业务别</label>
		<input id="loanInfo.loanSubject1" name="loanInfo.loanSubject1" class="nui-text nui-form-input"/>
	
		<label class="nui-form-label" >币种</label>
		<input id="summaryInfo.summaryCurrencyCd" name="summaryInfo.summaryCurrencyCd" class="nui-text nui-form-input"  dictTypeId="CD000001"/>
	
		<label class="nui-form-label" >借据金额</label>
		<input id="summaryInfo.summaryAmt" name="summaryInfo.summaryAmt" class="nui-text nui-form-input"/>
	
		<label class="nui-form-label" >起始日</label>
		<input id="summaryInfo.beginDate" name="summaryInfo.beginDate" class="nui-text nui-form-input"/>

		<label class="nui-form-label" >到期日</label>
		<input id="summaryInfo.endDate" name="summaryInfo.endDate" class="nui-text nui-form-input"/>
		
		<label class="nui-form-label" >还款方式</label>
		<input id="loanInfo.repayType" name="loanInfo.repayType" class="nui-text nui-form-input"  dictTypeId="XD_SXCD1162"/>
		
		<label class="nui-form-label" >约定还款日</label>
		<input id="loanInfo.specPaymentDate" name="loanInfo.specPaymentDate" class="nui-text nui-form-input"/>
		
		<label class="nui-form-label" >还款账号</label>
		<input id="zh.zh" name="zh.zh" class="nui-text nui-form-input"/>
		
		<label class="nui-form-label">利率类型：</label>
		<input name="loanRate.rateType" id="loanRate.rateType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1016" enabled="false" onvaluechanged="onselectType"/>
		
		<label class="nui-form-label" id="floatWay">浮动方式：</label>
		<input name="loanRate.floatWay" class="nui-text nui-form-input" dictTypeId="XD_SXCD1147" id="loanRate.floatWay" />
	
		<label class="nui-form-label" id="rateFloatProportion">浮动比例(%)：</label>
		<input id="loanRate.rateFloatProportion" name="loanRate.rateFloatProportion" class="nui-text nui-form-input"  vtype="float;maxLength:11;range:0,10000"/>
			
		<label class="nui-form-label" >利率(%)</label>
		<input id="loanRate.yearRate" name="loanRate.yearRate" class="nui-text nui-form-input"/>

		<label class="nui-form-label" >利率调整方式</label>
		<input id="loanRate.irUpdateFrequency" name="loanRate.irUpdateFrequency" class="nui-text nui-form-input"  dictTypeId="XD_SXCD1148"/>

		<label class="nui-form-label" >结息周期</label>
		<input id="loanRate.interestCollectType" name="loanRate.interestCollectType" class="nui-text nui-form-input"   dictTypeId="XD_SXCD1018"/>
		
		<label id="jjr">节假日顺延标志：</label>
		<input name="loanRate.holidayFlg" id="loanRate.holidayFlg"  data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" enabled="false" dictTypeId="XD_0002" onvaluechanged="conChangeHoliday"/>
		
		<label id="jjrlxclfs">节假日利息处理方式：</label>
		<input name="loanRate.holidayIntFlg" id="loanRate.holidayIntFlg"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="XD_SXYW0234"/>
	
		<label class="nui-form-label">出账机构</label>
		<input id="loanInfo.loanOrg" name="loanInfo.loanOrg"  class="nui-text nui-form-input"  dictTypeId="org"/>
  			
  		<label class="nui-form-label">经办人</label>
		<input id="loanInfo.userNum" name="loanInfo.userNum"  class="nui-text"  dictTypeId="user"/>
				
		 <label class="nui-form-label">经办机构</label>
		<input id="loanInfo.orgNum" name="loanInfo.orgNum"  class="nui-text"  dictTypeId="org"/>
		</div>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var summaryId ="<%=request.getParameter("summaryId") %>"; 
	initPage();
	function initPage(){
		var json = nui.encode({"summaryId":summaryId});
		$.ajax({
	        url: "com.bos.payInfo.PayInfo.getSummaryInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        cache: false,
	        success: function (mydata) {
	        	var o = nui.decode(mydata);
	        	//var productType = o.loanInfo.productType;
				//if(productType=="01007001"||productType=="01007003"||productType=="01007004"||
	   				//productType=="01007009"||productType=="01007012"||productType=="01007011"||
	   				//productType=="01007006"||productType=="01007005"||productType=="01007014"||
	   				//productType=="01007013"||productType=="01007010"){
	   					//$("#pjhm").css("display","none");
	   					//nui.get("pjxxInfo.pjhm").hide();
	   				//if(productType=="01007014"||productType=="01007013"||productType=="01007010"){
	   					
	   				//}else{
	   					//$("#ywbh").css("display","none");
	    				//nui.get("summaryInfo.ywbh").hide();
	   				//}
				//}else{
					//$("#ywbh").css("display","none");
	    			//nui.get("summaryInfo.ywbh").hide();
				//}
				form.setData(o);
			}
    	});
	}
	//节假日顺延标志选否，节假日利息处理方式为空且不可选；
	function conChangeHoliday(){
		var holidayFlag = nui.get("loanRate.holidayFlg").getValue();
		if(holidayFlag == '1'){
			$("#jjrlxclfs").css("display","block");
			nui.get("loanRate.holidayIntFlg").show();
		}else{//不顺延或者为空
			$("#jjrlxclfs").css("display","none");
			nui.get("loanRate.holidayIntFlg").hide();
			nui.get("loanRate.holidayIntFlg").setValue('');
		}
		//刷新table
		nui.get('loan1').refreshTable();
	}
	//利率类型触发事件
    function onselectType(){
		var reateType= nui.get("loanRate.rateType").getValue();
		if(reateType=="2"){//浮动利率
			//显示浮动利率
			$("#floatWay").css("display","block");
			$("#rateFloatProportion").css("display","block");
			nui.get("loanRate.floatWay").show();
			nui.get("loanRate.rateFloatProportion").show();
		}else if(reateType=="1"){//固定利率
			$("#floatWay").css("display","none");
			$("#rateFloatProportion").css("display","none");
			nui.get("loanRate.floatWay").hide();
			nui.get("loanRate.rateFloatProportion").hide();
		}
		//刷新table
		nui.get('loan1').refreshTable();
	}
</script>
</body>
</html>