<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
  - Author(s):chenchuan
  - Date: 2015-06-15
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>

<div id="form1">
	<input name="roleType" id="roleType" required="true" class="nui-hidden" value="2" />
	<div class="nui-dynpanel" columns="4">

		<label>保留客户权：</label>
		<input id="keepCsm" name="keepCsm" required="true" class="nui-combobox" dictTypeId="XD_CSMXFE001" />

		<label>保留业务权：</label>
		<input id="keepBiz" name="keepBiz" required="true" class="nui-combobox" dictTypeId="XD_CSMXFE001" />

		<label>新会计机构：</label>
		<input id="loanOrgId" name="loanOrgId" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" />

		<label>变更后所在机构：</label>
		<input id="newOrgNum" name="newOrgNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" value="<%=request.getParameter("oldOrgNum")%>" dictTypeId="org" enabled="false" />

		<label>变更后客户经理：</label>
		<input id="newUserNum" name="newUserNum" required="true" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32" />

		<label>移交原因：</label>
		<input id="transPerson" name="transPerson" required="true" class="nui-textbox nui-form-input" />

		<label>经办人：</label>
		<input name="handlingUserNum" required="true" value="<%=((UserObject) session.getAttribute("userObject"))
					.getUserName()%>" class="nui-textbox nui-form-input" Enabled="false" />
	</div>
	<div>
		<font color="blue"> <label style="color: blue;">注：</label> <br /> <label>保留客户权：客户信息不转移，可以继续对客户做业务</label> <br /> <label>保留业务权：业务信息不转移，只移交账务信息</label>
		</font>
	</div>
</div>

<div class="nui-toolbar" style="border-bottom: 0; text-align: center; padding-right: 20px; text-align: right;">
	<a class="nui-button" iconCls="icon-save" id="btnSave" onclick="save()">保存</a>
	<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var oldUserNum =
<%=JspUtil.getParameterHaveSign(request, "oldUserNum")%>
	;
	var oldOrgNum =
<%=JspUtil.getParameterHaveSign(request, "oldOrgNum")%>
	;
<%if (UserUtil.isManager()) {%>
	init("1");
<%} else if (UserUtil.isZHPresident()) {%>
	init("2");
<%}%>
	function init(v) {
		nui.get('roleType').setValue(v);
	}
	function GetData() {
		var data = form.getData();
		return data;
	}
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请按规则填写信息");
			return;
		}
		if (oldUserNum == nui.get("newUserNum").getValue()
				&& oldOrgNum == nui.get("newOrgNum").getValue()) {
			nui.alert("原客户经理不能和变更后客户经理相同");
			return;
		}
		nui.confirm("确定移交这些业务吗？", "确认", function(action) {
			if (action != "ok") {
				return;
			}
			GetData();
			CloseWindow("ok");
			return;
		});
	}
	function GetData() {
		var data = form.getData();
		return data;
	}
	// 新机构信息
	function selectEmpOrgs(e) {
		var btnEdit = this;
		var url = "/pub/orgDemolition/creditMove/select_all_org_tree.jsp";
		if (nui.get("roleType").getValue() == "2") {//非客户经理
			url = "/pub/sys/select_org_tree.jsp";
		}
		nui.open({
			url : nui.context + url,
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
						btnEdit.setValue(data.orgcode);
						btnEdit.setText(data.orgname);
						nui.get("newUserNum").setValue();
						nui.get("newUserNum").setText();
					}
				}
			}
		});
	}
	// 新客户经理
	function selectCustManegers(e) {
		var newOrgNum = nui.get("newOrgNum").getValue();
		if (newOrgNum == "") {
			nui.alert("请选择变更后所在机构");
			return;
		} else {
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum="
								+ newOrgNum,
						showMaxButton : true,
						title : "选择客户经理",
						width : 850,
						height : 450,
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

	}
</script>
</body>
</html>
