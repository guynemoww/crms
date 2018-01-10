<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>贷后管理信息查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="limitdetailinfo.partyNum" required="false" class="nui-textbox nui-form-input"  />
		<label>合同号：</label>
		<input name="" required="false" class="nui-textbox nui-form-input"  />
		<label>客户经理：</label>
		<input name="" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
	<strong >贷后管理信息查询</strong >
	<div id="grid1" class="nui-datagrid" style="width:auto;height:auto" 
		url="com.bos.pub.standingbook.bizinfo.limitdetailinfo.biz.ext"
		dataField="limitdetailinfos"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="" headerAlign="center" allowSort="true" >合同号</div>
			<div field="" headerAlign="center" allowSort="true" >授信审核审批依据</div>
			<div field="" headerAlign="center" allowSort="true" >行业</div>
			<div field="" headerAlign="center" allowSort="true" >主办客户经理</div>
			<div field="" headerAlign="center" allowSort="true" >审批人</div>
			<div field="" headerAlign="center" allowSort="true" >贷后检查清单</div>
			<div field="" headerAlign="center" allowSort="true" >还款方式</div>
			<div field="" headerAlign="center" allowSort="true" >资金流向</div>
			<div field="" headerAlign="center" allowSort="true" >贷后检查日志</div>
			<div field="" headerAlign="center" allowSort="true" >分类</div>
			<div field="" headerAlign="center" allowSort="true" >还款履约情况</div>
			<div field="" headerAlign="center" allowSort="true" >重大</div>
			<div field="" headerAlign="center" allowSort="true" >应急处理</div>
			<div field="" headerAlign="center" allowSort="true" >调查报告退回次数</div>
			<div field="" headerAlign="center" allowSort="true" >修改补充事项</div>
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