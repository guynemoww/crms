<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.entity.name.CsmTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<head>
<%@include file="/common/nui/common.jsp"%>
<%@include file="/csm/transfer/csmxfe_common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form">
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input name="item.partyName" class="nui-textbox" />
			<label>证件类型：</label>
			<input name="item.certType" class="nui-dictcombobox" dictTypeId="CDKH0002" />
			<label>证件号码：</label>
			<input name="item.certNum" class="nui-textbox" />
			<label>统一社会信用代码：</label>
			<input name="item.unifySocietyCreditNum" class="nui-textbox" />
			<label>营业执照：</label>
			<input name="item.registerCd" class="nui-textbox" />
			<label>中征码：</label>
			<input name="item.middelCode" class="nui-textbox" />
		</div>
		<div class="nui-toolbar" style="text-align: right; border: none">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div id="actionDiv" class="nui-toolbar" style="border: none; display: none;">
		<a class="nui-button" iconCls="icon-search" onclick="add()">添加</a>
		<a class="nui-button" iconCls="icon-reset" onclick="remove()">删除</a>
		<a id="approveBtn" class="nui-button" iconCls="icon-reset" onclick="approve(csmxfe)">确认</a>
	</div>
	<div class=nui-fit>
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="false" sortMode="client">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="PARTY_NAME_URL" headerAlign="center" align="center" allowSort="true" width="100">客户名称</div>
				<div field="CERT_TYPE" headerAlign="center" align="center" allowSort="true" dictTypeId='CDKH0002' width="100">证件类型</div>
				<div field="CERT_NUM" headerAlign="center" align="center" allowSort="true" width="100">证件号码</div>
				<div field="APPROVE_NUM" headerAlign="center" align="right" allowSort="true" width="100">移交批复数量</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var actionDiv = nui.get("actionDiv");
		var form = new nui.Form("#form1");
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
		var csmxfe;

		init();
		function init() {
			renderingGrid(
					grid,
					function(row) {
						row['PARTY_NAME_URL'] = '<a href="#" onclick="toGoCustDetail(\''
								+ row['PARTY_ID']
								+ '\');">'
								+ row['PARTY_NAME'] + '</a>';
					});
			csmxfe = getCsmxfe(transferId);
			if ("1" == csmxfe.transferType
					&& (csmxfe.status == '00' || csmxfe.status == '90')) {
				$("#actionDiv").css("display", "block");
				if (csmxfe.processId) {
					nui.get("approveBtn").hide();
				} else {
					nui.get("approveBtn").show();
				}
			}
			query();
		}

		function add() {
			nui.open({
				url : nui.context
						+ "/csm/transfer/csmxfe_party_select.jsp?transferId="
						+ transferId + "&orgNum=" + csmxfe.oldOrgId
						+ "&userNum=" + csmxfe.oldUserId,
				showMaxButton : true,
				title : "选择移交客户信息",
				width : 1000,
				height : 600,
				ondestroy : function(action) {
					query();
				}
			});
		}

		function remove() {
			actionDiv.loading("数据删除中...");
			var row = grid.getSelected();
			if (!row || row < 0) {
				actionDiv.unmask();
				alert("请选择一行");
				return;
			}
			var json = nui.encode({
				"param" : {
					"transferId" : transferId,
					"partyId" : row.PARTY_ID
				}
			});
			$
					.ajax({
						url : "com.bos.csm.transfer.transfer.removeDetailByParty.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(data) {
							debugger;
							actionDiv.unmask();
							if (data.msg) {
								alert(data.msg);
								return;
							} else {
								query();
								alert(actionSuccess);
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							actionDiv.unmask();
							nui.alert(jqXHR.responseText);
						}
					});
		}

		function query() {
			if (!transferId) {
				return;
			}
			var data = form.getData();
			data.sqlName = "com.bos.csm.transfer.transfer.getPartyInfo";
			data.item.transferId = transferId;
			grid.load(data);
		}

		function reset() {
			form.reset();
		}
	</script>
</body>
</html>

