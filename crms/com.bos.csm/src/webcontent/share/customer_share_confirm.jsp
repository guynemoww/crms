<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-25
  - Description:

-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@include file="/common/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="sqlName"  class="nui-hidden nui-form-input" value="com.bos.csm.share.share.shareList"/>
	<input name="item.id"  class="nui-hidden nui-form-input" value="<%=request.getParameter("bizId") %>"/>
</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.csm.pub.ibatis.getItem.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="receiveShareOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">申请共享机构</div>
		<div field="receiveShareUserNum" headerAlign="center" allowSort="true" dictTypeId="user">申请共享客户经理</div>
		<div field="remark" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD322830">共享原因</div>
		<div field="appStatus" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD7493">审批状态</div>
		<div field="operaterOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">操作机构</div>
		<div field="operaterUserNum" headerAlign="center" allowSort="true" dictTypeId="user">操作人</div>
		<div field="createDate" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">共享时间</div>
		</div>
	</div>
</div>
</div>			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var bizId = "<%=request.getParameter("bizId") %>";
	var wflow = "<%=request.getParameter("wflow") %>";
	
    function search() {
    	git.mask();
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask();
        });
    }
    search();
    
    function reset(){
		form.reset();
	}

	</script>
</body>
</html>
