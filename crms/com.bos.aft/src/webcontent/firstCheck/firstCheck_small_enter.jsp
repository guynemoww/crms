<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-11
  - Description:
-->
<head>
<title>小企业信贷中心贷后首次检查</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>小企业信贷中心贷后首次检查借据列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
	<input name="map.receiptNum" id="map.receiptNum" required="false" class="nui-hidden nui-form-input"/>
		<div class="nui-dynpanel" columns="6">
			
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" />
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<label>中征码：</label>
			<input name="map.middleCode" id="map.middleCode" required="false" class="nui-textbox nui-form-input" />

			<label>借据编号：</label>
			<input name="map.summaryNum" id="map.summaryNum" required="false" class="nui-textbox nui-form-input"  /> 
			
			<!-- <a class="nui-button"onclick="query">查询</a> -->
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	
		<div class="nui-toolbar">
			<a class="nui-button" id="btnCreate" onclick="add">创建</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.firstCheck.findBorListSmall.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NUM" headerAlign="center" align="center">客户编号</div>
				<div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
				<div field="CERT_TYPE" headerAlign="center" align="center" dictTypeId="CDKH0002">证件类型</div>
		        <div field="CERT_NUM" align="center" headerAlign="center" >证件号码</div>  
		        <div field="MIDDEL_CODE" align="center" headerAlign="center" >中征码</div>
		        <div field="SUMMARY_NUM" align="center" headerAlign="center" >借据编号</div> 
		        <div field="SUMMARY_AMT" headerAlign="center" align="right" dataType="currency">借据金额</div>
				<div field="JJYE" headerAlign="center" align="right" dataType="currency">借据余额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据起期</div>
				<div field="END_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">借据止期</div> 
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //借据列表
	query();
	function query(){
		var receiptNum=nui.get("map.receiptNum");
		var receiptNum1="<%=request.getParameter("receiptNum")%>";
		if(null!=receiptNum1&&"null"!=receiptNum1&&""!=receiptNum1){
			receiptNum.setValue(receiptNum1);
		}
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
	
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		
		nui.get("btnCreate").setEnabled(false);
		
		var reportName = row.PARTY_NAME+" "+row.CURR_DATE+" 首次检查";
		var json=nui.encode({"summaryId":row.SUMMARY_ID,"partyId":row.PARTY_ID,"contractId":row.CONTRACT_ID,"isSmall":"1","reportName":reportName});
		$.ajax({
	        url: "com.bos.aft.firstCheck.createFirstCheck.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
            	
            	//git.go(nui.context+"/aft/firstCheck/firstCheck_small_tree.jsp?firstCheckId="+data.tbAftFirstCheck.firstCheckId+"&partyId="+row.PARTY_ID+"&isSmall=1",parent);
	           	nui.open({//传值到tree页面
		            //url: nui.context+"/aft/firstCheck/firstCheck_small_tree.jsp?firstCheckId="+data.tbAftFirstCheck.firstCheckId+"&partyId="+row.PARTY_ID+"&isSmall=1"+"&processInstId="+data.processInstId+"&proFlag=1",
		            url: nui.context+"/aft/firstCheck/firstCheck_small_tree.jsp?firstCheckId="+data.tbAftFirstCheck.firstCheckId+"&partyId="+row.PARTY_ID+"&isSmall=1&flag=1",
		            showMaxButton: false,
		            title: "首次检查",
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
	            nui.get("map.certType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
    init();	
    
    //重置
	function reset(){
		form.reset();
	}
</script>
</body>
</html>