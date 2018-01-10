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
	<input id="aviAmt" name="aviAmt" required="true" class="nui-hidden nui-form-input"/>
	<div class="nui-dynpanel" columns="2" id="table">
		<label class="nui-form-label">权利价值（元）：</label>
		<input id="totalAmt" name="totalAmt" vtype="float;maxLength:20" class="nui-text nui-form-input" dataType="currency"/>
		
		<label class="nui-form-label">可用权利价值（元）：</label>
		<input id="aviAmt" name="aviAmt" vtype="float;maxLength:20" class="nui-text nui-form-input" dataType="currency"/>
	
		<label class="nui-form-label">本次担保金额（元）：</label>
		<input id="suretyAmt" name="suretyAmt" vtype="float;maxLength:20;range:1" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="saveSubGrtRel_save" iconCls="icon-save" onclick="save">确定</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var suretyId="<%=request.getParameter("suretyId") %>";
	var subcontractId="<%=request.getParameter("subcontractId") %>";
	
	initData();
	function initData(){
		var json=nui.encode({"suretyId":suretyId,"subcontractId":subcontractId});
		$.ajax({
        	url: "com.bos.grt.subContractManage.subContract.findGrtSuretyAmt.biz.ext",
        	type: 'POST',
        	data: json,
        	cache: false,
        	contentType:'text/json',
        	success: function (data) {
        		var o=nui.decode(data);
    			nui.get("totalAmt").setValue(o.data.totalAmt);
    			nui.get("aviAmt").setValue(o.data.aviAmt);
				if(o.data.type==1){//抵质押
					if(o.data.ifTopSubcon==1){//最高额
						nui.get("suretyAmt").setValue(o.data.aviAmt);
						nui.get("suretyAmt").setEnabled(false);
					}
				}else{//保证
					if(o.data.ifTopSubcon==0){//非最高额
						nui.get("suretyAmt").setValue(o.data.aviAmt);
						nui.get("suretyAmt").setEnabled(false);
					}
				}
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
        if(""==o.totalAmt){
        	alert("请先维护押品权利价值");
        	return;
        }
        if(o.suretyAmt<=0){
        	alert("请输入正确的本次保证金额");
        	return;
        }
        if(Number(o.suretyAmt)>Number(o.aviAmt)){
        	alert("本次担保金额不能突破押品可用担保额度");
        	return;
        }
        GetData();
		CloseWindow("ok");
		return;
	}
</script>
</body>
</html>