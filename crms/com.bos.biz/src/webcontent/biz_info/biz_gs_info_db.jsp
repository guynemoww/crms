<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 16:35:54
  - Description:
-->
<head>
<title>公司客户业务申请基本信息</title>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
</head>
<body>
<center>

	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="tbBizApply.applyId" class="nui-hidden nui-form-input" name ="tbBizApply.applyId"/>
		<input id="tbBizAmountApply.applyId" class="nui-hidden nui-form-input" name ="tbBizAmountApply.applyId"/>
		<input id="tbBizAmountApply.amountId" class="nui-hidden nui-form-input" name ="tbBizAmountApply.amountId"/>
		<div class="nui-dynpanel" columns="4" id="table1">
			<label class="nui-form-label">客户编号：</label>	
			<input id="party.partyNum" class="nui-text nui-form-input" name="party.partyNum"/>	
				
			<label class="nui-form-label">客户名称：</label>
			<input id="party.partyName" class="nui-text nui-form-input" name="party.partyName"/>
			
			<label id="certt">证件类型：</label>
			<input id="party.certType" class="nui-text nui-form-input" name="party.certType"  dictTypeId="CDKH0002" />
			
			<label id="certn">证件号码：</label>
			<input id="party.certNum" class="nui-text nui-form-input" name="party.certNum" />
		
			<label id="credlevel">信用等级：</label>
			<input id="party.creditLevel" class="nui-text nui-form-input" name="party.creditLevel"  required="false"/>
			
			<label class="nui-form-label">业务性质：</label>
			<input id="tbBizApply.bizTypeFlag" name="tbBizApply.bizTypeFlag" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0002"/>
			
<!-- 			<label class="nui-form-label">业务性质：</label>
			<input id="tbBizApply.bizType" name="tbBizApply.bizType" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0002"/> -->
			
			<label class="nui-form-label">业务发生性质：</label>
			<input id="tbBizApply.bizHappenNature" name="tbBizApply.bizHappenNature" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0004" />
			
			<label class="nui-form-label">业务发生方式：</label>
			<input id="tbBizApply.bizHappenType" name="tbBizApply.bizHappenType" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0001" />
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbBizApply.productType" class="nui-text nui-form-input" name="tbBizApply.productType" dictTypeId="product"/>
			
			<label class="nui-form-label">币种：</label>
			<input id="tbBizAmountApply.currencyCd" name="tbBizAmountApply.currencyCd"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="CD000001"/>
				
			<label class="nui-form-label">申请金额（元）：</label>
			<input id="tbBizAmountApply.creditAmount" name="tbBizAmountApply.creditAmount" class="nui-text nui-form-input" vtype="float;maxLength:20"  dataType="currency"/>
			
			<!-- <label>申请期限：</label>
			<div style="width:80%">
			<input name="tbBizAmountDetailApply.creditTerm" style="width:60%;float:left" id="tbBizAmountDetailApply.creditTerm"  vtype="int;maxLength:4" class="nui-textbox nui-form-input" enabled="false"/>
			<input name="tbBizAmountDetailApply.cycleUnit" id="tbBizAmountDetailApply.cycleUnit" style="width:40%;float:left"   data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="XD_GGCD6009" value="04"  enabled="false"/>
			</div> 
			<label id="repaymentType">申报止期：</label>
			<input id="tbBizAmountDetailApply.endDate" name="tbBizAmountDetailApply.endDate" class="nui-text nui-form-input" />
			-->	
			<label class="nui-form-label" id="bankTeam">是否银团：</label>
			<input id="tbBizApply.isBankTeamLoan" name="tbBizApply.isBankTeamLoan" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_0002"/>
			
			<label for="isteam$text">申报模式：</label>
			<input id="tbBizApply.applyModeType" name="tbBizApply.applyModeType" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0003" />
		</div>	
		<div class="nui-dynpanel" columns="4" id="dfx">
			<label class="nui-form-label" id="lowType">低类型：</label>
			<input id="tbBizApply.lowRiskBizType" name="tbBizApply.lowRiskBizType"  valueField="dictID" class="mini-newcheckbox" 
				Wrequired="true" repeatDirection="vertical" colspan="1" repeatLayout="flow" repeatItems="20" enabled="false"
				valueField="dictID" dictTypeId="XD_SXYW0225"/>
		</div>	
		<div class="nui-dynpanel" columns="4" id="dbfs">	
			<label class="nui-form-label" id="guarantyType">担保方式：</label>
	        <input id="tbBizAmountApply.guarantyType" name="tbBizAmountApply.guarantyType" class="mini-newcheckbox" required="true" data="data" valueField="dictID" dictTypeId="CDZC0005"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    		<a class="nui-button" id="biz_gs_info_save" iconCls="icon-save" onclick="save">保存</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var applyId ="<%=request.getParameter("applyId") %>";//业务申请ID
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	
	var bizType ="<%=request.getParameter("bizType")%>";//01-单笔  02-综合授信 03-集团综合授信
	//低类型只有申报模式为低才显示
	$("#dfx").css("display","none");
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>","ratingType":''});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.getAmountInfoByApplyId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	nui.get("tbBizAmountApply.currencyCd").setValue("CNY");
            	if(o.tbBizApply.applyModeType=='02'){
            		$("#dfx").css("display","block");
            		$("input[type='checkbox']").each(function(){
						var v1 = $(this).attr("value");
						if(v1 == "10" || v1 == "20" || v1 == "30"){
							$(this).remove();
							$("label[for='"+$(this).attr("id")+"']").attr("style","font-size: medium;");
						}
					});
            	}
            	//某些品种没有担保方式
            	if(o.tbBizApply.productType=='01006001' ||o.tbBizApply.productType=='01006002'
            	 || o.tbBizApply.productType=='01006010' //村镇银行贴现产品
            	){
            		$("#dbfs").css("display","none");
            	}
            	if(o.tbBizApply.productType=='01013001' || o.tbBizApply.productType=='01013010'){//对公委贷不需要评级必输
					nui.get("party.creditLevel").setRequired(false);
					nui.get("party.creditLevel").validate();
                }
			}
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("biz_gs_info_save").hide();
			form.setEnabled(false);
		}
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        nui.get("biz_gs_info_save").setEnabled(false);
		var o = form.getData();
		o.tbBizAmountApply.applyId=nui.get("tbBizApply.applyId").getValue();
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.bizInfo.bizInfo.saveAmoutInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("biz_gs_info_save").setEnabled(true);
        	}
        	alert("保存成功！");
        	initPage();
        	nui.get("biz_gs_info_save").setEnabled(true);
        }});
	}
</script>
</body>
</html>