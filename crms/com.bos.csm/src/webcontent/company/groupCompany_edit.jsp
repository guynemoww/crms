<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript"
	src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-5-12 12:42:24
  - Description:
-->
<head>
<title>修改集团客户信息</title>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<div id="form1">
		<fieldset>
			<legend>
				<span>集团客户信息</span>
			</legend>
			<input class="nui-hidden" id="party.partyId" name="party.partyId" value="<%=request.getParameter("partyId")%>" />
			<div class="nui-dynpanel" id="table1" columns="4">
				<label>集团客户编号：</label> 
				<input id="party.partyNum" name="party.partyNum" enabled="false" vtype="maxLength:32"
					class="nui-textbox nui-form-input" />
				<label>集团客户名称：</label> 
				<input id="party.partyName" name="party.partyName" required="true" vtype="maxLength:300"
				 	class="nui-textbox nui-form-input"  />
			</div>
		</fieldset>
		<div class="nui-toolbar" style="border: 0; text-align: right; padding-right: 20px;">
			<a id="btnSave" class="nui-button" style="margin-right: 5px;"iconCls="icon-save" onclick="edit()">保存</a> 
			<a id="btnClose" class="nui-button" iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		//------------------页面动态控制区------------------//
		var grid = nui.get("grid"); 
		var partyId="<%=request.getParameter("partyId")%>";
			
		$(document).ready(function() {
			var json = nui.encode({"partyId" : partyId});
			$.ajax({
				url : "com.bos.csm.company.company.findCompanyDetial.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					form.setData(text);
					//备份数据
					window.form1Data = form.getData();
				}
			});
		});


		//------------------事件操作区-----------------//
	function edit() {
			form.validate();
				if (form.isValid() == false) {
					nui.alert("请将集团客户信息填写正确");
					return;
				}
			
			//集团客户名不能重复
			var json = {"partyName" : nui.get("party.partyName").getValue(),"partyId" : partyId};
			msg = exeRule("RCSM_0115", "1", json);
			if (null != msg && '' != msg) {
					nui.alert(msg);
					return;
			}	
			
			git.mask("form1");
			var o = form.getData();
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.csm.company.company.updateCompany.biz.ext",//updateCompany
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					/* if(text.map.msg!="AAAAAAA"){
						alert(text.map.msgg);
						return;
					} */
					if (text.msg) {
						alert(text.msg);
						return;
					} else {
						nui.alert("修改成功！");
						CloseWindow();
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

