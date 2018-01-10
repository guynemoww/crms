<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>集团关联授信业务台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<!--<div class="nui-dynpanel" columns="6">
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
	</div>-->
	<strong >集团关联授信业务台账</strong >
	<div id="grid1" class="nui-datagrid" style="width:2950px;height:auto" 
		url="com.bos.pub.standingbook.bizinfo.limitdetailinfo.biz.ext"
		dataField="limitdetailinfos"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div header="企业基本信息" headerAlign="center">
                <div property="columns">
				<div type="indexcolumn" width="50">集团序号</div>
                <div field="" headerAlign="center" allowSort="true" width="100">集团名称</div>
                <div field="" headerAlign="center" allowSort="true" width="100">最终控制人</div>
                <div field="" headerAlign="center" allowSort="true" width="100">国别代码</div>
                <div field="" headerAlign="center" allowSort="true" width="100">成员名称</div>
                <div field="" headerAlign="center" allowSort="true" width="100">成员代码</div>
                <div field="" headerAlign="center" allowSort="true" width="100">成员类型</div>
                <div field="" headerAlign="center" allowSort="true" width="100">母公司标识</div>
                <div field="" headerAlign="center" allowSort="true" width="100">企业性质</div>
                <div field="" headerAlign="center" allowSort="true" width="100">企业规模</div>
                <div field="" headerAlign="center" allowSort="true" width="100">行业</div>
                </div>
            </div>
			<div header="授信业务信息" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">发生类型</div>
                <div field="" headerAlign="center" allowSort="true" width="100">是否权限内自行审批</div>
                <div field="" headerAlign="center" allowSort="true" width="100">授信金额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">授信敞口</div>
                <div field="" headerAlign="center" allowSort="true" width="100">授信余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">授信敞口余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">其中：贷款余额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">利率上浮比率</div>
                <div field="" headerAlign="center" allowSort="true" width="100">到期日</div>
                </div>
            </div>
            <div header="担保信息" headerAlign="center">
                <div property="columns">
                <div field="" headerAlign="center" allowSort="true" width="100">保证金金额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">存单质押担保金额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">信用方式金额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">保证担保金额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">其中：关联保证担保金额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">抵、质押担保金额</div>
                <div field="" headerAlign="center" allowSort="true" width="100">抵、质押物内容</div>
                <div field="" headerAlign="center" allowSort="true" width="100">抵、质押物总价值</div>
                </div>
            </div>
			<div field="" headerAlign="center" allowSort="true" width="100">经办行</div>
			<div field="" headerAlign="center" allowSort="true" width="100">主办行</div>
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