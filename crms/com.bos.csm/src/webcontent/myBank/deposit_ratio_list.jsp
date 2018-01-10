<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-5-5 13:16:44
  - Description:
-->
<head>
<title>我行存贷比信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.myBank.myBank.guaranteeList" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="20">
	    <div property="columns">
	       <div type="indexcolumn">序号</div>
	        <div field="partyName" allowSort="true" >是否首贷户</div>                
	        <div field="partyNum" allowSort="true" >币种</div> 
	        <div field="partyNum" allowSort="true" >日均活期存款</div> 
	        <div field="partyNum" allowSort="true" >日均定期存款</div> 
	        <div field="partyNum" allowSort="true" >日均贷款</div> 
	        <div field="partyNum" allowSort="true" >存贷比</div> 
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