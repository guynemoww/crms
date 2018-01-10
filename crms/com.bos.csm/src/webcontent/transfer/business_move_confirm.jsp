<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 陈川
  - Date: 2015-06-10
  - Description:

-->
<head>
<%@include file="/common/nui/common.jsp" %>
<%@include file="/common/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="sqlName"  class="nui-hidden nui-form-input" value="com.bos.csm.transfer.transfer.businessMoveHistory"/>
	<input name="item.id"  class="nui-hidden nui-form-input" value="<%=request.getParameter("bizId") %>"/>
</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.csm.pub.ibatis.getItem.biz.ext"
	dataField="items"allowAlternating="true" 
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div field="approvalNum" headerAlign="center" allowSort="true">批复编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="originalOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">原所在机构</div>
		<div field="originalUserNum" headerAlign="center" allowSort="true" dictTypeId="user">原客户经理</div>
		<div field="orgId" headerAlign="center" allowSort="true" dictTypeId="org">变更后所在机构</div>
		<div field="userId" headerAlign="center" allowSort="true" dictTypeId="user">变更后客户经理</div>
		<div field="transPerson" headerAlign="center" allowSort="true" >移交原因</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">移交时间</div>
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
