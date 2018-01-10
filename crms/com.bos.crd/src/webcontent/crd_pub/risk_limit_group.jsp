<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-25 18:35:01
  - Description:
  	限额添加限额信息
  	配置于菜单限额管理中
-->
<head>
<title>品种组管理</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label>组名：</label> 
			<input name="map.groupName" id="map.groupName" required="false" class="nui-textbox nui-form-input" /> 
			<label></label>
			<a class="nui-button"onclick="query">搜索</a>
		</div>
	</div>
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<input id="limitInfo.limitId" class="nui-hidden nui-form-input" name ="limitInfo.limitId"/>
		<a class="nui-button" id="" iconCls="icon-add" onclick="add()">添加</a>
		<a class="nui-button" id="" iconCls="icon-remove" onclick="del()">删除</a>
	    <a class="nui-button" id="" iconCls="icon-node" onclick="view()">查看</a>
	</div>
	<div id="datagrid" class="nui-datagrid" sortMode="client"
	    url="com.bos.crdPub.riskLimitGroup.getGroups.biz.ext" dataField="riskGroups"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>             
	        <div field="GROUP_NAME" allowSort="true" width="" headerAlign="center">组名</div>
	        <div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">创建人</div>
	         <div field="ORG_NUM" allowSort="true" width="" headerAlign="center"  dictTypeId="org">创建机构</div>
	    </div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("datagrid");
	query();
	function query(){
		var o = form.getData();
		grid.load({"map.groupName":o.map.groupName});
	}
	function add(){
		nui.open({
			url:nui.context + "/crd/crd_pub/risk_limit_group_add.jsp",
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            	grid.reload();
            	top.bizConWin = this;
            }
		});
	}
	function del(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔组信息!");
			return false;
		}
		var json = nui.encode({"group":{"id":row.ID}});
    	$.ajax({
            url: "com.bos.crdPub.riskLimitGroup.delGroups.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.msg){
            		alert(mydata.msg);
            		return;
            	}else{
            		alert("删除成功");
					query();
				}
			}
		});
	}
	function view(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔组信息!");
			return false;
		}
		nui.open({
			url:nui.context + "/crd/crd_pub/risk_limit_group_view.jsp?id="+row.ID,
			showMaxButton:true,
			title:"提示：可点击最大化按钮放大此窗口",
			width:"800",
            height:"500",
            ondestroy: function(e) {
            }
		});
	}
	
</script>
</body>
</html>