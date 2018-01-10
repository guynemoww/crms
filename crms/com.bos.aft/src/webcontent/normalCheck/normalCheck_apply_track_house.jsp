<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-04
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
	<input id="tbAftTrackHouse.checkHouseId" class="nui-hidden nui-form-input" name ="tbAftTrackHouse.checkHouseId"/>
	
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
	
 		<%@include file="/aft/normalCheck/require_list.jsp"%>
 	
	<fieldset>
		<legend>
	    	<span>本期项目投资及筹资变化情况</span>
	    </legend>
  	       	 
	    <div class="nui-dynpanel" columns="4" id="table2">
	    	
	    	<!-- <label class="nui-form-label">项目名称：</label> -->
			<!-- 	<input id="PROJECT_NAME" name="PROJECT_NAME"  class="nui-textbox nui-form-input" required="true" vtype="maxLength:1000" /> -->
	
 	    		    	<label class="nui-form-label">项目名称：</label>
				<input id="tbAftTrackHouse.customerInvestChange" name="tbAftTrackHouse.customerInvestChange" style="width:250px;"
			valueField="PROJECT_NAME" textField="PROJECT_NAME" data="grant_packages" class="nui-combobox nui-form-input" required="true"/>
	    	<label class="nui-form-label">本期支付工程款：</label>
			<input  id="tbAftTrackHouse.payProjectAmt" name="tbAftTrackHouse.payProjectAmt" required="true" class="nui-textbox nui-form-input" vtype="maxLength:1000" />
			
			<label class="nui-form-label">支付材料款：</label>
			<input  id="tbAftTrackHouse.payDataAmt" name="tbAftTrackHouse.payDataAmt" required="true" class="nui-textbox nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">支付其他费用：</label>
			<input  id="tbAftTrackHouse.payOtherAmt" name="tbAftTrackHouse.payOtherAmt" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">资本金增减：</label>
			<input  id="tbAftTrackHouse.amtChange" name="tbAftTrackHouse.amtChange" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">银行贷款增减：</label>
			<input  id="tbAftTrackHouse.loanChange" name="tbAftTrackHouse.loanChange" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">其中我行贷款增减：</label>
			<input  id="tbAftTrackHouse.mybankLoanChange" name="tbAftTrackHouse.mybankLoanChange" required="true" class="nui-textbox nui-form-input" vtype="maxLength:1000"  />
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>本期项目进展情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table3">
	    
	    	<label class="nui-form-label">在施工面积/套数：</label>
			<input  id="tbAftTrackHouse.workingArea" name="tbAftTrackHouse.workingArea" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">其中已竣工（或封顶）面积/套数：</label>
			<input  id="tbAftTrackHouse.finishArea" name="tbAftTrackHouse.finishArea" required="true" class="nui-textbox nui-form-input" vtype="maxLength:1000"  />
			
	    	<label class="nui-form-label">是否出现影响项目进展的重大不利因素：</label>
			<input id="tbAftTrackHouse.isProblem" name="tbAftTrackHouse.isProblem" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>本期项目销售情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table4">
	    
	    	<label class="nui-form-label">已取得预售证面积/套数：</label>
			<input  id="tbAftTrackHouse.getArea" name="tbAftTrackHouse.getArea" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">本期销售（套数/金额）：</label>
			<input  id="tbAftTrackHouse.sellArea" name="tbAftTrackHouse.sellArea" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">其中：现金销售：</label>
			<input  id="tbAftTrackHouse.cash" name="tbAftTrackHouse.cash" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">我行按揭：</label>
			<input  id="tbAftTrackHouse.mybankLoan" name="tbAftTrackHouse.mybankLoan" required="true" class="nui-textbox nui-form-input" vtype="maxLength:1000"  />
			
			<label class="nui-form-label">他行按揭：</label>
			<input  id="tbAftTrackHouse.otherbankLoan" name="tbAftTrackHouse.otherbankLoan" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">本期销售均价：</label>
			<input  id="tbAftTrackHouse.avgSellamt" name="tbAftTrackHouse.avgSellamt" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
			<label class="nui-form-label">与前期相比涨跌：</label>
			<input  id="tbAftTrackHouse.sellamtChange" name="tbAftTrackHouse.sellamtChange" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>本期在我行的资金回笼及还本付息情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table5">
	    
	    	<label class="nui-form-label">在我行的销售回款与销售收入比：</label>
			<input  id="tbAftTrackHouse.mybankSellRate" name="tbAftTrackHouse.mybankSellRate" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:1000" />
			
	    	<label class="nui-form-label">是否按期还本付息：</label>
	    				<input  id="tbAftTrackHouse.isRepay" name="tbAftTrackHouse.isRepay" required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="CDGY0001"/>
	    	 
			
	    </div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>项目市场前景分析</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table6">
	    
	    	<label class="nui-form-label">项目品质与周边楼盘相比较：</label>
			<input  id="tbAftTrackHouse.projectCompare" name="tbAftTrackHouse.projectCompare" required="true" class="nui-textarea nui-form-input"  vtype="maxLength:1000"/>
			
			<label class="nui-form-label">销售情况与周边楼盘相比较：</label>
			<input  id="tbAftTrackHouse.sellCompare" name="tbAftTrackHouse.sellCompare" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			

	    </div>
	</fieldset> 
	
	<fieldset>
		<legend>
	    	<span>本期借款人对外投资变化情况</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table7">
	    

	<!-- 		 <label class="nui-form-label">本期借款人对外投资变化情况：</label>
			<input  id="tbAftTrackHouse.customerInvestChange" name="tbAftTrackHouse.customerInvestChange" required="true" class="nui-textarea nui-form-input"  /> -->
			
			<label class="nui-form-label">新增（或收回）投资项目名称、金额：</label>
			<input  id="tbAftTrackHouse.investCondition" name="tbAftTrackHouse.investCondition" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
			
			<label class="nui-form-label">本期担保情况是否有变化：</label>
			<input id="tbAftTrackHouse.isGuarantChange" name="tbAftTrackHouse.isGuarantChange" required="true" 
					class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"  />
			
			<label class="nui-form-label">其他值得关注的情况（包括借款人对外投资、对外担保、股东变化及政策对房地产市场的影响变化等）：</label>
			<input  id="tbAftTrackHouse.otherCondition" name="tbAftTrackHouse.otherCondition" class="nui-textarea nui-form-input"  vtype="maxLength:1000"/>
			
			<label class="nui-form-label">检查人：</label>
			<input  id="tbAftNormalCheck.checkPerson" name="tbAftNormalCheck.checkPerson" class="nui-text nui-form-input" vtype="maxLength:1000" /> 
			
			<label class="nui-form-label">系统录入时间：</label>
			<input  id="tbAftNormalCheck.createDate" name="tbAftNormalCheck.createDate" class="nui-datepicker nui-form-input"  /> 
			
	    </div>
	</fieldset> 
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
			<a class="nui-button" id="btnDownload" onclick="clickDownload()">下载打印</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	var xmmcs = [{ id: '1', text: '消费性' }, { id: '2', text: '经营性'}];
	nui.parse();
	var form = new nui.Form("#form");
	var normalCheckId ="<%=request.getParameter("normalCheckId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识 
	var partyId;
	var grant_packages=[];
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
             	
            	grant_packages=mydata.items3 || [];
		    	if(grant_packages.length>0) {
		    		for (var i=grant_packages.length; i>0; i--) {
		        		grant_packages[i]=grant_packages[i-1];
		        	}

		    	}else{
		    			        	grant_packages[0]={};
 		            grant_packages[0].PROJECT_NAME="--请选择--";
		    	}
		    	/* debugger; */
		    	nui.get("tbAftTrackHouse.customerInvestChange").setData(grant_packages);
		    	// if (grant_packages.length>0) {
		    	//	nui.get("item3.PROJECT_NAME").setValue(grant_packages[1].PROJECT_NAME);
		    	//} 
            	
            // 	nui.get("item3.PROJECT_NAME").setData(o.items3);
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
	
	query1();
	function query1(){
		var o = form.getData();
		 
		
		var json = nui.decode({"partyId":o.tbAftNormalCheck.partyId,"normalCheckId":normalCheckId});
		partyId = o.tbAftNormalCheck.partyId;
		var grid1 = nui.get("grid1");
    	var grid2 = nui.get("grid2");
    	grid1.load(json);
		grid2.load(json);
		 //proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("grid2add").hide();
			nui.get("grid2del").hide();
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
            	if(row.executeId){
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
		o.normalCheckId = normalCheckId;
		o.summaryItems = summaryItems;
		o.tbAftRequireExecute = tbAftRequireExecute;
		debugger; 
	    var json1 =nui.get("grid2").getChanges();
	    var length= json1.length;
	    for(var i=0;i<length;i++){
	           if(json1[i]){
         debugger; 
          var requirement=json1[i].requirement;//本次检查需要落实的条件
          var executeResult=json1[i].executeResult;//落实情况 
        var lengthr ="";
          if(requirement){
          lengthr =requirement.replace(/[^u0000-u00ff]/g,"aaa").length;
          }
        var lengthe ="";
          if(executeResult){
          lengthe =executeResult.replace(/[^u0000-u00ff]/g,"aaa").length;
          }
        if(lengthr>1000){
         nui.alert("本次检查需要落实的条件填写信息过长"); 
/*          nui.alert(length); */
         return;
        }
           if(lengthe>1000){
         nui.alert("落实情况填写信息过长"); 
/*          nui.alert(length); */
         return;
        }
        
        }
        }
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
	    
/* 		var size1 = nui.get("grid2").getData().length;
		if (size1 == 0){
        	nui.alert("请填写贷款条件及落实情况");
        	return;
        } */ 
        
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
        	}else{
        		nui.alert("保存成功！");
        		initPage();
        		nui.get("con_contract_info_save").setEnabled(true);
        	}
        }});
	} 
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"checkId":normalCheckId,"partyId":partyId,"reportName":'/aft/normalCheck_track_house.docx'}});
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