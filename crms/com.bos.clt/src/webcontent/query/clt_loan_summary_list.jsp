<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>催收台账查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="催收台账查询">
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
					<div class="nui-dynpanel" columns="6">
					<label class="nui-form-label">客户名称：</label> 
					<input name="item.partyName" class="nui-textbox nui-form-input" /> 
					<label class="nui-form-label">证件类型：</label> 
					<input id="item.certType"name="item.certType" class="nui-dictcombobox nui-form-input"dictTypeId="CDKH0002" allowInput="false" /> 
					<label class="nui-form-label">证件号码：</label>
					<input id="item.certNum"name="item.certNum" class="nui-textbox nui-form-input" /> 
					<label class="nui-form-label">合同编号：</label>
					<input name="item.contractNum" class="nui-textbox nui-form-input" /> 
					<label class="nui-form-label">借据编号：</label>
					<input name="item.summaryNum" class="nui-textbox nui-form-input" /> 
					<label class="nui-form-label">经办行：</label> 
					<input name="item.orgNum"allowInput="false" class="nui-buttonEdit"onbuttonclick="selectOrg" required="true"
						text="<%=((UserObject) session.getAttribute("userObject")).getUserOrgName()%>"
						value="<%=((UserObject) session.getAttribute("userObject")).getUserOrgId()%>" />
					<label class="nui-form-label">催收方式：</label> 
					<input id="item.collectionType" name="item.collectionType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD3001"
						allowInput="false" /> 
					<label class="nui-form-label">催收日期：</label>
					<div colspan="1" >
						<input id="item.startDate" name="item.startDate" required="false"
							style="width: 90px;" class="nui-datepicker nui-form-input" /> -
						<input id="item.endDate" name="item.endDate" required="false"
							style="width: 90px;" class="nui-datepicker nui-form-input" />
					</div>
				</div>
					
				<div class="nui-toolbar" style="text-align:right;border:none" >
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
				</div>
			</div>
		<div class="nui-toolbar" style="width:99.5% border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" iconCls="icon-zoomin" onclick="selectPo()">查看</a>
			</div>
			<div id="datagrid1" class="nui-datagrid"style="width: 99.5%; height: auto"
				url="com.bos.clt.clt.queryTbCltRegisterInfo.biz.ext"
				dataField="items" allowResize="true" showReloadButton="false"
				allowAlternating="true" sizeList="[10,15,20,50,100]"
				multiSelect="false" pageSize="10" onselectionchanged=""
				sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="partyName" allowSort="true" align="center"
						headerAlign="center" autoEscape="false">客户名称</div>
					<div field="productCd" allowSort="true" align="center"
						headerAlign="center" dictTypeId="product">业务品种</div>
					<div field="summaryNum" allowSort="true" align="center"
						headerAlign="center">合同/借据编号</div>
					<div field="currencyCd" allowSort="true" align="center"
						headerAlign="center" dictTypeId="CD000001">币种</div>
					<div field="jjje" allowSort="true" align="center"
						headerAlign="center" dataType="currency" dictTypeId="">合同/借据金额</div>
					<div field="jjye" allowSort="true" align="center"
						headerAlign="center" dataType="currency" dictTypeId="">合同/借据余额</div>
					<div field="jjyqbj" allowSort="true" align="center"
						headerAlign="center" dataType="currency" dictTypeId="">逾期/垫款本金</div>
					<div field="dftItr" allowSort="true" align="center" headerAlign="center"
						dataType="currency" dictTypeId="">逾期利息</div>
					<div field="ljfx" allowSort="true" align="center" headerAlign="center"
						dataType="currency" dictTypeId="">罚息</div>
					<div field="beginDate" allowSort="true" align="center"
						headerAlign="center" dictTypeId="">起始日</div>
					<div field="endDate" allowSort="true" align="center"
						headerAlign="center" dictTypeId="">到期日</div>
					<div field="yqts" allowSort="true" align="center"
						headerAlign="center">逾期或垫款天数</div>
					<div field="fljg" allowSort="true" align="center"
						headerAlign="center" dictTypeId="XD_FLCD0001">风险分类</div>
					<div field="linkmanName" allowSort="true" align="center"
						headerAlign="center" dictTypeId="">联系人姓名</div>
					<div field="linkmanPhone" allowSort="true" align="center"
						headerAlign="center" dictTypeId="">联系人电话</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请完整填写信息！");
				return;
			}
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['summaryNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].summaryNum+ '\');">'+e.data[i]['summaryNum']+'</a>';
			}
		});

		function reset() {
			form.reset();
			queryInit();
		}

		function register() {
			var rows = grid.getSelecteds();
			if (rows.length == 0) { //没有选中项，表单不提交。
				alert("请选择！");
				git.unmask();
				return;
			}
			var json = nui.encode({
				"summarys" : rows,
				"register" : form2.getData()
			});
			$.ajax({
				url : "com.bos.clt.clt.register.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						nui.alert(text.msg);
					} else {

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}

		function selectPo() {
			var row = grid.getSelected();
			if (row) {
				nui.open({
					url : nui.context + "/clt/query/clt_loan_summary_view.jsp?summaryId=" + row.summaryId,
					showMaxButton : true,
					title : "催收历史记录",
					width : 1200,
					height : 400,
					onload : function(e) {
						var iframe = this.getIFrameEl();
						var text = iframe.contentWindow.document.body.innerText;
					},
					ondestroy : function(action) {
						queryInit();
					}
				});
			} else {
				nui.alert("请选择查看记录");
			}
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