<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-24 16:27:30
  - Description:
-->
<head>
<title>债项级信息</title>
</head>
<body>
<div style="padding:6px;">
	<label>债项信息：</label>  
	<div id="det_grid" class="nui-datagrid" style="width:100%;height:auto;overflow:hidden;" 
	    url="com.bos.aft.dailyInspect.queryAllItems.biz.ext"  dataField="breakCusArtInItem">
	    
	    <div property="columns"> 
	        <div field="partyNum"  headerAlign="center" allowSort="true">客户编号</div>                
	        <div field="partyName" allowSort="true"  headerAlign="center">客户名称</div>            
	        <div field="loanNum"  allowSort="true">借据号</div>
	        <div field="loanAmt"  allowSort="true" >借据金额</div>                                    
	        <div field="loanBalance"  headerAlign="center"  allowSort="true">借据余额</div>                
	        <div field="overdueDays"  headerAlign="center"  allowSort="true">逾期天数</div>                
	        <div field="businesstype"  headerAlign="center"  allowSort="true">业务品种</div>                
	        <div field="defaultCondition" headerAlign="center" dictTypeId="BREAK_CONDITION" allowSort="true">违约条件</div>                
	        <div field="defaultReason"  headerAlign="center"  allowSort="true">违约原因</div>                
	        <div field="defaultDt"  headerAlign="center"  allowSort="true" dateFormat="yyyy-MM-dd">违约日期</div>                
	    </div>
	</div>   
<script type="text/javascript">
	nui.parse();
	var param=<%=request.getParameter("param") %>;
	var grid = nui.get("det_grid");
	git.mask();
	function query(){
       grid.load({"partyId":param.partyId});
       git.unmask();
	}
	query();
</script>
</body>
</html>