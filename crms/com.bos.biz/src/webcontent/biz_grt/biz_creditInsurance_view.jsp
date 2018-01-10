<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-28

  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="item.relationId" id="relationId" class="nui-hidden" />
	<input name="tbGrtCreditsafe.suretyId" id="tbGrtCreditsafe.suretyId" class="nui-hidden" />
	<input type="hidden" id="reType" name="item.reType" value="03" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
		<label>保险公司名称：</label>
		<input id="dzy" name="tbGrtCreditsafe.insuranceCompanyName" required="true" class="nui-textbox nui-form-input"  />
		
		<label>信用保险类型：</label>
		<input name="tbGrtCreditsafe.insuranceCoverage" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YP_GLCD0119" />
		
		<label>保险单编号：</label>
		<input name="tbGrtCreditsafe.insurancePolicyNum" required="true" class="nui-textbox nui-form-input"  />
		
		<label>可用金额：</label>
		<input name="tbGrtCreditsafe.availableAmt" required="true" class="nui-textbox nui-form-input"  />
		
		<label>已用金额：</label>
		<input name="tbGrtCreditsafe.outstandingAmt" required="true" class="nui-textbox nui-form-input"  />
		
		<label>币种：</label>
		<input name="tbGrtCreditsafe.currencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"  />
		
		<label>收益人：</label>
		<input name="tbGrtCreditsafe.beneficiary" required="true" class="nui-textbox nui-form-input"  />
		
		<label>投保人：</label>
		<input name="tbGrtCreditsafe.policyHolder" required="true" class="nui-textbox nui-form-input"  />
		
		<label>本次占用价值：</label>
		<input id="item.debtAmount" vtype="float;maxLength:26" name="item.debtAmount" required="true" class="nui-textbox nui-form-input"   />
		
		
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
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
	function initForm() {
		var json=nui.encode({"item":{"relationId":"<%=request.getParameter("relationId") %>",
			"_entity":"com.bos.dataset.biz.TbBizGrtRelation"}});
		$.ajax({
	            url: "com.bos.biz.Collateral.getCollatera.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            		nui.get("relationId").setValue("<%=request.getParameter("relationId") %>");
	            		nui.get("tbGrtCreditsafe.suretyId").setValue(text.tbGrtCreditsafe.suretyId);
	            		nui.get("item.debtAmount").setValue(text.tbGrtCreditsafe.debtAmount);
	            		nui.get("reType").setValue("03");
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
		}
		if("<%=request.getParameter("type") %>"!="null"){
			if(form.isDataChanged()){
        		
        	}else{
        		nui.alert("您并未做出相应的信息修改，请勿重复此操作。","无效操作");
        		nui.get("btnCreate").setEnabled(true);
        		return;
        	}		
		}else{
			var o=form.getData();
			var json=nui.encode(o);
			//nui.alert(json);return;
			$.ajax({
		            url: "com.bos.biz.Collateral.addOrUpdateCollateral.biz.ext",
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
		}
	}
</script>
</body>
</html>
