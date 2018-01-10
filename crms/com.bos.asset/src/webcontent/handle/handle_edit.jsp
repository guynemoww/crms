<%@page import="com.bos.pub.web.JspUtil"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2017-05-23 20:32:11
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		String enable = JspUtil.getParameter(request, "enable", "false");
	%>
	<div id="form" class="nui-form">
		<input id="plan.id" name="plan.id" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>方案编号：</label>
			<input id="plan.planNum" name="plan.planNum" class="nui-textbox" enabled="false" />
			<label>方案名称：</label>
			<input id="plan.planName" name="plan.planName" class="nui-textbox" enabled="<%=enable%>" vtype="maxLength:30" />
			<label>处置方式：</label>
			<input id="plan.planType" name="plan.planType" class="nui-dictcombobox" enabled="false" required="true" dictTypeId="XD_ASSET001" />
			<label id="plan.cleanTakeType_label">清收登记方式：</label>
			<input id="plan.cleanTakeType" name="plan.cleanTakeType" class="nui-dictcombobox" enabled="false" required="true" dictTypeId="XD_ASSET003" />
			<label>方案申请人：</label>
			<input id="plan.regUserId" name="plan.regUserId" class="nui-text" dictTypeId="user" enabled="false" />
			<label>申请机构：</label>
			<input id="plan.regOrgId" name="plan.regOrgId" class="nui-text" dictTypeId="org" enabled="false" />
			<label>申请日期：</label>
			<input id="plan.regDate" name="plan.regDate" class="nui-text" enabled="false" />
			<label>审批状态：</label>
			<input id="plan.status" name="plan.status" class="nui-dictcombobox" enabled="false" dictTypeId="XD_ASSET002" />
		</div>
		<table style="width: 100%;">
			<tr>
				<td style="width: 20%; text-align: right;">
					<label>重要意见：</label>
				</td>
				<td style="width: 60%; height: 65px">
					<input id="plan.opinion" name="plan.opinion" class="nui-textarea" enabled="<%=enable%>" style="width: 100%; height: 100%" vtype="maxLength:400" />
				</td>
				<td style="width: 20%;"></td>
			</tr>
		</table>
		<div class="nui-toolbar" style="text-align: right; border: none;">
			<a class="nui-button" id="save_button" iconCls="icon-save" enabled="<%=enable%>" onclick="save">保存</a>
		</div>
	</div>

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form");
		initPage();

		function initPage() {
			var json = {
				planId :
	<%=JspUtil.getParameterHaveSign(request, "planId")%>
		};
			json = nui.encode(json);
			$.ajax({
				url : "com.bos.asset.AssetsHandle.getHandle.biz.ext",
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(mydata) {
					//debugger;
					var o = nui.decode(mydata);
					form.setData(o);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				}
			});
		}

		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请按规则填写信息");
				return;
			}
			nui.get("save_button").setEnabled(false);
			//debugger;
			var o = form.getData();
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.asset.AssetsHandle.saveHandle.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				async : false,
				contentType : 'text/json',
				success : function(text) {
					if (text.msg) {
						nui.alert(text.msg); //失败时后台直接返回出错信息
					} else {
						nui.alert(actionSuccess);
					}
					nui.get("save_button").setEnabled(true);
				}
			});
		}
	</script>

</body>
</html>