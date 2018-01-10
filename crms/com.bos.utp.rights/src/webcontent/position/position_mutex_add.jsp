<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2015-7-31
  - Description: 岗位引入页面
-->
<head>
<title>岗位管理</title>
</head>
<body>
<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button"  iconCls="icon-add" onclick="save()">保存</a>
	</div>
</div>	
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.utp.rights.positionManager.queryPositionNoMutex.biz.ext" dataField="items"
	    multiSelect="true"  emptyText="没有查到数据" allowResize="true" 
	    allowCellEdit="false" allowCellSelect="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	<div property="columns">
		<div type="checkcolumn"></div>
		<div field="posicode"   headerAlign="center" allowSort="true">岗位编码</div>
		<div field="posiname"   headerAlign="center" allowSort="true">岗位名称</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var posicode = '<%=request.getParameter("posicode") %>';
	var grid = nui.get("datagrid1");
	grid.load({"posicode":posicode});
    
    function save(){
    
    	var rows = grid.getSelecteds();
    	
    	if(null==rows || ''==rows){
		
			nui.alert("请选择记录！");
			return;
		}
    	
		var json =nui.encode({"items":rows,"posicode":posicode});
		$.ajax({
			url:"com.bos.utp.rights.positionManager.addPositionMutex.biz.ext",
			type:'POST',
			data:json,
			cache: false,
			contentType:'text/json',
			success:function(text){
				if(text.retcode == 1){
					nui.alert("互斥岗位保存成功！");
					CloseWindow("ok");
				}else{
					nui.alert("互斥岗位保存失败！", "系统提示");
				}
			}
		});
    }
  
</script>