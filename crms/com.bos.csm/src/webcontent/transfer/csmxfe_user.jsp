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
	<div id="actionDiv" class="nui-toolbar" style="border: none; display: none;">
		<a class="nui-button" iconCls="icon-search" onclick="add()">添加</a>
		<a class="nui-button" iconCls="icon-reset" onclick="remove()">删除</a>
		<a id="approveBtn" class="nui-button" iconCls="icon-reset" onclick="approve(csmxfe)">确认</a>
	</div>
	<div class=nui-fit>
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.dao.search.biz.ext" dataField="items" allowAlternating="true" multiSelect="false" sortMode="client">
			<div property="columns">
				<div type="checkcolumn"></div>
				<div field="OLD_USER_ID" headerAlign="center" allowSort="true" width="100" dictTypeId="user">原客户经理</div>
				<div field="PARTY_NUM" headerAlign="center" align="right" allowSort="true" width="100">移交客户数量</div>
				<div field="APPROVE_NUM" headerAlign="center" align="right" allowSort="true" width="100">移交批复数量</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var actionDiv = nui.get("actionDiv");
		var transferId =
	<%=JspUtil.getParameterHaveSign(request, "transferId")%>
		;
		var csmxfe;
		init();
		function init() {
			csmxfe = getCsmxfe(transferId);
			if ("4" == csmxfe.transferType
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
						+ "/csm/transfer/csmxfe_user_select.jsp?transferId="
						+ transferId + "&orgNum=" + csmxfe.oldOrgId,
				showMaxButton : true,
				title : "选择移交经理信息",
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
					"userNum" : row.OLD_USER_ID
				}
			});
			$
					.ajax({
						url : "com.bos.csm.transfer.transfer.removeDetailByUser.biz.ext",
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
			grid.load({
				"sqlName" : "com.bos.csm.transfer.transfer.getUserInfo",
				"item" : {
					"transferId" : transferId
				}
			});
		}
	</script>
</body>
</html>

