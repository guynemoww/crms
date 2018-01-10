<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-07-03 09:05:14
  - Description:小贷中心员工列表
-->
<head>
<title>小贷中心员工列表</title>
</head>
<body>

<div id="form1" class="nui-form" style="overflow:hidden;" >
		<div class="nui-dynpanel" columns="4">
			<label>用户名称：</label>
			<input id="item.userName" name="item.userName" class="nui-textbox nui-form-input"  />
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
</div>

<div class="nui-toolbar" style="border-bottom:0;padding:0px;">
    <a class="nui-button" onclick="saveData()">选中</a>            
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" 
url="com.bos.csm.pub.ibatis.getItem.biz.ext" idField="id"
allowResize="true" pageSize="10"  dataField="items" multiSelect="false" sizeList="[10,20,50,100]">

<div property="columns"> 
    <div type="checkcolumn">选择</div>
    <div field="userId" allowResize="false"  headerAlign="center" allowSort="true" >用户编号</div>
    <div field="userName" allowResize="false"  headerAlign="center" allowSort="true" >用户名称</div>
    <div field="orgname" allowResize="false"  headerAlign="center" allowSort="true" >机构名称</div>
</div>
</div>
</body>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1"); 
	//搜索事件
	function search() {       
        var o = form.getData();
        o.sqlName="com.bos.pub.grantManage.grant.select_grans_employee_id";
        grid.load(o);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
	function getData(){
		
		var row = grid.getSelected();
		return row;
	}
	
	//保存事件
	function saveData() {
         var row = grid.getSelected();
      
        if (row) {
            CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    }
	
</script>
</html>