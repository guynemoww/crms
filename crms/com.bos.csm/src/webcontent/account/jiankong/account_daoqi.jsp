<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>贷款到期监控</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="贷款到期监控">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.jiankong.daoqi" />
				</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="selectPo" sortMode="client">
					<div property="columns">
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">客户名称</div>
						<div field="productType" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="user">业务品种</div>
						<div field="summaryNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">合同编号</div>
						<div field="summaryNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据编号</div>
						<div field="summaryNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">币种</div>
						<div field="summaryAmt" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据金额</div>
						<div field="jjye" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据余额</div>
						<div field="beginDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">起始日期</div>
						<div field="endDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">到期日期</div>
						<div field="yqts" allowSort="true" width="" headerAlign="center"
							dictTypeId="">分类 </div>
						<div field="yqts" allowSort="true" width="" headerAlign="center"
							dictTypeId="">到期天数 </div>
					</div>
				</div>
			</center>
		</div>
	</div>
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
	</script>
</body>
</html>