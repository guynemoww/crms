<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>集团客户信息台账</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<strong >集团客户信息台账</strong >
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<table border="0" cellpadding="1" cellspacing="2">
    <tr>
        <td >填表日期：</td>
        <td >
            <input name="" class="nui-datepicker" />
        </td>
    </tr>
	</table>

<!--
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>-->
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.pub.standingbook.partyInfo.groupcompanyinfo.biz.ext"
		dataField="groupcompanyinfos"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >集团序号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >集团名称</div>
			<div field="registerNum" headerAlign="center" allowSort="true" >企业集团登记证编号</div>
			<div field="balanceSheetDate" headerAlign="center" allowSort="true" >资产负债表日期</div>
			<div field="totalAssets" headerAlign="center" allowSort="true" >资产总额(元)</div>
			<div field="liabilitySum" headerAlign="center" allowSort="true" >负债总额(元)</div>
			<div field="financeTypeCd" headerAlign="center" allowSort="true" dictTypeId="">报表类型</div>
			<div field="contryRegionCd" headerAlign="center" allowSort="true" dictTypeId="">国别代码</div>
			<div field="" headerAlign="center" allowSort="true" >母公司注册地址 </div>
			<div field="administrativeDivisionsCd" headerAlign="center" allowSort="true" dictTypeId="">母公司注册地行政区划代码</div>
			<div field="" headerAlign="center" allowSort="true" >母公司更新注册信息日期</div>
			<div field="" headerAlign="center" allowSort="true" >国内办公地址</div>
			<div field="" headerAlign="center" allowSort="true" dictTypeId="" >国内办公地址行政区划代码</div>
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