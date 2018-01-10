<%@page pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-08-08 14:48:46
  - Description:
-->
<head>
<title>期供信息</title>
   <%@include file="/common/nui/common.jsp" %>
    
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 4000px; height: auto;">
		<div title="期供信息查询">
			<div id="form1">
					<input id="dueNum" name="dueNum" required="false" class="nui-hidden nui-form-input"/>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: auto; height: auto;" sortMode="client" url="com.bos.account.BusiTransferImplServiceService.queryPeriodInfo.biz.ext"
			dataField="loanInfos" allowResize="true" showReloadButton="false" 
			allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="DUE_NUM" headerAlign="center" allowSort="true" align="center" width="100px">借据</div>
					<div field="CURR_PERI" headerAlign="center" allowSort="true" align="center" width="100px">本期期数</div>
					<div field="BEG_DATE" headerAlign="center" align="center" allowSort="true" width="100px">起始日</div>
					<div field="END_DATE" headerAlign="center" align="center" allowSort="true" width="100px">到期日</div>
					<div field="PAY_DATE" headerAlign="center" align="center" allowSort="true" width="100px">上次还款日</div>
					<div field="NEXT_PROV_DATE" headerAlign="center" align="center" allowSort="true" width="100px">下一还款日</div>
					<div field="RCV_PRN" headerAlign="center" align="center" allowSort="true" width="100px">应收本金</div>
					<div field="PAD_UP_PRN" headerAlign="center" allowSort="true" align="center" width="100px">实收本金</div>
					<div field="RCV_NOR_ITR_IN" headerAlign="center" allowSort="true" align="center" width="100px">应收表内正常利息</div>
					<div field="PAD_UP_NOR_ITR_IN" headerAlign="center" align="center" allowSort="true" width="100px">实收表内正常利息</div>
					<div field="RCV_DFT_ITR_IN" headerAlign="center" align="center" allowSort="true" width="100px">应收表内拖欠利息</div>
					<div field="PAD_UP_DFT_ITR_IN" headerAlign="center" align="center" allowSort="true" width="100px">实收表内拖欠利息</div>
					<div field="RCV_PNS_ITR_IN" headerAlign="center" align="center" allowSort="true" width="100px">应收表内罚息</div>
					<div field="PAD_UP_PNS_ITR_IN" headerAlign="center" align="center" allowSort="true" width="100px">实收表内罚息</div>
					<div field="RCV_CPD_ITR_IN" headerAlign="center" allowSort="true" align="center" width="100px">应收表内复利</div>
					<div field="PAD_UP_CPD_ITR_IN" headerAlign="center" allowSort="true" align="center" width="100px">实收表内复利</div>
					<div field="RCV_NOR_ITR_OUT" headerAlign="center" align="center" allowSort="true" width="100px">应收表外正常利息</div>
					<div field="PAD_UP_NOR_ITR_OUT" headerAlign="center" align="center" allowSort="true" width="100px">实收表外正常利息</div>
					<div field="RCV_DFT_ITR_OUT" headerAlign="center" align="center" allowSort="true" width="100px">应收表外拖欠利息</div>
					<div field="PAD_UP_DFT_ITR_OUT" headerAlign="center" align="center" allowSort="true" width="100px">实收表外拖欠利息</div>
					<div field="RCV_PNS_ITR_OUT" headerAlign="center" align="center" allowSort="true" width="100px">应收表外罚息</div>
					<div field="PAD_UP_PNS_ITR_OUT" headerAlign="center" allowSort="true" align="center" width="100px">实收表外罚息</div>
					<div field="RCV_CPD_ITR_OUT" headerAlign="center" allowSort="true" align="center" width="100px">应收表外复利</div>
					<div field="PAD_UP_CPD_ITR_OUT" headerAlign="center" align="center" allowSort="true" width="100px">实收表外复利</div>
					<div field="RCV_PAD_UP_PRN" headerAlign="center" align="center" allowSort="true" width="100px">逾期本金</div>
					<div field="DFT_PRN_OTD_ITR" headerAlign="center" align="center" allowSort="true" width="100px">拖欠本金未结计</div>
					<div field="NOR_ITR_IN_OTD_ITR" headerAlign="center" align="center" allowSort="true" width="110px">表内正常利息未结计</div>
					<div field="NOR_ITR_OUT_OTD_ITR" headerAlign="center" align="center" allowSort="true" width="110px">表外正常利息未结计</div>
					<div field="DFT_ITR_IN_OTD_ITR" headerAlign="center" allowSort="true" align="center" width="110px">表内拖欠利息未结计</div>
					<div field="DFT_ITR_OUT_OTD_ITR" headerAlign="center" allowSort="true" align="center" width="110px">表外拖欠利息未结计</div>
					<div field="PNS_ITR_IN_OTD_ITR" headerAlign="center" align="center" allowSort="true" width="100px">表内罚息未结计</div>
					<div field="PNS_ITR_OUT_OTD_ITR" headerAlign="center" align="center" allowSort="true" width="100px">表外罚息未结计</div>
					<div field="CPD_ITR_IN_OTD_ITR" headerAlign="center" align="center" allowSort="true" width="100px">表内复利未结计</div>
					<div field="CPD_ITR_OUT_OTD_ITR" headerAlign="center" align="center" allowSort="true" width="100px">表外复利未结计</div>
					<div field="GRA_PRN_DATE" headerAlign="center" align="center" allowSort="true" width="100px">宽限期本金到期日</div>
					<div field="GRA_ITR_DATE" headerAlign="center" allowSort="true" align="center" width="100px">宽限期利息到期日</div>
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