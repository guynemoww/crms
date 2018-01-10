<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>授信业务台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>经营单位：</label>
		<input name="limitdetailinfo.orgNum" required="false" class="nui-textbox nui-form-input"  />
		<label>客户名称：</label>
		<input name="limitdetailinfo.partyNum" required="false" class="nui-textbox nui-form-input"  />
		<label>授信品种：</label>
		<input name="limitdetailinfo.productType" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
	<strong >授信业务台账</strong >
	<div id="grid1" class="nui-datagrid" style="width:3000px;height:auto" 
		url="com.bos.pub.standingbook.bizinfo.limitdetailinfo.biz.ext"
		dataField="limitdetailinfos"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="orgNum" headerAlign="center" allowSort="true" >经营单位</div>
			<div field="userNum" headerAlign="center" allowSort="true" >客户经理</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product">授信品种</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">授信币种</div>
			<div field="creditAmt" headerAlign="center" allowSort="true" >授信金额（原币）</div>
			<div field="" headerAlign="center" allowSort="true" >授信金额（折人民币）</div>
			<div field="creditExposure" headerAlign="center" allowSort="true" >授信敞口（原币）</div>
			<div field="" headerAlign="center" allowSort="true" >授信敞口（折人民币）</div>
			<div field="creditTerm" headerAlign="center" allowSort="true" >授信期限</div>
			<div field="validDate" headerAlign="center" allowSort="true" >最终批复日</div>
			<div field="whetherPassPeanuts" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否小企业</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否小微企业</div>
			<div field="ifSteelFlag" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否钢贸</div>
			<div field="groupCustomer" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否集团关联</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否民营集团关联</div>
			<div field="" headerAlign="center" allowSort="true" >所属集团名称</div>
			<div field="directReplacementEstateLoan" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否房地产</div>
			<div field="isRemoteService" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否异地</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否重组</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否借新还旧</div>
			<div field="isSupplyChainBussiness" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否为供应链业务</div>
			<div field="guarantyType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1020">担保方式</div>
			<div field="mapRate" headerAlign="center" allowSort="true" >抵质押率</div>
			<div field="" headerAlign="center" allowSort="true" >最终度</div>
			<div field="ratioMargin" headerAlign="center" allowSort="true" >保证金比例</div>
			<div field="" headerAlign="center" allowSort="true" >审批权限</div>
			<div field="" headerAlign="center" allowSort="true" >新增或续借</div>
			<div field="" headerAlign="center" allowSort="true" >评审人</div>
			<div field="policyDecisionNum" headerAlign="center" allowSort="true" >审批人</div>
			<div field="yearRate" headerAlign="center" allowSort="true" >利率</div>
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