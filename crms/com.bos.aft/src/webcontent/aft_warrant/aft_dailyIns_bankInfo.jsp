<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-07-30 16:55:30
  - Description:
-->
<head>
<title>基本信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			    <label class="nui-form-label">担保机构编号：</label>
				<input name="partyNum" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		
			    <label class="nui-form-label">担保机构名称：</label>
				<input id="partyName" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		</div>
		<fieldset>
		  	<legend>
		    	<span>与我行合作情况</span>
		    </legend>
		    <div class="nui-dynpanel" columns="4">
		    	<label class="nui-form-label">与我行合作年限(单位：年)</label>
				<input id="periodMb" name="periodMb" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100"/>
	
		    	<label class="nui-form-label">我行授信额度：</label>
		    	<div>
					<input id="creditAmt" name="creditAmt" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
		        
		        <label class="nui-form-label">我行授信额度中备用额度：</label>
				<div>
					<input id="creditGrantingQuota" name="creditGrantingQuota" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
		        
		    	<label class="nui-form-label">我行在保余额：</label>
				<div>
					<input id="bulgariaBalance" name="bulgariaBalance" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
	
		    	<label class="nui-form-label">其中：我行口径小企业在保余额：</label>
				<div>
					<input id="bulgariaBalanceSmb" name="bulgariaBalanceSmb" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					万元
		        </div>
	
		    	<label class="nui-form-label">我行在保户数：</label>
				<div>
					<input id="householdsNum" name="householdsNum" required="false" setEnabled="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					户
		        </div>
		        
		        <label class="nui-form-label">其中：为我行口径小企业提供担保户数：</label>
		        <div>
					<input id="householdsNumSmb" name="householdsNumSmb" required="false" class="nui-text nui-form-input"  />
					户
		        </div>
		        
		    	<label class="nui-form-label">保证金账户余额：</label>
				<div>
					<input id="marginAccountBal" name="marginAccountBal" required="false" class="nui-textbox nui-form-input"  />
					万元
		        </div>
	
		    	<label class="nui-form-label">保证金比例：</label>
		    	<div>
					<input id="marginRadio" name="marginRadio" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
					%
				</div>
				
		    	<label class="nui-form-label">审批要求的保证金比例：</label>
		    	<div>
					<input id="approvalRequireMargin" name="approvalRequireMargin" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />%
				</div>
				
				<label class="nui-form-label">利率水平：</label>
				<input id="rate" name="rate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />
		    </div>
	    </fieldset>
	 </div>
	 <div id="form2" style="width:100%;height:auto;overflow:hidden;">   
	    <fieldset>
		  	<legend>
		    	<span>同业合作情况</span>
		    </legend>
		    <div id="addBtn" style="border-bottom:0;text-align:left;padding-top: 10px;padding-bottom:10px;">
				<a class="nui-button" iconCls="icon-add" onclick="btnAdd()">添加</a>
				<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		    </div>
			<div id="datagrid" class="nui-datagrid" showPager="false" ondrawsummarycell="onDrawSummaryCell" showSummaryRow="true"
	        	dataField="items"  idField="id" allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true">
		        <div property="columns"> 
			        <div field="cooperationBank" width="120" headerAlign="center">合作银行
		                <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>           
		            </div> 
		            <div field="approvedAmount" width="120" headerAlign="center">核定担保额度
		                <input property="editor" class="nui-textbox" dataType="float" style="width:100%;" minHeight="50"/>
		            </div>
		            <div field="approvedGuaranteeAmt" width="120" headerAlign="center">核定融资担保额度
		                <input property="editor" class="nui-textbox" dataType="float" style="width:100%;" minHeight="50"/>
		            </div>
		            <div field="guaranteeBal" width="120" headerAlign="center">融资担保额度
		                <input property="editor" class="nui-textbox" dataType="float" style="width:100%;" minHeight="50"/>
		            </div>
		            <div field="guaranteeHouseHolds" width="120" headerAlign="center">融资担保户数
		                <input property="editor" class="nui-textbox" dataType="float" style="width:100%;" minHeight="50"/>
		            </div>
		            <div field="marginRatio" width="120" headerAlign="center">保证金比例(%)
		                <input property="editor" class="nui-textbox" dataType="float" style="width:100%;" minHeight="50"/>
		            </div>
		            <div field="geId" visible="false" headerAlign="center">外部合作情况id</div>
				</div>
			</div>
			
	    </fieldset>
	</div>
	<div id="save" class="nui-toolbar"  style="border-bottom:0;text-align:right;margin-top: 20px;">
		<a class="nui-button" id="saveBtn" iconCls="icon-save" onclick="save()">保存</a>
		<a href="#" onclick="clickDownload()">贷后检查报告下载</a>
	</div>
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>
<script type="text/javascript">
	nui.parse();
	var form1 = new nui.Form("#form1");
	var form2 = new nui.Form("#form2");
	var giId = "<%=request.getParameter("giId")%>";
	var processInstId = "<%=request.getParameter("processInstId")%>";
	var posicode = "<%=request.getParameter("posicode") %>";
	var grid = nui.get("datagrid");
	var state;//用于控制页面能否编辑（状态）
	var partyId;
	var flgDisplay;//用于判断是否可编辑
	init();
	function getReplacePos(){
     var json = nui.encode({"posicode":posicode});
     nui.ajax({
         url: "com.bos.irm.queryInfo.queryReplacePos.biz.ext",
         type: 'POST',
         data: json,
         cache: false,
         async:false,        
         contentType:'text/json',
         success: function (text) {
             var o = nui.decode(text);
             flgDisplay = o.posCd;
         }
     });
 } 
	
	function init(){
	    getReplacePos();
		var json=nui.encode({"giId":giId});
		nui.ajax({
			url: "com.bos.aft.aft_warrant.queryBankInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async:false,
            success: function (text) {
            	if(text.msg == 1){
            		var o = nui.decode(text);
            		state = o.out.inspectState;
            		partyId = o.partyId;
            		nui.get("partyName").setValue(o.partyName);
            		grid.setData(o.items);
            		form1.setData(o.out);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
		});
		/*var inPut = grid.getData();
		return;
		for(var i=0;i<inPut.length;i++){
			if (inPut[i].cooperationBank == "汇总"){
				grid.
			}
		}*/
		/*if(state == 0){
		}else{
			$("#save").hide();
			$("#addBtn").hide();
			grid.allowCellEdit=false; 
			form1.setEnabled(false);//设置页面不能被编辑
			form2.setEnabled(false);//设置页面不能被编辑
		}*/
		if(flgDisplay == "P1001"){
		
		}else{
		   $("#save").hide();
			form1.setEnabled(false);//设置页面不能被编辑
			form2.setEnabled(false);//设置页面不能被编辑
		}
	}
	function btnAdd(){
			var index=grid.totalCount;
		    var newRow = { name: "New Row" };
            grid.addRow(newRow, index);
	}
	
	function onDrawSummaryCell(e){
		var result = e.result;
        var grid1 = e.sender;
        var rows = e.data;
            //服务端汇总计算
            if (e.field == "cooperationBank") {                
                var s = "汇总";
                e.cellHtml = s;
            }
            
            if (e.field == "approvedAmount"){
            	var approvedAmountTotal = 0;
            	for(var i = 0, l = rows.length; i < l; i++) {
            		var row = rows[i];
            		approvedAmountTotal =  parseInt(row.approvedAmount)+parseInt(approvedAmountTotal);
            	}
            	e.cellHtml = approvedAmountTotal;
            }
            
            if (e.field == "approvedGuaranteeAmt"){
            	var approvedGuaranteeAmtTotal = 0;
            	for(var i = 0, l = rows.length; i < l; i++) {
            		var row = rows[i];
            		approvedGuaranteeAmtTotal = parseInt(row.approvedGuaranteeAmt)+parseInt(approvedGuaranteeAmtTotal);
            	}
            	e.cellHtml = approvedGuaranteeAmtTotal;
            }
            
            if (e.field == "guaranteeBal"){
            	var guaranteeBalTotal = 0;
            	for(var i = 0, l = rows.length; i < l; i++) {
            		var row = rows[i];
            		guaranteeBalTotal = parseInt(row.guaranteeBal)+parseInt(guaranteeBalTotal);
            	}
            	e.cellHtml = guaranteeBalTotal;
            }
            
            if (e.field == "guaranteeHouseHolds"){
            	var guaranteeHouseHoldsTotal = 0;
            	for(var i = 0, l = rows.length; i < l; i++) {
            		var row = rows[i];
            		guaranteeHouseHoldsTotal = parseInt(row.guaranteeHouseHolds)+parseInt(guaranteeHouseHoldsTotal);
            	}
            	e.cellHtml = guaranteeHouseHoldsTotal;
            }
            
            /*if (e.field == "marginRatio"){
            	var marginRatioTotal = 0;
            	for(var i = 0, l = rows.length; i < l; i++) {
            		var row = rows[i];
            		marginRatioTotal = parseInt(row.marginRatio)+parseInt(marginRatioTotal);
            	}
            	e.cellHtml = marginRatioTotal;
            }*/
            
	}
	
	function remove(){
        var row = grid.getSelected();
        if(row){
        	var json=nui.encode({"row":row});
        	nui.ajax({
				url: "com.bos.aft.aft_warrant.deleteBankInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            async:false,
	            success: function (text) {
	            	if(text.msg == 1){
	            		alert("删除成功");
	            	}else{
	            		alert("删除失败");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	            }
			});
        	grid.removeRow(row);
      	}else{
        	alert("请选中一条记录");
        }
    }
    
    //保存
    function save(){
    	var item = form1.getData();
    	var items = grid.getData();
    	var json=nui.encode({"giId":giId,"item":item,"items":items});
    	nui.ajax({
			url: "com.bos.aft.aft_warrant.saveBankInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async:false,
            success: function (text) {
            	if(text.msg == 1){
            		alert("保存成功");
            	}else{
            		alert("保存失败");
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
		});
    }
    
    function clickDownload(){
    	var items = nui.encode(grid.getData());
    	var ifrm = document.getElementById("downloadFileFrame");
    	ifrm.src="com.bos.aft.aft_warrant.downLoadWarrantyReport.biz.ext2?giId="+giId+"&partyId="+partyId;
		/*var json =nui.encode({"giId":giId,"partyId":partyId}) ;
		$.ajax({
			url: "com.bos.aft.aft_warrant.downloadReport.biz.ext",
			type: 'POST',
			data: json,
			async:false,
			cache: false,
			contentType:'text/json',
			success: function (text) {
				
			},
			error: function () {
			       
			}
		});*/
	}
    
</script>
</body>
</html>