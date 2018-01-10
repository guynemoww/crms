<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-9-10 12:42:24
  - Description:
-->
<head>
<title>新增项目向导</title>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<script type="text/javascript"src="<%=contextPath%>/csm/js/commValidate.js"></script>
</head>
<body>
	<div id="form1" class="nui-form" style="width: 99.5%; height: 99.5%; overflow: hidden;">
		<input id="map.projectId" name="map.projectId"value="<%=request.getParameter("projectId")%>"class="nui-hidden" />
		<input id="map.summaryNums" name="map.summaryNums" class="nui-hidden" />
		<fieldset>
			<legend>
				<span>借据列表</span>
			</legend>

			<div title="借据列表" id="contract">
				<div class="nui-dynpanel" columns="6">
					<label class="nui-form-label">机构名称：</label>
					<input id="map.orgId"name="map.orgId"allowInput="false" class="nui-buttonEdit"onbuttonclick="selectOrg" /> 
					
					<label>客户编号：</label> 
					<input name="map.customerNum" id="map.customerNum" required="false"class="nui-textbox nui-form-input" />
					
					<label>客户名称：</label>
					<input name="map.customerName" id="map.customerName" required="false" class="nui-textbox nui-form-input" /> 
						
					<label>借据编号：</label>
					<input id="map.summaryNum" class="nui-textbox nui-form-input" name="map.summaryNum"  /> 

					<label>借据起始时间：</label>
					<input id="map.startDate" class="nui-datepicker nui-form-input"name="map.startDate" /> 
					
					<label>借据截止时间：</label> 
					<input id="map.endDate" class="nui-datepicker nui-form-input"name="map.endDate" /> 
						 
					<label>经办人：</label> 
					<input id="map.userNum"  name="map.userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers" />

				</div>

				<div class="nui-toolbar"style="text-align: right; padding-top: 15px; padding-right: 25px;"borderStyle="border:0;">
					<a class="nui-button" iconCls="icon-search" onclick="query()"id="btnQuery">查询</a> 
					<a class="nui-button" iconCls="icon-reset"onclick="reset()">重置</a>
					<a id="btnClose"class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
				</div>

				<div class="nui-toolbar">
					<a id="addCust" style="margin-left: 5px" class="nui-button"iconCls="icon-add" onclick="add()">移入</a> 
					（移入的借据要求：1.分类等级必须是"正常","关注";   2.业务品种不能是"委托贷款";   3.不是"贴息","停息"状态;   4.借据币种相同;   5.借据止期在项目封包日之后）
				</div>
			</div>
			
		</fieldset>
		<div id="grid" class="nui-datagrid" sortMode="client"
			url="com.bos.project.project.findUnContractList.biz.ext"
			dataField="items" allowAlternating="true" multiSelect="true"
			showEmptyText="true" allowResize="true" emptyText="没有查到数据"
			showReloadButton="false" onrowdblclick="" allowCellEdit="true"onselectionchanged="onSelectionChanged" 
			allowCellSelect="true" sizeList="[10,20,50,100]" pageSize="12">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="SUMMARY_NUM" headerAlign="center" align="center"width="10%" allowSort="true" >借据编号</div>
				<div field="PARTY_NUM" headerAlign="center" align="center" allowSort="true" >客户编号</div>
				<div field="PARTY_NAME" headerAlign="center" align="center" allowSort="true" >客户名称</div>
				<div field="CERT_TYPE" headerAlign="center" align="center" allowSort="true" 
					dictTypeId="CDKH0002">证件类型</div>
				<div field="CERT_NUM" headerAlign="center" align="center" allowSort="true" >证件号码</div>
				<div field="PRODUCT_TYPE" headerAlign="center" align="center" allowSort="true" 
					dictTypeId="product">业务品种</div>
				<div field="SUMMARY_AMT" headerAlign="center" align="right" allowSort="true" 
					dataType="currency">借据金额</div>
				<div field="JJYE" headerAlign="center" align="right" allowSort="true" 
					dataType="currency">借据余额</div>
				<div field="BEGIN_DATE" headerAlign="center" align="center" allowSort="true" >借据起期</div>
				<div field="END_DATE" headerAlign="center" align="center" allowSort="true" >借据止期</div>
				<div field="SPEC_PAYMENT_DATE" headerAlign="center" align="center" allowSort="true" >指定还息日期</div>
				<div field="ORG_NUM" headerAlign="center" align="center" allowSort="true" 
					dictTypeId="org">经办机构</div>
				<div field="USER_NUM" headerAlign="center" align="center" allowSort="true" 
					dictTypeId="user">客户经理</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form= new nui.Form("#form1");
		//------------------页面动态控制区------------------//
		var grid = nui.get("grid"); //借据列表
		var projectId= "<%=request.getParameter("projectId")%>";
		var summaryNums="<%=request.getAttribute("summaryNums")%>"
		if(summaryNums!="null"){
			nui.get("map.summaryNums").setValue(summaryNums);
		}
		query();
		function query() {
			var o = form.getData();
			grid.load(o);
		}

		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1)
				return;
			for (var i = 0; i < e.data.length; i++) {
				e.data[i]['SUMMARY_NUM'] = '<a href="#" onclick="bizView3231(\'' + e.data[i].SUMMARY_NUM + '\');">'+ e.data[i]['SUMMARY_NUM'] + '</a>';
			}
		});
		//机构选择
		function selectOrg() {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/sys/select_org_tree.jsp",
				showMaxButton : true,
				title : "选择机构",
				width : 350,
				height : 450,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.GetData();
						data = nui.clone(data);
						if (data) {
							self.orglevel = data.orglevel;
							btnEdit.setValue(data.orgid);
							btnEdit.setText(data.orgname);
						}
					}
				}
			});
		}

		//移入
		function add() {
			var rows = grid.getSelecteds();
			if (rows.length > 0) {
				var summaryIds = [];
				var summaryIdStr = "";
				for (var i = 0; i < rows.length; i++) {
					summaryIds.push(rows[i].SUMMARY_ID);
					if (i == rows.length - 1) {
						summaryIdStr += rows[i].SUMMARY_ID;
					} else {
						summaryIdStr += rows[i].SUMMARY_ID + ",";
					}
				}
				var json = nui.encode({
					"summaryIds" : summaryIds,
					"projectId" : projectId
				});
				$.ajax({
					url : "com.bos.project.project.contractsAdd.biz.ext",
					type : 'POST',
					data : json,
					cache : false,
					contentType : 'text/json',
					success : function(text) {
						if(text.msg){
							nui.alert(text.msg);
						}else{
							nui.alert("移入成功");
						}
						query();
					},
					error : function() {
						nui.alert("操作失败！");
					}
				});

			} else {
				nui.alert("请选中一条记录");
			}
		}

		// 经办人
		function selectCustManegers(e) {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/orgDemolition/creditMove/userManageALL.jsp",
				showMaxButton : true,
				title : "选择经办人",
				width : 800,
				height : 500,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.getData();
						data = nui.clone(data);
						if (data) {
							btnEdit.setValue(data.userId);
							btnEdit.setText(data.empName);
						}
					}
				}
			});

		}

		function onSelectionChanged() {
			var rows = grid.getSelecteds();
			var row = grid.getSelected();
			var checkDate = "";
			if (row) {
				if (row.TIEXI_STATUS == '01') {
					alert("不能移入状态为贴息的借据");
					grid.deselect(row);
					return;
				}
				if (row.TINGXI_STATUS == '01') {
					alert("不能移入状态为停息的借据");
					grid.deselect(row);
					return;
				}
				var fljg = row.FLJG.substr(0, 2);
				if (fljg == '03' || fljg == '04' || fljg == '05') {
					alert("不能移入分类结果为次级、可疑、损失的借据");
					grid.deselect(row);
					return;
				}
				if (row.PRODUCT_TYPE == '01013001' || row.PRODUCT_TYPE == '02005001' || row.PRODUCT_TYPE == '01013010' || row.PRODUCT_TYPE == '02005010' || row.PRODUCT_TYPE == '02005002' || row.PRODUCT_TYPE == '02005003') {
					alert("不能移入业务品种为委托贷款的借据");
					grid.deselect(row);
					return;
				}

				var json1 = {
					"projectId" : projectId,
					"summaryId" : row.SUMMARY_ID,
					"summaryCurrencyCd" : row.CURRENCY_CD
				};
				//判断项目下是否有借据，有才校验日期
				msg = exeRule("PUB_PROJECT_RELATION", "1", json1);
				if (null != msg && '' != msg) {//
					//判断借据的指定还息日期是否一致
					// 					msg = exeRule("PUB_PROJECT_DATE", "1", json1);
					// 					if (null == msg || '' == msg) {
					// 						nui.alert("选择的借据与项目中其他借据的指定还息日期不一致");
					// 						grid.deselect(row);
					// 						return;
					// 					}
					msg = exeRule("PUB_PROJECT_CURRENCY", "1", json1);
					if (null == msg || '' == msg) {
						nui.alert("选择的借据与项目中其他借据的币种不一致");
						grid.deselect(row);
						return;
					}

				}
				//判断借据是否在其他项目中
				msg = exeRule("PUB_PROJECT_LOAN", "1", json1);
				if (null != msg && '' != msg) {
					nui.alert(msg);
					grid.deselect(row);
					return;
				}

				//判断借据的止期是否在项目的封包日之后。
				msg = exeRule("PUB_PROJECT_LOAN_DATE", "1", json1);
				if (null != msg && '' != msg) {
					nui.alert(msg);
					grid.deselect(row);
					return;
				}
			}
		}

		//重置
		function reset() {
			form.reset();
		}
	</script>
</body>
</html>

