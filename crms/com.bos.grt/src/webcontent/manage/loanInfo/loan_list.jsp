<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-07
-->
<head>
<title>关联合同信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="grid4" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtManage.linkLoan.getLoanInfo.biz.ext"
		dataField="arrays"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn">序号</div>
			<div field="SUBCONTRACT_NUM" headerAlign="center">抵质押合同编号</div>
			<div field="SUBCONTRACT_AMT" headerAlign="center"dataType="currency">抵质押合同金额</div>
			<div field="PARTY_NAME" headerAlign="center">借款人名称</div>
			<div field="CONTRACT_NUM" headerAlign="center" >借款合同编号</div>
			<div field="SURETY_AMT" headerAlign="center"dataType="currency" >担保金额</div>
		</div>
	</div>
	
	<script type="text/javascript">
 	nui.parse();
 	
 	//押品主键ID
	var suretyId ="<%=request.getParameter("suretyId")%>";
		
	var grid = nui.get("grid4");
	
	var v="<%=request.getParameter("view") %>";
	
    search();
    
    function search() {
		var json=({"suretyId":suretyId});
	    grid.load(json);
	    
	    grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			e.data[i]['CONTRACT_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].CONTRACT_NUM+ '\');">'+e.data[i]['CONTRACT_NUM']+'</a>';
       			e.data[i]['SUBCONTRACT_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].SUBCONTRACT_NUM+ '\');">'+e.data[i]['SUBCONTRACT_NUM']+'</a>';
       		}
       });
    }
	
	function clickSubcontractNum(contractId){
		git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+contractId+"&contractType=02&proFlag=-1",parent);
	}
	
	
	</script>
</body>
</html>
