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
	<div class="nui-dynpanel" columns="2" id="table">
		<label class="nui-form-label">权利价值（元）：</label>
		<input id="mortgageValue" name="grtMortgageBasic.mortgageValue" vtype="float;maxLength:20;range:1" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
		
		<!-- <label class="nui-form-label">本次担保金额（元）：</label>
		<input id="suretyAmt" name="bizGrtRel.suretyAmt" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/> -->
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
	
	
	if(view=="0"){
		initData();
	}else{
		nui.get("grtMortgageBasic.suretyId").setValue(suretyId);
	}
	
	function initData(){
		var json=nui.encode({"grtMortgageBasic":{"suretyId":suretyId}});
		$.ajax({
        	url: "com.bos.grt.grtManage.mortgageSort.getMortgageValue.biz.ext",
        	type: 'POST',
        	data: json,
        	cache: false,
        	contentType:'text/json',
        	success: function (text) {
        		var o=nui.decode(text);
				form.setData(o);
				if("1" == o.grtMortgageBasic.ifCon){
					nui.get("mortgageValue").setEnabled(false);
				}
				form.validate();
				nui.get("grtMortgageBasic.suretyId").setValue(suretyId);
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
        GetData();
		CloseWindow("ok");
		return;
	}
</script>
</body>
</html>