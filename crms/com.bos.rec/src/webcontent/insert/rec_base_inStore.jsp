<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ganquan@git.com.cn
-->
<head>
<style>
</style>
<title>档案入库</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<script type="text/javascript"
	src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
	<center>
		<div id="form1" style="width: 99.5%; height: 99.5%; overflow: hidden;">
			<fieldset>
				<legend>
					<span>移交信息</span>
				</legend>
				<div class="nui-dynpanel" id="table1" columns="2">
					<label>移交人：</label> 
					<input id="item.transferPerson" name="item.transferPerson" required="true"
						allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32" />
					
					<label>接收人：</label> 
					<input id="" name="" value="<%=((UserObject)session.getAttribute("userObject")).getUserName() %>" enabled="false" required="true"
						class="nui-textbox nui-form-input" />
						
					<label>出入库时间：</label> 
					<input id="" name="" value="<%=GitUtil.getBusiDateStr()%>" enabled="false" required="true"
						class="nui-textbox nui-form-input" />
						
					<label>主体材料档案盒编号：</label> 
					<input id="recordNum1" name="recordNum1" required="false"
						class="nui-textbox nui-form-input" />
				    <label>业务材料档案盒编号：</label> 
					<input id="recordNum2" name="recordNum2" required="false"
						class="nui-textbox nui-form-input" />	
				   
					<input id="type" name="type" value="2" required="true"
						class="nui-hidden nui-form-input" />
				</div>
			</fieldset>
			<div class="nui-toolbar"
				style="border: 0; text-align: right; padding-right: 20px;">
				<a id="btnSave" class="nui-button" style="margin-right: 5px;"
					iconCls="icon-save" onclick="add">保存</a> <a id="btnClose"
					class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
		</div>
	</center>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		function add() {
			form.validate();
			if (form.isValid() == false)
				return;
			git.mask("form1");
			var rows =  window.Owner.grid.getSelecteds(); 
			var o = form.getData();
			o.rows = rows;
			var json = nui.encode(o);
			$
					.ajax({
						url : "com.bos.rec.rec.inStore.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							git.unmask("form1");
							if (text.msg) {
								nui.alert(text.msg);
								if (text.msg=='入库成功') {
									CloseWindow("ok");
								}
							} else {
								nui.alert("系统异常");
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							git.unmask("form1");
							nui.alert(jqXHR.responseText);
						}
					});
		}
		
		
		// 移交人
	function selectCustManegers(e) {
			var btnEdit = this;
			nui.open({
						url : nui.context
								+ "/pub/orgDemolition/creditMove/userManageALL.jsp",
						showMaxButton : true,
						title : "选择移交人",
						width : 800,
						height : 500,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.getData();
								data = nui.clone(data);
								if (data) {
									//  alert(nui.encode(data));
									btnEdit.setValue(data.userId);
									btnEdit.setText(data.empName);
								}
							}
						}
					});

	}
	</script>
</body>
</html>

