<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>催收登记</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div id="form1" class="nui-form">
		<input name="item.receiptNum" id="item.receiptNum" required="false" class="nui-hidden nui-form-input" />
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input name="item.partyName" class="nui-textbox nui-form-input" />
			<label>证件类型：</label>
			<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" allowInput="false" />
			<label>证件号码：</label>
			<input id="item.certNum" name="item.certNum" class="nui-textbox nui-form-input" />
			<label>合同编号：</label>
			<input name="item.contractNum" class="nui-textbox nui-form-input" />
			<label>借据编号：</label>
			<input name="item.summaryNum" class="nui-textbox nui-form-input" />
			<label>经办行：</label>
			<input name="item.orgNum" allowInput="false" class="nui-buttonEdit" required="true" dictTypeId="org" value="<%=GitUtil.getCurrentOrgCd()%>" onbuttonclick="selectOrg" />
		</div>
		<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 10px;">
			<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
			<a class="nui-button" iconCls="" onclick="reset()">重置</a>
		</div>
	</div>
	<div class="nui-fit">
		<div id="datagrid1" class="nui-datagrid" style="height: 99.5%" sortMode="client" url="com.bos.clt.clt.getLoanSummaryList.biz.ext" dataField="items" allowAlternating="true" multiSelect="true" showColumnsMenu="true" pageSize="10" >
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="partyName" allowSort="true" headerAlign="center" align="center">客户名称</div>
				<div field="productCd" allowSort="true" headerAlign="center" align="center" dictTypeId="product">业务品种</div>
				<div field="summaryNum" allowSort="true" headerAlign="center" align="center">合同/借据编号</div>
				<div field="currencyCd" allowSort="true" headerAlign="center" align="center" dictTypeId="CD000001">币种</div>
				<div field="jjje" allowSort="true" headerAlign="center" align="center" dataType="currency">合同/借据金额</div>
				<div field="jjye" allowSort="true" headerAlign="center" align="center" dataType="currency">合同/借据余额</div>
				<div field="jjyqbj" allowSort="true" headerAlign="center" align="center" dataType="currency">逾期/垫款本金</div>
				<div field="dftItr" allowSort="true" headerAlign="center" align="center" dataType="currency">逾期利息</div>
				<div field="ljfx" allowSort="true" headerAlign="center" align="center" dataType="currency">罚息</div>
				<div field="beginDate" allowSort="true" headerAlign="center" align="center">起始日</div>
				<div field="endDate" allowSort="true" headerAlign="center" align="center">到期日</div>
				<div field="yqts" allowSort="true" headerAlign="center" align="center">逾期或垫款天数</div>
				<div field="fljg" allowSort="true" headerAlign="center" align="center" dictTypeId="XD_FLCD0001">风险分类</div>
				<div field="linkmanName" allowSort="true" headerAlign="center" align="center">联系人姓名</div>
				<div field="linkmanPhone" allowSort="true" headerAlign="center" align="center">联系人电话</div>
			</div>
		</div>
	</div>
	<div id="form2">
		<fieldset>
			<legend>
				<span>催收记录</span>
			</legend>
			<div class="nui-dynpanel" columns="4">
				<label>催收时间：</label>
				<input id="collectionDate" name="collectionDate" class="nui-datepicker nui-form-input" style="width: 300px;" format="yyyy-MM-dd" required="true" />

				<label>催收方式：</label>
				<input id="collectionType" name="collectionType" style="width: 300px;" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD3001" onValuechanged="selectWayChange" required="true" />

				<label>催收对象：</label>
				<input id="collectionObject" name="collectionObject" style="width: 300px;" class="nui-textbox nui-form-input" required="true" vtype="maxLength:180" />

				<label>催收电话：</label>
				<input id="collectionPhone" name="collectionPhone" style="width: 300px;" class="nui-textbox nui-form-input" required="false" vtype="maxLength:15" />

				<label>催收地点：</label>
				<input id="collectionPlace" name="collectionPlace" style="width: 300px;" class="nui-textbox nui-form-input" required="false" vtype="maxLength:800" />

				<label>经办人：</label>
				<input class="nui-textbox nui-form-input" style="width: 300px;" value="<%=((UserObject) session.getAttribute("userObject"))
					.getUserName()%>" enabled="false" />

				<label>分析意见：</label>
				<input id="analyseOpinion" name="analyseOpinion" class="nui-textarea nui-form-input" style="height: 50px; width: 300px;" required="true" vtype="maxLength:1800" />

				<label>催收记录：</label>
				<input id="collectionDesc" name="collectionDesc" style="height: 50px; width: 300px;" class="nui-textarea nui-form-input" required="true" vtype="maxLength:1800" />

			</div>
		</fieldset>
		<div class="nui-toolbar" style="text-align: right; padding-right: 20px; border: 0;">
			<!--<a id="image" class="nui-button" iconCls="icon-zoomin" onclick="image()">影像扫描</a>  -->
			<a id="btnSave" class="nui-button" style="margin-right: 5px;" iconCls="icon-save" onclick="register">保存</a>
			<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var form2 = new nui.Form("#form2");
		var grid = nui.get("datagrid1");
		var cltId = "";
		function queryInit() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请完整填写信息！");
				return;
			}

			var receiptNum = nui.get("item.receiptNum");
			var receiptNum1 =
	<%=JspUtil.getParameterHaveSign(request, "receiptNum")%>
		;
			if (null != receiptNum1 && "null" != receiptNum1
					&& "" != receiptNum1) {
				receiptNum.setValue(receiptNum1);
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
				e.data[i]['summaryNum'] = '<a href="#" onclick="bizView3231(\''
						+ e.data[i].summaryNum + '\');">'
						+ e.data[i]['summaryNum'] + '</a>';
			}
		});

		function reset() {
			form.reset();
			form2.reset();
			queryInit();
		}

		function register() {
			form2.validate();
			if (form2.isValid() == false) {
				nui.alert("请输入必填项。");
				return;
			}
			var rows = grid.getSelecteds();
			if (rows.length == 0) { //没有选中项，表单不提交。
				alert("请至少选择一条记录！");
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
						if (text.msg == '登记成功') {
							cltId = text.register.cltId;
						}
					} else {
						nui.alert("系统异常");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
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

		function image() {
			if (cltId == "" || cltId == null) {
				return alert("请先保存催收登记信息");
			}
			var url = "/pub/imagePlatform/item_tree.jsp?businessNumber="
					+ cltId + "&partyTypeCd=01&flowModuleType=112";
			nui.open({
				url : nui.context + url,
				title : "影像资料",
				width : 1200,
				height : 800,
				onload : function() {
				},
				ondestroy : function(action) {
					grid.reload();
				}
			});
		}

		function selectWayChange() {
			var istc = nui.get("collectionType").getValue();
			if (istc == "1") {
				nui.get("collectionPhone").setRequired(true);
				nui.get("collectionPlace").setRequired(false);
			} else if (istc == "2" || istc == "3") {
				nui.get("collectionPlace").setRequired(true);
				nui.get("collectionPhone").setRequired(false);
			} else {
				nui.get("collectionPlace").setRequired(false);
				nui.get("collectionPhone").setRequired(false);
			}
			form.validate();
		}
	</script>
</body>
</html>