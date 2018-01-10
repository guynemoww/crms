<%@page pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-08-08 14:48:46
  - Description:
-->
<head>
<title>交易明细信息</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
      <%@include file="/common/nui/common.jsp" %>
    
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 2000px; height: auto;">
		<div title="交易明细查询">
			<div id="form1">
					<input id="dueNum" name="dueNum" required="false" class="nui-hidden nui-form-input"/>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: auto; height: auto;" sortMode="client" url="com.bos.account.BusiTransferImplServiceService.detailPeriodInfo.biz.ext"
			dataField="loanInfos" allowResize="true" showReloadButton="false" 
			allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="TRAN_DATE" headerAlign="center" allowSort="true" align="center" width="100px">营业日期</div>
					<div field="OPN_DEP" headerAlign="center" allowSort="true" align="center" width="100px">开户机构</div>
					<div field="DUE_NUM" headerAlign="center" align="center" allowSort="true" width="100px">借据编号</div>
					<div field="TRAN_COD" headerAlign="center" align="center" allowSort="true" width="100px">交易码</div>
					<div field="RCN_STAN" headerAlign="center" align="center" allowSort="true" width="100px">对账流水号</div>
					<div field="PAD_UP_AMT" headerAlign="center" align="center" allowSort="true" width="100px">金额</div>
					<div field="PRIM_ACCT" headerAlign="center" allowSort="true" align="center" width="100px">放款主账号</div>
					<div field="PAY_PRIM_ACCT" headerAlign="center" allowSort="true" align="center" width="100px">还款账号</div>
					<div field="TRN_TM" headerAlign="center" align="center" allowSort="true" width="100px">交易时间</div>
					<div field="OPR" headerAlign="center" align="center" allowSort="true" width="100px">操作员</div>
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