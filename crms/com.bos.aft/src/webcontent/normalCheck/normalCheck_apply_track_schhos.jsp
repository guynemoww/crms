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
	<input id="tbAftTrackSchhos.checkSchhosId" class="nui-hidden nui-form-input" name ="tbAftTrackSchhos.checkSchhosId"/>
	
 		<%@include file="/aft/normalCheck/summary_list4.jsp"%>
 
	
	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    
	    	<label class="nui-form-label">检查方式：</label>
			<input id="tbAftNormalCheck.checkWay" name="tbAftNormalCheck.checkWay" required="true" 
						class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0009"  />
						
			<label class="nui-form-label">检查日期：</label>
			<input  id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" class="nui-text nui-form-input"  />
			<!-- <input id="tbAftNormalCheck.checkDate" name="tbAftNormalCheck.checkDate" allowInput="false"  required="true" 
					class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/> -->
	    </div>
	</fieldset>
	
	<div class="nui-dynpanel" columns="1" id="table00">
		<%@include file="/aft/normalCheck/require_list.jsp"%>
	</div> 
	
	<fieldset>
		<legend>
	    	<span>本期项目投资及筹资变化情况（项目贷款）</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
	    
	    	<label class="nui-form-label">本期支付工程款：</label>
			<input  id="tbAftTrackSchhos.payProjectAmt" name="tbAftTrackSchhos.payProjectAmt" required="true" class="nui-textbox nui-form-input" vtype="float;minLength:1;maxLength:14" />
			
			<label class="nui-form-label">支付材料款：</label>
			<input  id="tbAftTrackSchhos.payDataAmt" name="tbAftTrackSchhos.payDataAmt" required="true" class="nui-textbox nui-form-input" vtype="float;minLength:1;maxLength:14" />
			
			<label class="nui-form-label">支付其他费用：</label>
			<input  id="tbAftTrackSchhos.payOtherAmt" name="tbAftTrackSchhos.payOtherAmt" required="true" class="nui-textbox nui-form-input" vtype="float;minLength:1;maxLength:14" />
			
			<label class="nui-form-label">自筹资金增加额：</label>
			<input  id="tbAftTrackSchhos.amtChange" name="tbAftTrackSchhos.amtChange" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">银行贷款增减(-)额：</label>
			<input  id="tbAftTrackSchhos.loanChange" name="tbAftTrackSchhos.loanChange" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">其中我行贷款增减(-)额：</label>
			<input  id="tbAftTrackSchhos.mybankLoanChange" name="tbAftTrackSchhos.mybankLoanChange" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>本期项目进展情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
	    
	    	<label class="nui-form-label">在建项目名称及施工进度：</label>
			<input  id="tbAftTrackSchhos.projectName" name="tbAftTrackSchhos.projectName" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			
			<label class="nui-form-label">政府配套资金或政策支持落实情况：</label>
			<input  id="tbAftTrackSchhos.supportCondition" name="tbAftTrackSchhos.supportCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			
	    	<label class="nui-form-label">是否出现影响项目进展的重大不利因素：</label>
			<input id="tbAftTrackSchhos.isProblem" name="tbAftTrackSchhos.isProblem" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	    </div>
	</fieldset>
	
	<div class="nui-dynpanel" columns="1" id="table000">
		<%@include file="/aft/normalCheck/equipment_list.jsp"%>
	</div> 
	
	<fieldset>
		<legend>
	    	<span>本期经营变化情况（项目竣工或经营性贷款）</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table4">
	    
	    	<label class="nui-form-label">（学校）教师数量变化情况：</label>
			<input  id="tbAftTrackSchhos.teacherChange" name="tbAftTrackSchhos.teacherChange"  class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">在校学生数量增减：</label>
			<input  id="tbAftTrackSchhos.studentChange" name="tbAftTrackSchhos.studentChange" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">收费标准增减：</label>
			<input  id="tbAftTrackSchhos.chargeChange" name="tbAftTrackSchhos.chargeChange"  class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">（医院）门诊、住院人数数量变化情况：</label>
			<input  id="tbAftTrackSchhos.patientChange" name="tbAftTrackSchhos.patientChange"  class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">本期收入比上期增减：</label>
			<input  id="tbAftTrackSchhos.incomeCompare" name="tbAftTrackSchhos.incomeCompare"  class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">收费标准增减：</label>
			<input  id="tbAftTrackSchhos.chargeChangeHos" name="tbAftTrackSchhos.chargeChangeHos"  class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>与银行往来情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table5">
	    
	    	<label class="nui-form-label">在我行的结算占比变化：</label>
			<input  id="tbAftTrackSchhos.mybankRateChange" name="tbAftTrackSchhos.mybankRateChange" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  />
			
	    	<label class="nui-form-label">我行贷款是否按期还本付息：</label>
			<input id="tbAftTrackSchhos.isMybankRepayOt" name="tbAftTrackSchhos.isMybankRepayOt" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
			
			<label class="nui-form-label">还款来源是否为主营收入：</label>
			<input id="tbAftTrackSchhos.isRepayIncome" name="tbAftTrackSchhos.isRepayIncome" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
			
			<label class="nui-form-label">在他行贷款是否逾期欠息：</label>
			<input id="tbAftTrackSchhos.isOtherbankDebt" name="tbAftTrackSchhos.isOtherbankDebt" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>本期借款人对外投资变化情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table6">
	    
	    	<label class="nui-form-label">新增（或收回）投资项目名称、金额：</label>
			<input  id="tbAftTrackSchhos.investName" name="tbAftTrackSchhos.investName" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			
			<label class="nui-form-label">本期担保情况是否有变化：</label>
			<input id="tbAftTrackSchhos.isGuarantChange" name="tbAftTrackSchhos.isGuarantChange" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
			
			<label class="nui-form-label">其他值得关注的情况（包括借款人对外投资、对外担保、股东变化及政策等）：</label>
			<input  id="tbAftTrackSchhos.otherCondition" name="tbAftTrackSchhos.otherCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			
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
	$("#table00").css("display","block");
	$("#table000").css("display","block");
	
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
		var json = nui.decode({"partyId":o.tbAftNormalCheck.partyId,"normalCheckId":normalCheckId});
		partyId = o.tbAftNormalCheck.partyId;
		var grid1 = nui.get("grid1");
    	var grid2 = nui.get("grid2");
    	var grid3 = nui.get("grid3");
    	grid1.load(json);
		grid2.load(json);
		grid3.load(json);
		 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("grid2add").hide();
			nui.get("grid2del").hide();
			nui.get("grid3add").hide();
			nui.get("grid3del").hide();
			form.setEnabled(false);
		} 
	} 
	
	//动态列表点击新增
	function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
    	var row={"newPeriodsNum":++count};
        nui.get(gr).addRow(row,0);
    }
    //动态列表删除操作
    function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	//删除数据库数据
            	if(row.executeId){//贷款条件及落实情况表
            		var json = nui.encode({"executeId":row.executeId});
	            	$.ajax({
			            url: "com.bos.aft.normalCheck.deleteRequireInfo.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else if(row.equipmentId){//设备购置情况表
            		var json = nui.encode({"executeId":row.executeId});
	            	$.ajax({
			            url: "com.bos.aft.normalCheck.deleteEquipmentInfo.biz.ext",
			            type: 'POST',
			            data: json,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	nui.get(gr).removeRow(row,true);/* 删除页面行 */
						}
	        		});
            	}else{
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
            	}
            });
        } else {
            nui.alert("请选中一条记录");
        }
    }
	
	function save(){
	
		var o = form.getData();
		
		o.tbAftNormalCheck.normalCheckId=normalCheckId
		var summaryItems = nui.get("grid1").getChanges();
		var tbAftRequireExecute = nui.get("grid2").getChanges();
		var tbAftBuyEquipment = nui.get("grid3").getChanges();
		o.normalCheckId = normalCheckId;
		o.summaryItems = summaryItems;
		o.tbAftRequireExecute = tbAftRequireExecute; 
		o.tbAftBuyEquipment = tbAftBuyEquipment; 
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        if(tbAftRequireExecute!=null && tbAftRequireExecute!=""){
	        for(var i=0;i<tbAftRequireExecute.length;i++){
        		if(tbAftRequireExecute[i].requirement == null || tbAftRequireExecute[i].requirement==""){
        			nui.alert("请填写本次检查需要落实的条件");
        			return;
	        	}
	        	if(tbAftRequireExecute[i].executeResult == null || tbAftRequireExecute[i].executeResult==""){
        			nui.alert("请填写落实情况");
        			return;
	        	}
        		tbAftRequireExecute[i].normalCheckId=normalCheckId;
	        }
	    }
	    
		var size1 = nui.get("grid2").getData().length;
		if (size1 == 0){
        	nui.alert("请填写贷款条件及落实情况");
        	return;
        } 
        
        if(tbAftBuyEquipment!=null && tbAftBuyEquipment!=""){
	        for(var i=0;i<tbAftBuyEquipment.length;i++){
        		if(tbAftBuyEquipment[i].equipmentName == null || tbAftBuyEquipment[i].equipmentName==""){
        			nui.alert("请填写设备名称");
        			return;
	        	}
	        	if(tbAftBuyEquipment[i].equipmentValue == null || tbAftBuyEquipment[i].equipmentValue==0){
        			nui.alert("请填写价值");
        			return;
	        	}
        		tbAftBuyEquipment[i].normalCheckId=normalCheckId;
	        }
	    }
	    
		var size2 = nui.get("grid3").getData().length;
		if (size2 == 0){
        	nui.alert("请填写本期设备购置情况（设备贷款）");
        	return;
        } 
        
        /* if (tbAftRequireExecute==null || tbAftRequireExecute == ""){
        	nui.alert("请填写贷款条件及落实情况");
        	return;
        } */
        
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
		
		var json = nui.encode({"map":{"checkId":normalCheckId,"partyId":partyId,"reportName":'/aft/normalCheck_track_schhos.docx'}});
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