<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-21

  - Description:TB_CSM_INVESTMENT_INFO, com.bos.dataset.csm.TbCsmInvestmentInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">

	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<input name="item.investCustId" id="item.investCustId" class="nui-hidden" />
	<input id="item.investmentId" name="item.investmentId"   class="nui-hidden"  />
	<fieldset>
	  <legend>
	    <span>股东投资情况</span>
	   </legend>
	<div class="nui-dynpanel" columns="4">
	
		<label>被投资客户类型：</label>
		<input id="item.investCustType" name="item.investCustType" dictTypeId="CDKH0034" enabled="false"
			class="nui-dictcombobox nui-form-input" 
			/>
		
		<label>被投资客户名称：</label>
		<input id="item.investCustName"  name="item.investCustName" required="true" enabled="false"  class="nui-buttonEdit"   />
		
		<label>证件类型：</label>
		<input id="item.certType" name="item.certType"enabled="false" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002" />
		
		<label>证件号码：</label>			
		<input id="item.orgRegisterNum"  name="item.orgRegisterNum" enabled="false" class="nui-textbox nui-form-input"/>
		
		<label>出资方式：</label>
		<input id="item.investimentMethodCd" name="item.investimentMethodCd" multiSelect="true" required="true" class="nui-checkboxlist nui-form-input" dictTypeId="CDKH0076"/>

		<label>币种：</label>
		<input id="item.currecyCd" name="item.currecyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001"  />

		<label>投资金额：</label>
		<input id="item.investmentAmt"  name="item.investmentAmt" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency" />

		<label>持股比例（%）：</label>
		<input id="item.shareholdingRatio"  name="item.shareholdingRatio" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:8;range:0,100" />
		
		<label>投资日期：</label>
		<input id="item.investmentDate" name="item.investmentDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
	</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
			
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
    var partyId = "<%=request.getParameter("partyId") %>";
    var investmentId = "<%=request.getParameter("investmentId") %>";
    
	if (partyId) {
		nui.get("item.partyId").setValue(partyId);
		
		var ways = mini.getDictData("CDKH0076"); 
		var datajson=[];
		for(var i=0; i<ways.length; i++){
			var json = {};
			json["id"]=ways[i].dictID;
	    	json["text"]=ways[i].dictName;
	    	datajson[i]=json;
		}
		nui.get('item.investimentMethodCd').setData(datajson);
	}
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

	function initForm() {
		var json=nui.encode({"investId":investmentId,"partyId":partyId});
		$.ajax({
	            url: "com.bos.csm.corporation.Investment.getTbCsmInvestmentInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            		nui.get("item.investCustName").setText(text.item.investCustName);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            	git.unmask("form1");
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
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		//nui.alert(json);return;
		$.ajax({
	            url: "com.bos.csm.corporation.Investment.updateTbCsmInvestmentInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
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
