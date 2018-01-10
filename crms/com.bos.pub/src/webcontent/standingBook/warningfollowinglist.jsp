<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>客户预警跟踪台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:2015px;height:auto;overflow:hidden;">
<div title="客户预警跟踪台账" >
<center>
<div id="form1" class="nui-form" style="width:99.7%;height:auto;overflow:hidden;" >
	<div class="nui-dynpanel" columns="6">
		<label>经办行：</label>
		<input name="warningfollowinglist.orgNum" required="false" class="nui-textbox nui-form-input"  />
		<label>借款人名称：</label>
		<input name="warningfollowinglist.partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>授信品种：</label>
		<input name="warningfollowinglist.productType" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="padding-right:70px;text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
	<div id="grid1" class="nui-datagrid" style="width:2000px;height:auto;margin-top:7px"" 
		url="com.bos.pub.standingbook.aftmanager.warningfollowinglist.biz.ext"
		dataField="warningfollowinglists"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="warningLevelCd" headerAlign="center" allowSort="true" >预警级别</div>
			<div field="confirmDate" headerAlign="center" allowSort="true" >预警时间</div>
			<div field="orgNum" headerAlign="center" allowSort="true" >经办行</div>
			<div field="partyName" headerAlign="center" allowSort="true" >借款人名称</div>
			<div field="industrialTypeCd" headerAlign="center" allowSort="true" >客户所属行业</div>
			<div field="productType" headerAlign="center" allowSort="true" >授信品种</div>
			<div field="" headerAlign="center" allowSort="true" >表内余额(元)</div>
			<div field="" headerAlign="center" allowSort="true" >表外余额(元)</div>
			<div field="creditExposure" headerAlign="center" allowSort="true" >授信敞口(元)</div>
			<div field="startDate" headerAlign="center" allowSort="true" >借款日</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >到期日</div>
			<div field="guaranterName" headerAlign="center" allowSort="true" >担保信息</div>
			<div field="assessCost" headerAlign="center" allowSort="true" >担保物估值</div>
			<div field="loanFiveLevelClassification" headerAlign="center" allowSort="true" >五级分类</div>
			<div field="signalState" headerAlign="center" allowSort="true" >预警信号</div>
			<div field="" headerAlign="center" allowSort="true" >预案及执行措施</div>
			<div field="" headerAlign="center" allowSort="true" >跟踪情况</div>
			<div field="" headerAlign="center" allowSort="true" >保全措施</div>
			<div field="userNum" headerAlign="center" allowSort="true" >客户经理</div>
			<div field="" headerAlign="center" allowSort="true" >检查监测人员</div>
			<div field="whetherPassPeanuts" headerAlign="center" allowSort="true" >是否小企业</div>
			<div field="" headerAlign="center" allowSort="true" >备注</div>
			<div field="" headerAlign="center" allowSort="true" >所属分行</div>
		</div>
	</div>
</center>
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