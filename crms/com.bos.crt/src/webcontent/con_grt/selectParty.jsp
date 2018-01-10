<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-04 14:38:20
  - Description:
-->
<head>
<title>选择担保人信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="datagrid" class="nui-datagrid" style="width:99.5%;"  sortMode="client"
	    url="com.bos.grt.conGrt.selectParty.biz.ext" dataField="grtPartys"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	     oncellclick="GetData" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="5">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>             
	        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">担保人名称</div>   
	        <div field="CERT_TYPE" allowSort="true" width="" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
	        <div field="CERT_CODE" allowSort="true" width="" headerAlign="center">证件号码</div>
	    </div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="selectParty_add" iconCls="icon-add" onclick="add()">确定</a>
	</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid");
	var applyId="<%=request.getParameter("applyId")%>";
	var subcontractType="<%=request.getParameter("subcontractType")%>";
	var data;
	query();
	function query(){
		grid.load({"applyId":applyId,"subcontractType":subcontractType});
	}
		//子页面调用方法
	function GetData(){
		var row = grid.getSelected();
		data = row;
		return data;
	}
	function add(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔客户信息");
			return false;
		}
		CloseWindow("ok");
		return;
	}
</script>
</body>
</html>