<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>客户业务信息台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>借款人名称：</label>
		<input name="limitdetailinfo.partyNum" required="false" class="nui-textbox nui-form-input"  />
		<label>借据编号：</label>
		<input name="" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
	<strong >客户业务信息台账</strong >
	<div id="grid1" class="nui-datagrid" style="width:3000px;height:auto" 
		url="com.bos.pub.standingbook.bizinfo.limitdetailinfo.biz.ext"
		dataField="limitdetailinfos"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="" headerAlign="center" allowSort="true" >借据编号</div>
			<div field="" headerAlign="center" allowSort="true" >上一级机构名称</div>
			<div field="" headerAlign="center" allowSort="true" >入账机构</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="product">借款人名称</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="CD000001">借款人组织机构代码</div>
			<div field="" headerAlign="center" allowSort="true" >主担保人名称</div>
			<div field="" headerAlign="center" allowSort="true" >主担保人组织机构代码</div>
			<div field="" headerAlign="center" allowSort="true" >担保方式</div>
			<div field="" headerAlign="center" allowSort="true" >借据币种</div>
			<div field="" headerAlign="center" allowSort="true" >总余额</div>
			<div field="" headerAlign="center" allowSort="true" >借据正常余额（当日）</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">贷款投向小类名称</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">借款人企业规模名称</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">合同业务品种</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">文本合同编号</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">合同序列号</div>
			<div field="" headerAlign="center" allowSort="true" >借据科目号</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">借据本月分类结果</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">借据执行年利率</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">执行利率浮动形式</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">执行利率浮动值</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">借款期限</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1020">合同币种</div>
			<div field="" headerAlign="center" allowSort="true" >合同金额</div>
			<div field="" headerAlign="center" allowSort="true" >合同起始日期</div>
			<div field="" headerAlign="center" allowSort="true" >合同到期日期</div>
			
			<div field="" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="" headerAlign="center" allowSort="true" >授信额度</div>
			<div field="" headerAlign="center" allowSort="true" >已投放金额</div>
			<div field="" headerAlign="center" allowSort="true" >币种</div>
			<div field="" headerAlign="center" allowSort="true" >放款日</div>
			<div field="" headerAlign="center" allowSort="true" >到期日</div>
			<div field="" headerAlign="center" allowSort="true" >展期日</div>
			<div field="" headerAlign="center" allowSort="true" >利率</div>
			<div field="" headerAlign="center" allowSort="true" >借据号</div>
			<div field="" headerAlign="center" allowSort="true" >合同号</div>
			<div field="" headerAlign="center" allowSort="true" >担保人</div>
			<div field="" headerAlign="center" allowSort="true" >担保物</div>
			<div field="" headerAlign="center" allowSort="true" >抵质押物</div>
			<div field="" headerAlign="center" allowSort="true" >行业</div>
			<div field="" headerAlign="center" allowSort="true" >地区</div>
			<div field="" headerAlign="center" allowSort="true" >贷款投向</div>
			<div field="" headerAlign="center" allowSort="true" >所属经营机构</div>
			<div field="" headerAlign="center" allowSort="true" >逾期情况</div>
			<div field="" headerAlign="center" allowSort="true" >五级分类</div>
			<div field="" headerAlign="center" allowSort="true" >企业规模</div>
			<div field="" headerAlign="center" allowSort="true" >企业性质</div>
			<div field="" headerAlign="center" allowSort="true" >主办客户经理</div>
			<div field="" headerAlign="center" allowSort="true" >审批人</div>
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