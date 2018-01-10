<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-22 16:35:54
  - Description:
-->
<head>
<title>个人客户业务申请基本信息</title>
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
			
			<label class="nui-form-label">证件类型：</label>
			<input id="party.certType" class="nui-text nui-form-input" name="party.certType" dictTypeId="CDKH0002"/>
			
			<label class="nui-form-label">证件号码：</label>
			<input id="party.certNum" class="nui-text nui-form-input" name="party.certNum"/>
			
			<label class="nui-form-label">信用等级：</label>
			<input id="party.creditLevel" class="nui-text nui-form-input" name="party.creditLevel"/>
			
			<label class="nui-form-label">业务编号：</label>
			<input id="tbBizApply.bizNum" class="nui-text nui-form-input" name="tbBizApply.bizNum"/>
			
			<label class="nui-form-label">业务性质：</label>
			<input id="tbBizApply.bizType" name="tbBizApply.bizType" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0002"/>
			
			<label class="nui-form-label">业务发生方式：</label>
			<input id="tbBizApply.bizHappenType" name="tbBizApply.bizHappenType" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0001" />
			
			<label class="nui-form-label">业务发生性质：</label>
			<input id="tbBizApply.bizHappenNature" name="tbBizApply.bizHappenNature" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0004" />
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbBizApply.productType" class="nui-text nui-form-input" name="tbBizApply.productType" dictTypeId="product"/>
			
			<label class="nui-form-label">币种：</label>
			<input id="tbBizAmountApply.currencyCd" name="tbBizAmountApply.currencyCd"  data="data" valueField="dictID" class="nui-text nui-form-input" dictTypeId="CD000001"/>
		
			<label class="nui-form-label">申请金额（元）：</label>
			<input id="tbBizAmountApply.creditAmount" name="tbBizAmountApply.creditAmount" class="nui-text nui-form-input" vtype="float;maxLength:20"  dataType="currency"/>
			
			<label for="isteam$text">申报模式：</label>
			<input id="tbBizApply.applyModeType" name="tbBizApply.applyModeType" data="data" valueField="dictID" 
			class="nui-text nui-form-input" dictTypeId="XD_SXYW0003" />
			
			<label for="nui-form-label">贷款形式：</label>
			<input id="tbBizApply.loanType" name="tbBizApply.loanType" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_DKXS0001" enabled="false"/>
	 		
	 		<label for="nui-form-label" id="busiPartner">是否有按揭合作商：</label>
			<input id="tbBizApply.busiPartner" name="tbBizApply.busiPartner" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" enabled="false"/>
	 	
	 		<label for="nui-form-label">是否绿色贷款：</label>
			<input id="tbBizApply.isGreenLoan" name="tbBizApply.isGreenLoan" data="data" valueField="dictID" 
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  enabled="false"/>
	 		
			<label class="nui-form-label" id="lowType">低类型：</label>
			<input id="tbBizApply.lowRiskBizType" name="tbBizApply.lowRiskBizType"  valueField="dictID" class="mini-newcheckbox" 
				Wrequired="true" repeatDirection="vertical" colspan="1" repeatLayout="flow" repeatItems="20" enabled="false"
				valueField="dictID" dictTypeId="XD_SXYW0225"/>
			<!-- 
			<label class="nui-form-label">申请日期：</label>
			<input id="tbBizApply.applyDate" name="tbBizApply.applyDate" class="nui-text nui-form-input"   required="true" format="yyyy-MM-dd" />
			 -->
			<label class="nui-form-label" id="guarantyType">担保方式：</label>
	        <input id="tbBizAmountApply.guarantyType" name="tbBizAmountApply.guarantyType" class="mini-newcheckbox" required="true" data="data" valueField="dictID" dictTypeId="CDZC0005"/>
	       <!--  <label class="nui-form-label">描述信息：</label>
			<input id="tbBizAmountApply.applyDesc" name="tbBizAmountApply.applyDesc"  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />
	 -->
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
	$("#lowType").css("display","none");
	nui.get("tbBizApply.lowRiskBizType").hide();
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>"});
		$.ajax({
            url: "com.bos.bizInfo.bizInfo.getAmountInfoByApplyId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async:false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	nui.get("tbBizAmountApply.currencyCd").setValue("CNY");
            	nui.get("party.partyName").setValue('<a href="#" onclick="toGoCustDetail(\''+ o.party.partyId+ '\');">'+o.party.partyName+'</a>');
            	if(o.tbBizApply.applyModeType=='02'){
            		$("#lowType").css("display","block");
					nui.get("tbBizApply.lowRiskBizType").show();
					nui.get('table1').refreshTable();
            		$("input[type='checkbox']").each(function(){
						var v1 = $(this).attr("value");
						if(v1 == "10" || v1 == "20" || v1 == "30"){
							$(this).remove();
							$("label[for='"+$(this).attr("id")+"']").attr("style","font-size: medium;");
						}
					});
            	}
            	//对私 二手房按揭、汽车消费贷款、公积金贷款显示是否有按揭合作商字段
            	var productType = nui.get("tbBizApply.productType").getValue();
            	if(productType == '02002003'||productType == '02003012'||productType == '02005001'
            	   ||productType == '02002010'||productType=='02002011'){
					$("#busiPartner").css("display","block");
					nui.get("tbBizApply.busiPartner").show();                        
				}else{
					$("#busiPartner").css("display","none");
					nui.get("tbBizApply.busiPartner").hide();
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
        var ff ;
        var json1 = nui.encode({applyId:nui.get("tbBizApply.applyId").getValue(),guatype:nui.get("tbBizAmountApply.guarantyType").value});
   		$.ajax({
        url: "com.bos.bizInfo.bizInfo.checkApplymsg.biz.ext",
        type: 'POST',
        data: json1,
        cache: false,
        async:false,
        contentType:'text/json',
        success: function (text) {
		        if(text.flag == "1"){
		        		nui.alert("个人住房按揭贷款,个人商用房按揭贷款担保方式必须存在抵押，不存在信用,请检查"); 
		        		ff="1";
		        }else if(text.flag == "2"){
		        		nui.alert("公积金住房委托贷款担保方式只有保证+抵押或者抵押方式,请检查");
		        		ff="1";
		        }
	        }
        });
        if(ff != "1"){
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
	}
	//客户视图
	function clickCust(e){
		var ps = e.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var infourl = nui.context + "/csm/workdesk/csm_corp_tab_private.jsp?corpid="
            + partyId+"&partyNum="+partyNum;
        nui.open({
	            url:infourl,
	            showMaxButton: true,
	            title: "",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                initPage();
	            }
      	  });	
	}
	
</script>
</body>
</html>