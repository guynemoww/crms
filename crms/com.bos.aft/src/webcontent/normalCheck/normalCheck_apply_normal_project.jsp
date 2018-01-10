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
	<input id="tbAftNormalProject.normalProjectId" class="nui-hidden nui-form-input" name ="tbAftNormalProject.normalProjectId"/>
	
	<fieldset>
		<legend>
	    	<span>客户基本信息</span>
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
			<input id="items[0].PFJE" name="items[0].PFJE" dataType="currency" class="nui-text nui-form-input"/>
		
			<label class="nui-form-label">贷款余额（元）：</label>
			<input id="items[0].JJYE" name="items[0].JJYE" dataType="currency" class="nui-text nui-form-input"/>
	    </div> 
	</fieldset> 
	
	<div class="nui-dynpanel" columns="1" id="table0">
		<%@include file="/aft/normalCheck/summary_list4.jsp"%>
	</div>
	
	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    	<label class="nui-form-label">检查方式：</label>
			<input id="tbAftNormalCheck.checkWay" name="tbAftNormalCheck.checkWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  />
						
			<label class="nui-form-label">检查日期：</label>
			<!--  <input  id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" class="nui-text nui-form-input"  />-->
			  <input id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>  
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>一、贷款资金使用情况</span>
	    </legend>
	    <%@include file="/aft/normalCheck/money_use_list2.jsp"%>
	</fieldset>
	
	<fieldset>
		<legend title="填写说明：如为房地产贷款，应具体到每一栋做到第几层等详细进度情况；是否出现影响项目正常进度的情况，如资金不足、手续不全、违规建设等">
	    	<span>二、项目最新进展情况</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table4">
	    	<!-- <label class="nui-form-label">项目最新进展情况：</label> -->
			<input  id="tbAftNormalProject.newCondition" name="tbAftNormalProject.newCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
		</div>
	</fieldset>
	
	<fieldset>
		<legend title="填写说明：项目总投资、已支付工程款及明细，项目筹资安排是否有变化，项目开发者对缺口资金的筹措是否已落实并到位，如银行贷款增减、自有资金增减情况等">
	    	<span>三、项目投入及筹资变化情况</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table5">
	    	<!-- <label class="nui-form-label">项目投入及筹资变化情况：</label> -->
			<input  id="tbAftNormalProject.injectCondition" name="tbAftNormalProject.injectCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
		</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>四、项目收益情况</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table6">
	    	<!-- <label class="nui-form-label">项目收益情况：</label> -->
			<input  id="tbAftNormalProject.incomeCondition" name="tbAftNormalProject.incomeCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
		</div>
	</fieldset>
	
	<fieldset>
		<legend title="填写说明：项目销售价格、销售数量与周边可比物相比情况；贷款因素分析 ">
	    	<span>五、项目前景及贷款因素分析</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table7">
	    	<!-- <label class="nui-form-label">项目前景及贷款因素分析：</label> -->
			<input  id="tbAftNormalProject.riskAnalysis" name="tbAftNormalProject.riskAnalysis" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
		</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>六、第二还款来源变动情况分析</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table8">
	    	<!-- <label class="nui-form-label">第二还款来源变动情况分析：</label> -->
			<input  id="tbAftNormalProject.repayChange" name="tbAftNormalProject.repayChange" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
		</div>
	</fieldset>
	
	<fieldset>
		<legend title="填写说明：如债务纠纷、对外担保、行业影响、政企关系、银企关系、行政处罚等">
	    	<span>七、其他值得关注的情况说明</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table9">
	    	<!-- <label class="nui-form-label">其他值得关注的情况说明：</label> -->
			<input  id="tbAftNormalProject.otherCondition" name="tbAftNormalProject.otherCondition" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
		</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>八、检查结论</span>
	    </legend> 
	    <div class="nui-dynpanel" columns="1" id="table10">
	    	<!-- <label class="nui-form-label">检查结论：</label> -->
			<input  id="tbAftNormalCheck.checkResult" name="tbAftNormalCheck.checkResult" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
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
	var partyId;
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
            
            	if(mydata.tbAftNormalCheck.isFinish != null && mydata.tbAftNormalCheck.isFinish != "") {
            		if(mydata.tbAftNormalCheck.isFinish == "1") {
	            		nui.get("btnDownload").setEnabled(true);
            		}else {
            			nui.get("btnDownload").setEnabled(false);
            		}
            	}else {
            		nui.get("btnDownload").setEnabled(false);
            	}
            
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
		partyId = o.tbAftNormalCheck.partyId;
		//alert(partyId);
		var grid1 = nui.get("grid1");
    	grid1.load(json);
    	var grid5 = nui.get("grid5");
		grid5.load(json);
	} 
	
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        nui.get("con_contract_info_save").setEnabled(false);
		var o = form.getData();
		
		o.tbAftNormalCheck.normalCheckId=normalCheckId
		var summaryItems = nui.get("grid1").getChanges();
		o.normalCheckId = normalCheckId;
		o.summaryItems = summaryItems;
		
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
		//alert(partyId);
		var json = nui.encode({"map":{"checkId":normalCheckId,"partyId":partyId,"reportName":'/aft/normalCheck_normal_project.docx'}});
		$.ajax({
            url: "com.bos.aft.normalCheck.printNormalCheck.biz.ext",
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