<%@page pageEncoding="UTF-8"%>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): pc
  - Date: 2016-05-10 11:01:34
  - Description:
-->
<head>
<title>家庭财务信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
	<center>
		<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
			<fieldset>
				<legend>
					<span>基本信息</span>
				</legend>
				<input id="family.applyId" class="nui-hidden nui-form-input" name="family.applyId" value="<%=request.getParameter("partyId")%>" />
				<input id="family.financeId" class="nui-hidden nui-form-input" name="family.financeId"/>
				<input id="family.createTime" class="nui-hidden nui-form-input" name="family.createTime" />
				<input id="natural.partyId" class="nui-hidden nui-form-input" name="natural.partyId" value="<%=request.getParameter("applyId")%>" />
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">客户名称：</label> 
					<input id="party.partyName"
						class="nui-textbox nui-form-input" name="party.partyName"
						required="true" readonly="true"
						Enabled="false"/> 
					<label class="nui-form-label">证件类型：</label> 
					<input id="party.certType" name="party.certType" valueField="dictID"
						textField="dictName" class="nui-dictcombobox nui-form-input"
						dictTypeId="CDKH0002" required="true" readonly="true"
						Enabled="false"/> 
					<label class="nui-form-label">证件号码：</label> 
					<input id="party.certNum"
						class="nui-textbox nui-form-input" name="party.certNum"
						required="true" readonly="true"
						Enabled="false" /> 
					<label class="nui-form-label">是否信贷客户：</label> 
					<input id="party.isPotentialCust" name="party.isPotentialCust"
						valueField="dictID" textField="dictName"
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"
						required="true" readonly="true"
						Enabled="false" />
				</div>
			</fieldset>
			<fieldset>
				<legend>
					<span>家庭财务信息</span>
				</legend>
				<input id="family.partyId" class="nui-hidden nui-form-input" name="family.partyId" value="<%=request.getParameter("applyId")%>"/>
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">日期：</label>
					<input id="family.financeDate" class="nui-datepicker nui-form-input" name="family.financeDate"  allowInput="false"
						required="true" format="yyyy-MM-dd"/>
					<label class="nui-form-label">个人月收入：</label>
					<input id="family.personMonthlyIncome" name="family.personMonthlyIncome"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label id="nui-form-label">家庭稳定收入（月）：</label>
					<input id="family.familySteadyIncome" name="family.familySteadyIncome" 
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency" required="true" />
					<label class="nui-form-label">家庭租金收入（月）：</label>
					<input id="family.familyRentIncome" name="family.familyRentIncome"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label class="nui-form-label">家庭投资分红（月）：</label> 
					<input id="family.familyInvestmentShare" name="family.familyInvestmentShare"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label class="nui-form-label">家庭其它收入（月）：</label> 
					<input id="family.familyOtherIncome" name="family.familyOtherIncome"
						class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"
						required="true" />
					<label class="nui-form-label">金融资产：</label> 
					<input id="family.financialAssets" name="family.financialAssets"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true"/>
					<label class="nui-form-label">固定资产：</label> 
					<input id="family.fixedAssets" name="family.fixedAssets"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true" />	
					<label class="nui-form-label">其它资产及投资：</label> 
					<input id="family.otherAsset" name="family.otherAsset"
						class="nui-textbox nui-form-input"  vtype="float;maxLength:20" dataType="currency"
						required="true"/>	
					<label class="nui-form-label">负债及或有负债：</label>
					<input id="family.incurDebts"
						class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"
						name="family.incurDebts" required="true"/>
					<label class="nui-form-label">每月其它债务支出：</label>
					<input id="family.monthDebtPay" name="family.monthDebtPay"
						class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:20" dataType="currency"/>  
					<label class="nui-form-label">备注：</label>
					<input id="family.remark" name="family.remark"
						class="nui-textbox nui-form-input" required="false"/>
 				</div>
			</fieldset>
			<!-- <fieldset>
				<legend>
					<span>系统信息</span>
				</legend>
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">经办机构：</label> 
					<input id="family.orgNum" class="nui-text nui-form-input" 
						name="family.orgNum" dictTypeId="org" />
					<label class="nui-form-label">经办人：</label> 
					<input id="family.userNum" class="nui-text nui-form-input"
						name="family.userNum" dictTypeId="user" /> 
					<label class="nui-form-label">经办日期：</label> 
					<input id="family.dealDate" class="nui-text nui-form-input"
						name="family.dealDate" dateFormat="yyyy-MM-dd" />
				</div>
			</fieldset> -->
			
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="btnSave" iconCls="icon-save" onclick="update">保存</a>
		</div>
</center>
<script type="text/javascript">
    nui.parse();
    git.mask("form1");
    var form = new nui.Form("#form1");
    var proFlag = "<%=request.getParameter("proFlag")%>" ;
	if(proFlag!=1){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	}	
    <%--  数据初始化--%>
	function initForm(){
		var partyId = "<%=request.getParameter("partyId")%>";
		var applyId = "<%=request.getParameter("applyId")%>";
	  	var json = nui.encode({"family":{"partyId":partyId,"applyId":applyId}});
		$.ajax({
			  url: "com.bos.bizInfo.person.queryFamilyFinance.biz.ext",
			  type: 'POST',
			  data: json,
			  cache: false,
			  contentType: 'text/json',
			  success: function (mydata) {
	          		 git.unmask("form1");
	                 var o = nui.decode(mydata);
	                 form.setData(o);
	          }, 
              error: function (jqXHR, textStatus, errorThrown) {
            	git.unmask("form1");
                nui.alert(jqXHR.responseText);
              }     
		});
	 }
	initForm();
	function update(){
			form.validate();
	        if (form.isValid()==false)
	        	return;      
	       var o = form.getData();
	        var json = nui.encode(o);
	        $.ajax({
	            url: "com.bos.bizInfo.person.saveFamilyFinance.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		alert(text.msg);
	            	} else {
	            		alert("保存成功！");           			
	            		
	            	}
	            	initForm();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
	        
		}
</script>
</body>
</html>