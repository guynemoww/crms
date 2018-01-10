<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-07-10 16:04:05
  - Description:
-->
<head>
<title>核销收回操作</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width: 99.5%; height: 99.5%;">
		<fieldset>
			<legend>
				<span>核销收回</span>
			</legend>
			<div class="nui-dynpanel" id="table1" columns="4">
				<label>借据编号：</label> 
				<input name="dueNum" required="false" class="nui-textbox" id="dueNum" enabled="false" />
				<label>还款总金额：</label>
				<input id="padUpAmt" class="nui-textbox" name="padUpAmt" /> 
				<label>核销本金：</label>
				<input id="rcvPrn" class="nui-textbox" name="rcvPrn" enabled="false" />
				<label>核销表内利息：</label> 
				<input id="rcvNorItrIn" class="nui-textbox" name="rcvNorItrIn" enabled="false" />
				<label>核销表外利息：</label> 
				<input id="rcvNorItrOut" class="nui-textbox" name="rcvNorItrOut" enabled="false" /> 
				<label>核销未结计利息：</label>
				<input id="rcvOtdItr" class="nui-textbox" name="rcvOtdItr" enabled="false" />
				<label>还款账号：</label>
				<input id="payPrimAcct" class="nui-textbox" name="payPrimAcct" />
			    <label>还款账户名称：</label>
				<input id="payPrimName" class="nui-textbox" name="payPrimName" />
			</div>
		</fieldset>
		<div class="nui-toolbar"
			style="border: 0; text-align: right; padding-right: 20px;">
			<a id="btnSave" class="nui-button" style="margin-right: 5px;"
				iconCls="icon-save" onclick="verifyBackEnter()">核销收回</a> <a
				id="btnClose" class="nui-button" iconCls="icon-close"
				onclick="CloseWindow()">关闭</a>
		</div>
	</div>
	<script type="text/javascript">
    	nui.parse();
    	var form = new nui.Form("#form1");
    	var summaryNum = "<%=request.getParameter("summaryNum")%>";
    	var telNo = "<%=request.getParameter("nftNo")%>";
    	var loanOrg = "<%=request.getParameter("loanOrg")%>";
		initPage();
		function initPage() {
			var json = nui.encode({
				"summaryNum" : summaryNum,
				"telNo" : telNo,
				"loanOrg" : loanOrg
			});
			$.ajax({
				url : "com.bos.pub.dao.verifyBackControl.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				cache : false,
				success : function(mydata) {
					var o = nui.decode(mydata.items);
					form.setData(o);
				}
			});
		}
		function verifyBackEnter() {
			var padUpAmt = nui.get("padUpAmt").getValue();
			var payPrimAcct = nui.get("payPrimAcct").getValue();
			var payPrimName = nui.get("payPrimName").getValue();
			var json1 = nui.encode({
				"dueNum" : summaryNum,
				"telNo" : telNo,
				"padUpAmt" : padUpAmt,
				"payPrimAcct" : payPrimAcct,
				"payPrimName" : payPrimName
			});
			$.ajax({
				url : "com.bos.pub.dao.verifyBack.biz.ext",
				type : 'POST',
				data : json1,
				cache : false,
				contentType : 'text/json',
				cache : false,
				success : function(mydata) {
					var result = mydata.result;
					if (result != "1") {
						nui.alert(result);
					} else {
						nui.alert("核销收回成功!");
						CloseWindow('ok');
					}
				}
			});
		}
	</script>
</body>
</html>