<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-31
  - Description:TB_CON_LOAN_ACCOUNT_INFO, com.bos.dataset.pay.TbConLoanAccountInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<div id="form1" style="width:96%;height:80%;overflow:hidden; text-align:center;margin: 10px;" >
	<input id="id" name="tbConZh.id"  class="nui-hidden nui-form-input" value=""/>
	<fieldset>
		  	<legend>
		   		<span>帐户信息</span>
		    </legend>
			<div class="nui-dynpanel" columns="4">
				<!-- 账户类型为手选 
				<label>账户类型：</label>
				<input id="zhlx" name="tbConZh.zhlx" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1208"/>
				-->
				<!--<label>账户名称：</label>
				<input id="zhmc" name="tbConZh.zhmc" class="nui-textbox nui-form-input" vtype="maxLength:200" required="true" />
				--><label>客户账号：</label>
				<div style="width:120%">
					<input id="zh" name="zh"  style="width:68%;float:left"  required="true" class="nui-textbox nui-form-input" />
				</div>
				<label>货币代号：</label>
				<div style="width:100%">
					
				<input id="hbdh" name="hbdh" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100001"   dValue="01"/>
				
				</div>
				<label>钞汇标志：</label>
				<div style="width:100%">
				<input id="chbz" name="chbz" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD100002"   dValue="0"/>
				
				
				</div>
				<!-- 输入账户名称与账户账号，以下信息反显 -->
		<%--		<label>账户标识：</label>
				<input id="zhbs" name="tbConZh.zhbs" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0219"/>
				--%>
			<!--	<label>卡折标志：</label>
				<input id="kzbs" name="tbConZh.kzbs" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0220"/>
				-->
				<!--<label>开户行：</label>
				<input id="zhkhjg"  name="tbConZh.zhkhjg" required="true"  class="nui-text nui-form-input" dictTypeId="org"/>
				-->
				
				<label>客户账户名称：</label>
				<input id="zhmc"  name="zhmc"   class="nui-text nui-form-input"/>
				
				<label>机构名称：</label>
				<input id="jgmc"  name="jgmc"   class="nui-text nui-form-input"/>
				
				<label>账户状态 ：</label>
				<input id="zhzt"  name="zhzt"   class="nui-text nui-form-input"/>
				
				<label>账户余额状态 ：</label>
				<input id="zhyezt"  name="zhyezt"   class="nui-text nui-form-input"/>
				
				<label>开户机构：</label>
				<input id="khjg"  name="khjg"   class="nui-text nui-form-input"/>
				
				<label>账户余额：</label>
				<input id="ye"  name="ye"   class="nui-text nui-form-input"/>
				
				<label>可用余额：</label>
				<input id="kyye"  name="kyye"   class="nui-text nui-form-input"/>

			</div>
	</fieldset>		
	<div class="nui-toolbar" style="border-bottom:0;text-align:right;">
		<a class="nui-button" iconCls="icon-search" onclick="getInfo()" id="add">查询</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
		
	    
			
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	
    function getInfo(){
    	//nui.get("zhmc").setValue("Loading...");
    	//nui.get("jgmc").setValue("Loading...");
    	//nui.get("zhzt").setValue("Loading...");
    	//nui.get("khjg").setValue("Loading...");
    	//nui.get("ye").setValue("Loading...");
	   // nui.get("kyye").setValue("Loading...");
    	var AccNo = nui.get("zh").getValue();
    	var CurrCode = nui.get("hbdh").getValue();
    	var CashFlag = nui.get("chbz").getValue();
    	//var reg = new RegExp("^[0-9]*$");
    	//if(!reg.test(AccNo) || AccNo == null || AccNo == ''){
    	if(!AccNo){
    		alert("请输入合法账号！");
    		return;
    	}else if(CurrCode == null || CurrCode == ''){
    		alert("请选择货币代号！");
    		return;
    	}else if(CashFlag == null || CashFlag == ''){
    		alert("请选择钞汇标志！");
    		return;
    	}
    	
    	AccNo = AccNo.trim();
    	CurrCode = CurrCode.trim();
    	CashFlag = CashFlag.trim();
    	var subNo = AccNo.substring(4,5);
     if(subNo == '8' || subNo == '9'){
    	 var json2=nui.encode({"acctInd":AccNo , "currCode": CurrCode});
		  $.ajax({
	        url: "com.bos.accInfo.accInfo.queryAcc2.biz.ext",
	        type: 'POST',
	        data: json2,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	var message = text.msg;
	        	var code = text.code;
	        	if(code != 'AAAAAAA'){
	        		nui.alert(message);
	        		return;
	        	}
	        	// 客户账户名称
	        	var zhmc = text.oXD15AccountInfo.oxd015ResBody.fXD151[0].acctChnName;
	        	nui.get("zhmc").setValue(zhmc);
				
				// 机构名称
				var jgmc = text.oXD15AccountInfo.oxd015ResBody.fXD151[0].businessBrch;
	        	nui.get("jgmc").setValue(jgmc);
				
				// 账户余额状态
				var zhyezt = text.oXD15AccountInfo.oxd015ResBody.fXD151[0].fundAcctStat;
				if(zhyezt == '0'){
					nui.get("zhyezt").setValue("正常");
				}
				if(zhyezt == '1'){
					nui.get("zhyezt").setValue("金额冻/控");
				}
				if(zhyezt == '2'){
					nui.get("zhyezt").setValue("封闭冻结");
				}
				if(zhyezt == '3'){
					nui.get("zhyezt").setValue("只收不付");
				}
				if(zhyezt == '4'){
					nui.get("zhyezt").setValue("只付不收");
				}
				if(zhyezt == '5'){
					nui.get("zhyezt").setValue("组合限制");
				}
				// 开户机构
				var khjg = text.oXD15AccountInfo.oxd015ResBody.fXD151[0].openBrch;
	        	nui.get("khjg").setValue(khjg);
	        	
	        	//可用余额
	        	var ye = text.oXD15AccountInfo.oxd015ResBody.fXD151[0].balance;
	        	nui.get("kyye").setValue(ye);
	        	
	        	//alert(nui.decode(text.queryAcc))
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
    	 }else{
    	 var json=nui.encode({"acctInd":AccNo , "currCode": CurrCode , "cashFlag" :CashFlag});
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
	        		return;
	        	}
	        	
	        	
	        	// 客户账户名称
	        	var zhmc = text.hxresponse.oxd052ResBody.custName;
	        	nui.get("zhmc").setValue(zhmc);
				
				// 机构名称
				var jgmc = text.hxresponse.oxd052ResBody.brcnName;
	        	nui.get("jgmc").setValue(jgmc);
				
				// 账户余额状态
				var zhyezt = text.hxresponse.oxd052ResBody.acctStat;
				if(zhyezt == '0'){
					nui.get("zhyezt").setValue("正常");
				}
				if(zhyezt == '1'){
					nui.get("zhyezt").setValue("金额冻/控");
				}
				if(zhyezt == '2'){
					nui.get("zhyezt").setValue("封闭冻结");
				}
				if(zhyezt == '3'){
					nui.get("zhyezt").setValue("只收不付");
				}
				if(zhyezt == '4'){
					nui.get("zhyezt").setValue("只付不收");
				}
				if(zhyezt == '5'){
					nui.get("zhyezt").setValue("组合限制");
				}
	        	// 账户状态
				var zhzt =  text.hxresponse.oxd052ResBody.acctStat1;
				if(zhzt == 'A'){
					nui.get("zhzt").setValue("正常");
				}
				if(zhzt == 'C'){
					nui.get("zhzt").setValue("销户");
				}
				if(zhzt == 'D'){
					nui.get("zhzt").setValue("久悬户");
				}
				if(zhzt == 'I'){
					nui.get("zhzt").setValue("转营业外收入");
				}
				
				// 开户机构
				var khjg = text.hxresponse.oxd052ResBody.openBrch;
	        	nui.get("khjg").setValue(khjg);
	        	
	        	//余额
	        	var ye = text.hxresponse.oxd052ResBody.accrrestAmt;
	        	nui.get("ye").setValue(ye);
	        	
	        	//可用余额
	        	var kyye = text.hxresponse.oxd052ResBody.availableAmt;
	        	nui.get("kyye").setValue(kyye);
	        	
	        	//alert(nui.decode(text.queryAcc))
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
    	 }
    
    }
   
	</script>
</body>
</html>
