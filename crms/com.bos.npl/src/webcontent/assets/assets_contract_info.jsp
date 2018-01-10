<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): zhufaying
  - Date: 2014-07-17 11:33:09
  - Description:
-->
<head>
<title>合同信息</title>
</head>
<body>
	<center>
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		 url="com.bos.crt.queryContract.queryContractList.biz.ext" dataField="cons" allowResize="true"  
		 multiSelect="false" pageSize="5" sortMode="client">	
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div type="indexcolumn">序号</div>
				<div field="contractNum" headerAlign="center"   >合同编号</div>
				<div field="partyName" headerAlign="center"  >客户名称</div>
			    <div field="bizType"  align="center" dictTypeId="XD_SXCD1038">合同性质</div>
			    <div field="bizHappenType"  align="center"  dictTypeId="XD_SXCD1039">合同发生性质</div> 
				<div field="productType" headerAlign="center" dictTypeId="product"  renderer="productType">授信品种</div>
				<div field="contractStatusCd" dictTypeId="XD_SXCD1106"  >合同状态</div>
				<div field="startDate" headerAlign="center"  >起始日期</div>
				<div field="expirationDate" headerAlign="center"  >到期日期</div>
				<div field="currencyCd" dictTypeId="CD000001" >币种</div>
				<div field="contractTotalAmt" headerAlign="center"  dataType="currency" >合同金额</div>
				<div field="occupiedAmt" headerAlign="center"  dataType="currency" >已发放金额</div>
				<div field="availableAmt" headerAlign="center"  dataType="currency" >合同已用金额</div>
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
	    	borderStyle="border:0;">
	    	<a class="nui-button" id="btnCreate" iconCls="icon-ok" onclick="create">确定</a>
	    	<a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
		</div>
	</center>
<script type="text/javascript">
     nui.parse();
	 git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
	 var grid = nui.get("grid1");

    function search() {
		var data = {"con/contractStatusCd":"CS300","con/partyId":"<%=request.getParameter("partyId") %>"};
        grid.load(data);
         git.unmask();
    }
    search();
    
    function create(){
    	var contractInfo = grid.getSelected();
    	if (!contractInfo) {
			nui.alert("请选择一条记录");
			return;
		}
		top.bizConWin.max();
		git.go(nui.context +"/crt/view/contract_main.jsp?contractId="+contractInfo.contractId);
    }
</script>
</body>
</html>