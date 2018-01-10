<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>借据流水信息查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<center>
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<input name="item.dueNum" id="item.dueNum"  class="nui-hidden" value="<%=request.getParameter("dueNum")%>"/>
				<input name="nameSqlId" id="nameSqlId"  class="nui-hidden" value="com.bos.account.credit.accLoanList"/>
			</div>
			<div id="datagrid1" class="nui-datagrid"
				style="width: 99.5%; height: auto"
				url="com.bos.risk.common.queryByNamedSqlWithPageSdp.biz.ext" dataField="items"
				allowResize="true" showReloadButton="false"
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="selectPo" sortMode="client">
				<div property="columns">
					<div field="SUP_DATE" allowSort="true" width="" headerAlign="center">营业日期</div>
					<div field="CUS_NO" allowSort="true" width="" headerAlign="center" >客户编号</div>
					<div field="DUE_NUM" allowSort="true" width="" headerAlign="center" >借据编号</div>
					<div field="BRW_NAME" allowSort="true" width="" headerAlign="center" >借款人名称</div>
					<div field="ACC_TYP" allowSort="true" width="" headerAlign="center" dictTypeId="accountType">账户类型</div>
					<div field="EVT_DESC" allowSort="true" width="" headerAlign="center" >交易名称</div>
					<div field="CURR_COD" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
					<div field="BRW_LGO" allowSort="true" width="" headerAlign="center" >借贷标志</div>
					<div field="AMT_INCUR" allowSort="true" width="" headerAlign="center" >发生额</div>
					<div field="REL_TIM_BAL" allowSort="true" width="" headerAlign="center" >实时余额</div>
				</div>
			</div>
		</center>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			queryInit();
		}
		//机构选择
		function selectOrg(){
		
			var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/sys/select_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 350,
	            height: 450,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    if (data) {
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgid);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });      
		}
		
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
				e.data[i]['summaryNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].summaryNum+ '\');">'+e.data[i]['summaryNum']+'</a>';
			}
		});
	</script>
</body>
</html>