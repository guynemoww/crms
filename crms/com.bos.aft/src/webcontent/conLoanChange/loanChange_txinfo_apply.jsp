<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-08-20
  - Description:
-->
<head>
<title>贴息主体变更信息</title>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<!-- 使用saveEntity方法时需要隐藏主键 -->
	<input id="tbConLoanChange.changeId" class="nui-hidden nui-form-input" name ="tbConLoanChange.changeId"/>
	<input id="tbConLoanTx.txId" class="nui-hidden nui-form-input" name ="tbConLoanTx.txId"/>
	<input id="tbCsmParty.partyTypeCd" class="nui-hidden nui-form-input" name ="tbCsmParty.partyTypeCd"/>
	
	<fieldset>
		<legend>
	    	<span>借据信息</span>
	    </legend>
	    
	    <div class="nui-dynpanel" columns="4">
		    <label class="nui-form-label">合同编号：</label>
			<input id="tbConContractInfo.contractNum" class="nui-text nui-form-input" name="tbConContractInfo.contractNum"/>
			
			<label class="nui-form-label">借据编号：</label>
			<input id="tbLoanSummary.summaryNum" class="nui-text nui-form-input" name="tbLoanSummary.summaryNum"/>
			
		</div>
	    
	    <div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">客户名称：</label>
			<input id="tbCsmParty.partyName" class="nui-text nui-form-input" name="tbCsmParty.partyName"/>
		</div>
		
		<div id="type01" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="orgRegisterCd01" name="orgRegisterCd01" class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmCorporation.orgRegisterCd" class="nui-text nui-form-input" name="tbCsmCorporation.orgRegisterCd"/>
		</div>
		
		<div id="type02" class="nui-dynpanel" columns="4">
			<label class="nui-form-label">证件类型：</label>
			<input id="tbCsmNaturalPerson.certType" name="tbCsmNaturalPerson.certType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="CDKH0002"/>
				   
			<label class="nui-form-label">证件号码：</label>
			<input id="tbCsmNaturalPerson.certNum" class="nui-text nui-form-input" name="tbCsmNaturalPerson.certNum"/>
		</div>
		
		
		
		<!-- <label class="nui-form-label">经办机构：</label>
		<input id="tbConLoanChange.orgNum" name="tbConLoanChange.orgNum" class="nui-text nui-form-input" dictTypeId="org" />
		
		<label class="nui-form-label">客户经理：</label>
		<input id="tbConLoanChange.userNum" name="tbConLoanChange.userNum" class="nui-text nui-form-input" dictTypeId="user" />
		 -->
		 
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">业务品种：</label>
			<input id="tbConContractInfo.productType" name="tbConContractInfo.productType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="product"/>
			<label class="nui-form-label">借据金额：</label>
			<input id="tbLoanSummary.summaryAmt" name="tbLoanSummary.summaryAmt"  class="nui-text nui-form-input" dataType="currency"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据余额：</label>
			<input id="tbLoanSummary.jjye" name="tbLoanSummary.jjye"  class="nui-text nui-form-input" dataType="currency"/>
			<label class="nui-form-label">借据起期：</label>
			<input id="tbLoanSummary.beginDate" name="tbLoanSummary.beginDate" class="nui-text nui-form-input"  />
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">借据止期：</label>
			<input id="tbLoanSummary.endDate" name="tbLoanSummary.endDate" class="nui-text nui-form-input"  />
			<label class="nui-form-label">结息周期：</label>
			<input id="tbLoanLoanrate.interestCollectType" name="tbLoanLoanrate.interestCollectType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_SXCD1018"/>
			
		</div>
		
		<div class="nui-dynpanel" columns="4">
			
			<label class="nui-form-label">执行利率（%）：</label>
			<input id="tbLoanLoanrate.yearRate" class="nui-text nui-form-input" name="tbLoanLoanrate.yearRate"/>
			
				<label class="nui-form-label">贴息止期：</label>
			<input id="tbLoanInfo.txzq" class="nui-text nui-form-input" name="tbLoanInfo.txzq"/>
		</div>
		
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">借据调整类型：</label>
			<input id="tbConLoanChange.loanChangeType" name="tbConLoanChange.loanChangeType" data="data" valueField="dictID" 
				   class="nui-text nui-form-input" dictTypeId="XD_DHBG0001"/>
			<label class="nui-form-label">调整原因：</label>
			<input  id="tbConLoanChange.changeReason" name="tbConLoanChange.changeReason" required="true" class="nui-textarea nui-form-input"  vtype="maxLength:999"/> 
		</div>
	</fieldset>

	<fieldset>
		<legend>
	    	<span>变更前</span>
	    </legend>
 
	    <div id="gridtx" class="nui-datagrid" style="width: 99%; height: 150px"
		url="com.bos.aft.conLoanChange.getTxList.biz.ext"
		dataField="txs" allowResize="false" showReloadButton="false"
		allowCellEdit="false" sizeList="[10,15,20,50,100]"
		multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
  			<div field="txfs" headerAlign="center" allowSort="true"
				dictTypeId="XD_TXFS0001" width="10px">贴息方式</div>
			<div field="txbl" headerAlign="center" allowSort="true" width="10px">贴息比例（%）</div>
			<div field="gdje" headerAlign="center" allowSort="true" width="10px">固定金额（元）</div>
			<div field="xe" headerAlign="center" allowSort="true" width="10px">限额</div>
			<div field="qx" headerAlign="center" allowSort="true" width="10px">期限（月）</div>
			<div field="txzt1" headerAlign="center" allowSort="true" width="10px">贴息主体</div>
			<div field="txzh1" headerAlign="center" allowSort="true" width="10px">贴息账号</div>
		</div>
	</div>
	</fieldset>
	
	<fieldset>
		<legend>
	    	<span>变更后</span>
	    </legend>
	   <div class="nui-toolbar"
		style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
		<!--<a class="nui-button" id="tx_add" iconCls="icon-add"
			onclick="addtx()">增加</a>-->
		<a class="nui-button" id="tx_edit"
			iconCls="icon-edit" onclick="edittx(0)">编辑</a> 
		<a class="nui-button" id="tx_view" iconCls="icon-node"
			onclick="edittx(1)">查看</a> 
		<!--<a class="nui-button" id="tx_remove"
			iconCls="icon-remove" onclick="removetx()">删除</a>-->
	</div> 
	  <div id="gridtx2" class="nui-datagrid" style="width: 99%; height: 150px"
		url="com.bos.aft.conLoanChange.getTxList.biz.ext"
		dataField="txs" allowResize="false" showReloadButton="false"
		allowCellEdit="false" sizeList="[10,15,20,50,100]"
		multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="txfs" headerAlign="center" allowSort="true"
				dictTypeId="XD_TXFS0001">贴息方式</div>
			<div field="txbl" headerAlign="center" allowSort="true">贴息比例（%）</div>
			<div field="gdje" headerAlign="center" allowSort="true">固定金额（元）</div>
			<div field="xe" headerAlign="center" allowSort="true">限额</div>
			<div field="qx" headerAlign="center" allowSort="true">期限（月）</div>
			<div field="txzt1" headerAlign="center" allowSort="true">贴息主体</div>
			<div field="txzh1" headerAlign="center" allowSort="true">贴息账号</div>
		</div>
	</div>
	</fieldset>
	
	
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
	var gridtx = nui.get("gridtx");
	var gridtx2 = nui.get("gridtx2");
	
	var applyId;
	<%-- var contractId ="<%=request.getParameter("contractId") %>";  --%>
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.encode({"changeId":changeId});
		$.ajax({
            url: "com.bos.aft.conLoanChange.findChangeInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
            	  applyId=o.tbBizApprove.applyId;
            	var json = {"applyId":applyId,"changeId":changeId,"bglx":"1"};	
      			  gridtx.load(json);
      			  
      			  var json2 = {"applyId":applyId,"changeId":changeId,"bglx":"2"};	
      			  gridtx2.load(json2);
      			   
            	form.setData(o);
            	if(nui.get("tbCsmParty.partyTypeCd").getValue()=="01") {
            		nui.get("orgRegisterCd01").setValue("组织机构代码");
            	}
            	query1();
			}
        });
        
		//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
		if("1" != proFlag){
			nui.get("con_contract_info_save").hide();
	//		nui.get("tx_add").hide();
			nui.get("tx_edit").hide();
			nui.get("tx_view").hide();
	//		nui.get("tx_remove").hide();
			
			form.setEnabled(false);
		}
        
	}
	
	function query1(){
		var partyTypeCd= nui.get("tbCsmParty.partyTypeCd").getValue();
  		if(partyTypeCd=="01"){
  			$("#type01").css("display","block");
  			$("#type02").css("display","none");
  		}else if(partyTypeCd=="02"){
  			$("#type01").css("display","none");
  			$("#type02").css("display","block");
  		}else {
  			$("#type01").css("display","none");
  			$("#type02").css("display","none");
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

<script>
	
	function onselectType(e){		
		var newTxWay= nui.get("tbConLoanTx.newTxWay").getValue();
		if(newTxWay=='01'){//比例
				$("#txRate").css("display","block");
				$("#txAmt").css("display","none");
				$("#txMaxamt").css("display","none");
			}else if(newTxWay=='02'){//固定金额
				$("#txRate").css("display","none");
				$("#txAmt").css("display","block");
				$("#txMaxamt").css("display","none");
			}else if(newTxWay=='03'){//限额+比例
				$("#txRate").css("display","block");
				$("#txAmt").css("display","none");
				$("#txMaxamt").css("display","block");
			}else{//限额+比例
				$("#txRate").css("display","none");
				$("#txAmt").css("display","none");
				$("#txMaxamt").css("display","none");
			}
	}
	
	//编辑贴息信息
	function edittx(v) {
		var grid = nui.get("gridtx2");
		var row = grid.getSelected();
		if (row) {
			nui.open({
				url : nui.context + "/aft/conLoanChange/con_tx_edit.jsp?txId=" + row.txId + "&view=" + v,
				title : "编辑",
				width : 800,
				height : 500,
				allowResize : true,
				showMaxButton : true,
				onload : function() {
					var iframe = this.getIFrameEl();
					var data = row;
					//iframe.contentWindow.SetData(data);
				},
				ondestroy : function(action) {
					if (action == "ok") {
						var json = nui.decode({ "applyId" : applyId,"changeId":changeId,"bglx":"2" });
						gridtx2.load(json);
					}
				}
			});
		} else {
			alert("请选择项目信息！");
		}
	}
	
		function addtx() {
		nui.open({
				url : nui.context + "/aft/conLoanChange/con_tx_add.jsp?applyId=" + applyId+"&changeId="+changeId+"&bglx="+"2",
				title : "新增",
				width : 800,
				height : 500,
				allowResize : true,
				showMaxButton : true,
				ondestroy : function(action) {
					if (action == "ok") {
						var json = nui.decode({ "applyId" : applyId,"changeId":changeId,"bglx":"2" });
						gridtx2.load(json);
					}
				}
			});
	}
		//删除贴息信息
	function removetx() {
		var grid = nui.get("gridtx2");
		var rows = grid.getSelected();
		if (null == rows) {
			nui.alert("请选择项目信息！");
			return false;
		}
		var json = nui.encode({
			"tbConLoanTx" : rows
		});
		nui.confirm("确定删除吗？", "确认", function(action) {
			if (action != "ok")
				return;
			$.ajax({
				url : "com.bos.aft.conLoanChange.delTx.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg);
						return;
					}
					var json = nui.decode({ "applyId" : applyId,"changeId":changeId,"bglx":"2" });
						gridtx2.load(json);
				},
				error : function() {
					nui.alert("操作失败！");
				}
			});
		});
	}
 </script>

</body>
</html>