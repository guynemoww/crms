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
	<div class="nui-dynpanel" columns="2" id="table">
<%--		<label class="nui-form-label">权利价值（元）：</label>
		<input id="mortgageValue" name="grtMortgageBasic.mortgageValue" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
		--%>
		<label class="nui-form-label">合同可用金额（元）：</label>
		<input id="conSub.aviAmt" name="conSub.aviAmt" vtype="float;maxLength:20;range:1" class="nui-text nui-form-input" dataType="currency"/>
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
	var subcontractId="<%=request.getParameter("subcontractId") %>";
	var data;
	var conSubconId="<%=request.getParameter("conSubconId") %>";
	var xgbz="<%=request.getParameter("xgbz")%>";
	var contractId="<%=request.getParameter("contractId") %>";
	
	
	initPage();
	function initPage(){
		var json = nui.encode({"subcontractId":subcontractId,"conSubconId":conSubconId,"xgbz":xgbz,"contractId":contractId});
		$.ajax({
            url: "com.bos.bizInfo.bizGrt.getSubCon.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	if(null == o.conSub.aviAmt){
            		o.conSub.aviAmt=o.conSub.zgbjxe;
            	}
            	else {
            	if(xgbz=="1"){
            	o.conSub.aviAmt=o.conSub.zgbjxe-o.item[0].YYJE;
            	}
            	
            	}
            	
            		
            	o.suretyAmt=o.conSubRel.suretyAmt;
            	form.setData(o);
			}
        });
	}

	function GetData(){
		  data = form.getData();
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
        	alert("请输入正确的本次担保金额");
        	return;
        }
        if(Number(o.suretyAmt) > Number(o.conSub.aviAmt)){
        	nui.get("suretyAmt").setValue("");
        	form.validate();
        	alert("本次担保金额不可超过合同可用金额");
        	return;
        }
        
        if (xgbz=="1") {
        						data = form.getData();
				        				var json = nui.encode({"conSubcontractRel":{"conSubconId":conSubconId,"suretyAmt":data.suretyAmt}});
										//将担保物关联到抵质押合同
										$.ajax({
									        url: "com.bos.grt.conGrt.saveConGrtRelAmt.biz.ext",
									        type: 'POST',
									        data: json,
									        contentType:'text/json',
									        cache: false,
									        success: function (data) {
								            	//CloseWindow("ok");
								            	alert("保存成功");
									        },
									        error: function (jqXHR, textStatus, errorThrown) {
									            alert(jqXHR.responseText);
									        }
								        });
				                    }else{
				                    
				                    GetData();
				                    }
		CloseWindow("ok");
		return;
	}
</script>
</body>
</html>