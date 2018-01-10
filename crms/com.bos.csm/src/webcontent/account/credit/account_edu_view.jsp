<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>客户业务列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<center>
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<input name="item.partyId" id="item.partyId"  class="nui-hidden" value="<%=request.getParameter("partyId")%>"/>
				<input name="nameSqlId" id="nameSqlId"  class="nui-hidden" value="com.bos.account.credit.edulist"/>
			</div>
			<div id="datagrid1" class="nui-datagrid"
				style="width: 99.5%; height: auto"
				url="com.bos.risk.common.queryByNamedSqlWithPage.biz.ext" dataField="items"
				allowResize="true" showReloadButton="false"
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="selectPo" sortMode="client">
				<div property="columns">
					<div field="orgNum" allowSort="true" width="" headerAlign="center"  dictTypeId="org">机构名称</div>
					<div field="partyName" allowSort="true" width="" headerAlign="center" >客户名称</div>
					<div field="approvalNum" allowSort="true" width="" headerAlign="center" >批复编号</div>
					<div field="bizType" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
					<div field="currencyCd" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
					<div field="creditAmt" allowSort="true" width="" headerAlign="center" >批复金额</div>
					<div field="availableAmt" allowSort="true" width="" headerAlign="center" >批复已用金额</div>
					<div field="usedAmt" allowSort="true" width="" headerAlign="center"  >已用金额</div>
					<div field="validDate" allowSort="true" width="" headerAlign="center" >起始日</div>
					<div field="endDate" allowSort="true" width="" headerAlign="center" >到期日</div>
					<div field="userNum" allowSort="true" width="" headerAlign="center"  dictTypeId="user">经办人</div>
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
				e.data[i]['approvalNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].approvalNum+ '\');">'+e.data[i]['approvalNum']+'</a>';
			}
		});
	</script>
</body>
</html>