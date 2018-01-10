<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-07 16:44:05
  - Description:
-->
<head>
<title>协议明细信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div class="nui-toolbar" style="text-align:left;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" id="biz_detail_enter_view" iconCls="icon-zoomin" onclick="view()">查看</a>
	    <a class="nui-button" id="btnSave" iconCls="icon-edit" onclick="edit()">编辑</a>
	</div>
	<div id="datagrid" class="nui-datagrid" style="width:99.5%;"  sortMode="client"
	    url="com.bos.conInfo.conInfoSxxy.GetConDetailsPj.biz.ext" dataField="tbBizAmountDetailApproves"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" allowResize="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="productType" allowSort="true" width="" dictTypeId="product">业务品种</div> 
	        <div field="currencyCd" allowSort="true" width="" dictTypeId="CD000001" headerAlign="center">币种</div>
	        <div field="detailAmt" allowSort="true" width="" headerAlign="center" dataType="currency">金额</div>   
	    </div>
	</div>
	<input id="tbBizApply.applyId" class="nui-hidden nui-form-input" name ="tbBizApply.applyId"/>
	<input id="tbBizAmountApply.amountId" class="nui-hidden nui-form-input" name ="tbBizAmountApply.amountId"/>
	<input id="partyId" class="nui-hidden nui-form-input" name ="partyName"/>
	<input id="ecifPartyNum" class="nui-hidden nui-form-input" name ="ecifPartyNum"/>
<script type="text/javascript">
	nui.parse();
	var contractId ="<%=request.getParameter("contractId")%>";
	var bizType ="<%=request.getParameter("bizType")%>";
	var productType ="<%=request.getParameter("productType")%>";
	var grid = nui.get("datagrid");
	
	var partyId ="<%=request.getParameter("partyId")%>";
	nui.get("partyId").setValue(partyId); 
	var ecifPartyNum ="<%=request.getParameter("ecifPartyNum")%>";
	nui.get("ecifPartyNum").setValue(ecifPartyNum); 
	
	initPage();
	//初始化页面
	function initPage(){
		var json = nui.decode({"contractId":contractId,"productType":productType});
		grid.load(json);
	}
	
	function del(){
		grid.load({"applyId":applyId});
	}
	function update(){
	
	}
	function view(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		nui.open({
			url: nui.context + "/com.bos.bizProductDetail.getProductDetail.flow?amountDetailId="+row.amountDetailId
			+"&productType="+row.productType+"&modelFlag=02"+"&contStep=1",
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	//nui.get("datagrid1").reload();
            	top.bizConWin = this;
            }
		});
		//grid.load({"applyId":applyId});
	}
	
	function edit(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		nui.open({
			url: nui.context + "/com.bos.bizProductDetail.getProductDetail.flow?amountDetailId="+row.amountDetailId+"&productType="+row.productType+"&modelFlag=03"+"&proFlag=1"+
			"&contStep=1&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
			state:"max",
            ondestroy: function(e) {
            	//nui.get("datagrid1").reload();
            	top.bizConWin = this;
            }
		});
		//grid.load({"applyId":applyId});
	
	}
</script>	
</body>
</html>