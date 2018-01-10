<%@page import="com.bos.pub.UserUtil"%>
<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王振华
  - Date: 2016-6-3 
  - Description:
-->
<head>
<title>固定查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display: none;"></iframe>
	<div id="form1" class="nui-form">
		<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.loanPerson.gdbw" />
		<div class="nui-dynpanel" columns="4">
			<input id="item.orgId" class="nui-hidden" name="item.orgId" value="<%=GitUtil.getCurrentOrgId()%>" />
			<%
				if (UserUtil.isManager()) {
			%>
			<input id="item.userNum" class="nui-hidden" name="item.userNum" value="<%=GitUtil.getCurrentUserId()%>" />
			<%
				}
			%>
		</div>
		<!--  
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
											
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>	
						
					</div>
					-->
	</div>
	<div class="nui-fit">
		<div id="datagrid1" class="nui-datagrid" style="height: 100%" allowAlternating="true" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" multiSelect="false" sortMode="client">
			<div property="columns">
				<div field="orgName" allowSort="true" width="80px" headerAlign="center" dictTypeId="">机构</div>

				<div header="正常" headerAlign="center">
					<div property="columns">
						<div field="zchs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">户数</div>
						<div field="zcbs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">笔数</div>
						<div field="zcye" allowSort="true" width="60px" headerAlign="center" dictTypeId="">余额</div>
					</div>
				</div>

				<div header="关注" headerAlign="center">
					<div property="columns">
						<div field="gzhs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">户数</div>
						<div field="gzbs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">笔数</div>
						<div field="gzdk" allowSort="true" width="70px" headerAlign="center" dictTypeId="">垫款余额</div>
						<div field="gzye" allowSort="true" width="70px" headerAlign="center" dictTypeId="">余额</div>
					</div>
				</div>

				<div header="次级" headerAlign="center">
					<div property="columns">
						<div field="cjhs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">户数</div>
						<div field="cjbs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">笔数</div>
						<div field="cjdk" allowSort="true" width="70px" headerAlign="center" dictTypeId="">垫款余额</div>
						<div field="cjye" allowSort="true" width="70px" headerAlign="center" dictTypeId="">余额</div>
					</div>
				</div>

				<div header="可疑" headerAlign="center">
					<div property="columns">
						<div field="kyhs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">户数</div>
						<div field="kybs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">笔数</div>
						<div field="kydk" allowSort="true" width="70px" headerAlign="center" dictTypeId="">垫款余额</div>
						<div field="kyye" allowSort="true" width="70px" headerAlign="center" dictTypeId="">余额</div>
					</div>
				</div>

				<div header="损失" headerAlign="center">
					<div property="columns">
						<div field="sshs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">户数</div>
						<div field="ssbs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">笔数</div>
						<div field="ssdk" allowSort="true" width="70px" headerAlign="center" dictTypeId="">垫款余额</div>
						<div field="ssye" allowSort="true" width="70px" headerAlign="center" dictTypeId="">余额</div>
					</div>
				</div>

				<div header="合计" headerAlign="center">
					<div property="columns">
						<div field="hjhs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">户数</div>
						<div field="hjbs" allowSort="true" width="40px" headerAlign="center" dictTypeId="">笔数</div>
						<div field="hjdk" allowSort="true" width="70px" headerAlign="center" dictTypeId="">垫款余额</div>
						<div field="hjye" allowSort="true" width="70px" headerAlign="center" dictTypeId="">余额</div>
					</div>
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
			var o = form.getData();//逻辑流必须返回total
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
							btnEdit.setValue(data.orgcode);
							btnEdit.setText(data.orgname);
						}
					}
				}
			});
		}

		// 经办人
		function selectCustManegers(e) {
			var orgNum = nui.get("item.orgcode").getValue();
			if (orgNum == "") {
				alert("请先选择机构");
				return;
			} else {
				var btnEdit = this;
				nui.open({
					url : nui.context + "/pub/user/select_user.jsp?orgNum="
							+ orgNum,
					showMaxButton : true,
					title : "选择客户经理",
					width : 800,
					height : 500,
					ondestroy : function(action) {
						if (action == "ok") {
							var iframe = this.getIFrameEl();
							var data = iframe.contentWindow.getData();
							data = nui.clone(data);
							if (data) {
								//  alert(nui.encode(data));
								btnEdit.setText(null);
								btnEdit.setValue(data.userId);
							}
						}
					}
				});
			}
		}

		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
		});

		function dc() {
			var ifrm = document.getElementById("exportFrame");
			git.mask();
			var o = form.getData();//逻辑流必须返回total
			var json = nui.encode(o);

			$.ajax({
				url : "com.bos.csm.pub.ibatis.xwhkDownloadEXCEL.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						git.unmask();
						ifrm.src = nui.context
								+ "/pub/io/file/download.jsp?deleteFile=true";

					} else {
						git.unmask();
						nui.alert("下载数据有误！");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}
	</script>
</body>
</html>