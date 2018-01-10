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
		<input id="grtBasic.mortgageValue" name="grtBasic.mortgageValue" vtype="float;maxLength:20" class="nui-text nui-form-input" dataType="currency"/>
		
		<label class="nui-form-label">可用权利价值（元）：</label>
		<input id="aviAmt" name="aviAmt" vtype="float;maxLength:20" class="nui-text nui-form-input" dataType="currency"/>
	
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
        	url: "com.bos.grt.grtManage.mortgageSort.getSubCon.biz.ext",
        	type: 'POST',
        	data: json,
        	cache: false,
        	contentType:'text/json',
        	success: function (text) {
        		var o=nui.decode(text);
    			nui.get("grtBasic.mortgageValue").setValue(o.grtBasic.mortgageValue);
    			nui.get("aviAmt").setValue(o.grtBasic.aviAmt);
    			//最高抵押 抵押合同基本信息界面，抵押最高本金限额与权利价值不一致需要提示
    			if("1"==o.conSub.ifTopSubcon && o.conSub.zgbjxe!=o.grtBasic.mortgageValue){
    				if(o.conSub.subcontractType=="01"){//抵押合同
	    				alert("抵押最高本金限额与权利价值不一致");
    				}
    				if(o.conSub.subcontractType=="02"){//质押合同
	    				alert("质押最高本金限额与权利价值不一致");
    				}
    			}
    			if("1" == o.conSub.ifTopSubcon){
					nui.get("suretyAmt").setValue(o.grtBasic.aviAmt);
					//业务要求 若为最高额抵押 则可以手输20170925
					//nui.get("suretyAmt").setEnabled(false);
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
        if(""==o.grtBasic.mortgageValue){
        	alert("请先维护押品权利价值");
        	return;
        }
        if(o.suretyAmt<=0){
        	alert("请输入正确的担保确认金额");
        	return;
        }
        if(Number(o.suretyAmt)>Number(o.aviAmt)){
        	alert("担保确认金额不能突破押品可用担保额度");
        	return;
        }
        GetData();
		CloseWindow("ok");
		return;
	}
</script>
</body>
</html>