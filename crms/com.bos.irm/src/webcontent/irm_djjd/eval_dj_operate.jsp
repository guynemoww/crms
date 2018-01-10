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
			<input id="amount.attachedId" name="amount.attachedId"
				class="nui-hidden nui-form-input" />
			<div class="nui-dynpanel" columns="4">
		<label>冻结原因：</label>
		<input id="amount.attachedDesc" name="amount.attachedDesc" class="nui-textarea nui-form-input"
			 required="true"/>
	</div>	-->
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<input id="amount.amountId" name="amount.amountId" class="nui-hidden nui-form-input" />
	<input id="bizjddj.frzNum" name="bizjddj.frzNum" class="nui-hidden nui-form-input" />
	<input id="amount.approveId" name="amount.approveId" class="nui-hidden nui-form-input" />
	<div class="nui-dynpanel" columns="4">
				<label>客户编号：</label>
				<div style="width:120%">
					<input id="party.partyNum" name="party.partyNum"  style="width:68%;float:left"    class="nui-text nui-form-input" />
				</div>
				<label class="nui-form-label">客户名称：</label>
				<input id ='party.partyName' name="party.partyName" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
				
				<label class="nui-form-label">客户类型：</label>
				<input id="party.partyTypeCd" name="party.partyTypeCd" required="false" valueField="dictID" dictTypeId="XD_KHCD1001" class="nui-text nui-form-input"  vtype="maxLength:100" />	
										
				<label>冻结种类：</label>
				<div style="width:100%">
				<input id="bizjddj.frzType" name="bizjddj.frzType" class="nui-dictcombobox nui-form-input" dictTypeId="CD100003"  enabled="false"/>
				</div>
				
				<!--<label>可冻结金额：</label>
				<input id="amount.creditAvi"  name="amount.creditAvi"   class="nui-text nui-form-input"/>
				
				<label>冻结金额：</label>
				<input id="bizjddj.frzAmt"  name="bizjddj.frzAmt"   class="nui-text nui-form-input"   onblur="amountValid"/>
				  -->
				<label>冻结原因：</label>
				<input id="bizjddj.frzReason"  name="bizjddj.frzReason"  required="true"  class="nui-textarea nui-form-input" vtype="maxLength:190"/>
				
				<!-- <label>是否到期自动解冻：</label>
				<input id="bizjddj.operFlag"  name="bizjddj.operFlag"   class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="zdjdChange"/>
				
				<label id="frzEndDateLable" class="nui-form-label">冻结终止日期：</label>
				<input id="bizjddj.frzEndDate"  name="bizjddj.frzEndDate"  required="true" class="nui-datepicker nui-form-input"  allowInput="false" />
				 -->
		</div>
	<div id="div1" class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="create">保存</a>
		<!-- <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a> -->
	</div>
</center>
<script type="text/javascript">
	nui.parse(); 
	var form = new nui.Form("#form");
	var amountId = "<%=request.getParameter("amountId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";
	init();
    function init(){
	    var json = nui.encode({"amountId":amountId});  	
   	    nui.ajax({//获取冻结信息
	        url: "com.bos.irm.queryInfo.queryCustOperdjjd.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                form.setData(o);
	                //var frzDate = o.bizjddj.frzEndDate;
	                //if(frzDate && frzDate.length > 7){
	                	//var frzDate2 = frzDate.substring(0,4)+'-'+frzDate.substring(4,6)+'-'+frzDate.substring(6,8);
	                	//nui.get("bizjddj.frzEndDate").setValue(frzDate2);
	                //}
	            }
	        }
	    });
    }
    if(proFlag==0){//查看
    	form.setEnabled(false);
    	nui.get("div1").hide();
    }
	//解冻终止日期---默认隐藏 	
	//if(nui.get("bizjddj.operFlag").getValue() == '0'){//是否到期自动解冻---否
		//$("#frzEndDateLable").css("display","none");
		//nui.get("bizjddj.frzEndDate").hide();
	//}
	//保存
	function create(){
		git.mask();
		nui.get("btnCreate").setEnabled(false);
		var o = form.getData();
		if (form.isValid() == false) {
			alert("请将信息填写完整");
			git.unmask();
			nui.get("btnCreate").setEnabled(true);
			return;
		}
	    var json = {"tbBizJdDjFlow":{
	    	"frzNum":o.bizjddj.frzNum,
			"frzType":o.bizjddj.frzType,
			"frzAmt":o.bizjddj.frzAmt,
			"frzReason":o.bizjddj.frzReason}};
		$.ajax({
			url : "com.bos.bizInfo.bizInfo.saveFrzDataAndCallCoreImpl.biz.ext",
			type : 'POST',
			data : nui.encode(json),
			cache : false,
			contentType : 'text/json',
			cache : false,
			success : function(mydata) {
				nui.alert(mydata.msg);
			}
		});
		git.unmask();
		nui.get("btnCreate").setEnabled(true);
	}
	//是否到期自动解冻
	//function zdjdChange(){
		//var operFlag = nui.get("bizjddj.operFlag").getValue();
		//if(operFlag=="1"){
			//$("#frzEndDateLable").css("display","block");
			//nui.get("bizjddj.frzEndDate").show();
		//}else{
			//$("#frzEndDateLable").css("display","none");
			//nui.get("bizjddj.frzEndDate").hide();
		//}
	//}
	//冻结金额校验---类型和数值的校验
	//function amountValid(){
		//var reg = new RegExp("^[0-9]*$");
		//var frzAmt = nui.get("bizjddj.frzAmt").getValue();
		//if (!reg.test(frzAmt)) {
			//nui.alert("需冻结金额只能为数字类型!");
			//nui.get("bizjddj.frzAmt").setValue("");
		//}
		//if(parseFloat(useAmt) < parseFloat(frzAmt)){
		//	nui.alert("需冻结金额["+frzAmt+"]不能大于可冻结金额["+useAmt+"]");
			//nui.get("bizjddj.frzAmt").setValue("");
		//}
	//}
</script>
</body>
</html>