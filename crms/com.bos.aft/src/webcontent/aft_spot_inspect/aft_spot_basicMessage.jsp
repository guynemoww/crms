<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>基本信息</title>
</head>
<body>
		<div id="datagrid1" class="nui-datagrid" showPager="false" dataField="credits"
	        url="com.bos.aft.aft_spot_inspect.queryCreditMessage.biz.ext" editNextOnEnterKey="true" style="margin-top:20px;" >
	        <div property="columns"> 
	       		 <div field="productType"  headerAlign="center">授信品种</div>      
			     <div field="availableExposure"  headerAlign="center">授信余额</div>                              
	             <div field=""  headerAlign="center">是否便捷贷业务</div>
	            <div field="guarantyType"  headerAlign="center">担保方式 </div> 
	            <div field=""  headerAlign="center">保证人</div>
	        </div>
	     </div>	
	<script type="text/javascript">
		nui.parse();
		var param=<%=request.getParameter("param") %>;
		var grid = nui.get("datagrid1");
	
       grid.load({"partyId":param.partyId});
		
	</script>
</body>
</html>