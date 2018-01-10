<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-11-26
  - Description:
-->
<head>
<title>指定账号还款</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>借据信息</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
	
		<div class="nui-dynpanel" columns="6">
		
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
			<!-- <input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" /> -->
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<label>合同编号：</label>
			<input name="map.contractNum" id="map.contractNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<label>借据编号：</label>
			<input name="map.summaryNum" id="map.summaryNum" required="false" class="nui-textbox nui-form-input"  /> 
			
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
		
		<div class="nui-toolbar" style="margin-top:7px;width:99.5%">
			<!-- <label class="nui-form-label">贷后变更类型：</label>
			<input name="loanChangeType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHBG0001" id="loanChangeType" 
					style="margin-right:5px;height:21px;width:13%"/> -->
			<a class="nui-button" id="btnCreate" onclick="add" style="margin-right:27px;height:21px">创建</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.conLoanChange.findLoanListAcc.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" headerAlign="center" align="center" >客户名称</div>
		        <div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">业务品种</div>
		        <div field="CONTRACT_NUM" headerAlign="center" align="center" >合同编号</div>
		        <div field="SUMMARY_NUM" headerAlign="center" align="center" >借据编号</div>
				<div field="SUMMARY_AMT" headerAlign="center" align="right" dataType="currency">借据金额</div>
				<div field="JJYE" headerAlign="center" align="right" dataType="currency">借据余额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center">借据起期</div>
				<div field="END_DATE" headerAlign="center" align="center">借据止期</div>
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var loanChangeType = nui.get("loanChangeType");
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //借据列表
	
	var busDate;
	
	query();
	function query(){
	
		var json = nui.encode({});
		$.ajax({
            url: "com.bos.aft.conLoanChange.getBusDate.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
            	busDate = mydata.busDate;
			}
        });
		
		var o = form.getData();
		grid.load(o);
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			if(null !=e.data[i].PARTY_NAME && ''!=e.data[i].PARTY_NAME){
	       			//客户链接
	       			e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
	       				
	       			//合同链接
	       			e.data[i]['CONTRACT_NUM']='<a href="#" onclick="goToLoan();return false;" value="'
	       				+ e.data[i].contractDetailId
	       				+ '">'+e.data[i]['CONTRACT_NUM']+'</a>';
	       				
	       			//借据链接
	       			e.data[i]['SUMMARY_NUM']='<a href="#" onclick="goToLoanSum();return false;" value="'
	       				+ e.data[i].contractDetailId
	       				+ '">'+e.data[i]['SUMMARY_NUM']+'</a>';
	       				
       			}else{
       			
       				e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['ENGLISH_NAME']+'</a>';
       			}
       		}
       });
	}
	
	function goToLoan(e){
		var row=grid.getSelected();
		nui.open({
            url:nui.context +"/crt/con_info/con_tree.jsp?contractId="+row.CONTRACT_ID+"&contractType=02&proFlag=-1",
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
	}
	
	function goToLoanSum(e){
		var row=grid.getSelected();
		nui.open({
            url:nui.context +"/pay/payout_info/pay_tree.jsp?loanId="+row.LOAN_ID+"&processInstId=0&proFlag=-1",
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            }
  	 	 });	
	}
	
	function clickCust(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
		var corpCustomerTypeCd = ps[2];
		var partyTypeCd = ps[3];
        if(partyTypeCd=="01") {
	        var infourl = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd+"&qote=1";
        }else {
	        var infourl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&qote=1";
        }
        
            
            
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
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	  });	
            
	}
	
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		
		/* if(loanChangeType.getValue()=="") {
			return nui.alert("请选择贷后变更类型");
		} */
		
		if(row.PRODUCT_TYPE=="01006001" || row.PRODUCT_TYPE=="01006002" || 
		row.PRODUCT_TYPE=="01006010" //村镇银行贴现产品
		) {
			return nui.alert("汇票贴现不可做贷后变更！");
		}
		
		if((row.PRODUCT_TYPE=="01008001" || row.PRODUCT_TYPE=="01008010") && row.SUMMARY_STATUS_CD!="03") {
			return nui.alert("银行承兑汇票不可做贷后变更！");
		}
		
		if(row.PRODUCT_TYPE=="01008002" && row.SUMMARY_STATUS_CD!="03") {
			return nui.alert("银承通不可做贷后变更！");
		}
		
		nui.get("btnCreate").setEnabled(false);
		
		var json=nui.encode({"contractId":row.CONTRACT_ID,"partyId":row.PARTY_ID,"summaryId":row.SUMMARY_ID,"loanChangeType":"15","isSmall":row.IS_SMALL});
		$.ajax({  
	        url: "com.bos.aft.conLoanChange.createAccountrepay.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
            	if(data.flag){
                	return nui.alert(data.flag);
                }
            	nui.open({//传值到tree页面
		            url: nui.context+"/aft/conLoanChange/loanChange_tree_accountrepay.jsp?changeId="+data.tbConLoanChange.changeId+"&contractId="+row.CONTRACT_ID+"&partyId="+row.PARTY_ID+"&summaryId="+row.SUMMARY_ID+"&loanChangeType=15",
		            showMaxButton: false,
		            title: "贷后变更",
		            width: 1024,
		            height: 768,
		            state:"max",
		            ondestroy: function (action) {
		            	query();//重新刷新页面
		            }
	        	});
	        	if(data.msg){
	        		if(data.msg != "创建成功！") {
	        			alert(data.msg);
	        		}
                }
            },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            git.unmask();
	        }
        });	
	}
    
    //重置
	function reset(){
		form.reset();
	}
</script>
</body>
</html>