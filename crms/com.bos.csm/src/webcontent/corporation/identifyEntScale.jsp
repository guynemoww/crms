<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): WangHui
  - Date: 2017-05-08 17:15:08
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		boolean enabled = !UserUtil.isManager();
	%>
	<div class="nui-fit">
		<div id="form1">
			<input id="sqlName" name="sqlName" class="nui-hidden" />
			<div id="corIdentify" class="nui-dynpanel" columns="6">
				<label>机构名称：</label>
				<input id="item.orgNum" name="item.orgNum" allowInput="false" enabled="<%=enabled%>" class="nui-buttonedit" value="<%=GitUtil.getCurrentOrgCd()%>" dictTypeId="org" onbuttonclick="selectEmpOrgs" />
				<label>客户经理：</label>
				<input id="item.userNum" name="item.userNum" allowInput="false" enabled="<%=enabled%>" class="nui-buttonedit" value="<%=GitUtil.getCurrentUserId()%>" dictTypeId="user" onbuttonclick="selectCustManegers" />
				<label>客户名称：</label>
				<input name="item.partyName" required="false" class="nui-textbox" />
				<label>证件类型：</label>
				<input name="item.certType" class="nui-dictcombobox" dictTypeId="CDKH0002" allowInput="false" />
				<label>证件号码：</label>
				<input name="item.certNum" class="nui-textbox" />
			</div>
			<div id="corIdentifyGrid" class="nui-toolbar" style="text-align: right; border: none">
				<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
				<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
			</div>
		</div>
		<div class="nui-fit">
			<div id="grid" class="nui-datagrid" style="height: 100%" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" sortMode="client" allowAlternating="true">
				<div property="columns">
					<div field="PARTY_NAME" allowSort="true" headerAlign="center" align="center" width="200">客户名称</div>
					<div field="CERT_TYPE" allowSort="true" headerAlign="center" align="center" dictTypeId="CDKH0002">证件类型</div>
					<div field="CERT_NUM" allowSort="true" headerAlign="center" align="center">证件号</div>
					<div field="SCALE_CODE" allowSort="true" headerAlign="center" align="center" dictTypeId="CDKH0027">现企业规模</div>
					<div field="OLD_SCALE_CODE" allowSort="true" headerAlign="center" align="center" dictTypeId="CDKH0027">原企业规模</div>
					<div field="ORG_NUM" allowSort="true" headerAlign="center" align="center" dictTypeId="org">经办机构</div>
					<div field="USER_NUM" allowSort="true" headerAlign="center" align="center" dictTypeId="user">经办人</div>
					<div field="CREATE_DATE" allowSort="true" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">经办时间</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	nui.parse();

	var form = new nui.Form("#form1");
	var grid = nui.get("grid");
	
	var qote = "<%=request.getParameter("qote") %>";
	var partyId = "<%=request.getParameter("partyId") %>";
	
	if(qote == 2){//客户信息进来的入口 都只能查看
		nui.get("corIdentify").hide();
		nui.get("corIdentifyGrid").hide();
	}
	
	window.onload = function() {
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {
				if (e.data[i]) {
					setA_partyName(e.data[i]);
				}
			}
		});
		query();
	}

	function query() {
		var o = form.getData();
		o.item.partyId = partyId;
		if(partyId == "null"){//客户信息里 查询某个客户的企业规模认定
			o.item.partyId = null;
		}
		o.sqlName = "com.bos.csm.corporation.corporation.searchCorpScaleInfo";
		if (o.item.orgNum || o.item.userNum) {
			o.item.searchMode = "searchByOrgOrUser";
		} else {
			o.item.searchMode = null;
		}
		grid.load(o);
	}

	function reset() {
		form.reset();
		query();
	}

	function selectEmpOrgs(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context
					+ "/pub/sys/select_org_tree.jsp?searchMode=legorg",
			showMaxButton : true,
			title : "选择机构",
			width : 350,
			height : 400,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.GetData();
					btnEdit.setText(null);
					btnEdit.setValue(data.orgcode);
				} else if (action == "clear") {
					btnEdit.setText(null);
					btnEdit.setValue(null);
				}
				nui.get("item.userNum").setText(null);
				nui.get("item.userNum").setValue(null);
			}
		});
	}

	function selectCustManegers(e) {
		var newOrgNum = nui.get("item.orgNum").getValue();
		if (!newOrgNum || newOrgNum == "") {
			nui.alert("请选择变更后所在机构");
			return;
		}
		var btnEdit = this;
		nui
				.open({
					url : nui.context + "/pub/user/select_user.jsp?orgNum="
							+ newOrgNum,
					showMaxButton : true,
					title : "选择客户经理",
					width : 850,
					height : 450,
					ondestroy : function(action) {
						if (action == "ok") {
							var iframe = this.getIFrameEl();
							var data = iframe.contentWindow.getData();
							btnEdit.setText(null);
							btnEdit.setValue(data.userNum);
						} else if (action == "clear") {
							btnEdit.setText(null);
							btnEdit.setValue(null);
						}
					}
				});

	}

	function setA_partyName(obj) {
		//客户链接
		obj['partyName'] = '<a href="javascript:void(0);" onclick="clickCust('
				+ getValueStr(obj.partyId) + ',' + getValueStr(obj.partyNum)
				+ ',' + getValueStr(obj.corpCustomerTypeCd) + ','
				+ getValueStr(obj.partyTypeCd) + ');return false;">'
				+ obj['partyName'] + '</a>';
	}

	function clickCust(partyId, partyNum, corpCustomerTypeCd, partyTypeCd) {
		var openUrl;
		if (partyTypeCd == "01") {
			openUrl = nui.context
					+ "/csm/corporation/csm_corporation_tree.jsp?partyId="
					+ partyId + "&partyNum=" + partyNum + "&cusType="
					+ corpCustomerTypeCd + "&qote=1";
		} else {
			openUrl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
					+ partyId + "&partyNum=" + partyNum + "&qote=1";
		}
		open(openUrl);
	}

	function getValueStr(value) {
		return '\'' + value + '\'';
	}

	function open(openUrl) {
		nui.open({
			url : openUrl,
			showMaxButton : true,
			title : "",
			width : 1024,
			height : 768,
			state : "max"
		});
	}
</script>
</html>