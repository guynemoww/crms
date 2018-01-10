<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): hanshuo
  - Date: 2014-05-27 09:40:34
  - Description:
-->
<head>
<title>贷后检查工作情况查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="aftWorkFlowChecking.partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>组织机构代码：</label>
		<input name="aftWorkFlowChecking.orgNum" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
	<strong >贷后管理信息查询</strong >
	<div id="grid1" class="nui-datagrid" style="width:auto;height:auto" 
		url="com.bos.pub.standingbook.aftmanager.aftworkflowchecking.biz.ext"
		dataField="aftWorkFlowCheckings"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="certCode" headerAlign="center" allowSort="true" >组织机构代码</div>
			<div field="fourrCD" headerAlign="center" allowSort="true" >企业规模</div>
			<div field="productType" headerAlign="center" allowSort="true" >业务品种</div>
			<div field="contractTotalAmt" headerAlign="center" allowSort="true" >合同金额</div>
			<div field="startDate" headerAlign="center" allowSort="true" >放款日</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >到期日</div>
			<div field="orgNum" headerAlign="center" allowSort="true" >所属支行</div>
			<div field="userNum" headerAlign="center" allowSort="true" >客户经理</div>
			<div field="" headerAlign="center" allowSort="true" >营销团队</div>
		</div>
	</div>
</div> 	
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