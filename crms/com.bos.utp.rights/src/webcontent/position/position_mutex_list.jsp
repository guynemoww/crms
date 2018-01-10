<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2015-7-31
  - Description: 岗位互斥管理页面
-->
<head>
<title>岗位互斥管理</title>
</head>
<body>
<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button"  iconCls="icon-add" onclick="addMutex()">新增</a>
		<a class="nui-button"  iconCls="icon-remove" onclick="delMutex()">删除</a>
	</div>
</div>	
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.utp.rights.positionManager.queryPositionMutex.biz.ext" dataField="items"
	    multiSelect="true"  emptyText="没有查到数据" allowResize="true" 
	    allowCellEdit="false" allowCellSelect="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	<div property="columns">
		<div type="checkcolumn"></div>
		<div field="toMutexPosicode"   headerAlign="center" allowSort="true">互斥岗位编码</div>
		<div field="toMutexPosiname"   headerAlign="center" allowSort="true">互斥岗位名称</div>
		<div field="createOrgNum"   headerAlign="center" allowSort="true" dicttypeid="org">创建机构</div>
		<div field="createUserNum"   headerAlign="center" allowSort="true" dicttypeid="user" >创建人</div>
		<div field="createDate"   headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	
	var posicode = '<%=request.getParameter("posicode") %>';
	var grid = nui.get("datagrid1");
	grid.load({"posicode":posicode});
    
    //新增互斥岗位
	function addMutex(){
		nui.open({
			url:"<%= request.getContextPath() %>/utp/rights/position/position_mutex_add.jsp?posicode="+posicode,
			title:'互斥岗位新增',
			width:600,
			height:400,
			ondestroy:function(action){
					grid.reload();
			}
		});
	}
	
	//删除互斥岗位
	function delMutex(){
		var rows = grid.getSelecteds();
		
		if(null==rows || ''==rows){
		
			nui.alert("请选择记录！");
			return;
		}
		
	  nui.confirm("确定删除选中记录？", "系统提示", function(action){
			if(action=="ok"){
				git.mask();
				var json = nui.encode({item:rows});
				$.ajax({
					url:"com.bos.utp.rights.positionManager.deletePositionMutex.biz.ext",
					type:'POST',
					data:json,
					cache: false,
					contentType:'text/json',
					success:function(text){
						if(text.retcode == 1){
							grid.reload();
						}else{
							nui.alert("互斥岗位删除失败！", "系统提示");
						}
						git.unmask();
					}
				});
			}
		});
	}
</script>