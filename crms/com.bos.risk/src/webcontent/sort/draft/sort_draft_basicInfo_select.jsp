<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<!--
		- Author(s): caohaijie - Date: 2015-6-24 10:38:33 - Description:选中菜单的子菜单列表，tab页中使用
	-->
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<div id="form1" style="width:99%;height:auto;overflow:hidden;">
			<input name="item.partyId" id="item.partyId" class="nui-hidden" />
			<input name="item.acApplyId" id="item.acApplyId" class="nui-hidden" />
			<div class="nui-dynpanel" columns="4">
				<label class="nui-form-label">客户名称：</label>
				<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input"  readonly="true" Enabled="false"/>
				
				<label class="nui-form-label">通讯地址：</label>
				<input id="item.addressValue"  name="item.addressValue" class="nui-textbox nui-form-input" readonly="true" Enabled="false"/>
				
				<label class="nui-form-label">授信额度：</label>
				<input id="item.creditAmt" name="item.creditAmt" class="nui-textbox nui-form-input"  readonly="true" dataType="currency" Enabled="false"/>
				
				<label class="nui-form-label">授信余额：</label>
				<input id="item.availableAmt" name="item.availableAmt" class="nui-textbox nui-form-input" readonly="true" dataType="currency" Enabled="false"/>
				
				<label class="nui-form-label">经营范围：</label>
				<input id="item.businessScope" colspan="3" name="item.businessScope" class="nui-textarea" readonly="true" Enabled="false"/>
				
			</div>
		</div>

		<script type="text/javascript">
			nui.parse();
	  		var form = new nui.Form("#form1");
			var partyId = "<%=request.getParameter("partyId")%>";
			var acApplyId = "<%=request.getParameter("acApplyId")%>";
			//初始化页面
		    $(document).ready(function(){
				var json = nui.encode({"acApplyId":acApplyId});
				$.ajax({
			        url: "com.bos.risk.sort.queryBasicInfoSelect.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	git.unmask("form1");
		               	//var data = nui.decode(text);
		               	form.setData(text);
						//备份数据
		                window.form1Data=form.getData();
			        }
		    	});
			});
		</script>
	</body>
</html>
