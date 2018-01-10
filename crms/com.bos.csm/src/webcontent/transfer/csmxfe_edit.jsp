<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.pub.entity.name.CsmTableName"%>
<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s):chenchuan
  - Date: 2015-06-15
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<%@include file="/csm/transfer/csmxfe_common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>

<div id="form1">

	<div class="nui-dynpanel" columns="4" id="showDiv">
		<label id="transferTypeLab">业务类型：</label>
		<input id="csmxfe.transferType" name="csmxfe.transferType" required="true" class="nui-text" dictTypeId="XD_CSMXFE001" enabled="false" />
		<label></label>
		<label></label>

		<label id="oldOrgIdLab">原机构名称：</label>
		<input id="csmxfe.oldOrgId" name="csmxfe.oldOrgId" required="true" class="nui-buttonEdit" allowInput="false" dictTypeId="org" onbuttonclick="selectOrg(this,'csmxfe.oldUserId')" />
		<label id="oldUserIdLab">原客户经理：</label>
		<input id="csmxfe.oldUserId" name="csmxfe.oldUserId" allowInput="false" class="nui-buttonEdit" dictTypeId="user" onbuttonclick="selectUser(this,'csmxfe.oldOrgId')" />

		<label id="newOrgIdLab">新机构名称：</label>
		<input id="csmxfe.newOrgId" name="csmxfe.newOrgId" allowInput="false" class="nui-buttonEdit" dictTypeId="org" onbuttonclick="selectOrg(this,'csmxfe.newUserId','csmxfe.newLoanOrg')" />
		<label id="newUserIdLab">新客户经理：</label>
		<input id="csmxfe.newUserId" name="csmxfe.newUserId" allowInput="false" class="nui-buttonEdit" dictTypeId="user" onbuttonclick="selectUser(this,'csmxfe.newOrgId')" />
		<label id="xfeBizLab">是否移交业务：</label>
		<input id="csmxfe.xfeBiz" name="csmxfe.xfeBiz" required="true" class="nui-dictcombobox" dictTypeId="XD_0002" onvaluechanged="xfeBizChange()" />
		<label id="keepCsmLab">保留客户共享权：</label>
		<input id="csmxfe.keepCsm" name="csmxfe.keepCsm" required="true" class="nui-dictcombobox" dictTypeId="XD_0002" />
		<label id="xfeAcctLab">是否移交账务：</label>
		<input id="csmxfe.xfeAcct" name="csmxfe.xfeAcct" required="true" class="nui-dictcombobox" dictTypeId="XD_0002" onvaluechanged="xfeAcctChange" />
		<label id="newLoanOrgLab">新会计机构：</label>
		<input id="csmxfe.newLoanOrg" name="csmxfe.newLoanOrg" allowInput="false" class="nui-buttonEdit" dictTypeId="acctOrg" onbuttonclick="selectAccOrg" enabled="false" />
		<label>经办人：</label>
		<input name="csmxfe.userId" class="nui-text" enabled="false" dictTypeId="user" />
		<input name="csmxfe.transferId" class="nui-hidden" />
	</div>
	</fieldset>
	<div class="nui-dynpanel" columns="4">
		<label>移交原因：</label>
		<input id="csmxfe.remark" name="csmxfe.remark" colspan="3" required="true" class="nui-textarea" style="width: 100%; height: 75px" vtype="maxLength: 1200" />
	</div>
</div>

<div class="nui-toolbar" style="padding-right: 20px; text-align: right; border: none;">
	<a id="removeBtn" class="nui-button" iconCls="icon-save" id="btnSave" onclick="remove()">删除申请</a>
	<a id="toActionBtn" class="nui-button" iconCls="icon-save" id="btnSave" onclick="toAction()">确定操作</a>
	<a id="saveBtn" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var proFlag =
<%=JspUtil.getParameterHaveSign(request, "proFlag")%>
	;//1：可修改。0：不可修改
	var transferId =
<%=JspUtil.getParameterHaveSign(request, "transferId")%>
	;
	var actionType =
