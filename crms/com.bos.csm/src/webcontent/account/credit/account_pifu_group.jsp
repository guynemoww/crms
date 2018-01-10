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
<title>批复查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>
</head>
<body>

	<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
		<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.pifuGroup" />
		<div class="nui-dynpanel" columns="8">
			<label class="nui-form-label">机构名称：</label>
			<input id="item.orgNum" name="item.orgNum" value="<%=GitUtil.getCurrentOrgCd()%>" dictTypeId="org" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg" />
			<label>客户名称：</label>
			<input name="item.partyName" class="nui-textbox nui-form-input" />
			<label>证件类型：</label>
			<input id="item.mCertType" name="item.mCertType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" allowInput="false" />
			<label>证件号码：</label>
			<input id="item.mCertNum" class="nui-textbox nui-form-input" name="item.mCertNum" onvalidation="" />
			<label>批复编号：</label>
			<input id="item.approvalNum" class="nui-textbox nui-form-input" name="item.approvalNum" />
			<%
				if (UserUtil.isManager()) {
			%>
			<input id="item.userNum" class="nui-hidden nui-form-input" name="item.userNum" dictTypeId="org" value="<%=GitUtil.getCurrentUserId()%>" />
			<%
				}
			%>
		</div>
		<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">
			<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
			<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
		</div>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" allowResize="true" showReloadButton="false" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client" allowAlternating="true">
		<div property="columns">
			<div type="indexcolumn">序号</div>
			<div field="ORG_NUM" allowSort="true" width="" headerAlign="center" autoEscape="false" dictTypeId="org">机构名称</div>
			<div field="PARTY_NAME" allowSort="true" width="" headerAlign="center" dictTypeId="">客户名称</div>
			<div field="APPROVAL_NUM" allowSort="true" width="" headerAlign="center">批复编号</div>
			<div field="BIZ_TYPE_FLAG" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0002">业务性质</div>
			<div field="CURRENCY_CD" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
			<div field="SUM1" allowSort="true" width="" headerAlign="center" dictTypeId="">批复金额</div>
			<div field="SUM3" allowSort="true" width="" headerAlign="center" dictTypeId="">已用金额</div>
			<div field="SUM2" allowSort="true" width="" headerAlign="center" dictTypeId="">可用余额</div>
			<div field="START_DATE" allowSort="true" width="" dateFormat="yyyy-MM-dd" headerAlign="center" dictTypeId="">起始日</div>
			<div field="END_DATE" allowSort="true" width="" dateFormat="yyyy-MM-dd" headerAlign="center" dictTypeId="">到期日</div>
			<div field="USER_NUM" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
			<!-- <div field="firstName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">第一责任人</div> -->
		</div>
	</div>

	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			if (form.isValid() == false) {
				nui.alert("请输入必填项。");
				return;
			}

			var o = form.getData();//逻辑流必须返回total
			if (o.item.mCertType || o.item.mCertNum) {
				o.item.memberInfo = "memberInfo";
			} else {
				o.item.memberInfo = null;
			}

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
				url : nui.context
						+ "/pub/sys/select_org_tree.jsp",
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
							debugger;
							self.orglevel = data.orglevel;
							btnEdit.setValue(data.orgCode);
							btnEdit.setText(data.orgname);
						}
					}
				}
			});
		}

 		grid.on("preload",function(e) {
				if (!e.data || e.data.length < 1) {
					return;
				}
				for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
					e.data[i]['PARTY_NAME'] = '<a href="#" onclick="toGoCustDetail(\''
							+ e.data[i].GROUP_PARTY_ID
							+ '\');">'
							+ e.data[i]['PARTY_NAME'] + '</a>';
					e.data[i]['APPROVAL_NUM'] = '<a href="#" onclick="bizView3231(\''
							+ e.data[i].APPROVAL_NUM
							+ '\');">'
							+ e.data[i]['APPROVAL_NUM'] + '</a>';
				}
			}); 
			
			
	</script>
</body>
</html>