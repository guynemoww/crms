<%@page import="com.bos.pub.GitUtil"%>
<%@page import="com.bos.pub.UserUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>联保客户查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: auto;">
		<div title="联保客户查询">
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.csm.guarGroup" />
				<div class="nui-dynpanel" columns="8">
					<label class="nui-form-label">机构名称：</label>
					<input id="item.orgId" name="item.orgId" value="<%=GitUtil.getCurrentOrgId()%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg" />
					<label>联保小组编号：</label>
					<input name="item.partyNum" class="nui-textbox nui-form-input" />
					<label>联保小组成员：</label>
					<input name="item.partyName" class="nui-textbox nui-form-input" />

					<label>成员统一社会信用代码：</label>
					<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" required="false" />
					<label>成员营业执照：</label>
					<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" required="false" />
					<label>成员组织机构代码：</label>
					<input id="item.orgRegisterCd" name="item.orgRegisterCd" required="false" class="nui-textbox nui-form-input" />
					<label>成员证件类型：</label>
					<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" allowInput="false" />
					<label>成员证件号码：</label>
					<input id="item.certNum" class="nui-textbox nui-form-input" name="item.certNum" onvalidation="" />
					<label>成员中征码：</label>
					<input id="item.middelCode" name="item.middelCode" required="false" class="nui-textbox nui-form-input" />
					<%
						if (UserUtil.isManager()) {
					%>
					<input id="item.userNum" class="nui-hidden nui-form-input" name="item.userNum" value="<%=GitUtil.getCurrentUserId()%>" />
					<%
						}
					%>
				</div>
				<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
				</div>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" allowResize="true" showReloadButton="false" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client"  allowAlternating="true">
				<div property="columns">
					<div type="indexcolumn">序号</div>
					<div field="partyNum" allowSort="true" width="" headerAlign="center" autoEscape="false">联保小组编号</div>
					<div field="jointGuaranteeType" allowSort="true" width="" headerAlign="center" dictTypeId="XD_KHCD4001">联保小组类型</div>
					<div field="memberNum" allowSort="true" width="" headerAlign="center">成员人数</div>
					<div field="jointGuaranteeStatus" allowSort="true" width="" headerAlign="center" dictTypeId="XD_KHCD0231">联保小组状态</div>
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
			if (form.isValid() == false) {
				nui.alert("请输入必填项。");
				return;
			}
			//alert(nui.get("item.userNum").getValue());
			var o = form.getData();//逻辑流必须返回total
			if (o.item.partyName || o.item.unifySocietyCreditNum
					|| o.item.registerCd || o.item.orgRegisterCd
					|| o.item.certType || o.item.certNum || o.item.middelCode) {
				o.item.memeberInfo = "memeberInfo";
			} else {
				o.item.memeberInfo = null;
			}
			debugger;
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

		grid
				.on(
						"preload",
						function(e) {
							if (!e.data || e.data.length < 1) {
								return;
							}
							for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
								e.data[i]['partyNum'] = '<a href="#" onclick="toGoCustDetail(\''
										+ e.data[i].partyId
										+ '\');">'
										+ e.data[i]['partyNum'] + '</a>';
							}
						});
	</script>
</body>
</html>