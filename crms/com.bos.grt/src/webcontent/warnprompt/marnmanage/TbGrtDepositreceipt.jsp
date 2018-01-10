<%@page pageEncoding="UTF-8"%>
<html>

<head>
<title>存单到期提示</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<form id="form1" action="" method="post" class="nui-form" style="height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>存单号：</label>
		<input name="map/depositReceiptNum" required="false" class="nui-textbox nui-form-input"  />
		<label>贷款人名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:right;margin-right:440px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	
   </div>
</form>
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:1px"
		url="com.bos.grt.marnmanage.marnmanage.tbGrtDepositreceipt.biz.ext"
		dataField="deposit"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">机构名称</div>
			<div field="partyName" headerAlign="center" allowSort="true" >贷款人名称</div>
			<div field="contractNum" headerAlign="center" allowSort="true">贷款合同号</div>
			<div field="subcontractNum" headerAlign="center" allowSort="true">担保合同号</div>
			<div field="type" headerAlign="center" allowSort="true" >押品类型</div>
			<div field="depositReceiptNum" headerAlign="center" allowSort="true">存单号</div>
			<div field="startDate" headerAlign="center" allowSort="true">存单起始日期</div>
			<div field="dueDate" headerAlign="center" allowSort="true">存单到期日期</div>
			<div field="promptCreateDate" headerAlign="center" allowSort="true">提示生成日期</div>
		</div>
	</div>
</center>
	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}

	</script>
</body>
</html>