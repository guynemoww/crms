<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.entity.name.CsmTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<head>
<%@include file="/common/nui/common.jsp"%>
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
		<div id="actionDiv" class="nui-toolbar" style="text-align: right; border: none">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div class="nui-fit">
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="true" sortMode="client">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="PARTY_NUM_URL" headerAlign="center" align="center" allowSort="true" width="100">客户编号</div>
				<div field="PARTY_NAME" headerAlign="center" align="center" allowSort="true" width="200">客户名称</div>
				<div field="CERT_TYPE" headerAlign="center" align="center" allowSort="true" dictTypeId='CDKH0002' width="100">证件类型</div>
				<div field="CERT_NUM" headerAlign="center" align="center" allowSort="true" width="100">证件号码</div>
				<div field="APPROVE_NUM" headerAlign="center" align="right" allowSort="true" width="60">可移交批复数量</div>
				<div field="USER_NUM" headerAlign="center" allowSort="true" width="100" dictTypeId="user">原客户经理</div>
				<div field="ORG_NUM" headerAlign="center" allowSort="true" width="100" dictTypeId="org">原机构名称</div>
			</div>
		</div>
	</div>
	<div id="actionDiv" class="nui-toolbar" style="text-align: center; border: none;">
		<a class="nui-button" iconCls="icon-search" onclick="createDetail()">添加</a>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var form = new nui.Form("#form1");
		var actionDiv = nui.get("actionDiv");
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
		var userNum =
	<%=JspUtil.getParameterHaveSign(request, "userNum")%>
		;
		var orgNum =
	<%=JspUtil.getParameterHaveSign(request, "orgNum")%>
		;
		init();
		function init() {
			renderingGrid(grid, function(row) {
				row['PARTY_NUM_URL'] = '<a href="#" onclick="toGoCustDetail(\''
						+ row['PARTY_ID'] + '\');">' + row['PARTY_NUM']
						+ '</a>';
			});
			query();
		}

		function query() {
			if (!orgNum) {
				alert("原机构信息必须存在");
				return;
			}
			var o = form.getData();
			o.sqlName = "com.bos.csm.transfer.transfer.getPartyInfoToSelect";
			o.item.userNum = userNum;
			o.item.orgNum = orgNum;
			grid.load(o);
		}
		function reset() {
			form.reset();
		}

		function createDetail() {
			actionDiv.loading("数据处理中...");
			var rows = grid.getSelecteds();
			if (rows.length == 0) {
				alert("请选择一条数据");
				actionDiv.unmask();
				return;
			}
			var params = [];
			for (var i = 0; i < rows.length; i++) {
				params[i] = {
					"partyId" : rows[i].PARTY_ID,
					"userNum" : rows[i].USER_NUM
				};
			}
			var json = nui.encode({
				"transferId" : transferId,
				"orgNum" : orgNum,
				"params" : params
			});
			$
					.ajax({
						url : "com.bos.csm.transfer.transfer.createDetailByParty.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						async : false,
						contentType : 'text/json',
						success : function(data) {
							actionDiv.unmask();
							if (data.msg) {
								alert(data.msg);
								return;
							} else {
								alert(actionSuccess);
								query();
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							actionDiv.unmask();
							nui.alert(jqXHR.responseText);
						}
					});
		}
	</script>
</body>
</html>
