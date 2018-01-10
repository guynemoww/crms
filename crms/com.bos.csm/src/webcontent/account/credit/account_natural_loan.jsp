<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>个人借据信息查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display: none;"></iframe>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="个人借据信息查询">
			<div id="form1" class="nui-form">
				<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.indvcustomerLoan" />
				<div class="nui-dynpanel" columns="8" style="margin-top: 0;margin-bottom: 0;">
					<label class="nui-form-label">机构名称：</label>
					<input id="item.orgcode" name="item.orgcode" value="<%=GitUtil.getCurrentOrgCd()%>" allowInput="false" required="true" dictTypeId="org" class="nui-buttonEdit" onbuttonclick="selectOrg" />
					<label>客户名称：</label>
					<input name="item.partyName" class="nui-textbox nui-form-input" />
					<label>证件类型：</label>
					<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" textField="dictname" valueField="dictid" dictTypeId="CDKH0002" allowInput="false" />
					<label>证件号码：</label>
					<input id="item.certNum" class="nui-textbox nui-form-input" name="item.certNum" onvalidation="" />
					<label>贷款品种：</label>
					<input id="item.productType" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct" name="item.productType" dictTypeId="product" />
					<label>合同编号：</label>
					<input id="item.contractNum" class="nui-textbox nui-form-input" name="item.contractNum" onvalidation="" />
					<label>借据编号：</label>
					<input id="item.summaryNum" class="nui-textbox nui-form-input" name="item.summaryNum" onvalidation="" />
					<label>借据状态：</label>
					<input id="item.summaryStatusCd" class="nui-dictcombobox nui-form-input" name="item.summaryStatusCd" dictTypeId="XD_SXYW0226" />
					<label>借据起期：</label>
					<input id="item.beginDate" class="nui-datepicker nui-form-input" name="item.beginDate" onvalidation="" />
					<label>借据止期：</label>
					<input id="item.endDate" class="nui-datepicker nui-form-input" name="item.endDate" onvalidation="" />
					<label>金额范围：</label>			
						<div colspan="3" style="text-align: left;">
						<input id="item.amtType" name="item.amtType" class="nui-combobox" data="amtType" style="width: 100px;" />
						<input id="item.minAmt" name="item.minAmt" class="nui-textbox"  style="width: 100px;"/>-	
						<input id="item.maxAmt" name="item.maxAmt" class="nui-textbox"  style="width: 100px;"/>
					</div>
					<!--  <label>经办人：</label> -->
					<%
						if (UserUtil.isManager()) {
					%>
					<input id="item.userNum" class="nui-hidden nui-buttonEdit" name="item.userNum" value="<%=GitUtil.getCurrentUserId()%>" readonly />
					<%
						}
						//else{
					%>
					<!--  
						<input id="item.userNum" name="item.userNum" required="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers"/>
						-->
					<%
						//}
					%>

				</div>
				<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">

					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
					<!-- <a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a> -->

				</div>
			</div>
			<div class="nui-fit">
				<div id="datagrid1" class="nui-datagrid" style="height: 100%" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" multiSelect="false" sortMode="client" allowAlternating="true">
					<div property="columns">
						<div field="orgNum" allowSort="true" width="" headerAlign="center" autoEscape="false" dictTypeId="org">机构名称</div>

						<div field="partyName" allowSort="true" width="" headerAlign="center" dictTypeId="">客户名称</div>

						<div field="contractNum" allowSort="true" width="" headerAlign="center" dictTypeId="">合同编号</div>

						<div field="summaryNum" allowSort="true" width="" headerAlign="center" dictTypeId="">借据编号</div>
						
						<div field="periodNum" allowSort="true" width="" headerAlign="center" dictTypeId="">展期协议号</div>

						<div field="productType" allowSort="true" width="" headerAlign="center" dictTypeId="product">贷款品种</div>
						<!--  
						<div field="loanSubject" allowSort="true" width="" headerAlign="center" dictTypeId="">业务别</div>
						-->
						<div field="currencyCd" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>

						<div field="summaryAmt" allowSort="true" width="" headerAlign="center" dictTypeId="">借据金额</div>

						<div field="jjye" allowSort="true" width="" headerAlign="center" dictTypeId="">借据余额</div>

						<div field="beginDate" allowSort="true" width="" headerAlign="center" dictTypeId="">借据起期</div>
						
						<div field="endDate" allowSort="true" width="" headerAlign="center" dictTypeId="">借据止期</div>
						<div field="extendEndDate" allowSort="true" width="" headerAlign="center" dictTypeId="">展期到期日</div>	
						<!--  
						<div field="yearRate" allowSort="true" width="" headerAlign="center" dictTypeId="">贷款利率</div>
						-->
						<div field="summaryStatusCd" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0226">借据状态</div>

						<div field="yqts" allowSort="true" width="" headerAlign="center" dictTypeId="">逾期天数</div>

						<div field="jjyqbj" allowSort="true" width="" headerAlign="center" dictTypeId="">逾期本金</div>

						<div field="normalItr" allowSort="true" width="" headerAlign="center" dictTypeId="">正常利息</div>
						<div field="arrearItr" allowSort="true" width="" headerAlign="center" dictTypeId="">拖欠利息</div>
						<!-- <div field="punishItr" allowSort="true" width="" headerAlign="center" dictTypeId="">罚息</div> -->
						<div field="newPnsItr" allowSort="true" width="" headerAlign="center" dictTypeId="">罚息</div>
						<div field="newFlItr" allowSort="true" width="" headerAlign="center" dictTypeId="">复利</div>
						<div field="periodFlag" allowSort="true" width="" headerAlign="center" dictTypeId="XD_0002">展期标志</div>
						<div field="periodState" allowSort="true" width="" headerAlign="center" dictTypeId="XD_ZQZT0001">展期状态</div>
						<div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
						<!--  
						<div field="viewLoanList" allowSort="true" width="" headerAlign="center" dictTypeId="">查看流水</div>
						-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var amtType = [{"id":"SUMMARY_AMT","text":"借据金额"},{"id":"JJYE","text":"借据余额"}];
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		
		nui.get("item.amtType").setValue(amtType[0].id);
		
		function queryInit() {
			if (form.isValid() == false) {
				nui.alert("请输入必填项。");
				return;
			}

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
							btnEdit.setValue(data.orgcode);
							btnEdit.setText(data.orgname);
						}
					}
				}
			});
		}

		function init() {
			git.mask();
			var json = nui.encode({
				parentId : "10000"
			});
			$.ajax({
				url : "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask();
					nui.get("item.certType").setData(text.levels);
					custFlag = true;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask();
					nui.alert(jqXHR.responseText);
				}
			});
		}
		init();

		grid
				.on(
						"preload",
						function(e) {
							if (!e.data || e.data.length < 1) {
								return;
							}
							for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
								e.data[i]['partyName'] = '<a href="#" onclick="toGoCustDetail(\''
										+ e.data[i].partyId
										+ '\');">'
										+ e.data[i]['partyName'] + '</a>';
								e.data[i]['contractNum'] = '<a href="#" onclick="bizView3231(\''
										+ e.data[i].contractNum
										+ '\');">'
										+ e.data[i]['contractNum'] + '</a>';
								e.data[i]['summaryNum'] = '<a href="#" onclick="bizView3231(\''
										+ e.data[i].summaryNum
										+ '\');">'
										+ e.data[i]['summaryNum'] + '</a>';
								//e.data[i]['viewLoanList']='<button onclick=\"viewLoan();\">查看流水</button>';
							}
						});

		function viewLoan() {
			var row = grid.getSelected();
			debugger;
			if (row) {
				nui.open({
					url : "csm/account/credit/loan_list.jsp?dueNum="
							+ row.summaryNum2,
					title : "借据流水",
					width : 1000,
					height : 500,
					allowResize : true,
					showMaxButton : true,
					onload : function() {
						var iframe = this.getIFrameEl();
						var data = row;
					}
				});
				return;
			} else {
				alert("请选中一条记录");
			}

		}

		var arr = git.getDictDataFilter("XD_SXYW0226", "02,03,04");
		//借据状态中没有未结清的说法  这里有一个需求是在页面可以筛选除了结清以外的借据：就是未结清状态   页面做处理 
		var wjq = {
			"dictId":"100",
			"dictName":"未结清",
			"sortNo": "100",
			"status":"1",
			"parentid":null
		
		};
		arr.push(wjq)
		nui.get("item.summaryStatusCd").setData(arr);

		// 经办人
		function selectCustManegers(e) {
			var newOrgNum;
			newOrgNum = nui.get("item.orgcode").getValue();

			if (newOrgNum == "") {
				alert("请先选择机构");
				return;
			} else {
				var orgIds;
				orgIds = nui.get("item.orgcode").getValue();
				var btnEdit = this;
				nui
						.open({
							url : nui.context
									+ "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum="
									+ orgIds,
							showMaxButton : true,
							title : "选择客户经理",
							width : 800,
							height : 500,
							ondestroy : function(action) {
								if (action == "ok") {
									var iframe = this.getIFrameEl();
									var data = iframe.contentWindow.getData();
									data = nui.clone(data);
									if (data) {
										//  alert(nui.encode(data));
										btnEdit.setValue(data.userId);
										btnEdit.setText(data.empName);
									}
								}
							}
						});
			}

		}

		//产品选择
		function selectProduct(e) {
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/product/product/select_product_tree.jsp?partyTypeCd=02&partyId=null",
						title : "选择",
						width : 800,
						height : 450,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.currentNode;
								data = nui.clone(data);
								if (data) {
									btnEdit.setValue(data.productCd);
									btnEdit.setText(data.productName);
								}
							}
							if (action == "clear") { //清空选择的内容
								btnEdit.setValue("");
								btnEdit.setText("");
							}
						}
					});
		}

		function dc() {
			var ifrm = document.getElementById("exportFrame");
			git.mask();
			var o = form.getData();//逻辑流必须返回total
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.csm.pub.ibatis.zzrJJDownloadEXCEL.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						git.unmask();
						ifrm.src = nui.context
								+ "/pub/io/file/download.jsp?deleteFile=true";
					} else {
						git.unmask();
						nui.alert("下载数据有误！");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
	</script>
</body>
</html>