<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): g_Yiy
  - Date: 2015-01-05 14:21:29
  - Description:
-->
<head>
<title>处置方案</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
待确认财务回收
		<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
			 url="com.bos.npl.assets.AssetsPerforming.getAssetsPerforming.biz.ext" dataField="tbNplAssetsPerformings" allowResize="false" 
			 showReloadButton="false" multiSelect="true" pageSize="5" sortMode="client" 
			 showPager="true" showFooter="false" virtualScroll="true">	 
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="aa" headerAlign="center" type="indexcolumn"  allowSort="false">序号</div>
				<div field="partyNum" headerAlign="center" allowSort="false">客户编号</div>
				<div field="partyName" headerAlign="center" allowSort="false">客户名称</div>
				<div field="partyType" headerAlign="center" allowSort="false">客户类型</div>
				<div field="creditAmt" headerAlign="center" allowSort="false">授信额度</div>
				<div field="balanceAmt" headerAlign="center" allowSort="false">授信余额</div>
				<div field="riskClassify" headerAlign="center" allowSort="false">最新分类</div>
				<div field="holdDate" headerAlign="center" allowSort="false">认定时间</div>
				
				<div field="overdueInterest" headerAlign="center" allowSort="false">表内外应收未收利息</div>
				<div field="overduedays" headerAlign="center" allowSort="false">逾期或垫款天数</div>
				<div field="intoDate" headerAlign="center" allowSort="false">转入保全时间</div>
				<div field="clearDate" headerAlign="center" allowSort="false">结清日期</div>
				<div field="responsibleUserNum" headerAlign="center" allowSort="false">保全责任人</div>
				<div field="responsibleOrgNum" headerAlign="center" allowSort="false">保全责任机构</div>
				
				<div field="orgNum" headerAlign="center" allowSort="false" dictTypeId="org">经办机构</div>
				<div field="userNum" headerAlign="center" allowSort="false" dictTypeId="user">客户经理</div>
				<div field="assetsStatus" headerAlign="center" allowSort="false">状态</div>
			</div>
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="cre">回收</a>
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="view">查看</a>
		</div>
已确认财务回收		
		<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
			 url="com.bos.npl.assets.AssetsPerforming.getAssetsPerforming.biz.ext" dataField="tbNplAssetsPerformings" allowResize="false" 
			 showReloadButton="false" multiSelect="true" pageSize="5" sortMode="client" 
			 showPager="true" showFooter="false" virtualScroll="true">	 
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="aa" headerAlign="center" type="indexcolumn"  allowSort="false">序号</div>
				<div field="partyNum" headerAlign="center" allowSort="false">客户编号</div>
				<div field="partyName" headerAlign="center" allowSort="false">客户名称</div>
				<div field="partyType" headerAlign="center" allowSort="false">客户类型</div>
				<div field="creditAmt" headerAlign="center" allowSort="false">授信额度</div>
				<div field="balanceAmt" headerAlign="center" allowSort="false">授信余额</div>
				<div field="riskClassify" headerAlign="center" allowSort="false">最新分类</div>
				<div field="holdDate" headerAlign="center" allowSort="false">认定时间</div>
				
				<div field="overdueInterest" headerAlign="center" allowSort="false">表内外应收未收利息</div>
				<div field="overduedays" headerAlign="center" allowSort="false">逾期或垫款天数</div>
				<div field="intoDate" headerAlign="center" allowSort="false">转入保全时间</div>
				<div field="clearDate" headerAlign="center" allowSort="false">结清日期</div>
				<div field="responsibleUserNum" headerAlign="center" allowSort="false">保全责任人</div>
				<div field="responsibleOrgNum" headerAlign="center" allowSort="false">保全责任机构</div>
				
				<div field="orgNum" headerAlign="center" allowSort="false" dictTypeId="org">经办机构</div>
				<div field="userNum" headerAlign="center" allowSort="false" dictTypeId="user">客户经理</div>
				<div field="assetsStatus" headerAlign="center" allowSort="false">状态</div>
			</div>
		</div>
</body>
</html>