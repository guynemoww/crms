<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2014-08-25 14:12:10
  - Description:预警信号提示
-->
<head>
<title>查看预警提示详情</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" sortMode="client"
	url="com.bos.pub.Remind.queryWarningDetail.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" >
	<div property="columns">
		<div type="indexcolumn">序号</div>
		<div field="CSM_SIGNAL_ID" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true" width="40%;">预警信号</div>
		<div field="csmwarningtypeid" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0003">预警信号类别</div>
		<div field="SIGNAL_SOURCE_CD" headerAlign="center" allowSort="true"  dictTypeId="XD_YJCD0001">预警信号来源</div>
		<div field="SIGNAL_STATUS_CD" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002">预警信号状态</div>
		<div field="SIGNAL_STATE" headerAlign="center" allowSort="true" >预警事项说明</div>
	</div>
</div> 
<div class="nui-toolbar" style="border-bottom:0;text-align:right;padding-top:5px;padding-bottom:5px;">
	<a class="nui-button" style="margin-right:5px;height:21px;" onclick="CloseWindow('ok')"> 关闭 </a>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid1");
	var partyId="<%=request.getParameter("partyId") %>";
	grid.load({"partyId":partyId});
	
	
</script>
</body>
</html>