<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): ganquan@git.com.cn
-->
<head>
<style>
</style>
<title>档案借用</title>
<%@include file="/common/nui/common.jsp"%>
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
					<label>接收人：</label> 
					<input id="item.lendPerson" name="item.lendPerson" required="true"
						allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers" vtype="maxLength:32"  />
					<label>移交人：</label> 
					<input id="" name="" value="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>" enabled="false" required="true"
						class="nui-textbox nui-form-input" />
					<label>借用期限(天)：</label> 
					<input id="item.lendDeadline" name="item.lendDeadline" required="true"
						class="nui-textbox nui-form-input" onblur="genDate" vtype="int;maxLength:8;range:1,99999"/>
					<label>归还日期：</label> 
					<input id="item.comeBackDate" name="item.comeBackDate" required="true" enabled="false"
						class="nui-datepicker nui-form-input" showTime="true" format="yyyy-MM-dd" />
					<label>备注：</label> 
					<input id="item.remark" name="item.remark" required="false"
						class="nui-textbox nui-form-input" />
					
					<input id="type" name="type" value="3" required="true"
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
						url : "com.bos.rec.rec.borrow.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							git.unmask("form1");
							if (text.msg) {
								nui.alert(text.msg);
								if (text.msg=='借用成功') {
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
		
		function genDate(){
			nui.get('item.comeBackDate').setValue(getthedate('<%=GitUtil.getBusiDateStr()%>',nui.get('item.lendDeadline').getValue()));
		}
		
		function getthedate(dd,dadd){
			var a = new Date();
			a=a.valueOf();
			a=a+dadd*24*60*60*1000;
			a= new Date(a);
			var m = a.getMonth()+1;
			if(m.toString().length==1){
				m='0'+m;
			}
			var d = a.getDate();
			if(d.toString().length==1){
				d='0'+d;
			}
			return a.getFullYear()+'-'+m+'-'+d;
		}
			//接受人
	function selectCustManegers(e) {
			var btnEdit = this;
			nui.open({
						url : nui.context
								+ "/pub/orgDemolition/creditMove/userManageALL.jsp",
						showMaxButton : true,
						title : "选择接受人",
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

