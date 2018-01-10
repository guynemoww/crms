<%@page import="com.bos.pub.entity.name.CsmTableName"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script>
	function getCsmxfe(transferId) {
		var entityName =
<%=JspUtil.getStrHaveSign(CsmTableName.TB_CSMXFE_TRANSFER)%>
	;
		var json = nui.encode({
			"item" : {
				"_entity" : entityName,
				"transferId" : transferId
			}
		});
		var obj = null;
		$.ajax({
			url : "com.bos.pub.dao.expandEntity.biz.ext",
			type : 'POST',
			data : json,
			async : false,
			cache : false,
			contentType : 'text/json',
			success : function(mydata) {
				if (mydata.msg != null) {
					alert(mydata.msg);
				} else {
					obj = mydata.item;
				}
			}
		});
		return obj;
	}

	function approve(csmxfe) {
		if (!nui.confirm("确认之后，数据无法编辑，是否确定操作?")) {
			return;
		}
		actionDiv.loading("数据处理中...");
		var json = nui.encode({
			"transfer" : csmxfe
		});
		$.ajax({
			url : "com.bos.csm.transfer.transfer.approveCsmxfe.biz.ext",
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

	function createCsmxfe(type, workFlow) {
		if (type == null || type == "") {
			alert("请选择业务类型");
			return;
		}
		var json = nui.encode({
			"transferType" : type,
			"workFlow" : false == workFlow ? false : true
		});
		$.ajax({
			url : "com.bos.csm.transfer.transfer.createCsmxfe.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			async : false,
			contentType : 'text/json',
			success : function(data) {
				if (data.msg) {
					alert(data.msg);
					return;
				} else {
					debugger;
					showInfo(data.csmxfe.transferId, data.csmxfe.processId);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}
	function showInfo(transferId, processId) {
		debugger;
		if ("" == processId || "null" == processId) {
			processId = null;
		}
		nui.open({
				url : nui.context
						+ "/csm/transfer/csmxfe_tree.jsp?bizId="
						+ transferId
						+ (processId != null ? ("&processInstId=" + processId)
								: ""),
				title : "查看记录",
				width : 1024,
				height : 720,
				showMaxButton : true,
				ondestroy : function() {
					query();
				}
			});
	}

	function selectOrg(e) {
		nui.open({
			url : nui.context + "/pub/sys/select_org_tree.jsp?clear=0",
			showMaxButton : true,
			title : "选择机构",
			width : 450,
			height : 650,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					e.setText(null);
					e.setValue(data.orgcode);
				}
			}
		});
	}

	function selectUser(orgnum, e) {
		var newOrgNum = nui.get(orgnum).getValue();
		if (newOrgNum == "") {
			newOrgNum = null;
		}
		nui.open({
			url : nui.context
					+ "/pub/user/select_user.jsp?orgMode=legorg&clear=0"
					+ (newOrgNum == null ? "" : ("&orgNum=" + newOrgNum)),
			showMaxButton : true,
			title : "选择客户经理",
			width : 800,
			height : 550,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					e.setText(null);
					e.setValue(data.userNum);
					if (newOrgNum == null) {
						nui.get(orgnum).setText(null);
						nui.get(orgnum).setValue(data.orgNum);
					}
				}
			}
		});
	}
</script>
</body>
</html>
