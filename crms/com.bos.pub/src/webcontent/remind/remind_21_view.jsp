<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2014-08-25 14:12:10
  - Description:逾期/垫款提示
-->
<head>
<title>逾期/垫款提示</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input" />

			<label>提示日期：</label>
			<div>
				<input id="item.stDate" name="item.stDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width: 160px" />
				
				<input id="item.enDate" name="item.enDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" style="width: 160px" />
			</div>
		</div>
		<div class="nui-toolbar" style="text-align: right; border: none;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div class="nui-fit">
		<div id="grid1" class="nui-datagrid" style="height: 100%;" sortMode="client" url="com.bos.pub.Remind.getfkwrkWarning.biz.ext" dataField="items" multiSelect="false">
			<div property="columns">
				<div type="indexcolumn">序号</div>
				<div field="PARTY_NAME" headerAlign="center" allowSort="true">客户名称</div>
				<div field="CONTRACT_NUM" headerAlign="center" allowSort="true">合同编号</div>
				<div field="PRODUCT_NAME" headerAlign="center" allowSort="true" >贷款品种</div>
				<div field="END_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">预计登记到期日期</div>
				<div field="OPERATORNAME" headerAlign="center" allowSort="true">经办人</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var grid = nui.get("grid1");
		var remindType =<%=JspUtil.getParameterHaveSign(request, "remindType")%>;
		var exsql = "com.bos.pub.remind.select_" + remindType + "_id";
		//初始化提醒状态
//		var arr = git.getDictDataFilter("YP_YJCD0002", "01,02,03");
//		nui.get("item.remindStatus").setData(arr);
//		nui.get("item.remindStatus").setValue("01");

		grid.on("preload",
						function(e) {
							if (!e.data || e.data.length < 1)
								return;
							for (var i = 0; i < e.data.length; i++) {
								e.data[i]['CONTRACT_NUM'] = '<a href="#" onclick="bizView3231(\''
										+ e.data[i].CONTRACT_NUM
										+ '\');">'
										+ e.data[i].CONTRACT_NUM + '</a>'
							}
						});

		function query() {
			var o = form.getData(); //获取表单多个控件的数据
			o.remindType = remindType;
			grid.load(o);
		}
		query();
		function reset() {
			form.reset();
		}
		function toDo(remindId, status) {

			//更新提示状态
			var json = nui.encode({
				"remindId" : remindId,
				"remindStatus" : status
			});
			nui.ajax({
				url : "com.bos.pub.Remind.updateRemindInfoStatus.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						alert(text.msg);
					} else {
						grid.reload();
					}
				}
			});
		}
	</script>
</body>
</html>