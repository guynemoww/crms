<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-06 18:52:09
  - Description:
-->
<head>
<title>填写押品信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="grtMortgageBasic.suretyId" id="grtMortgageBasic.suretyId" class="nui-hidden" />
	<!-- <input id="aviAmt" name="aviAmt"  class="nui-hidden"/> -->
	<div class="nui-dynpanel" columns="2" id="table">
		<label class="nui-form-label">业务申请担保金额（元）：</label>
		<input id="grtBasic.suretyAmt" name="grtBasic.suretyAmt" vtype="float;maxLength:20" required="true" class="nui-text nui-form-input" dataType="currency"/>
	
		<label class="nui-form-label">业务申请可用金额（元）：</label>
		<input id="grtBasic.usableGuaranteeLimit" name="grtBasic.usableGuaranteeLimit" class="nui-text nui-form-input" dataType="currency"/>
		
		<label class="nui-form-label">担保确认金额（元）：</label>
		<input id="suretyAmt" name="suretyAmt" vtype="float;maxLength:20;range:1" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="saveSubGrtRel_save" iconCls="icon-save" onclick="save">确定</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var view="<%=request.getParameter("view") %>";
	var relationId="<%=request.getParameter("relationId") %>";
	var suretyId="<%=request.getParameter("suretyId") %>";
	var subcontractId="<%=request.getParameter("subcontractId") %>";
	
	initData();
	function initData(){
		var json=nui.encode({"suretyId":suretyId,"subcontractId":subcontractId});
		$.ajax({
        	url: "com.bos.grt.grtManage.mortgageSort.getSubConBzr.biz.ext",
        	type: 'POST',
        	data: json,
        	cache: false,
        	contentType:'text/json',
        	success: function (text) {
        		var o=nui.decode(text);
        		form.setData(o);
        		//担保确认金额赋值为业务申请可用金额
        		nui.get("suretyAmt").setValue(o.grtBasic.usableGuaranteeLimit);
				form.validate();
        	},
        	error: function (jqXHR, textStatus, errorThrown) {
            	nui.alert(jqXHR.responseText);
        	}
		});
	}
	
	function GetData(){
		var data = form.getData();
		return data;
	}
	function save(){
		form.validate();
		
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        var o = form.getData();
        if(o.suretyAmt<=0){
        	alert("请输入正确的担保确认金额");
        	return;
        }
        if(Number(o.suretyAmt)>Number(o.grtBasic.usableGuaranteeLimit)){
        	alert("担保确认金额不能大于保证人业务申请可用金额");
        	return;
        }
        GetData();
		CloseWindow("ok");
		return;
	}
</script>
</body>
</html>