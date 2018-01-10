<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2013-11-12 11:29:31
  - Description:黑名单待办审批展示页
-->
<head>
<title>黑名单待办审批展示页</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:100%;height:auto;overflow:hidden;align:center">
	<input name="blacklist.processId" id="blacklist.processId" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99%;height:auto;"  sortMode="client"
	    url="com.bos.csm.blacklist.blacklist.queryBlackList.biz.ext" dataField="blacklists"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" 
	     onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns" >
	         <div type="indexcolumn">序号</div>
	        <div field="partyName" allowSort="true"  headerAlign="center" autoEscape="false">客户名称</div>
	        <div field="partyNum" allowSort="true" headerAlign="center" autoEscape="false">CRMS客户编号</div>
	        <div field="EcifPartyNum" allowSort="true" headerAlign="center" autoEscape="false">ECIF客户编号</div>
	        <div field="status" allowSort="true" headerAlign="center" dictTypeId="XD_KHCD0248" autoEscape="false">黑名单状态</div>
	        <div field="createTime" allowSort="true"  headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">纳入黑名单时间</div>
	        <div field="blackListReasonCd" allowSort="true" renderer="onGenderReasonCd" dictTypeId="CDKH0010" headerAlign="center" renderer="rendertype">纳入理由</div>
	     </div>
	 </div>
</center>
</body>
<script type="text/javascript">
	nui.parse();
	git.mask();
	//获取form对象
	var form = new nui.Form("#form1");
	//获取列表对象
	var grid = nui.get("datagrid1");
	//查询事件
	var processInstId = "<%=request.getParameter("processInstId")%>" ;
	
	function query(){//黑名单客户查询
		if (processInstId) {
			nui.get("blacklist.processId").setValue(processInstId);
	       	var data = form.getData(); //获取表单多个控件的数据
	        grid.load(data);
			
		}else{
			alert("未查询到对应的流程");
		}
		git.unmask();
		
	}
	query();
	
	
</script>


</html>