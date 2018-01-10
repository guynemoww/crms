<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zhufaying
  - Date: 2014-03-28 15:52:21
  - Description:
-->
<head>
<title>低风险业务</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<div id="grid" class="nui-datagrid"   sortMode="client"
		    url="com.bos.bizApply.bizApply.getApplyTzBizInfo.biz.ext" dataField="bizInfos"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
	    	<div property="columns">
		        <div type="indexcolumn" width="50px;">序号</div>
		        <div field="PARTY_NAME" allowSort="true" width="" headerAlign="center">客户名称</div>
		        <div field="APPROVAL_NUM" allowSort="true" width="" headerAlign="center">批复编号</div>
		        <div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dataType="currencd" dictTypeId="CD000001">币种</div>
		        <div field="CREDIT_AMOUNT" allowSort="true" width="" headerAlign="center">批复金额</div>
		        <div field="USED_AMT" allowSort="true" width="" headerAlign="center">已用金额</div>
		        <div field="AVAILABLE_AMT" allowSort="true" width="" headerAlign="center">可用金额</div>
		        <div field="APPROVE_CONCLUSION" allowSort="true" width="" headerAlign="center"  dictTypeId="XD_WFCD0002">审批结论</div>
		        <div field="VALID_DATE" allowSort="true" width="" headerAlign="center" >审批日期</div>
		        <div field="END_DATE" allowSort="true" width="" headerAlign="center">到期日期</div>
	     	</div>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //批复列表
	query();
	function query(){
		var o = form.getData();
		var json = {
			"partyId":"<%=request.getParameter("partyId")%>",
			"approveConclusion":"1",//同意
			"applyModeType":"02"//低风险业务
		}
		grid.load(json);
	}
	
</script>
</body>
</html>