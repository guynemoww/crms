<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-5-5 13:16:44
  - Description:
-->
<head>
<title>拒贷信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.myBank.myBank.refuseList" class="nui-hidden" />
	<input name="item.partyId" value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="20">
	    <div property="columns">
	    	<div type="indexcolumn">序号</div>
	        <div field="partyName" allowSort="true" >客户名称</div>                
	        <div field="applyDate" allowSort="true" dateformat="yyyy-MM-dd" >申请日期</div> 
	        <div field="creditAmount" allowSort="true" >申请金额</div> 
	        <div field="creditTerm" allowSort="true" >申请期限</div> 
	        <div field="loanUse" allowSort="true" >贷款用途</div> 
	        <div field="validDate" allowSort="true" >拒绝日期</div> 
	        <div field="remark" allowSort="true" dictTypeId="" >拒绝原因</div> 
	        <div field="" allowSort="true" >备注</div> 
	     </div>
	</div>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	
	function init() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    init();
</script>
	
	

</body>
</html>