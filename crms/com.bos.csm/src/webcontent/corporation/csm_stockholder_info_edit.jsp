<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangyanli
  - Date: 2013-11-20

  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="tbCsmStockholderInfo.stockholderId" id="tbCsmStockholderInfo.stockholderId" class="nui-hidden" />
	<input name="tbCsmStockholderInfo.partyId" id="tbCsmStockholderInfo.partyId" class="nui-hidden"/>
	<input name="tbCsmStockholderInfo.naturalPartyId" id="tbCsmStockholderInfo.naturalPartyId" class="nui-hidden"/>
	<fieldset>
	  <legend>
	    <span>股东信息</span>
	  </legend>
	  <div id="" class="nui-dynpanel"  columns="4">
	  	<label>关联方国别：</label>
		<input id="tbCsmStockholderInfo.legalContry" name="tbCsmStockholderInfo.legalContry" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" dValue="CHN"  onvaluechanged="noRequired" />
		
		<label>是否我行客户：</label>
		<input id="tbCsmStockholderInfo.iscout" name="tbCsmStockholderInfo.iscout" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="" onvaluechanged="isOwnCust" />
			
	  	<label>股东类型：</label>
	  	<input id="tbCsmStockholderInfo.stockholderTypeCd" name="tbCsmStockholderInfo.stockholderTypeCd" 
			required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0034" 
			onvaluechanged="stockholderTypeCdValueChange"/>
		<!--<input id="tbCsmStockholderInfo.stockholderTypeCd" name="tbCsmStockholderInfo.stockholderTypeCd" 
			required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0034" 
			onvaluechanged="clearStockHolderInfo"/>-->
			
		<<!--label>关联类型：</label>
		<input id="tbCsmStockholderInfo.relatedCd"  name="tbCsmStockholderInfo.relatedCd" required="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"/>-->
		<!--<input id="tbCsmStockholderInfo.relatedCd" name="tbCsmStockholderInfo.relatedCd" required="true" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList"/>
	-->
	  	<label>股东名称：</label>
		<input id="tbCsmStockholderInfo.stockeholderName"  name="tbCsmStockholderInfo.stockeholderName" required="true" 
			class="nui-buttonEdit" onbuttonclick="selectCust" />
		</div>
		
		<div id="cardInfo" class="nui-dynpanel"  columns="4">
	  	<label>证件类型：</label>
		<input id="tbCsmStockholderInfo.certificateTypeCd"  name="tbCsmStockholderInfo.certificateTypeCd" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_ZJLX0002"/>
		
		<label>证件号码：</label>
		<input id="tbCsmStockholderInfo.certificateNum"  name="tbCsmStockholderInfo.certificateNum" 
			class="nui-textbox nui-form-input" />
		 </div>	
			
		 <div id="registInfo" class="nui-dynpanel"  columns="4">
		<label id="registrCd">登记注册号码：</label>
		<input id="tbCsmStockholderInfo.registrCd" name="tbCsmStockholderInfo.registrCd" enabled="true" class="nui-textbox nui-form-input" />
		
		<label id="orgRegisterCd">组织机构代码：</label>
		<input id="tbCsmStockholderInfo.orgRegisterCd" name="tbCsmStockholderInfo.orgRegisterCd" enabled="true" class="nui-textbox nui-form-input" />
		
		<label id="orgCreditCode">中征码：</label>
		<input id="tbCsmStockholderInfo.orgCreditCode" name="tbCsmStockholderInfo.orgCreditCode" enabled="true" class="nui-textbox nui-form-input" />
		
		<label>备注：</label>
			<input id="tbCsmStockholderInfo.remark" name="tbCsmStockholderInfo.remark" required="false" 
			class="nui-textbox nui-form-input" vtype="maxLength:1000" />
			
		</div>
		<div id="registInfo" class="nui-dynpanel"  columns="4">
		<label>股东结构对应日期：</label>
			<input id="tbCsmStockholderInfo.updateTime" name="tbCsmStockholderInfo.updateTime" required="true" 
			class="nui-datepicker nui-form-input" />
		</div>
	</fieldset>
	<fieldset>
	  <legend>
	    <span>出资情况</span>
	   </legend>
	<div class="nui-dynpanel" columns="4">

		<label>出资币种：</label>
		<input id="tbCsmStockholderInfo.currecyCd" name="tbCsmStockholderInfo.currecyCd" enabled="false" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"  />
		
		<label>货币金额（元）：</label>
		<input id="tbCsmStockholderInfo.currencyAmt"  name="tbCsmStockholderInfo.currencyAmt" required="true" 
			class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" onblur="countAmt"/>
			
		<label>实物金额（元）：</label>
		<input id="tbCsmStockholderInfo.realObjectAmt"  name="tbCsmStockholderInfo.realObjectAmt" required="true" 
			class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" onblur="countAmt"/>
			
		<label>无形资产金额（元）：</label>
		<input id="tbCsmStockholderInfo.intangibleAssetsAmt"  name="tbCsmStockholderInfo.intangibleAssetsAmt" required="true" 
			class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" onblur="countAmt"/>
			
		<label>其他金额（元）：</label>
		<input id="tbCsmStockholderInfo.otherAmt"  name="tbCsmStockholderInfo.otherAmt" required="true" 
			class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" onblur="countAmt"/>
			
		<label>实际出资金额（元）：</label>
		<input id="tbCsmStockholderInfo.actualInvestmentAmt"  name="tbCsmStockholderInfo.actualInvestmentAmt" 
			class="nui-textbox nui-form-input" enabled="false" vtype="float;maxLength:20" dataType="currency" />
			
		<label>申请注册金额（元）：</label>
		<input id="tbCsmStockholderInfo.applyRegisterAmt"  name="tbCsmStockholderInfo.applyRegisterAmt" required="true" 
			class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" onblur="countAmt"/>
			
		<label>总注册资本（元）：</label>
		<input id="tbCsmStockholderInfo.totalRegisterAmt"  name="tbCsmStockholderInfo.totalRegisterAmt" 
			class="nui-textbox nui-form-input" enabled="false" vtype="float;maxLength:20" dataType="currency" />
		
		<label>占股比例（%）：</label>
		<input id="tbCsmStockholderInfo.totalSharesPercent" name="tbCsmStockholderInfo.totalSharesPercent"  
			 class="nui-textbox nui-form-input" enabled="false"/>
	
		<label>投资到位率（%）：</label>
		<input id="tbCsmStockholderInfo.investmentInPlacePercent" name="tbCsmStockholderInfo.investmentInPlacePercent" 
			 class="nui-textbox nui-form-input" enabled="false" vtype="float;maxLength:13;range:0,100" />
	</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
		<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
			
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
    var partyId = '<%=request.getParameter("partyId") %>';
    var qote = '<%=request.getParameter("qote") %>';
    var stockholderId = '<%=request.getParameter("itemId") %>';
	if (qote=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

	$(document).ready(function(){
		var json=nui.encode({"stockholderId":stockholderId});
		$.ajax({
            url: "com.bos.csm.corporation.Stockholder.findTbCsmStockholderInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
//             	var json = nui.encode({"partyId":partyId});
// 				$.ajax({
// 					url : "com.bos.csm.corporation.corporation.getCorpration.biz.ext",
// 					type : 'POST',
// 					data : json,
// 					cache : false,
// 					contentType : 'text/json',
// 					success : function(text) {
// 						git.unmask("form1");
// 						nui.get("tbCsmStockholderInfo.totalRegisterAmt").setValue(text.item.registerAssets);
// 						nui.get("tbCsmStockholderInfo.currecyCd").setValue(text.item.registerAssetsCurrencyCd);
						
// 						//重新计算
// 						countAmt();
// 					}
// 				});
				git.unmask("form1");
        		form.setData(text);
        		window.form1Data = form.getData();
        		nui.get("tbCsmStockholderInfo.stockeholderName").setText(text.tbCsmStockholderInfo.stockeholderName);
            },
            error: function (jqXHR, textStatus, errorThrown) {
				git.unmask("form1");		               
                nui.alert(jqXHR.responseText);
            }
		});
	});
	
	//非中国企业证件信息非必填
	function noRequired(){
		var legalContry=nui.get("tbCsmStockholderInfo.legalContry").getValue();
		if(legalContry !="CHN"){
		nui.get("tbCsmStockholderInfo.certificateTypeCd").setRequired(false);
		nui.get("tbCsmStockholderInfo.certificateNum").setRequired(false);
		}
	}
	//选择是否我行客户时调用
	function isOwnCust(){
		var iscout= nui.get("tbCsmStockholderInfo.iscout").getValue();
  		if(iscout=="0"){//不是我行客户，允许“手工输入”
  			$("#cardInfo").css("display","none");
  			nui.get("tbCsmStockholderInfo.stockeholderName").setAllowInput(true);
  			nui.get("tbCsmStockholderInfo.orgRegisterCd").setValue("");
  			nui.get("tbCsmStockholderInfo.orgRegisterCd").setEnabled(true);
  		}else{
  			$("#cardInfo").css("display","block");
  		  	nui.get("tbCsmStockholderInfo.certificateTypeCd").setEnabled(false);
  		  	nui.get("tbCsmStockholderInfo.certificateNum").setEnabled(false);
  		  	nui.get("tbCsmStockholderInfo.stockeholderName").setAllowInput(false);
  		}
		nui.get("tbCsmStockholderInfo.stockeholderName").setText("");
		nui.get("tbCsmStockholderInfo.stockeholderName").setValue("");
		nui.get("tbCsmStockholderInfo.certificateTypeCd").setValue("");
		nui.get("tbCsmStockholderInfo.certificateNum").setValue("");
	}
	
	function stockholderTypeCdValueChange(){
		var stockholderTypeCdValue=nui.get("tbCsmStockholderInfo.stockholderTypeCd").getValue();
		if(stockholderTypeCdValue != '6'){//非自然人
			var arr1 = nui.getDictData("XD_ZJLX0002");
			nui.get("tbCsmStockholderInfo.certificateTypeCd").setData(arr1);
			$("#registInfo").css("display","block");
		}else{
					var arr2 = nui.getDictData("CDKH0002");
		nui.get("tbCsmStockholderInfo.certificateTypeCd").setData(arr2);
			$("#registInfo").css("display","none");
		}
	}
	//计算数据
	function countAmt(){
	
		//计算实际出资金额
		countActualInvestmentAmt();
		//计算占股比例，投资到位率
		changeApplyRegisterAmt();
	}
	
	// 计算实际出资金额，规则：实际出资金额=货币金额+实物金额+无形资产金额+其他金额
	function countActualInvestmentAmt(){
	
		var v_currencyAmt = nui.get("tbCsmStockholderInfo.currencyAmt").getValue();
		var v_realObjectAmt =nui.get("tbCsmStockholderInfo.realObjectAmt").getValue();
		var v_intangibleAssetsAmt = nui.get("tbCsmStockholderInfo.intangibleAssetsAmt").getValue();
		var v_otherAmt = nui.get("tbCsmStockholderInfo.otherAmt").getValue();
		
		var actualCurrency = parseFloat(v_currencyAmt)+ parseFloat(v_realObjectAmt)+ 
		parseFloat(v_intangibleAssetsAmt)+ parseFloat(v_otherAmt);
		nui.get("tbCsmStockholderInfo.actualInvestmentAmt").setValue(actualCurrency.toFixed(2));
	}
	
	// 计算占股比例%，投资到位率%;规则：占股比例=申请注册金额/总注册资本，投资到位率=实际出资金额/申请注册金额（保留两位小数）
	function changeApplyRegisterAmt() {
		//申请注册金额
		var applyRegisterAmt = nui.get("tbCsmStockholderInfo.applyRegisterAmt").getValue();
		//总注册资本
		var totalRegisterAmt = nui.get("tbCsmStockholderInfo.totalRegisterAmt").getValue();
		//实际出资金额
		var actualInvestmentAmt = nui.get("tbCsmStockholderInfo.actualInvestmentAmt").getValue();
		//计算占股比例与投资到位率
		if (!isNullOrEmptry(applyRegisterAmt)) {
			
			//反显计算占股比例
			var fRegisterAmt = parseFloat(applyRegisterAmt);
			if (!isNullOrEmptry(totalRegisterAmt)) {
				var percent = fRegisterAmt/parseFloat(totalRegisterAmt)*100;
				nui.get("tbCsmStockholderInfo.totalSharesPercent").setValue(percent.toFixed(2));
			}
			
			//反显计算投资到位率
			if (!isNullOrEmptry(actualInvestmentAmt)) {
				var fActualInvestmentAmt = parseFloat(actualInvestmentAmt);
				var ipercent = fActualInvestmentAmt/fRegisterAmt*100;
				nui.get("tbCsmStockholderInfo.investmentInPlacePercent").setValue(ipercent.toFixed(2));
			}
		}
	}
	// 清空股东信息，用于切换股东类型时调用
	function clearStockHolderInfo() {
		var stockholderTypeCd= nui.get("tbCsmStockholderInfo.stockholderTypeCd").getValue();
  		if(stockholderTypeCd=="2"||stockholderTypeCd=="3"||stockholderTypeCd=="4"||stockholderTypeCd=="5"){//如果股东类型选择2-机关，3-事业单位，4-社会团体，5-其他组织机构，股东名称允许“手工输入”
  			nui.get("tbCsmStockholderInfo.certificateTypeCd").setEnabled(true);
  			nui.get("tbCsmStockholderInfo.certificateNum").setEnabled(true);
  			nui.get("tbCsmStockholderInfo.stockeholderName").setAllowInput(true);
  		}else{
  		  	nui.get("tbCsmStockholderInfo.certificateTypeCd").setEnabled(false);
  		  	nui.get("tbCsmStockholderInfo.certificateNum").setEnabled(false);
  		  	nui.get("tbCsmStockholderInfo.stockeholderName").setAllowInput(false);
  		}
		nui.get("tbCsmStockholderInfo.stockeholderName").setText("");
		nui.get("tbCsmStockholderInfo.stockeholderName").setValue("");
		nui.get("tbCsmStockholderInfo.certificateTypeCd").setValue("");
		nui.get("tbCsmStockholderInfo.certificateNum").setValue("");
	}
	
	
	function selectCust(e) {
		// 01:法人；02：自然人
		var stockHolderType = nui.get("tbCsmStockholderInfo.stockholderTypeCd").getValue();
		if(null == stockHolderType || ''==stockHolderType){
		
			nui.alert("请先选择股东类型！");
			return;
		}
		nui.open({
	        url: nui.context + "/csm/pub/queryCorpAndNartual.jsp?stockholderTypeCd=" + stockHolderType,
	        showMaxButton: true,
	        title: "选择企业或者个人",
	        width: 1000,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
	                	if(data.partyId==partyId){
	                		alert("客户自身不能作为股东，请重新选择!");
	                	}else{
	                    	nui.get("tbCsmStockholderInfo.naturalPartyId")
									.setValue(data.partyId);
							nui.get("tbCsmStockholderInfo.stockeholderName")
									.setText(data.partyName);
							nui.get("tbCsmStockholderInfo.stockeholderName")
									.setValue(data.partyName);
							nui.get("tbCsmStockholderInfo.certificateTypeCd").setValue(
									data.certType);
							nui.get("tbCsmStockholderInfo.certificateNum").setValue(
									data.certNum);
	                	}
	                	//重新计算占股比例
	                	nui.get("tbCsmStockholderInfo.totalSharesPercent").setValue("");
	                	changeApplyRegisterAmt();
	                }
	            }
	        }
	    }); 
    }
    
    //判断值是否为空或null
    function isNullOrEmptry(v){
    
    	if(null == v || ''==v || 'undefined'==v){
    	
    		return true;
    	}else{
    	
    		return false;
    	}
    }
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		
// 		var json1 = {
// 			"naturalPartyId" : nui.get("tbCsmStockholderInfo.naturalPartyId").getValue(),
// 			"partyId" : nui.get("tbCsmStockholderInfo.partyId").getValue()
// 		};
// 		msg = exeRule("CUS_GD", "1", json1);
// 		if (null != msg && '' != msg) {
// 			nui.alert(msg);
// 			return;
// 		}
		
		//实际出资金额不得大于申请注册金额。
		if(parseInt(nui.get('tbCsmStockholderInfo.actualInvestmentAmt').getValue())>parseInt(nui.get('tbCsmStockholderInfo.applyRegisterAmt').getValue())){
			alert('实际出资金额不得大于认缴金额');
			return;
		}
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.corporation.Stockholder.updateTbCsmStockholderInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            		return;
	            	} else {
	            		//alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
			});
	}
</script>
</body>
</html>
