<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-25 17:12:23
  - Description:
-->
<head>
<title>限额管理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label>限额机构：</label>
			<input name="orgName" id="orgName" required="false" class="nui-textbox nui-form-input" /> 
			<label>限额组：</label>
			<input name="groupName" id="groupName" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>
	</div>
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		
		<a class="nui-button" id="" iconCls="icon-add" onclick="add()">添加</a>
		<a class="nui-button" id="" iconCls="icon-remove" onclick="del()">删除</a>
	    <a class="nui-button" id="" iconCls="icon-edit" onclick="update()">编辑</a>
	    <a class="nui-button" id="" iconCls="icon-node" onclick="view()">查看</a>
	</div>
	<div id="datagrid" class="nui-datagrid" sortMode="client"
	    url="com.bos.crdPub.riskLimitInfo.getRiskLimitInfo.biz.ext" dataField="limitInfos"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>             
	        <div field="LIMIT_ORG" allowSort="true" width=""  dictTypeId="org">限额机构</div> 
	        <div field="GROUP_NAME" allowSort="true" width="" headerAlign="center">限额组</div>
	        <div field="LIMIT_AMT" allowSort="true" width="" headerAlign="center">分配额度</div>
	       <%-- <div field="AVAILABLE_AMT" allowSort="true" width="" headerAlign="center">可用额度</div>--%>
	        <div field="START_DATE" allowSort="true" width="" headerAlign="center">起始日期</div>
	        <div field="END_DATE" allowSort="true" width="" headerAlign="center">到期日期</div>
	    </div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("datagrid");
	initPage();
	function initPage(){
		var o = form.getData();
		grid.load({"limitOrg":o.limitOrg});
	}
	function add(){
		nui.open({
			url:nui.context+"/crd/crd_pub/risk_limit_info_add.jsp",
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	grid.reload();
            	top.bizConWin = this;
            }
		})
	}
	function del(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔限额信息!");
			return false;
		}
		var json = nui.encode({"limitId":row.LIMIT_ID});
   		$.ajax({
	        url: "com.bos.crdPub.riskLimitInfo.delRiskLimit.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	alert("删除成功！");
	        	initPage();
	        }}
	    );
	}
	function update(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔限额信息!");
			return false;
		}
		nui.open({
			url:nui.context+"/crd/crd_pub/risk_limit_info_view.jsp?limitId="+row.LIMIT_ID+"&v=1",
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	initPage();
            }
		})
	}
	function view(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔限额信息!");
			return false;
		}
		nui.open({
			url:nui.context+"/crd/crd_pub/risk_limit_info_view.jsp?limitId="+row.LIMIT_ID+"&v=0",
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	initPage();
            }
		})
	}
</script>
</body>
</html>