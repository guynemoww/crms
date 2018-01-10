<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:12:58
  - Description:
-->
<head>
<title>法律诉讼</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
	<div class="nui-dynpanel" columns="4" id="table1">
  
		<label id="proceedingsType" class="nui-form-label" >诉讼处置方式：</label>
		<input id="datailScheme.proceedingsType" name="datailScheme.proceedingsType" 
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="bondsman">担保人：</label>
  		<input id="datailScheme.bondsman" name="datailScheme.bondsman"   
  		class="nui-textbox nui-form-input" required="true"  />
  
		<label id="dispositionPrincipalAmount" class="nui-form-label" >处置本金金额：</label>
		<input id="datailScheme.dispositionPrincipalAmount" name="datailScheme.dispositionPrincipalAmount"
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="edispositionInterestAmount" class="nui-form-label" >处置利息金额：</label>
  		<input id="datailScheme.dispositionInterestAmount" name="datailScheme.dispositionInterestAmount" 
  		class="nui-textbox nui-form-input" required="true" />
  
		<label id="totalAmount" class="nui-form-label" >合计：</label>
		<input id="datailScheme.totalAmount" name="datailScheme.totalAmount"   
		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="proceedingExpenses">诉讼费用：</label>
		<input id="datailScheme.proceedingExpenses" name="datailScheme.proceedingExpenses"   
 		class="nui-textbox nui-form-input" required="true"  />
  
		<label id="proceedingDate">诉讼日期：</label>
		<input id="datailScheme.proceedingDate" name="datailScheme.proceedingDate"   
		class="nui-datepicker nui-form-input" required="true"  />
		
		<label id="localAgent">本行代理人：</label>
		<input id="datailScheme.localAgent" name="datailScheme.localAgent"   
		class="nui-textbox nui-form-input" required="true"  />
		
		<label id="lawFirm">代理律师事务所：</label>
		<input id="datailScheme.lawFirm" name="datailScheme.lawFirm"   
		class="nui-textbox nui-form-input" required="true"  />
		
		<label id="lawyerDescribed">代理律师描述：</label>
		<input id="datailScheme.lawyerDescribed" name="datailScheme.lawyerDescribed"   
		class="nui-textbox nui-form-input" required="true"  />
		
		<label id="trialInstitutione">审理机构：</label>
		<input id="datailScheme.trialInstitution" name="datailScheme.trialInstitution"   
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

		<label id="proceedingInstruction" class="nui-form-label" >诉讼说明：</label>
		<input id="datailScheme.proceedingInstruction"  name="datailScheme.proceedingInstruction"   
		class="nui-textarea nui-form-input" required="true" colspan="3"/>
  		
		<label id="caseDescription" class="nui-form-label" >案件描述：</label>
		<input id="datailScheme.caseDescription"  name="datailScheme.caseDescription"   
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
        o.datailScheme.dispostionType = '3';
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