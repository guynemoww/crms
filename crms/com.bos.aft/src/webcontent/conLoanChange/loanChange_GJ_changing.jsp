<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lenovo
  - Date: 2017-08-01 10:30:46
  - Description:
-->
<head>
<title>贸易融资变更撤销</title>
<%@include file="/common/nui/common.jsp"%>    
</head>
<body>
		<div class="nui-toolbar" style="text-align:left;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" id="ck" iconCls="icon-search" onclick="query()">查看详情</a>
			<a class="nui-button" id="cx" iconCls="icon-reset" onclick="cx()">变更撤销</a>
		</div>
	<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.conLoanChange.findGjLoanList.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="LOAN_CHANGE_TYPE" headerAlign="center" align="center" dictTypeId="XD_DHBG0001">变更类型</div>
		        <div field="PARTY_NAME" headerAlign="center" align="center" >客户名称</div>
		        <div field="PRODUCT_TYPE" headerAlign="center" align="center" dictTypeId="product">业务品种</div>
		        <div field="CONTRACT_NUM" headerAlign="center" align="center" >合同编号</div>
		        <div field="SUMMARY_NUM" headerAlign="center" align="center" >借据编号</div>
				<div field="SUMMARY_AMT" headerAlign="center" align="right" dataType="currency">借据金额</div>
				<div field="JJYE" headerAlign="center" align="right" dataType="currency">借据余额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据起期</div>
				<div field="END_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据止期</div>
		    </div>
		</div>

	<script type="text/javascript">
    	nui.parse();
		var grid = nui.get("grid");  //借据列表
    	initPage();
		function initPage(){
			grid.load();
			grid.on("preload",function(e){
       			if (!e.data || e.data.length < 1)
       				return;
       			for (var i=0; i<e.data.length; i++){
       				//nui.alert(nui.encode(e.data[i].PARTY_NAME));
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
	function query(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		var changeId = row.CHANGE_ID;	
		var changeType = row.LOAN_CHANGE_TYPE;
		if(changeType=="12"){
			nui.open({
				url: nui.context+"/aft/conLoanChange/loanChange_xyz_apply.jsp?proFlag=0&changeId="+changeId,
		    	showMaxButton: false,
		   		title: "信用证变更信息",
		    	width: 1024,
		    	height: 768,
		    	state:"max",
		    	ondestroy: function (action) {
		        	initPage();    	
		        }
	    	});
		}
		if(changeType=="13"){
			nui.open({
				url: nui.context+"/aft/conLoanChange/loanChange_bh_apply.jsp?proFlag=0&changeId="+changeId,
		    	showMaxButton: false,
		    	title: "保函变更信息",
		    	width: 1024,
		    	height: 768,
		    	state:"max",
		    	ondestroy: function (action) {
		        	initPage();       	
		        }
	    	});
		}
	}
	
	function cx(){
		git.mask();  
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		nui.get("cx").setEnabled(false);
		var json1 = nui.encode({"summaryNum":"","loanId":row.LOAN_ID,"summaryId":row.SUMMARY_ID,"changeId":row.CHANGE_ID});
		$.ajax({
			url: "com.bos.pay.LoanSummary.GjBussBackout.biz.ext",
			type: 'POST',
			data: json1,
		    async: false,
			contentType:'text/json',
			cache: false,
			success: function (mydata) {
				if(mydata.resultCode=="9999"){
					alert(mydata.resultMsg);
				    nui.get("cx").setEnabled(true);
				    git.unmask();  
				}else{
				    alert("撤销成功");
				    nui.get("cx").setEnabled(true);
					initPage();
					git.unmask();  
				}
			}
		});
	}
	function goToLoan(e){
		var row=grid.getSelected();
		//alert(row.CONTRACT_ID);
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
		//alert(row.CONTRACT_ID);
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
		//alert(ps[0]+"--"+ps[1]+"--"+ps[2]+"--"+ps[3]);
        if(partyTypeCd=="01") {
        	/* var infourl = nui.context + "/csm/workdesk/csm_corp_tab.jsp?corpid="
            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd; */
	        var infourl = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd+"&qote=1";
        }else {
        	/* var infourl = nui.context + "/csm/workdesk/csm_corp_tab_natural.jsp?corpid="
            + partyId+"&partyNum="+partyNum; */
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
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                initPage();
	            }
      	  });	
            
	}
	
    </script>
</body>
</html>