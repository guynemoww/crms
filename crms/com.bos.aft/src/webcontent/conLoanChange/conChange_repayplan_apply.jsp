<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-05-27
  - Description:
-->
<head>
<title>合同还款计划表调整申请信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbConLoanChange.contractId" class="nui-hidden nui-form-input" name ="tbConLoanChange.contractId"/>
	
	<fieldset>
		<legend>
	    	<span>合同信息</span>
	    </legend>
	    <div class="nui-dynpanel" columns="4" id="table1">
	    	
			<label class="nui-form-label">合同编号：</label>
			<input id="tbConContractInfo.contractNum" class="nui-text nui-form-input" name="tbConContractInfo.contractNum"/>
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbConContractInfo.productType" name="tbConContractInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
			
			<label class="nui-form-label">合同金额：</label>
			<input id="tbConContractInfo.contractAmt" name="tbConContractInfo.contractAmt"  class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">合同已用金额：</label>
			<input id="tbConContractInfo.conBalance" name="tbConContractInfo.conBalance"  class="nui-text nui-form-input" dataType="currency"/>
			
			<label class="nui-form-label">合同起期：</label>
			<input id="tbConContractInfo.beginDate" name="tbConContractInfo.beginDate" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">合同止期：</label>
			<input id="tbConContractInfo.endDate" name="tbConContractInfo.endDate" class="nui-text nui-form-input" />
			
			<label class="nui-form-label">结息周期：</label>
			<input id="tbConLoanrate.interestCollectType" name="tbConLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
				   
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbConLoanrate.yearRate" class="nui-text nui-form-input" name="tbConLoanrate.yearRate"/>
			
			<label class="nui-form-label">经办机构：</label>
			<input id="tbConLoanChange.orgNum" name="tbConLoanChange.orgNum" class="nui-text nui-form-input" dictTypeId="org" />
			
			<label class="nui-form-label">客户经理：</label>
			<input id="tbConLoanChange.userNum" name="tbConLoanChange.userNum" class="nui-text nui-form-input" dictTypeId="user" />
			
			<label class="nui-form-label">合同调整类型：</label>
			<input id="tbConLoanChange.conChangeType" name="tbConLoanChange.conChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
			
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input" vtype="maxLength:999"/> 
			
	    </div>
	</fieldset>

	<div class="nui-dynpanel" columns="1" id="table3">
		<%@include file="/aft/conLoanChange/include_old_repayplan.jsp"%>
	</div> 
	
	<div class="nui-dynpanel" columns="1" id="table4">
		<%@include file="/aft/conLoanChange/include_new_repayplan.jsp"%>
	</div> 
	
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="con_contract_info_save" iconCls="icon-save" onclick="save">保存</a>
	</div> 

</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var changeId ="<%=request.getParameter("changeId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	$("#table3").css("display","block");
	$("#table4").css("display","block");
	
	//初始化页面
	initPage();
	function initPage(){
		
		var json = nui.encode({"changeId":changeId});
		var con;
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
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
	
	
	//初始化还款计划查询
  	function query1(){
  		var o = form.getData();
		var json = nui.decode({"contractId":o.tbConLoanChange.contractId,"changeId":changeId});
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
            	if(row.repayplanChangeId){
            		var json = nui.encode({"repayplanChangeId":row.repayplanChangeId});
	            	$.ajax({
			            url: "com.bos.aft.conLoanChange.deleteRepayplanChange.biz.ext",
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
		
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        
        nui.get("con_contract_info_save").setEnabled(false);
		var o = form.getData();
		o.tbConLoanChange.changeId=changeId
		
		var repayPlans = nui.get("grid1").getChanges();/* 还款 */
		var repayPlans2 = nui.get("grid2").getChanges();/* 还款 */
		o.changeId = changeId;
		//o.contractId = contractId;
		o.repayPlans = repayPlans;
		o.repayPlans2 = repayPlans2;
		var json = nui.encode(o);
   		$.ajax({
        url: "com.bos.aft.conLoanChange.saveConLoanChange.biz.ext",
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
        }});
	} 
	
	
</script>
</body>
</html>