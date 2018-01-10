<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): lvjianhao
  - Date: 20134-12-19 00:02:17
  - Description:
-->
<head>
<title>企业规模自动重计算</title>
</head>
<body>
<div class="nui-dynpanel" columns="4" id="show">
	<label>总计算量:</label>
	<input id="count" name="count" value="0" required="true" class="nui-text" />
	<label>已执行到:</label>
	<input name="index" value="0" class="nui-text"  />
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:left">
	<a id = "add" class="nui-button" iconCls="icon-add" onclick="start" >开始执行</a>
</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"
	allowResize="true" showReloadButton="true" dataFieId="rowList"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="20" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
	        <div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="bridNum" headerAlign="center" allowSort="true" >年报数量</div>
			<div field="businessIncome" headerAlign="center" allowSort="true"  dataType="currency" >营业收入</div>
			<div field="TotalAssets" headerAlign="center" allowSort="true"  dataType="currency">资产总额</div>
			<div field="industrialTypeCd" headerAlign="center" allowSort="true" dictTypeCd="">行业大类</div>
			<div field="employeesNumber" headerAlign="center" allowSort="true">从业人数</div>
			<div field="fourzEnterpriseSizeCd" headerAlign="center" allowSort="true" dictTypeCd="" >统计专用企业规模（重计算前）</div>
			<div field="fourzEnterpriseSizeCd2" headerAlign="center" allowSort="true" dictTypeCd="" >统计专用企业规模（重计算后）</div>
		</div>
</div>

	
<script type="text/javascript">

    nui.parse();
    nui.parse();
	var grid = nui.get("grid1");
	var rowList = new Array();
	var index = 0;
	var al = null;
	
	function start(){
		git.mask();
		$.ajax({
	                url: "com.bos.csm.pub.enterpriseSize.enterpriseSize.biz.ext",
	                async:false,
		            type: 'POST',
		            data: null,
		            cache: false,
		            contentType:'text/json',
	                success: function (text) {
	                	git.unmask();
	                	alert("已经执行完毕共执行："+text.msg+"条数据");
	                	
	                }
	      });
	}
	
	
	

	 
	
</script>
</body>
</html>
