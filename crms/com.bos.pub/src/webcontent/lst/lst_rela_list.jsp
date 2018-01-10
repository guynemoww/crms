<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-26 8:26:27
  - Description:
-->
<head>
<title>关联方名单管理</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>

</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 115%;">
		<div title="关联方名单管理">
			<form id="form2" method="post" action="com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile">
				<input id="entity" name="item/entity" value="com.bos.lst.lst.TbLstRela" class="nui-hidden" />
				<input name="importCd" value='101' id="importCd" class="nui-hidden" />
			</form>
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.lst.lst.relaList" />
				<div class="nui-dynpanel" columns="6">
					<label>客户名称：</label>
					<input name="item.partyName" id="item.partyName" class="nui-textbox nui-form-input" />
					<label>证件类型：</label>
					<input name="item.certType" id="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002" />
					<label>证件号码：</label>
					<input name="item.certNum" id="item.certNum" class="nui-textbox nui-form-input" />
				</div>
				<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
				</div>
				<!-- 判断登录人员的角色 -->


			</div>

			<div class="nui-toolbar" style="padding-right: 20px; text-align: left; padding-top: 5px; padding-bottom: 5px;" borderStyle="border:0;">
				<%
					UserObject user = (UserObject) session.getAttribute("userObject");
					String manage = "";
					DataObject[] roles = (DataObject[]) user.getAttributes().get(
							"roles");
					if (null != roles && roles.length > 0) {
						for (int i = 0; i < roles.length; i++) {
							DataObject role = roles[i];
							if ("eosadmin".equals(role.get("roleid"))) {//总行系统管理员
								manage = "true";
							} else {
								continue;
							}
						}
					}
					if (manage.equals("true")) {
				%>
				<a id="upload" class="nui-button" iconCls="icon-upload" onclick="impExcel()">上传</a>
				<%
					} else {
				%>
				<a id="remove" class="nui-button" iconCls="icon-remove" onclick="removeRela()">转出</a>
				<%
					}
				%>

			</div>

			<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto;" allowAlternating="true" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" allowResize="false" showReloadButton="false" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field=partyName allowSort="true" headerAlign="center">客户名称</div>
					<div field=certType allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
					<div field="certNum" allowSort="true" headerAlign="center">证件号码</div>
					<div field="cgPoint" allowSort="true" headerAlign="center">占股比例</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		//var grid2 = nui.get("datagrid2");
		function queryInit() {
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function initRole() {
			nui.get('upload').hide();
		}

		function reset() {
			form.reset();
			queryInit();
		}
		/* function upload(){
			//打开上传面
			nui.open({
				url : nui.context + "/pub/lst/upload_rela.jsp",
				showMaxButton : true,
				title : "上传文件",
				width : 800,
				height : 300,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					queryInit();
				}
			});
		} */

		//退出我行关联方名单
		function removeRela() {
			var row = grid.getSelected();
			if (row == null) {
				alert('请选择一条记录');
				return;
			}
			//var json = nui.encode({item:row});//传整条记录
			//将记录的主键传过去
			var json = nui.encode({
				item : {
					'rId' : row.rId
				}
			});
			//alert(json);
			nui.confirm("确定删除吗？", "确认", function(action) {
				if (action == "ok") {
					$.ajax({
						url : "com.bos.pub.lst.lst.removeRelaList.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							//nui.alert(text.msg);
							queryInit();
						},
						error : function() {
							nui.alert("操作失败!");
						}
					});
				}
			})
		}

		function exportEmp() {
			var frm = document.getElementById("form2");
			frm.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile";
			frm.submit();
		}

		function impExcel() {
			//打开上传面
			nui.open({
				url : nui.context + "/com.bos.lst.lstRela.flow",
				showMaxButton : true,
				title : "上传文件",
				width : 800,
				height : 300,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					queryInit();
				}
			});
		}
	</script>
</body>
</html>