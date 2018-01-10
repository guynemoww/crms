<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-08
  - Description:
-->
<head>
<title>日常检查申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbAftNormalCheck.normalCheckId" class="nui-hidden nui-form-input" name ="tbAftNormalCheck.normalCheckId"/>
	<input id="tbAftNormalCheck.checkType" class="nui-hidden nui-form-input" name ="tbAftNormalCheck.checkType"/>
	<input id="tbAftNormalCheck.partyId" class="nui-hidden nui-form-input" name ="tbAftNormalCheck.partyId"/>
	<input id="tbAftNormalPerson.normalPersonId" class="nui-hidden nui-form-input" name ="tbAftNormalPerson.normalPersonId"/>
	
	<fieldset>
		<legend>
	    	<span>一、客户基本信息</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="4" id="table1">
	    	<label class="nui-form-label">客户名称：</label>
			<input  id="items[0].PARTY_NAME" name="items[0].PARTY_NAME" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">证件类型：</label>
			<input id="items[0].CERT_TYPE" name="items[0].CERT_TYPE" 
						class="nui-text nui-form-input" dictTypeId="CDKH0002"  />
				   
			<label class="nui-form-label">证件号码：</label>
			<input  id="items[0].CERT_NUM" name="items[0].CERT_NUM" class="nui-text nui-form-input" /> 
			
			<label class="nui-form-label">批复金额（元）：</label>
			<input id="items[0].PFJE" name="items[0].PFJE" dataType="" class="nui-text nui-form-input"/>
		
			<label class="nui-form-label">贷款余额（元）：</label>
			<input id="items[0].JJYE" name="items[0].JJYE" dataType="" class="nui-text nui-form-input"/>
	    </div>
	</fieldset>
	
 		<%@include file="/aft/normalCheck/summary_list2.jsp"%>
 		<fieldset>
		<legend>
	    	<span>本期贷款使用情况</span>
	    </legend>
 		<%@include file="/aft/normalCheck/money_use_list.jsp"%>
 			</fieldset>
 	
	<fieldset>
		<legend>
	    	<span>二、贷款后还本付息情况</span>
	    </legend> 
	 
	    
	    	<div class="nui-dynpanel" columns="2" id="table2">
	    	 <label class="nui-form-label">贷款发放后至检查日，还本付息情况：</label> 
 				<input  id="tbAftNormalPerson.repayCondition" name="tbAftNormalPerson.repayCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			</div>
	     
	    </fieldset>
	  
	    <fieldset>
	    	<legend>
	    		<span>三、贷款后非财务因素分析</span>
	    	</legend>
	    	<div class="nui-dynpanel" columns="1" id="table4">
				<!-- <label class="nui-form-label">到期贷款归还情况：</label> -->
				<input  id="tbAftNormalPerson.noFinanceAnalysis" name="tbAftNormalPerson.noFinanceAnalysis" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			</div>
	    </fieldset>
	
	
	<fieldset>
		<legend>
	    	<span>四、偿还贷款能力及贷款因素分析及采取的贷款管理措施分析</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table5">
	    	<!-- <label class="nui-form-label">贷款后非财务因素分析：</label> -->
			<input  id="tbAftNormalPerson.financeAnalysis" name="tbAftNormalPerson.financeAnalysis" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
		</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>五、其他值得关注的情况说明</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table6">
	    	<!-- <label class="nui-form-label">贷款后财务因素分析：</label> -->
			<input  id="tbAftNormalPerson.otherCondition" name="tbAftNormalPerson.otherCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
		</div>
	</fieldset>

	
	<fieldset>
		<legend>
	    	<span>六、检查结论</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table10">
	    	<!-- <label class="nui-form-label">处理意见：</label> -->
			<input  id="tbAftNormalCheck.checkResult" name="tbAftNormalCheck.checkResult" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
		</div>
	    <div class="nui-dynpanel" columns="4" id="table11">
	    	<label class="nui-form-label">检查人：</label>
			<input  id="tbAftNormalCheck.checkPerson" name="tbAftNormalCheck.checkPerson" class="nui-text nui-form-input"  /> 
			
			<label class="nui-form-label">系统录入时间：</label>
			<input  id="tbAftNormalCheck.createDate" name="tbAftNormalCheck.createDate" class="nui-text nui-form-input"  /> 
		</div>	
	</fieldset> 
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
			<a class="nui-button" id="btnDownload" onclick="clickDownload()">下载打印</a>
			
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var normalCheckId ="<%=request.getParameter("normalCheckId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识 
	$("#table0").css("display","block");
	
	//初始化页面
	initPage();
	function initPage(){
		var json = nui.encode({"normalCheckId":normalCheckId});
		$.ajax({
            url: "com.bos.aft.normalCheck.findNormalCheck.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	form.setData(o);
            	query1();
			}
        });
        
        //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
			form.setEnabled(false);
		}
        
	}
	
	
  	function query1(){
  		var o = form.getData();
		var json = nui.decode({"partyId":o.tbAftNormalCheck.partyId,"normalCheckId":o.tbAftNormalCheck.normalCheckId});
		var grid1 = nui.get("grid1");
    	grid1.load(json);
     
		 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			
			form.setEnabled(false);
		} 
	} 
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
		var o = form.getData();
		
		o.tbAftNormalCheck.normalCheckId=normalCheckId
		
		o.normalCheckId = normalCheckId;
		
		nui.get("con_contract_info_save").setEnabled(false);
		var json = nui.encode(o);
		
   		$.ajax({
        url: "com.bos.aft.normalCheck.saveNormalCheck.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg); //失败时后台直接返回出错信息
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        	alert("保存成功！");
        	initPage();
        	nui.get("con_contract_info_save").setEnabled(true);
        }});
	} 
	
 
   
	
	function clickDownload(){
		var o = form.getData();
		var json = nui.encode({"map":{"checkId":normalCheckId,"partyId":o.tbAftNormalCheck.partyId,"reportName":'/aft/normalCheck_person_xf.docx'}});
		$.ajax({
            url: "com.bos.aft.normalCheck.printNormalCheck.biz.ext",
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