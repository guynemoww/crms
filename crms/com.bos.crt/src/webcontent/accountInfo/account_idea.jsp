<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-10-24 15:47:24
  - Description:
-->
<head>
<title>批复意见</title>
    <%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="form1" style="width:100%;height:auto" class="nui-form">
		<div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" 
		    borderStyle="border:0;">
		    <a class="nui-button" iconCls="icon-search" onclick="queryExcl()">打印</a>
		</div>	
		<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.accInfo.accInfo.getAccInfoIdeaList.biz.ext"
			dataField="items"   allowAlternating="true" multiSelect="false" showEmptyText="true"
	    	emptyText="没有查到数据" showReloadButton="false" allowResize="true"
	    	onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    	sizeList="[10,20,50,100]" pageSize="30">
			<div property="columns">
				<div field="performtime" headerAlign="center"  width="10%" height="50">批复时间</div>
				<div field="userName" headerAlign="center" width="4%" height="50">批复人</div>
				<div field="postName" headerAlign="center"  width="10%" height="auto">批复岗位</div>
				<div field="nextUsersName" headerAlign="center"  width="10%" height="auto">下一批复人</div>
				<div field="nextPostName" headerAlign="center" width="10%">下一批复岗位</div>
				<div field="conclusion" headerAlign="center" dictTypeId="XD_WFCD0002" width="4%">批复结论</div>
				<div field="opinion" headerAlign="center"  width="50%">批复意见</div>
			</div>
		</div>	
	</div> 	


	<script type="text/javascript">
    	nui.parse();
    	var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var ollprocessInstId;
    	function search() {
			var contractId = "<%=request.getParameter("contractId") %>";
			var json = {"contractId":contractId};	
	        grid.load(json);
	    }
	    search();
	    function reset() {
			form.reset();
		}
	   grid.on(
				"preload",
				function(e) {
					if (!e.data || e.data.length < 1) {
						return;
					}
					ollprocessInstId = e.data[0].PROCESSID;
				});   
	function queryExcl(){
		var json =nui.encode({"processInstId":ollprocessInstId,"printType":"SPLC"});
		$.ajax({
	        url: "com.bos.pay.LoanSummary.printWorkflow.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	if (text.swfPath) {
									nui
											.open({
												url : nui.context
														+ "/biz/biz_report/contract_view.jsp?filePath="
														+ text.swfPath,
												title : "贷款审批历史记录",
												width : 1000,
												height : 500,
												onload : function() {
												},
												ondestroy : function(action) {
													grid.reload();
												}
	
											});
								} else {
									alert("无打印信息!");
								}
	        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
	     });
	}
    </script>
</body>
</html>