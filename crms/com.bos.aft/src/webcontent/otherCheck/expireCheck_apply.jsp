<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-07-08
  - Description:
-->
<head>
<title>授信到期前跟踪检查申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbAftExpireCheck.checkId" class="nui-hidden nui-form-input" name ="tbAftExpireCheck.checkId"/>
	
	<fieldset>
		<legend>
	    	<span>基本信息</span>
	    </legend>
	    <!-- <div class="nui-dynpanel" columns="4" id="table1"> -->
	    
	     <div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	    	<label class="nui-form-label">客户编号：</label>
			<input id="tbCsmParty.partyNum" class="nui-text nui-form-input" name="tbCsmParty.partyNum"/>
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
		</div>
	    
	    <div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
	    	<label class="nui-form-label">业务品种：</label>
			<input id="tbLoanInfo.productType" name="tbLoanInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
				   
			<label class="nui-form-label">借据编号：</label>
			<input id="tbLoanSummary.summaryNum" class="nui-text nui-form-input" name="tbLoanSummary.summaryNum"/>
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt" dataType="currency" class="nui-text nui-form-input"/>
		
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye" dataType="currency" class="nui-text nui-form-input"/>
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">
			<label class="nui-form-label">借据起期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />
			
			<label class="nui-form-label">借据止期：</label>
			<input id="tbLoanSummary.endDate" name="tbLoanSummary.endDate" class="nui-text nui-form-input"  />
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">	
			<label class="nui-form-label">是否存在影响按时归还我行贷款资金的因素：</label>
			<input id="tbAftExpireCheck.isRisk" name="tbAftExpireCheck.isRisk" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" onvaluechanged="onselectType" />
		</div>
		
		<div id="div1" class="nui-dynpanel"  columns="4" style="width:99.5%;">		
	    	<label class="nui-form-label">情况说明：</label>
			<input  id="tbAftExpireCheck.riskCondition" name="tbAftExpireCheck.riskCondition" class="nui-textarea nui-form-input"  required="true" /> 
		</div>
		
		<div class="nui-dynpanel"  columns="4" style="width:99.5%;">		
			<label class="nui-form-label">管户客户经理：</label>
			<input id="tbAftExpireCheck.userNum" name="tbAftExpireCheck.userNum" class="nui-text nui-form-input" dictTypeId="user" />
			
			<label class="nui-form-label">经营行负责人：</label>
			<input id="items1[0].OPERATORNAME" name="items1[0].OPERATORNAME" class="nui-text nui-form-input" dictTypeId="user" />
		</div>
	    <!-- </div> -->
	</fieldset>
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
 			
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save(1)">保存</a>
			<a class="nui-button" id="con_contract_temp_save" iconCls="icon-save" onclick="save(2)">临时保存</a>
			<a class="nui-button" id="btnDownload" onclick="clickDownload()">下载打印</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var checkId ="<%=request.getParameter("checkId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识  
	var OrgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>;
 	$("#div1").css("display","none");
 	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"checkId":checkId,"checkType":"e","OrgId":OrgId});
		$.ajax({
            url: "com.bos.aft.otherCheck.findCheckInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            
            	if(mydata.tbAftExpireCheck.isRisk != null && mydata.tbAftExpireCheck.isRisk != "") {
            		nui.get("btnDownload").setEnabled(true);
            	}else {
            		nui.get("btnDownload").setEnabled(false);
            	}
            
            	var o = nui.decode(mydata);
             	form.setData(o);
            }
        });
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			nui.get("con_contract_temp_save").hide();
			form.setEnabled(false);
		} 
        
	}
	function save(v){
		
        var o = form.getData();
		o.checkType = "e";
		if(v==1){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        }
        var isRisk = o.tbAftExpireCheck.isRisk;        
        if(isRisk=="1"){//是
        	var riskCondition = o.tbAftExpireCheck.riskCondition;
        	if (riskCondition == null || riskCondition==""){
        		return alert("请填写情况说明");
        	}
        } 
        
        nui.get("con_contract_info_save").setEnabled(false);
		
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.otherCheck.saveCheckInfo.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	nui.get("con_contract_info_save").setEnabled(true);
        	initPage();
        }});
	} 
	
	function onselectType(e){
		var isRisk= nui.get("tbAftExpireCheck.isRisk").getValue();//是否存在影响按时归还我行授信资金的因素
		if(isRisk=="0"){//否
			$("#div1").css("display","none");

			nui.get("tbAftExpireCheck.riskCondition").setValue(null);
		}else if(isRisk=="1"){//是
			$("#div1").css("display","block");
			
		}else{//非反显
			$("#div1").css("display","none");
		}
	}
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"checkId":checkId,"reportName":'/aft/expireCheck.docx'}});
		$.ajax({
            url: "com.bos.aft.otherCheck.printExpireCheck.biz.ext",
            //url: "com.bos.biz.print.printApproveXw.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.swfPath){
            		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
						title: "检查信息预览", 
						width: 1024,
		            	height: 768,
		            	state:"max",
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                git.unmask();
            }
       	});	
	}
</script>

</body>
</html>