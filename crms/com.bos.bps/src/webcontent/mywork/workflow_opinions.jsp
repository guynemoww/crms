<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): lijianfei
  - Date: 2014-03-26 09:30:42
  - Description:
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程过程意见</title>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input class="nui-hidden" id="processInstId" name="processInstId" value="<%=request.getParameter("processInstId")%>"/>
</div>
<div id="panel1" class="nui-panel" title="过程意见" iconCls="icon-add" style="width:100%;height:200px;" 
    showToolbar="true" showCollapseButton="true" showFooter="false" allowResize="true">
    
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.bps.util.TbWfmWorkItemInstance.queryTbWfmWorkitemByProcessId.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="5">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>
	        <div field="orgName" allowSort="true" width="" headerAlign="center">操作机构</div> 
	        <div field="postName" allowSort="true" width="" headerAlign="center">操作岗</div> 
	        <div field="userName" allowSort="true" width="" headerAlign="center">操作人</div>   
	        <div field="nextOrgName" allowSort="true" width="" headerAlign="center">下一操作机构</div>
	        <div field="nextPostName" allowSort="true" width="" headerAlign="center">下一操作岗</div> 
	        <div field="nextUsersName" allowSort="true" width="" headerAlign="center">下一操作人</div> 
	        <%--<div field="receiveTime" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">开始时间</div>--%>
	        <div field="finishTime" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">操作时间</div>
	        <div field="conclusion" allowSort="true" width="" renderer="onConclusion" headerAlign="center">意见结论</div> 
	        <div field="opinion" allowSort="true" width="" headerAlign="center">意见</div> 
	        <div field="status" allowSort="true" width="" renderer="onStatus" headerAlign="center">状态</div> 
	     </div>
	</div>
	
</div>

<div id="panel2" class="nui-panel" title="流程图" iconCls="icon-add" style="width:100%;height:330px;" 
    showToolbar="true" showCollapseButton="true" showFooter="false" allowResize="true">
    
<iframe name="mainFrame" src="<%=contextPath%>/bps/mywork/work_flow_graph.jsp?processInstId=<%=request.getParameter("processInstId")%>" width="100%" height="100%" frameborder="0">
</iframe>

</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var o = form.getData();
	var grid = nui.get("datagrid1");
	grid.load(o);
	
	function onConclusion(e) {
        return nui.getDictText("XD_WFCD0002",e.value);
    }
    function onStatus(e){
    	return nui.getDictText("XD_WFCD0003",e.value);
    }
</script>
</html>