<%=JspUtil.getParameterHaveSign(request, "actionType")%>
	;
	var csmxfe;
	initPage();
	function initPage() {
		csmxfe = getCsmxfe(transferId);
		if (!csmxfe) {
			return;
		}
		if (csmxfe.status == "10") {
			nui.get("toActionBtn").show();
		} else {
			nui.get("toActionBtn").hide();
		}
		if (csmxfe.status != "00") {
			nui.get("removeBtn").hide();
			nui.get("saveBtn").hide();
		}
		form.setData({
			"csmxfe" : csmxfe
		});
		if (csmxfe.transferType == "1") {
			type1();
		} else if (csmxfe.transferType == "2") {
			type2();
		} else if (csmxfe.transferType == "4") {
			type4();
		}
		nui.get('showDiv').refreshTable();
	}
	function type1() {
		setValueByNull("csmxfe.xfeBiz", "1");
		nui.get("csmxfe.xfeBiz").setEnabled(false);
		nui.get("csmxfe.newUserId").setRequired(true);
	}
	function type2() {
		setValueByNull("csmxfe.xfeBiz", "1");
		setValueByNull("csmxfe.xfeAcct", "0");
		setValueByNull("csmxfe.keepCsm", "0");
		$("#keepCsmLab").hide();
		nui.get("csmxfe.keepCsm").hide();
		debugger;
		if (csmxfe.processId) {
			var userNum =
<%=JspUtil.getStrHaveSign(GitUtil.getCurrentUserId())%>
	;
			var orgNum =
<%=JspUtil.getStrHaveSign(GitUtil.getCurrentOrgCd())%>
	;
			nui.get("csmxfe.oldUserId").setValue(userNum);
			nui.get("csmxfe.oldUserId").setEnabled(false);
			nui.get("csmxfe.oldOrgId").setValue(orgNum);
			nui.get("csmxfe.oldOrgId").setEnabled(false);
		}
	}

	function type4() {
		setValueByNull("csmxfe.xfeBiz", "1");
		setValueByNull("csmxfe.keepCsm", "0");
		nui.get("csmxfe.xfeBiz").setEnabled(false);
		nui.get("csmxfe.keepCsm").setEnabled(false);
		$("#oldUserIdLab").hide();
		nui.get("csmxfe.oldUserId").hide();
		$("#newUserIdLab").hide();
		nui.get("csmxfe.newUserId").hide();
		$("#xfeBizLab").hide();
		nui.get("csmxfe.xfeBiz").hide();
		$("#keepCsmLab").hide();
		nui.get("csmxfe.keepCsm").hide();
	}
	function setValueByNull(name, value) {
		if (!nui.get(name).getValue()) {
			nui.get(name).setValue(value);
		}
	}
	function xfeBizChange() {
		if ("1" == nui.get("csmxfe.xfeBiz").getValue()) {
			nui.get("csmxfe.keepCsm").setEnabled(true);
		} else {
			if (!nui.get("csmxfe.keepCsm").getValue()) {
				nui.get("csmxfe.keepCsm").setValue("1");
			}
			nui.get("csmxfe.keepCsm").setEnabled(false);
		}
	}
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请按规则填写信息");
			return;
		}
		var json = nui.encode(form.getData());
		$.ajax({
			url : "com.bos.csm.transfer.transfer.saveCsmxfe.biz.ext",
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
					alert(actionSuccess);
					initPage();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}

	function toAction() {
		if (nui.confirm("是否确定执行最后步骤?")) {
			if (nui.confirm("该操作将执行数据操作，数据改变不可逆，请谨慎选择!")) {
				realAction();
			}
		}
	}
	function remove() {
		if (!nui.confirm("是否确定删除该申请信息?")) {
			return;
		}
		var json = nui.encode({
			"transferId" : transferId
		});
		$.ajax({
			url : "com.bos.csm.transfer.transfer.removeCsmxfe.biz.ext",
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
					closeWindow(self, '/csm/transfer/csm_xfe_list.jsp');
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}

	function closeWindow(window, url) {
		if (window.CloseOwnerWindow) {
			window.CloseOwnerWindow("submit");
		}
		if (window.parent && window.parent != top) {
			closeWindow(window.parent);
		} else {
			window.location.replace(nui.context + url);
		}
	}

	function realAction() {
		var xfeId = form.getData().csmxfe.transferId;
		if (!xfeId || xfeId == "") {
			return;
		}
		var json = nui.encode({
			"csmxfeId" : xfeId
		});
		$.ajax({
			url : "com.bos.csm.transfer.transfer.actionCsmxfe.biz.ext",
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
					initPage();
					alert(actionSuccess);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				nui.alert(jqXHR.responseText);
			}
		});
	}
	function xfeAcctChange() {
		var v = nui.get("csmxfe.xfeAcct").getValue();
		if ("1" != v) {
			nui.get("csmxfe.newLoanOrg").setValue(null);
			nui.get("csmxfe.newLoanOrg").setText(null);
			nui.get("csmxfe.newLoanOrg").setEnabled(false);
			nui.get("csmxfe.newLoanOrg").setEnabled(false);
		} else {
			nui.get("csmxfe.newLoanOrg").setEnabled(true);
			nui.get("csmxfe.newLoanOrg").setRequired(true);
		}
	}

	function selectOrg(e, cleanName, cleanName2) {
		nui.open({
			url : nui.context
					+ "/pub/sys/select_org_tree.jsp?searchMode=legorg&clear=0",
			showMaxButton : true,
			title : "选择机构",
			width : 450,
			height : 650,
			async : true,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					if (cleanName) {
						nui.get(cleanName).setText(null);
						nui.get(cleanName).setValue(null);
					}
					if (cleanName2) {
						nui.get(cleanName2).setText(null);
						nui.get(cleanName2).setValue(null);
					}
					e.setText(null);
					e.setValue(data.orgcode);
					e._OnValueChanged();
				}
			}
		});
	}

	function selectUser(e, orgName) {
		var btnEdit = this;
		var orgId = nui.get(orgName).getValue();
		nui.open({
			url : nui.context
					+ "/pub/user/select_user.jsp?clear=0&orgMode=legorg"
					+ (orgId ? ("&orgNum=" + orgId) : ""),
			showMaxButton : true,
			title : "选择客户经理",
			width : 850,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					if (data) {
						if (!orgId) {
							nui.get(orgName).setText(null);
							nui.get(orgName).setValue(data.orgNum);
							nui.get(orgName)._OnValueChanged();
						}//机构信息变动会清除经理信息，所以先设置机构信息
						e.setText(null);
						e.setValue(data.userNum);
					}
				}
			}
		});
	}

	function selectAccOrg() {
		var org = nui.get("csmxfe.newOrgId").getValue();
		debugger;
		if (!org || org == "") {
			alert("请先选择新机构信息");
			return;
		}
		nui.open({
			url : nui.context + "/pay/payout_info/pay_org_select.jsp?orgCode="
					+ org,
			showMaxButton : true,
			title : "选择会计机构",
			width : 800,
			height : 500,
			ondestroy : function(action) {
				if (action == 'ok') {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.getData();
					nui.get("csmxfe.newLoanOrg").setText(null);
					nui.get("csmxfe.newLoanOrg").setValue(data.ACC_ORG_NO);
					if ("1" == data.COL1) {
						nui.get("csmxfe.newLoanOrg").setText(data.ACC_ORG_ID);
					}
				}
			}
		});
	}
</script>
</body>
</html>
