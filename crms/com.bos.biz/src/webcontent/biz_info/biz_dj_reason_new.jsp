<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>冻结</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>

<body>
<center>
	<!--<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
			<input id="tbBizAmountApprove.attachedId" name="tbBizAmountApprove.attachedId"
				class="nui-hidden nui-form-input" />
			<div class="nui-dynpanel" columns="4">
		<label>冻结原因：</label>
		<input id="tbBizAmountApprove.attachedDesc" name="tbBizAmountApprove.attachedDesc" class="nui-textarea nui-form-input"
			 required="true"/>
	</div>	-->
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="tbBizAmountApprove.amountId" name="tbBizAmountApprove.amountId" class="nui-hidden nui-form-input" />
	<div class="nui-dynpanel" columns="4">
				<label>客户编号：</label>
				<div style="width:120%">
					<input id="zh" name="zh"  style="width:68%;float:left"    class="nui-text nui-form-input" />
				</div>
												
				<label>冻结种类：</label>
				<div style="width:100%">
				<input id="tbBizJdDjFlow.frzType" name="tbBizJdDjFlow.frzType" enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="CD100003"  />
				</div>
				
				<label>可冻结金额：</label>
				<input id="kdjje"  name="kdjje"   class="nui-text nui-form-input"/>
				
				<label>冻结金额：</label>
				<input id="tbBizJdDjFlow.frzAmt"  name="tbBizJdDjFlow.frzAmt"   class="nui-text nui-form-input"   onblur="amountValid"/>
				
				<label>冻结原因：</label>
				<input id="tbBizJdDjFlow.frzReason"  name="tbBizJdDjFlow.frzReason"  required="true"  class="nui-textarea nui-form-input" vtype="maxLength:190"/>
				
				<label>是否到期自动解冻：</label>
				<input id="tbBizJdDjFlow.operFlag"  name="tbBizJdDjFlow.operFlag"   enabled="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="zdjdChange"/>
				
				<label id="frzEndDateLable" class="nui-form-label">冻结终止日期：</label>
				<input id="tbBizJdDjFlow.frzEndDate"  name="tbBizJdDjFlow.frzEndDate"  required="true" class="nui-datepicker nui-form-input"  allowInput="false" />

		</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var applyId = "<%=request.getParameter("applyId") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var amountId = "<%=request.getParameter("amountId") %>";
	var useAmt = "<%=request.getParameter("useAmt") %>";
	var partyNum = "<%=request.getParameter("partyNum") %>";
	var approveId = "<%=request.getParameter("approveId") %>";
	
	//可解冻金额---批复可用金额 
	nui.get("kdjje").setValue(useAmt);
	//冻结金额---全部金额冻结
	nui.get("tbBizJdDjFlow.frzAmt").setValue(useAmt);
	//冻结种类---金额冻结 
	nui.get("tbBizJdDjFlow.frzType").setValue("11");
	//是否到期自动解冻---否
	nui.get("tbBizJdDjFlow.operFlag").setValue("0");
	
	//客户编号
	nui.get("zh").setValue(partyNum);
	//解冻终止日期---默认隐藏 	
	$("#frzEndDateLable").css("display","none");
	nui.get("tbBizJdDjFlow.frzEndDate").hide();
	//保存
	function create(){
		var AccNo = nui.get("zh").getValue();
    	var FrzType  = nui.get("tbBizJdDjFlow.frzType").getValue();//冻结类型
    	var FrzAmt = nui.get("tbBizJdDjFlow.frzAmt").getValue();//冻结金额
    	var FrzReason = nui.get("tbBizJdDjFlow.frzReason").getValue();//冻结原因
    	var OperFlag = nui.get("tbBizJdDjFlow.operFlag").getValue();//是否到期自动解冻
    	var EndDate = nui.get("tbBizJdDjFlow.frzEndDate").getValue();//冻结终止日期
    	
		var reg = new RegExp("^[0-9]*$");
		if (!reg.test(AccNo) || AccNo == null || AccNo == '') {
			nui.alert("请输入合法账号！");
			return;
		} else if (FrzType == null || FrzType == '') {
			nui.alert("请输入冻结类型！");
			return;
		} else if (!reg.test(FrzAmt) || FrzAmt == null || FrzAmt == '') {
			nui.alert("请输入合法的需冻结金额！");
			return;
		} else if (FrzReason == null || FrzReason == '') {
			nui.alert("请输入需冻结原因！");
			return;
		} else if (OperFlag == null || OperFlag == '') {
			nui.alert("请输入是否到期自动解冻！");
			return;
		} else if (OperFlag == '1') {
			if (EndDate == null || EndDate == '') {
				nui.alert("请输入到期自动解冻日期！");
				return;
			}
		}
		nui.get("btnCreate").setEnabled(false);
		//var o = form.getData();
	    //var json = nui.encode(o.tbBizJdDjFlow);
		var json=nui.encode({"amountId":amountId ,"approveId":approveId, "frzType": FrzType , "frzAmt" :FrzAmt, "frzReason":FrzReason, "operFlag":OperFlag , "endDate":EndDate});
		$.ajax({
			url : "com.bos.bizInfo.bizInfo.saveFrzDataAndCallCoreImpl.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			cache : false,
			success : function(mydata) {
				nui.alert(mydata.msg);
				CloseWindow("ok");
			}
		});
		nui.get("btnCreate").setEnabled(true);
	}
	//是否到期自动解冻
	function zdjdChange(){
		var operFlag = nui.get("tbBizJdDjFlow.operFlag").getValue();
		if(operFlag=="1"){
			$("#frzEndDateLable").css("display","block");
			nui.get("tbBizJdDjFlow.frzEndDate").show();
		}else{
			$("#frzEndDateLable").css("display","none");
			nui.get("tbBizJdDjFlow.frzEndDate").hide();
		}
	}
	//冻结金额校验---类型和数值的校验
	function amountValid(){
		var reg = new RegExp("^[0-9]*$");
		var frzAmt = nui.get("tbBizJdDjFlow.frzAmt").getValue();
		if (!reg.test(frzAmt)) {
			nui.alert("需冻结金额只能为数字类型!");
			nui.get("tbBizJdDjFlow.frzAmt").setValue("");
		}
		if(parseFloat(useAmt) < parseFloat(frzAmt)){
			nui.alert("需冻结金额["+frzAmt+"]不能大于可冻结金额["+useAmt+"]");
			nui.get("tbBizJdDjFlow.frzAmt").setValue("");
		}
	}
</script>
</body>
</html>