<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>对公授信客户预警跟踪台账</title>
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
	<strong >对公授信客户预警跟踪台账</strong >
	<div id="grid1" class="nui-datagrid" style="width:3130px;height:auto" 
		url="com.bos.pub.standingbook.bizinfo.limitdetailinfo.biz.ext"
		dataField="limitdetailinfos"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="orgNum" headerAlign="center" allowSort="true" width="100">经营单位</div>
			<div field="" headerAlign="center" allowSort="true" width="100">经办行</div>
			<div field="partyName" headerAlign="center" allowSort="true" width="100">借款人名称</div>
			<div header="预警情况" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">首次预警时间</div>
                <div field="" headerAlign="center" allowSort="true" width="100">预警信号序号</div>
                <div field="" headerAlign="center" allowSort="true" width="100">预警原因（针对信号序号逐项详述）</div>
                <div field="" headerAlign="center" allowSort="true" width="100">本月预警级别</div>
                <div field="" headerAlign="center" allowSort="true" width="100">上月预警级别</div>
                <div field="" headerAlign="center" allowSort="true" width="100">预警级别调整原因</div>
                </div>
            </div>
			<div header="授信余额" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">本月表内余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">本月表外余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">本月合计余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">较上月增减</div>
                </div>
            </div>
            <div header="敞口余额" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">本月表内余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">本月表外余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">本月合计余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">较上月增减</div>
                </div>
            </div>
            <div header="借款人基本情况" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">借款日</div>
                <div field="" headerAlign="center" allowSort="true" width="100">到期日</div>
                <div field="" headerAlign="center" allowSort="true" width="100">担保方式</div>
                <div field="" headerAlign="center" allowSort="true" width="100">担保人名称</div>
                <div field="" headerAlign="center" allowSort="true" width="100">抵质押率</div>
                <div field="" headerAlign="center" allowSort="true" width="100">行业（大类）</div>
                <div field="" headerAlign="center" allowSort="true" width="100">行业（细分）</div>
                <div field="" headerAlign="center" allowSort="true" width="100">本月五级分类</div>
                <div field="" headerAlign="center" allowSort="true" width="100">上月五级分类</div>
                </div>
            </div>
            <div header="预案情况" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">预案</div>
                <div field="" headerAlign="center" allowSort="true" width="100">预案执行/调整情况</div>
                </div>
            </div>
            <div header="相关责任人" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">客户经理</div>
                <div field="" headerAlign="center" allowSort="true" width="100">检查监测人员</div>
                </div>
            </div>
			<div field="" headerAlign="center" allowSort="true" width="100">备注</div>
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