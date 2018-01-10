<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-10 13:17:36
  - Description:
-->
<head>
<title>集团成员变更信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.company.company.memberChangeList" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="20">
	    <div property="columns">
	        <!-- 
	        <div field="partyNum" allowSort="true" width="20%" headerAlign="center" >集团成员编号</div> 
	         -->               
	        <div field="partyName" allowSort="true" width="" headerAlign="center" >集团成员名称</div> 
	        <div field="actionType" allowSort="true" width="" headerAlign="center" dictTypeId="XD_JTKH6001">变更方式</div>  
	        <!-- 
	        <div field="changeReason" allowSort="true" width="" headerAlign="center">变更原因</div>  	
	         -->
	        <div field="operateUserId" allowSort="true" width="" headerAlign="center" dictTypeId="user">操作人员</div>  
	        <div field="changeDate" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">变更日期</div> 	
	        <%--<div field="createTime" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">操作时间</div>  --%>
	     </div>
	</div>

<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
		
	function init() {
	  if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
     	git.unmask();
     }
     init();
	
</script>


</body>
</html>