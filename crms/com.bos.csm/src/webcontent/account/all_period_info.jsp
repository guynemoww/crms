<%@page pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-08-08 14:48:46
  - Description:
-->
<head>
<title>统计信息</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <%@include file="/common/nui/common.jsp" %>
    
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 2000px; height: auto;">
		<div title="统计信息查询">
			<div id="form1">
					<input id="dueNum" name="dueNum" required="false" class="nui-hidden nui-form-input"/>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: auto; height: auto;" sortMode="client" url="com.bos.account.BusiTransferImplServiceService.allPeriodInfo.biz.ext"
			dataField="allloanInfos" allowResize="true" showReloadButton="false" 
			allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="DUE_NUM" headerAlign="center" allowSort="true" align="center" width="100px">借据编号</div>
					<div field="TOT_NUM_BASE" headerAlign="center" allowSort="true" align="center" width="100px">总期数</div>
					<div field="YQ" headerAlign="center" align="center" allowSort="true" width="100px">逾期期数</div>
					<div field="YYQ" headerAlign="center" align="center" allowSort="true" width="100px">已还期数</div>
					<div field="TOT_CURR_PERI" headerAlign="center" align="center" allowSort="true" width="100px">剩余期数</div>
					<div field="PAD_UP_PRN" headerAlign="center" align="center" allowSort="true" width="100px">已还本金</div>
					<div field="PAD_UP_ITR" headerAlign="center" align="center" allowSort="true" width="100px">已还利息</div>
					<div field="RCV_UP_PRN" headerAlign="center" allowSort="true" align="center" width="100px">逾期本金</div>
					<div field="RCV_UP_ITR" headerAlign="center" allowSort="true" align="center" width="100px">逾期利息</div>
					<div field="NOR_DVL_BAL" headerAlign="center" align="center" allowSort="true" width="100px">剩余本金</div>
					<div field="OFT_PRN_BAL" headerAlign="center" align="center" allowSort="true" width="100px">核销本金</div>
					<div field="OFT_ITR_BAL" headerAlign="center" align="center" allowSort="true" width="100px">核销利息</div>
					<div field="FRE_ICM_BAL" headerAlign="center" align="center" allowSort="true" width="100px">违约金收入</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    	nui.parse();
    	var dueNum = '<%=request.getParameter("summaryNum") %>';
    	nui.get("dueNum").setValue(dueNum);
    	 var form = new nui.Form("#form1"); 
		 var grid = nui.get("datagrid1");
		 initPage();
	     function initPage(){
	    		var data = form.getData();
	       		 grid.load(data);
	    	}
    </script>
</body>
</html>