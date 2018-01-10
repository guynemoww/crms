<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:12:58
  - Description:
-->
<head>
<title>代理处置</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
	<div class="nui-dynpanel" columns="4" id="table1">
  
		<label id="dispositionType" class="nui-form-label" >代理处置方式：</label>
		<input id="datailScheme.dispositionType"  name="datailScheme.dispositionType" 
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="trusteeName">受托人名称：</label>
  		<input id="datailScheme.trusteeName" name="datailScheme.trusteeName"
  		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="ifLegalTeamMembers" class="nui-form-label" >是否为律师团成员：</label>
		<input id="datailScheme.ifLegalTeamMembers" name="datailScheme.ifLegalTeamMembers" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="trusteeContacts" class="nui-form-label" >受托人联系方式：</label>
  		<input id="datailScheme.trusteeContacts" name="datailScheme.trusteeContacts" 
  		class="nui-textbox nui-form-input" required="true" />
  
		<label id="agentExpenses" class="nui-form-label" >代理费用：</label>
		<input id="datailScheme.agentExpenses" name="datailScheme.agentExpenses"
		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="delegationBeginDate">委托起始日期：</label>
		<input id="datailScheme.delegationBeginDate" name="datailScheme.delegationBeginDate"
		class="nui-datepicker nui-form-input" required="true"/>
  		
  		<label id="delegationEndDate">委托终止日期：</label>
		<input id="datailScheme.delegationEndDate" name="datailScheme.delegationEndDate"
		class="nui-datepicker nui-form-input" required="true"/>
  		
  		<label id="localAgent">本行参与人员：</label>
		<input id="datailScheme.localAgent" name="datailScheme.localAgent"
		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="delegationAmount">委托案件金额：</label>
		<input id="datailScheme.delegationAmount" name="datailScheme.delegationAmount"
		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="delegationPrincipalAmount">委托案件本金：</label>
		<input id="datailScheme.delegationPrincipalAmount" name="datailScheme.delegationPrincipalAmount"
		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="delegationInterestAmount">委托案件利息：</label>
		<input id="datailScheme.delegationInterestAmount" name="datailScheme.delegationInterestAmount"
		class="nui-textbox nui-form-input" required="true"/>
  		
  		<label id="otherAmount">其他金额：</label>
		<input id="datailScheme.otherAmount" name="datailScheme.otherAmount"
		class="nui-textbox nui-form-input" required="true"/>
		
		<label id="userNum">经办人：</label>
		<input id="datailScheme.userNum" name="datailScheme.userNum"
		class="nui-textbox nui-form-input" required="true"/>
		
		<label id="orgNum">经办机构：</label>
		<input id="datailScheme.orgNum" name="datailScheme.orgNum"
		class="nui-textbox nui-form-input" required="true"/>
		
		<label id="handingDate">经办日期：</label>
		<input id="datailScheme.handingDate" name="datailScheme.handingDate"
		class="nui-textbox nui-form-input" required="true"/>
  
		<label></label><label></label>
		<label id="expensesInstruction" class="nui-form-label" >费用说明：</label>
		<input id="datailScheme.expensesInstruction"  name="datailScheme.expensesInstruction"
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
        o.datailScheme.dispostionType = '4';
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