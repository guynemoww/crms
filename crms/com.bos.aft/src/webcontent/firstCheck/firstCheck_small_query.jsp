<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-11
  - Description:
-->
<head>
<title>首次检查列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>首次检查列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
	
		<div class="nui-dynpanel" columns="6">
			
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>借据编号：</label>
			<input name="map.summaryNum" id="map.summaryNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<label>报告编号：</label>
			<input name="map.reportNum" id="map.reportNum" required="false" class="nui-textbox nui-form-input"  />
			
		</div>
				<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		
 			<a class="nui-button"onclick="query">查询</a>
		</div>
		<div class="nui-toolbar">
			<!-- <a class="nui-button" id="btnEdit" onclick="edit('grid')">修改</a> -->
			<a class="nui-button" id="btnView" onclick="view('grid')">查看</a>
			<a class="nui-button" id="btnDownload" onclick="clickDownload()">下载</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.firstCheck.findFirstChcekListSmall.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NUM" headerAlign="center" align="center">客户编号</div>
				<div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
		        <div field="SUMMARY_NUM" align="center" headerAlign="center" >借据编号</div> 
		        <div field="SUMMARY_AMT" headerAlign="center" align="right" dataType="currency">借据金额</div>
				<div field="JJYE" headerAlign="center" align="right" dataType="currency">借据余额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据起期</div>
				<div field="END_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据止期</div> 
				<div field="REPORT_NUM" align="center" headerAlign="center" >报告编号</div> 
				<div field="REPORT_NAME" align="center" headerAlign="center" >报告名称</div> 
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  
	query();
	function query(){
		var o = form.getData();
		grid.load(o);
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			//客户链接
       			e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM
       				+ '\');return false;" value="'
       				+ e.data[i].PARTY_ID
       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
	       				
       			//借据链接
       			e.data[i]['SUMMARY_NUM']='<a href="#" onclick="goToLoanSum();return false;" value="'
       				+ e.data[i].contractDetailId
       				+ '">'+e.data[i]['SUMMARY_NUM']+'</a>';
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
	
	/* function clickCust(e){
		var ps = e.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var infourl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
            + partyId+"&partyNum="+partyNum+"&qote=1";
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
	                query();
	            }
      	  });	
	} */
	
	function clickCust(partyId,partyNum){
		var ps = partyId.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var url = nui.context +  "/csm/loan/loan_tree.jsp?partyId="
	        + partyId+"&qote=1&partyNum="+partyNum;
	     //查看
			nui.open({
	            url: url,
	            showMaxButton: true,
	            title: "查看客户信息",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                query();
	            }
      	 	 });
	}
	
	function edit(gr){
		var row = nui.get(gr).getSelected();
		if (row) {
			nui.open({
	            url: nui.context+"/aft/firstCheck/firstCheck_small_tree.jsp?firstCheckId="+row.FIRST_CHECK_ID+"&partyId="+row.PARTY_ID+"&isSmall=1&flag=2",
	            showMaxButton: true,
	            title: "首次检查",
	            width: 800,
	            height: 500,
	            state:"max",
	            ondestroy: function(e) {
	                //top.bizConWin = this;
            		//grid.load({"applyId":applyId});
	            }
	        });
    	}else{
    		nui.alert("请选中一条记录");
    	}
	}
	
	function view(gr){
		var row = nui.get(gr).getSelected();
		if (row) {
			var amountDetailId = row.amountDetailId;
    		nui.open({
	            url: nui.context+"/aft/firstCheck/firstCheck_tree.jsp?firstCheckId="+row.FIRST_CHECK_ID+"&partyId="+row.PARTY_ID+"&isSmall=0&flag=3",
	            showMaxButton: true,
	            title: "首次检查",
	            width: 800,
	            height: 500,
	            state:"max",
	            ondestroy: function(e) {
	            	//top.bizConWin = this;
            		//grid.load({"applyId":applyId});
	            }
	        });
    	}else{
    		nui.alert("请选中一条记录");
    	}
	} 
	
	function download(){
	
		nui.alert("该功能正在开发中。。。。。。");
		return false;
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		
	}
	
	function clickDownload(){
	
		/* nui.alert("该功能正在开发中。。。。。。");
		return false; */
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		
		/* var json = nui.encode({"map":{"sqlName":"com.bos.bizApprove.bizApprove.getApproveInfo","applyId":applyId,"reportName":'/approve/XDZXApproveNotice.docx'}}); */
		/* var json = nui.encode({"map":{"sqlName":"com.bos.aft.firstCheck.getApproveInfo","applyId":applyId,"reportName":'/aft/firstCheck_small.docx'}}); */
		var json = nui.encode({"map":{"checkId":row.FIRST_CHECK_ID,"partyId":row.PARTY_ID,"reportName":'/aft/firstCheck.docx'}});
		$.ajax({
            url: "com.bos.aft.firstCheck.printFirstCheck.biz.ext",
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
	
	//初始化自然人证件类型
	function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	  //          nui.get("map.certType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
    init();	
</script>
</body>
</html>