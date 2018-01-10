<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:12:58
  - Description:
-->
<head>
<title>资产抵债</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
	<div class="nui-dynpanel" columns="4" id="table1">
  
		<label id="mortgageAssetsDispositionTy" class="nui-form-label" >抵债资产处置方式：</label>
		<input id="datailScheme.mortgageAssetsDispositionTy" name="datailScheme.mortgageAssetsDispositionTy" 
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="mortgageAssetsChargingType">抵债资产收取方式：</label>
  		<input id="datailScheme.mortgageAssetsChargingType" name="datailScheme.mortgageAssetsChargingType"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="dispositionPrincipalAmount" class="nui-form-label" >处置本金金额：</label>
		<input id="datailScheme.dispositionPrincipalAmount" name="datailScheme.dispositionPrincipalAmount"
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="dispositionInterestAmount" class="nui-form-label" >处置利息金额：</label>
  		<input id="datailScheme.dispositionInterestAmount" name="datailScheme.dispositionInterestAmount" 
  		class="nui-textbox nui-form-input" required="true" />
  
		<label id="mortgageAssetsValue" class="nui-form-label" >抵债资产价值：</label>
		<input id="datailScheme.mortgageAssetsValue" name="datailScheme.mortgageAssetsValue"
		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="mortgageExpenses">抵债费用：</label>
		<input id="datailScheme.mortgageExpenses" name="datailScheme.mortgageExpenses"
		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="mortgageAssetsEntryValue">抵债资产入账价值：</label>
		<input id="datailScheme.mortgageAssetsEntryValue" name="datailScheme.mortgageAssetsEntryValue"
		class="nui-textbox nui-form-input" required="true"  />
		
		<label id="mortgageAssetsIntoAmount">抵债资产转入金额：</label>
		<input id="datailScheme.mortgageAssetsIntoAmount" name="datailScheme.mortgageAssetsIntoAmount" 
		class="nui-textbox nui-form-input" required="true"  />
		
		<label id="userNum">经办人：</label>
		<input id="datailScheme.userNum" name="datailScheme.userNum"   
		class="nui-textbox nui-form-input" required="true"  />
		
		<label id="orgNum">经办机构：</label>
		<input id="datailScheme.orgNum" name="datailScheme.orgNum"   
		class="nui-textbox nui-form-input" required="true"  />
		
		<label id="handingDate">经办日期：</label>
		<input id="datailScheme.handingDate" name="datailScheme.handingDate"   
		class="nui-textbox nui-form-input" required="true"  />
  
		<label></label><label></label>
		<label id="mortgageExpensesInstruction" class="nui-form-label" >抵债费用说明：</label>
		<input id="datailScheme.mortgageExpensesInstruction"  name="datailScheme.mortgageExpensesInstruction"   
		class="nui-textarea nui-form-input" required="true" colspan="3"/>
		<label id="supplementaryInstruction" class="nui-form-label" >补充说明：</label>
		<input id="datailScheme.supplementaryInstruction"  name="datailScheme.supplementaryInstruction"   
		class="nui-textarea nui-form-input" required="true" colspan="3"/>
		<label id="dispositionOpinion" class="nui-form-label" >处置意见：</label>
		<input id="datailScheme.dispositionOpinion"  name="datailScheme.dispositionOpinion"   
		class="nui-textarea nui-form-input" required="true" colspan="3"/>
		<input id="datailScheme.collectionId" name="datailScheme.collectionId" 
		class="nui-hidden nui-form-input" required="true"/>
		<input id="datailScheme.schemeDetailId" name="datailScheme.schemeDetailId"
		class="nui-hidden nui-form-input" required="true"/>
		<input id="datailScheme.dispostionType" name="datailScheme.dispostionType"
		class="nui-hidden nui-form-input" required="true"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="save" iconCls="icon-save" onclick="create">确定</a>
		<a class="nui-button" id="" iconCls="icon-close" onclick="back">关闭</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	//修改或查看
	var editFlag = "<%=request.getParameter("editFlag")%>";
	if('0'== editFlag){
		form.setEnabled(false);
	}
	query();
	//查询
	function query(){
		var json = nui.encode({"schemeDetailId":"<%=request.getParameter("schemeDetailId")%>"});
		$.ajax({
	        url: "com.bos.npl.assets.AssetsDispositionSchemeDetail.getSchemeTypeDetail.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	var o = nui.decode(mydata);
	        	form.setData(o);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	nui.alert(jqXHR.responseText);
	        }
     	});
	}
	//保存
	function create(){
 		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        var o = form.getData();
        o.datailScheme.dispostionType = '7';
        o.datailScheme.schemeDetailId = '<%=request.getParameter("schemeDetailId")%>';
        var json = nui.encode(o);
        $.ajax({
	        url: "com.bos.npl.assets.AssetsDispositionSchemeDetail.createSchemeTypeDetail.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	alert("保存成功！");
	        	query();
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        nui.alert(jqXHR.responseText);
	        }
     	});
	}
	//关闭
	function back() {
 		CloseWindow("ok");
 	}
</script>
</body>
</html>