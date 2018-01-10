<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-07-08
  - Description:
-->
<head>
<title>重点检查申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbAftPointCheck.checkId" class="nui-hidden nui-form-input" name ="tbAftPointCheck.checkId"/>
	
	<fieldset>
		<legend>
	    	<span>基本信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    
	    	<label class="nui-form-label">机构名称：</label>
			<input id="tbAftPointCheck.orgNum" name="tbAftPointCheck.orgNum" class="nui-text nui-form-input" dictTypeId="org" />
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
			
			<label class="nui-form-label">合同编号：</label>
			<input id="tbConContractInfo.contractNum" class="nui-text nui-form-input" name="tbConContractInfo.contractNum"/>
			
			<label class="nui-form-label">合同金额：</label>
			<input id="tbConContractInfo.contractAmt" name="tbConContractInfo.contractAmt" dataType="currency" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">合同已用金额：</label>
			<input id="tbConContractInfo.conYuE" name="tbConContractInfo.conYuE" dataType="currency" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">合同起期：</label>
			<input id="tbConContractInfo.beginDate" name="tbConContractInfo.beginDate" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd"/>
			
			<label class="nui-form-label">合同止期：</label>
			<input id="tbConContractInfo.endDate" name="tbConContractInfo.endDate" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd" />
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbConContractInfo.productType" name="tbConContractInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
			
			<!-- <label class="nui-form-label">逾期本金：</label>
			<input id="tbConContractInfo.conYuE2" name="tbConContractInfo.conYuE2" dataType="currency" class="nui-text nui-form-input"/> -->
			
			<label class="nui-form-label">逾期本金：</label>
			<input id="items[0].YQBJ" name="items[0].YQBJ" dataType="currency" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">欠息合计：</label>
			<input id="items[0].QXHJ" name="items[0].QXHJ" dataType="currency" class="nui-text nui-form-input"/>
			
			<label class="nui-form-label">分类：</label>
			<input id="items[0].FXFL" name="items[0].FXFL" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_FLCD0001"/>
			
	    </div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>检查信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table2">
		    <label class="nui-form-label">问题描述：</label>
				<input  id="tbAftPointCheck.problemDes" name="tbAftPointCheck.problemDes" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000"  /> 
		</div> 
	    
	    <fieldset>
	    	<legend>
	    		<span>处置进度</span>
	    	</legend>
	    	<div class="nui-dynpanel" columns="4" id="table3">
		    	<label class="nui-form-label">催收情况：</label>
				<input  id="tbAftPointCheck.collectCondition" name="tbAftPointCheck.collectCondition" class="nui-textarea nui-form-input" vtype="maxLength:1000"  /> 
				
				<label class="nui-form-label">诉讼情况：</label>
				<input  id="tbAftPointCheck.legalCondition" name="tbAftPointCheck.legalCondition" class="nui-textarea nui-form-input"  vtype="maxLength:1000" /> 
				
				<label class="nui-form-label">其他情况：</label>
				<input  id="tbAftPointCheck.otherCondition" name="tbAftPointCheck.otherCondition" class="nui-textarea nui-form-input"  vtype="maxLength:1000" /> 
			</div> 
	    </fieldset> 
	    
	    
	    <div class="nui-dynpanel" columns="4" id="table4">
	    
	    	
			<label class="nui-form-label">经营行负责人：</label>
			<input  id="tbAftPointCheck.bankLeader" name="tbAftPointCheck.bankLeader" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32"  /> 
			
			<label class="nui-form-label">化解工作负责人：</label>
			<input  id="tbAftPointCheck.workLeader" name="tbAftPointCheck.workLeader" required="true" class="nui-textbox nui-form-input" vtype="maxLength:32"  /> 
			
			<label class="nui-form-label">总行负责人：</label>
			<input  id="tbAftPointCheck.headLeader" name="tbAftPointCheck.headLeader" class="nui-textbox nui-form-input" vtype="maxLength:32"  /> 
			
			<label class="nui-form-label">是否新增：</label>
			<input id="tbAftPointCheck.isNewadd" name="tbAftPointCheck.isNewadd" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001" />
			
	    </div> 
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
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"checkId":checkId,"checkType":"p"});
		$.ajax({
            url: "com.bos.aft.otherCheck.findCheckInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            
            	if(mydata.tbAftPointCheck.problemDes != null && mydata.tbAftPointCheck.problemDes != "") {
            		nui.get("btnDownload").setEnabled(true);
            	}else {
            		nui.get("btnDownload").setEnabled(false);
            	}
            
            	var o = nui.decode(mydata);
            	form.setData(o);

            	//nui.get("tbConContractInfo.conYuE2").setValue(o.tbConContractInfo.conYuE);
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
		o.checkType = "p";
	if(v==1){
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
		
		if(o.tbAftPointCheck.collectCondition=="" && o.tbAftPointCheck.legalCondition=="" && o.tbAftPointCheck.otherCondition=="") {
			return nui.alert("催收情况、诉讼情况、其他情况请至少填写一项！");
		}
		
		nui.get("con_contract_info_save").setEnabled(false);
	}
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
	
	function clickDownload(){
		
		var json = nui.encode({"map":{"checkId":checkId,"reportName":'/aft/pointCheck.docx'}});
		$.ajax({
            url: "com.bos.aft.otherCheck.printPointCheck.biz.ext",
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