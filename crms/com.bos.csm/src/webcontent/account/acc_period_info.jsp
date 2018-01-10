<%@page pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-08-08 14:48:46
  - Description:
-->
<head>
<title>分录信息</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
      <%@include file="/common/nui/common.jsp" %>
    
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 2000px; height: auto;">
		<div title="分录信息查询">
			<div id="form1">
					<input id="dueNum" name="dueNum" required="false" class="nui-hidden nui-form-input"/>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: auto; height: auto;" sortMode="client" url="com.bos.account.BusiTransferImplServiceService.accPeriodInfo.biz.ext"
			dataField="loanInfos" allowResize="true" showReloadButton="false" 
			allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="RCV_DATE" headerAlign="center" allowSort="true" align="center" width="100px">营业日期</div>
					<div field="DUE_NUM" headerAlign="center" allowSort="true" align="center" width="100px">借据编号</div>
					<div field="TRAN_COD" headerAlign="center" align="center" allowSort="true" width="100px">交易代码</div>
					<div field="TAL_DEP" headerAlign="center" align="center" allowSort="true" width="100px">核算机构</div>
					<div field="OPN_DEP" headerAlign="center" align="center" allowSort="true" width="100px">开户机构</div>
					<div field="BRW_NAME" headerAlign="center" align="center" allowSort="true" width="100px">借款人名称</div>
					<div field="BUS_COD" headerAlign="center" align="center" allowSort="true" width="100px">业务别</div>
					<div field="CURR_COD" headerAlign="center" align="center" allowSort="true" width="100px">币种</div>
					<div field="ACC_CLS" headerAlign="center" allowSort="true" align="center" width="100px">表内外标志</div>
					<div field="BRW_LGO" headerAlign="center" allowSort="true" align="center" width="100px">借贷标志</div>
					<div field="PAY_AMT" headerAlign="center" align="center" allowSort="true" width="100px">发生额</div>
					<div field="REL_TIM_BAL" headerAlign="center" align="center" allowSort="true" width="100px">实时余额</div>
					<div field="BAL_DIRT" headerAlign="center" align="center" allowSort="true" width="100px">账户余额方向</div>
					<div field="STD_COD" headerAlign="center" align="center" allowSort="true" width="100px">标准账号</div>
					<div field="RECALL" headerAlign="center" allowSort="true" align="center" width="100px">撤销标志</div>
					<div field="HOST_TM" headerAlign="center" align="center" allowSort="true" width="100px">系统时间</div>
					<div field="SUP_STAN" headerAlign="center" align="center" allowSort="true" width="100px">核算系统流水号</div>
					<div field="RCN_STAN" headerAlign="center" align="center" allowSort="true" width="100px">对账流水号</div>
					<div field="SEQ_NO" headerAlign="center" align="center" allowSort="true" width="100px">分录序号</div>
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