<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>保函台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="融资性保函台账" >
<center>
<form id="form1" action="" class="nui-form"method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
		<label>申请单位：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</form>	
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px" 
		url="com.bos.pub.standingbook.bizinfo.financebackletterinfo.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="5" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >申请单位</div>
			<div field="productName" headerAlign="center" allowSort="true">种类</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" >签发金额（元）</div>
			<div field="startDate" headerAlign="center" allowSort="true" >签发日期</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >截止日期</div>
			<div field="isSupplyChainBussiness" headerAlign="center" allowSort="true" dictTypeId="YesOrNo" >是否为供应链业务</div>
		</div>
	</div>
</div> 	
</center>
</div>
</div>	
<div id="tabs2" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="非融资性保函台账" >
<center>
<form id="form2" action="" class="nui-form"method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6">
		<label>申请单位：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search2()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset2()">重置</a>
	</div>
</form>	
	<div id="grid2" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px" 
		url="com.bos.pub.standingbook.bizinfo.missfinancebackletterinfo.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="5" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >申请单位</div>
			<div field="productName" headerAlign="center" allowSort="true">种类</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" >签发金额（元）</div>
			<div field="startDate" headerAlign="center" allowSort="true" >签发日期</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >截止日期</div>
			<div field="isSupplyChainBussiness" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否为供应链业务</div>
		</div>
	</div>
</center>
</div>
</div>		
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
    var form2 = new nui.Form("#form2"); 
	var grid = nui.get("grid1");
	var grid2 = nui.get("grid2");
	
	//初始化融资性保函页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}

	//初始化非融资性保函页面
    function search2() {
		var data = form2.getData(); //获取表单多个控件的数据
        grid2.load(data);
    }
    search2();
    
    function reset2(){
		form2.reset();
		search2();
	}
	

	</script>
</body>
</html>