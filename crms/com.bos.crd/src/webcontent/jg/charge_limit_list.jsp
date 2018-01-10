<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>监管限额</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs">
<div title="监管限额管理">   <!-- tabs start -->
	<div class="nui-toolbar">
	    <a class="nui-button" iconCls="icon-edit" onclick="add" id="add">新增</a>
	    <a class="nui-button" iconCls="icon-edit" onclick="edit" id="edit">修改</a>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.crd.LimitMgr.queryChargeLimit.biz.ext" dataField="chargeLimits"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" 
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">序号</div>                
	        <div field="jgLimitType" allowSort="true" dictTypeId="XD_EDCD0005" headerAlign="center" autoEscape="false"> 监管限额类型</div> 
	       <%-- <div field="zbje" allowSort="true" dataType="currency"  headerAlign="center" autoEscape="false"> 净资本额</div>
	        <div field="jzdbl" allowSort="true" dataType="currency"  headerAlign="center" autoEscape="false"> 集中度比例</div> --%>
	        <div field="jzdxe" allowSort="true" dataType="currency"  headerAlign="center" autoEscape="false"> 监管限额金额</div> 
	        <div field="userNum" allowSort="true" dictTypeId="user" headerAlign="center" >经办人</div>
	        <div field="orgNum" allowSort="true" dictTypeId="org" headerAlign="center" >经办机构</div>       
	        <div field="createTime" allowSort="true" dateFormat="yyyy-MM-dd" headerAlign="center" >经办时间</div>      
	     </div>
	</div>
</div> <!-- tab1 end -->
</div>
<script type="text/javascript">
	nui.parse();
	
	var grid = nui.get("datagrid1");
	
	query();
	function query(){
		grid.load();
	}
	
	
	//增加监管限额
	function add(){
		nui.open({
            url: nui.context+"/crd/jg/charge_limit_edit.jsp",
            title: "监管限额限额管理", 
            width: 750, 
        	height: 300,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                query();
            }
        });
	}
	function edit(){
		var col = grid.getSelected();
		if(col){
			nui.open({
	            url: nui.context+"/crd/jg/charge_limit_edit.jsp?limitId="+col.limitId,
	            title: "监管限额限额管理", 
	            width: 750, 
	        	height: 300,
	        	allowResize:true,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                query();
	            }
	        });
		}else{
			alert("请先选择一条记录");
		}
	}
</script>
</body>
</html>