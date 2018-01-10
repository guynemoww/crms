<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-5-5 13:16:44
  - Description:
-->
<head>
<title>违约记录</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.myBank.myBank.defaultList" class="nui-hidden" />
	<input name="item.partyId" value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="20">
	    <div property="columns">
	    	<div type="indexcolumn">序号</div>
	        <div field="partyName" allowSort="true" >客户名称</div>                
	        <div field="contractNum" allowSort="true" >合同编号</div> 
	        <div field="summaryNum" allowSort="true" >借据编号</div> 
	        <div field="partyNum" allowSort="true" >逾期/垫款金额</div> 
	        <div field="partyNum" allowSort="true" >逾期/垫款日期</div> 
	        <div field="partyNum" allowSort="true" >归还日期</div> 
	        <div field="partyNum" allowSort="true" >备注</div> 
	        <div field="partyNum" allowSort="true" >经办人</div> 
	        <div field="partyNum" allowSort="true" >经办机构</div> 
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